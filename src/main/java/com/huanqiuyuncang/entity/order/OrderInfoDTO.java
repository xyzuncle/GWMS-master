package com.huanqiuyuncang.entity.order;

/**
 * Created by lzf on 2017/8/26.
 */
public class OrderInfoDTO {

    private String id;

    private String innerorderid;

    private String ordernum;

    private String outerordernum;

    private String sender;

    private String recipient;

    private String recipientprovince;

    private String recipientcity;

    private String ordervalue;

    private String city;

    private String province;

    private String orderproductcount;

    private String remark;



    public OrderInfoDTO() {
    }

    public OrderInfoDTO(String id, String innerorderid, String ordernum, String outerordernum, String sender, String recipient, String recipientprovince,
                        String recipientcity, String ordervalue, String city, String province, String orderproductcount,String remark) {
        this.id = id;
        this.innerorderid = innerorderid;
        this.ordernum = ordernum;
        this.outerordernum = outerordernum;
        this.sender = sender;
        this.recipient = recipient;
        this.recipientprovince = recipientprovince;
        this.recipientcity = recipientcity;
        this.ordervalue = ordervalue;
        this.city = city;
        this.province = province;
        this.orderproductcount = orderproductcount;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInnerorderid() {
        return innerorderid;
    }

    public void setInnerorderid(String innerorderid) {
        this.innerorderid = innerorderid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getOuterordernum() {
        return outerordernum;
    }

    public void setOuterordernum(String outerordernum) {
        this.outerordernum = outerordernum;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientprovince() {
        return recipientprovince;
    }

    public void setRecipientprovince(String recipientprovince) {
        this.recipientprovince = recipientprovince;
    }

    public String getRecipientcity() {
        return recipientcity;
    }

    public void setRecipientcity(String recipientcity) {
        this.recipientcity = recipientcity;
    }

    public String getOrdervalue() {
        return ordervalue;
    }

    public void setOrdervalue(String ordervalue) {
        this.ordervalue = ordervalue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getOrderproductcount() {
        return orderproductcount;
    }

    public void setOrderproductcount(String orderproductcount) {
        this.orderproductcount = orderproductcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
