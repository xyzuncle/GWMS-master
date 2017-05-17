package com.huanqiuyuncang.entity.warehouse;

import java.util.Date;

public class PackageWarehouseEntity {
    private String packagewarehouseid;

    private String cangwei;

    private String baoguodanhao;

    private String kehubianhao;

    private String shuliang;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public PackageWarehouseEntity(String packagewarehouseid, String cangwei, String baoguodanhao, String kehubianhao, String shuliang, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.packagewarehouseid = packagewarehouseid;
        this.cangwei = cangwei;
        this.baoguodanhao = baoguodanhao;
        this.kehubianhao = kehubianhao;
        this.shuliang = shuliang;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public PackageWarehouseEntity() {
        super();
    }

    public String getPackagewarehouseid() {
        return packagewarehouseid;
    }

    public void setPackagewarehouseid(String packagewarehouseid) {
        this.packagewarehouseid = packagewarehouseid == null ? null : packagewarehouseid.trim();
    }

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei == null ? null : cangwei.trim();
    }

    public String getBaoguodanhao() {
        return baoguodanhao;
    }

    public void setBaoguodanhao(String baoguodanhao) {
        this.baoguodanhao = baoguodanhao == null ? null : baoguodanhao.trim();
    }

    public String getKehubianhao() {
        return kehubianhao;
    }

    public void setKehubianhao(String kehubianhao) {
        this.kehubianhao = kehubianhao == null ? null : kehubianhao.trim();
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang == null ? null : shuliang.trim();
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