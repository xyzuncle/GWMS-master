package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.OrdernumDAO;
import com.huanqiuyuncang.entity.order.OrdernumEntity;
import com.huanqiuyuncang.service.wms.order.OrdernumInterface;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/8/25.
 */
@Service
public class OrdernumService implements OrdernumInterface {

    @Autowired
    private OrdernumDAO ordernumDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return ordernumDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrdernumEntity record) {
        return ordernumDAO.insert(record);
    }

    @Override
    public int insertSelective(OrdernumEntity record) {
        return ordernumDAO.insertSelective(record);
    }

    @Override
    public OrdernumEntity selectByPrimaryKey(String id) {
        return ordernumDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrdernumEntity record) {
        return ordernumDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrdernumEntity record) {
        return ordernumDAO.updateByPrimaryKey(record);
    }

    @Override
    public OrdernumEntity selectByDingdanhao(String dingdanhao) {
        return ordernumDAO.selectByDingdanhao(dingdanhao);
    }

    @Override
    public List<OrdernumEntity> selectByBaoguoDanhao(String baoguodanhao) {
        return ordernumDAO.selectByBaoguoDanhao(baoguodanhao);
    }

    @Override
    public List<OrdernumEntity> selectByPartentId(String id) {
        return ordernumDAO.selectByPartentId(id);
    }

    @Override
    public List<String> selectOrdernumToexcel() {
        return ordernumDAO.selectOrdernumToexcel();
    }

    @Override
    public List<PageData> selectOrder4YT() {
        return ordernumDAO.selectOrder4YT();
    }

    @Override
    public List<PageData> selectFenjianDanInfoById(String id) {
        return ordernumDAO.selectFenjianDanInfoById(id);
    }

    @Override
    public List<PageData> selectZongFenJianDanInfoBy(String data_ids) {
        return ordernumDAO.selectZongFenJianDanInfoBy(data_ids);
    }
}
