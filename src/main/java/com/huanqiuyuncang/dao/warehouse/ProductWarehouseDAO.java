package com.huanqiuyuncang.dao.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface ProductWarehouseDAO {
    int deleteByPrimaryKey(String productwarehouseid);

    int insert(ProductWarehouseEntity record);

    int insertSelective(ProductWarehouseEntity record);

    ProductWarehouseEntity selectByPrimaryKey(String productwarehouseid);

    int updateByPrimaryKeySelective(ProductWarehouseEntity record);

    int updateByPrimaryKey(ProductWarehouseEntity record);

    List<ProductWarehouseEntity> datalistPage(Page page);

    ProductWarehouseEntity selectByChuKuShangPin(ChuKuShangPinEntity chuKuShangPinEntity);

    ProductWarehouseEntity selectByPd(PageData pd);

    List<ProductWarehouseEntity> aaa(List<CangKuEntity> aaa);
    List<ProductWarehouseEntity> bbb(PageData pd);
    List<ProductWarehouseEntity> ccc(Page page);
}