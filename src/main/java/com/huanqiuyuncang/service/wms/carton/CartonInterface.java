package com.huanqiuyuncang.service.wms.carton;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface CartonInterface {
    int deleteByPrimaryKey(String cartonid) throws Exception;

    int insert(CartonEntity record) throws Exception;

    int insertSelective(PageData pageData) throws Exception;

    CartonEntity selectByPrimaryKey(String cartonid)throws Exception ;

    int updateByPrimaryKeySelective(PageData pageData)throws Exception ;

    int updateByPrimaryKey(CartonEntity record)throws Exception ;

    List<CartonEntity> datalistPage(Page page)throws Exception ;

    void deleteAll(String[] arrayDATA_ids) throws Exception;

    List<CartonEntity> selectAll() throws Exception;

    CartonEntity findCartonByCartonCode(String cartontype)throws Exception;

}