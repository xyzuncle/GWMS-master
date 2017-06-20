package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.CaiGouShangPinDAO;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.service.wms.order.CaiGouShangPinInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/6/19.
 */
@Service
public class CaiGouShangPinService implements CaiGouShangPinInterface {

    @Autowired
    private CaiGouShangPinDAO caiGouShangPinDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return caiGouShangPinDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CaiGouShangPinEntity record) {
        return caiGouShangPinDAO.insert(record);
    }

    @Override
    public int insertSelective(CaiGouShangPinEntity record) {
        return caiGouShangPinDAO.insertSelective(record);
    }

    @Override
    public CaiGouShangPinEntity selectByPrimaryKey(String id) {
        return caiGouShangPinDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CaiGouShangPinEntity record) {
        return caiGouShangPinDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CaiGouShangPinEntity record) {
        return caiGouShangPinDAO.updateByPrimaryKey(record);
    }

    public List<CaiGouShangPinEntity> selectByCaiGouDingDanId(String caigoudingdanid){
        return caiGouShangPinDAO.selectByCaiGouDingDanId(caigoudingdanid);
    }
}
