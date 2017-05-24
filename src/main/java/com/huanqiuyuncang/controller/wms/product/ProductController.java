package com.huanqiuyuncang.controller.wms.product;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.system.fhlog.FHlogManager;
import com.huanqiuyuncang.service.wms.brand.BrandInterface;
import com.huanqiuyuncang.service.wms.carton.CartonInterface;
import com.huanqiuyuncang.service.wms.country.CountryManager;
import com.huanqiuyuncang.service.wms.customs.CustomsInterface;
import com.huanqiuyuncang.service.wms.luggagemail.LuggageMailInterface;
import com.huanqiuyuncang.service.wms.packagetype.PackageTypeInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
    @Resource(name="fhlogService")
    private FHlogManager FHLOG;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(ProductEntity productEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增Product");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        productEntity.setProductId(this.get32UUID());
        productEntity.setAuditStatus(0);
        productEntity.setBlockStatus(0);
        productEntity.setCreateuser(username);
        productEntity.setCreatetime(date);
        productEntity.setUpdateuser(username);
        productEntity.setUpdatetime(date);
        System.out.println(productEntity.toString());
        productService.insertSelective(productEntity);
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
        PageData pd = this.getPageData();
        productService.deleteByPrimaryKey(pd);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(ProductEntity productEntity) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改Product");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        productEntity.setUpdateuser(username);
        productEntity.setUpdatetime(date);
        productService.updateByPrimaryKeySelective(productEntity);
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
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        Map<String, String> hc = Jurisdiction.getHC();
        //与审核权限挂钩，没有权限就只显示自己创建的商品
        if(!hc.keySet().contains("productAuditor")){
            pd.put("createuser",Jurisdiction.getUsername());
        }
        if(!hc.keySet().contains("productBlock")){
            pd.put("createuser",Jurisdiction.getUsername());
        }
        String blockStatus = pd.getString("blockStatus");
        if("1".equals(blockStatus)){
            pd.put("auditStatus","");
        }
        page.setPd(pd);
        List<ProductEntity> varList = productService.datalistPage(page);
        setSelectList(mv);
        mv.setViewName("wms/product/product_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        if("1".equals(blockStatus)){
            pd.put("auditStatus","0");
        }
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
        PageData pd = this.getPageData();
        setSelectList(mv);
        mv.setViewName("wms/product/product_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**打开上传EXCEL页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/goUploadExcel")
    public ModelAndView goUploadExcel()throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("wms/product/uploadexcel");
        return mv;
    }



    /**下载模版
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/downExcel")
    public void downExcel(HttpServletResponse response)throws Exception{
        FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "product.xls", "product.xls");
    }

    /**从EXCEL导入到数据库
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/readExcel" , produces = "text/html;charset=UTF-8")
    public  ModelAndView readExcel(@RequestParam(value="excel",required=false) MultipartFile file) throws Exception{
        ModelAndView mv = this.getModelAndView();
        FHLOG.save(Jurisdiction.getUsername(), "从EXCEL导入到数据库");
        PageData pd = new PageData();
        if (null != file && !file.isEmpty()) {
            String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
            String fileName =  FileUpload.fileUp(file, filePath, "productexcel");							//执行上传
            List<PageData> listPd = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            String resturt = productService.saveProductFromExcel(listPd);
            //*存入数据库操作======================================*//*
            if(StringUtils.isNotBlank(resturt)){
                StringBuffer start = new StringBuffer("请将一下错误数据摘出修改后重新导入：\n");
                start.append(resturt);
                mv.addObject("msg","product_error");
                mv.addObject("resturt",start.toString());
            }else{
                mv.addObject("msg","success");
            }

        }
        mv.addObject("pd",pd);
        mv.setViewName("save_result");
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
        String productId = pd.getString("productId");
        ProductEntity product = productService.selectByPrimaryKey(productId);//根据ID读取
        String formateCreateTime = DateUtil.format(product.getCreatetime(),"yyyy-MM-dd HH:mm:ss");
        String formateUpdateTime = DateUtil.format(product.getUpdatetime(),"yyyy-MM-dd HH:mm:ss");
        product.setFormatCreateTime(formateCreateTime);
        product.setFormateUpdateTime(formateUpdateTime);
        setSelectList(mv);
        mv.setViewName("wms/product/product_edit");
        mv.addObject("msg", "edit");
        mv.addObject("product", product);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**去修改页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goImage")
    public ModelAndView goImage()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String src = pd.getString("src");
        String yuantu = src.substring(src.lastIndexOf("/") + 1).split(ImageUtil.DEFAULT_PREVFIX)[1];
        yuantu = this.getRequest().getContextPath()+ImageUtil.DEFAULT_PATH +"/"+yuantu;
        mv.setViewName("wms/product/product_imageView");
        mv.addObject("msg", "view");
        mv.addObject("yuantu", yuantu);
        mv.addObject("src", src);
        return mv;
    }


    /**去详情页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goView")
    public ModelAndView goView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String productId = pd.getString("productId");
        ProductEntity product = productService.selectByPrimaryKey(productId);//根据ID读取
        String brandname = "";
        if(StringUtils.isNotBlank(product.getBrandname())){
            BrandEntity brandEntity = brandService.selectByPrimaryKey(product.getBrandname());
            brandname = brandEntity.getBrandname();
        }
        String customsname = "";
        if(StringUtils.isNotBlank(product.getCustomscode())){
            CustomsEntity customsEntity = customsService.selectByPrimaryKey(product.getCustomscode());
            customsname = customsEntity.getCustomsname();
        }
        String s = productService.selectCountryNameByID(product.getProducingArea());
        String packagerName ="";
        if(StringUtils.isNotBlank(product.getDefaultpackage())){
            PackageTypeEntity packageTypeEntity = packageTypeService.selectByPrimaryKey(product.getDefaultpackage());
            packagerName = packageTypeEntity.getPackagename();
        }
        String luggagemailname = "";
        if(StringUtils.isNotBlank(product.getLuggagemail())){
            LuggageMailEntity luggageMailEntity = luggageMailService.selectByPrimaryKey(product.getLuggagemail());
            luggagemailname = luggageMailEntity.getLuggagemailname();
        }
        String cartonnameA = "";
        if(StringUtils.isNotBlank(product.getCartontypea())){
            CartonEntity cartonEntityA = cartonService.selectByPrimaryKey(product.getCartontypea());
            cartonnameA = cartonEntityA.getCartonname();
        }
        String cartonnameB = "";
        if(StringUtils.isNotBlank(product.getCartontypea())){
            CartonEntity cartonEntityB = cartonService.selectByPrimaryKey(product.getCartontypeb());
            cartonnameB = cartonEntityB.getCartonname();
        }


        product.setBrandname(brandname);
        product.setCustomscode(customsname);
        product.setProducingArea(s);
        product.setDefaultpackage(packagerName);
        product.setLuggagemail(luggagemailname);
        product.setCartontypea(cartonnameA);
        product.setCartontypeb(cartonnameB);
        String formateCreateTime = DateUtil.format(product.getCreatetime(),"yyyy-MM-dd HH:mm:ss");
        String formateUpdateTime = DateUtil.format(product.getUpdatetime(),"yyyy-MM-dd HH:mm:ss");
        product.setFormatCreateTime(formateCreateTime);
        product.setFormateUpdateTime(formateUpdateTime);
        setSelectList(mv);
        mv.setViewName("wms/product/product_view");
        mv.addObject("msg", "edit");
        mv.addObject("product", product);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }
    /**修改审核状态
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/updateAuditor")
    public void updateAuditor(PrintWriter out)throws Exception{
        PageData pd = this.getPageData();
        String productId = pd.getString("productId");
        ProductEntity product = productService.selectByPrimaryKey(productId);//根据ID读取
        product.setAuditStatus(1);
        productService.updateByPrimaryKeySelective(product);
        out.write("success");
        out.close();
    }

    /**修改停用状态
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/updateBlock")
    public void updateBlock(PrintWriter out)throws Exception{
        PageData pd = this.getPageData();
        String productId = pd.getString("productId");
        ProductEntity product = productService.selectByPrimaryKey(productId);//根据ID读取
        if(product.getBlockStatus() == 0){
            product.setBlockStatus(1);
        }else{
            product.setBlockStatus(0);
        }
        productService.updateByPrimaryKeySelective(product);
        out.write("success");
        out.close();
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
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
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
        PageData pd = this.getPageData();
        Map<String, String> hc = Jurisdiction.getHC();
        if(!hc.keySet().contains("productAuditor")){
            pd.put("createuser",Jurisdiction.getUsername());
        }
        List<ProductEntity> varOList = productService.selectForExcel(pd);
        Map<String, Object> dataMap = getDataMap(varOList);
        ObjectExcelView erv = new ObjectExcelView();
        ModelAndView mv = new ModelAndView(erv,dataMap);
        return mv;
    }

    private Map<String, Object> getDataMap(List<ProductEntity> varOList) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("货号");	//1
        titles.add("商品名称");	//2
        titles.add("外文名称");	//3
        titles.add("主条码");	//4
        titles.add("辅助条码1");	//5
        titles.add("辅助条码2");	//6
        titles.add("辅助条码3");	//7
        titles.add("辅助条码4");	//8
        titles.add("品牌");	//9
        titles.add("单位");	//10
        titles.add("规格");	//11
        titles.add("产地");	//12
        titles.add("保质期（天）");	//13
        titles.add("毛重（g）");	//14
        titles.add("净重（g）");	//15
        titles.add("长（cm）");	//16
        titles.add("宽（cm）");	//17
        titles.add("高（cm）");	//18
        titles.add("体积（cm³）");	//19
        titles.add("商品分类");	//20
        titles.add("备注");	//21
        dataMap.put("titles", titles);
        List<PageData> varList = new ArrayList<PageData>();
        for(int i=0;i<varOList.size();i++){
            ProductEntity productEntity = varOList.get(i);
            PageData vpd = new PageData();
            vpd.put("var1", productEntity.getProductnum());	//1
            vpd.put("var2", productEntity.getProductname());	//2
            vpd.put("var3", productEntity.getProductename());	//3
            vpd.put("var4", productEntity.getBarcodeMain());	//3
            vpd.put("var5", productEntity.getBarcodeAuxiliary1());	//3
            vpd.put("var6", productEntity.getBarcodeAuxiliary2());	//3
            vpd.put("var7", productEntity.getBarcodeAuxiliary3());	//3
            vpd.put("var8", productEntity.getBarcodeAuxiliary4());	//3
            vpd.put("var9", productEntity.getBrandname());	//3
            vpd.put("var10", productEntity.getUnit());	//3
            vpd.put("var11", productEntity.getStandard());	//3
            vpd.put("var12", productEntity.getProducingArea());	//3
            vpd.put("var13", productEntity.getExpirationDate());	//3
            vpd.put("var14", productEntity.getGrossWeight()+"");	//3
            vpd.put("var15", productEntity.getNetWeight()+"");	//3
            vpd.put("var16", productEntity.getProductLength()+"");	//3
            vpd.put("var17", productEntity.getProductWidth()+"");	//3
            vpd.put("var18", productEntity.getProductHigh()+"");	//3
            vpd.put("var19", productEntity.getProductVolume()+"");	//3
            vpd.put("var20", productEntity.getLuggagemail());	//3
            vpd.put("var21", productEntity.getRemark1());	//3
            varList.add(vpd);
        }
        dataMap.put("varList", varList);
        return dataMap;
    }


    /**检查货号
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/findProductByProductNum")
    @ResponseBody
    public Object findProductByProductNum(String productnum) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"findProductByProductNum");
        ProductEntity product = productService.findProductByProductNum(productnum);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        if (product != null){
            errInfo = "error";
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    /**检查条码
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/findProductByBarCode")
    @ResponseBody
    public Object findProductByBarCode(String barCode) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"删除Brand");
        ProductEntity product = productService.findProductByBarCode(barCode);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "success";
        if (product != null){
            errInfo = "error";
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }
}
