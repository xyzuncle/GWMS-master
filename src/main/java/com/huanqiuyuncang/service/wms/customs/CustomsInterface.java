package com.huanqiuyuncang.service.wms.customs;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface CustomsInterface {
    int deleteByPrimaryKey(String customsid) throws Exception;

    int insert(CustomsEntity record)  throws Exception;

    int insertSelective(PageData record) throws Exception;

    CustomsEntity selectByPrimaryKey(String customsid) throws Exception;

    int updateByPrimaryKeySelective(PageData record) throws Exception;

    int updateByPrimaryKey(CustomsEntity record) throws Exception;

    List<CustomsEntity> datalistPage(Page page) throws Exception;

    void deleteAll(String[] arrayDATA_ids) throws Exception;

    List<CustomsEntity> selectAll()throws Exception;

    CustomsEntity findCustomsByCustomsCode(String customscode)throws Exception;

}