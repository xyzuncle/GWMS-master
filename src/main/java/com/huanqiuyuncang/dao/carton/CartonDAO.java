package com.huanqiuyuncang.dao.carton;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.carton.CartonEntity;

import java.util.List;

public interface CartonDAO {
    int deleteByPrimaryKey(String cartonid);

    int insert(CartonEntity record);

    int insertSelective(CartonEntity record);

    CartonEntity selectByPrimaryKey(String cartonid);

    int updateByPrimaryKeySelective(CartonEntity record);

    int updateByPrimaryKey(CartonEntity record);

    List<CartonEntity> datalistPage(Page page);
}