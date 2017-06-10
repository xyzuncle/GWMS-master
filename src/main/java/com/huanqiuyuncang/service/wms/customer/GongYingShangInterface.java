package com.huanqiuyuncang.service.wms.customer;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;

import java.util.List;

public interface GongYingShangInterface {
    int deleteByPrimaryKey(String gongyingshangid);

    int insert(GongYingShangEntity record);

    int insertSelective(GongYingShangEntity record);

    GongYingShangEntity selectByPrimaryKey(String gongyingshangid);

    int updateByPrimaryKeySelective(GongYingShangEntity record);

    int updateByPrimaryKey(GongYingShangEntity record);

    List<GongYingShangEntity> datalistPage(Page page);

    List<GongYingShangEntity> selectAll();

    List<GongYingShangEntity> selectByLoginName(String loginname);

    String selectNameByCode(String code);

    String selectCodeByLoginName(String loginname);

    GongYingShangEntity selectgongyingshangByCode(String code);

    GongYingShangEntity selectgongyingshangByName(String name);

    void deleteAll(String[] arrayDATA_ids);

}