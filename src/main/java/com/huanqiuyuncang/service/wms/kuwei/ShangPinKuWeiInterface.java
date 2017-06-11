package com.huanqiuyuncang.service.wms.kuwei;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;

import java.util.List;

public interface ShangPinKuWeiInterface {
    int deleteByPrimaryKey(String id);

    int insert(ShangPinKuWeiEntity record);

    int insertSelective(ShangPinKuWeiEntity record);

    ShangPinKuWeiEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShangPinKuWeiEntity record);

    int updateByPrimaryKey(ShangPinKuWeiEntity record);

    List<ShangPinKuWeiEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);
}