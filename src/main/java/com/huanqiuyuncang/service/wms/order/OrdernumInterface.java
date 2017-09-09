package com.huanqiuyuncang.service.wms.order;

import com.huanqiuyuncang.entity.order.OrdernumEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface OrdernumInterface {
    int deleteByPrimaryKey(String id);

    int insert(OrdernumEntity record);

    int insertSelective(OrdernumEntity record);

    OrdernumEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrdernumEntity record);

    int updateByPrimaryKey(OrdernumEntity record);

    OrdernumEntity selectByDingdanhao(String dingdanhao);

    List<OrdernumEntity> selectByBaoguoDanhao(String baoguodanhao);

    List<OrdernumEntity> selectByPartentId(String id);

    List<String> selectOrdernumToexcel();

    List<PageData> selectOrder4YT();


    List<PageData> selectFenjianDanInfoById(String id);

    List<PageData> selectZongFenJianDanInfoBy(String data_ids);
}