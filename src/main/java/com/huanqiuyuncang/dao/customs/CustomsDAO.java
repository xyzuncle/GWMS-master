package com.huanqiuyuncang.dao.customs;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;

import java.util.List;

public interface CustomsDAO {
    int deleteByPrimaryKey(String customsid);

    int insert(CustomsEntity record);

    int insertSelective(CustomsEntity record);

    CustomsEntity selectByPrimaryKey(String customsid);

    int updateByPrimaryKeySelective(CustomsEntity record);

    int updateByPrimaryKey(CustomsEntity record);

    List<CustomsEntity> datalistPage(Page page);
}