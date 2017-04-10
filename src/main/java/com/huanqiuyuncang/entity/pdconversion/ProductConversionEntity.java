package com.huanqiuyuncang.entity.pdconversion;

import java.util.Date;

public class ProductConversionEntity {
    private String productconversionid;

    private String outerproductnum;

    private String customercode;

    private String productnum1;

    private String productcount1;

    private String priceof1;

    private String productnum2;

    private String productcount2;

    private String priceof2;

    private String productnum3;

    private String productcount3;

    private String priceof3;

    private String productnum4;

    private String productcount4;

    private String priceof4;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public ProductConversionEntity(String productconversionid, String outerproductnum, String customercode, String productnum1, String productcount1, String priceof1, String productnum2, String productcount2, String priceof2, String productnum3, String productcount3, String priceof3, String productnum4, String productcount4, String priceof4, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.productconversionid = productconversionid;
        this.outerproductnum = outerproductnum;
        this.customercode = customercode;
        this.productnum1 = productnum1;
        this.productcount1 = productcount1;
        this.priceof1 = priceof1;
        this.productnum2 = productnum2;
        this.productcount2 = productcount2;
        this.priceof2 = priceof2;
        this.productnum3 = productnum3;
        this.productcount3 = productcount3;
        this.priceof3 = priceof3;
        this.productnum4 = productnum4;
        this.productcount4 = productcount4;
        this.priceof4 = priceof4;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public ProductConversionEntity() {
        super();
    }

    public String getProductconversionid() {
        return productconversionid;
    }

    public void setProductconversionid(String productconversionid) {
        this.productconversionid = productconversionid == null ? null : productconversionid.trim();
    }

    public String getOuterproductnum() {
        return outerproductnum;
    }

    public void setOuterproductnum(String outerproductnum) {
        this.outerproductnum = outerproductnum == null ? null : outerproductnum.trim();
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode == null ? null : customercode.trim();
    }

    public String getProductnum1() {
        return productnum1;
    }

    public void setProductnum1(String productnum1) {
        this.productnum1 = productnum1 == null ? null : productnum1.trim();
    }

    public String getProductcount1() {
        return productcount1;
    }

    public void setProductcount1(String productcount1) {
        this.productcount1 = productcount1 == null ? null : productcount1.trim();
    }

    public String getPriceof1() {
        return priceof1;
    }

    public void setPriceof1(String priceof1) {
        this.priceof1 = priceof1 == null ? null : priceof1.trim();
    }

    public String getProductnum2() {
        return productnum2;
    }

    public void setProductnum2(String productnum2) {
        this.productnum2 = productnum2 == null ? null : productnum2.trim();
    }

    public String getProductcount2() {
        return productcount2;
    }

    public void setProductcount2(String productcount2) {
        this.productcount2 = productcount2 == null ? null : productcount2.trim();
    }

    public String getPriceof2() {
        return priceof2;
    }

    public void setPriceof2(String priceof2) {
        this.priceof2 = priceof2 == null ? null : priceof2.trim();
    }

    public String getProductnum3() {
        return productnum3;
    }

    public void setProductnum3(String productnum3) {
        this.productnum3 = productnum3 == null ? null : productnum3.trim();
    }

    public String getProductcount3() {
        return productcount3;
    }

    public void setProductcount3(String productcount3) {
        this.productcount3 = productcount3 == null ? null : productcount3.trim();
    }

    public String getPriceof3() {
        return priceof3;
    }

    public void setPriceof3(String priceof3) {
        this.priceof3 = priceof3 == null ? null : priceof3.trim();
    }

    public String getProductnum4() {
        return productnum4;
    }

    public void setProductnum4(String productnum4) {
        this.productnum4 = productnum4 == null ? null : productnum4.trim();
    }

    public String getProductcount4() {
        return productcount4;
    }

    public void setProductcount4(String productcount4) {
        this.productcount4 = productcount4 == null ? null : productcount4.trim();
    }

    public String getPriceof4() {
        return priceof4;
    }

    public void setPriceof4(String priceof4) {
        this.priceof4 = priceof4 == null ? null : priceof4.trim();
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