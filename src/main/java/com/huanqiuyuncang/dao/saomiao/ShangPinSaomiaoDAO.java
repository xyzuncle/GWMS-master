package com.huanqiuyuncang.dao.saomiao;

import com.huanqiuyuncang.entity.saomiao.ShangPinSaomiaoEntity;

public interface ShangPinSaomiaoDAO {
    int deleteByPrimaryKey(String id);

    int insert(ShangPinSaomiaoEntity record);

    int insertSelective(ShangPinSaomiaoEntity record);

    ShangPinSaomiaoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShangPinSaomiaoEntity record);

    int updateByPrimaryKey(ShangPinSaomiaoEntity record);

    Integer selectSaomiaoSumByShangpin(String shangpinid);
}