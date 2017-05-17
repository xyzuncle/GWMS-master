package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerPackageEntity;

import java.util.List;

public interface InnerPackageDAO {
    int deleteByPrimaryKey(String innerpackageid);

    int insert(InnerPackageEntity record);

    int insertSelective(InnerPackageEntity record);

    InnerPackageEntity selectByPrimaryKey(String innerpackageid);

    int updateByPrimaryKeySelective(InnerPackageEntity record);

    int updateByPrimaryKey(InnerPackageEntity record);

    List<InnerPackageEntity> datalistPage(Page page);


}