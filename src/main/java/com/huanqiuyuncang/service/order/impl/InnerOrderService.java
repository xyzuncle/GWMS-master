package com.huanqiuyuncang.service.order.impl;

import com.huanqiuyuncang.controller.wms.customer.CustomerController;
import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.order.InnerOrderDAO;
import com.huanqiuyuncang.dao.order.OrderProductDAO;
import com.huanqiuyuncang.dao.pdconversion.ProductConversionDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.pdconversion.ProductConversionEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.order.InnerOrderInterface;
import com.huanqiuyuncang.service.pdconversion.ProductConversionInterface;
import com.huanqiuyuncang.util.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
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
    private ProductDAO productDAO;

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
            String customerordernum = OrderUtil.getOrderNum(customernum.split("_")[1]);
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
        String packagenum = OrderUtil.getPackageNum(customernum.split("_")[1]);
        List<OrderProductEntity> orderProductEntities = orderProductDAO.selectOrderProduct(innerOrder.getCustomerordernum());
        orderProductEntities.forEach( orderProductEntity -> {
            orderProductEntity.setinnerpackagenum(packagenum);
            orderProductDAO.updateByPrimaryKeySelective(orderProductEntity);
        });
        innerOrder.setInnerpackagenum(packagenum);
        innerOrder.setOrderstatus("orderStatus_daidabao");
        innerOrder.setCartonid("");
        innerOrder.setPackageid("");
        innerOrderDAO.updateByPrimaryKeySelective(innerOrder);
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
    public void shenheAll(String[] arrayDATA_ids) {
        for (String id :arrayDATA_ids){
            InnerOrderEntity innerOrderEntity = innerOrderDAO.selectByPrimaryKey(id);
            innerOrderEntity.setOrderstatus("orderStatus_yiqueren");
            innerOrderDAO.updateByPrimaryKeySelective(innerOrderEntity);
        }
    }



    @Override
    public String saveOrderFromExcel(List<PageData> orderListPd, List<PageData> listPd) {
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

        if("订单页".equals(orderStr.toString())&&"订单商品页".equals(pdStr.toString())){
            orderList.forEach(pd->{
                innerOrderDAO.insert(pd);
            });
            pdList.forEach(pd->{
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
        String barcode = pageData.getString("var2");
        String count = pageData.getString("var3");
        String shenbaojia = pageData.getString("var4");
        String lingshoujia = pageData.getString("var5");
        String beizhu = pageData.getString("var6");
        String customerOrderNum = "";
        if(StringUtils.isBlank(waibudingdanhao)){
            return "外部订单号未填写；";
        }else{
            if(!outerOrderNum.keySet().contains(waibudingdanhao)){
                return "订单页没有改订单号；";
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
        if (StringUtils.isNotBlank(waibuhuohao)){
            ProductConversionEntity pdConversion = productConversionDAO.selectByOuterPdNum(waibuhuohao);
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
                        pd.setBarcode(barcode);
                        pd.setOuterproductnum(waibuhuohao);
                        pdList.add(pd);
                    }
                };
            }
        }else{
            if (StringUtils.isNotBlank(barcode)){
                ProductEntity productByBarCode = productDAO.findProductByBarCode(barcode);
                if(productByBarCode == null){
                    return "商品条码未找到；";
                }else{
                    OrderProductEntity pd = new OrderProductEntity();
                    pd.setOuterordernum(waibudingdanhao);
                    pd.setCustomerordernum(customerOrderNum);
                    pd.setCount(count);
                    pd.setRemark(beizhu);
                    pd.setDeclareprice(shenbaojia);
                    pd.setRetailprice(lingshoujia);
                    pd.setBarcode(barcode);
                    pd.setOuterproductnum(waibuhuohao);
                    pdList.add(pd);
                }
            }else{
                return "外部货号/商品条码未填写；";
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
            shoujianrenqu = innerOrderDAO.selectAreaCodeByName(shoujianrenqu);
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
        String username = Jurisdiction.getUsername();
        String customernum = customerDAO.selectCodeByCode(username);
        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        PropUtil.writeProperties("serialnumber",serialnumber+1+"");
        String serialnumberStr = String.format("%0" + 5 + "d", serialnumber);
        String customerordernum = "O"+customernum.split("_")[1]+formatDate+serialnumberStr;


        InnerOrderEntity order = new InnerOrderEntity();
        order.setCustomerordernum(customerordernum);
        order.setOuterordernum(waibudingdanhao);
        order.setOrdertime(ordertime);
        order.setSender(jijianren);
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
        order.setOrdermultistatus(CustomerController.CUSTOMERSTATUS);
        orderList.add(order);
        outerOrderNum.put(waibudingdanhao,customerordernum);
        return "";
    }

    public static void main(String[] args) throws InterruptedException {
        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber);
        PropUtil.writeProperties("serialnumber",serialnumber+1+"");
        Integer serialnumber1 = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber1);
        PropUtil.writeProperties("serialnumber",serialnumber1+1+"");
        Integer serialnumber2 = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber2);
        PropUtil.writeProperties("serialnumber",serialnumber2+1+"");
        Integer serialnumber3 = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber3);


    }

}
