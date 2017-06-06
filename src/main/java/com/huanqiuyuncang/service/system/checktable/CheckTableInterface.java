package com.huanqiuyuncang.service.system.checktable;


import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.checktable.CheckTableEntity;
import com.huanqiuyuncang.entity.system.Dictionaries;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface CheckTableInterface {
    int deleteByPrimaryKey(String checktableid);

    int insert(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    int insertSelective(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    com.huanqiuyuncang.entity.checktable.CheckTableEntity selectByPrimaryKey(String checktableid);

    int updateByPrimaryKeySelective(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    int updateByPrimaryKey(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    List<CheckTableEntity> list(Page page);

    List<CheckTableEntity>  listAllDict(String parentId);

    List<CheckTableEntity> listSubDictByParentId(String parentId);

    Integer sumCheckTable(String tableName,PageData pd);
}