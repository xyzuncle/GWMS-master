package com.huanqiuyuncang.entity.warehouse;

import java.util.Date;

public class YiKuEntity {
    private String id;

    private String productnum;

    private String srccangku;

    private String srccangwei;

    private String targetcangku;

    private String targetcangwei;

    private String yikushuangliang;

    private String yikustatus;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public YiKuEntity(String id, String productnum, String srccangku, String srccangwei, String targetcangku, String targetcangwei, String yikushuangliang, String yikustatus, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.productnum = productnum;
        this.srccangku = srccangku;
        this.srccangwei = srccangwei;
        this.targetcangku = targetcangku;
        this.targetcangwei = targetcangwei;
        this.yikushuangliang = yikushuangliang;
        this.yikustatus = yikustatus;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public YiKuEntity() {
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

    public String getSrccangku() {
        return srccangku;
    }

    public void setSrccangku(String srccangku) {
        this.srccangku = srccangku == null ? null : srccangku.trim();
    }

    public String getSrccangwei() {
        return srccangwei;
    }

    public void setSrccangwei(String srccangwei) {
        this.srccangwei = srccangwei == null ? null : srccangwei.trim();
    }

    public String getTargetcangku() {
        return targetcangku;
    }

    public void setTargetcangku(String targetcangku) {
        this.targetcangku = targetcangku == null ? null : targetcangku.trim();
    }

    public String getTargetcangwei() {
        return targetcangwei;
    }

    public void setTargetcangwei(String targetcangwei) {
        this.targetcangwei = targetcangwei == null ? null : targetcangwei.trim();
    }

    public String getYikushuangliang() {
        return yikushuangliang;
    }

    public void setYikushuangliang(String yikushuangliang) {
        this.yikushuangliang = yikushuangliang == null ? null : yikushuangliang.trim();
    }

    public String getYikustatus() {
        return yikustatus;
    }

    public void setYikustatus(String yikustatus) {
        this.yikustatus = yikustatus == null ? null : yikustatus.trim();
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