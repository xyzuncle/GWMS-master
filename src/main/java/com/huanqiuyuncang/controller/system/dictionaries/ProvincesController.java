package com.huanqiuyuncang.controller.system.dictionaries;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.AreasEntity;
import com.huanqiuyuncang.entity.system.CitiesEntity;
import com.huanqiuyuncang.entity.system.ProvincesEntity;
import com.huanqiuyuncang.service.system.areas.AreasInterface;
import com.huanqiuyuncang.service.system.cities.CitiesInterface;
import com.huanqiuyuncang.service.system.provinces.ProvincesInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/7/28.
 */
@Controller
@RequestMapping(value="/province")
public class ProvincesController extends BaseController {
    String menuUrl = "province/provincelist.do"; //菜单地址(权限用)

    @Autowired
    private ProvincesInterface provincesService;

    @Autowired
    private CitiesInterface citiesService;

    @Autowired
    private AreasInterface areasService;

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/provincelist")
    public ModelAndView provincelist(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表Dictionaries");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<ProvincesEntity> varList = provincesService.list(page);
        mv.setViewName("system/dictionaries/province_list");
        mv.addObject("varList", varList);
        mv.addObject("QX",Jurisdiction.getHC());
        return mv;
    }

    @RequestMapping(value="/citieslist")
    public ModelAndView citieslist(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表Dictionaries");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<CitiesEntity> varList = citiesService.list(page);
        mv.setViewName("system/dictionaries/cities_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());
        return mv;
    }


    @RequestMapping(value="/areaslist")
    public ModelAndView areaslist(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表Dictionaries");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<AreasEntity> varList = areasService.list(page);
        String provinceid = pd.getString("provinceid");

        if(StringUtils.isEmpty(provinceid)){
            String cityid = pd.getString("cityid");
            CitiesEntity citiesEntity = citiesService.selectByCityid(cityid);
            pd.put("provinceid",citiesEntity.getProvinceid());
        }
        mv.setViewName("system/dictionaries/areas_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());
        return mv;
    }


    @RequestMapping(value="/goProvinceEdit")
    public ModelAndView goProvinceEdit()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String id = pd.getString("id");
        ProvincesEntity province = provincesService.selectByPrimaryKey(Integer.parseInt(id));	//根据ID读取
        mv.addObject("province", province);
        mv.setViewName("system/dictionaries/province_edit");
        mv.addObject("msg", "editProvince");
        return mv;
    }



    @RequestMapping(value="/deleteProvince")
    @ResponseBody
    public Object deleteProvince(@RequestParam String id) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        logBefore(logger, Jurisdiction.getUsername()+"删除Dictionaries");
        Map<String,String> map = new HashMap<String,String>();
        PageData pd = new PageData();
        ProvincesEntity provincesEntity = provincesService.selectByPrimaryKey(Integer.parseInt(id));
        pd.put("provinceid", provincesEntity.getProvinceid());
        String errInfo = "success";
        Page page = new Page();
        page.setPd(pd);
        if(citiesService.list(page).size() > 0){//判断是否有子级，是：不允许删除
            errInfo = "false";
        }
        if("success".equals(errInfo)){
            provincesService.deleteByPrimaryKey(Integer.parseInt(id));	//执行删除
        }
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }



