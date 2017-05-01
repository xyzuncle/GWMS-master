package com.huanqiuyuncang.service.wms.packagetype.impl;

import com.huanqiuyuncang.dao.packagetype.PackageTypeDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.packagetype.PackageTypeEntity;
import com.huanqiuyuncang.service.wms.packagetype.PackageTypeInterface;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/4/3.
 */
@Service
public class PackageTypeService implements PackageTypeInterface {
    @Autowired
    private PackageTypeDAO packageTypeDAO;
    @Override
    public int deleteByPrimaryKey(String packageid)throws Exception {
        return packageTypeDAO.deleteByPrimaryKey(packageid);
    }

    @Override
    public int insert(PackageTypeEntity record)throws Exception {

        return packageTypeDAO.insert(record);
    }

    @Override
    public int insertSelective(PageData pageData)throws Exception {
        PackageTypeEntity packageTypeEntity = (PackageTypeEntity) BeanMapUtil.mapToObject(pageData, PackageTypeEntity.class);
        return packageTypeDAO.insertSelective(packageTypeEntity);
    }

    @Override
    public PackageTypeEntity selectByPrimaryKey(String packageid)throws Exception {

        return packageTypeDAO.selectByPrimaryKey(packageid);
    }

    @Override
    public int updateByPrimaryKeySelective(PageData pageData)throws Exception {
        PackageTypeEntity packageTypeEntity = (PackageTypeEntity) BeanMapUtil.mapToObject(pageData, PackageTypeEntity.class);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        packageTypeEntity.setUpdatetime(date);
        packageTypeEntity.setUpdateuser(username);
        return packageTypeDAO.updateByPrimaryKeySelective(packageTypeEntity);
    }

    @Override
    public int updateByPrimaryKey(PackageTypeEntity record)throws Exception {

        return packageTypeDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<PackageTypeEntity> datalistPage(Page page) throws Exception {
        return packageTypeDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) throws Exception {
        Arrays.asList(arrayDATA_ids).forEach(id->packageTypeDAO.deleteByPrimaryKey(id));
    }

    @Override
    public List<PackageTypeEntity> selectAll() throws Exception {
        return packageTypeDAO.selectAll();
    }

    @Override
    public PackageTypeEntity findPackageTypeByPackageType(String packagetype) throws Exception {
        return packageTypeDAO.findPackageTypeByPackageType( packagetype) ;
    }
}
