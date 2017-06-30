package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.warehouse.ChuKuShangPinDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.warehouse.ChuKuShangPinInterface;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzf on 2017/5/13.
 */
@Service
public class ChuKuShangPinService implements ChuKuShangPinInterface {

    @Autowired
    private ChuKuShangPinDAO chuKuShangPinDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private ProductWarehouseDAO productWarehouseDAO;

    @Override
    public int deleteByPrimaryKey(String chukushangpinid) {
        return chuKuShangPinDAO.deleteByPrimaryKey(chukushangpinid);
    }

    @Override
    public int insert(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.insert(record);
    }

    @Override
    public int insertSelective(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.insertSelective(record);
    }

    @Override
    public ChuKuShangPinEntity selectByPrimaryKey(String chukushangpinid) {
        return chuKuShangPinDAO.selectByPrimaryKey(chukushangpinid);
    }

    @Override
    public int updateByPrimaryKeySelective(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ChuKuShangPinEntity record) {
        return chuKuShangPinDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<ChuKuShangPinEntity> datalistPage(Page page) {
        return chuKuShangPinDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for(int i = 0 ; i<arrayDATA_ids.length ; i++){
            chuKuShangPinDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }

    @Override
    public PageData updateSaomiaoShangPin(String[] shuliangarr, String[] huohaoarr, String[] dingdanhaoarr) {
        PageData pd = new PageData();
        pd.put("msg","error");
        pd.put("resturt","没有找到该货号");
        for(int i = 0 ;i<huohaoarr.length;i++){
            String dingdanhao = dingdanhaoarr[i];
            String huohao = huohaoarr[i];
            String shuliang = shuliangarr[i];
            ChuKuShangPinEntity chuKuShangPinEntity = chuKuShangPinDAO.selectByDingDanHaoAndHuoHao(dingdanhao,huohao);
            if(chuKuShangPinEntity != null){
                CustomerEntity customerEntity = customerDAO.selectCustomerByCode(chuKuShangPinEntity.getKehubianhao());
                String customerstatus = customerEntity.getCustomerstatus();
                String[] statusArr = customerstatus.split("_");
                chuKuShangPinEntity.setChukuzhuangtai("yichuku");
                ProductWarehouseEntity productWarehouse = productWarehouseDAO.selectByChuKuShangPin(chuKuShangPinEntity);
                if("1".equals(productWarehouse.getSuokustatus())){
                    pd.put("msg","error");
                    pd.put("resturt","仓库处于盘点状态，不能进行进出库操作。");
                }else{
                    Integer sum = Integer.parseInt(productWarehouse.getShuliang());
                    int count = Integer.parseInt(shuliang);
                    if("0".equals(statusArr[7])){
                        if(sum < count){
                            pd.put("msg","error");
                            pd.put("resturt","库存不足！");
                            return pd;
                        }
                    }
                    sum = sum-count;
                    productWarehouse.setShuliang(Integer.toString(sum));
                    productWarehouseDAO.updateByPrimaryKeySelective(productWarehouse);
                    chuKuShangPinDAO.updateByPrimaryKey(chuKuShangPinEntity);
                    pd.put("msg","success");
                    pd.put("resturt","");
                }
            }
        }
        return pd;
    }
}
