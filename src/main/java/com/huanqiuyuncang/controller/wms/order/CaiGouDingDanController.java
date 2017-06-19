package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.controller.wms.customer.CustomerController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.customer.GongYingShangInterface;
import com.huanqiuyuncang.service.wms.order.CaiGouDingDanInterface;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
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
@RequestMapping("caigoudingdan")
public class CaiGouDingDanController  extends BaseController {

    String menuUrl = "caigoudingdan/list.do"; //菜单地址(权限用)

    @Autowired
    private CaiGouDingDanInterface caiGouDingDanService;

    @Autowired
    private GongYingShangInterface gongYingShangService;
    @Autowired
    private CustomerInterface customerService;

    @Autowired
    private ProductWarehouseInterface productWarehouseService;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增caigoudingdan");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        CaiGouDingDanEntity caiGouDingDan = (CaiGouDingDanEntity) BeanMapUtil.mapToObject(pd, CaiGouDingDanEntity.class);
        caiGouDingDan.setCaigoudingdanid(this.get32UUID());
        caiGouDingDan.setCreateuser(username);
        caiGouDingDan.setCreatetime(date);
        caiGouDingDan.setUpdateuser(username);
        caiGouDingDan.setUpdatetime(date);
        caiGouDingDan.setCaigoudingdanstatus("caigouStatus_daiqueren");
        caiGouDingDanService.insertSelective(caiGouDingDan);
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
        logBefore(logger, Jurisdiction.getUsername()+"删除caigoudingdan");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
        String caigoudingdanid = pd.getString("caigoudingdanid");
        Integer sum = checkTable("wms_caigoudingdan","caigoudingdanid", caigoudingdanid);
        String msg = "success";
        if(sum >0){
            msg = "error";
        }else{
            caiGouDingDanService.deleteByPrimaryKey(caigoudingdanid);
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
        CaiGouDingDanEntity caiGouDingDan = (CaiGouDingDanEntity) BeanMapUtil.mapToObject(pd, CaiGouDingDanEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        caiGouDingDan.setUpdatetime(date);
        caiGouDingDan.setUpdateuser(username);
        caiGouDingDanService.updateByPrimaryKeySelective(caiGouDingDan);
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
        pd.put("createuser",Jurisdiction.getUsername());
        page.setPd(pd);
        //判断是否据有查看所有权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("createuser");
        }
        List<CaiGouDingDanEntity> varList = caiGouDingDanService.datalistPage(page);
        varList.forEach(caiGouDingDanEntity -> {
            String name = gongYingShangService.selectNameByCode(caiGouDingDanEntity.getGongyingshangbianhao());
            caiGouDingDanEntity.setGongyingshangbianhao(name);
        });
        mv.setViewName("wms/innerorder/caigoudingdan_list");
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
        mv.setViewName("wms/innerorder/caigoudingdan_edit");
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
        String caigoudingdanid = pd.getString("caigoudingdanid");
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanService.selectByPrimaryKey(caigoudingdanid);//根据ID读取
       /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");*/
        String formateCreateTime = DateUtil.format(caiGouDingDan.getCreatetime(),"yyyy-MM-dd");
        String formateUpdateTime = DateUtil.format(caiGouDingDan.getUpdatetime(),"yyyy-MM-dd");
        caiGouDingDan.setFormatCreateTime(formateCreateTime);
        caiGouDingDan.setFormateUpdateTime(formateUpdateTime);
        mv.setViewName("wms/innerorder/caigoudingdan_edit");
        mv.addObject("msg", "edit");
        mv.addObject("caigoudingdan", caiGouDingDan);
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
        logBefore(logger, Jurisdiction.getUsername()+"批量删除gongYingShang");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            Integer sum = checkTable("wms_caigoudingdan","caigoudingdanid", ArrayDATA_IDS);
            if(sum > 0){
                map.put("msg","error");
                return AppUtil.returnObject(pd, map);
            }
            caiGouDingDanService.deleteAll(ArrayDATA_IDS);
            map.put("msg", "success");
        }else{
            map.put("msg","error");
        }
        return AppUtil.returnObject(pd, map);
    }

    /**批量审核
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/shenheAll")
    @ResponseBody
    public Object shenheAll() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            caiGouDingDanService.shenheAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**批量作废
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/zuofeiAll")
    @ResponseBody
    public Object zuofeiAll() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            caiGouDingDanService.zuofeiAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }



    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/rukulist")
    public ModelAndView rukulist(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表gongYingShang");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        page.setPd(pd);
        List<CaiGouDingDanEntity> varList = caiGouDingDanService.datalistPage(page);
        varList.forEach(caiGouDingDanEntity -> {
            String name = gongYingShangService.selectNameByCode(caiGouDingDanEntity.getGongyingshangbianhao());
            caiGouDingDanEntity.setGongyingshangbianhao(name);
        });
        mv.setViewName("wms/warehouse/caigouruku_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }


    /**去修改页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goruku")
    public ModelAndView goruku()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String caigoudingdanid = pd.getString("caigoudingdanid");
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanService.selectByPrimaryKey(caigoudingdanid);//根据ID读取
        mv.setViewName("wms/warehouse/caigouruku_edit");
        mv.addObject("msg", "ruku");
        mv.addObject("caigoudingdan", caiGouDingDan);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/goview")
    public ModelAndView goview() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String caigoudingdanid = pd.getString("caigoudingdanid");
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanService.selectByPrimaryKey(caigoudingdanid);//根据ID读取
        String formateCreateTime = DateUtil.format(caiGouDingDan.getCreatetime(),"yyyy-MM-dd");
        String formateUpdateTime = DateUtil.format(caiGouDingDan.getUpdatetime(),"yyyy-MM-dd");
        caiGouDingDan.setFormatCreateTime(formateCreateTime);
        caiGouDingDan.setFormateUpdateTime(formateUpdateTime);
        mv.setViewName("wms/warehouse/caigouruku_view");
        mv.addObject("msg", "edit");
        mv.addObject("caigoudingdan", caiGouDingDan);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/ruku")
    public ModelAndView ruku() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改gongYingShang");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String caigoudingdanid = pd.getString("caigoudingdanid");
        PageData ruku = caiGouDingDanService.saveruku(caigoudingdanid);
        mv.addObject("msg",ruku.getString("msg"));
        mv.addObject("resturt",ruku.getString("resturt"));
        mv.setViewName("save_result");
        return mv;
    }

}
