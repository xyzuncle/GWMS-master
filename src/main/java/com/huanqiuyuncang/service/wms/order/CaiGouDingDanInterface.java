package com.huanqiuyuncang.service.wms.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;

import java.util.List;

public interface CaiGouDingDanInterface {
    int deleteByPrimaryKey(String caigoudingdanid);

    int insert(CaiGouDingDanEntity record);

    int insertSelective(CaiGouDingDanEntity record);

    CaiGouDingDanEntity selectByPrimaryKey(String caigoudingdanid);

    int updateByPrimaryKeySelective(CaiGouDingDanEntity record);

    int updateByPrimaryKey(CaiGouDingDanEntity record);

    List<CaiGouDingDanEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);

    void shenheAll(String[] arrayDATA_ids);

    void zuofeiAll(String[] arrayDATA_ids);
}