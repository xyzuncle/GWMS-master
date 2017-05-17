package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.warehouse.ChuKuShangPinDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.service.wms.warehouse.ChuKuShangPinInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/13.
 */
@Service
public class ChuKuShangPinService implements ChuKuShangPinInterface {

    @Autowired
    private ChuKuShangPinDAO chuKuShangPinDAO;


    @Override
    public int deleteByPrimaryKey(String chukushangpinid) {
        return chuKuShangPinDAO.deleteByPrimaryKey(chukushangpinid);
    }

    @Override
    public int insert(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.insert(record);
    }

    @Override
    public int insertSelective(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.insertSelective(record);
    }

    @Override
    public ChuKuShangPinEntity selectByPrimaryKey(String chukushangpinid) {
        return chuKuShangPinDAO.selectByPrimaryKey(chukushangpinid);
    }

    @Override
    public int updateByPrimaryKeySelective(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ChuKuShangPinEntity> datalistPage(Page page) {
        return chuKuShangPinDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for(int i = 0 ; i<arrayDATA_ids.length ; i++){
            chuKuShangPinDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }
}
