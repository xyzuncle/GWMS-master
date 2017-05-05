package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.controller.wms.customer.CustomerController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
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
    private InnerPackageInterface innerPackageService;

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
        List<InnerPackageEntity> varList =   innerPackageService.datalistPage(page);
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



    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(InnerPackageEntity innerPackageEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增innerPackage");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        innerPackageEntity.setInnerpackageid(this.get32UUID());
        innerPackageEntity.setCreateuser(username);
        innerPackageEntity.setCreatetime(date);
        innerPackageEntity.setUpdateuser(username);
        innerPackageEntity.setUpdatetime(date);
        innerPackageEntity.setPackagemultistatus(CustomerController.CUSTOMERSTATUS);
        String token = (String)this.getRequest().getSession().getAttribute("token");
        innerPackageService.insertSelective(innerPackageEntity);
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
        String innerpackageid = pd.getString("innerpackageid");
        innerPackageService.deleteByPrimaryKey(innerpackageid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(InnerPackageEntity innerPackageEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改innerpackageid");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        innerPackageEntity.setUpdatetime(date);
        innerPackageEntity.setUpdateuser(username);
        innerPackageService.updateByPrimaryKeySelective(innerPackageEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
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
        orderStatusList.remove(0);
        orderStatusList.remove(0);
        orderStatusList.remove(2);
        orderStatusList.remove(2);
        orderStatusList.remove(2);
        String token = this.get32UUID();
        List<PackageTypeEntity> packageTypeList = packageTypeService.selectAll();
        List<CartonEntity> cartonList = cartonService.selectAll();
        this.getRequest().getSession().setAttribute("token",token);
        mv.setViewName("wms/innerorder/innerpackage_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        mv.addObject("customerList", customerList);
        mv.addObject("baoguanList", baoguanList);
        mv.addObject("packageTypeList", packageTypeList);
        mv.addObject("cartonList", cartonList);
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

        return null;
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
