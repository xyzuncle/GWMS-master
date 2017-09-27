package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.dao.kuwei.BaoGuoKuWeiDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.order.*;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.system.Dictionaries;
import com.huanqiuyuncang.service.system.dictionaries.DictionariesManager;
import com.huanqiuyuncang.service.wms.kuwei.BaoGuoKuWeiInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.order.InnerOrderInterface;
import com.huanqiuyuncang.service.wms.order.OrderProductInterface;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.order.OrdernumInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
    @Resource(name="dictionariesService")
    private DictionariesManager dictionariesService;
    @Autowired
    private BaoGuoKuWeiInterface baoGuoKuWeiService;
    @Autowired
    private CangKuInterface cangKuService;
    @Autowired
    private OrdernumInterface ordernumService;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(InnerOrderEntity innerOrder) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增innerorder");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
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
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        OrdernumEntity ordernumEntity = ordernumService.selectByPrimaryKey(id);
        Integer sum = checkTable("wms_innerorder","innerorderid", ordernumEntity.getOrderinfo());
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            innerOrderService.deleteByPrimaryKey(id);
        }
        out.write(msg);
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
        PageData pd = this.getPageData();
        Map<String, String> hc = Jurisdiction.getHC();
        if(!hc.containsKey("findAllOrder")||"0".equals(hc.get("findAllOrder"))){
            pd.put("createuser",Jurisdiction.getUsername());
        }
        page.setPd(pd);
        List<OrderInfoDTO> varList =   innerOrderService.datalistPage(page);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal allprice = new BigDecimal(0);
         Double allcount = new Double(0);
        for(OrderInfoDTO order : varList) {
            BigDecimal price = new BigDecimal(order.getOrdervalue());
            allprice = allprice.add(price);
            allcount = allcount+Double.parseDouble(order.getOrderproductcount());

        };
        List<CustomerEntity> customerList = getCustomerList();
        mv.setViewName("wms/innerorder/innerorder_list");
        mv.addObject("varList", varList);
        mv.addObject("allprice", allprice);
        mv.addObject("allcount", allcount);
        mv.addObject("customerList", customerList);
        mv.addObject("pd", pd);
        mv.addObject("QX",hc);	//按钮权限
        return mv;
    }

    /**去新增页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goAdd")
    public ModelAndView goAdd()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String token = this.get32UUID();
        this.getRequest().getSession().setAttribute("token",token);
        makeSelectInfo(mv);
        mv.setViewName("wms/innerorder/innerorder_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
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
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        OrdernumEntity ordernumEntity = ordernumService.selectByPrimaryKey(id);
        InnerOrderEntity innerorderEntity = innerOrderService.selectByPrimaryKey(ordernumEntity.getOrderinfo());//根据ID读取
        makeSelectInfo(mv);
        makeFormatterTime(innerorderEntity);
        this.getRequest().getSession().setAttribute("token", ordernumEntity.getOrdernum());
        mv.setViewName("wms/innerorder/innerorder_edit");
        mv.addObject("msg", "edit");
        mv.addObject("innerorder", innerorderEntity);
        mv.addObject("pd", pd);
        mv.addObject("token", ordernumEntity.getOrdernum());
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    private void makeSelectInfo(ModelAndView mv) {
        List<CustomerEntity> customerList = getCustomerList();
        String baoguan_ID = "d67d48a2aa434a8995cc3aa0d2b24756";
        String orderStatus_ID = "94809020e5b847de824c4b39e20c4e5f";
        List<PageData> baoguanList = innerOrderService.selectDictionaries(baoguan_ID);
        List<PageData> orderStatusList = innerOrderService.selectDictionaries(orderStatus_ID);
        mv.addObject("customerList", customerList);
        mv.addObject("baoguanList", baoguanList);
        mv.addObject("orderStatusList", orderStatusList);
    }

    @RequestMapping(value="/goview")
    public ModelAndView goview()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        OrdernumEntity ordernumEntity = ordernumService.selectByPrimaryKey(id);
        InnerOrderEntity innerorderEntity = innerOrderService.selectByPrimaryKey(ordernumEntity.getOrderinfo());
        makeFormatterTime(innerorderEntity);
        this.getRequest().getSession().setAttribute("token", ordernumEntity.getOrdernum());
        makeSelectInfo(mv);
        mv.setViewName("wms/innerorder/innerorder_view");
        mv.addObject("msg", "view");
        mv.addObject("innerorder", innerorderEntity);
        mv.addObject("pd", pd);
        mv.addObject("token", ordernumEntity.getOrdernum());
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    private void makeFormatterTime(InnerOrderEntity innerorderEntity) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formateCreateTime = formatter.format(innerorderEntity.getCreatetime());
        String formateUpdateTime = formatter.format(innerorderEntity.getUpdatetime());
        String formateOrderTime = formatter.format(innerorderEntity.getOrdertime());
        innerorderEntity.setFormatCreateTime(formateCreateTime);
        innerorderEntity.setFormateUpdateTime(formateUpdateTime);
        innerorderEntity.setFormateOrderTime(formateOrderTime);
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
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/orderpdlist")
    public void orderpdlist(PrintWriter printWriter,String customerordernum) throws Exception{
        List<OrderProductEntity> list = orderProductService.selectOrderProduct(customerordernum);
        String json = JSONArray.fromObject(list, DateJsonConfig.getJsonConfig()).toString();
        String resultJson = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
        printWriter.write(resultJson);
    }

    private List<CustomerEntity> getCustomerList() {
        List<CustomerEntity> customerList = null;
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("customerlist")){
            customerList = customerService.selectAll();
        }else{
            String createUser = Jurisdiction.getUsername();
            customerList = customerService.selectByLoginName(createUser);
        }
        return customerList;
    }

    @RequestMapping(value="/goAddProduct")
    public ModelAndView goAddProduct()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        mv.setViewName("wms/innerorder/innerorder_orderpd");
        mv.addObject("msg", "saveOrderProduct");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/goOrderProductPage")
    public ModelAndView goOrderProductPage(String id)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        this.getRequest().getSession().setAttribute("OrdersId",id);
        mv.setViewName("wms/innerorder/innerorder_orderpd");
        mv.addObject("msg", "saveOrdersProduct");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/goEditProduct")
    public ModelAndView goEditProduct()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
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
        PageData pd = this.getPageData();
        Date date = new Date();
        String username = Jurisdiction.getUsername();
        String token = (String)this.getRequest().getSession().getAttribute("token");
        orderProductEntity.setCustomerordernum(token);
        orderProductEntity.setCreateuser(username);
        orderProductEntity.setCreatetime(date);
        orderProductEntity.setUpdateuser(username);
        orderProductEntity.setUpdatetime(date);
        orderProductService.insertOrderProduct(orderProductEntity);
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/saveOrdersProduct")
    public ModelAndView saveOrdersProduct(OrderProductEntity orderProductEntity)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        Date date = new Date();
        String username = Jurisdiction.getUsername();
        String ids = (String)this.getRequest().getSession().getAttribute("OrdersId");
        for(String id : ids.split(",")){
            OrdernumEntity ordernumEntity = ordernumService.selectByPrimaryKey(id);
            orderProductEntity.setCustomerordernum(ordernumEntity.getOrdernum());
            orderProductEntity.setCreateuser(username);
            orderProductEntity.setCreatetime(date);
            orderProductEntity.setUpdateuser(username);
            orderProductEntity.setUpdatetime(date);
            orderProductService.insertOrderProduct(orderProductEntity);
        }
        this.getRequest().getSession().removeAttribute("OrdersId");
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/editOrderProduct")
    public ModelAndView editOrderProduct(OrderProductEntity orderProductEntity)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        orderProductService.updateByPrimaryKeySelective(orderProductEntity);
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/delOrderPd")
    @ResponseBody
    public Object delOrderPd(String orderproducrtid)throws Exception{
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
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
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

    @RequestMapping(value="/pingzheng")
    public ModelAndView pingzheng()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String id = pd.getString("id");
        OrdernumEntity ordernumEntity = ordernumService.selectByPrimaryKey(id);
        InnerOrderEntity order = innerOrderService.selectByPrimaryKey(ordernumEntity.getOrderinfo());//根据ID读取
        PingZhengEnetity pingZhengEnetity = new PingZhengEnetity();
        String customerName = customerService.selectNameByCode(order.getCustomernum()); //客户
        pingZhengEnetity.setCustomerName(customerName);
        pingZhengEnetity.setYewuyuan(order.getCreateuser());
        pingZhengEnetity.setOrderTime(format.format(order.getOrdertime()));
        pingZhengEnetity.setFukuanfangshi(order.getPaymentmethod());
        pingZhengEnetity.setDingdanbeizhu(order.getCustomerremarks());
        pingZhengEnetity.setYouxiaoqi("30天");
        List<OrderProductEntity> orderProductEntities = orderProductService.selectOrderProduct(ordernumEntity.getOrdernum());
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

    @RequestMapping(value="/zuofeiAll")
    @ResponseBody
    public Object zuofeiAll() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.zuofeiAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/yichang")
    @ResponseBody
    public Object yichang() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.yichang(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }


    @RequestMapping(value="/goChuku")
    public ModelAndView goChuku()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String DATA_IDS = pd.getString("id");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            String customernum = innerOrderService.selectCustomernumByOrderNumId(ArrayDATA_IDS[0]);
            CustomerEntity customerEntity = customerService.selectCustomerByCode(customernum);
            String defaultwarehouse = customerEntity.getDefaultwarehouse();
            CangKuEntity cangKuEntity = cangKuService.selectByCangKu(defaultwarehouse);
            mv.addObject("cangkushuxing", cangKuEntity.getCangkushuxing());
            mv.addObject("cangkuid", cangKuEntity.getId());
            String customerstatus = customerEntity.getCustomerstatus();
            String[] split = customerstatus.split("_");
            if("0".equals(split[2])){
                mv.addObject("kuwei", "自定义库位");
            }else{
                mv.addObject("kuwei", "默认库位");
            }

        }
        setCangKuShuXing(mv);
        mv.setViewName("wms/innerorder/innerorder_chuku");
        mv.addObject("msg", "saveShangpinChuku");
        mv.addObject("id", DATA_IDS);
        mv.addObject("pd", pd);
        return mv;
    }

    private void setCangKuShuXing(ModelAndView mv) throws Exception {
        List<Dictionaries> dictionaries = dictionariesService.listSubDictByParentId("89bb990471364bbc8f17d7bb8755c522");
        mv.addObject("dictionaries",dictionaries);
    }


    @RequestMapping(value="/getCangku")
    @ResponseBody
    public Object getCangku() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String code = pd.getString("code");
        List<CangKuEntity> pdList = innerOrderService.getCangku(code);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/saveShangpinChuku")
    public ModelAndView saveShangpinChuku(String id,String cangkushuxing,String cangku,String kuwei) throws Exception{
        ModelAndView mv = this.getModelAndView();
        if(null != id && !"".equals(id)){
            String ids[] = id.split(",");
            innerOrderService.saveShangpinChuku(ids,cangkushuxing,cangku,kuwei);
        }

        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


    @RequestMapping(value="/gobaoguokuwei")
    public ModelAndView gobaoguokuwei()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List<CustomerEntity> customerList = getCustomerList();
        mv.setViewName("wms/innerorder/innerpackage_ruku");
        mv.addObject("msg", "saveBaoguoRuku");
        mv.addObject("customerList", customerList);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/saveBaoguoRuku")
    public ModelAndView saveBaoguoRuku(String id,String baoguokuwei) throws Exception{
        ModelAndView mv = this.getModelAndView();
        if(null != id && !"".equals(id)){
            String ids[] = id.split(",");
            innerOrderService.saveBaoguoRuku(ids,baoguokuwei);
        }
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    @RequestMapping(value="/getbaoguocangku")
    @ResponseBody
    public Object getbaoguocangku() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String code = pd.getString("code");
        List<BaoGuoKuWeiEntity> pdList = baoGuoKuWeiService.selectByCustomernum(code);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/getyujing")
    @ResponseBody
    public Object getyujing() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String dingdanhao = pd.getString("dingdanhao");
        OrdernumEntity order = ordernumService.selectByDingdanhao(dingdanhao);
        String yujing = order.getYujingstatus();
        if(StringUtils.isNotEmpty(yujing)){
            pd.put("BIANMA",yujing);
            PageData dic = dictionariesService.findByBianma(pd);
            yujing = dic.getString("NAME");
        }
        map.put("yujing", yujing);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/orderChildren")
    public void orderChildren(PrintWriter printWriter,String id) throws Exception{
        if(StringUtils.isNotEmpty(id)){
            List<OrdernumEntity> list = ordernumService.selectByPartentId(id);
            String json = JSONArray.fromObject(list, DateJsonConfig.getJsonConfig()).toString();
            String resultJson = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
            printWriter.write(resultJson);
        }else{
            printWriter.write("");
        }
    }

    @RequestMapping(value="/gohedan")
    public ModelAndView gohedan() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String starttime = pd.getString("starttime");
        String endtime = pd.getString("endtime");
        putSearchDate(starttime,endtime,pd);
        List<OrderInfoDTO> varList  =  innerOrderService.selectByhedan(pd);
        mv.setViewName("wms/innerorder/innerorder_hedan");
        mv.addObject("msg", "savehedan");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/hedan")
    public ModelAndView hedan(String DATA_IDS) throws Exception{
        ModelAndView mv = this.getModelAndView();
        innerOrderService.savehedan(DATA_IDS);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    @RequestMapping(value="/gochaidan")
    public ModelAndView gochaidan() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String starttime = pd.getString("starttime");
        String endtime = pd.getString("endtime");
        putSearchDate(starttime,endtime,pd);
        List<OrderInfoDTO> varList  =  innerOrderService.selectBychaidan(pd);
        mv.setViewName("wms/innerorder/innerorder_chaidan");
        mv.addObject("msg", "savechaidan");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/chaidan")
    public ModelAndView chaidan(String DATA_IDS) throws Exception{
        ModelAndView mv = this.getModelAndView();
        innerOrderService.savechaidan( DATA_IDS);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    private void putSearchDate(String starttime,String endtime, PageData pd) {
        String startDate = " 00:00:00";
        String endDate = " 23:59:59";
        startDate = starttime+startDate;
        endDate = endtime+endDate;
        pd.put("starttime",startDate);
        pd.put("endtime",endDate);
    }

    @RequestMapping(value="/toDingdanhaoExcel")
    public ModelAndView toDingdanhaoExcel() throws Exception{
        PageData pd = this.getPageData();
        List<String> varOList = ordernumService.selectOrdernumToexcel();
        Map<String, Object> dataMap = getDingdanhaoDataMap(varOList);
        ObjectExcelView erv = new ObjectExcelView();
        ModelAndView mv = new ModelAndView(erv,dataMap);
        return mv;
    }

    private Map<String,Object> getDingdanhaoDataMap(List<String> varOList) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("订单号");
        dataMap.put("titles", titles);
        List<PageData> varList = new ArrayList<PageData>();
        for(int i=0;i<varOList.size();i++){
            PageData vpd = new PageData();
            vpd.put("var1", varOList.get(i));
            varList.add(vpd);
        }
        dataMap.put("varList", varList);
        return  dataMap;
    }

    @RequestMapping(value="/toDingdanExcelForYT")
    public ModelAndView toDingdanExcelForYT() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> varOList = ordernumService.selectOrder4YT();
        Map<String, Object> dataMap = getOrderDataMap(varOList);
        ObjectExcelView erv = new ObjectExcelView();
        ModelAndView mv = new ModelAndView(erv,dataMap);
        return mv;
    }

    @RequestMapping(value="/tofenjiandan")
    public ModelAndView tofenjiandan(String DATA_IDS) throws Exception{
        String[] ids = DATA_IDS.split(",");
        for(String id:ids){
            List<PageData> fenJianDanInfo = ordernumService.selectFenjianDanInfoById(id);
            for (PageData pd : fenJianDanInfo) {
                String ordernum = pd.getString("ordernum");//订单号
                String recipient = pd.getString("recipient");//收件人
                String productnum = pd.getString("productnum");//商品货号
                String productname = pd.getString("productname");//商品名称
                String barcode = pd.getString("barcode");//商品条码
                String count = pd.getString("count");//商品数量
            }

        }
        List<PageData> zongFenJianDanInfo = ordernumService.selectZongFenJianDanInfoBy(DATA_IDS);
        for (PageData pd : zongFenJianDanInfo) {
            String productnum = pd.getString("productnum");//商品货号
            String productname = pd.getString("productname");//商品名称
            String barcode = pd.getString("barcode");//商品条码
            String count = pd.getString("count");//商品数量

        }
        return null;
    }

    private Map<String, Object> getOrderDataMap(List<PageData> varOList) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("订单号");	//1
        titles.add("商品名称");	//2
        titles.add("数量");	//3
        titles.add("卖家姓名");	//4
        titles.add("买家收货省");	//5
        titles.add("买家收货市");	//6
        titles.add("买家收货区");	//7
        titles.add("买家收货地址");	//8
        titles.add("卖家手机号");	//9
        titles.add("发件人");	//10
        titles.add("发件人电话");	//11
        titles.add("发件人省");	//12
        titles.add("发件人市");	//13
        titles.add("发件人区");	//14
        titles.add("发件地址");	//15
        titles.add("发件人邮编");	//16
        titles.add("代收货款");	//17
        titles.add("备注");	//18
        titles.add("买家邮编");	//19
        titles.add("买家电话");	//20
        titles.add("报价金额");	//21
        dataMap.put("titles", titles);
        List<PageData> varList = new ArrayList<PageData>();
        for(int i=0;i<varOList.size();i++){
            PageData pd = varOList.get(i);
            PageData vpd = new PageData();
            vpd.put("var1", pd.getString("ordernum"));
            vpd.put("var2", pd.getString("productname"));
            vpd.put("var3", pd.getString("recipient"));
            vpd.put("var4", pd.getString("COUNT"));
            vpd.put("var5", pd.getString("province"));
            vpd.put("var6", pd.getString("city"));
            vpd.put("var7", pd.getString("AREA"));
            vpd.put("var8", pd.getString("recipientaddress"));
            vpd.put("var9", pd.getString("recipientphone"));
            vpd.put("var10", pd.getString("sender"));
            vpd.put("var11", pd.getString("senderphone"));
            vpd.put("var12", pd.getString("sendercountry"));
            vpd.put("var13", pd.getString("sendercity"));
            vpd.put("var14", pd.getString("senderarea"));
            vpd.put("var15", pd.getString("senderaddress"));
            vpd.put("var16", pd.getString("senderpostcode"));
            vpd.put("var17", pd.getString("")); //代收货款
            vpd.put("var18", pd.getString("remark"));
            vpd.put("var19", pd.getString("recipientpostcode"));
            vpd.put("var20", pd.getString("recipientphone"));
            vpd.put("var21", pd.getString(""));//报价金额
            varList.add(vpd);
        }
        dataMap.put("varList", varList);
        return  dataMap;
    }


}
