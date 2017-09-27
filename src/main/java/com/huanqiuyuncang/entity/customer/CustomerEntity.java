package com.huanqiuyuncang.entity.customer;

import com.huanqiuyuncang.util.DateUtil;
import com.huanqiuyuncang.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class CustomerEntity {
    private String customerid;

    private String customercode;

    private String customername;

    private String defaultwarehouse;

    private String customerstatus;

    private String remark;

    private String loginname;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private String formatCreateTime;

    private String formateUpdateTime;

    public String getFormatCreateTime() {
        formatCreateTime =  StringUtils.isBlank(formatCreateTime) ? DateUtil.format(getCreatetime(), "yyyy-MM-dd") :formatCreateTime;
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {

        this.formatCreateTime = formatCreateTime;
    }

    public String getFormateUpdateTime() {
        formateUpdateTime =  StringUtils.isBlank(formateUpdateTime) ? DateUtil.format(getUpdatetime(), "yyyy-MM-dd") :formateUpdateTime;
        return formateUpdateTime;
    }

    public void setFormateUpdateTime(String formateUpdateTime) {

        this.formateUpdateTime = formateUpdateTime;
    }


    public CustomerEntity(String customerid, String customercode, String customername, String defaultwarehouse, String customerstatus, String remark, String loginname, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.customerid = customerid;
        this.customercode = customercode;
        this.customername = customername;
        this.defaultwarehouse = defaultwarehouse;
        this.customerstatus = customerstatus;
        this.remark = remark;
        this.loginname = loginname;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public CustomerEntity() {
        super();
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode == null ? null : customercode.trim();
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername == null ? null : customername.trim();
    }

    public String getDefaultwarehouse() {
        return defaultwarehouse;
    }

    public void setDefaultwarehouse(String defaultwarehouse) {
        this.defaultwarehouse = defaultwarehouse == null ? null : defaultwarehouse.trim();
    }

    public String getCustomerstatus() {
        return customerstatus;
    }

    public void setCustomerstatus(String customerstatus) {
        this.customerstatus = customerstatus == null ? null : customerstatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
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