package com.huanqiuyuncang.service.system.cities.impl;

import com.huanqiuyuncang.dao.system.CitiesDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.CitiesEntity;
import com.huanqiuyuncang.service.system.cities.CitiesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/7/28.
 */
@Service
public class CitiesService implements CitiesInterface {
    @Autowired
    private CitiesDAO citiesDAO;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return citiesDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CitiesEntity record) {
        return citiesDAO.insert(record);
    }

    @Override
    public int insertSelective(CitiesEntity record) {
        return citiesDAO.insertSelective(record);
    }

    @Override
    public CitiesEntity selectByPrimaryKey(Integer id) {
        return citiesDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CitiesEntity record) {
        return citiesDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CitiesEntity record) {
        return citiesDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CitiesEntity> list(Page page) {
        return citiesDAO.datalistPage(page);
    }

    @Override
    public CitiesEntity selectByCityid(String cityid) {
        return citiesDAO.selectByCityid(cityid);
    }
}
