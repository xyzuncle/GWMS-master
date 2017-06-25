package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.order.OrderProductEntity;

import java.util.List;

public interface OrderProductDAO {
    int deleteByPrimaryKey(String orderproducrtid);

    int insert(OrderProductEntity record);

    int insertSelective(OrderProductEntity record);

    OrderProductEntity selectByPrimaryKey(String orderproducrtid);

    int updateByPrimaryKeySelective(OrderProductEntity record);

    int updateByPrimaryKey(OrderProductEntity record);

    List<OrderProductEntity> selectOrderProduct(String customerordernum);

    void deleteByCustomerordernum(String token);

    Integer orderproductSum(String innerpackagenum);

    List<OrderProductEntity> selectOrderProductBypackagenum(String innerpackagenum);

    String selectProductsumByOrderNum(String customerordernum);
}