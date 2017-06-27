package com.huanqiuyuncang.dao.checktable;


import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.checktable.CheckTableEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface CheckTableDAO {
    int deleteByPrimaryKey(String checktableid);

    int insert(CheckTableEntity record);

    int insertSelective(CheckTableEntity record);

    CheckTableEntity selectByPrimaryKey(String checktableid);

    int updateByPrimaryKeySelective(CheckTableEntity record);

    int updateByPrimaryKey(CheckTableEntity record);

    List<CheckTableEntity> datalistPage(Page page);

    List<CheckTableEntity> listSubDictByParentId(String parentId);


    List<CheckTableEntity> selectByChcektableName(String tableName);

    Integer selectByTableNameAndField(PageData pd);


    String selectCheckValueByPd(PageData pd);
}