package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.warehouse.PackageWarehouseDAO;
import com.huanqiuyuncang.dao.warehouse.RuKuBaoGuoDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.PackageWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.service.wms.warehouse.RuKuBaoGuoInterface;
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
public class RuKuBaoGuoService implements RuKuBaoGuoInterface {
    @Autowired
    private RuKuBaoGuoDAO ruKuBaoGuoDAO;

    @Autowired
    private PackageWarehouseDAO packageWarehouseDAO;
    @Override
    public int deleteByPrimaryKey(String rukubaoguoid) {
        return ruKuBaoGuoDAO.deleteByPrimaryKey(rukubaoguoid);
    }

    @Override
    public int insert(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.insert(record);
    }

    @Override
    public int insertSelective(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.insertSelective(record);
    }

    @Override
    public RuKuBaoGuoEntity selectByPrimaryKey(String rukubaoguoid) {
        return ruKuBaoGuoDAO.selectByPrimaryKey(rukubaoguoid);
    }

    @Override
    public int updateByPrimaryKeySelective(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RuKuBaoGuoEntity record) {
        return ruKuBaoGuoDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<RuKuBaoGuoEntity> datalistPage(Page page) {
        return ruKuBaoGuoDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for(int i = 0;i<arrayDATA_ids.length;i++){
            ruKuBaoGuoDAO.deleteByPrimaryKey(arrayDATA_ids[i]);
        }
    }

    @Override
    public PageData saveruku(List<String> danhaoList) {
        for (String  danhao: danhaoList) {
            RuKuBaoGuoEntity ruKuBaoGuoEntity = ruKuBaoGuoDAO.selectByDanHao(danhao);
            if(ruKuBaoGuoEntity != null){
                String username = Jurisdiction.getUsername();
                Date date = new Date();
                ruKuBaoGuoEntity.setRukuzhuangtai("orderStatus_yidabao");
                String cangwei = ruKuBaoGuoEntity.getCangwei();
                String cangku = ruKuBaoGuoEntity.getCangku();
                PackageWarehouseEntity packageWarehouse = packageWarehouseDAO.selectByRuKuBaoGuo(ruKuBaoGuoEntity);
                if(packageWarehouse == null){
                    createPackageWarehouse(username, date,cangku,  cangwei);
                }else {
                    updatePackageWarehouse(packageWarehouse);
                }
                ruKuBaoGuoDAO.updateByPrimaryKeySelective(ruKuBaoGuoEntity);
            }
        }
        PageData pd = new PageData();
        pd.put("msg","success");
        pd.put("resturt","ok");
        return pd;
    }

    private void updatePackageWarehouse(PackageWarehouseEntity packageWarehouse) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        Integer sum = Integer.parseInt(packageWarehouse.getShuliang());
        sum = sum+1;
        packageWarehouse.setShuliang(Integer.toString(sum));
        packageWarehouse.setUpdatetime(date);
        packageWarehouse.setUpdateuser(username);
        packageWarehouseDAO.updateByPrimaryKeySelective(packageWarehouse);
    }

    private void createPackageWarehouse(String username, Date date, String cangku, String cangwei) {
        PackageWarehouseEntity packageW = new PackageWarehouseEntity();
        packageW.setPackagewarehouseid(UuidUtil.get32UUID());
        packageW.setCangku(cangku);
        packageW.setCangwei(cangwei);
        packageW.setUpdatetime(date);
        packageW.setUpdateuser(username);
        packageW.setCreatetime(date);
        packageW.setCreateuser(username);
        packageW.setShuliang("1");
        packageWarehouseDAO.insert(packageW);
    }
}
