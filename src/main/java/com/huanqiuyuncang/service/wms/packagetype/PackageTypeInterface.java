package com.huanqiuyuncang.service.wms.packagetype;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface PackageTypeInterface {
    int deleteByPrimaryKey(String packageid) throws Exception;

    int insert(PackageTypeEntity record)throws Exception;

    int insertSelective(PageData pageData)throws Exception;

    PackageTypeEntity selectByPrimaryKey(String packageid)throws Exception;

    int updateByPrimaryKeySelective(PageData pageData)throws Exception;

    int updateByPrimaryKey(PackageTypeEntity record)throws Exception;

    List<PackageTypeEntity> datalistPage(Page page) throws Exception;

    void deleteAll(String[] arrayDATA_ids)throws Exception;

    List<PackageTypeEntity> selectAll()throws Exception;

    PackageTypeEntity findPackageTypeByPackageType(String packagetype)throws Exception;
}