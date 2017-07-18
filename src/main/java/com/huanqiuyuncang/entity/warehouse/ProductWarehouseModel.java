package com.huanqiuyuncang.entity.warehouse;

import java.util.List;

/**
 * Created by lzf on 2017/7/18.
 */
public class ProductWarehouseModel {
    private List<ProductWarehouseEntity> list;

    public ProductWarehouseModel() {
    }

    public ProductWarehouseModel(List<ProductWarehouseEntity> list) {
        this.list = list;
    }

    public List<ProductWarehouseEntity> getList() {
        return list;
    }

    public void setList(List<ProductWarehouseEntity> list) {
        this.list = list;
    }
}
