package com.huanqiuyuncang.service.wms.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;
import java.util.Map;

public interface CaiGouDingDanInterface {
    int deleteByPrimaryKey(String caigoudingdanid);

    int insert(CaiGouDingDanEntity record);

    int insertSelective(CaiGouDingDanEntity record, String token);

    CaiGouDingDanEntity selectByPrimaryKey(String caigoudingdanid);

    int updateByPrimaryKeySelective(CaiGouDingDanEntity record);

    int updateByPrimaryKey(CaiGouDingDanEntity record);

    List<CaiGouDingDanEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);

    void shenheAll(String[] arrayDATA_ids);

    void zuofeiAll(String[] arrayDATA_ids);

    PageData saveruku(String caigoudingdanid,  Map<String,String> huohaoArr);

    String saveDingDanFromExcel(List<PageData> caigouList);

    void saveShangpinRuku(String[] ids, String cangkushuxing, String cangku, String kuwei);
}