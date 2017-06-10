package com.huanqiuyuncang.service.pdconversion.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.pdconversion.ProductConversionDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.service.pdconversion.ProductConversionInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/4/9.
 */
@Service
public class ProductConversionService implements ProductConversionInterface {

    @Autowired
    private ProductConversionDAO productConversionDAO;
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public int deleteByPrimaryKey(String productconversionid) {
        return productConversionDAO.deleteByPrimaryKey(productconversionid);
    }

    @Override
    public int insert(ProductConversionEntity record) {
        return productConversionDAO.insert(record);
    }

    @Override
    public int insertSelective(ProductConversionEntity record) {
        return productConversionDAO.insertSelective(record);
    }

    @Override
    public ProductConversionEntity selectByPrimaryKey(String productconversionid) {
        return productConversionDAO.selectByPrimaryKey(productconversionid);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductConversionEntity record) {
        return productConversionDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProductConversionEntity record) {
        return productConversionDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ProductConversionEntity> datalistPage(Page page) {
        return productConversionDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (int i = 0;i<arrayDATA_ids.length;i++){
            productConversionDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }

    @Override
    public ProductConversionEntity selectByOuterPdNum(String outerproductnum) {
        return productConversionDAO.selectByOuterPdNum(outerproductnum);
    }

    @Override
    public String saveFromExcel(List<PageData> listPd) {
        StringBuffer resturt = new StringBuffer("");
        if(listPd != null && listPd.size()>0){
            for(int i = 0 ;i<listPd.size();i++) {
                int step = i+1;
                PageData pageData = listPd.get(i);
                String waibuhuohao = pageData.getString("var0");
                String huohao1 = pageData.getString("var1");
                String shuliang1 = pageData.getString("var2");
                String jiagezhanbi1 = pageData.getString("var3");
                String huohao2 = pageData.getString("var4");
                String shuliang2 = pageData.getString("var5");
                String jiagezhanbi2 = pageData.getString("var6");
                String huohao3 = pageData.getString("var7");
                String shuliang3 = pageData.getString("var8");
                String jiagezhanbi3 = pageData.getString("var9");
                String huohao4 = pageData.getString("var10");
                String shuliang4 = pageData.getString("var11");
                String jiagezhanbi4 = pageData.getString("var12");
                String beizhu = pageData.getString("var13");
                String customercode = "";
                String userName = Jurisdiction.getUsername();
                Date date = new Date();
                List<CustomerEntity> customerEntities = customerDAO.selectByLoginName(userName);
                if(customerEntities != null && customerEntities.size()>0){
                    customercode = customerEntities.get(0).getCustomercode();
                }
                ProductConversionEntity pd = new ProductConversionEntity();
                pd.setCreatetime(date);
                pd.setCreateuser(userName);
                pd.setUpdateuser(userName);
                pd.setUpdatetime(date);
                pd.setProductconversionid(UuidUtil.get32UUID());
                pd.setOuterproductnum(waibuhuohao);
                pd.setCustomercode(customercode);
                pd.setProductnum1(huohao1);
                pd.setProductnum2(huohao2);
                pd.setProductnum3(huohao3);
                pd.setProductnum4(huohao4);
                pd.setProductcount1(shuliang1);
                pd.setProductcount2(shuliang2);
                pd.setProductcount3(shuliang3);
                pd.setProductcount4(shuliang4);
                pd.setPriceof1(jiagezhanbi1);
                pd.setPriceof2(jiagezhanbi2);
                pd.setPriceof3(jiagezhanbi3);
                pd.setPriceof4(jiagezhanbi4);
                pd.setRemark(beizhu);
                productConversionDAO.insert(pd);
            }

        }
        return resturt.toString();
    }

}
