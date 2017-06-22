package com.huanqiuyuncang.service.wms.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface ChuKuShangPinInterface {
    int deleteByPrimaryKey(String chukushangpinid);

    int insert(ChuKuShangPinEntity record);

    int insertSelective(ChuKuShangPinEntity record);

    ChuKuShangPinEntity selectByPrimaryKey(String chukushangpinid);

    int updateByPrimaryKeySelective(ChuKuShangPinEntity record);

    int updateByPrimaryKey(ChuKuShangPinEntity record);


    List<ChuKuShangPinEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);


    PageData updateSaomiaoShangPin(String[] huohaoarr, String[] dingdanhaoarr);
}