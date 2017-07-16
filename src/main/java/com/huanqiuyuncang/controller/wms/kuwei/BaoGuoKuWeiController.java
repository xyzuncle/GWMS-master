package com.huanqiuyuncang.controller.wms.kuwei;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.kuwei.BaoGuoKuWeiInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/6/11.
 */
@Controller
@RequestMapping("baoguokuwei")
public class BaoGuoKuWeiController extends BaseController {
    String menuUrl = "baoguokuwei/list.do"; //菜单地址(权限用)
    @Autowired
    private BaoGuoKuWeiInterface baoGuoKuWeiService;
    @Autowired
    private CangKuInterface cangKuService;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        BeanMapUtil.setCreateUserInfo(pd);
        BeanMapUtil.setUpdateUserInfo(pd);
        BaoGuoKuWeiEntity baoGuoKuWei = (BaoGuoKuWeiEntity) BeanMapUtil.mapToObject(pd, BaoGuoKuWeiEntity.class);
        baoGuoKuWei.setId(this.get32UUID());
        baoGuoKuWeiService.insertSelective(baoGuoKuWei);
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
        Integer sum = checkTable("wms_baoguokuwei","id", id);
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            baoGuoKuWeiService.deleteByPrimaryKey(id);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        BeanMapUtil.setUpdateUserInfo(pd);
        BaoGuoKuWeiEntity baoGuoKuWei = (BaoGuoKuWeiEntity) BeanMapUtil.mapToObject(pd, BaoGuoKuWeiEntity.class);
        baoGuoKuWeiService.updateByPrimaryKeySelective(baoGuoKuWei);
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
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String USERNAME = Jurisdiction.getUsername();
        String role_name = gerRolename(USERNAME);
        if("仓库管理员".equals(role_name)){
            String cangkuid = pd.getString("cangku");
            if(cangkuid == null || StringUtils.isBlank(cangkuid)){
                List<CangKuEntity> cangkuList = cangKuService.selectByCangkuuser(USERNAME);
                if(cangkuList != null && cangkuList.size()>0){
                    String cangkuCodes = "";
                    for(CangKuEntity cangku : cangkuList){
                        cangkuCodes = cangkuCodes+cangku.getCangkubianhao()+",";
                    }
                    if(StringUtils.isNotBlank(cangkuCodes)){
                        cangkuCodes = cangkuCodes.substring(0,cangkuCodes.length()-1);
                        pd.put("cangku",cangkuCodes);
                    }
                }
            }
        }
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("cangku");
        }
        page.setPd(pd);
        List<BaoGuoKuWeiEntity> varList =  baoGuoKuWeiService.datalistPage(page);
        mv.setViewName("wms/baoguokuwei/baoguokuwei_list");
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
        mv.setViewName("wms/baoguokuwei/baoguokuwei_edit");
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
        BaoGuoKuWeiEntity baoGuoKuWei = baoGuoKuWeiService.selectByPrimaryKey(id);//根据ID读取
        mv.setViewName("wms/baoguokuwei/baoguokuwei_edit");
        mv.addObject("msg", "edit");
        mv.addObject("baoguokuwei", baoGuoKuWei);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }
    @RequestMapping(value="/gopackageuser")
    public ModelAndView gopackageuser()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String id = pd.getString("id");
        BaoGuoKuWeiEntity baoGuoKuWei = baoGuoKuWeiService.selectByPrimaryKey(id);//根据ID读取
        mv.setViewName("wms/baoguokuwei/baoguokuwei_packageuser");
        mv.addObject("msg", "edit");
        mv.addObject("baoguokuwei", baoGuoKuWei);
        mv.addObject("pd", pd);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            Integer sum = checkTable("wms_baoguokuwei","id", ArrayDATA_IDS);
            if(sum > 0){
                map.put("msg","error");
                return AppUtil.returnObject(pd, map);
            }
            baoGuoKuWeiService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }


}
