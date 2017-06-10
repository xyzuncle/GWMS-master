package com.huanqiuyuncang.dao.product;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface ProductDAO {
    int deleteByPrimaryKey(String productId);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    ProductEntity selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);

    List<ProductEntity> selectForExcel(PageData pd);

    List<ProductEntity>  datalistPage(Page page);

    ProductEntity findProductByProductNum(String productnum);

    ProductEntity findProductByBarCode(String barCode);

    String selectCountryNameByID(String producingArea);

    String selectCountryIDByName(String producingArea);

    ProductEntity findProductByBarCodeOrNum(String biaoshi);
}