package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.CaiGouDingDanDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.service.wms.order.CaiGouDingDanInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/30.
 */
@Service
public class CaiGouDingDanService implements CaiGouDingDanInterface {
    @Autowired
    private CaiGouDingDanDAO caiGouDingDanDAO;
    @Override
    public int deleteByPrimaryKey(String caigoudingdanid) {
        return caiGouDingDanDAO.deleteByPrimaryKey(caigoudingdanid);
    }

    @Override
    public int insert(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.insert(record);
    }

    @Override
    public int insertSelective(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.insertSelective(record);
    }

    @Override
    public CaiGouDingDanEntity selectByPrimaryKey(String caigoudingdanid) {
        return caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
    }

    @Override
    public int updateByPrimaryKeySelective(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CaiGouDingDanEntity> datalistPage(Page page) {
        return caiGouDingDanDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            caiGouDingDanDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void shenheAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yiqueren");
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }

    @Override
    public void zuofeiAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yizuofei");
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }
}
