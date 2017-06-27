package com.huanqiuyuncang.dao.kuwei;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;

import java.util.List;

public interface ShangPinKuWeiDAO {
    int deleteByPrimaryKey(String id);

    int insert(ShangPinKuWeiEntity record);

    int insertSelective(ShangPinKuWeiEntity record);

    ShangPinKuWeiEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShangPinKuWeiEntity record);

    int updateByPrimaryKey(ShangPinKuWeiEntity record);

    List<ShangPinKuWeiEntity> datalistPage(Page page);

    List<ShangPinKuWeiEntity> selectByKuWei(String cangkuid);

    List<ShangPinKuWeiEntity> selectByKuweiAndProductnum(String cangku, String productnum);
}