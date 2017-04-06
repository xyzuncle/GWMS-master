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

    int updateByPrimaryKeySelective(PageData pageData) throws Exception ;

    int updateByPrimaryKey(ProductEntity record) throws Exception ;


    List<ProductEntity> queryByPage(Page page);

    List<ProductEntity> queryAll(Page page);

    void deleteAll(String[] arrayDATA_ids);

    List<PageData> list(Page page)throws Exception;

    public List<PageData> datalistPage(Page page)throws Exception;

    ProductEntity findProductByProductNum(String productnum)throws Exception;

    ProductEntity findProductByBarCode(String barCode)throws Exception;

    List<PageData> selectForExcel(PageData pageData)throws Exception;
}
