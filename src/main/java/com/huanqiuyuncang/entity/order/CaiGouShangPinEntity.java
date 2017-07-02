package com.huanqiuyuncang.entity.order;


public class CaiGouShangPinEntity {
    private String id;

    private String caigoudingdanid;

    private String shangpinhuohao;

    private String shuliang;

    private String caigoujiage;

    private String xiaoji;

    private String beizhu;

    private String saomastatus;

    public CaiGouShangPinEntity(String id, String caigoudingdanid, String shangpinhuohao, String shuliang, String caigoujiage, String xiaoji, String beizhu, String saomastatus) {
        this.id = id;
        this.caigoudingdanid = caigoudingdanid;
        this.shangpinhuohao = shangpinhuohao;
        this.shuliang = shuliang;
        this.caigoujiage = caigoujiage;
        this.xiaoji = xiaoji;
        this.beizhu = beizhu;
        this.saomastatus = saomastatus;
    }

    public CaiGouShangPinEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaigoudingdanid() {
        return caigoudingdanid;
    }

    public void setCaigoudingdanid(String caigoudingdanid) {
        this.caigoudingdanid = caigoudingdanid == null ? null : caigoudingdanid.trim();
    }

    public String getShangpinhuohao() {
        return shangpinhuohao;
    }

    public void setShangpinhuohao(String shangpinhuohao) {
        this.shangpinhuohao = shangpinhuohao == null ? null : shangpinhuohao.trim();
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang == null ? null : shuliang.trim();
    }

    public String getCaigoujiage() {
        return caigoujiage;
    }

    public void setCaigoujiage(String caigoujiage) {
        this.caigoujiage = caigoujiage == null ? null : caigoujiage.trim();
    }

    public String getXiaoji() {
        return xiaoji;
    }

    public void setXiaoji(String xiaoji) {
        this.xiaoji = xiaoji == null ? null : xiaoji.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public String getSaomastatus() {
        return saomastatus;
    }

    public void setSaomastatus(String saomastatus) {
        this.saomastatus = saomastatus == null ? null : saomastatus.trim();
    }

    private String saomiaoshuliang;

    public String getSaomiaoshuliang() {
        return saomiaoshuliang;
    }

    public void setSaomiaoshuliang(String saomiaoshuliang) {
        this.saomiaoshuliang = saomiaoshuliang;
    }
}