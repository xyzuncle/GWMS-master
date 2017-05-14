package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.warehouse.PackageWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.service.wms.warehouse.PackageWarehouseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/13.
 */
@Service
public class PackageWarehouseService implements PackageWarehouseInterface {
    @Autowired
    private PackageWarehouseDAO packageWarehouseDAO;
    @Override
    public int deleteByPrimaryKey(String packagewarehouseid) {
        return packageWarehouseDAO.deleteByPrimaryKey(packagewarehouseid);
    }

    @Override
    public int insert(PackageWarehouseEntity record) {
        return packageWarehouseDAO.insert(record);
    }

    @Override
    public int insertSelective(PackageWarehouseEntity record) {
        return packageWarehouseDAO.insertSelective(record);
    }

    @Override
    public PackageWarehouseEntity selectByPrimaryKey(String packagewarehouseid) {
        return packageWarehouseDAO.selectByPrimaryKey(packagewarehouseid);
    }

    @Override
    public int updateByPrimaryKeySelective(PackageWarehouseEntity record) {
        return packageWarehouseDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PackageWarehouseEntity record) {
        return packageWarehouseDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<PackageWarehouseEntity> datalistPage(Page page) {
        return packageWarehouseDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for(int i = 0;i<arrayDATA_ids.length;i++){
            packageWarehouseDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }

    @Override
    public PackageWarehouseEntity selectByRuKuBaoGuo(RuKuBaoGuoEntity ruKuBaoGuoEntity) {
        return packageWarehouseDAO.selectByRuKuBaoGuo(ruKuBaoGuoEntity);
    }

}
