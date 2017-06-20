package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;

import java.util.List;

public interface CaiGouShangPinDAO {
    int deleteByPrimaryKey(String id);

    int insert(CaiGouShangPinEntity record);

    int insertSelective(CaiGouShangPinEntity record);

    CaiGouShangPinEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CaiGouShangPinEntity record);

    int updateByPrimaryKey(CaiGouShangPinEntity record);

    List<CaiGouShangPinEntity> selectByCaiGouDingDanId(String caigoudingdanid);
}