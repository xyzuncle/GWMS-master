package com.huanqiuyuncang.controller.wms.warehouse;

import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.YiKuEntity;
import com.huanqiuyuncang.entity.warehouse.YiKuSaomiaoDTO;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.kuwei.ShangPinKuWeiInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.service.wms.warehouse.YiKuInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/7/12.
 */
@Controller
@RequestMapping(value="/yiku")
public class YiKuController extends BaseController {

    String menuUrl = "yiku/list.do";

    @Autowired
    private YiKuInterface yiKuService;

    @Autowired
    private CangKuInterface cangKuService;

    @Autowired
    private ShangPinKuWeiInterface shangPinKuWeiService;

    @Autowired
    private ProductInterface productService;

    /**列表
     * @param page
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list(Page page) throws Exception{
        logBefore(logger, Jurisdiction.getUsername()+"列表Product");
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        pd.put("createuser",Jurisdiction.getUsername());
        //判断是否据有查看所有权限
        Map<String, String> hc = Jurisdiction.getHC();
        if(hc.keySet().contains("adminsearch") && "1".equals(hc.get("adminsearch"))){
            pd.remove("createuser");
        }
        page.setPd(pd);
        List<YiKuEntity> varList = yiKuService.datalistPage(page);

        mv.setViewName("wms/warehouse/yiku_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
        return mv;
    }

    @RequestMapping(value="/yikusaomiao")
    public ModelAndView goyiku()throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        mv.setViewName("wms/warehouse/yiku_saomiao");
        mv.addObject("msg", "yiku");
        mv.addObject("pd", pd);
        return mv;
    }

    @RequestMapping(value="/yiku")
    public ModelAndView yiku(List<YiKuSaomiaoDTO> saomiaoList) throws Exception{
        ModelAndView mv = this.getModelAndView();

        yiKuService.updateyiku(saomiaoList,mv);




        mv.setViewName("save_result");
        return mv;
    }

}
