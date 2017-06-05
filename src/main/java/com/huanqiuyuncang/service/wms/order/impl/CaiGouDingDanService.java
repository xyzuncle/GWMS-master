package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.order.CaiGouDingDanDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.order.CaiGouDingDanInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/5/30.
 */
@Service
public class CaiGouDingDanService implements CaiGouDingDanInterface {
    @Autowired
    private CaiGouDingDanDAO caiGouDingDanDAO;
    @Autowired
    private ProductWarehouseDAO productWarehouseDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public int deleteByPrimaryKey(String caigoudingdanid) {
        return caiGouDingDanDAO.deleteByPrimaryKey(caigoudingdanid);
    }

    @Override
    public int insert(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.insert(record);
    }

    @Override
    public int insertSelective(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.insertSelective(record);
    }

    @Override
    public CaiGouDingDanEntity selectByPrimaryKey(String caigoudingdanid) {
        return caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
    }

    @Override
    public int updateByPrimaryKeySelective(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CaiGouDingDanEntity> datalistPage(Page page) {
        return caiGouDingDanDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            caiGouDingDanDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void shenheAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yiqueren");
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }

    @Override
    public void zuofeiAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yizuofei");
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }

    @Override
    public PageData ruku(String caigoudingdanid) {
        PageData pd = new PageData();
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        caiGouDingDan.setUpdatetime(date);
        caiGouDingDan.setUpdateuser(username);
        caiGouDingDan.setCaigoudingdanstatus("caigouStatus_yiruku");
        caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDan);
        PageData args = new PageData();
        args.put("neibuhuohao",caiGouDingDan.getShangpinhuohao());
        args.put("kehubianhao",caiGouDingDan.getKehubianhao());
        ProductWarehouseEntity productWarehouse = productWarehouseDAO.selectByPd(args);
        if(productWarehouse == null){
            productWarehouse = new ProductWarehouseEntity();
            productWarehouse.setProductwarehouseid(UuidUtil.get32UUID());
            productWarehouse.setShuliang(caiGouDingDan.getShuliang());
            ProductEntity productByProductNum = productDAO.findProductByProductNum(caiGouDingDan.getShangpinhuohao());
            CustomerEntity customerEntity = customerDAO.selectCustomerByCode(caiGouDingDan.getKehubianhao());
            productWarehouse.setCangwei(customerEntity.getDefaultwarehouse());
            productWarehouse.setShangpintiaoma(productByProductNum.getBarcodeMain());
            productWarehouse.setCreatetime(date);
            productWarehouse.setCreateuser(username);
            productWarehouse.setUpdatetime(date);
            productWarehouse.setUpdateuser(username);
            productWarehouse.setKehubianhao(caiGouDingDan.getKehubianhao());
            productWarehouse.setNeibuhuohao(caiGouDingDan.getShangpinhuohao());
            productWarehouseDAO.insertSelective(productWarehouse);
            pd.put("msg","success");
            pd.put("resturt","OK");
        }else{
            if("1".equals(productWarehouse.getSuokustatus())){
                pd.put("msg","error");
                pd.put("resturt","仓库处于盘点状态，不能进行进出库操作。");
            }else{
                String shuliang = productWarehouse.getShuliang();
                Integer sum =Integer.parseInt(shuliang);
                sum = sum + Integer.parseInt(caiGouDingDan.getShuliang());
                productWarehouse.setShuliang(Integer.toString(sum));
                productWarehouseDAO.updateByPrimaryKeySelective(productWarehouse);
                pd.put("msg","success");
                pd.put("resturt","OK");
            }

        }


        return pd;
    }
}
