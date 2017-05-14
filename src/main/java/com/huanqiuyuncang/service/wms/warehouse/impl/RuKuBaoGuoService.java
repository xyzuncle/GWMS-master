package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.warehouse.RuKuBaoGuoDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.service.wms.warehouse.RuKuBaoGuoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/13.
 */
@Service
public class RuKuBaoGuoService implements RuKuBaoGuoInterface {
    @Autowired
    private RuKuBaoGuoDAO ruKuBaoGuoDAO;
    @Override
    public int deleteByPrimaryKey(String rukubaoguoid) {
        return ruKuBaoGuoDAO.deleteByPrimaryKey(rukubaoguoid);
    }

    @Override
    public int insert(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.insert(record);
    }

    @Override
    public int insertSelective(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.insertSelective(record);
    }

    @Override
    public RuKuBaoGuoEntity selectByPrimaryKey(String rukubaoguoid) {
        return ruKuBaoGuoDAO.selectByPrimaryKey(rukubaoguoid);
    }

    @Override
    public int updateByPrimaryKeySelective(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<RuKuBaoGuoEntity> datalistPage(Page page) {
        return ruKuBaoGuoDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for(int i = 0;i<arrayDATA_ids.length;i++){
            ruKuBaoGuoDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }
}
