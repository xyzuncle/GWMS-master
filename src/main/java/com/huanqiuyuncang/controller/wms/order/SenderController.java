package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.order.SenderEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.order.SenderInterface;
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
import java.util.*;

/**
 * Created by lzf on 2017/8/21.
 */
@Controller
@RequestMapping(value="/sender")
public class SenderController extends BaseController {
    String menuUrl = "sender/list.do"; //菜单地址(权限用)

    @Autowired
    private SenderInterface senderService;

    @Autowired
    private CustomerInterface customerService;

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增luggagemail");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        SenderEntity sender = (SenderEntity) BeanMapUtil.mapToObject(pd, SenderEntity.class);
        sender.setId(this.get32UUID());
        sender.setCreateuser(username);
        sender.setCreatetime(date);
        sender.setUpdateuser(username);
        sender.setUpdatetime(date);
        senderService.insertSelective(sender);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        String msg = "success";
        senderService.deleteByPrimaryKey(id);
        out.write(msg);
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改luggagemail");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        SenderEntity sender = (SenderEntity) BeanMapUtil.mapToObject(pd, SenderEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        sender.setUpdateuser(username);
        sender.setUpdatetime(date);
        senderService.updateByPrimaryKeySelective(sender);
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
        logBefore(logger, Jurisdiction.getUsername()+"列表luggagemail");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<SenderEntity> varList =  senderService.datalistPage(page);
        mv.setViewName("wms/sender/sender_list");
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
        PageData pd = this.getPageData();
        List<CustomerEntity> customerList = getCustomerList();
        mv.addObject("customerList", customerList);
        mv.setViewName("wms/sender/sender_edit");
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
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        SenderEntity sender = senderService.selectByPrimaryKey(id);//根据ID读取
        List<CustomerEntity> customerList = getCustomerList();
        mv.addObject("customerList", customerList);
        mv.setViewName("wms/sender/sender_edit");
        mv.addObject("msg", "edit");
        mv.addObject("sender", sender);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            senderService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
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


    @RequestMapping(value="/getSenderInfo")
    @ResponseBody
    public Object getArea() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String customercode = pd.getString("customercode");
        List<SenderEntity> list = senderService.selectByCustomercode(customercode);
        Random rand = new Random();
        int randNum = rand.nextInt(list.size());
        map.put("sender", list.get(randNum));
        return AppUtil.returnObject(pd, map);
    }

}
