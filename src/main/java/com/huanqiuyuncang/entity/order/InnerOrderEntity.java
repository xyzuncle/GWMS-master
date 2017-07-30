package com.huanqiuyuncang.entity.order;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InnerOrderEntity {
    private String innerorderid;

    private String innerpackagenum;

    private String customerordernum;

    private String customernum;

    private String outerordernum;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ordertime;

    private String sender;

    private String senderphone;

    private String sendercountry;

    private String senderprovince;

    private String sendercity;

    private String senderarea;

    private String senderaddress;

    private String senderpostcode;

    private String recipient;

    private String recipientidcard;

    private String recipientphone;

    private String recipientcountry;

    private String recipientprovince;

    private String recipientcity;

    private String recipientarea;

    private String recipientaddress;

    private String recipientpostcode;

    private String paymentmethod;

    private String paymentnum;

    private String paymenttime;

    private String orderproductcount;

    private String customerremarks;

    private String couriername;

    private String couriernum;

    private String customsmodel;

    private String ordervalue;

    private String cartonid;

    private String packageid;

    private String declarevalue;

    private String taxesfees;

    private String freight;

    private String packingcost;

    private String orthercost;

    private String warehousedelivery;

    private String orderstatus;

    private String ordermultistatus;

    private String remark;

    private String remark1;

    private String remark2;

    private String yujingstatus;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private String formateOrderTime;

    private String formatCreateTime;

    private String formateUpdateTime;

    private String  productsum;

    public String getProductsum() {
        return productsum;
    }

    public void setProductsum(String productsum) {
        this.productsum = productsum;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getFormateUpdateTime() {
        return formateUpdateTime;
    }

    public void setFormateUpdateTime(String formateUpdateTime) {
        this.formateUpdateTime = formateUpdateTime;
    }

    public String getFormateOrderTime() {
        return formateOrderTime;
    }

    public void setFormateOrderTime(String formateOrderTime) {
        this.formateOrderTime = formateOrderTime;
    }

    public InnerOrderEntity(String innerorderid, String innerpackagenum, String customerordernum, String customernum,
                            String outerordernum, Date ordertime, String sender, String senderphone, String sendercountry,
                            String senderprovince, String sendercity, String senderarea, String senderaddress,
                            String senderpostcode, String recipient, String recipientidcard, String recipientphone,
                            String recipientcountry, String recipientprovince, String recipientcity, String recipientarea,
                            String recipientaddress, String recipientpostcode, String paymentmethod, String paymentnum,
                            String paymenttime, String orderproductcount, String customerremarks, String couriername,
                            String couriernum, String customsmodel, String ordervalue, String cartonid, String packageid,
                            String declarevalue, String taxesfees, String freight, String packingcost, String orthercost,
                            String warehousedelivery, String orderstatus, String ordermultistatus, String remark,
                            String remark1, String remark2, String yujingstatus, String createuser, Date createtime, String updateuser,
                            Date updatetime) {
        this.innerorderid = innerorderid;
        this.innerpackagenum = innerpackagenum;
        this.customerordernum = customerordernum;
        this.customernum = customernum;
        this.outerordernum = outerordernum;
        this.ordertime = ordertime;
        this.sender = sender;
        this.senderphone = senderphone;
        this.sendercountry = sendercountry;
        this.senderprovince = senderprovince;
        this.sendercity = sendercity;
        this.senderarea = senderarea;
        this.senderaddress = senderaddress;
        this.senderpostcode = senderpostcode;
        this.recipient = recipient;
        this.recipientidcard = recipientidcard;
        this.recipientphone = recipientphone;
        this.recipientcountry = recipientcountry;
        this.recipientprovince = recipientprovince;
        this.recipientcity = recipientcity;
        this.recipientarea = recipientarea;
        this.recipientaddress = recipientaddress;
        this.recipientpostcode = recipientpostcode;
        this.paymentmethod = paymentmethod;
        this.paymentnum = paymentnum;
        this.paymenttime = paymenttime;
        this.orderproductcount = orderproductcount;
        this.customerremarks = customerremarks;
        this.couriername = couriername;
        this.couriernum = couriernum;
        this.customsmodel = customsmodel;
        this.ordervalue = ordervalue;
        this.cartonid = cartonid;
        this.packageid = packageid;
        this.declarevalue = declarevalue;
        this.taxesfees = taxesfees;
        this.freight = freight;
        this.packingcost = packingcost;
        this.orthercost = orthercost;
        this.warehousedelivery = warehousedelivery;
        this.orderstatus = orderstatus;
        this.ordermultistatus = ordermultistatus;
        this.remark = remark;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.yujingstatus = yujingstatus;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public InnerOrderEntity() {
        super();
    }

    public String getInnerorderid() {
        return innerorderid;
    }

    public void setInnerorderid(String innerorderid) {
        this.innerorderid = innerorderid == null ? null : innerorderid.trim();
    }

    public String getCustomerordernum() {
        return customerordernum;
    }

    public void setCustomerordernum(String customerordernum) {
        this.customerordernum = customerordernum == null ? null : customerordernum.trim();
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum == null ? null : customernum.trim();
    }

    public String getOuterordernum() {
        return outerordernum;
    }

    public void setOuterordernum(String outerordernum) {
        this.outerordernum = outerordernum == null ? null : outerordernum.trim();
    }

    public Date getOrdertime() {

        return ordertime;

    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getSenderphone() {
        return senderphone;
    }

    public void setSenderphone(String senderphone) {
        this.senderphone = senderphone == null ? null : senderphone.trim();
    }

    public String getSendercountry() {
        return sendercountry;
    }

    public void setSendercountry(String sendercountry) {
        this.sendercountry = sendercountry == null ? null : sendercountry.trim();
    }

    public String getSenderprovince() {
        return senderprovince;
    }

    public void setSenderprovince(String senderprovince) {
        this.senderprovince = senderprovince == null ? null : senderprovince.trim();
    }

    public String getSendercity() {
        return sendercity;
    }

    public void setSendercity(String sendercity) {
        this.sendercity = sendercity == null ? null : sendercity.trim();
    }

    public String getSenderarea() {
        return senderarea;
    }

    public void setSenderarea(String senderarea) {
        this.senderarea = senderarea == null ? null : senderarea.trim();
    }

    public String getSenderaddress() {
        return senderaddress;
    }

    public void setSenderaddress(String senderaddress) {
        this.senderaddress = senderaddress == null ? null : senderaddress.trim();
    }

    public String getSenderpostcode() {
        return senderpostcode;
    }

    public void setSenderpostcode(String senderpostcode) {
        this.senderpostcode = senderpostcode == null ? null : senderpostcode.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getRecipientidcard() {
        return recipientidcard;
    }

    public void setRecipientidcard(String recipientidcard) {
        this.recipientidcard = recipientidcard == null ? null : recipientidcard.trim();
    }

    public String getOrdermultistatus() {
        return ordermultistatus;
    }

    public void setOrdermultistatus(String ordermultistatus) {
        this.ordermultistatus = ordermultistatus;
    }

    public String getRecipientphone() {
        return recipientphone;
    }

    public void setRecipientphone(String recipientphone) {
        this.recipientphone = recipientphone == null ? null : recipientphone.trim();
    }

    public String getRecipientcountry() {
        return recipientcountry;
    }

    public void setRecipientcountry(String recipientcountry) {
        this.recipientcountry = recipientcountry == null ? null : recipientcountry.trim();
    }

    public String getRecipientprovince() {
        return recipientprovince;
    }

    public void setRecipientprovince(String recipientprovince) {
        this.recipientprovince = recipientprovince == null ? null : recipientprovince.trim();
    }

    public String getRecipientcity() {
        return recipientcity;
    }

    public void setRecipientcity(String recipientcity) {
        this.recipientcity = recipientcity == null ? null : recipientcity.trim();
    }

    public String getRecipientarea() {
        return recipientarea;
    }

    public void setRecipientarea(String recipientarea) {
        this.recipientarea = recipientarea == null ? null : recipientarea.trim();
    }

    public String getRecipientaddress() {
        return recipientaddress;
    }

    public void setRecipientaddress(String recipientaddress) {
        this.recipientaddress = recipientaddress == null ? null : recipientaddress.trim();
    }

    public String getRecipientpostcode() {
        return recipientpostcode;
    }

    public void setRecipientpostcode(String recipientpostcode) {
        this.recipientpostcode = recipientpostcode == null ? null : recipientpostcode.trim();
    }

    public String getYujingstatus() {
        return yujingstatus;
    }

    public void setYujingstatus(String yujingstatus) {
        this.yujingstatus = yujingstatus;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod == null ? null : paymentmethod.trim();
    }

    public String getPaymentnum() {
        return paymentnum;
    }

    public void setPaymentnum(String paymentnum) {
        this.paymentnum = paymentnum == null ? null : paymentnum.trim();
    }

    public String getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(String paymenttime) {
        this.paymenttime = paymenttime;
    }

    public String getOrderproductcount() {
        return orderproductcount;
    }

    public void setOrderproductcount(String orderproductcount) {
        this.orderproductcount = orderproductcount == null ? null : orderproductcount.trim();
    }

    public String getCustomerremarks() {
        return customerremarks;
    }

    public void setCustomerremarks(String customerremarks) {
        this.customerremarks = customerremarks == null ? null : customerremarks.trim();
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername == null ? null : couriername.trim();
    }

    public String getCouriernum() {
        return couriernum;
    }

    public void setCouriernum(String couriernum) {
        this.couriernum = couriernum == null ? null : couriernum.trim();
    }

    public String getCustomsmodel() {
        return customsmodel;
    }

    public void setCustomsmodel(String customsmodel) {
        this.customsmodel = customsmodel == null ? null : customsmodel.trim();
    }

    public String getOrdervalue() {
        return ordervalue;
    }

    public void setOrdervalue(String ordervalue) {
        this.ordervalue = ordervalue == null ? null : ordervalue.trim();
    }

    public String getDeclarevalue() {
        return declarevalue;
    }

    public void setDeclarevalue(String declarevalue) {
        this.declarevalue = declarevalue == null ? null : declarevalue.trim();
    }

    public String getTaxesfees() {
        return taxesfees;
    }

    public void setTaxesfees(String taxesfees) {
        this.taxesfees = taxesfees == null ? null : taxesfees.trim();
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }

    public String getPackingcost() {
        return packingcost;
    }

    public void setPackingcost(String packingcost) {
        this.packingcost = packingcost == null ? null : packingcost.trim();
    }

    public String getOrthercost() {
        return orthercost;
    }

    public void setOrthercost(String orthercost) {
        this.orthercost = orthercost == null ? null : orthercost.trim();
    }

    public String getWarehousedelivery() {
        return warehousedelivery;
    }

    public void setWarehousedelivery(String warehousedelivery) {
        this.warehousedelivery = warehousedelivery == null ? null : warehousedelivery.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getInnerpackagenum() {
        return innerpackagenum;
    }

    public void setInnerpackagenum(String innerpackagenum) {
        this.innerpackagenum = innerpackagenum;
    }

    public String getCartonid() {
        return cartonid;
    }

    public void setCartonid(String cartonid) {
        this.cartonid = cartonid;
    }

    public String getPackageid() {
        return packageid;
    }

    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }
}