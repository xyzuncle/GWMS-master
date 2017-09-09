package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.SenderEntity;

import java.util.List;

public interface SenderDAO {
    int deleteByPrimaryKey(String id);

    int insert(SenderEntity record);

    int insertSelective(SenderEntity record);

    SenderEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SenderEntity record);

    int updateByPrimaryKey(SenderEntity record);

    List<SenderEntity> datalistPage(Page page);

    List<SenderEntity> selectByCustomercode(String customercode);
}