package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.order.OrderProductDAO;
import com.huanqiuyuncang.dao.pdconversion.ProductConversionDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.wms.order.OrderProductInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/4/13.
 */
@Service
public class OrderProductService implements OrderProductInterface {

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ProductConversionDAO productConversionDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public int deleteByPrimaryKey(String orderproducrtid) {
        return orderProductDAO.deleteByPrimaryKey(orderproducrtid);
    }

    @Override
    public int insert(OrderProductEntity record) {
        return orderProductDAO.insert(record);
    }

    @Override
    public int insertSelective(OrderProductEntity record) {
        return orderProductDAO.insertSelective(record);
    }

    @Override
    public OrderProductEntity selectByPrimaryKey(String orderproducrtid) {
        return orderProductDAO.selectByPrimaryKey(orderproducrtid);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderProductEntity record) {
        return orderProductDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderProductEntity record) {

        return orderProductDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<OrderProductEntity> selectOrderProduct(String customerordernum) {
        return orderProductDAO.selectOrderProduct(customerordernum);
    }

    @Override
    public List<OrderProductEntity> selectOrderProductBypackagenum(String innerpackagenum) {
        return orderProductDAO.selectOrderProductBypackagenum(innerpackagenum);
    }

    @Override
    public Integer orderproductSum(String innerpackagenum) {
        return orderProductDAO.orderproductSum(innerpackagenum);
    }

    @Override
    public void insertOrderProduct(OrderProductEntity orderProductEntity) {
        String username = Jurisdiction.getUsername();
        List<CustomerEntity> customerEntities = customerDAO.selectByLoginName(orderProductEntity.getCreateuser());
        CustomerEntity customerEntity = customerEntities.get(0);
        String[] customerstatus = customerEntity.getCustomerstatus().split("_");
        if("1".equals(customerstatus[1])){
            ProductConversionEntity pdConversion = productConversionDAO.selectByOuterPdNum(orderProductEntity.getOuterproductnum(), username);
            if(pdConversion!=null){
                Map<String, String[]> pdNumMap = this.getPdNumMap(pdConversion);
                for(String pdNum : pdNumMap.keySet()){
                    ProductEntity productByBarCode = productDAO.findProductByProductNum(pdNum);
                    if(productByBarCode != null){
                        String[] arry = pdNumMap.get(pdNum);
                        BigDecimal lingshoujiaReal = new BigDecimal(orderProductEntity.getRetailprice()).multiply(new BigDecimal(arry[1]));
                        BigDecimal jiesuanjiaReal = new BigDecimal(orderProductEntity.getDeclareprice()).multiply(new BigDecimal(arry[1]));
                        OrderProductEntity pd = new OrderProductEntity();
                        pd.setCustomerordernum(orderProductEntity.getCustomerordernum());
                        pd.setOuterordernum(orderProductEntity.getOuterordernum());
                        pd.setCount(arry[0]);
                        pd.setRemark(orderProductEntity.getRemark());
                        pd.setDeclareprice(jiesuanjiaReal.toString());
                        pd.setRetailprice(lingshoujiaReal.toString());
                        pd.setOuterproductnum(productByBarCode.getProductnum());
                        pd.setorderproducrtid(UuidUtil.get32UUID());
                        pd.setCreateuser(orderProductEntity.getCreateuser());
                        pd.setCreatetime(orderProductEntity.getCreatetime());
                        pd.setUpdatetime(orderProductEntity.getUpdatetime());
                        pd.setUpdateuser(orderProductEntity.getUpdateuser());
                        orderProductDAO.insertSelective(pd);
                    }
                };
            }
        }else{
            ProductEntity productEntity = productDAO.findProductByProductNum(orderProductEntity.getOuterproductnum());
            if(productEntity != null){
                orderProductEntity.setorderproducrtid(UuidUtil.get32UUID());
                orderProductEntity.setOuterproductnum(productEntity.getProductnum());
                orderProductDAO.insertSelective(orderProductEntity);
            }
        }
    }

    @Override
    public String selectProductsumByOrderNum(String customerordernum) {
        return orderProductDAO.selectProductsumByOrderNum(customerordernum);
    }

    @Override
    public PageData selectStatisticsByOrderNum(String customerordernum) {
        return orderProductDAO.selectStatisticsByOrderNum(customerordernum);
    }

    private Map<String,String[]> getPdNumMap(ProductConversionEntity pdConversion ){
        Map<String,String[]> pdMap = new HashMap<>();
        String productnum1 = pdConversion.getProductnum1();
        if(StringUtils.isNotBlank(productnum1)){
            pdMap.put(productnum1,new String[]{pdConversion.getProductcount1(),pdConversion.getPriceof1()});
        }
        String productnum2 = pdConversion.getProductnum2();
        if(StringUtils.isNotBlank(productnum2)){
            pdMap.put(productnum2,new String[]{pdConversion.getProductcount2(),pdConversion.getPriceof2()});
        }
        String productnum3 = pdConversion.getProductnum3();
        if(StringUtils.isNotBlank(productnum1)){
            pdMap.put(productnum3,new String[]{pdConversion.getProductcount3(),pdConversion.getPriceof3()});
        }
        String productnum4 = pdConversion.getProductnum4();
        if(StringUtils.isNotBlank(productnum1)){
            pdMap.put(productnum4,new String[]{pdConversion.getProductcount4(),pdConversion.getPriceof4()});
        }
        return pdMap;
    }
}
