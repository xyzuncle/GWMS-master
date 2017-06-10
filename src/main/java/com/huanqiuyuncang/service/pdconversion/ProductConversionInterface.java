package com.huanqiuyuncang.service.pdconversion;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface ProductConversionInterface {
    int deleteByPrimaryKey(String productconversionid);

    int insert(ProductConversionEntity record);

    int insertSelective(ProductConversionEntity record);

    ProductConversionEntity selectByPrimaryKey(String productconversionid);

    int updateByPrimaryKeySelective(ProductConversionEntity record);

    int updateByPrimaryKey(ProductConversionEntity record);

    List<ProductConversionEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);


    ProductConversionEntity selectByOuterPdNum(String outerproductnum);

    String saveFromExcel(List<PageData> listPd);
}