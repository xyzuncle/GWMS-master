package com.huanqiuyuncang.dao.packagetype;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;

import java.util.List;

public interface PackageTypeDAO {
    int deleteByPrimaryKey(String packageid);

    int insert(PackageTypeEntity record);

    int insertSelective(PackageTypeEntity record);

    PackageTypeEntity selectByPrimaryKey(String packageid);

    int updateByPrimaryKeySelective(PackageTypeEntity record);

    int updateByPrimaryKey(PackageTypeEntity record);

    List<PackageTypeEntity> datalistPage(Page page);
}