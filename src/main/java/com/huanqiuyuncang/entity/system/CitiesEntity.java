package com.huanqiuyuncang.entity.system;

public class CitiesEntity {
    private Integer id;

    private String cityid;

    private String city;

    private String provinceid;

    public CitiesEntity(Integer id, String cityid, String city, String provinceid) {
        this.id = id;
        this.cityid = cityid;
        this.city = city;
        this.provinceid = provinceid;
    }

    public CitiesEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }
}