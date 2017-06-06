package com.huanqiuyuncang.entity.checktable;

import java.util.List;

public class CheckTableEntity {
    private String checktableid;

    private String chcektablename;

    private String checkfield;

    private Integer ordernum;

    private String parentid;

    private String bezhu;

    private String target;
    private CheckTableEntity dict;
    private List<CheckTableEntity> subDict;
    private boolean hasDict = false;
    private String treeurl;

    public CheckTableEntity(String checktableid, String chcektablename, String checkfield, Integer ordernum, String parentid, String bezhu) {
        this.checktableid = checktableid;
        this.chcektablename = chcektablename;
        this.checkfield = checkfield;
        this.ordernum = ordernum;
        this.parentid = parentid;
        this.bezhu = bezhu;
    }

    public CheckTableEntity() {
        super();
    }

    public String getChecktableid() {
        return checktableid;
    }

    public void setChecktableid(String checktableid) {
        this.checktableid = checktableid == null ? null : checktableid.trim();
    }

    public String getChcektablename() {
        return chcektablename;
    }

    public void setChcektablename(String chcektablename) {
        this.chcektablename = chcektablename == null ? null : chcektablename.trim();
    }

    public String getCheckfield() {
        return checkfield;
    }

    public void setCheckfield(String checkfield) {
        this.checkfield = checkfield == null ? null : checkfield.trim();
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getBezhu() {
        return bezhu;
    }

    public void setBezhu(String bezhu) {
        this.bezhu = bezhu == null ? null : bezhu.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public CheckTableEntity getDict() {
        return dict;
    }

    public void setDict(CheckTableEntity dict) {
        this.dict = dict;
    }

    public List<CheckTableEntity> getSubDict() {
        return subDict;
    }

    public void setSubDict(List<CheckTableEntity> subDict) {
        this.subDict = subDict;
    }

    public boolean isHasDict() {
        return hasDict;
    }

    public void setHasDict(boolean hasDict) {
        this.hasDict = hasDict;
    }

    public String getTreeurl() {
        return treeurl;
    }

    public void setTreeurl(String treeurl) {
        this.treeurl = treeurl;
    }
}