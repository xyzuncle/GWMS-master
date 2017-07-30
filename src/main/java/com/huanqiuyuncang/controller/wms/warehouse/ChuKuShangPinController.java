package com.huanqiuyuncang.controller.wms.warehouse;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.order.ProductSaomiaoDTO;
import com.huanqiuyuncang.entity.system.Role;
import com.huanqiuyuncang.entity.system.User;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.system.role.RoleManager;
import com.huanqiuyuncang.service.system.user.UserManager;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.saomiao.ShangPinSaomiaoInterface;
import com.huanqiuyuncang.service.wms.warehouse.ChuKuShangPinInterface;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
import com.huanqiuyuncang.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ShangPinSaomiaoInterface shangPinSaomiaoService;

    @Autowired
    private CangKuInterface cangKuService;


    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        String USERNAME = Jurisdiction.getUsername();
        String role_name = gerRolename(USERNAME);
        if("注册用户".equals(role_name)){
            List<CustomerEntity> customerList = customerService.selectByLoginName(USERNAME);
            if(customerList != null && customerList.size() >0){
                CustomerEntity customerEntity = customerList.get(0);
                String kehubianhao = customerEntity.getCustomercode();
                pd.put("kehubianhao",kehubianhao);
            }
        }else if("仓库管理员".equals(role_name)){
            String cangkuid = pd.getString("cangku");
            if(cangkuid == null || StringUtils.isBlank(cangkuid)){
                List<CangKuEntity> cangkuList = cangKuService.selectByCangkuuser(USERNAME);
                if(cangkuList != null && cangkuList.size()>0){
                    String cangkuids = "";
                    for (CangKuEntity cangKuEntity :cangkuList){
                        cangkuids  = cangkuids+cangKuEntity.getId()+",";
                    }
                    cangkuids = cangkuids.substring(0,cangkuids.length()-1);
                    pd.put("cangku",cangkuids);
                }
            }
        }
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("cangku");
            pd.remove("kehubianhao");
        }
        page.setPd(pd);
        List<ChuKuShangPinEntity> varList = chuKuShangPinService.datalistPage(page);
        varList.forEach(chuKuShangPinEntity -> {
            Integer sum = shangPinSaomiaoService.selectSaomiaoSumByShangpin(chuKuShangPinEntity.getChukushangpinid());
            chuKuShangPinEntity.setSaomiaoshuliang(Integer.toString(sum));
        });
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
        String status = pd.getString("status");
        if("0".equals(status)){
            mv.setViewName("wms/warehouse/shangpin_zidongsaoma");
            mv.addObject("msg", "updatezidongcangku");
        }else{
            mv.setViewName("wms/warehouse/shangpin_saoma");
            mv.addObject("msg", "updatecangku");
        }

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


    @RequestMapping(value="/updatezidongcangku")
    public ModelAndView updatezidongcangku() throws Exception{
        ModelAndView mv = this.getModelAndView();
        String[] huohaoarr = this.getRequest().getParameterValues("huohao");
        String dingdanhao = this.getRequest().getParameter("dingdanhao");
        String [] dingdanhaoarr = {dingdanhao};
        Map<String, List<String>> stringListMap = makeShangpinShuliang(huohaoarr, dingdanhaoarr);
        List<String> dingdanhaoList = stringListMap.get("dingdanhaoList");
        List<String> tiaomaList = stringListMap.get("huohaoList");
        List<String> shuliangList = stringListMap.get("shuliangList");
        PageData result = chuKuShangPinService.updateSaomiaoShangPin(shuliangList.toArray(new String[shuliangList.size()]),
                tiaomaList.toArray(new String[tiaomaList.size()]),dingdanhaoList.toArray(new String[dingdanhaoList.size()]));
        mv.addObject("msg",result.getString("msg"));
        mv.addObject("resturt",result.getString("resturt"));
        mv.setViewName("save_result");
        return mv;
    }

    private Map<String,List<String>> makeShangpinShuliang(String[] huohaoarr, String[] dingdanhaoarr) {
        Map<String,List<String>> map = new HashMap<>();
        Map<String,String> huohaomap = new HashMap<>();
        List<String> dingdanhaoList = new ArrayList<>();
        List<String> huohaoList = new ArrayList<>();
        List<String> shuliangList = new ArrayList<>();
        for (int i = 0 ;i <huohaoarr.length ;i++){
            String shuliang = huohaomap.get(huohaoarr[i]);
            String dingdanhao =  dingdanhaoarr[i];
            if(shuliang == null){
                huohaomap.put(huohaoarr[i],"1");
            }else{
                String s = huohaomap.get(huohaoarr[i]);
                int sum = Integer.parseInt(s);
                huohaomap.put(huohaoarr[i],Integer.toString(sum+1));
            }
            if(!dingdanhaoList.contains(dingdanhao)){
                dingdanhaoList.add(dingdanhao);
            }
        }
        for(String key : huohaomap.keySet()){
            huohaoList.add(key);
            shuliangList.add(huohaomap.get(key));
        }
        map.put("dingdanhaoList",dingdanhaoList);
        map.put("huohaoList",huohaoList);
        map.put("shuliangList",shuliangList);
        return map;
    }

    @RequestMapping(value="/updatecangku")
    public ModelAndView updatecangku() throws Exception{
        ModelAndView mv = this.getModelAndView();
        String[] tiaomaarr = this.getRequest().getParameterValues("huohao");
        String[] dingdanhaoarr = this.getRequest().getParameterValues("dingdanhao");
        String[] shuliang = this.getRequest().getParameterValues("shuliang");
        PageData result = chuKuShangPinService.updateSaomiaoShangPin(shuliang,tiaomaarr,dingdanhaoarr);
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

    @RequestMapping(value="/huizong")
    public void huizong(PrintWriter printWriter) throws Exception{
        HttpServletRequest request = this.getRequest();
        String[] huohaos = request.getParameterValues("huohao");
        String dingdanhao = request.getParameter("dingdanhao");
        String[] shuliangs = null;
        String saomamoshi = request.getParameter("saomamoshi");
        if("1".equals(saomamoshi)){
            shuliangs  = request.getParameterValues("shuliang");
        }
        Map<String,ProductSaomiaoDTO> map = new HashMap<>();
        for(int i = 0 ; i < huohaos.length ; i++){
            String tiaoma = huohaos[i];
            if(StringUtils.isNotEmpty(tiaoma)){
                ChuKuShangPinEntity chuKuShangPinEntity = chuKuShangPinService.selectByDingdanhaoAndTiaoma(dingdanhao,tiaoma);
                ProductSaomiaoDTO productSaomiaoDTO = map.get(tiaoma);
                if(productSaomiaoDTO == null){
                    productSaomiaoDTO = new ProductSaomiaoDTO();
                    productSaomiaoDTO.setTiaoma(tiaoma);
                    if("1".equals(saomamoshi)){
                        productSaomiaoDTO.setShuliang(Integer.parseInt(shuliangs[i]));
                    }else{
                        productSaomiaoDTO.setShuliang(1);
                    }
                    String kuwei = chuKuShangPinEntity == null?"":chuKuShangPinEntity.getCangwei();
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
            List<ChuKuShangPinEntity> list = chuKuShangPinService.selectHistoryInfoByBarcode(tiaoma);
            for(ChuKuShangPinEntity chuKuShangPinEntity : list){
                String shuliang = chuKuShangPinEntity.getShuliang();
                String cangwei = chuKuShangPinEntity.getCangwei();
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
        String dingdanhao = pd.getString("dingdanhao");
        String tiaoma = pd.getString("tiaoma");
        ChuKuShangPinEntity chuKuShangPinEntity = chuKuShangPinService.selectByDingdanhaoAndTiaoma(dingdanhao, tiaoma);
        String cangwei = "";
        if(chuKuShangPinEntity != null){
            cangwei = chuKuShangPinEntity.getCangwei();
        }
        map.put("cangwei", cangwei);
        return AppUtil.returnObject(pd, map);
    }
}
