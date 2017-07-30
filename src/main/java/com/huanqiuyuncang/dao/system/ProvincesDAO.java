package com.huanqiuyuncang.dao.system;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.AreasEntity;
import com.huanqiuyuncang.entity.system.ProvincesEntity;

import java.util.List;

public interface ProvincesDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvincesEntity record);

    int insertSelective(ProvincesEntity record);

    ProvincesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvincesEntity record);

    int updateByPrimaryKey(ProvincesEntity record);

    List<ProvincesEntity> datalistPage(Page page);

    ProvincesEntity selectByProvinceid(String provinceid);
}