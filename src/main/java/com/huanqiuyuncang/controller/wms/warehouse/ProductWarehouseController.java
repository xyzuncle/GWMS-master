package com.huanqiuyuncang.controller.wms.warehouse;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.YiKuEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
import com.huanqiuyuncang.service.wms.warehouse.YiKuInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
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
@RequestMapping(value="/productwarehouse")
public class ProductWarehouseController extends BaseController {
    String menuUrl = "productwarehouse/list.do";
    @Autowired
    private ProductWarehouseInterface productWarehouseService;

    @Autowired
    private ProductInterface productService;

    @Autowired
    private YiKuInterface yiKuService;

    @Autowired
    private CustomerInterface customerService;

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
        List<CangKuEntity> cangkucommonlist = cangKuService.getCangku("cangkushuxing_common");
        String USERNAME = Jurisdiction.getUsername();
        String role_name = gerRolename(USERNAME);
        if("注册用户".equals(role_name)){
            List<CustomerEntity> customerList = customerService.selectByLoginName(USERNAME);
            if(customerList != null && customerList.size() >0){
                CustomerEntity customerEntity = customerList.get(0);
                String kehubianhao = customerEntity.getCustomercode();
                if(StringUtils.isBlank(pd.getString("kehubianhao"))){
                    pd.put("kehubianhao",kehubianhao);
                    String cangkuCodes = "";
                    for(CangKuEntity cangku : cangkucommonlist){
                        cangkuCodes = cangkuCodes+cangku.getCangkubianhao()+",";
                    }
                    pd.put("cangkuCodes",cangkuCodes);
                }
            }

        }else if("仓库管理员".equals(role_name)){
            String cangkuid = pd.getString("cangku");
            if(cangkuid == null || StringUtils.isBlank(cangkuid)){
                List<CangKuEntity> cangkuList = cangKuService.selectByCangkuuser(USERNAME);
                cangkuList.addAll(cangkucommonlist);
                if(cangkuList != null && cangkuList.size()>0){
                    String cangkuCodes = "";
                    for(CangKuEntity cangku : cangkuList){
                        cangkuCodes = cangkuCodes+cangku.getCangkubianhao()+",";
                    }
                    if(StringUtils.isNotBlank(cangkuCodes)){
                        cangkuCodes = cangkuCodes.substring(0,cangkuCodes.length()-1);
                        if(StringUtils.isBlank(pd.getString("cangku"))){
                            pd.put("cangku",cangkuCodes);
                        }

                    }
                }
            }
        }
        //判断是否据有查看所有权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("cangku");
            pd.remove("cangkuCodes");
            pd.remove("kehubianhao");
        }
        page.setPd(pd);
        List<ProductWarehouseEntity> varList = productWarehouseService.datalistPage(page);

        for(ProductWarehouseEntity pw : varList){
            String cangkuid = pw.getCangku();
            CangKuEntity cangKuEntity = cangKuService.selectByPrimaryKey(cangkuid);
            pw.setCangku(cangKuEntity.getCangkuname());
        }

        mv.setViewName("wms/warehouse/productwarehouse_list");
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
        mv.setViewName("wms/warehouse/productwarehouse_edit");
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
        String productwarehouseid = pd.getString("productwarehouseid");
        ProductWarehouseEntity ProductWarehouseEntity = productWarehouseService.selectByPrimaryKey(productwarehouseid);//根据ID读取
        mv.setViewName("wms/warehouse/productwarehouse_edit");
        mv.addObject("msg", "edit");
        mv.addObject("productwarehouse", ProductWarehouseEntity);
        mv.addObject("pd", pd);
        return mv;
    }


    /**去修改页面
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/goyiku")
    public ModelAndView goyiku()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List<ProductWarehouseEntity> list = new ArrayList<>();
        String DATA_IDS = pd.getString("productwarehouseid");
        if(null != DATA_IDS && !"".equals(DATA_IDS)){
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            for(String id : ArrayDATA_IDS){
                ProductWarehouseEntity productWarehouseEntity = productWarehouseService.selectByPrimaryKey(id);
                ProductEntity product = productService.findProductByProductNum(productWarehouseEntity.getNeibuhuohao());
                productWarehouseEntity.setProductName(product.getProductname());
                list.add(productWarehouseEntity);

            }
        }
        mv.setViewName("wms/warehouse/productwarehouse_edit");
        mv.addObject("msg", "yiku");
        mv.addObject("ids", DATA_IDS);
        mv.addObject("list", list);
        mv.addObject("pd", pd);
        return mv;
    }
    @RequestMapping(value="/pandian")
    public ModelAndView pandian()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        String productwarehouseid = pd.getString("productwarehouseid");
        ProductWarehouseEntity productWarehouse = productWarehouseService.selectByPrimaryKey(productwarehouseid);//根据ID读取
        productWarehouse.setSuokustatus("1");
        productWarehouseService.updateByPrimaryKeySelective(productWarehouse);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    @RequestMapping(value="/yiku")
    public ModelAndView yiku(List<ProductWarehouseEntity> pwlist,String cangku,String cangwei) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        List<YiKuEntity> list = new ArrayList<>();
        for(ProductWarehouseEntity pw : pwlist){
            String productwarehouseid = pw.getProductwarehouseid();
            String yikushuliang = pw.getShuliang();
            ProductWarehouseEntity productWarehouseEntity = productWarehouseService.selectByPrimaryKey(productwarehouseid);
            YiKuEntity yiKu = new YiKuEntity();
            yiKu.setId(UuidUtil.get32UUID());
            yiKu.setProductnum(productWarehouseEntity.getNeibuhuohao());
            yiKu.setSrccangku(productWarehouseEntity.getCangku());
            yiKu.setSrccangwei(productWarehouseEntity.getCangwei());
            yiKu.setTargetcangku(cangku);
            yiKu.setTargetcangwei(cangwei);
            yiKu.setYikushuangliang(yikushuliang);
            yiKu.setYikustatus("yiku_daiyiku");
            yiKu.setCreateuser(username);
            yiKu.setCreatetime(date);
            yiKu.setUpdateuser(username);
            yiKu.setUpdatetime(date);
            list.add(yiKu);
        }
        yiKuService.insert(list);
        /*String productwarehouseid = productWarehouseEntity.getProductwarehouseid();
        String cangwei = productWarehouseEntity.getCangwei();
        String cangku = productWarehouseEntity.getCangku();
        ProductWarehouseEntity sourceEntity = productWarehouseService.selectByPrimaryKey(productwarehouseid);
        PageData aa = new PageData();
        aa.put("cangwei",cangwei);
        aa.put("cangku",cangku);
        ProductWarehouseEntity productWarehouse = productWarehouseService.selectByPd(aa);
        if(productWarehouse != null){
            Integer sum = Integer.parseInt(productWarehouse.getShuliang()) + Integer.parseInt(sourceEntity.getShuliang());
            productWarehouse.setShuliang(Integer.toString(sum));
            productWarehouseService.updateByPrimaryKeySelective(productWarehouse);
        }else{
            sourceEntity.setCangwei(cangwei);
            productWarehouseService.updateByPrimaryKeySelective(sourceEntity);
        }*/
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(ProductWarehouseEntity productWarehouseEntity) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        productWarehouseEntity.setProductwarehouseid(this.get32UUID());
        productWarehouseEntity.setCreateuser(username);
        productWarehouseEntity.setUpdateuser(username);
        productWarehouseEntity.setCreatetime(date);
        productWarehouseEntity.setUpdatetime(date);
        productWarehouseService.insertSelective(productWarehouseEntity);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
        String productwarehouseid = pd.getString("productwarehouseid");
        productWarehouseService.deleteByPrimaryKey(productwarehouseid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(ProductWarehouseEntity productWarehouseEntity) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        productWarehouseService.updateByPrimaryKeySelective(productWarehouseEntity);
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
            productWarehouseService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }
}
