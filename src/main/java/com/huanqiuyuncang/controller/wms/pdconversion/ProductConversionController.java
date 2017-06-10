package com.huanqiuyuncang.controller.wms.pdconversion;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.pdconversion.ProductConversionInterface;
import com.huanqiuyuncang.service.system.fhlog.FHlogManager;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.customer.impl.CustomerService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by lzf on 2017/4/9.
 */
@Controller
@RequestMapping(value="/pdconversion")
public class ProductConversionController extends BaseController {
    String menuUrl = "pdconversion/list.do"; //菜单地址(权限用)
    @Autowired
    private ProductConversionInterface productConversionService;
    @Autowired
    private CustomerInterface customerService;

    @Resource(name="fhlogService")
    private FHlogManager FHLOG;
    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"新增pdconversion");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        ProductConversionEntity pdConversion = (ProductConversionEntity)BeanMapUtil.mapToObject(pd, ProductConversionEntity.class);
        pdConversion.setProductconversionid(this.get32UUID());
        pdConversion.setCreateuser(username);
        pdConversion.setCreatetime(date);
        pdConversion.setUpdatetime(date);
        pdConversion.setUpdateuser(username);
        productConversionService.insertSelective(pdConversion);
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
        logBefore(logger, Jurisdiction.getUsername()+"删除pdconversion");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
        String pdconversionid = pd.getString("pdconversionid");
        productConversionService.deleteByPrimaryKey(pdconversionid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"修改pdconversion");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        ProductConversionEntity pdconversionid = (ProductConversionEntity)BeanMapUtil.mapToObject(pd,ProductConversionEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        pdconversionid.setUpdatetime(date);
        pdconversionid.setUpdateuser(username);
        productConversionService.updateByPrimaryKeySelective(pdconversionid);
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
        logBefore(logger, Jurisdiction.getUsername()+"列表pdconversion");
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd.put("createuser",Jurisdiction.getUsername());
        page.setPd(pd);
        List<ProductConversionEntity> varList =  productConversionService.datalistPage(page);
        mv.setViewName("wms/pdconversion/pdconversion_list");
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
        String username = Jurisdiction.getUsername();
        List<CustomerEntity> customerEntities = customerService.selectByLoginName(username);
        ProductConversionEntity pdconversion = new ProductConversionEntity();
        if(customerEntities != null && customerEntities.size()>0){
            pdconversion.setCustomercode(customerEntities.get(0).getCustomercode());
        }
        mv.setViewName("wms/pdconversion/pdconversion_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        mv.addObject("pdconversion", pdconversion);
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
        String pdconversionid = pd.getString("pdconversionid");
        ProductConversionEntity pdconversion = productConversionService.selectByPrimaryKey(pdconversionid);//根据ID读取
        mv.setViewName("wms/pdconversion/pdconversion_edit");
        mv.addObject("msg", "edit");
        mv.addObject("pdconversion", pdconversion);
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
        logBefore(logger, Jurisdiction.getUsername()+"批量删除pdconversion");
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            productConversionService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    @RequestMapping(value="/checkNum")
    @ResponseBody
    public Object checkNum(String outerproductnum) throws Exception{
        ProductConversionEntity pd = productConversionService.selectByOuterPdNum(outerproductnum);
        Map<String,String> map = new HashMap<String,String>();
        String errInfo = "error";
        if (pd != null){
            errInfo = "success";
        }
        map.put("result", errInfo);				//返回结果
        return AppUtil.returnObject(new PageData(), map);
    }


    /**打开上传EXCEL页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/goUploadExcel")
    public ModelAndView goUploadExcel()throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("wms/pdconversion/uploadexcel");
        return mv;
    }

    /**下载模版
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/downExcel")
    public void downExcel(HttpServletResponse response)throws Exception{
        FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "pdconversion.xls", "pdconversion.xls");
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
            String fileName =  FileUpload.fileUp(file, filePath, "pdconversionexcel");							//执行上传
            List<PageData> listPd = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            String resturt = productConversionService.saveFromExcel(listPd);
            //*存入数据库操作======================================*//*
            if(StringUtils.isNotBlank(resturt)){
                StringBuffer start = new StringBuffer("请将一下错误数据摘出修改后重新导入：\n");
                start.append(resturt);
                mv.addObject("msg","pdconversion_error");
                mv.addObject("resturt",start.toString());
            }else{
                mv.addObject("msg","success");
            }

        }
        mv.addObject("pd",pd);
        mv.setViewName("save_result");
        return mv;
    }




}
