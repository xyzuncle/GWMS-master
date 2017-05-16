package com.huanqiuyuncang.service.wms.customer.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.service.wms.customer.CustomerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/4/12.
 */
@Service
public class CustomerService implements CustomerInterface {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public int deleteByPrimaryKey(String customerid) {
        return customerDAO.deleteByPrimaryKey(customerid);
    }

    @Override
    public int insert(CustomerEntity record) {
        return customerDAO.insert(record);
    }

    @Override
    public int insertSelective(CustomerEntity record) {
        return customerDAO.insertSelective(record);
    }

    @Override
    public CustomerEntity selectByPrimaryKey(String customerid) {
        return customerDAO.selectByPrimaryKey(customerid);
    }

    @Override
    public int updateByPrimaryKeySelective(CustomerEntity record) {
        return customerDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CustomerEntity record) {
        return customerDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CustomerEntity> datalistPage(Page page) {
        return customerDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id :arrayDATA_ids) {
            customerDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<CustomerEntity> selectAll() {
        return customerDAO.selectAll();
    }

    @Override
    public List<CustomerEntity> selectByCreateUser(String createUser) {
        return customerDAO.selectByCreateUser(createUser);
    }

    @Override
    public String selectNameByCode(String customernum) {

        return customerDAO.selectNameByCode(customernum);
    }

    @Override
    public CustomerEntity selectCustomerByCode(String customercode) {
        return customerDAO.selectCustomerByCode(customercode);
    }
}
