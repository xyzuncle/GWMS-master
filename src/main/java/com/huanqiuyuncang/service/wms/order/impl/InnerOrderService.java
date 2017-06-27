package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.kuwei.BaoGuoKuWeiDAO;
import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.kuwei.ShangPinKuWeiDAO;
import com.huanqiuyuncang.dao.order.InnerOrderDAO;
import com.huanqiuyuncang.dao.order.OrderProductDAO;
import com.huanqiuyuncang.dao.pdconversion.ProductConversionDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.warehouse.ChuKuShangPinDAO;
import com.huanqiuyuncang.dao.warehouse.RuKuBaoGuoDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.kuwei.BaoGuoKuWeiEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.service.wms.order.InnerOrderInterface;
import com.huanqiuyuncang.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lzf on 2017/4/11.
 */
@Service
public class InnerOrderService implements InnerOrderInterface {
    @Autowired
    private InnerOrderDAO innerOrderDAO;

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private ProductConversionDAO productConversionDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ChuKuShangPinDAO chuKuShangPinDAO;

    @Autowired
    private RuKuBaoGuoDAO ruKuBaoGuoDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private BaoGuoKuWeiDAO baoGuoKuWeiDAO;

    @Autowired
    private CangKuDAO cangKuDAO;

    @Autowired
    private ShangPinKuWeiDAO shangPinKuWeiDAO;

    @Override
    public int insert(InnerOrderEntity record) {
        return innerOrderDAO.insert(record);
    }

