package com.huanqiuyuncang.entity.warehouse;

import java.util.Date;

public class ChuKuShangPinEntity {
    private String chukushangpinid;

    private String baoguodanhao;

    private String kehudingdanhao;

    private String waibudingdanhao;

    private String kehubianhao;

    private String neibuhuohao;

    private String shangpintiaoma;

    private String shuliang;

    private String cangku;

    private String cangwei;

    private String chukuzhuangtai;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public ChuKuShangPinEntity(String chukushangpinid, String baoguodanhao, String kehudingdanhao, String waibudingdanhao, String kehubianhao, String neibuhuohao, String shangpintiaoma, String shuliang, String cangku, String cangwei, String chukuzhuangtai, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.chukushangpinid = chukushangpinid;
        this.baoguodanhao = baoguodanhao;
        this.kehudingdanhao = kehudingdanhao;
        this.waibudingdanhao = waibudingdanhao;
        this.kehubianhao = kehubianhao;
        this.neibuhuohao = neibuhuohao;
        this.shangpintiaoma = shangpintiaoma;
        this.shuliang = shuliang;
        this.cangku = cangku;
        this.cangwei = cangwei;
        this.chukuzhuangtai = chukuzhuangtai;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public ChuKuShangPinEntity() {
        super();
    }

    public String getChukushangpinid() {
        return chukushangpinid;
    }

    public void setChukushangpinid(String chukushangpinid) {
        this.chukushangpinid = chukushangpinid == null ? null : chukushangpinid.trim();
    }

    public String getBaoguodanhao() {
        return baoguodanhao;
    }

    public void setBaoguodanhao(String baoguodanhao) {
        this.baoguodanhao = baoguodanhao == null ? null : baoguodanhao.trim();
    }

    public String getKehudingdanhao() {
        return kehudingdanhao;
    }

    public void setKehudingdanhao(String kehudingdanhao) {
        this.kehudingdanhao = kehudingdanhao == null ? null : kehudingdanhao.trim();
    }

    public String getWaibudingdanhao() {
        return waibudingdanhao;
    }

    public void setWaibudingdanhao(String waibudingdanhao) {
        this.waibudingdanhao = waibudingdanhao == null ? null : waibudingdanhao.trim();
    }

    public String getKehubianhao() {
        return kehubianhao;
    }

    public void setKehubianhao(String kehubianhao) {
        this.kehubianhao = kehubianhao == null ? null : kehubianhao.trim();
    }

    public String getNeibuhuohao() {
        return neibuhuohao;
    }

    public void setNeibuhuohao(String neibuhuohao) {
        this.neibuhuohao = neibuhuohao == null ? null : neibuhuohao.trim();
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

    public String getCangku() {
        return cangku;
    }

    public void setCangku(String cangku) {
        this.cangku = cangku == null ? null : cangku.trim();
    }

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei == null ? null : cangwei.trim();
    }

    public String getChukuzhuangtai() {
        return chukuzhuangtai;
    }

    public void setChukuzhuangtai(String chukuzhuangtai) {
        this.chukuzhuangtai = chukuzhuangtai == null ? null : chukuzhuangtai.trim();
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