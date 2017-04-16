package com.huanqiuyuncang.entity.order;

/**
 * Created by lzf on 2017/4/15.
 */
public class ProductOrderBase {

    private String productnum;

    private String productname;

    private String unit;

    private String count;

    private String price;

    private String totalprice;

    private String crossborderCourierfee;

    private String remark;

    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getCrossborderCourierfee() {
        return crossborderCourierfee;
    }

    public void setCrossborderCourierfee(String crossborderCourierfee) {
        this.crossborderCourierfee = crossborderCourierfee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
