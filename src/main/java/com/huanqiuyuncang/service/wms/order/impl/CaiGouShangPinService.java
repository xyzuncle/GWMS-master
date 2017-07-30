package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.CaiGouShangPinDAO;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.service.wms.order.CaiGouShangPinInterface;
import com.huanqiuyuncang.util.PageData;
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

    @Override
    public String selectKuweiByBarcodeAndCangku(String barCode,String cangkuid) {
        return caiGouShangPinDAO.selectKuweiByBarcodeAndCangku(barCode,cangkuid);
    }

    @Override
    public  List<PageData> selectHistoryInfoByBarcode(String barCode) {
        return  caiGouShangPinDAO.selectHistoryInfoByBarcode(barCode);
    }
}
