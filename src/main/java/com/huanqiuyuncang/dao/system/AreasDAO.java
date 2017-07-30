package com.huanqiuyuncang.dao.system;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.AreasEntity;

import java.util.List;

public interface AreasDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(AreasEntity record);

    int insertSelective(AreasEntity record);

    AreasEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreasEntity record);

    int updateByPrimaryKey(AreasEntity record);

    List<AreasEntity> datalistPage(Page page);
}