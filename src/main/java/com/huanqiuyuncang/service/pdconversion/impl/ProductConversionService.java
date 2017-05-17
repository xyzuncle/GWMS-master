package com.huanqiuyuncang.service.pdconversion.impl;

import com.huanqiuyuncang.dao.pdconversion.ProductConversionDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.service.pdconversion.ProductConversionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/4/9.
 */
@Service
public class ProductConversionService implements ProductConversionInterface {

    @Autowired
    private ProductConversionDAO productConversionDAO;

    @Override
    public int deleteByPrimaryKey(String productconversionid) {
        return productConversionDAO.deleteByPrimaryKey(productconversionid);
    }

    @Override
    public int insert(ProductConversionEntity record) {
        return productConversionDAO.insert(record);
    }

    @Override
    public int insertSelective(ProductConversionEntity record) {
        return productConversionDAO.insertSelective(record);
    }

    @Override
    public ProductConversionEntity selectByPrimaryKey(String productconversionid) {
        return productConversionDAO.selectByPrimaryKey(productconversionid);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductConversionEntity record) {
        return productConversionDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProductConversionEntity record) {
        return productConversionDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ProductConversionEntity> datalistPage(Page page) {
        return productConversionDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (int i = 0;i<arrayDATA_ids.length;i++){
            productConversionDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }
}
