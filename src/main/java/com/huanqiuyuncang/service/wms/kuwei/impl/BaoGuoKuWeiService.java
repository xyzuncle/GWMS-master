package com.huanqiuyuncang.service.wms.kuwei.impl;

import com.huanqiuyuncang.dao.kuwei.BaoGuoKuWeiDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.service.wms.kuwei.BaoGuoKuWeiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/6/11.
 */
@Service
public class BaoGuoKuWeiService implements BaoGuoKuWeiInterface {
    @Autowired
    private BaoGuoKuWeiDAO baoGuoKuWeiDAO;
    @Override
    public int deleteByPrimaryKey(String id) {
        return baoGuoKuWeiDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BaoGuoKuWeiEntity record) {
        return baoGuoKuWeiDAO.insert(record);
    }

    @Override
    public int insertSelective(BaoGuoKuWeiEntity record) {
        return baoGuoKuWeiDAO.insertSelective(record);
    }

    @Override
    public BaoGuoKuWeiEntity selectByPrimaryKey(String id) {
        return baoGuoKuWeiDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BaoGuoKuWeiEntity record) {
        return baoGuoKuWeiDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BaoGuoKuWeiEntity record) {
        return baoGuoKuWeiDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<BaoGuoKuWeiEntity> datalistPage(Page page) {
        return baoGuoKuWeiDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            baoGuoKuWeiDAO.deleteByPrimaryKey(id);
        }
    }
}
