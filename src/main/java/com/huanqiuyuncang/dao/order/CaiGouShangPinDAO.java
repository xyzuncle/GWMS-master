package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.util.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaiGouShangPinDAO {
    int deleteByPrimaryKey(String id);

    int insert(CaiGouShangPinEntity record);

    int insertSelective(CaiGouShangPinEntity record);

    CaiGouShangPinEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CaiGouShangPinEntity record);

    int updateByPrimaryKey(CaiGouShangPinEntity record);

    List<CaiGouShangPinEntity> selectByCaiGouDingDanId(String caigoudingdanid);

    List<String> selectProductNumByDingDanId(String caigoudingdanid);

    String selectKuweiByBarcodeAndCangku(@Param("barCode") String barCode, @Param("cangkuid")String cangkuid);

    List<PageData> selectHistoryInfoByBarcode(String barCode);
}