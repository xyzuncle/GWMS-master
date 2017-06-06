package com.huanqiuyuncang.controller.wms.carton;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.service.wms.carton.CartonInterface;
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
 * 说明：纸箱
 * Created by lzf on 2017/4/4.
 */
@Controller
@RequestMapping(value="/carton")
public class CartonController extends BaseController {
    String menuUrl = "carton/list.do"; //菜单地址(权限用)
    @Autowired
    private CartonInterface cartonService;

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增carton");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        pd.put("cartonid", this.get32UUID());	//主键
        BeanMapUtil.setCreateUserInfo(pd);
        BeanMapUtil.setUpdateUserInfo(pd);
        cartonService.insertSelective(pd);
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
        logBefore(logger, Jurisdiction.getUsername()+"删除carton");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
        String cartonid = pd.getString("cartonid");
        Integer sum = checkTable("wms_carton","cartonID", cartonid);
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            cartonService.deleteByPrimaryKey(cartonid);
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
        logBefore(logger, Jurisdiction.getUsername()+"修改carton");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        BeanMapUtil.setUpdateUserInfo(pd);
        cartonService.updateByPrimaryKeySelective(pd);
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
        logBefore(logger, Jurisdiction.getUsername()+"列表carton");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<CartonEntity> varList =   cartonService.datalistPage(page);
        mv.setViewName("wms/carton/carton_list");
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
        mv.setViewName("wms/carton/carton_edit");
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
        String cartonid = pd.getString("cartonid");
        CartonEntity carton = cartonService.selectByPrimaryKey(cartonid);//根据ID读取
        mv.setViewName("wms/carton/carton_edit");
        mv.addObject("msg", "edit");
        mv.addObject("carton", carton);
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
        logBefore(logger, Jurisdiction.getUsername()+"批量删除carton");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            Integer sum = checkTable("wms_carton","cartonID", ArrayDATA_IDS);
            if(sum > 0){
                map.put("msg","error");
                return AppUtil.returnObject(pd, map);
            }
            cartonService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/findCartonByCartonCode")
    @ResponseBody
    public Object findCartonByCartonCode(String cartontype) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"删除Brand");
        CartonEntity cartonEntity = cartonService.findCartonByCartonCode(cartontype);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        if (cartonEntity != null){
            errInfo = "error";
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }
}
