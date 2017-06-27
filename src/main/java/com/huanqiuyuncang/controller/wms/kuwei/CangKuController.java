package com.huanqiuyuncang.controller.wms.kuwei;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.system.Dictionaries;
import com.huanqiuyuncang.service.system.dictionaries.DictionariesManager;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.util.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/6/11.
 */
@Controller
@RequestMapping("/cangku")
public class CangKuController extends BaseController {
    String menuUrl = "cangku/list.do"; //菜单地址(权限用)
    @Autowired
    private CangKuInterface cangKuService;

    @Resource(name="dictionariesService")
    private DictionariesManager dictionariesService;

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
        CangKuEntity cangKuEntity = (CangKuEntity) BeanMapUtil.mapToObject(pd, CangKuEntity.class);
        String token = (String)this.getRequest().getSession().getAttribute("token");
        cangKuEntity.setId(token);
        cangKuService.insertSelective(cangKuEntity);
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
        Integer sum = checkTable("wms_cangku","id", id);
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            cangKuService.deleteByPrimaryKey(id);
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
        CangKuEntity cangKuEntity = (CangKuEntity) BeanMapUtil.mapToObject(pd, CangKuEntity.class);
        cangKuService.updateByPrimaryKeySelective(cangKuEntity);
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
        List<CangKuEntity> varList =  cangKuService.datalistPage(page);
        for (CangKuEntity cangku:varList) {
            String cangkushuxing = cangku.getCangkushuxing();
            pd.put("BIANMA",cangkushuxing);
            PageData dic = dictionariesService.findByBianma(pd);
            cangku.setCangkushuxing(dic.getString("NAME"));
        }
        mv.setViewName("wms/cangku/cangku_list");
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
        setCangKuShuXing(mv);
        String token = this.get32UUID();
        this.getRequest().getSession().setAttribute("token",token);
        mv.setViewName("wms/cangku/cangku_edit");
        mv.addObject("msg", "save");
        mv.addObject("token",token);
        mv.addObject("pd", pd);
        return mv;
    }

    private void setCangKuShuXing(ModelAndView mv) throws Exception {
        List<Dictionaries> dictionaries = dictionariesService.listSubDictByParentId("89bb990471364bbc8f17d7bb8755c522");
        mv.addObject("dictionaries",dictionaries);
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
        CangKuEntity cangKuEntity = cangKuService.selectByPrimaryKey(id);//根据ID读取
        setCangKuShuXing(mv);
        this.getRequest().getSession().setAttribute("token", cangKuEntity.getId());
        mv.setViewName("wms/cangku/cangku_edit");
        mv.addObject("msg", "edit");
        mv.addObject("cangku", cangKuEntity);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;}
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            Integer sum = checkTable("wms_cangku","id", ArrayDATA_IDS);
            if(sum > 0){
                map.put("msg","error");
                return AppUtil.returnObject(pd, map);
            }
            cangKuService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }


}
