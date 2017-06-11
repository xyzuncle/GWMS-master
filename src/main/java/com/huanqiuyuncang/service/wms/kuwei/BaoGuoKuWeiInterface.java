package com.huanqiuyuncang.service.wms.kuwei;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;

import java.util.List;

public interface BaoGuoKuWeiInterface {
    int deleteByPrimaryKey(String id);

    int insert(BaoGuoKuWeiEntity record);

    int insertSelective(BaoGuoKuWeiEntity record);

    BaoGuoKuWeiEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaoGuoKuWeiEntity record);

    int updateByPrimaryKey(BaoGuoKuWeiEntity record);

    List<BaoGuoKuWeiEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);
}