package com.huanqiuyuncang.controller.wms.customer;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lzf on 2017/4/12.
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    // 默认客户状态
    // 1.计算跨境速递费	是否外部商品转换	发货仓库
    // 2.按商品内部货值计算申报货值	收款状态	计算预计纸箱和包装及费用	计算运费

    public static final String CUSTOMERSTATUS = "1_1_1_1_1_1_1";

    String menuUrl = "customer/list.do"; //菜单地址(权限用)
    @Autowired
    private CustomerInterface customerService;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增CustomerEntity");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        CustomerEntity customerEntity = (CustomerEntity) BeanMapUtil.mapToObject(pd, CustomerEntity.class);
        String customercode = customerEntity.getCustomercode();
        customercode = username+"_"+customercode;
        customerEntity.setCustomercode(customercode);
        customerEntity.setCustomerid(this.get32UUID());
        customerEntity.setCreateuser(username);
        customerEntity.setCreatetime(date);
        customerEntity.setUpdateuser(username);
        customerEntity.setUpdatetime(date);
        customerEntity.setCustomerstatus(CUSTOMERSTATUS);
        customerService.insertSelective(customerEntity);
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
        logBefore(logger, Jurisdiction.getUsername()+"删除CustomerEntity");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = new PageData();
        pd = this.getPageData();
        String customerid = pd.getString("customerid");
        customerService.deleteByPrimaryKey(customerid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改CustomerEntity");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        CustomerEntity customerEntity = (CustomerEntity) BeanMapUtil.mapToObject(pd,CustomerEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        customerEntity.setUpdatetime(date);
        customerEntity.setUpdateuser(username);
        customerService.updateByPrimaryKeySelective(customerEntity);
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
        logBefore(logger, Jurisdiction.getUsername()+"列表CustomerEntity");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        pd.put("createuser",Jurisdiction.getUsername());
        //判断是否据有查看所有客户权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("customerlist")){
            pd.remove("createuser");
        }
        List<CustomerEntity> varList =   customerService.datalistPage(page);
        mv.setViewName("wms/customer/customer_list");
        mv.addObject("varList", varList);
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
        mv.setViewName("wms/customer/customer_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
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
        String packageid = pd.getString("packageid");
        CustomerEntity customerEntity = customerService.selectByPrimaryKey(packageid);//根据ID读取
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formateCreateTime = formatter.format(customerEntity.getCreatetime());
        String formateUpdateTime = formatter.format(customerEntity.getUpdatetime());
        customerEntity.setFormatCreateTime(formateCreateTime);
        customerEntity.setFormateUpdateTime(formateUpdateTime);
        mv.setViewName("wms/customer/customer_edit");
        mv.addObject("msg", "edit");
        mv.addObject("customer", customerEntity);
        mv.addObject("pd", pd);
        return mv;
    }

    /**批量删除
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/deleteAll")
    @ResponseBody
    public Object deleteAll() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"批量删除CustomerEntity");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            customerService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }
}
