package com.huanqiuyuncang.controller.wms.inventorylocation;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.huanqiuyuncang.service.wms.inventorylocation.impl.InventoryLocationService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.huanqiuyuncang.controller.base.BaseController;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.util.AppUtil;
import com.huanqiuyuncang.util.ObjectExcelView;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.Tools;
import com.huanqiuyuncang.service.wms.inventorylocation.InventoryLocationManager;

/** 
 * 说明：商品归位
 * @author
 * 创建时间：2017-03-12
 */
@Controller
@RequestMapping(value="/inventorylocation")
public class InventoryLocationController extends BaseController {
	
	String menuUrl = "inventorylocation/list.do"; //菜单地址(权限用)
	@Resource(name="inventorylocationService")
	private InventoryLocationManager inventorylocationService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增InventoryLocation");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
		pd.put("INVENTORYLOCATION_ID", this.get32UUID());	//主键
		inventorylocationService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除InventoryLocation");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
        PageData pd = this.getPageData();
		inventorylocationService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改InventoryLocation");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
		inventorylocationService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表InventoryLocation");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = inventorylocationService.list(page);	//列出InventoryLocation列表
		mv.setViewName("wms/inventorylocation/inventorylocation_list");
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
		mv.setViewName("wms/inventorylocation/inventorylocation_edit");
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
		pd = inventorylocationService.findById(pd);	//根据ID读取
		mv.setViewName("wms/inventorylocation/inventorylocation_edit");
		mv.addObject("msg", "edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除InventoryLocation");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			inventorylocationService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出InventoryLocation到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        PageData pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商品名称");	//1
		titles.add("条码");	//2
		titles.add("库位");	//3
		titles.add("备注");	//4
		//titles.add("备注2");	//5
		dataMap.put("titles", titles);
		List<PageData> varOList = inventorylocationService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("GOODS_NAME"));	    //1
			vpd.put("var2", varOList.get(i).get("GOODS_UPC").toString());	//2
			vpd.put("var3", varOList.get(i).getString("LOCATION"));	    //3
			vpd.put("var4", varOList.get(i).getString("REMARKS_1"));	    //4
			//vpd.put("var5", varOList.get(i).getString("REMARKS_2"));	    //5
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		ModelAndView mv = new ModelAndView(erv,dataMap);
		return mv;
	}

	/**判断编码是否存在
	 * @return
	 */
	@RequestMapping(value="/hasUPC")
	@ResponseBody
	public Object hasUPC(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		try{
            PageData pd = this.getPageData();
			if(inventorylocationService.findByUPC(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
