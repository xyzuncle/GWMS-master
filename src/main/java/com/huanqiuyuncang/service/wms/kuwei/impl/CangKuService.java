package com.huanqiuyuncang.service.wms.kuwei.impl;

import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.kuwei.ShangPinKuWeiDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
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

    @Autowired
    private ShangPinKuWeiDAO shangPinKuWeiDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        List<ShangPinKuWeiEntity> shangPinKuWeiEntities = shangPinKuWeiDAO.selectByKuWei(id);
        for (ShangPinKuWeiEntity shangpinkuwei: shangPinKuWeiEntities) {
            shangPinKuWeiDAO.deleteByPrimaryKey(shangpinkuwei.getId());
        }
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

    @Override
    public CangKuEntity selectByCangKu(String cangku) {

        return  cangKuDAO.selectByCangKu(cangku);
    }

    @Override
    public  List<CangKuEntity> selectByCreateUser(String createUser) {
        return cangKuDAO.selectByCreateUser(createUser);
    }

    @Override
    public List<CangKuEntity> selectByCangkuuser(String cangkuuser) {

        return cangKuDAO.selectByCangkuuser(cangkuuser);
    }

    @Override
    public List<CangKuEntity> getCangku(String cangkushuxing) {
        return cangKuDAO.getCangku(cangkushuxing);
    }

}
