package com.huanqiuyuncang.service.wms.product;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

/**
 *  商品接口类
 * Created by lzf on 2017/4/2.
 */
public interface ProductInterface {

    int deleteByPrimaryKey(PageData pageData) throws Exception ;

    int insert(PageData pd) throws Exception ;

    int insertSelective(ProductEntity productEntity) throws Exception ;

    ProductEntity selectByPrimaryKey(String id) throws Exception ;

    int updateByPrimaryKeySelective(ProductEntity productEntity) throws Exception ;

    int updateByPrimaryKey(ProductEntity record) throws Exception ;


    void deleteAll(String[] arrayDATA_ids);

    List<PageData> list(Page page)throws Exception;

    public List<ProductEntity> datalistPage(Page page)throws Exception;

    ProductEntity findProductByProductNum(String productnum)throws Exception;

    ProductEntity findProductByBarCode(String barCode)throws Exception;

    List<ProductEntity> selectForExcel(PageData pageData)throws Exception;

    String saveProductFromExcel(List<PageData> listPd);

    String selectCountryNameByID(String countryId);
}
