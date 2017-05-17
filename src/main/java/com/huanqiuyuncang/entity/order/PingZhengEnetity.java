package com.huanqiuyuncang.entity.order;

import java.util.List;

/**
 * Created by lzf on 2017/4/15.
 */
public class PingZhengEnetity {

    private String customerName;

    private String orderTime;

    private String danjuhao;

    private String cangku;

    private String yewuyuan;

    private String zhekou;

    private String dingdanjine;

    private String youxiaoqi;

    private String fukuanfangshi;

    private String dingdanbeizhu;

    private String bizhong;

    private List<ProductOrderBase> pdlist;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDanjuhao() {
        return danjuhao;
    }

    public void setDanjuhao(String danjuhao) {
        this.danjuhao = danjuhao;
    }

    public String getCangku() {
        return cangku;
    }

    public void setCangku(String cangku) {
        this.cangku = cangku;
    }

    public String getYewuyuan() {
        return yewuyuan;
    }

    public void setYewuyuan(String yewuyuan) {
        this.yewuyuan = yewuyuan;
    }

    public String getZhekou() {
        return zhekou;
    }

    public void setZhekou(String zhekou) {
        this.zhekou = zhekou;
    }

    public String getDingdanjine() {
        return dingdanjine;
    }

    public void setDingdanjine(String dingdanjine) {
        this.dingdanjine = dingdanjine;
    }

    public String getYouxiaoqi() {
        return youxiaoqi;
    }

    public void setYouxiaoqi(String youxiaoqi) {
        this.youxiaoqi = youxiaoqi;
    }

    public String getFukuanfangshi() {
        return fukuanfangshi;
    }

    public void setFukuanfangshi(String fukuanfangshi) {
        this.fukuanfangshi = fukuanfangshi;
    }

    public String getDingdanbeizhu() {
        return dingdanbeizhu;
    }

    public void setDingdanbeizhu(String dingdanbeizhu) {
        this.dingdanbeizhu = dingdanbeizhu;
    }

    public String getBizhong() {
        return bizhong;
    }

    public void setBizhong(String bizhong) {
        this.bizhong = bizhong;
    }

    public List<ProductOrderBase> getPdlist() {
        return pdlist;
    }

    public void setPdlist(List<ProductOrderBase> pdlist) {
        this.pdlist = pdlist;
    }
}
