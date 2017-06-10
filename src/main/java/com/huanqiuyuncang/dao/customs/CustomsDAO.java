package com.huanqiuyuncang.dao.customs;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customs.CustomsEntity;

import java.util.List;

public interface CustomsDAO {
    int deleteByPrimaryKey(String customsid);

    int insert(CustomsEntity record);

    int insertSelective(CustomsEntity record);

    CustomsEntity selectByPrimaryKey(String customsid);

    int updateByPrimaryKeySelective(CustomsEntity record);

    int updateByPrimaryKey(CustomsEntity record);

    List<CustomsEntity> datalistPage(Page page);

    List<CustomsEntity> selectAll();

    CustomsEntity findCustomsByCustomsCode(String customscode);
}