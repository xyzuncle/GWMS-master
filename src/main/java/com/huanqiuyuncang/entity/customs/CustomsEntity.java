package com.huanqiuyuncang.entity.customs;

import java.util.Date;

public class CustomsEntity {
    private String customsid;

    private String customscode;

    private String customsname;

    private String rates;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public CustomsEntity(String customsid, String customscode, String customsname, String rates, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.customsid = customsid;
        this.customscode = customscode;
        this.customsname = customsname;
        this.rates = rates;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public CustomsEntity() {
        super();
    }

    public String getCustomsid() {
        return customsid;
    }

    public void setCustomsid(String customsid) {
        this.customsid = customsid == null ? null : customsid.trim();
    }

    public String getCustomscode() {
        return customscode;
    }

    public void setCustomscode(String customscode) {
        this.customscode = customscode == null ? null : customscode.trim();
    }

    public String getCustomsname() {
        return customsname;
    }

    public void setCustomsname(String customsname) {
        this.customsname = customsname == null ? null : customsname.trim();
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates == null ? null : rates.trim();
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