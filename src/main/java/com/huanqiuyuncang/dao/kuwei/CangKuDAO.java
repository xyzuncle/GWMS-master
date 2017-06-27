package com.huanqiuyuncang.dao.kuwei;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;

import java.util.List;

public interface CangKuDAO {
    int deleteByPrimaryKey(String id);

    int insert(CangKuEntity record);

    int insertSelective(CangKuEntity record);

    CangKuEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CangKuEntity record);

    int updateByPrimaryKey(CangKuEntity record);

    List<CangKuEntity> datalistPage(Page page);

    List<CangKuEntity> getCangku(String cangkushuxing);
}