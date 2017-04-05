package com.huanqiuyuncang.service.wms.brand.impl;

import com.huanqiuyuncang.dao.brand.BrandDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;
import com.huanqiuyuncang.service.wms.brand.BrandInterface;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lzf on 2017/4/3.
 */
@Service
public class BrandService implements BrandInterface {

    @Autowired
    private BrandDAO brandDAO;

    @Override
    public int deleteByPrimaryKey(String brandid) throws Exception {
        return brandDAO.deleteByPrimaryKey(brandid);
    }

    @Override
    public int insert(BrandEntity record) throws Exception {
        return brandDAO.insert(record);
    }

    @Override
    public int insertSelective(PageData pageData)  throws Exception{
        BrandEntity brand = (BrandEntity) BeanMapUtil.mapToObject(pageData, BrandEntity.class);
        return brandDAO.insertSelective(brand);
    }

    @Override
    public BrandEntity selectByPrimaryKey(String brandid) throws Exception {
        return brandDAO.selectByPrimaryKey(brandid);
    }

    @Override
    public int updateByPrimaryKeySelective(PageData pageData) throws Exception {
        BrandEntity brand = (BrandEntity) BeanMapUtil.mapToObject(pageData, BrandEntity.class);
        return brandDAO.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int updateByPrimaryKey(BrandEntity record) throws Exception {
        return brandDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<BrandEntity> datalistPage(Page page) throws Exception {
        return brandDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) throws Exception {
        Arrays.asList(arrayDATA_ids).forEach(id->brandDAO.deleteByPrimaryKey(id));
    }

    @Override
    public List<BrandEntity> selectAll() throws Exception {
        return brandDAO.selectAll();
    }

    @Override
    public BrandEntity findBrandByBrandCode(String brandcode) throws Exception {
        return brandDAO.findBrandByBrandCode(brandcode);
    }
}
