package com.huanqiuyuncang.dao;

import com.huanqiuyuncang.entity.customs.CustomsEntity;

public interface CustomsEntityMapper {
    int deleteByPrimaryKey(String customsid);

    int insert(CustomsEntity record);

    int insertSelective(CustomsEntity record);

    CustomsEntity selectByPrimaryKey(String customsid);

    int updateByPrimaryKeySelective(CustomsEntity record);

    int updateByPrimaryKey(CustomsEntity record);
}