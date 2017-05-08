package com.huanqiuyuncang.service.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.InnerPackageEntity;

import java.util.List;

public interface InnerPackageInterface {
    int deleteByPrimaryKey(String innerpackageid);

    int insert(InnerPackageEntity record);

    int insertSelective(InnerPackageEntity record);

    InnerPackageEntity selectByPrimaryKey(String innerpackageid);

    int updateByPrimaryKeySelective(InnerPackageEntity record);

    int updateByPrimaryKey(InnerPackageEntity record);

    List<InnerPackageEntity> datalistPage(Page page);

    void insertPackageInfo(InnerPackageEntity innerPackageEntity);
}