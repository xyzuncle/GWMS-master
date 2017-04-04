package com.huanqiuyuncang.dao.luggagemail;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;

import java.util.List;

public interface LuggageMailDAO {
    int deleteByPrimaryKey(String luggagemailid);

    int insert(LuggageMailEntity record);

    int insertSelective(LuggageMailEntity record);

    LuggageMailEntity selectByPrimaryKey(String luggagemailid);

    int updateByPrimaryKeySelective(LuggageMailEntity record);

    int updateByPrimaryKey(LuggageMailEntity record);

    List<LuggageMailEntity> datalistPage(Page page);

    List<LuggageMailEntity> selectAll();

    LuggageMailEntity findLuggageMailByLuggageMailCode(String luggagemailcode);
}