    @Override
    public int insertSelective(InnerOrderEntity record) {
        return innerOrderDAO.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(InnerOrderEntity record) {
        return innerOrderDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<InnerOrderEntity> datalistPage(Page page) {
        return innerOrderDAO.datalistPage(page);
    }

    @Override
    public int deleteByPrimaryKey(String innerorderid) {
        InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(innerorderid);
        orderProductDAO.deleteByCustomerordernum(innerOrderEntity.getCustomerordernum());
        return innerOrderDAO.deleteByPrimaryKey(innerorderid);
    }

    @Override
    public InnerOrderEntity selectByPrimaryKey(String innerorderid) {
        return innerOrderDAO.selectByPrimaryKey(innerorderid);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids){
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            orderProductDAO.deleteByCustomerordernum(innerOrderEntity.getCustomerordernum());
            innerOrderDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<PageData> selectDictionaries(String PARENT_ID) {

        return innerOrderDAO.selectDictionaries(PARENT_ID);
    }

    @Override
    public void insertOrderInfo(InnerOrderEntity innerOrder, String token) {
        try{
            String customernum = innerOrder.getCustomernum();
            innerOrder.setCustomernum(customernum);
            String customerordernum = OrderUtil.getOrderNum(customernum);
            innerOrder.setCustomerordernum(customerordernum);
            innerOrderDAO.insertSelective(innerOrder);
            List<OrderProductEntity> list = orderProductDAO.selectOrderProduct(token);
            list.forEach(orderProduct ->{
                orderProduct.setCustomerordernum(customerordernum);
                orderProductDAO.updateByPrimaryKeySelective(orderProduct);
            });
        }catch (Exception e){
            orderProductDAO.deleteByCustomerordernum(token);
        }
    }

    public void createpackage(String id){

        InnerOrderEntity innerOrder = innerOrderDAO.selectByPrimaryKey(id);
        String customernum = innerOrder.getCustomernum();
        String packagenum = OrderUtil.getPackageNum(customernum);
        setPackageInfo(innerOrder, packagenum);
        innerOrderDAO.updateByPrimaryKeySelective(innerOrder);
    }

    private void setPackageInfo(InnerOrderEntity innerOrder, String packagenum) {
        List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(innerOrder.getCustomerordernum());
        List<InnerOrderEntity> innerOrderList = innerOrderDAO.selectByCustomerInfo(innerOrder);
        if(innerOrderList != null && innerOrderList.size()>0) {
            packagenum = innerOrderList.get(0).getInnerpackagenum();
        }
        String defaultCarton = "";
        String defaultCartonprefix = "";
        String packageType = "";
        Integer sum = 0;
        String username = Jurisdiction.getUsername();
        for(int i = 0 ;i<orderProductEntities.size();i++){
            OrderProductEntity orderProductEntity = orderProductEntities.get(i);
            orderProductEntity.setinnerpackagenum(packagenum);
            orderProductDAO.updateByPrimaryKeySelective(orderProductEntity);
            String barcode = orderProductEntity.getOuterproductnum();
            ProductEntity product = productDAO.findProductByBarCodeOrNum(barcode, username);
            Integer count = Integer.parseInt(orderProductEntity.getCount());
            sum += count;
            String cartontypea = product.getCartontypea();
            String cartontypeb = product.getCartontypeb();
            Integer cartontypeanum = product.getCartontypeanum();
            Integer cartontypebnum = product.getCartontypebnum();
            String defaultpackage = product.getDefaultpackage();
            Integer pnum = StringUtil.getNum(defaultpackage);
            if(StringUtils.isNotBlank(cartontypea)&&StringUtils.isNotBlank(cartontypeb)){
                Integer cartonA = StringUtil.getNum(cartontypea);
                Integer cartonB = StringUtil.getNum(cartontypeb);
                if(i == 0){
                    defaultCartonprefix =cartontypea.substring(0,1);
                }
                if(!(cartontypea.startsWith(defaultCartonprefix)&&cartontypeb.startsWith(defaultCartonprefix))){
                    defaultCarton = "";
                }else if(sum < cartontypeanum){
                    defaultCarton =  StringUtils.isBlank(defaultCarton)?cartontypea:defaultCarton;
                    defaultCarton = StringUtil.getNum(defaultCarton)>cartonA?cartontypea:defaultCarton;
                }else if(sum < cartontypebnum){
                    defaultCarton =  StringUtils.isBlank(defaultCarton)?cartontypeb:defaultCarton;
                    defaultCarton = StringUtil.getNum(defaultCarton)>cartonB?cartontypeb:defaultCarton;
                }else{
                    defaultCarton = "";
                }
            }
            if(StringUtils.isBlank(packageType)){
                packageType = defaultpackage;
            }else{
                packageType = StringUtil.getNum(packageType)>pnum?defaultpackage:packageType;
            }
        }

        if(innerOrderList != null && innerOrderList.size()>0){
            for ( InnerOrderEntity innerOrderEntity : innerOrderList){
                String cartonid = innerOrderEntity.getCartonid();
                String packageid = innerOrderEntity.getPackageid();
                Integer cnum = StringUtil.getNum(cartonid);
                Integer pnum = StringUtil.getNum(packageid);
                packageType = StringUtil.getNum(packageType)>pnum?packageType:packageid;
                defaultCarton = StringUtil.getNum(defaultCarton)>cnum?cartonid:defaultCarton;
            }
        }
        innerOrder.setInnerpackagenum(packagenum);
        innerOrder.setOrderstatus("packageStatus_daishenhe");
        innerOrder.setCartonid(defaultCarton);
        innerOrder.setPackageid(packageType);
    }

    @Override
    public String savePackageFromExcel(List<PageData> orderListPd, List<PageData> listPd) {
        List<InnerOrderEntity> orderList = new ArrayList<>();
        List<OrderProductEntity> pdList = new ArrayList<>();
        Map<String,String> outerOrderNum = new HashMap<>();
        final StringBuffer orderStr = new StringBuffer("订单页");
        for(int i = 0 ;i<orderListPd.size();i++) {
            PageData pageData = orderListPd.get(i);
            String start = "第"+(i+1)+"行有错误";
            String orderListStr = makeOrderList(orderList,pageData,outerOrderNum);
            if(StringUtils.isNotBlank(orderListStr)){
                orderStr.append(start + orderListStr);
            }
        };
        final StringBuffer pdStr = new StringBuffer("订单商品页");
        for(int i = 0 ;i<listPd.size();i++) {
            PageData pageData = listPd.get(i);
            String start = "第"+(i+1)+"行有错误";
            String pdListStr = makeOrderPdList(pdList,pageData,outerOrderNum);
            if(StringUtils.isNotBlank(pdListStr)){
                pdStr.append(start + pdListStr);
            }
        };
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        if("订单页".equals(orderStr.toString())&&"订单商品页".equals(pdStr.toString())){
            pdList.forEach(pd->{
                pd.setCreatetime(date);
                pd.setCreateuser(username);
                pd.setUpdatetime(date);
                pd.setUpdateuser(username);
                pd.setorderproducrtid(UuidUtil.get32UUID());
                orderProductDAO.insert(pd);
            });
            orderList.forEach(pd->{
                pd.setCreatetime(date);
                pd.setCreateuser(username);
                pd.setUpdatetime(date);
                pd.setUpdateuser(username);
                pd.setInnerorderid(UuidUtil.get32UUID());
                String customernum = pd.getCustomernum();
                String packagenum = OrderUtil.getPackageNum(customernum);
                setPackageInfo(pd, packagenum);
                innerOrderDAO.insert(pd);
            });


            return "";
        }else{
            return orderStr.append(pdStr).toString();
        }

    }

    @Override
    public void zuofeiAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            innerOrderEntity.setOrderstatus("orderStatus_zuofei");
            innerOrderDAO.updateByPrimaryKeySelective(innerOrderEntity);
        }
    }
    @Override
    public void yichang(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            innerOrderEntity.setOrderstatus("orderStatus_yichangjian");
            innerOrderDAO.updateByPrimaryKeySelective(innerOrderEntity);
        }
    }

