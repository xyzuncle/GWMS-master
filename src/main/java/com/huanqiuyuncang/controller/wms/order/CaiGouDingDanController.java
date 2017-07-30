package com.huanqiuyuncang.controller.wms.order;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.order.ProductSaomiaoDTO;
import com.huanqiuyuncang.service.wms.product.impl.ProductService;
import com.huanqiuyuncang.service.wms.saomiao.ShangPinSaomiaoInterface;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.entity.system.Dictionaries;
import com.huanqiuyuncang.service.system.dictionaries.DictionariesManager;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.customer.GongYingShangInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.order.CaiGouDingDanInterface;
import com.huanqiuyuncang.service.wms.order.CaiGouShangPinInterface;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
import com.huanqiuyuncang.util.*;
import net.sf.json.JSONArray;
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
    private CaiGouShangPinInterface caiGouShangPinService;

    @Autowired
    private ProductWarehouseInterface productWarehouseService;

    @Autowired
    private CangKuInterface cangKuService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShangPinSaomiaoInterface shangPinSaomiaoService;

    @Resource(name="dictionariesService")
    private DictionariesManager dictionariesService;
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
        String token = (String)this.getRequest().getSession().getAttribute("token");
        caiGouDingDanService.insertSelective(caiGouDingDan,token);
        this.getRequest().getSession().removeAttribute("token");
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
        String token = this.get32UUID();
        this.getRequest().getSession().setAttribute("token",token);
        mv.addObject("token", token);
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
        this.getRequest().getSession().setAttribute("token", caiGouDingDan.getCaigoudingdanid());
        mv.setViewName("wms/innerorder/caigoudingdan_edit");
        mv.addObject("msg", "edit");
        mv.addObject("caigoudingdan", caiGouDingDan);
        mv.addObject("token", caiGouDingDan.getCaigoudingdanid());
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
        String USERNAME = Jurisdiction.getUsername();
        String role_name = gerRolename(USERNAME);
        if("仓库管理员".equals(role_name)){
            String cangkuid = pd.getString("cangku");
            if(cangkuid == null || StringUtils.isBlank(cangkuid)){
                List<CangKuEntity> cangkuList = cangKuService.selectByCangkuuser(USERNAME);
                if(cangkuList != null && cangkuList.size()>0){
                    String cangkuids = "";
                    for(CangKuEntity cangku : cangkuList){
                        cangkuids = cangkuids + cangku.getId()+",";
                    }
                    cangkuids = cangkuids.substring(0,cangkuids.length()-1);
                    pd.put("cangku",cangkuids);
                }
            }
        }
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("cangku");
        }
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
        String status = pd.getString("status");

        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanService.selectByPrimaryKey(caigoudingdanid);//根据ID读取
        String yujingstatus = caiGouDingDan.getYujingstatus();
        if(StringUtils.isNotBlank(yujingstatus)){
            pd.put("BIANMA",yujingstatus);
            PageData dic = dictionariesService.findByBianma(pd);
            caiGouDingDan.setYujingstatus(dic.getString("NAME"));
        }
        if("0".equals(status)){
            mv.setViewName("wms/warehouse/caigouruku_zidongsaomiao");
            mv.addObject("msg", "zidongruku");
        }else{
            mv.setViewName("wms/warehouse/caigouruku_edit");
            mv.addObject("msg", "ruku");
        }
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
        String[] arr = this.getRequest().getParameterValues("shangpinhuohao");
        String[] shuliangarr = this.getRequest().getParameterValues("shuliang");
        Map<String,String> huohaoshuliang = makehuohaoshuliang(arr,shuliangarr);
        PageData ruku = caiGouDingDanService.saveruku(caigoudingdanid,huohaoshuliang);
        mv.addObject("msg",ruku.getString("msg"));
        mv.addObject("resturt",ruku.getString("resturt"));
        mv.setViewName("save_result");
        return mv;
    }

    @RequestMapping(value="/zidongruku")
    public ModelAndView zidongruku() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String caigoudingdanid = pd.getString("caigoudingdanid");
        String[] arr = this.getRequest().getParameterValues("shangpinhuohao");
        Map<String,String> huohaoshuliang = makehuohaoshuliang(arr);
        PageData ruku = caiGouDingDanService.saveruku(caigoudingdanid,huohaoshuliang);
        mv.addObject("msg",ruku.getString("msg"));
        mv.addObject("resturt",ruku.getString("resturt"));
        mv.setViewName("save_result");
        return mv;
    }

    private Map<String,String> makehuohaoshuliang(String[] arr) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0 ;i <arr.length ;i++){
            String shuliang = map.get(arr[i]);
            if(shuliang == null){
                map.put(arr[i],"1");
            }else{
                String s = map.get(arr[i]);
                int sum = Integer.parseInt(s);
                map.put(arr[i],Integer.toString(sum+1));
            }
        }
        return  map;
    }


    private Map<String,String> makehuohaoshuliang(String[] arr, String[] shuliangarr) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0 ;i <arr.length ;i++){
            map.put(arr[i],shuliangarr[i]);
        }
        return  map;
    }

    @RequestMapping(value="/pdlist")
    public void pdlist(PrintWriter printWriter,String caigoudingdanid) throws Exception{
       List<CaiGouShangPinEntity> list = caiGouShangPinService.selectByCaiGouDingDanId(caigoudingdanid);
        list.forEach(caiGouShangPinEntity -> {
            Integer sum = shangPinSaomiaoService.selectSaomiaoSumByShangpin(caiGouShangPinEntity.getId());
            caiGouShangPinEntity.setSaomiaoshuliang(Integer.toString(sum));
        });
        String json = JSONArray.fromObject(list, DateJsonConfig.getJsonConfig()).toString();
        String resultJson = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
        printWriter.write(resultJson);
    }


    @RequestMapping(value="/goAddProduct")
    public ModelAndView goAddProduct()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        mv.setViewName("wms/innerorder/caigou_pd");
        mv.addObject("msg", "saveCaigouProduct");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/goEditProduct")
    public ModelAndView goEditProduct()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String id  = pd.getString("id");
        CaiGouShangPinEntity caigoupd = caiGouShangPinService.selectByPrimaryKey(id);
        mv.setViewName("wms/innerorder/caigou_pd");
        mv.addObject("msg", "editCaigouProduct");
        mv.addObject("caigoupd",caigoupd);
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/delcaigouPd")
    @ResponseBody
    public Object delcaigouPd(String id)throws Exception{
        caiGouShangPinService.deleteByPrimaryKey(id);
        Map<String,String> map = new HashMap<String,String>();
        map.put("result", "success");
        return AppUtil.returnObject(new PageData(), map);
    }

    @RequestMapping(value="/saveCaigouProduct")
    public ModelAndView saveCaigouProduct(CaiGouShangPinEntity caiGouShangPinEntity)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String token = (String)this.getRequest().getSession().getAttribute("token");
        caiGouShangPinEntity.setCaigoudingdanid(token);
        caiGouShangPinEntity.setId(this.get32UUID());
        caiGouShangPinEntity.setSaomastatus("0");
        caiGouShangPinService.insert(caiGouShangPinEntity);
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/editCaigouProduct")
    public ModelAndView editCaigouProduct(CaiGouShangPinEntity caiGouShangPinEntity)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        caiGouShangPinService.updateByPrimaryKeySelective(caiGouShangPinEntity);
        mv.setViewName("save_result");
        mv.addObject("msg","success");
        mv.addObject("pd", pd);
        return mv;
    }

    /**打开上传EXCEL页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/goUploadExcel")
    public ModelAndView goUploadExcel()throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("wms/innerorder/uploadcaigouexcel");
        return mv;
    }

    /**下载模版
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/downExcel")
    public void downExcel(HttpServletResponse response)throws Exception{
        FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "caigou.xls", "caigou.xls");
    }

    /**从EXCEL导入到数据库
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/readExcel" , produces = "text/html;charset=UTF-8")
    public  ModelAndView readExcel(@RequestParam(value="excel",required=false) MultipartFile file) throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        if (null != file && !file.isEmpty()) {
            String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
            String fileName =  FileUpload.fileUp(file, filePath, "caigouexcel");							//执行上传
            List<PageData> caigouList = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            String resturt =caiGouDingDanService.saveDingDanFromExcel(caigouList); //innerOrderService.saveOrderFromExcel(orderList,orderPdList);
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

    @RequestMapping(value="/getCustomerCode")
    @ResponseBody
    public Object getCustomerCode() throws Exception{
        String username = Jurisdiction.getUsername();
        List<CustomerEntity> customerEntities = customerService.selectByLoginName(username);
        String msg ="";
        if(customerEntities != null && customerEntities.size()>0){
            msg = customerEntities.get(0).getCustomercode();
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("result", msg);
        return AppUtil.returnObject(this.getPageData(), map);
    }

    @RequestMapping(value="/goTuisongRuku")
    public ModelAndView goTuisongRuku()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String DATA_IDS = pd.getString("caigoudingdanid");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanService.selectByPrimaryKey(ArrayDATA_IDS[0]);
            String gongyingshangbianhao = caiGouDingDanEntity.getGongyingshangbianhao();
            GongYingShangEntity gongYingShangEntity = gongYingShangService.selectgongyingshangByCode(gongyingshangbianhao);
            String defaultwarehouse = gongYingShangEntity.getDefaultwarehouse();
            CangKuEntity cangKuEntity = cangKuService.selectByCangKu(defaultwarehouse);
            mv.addObject("cangkushuxing", cangKuEntity.getCangkushuxing());
            mv.addObject("cangkuid", cangKuEntity.getId());
            String gongyingshangstatus = gongYingShangEntity.getGongyingshangstatus();
            String[] split = gongyingshangstatus.split("_");
            if("0".equals(split[2])){
                mv.addObject("kuwei", "自定义库位");
            }else{
                mv.addObject("kuwei", "默认库位");
            }
        }
        setCangKuShuXing(mv);
        mv.setViewName("wms/innerorder/caigoudingdan_ruku");
        mv.addObject("msg", "saveShangpinRuku");
        mv.addObject("pd", pd);
        return mv;
    }


    @RequestMapping(value="/saveShangpinRuku")
    public ModelAndView saveShangpinRuku(String caigoudingdanid,String cangkushuxing,String cangku,String kuwei) throws Exception{
        ModelAndView mv = this.getModelAndView();
        if(null != caigoudingdanid && !"".equals(caigoudingdanid)){
            String ids[] = caigoudingdanid.split(",");
            caiGouDingDanService.saveShangpinRuku(ids,cangkushuxing,cangku,kuwei);
        }

        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    private void setCangKuShuXing(ModelAndView mv) throws Exception {
        List<Dictionaries> dictionaries = dictionariesService.listSubDictByParentId("89bb990471364bbc8f17d7bb8755c522");
        mv.addObject("dictionaries",dictionaries);
    }

    @RequestMapping(value="/huizong")
    public void huizong(PrintWriter printWriter) throws Exception{
        HttpServletRequest request = this.getRequest();
        String[] shangpinhuohaos = request.getParameterValues("shangpinhuohao");
        String caigoudingdanid = request.getParameter("caigoudingdanid");
        String saomamoshi = request.getParameter("saomamoshi");
        CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanService.selectByPrimaryKey(caigoudingdanid);
        String cangkuid = caiGouDingDanEntity.getCangku();
        Map<String,ProductSaomiaoDTO> map = new HashMap<>();
        String[] shuliangs = null;
        if("1".equals(saomamoshi)){
            shuliangs  = request.getParameterValues("shuliang");
        }
            for(int i = 0 ; i < shangpinhuohaos.length ; i++){
                String tiaoma = shangpinhuohaos[i];
                if(StringUtils.isNotEmpty(tiaoma)){
                    String kuwei = caiGouShangPinService.selectKuweiByBarcodeAndCangku(tiaoma,cangkuid);
                    ProductSaomiaoDTO productSaomiaoDTO = map.get(tiaoma);
                    if(productSaomiaoDTO == null){
                        productSaomiaoDTO = new ProductSaomiaoDTO();
                        productSaomiaoDTO.setTiaoma(tiaoma);
                        if("1".equals(saomamoshi)){
                            productSaomiaoDTO.setShuliang(Integer.parseInt(shuliangs[i]));
                        }else{
                            productSaomiaoDTO.setShuliang(1);
                        }
                        productSaomiaoDTO.setKuwei(kuwei);
                        map.put(tiaoma,productSaomiaoDTO);
                    }else{
                        if("1".equals(saomamoshi)){
                            productSaomiaoDTO.setShuliang(Integer.parseInt(shuliangs[i]));
                        }else{
                            Integer shuliang = productSaomiaoDTO.getShuliang();
                            productSaomiaoDTO.setShuliang(shuliang+1);
                        }

                    }
                }
            }

        List<ProductSaomiaoDTO> list = new ArrayList<>();
        for(String key : map.keySet()){
            list.add(map.get(key));
        }
        String json = JSONArray.fromObject(list, DateJsonConfig.getJsonConfig()).toString();
        String resultJson = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
        printWriter.write(resultJson);
    }


    @RequestMapping(value="/mingxi")
    public void mingxi(PrintWriter printWriter) throws Exception{
        HttpServletRequest request = this.getRequest();
        List<ProductSaomiaoDTO> reslist = new ArrayList<>();
        String tiaoma = request.getParameter("tiaoma");
        if(StringUtils.isNotBlank(tiaoma)){
            List<PageData> list = caiGouShangPinService.selectHistoryInfoByBarcode(tiaoma);
            for(PageData pd : list){
                String shuliang = pd.getString("shuliang");
                String cangkuid = pd.getString("cangku");
                String cangwei = pd.getString("cangwei");
                if(StringUtils.isBlank(cangwei)){
                    cangwei = caiGouShangPinService.selectKuweiByBarcodeAndCangku(tiaoma, cangkuid);
                }
                ProductSaomiaoDTO productSaomiaoDTO = new ProductSaomiaoDTO();
                productSaomiaoDTO.setTiaoma(tiaoma);
                productSaomiaoDTO.setShuliang(Integer.parseInt(shuliang));
                productSaomiaoDTO.setKuwei(cangwei);
                reslist.add(productSaomiaoDTO);
            }
        }
        String json = JSONArray.fromObject(reslist, DateJsonConfig.getJsonConfig()).toString();
        String resultJson = "{\"total\":" + reslist.size() + ",\"rows\":" + json + "}";
        printWriter.write(resultJson);
    }

    @RequestMapping(value="/getkuwei")
    @ResponseBody
    public Object getArea() throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
        String cangkuid = pd.getString("cangku");
        String tiaoma = pd.getString("tiaoma");
        String cangwei = caiGouShangPinService.selectKuweiByBarcodeAndCangku(tiaoma, cangkuid);
        map.put("cangwei", cangwei);
        return AppUtil.returnObject(pd, map);
    }
}
