package com.huanqiuyuncang.dao.demo;

import com.huanqiuyuncang.entity.demo.TestUser;

import java.util.HashMap;
import java.util.List;

public interface TestUserDao {

    int deleteByPrimaryKey(String id);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(String id);

    List<TestUser> selectByObj(TestUser testUser);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);

    List<TestUser> selectByMap(HashMap<String, String> userMap);
}