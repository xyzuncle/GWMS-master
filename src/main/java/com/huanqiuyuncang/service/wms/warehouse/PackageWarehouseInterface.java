package com.huanqiuyuncang.service.wms.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;

import java.util.List;

public interface PackageWarehouseInterface {
    int deleteByPrimaryKey(String packagewarehouseid);

    int insert(PackageWarehouseEntity record);

    int insertSelective(PackageWarehouseEntity record);

    PackageWarehouseEntity selectByPrimaryKey(String packagewarehouseid);

    int updateByPrimaryKeySelective(PackageWarehouseEntity record);

    int updateByPrimaryKey(PackageWarehouseEntity record);

    List<PackageWarehouseEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);

    PackageWarehouseEntity selectByRuKuBaoGuo(RuKuBaoGuoEntity ruKuBaoGuoEntity);
}