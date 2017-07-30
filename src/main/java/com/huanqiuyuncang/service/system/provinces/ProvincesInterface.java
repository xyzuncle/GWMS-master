package com.huanqiuyuncang.service.system.provinces;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.ProvincesEntity;

import java.util.List;

public interface ProvincesInterface {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvincesEntity record);

    int insertSelective(ProvincesEntity record);

    ProvincesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvincesEntity record);

    int updateByPrimaryKey(ProvincesEntity record);

    List<ProvincesEntity> list(Page page);

    ProvincesEntity selectByProvinceid(String provinceid);
}