package com.huanqiuyuncang.controller.wms.warehouse;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.warehouse.PackageWarehouseInterface;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value="/packagewarehouse")
public class PackageWarehouseController extends BaseController {
    String menuUrl = "packagewarehouse/list.do";
    @Autowired
    private PackageWarehouseInterface packageWarehouseService;

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
                    if(cangkucommonlist != null && cangkucommonlist.size()>0){
                        String cangkuCodes = "";
                        for(CangKuEntity cangku : cangkucommonlist){
                            cangkuCodes = cangkuCodes+cangku.getCangkubianhao()+",";
                        }
                        cangkuCodes = cangkuCodes.substring(0,cangkuCodes.length()-1);
                        pd.put("cangkuCodes",cangkuCodes);
                    }
                }
            }

        }else if("仓库管理员".equals(role_name)){
            String cangkuid = pd.getString("cangku");
            if(cangkuid == null || StringUtils.isBlank(cangkuid)){
                List<CangKuEntity> cangkuList = cangKuService.selectByCangkuuser(USERNAME);
                cangkuList.addAll(cangkucommonlist);
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
            pd.remove("cangkuCodes");
            pd.remove("kehubianhao");
        }
        page.setPd(pd);
        List<PackageWarehouseEntity> varList = packageWarehouseService.datalistPage(page);
        mv.setViewName("wms/warehouse/packagewarehouse_list");
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
        mv.setViewName("wms/warehouse/packagewarehouse_edit");
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
        String packagewarehouseid = pd.getString("packagewarehouseid");
        PackageWarehouseEntity packageWarehouseEntity = packageWarehouseService.selectByPrimaryKey(packagewarehouseid);//根据ID读取
        mv.setViewName("wms/warehouse/packagewarehouse_edit");
        mv.addObject("msg", "edit");
        mv.addObject("packagewarehouse", packageWarehouseEntity);
        mv.addObject("pd", pd);
        return mv;
    }

    /**保存
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save(PackageWarehouseEntity packageWarehouseEntity) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        packageWarehouseEntity.setPackagewarehouseid(this.get32UUID());
        packageWarehouseEntity.setCreateuser(username);
        packageWarehouseEntity.setUpdateuser(username);
        packageWarehouseEntity.setCreatetime(date);
        packageWarehouseEntity.setUpdatetime(date);
        packageWarehouseService.insertSelective(packageWarehouseEntity);
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
        String packagewarehouseid = pd.getString("packagewarehouseid");
        packageWarehouseService.deleteByPrimaryKey(packagewarehouseid);
        out.write("success");
        out.close();
    }

    /**修改
     * @param
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(PackageWarehouseEntity packageWarehouseEntity) throws Exception{
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
        ModelAndView mv = this.getModelAndView();
        packageWarehouseService.updateByPrimaryKeySelective(packageWarehouseEntity);
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
            packageWarehouseService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

}
