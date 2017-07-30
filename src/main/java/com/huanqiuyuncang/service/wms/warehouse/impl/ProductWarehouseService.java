package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.PandianEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.warehouse.ProductWarehouseInterface;
import com.huanqiuyuncang.util.DateUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/5/13.
 */
@Service
public class ProductWarehouseService implements ProductWarehouseInterface {
    @Autowired
    private ProductWarehouseDAO productWarehouseDAO;
    @Override
    public int deleteByPrimaryKey(String productwarehouseid) {
        return productWarehouseDAO.deleteByPrimaryKey(productwarehouseid);
    }

    @Override
    public int insert(ProductWarehouseEntity record) {
        return productWarehouseDAO.insert(record);
    }

    @Override
    public int insertSelective(ProductWarehouseEntity record) {
        return productWarehouseDAO.insertSelective(record);
    }

    @Override
    public ProductWarehouseEntity selectByPrimaryKey(String productwarehouseid) {
        return productWarehouseDAO.selectByPrimaryKey(productwarehouseid);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductWarehouseEntity record) {
        return productWarehouseDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProductWarehouseEntity record) {
        return productWarehouseDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ProductWarehouseEntity> datalistPage(Page page) {
        return productWarehouseDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for(int i = 0;i<arrayDATA_ids.length;i++){
            productWarehouseDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }

    @Override
    public ProductWarehouseEntity selectByChuKuShangPin(ChuKuShangPinEntity chuKuShangPinEntity) {
        return productWarehouseDAO.selectByChuKuShangPin(chuKuShangPinEntity);
    }

    @Override
    public ProductWarehouseEntity selectByPd(PageData pd) {
        return productWarehouseDAO.selectByPd(pd);
    }

    @Override
    public void savePandian(String[] idsarr) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for(String productwarehouseid : idsarr ){
            ProductWarehouseEntity productWarehouse = productWarehouseDAO.selectByPrimaryKey(productwarehouseid);//根据ID读取
            PandianEntity pandian = new PandianEntity();
            pandian.setId(UuidUtil.get32UUID());
            pandian.setCangku(productWarehouse.getCangku());
            pandian.setProductwarehouseid(productwarehouseid);
            pandian.setKuwei(productWarehouse.getCangwei());
            pandian.setShangpinbianhao(productWarehouse.getNeibuhuohao());
            pandian.setCreateuser(username);
            pandian.setCreatetime(date);
            pandian.setUpdatetime(date);
            pandian.setUpdateuser(username);
            pandian.setStatus("pandian_daiqueren");
            productWarehouse.setSuokustatus("1");
            productWarehouseDAO.updateByPrimaryKeySelective(productWarehouse);
        }
    }


}
