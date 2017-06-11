package com.huanqiuyuncang.entity.kuwei;

import java.util.Date;

public class CangKuEntity {
    private String id;

    private String cangkubianhao;

    private String cangkuname;

    private String cangkushuxing;

    private String beizhu;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public CangKuEntity(String id, String cangkubianhao, String cangkuname, String cangkushuxing, String beizhu, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.cangkubianhao = cangkubianhao;
        this.cangkuname = cangkuname;
        this.cangkushuxing = cangkushuxing;
        this.beizhu = beizhu;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public CangKuEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCangkubianhao() {
        return cangkubianhao;
    }

    public void setCangkubianhao(String cangkubianhao) {
        this.cangkubianhao = cangkubianhao == null ? null : cangkubianhao.trim();
    }

    public String getCangkuname() {
        return cangkuname;
    }

    public void setCangkuname(String cangkuname) {
        this.cangkuname = cangkuname == null ? null : cangkuname.trim();
    }

    public String getCangkushuxing() {
        return cangkushuxing;
    }

    public void setCangkushuxing(String cangkushuxing) {
        this.cangkushuxing = cangkushuxing == null ? null : cangkushuxing.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
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