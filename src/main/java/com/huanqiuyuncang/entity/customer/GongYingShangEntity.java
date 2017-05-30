package com.huanqiuyuncang.entity.customer;

import java.util.Date;

public class GongYingShangEntity {
    private String gongyingshangid;

    private String gongyingshangcode;

    private String gongyingshangname;

    private String defaultwarehouse;

    private String gongyingshangstatus;

    private String remark;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public GongYingShangEntity(String gongyingshangid, String gongyingshangcode, String gongyingshangname, String defaultwarehouse, String gongyingshangstatus, String remark, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.gongyingshangid = gongyingshangid;
        this.gongyingshangcode = gongyingshangcode;
        this.gongyingshangname = gongyingshangname;
        this.defaultwarehouse = defaultwarehouse;
        this.gongyingshangstatus = gongyingshangstatus;
        this.remark = remark;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public GongYingShangEntity() {
        super();
    }

    public String getGongyingshangid() {
        return gongyingshangid;
    }

    public void setGongyingshangid(String gongyingshangid) {
        this.gongyingshangid = gongyingshangid == null ? null : gongyingshangid.trim();
    }

    public String getGongyingshangcode() {
        return gongyingshangcode;
    }

    public void setGongyingshangcode(String gongyingshangcode) {
        this.gongyingshangcode = gongyingshangcode == null ? null : gongyingshangcode.trim();
    }

    public String getGongyingshangname() {
        return gongyingshangname;
    }

    public void setGongyingshangname(String gongyingshangname) {
        this.gongyingshangname = gongyingshangname == null ? null : gongyingshangname.trim();
    }

    public String getDefaultwarehouse() {
        return defaultwarehouse;
    }

    public void setDefaultwarehouse(String defaultwarehouse) {
        this.defaultwarehouse = defaultwarehouse == null ? null : defaultwarehouse.trim();
    }

    public String getGongyingshangstatus() {
        return gongyingshangstatus;
    }

    public void setGongyingshangstatus(String gongyingshangstatus) {
        this.gongyingshangstatus = gongyingshangstatus == null ? null : gongyingshangstatus.trim();
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



}