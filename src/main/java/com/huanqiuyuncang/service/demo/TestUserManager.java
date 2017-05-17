package com.huanqiuyuncang.service.demo;

import com.huanqiuyuncang.entity.demo.TestUser;

import java.util.HashMap;
import java.util.List;


/** 用户接口类
 * @author
 * 修改时间：2015.11.2
 */
public interface TestUserManager {
    /**新增
     */
    public int save(TestUser testUser)throws Exception;

    /**删除
     * @param pd
     * @throws Exception
     */
    public void delete(TestUser testUser)throws Exception;

    public void deleteByID(String ID)throws Exception;


    public TestUser queryByID(String id)throws Exception;

    void update(TestUser testUser)throws Exception;

    List<TestUser> queryByTestUser(TestUser testUser)throws Exception ;

    List<TestUser> queryByMap(HashMap<String, String> userMap)throws Exception;

    int saveTestTransaction(TestUser testUser)throws Exception;
}
