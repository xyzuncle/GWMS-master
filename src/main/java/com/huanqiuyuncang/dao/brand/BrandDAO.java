package com.huanqiuyuncang.dao.brand;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;

import java.util.List;

public interface BrandDAO {
    int deleteByPrimaryKey(String brandid);

    int insert(BrandEntity record);

    int insertSelective(BrandEntity record);

    BrandEntity selectByPrimaryKey(String brandid);

    int updateByPrimaryKeySelective(BrandEntity record);

    int updateByPrimaryKey(BrandEntity record);

    List<BrandEntity> datalistPage(Page page);

    List<BrandEntity> selectAll();

    BrandEntity findBrandByBrandCode(String brandcode);

    BrandEntity selectBrandByBrandName(String brandname);
}