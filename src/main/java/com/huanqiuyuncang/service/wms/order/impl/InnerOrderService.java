package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.kuwei.BaoGuoKuWeiDAO;
import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.kuwei.ShangPinKuWeiDAO;
import com.huanqiuyuncang.dao.order.InnerOrderDAO;
import com.huanqiuyuncang.dao.order.OrderProductDAO;
import com.huanqiuyuncang.dao.order.OrdernumDAO;
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
import com.huanqiuyuncang.entity.order.OrderInfoDTO;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.order.OrdernumEntity;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.service.wms.order.InnerOrderInterface;
import com.huanqiuyuncang.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.print.attribute.standard.MediaSizeName.C;

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

    @Autowired
    private OrdernumDAO ordernumDAO;
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
    public List<OrderInfoDTO> datalistPage(Page page) {
        return innerOrderDAO.datalistPage(page);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
        ordernumDAO.deleteByPrimaryKey(id);
        orderProductDAO.deleteByCustomerordernum(ordernumEntity.getOrdernum());
        return innerOrderDAO.deleteByPrimaryKey(ordernumEntity.getOrderinfo());
    }

    @Override
    public InnerOrderEntity selectByPrimaryKey(String innerorderid) {
        return innerOrderDAO.selectByPrimaryKey(innerorderid);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids){
            deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<PageData> selectDictionaries(String PARENT_ID) {

        return innerOrderDAO.selectDictionaries(PARENT_ID);
    }

    @Override
    public void insertOrderInfo(InnerOrderEntity innerOrder, String token) {
        try{
            String username = Jurisdiction.getUsername();
            Date date = new Date();
            String innerorderid = makeInnerOrderInfo(innerOrder, username, date);
            String ordernum = makeOrderNumInfo(innerOrder, username, date, innerorderid,token);
            List<OrderProductEntity> list = orderProductDAO.selectOrderProduct(token);
            if(list!=null  && list.size()>0){
                list.forEach(orderProduct ->{
                    orderProduct.setCustomerordernum(ordernum);
                    orderProductDAO.updateByPrimaryKeySelective(orderProduct);
                });
            }

        }catch (Exception e){
                orderProductDAO.deleteByCustomerordernum(token);
        }
    }

    private String makeOrderNumInfo(InnerOrderEntity innerOrder, String username, Date date, String innerorderid,String token) {
        PageData respd = orderProductDAO.selectStatisticsByOrderNum(token);
        BigDecimal sumprice=null;
        Double sumcount=null;
        //空指针异常
        if(respd!=null){
            sumprice = (BigDecimal)respd.get("sumprice");
            sumcount = (Double)respd.get("sumcount");
        }



        String customernum = innerOrder.getCustomernum();
        String ordernum = OrderUtil.getOrderNum(customernum);
        OrdernumEntity ordernumEntity = new OrdernumEntity();
        ordernumEntity.setId(UuidUtil.get32UUID());
        ordernumEntity.setOrdernum(ordernum);
        ordernumEntity.setOrderinfo(innerorderid);
        ordernumEntity.setOrderstatus("orderStatus_daiqueren");
        ordernumEntity.setYujingstatus("yujing_zhengchang");
        ordernumEntity.setIspartent(1);
        ordernumEntity.setOrdervalue(sumprice==null?"":sumprice.toString());
        ordernumEntity.setOrderproductcount(sumcount==null?"":sumcount.toString());
        ordernumEntity.setParentid("");
        ordernumEntity.setRemark("");
        ordernumEntity.setCreateuser(username);
        ordernumEntity.setCreatetime(date);
        ordernumEntity.setUpdateuser(username);
        ordernumEntity.setUpdatetime(date);
        ordernumDAO.insertSelective(ordernumEntity);
        return ordernum;
    }

    private String makeInnerOrderInfo(InnerOrderEntity innerOrder, String username, Date date) {
        String innerorderid = UuidUtil.get32UUID();
        innerOrder.setInnerorderid(innerorderid);
        innerOrder.setCreateuser(username);
        innerOrder.setCreatetime(date);
        innerOrder.setUpdateuser(username);
        innerOrder.setUpdatetime(date);
        innerOrderDAO.insertSelective(innerOrder);
        return innerorderid;
    }

    public void createpackage(String[] ids){
        //一个订单一个包裹
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for(String id : ids){
            OrdernumEntity ordernumEntity = savePackageInfo(username, date, id);
            String packagenum = ordernumEntity.getPackagenum();
            String defaultCarton = "";
            String packageType = "";
            InnerOrderEntity innerorder = innerOrderDAO.selectByPrimaryKey(ordernumEntity.getOrderinfo());
            List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(ordernumEntity.getOrdernum());
            Integer sum = Integer.parseInt(orderProductDAO.selectProductsumByOrderNum(ordernumEntity.getOrdernum()));
            for (OrderProductEntity orderProductEntity : orderProductEntities) {
                savePackagenum(packagenum, orderProductEntity);
                String barcode = orderProductEntity.getOuterproductnum();
                ProductEntity product = productDAO.findProductByBarCodeOrNum(barcode);
                String defaultpackage = product.getDefaultpackage();
                Integer pnum = StringUtil.getNum(defaultpackage);
                defaultCarton = makeDefaultCarton(defaultCarton,sum,product);
                if(StringUtils.isEmpty(packageType)){
                    packageType = defaultpackage;
                }else{
                    packageType = StringUtil.getNum(packageType)>pnum?defaultpackage:packageType;
                }

            }
            innerorder.setCartonid(defaultCarton);
            innerorder.setPackageid(packageType);
            innerorder.setUpdatetime(date);
            innerorder.setUpdateuser(username);
            innerOrderDAO.updateByPrimaryKeySelective(innerorder);
        }


    }

    private String makeDefaultCarton(String defaultCarton, Integer sum, ProductEntity product) {
        String cartontypea = product.getCartontypea();
        String cartontypeb = product.getCartontypeb();
        Integer cartontypeanum = product.getCartontypeanum();
        Integer cartontypebnum = product.getCartontypebnum();
        Boolean falg = comparetoStr(defaultCarton,cartontypeb);
        if(falg){
            if(sum <= cartontypeanum){
                defaultCarton = cartontypea;
            }else if(sum <= cartontypebnum){
                defaultCarton = cartontypeb;
            }
        }


        return defaultCarton;
    }

    private Boolean comparetoStr(String defaultCarton, String cartontypeb) {
        Boolean falg = true;
        if(StringUtils.isBlank(defaultCarton)){
            defaultCarton = cartontypeb;
        }else{
            char a = defaultCarton.charAt(0);
            char b = cartontypeb.charAt(0);
            falg = a < b;
            defaultCarton = cartontypeb;
        }

        return falg;
    }

    private void savePackagenum(String packagenum, OrderProductEntity orderProductEntity) {
        orderProductEntity.setinnerpackagenum(packagenum);
        orderProductDAO.updateByPrimaryKeySelective(orderProductEntity);
    }

    private OrdernumEntity savePackageInfo(String username, Date date, String id) {
        String customernum = innerOrderDAO.selectCustomernumByOrderNumId(id);
        String packagenum = OrderUtil.getPackageNum(customernum);
        OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
        ordernumEntity.setPackagenum(packagenum);
        ordernumEntity.setOrderstatus("orderStatus_yidabao");
        ordernumEntity.setUpdatetime(date);
        ordernumEntity.setUpdateuser(username);
        ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
        return ordernumEntity;
    }

    private void setPackageInfo(String[] ids , String packagenum) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        String defaultCarton = "";
        String packageType = "";
        String defaultCartonprefix = "";
        List<InnerOrderEntity> orderlist = new ArrayList<>();
        for(String id : ids){
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(ordernumEntity.getOrderinfo());
            orderlist.add(innerOrderEntity);
            Integer sum = 0;
            List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(ordernumEntity.getOrdernum());
            for(int i = 0 ;i<orderProductEntities.size();i++){
                OrderProductEntity orderProductEntity = orderProductEntities.get(i);
                savePackagenum(packagenum, orderProductEntity);
                String barcode = orderProductEntity.getOuterproductnum();
                ProductEntity product = productDAO.findProductByBarCodeOrNum(barcode);
                Integer count = Integer.parseInt(orderProductEntity.getCount());
                sum += count;
                String cartontypea = product.getCartontypea();
                String cartontypeb = product.getCartontypeb();
                Integer cartontypeanum = product.getCartontypeanum();
                Integer cartontypebnum = product.getCartontypebnum();
                String defaultpackage = product.getDefaultpackage();
                Integer pnum = StringUtil.getNum(defaultpackage);
                if(StringUtils.isNotEmpty(cartontypea)&&StringUtils.isNotEmpty(cartontypeb)){
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
                if(StringUtils.isEmpty(packageType)){
                    packageType = defaultpackage;
                }else{
                    packageType = StringUtil.getNum(packageType)>pnum?defaultpackage:packageType;
                }
                ordernumEntity.setPackagenum(packagenum);
                ordernumEntity.setOrderstatus("orderStatus_yidabao");
                ordernumEntity.setUpdatetime(date);
                ordernumEntity.setUpdateuser(username);
                // innerOrder.setOrderstatus("orderStatus_daidabao");
                ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
            }
        }
        for(InnerOrderEntity innerorder : orderlist){
            innerorder.setCartonid(defaultCarton);
            innerorder.setPackageid(packageType);
            innerorder.setUpdatetime(date);
            innerorder.setUpdateuser(username);
            innerOrderDAO.updateByPrimaryKeySelective(innerorder);
        }
    }

    @Override
    public String savePackageFromExcel(List<PageData> orderListPd, List<PageData> listPd) {
        List<InnerOrderEntity> orderList = new ArrayList<>();
        List<OrderProductEntity> pdList = new ArrayList<>();
        List<OrdernumEntity> ordernumList = new ArrayList<>();
        Map<String,String> outerOrderNum = new HashMap<>();
        final StringBuffer orderStr = new StringBuffer("订单页");
        for(int i = 0 ;i<orderListPd.size();i++) {
            PageData pageData = orderListPd.get(i);
            String start = "第"+(i+1)+"行有错误";
            String orderListStr = makeOrderList(ordernumList,orderList,pageData,outerOrderNum);
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
            ordernumList.forEach(pd->{
                pd.setCreatetime(date);
                pd.setCreateuser(username);
                pd.setUpdatetime(date);
                pd.setUpdateuser(username);
          /*      String customernum = pd.getCustomernum();
                String packagenum = OrderUtil.getPackageNum(customernum);
                setPackageInfo(pd.getId(), packagenum);
                ordernumDAO.insert(pd);*/
            });
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
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            ordernumEntity.setOrderstatus("orderStatus_zuofei");
            ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
        }
    }
    @Override
    public void yichang(String[] arrayDATA_ids) {
        for (String id : arrayDATA_ids) {
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            ordernumEntity.setOrderstatus("orderStatus_yichangjian");
            ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
        }
    }

    @Override
    public void makequeren(String[] arrayDATA_ids) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :arrayDATA_ids){
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            ordernumEntity.setOrderstatus("packageStatus_yishenhe");
            ordernumEntity.setUpdateuser(username);
            ordernumEntity.setUpdatetime(date);
            ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
        }
    }

    @Override
    public void makeshenhe(String[] arrayDATA_ids) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :arrayDATA_ids){
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            ordernumEntity.setOrderstatus("orderStatus_daidabao");
            ordernumEntity.setUpdateuser(username);
            ordernumEntity.setUpdatetime(date);
            ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
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
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            ordernumEntity.setUpdateuser(username);
            ordernumEntity.setUpdatetime(date);
            if("orderStatus_daiqueren".equals(ordernumEntity.getOrderstatus())){
                ordernumEntity.setOrderstatus("orderStatus_yiqueren");
            }else if("orderStatus_daidabao".equals(ordernumEntity.getOrderstatus())){
                ordernumEntity.setOrderstatus("orderStatus_yidabao");
            }
            ordernumDAO.updateByPrimaryKeySelective(ordernumEntity);
        }
    }

    @Override
    public String saveOrderFromExcel(List<PageData> orderListPd, List<PageData> listPd) {
        List<InnerOrderEntity> orderList = new ArrayList<>();
        List<OrderProductEntity> pdList = new ArrayList<>();
        List<OrdernumEntity> ordernumList = new ArrayList<>();
        Map<String,String> outerOrderNum = new HashMap<>();
        final StringBuffer orderStr = new StringBuffer("订单页");
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for(int i = 0 ;i<orderListPd.size();i++) {
            PageData pageData = orderListPd.get(i);
            String start = "第"+(i+1)+"行有错误";
            String orderListStr = makeOrderList(ordernumList,orderList,pageData,outerOrderNum);
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
            ordernumList.forEach(pd->{
                pd.setCreatetime(date);
                pd.setCreateuser(username);
                pd.setUpdatetime(date);
                pd.setUpdateuser(username);
                ordernumDAO.insert(pd);
            });
            orderList.forEach(pd->{
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
                               if("product_daishenhe".equals(productByBarCode.getAuditStatus()) && !username.equals(productByBarCode.getCreateuser())){
                                   return "商品未找到,请查看商品货号/编号；";
                               }else if("product_yitingyong".equals(productByBarCode.getAuditStatus())){
                                   return "商品已停用；";
                               }
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
                   ProductEntity productByBarCode = productDAO.findProductByBarCodeOrNum(waibuhuohao);
                   if(productByBarCode == null ){
                       return "商品未找到,请查看商品货号/编号；";
                   }else{
                       if("product_daishenhe".equals(productByBarCode.getAuditStatus()) && !username.equals(productByBarCode.getCreateuser())){
                           return "商品未找到,请查看商品货号/编号；";
                       }else if("product_yitingyong".equals(productByBarCode.getAuditStatus())){
                           return "商品已停用；";
                       }
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

    private String makeOrderList(List<OrdernumEntity> ordernumList ,List<InnerOrderEntity> orderList, PageData pageData, Map<String, String> outerOrderNum) {
        String waibudingdanhao = pageData.getString("var0");
        String xiadanshijian = pageData.getString("var1");
        String baoguanmoshi = pageData.getString("var23");
        String dingdanhuozhi = pageData.getString("var24");
        Date ordertime = null;
        if(StringUtils.isBlank(waibudingdanhao)){
            return "外部订单号未填写；";
        }else{
            if(!outerOrderNum.keySet().contains(waibudingdanhao)){
                return "订单页已有该订单号；";
            }else{
                InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByOuterordernum(waibudingdanhao);
                if(innerOrderEntity != null){
                    return "该订单号已导入；";
                }
            }
        }
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
        String orderid = UuidUtil.get32UUID();
        OrdernumEntity ordernumEntity = new OrdernumEntity();
        ordernumEntity.setId(UuidUtil.get32UUID());
        ordernumEntity.setOrdernum(customerordernum);
        ordernumEntity.setOrdervalue(dingdanhuozhi);
        ordernumEntity.setOrderstatus("orderStatus_daiqueren");
        ordernumEntity.setOrderinfo(orderid);
        ordernumList.add(ordernumEntity);
        InnerOrderEntity order = new InnerOrderEntity();
        order.setInnerorderid(orderid);
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
        order.setRemark(beizhu);
        orderList.add(order);
        outerOrderNum.put(waibudingdanhao,customerordernum);
        return "";
    }

    @Override
    public List<CangKuEntity> getCangku(String cangkushuxing) {
        return cangKuDAO.getCangku(cangkushuxing);
    }

    @Override
    public void saveShangpinChuku(String[] ids, String cangkushuxing, String cangku, String kuwei) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :ids){
            //1. 获取要推送入库的订单
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(ordernumEntity.getOrderinfo());
            //2.获取该订单的商品集合
            List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(ordernumEntity.getOrdernum());
            //3.查询仓库信息
            CangKuEntity cangKuEntity = cangKuDAO.selectByCangKu(cangku);
            if(StringUtils.isNotBlank(kuwei) && !"默认库位".equals(kuwei)&& !"自定义库位".equals(kuwei)){
                ShangPinKuWeiEntity shangPinKuWeiEntity = shangPinKuWeiDAO.selectByKuWei(kuwei);
                if(shangPinKuWeiEntity == null){
                    ShangPinKuWeiEntity newKuWei = new ShangPinKuWeiEntity();
                    newKuWei.setCangku(cangKuEntity.getId());
                    newKuWei.setId(UuidUtil.get32UUID());
                    newKuWei.setKuwei(kuwei);
                    newKuWei.setCreateuser(username);
                    newKuWei.setCreatetime(new Date());
                    newKuWei.setUpdateuser(username);
                    newKuWei.setUpdatetime(new Date());
                    shangPinKuWeiDAO.insertSelective(newKuWei);
                }
            }else{
                kuwei = "";
            }
            for(OrderProductEntity orderProduct :orderProductEntities){
                //3.创建商品出库信息
                ChuKuShangPinEntity chuKuShangPinEntity = new ChuKuShangPinEntity();
                chuKuShangPinEntity.setChukushangpinid(UuidUtil.get32UUID());
                chuKuShangPinEntity.setKehubianhao(innerOrderEntity.getCustomernum());
                chuKuShangPinEntity.setKehudingdanhao(ordernumEntity.getOrdernum());
                chuKuShangPinEntity.setWaibudingdanhao(innerOrderEntity.getOuterordernum());
                ProductEntity productEntity = productDAO.findProductByProductNum(orderProduct.getOuterproductnum());
                chuKuShangPinEntity.setNeibuhuohao(productEntity.getProductnum());
                chuKuShangPinEntity.setShangpintiaoma(productEntity.getBarcodeMain());
                chuKuShangPinEntity.setShuliang(orderProduct.getCount());
                chuKuShangPinEntity.setChukuzhuangtai("daichuku");
                chuKuShangPinEntity.setCangku(cangku);
                if(StringUtils.isBlank(kuwei)){
                    List<ShangPinKuWeiEntity> list = shangPinKuWeiDAO.selectByCangkuAndProductnum(cangKuEntity.getId(), productEntity.getProductnum());
                    kuwei = (list!=null && list.size()>0)?list.get(0).getKuwei():"";
                }
                chuKuShangPinEntity.setCangwei(kuwei);
                chuKuShangPinEntity.setCreateuser(username);
                chuKuShangPinEntity.setCreatetime(date);
                chuKuShangPinEntity.setUpdatetime(date);
                chuKuShangPinEntity.setUpdateuser(username);
                chuKuShangPinDAO.insertSelective(chuKuShangPinEntity);
            };
        }
        createpackage(ids);
    }

    public void saveBaoguoRuku(String[] ids, String baoguokuwei) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        for (String id :ids) {
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(ordernumEntity.getOrderinfo());
            RuKuBaoGuoEntity ruKuBaoGuoEntity = new RuKuBaoGuoEntity();
            ruKuBaoGuoEntity.setRukubaoguoid(UuidUtil.get32UUID());
            ruKuBaoGuoEntity.setRukuzhuangtai("orderStatus_daidabao");
            ruKuBaoGuoEntity.setKehubianhao(innerOrderEntity.getCustomernum());
            ruKuBaoGuoEntity.setBaoguodanhao(ordernumEntity.getPackagenum());
            BaoGuoKuWeiEntity baoGuoKuWeiEntity = baoGuoKuWeiDAO.selectByPrimaryKey(baoguokuwei);
            if(baoGuoKuWeiEntity != null){
                ruKuBaoGuoEntity.setCangku(baoGuoKuWeiEntity.getCangku());
                ruKuBaoGuoEntity.setCangwei(baoGuoKuWeiEntity.getKuwei());
            }else{
                ruKuBaoGuoEntity.setCangwei("P0000");
            }
            List<CustomerEntity> customerEntities = customerDAO.selectByLoginName(username);
            String customerCode = "";
            if(customerEntities != null && customerEntities.size()>0){
                customerCode = customerEntities.get(0).getCustomercode();
            }
            List<BaoGuoKuWeiEntity> baoGuoKuWeiEntities = baoGuoKuWeiDAO.selectbyCustomerNum(customerCode);
            if(baoGuoKuWeiEntities != null && baoGuoKuWeiEntities.size()>0){
                username = baoGuoKuWeiEntities.get(0).getCreateuser();
            }
            ruKuBaoGuoEntity.setCreateuser(username);
            ruKuBaoGuoEntity.setCreatetime(date);
            ruKuBaoGuoEntity.setUpdatetime(date);
            ruKuBaoGuoEntity.setUpdateuser(username);
            ruKuBaoGuoDAO.insertSelective(ruKuBaoGuoEntity);
        }

    }

    @Override
    public String selectCustomernumByOrderNumId(String ordernumid) {
        return innerOrderDAO.selectCustomernumByOrderNumId(ordernumid);
    }

    @Override
    public List<InnerOrderEntity> packagelistPage(Page page) {
        return innerOrderDAO.packagelistPage(page);
    }

    @Override
    public String savehedan(String DATA_IDS) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        Map<String,List<OrdernumEntity>> childMap = new HashMap<>();
        List<OrdernumEntity> allList = new ArrayList<>();
        String[] ids = DATA_IDS.split(",");
        for (String id : ids) {
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            allList.add(ordernumEntity);
        }
        for (OrdernumEntity childrenOrder: allList) {
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(childrenOrder.getOrderinfo());
            String key = innerOrderEntity.getRecipient() + "#" + innerOrderEntity.getRecipientphone();
            List<OrdernumEntity> ordernumEntities = childMap.get(key);
            if(ordernumEntities == null || ordernumEntities.size() <=0){
                ordernumEntities = new ArrayList<>();
            }
            ordernumEntities.add(childrenOrder);
        }
        String partition = "H";
        for(String key : childMap.keySet()){
            List<OrdernumEntity> ordernumList = childMap.get(key);
            int step = 1;
            String parentId = UuidUtil.get32UUID();
            Integer sumProductcount = 0;
            Double sumOrdervalue = 0.00;
            String pOrdernum = ordernumList.get(0).getOrdernum();
            String orderinfo = ordernumList.get(0).getOrderinfo();
            for (OrdernumEntity ordernum : ordernumList) {
                String orderproductcount = ordernum.getOrderproductcount();
                String ordervalue = ordernum.getOrdervalue();
                sumProductcount += Integer.parseInt(orderproductcount);
                sumOrdervalue += Double.parseDouble(ordervalue);
                String childordernum = ordernum.getOrdernum() + partition + step;
                step++;
                ordernum.setOrdernum(childordernum);
                ordernum.setIspartent(0);
                ordernum.setYujingstatus("yujing_hedan");
                ordernum.setParentid(parentId);
                ordernum.setUpdatetime(date);
                ordernum.setUpdateuser(username);
                ordernumDAO.updateByPrimaryKeySelective(ordernum);
            }
            OrdernumEntity parentOrdernum = new OrdernumEntity();
            parentOrdernum.setId(parentId);
            parentOrdernum.setOrdernum(pOrdernum);
            parentOrdernum.setPackagenum("");
            parentOrdernum.setOrderinfo(orderinfo);
            parentOrdernum.setOrdervalue(sumOrdervalue.toString());
            parentOrdernum.setOrderproductcount(sumProductcount.toString());
            parentOrdernum.setRemark("");
            parentOrdernum.setOrderstatus("orderStatus_yiqueren");
            parentOrdernum.setIspartent(1);
            parentOrdernum.setYujingstatus("yujing_hedan");
            parentOrdernum.setRemark("");
            parentOrdernum.setCreateuser(username);
            parentOrdernum.setCreatetime(date);
            parentOrdernum.setUpdatetime(date);
            parentOrdernum.setUpdateuser(username);
            ordernumDAO.insertSelective(parentOrdernum);
        }
        return  "success";
    }



    @Override
    public String savechaidan(String DATA_IDS) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        String partition = "C";
        String[] ids = DATA_IDS.split(",");
        for(String id : ids){
            OrdernumEntity ordernumEntity = ordernumDAO.selectByPrimaryKey(id);
            List<OrderProductEntity> orderProductList = orderProductDAO.selectOrderProduct(ordernumEntity.getOrdernum());
            List<OrderProductEntity> childrenOrderProduct =  makeChildrenOrderProduct4Chaidan(orderProductList);
            for(OrderProductEntity orderproduct : childrenOrderProduct){
                int step = 1;
                double retailprice = Double.parseDouble(orderproduct.getRetailprice());
                int count = Integer.parseInt(orderproduct.getCount());
                double sum = retailprice * count;
                OrdernumEntity childOrdernum = new OrdernumEntity();
                childOrdernum.setId(UuidUtil.get32UUID());
                String childordernum = ordernumEntity.getOrdernum() + partition + step;
                step++;
                childOrdernum.setOrdernum(childordernum);
                childOrdernum.setPackagenum("");
                childOrdernum.setOrderinfo(ordernumEntity.getOrderinfo());
                childOrdernum.setOrdervalue(sum+"");
                childOrdernum.setOrderproductcount(orderproduct.getCount());
                childOrdernum.setRemark("");
                childOrdernum.setOrderstatus("orderStatus_yiqueren");
                childOrdernum.setIspartent(0);
                childOrdernum.setYujingstatus("yujing_chaidan");
                childOrdernum.setParentid(id);
                childOrdernum.setRemark("");
                childOrdernum.setCreateuser(username);
                childOrdernum.setCreatetime(date);
                childOrdernum.setUpdatetime(date);
                childOrdernum.setUpdateuser(username);
                ordernumDAO.insertSelective(childOrdernum);
                orderproduct.setCustomerordernum(childordernum);
                orderProductDAO.insertSelective(orderproduct);
            }
        }

        return "success";
    }

    @Override
    public List<OrderInfoDTO> selectByhedan(PageData pd) {
        List<OrderInfoDTO> orderInfoList = ordernumDAO.selectByhedan(pd);
        return orderInfoList;
    }

    @Override
    public List<OrderInfoDTO> selectBychaidan(PageData pd) {
        // 查询出复合拆单条件的订单集合
        List<OrderInfoDTO> ordernumList = ordernumDAO.selectByChaidan(pd);
        return ordernumList;
    }

    private List<OrderProductEntity> makeChildrenOrderProduct4Chaidan(List<OrderProductEntity> orderProductList) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        List<OrderProductEntity> childList = new ArrayList<>();
        for (OrderProductEntity orderProduct:orderProductList){
            double retailprice = Double.parseDouble(orderProduct.getRetailprice());
            int count = Integer.parseInt(orderProduct.getCount());
            double sum = retailprice * count;
            if(retailprice >= 166.00 || sum <=166.00  ){
                OrderProductEntity clone = (OrderProductEntity)orderProduct.clone();
                clone.setorderproducrtid(UuidUtil.get32UUID());
                clone.setCreatetime(date);
                clone.setCreateuser(username);
                clone.setUpdatetime(date);
                clone.setUpdateuser(username);
                childList.add(clone);
            }else if(retailprice >= 83.00 && retailprice <= 166.00 ){
                for(int i = 1 ; i <= count ; i++){
                    OrderProductEntity clone = (OrderProductEntity)orderProduct.clone();
                    clone.setorderproducrtid(UuidUtil.get32UUID());
                    clone.setCount("1");
                    clone.setCreatetime(date);
                    clone.setCreateuser(username);
                    clone.setUpdatetime(date);
                    clone.setUpdateuser(username);
                    childList.add(clone);
                }
            }else{
                int step = 2;
                for(int i = 3 ; i<= count ; i++){
                    double v = retailprice * i;
                    if(v >= 166.00){
                        step = i-1;
                    }
                }
                double index = Math.ceil(count / step);
                for(int i = 0 ; i< index ; i++){
                    OrderProductEntity clone = (OrderProductEntity)orderProduct.clone();
                    clone.setorderproducrtid(UuidUtil.get32UUID());
                    if(count > step){
                        clone.setCount(step+"");
                        count -= step;
                    }else{
                        clone.setCount(count+"");
                    }
                    clone.setCreatetime(date);
                    clone.setCreateuser(username);
                    clone.setUpdatetime(date);
                    clone.setUpdateuser(username);
                    childList.add(clone);
                }
            }
        }
        return childList;
    }


  /*  @Override
    public InnerOrderEntity selectByDingdanhao(String dingdanhao) {
        return innerOrderDAO.selectByDingdanhao(dingdanhao);
    }
问题： 1. 合单时，订单金额大于166 如何处理？（现在实现的是：只要符合合单条件就会合单）
	2.拆单时，商品的单价数量大于166并且订单的数量>1做拆单处理吗？（现在实现的是：订单>166 && 数量>1 就拆单，没有考虑单价问题）
	3.文件4 是要怎么处理？ 我看就是一个订单号
	4.文件1（圆通标准模板） 是要用这个模板做excel导入订单吗？ 那原先的导入不要了吗？
	5.总分拣单和分拣单直接导出excel吗？还是弹出页面然后打印。
    @Override
    public List<InnerOrderEntity> selectByBaoguoDanhao(String baoguodanhao) {
        return innerOrderDAO.selectByBaoguoDanhao(baoguodanhao);
    }*/

}
