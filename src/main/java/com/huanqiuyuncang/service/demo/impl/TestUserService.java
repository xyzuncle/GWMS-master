package com.huanqiuyuncang.service.demo.impl;


import com.huanqiuyuncang.dao.demo.TestUserDao;
import com.huanqiuyuncang.entity.Page;

import com.huanqiuyuncang.entity.demo.TestUser;
import com.huanqiuyuncang.service.demo.TestUserManager;
import com.huanqiuyuncang.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lzf on 2017/3/31.
 */
@Service("testUserService")
@Transactional
public class TestUserService implements TestUserManager {
   @Autowired
   private TestUserDao testUserDao;

    public int  save(TestUser testUser) throws Exception {
         return testUserDao.insert(testUser);
    }

    public void deleteByID(String id) throws Exception {
        testUserDao.deleteByPrimaryKey(id);
    }

    public TestUser queryByID(String id)throws Exception {
        TestUser testUser = testUserDao.selectByPrimaryKey(id);
        return testUser;
    }

    public void update(TestUser testUser) throws Exception {
        testUserDao.updateByPrimaryKeySelective(testUser);
    }

    public List<TestUser> queryByTestUser(TestUser testUser)throws Exception  {
        List<TestUser> list =  testUserDao.selectByObj(testUser);
        //List<demo> list = (List<demo>) dao.findForList("TestUserDao.selectByObj", testUser);
        return list;
    }

    public List<TestUser> queryByMap(HashMap<String, String> userMap) throws Exception {
        List<TestUser> list =  testUserDao.selectByMap(userMap);
        //List<demo> list = (List<demo>) dao.findForList("TestUserDao.selectByMap", userMap);
        return list;
    }

    @Override
    public int saveTestTransaction(TestUser testUser) throws Exception {
         testUserDao.insert(testUser);
         throw new RuntimeException("测试testTransaction。。。。。");
        //return 0;
    }


    public void delete(TestUser testUser) throws Exception {

    }

    public List<PageData> list(Page page) throws Exception {
        return null;
    }

    public List<PageData> listAll(PageData pd) throws Exception {
        return null;
    }
}
