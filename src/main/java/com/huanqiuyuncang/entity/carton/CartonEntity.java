package com.huanqiuyuncang.entity.carton;

import java.util.Date;

public class CartonEntity {
    private String cartonid;

    private String cartontype;

    private String cartonname;

    private String length;

    private String width;

    private String high;

    private String volume;

    private String price;

    private String weight;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public CartonEntity(String cartonid, String cartontype, String cartonname, String length, String width, String high, String volume, String price, String weight, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.cartonid = cartonid;
        this.cartontype = cartontype;
        this.cartonname = cartonname;
        this.length = length;
        this.width = width;
        this.high = high;
        this.volume = volume;
        this.price = price;
        this.weight = weight;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public CartonEntity() {
        super();
    }

    public String getCartonid() {
        return cartonid;
    }

    public void setCartonid(String cartonid) {
        this.cartonid = cartonid == null ? null : cartonid.trim();
    }

    public String getCartontype() {
        return cartontype;
    }

    public void setCartontype(String cartontype) {
        this.cartontype = cartontype == null ? null : cartontype.trim();
    }

    public String getCartonname() {
        return cartonname;
    }

    public void setCartonname(String cartonname) {
        this.cartonname = cartonname == null ? null : cartonname.trim();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high == null ? null : high.trim();
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
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