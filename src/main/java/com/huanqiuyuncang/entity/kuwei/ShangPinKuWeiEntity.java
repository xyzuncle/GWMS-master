package com.huanqiuyuncang.entity.kuwei;

import java.util.Date;

public class ShangPinKuWeiEntity {
    private String id;

    private String productnum;

    private String cangku;

    private String kuwei;

    private String beizhu;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public ShangPinKuWeiEntity(String id, String productnum, String cangku, String kuwei, String beizhu, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.productnum = productnum;
        this.cangku = cangku;
        this.kuwei = kuwei;
        this.beizhu = beizhu;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public ShangPinKuWeiEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum == null ? null : productnum.trim();
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