package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.dao.order.OrdernumDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrdernumEntity;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;
import com.huanqiuyuncang.service.wms.order.InnerOrderInterface;
import com.huanqiuyuncang.service.wms.carton.CartonInterface;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.order.OrdernumInterface;
import com.huanqiuyuncang.service.wms.packagetype.PackageTypeInterface;
import com.huanqiuyuncang.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by lzf on 2017/5/3.
 */
@Controller
@RequestMapping("innerpackage")
public class InnerPackageController extends BaseController {

    String menuUrl = "innerpackage/list.do"; //菜单地址(权限用)

    @Autowired
    private CustomerInterface customerService;

    @Autowired
    private InnerOrderInterface innerOrderService;

    @Autowired
    private PackageTypeInterface packageTypeService;

    @Autowired
    private CartonInterface cartonService;

    @Autowired
    private OrdernumInterface ordernumService;

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表innerPackage");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd.put("createuser",Jurisdiction.getUsername());
        page.setPd(pd);
        //判断是否据有查看所有权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("createuser");
        }
        List<InnerOrderEntity> varList =   innerOrderService.packagelistPage(page);
        varList.forEach(innerOrderEntity -> {
            String formateOrderTime = DateUtil.format(innerOrderEntity.getOrdertime(),"yyyy-MM-dd HH:mm:ss");
            innerOrderEntity.setFormateOrderTime(formateOrderTime);
        });
        List<CustomerEntity> customerList = getCustomerList();
        mv.setViewName("wms/innerorder/innerpackage_list");
        mv.addObject("varList", varList);
        mv.addObject("customerList", customerList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**去修改页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goView")
    public ModelAndView goView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        OrdernumEntity ordernumEntity = ordernumService.selectByPrimaryKey(id);
        InnerOrderEntity innerorderEntity = innerOrderService.selectByPrimaryKey(ordernumEntity.getOrderinfo());//根据ID读取
        formateDate(innerorderEntity);
        this.getRequest().getSession().setAttribute("token", ordernumEntity.getOrderinfo());
        makeSeletInfo(mv);
        mv.setViewName("wms/innerorder/innerpackage_view");
        mv.addObject("msg", "edit");
        mv.addObject("innerorder", innerorderEntity);
        mv.addObject("pd", pd);
        mv.addObject("token",  ordernumEntity.getOrderinfo());
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    private void formateDate(InnerOrderEntity innerorderEntity) {
        String formateCreateTime = DateUtil.format(innerorderEntity.getCreatetime(),"yyyy-MM-dd");
        String formateUpdateTime = DateUtil.format(innerorderEntity.getUpdatetime(),"yyyy-MM-dd");
        String formateOrderTime =  DateUtil.format(innerorderEntity.getOrdertime(),"yyyy-MM-dd");
        innerorderEntity.setFormatCreateTime(formateCreateTime);
        innerorderEntity.setFormateUpdateTime(formateUpdateTime);
        innerorderEntity.setFormateOrderTime(formateOrderTime);
    }

    private void makeSeletInfo(ModelAndView mv) throws Exception {
        List<CustomerEntity> customerList = getCustomerList();
        String baoguan_ID = "d67d48a2aa434a8995cc3aa0d2b24756";
        String orderStatus_ID = "94809020e5b847de824c4b39e20c4e5f";
        List<PageData> baoguanList = innerOrderService.selectDictionaries(baoguan_ID);
        List<PageData> orderStatusList = innerOrderService.selectDictionaries(orderStatus_ID);
        List<CartonEntity> cartonList = cartonService.selectAll();
        List<PackageTypeEntity> packageTypeList = packageTypeService.selectAll();
        mv.addObject("cartonList", cartonList);
        mv.addObject("packageTypeList", packageTypeList);
        mv.addObject("customerList", customerList);
        mv.addObject("baoguanList", baoguanList);
        mv.addObject("orderStatusList", orderStatusList);
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

    @RequestMapping(value="/makequeren")
    @ResponseBody
    public Object makequeren() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.makequeren(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/makeshenhe")
    @ResponseBody
    public Object makeshenhe() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            innerOrderService.makeshenhe(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }


    /**打开上传EXCEL页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/goUploadExcel")
    public ModelAndView goUploadExcel()throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("wms/innerorder/uploadpackageexcel");
        return mv;
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
            String resturt = innerOrderService.savePackageFromExcel(orderList,orderPdList);
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
