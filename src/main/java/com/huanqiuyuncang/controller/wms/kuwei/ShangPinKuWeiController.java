package com.huanqiuyuncang.controller.wms.kuwei;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.service.wms.kuwei.BaoGuoKuWeiInterface;
import com.huanqiuyuncang.service.wms.kuwei.ShangPinKuWeiInterface;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/6/11.
 */
@Controller
@RequestMapping("shangpinkuwei")
public class ShangPinKuWeiController extends BaseController {
    String menuUrl = "shangpinkuwei/list.do"; //菜单地址(权限用)
    @Autowired
    private ShangPinKuWeiInterface shangPinKuWeiService;

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
        ShangPinKuWeiEntity shangPinKuWeiEntity = (ShangPinKuWeiEntity) BeanMapUtil.mapToObject(pd, ShangPinKuWeiEntity.class);
        shangPinKuWeiEntity.setId(this.get32UUID());
        shangPinKuWeiService.insertSelective(shangPinKuWeiEntity);
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
        Integer sum = checkTable("wms_shangpinkuwei","id", id);
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            shangPinKuWeiService.deleteByPrimaryKey(id);
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
        ShangPinKuWeiEntity shangPinKuWeiEntity = (ShangPinKuWeiEntity) BeanMapUtil.mapToObject(pd, ShangPinKuWeiEntity.class);
        shangPinKuWeiService.updateByPrimaryKeySelective(shangPinKuWeiEntity);
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
        page.setPd(pd);
        List<ShangPinKuWeiEntity> varList =  shangPinKuWeiService.datalistPage(page);
        mv.setViewName("wms/shangpinkuwei/shangpinkuwei_list");
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
        mv.setViewName("wms/shangpinkuwei/shangpinkuwei_edit");
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
        ShangPinKuWeiEntity shangPinKuWeiEntity = shangPinKuWeiService.selectByPrimaryKey(id);//根据ID读取
        mv.setViewName("wms/shangpinkuwei/shangpinkuwei_edit");
        mv.addObject("msg", "edit");
        mv.addObject("shangpinkuwei", shangPinKuWeiEntity);
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
            Integer sum = checkTable("wms_shangpinkuwei","id", ArrayDATA_IDS);
            if(sum > 0){
                map.put("msg","error");
                return AppUtil.returnObject(pd, map);
            }
            shangPinKuWeiService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }


}
