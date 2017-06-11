package com.huanqiuyuncang.entity.kuwei;

import java.util.Date;

public class BaoGuoKuWeiEntity {
    private String id;

    private String customernum;

    private String cangku;

    private String kuwei;

    private String beizhu;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public BaoGuoKuWeiEntity(String id, String customernum, String cangku, String kuwei, String beizhu, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.customernum = customernum;
        this.cangku = cangku;
        this.kuwei = kuwei;
        this.beizhu = beizhu;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public BaoGuoKuWeiEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum == null ? null : customernum.trim();
    }

    public String getCangku() {
        return cangku;
    }

    public void setCangku(String cangku) {
        this.cangku = cangku == null ? null : cangku.trim();
    }

    public String getKuwei() {
        return kuwei;
    }

    public void setKuwei(String kuwei) {
        this.kuwei = kuwei == null ? null : kuwei.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
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