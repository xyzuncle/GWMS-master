package com.huanqiuyuncang.service.wms.customs.impl;

import com.huanqiuyuncang.dao.customs.CustomsDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customs.CustomsEntity;
import com.huanqiuyuncang.service.wms.customs.CustomsInterface;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/4/3.
 */
@Service
public class CustomsService implements CustomsInterface {

    @Autowired
    private CustomsDAO customsDAO;

    @Override
    public int deleteByPrimaryKey(String customsid)throws Exception {
        return customsDAO.deleteByPrimaryKey(customsid);
    }

    @Override
    public int insert(CustomsEntity record)throws Exception {
        return customsDAO.insert(record);
    }

    @Override
    public int insertSelective(PageData record) throws Exception {
        CustomsEntity customsEntity = (CustomsEntity) BeanMapUtil.mapToObject(record, CustomsEntity.class);
        return customsDAO.insertSelective(customsEntity);
    }

    @Override
    public CustomsEntity selectByPrimaryKey(String customsid) throws Exception{

        return customsDAO.selectByPrimaryKey(customsid);
    }

    @Override
    public int updateByPrimaryKeySelective(PageData record)throws Exception {
        CustomsEntity customsEntity = (CustomsEntity) BeanMapUtil.mapToObject(record, CustomsEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        customsEntity.setUpdatetime(date);
        customsEntity.setUpdateuser(username);
        return customsDAO.updateByPrimaryKeySelective(customsEntity);
    }

    @Override
    public int updateByPrimaryKey(CustomsEntity record) throws Exception{

        return customsDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CustomsEntity> datalistPage(Page page)throws Exception {
        return customsDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) throws Exception {
        Arrays.asList(arrayDATA_ids).forEach(id->customsDAO.deleteByPrimaryKey(id));
    }

    @Override
    public List<CustomsEntity> selectAll() throws Exception {
        return customsDAO.selectAll();
    }

    @Override
    public CustomsEntity findCustomsByCustomsCode(String customscode) throws Exception {
        return customsDAO.findCustomsByCustomsCode(customscode);
    }
}
