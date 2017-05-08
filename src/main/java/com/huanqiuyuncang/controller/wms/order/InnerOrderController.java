package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.controller.wms.customer.CustomerController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.order.PingZhengEnetity;
import com.huanqiuyuncang.entity.order.ProductOrderBase;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.order.InnerOrderInterface;
import com.huanqiuyuncang.service.order.OrderProductInterface;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lzf on 2017/4/11.
 */
@Controller
@RequestMapping(value="/innerorder")
public class InnerOrderController extends BaseController {
    String menuUrl = "innerorder/list.do"; //菜单地址(权限用)
    @Autowired
    private InnerOrderInterface innerOrderService;
    @Autowired
    private CustomerInterface customerService;
    @Autowired
    private ProductInterface productService;
    @Autowired
    private OrderProductInterface orderProductService;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(InnerOrderEntity innerOrder) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增innerorder");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        innerOrder.setInnerorderid(this.get32UUID());
        innerOrder.setCreateuser(username);
        innerOrder.setCreatetime(date);
        innerOrder.setUpdateuser(username);
        innerOrder.setUpdatetime(date);
        innerOrder.setOrdermultistatus(CustomerController.CUSTOMERSTATUS);
        String token = (String)this.getRequest().getSession().getAttribute("token");
        innerOrderService.insertOrderInfo(innerOrder,token);
        this.getRequest().getSession().removeAttribute("token");
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    /**删除
     * @param out
     * @throws Exception
     */
    @RequestMapping(value="/delete")
    public void delete(PrintWriter out) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"删除innerorder");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = new PageData();
        pd = this.getPageData();
        String innerorderid = pd.getString("innerorderid");
        innerOrderService.deleteByPrimaryKey(innerorderid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(InnerOrderEntity innerOrder) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改innerorder");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        innerOrder.setUpdatetime(date);
        innerOrder.setUpdateuser(username);
        innerOrderService.updateByPrimaryKeySelective(innerOrder);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表innerorder");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<InnerOrderEntity> varList =   innerOrderService.datalistPage(page);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        varList.forEach(innerOrderEntity -> {
            String formateCreateTime = formatter.format(innerOrderEntity.getOrdertime());
            innerOrderEntity.setFormateOrderTime(formateCreateTime);
            String recipientprovince = innerOrderEntity.getRecipientprovince();
            String recipientcity = innerOrderEntity.getRecipientcity();
            recipientprovince = innerOrderService.selectProvinceNameByCode(recipientprovince);
            recipientcity = innerOrderService.selectCityNameByCode(recipientcity);
            innerOrderEntity.setRecipientprovince(recipientprovince);
            innerOrderEntity.setRecipientcity(recipientcity);
        });
        List<CustomsEntity> customerList = getCustomsList();
        mv.setViewName("wms/innerorder/innerorder_list");
        mv.addObject("varList", varList);
        mv.addObject("customerList", customerList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**去新增页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goAdd")
    public ModelAndView goAdd()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        List<CustomsEntity> customerList = getCustomsList();
        String baoguan_ID = "d67d48a2aa434a8995cc3aa0d2b24756";
        String orderStatus_ID = "94809020e5b847de824c4b39e20c4e5f";
        List<PageData> baoguanList = innerOrderService.selectDictionaries(baoguan_ID);
        List<PageData> orderStatusList = innerOrderService.selectDictionaries(orderStatus_ID);
        String token = this.get32UUID();
        this.getRequest().getSession().setAttribute("token",token);
        mv.setViewName("wms/innerorder/innerorder_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        mv.addObject("customerList", customerList);
        mv.addObject("baoguanList", baoguanList);
        mv.addObject("orderStatusList", orderStatusList);
        mv.addObject("token", token);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**去修改页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goEdit")
    public ModelAndView goEdit()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String innerorderid = pd.getString("innerorderid");
        InnerOrderEntity innerorderEntity = innerOrderService.selectByPrimaryKey(innerorderid);//根据ID读取
        List<CustomsEntity> customerList = getCustomsList();
        String baoguan_ID = "d67d48a2aa434a8995cc3aa0d2b24756";
        String orderStatus_ID = "94809020e5b847de824c4b39e20c4e5f";
        List<PageData> baoguanList = innerOrderService.selectDictionaries(baoguan_ID);
        List<PageData> orderStatusList = innerOrderService.selectDictionaries(orderStatus_ID);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formateCreateTime = formatter.format(innerorderEntity.getCreatetime());
        String formateUpdateTime = formatter.format(innerorderEntity.getUpdatetime());
        String formateOrderTime = formatter.format(innerorderEntity.getOrdertime());
        innerorderEntity.setFormatCreateTime(formateCreateTime);
        innerorderEntity.setFormateUpdateTime(formateUpdateTime);
        innerorderEntity.setFormateOrderTime(formateOrderTime);
        this.getRequest().getSession().setAttribute("token", innerorderEntity.getCustomerordernum());
        mv.setViewName("wms/innerorder/innerorder_edit");
        mv.addObject("msg", "edit");
        mv.addObject("innerorder", innerorderEntity);
        mv.addObject("pd", pd);
        mv.addObject("customerList", customerList);
        mv.addObject("baoguanList", baoguanList);
        mv.addObject("orderStatusList", orderStatusList);
        mv.addObject("token", innerorderEntity.getCustomerordernum());
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**批量删除
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/deleteAll")
    @ResponseBody
    public Object deleteAll() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"批量删除innerorder");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }


    @RequestMapping(value="/orderpdlist")
    public void orderpdlist(PrintWriter printWriter,String customerordernum) throws Exception{
        List<OrderProductEntity> list = orderProductService.selectOrderProduct(customerordernum);
        String json = JSONArray.fromObject(list, DateJsonConfig.getJsonConfig()).toString();
        String resultJson = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
        printWriter.write(resultJson);
    }

    private List<CustomsEntity> getCustomsList() {
        List<CustomsEntity> customerList = null;
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("customerlist")){
            customerList = customerService.selectAll();
        }else{
            String createUser = Jurisdiction.getUsername();
            customerList = customerService.selectByCreateUser(createUser);
        }
        return customerList;
    }



    @RequestMapping(value="/goAddProduct")
    public ModelAndView goAddProduct()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("wms/innerorder/innerorder_orderpd");
        mv.addObject("msg", "saveOrderProduct");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/goEditProduct")
    public ModelAndView goEditProduct()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String orderproducrtid  = pd.getString("orderproducrtid");
        OrderProductEntity orderProductEntity = orderProductService.selectByPrimaryKey(orderproducrtid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formateCreateTime = formatter.format(orderProductEntity.getCreatetime());
        String formateUpdateTime = formatter.format(orderProductEntity.getUpdatetime());
        orderProductEntity.setFormatCreateTime(formateCreateTime);
        orderProductEntity.setFormateUpdateTime(formateUpdateTime);
        mv.setViewName("wms/innerorder/innerorder_orderpd");
        mv.addObject("msg", "editOrderProduct");
        mv.addObject("orderProduct",orderProductEntity);
        mv.addObject("pd", pd);
        return mv;
    }



    @RequestMapping(value="/saveOrderProduct")
    public ModelAndView saveOrderProduct(OrderProductEntity orderProductEntity)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        Date date = new Date();
        String username = Jurisdiction.getUsername();
        String token = (String)this.getRequest().getSession().getAttribute("token");
        orderProductEntity.setCustomerordernum(token);
        orderProductEntity.setorderproducrtid(this.get32UUID());
        orderProductEntity.setCreateuser(username);
        orderProductEntity.setCreatetime(date);
        orderProductEntity.setUpdateuser(username);
        orderProductEntity.setUpdatetime(date);
        orderProductService.insert(orderProductEntity);
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/editOrderProduct")
    public ModelAndView editOrderProduct(OrderProductEntity orderProductEntity)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        orderProductService.updateByPrimaryKeySelective(orderProductEntity);
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/delOrderPd")
    @ResponseBody
    public Object delOrderPd(String orderproducrtid)throws Exception{
        PageData pd = new PageData();
        pd = this.getPageData();
        orderProductService.deleteByPrimaryKey(orderproducrtid);
        Map<String,String> map = new HashMap<String,String>();
        map.put("result", "success");
        return AppUtil.returnObject(new PageData(), map);
    }



    @RequestMapping(value="/getprovince")
    @ResponseBody
    public Object getprovince() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        List<PageData> pdList = innerOrderService.selectProvince();
        PageData pd = this.getPageData();
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/getCity")
    @ResponseBody
    public Object getCity() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String code = pd.getString("code");
        List<PageData> pdList = innerOrderService.selectCity(code);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/getArea")
    @ResponseBody
    public Object getArea() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String code = pd.getString("code");
        List<PageData> pdList = innerOrderService.selectArea(code);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**批量审核
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/shenheAll")
    @ResponseBody
    public Object shenheAll() throws Exception{
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.shenheAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/createpackage")
    @ResponseBody
    public Object createpackage() throws Exception{
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String id = pd.getString("id");
        if(null != id && !"".equals(id)){
            innerOrderService.createpackage(id);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }


    @RequestMapping(value="/pingzheng")
    public ModelAndView pingzheng()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String innerorderid = pd.getString("innerorderid");
        InnerOrderEntity order = innerOrderService.selectByPrimaryKey(innerorderid);//根据ID读取
        PingZhengEnetity pingZhengEnetity = new PingZhengEnetity();
        String customerName = customerService.selectNameByCode(order.getCustomernum()); //客户
        pingZhengEnetity.setCustomerName(customerName);
        pingZhengEnetity.setYewuyuan(order.getCreateuser());
        pingZhengEnetity.setOrderTime(format.format(order.getOrdertime()));
        pingZhengEnetity.setFukuanfangshi(order.getPaymentmethod());
        pingZhengEnetity.setDingdanbeizhu(order.getCustomerremarks());
        pingZhengEnetity.setYouxiaoqi("30天");
        List<OrderProductEntity> orderProductEntities = orderProductService.selectOrderProduct(order.getCustomerordernum());
        Double dingdanjine = new Double(0);
        for(OrderProductEntity orderProductEntity : orderProductEntities) {
            String retailprice = orderProductEntity.getDeclareprice();
            String count = orderProductEntity.getCount();
            dingdanjine = dingdanjine + (Double.parseDouble(retailprice)*Double.parseDouble(count));
        };
        pingZhengEnetity.setDingdanjine("");

        List<ProductOrderBase> list = new ArrayList<>();
        orderProductEntities.forEach(orderProduct ->{
            try {
                ProductOrderBase base = new ProductOrderBase();
                ProductEntity product = productService.findProductByBarCode(orderProduct.getBarcode());
                base.setProductnum(product.getProductnum());
                base.setProductname(product.getProductname());
                base.setCount(orderProduct.getCount());
                base.setPrice(orderProduct.getDeclareprice());
                base.setPrice(Double.toString(product.getCrossborderCourierfee()));
                base.setRemark(orderProduct.getRemark());
                list.add(base);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        pingZhengEnetity.setPdlist(list);
        mv.setViewName("wms/innerorder/orderPingzheng");
        mv.addObject("pd", pd);
        mv.addObject("pingZheng", pingZhengEnetity);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }


    /**打开上传EXCEL页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/goUploadExcel")
    public ModelAndView goUploadExcel()throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("wms/innerorder/uploadexcel");
        return mv;
    }



    /**下载模版
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/downExcel")
    public void downExcel(HttpServletResponse response)throws Exception{
        FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "order.xls", "order.xls");
    }

    /**从EXCEL导入到数据库
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/readExcel" , produces = "text/html;charset=UTF-8")
    public  ModelAndView readExcel(@RequestParam(value="excel",required=false) MultipartFile file) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        if (null != file && !file.isEmpty()) {
            String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
            String fileName =  FileUpload.fileUp(file, filePath, "innerorderexcel");							//执行上传
            List<PageData> orderList = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            List<PageData> orderPdList = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 1);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            String resturt = innerOrderService.saveOrderFromExcel(orderList,orderPdList);
            //*存入数据库操作======================================*//*
            if(StringUtils.isNotBlank(resturt)){
                StringBuffer start = new StringBuffer("请将一下错误数据摘出修改后重新导入：\n");
                start.append(resturt);
                mv.addObject("msg","product_error");
                mv.addObject("resturt",start.toString());
            }else{
                mv.addObject("msg","success");
            }

        }
        mv.addObject("pd",pd);
        mv.setViewName("save_result");
        return mv;
    }



}
