package com.huanqiuyuncang.service.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface InnerOrderInterface {
    int insert(InnerOrderEntity record);

    int insertSelective(InnerOrderEntity record);

    int updateByPrimaryKeySelective(InnerOrderEntity record);

    List<InnerOrderEntity> datalistPage(Page page);

    int deleteByPrimaryKey(String innerorderid);

    InnerOrderEntity selectByPrimaryKey(String innerorderid);

    void deleteAll(String[] arrayDATA_ids);

    List<PageData> selectDictionaries(String PARENT_ID);


    void insertOrderInfo(InnerOrderEntity innerOrder, String token);


    List<PageData> selectProvince();

    List<PageData> selectCity(String code);

    List<PageData> selectArea(String code);

    String selectProvinceNameByCode(String recipientprovince);

    String selectCityNameByCode(String recipientcity);

    void shenheAll(String[] arrayDATA_ids);

    String saveOrderFromExcel(List<PageData> orderListPd, List<PageData> listPd);
}