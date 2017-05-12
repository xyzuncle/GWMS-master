package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.InnerPackageDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerPackageEntity;
import com.huanqiuyuncang.service.wms.order.InnerPackageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/3.
 */
@Service
public class InnerPackageService implements InnerPackageInterface {

    @Autowired
    private InnerPackageDAO innerPackageDAO;

    @Override
    public int deleteByPrimaryKey(String innerpackageid) {
        return innerPackageDAO.deleteByPrimaryKey(innerpackageid);
    }

    @Override
    public int insert(InnerPackageEntity record) {
        return innerPackageDAO.insert(record);
    }

    @Override
    public int insertSelective(InnerPackageEntity record) {
        return innerPackageDAO.insertSelective(record);
    }

    @Override
    public InnerPackageEntity selectByPrimaryKey(String innerpackageid) {
        return innerPackageDAO.selectByPrimaryKey(innerpackageid);
    }

    @Override
    public int updateByPrimaryKeySelective(InnerPackageEntity record) {
        return innerPackageDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InnerPackageEntity record) {
        return innerPackageDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<InnerPackageEntity> datalistPage(Page page) {
        return innerPackageDAO.datalistPage(page);
    }

    @Override
    public void insertPackageInfo(InnerPackageEntity innerPackageEntity) {

    }


}
