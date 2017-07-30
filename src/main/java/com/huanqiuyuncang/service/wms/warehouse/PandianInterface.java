package com.huanqiuyuncang.service.wms.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.PandianEntity;

import java.util.List;
import java.util.Map;

public interface PandianInterface {
    int deleteByPrimaryKey(String id);

    int insert(PandianEntity record);

    int insertSelective(PandianEntity record);

    PandianEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PandianEntity record);

    int updateByPrimaryKey(PandianEntity record);

    List<PandianEntity> datalistPage(Page page);

    void updatePandian(String kuwei, Map<String, Integer> map);
}