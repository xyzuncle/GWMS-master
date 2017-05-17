package com.huanqiuyuncang.entity.warehouse;

import java.util.Date;

public class RuKuBaoGuoEntity {
    private String rukubaoguoid;

    private String baoguodanhao;

    private String kehubianhao;

    private String cangwei;

    private String rukuzhuangtai;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public RuKuBaoGuoEntity(String rukubaoguoid, String baoguodanhao, String kehubianhao, String cangwei, String rukuzhuangtai, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.rukubaoguoid = rukubaoguoid;
        this.baoguodanhao = baoguodanhao;
        this.kehubianhao = kehubianhao;
        this.cangwei = cangwei;
        this.rukuzhuangtai = rukuzhuangtai;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public RuKuBaoGuoEntity() {
        super();
    }

    public String getRukubaoguoid() {
        return rukubaoguoid;
    }

    public void setRukubaoguoid(String rukubaoguoid) {
        this.rukubaoguoid = rukubaoguoid == null ? null : rukubaoguoid.trim();
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

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei == null ? "P0000" : cangwei.trim();
    }

    public String getRukuzhuangtai() {
        return rukuzhuangtai;
    }

    public void setRukuzhuangtai(String rukuzhuangtai) {
        this.rukuzhuangtai = rukuzhuangtai == null ? null : rukuzhuangtai.trim();
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