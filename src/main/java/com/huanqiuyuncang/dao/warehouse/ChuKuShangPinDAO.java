package com.huanqiuyuncang.dao.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;

import java.util.List;

public interface ChuKuShangPinDAO {
    int deleteByPrimaryKey(String chukushangpinid);

    int insert(ChuKuShangPinEntity record);

    int insertSelective(ChuKuShangPinEntity record);

    ChuKuShangPinEntity selectByPrimaryKey(String chukushangpinid);

    int updateByPrimaryKeySelective(ChuKuShangPinEntity record);

    int updateByPrimaryKey(ChuKuShangPinEntity record);

    List<ChuKuShangPinEntity> datalistPage(Page page);

    ChuKuShangPinEntity selectByDingDanHaoAndHuoHao(String dingdanhao, String huohao);
}