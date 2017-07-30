package com.huanqiuyuncang.service.system.cities;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.CitiesEntity;

import java.util.List;

public interface CitiesInterface {
    int deleteByPrimaryKey(Integer id);

    int insert(CitiesEntity record);

    int insertSelective(CitiesEntity record);

    CitiesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CitiesEntity record);

    int updateByPrimaryKey(CitiesEntity record);


    List<CitiesEntity> list(Page page);


    CitiesEntity selectByCityid(String cityid);
}