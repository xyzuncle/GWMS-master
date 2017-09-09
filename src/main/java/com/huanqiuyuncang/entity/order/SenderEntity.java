package com.huanqiuyuncang.entity.order;

import java.util.Date;

public class SenderEntity {
    private String id;

    private String customercode;

    private String sender;

    private String senderphone;

    private String sendercountry;

    private String senderprovince;

    private String sendercity;

    private String senderarea;

    private String senderaddress;

    private String senderpostcode;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public SenderEntity(String id, String customercode, String sender, String senderphone, String sendercountry, String senderprovince, String sendercity, String senderarea, String senderaddress, String senderpostcode, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.customercode = customercode;
        this.sender = sender;
        this.senderphone = senderphone;
        this.sendercountry = sendercountry;
        this.senderprovince = senderprovince;
        this.sendercity = sendercity;
        this.senderarea = senderarea;
        this.senderaddress = senderaddress;
        this.senderpostcode = senderpostcode;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public SenderEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode == null ? null : customercode.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}