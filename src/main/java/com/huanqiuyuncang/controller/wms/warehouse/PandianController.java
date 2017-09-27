package com.huanqiuyuncang.controller.wms.warehouse;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.order.ProductSaomiaoDTO;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.*;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.kuwei.ShangPinKuWeiInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.service.wms.warehouse.PandianInterface;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
import com.huanqiuyuncang.service.wms.warehouse.YiKuInterface;
import com.huanqiuyuncang.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by lzf on 2017/5/13.
 */
@Controller
@RequestMapping(value="/pandian")
public class PandianController extends BaseController {
    String menuUrl = "pandian/list.do";
    @Autowired
    private ProductWarehouseInterface productWarehouseService;

    @Autowired
    private PandianInterface pandianService;

    @Autowired
    private CangKuInterface cangKuService;

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表Product");
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        String USERNAME = Jurisdiction.getUsername();
        String role_name = gerRolename(USERNAME);
      if("仓库管理员".equals(role_name)){
            String cangkuid = pd.getString("cangku");
            if(cangkuid == null || StringUtils.isBlank(cangkuid)){
                List<CangKuEntity> cangkuList = cangKuService.selectByCangkuuser(USERNAME);
                if(cangkuList != null && cangkuList.size()>0){
                        if(StringUtils.isBlank(pd.getString("cangku"))&&cangkuList != null && cangkuList.size()>0){
                            String cangkuCodes = "";
                            for(CangKuEntity cangku : cangkuList){
                                cangkuCodes = cangkuCodes+cangku.getCangkubianhao()+",";
                             }
                            cangkuCodes = cangkuCodes.substring(0,cangkuCodes.length()-1);
                            pd.put("cangku",cangkuCodes);
                        }
                }
            }
        }
        //判断是否据有查看所有权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("cangku");
        }
        page.setPd(pd);
        List<PandianEntity> varList = pandianService.datalistPage(page);

        for(PandianEntity pandian : varList){
            String cangkubianhao = pandian.getCangku();
            CangKuEntity cangKuEntity = cangKuService.selectByCangKu(cangkubianhao);
            pandian.setCangku(cangKuEntity.getCangkuname());
        }

        mv.setViewName("wms/warehouse/pandian_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    /**去新增页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goSaoMiao")
    public ModelAndView goSaoMiao()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String status = pd.getString("status");
        if("0".equals(status)){
            mv.setViewName("wms/warehouse/pandian_zidongsaoma");
        }else{
            mv.setViewName("wms/warehouse/pandian_saoma");
        }
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        return mv;
    }


    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        ModelAndView mv = this.getModelAndView();
        HttpServletRequest request = this.getRequest();
        String saomamoshi = request.getParameter("saomamoshi");
        String kuwei = request.getParameter("kuwei");
        String[] tiaomas = request.getParameterValues("shangpinbianhao");
        String[] shuliangs = null;
        if("1".equals(saomamoshi)){
            shuliangs  = request.getParameterValues("shuliang");
        }
        Map<String , Integer> map =  makePandianInfo(tiaomas,shuliangs);

        pandianService.updatePandian(kuwei,map);

        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    private Map<String , Integer> makePandianInfo( String[] tiaomas, String[] shuliangs) {
        Map<String , Integer> map = new HashMap<>();
        if(shuliangs == null || shuliangs.length <= 0 ){
            for(String tiaoma : tiaomas){
                if(map.get(tiaoma) != null){
                    Integer sum = map.get(tiaoma);
                    map.put(tiaoma,sum+1);
                }else{
                    map.put(tiaoma,1);
                }
            }
        }else{
            for(int i =0;i< tiaomas.length;i++){
                map.put(tiaomas[i],Integer.parseInt(shuliangs[i]));
            }
        }
        return  map;
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
                ProductSaomiaoDTO productSaomiaoDTO = map.get(tiaoma);
                if(productSaomiaoDTO == null){
                    productSaomiaoDTO = new ProductSaomiaoDTO();
                    productSaomiaoDTO.setTiaoma(tiaoma);
                    if("1".equals(saomamoshi)){
                        productSaomiaoDTO.setShuliang(Integer.parseInt(shuliangs[i]));
                    }else{
                        productSaomiaoDTO.setShuliang(1);
                    }
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
}
