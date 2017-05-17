package com.huanqiuyuncang.service.wms.brand;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface BrandInterface {
    int deleteByPrimaryKey(String brandid) throws Exception;

    int insert(BrandEntity record) throws Exception;

    int insertSelective(PageData pageData) throws Exception;

    BrandEntity selectByPrimaryKey(String brandid) throws Exception;

    int updateByPrimaryKeySelective(PageData pageData) throws Exception;

    int updateByPrimaryKey(BrandEntity record) throws Exception;

    List<BrandEntity> datalistPage(Page page)  throws Exception;

    void deleteAll(String[] arrayDATA_ids)throws Exception;

    List<BrandEntity> selectAll()throws Exception;

    BrandEntity findBrandByBrandCode(String brandcode)throws Exception;
}