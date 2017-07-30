package com.huanqiuyuncang.service.system.provinces.impl;

import com.huanqiuyuncang.dao.system.ProvincesDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.ProvincesEntity;
import com.huanqiuyuncang.service.system.provinces.ProvincesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/7/28.
 */
@Service
public class ProvincesService implements ProvincesInterface {
    @Autowired
    private ProvincesDAO provincesDAO;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return provincesDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProvincesEntity record) {
        return provincesDAO.insert(record);
    }

    @Override
    public int insertSelective(ProvincesEntity record) {
        return provincesDAO.insertSelective(record);
    }

    @Override
    public ProvincesEntity selectByPrimaryKey(Integer id) {
        return provincesDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProvincesEntity record) {
        return provincesDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProvincesEntity record) {
        return provincesDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ProvincesEntity> list(Page page) {
        return provincesDAO.datalistPage(page);
    }

    @Override
    public ProvincesEntity selectByProvinceid(String provinceid) {
        return provincesDAO.selectByProvinceid(provinceid);
    }
}
