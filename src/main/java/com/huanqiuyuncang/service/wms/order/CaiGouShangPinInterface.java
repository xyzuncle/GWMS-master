package com.huanqiuyuncang.service.wms.order;

import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface CaiGouShangPinInterface {
    int deleteByPrimaryKey(String id);

    int insert(CaiGouShangPinEntity record);

    int insertSelective(CaiGouShangPinEntity record);

    CaiGouShangPinEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CaiGouShangPinEntity record);

    int updateByPrimaryKey(CaiGouShangPinEntity record);

    List<CaiGouShangPinEntity> selectByCaiGouDingDanId(String caigoudingdanid);

    String selectKuweiByBarcodeAndCangku(String barCode,String cangkuid);

    List<PageData> selectHistoryInfoByBarcode(String barCode);
}