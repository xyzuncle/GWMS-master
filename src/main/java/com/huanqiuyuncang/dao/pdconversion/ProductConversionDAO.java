package com.huanqiuyuncang.dao.pdconversion;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;

import java.util.List;

public interface ProductConversionDAO {
    int deleteByPrimaryKey(String productconversionid);

    int insert(ProductConversionEntity record);

    int insertSelective(ProductConversionEntity record);

    ProductConversionEntity selectByPrimaryKey(String productconversionid);

    int updateByPrimaryKeySelective(ProductConversionEntity record);

    int updateByPrimaryKey(ProductConversionEntity record);

    List<ProductConversionEntity> datalistPage(Page page);

    ProductConversionEntity selectByOuterPdNum(String outerproductnum);
}