package com.huanqiuyuncang.service.wms.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface RuKuBaoGuoInterface {
    int deleteByPrimaryKey(String rukubaoguoid);

    int insert(RuKuBaoGuoEntity record);

    int insertSelective(RuKuBaoGuoEntity record);

    RuKuBaoGuoEntity selectByPrimaryKey(String rukubaoguoid);

    int updateByPrimaryKeySelective(RuKuBaoGuoEntity record);

    int updateByPrimaryKey(RuKuBaoGuoEntity record);

    List<RuKuBaoGuoEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);

    PageData saveruku(List<String> danhaoList);
}