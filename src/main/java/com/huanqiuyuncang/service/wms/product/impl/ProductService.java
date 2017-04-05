package com.huanqiuyuncang.service.wms.product.impl;

import com.huanqiuyuncang.dao.DaoSupport;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lzf on 2017/4/2.
 */
@Service("productService")
public class ProductService implements ProductInterface {

    @Resource(name = "daoSupport")
    private DaoSupport dao;
    @Autowired
    private ProductDAO productDAO;
    @Override
    public int deleteByPrimaryKey(PageData pageData) throws Exception{
        String id = (String) pageData.get("productId");
        return productDAO.deleteByPrimaryKey(String.valueOf(id));
    }

    @Override
    public int insert(PageData pageData) throws Exception {
        ProductEntity product = (ProductEntity) BeanMapUtil.mapToObject(pageData, ProductEntity.class);
        return productDAO.insert(product);
    }

    @Override
    public int insertSelective(PageData pageData) throws Exception{
        ProductEntity product = (ProductEntity) BeanMapUtil.mapToObject(pageData, ProductEntity.class);
        return productDAO.insertSelective(product);
    }

    @Override
    public ProductEntity selectByPrimaryKey(String id)throws Exception {
        return productDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PageData pageData)throws Exception {
        ProductEntity product = (ProductEntity) BeanMapUtil.mapToObject(pageData, ProductEntity.class);
        return productDAO.updateByPrimaryKeySelective(product);
    }

    @Override
    public int updateByPrimaryKey(ProductEntity record) throws Exception{
        return productDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ProductEntity> queryByPage(Page page) {
        return productDAO.queryByPage(page);
    }

    @Override
    public List<ProductEntity> queryAll(Page page) {
        return productDAO.queryAll(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        if(arrayDATA_ids != null && arrayDATA_ids.length>0){
            for (String id :arrayDATA_ids) {
                productDAO.deleteByPrimaryKey(id);
            }
        }
    }

    public List<PageData> list(Page page)throws Exception{
        return (List<PageData>)dao.findForList("com.huanqiuyuncang.dao.product.ProductDAO.datalistPage", page);
    }

    public List<PageData> datalistPage(Page page)throws Exception{
        return productDAO.datalistPage( page);
    }

    @Override
    public ProductEntity findProductByProductNum(String productnum) throws Exception {
        return productDAO.findProductByProductNum(productnum);
    }

    @Override
    public ProductEntity findProductByBarCode(String barCode) throws Exception {
        return productDAO.findProductByBarCode(barCode);
    }

}
