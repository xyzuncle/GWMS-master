package com.huanqiuyuncang.entity.order;

import java.util.Date;

public class CaiGouDingDanEntity {
    private String caigoudingdanid;

    private String caigoudingdanhao;

    private String gongyingshangbianhao;

    private String kehubianhao;

    private String caigoujiage;

    private String caigoudingdanstatus;

    private String cangku;

    private String cangwei;

    private String cangkuuser;

    private String beizhu;

    private String yujingstatus;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public CaiGouDingDanEntity(String caigoudingdanid, String caigoudingdanhao, String gongyingshangbianhao, String kehubianhao, String caigoujiage, String caigoudingdanstatus, String cangku,String cangwei, String cangkuuser, String beizhu, String yujingstatus, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.caigoudingdanid = caigoudingdanid;
        this.caigoudingdanhao = caigoudingdanhao;
        this.gongyingshangbianhao = gongyingshangbianhao;
        this.kehubianhao = kehubianhao;
        this.caigoujiage = caigoujiage;
        this.caigoudingdanstatus = caigoudingdanstatus;
        this.cangku = cangku;
        this.cangwei = cangwei;
        this.cangkuuser = cangkuuser;
        this.beizhu = beizhu;
        this.beizhu = yujingstatus;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public CaiGouDingDanEntity() {
        super();
    }

    public String getCaigoudingdanid() {
        return caigoudingdanid;
    }

    public void setCaigoudingdanid(String caigoudingdanid) {
        this.caigoudingdanid = caigoudingdanid == null ? null : caigoudingdanid.trim();
    }

    public String getCaigoudingdanhao() {
        return caigoudingdanhao;
    }

    public void setCaigoudingdanhao(String caigoudingdanhao) {
        this.caigoudingdanhao = caigoudingdanhao == null ? null : caigoudingdanhao.trim();
    }

    public String getGongyingshangbianhao() {
        return gongyingshangbianhao;
    }

    public void setGongyingshangbianhao(String gongyingshangbianhao) {
        this.gongyingshangbianhao = gongyingshangbianhao == null ? null : gongyingshangbianhao.trim();
    }

    public String getKehubianhao() {
        return kehubianhao;
    }

    public void setKehubianhao(String kehubianhao) {
        this.kehubianhao = kehubianhao == null ? null : kehubianhao.trim();
    }

    public String getCaigoujiage() {
        return caigoujiage;
    }

    public void setCaigoujiage(String caigoujiage) {
        this.caigoujiage = caigoujiage == null ? null : caigoujiage.trim();
    }

    public String getCaigoudingdanstatus() {
        return caigoudingdanstatus;
    }

    public void setCaigoudingdanstatus(String caigoudingdanstatus) {
        this.caigoudingdanstatus = caigoudingdanstatus == null ? null : caigoudingdanstatus.trim();
    }

    public String getCangku() {
        return cangku;
    }

    public void setCangku(String cangku) {
        this.cangku = cangku == null ? null : cangku.trim();
    }

    public String getCangkuuser() {
        return cangkuuser;
    }

    public void setCangkuuser(String cangkuuser) {
        this.cangkuuser = cangkuuser == null ? null : cangkuuser.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public String getYujingstatus() {
        return yujingstatus;
    }

    public void setYujingstatus(String yujingstatus) {
        this.yujingstatus = yujingstatus;
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

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei;
    }
}