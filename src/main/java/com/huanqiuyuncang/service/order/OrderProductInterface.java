package com.huanqiuyuncang.service.order;

import com.huanqiuyuncang.entity.order.OrderProductEntity;

import java.util.List;

public interface OrderProductInterface {
    int deleteByPrimaryKey(String orderproducrtid);

    int insert(OrderProductEntity record);

    int insertSelective(OrderProductEntity record);

    OrderProductEntity selectByPrimaryKey(String orderproducrtid);

    int updateByPrimaryKeySelective(OrderProductEntity record);

    int updateByPrimaryKey(OrderProductEntity record);

    List<OrderProductEntity> selectOrderProduct(String customerordernum);
}