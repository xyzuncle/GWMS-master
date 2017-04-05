package com.huanqiuyuncang.service.wms.carton.impl;

import com.huanqiuyuncang.dao.carton.CartonDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.carton.CartonEntity;
import com.huanqiuyuncang.service.wms.carton.CartonInterface;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lzf on 2017/4/3.
 */
@Service
public class CartonService implements CartonInterface {
    @Autowired
    private CartonDAO cartonDAO;

    @Override
    public int deleteByPrimaryKey(String cartonid)throws Exception  {
        return cartonDAO.deleteByPrimaryKey(cartonid);
    }

    @Override
    public int insert(CartonEntity record)throws Exception  {

        return cartonDAO.insert(record);
    }

    @Override
    public int insertSelective(PageData pageData) throws Exception {
        CartonEntity cartonEntity = (CartonEntity) BeanMapUtil.mapToObject(pageData, CartonEntity.class);
        return cartonDAO.insertSelective(cartonEntity);
    }

    @Override
    public CartonEntity selectByPrimaryKey(String cartonid)throws Exception  {
        return cartonDAO.selectByPrimaryKey(cartonid);
    }

    @Override
    public int updateByPrimaryKeySelective(PageData pageData)throws Exception  {
        CartonEntity cartonEntity = (CartonEntity) BeanMapUtil.mapToObject(pageData, CartonEntity.class);
        return cartonDAO.updateByPrimaryKeySelective(cartonEntity);
    }

    @Override
    public int updateByPrimaryKey(CartonEntity record)throws Exception  {
        return cartonDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CartonEntity> datalistPage(Page page) throws Exception {
        return cartonDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) throws Exception {
        Arrays.asList(arrayDATA_ids).forEach(id->cartonDAO.deleteByPrimaryKey(id));
    }

    @Override
    public List<CartonEntity> selectAll() throws Exception {
        return cartonDAO.selectAll();
    }



    @Override
    public CartonEntity findCartonByCartonCode(String cartontype) throws Exception {
        return cartonDAO.findCartonByCartonCode(cartontype);
    }
}
