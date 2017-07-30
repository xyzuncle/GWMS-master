package com.huanqiuyuncang.entity.system;

public class AreasEntity {
    private Integer id;

    private String areaid;

    private String area;

    private String cityid;

    public AreasEntity(Integer id, String areaid, String area, String cityid) {
        this.id = id;
        this.areaid = areaid;
        this.area = area;
        this.cityid = cityid;
    }

    public AreasEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }
}