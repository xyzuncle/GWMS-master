package com.huanqiuyuncang.service.wms.saomiao.impl;

import com.huanqiuyuncang.dao.saomiao.ShangPinSaomiaoDAO;
import com.huanqiuyuncang.entity.saomiao.ShangPinSaomiaoEntity;
import com.huanqiuyuncang.service.wms.saomiao.ShangPinSaomiaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lzf on 2017/7/2.
 */
@Service
public class ShangPinSaomiaoService implements ShangPinSaomiaoInterface {

    @Autowired
    private ShangPinSaomiaoDAO shangPinSaomiaoDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return shangPinSaomiaoDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ShangPinSaomiaoEntity record) {
        return shangPinSaomiaoDAO.insertSelective(record);
    }

    @Override
    public int insertSelective(ShangPinSaomiaoEntity record) {
        return shangPinSaomiaoDAO.insertSelective(record);
    }

    @Override
    public ShangPinSaomiaoEntity selectByPrimaryKey(String id) {
        return shangPinSaomiaoDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ShangPinSaomiaoEntity record) {
        return shangPinSaomiaoDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ShangPinSaomiaoEntity record) {
        return shangPinSaomiaoDAO.updateByPrimaryKey(record);
    }

    @Override
    public Integer selectSaomiaoSumByShangpin(String shangpinid) {
        return shangPinSaomiaoDAO.selectSaomiaoSumByShangpin(shangpinid);
    }
}
