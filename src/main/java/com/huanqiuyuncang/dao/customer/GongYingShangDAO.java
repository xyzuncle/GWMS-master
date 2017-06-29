package com.huanqiuyuncang.dao.customer;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GongYingShangDAO {
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

    GongYingShangEntity selectGongyingshangByCode(String code);

    GongYingShangEntity selectGongyingshangByName(String name);

    GongYingShangEntity selectByCodeAndLoginName(@Param("gongyingshangbianhao") String gongyingshangbianhao, @Param("loginname")String loginname);
}