    @RequestMapping(value="/goProvinceAdd")
    public ModelAndView goProvinceAdd()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("system/dictionaries/province_edit");
        mv.addObject("msg", "saveProvince");
        return mv;
    }


    @RequestMapping(value="/editProvince")
    public ModelAndView editProvince(ProvincesEntity provincesEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改Dictionaries");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        provincesService.updateByPrimaryKeySelective(provincesEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


    @RequestMapping(value="/saveProvince")
    public ModelAndView saveProvince(ProvincesEntity provincesEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增Dictionaries");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        provincesService.insertSelective(provincesEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    @RequestMapping(value="/goCityEdit")
    public ModelAndView goCityEdit()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String id = pd.getString("id");
        CitiesEntity city = citiesService.selectByPrimaryKey(Integer.parseInt(id));	//根据ID读取
        ProvincesEntity provincesEntity = provincesService.selectByProvinceid(city.getProvinceid());
        mv.addObject("city", city);
        mv.addObject("province", provincesEntity);
        mv.setViewName("system/dictionaries/city_edit");
        mv.addObject("msg", "editCity");
        return mv;
    }



    @RequestMapping(value="/deleteCity")
    @ResponseBody
    public Object deleteCity(@RequestParam String id) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        logBefore(logger, Jurisdiction.getUsername()+"删除Dictionaries");
        Map<String,String> map = new HashMap<String,String>();
        PageData pd = new PageData();
        CitiesEntity citiesEntity = citiesService.selectByPrimaryKey(Integer.parseInt(id));
        pd.put("cityid", citiesEntity.getCityid());
        String errInfo = "success";
        Page page = new Page();
        page.setPd(pd);
        if(areasService.list(page).size() > 0){//判断是否有子级，是：不允许删除
            errInfo = "false";
        }
        if("success".equals(errInfo)){
            citiesService.deleteByPrimaryKey(Integer.parseInt(id));	//执行删除
        }
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }



    @RequestMapping(value="/goCityAdd")
    public ModelAndView goCityAdd()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String provinceid = pd.getString("provinceid");
        ProvincesEntity province = provincesService.selectByProvinceid(provinceid);
        mv.addObject("province", province);
        mv.addObject("pd", pd);
        mv.addObject("provinceid", provinceid);
        mv.setViewName("system/dictionaries/city_edit");
        mv.addObject("msg", "saveCity");

        return mv;
    }


    @RequestMapping(value="/editCity")
    public ModelAndView editCity(CitiesEntity citiesEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改Dictionaries");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        citiesService.updateByPrimaryKeySelective(citiesEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


    @RequestMapping(value="/saveCity")
    public ModelAndView saveCity(CitiesEntity citiesEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增Dictionaries");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        citiesService.insertSelective(citiesEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    @RequestMapping(value="/goAreaEdit")
    public ModelAndView goAreaEdit()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String id = pd.getString("id");
        AreasEntity area = areasService.selectByPrimaryKey(Integer.parseInt(id));	//根据ID读取
        CitiesEntity city = citiesService.selectByCityid(area.getCityid());
        mv.addObject("area", area);
        mv.addObject("city", city);
        mv.addObject("pd", pd);
        mv.setViewName("system/dictionaries/area_edit");
        mv.addObject("msg", "editArea");
        return mv;
    }



    @RequestMapping(value="/deleteArea")
    @ResponseBody
    public Object deleteArea(@RequestParam String id) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        logBefore(logger, Jurisdiction.getUsername()+"删除Dictionaries");
        Map<String,String> map = new HashMap<String,String>();
        PageData pd = new PageData();
        String errInfo = "success";
        areasService.deleteByPrimaryKey(Integer.parseInt(id));	//执行删除
        map.put("result", errInfo);
        return AppUtil.returnObject(new PageData(), map);
    }



    @RequestMapping(value="/goAreaAdd")
    public ModelAndView goAreaAdd()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String cityid = pd.getString("cityid");
        CitiesEntity citiesEntity = citiesService.selectByCityid(cityid);
        mv.setViewName("system/dictionaries/area_edit");
        mv.addObject("msg", "saveArea");
        mv.addObject("city", citiesEntity);

        return mv;
    }


    @RequestMapping(value="/editArea")
    public ModelAndView editArea(AreasEntity areasEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改Dictionaries");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        areasService.updateByPrimaryKeySelective(areasEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


    @RequestMapping(value="/saveArea")
    public ModelAndView saveArea(AreasEntity areasEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增Dictionaries");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        areasService.insertSelective(areasEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


}
