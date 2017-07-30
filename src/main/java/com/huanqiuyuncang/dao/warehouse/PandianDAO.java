package com.huanqiuyuncang.dao.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.PandianEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface PandianDAO {
    int deleteByPrimaryKey(String id);

    int insert(PandianEntity record);

    int insertSelective(PandianEntity record);

    PandianEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PandianEntity record);

    int updateByPrimaryKey(PandianEntity record);

    List<PandianEntity> datalistPage(Page page);

    PandianEntity selectByPageData(PageData pd);
}