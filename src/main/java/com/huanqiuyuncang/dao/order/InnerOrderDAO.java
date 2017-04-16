package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface InnerOrderDAO {
    int insert(InnerOrderEntity record);

    int insertSelective(InnerOrderEntity record);

    int updateByPrimaryKeySelective(InnerOrderEntity record);

    List<InnerOrderEntity> datalistPage(Page page);

    int deleteByPrimaryKey(String innerorderid);

    InnerOrderEntity selectByPrimaryKey(String innerorderid);

    List<PageData> selectDictionaries(String parent_id);

    List<PageData> selectProvince();

    List<PageData> selectCity(String code);

    List<PageData> selectArea(String code);

    String selectProvinceNameByCode(String code);

    String selectCityNameByCode(String code);

    String selectDictBianMaByName(String baoguanmoshi);
}