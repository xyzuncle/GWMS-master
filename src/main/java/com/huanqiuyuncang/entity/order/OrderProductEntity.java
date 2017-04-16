package com.huanqiuyuncang.entity.order;

import java.util.Date;

public class OrderProductEntity {
    private String orderproducrtid;

    private String customerordernum;

    private String outerordernum;

    private String outerproductnum;

    private String barcode;

    private String count;

    private String declareprice;

    private String retailprice;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private String formatCreateTime;

    private String formateUpdateTime;

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



    public OrderProductEntity(String orderproducrtid, String customerordernum, String outerordernum, String outerproductnum, String barcode, String count, String declareprice, String retailprice, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.orderproducrtid = orderproducrtid;
        this.customerordernum = customerordernum;
        this.outerordernum = outerordernum;
        this.outerproductnum = outerproductnum;
        this.barcode = barcode;
        this.count = count;
        this.declareprice = declareprice;
        this.retailprice = retailprice;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public OrderProductEntity() {
        super();
    }

    public String getorderproducrtid() {
        return orderproducrtid;
    }

    public void setorderproducrtid(String orderproducrtid) {
        this.orderproducrtid = orderproducrtid == null ? null : orderproducrtid.trim();
    }

    public String getCustomerordernum() {
        return customerordernum;
    }

    public void setCustomerordernum(String customerordernum) {
        this.customerordernum = customerordernum == null ? null : customerordernum.trim();
    }

    public String getOuterordernum() {
        return outerordernum;
    }

    public void setOuterordernum(String outerordernum) {
        this.outerordernum = outerordernum == null ? null : outerordernum.trim();
    }

    public String getOuterproductnum() {
        return outerproductnum;
    }

    public void setOuterproductnum(String outerproductnum) {
        this.outerproductnum = outerproductnum == null ? null : outerproductnum.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count == null ? null : count.trim();
    }

    public String getDeclareprice() {
        return declareprice;
    }

    public void setDeclareprice(String declareprice) {
        this.declareprice = declareprice == null ? null : declareprice.trim();
    }

    public String getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(String retailprice) {
        this.retailprice = retailprice == null ? null : retailprice.trim();
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