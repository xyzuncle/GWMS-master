package com.huanqiuyuncang.controller.system.checktable;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.checktable.CheckTableEntity;
import com.huanqiuyuncang.service.system.checktable.CheckTableInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/6/5.
 */
@Controller
@RequestMapping(value="/checktable")
public class CheckTableController extends BaseController {

    @Autowired
    private CheckTableInterface checkTableService;

    String menuUrl = "checktable/list.do"; //菜单地址(权限用)

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        CheckTableEntity checkTableEntity = (CheckTableEntity)BeanMapUtil.mapToObject(pd, CheckTableEntity.class);
        checkTableEntity.setChecktableid(this.get32UUID());
        checkTableService.insert(checkTableEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


    /**删除
     * @param out
     * @throws Exception
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public Object delete(@RequestParam String checktableid) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        PageData pd = this.getPageData();
        checkTableService.deleteByPrimaryKey(checktableid);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
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
        CheckTableEntity checkTableEntity = (CheckTableEntity)BeanMapUtil.mapToObject(pd, CheckTableEntity.class);
        checkTableService.updateByPrimaryKeySelective(checkTableEntity);
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
        PageData pd = new PageData();
        pd = this.getPageData();
        String keywords = pd.getString("keywords");					//检索条件
        if(null != keywords && !"".equals(keywords)){
            pd.put("keywords", keywords.trim());
        }
        String checktableid = null == pd.get("checktableid")?"":pd.get("checktableid").toString();
        if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
            checktableid = pd.get("id").toString();
        }
        pd.put("checktableid", checktableid);
        page.setPd(pd);
        List<CheckTableEntity> varList = checkTableService.list(page);
        mv.addObject("checkTable", checkTableService.selectByPrimaryKey(checktableid));		//传入上级所有信息
        mv.addObject("checktableid", checktableid);			//上级ID
        mv.setViewName("system/check/checktable_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());					//按钮权限
        return mv;
    }

    /**
     * 显示列表ztree
     * @param model
     * @return
     */
    @RequestMapping(value="/listAllDict")
    public ModelAndView listAllDict(Model model, String checktableid)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try{
            JSONArray arr = JSONArray.fromObject(checkTableService.listAllDict("0"));
            String json = arr.toString();
            json = json.replaceAll("checktableid", "id").replaceAll("parentid", "pId").replaceAll("chcektablename", "name").replaceAll("subDict", "nodes").replaceAll("hasDict", "checked").replaceAll("treeurl", "url");
            model.addAttribute("zTreeNodes", json);
            mv.addObject("checktableid",checktableid);
            mv.addObject("pd", pd);
            mv.setViewName("system/check/checktable_ztree");
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
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
        String checktableid = null == pd.get("checktableid")?"":pd.get("checktableid").toString();
        pd.put("checktableid", checktableid);					//上级ID
        mv.addObject("pds",checkTableService.selectByPrimaryKey(checktableid));		//传入上级所有信息
        mv.addObject("checktableid", checktableid);			//传入ID，作为子级ID用
        mv.setViewName("system/check/checktable_edit");
        mv.addObject("msg", "save");
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
        String checktableid = pd.getString("checktableid");
        CheckTableEntity checkTableEntity = checkTableService.selectByPrimaryKey(checktableid);//根据ID读取
        mv.addObject("checkTable", checkTableEntity);					//放入视图容器
        mv.addObject("pd", pd);					//放入视图容器
        mv.addObject("pds",checkTableService.selectByPrimaryKey(checkTableEntity.getParentid()));				//传入上级所有信息
        mv.addObject("checktableid", checkTableEntity.getParentid());	//传入上级ID，作为子ID用
        pd.put("checktableid",checktableid);							//复原本ID
        mv.setViewName("system/check/checktable_edit");
        mv.addObject("msg", "edit");
        return mv;
    }

}
