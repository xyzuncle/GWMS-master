package com.huanqiuyuncang.dao.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;

import java.util.List;

public interface RuKuBaoGuoDAO {
    int deleteByPrimaryKey(String rukubaoguoid);

    int insert(RuKuBaoGuoEntity record);

    int insertSelective(RuKuBaoGuoEntity record);

    RuKuBaoGuoEntity selectByPrimaryKey(String rukubaoguoid);

    int updateByPrimaryKeySelective(RuKuBaoGuoEntity record);

    int updateByPrimaryKey(RuKuBaoGuoEntity record);

    List<RuKuBaoGuoEntity> datalistPage(Page page);
}