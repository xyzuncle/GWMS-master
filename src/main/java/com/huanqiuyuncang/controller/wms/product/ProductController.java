package com.huanqiuyuncang.controller.wms.product;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.wms.brand.BrandInterface;
import com.huanqiuyuncang.service.wms.brand.impl.BrandService;
import com.huanqiuyuncang.service.wms.carton.CartonInterface;
import com.huanqiuyuncang.service.wms.country.CountryManager;
import com.huanqiuyuncang.service.wms.customs.CustomsInterface;
import com.huanqiuyuncang.service.wms.luggagemail.LuggageMailInterface;
import com.huanqiuyuncang.service.wms.packagetype.PackageTypeInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.ObjectExcelView;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.*;

/**
 * 说明 ： 商品
 * Created by lzf on 2017/4/2.
 */
@Controller
@RequestMapping(value="/product")
public class ProductController extends BaseController {
    String menuUrl = "product/list.do"; //菜单地址(权限用)
    @Resource(name="productService")
    private ProductInterface productService;

    @Autowired
    private BrandInterface brandService;
    @Autowired
    private LuggageMailInterface luggageMailService;
    @Autowired
    private CountryManager countryService;
    @Autowired
    private CustomsInterface customsService;
    @Autowired
    private PackageTypeInterface packageTypeService;
    @Autowired
    private CartonInterface cartonService;

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增Product");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        pd.put("productId", this.get32UUID());	//主键
        pd.put("createuser", username);	//创建者
        pd.put("updateuser", username);	//第一次保存，创建者就是修改者
        pd.put("createtime", date);	//创建时间
        pd.put("updatetime", date);	//第一次保存，创建时间就是修改时间
        productService.insertSelective(pd);
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
        logBefore(logger, Jurisdiction.getUsername()+"删除Product");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = new PageData();
        pd = this.getPageData();
        productService.deleteByPrimaryKey(pd);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改Product");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        productService.updateByPrimaryKeySelective(pd);
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
        logBefore(logger, Jurisdiction.getUsername()+"列表Product");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<PageData> varList = productService.datalistPage(page);
        setSelectList(mv);
        mv.setViewName("wms/product/product_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限

        return mv;
    }

    private void setSelectList(ModelAndView mv) throws Exception {
        List<BrandEntity> brandList = brandService.selectAll();
        List<LuggageMailEntity> luggageMailList =  luggageMailService.selectAll();
        List<PageData> pageDatas = countryService.listAll(null);
        List<CustomsEntity> customsList =  customsService.selectAll();
        List<CartonEntity> cartonList =  cartonService.selectAll();
        List<PackageTypeEntity> packageList =  packageTypeService.selectAll();
        mv.addObject("producingAreaList",pageDatas);
        mv.addObject("luggageMailList",luggageMailList);
        mv.addObject("brandList",brandList);
        mv.addObject("customsList",customsList);
        mv.addObject("cartonList",cartonList);
        mv.addObject("packageList",packageList);
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
        setSelectList(mv);
        mv.setViewName("wms/product/product_edit");
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
        PageData pd = new PageData();
        pd = this.getPageData();
        String productId = pd.getString("productId");
        ProductEntity product = productService.selectByPrimaryKey(productId);//根据ID读取
        setSelectList(mv);
        mv.setViewName("wms/product/product_edit");
        mv.addObject("msg", "edit");
        mv.addObject("product", product);
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
        logBefore(logger, Jurisdiction.getUsername()+"批量删除Product");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        PageData pd = new PageData();
        Map<String,Object> map = new HashMap<String,Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            productService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**导出到excel
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/excel")
    public ModelAndView exportExcel() throws Exception{
       logBefore(logger, Jurisdiction.getUsername()+"导出Fhbutton到excel");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        ModelAndView mv = new ModelAndView();
       /*  PageData pd = new PageData();
        pd = this.getPageData();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("名称");	//1
        titles.add("权限标识");	//2
        titles.add("备注");	//3
        dataMap.put("titles", titles);
        List<PageData> varOList = fhbuttonService.listAll(pd);
        List<PageData> varList = new ArrayList<PageData>();
        for(int i=0;i<varOList.size();i++){
            PageData vpd = new PageData();
            vpd.put("var1", varOList.get(i).getString("NAME"));	//1
            vpd.put("var2", varOList.get(i).getString("QX_NAME"));	//2
            vpd.put("var3", varOList.get(i).getString("BZ"));	//3
            varList.add(vpd);
        }
        dataMap.put("varList", varList);
        ObjectExcelView erv = new ObjectExcelView();
        mv = new ModelAndView(erv,dataMap);*/
        return mv;
    }
    @RequestMapping(value="/findProductByProductNum")
    public void findProductByProductNum(String productnum , PrintWriter out) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"删除Brand");
        ProductEntity product = productService.findProductByProductNum(productnum);
        if (product != null){
            out.write("success_"+product.getProductId());
        }else {
            out.write("error");
        }

        out.close();
    }
    @RequestMapping(value="/findProductByBarCode")
    public void findProductByBarCode(String barCode , PrintWriter out) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"删除Brand");
        ProductEntity product = productService.findProductByBarCode(barCode);
        if (product != null){
            out.write("success_"+product.getProductId());
        }else {
            out.write("error");
        }

        out.close();
    }
}