    @Override
    public void makequeren(String[] arrayDATA_ids) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :arrayDATA_ids){
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            innerOrderEntity.setOrderstatus("packageStatus_yishenhe");
            innerOrderEntity.setUpdateuser(username);
            innerOrderEntity.setUpdatetime(date);
            innerOrderDAO.updateByPrimaryKeySelective(innerOrderEntity);
        }
    }

    @Override
    public void makeshenhe(String[] arrayDATA_ids) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :arrayDATA_ids){
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            innerOrderEntity.setOrderstatus("orderStatus_daidabao");
            innerOrderEntity.setUpdateuser(username);
            innerOrderEntity.setUpdatetime(date);
            innerOrderDAO.updateByPrimaryKeySelective(innerOrderEntity);
        }
    }



    @Override
    public List<PageData> selectProvince() {
        return innerOrderDAO.selectProvince();
    }

    @Override
    public List<PageData> selectCity(String code) {
        return innerOrderDAO.selectCity(code);
    }

    @Override
    public List<PageData> selectArea(String code) {
        return innerOrderDAO.selectArea(code);
    }

    @Override
    public String selectProvinceNameByCode(String recipientprovince) {
        return innerOrderDAO.selectProvinceNameByCode(recipientprovince);
    }

    @Override
    public String selectCityNameByCode(String recipientcity) {
        return innerOrderDAO.selectCityNameByCode(recipientcity);
    }

    @Override
    public String selectAreaNameByCode(String areaCode) {
        return innerOrderDAO.selectAreaNameByCode(areaCode);
    }

    @Override
    public void shenheAll(String[] arrayDATA_ids) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :arrayDATA_ids){
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            if("orderStatus_daiqueren".equals(innerOrderEntity.getOrderstatus())){
                innerOrderEntity.setOrderstatus("orderStatus_yiqueren");
              /*  List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(innerOrderEntity.getCustomerordernum());
                orderProductEntities.forEach(orderProduct ->{
                    CustomerEntity customer = customerDAO.selectCustomerByCode(innerOrderEntity.getCustomernum());
                    ChuKuShangPinEntity chuKuShangPinEntity = new ChuKuShangPinEntity();
                    chuKuShangPinEntity.setChukushangpinid(UuidUtil.get32UUID());
                    chuKuShangPinEntity.setKehubianhao(innerOrderEntity.getCustomernum());
                    chuKuShangPinEntity.setKehudingdanhao(innerOrderEntity.getCustomerordernum());
                    chuKuShangPinEntity.setWaibudingdanhao(innerOrderEntity.getOuterordernum());
                    ProductEntity productEntity = productDAO.findProductByBarCodeOrNum(orderProduct.getOuterproductnum(), username);
                    chuKuShangPinEntity.setNeibuhuohao(productEntity.getProductnum());
                    chuKuShangPinEntity.setShangpintiaoma(productEntity.getBarcodeMain());
                    chuKuShangPinEntity.setShuliang(orderProduct.getCount());
                    chuKuShangPinEntity.setChukuzhuangtai("daichuku");
                    chuKuShangPinEntity.setCangwei(customer.getDefaultwarehouse());
                    chuKuShangPinEntity.setCreateuser(username);
                    chuKuShangPinEntity.setCreatetime(date);
                    chuKuShangPinEntity.setUpdatetime(date);
                    chuKuShangPinEntity.setUpdateuser(username);
                    chuKuShangPinDAO.insertSelective(chuKuShangPinEntity);
                });*/
            }else if("orderStatus_daidabao".equals(innerOrderEntity.getOrderstatus())){
                innerOrderEntity.setOrderstatus("orderStatus_yidabao");
            /*    RuKuBaoGuoEntity ruKuBaoGuoEntity = new RuKuBaoGuoEntity();
                ruKuBaoGuoEntity.setRukubaoguoid(UuidUtil.get32UUID());
                ruKuBaoGuoEntity.setRukuzhuangtai("orderStatus_daidabao");
                ruKuBaoGuoEntity.setKehubianhao(innerOrderEntity.getCustomernum());
                ruKuBaoGuoEntity.setBaoguodanhao(innerOrderEntity.getInnerpackagenum());
                List<BaoGuoKuWeiEntity> list = baoGuoKuWeiDAO.selectbyCustomerNum(innerOrderEntity.getCustomernum());
                if(list != null && list.size()>0){
                    ruKuBaoGuoEntity.setCangwei(list.get(0).getKuwei());
                }else{
                    ruKuBaoGuoEntity.setCangwei("P0000");
                }
                ruKuBaoGuoEntity.setCreateuser(username);
                ruKuBaoGuoEntity.setCreatetime(date);
                ruKuBaoGuoEntity.setUpdatetime(date);
                ruKuBaoGuoEntity.setUpdateuser(username);
                ruKuBaoGuoDAO.insertSelective(ruKuBaoGuoEntity);*/
            }
            innerOrderDAO.updateByPrimaryKeySelective(innerOrderEntity);
        }
    }

    @Override
    public String saveOrderFromExcel(List<PageData> orderListPd, List<PageData> listPd) {
        List<InnerOrderEntity> orderList = new ArrayList<>();
        List<OrderProductEntity> pdList = new ArrayList<>();
        Map<String,String> outerOrderNum = new HashMap<>();
        final StringBuffer orderStr = new StringBuffer("订单页");
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for(int i = 0 ;i<orderListPd.size();i++) {
            PageData pageData = orderListPd.get(i);
            String start = "第"+(i+1)+"行有错误";
            String orderListStr = makeOrderList(orderList,pageData,outerOrderNum);
            if(StringUtils.isNotBlank(orderListStr)){
                orderStr.append(start + orderListStr);
            }
        };
        final StringBuffer pdStr = new StringBuffer("订单商品页");
        for(int i = 0 ;i<listPd.size();i++) {
            PageData pageData = listPd.get(i);
            String start = "第"+(i+1)+"行有错误";
            String pdListStr = makeOrderPdList(pdList,pageData,outerOrderNum);
            if(StringUtils.isNotBlank(pdListStr)){
                pdStr.append(start + pdListStr);
            }
        };

        if("订单页".equals(orderStr.toString())&&"订单商品页".equals(pdStr.toString())){
            orderList.forEach(pd->{
                pd.setInnerorderid(UuidUtil.get32UUID());
                pd.setCreatetime(date);
                pd.setCreateuser(username);
                pd.setUpdatetime(date);
                pd.setUpdateuser(username);
                innerOrderDAO.insert(pd);
            });
            pdList.forEach(pd->{
                pd.setCreatetime(date);
                pd.setCreateuser(username);
                pd.setUpdatetime(date);
                pd.setUpdateuser(username);
                pd.setorderproducrtid(UuidUtil.get32UUID());
               orderProductDAO.insert(pd);
            });
            return "";
        }else{
            return orderStr.append(pdStr).toString();
        }


    }

    private String makeOrderPdList(List<OrderProductEntity> pdList, PageData pageData, Map<String, String> outerOrderNum) {
        String waibudingdanhao = pageData.getString("var0");
        String waibuhuohao = pageData.getString("var1");
        String count = pageData.getString("var2");
        String shenbaojia = pageData.getString("var3");
        String lingshoujia = pageData.getString("var4");
        String beizhu = pageData.getString("var5");
        String customerOrderNum = "";
        if(StringUtils.isBlank(waibudingdanhao)){
            return "外部订单号未填写；";
        }else{
            if(!outerOrderNum.keySet().contains(waibudingdanhao)){
                return "订单页没有该订单号；";
            }else{
                customerOrderNum = outerOrderNum.get(waibudingdanhao);
            }
        }
        if(StringUtils.isBlank(shenbaojia)){
            return "申报价未填写；";
        }
        if(StringUtils.isBlank(lingshoujia)){
            return "零售价未填写；";
        }
        String username = Jurisdiction.getUsername();
        List<CustomerEntity> customerEntities = customerDAO.selectByLoginName(username);
       if(customerEntities == null && customerEntities.size()>1){
           return "改登陆用户未找到客户编号或找到多个；";
       }else{
           if(StringUtils.isNotBlank(waibuhuohao)){
               // 0.计算跨境速递费	1.是否外部商品转换	2.发货仓库
               // 3.按商品内部货值计算申报货值	4.收款状态	5.计算预计纸箱和包装及费用	6.计算运费 7.负仓出库
               CustomerEntity customerEntity = customerEntities.get(0);
               String[] customerstatus = customerEntity.getCustomerstatus().split("_");
               if("1".equals(customerstatus[1])){
                   ProductConversionEntity pdConversion = productConversionDAO.selectByOuterPdNum(waibuhuohao,username);
                   if(pdConversion == null){
                       return "外部货号未找到；";
                   }else{
                       Map<String, String[]> pdNumMap = this.getPdNumMap(pdConversion);
                       for(String pdNum : pdNumMap.keySet()){
                           ProductEntity productByBarCode = productDAO.findProductByProductNum(pdNum);
                           if(productByBarCode != null){
                               String[] arry = pdNumMap.get(pdNum);
                               BigDecimal lingshoujiaReal = new BigDecimal(shenbaojia).multiply(new BigDecimal(arry[1]));
                               BigDecimal jiesuanjiaReal = new BigDecimal(lingshoujia).multiply(new BigDecimal(arry[1]));
                               OrderProductEntity pd = new OrderProductEntity();
                               pd.setCustomerordernum(customerOrderNum);
                               pd.setOuterordernum(waibudingdanhao);
                               pd.setCount(arry[0]);
                               pd.setRemark(beizhu);
                               pd.setDeclareprice(jiesuanjiaReal.toString());
                               pd.setRetailprice(lingshoujiaReal.toString());
                               pd.setOuterproductnum(productByBarCode.getProductnum());
                               pdList.add(pd);
                           }
                       };
                   }
               }else{
                   ProductEntity productByBarCode = productDAO.findProductByBarCodeOrNum(waibuhuohao,username);
                   if(productByBarCode == null){
                       return "商品未找到,请查看商品货号/编号；";
                   }else{
                       OrderProductEntity pd = new OrderProductEntity();
                       pd.setOuterordernum(waibudingdanhao);
                       pd.setCustomerordernum(customerOrderNum);
                       pd.setCount(count);
                       pd.setRemark(beizhu);
                       pd.setDeclareprice(shenbaojia);
                       pd.setRetailprice(lingshoujia);
                       pd.setOuterproductnum(productByBarCode.getProductnum());
                       pdList.add(pd);
                   }
               }
           }else{
               return "外部货号/商品货号/条码未填写；";
           }
       }
        return "";
    }

    private Map<String,String[]> getPdNumMap(ProductConversionEntity pdConversion ){
        Map<String,String[]> pdMap = new HashMap<>();
        String productnum1 = pdConversion.getProductnum1();
        if(StringUtils.isNotBlank(productnum1)){
            pdMap.put(productnum1,new String[]{pdConversion.getProductcount1(),pdConversion.getPriceof1()});
        }
        String productnum2 = pdConversion.getProductnum2();
        if(StringUtils.isNotBlank(productnum2)){
            pdMap.put(productnum2,new String[]{pdConversion.getProductcount2(),pdConversion.getPriceof2()});
        }
        String productnum3 = pdConversion.getProductnum3();
        if(StringUtils.isNotBlank(productnum1)){
            pdMap.put(productnum3,new String[]{pdConversion.getProductcount3(),pdConversion.getPriceof3()});
        }
        String productnum4 = pdConversion.getProductnum4();
        if(StringUtils.isNotBlank(productnum1)){
            pdMap.put(productnum4,new String[]{pdConversion.getProductcount4(),pdConversion.getPriceof4()});
        }
        return pdMap;
    }

    private String makeOrderList(List<InnerOrderEntity> orderList, PageData pageData, Map<String, String> outerOrderNum) {
        String waibudingdanhao = pageData.getString("var0");
        String xiadanshijian = pageData.getString("var1");
        String baoguanmoshi = pageData.getString("var23");
        String dingdanhuozhi = pageData.getString("var24");
        Date ordertime = null;
        if (StringUtils.isBlank(baoguanmoshi)) {
            return "报关模式未填写；";
        } else {
            baoguanmoshi = innerOrderDAO.selectDictBianMaByName(baoguanmoshi);
            if (StringUtils.isBlank(baoguanmoshi)) {
                return "报关模式填写错误未找到；";
            }
        }
        if (StringUtils.isBlank(dingdanhuozhi)) {
            return "订单货值未填写；";
        }
        if (StringUtils.isNotBlank(xiadanshijian)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                ordertime = format.parse(xiadanshijian);
            } catch (ParseException e) {
                return "下单时间格式不正确请按照（yyyy-MM-dd HH:mm:ss）格式填写；";
            }
        } else {
            return "下单时间未填写";
        }
        String username = Jurisdiction.getUsername();
        String customernum = "";
        try{
            customernum = customerDAO.selectCodeByLoginName(username);
        }catch (Exception e){
            return "该登陆用户的找到多个客户编号！";
        }

        if (StringUtils.isBlank(customernum)) {
            return "该登陆用户的客户编号未找到！";
        }
        String jijianren = pageData.getString("var2");
        String jijianrenTel = pageData.getString("var3");
        String jijianrenCountry = pageData.getString("var4");
        String jijianrensheng = pageData.getString("var5");
        String jijianrenshi = pageData.getString("var6");
        String jijianrenqu = pageData.getString("var7");
        String jijianrendizhi = pageData.getString("var8");
        String jijianrenyoubian = pageData.getString("var9");
        String shoujianren = pageData.getString("var10");
        String shoujianrenIdCard = pageData.getString("var11");
        String shoujianrenTel = pageData.getString("var12");
        String shoujianrenCountry = pageData.getString("var13");
        String shoujianrensheng = pageData.getString("var14");
        if(StringUtils.isNotBlank(shoujianrensheng)){
            shoujianrensheng = innerOrderDAO.selectProvinceCodeByName(shoujianrensheng);
        }
        String shoujianrenshi = pageData.getString("var15");
        if(StringUtils.isNotBlank(shoujianrenshi)){
            shoujianrenshi = innerOrderDAO.selectCityCodeByName(shoujianrenshi);
        }
        String shoujianrenqu = pageData.getString("var16");
        if(StringUtils.isNotBlank(shoujianrenqu)){
            String[] arr = innerOrderDAO.selectAreaCodeByName(shoujianrenqu);
            shoujianrenqu =  arr.length>1||arr.length ==0?"":arr[0];
        }
        String shoujianrendizhi = pageData.getString("var17");
        String shoujianrenyoubian = pageData.getString("var18");
        String zhifufangshi = pageData.getString("var19");
        String zhifujiaoyihao = pageData.getString("var20");
        String zhifushijian = pageData.getString("var21");
        String gukebeizhu = pageData.getString("var22");
        String beizhu = pageData.getString("var25");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYMMdd");
        String formatDate = format.format(date);


        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("orderserialnumber"));
        PropUtil.writeProperties("orderserialnumber",serialnumber+1+"");
        String serialnumberStr = String.format("%0" + 5 + "d", serialnumber);
        String customerordernum = "O"+customernum+formatDate+serialnumberStr;


        InnerOrderEntity order = new InnerOrderEntity();
        order.setCustomerordernum(customerordernum);
        order.setOuterordernum(waibudingdanhao);
        order.setOrdertime(ordertime);
        order.setSender(jijianren);
        order.setCustomernum(customernum);
        order.setSenderphone(jijianrenTel);
        order.setSendercountry(jijianrenCountry);
        order.setSenderprovince(jijianrensheng);
        order.setSendercity(jijianrenshi);
        order.setSenderarea(jijianrenqu);
        order.setSenderaddress(jijianrendizhi);
        order.setSenderpostcode(jijianrenyoubian);
        order.setRecipient(shoujianren);
        order.setRecipientidcard(shoujianrenIdCard);
        order.setRecipientphone(shoujianrenTel);
        order.setRecipientcountry(shoujianrenCountry);
        order.setRecipientprovince(shoujianrensheng);
        order.setRecipientcity(shoujianrenshi);
        order.setRecipientarea(shoujianrenqu);
        order.setRecipientaddress(shoujianrendizhi);
        order.setRecipientpostcode(shoujianrenyoubian);
        order.setPaymentmethod(zhifufangshi);
        order.setPaymentnum(zhifujiaoyihao);
        order.setPaymenttime(zhifushijian);
        order.setCustomerremarks(gukebeizhu);
        order.setCustomsmodel(baoguanmoshi);
        order.setOrdervalue(dingdanhuozhi);
        order.setRemark(beizhu);
        order.setOrderstatus("orderStatus_daiqueren");
        //order.setOrdermultistatus(CustomerController.CUSTOMERSTATUS);
        orderList.add(order);
        outerOrderNum.put(waibudingdanhao,customerordernum);
        return "";
    }

    @Override
    public List<CangKuEntity> getCangku(String cangkushuxing) {
        return cangKuDAO.getCangku(cangkushuxing);
    }

    @Override
    public void saveShangpinChuku(String[] ids, String cangkushuxing, String cangku) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :ids){
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            String customerName = innerOrderEntity.getCustomernum();
              List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(innerOrderEntity.getCustomerordernum());
                orderProductEntities.forEach(orderProduct ->{
                    CustomerEntity customer = customerDAO.selectCustomerByCode(innerOrderEntity.getCustomernum());
                    ChuKuShangPinEntity chuKuShangPinEntity = new ChuKuShangPinEntity();
                    chuKuShangPinEntity.setChukushangpinid(UuidUtil.get32UUID());
                    chuKuShangPinEntity.setKehubianhao(innerOrderEntity.getCustomernum());
                    chuKuShangPinEntity.setKehudingdanhao(innerOrderEntity.getCustomerordernum());
                    chuKuShangPinEntity.setWaibudingdanhao(innerOrderEntity.getOuterordernum());
                    ProductEntity productEntity = productDAO.findProductByProductNum(orderProduct.getOuterproductnum());
                    chuKuShangPinEntity.setNeibuhuohao(productEntity.getProductnum());
                    chuKuShangPinEntity.setShangpintiaoma(productEntity.getBarcodeMain());
                    chuKuShangPinEntity.setShuliang(orderProduct.getCount());
                    chuKuShangPinEntity.setChukuzhuangtai("daichuku");
                    chuKuShangPinEntity.setCangku(cangku);
                    CangKuEntity cangKuEntity = cangKuDAO.selectByPrimaryKey(cangku);
                    String createuser = cangKuEntity.getCreateuser();
                    List<ShangPinKuWeiEntity> list = shangPinKuWeiDAO.selectByKuweiAndProductnum(cangku,productEntity.getProductnum());
                    String kuwei = (list!=null && list.size()>0)?list.get(0).getKuwei():"";
                    chuKuShangPinEntity.setCangwei(kuwei);
                    chuKuShangPinEntity.setCreateuser(createuser);
                    chuKuShangPinEntity.setCreatetime(date);
                    chuKuShangPinEntity.setUpdatetime(date);
                    chuKuShangPinEntity.setUpdateuser(createuser);
                    chuKuShangPinDAO.insertSelective(chuKuShangPinEntity);
                });
        }
    }

    public void saveBaoguoRuku(String[] ids, String baoguokuwei) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :ids) {
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            RuKuBaoGuoEntity ruKuBaoGuoEntity = new RuKuBaoGuoEntity();
            ruKuBaoGuoEntity.setRukubaoguoid(UuidUtil.get32UUID());
            ruKuBaoGuoEntity.setRukuzhuangtai("orderStatus_daidabao");
            ruKuBaoGuoEntity.setKehubianhao(innerOrderEntity.getCustomernum());
            ruKuBaoGuoEntity.setBaoguodanhao(innerOrderEntity.getInnerpackagenum());
            BaoGuoKuWeiEntity baoGuoKuWeiEntity = baoGuoKuWeiDAO.selectByPrimaryKey(baoguokuwei);
            if(baoGuoKuWeiEntity != null){
                ruKuBaoGuoEntity.setCangku(baoGuoKuWeiEntity.getCangku());
                ruKuBaoGuoEntity.setCangwei(baoGuoKuWeiEntity.getKuwei());
            }else{
                ruKuBaoGuoEntity.setCangwei("P0000");
            }
            ruKuBaoGuoEntity.setCreateuser(username);
            ruKuBaoGuoEntity.setCreatetime(date);
            ruKuBaoGuoEntity.setUpdatetime(date);
            ruKuBaoGuoEntity.setUpdateuser(username);
            ruKuBaoGuoDAO.insertSelective(ruKuBaoGuoEntity);
        }

    }

}
