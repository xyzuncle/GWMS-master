package com.huanqiuyuncang.test.demo;


import com.huanqiuyuncang.dao.checktable.CheckTableDAO;
import com.huanqiuyuncang.dao.customer.GongYingShangDAO;
import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.order.OrderProductDAO;
import com.huanqiuyuncang.dao.order.OrdernumDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.saomiao.ShangPinSaomiaoDAO;
import com.huanqiuyuncang.dao.system.AreasDAO;
import com.huanqiuyuncang.dao.system.CitiesDAO;
import com.huanqiuyuncang.dao.system.ProvincesDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.entity.demo.TestUser;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.order.OrdernumEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.system.CitiesEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.demo.TestUserManager;
import com.huanqiuyuncang.service.system.checktable.impl.CheckTableService;
import com.huanqiuyuncang.service.wms.order.OrderProductInterface;
import com.huanqiuyuncang.util.PageData;
import org.apache.james.mime4j.field.datetime.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;
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

    @Autowired
    private CangKuDAO cangKuDAO;

    @Autowired
    private ShangPinSaomiaoDAO shangPinSaomiaoDAO;

    @Autowired
    private ProductWarehouseDAO productWarehouseDAO;

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private CitiesDAO citiesDAO;

    @Autowired
    private ProvincesDAO provincesDAO;
    @Autowired
    private AreasDAO areasDAO;

    @Autowired
    private OrdernumDAO ordernumDAO;
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

    @Test
    public void testcangku(){
        CangKuEntity cangKuEntity = cangKuDAO.selectByCangKu("aaa");
        System.out.println("    *************************************** bbb    "+(cangKuEntity == null));
    }

    @Test
    public void testsum(){
        Integer aaa = shangPinSaomiaoDAO.selectSaomiaoSumByShangpin("aaa");
        System.out.println("    *************************************** ccc    "+aaa);
    }
    @Test
    public void testMap(){
        String[] huohaoarr = {"1","1","1","2","3","4","5","6"};
        String[] dingdanhaoarr = {"1","1","1","2","3","4","5","6"};
        makeShangpinShuliang(huohaoarr, dingdanhaoarr);
    }


    private void makeShangpinShuliang(String[] huohaoarr, String[] dingdanhaoarr) {

        Map<String,String> huohaomap = new HashMap<>();
        List<String> dingdanhaoList = new ArrayList<>();
        List<String> huohaoList = new ArrayList<>();
        List<String> shuliangList = new ArrayList<>();
        for (int i = 0 ;i <huohaoarr.length ;i++){
            String shuliang = huohaomap.get(huohaoarr[i]);
            String dingdanhao =  dingdanhaoarr[i];
            if(shuliang == null){
                huohaomap.put(huohaoarr[i],"1");
            }else{
                String s = huohaomap.get(huohaoarr[i]);
                int sum = Integer.parseInt(s);
                huohaomap.put(huohaoarr[i],Integer.toString(sum+1));
            }
            if(!dingdanhaoList.contains(dingdanhao)){
                dingdanhaoList.add(dingdanhao);
            }
        }
        for(String key : huohaomap.keySet()){
            huohaoList.add(key);
            shuliangList.add(huohaomap.get(key));
        }

        System.out.println("***************     huohaoList" + Arrays.asList(huohaoList.toArray(new String[huohaoList.size()])));
        System.out.println("***************     shuliangList" + Arrays.asList(shuliangList.toArray(new String[shuliangList.size()])));
        System.out.println("***************     dingdanhaoList" + Arrays.asList(dingdanhaoList.toArray(new String[dingdanhaoList.size()])));
    }



    @Test
    public void testINQuery(){

       Page page = new Page();
        PageData pd = new PageData();
     /*    List<CangKuEntity> aaa = new ArrayList<>();
        CangKuEntity cangKuEntity = new CangKuEntity();
        cangKuEntity.setId("aaa");
        cangKuEntity.setCangkubianhao("bbb");
        aaa.add(cangKuEntity);
        List<ProductWarehouseEntity> list1 = productWarehouseDAO.aaa(aaa);
        System.out.println("***************  aaa   ***************     " + list1.size());

        pd.put("bbb",aaa);
        List<ProductWarehouseEntity> list2 = productWarehouseDAO.bbb(pd);
        System.out.println("***************  bbb   ***************     " + list2.size());

        pd.put("ccc",aaa);
        page.setPd(pd);
        List<ProductWarehouseEntity> list3 = productWarehouseDAO.datalistPage(page);
        System.out.println("***************  ccc   ***************     " + list3.size());*/
        //pd.put("shangpintiaoma","aaa");
       // pd.put("cangkuCodes",aaa);aa
/*        List<String> dd = new ArrayList<>();
        dd.add("ddddd");
        pd.put("cangku",dd);
        page.setPd(pd);
        List<ProductWarehouseEntity> list4 = productWarehouseDAO.datalistPage(page);
        System.out.println("***************  dddd   ***************     " + list4.size());*/
        pd.put("ddd","aaa,bbb,ccc,ddd");
        page.setPd(pd);
        List<ProductWarehouseEntity> list4 = productWarehouseDAO.datalistPage(page);
        System.out.println("***************  dddd   ***************     " + list4.size());
    }



    @Test
    public void testStatistics(){
        /*PageData pd = orderProductDAO.selectStatisticsByOrderNum("Oultst17062902516");
        BigDecimal sumprice = (BigDecimal)pd.get("sumprice");
        Double sumcount = (Double)pd.get("sumcount");

        System.out.println("***************  testStatistics   ***************     " + pd.get("sumprice") + "===="+sumcount);
    */}
    @Test
    public void testCity(){
    /*    CitiesEntity citiesEntity = new CitiesEntity();
        citiesEntity.setCity("测试");
        citiesEntity.setCityid("999999");
        citiesEntity.setProvinceid("999999");
        citiesDAO.insert(citiesEntity);
        System.out.println("***************  testCity   ***************     " + citiesEntity.getId());
   */

        Page page = new Page();
        PageData pd = new PageData();
        page.setPd(pd);
        List<CitiesEntity> citiesEntities = citiesDAO.datalistPage(page);
        System.out.println("***************  testCity   ***************     " + citiesEntities.size());
    }

    @Test
    public void testOrderNum(){
        List<OrdernumEntity> aaa = ordernumDAO.selectByPartentId("aaa");
        System.out.println("***************  testOrderNum   ***************     " + aaa.size());
    }
    @Test
    public void testDate(){
        String startDate = " 00:00:00";
        String endDate = " 23:59:59";
    }

}
