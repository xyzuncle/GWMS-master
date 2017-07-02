package com.huanqiuyuncang.service.wms.saomiao;

import com.huanqiuyuncang.entity.saomiao.ShangPinSaomiaoEntity;

/**
 * Created by lzf on 2017/7/2.
 */
public interface ShangPinSaomiaoInterface {

    int deleteByPrimaryKey(String id);

    int insert(ShangPinSaomiaoEntity record);

    int insertSelective(ShangPinSaomiaoEntity record);

    ShangPinSaomiaoEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShangPinSaomiaoEntity record);

    int updateByPrimaryKey(ShangPinSaomiaoEntity record);

    Integer selectSaomiaoSumByShangpin(String shangpinid);
}
