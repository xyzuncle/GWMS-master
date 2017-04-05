package com.huanqiuyuncang.service.wms.luggagemail;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface LuggageMailInterface {
    int deleteByPrimaryKey(String luggagemailid) throws Exception;

    int insert(LuggageMailEntity record)  throws Exception;

    int insertSelective(PageData record) throws Exception;

    LuggageMailEntity selectByPrimaryKey(String luggagemailid) throws Exception;

    int updateByPrimaryKeySelective(PageData pageData) throws Exception;

    int updateByPrimaryKey(LuggageMailEntity record) throws Exception;

    List<LuggageMailEntity> datalistPage(Page page) throws Exception;

    void deleteAll(String[] arrayDATA_ids) throws Exception;

    List<LuggageMailEntity> selectAll() throws Exception;

    LuggageMailEntity findLuggageMailByLuggageMailCode(String luggagemailcode) throws Exception;
}