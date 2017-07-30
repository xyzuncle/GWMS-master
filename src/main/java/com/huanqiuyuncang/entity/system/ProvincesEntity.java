package com.huanqiuyuncang.entity.system;

public class ProvincesEntity {
    private Integer id;

    private String provinceid;

    private String province;

    private String countryid;

    public ProvincesEntity(Integer id, String provinceid, String province, String countryid) {
        this.id = id;
        this.provinceid = provinceid;
        this.province = province;
        this.countryid = countryid;
    }

    public ProvincesEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid == null ? null : provinceid.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid == null ? null : countryid.trim();
    }
}