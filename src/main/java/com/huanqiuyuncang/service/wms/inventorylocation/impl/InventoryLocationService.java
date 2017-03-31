package com.huanqiuyuncang.service.wms.inventorylocation.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.huanqiuyuncang.dao.DaoSupport;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.service.wms.inventorylocation.InventoryLocationManager;

/** 
 * 说明： 商品归位
 * @author
 * 创建时间：2017-03-12
 * @version
 */
@Service("inventorylocationService")
public class InventoryLocationService implements InventoryLocationManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("InventoryLocationMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("InventoryLocationMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("InventoryLocationMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("InventoryLocationMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("InventoryLocationMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("InventoryLocationMapper.findById", pd);
	}

	/**通过UPC获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUPC(PageData pd)throws Exception{
		return (PageData)dao.findForObject("InventoryLocationMapper.findByUPC", pd);
	}

	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("InventoryLocationMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

