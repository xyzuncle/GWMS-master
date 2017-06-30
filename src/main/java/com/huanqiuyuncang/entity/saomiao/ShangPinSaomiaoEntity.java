package com.huanqiuyuncang.entity.saomiao;

import java.util.Date;

public class ShangPinSaomiaoEntity {
    private String id;

    private String shangpinid;

    private Integer saomiaoshuliang;

    private String kuozhan1;

    private String kuozhan2;

    private String kuozhan3;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public ShangPinSaomiaoEntity(String id, String shangpinid, Integer saomiaoshuliang, String kuozhan1, String kuozhan2, String kuozhan3, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.id = id;
        this.shangpinid = shangpinid;
        this.saomiaoshuliang = saomiaoshuliang;
        this.kuozhan1 = kuozhan1;
        this.kuozhan2 = kuozhan2;
        this.kuozhan3 = kuozhan3;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public ShangPinSaomiaoEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShangpinid() {
        return shangpinid;
    }

    public void setShangpinid(String shangpinid) {
        this.shangpinid = shangpinid == null ? null : shangpinid.trim();
    }

    public Integer getSaomiaoshuliang() {
        return saomiaoshuliang;
    }

    public void setSaomiaoshuliang(Integer saomiaoshuliang) {
        this.saomiaoshuliang = saomiaoshuliang;
    }

    public String getKuozhan1() {
        return kuozhan1;
    }

    public void setKuozhan1(String kuozhan1) {
        this.kuozhan1 = kuozhan1 == null ? null : kuozhan1.trim();
    }

    public String getKuozhan2() {
        return kuozhan2;
    }

    public void setKuozhan2(String kuozhan2) {
        this.kuozhan2 = kuozhan2 == null ? null : kuozhan2.trim();
    }

    public String getKuozhan3() {
        return kuozhan3;
    }

    public void setKuozhan3(String kuozhan3) {
        this.kuozhan3 = kuozhan3 == null ? null : kuozhan3.trim();
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