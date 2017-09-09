package com.huanqiuyuncang.service.wms.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.SenderEntity;

import java.util.List;

public interface SenderInterface {
    int deleteByPrimaryKey(String id);

    int insert(SenderEntity record);

    int insertSelective(SenderEntity record);

    SenderEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SenderEntity record);

    int updateByPrimaryKey(SenderEntity record);

    List<SenderEntity> datalistPage(Page page);

    void deleteAll(String[] arrayDATA_ids);

    List<SenderEntity> selectByCustomercode(String customercode);
}