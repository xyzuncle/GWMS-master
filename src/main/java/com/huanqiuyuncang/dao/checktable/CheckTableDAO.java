package com.huanqiuyuncang.dao.checktable;


import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.checktable.CheckTableEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface CheckTableDAO {
    int deleteByPrimaryKey(String checktableid);

    int insert(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    int insertSelective(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    com.huanqiuyuncang.entity.checktable.CheckTableEntity selectByPrimaryKey(String checktableid);

    int updateByPrimaryKeySelective(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    int updateByPrimaryKey(com.huanqiuyuncang.entity.checktable.CheckTableEntity record);

    List<CheckTableEntity> datalistPage(Page page);

    List<CheckTableEntity> listSubDictByParentId(String parentId);


    List<CheckTableEntity> selectByChcektableName(String tableName);

    Integer selectByTableNameAndField(PageData pd);


    String selectCheckValueByPd(PageData pd);
}