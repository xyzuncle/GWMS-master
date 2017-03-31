package com.huanqiuyuncang.service.wms.country;

import java.util.List;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.util.PageData;

/** 
 * 说明： 国家接口
 * @author
 * 创建时间：2017-03-19
 * @version
 */
public interface CountryManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
    void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
    void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
    void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
    List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
    List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
    PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
    void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
}

