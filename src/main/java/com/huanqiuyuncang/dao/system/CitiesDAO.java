package com.huanqiuyuncang.dao.system;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.AreasEntity;
import com.huanqiuyuncang.entity.system.CitiesEntity;

import java.util.List;

public interface CitiesDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(CitiesEntity record);

    int insertSelective(CitiesEntity record);

    CitiesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CitiesEntity record);

    int updateByPrimaryKey(CitiesEntity record);

    List<CitiesEntity> datalistPage(Page page);

    CitiesEntity selectByCityid(String cityid);
}