package com.huanqiuyuncang.service.wms.luggagemail.impl;

import com.huanqiuyuncang.dao.luggagemail.LuggageMailDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.service.wms.luggagemail.LuggageMailInterface;
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
public class LuggageMailService implements LuggageMailInterface {
    @Autowired
    private LuggageMailDAO luggageMailDAO;
    @Override
    public int deleteByPrimaryKey(String luggagemailid)throws Exception {
        return luggageMailDAO.deleteByPrimaryKey(luggagemailid);
    }

    @Override
    public int insert(LuggageMailEntity record)throws Exception {
        return luggageMailDAO.insert(record);
    }

    @Override
    public int insertSelective(PageData record)throws Exception {
        LuggageMailEntity luggageMailEntity = (LuggageMailEntity) BeanMapUtil.mapToObject(record, LuggageMailEntity.class);
        return luggageMailDAO.insertSelective(luggageMailEntity);
    }

    @Override
    public LuggageMailEntity selectByPrimaryKey(String luggagemailid) throws Exception{

        return luggageMailDAO.selectByPrimaryKey(luggagemailid);
    }

    @Override
    public int updateByPrimaryKeySelective(PageData pageData)throws Exception {
        LuggageMailEntity luggageMailEntity = (LuggageMailEntity) BeanMapUtil.mapToObject(pageData, LuggageMailEntity.class);
        return luggageMailDAO.updateByPrimaryKeySelective(luggageMailEntity);
    }

    @Override
    public int updateByPrimaryKey(LuggageMailEntity record)throws Exception {

        return luggageMailDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<LuggageMailEntity> datalistPage(Page page) throws Exception {
        return luggageMailDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) throws Exception {
        Arrays.asList(arrayDATA_ids).forEach(id->luggageMailDAO.deleteByPrimaryKey(id));
    }

    @Override
    public List<LuggageMailEntity> selectAll() throws Exception {
        return luggageMailDAO.selectAll();
    }

    @Override
    public LuggageMailEntity findLuggageMailByLuggageMailCode(String luggagemailcode) throws Exception {
        return luggageMailDAO.findLuggageMailByLuggageMailCode(luggagemailcode);
    }
}
