package com.huanqiuyuncang.controller.wms.customer;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.service.wms.customer.GongYingShangInterface;
import com.huanqiuyuncang.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by lzf on 2017/5/30.
 */
@Controller
@RequestMapping("gongyingshang")
public class GongYingShangController extends BaseController {

    String menuUrl = "gongyingshang/list.do"; //菜单地址(权限用)
    @Autowired
    private GongYingShangInterface gongYingShangService;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增gongYingShang");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        GongYingShangEntity gongYingShang = (GongYingShangEntity) BeanMapUtil.mapToObject(pd, GongYingShangEntity.class);
        gongYingShang.setGongyingshangid(this.get32UUID());
        gongYingShang.setCreateuser(username);
        gongYingShang.setCreatetime(date);
        gongYingShang.setUpdateuser(username);
        gongYingShang.setUpdatetime(date);
        gongYingShang.setGongyingshangstatus(CustomerController.CUSTOMERSTATUS);
        gongYingShangService.insertSelective(gongYingShang);
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
        logBefore(logger, Jurisdiction.getUsername()+"删除gongYingShang");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
        String gongyingshangid = pd.getString("gongyingshangid");
        Integer sum = checkTable("wms_gongyingshang","gongyingshangid", gongyingshangid);
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            gongYingShangService.deleteByPrimaryKey(gongyingshangid);
        }
        out.write(msg);
        out.close();

    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改gongYingShang");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        GongYingShangEntity gongYingShang = (GongYingShangEntity) BeanMapUtil.mapToObject(pd,GongYingShangEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        gongYingShang.setUpdatetime(date);
        gongYingShang.setUpdateuser(username);
        gongYingShangService.updateByPrimaryKeySelective(gongYingShang);
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
        logBefore(logger, Jurisdiction.getUsername()+"列表gongYingShang");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        pd.put("createuser",Jurisdiction.getUsername());
        //判断是否据有查看所有客户权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("gongyingshanglist")){
            pd.remove("createuser");
        }
        List<GongYingShangEntity> varList = gongYingShangService.datalistPage(page);
        mv.setViewName("wms/customer/gongyingshang_list");
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
        mv.setViewName("wms/customer/gongyingshang_edit");
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
        String gongyingshangid = pd.getString("gongyingshangid");
        GongYingShangEntity gongYingShang = gongYingShangService.selectByPrimaryKey(gongyingshangid);//根据ID读取
       /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");*/
        String formateCreateTime = DateUtil.format(gongYingShang.getCreatetime(),"yyyy-MM-dd");
        String formateUpdateTime = DateUtil.format(gongYingShang.getUpdatetime(),"yyyy-MM-dd");
        gongYingShang.setFormatCreateTime(formateCreateTime);
        gongYingShang.setFormateUpdateTime(formateUpdateTime);
        mv.setViewName("wms/customer/gongyingshang_edit");
        mv.addObject("msg", "edit");
        mv.addObject("gongyingshang", gongYingShang);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/goStatus")
    public ModelAndView goStatus()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String gongyingshangid = pd.getString("gongyingshangid");
        GongYingShangEntity gongYingShangEntity = gongYingShangService.selectByPrimaryKey(gongyingshangid);
        mv.setViewName("wms/customer/gongyingshang_statusview");
        mv.addObject("msg", "changestatus");
        mv.addObject("pd", pd);
        mv.addObject("gongyingshangid", gongyingshangid);
        mv.addObject("gongyingshangStatus", gongYingShangEntity.getGongyingshangstatus());
        return mv;
    }


    @RequestMapping(value="/changestatus")
    public ModelAndView changestatus() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String str = "";
        for(int i = 0;i<9 ; i++){
            str = str + pd.getString(""+i)+"_";
        }
        str =  str.substring(0,str.length()-1);
        String gongyingshangid =  pd.getString("gongyingshangid");
        GongYingShangEntity gongYingShang = gongYingShangService.selectByPrimaryKey(gongyingshangid);
        gongYingShang.setGongyingshangstatus(str);
        gongYingShangService.updateByPrimaryKeySelective(gongYingShang);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    /**批量删除
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/deleteAll")
    @ResponseBody
    public Object deleteAll() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"批量删除gongYingShang");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            Integer sum = checkTable("wms_gongyingshang","gongyingshangid", ArrayDATA_IDS);
            if(sum > 0){
                map.put("msg","error");
                return AppUtil.returnObject(pd, map);
            }
            gongYingShangService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/findgongyingshangByCode")
    @ResponseBody
    public Object findgongyingshangByCode(String gongyingshangcode) throws Exception{
        GongYingShangEntity gongYingShang = gongYingShangService.selectgongyingshangByCode(gongyingshangcode);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        if (gongYingShang != null){
            errInfo = "error";
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    @RequestMapping(value="/findgongyingshangByName")
    @ResponseBody
    public Object findgongyingshangByName(String gongyingshangname) throws Exception{
        GongYingShangEntity gongYingShang = gongYingShangService.selectgongyingshangByName(gongyingshangname);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        if (gongYingShang != null){
            errInfo = "error";
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }



}
