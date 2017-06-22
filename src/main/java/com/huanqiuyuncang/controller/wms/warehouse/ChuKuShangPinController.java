package com.huanqiuyuncang.controller.wms.warehouse;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.warehouse.ChuKuShangPinInterface;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by lzf on 2017/5/13.
 */
@Controller
@RequestMapping(value="/chukushangpin")
public class ChuKuShangPinController extends BaseController {
    String menuUrl = "chukushangpin/list.do";
    @Autowired
    private ChuKuShangPinInterface chuKuShangPinService;

    @Autowired
    private ProductWarehouseInterface productWarehouseService;

    @Autowired
    private CustomerInterface customerService;

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        page.setPd(pd);
        List<ChuKuShangPinEntity> varList = chuKuShangPinService.datalistPage(page);
        mv.setViewName("wms/warehouse/chukushangpin_list");
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
        mv.setViewName("wms/warehouse/chukushangpin_edit");
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
        String chukushangpinid = pd.getString("chukushangpinid");
        ChuKuShangPinEntity chuKuShangPinEntity = chuKuShangPinService.selectByPrimaryKey(chukushangpinid);//根据ID读取
        mv.setViewName("wms/warehouse/chukushangpin_edit");
        mv.addObject("msg", "edit");
        mv.addObject("chukushangpin", chuKuShangPinEntity);
        mv.addObject("pd", pd);
        return mv;
    }
    /**去扫码页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goSaoma")
    public ModelAndView goSaoma()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String chukushangpinid = pd.getString("chukushangpinid");
        ChuKuShangPinEntity chuKuShangPinEntity = chuKuShangPinService.selectByPrimaryKey(chukushangpinid);//根据ID读取
        mv.setViewName("wms/warehouse/shangpin_saoma");
        mv.addObject("msg", "updatecangku");
        mv.addObject("chukushangpin", chuKuShangPinEntity);
        mv.addObject("pd", pd);
        return mv;
    }

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(ChuKuShangPinEntity chuKuShangPinEntity) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        chuKuShangPinEntity.setChukushangpinid(this.get32UUID());
        chuKuShangPinEntity.setCreateuser(username);
        chuKuShangPinEntity.setUpdateuser(username);
        chuKuShangPinEntity.setCreatetime(date);
        chuKuShangPinEntity.setUpdatetime(date);
        chuKuShangPinEntity.setChukuzhuangtai("daichuku");
        chuKuShangPinService.insertSelective(chuKuShangPinEntity);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }


    @RequestMapping(value="/updatecangku")
    public ModelAndView updatecangku() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        String[] huohaoarr = this.getRequest().getParameterValues("huohao");
        String[] dingdanhaoarr = this.getRequest().getParameterValues("dingdanhao");
        PageData result = chuKuShangPinService.updateSaomiaoShangPin(huohaoarr,dingdanhaoarr);
        mv.addObject("msg",result.getString("msg"));
        mv.addObject("resturt",result.getString("resturt"));
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
        String chukushangpinid = pd.getString("chukushangpinid");
        chuKuShangPinService.deleteByPrimaryKey(chukushangpinid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(ChuKuShangPinEntity chuKuShangPinEntity) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        chuKuShangPinService.updateByPrimaryKeySelective(chuKuShangPinEntity);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            chuKuShangPinService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }



}
