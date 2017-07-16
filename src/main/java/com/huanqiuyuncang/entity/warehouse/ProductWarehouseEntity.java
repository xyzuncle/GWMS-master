package com.huanqiuyuncang.entity.warehouse;

import java.util.Date;

public class ProductWarehouseEntity {
    private String productwarehouseid;

    private String cangku;

    private String cangwei;

    private String neibuhuohao;

    private String kehubianhao;

    private String shangpintiaoma;

    private String shuliang;

    private String suokustatus;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public ProductWarehouseEntity(String productwarehouseid, String cangku, String cangwei, String neibuhuohao, String kehubianhao, String shangpintiaoma, String shuliang, String suokustatus, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.productwarehouseid = productwarehouseid;
        this.cangwei = cangwei;
        this.cangku = cangku;
        this.neibuhuohao = neibuhuohao;
        this.kehubianhao = kehubianhao;
        this.shangpintiaoma = shangpintiaoma;
        this.shuliang = shuliang;
        this.suokustatus = suokustatus;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public ProductWarehouseEntity() {
        super();
    }

    public String getProductwarehouseid() {
        return productwarehouseid;
    }

    public void setProductwarehouseid(String productwarehouseid) {
        this.productwarehouseid = productwarehouseid == null ? null : productwarehouseid.trim();
    }

    public String getCangku() {
        return cangku;
    }

    public void setCangku(String cangku) {
        this.cangku = cangku;
    }

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei == null ? null : cangwei.trim();
    }

    public String getNeibuhuohao() {
        return neibuhuohao;
    }

    public void setNeibuhuohao(String neibuhuohao) {
        this.neibuhuohao = neibuhuohao == null ? null : neibuhuohao.trim();
    }

    public String getKehubianhao() {
        return kehubianhao;
    }

    public void setKehubianhao(String kehubianhao) {
        this.kehubianhao = kehubianhao == null ? null : kehubianhao.trim();
    }

    public String getShangpintiaoma() {
        return shangpintiaoma;
    }

    public void setShangpintiaoma(String shangpintiaoma) {
        this.shangpintiaoma = shangpintiaoma == null ? null : shangpintiaoma.trim();
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang == null ? null : shuliang.trim();
    }

    public String getSuokustatus() {
        return suokustatus;
    }

    public void setSuokustatus(String suokustatus) {
        this.suokustatus = suokustatus == null ? null : suokustatus.trim();
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

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}