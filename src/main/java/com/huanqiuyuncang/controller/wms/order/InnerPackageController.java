package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.controller.wms.customer.CustomerController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.InnerPackageEntity;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;
import com.huanqiuyuncang.service.order.InnerOrderInterface;
import com.huanqiuyuncang.service.order.InnerPackageInterface;
import com.huanqiuyuncang.service.order.OrderProductInterface;
import com.huanqiuyuncang.service.wms.carton.CartonInterface;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.packagetype.PackageTypeInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private ProductInterface productService;

    @Autowired
    private OrderProductInterface orderProductService;

    @Autowired
    private InnerOrderInterface innerOrderService;

    @Autowired
    private PackageTypeInterface packageTypeService;

    @Autowired
    private CartonInterface cartonService;

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表innerPackage");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<InnerOrderEntity> varList =   innerOrderService.datalistPage(page);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        varList.forEach(innerOrderEntity -> {
            String formateCreateTime = formatter.format(innerOrderEntity.getOrdertime());
            innerOrderEntity.setFormateOrderTime(formateCreateTime);
        });
        List<CustomsEntity> customerList = getCustomsList();
        mv.setViewName("wms/innerorder/innerpackage_list");
        mv.addObject("varList", varList);
        mv.addObject("customerList", customerList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }


    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(InnerOrderEntity innerOrder) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改innerpackageid");
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
        List<CartonEntity> cartonList = cartonService.selectAll();
        List<PackageTypeEntity> packageTypeList = packageTypeService.selectAll();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formateCreateTime = formatter.format(innerorderEntity.getCreatetime());
        String formateUpdateTime = formatter.format(innerorderEntity.getUpdatetime());
        String formateOrderTime = formatter.format(innerorderEntity.getOrdertime());
        innerorderEntity.setFormatCreateTime(formateCreateTime);
        innerorderEntity.setFormateUpdateTime(formateUpdateTime);
        innerorderEntity.setFormateOrderTime(formateOrderTime);
        this.getRequest().getSession().setAttribute("token", innerorderEntity.getCustomerordernum());
        mv.setViewName("wms/innerorder/innerpackage_edit");
        mv.addObject("msg", "edit");
        mv.addObject("innerorder", innerorderEntity);
        mv.addObject("cartonList", cartonList);
        mv.addObject("packageTypeList", packageTypeList);
        mv.addObject("pd", pd);
        mv.addObject("customerList", customerList);
        mv.addObject("baoguanList", baoguanList);
        mv.addObject("orderStatusList", orderStatusList);
        mv.addObject("token", innerorderEntity.getCustomerordernum());
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
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



}
