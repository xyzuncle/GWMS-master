package com.huanqiuyuncang.dao.customer;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;

import java.util.List;

public interface CustomerDAO {
    int deleteByPrimaryKey(String customerid);

    int insert(CustomerEntity record);

    int insertSelective(CustomerEntity record);

    CustomerEntity selectByPrimaryKey(String customerid);

    int updateByPrimaryKeySelective(CustomerEntity record);

    int updateByPrimaryKey(CustomerEntity record);

    List<CustomerEntity> datalistPage(Page page);

    List<CustomerEntity> selectAll();

    List<CustomerEntity> selectByCreateUser(String createUser);

    String selectNameByCode(String code);

    String selectCodeByCode(String code);

    CustomerEntity selectCustomerByCode(String customernum);
}