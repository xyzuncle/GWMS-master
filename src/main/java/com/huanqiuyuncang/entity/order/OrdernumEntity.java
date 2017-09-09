package com.huanqiuyuncang.entity.order;

import java.util.Date;

public class OrdernumEntity {
    private String id;

    private String ordernum;

    private String packagenum;

    private String orderinfo;

    private String ordervalue;

    private String orderproductcount;

    private String orderstatus;

    private String yujingstatus;

    private Integer ispartent;

    private String parentid;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public OrdernumEntity(String id, String ordernum, String packagenum, String orderinfo,  String ordervalue,String orderproductcount, String orderstatus, String yujingstatus, Integer ispartent, String parentid, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.ordernum = ordernum;
        this.packagenum = packagenum;
        this.orderinfo = orderinfo;
        this.ordervalue = ordervalue;
        this.orderproductcount = orderproductcount;
        this.orderstatus = orderstatus;
        this.yujingstatus = yujingstatus;
        this.ispartent = ispartent;
        this.parentid = parentid;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public OrdernumEntity(String id, String ordernum,String orderproductcount,  String ordervalue, String yujingstatus) {
        this.id = id;
        this.ordernum = ordernum;
        this.ordervalue = ordervalue;
        this.orderproductcount = orderproductcount;
        this.yujingstatus = yujingstatus;

    }

    public OrdernumEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public String getPackagenum() {
        return packagenum;
    }

    public void setPackagenum(String packagenum) {
        this.packagenum = packagenum == null ? null : packagenum.trim();
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo == null ? null : orderinfo.trim();
    }

    public String getOrdervalue() {
        return ordervalue;
    }

    public void setOrdervalue(String ordervalue) {
        this.ordervalue = ordervalue;
    }

    public String getOrderproductcount() {
        return orderproductcount;
    }

    public void setOrderproductcount(String orderproductcount) {
        this.orderproductcount = orderproductcount;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getYujingstatus() {
        return yujingstatus;
    }

    public void setYujingstatus(String yujingstatus) {
        this.yujingstatus = yujingstatus == null ? null : yujingstatus.trim();
    }

    public Integer getIspartent() {
        return ispartent;
    }

    public void setIspartent(Integer ispartent) {
        this.ispartent = ispartent;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
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