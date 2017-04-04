package com.huanqiuyuncang.entity.luggagemail;

import java.util.Date;

public class LuggageMailEntity {
    private String luggagemailid;

    private String luggagemailcode;

    private String luggagemailname;

    private String rates;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public LuggageMailEntity(String luggagemailid, String luggagemailcode, String luggagemailname, String rates, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.luggagemailid = luggagemailid;
        this.luggagemailcode = luggagemailcode;
        this.luggagemailname = luggagemailname;
        this.rates = rates;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public LuggageMailEntity() {
        super();
    }

    public String getLuggagemailid() {
        return luggagemailid;
    }

    public void setLuggagemailid(String luggagemailid) {
        this.luggagemailid = luggagemailid == null ? null : luggagemailid.trim();
    }

    public String getLuggagemailcode() {
        return luggagemailcode;
    }

    public void setLuggagemailcode(String luggagemailcode) {
        this.luggagemailcode = luggagemailcode == null ? null : luggagemailcode.trim();
    }

    public String getLuggagemailname() {
        return luggagemailname;
    }

    public void setLuggagemailname(String luggagemailname) {
        this.luggagemailname = luggagemailname == null ? null : luggagemailname.trim();
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