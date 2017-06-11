package com.huanqiuyuncang.service.wms.kuwei.impl;

import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/6/11.
 */
@Service
public class CangKuService implements CangKuInterface {
    @Autowired
    private CangKuDAO cangKuDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return cangKuDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CangKuEntity record) {
        return cangKuDAO.insert(record);
    }

    @Override
    public int insertSelective(CangKuEntity record) {
        return cangKuDAO.insertSelective(record);
    }

    @Override
    public CangKuEntity selectByPrimaryKey(String id) {
        return cangKuDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CangKuEntity record) {
        return cangKuDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CangKuEntity record) {
        return cangKuDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CangKuEntity> datalistPage(Page page) {
        return cangKuDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            cangKuDAO.deleteByPrimaryKey(id);
        }
    }
}
