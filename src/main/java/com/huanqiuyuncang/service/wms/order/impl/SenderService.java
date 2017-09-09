package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.SenderDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.SenderEntity;
import com.huanqiuyuncang.service.wms.order.SenderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/8/20.
 */
@Service
public class SenderService implements SenderInterface {

    @Autowired
    private SenderDAO senderDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return senderDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SenderEntity record) {
        return senderDAO.insert(record);
    }

    @Override
    public int insertSelective(SenderEntity record) {
        return senderDAO.insertSelective(record);
    }

    @Override
    public SenderEntity selectByPrimaryKey(String id) {
        return senderDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SenderEntity record) {
        return senderDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SenderEntity record) {
        return senderDAO.updateByPrimaryKey(record);
    }

    public List<SenderEntity> datalistPage(Page page) {
        return senderDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            senderDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<SenderEntity> selectByCustomercode(String customercode) {
        return senderDAO.selectByCustomercode(customercode);
    }
}
