package com.huanqiuyuncang.service.wms.customer.impl;

import com.huanqiuyuncang.dao.customer.GongYingShangDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.service.wms.customer.GongYingShangInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/30.
 */
@Service
public class GongYingShangService implements GongYingShangInterface {

    @Autowired
    private GongYingShangDAO gongYingShangDAO;

    @Override
    public int deleteByPrimaryKey(String gongyingshangid) {
        return gongYingShangDAO.deleteByPrimaryKey(gongyingshangid);
    }

    @Override
    public int insert(GongYingShangEntity record) {
        return gongYingShangDAO.insert(record);
    }

    @Override
    public int insertSelective(GongYingShangEntity record) {
        return gongYingShangDAO.insertSelective(record);
    }

    @Override
    public GongYingShangEntity selectByPrimaryKey(String gongyingshangid) {
        return gongYingShangDAO.selectByPrimaryKey(gongyingshangid);
    }

    @Override
    public int updateByPrimaryKeySelective(GongYingShangEntity record) {
        return gongYingShangDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GongYingShangEntity record) {
        return gongYingShangDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<GongYingShangEntity> datalistPage(Page page) {
        return gongYingShangDAO.datalistPage(page);
    }

    @Override
    public List<GongYingShangEntity> selectAll() {
        return gongYingShangDAO.selectAll();
    }

    @Override
    public List<GongYingShangEntity> selectByLoginName(String loginname) {
        return gongYingShangDAO.selectByLoginName(loginname);
    }

    @Override
    public String selectNameByCode(String code) {
        return gongYingShangDAO.selectNameByCode(code);
    }

    @Override
    public String selectCodeByLoginName(String loginname) {

        return gongYingShangDAO.selectCodeByLoginName(loginname);
    }

    @Override
    public GongYingShangEntity selectgongyingshangByCode(String code) {
        return gongYingShangDAO.selectGongyingshangByCode(code);
    }

    @Override
    public GongYingShangEntity selectgongyingshangByName(String name) {
        return gongYingShangDAO.selectGongyingshangByName(name);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            gongYingShangDAO.deleteByPrimaryKey(id);
        }
    }




}
