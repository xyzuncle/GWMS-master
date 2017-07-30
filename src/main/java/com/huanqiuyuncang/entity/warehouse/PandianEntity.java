package com.huanqiuyuncang.entity.warehouse;

import java.util.Date;

public class PandianEntity {
    private String id;

    private String productwarehouseid;

    private String status;

    private String beizhu;

    private String cangku;

    private String kuwei;

    private String shangpinbianhao;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public PandianEntity(String id, String productwarehouseid, String status, String beizhu, String cangku, String kuwei, String shangpinbianhao, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.productwarehouseid = productwarehouseid;
        this.status = status;
        this.beizhu = beizhu;
        this.cangku = cangku;
        this.kuwei = kuwei;
        this.shangpinbianhao = shangpinbianhao;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public PandianEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductwarehouseid() {
        return productwarehouseid;
    }

    public void setProductwarehouseid(String productwarehouseid) {
        this.productwarehouseid = productwarehouseid == null ? null : productwarehouseid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public String getCangku() {
        return cangku;
    }

    public void setCangku(String cangku) {
        this.cangku = cangku == null ? null : cangku.trim();
    }

    public String getKuwei() {
        return kuwei;
    }

    public void setKuwei(String kuwei) {
        this.kuwei = kuwei == null ? null : kuwei.trim();
    }

    public String getShangpinbianhao() {
        return shangpinbianhao;
    }

    public void setShangpinbianhao(String shangpinbianhao) {
        this.shangpinbianhao = shangpinbianhao == null ? null : shangpinbianhao.trim();
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

    private String shuliang;

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }
}