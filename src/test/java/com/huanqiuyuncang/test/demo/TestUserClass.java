package com.huanqiuyuncang.test.demo;


import com.huanqiuyuncang.dao.checktable.CheckTableDAO;
import com.huanqiuyuncang.dao.customer.GongYingShangDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.entity.demo.TestUser;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.demo.TestUserManager;
import com.huanqiuyuncang.service.system.checktable.impl.CheckTableService;
import com.huanqiuyuncang.util.PageData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by lzf on 2017/3/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "parent",
        locations = {"classpath:spring/ApplicationContext-main.xml",
                "classpath:spring/ApplicationContext-dataSource.xml",
                "classpath:spring/ApplicationContext-shiro.xml",
                "classpath:spring/ApplicationContext-redis.xml"})
public class TestUserClass {
    @Autowired
    private TestUserManager testUserService;

    @Autowired
    private CheckTableDAO checkTableDAO;

    @Autowired
    private GongYingShangDAO gongYingShangDAO;

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void testSave()throws Exception{
        TestUser testUser = new TestUser();
        testUser.setAddress("测试地址aaa");
        testUser.setAge(20);
        testUser.setId(UUID.randomUUID().toString());
        testUser.setPassword("123456");
        testUser.setTel(1234567);
        testUser.setUsername("admin9999");
        testUser.setSex("男");
        int save = testUserService.save(testUser);
        System.out.println("**************************"+save);
    }

    @Test
    public void testTransaction(){
        TestUser testUser = new TestUser();
        testUser.setAddress("测试Transaction");
        testUser.setAge(20);
        testUser.setId(UUID.randomUUID().toString());
        testUser.setPassword("123456");
        testUser.setTel(1234567);
        testUser.setUsername("测试Transaction");
        testUser.setSex("男");
        int save = 0;
        try {
            save = testUserService.saveTestTransaction(testUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("**************************"+save);
    }

    @Test
    public void testDeleteByID()throws Exception{
        String id = "1606e938-d15d-4984-b50e-07123e0b56a8";
        testUserService.deleteByID(id);
    }

    @Test
    public void testqueryByID()throws Exception{

        String id = "24827aa4-695f-4ef8-bd12-ded1569dce37";
        TestUser testUser = testUserService.queryByID(id);
        System.out.println(testUser.toString());
    }
    @Test
    public void testUpdate()throws Exception{
        String id = "24827aa4-695f-4ef8-bd12-ded1569dce37";
        TestUser testUser = testUserService.queryByID(id);
        testUser.setUsername("aaaaaaaaaa");
        testUserService.update(testUser);
        System.out.println(testUser.toString());
    }

    @Test
    public void testUpdateOther()throws Exception{
        TestUser testUser = new TestUser();
        String id = "24827aa4-695f-4ef8-bd12-ded1569dce37";
        testUser.setUsername("bbbbbbbbbbbb");
        testUser.setId(id);
        testUserService.update(testUser);
        System.out.println(testUser.toString());
    }

    @Test
    public void testSelectByObj()throws Exception{
        HashMap<String,String> userMap = new HashMap<String, String>();
        userMap.put("address","测试地址");
        List<TestUser> userList1 = testUserService.queryByMap(userMap);
        userList1.forEach(user -> {
            System.out.print(user.toString()+"********    ");
            boolean falg = user.getAddress().contains("测试地址");
            System.out.println(falg);
        });
        TestUser testUser = new TestUser();
        testUser.setAddress("测试地址");

        List<TestUser> userList = testUserService.queryByTestUser(testUser);
        List<TestUser> list = userList.stream()
                .filter(user -> user.getAddress().contains("9999"))
                .collect(Collectors.toList());

        list.forEach(user -> System.out.println(user.toString()+"================"));

    }


    @Test
    public void testCheckTable(){
        PageData pd = new PageData();
        pd.put("chcektablename","wms_product");
        pd.put("checkfield","productNum");
        pd.put("fieldvalue","11");
        Integer count = checkTableDAO.selectByTableNameAndField(pd);
        System.out.println("******************   "+count+"  ************************************");
    }
    @Test
    public void testgongyingshang(){
        ProductEntity productByBarCode = productDAO.findProductByBarCodeOrNum("1111");
        System.out.println("    ***************************************     "+(productByBarCode == null));
    }


}
