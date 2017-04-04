package com.huanqiuyuncang.dao;

import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;

public interface LuggageMailEntityMapper {
    int deleteByPrimaryKey(String luggagemailid);

    int insert(LuggageMailEntity record);

    int insertSelective(LuggageMailEntity record);

    LuggageMailEntity selectByPrimaryKey(String luggagemailid);

    int updateByPrimaryKeySelective(LuggageMailEntity record);

    int updateByPrimaryKey(LuggageMailEntity record);
}