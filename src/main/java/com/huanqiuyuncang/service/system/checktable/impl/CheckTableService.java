package com.huanqiuyuncang.service.system.checktable.impl;

import com.huanqiuyuncang.dao.checktable.CheckTableDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.checktable.CheckTableEntity;
import com.huanqiuyuncang.entity.system.Dictionaries;
import com.huanqiuyuncang.service.system.checktable.CheckTableInterface;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/6/5.
 */
@Service
public class CheckTableService implements CheckTableInterface {

    @Autowired
    private CheckTableDAO checkTableDAO;

    @Override
    public int deleteByPrimaryKey(String checktableid) {
        return checkTableDAO.deleteByPrimaryKey(checktableid);
    }

    @Override
    public int insert(CheckTableEntity record) {
        return checkTableDAO.insert(record);
    }

    @Override
    public int insertSelective(CheckTableEntity record) {
        return checkTableDAO.insertSelective(record);
    }

    @Override
    public CheckTableEntity selectByPrimaryKey(String checktableid) {
        return checkTableDAO.selectByPrimaryKey(checktableid);
    }

    @Override
    public int updateByPrimaryKeySelective(CheckTableEntity record) {
        return checkTableDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CheckTableEntity record) {
        return checkTableDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CheckTableEntity> list(Page page) {
        return checkTableDAO.datalistPage(page);
    }

    @Override
    public List<CheckTableEntity> listAllDict(String parentId) {
        List<CheckTableEntity> checkList = this.listSubDictByParentId(parentId);
        for(CheckTableEntity check : checkList){
            check.setTreeurl("checktable/list.do?checktableid="+check.getChecktableid());
            check.setSubDict(this.listAllDict(check.getChecktableid()));
            check.setTarget("treeFrame");
        }
        return checkList;
    }

    public List<CheckTableEntity> listSubDictByParentId(String parentId) {
        return checkTableDAO.listSubDictByParentId(parentId);
    }


    public Integer sumCheckTable(String tableName,PageData pd){
        List<CheckTableEntity> list =  checkTableDAO.selectByChcektableName(tableName);
        Integer sum = 0;
        if(list != null && list.size()>0){
            for (CheckTableEntity ckecktable:list) {
                pd.put("checkfield", ckecktable.getCheckfield());
                pd.put("chcektablename",tableName);
                String fieldvalue = checkTableDAO.selectCheckValueByPd(pd);
                List<CheckTableEntity> childrenList = checkTableDAO.listSubDictByParentId(ckecktable.getChecktableid());
                if(childrenList != null && childrenList.size()>0){
                    for (CheckTableEntity child : childrenList) {
                        String chcektablename = child.getChcektablename();
                        String checkfield = child.getCheckfield();
                        pd.put("chcektablename",chcektablename);
                        pd.put("checkfield",checkfield);
                        pd.put("fieldvalue",fieldvalue);
                        Integer count = checkTableDAO.selectByTableNameAndField(pd);
                        sum += count;
                    }
                    return sum;
                }else{
                    return sum;
                }
            }
        }
        return sum;
    }

}
