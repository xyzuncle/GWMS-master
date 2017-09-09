package com.huanqiuyuncang.entity.order;

/**
 * Created by lzf on 2017/8/26.
 */
public class PackageInfoDTO {

    private String id;

    private String innerorderid;

    private String packagenum;

    private String productcount;

    private String packagevalue;

    public PackageInfoDTO() {
    }

    public PackageInfoDTO(String id, String innerorderid, String packagenum, String productcount, String packagevalue) {
        this.id = id;
        this.innerorderid = innerorderid;
        this.packagenum = packagenum;
        this.productcount = productcount;
        this.packagevalue = packagevalue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInnerorderid() {
        return innerorderid;
    }

    public void setInnerorderid(String innerorderid) {
        this.innerorderid = innerorderid;
    }

    public String getPackagenum() {
        return packagenum;
    }

    public void setPackagenum(String packagenum) {
        this.packagenum = packagenum;
    }

    public String getProductcount() {
        return productcount;
    }

    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }

    public String getPackagevalue() {
        return packagevalue;
    }

    public void setPackagevalue(String packagevalue) {
        this.packagevalue = packagevalue;
    }
}
