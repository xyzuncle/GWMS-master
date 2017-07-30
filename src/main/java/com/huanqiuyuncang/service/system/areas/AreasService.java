package com.huanqiuyuncang.service.system.areas;

import com.huanqiuyuncang.dao.system.AreasDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.system.AreasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/7/28.
 */
@Service
public class AreasService implements AreasInterface {
    @Autowired
    private AreasDAO areasDAO;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return areasDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AreasEntity record) {
        return areasDAO.insert(record);
    }

    @Override
    public int insertSelective(AreasEntity record) {
        return areasDAO.insertSelective(record);
    }

    @Override
    public AreasEntity selectByPrimaryKey(Integer id) {
        return areasDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AreasEntity record) {
        return areasDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AreasEntity record) {
        return areasDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<AreasEntity> list(Page page) {
        return areasDAO.datalistPage(page);
    }
}
