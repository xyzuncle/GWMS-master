package com.huanqiuyuncang.dao.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.YiKuEntity;
import com.huanqiuyuncang.util.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YiKuDAO {
    int deleteByPrimaryKey(String id);

    int insert(YiKuEntity record);

    int insertSelective(YiKuEntity record);

    YiKuEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YiKuEntity record);

    int updateByPrimaryKey(YiKuEntity record);

    List<YiKuEntity> datalistPage(Page page);

    List<YiKuEntity> selectByPdNumAndTargetCangkuInfo(@Param("kuwei")String productnum, @Param("kuwei")String cangkubianhao,@Param("kuwei")String kuwei);

    List<YiKuEntity> selectByArgs(PageData pd);

}