package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.order.OrderProductDAO;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.service.wms.order.OrderProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/4/13.
 */
@Service
public class OrderProductService implements OrderProductInterface {

    @Autowired
    private OrderProductDAO orderProductDAO;
    @Override
    public int deleteByPrimaryKey(String orderproducrtid) {
        return orderProductDAO.deleteByPrimaryKey(orderproducrtid);
    }

    @Override
    public int insert(OrderProductEntity record) {
        return orderProductDAO.insert(record);
    }

    @Override
    public int insertSelective(OrderProductEntity record) {
        return orderProductDAO.insertSelective(record);
    }

    @Override
    public OrderProductEntity selectByPrimaryKey(String orderproducrtid) {
        return orderProductDAO.selectByPrimaryKey(orderproducrtid);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderProductEntity record) {
        return orderProductDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderProductEntity record) {

        return orderProductDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<OrderProductEntity> selectOrderProduct(String customerordernum) {
        return orderProductDAO.selectOrderProduct(customerordernum);
    }
}
