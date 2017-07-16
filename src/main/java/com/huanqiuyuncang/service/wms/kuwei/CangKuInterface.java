package com.huanqiuyuncang.service.wms.kuwei;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;

import java.util.List;

public interface CangKuInterface {
    int deleteByPrimaryKey(String id);

    int insert(CangKuEntity record);

    int insertSelective(CangKuEntity record);

    CangKuEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CangKuEntity record);

    int updateByPrimaryKey(CangKuEntity record);

    List<CangKuEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);

    CangKuEntity selectByCangKu(String cangku);

    List<CangKuEntity> selectByCreateUser(String createUser);

    List<CangKuEntity> selectByCangkuuser(String cangkuuser);

    List<CangKuEntity> getCangku(String cangkushuxing);

}