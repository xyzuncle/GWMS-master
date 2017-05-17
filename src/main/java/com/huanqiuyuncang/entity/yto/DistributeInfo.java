package com.huanqiuyuncang.entity.yto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by xyz on 2017/5/16.
 * XML中的子属性
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "distributeInfo")
@XmlType(propOrder = {
        "shortAddress",
        "consigneeBranchCode",
        "packageCenterCode",
        "packageCenterName"
})
public class DistributeInfo implements Serializable {


    public DistributeInfo() {

    }
    public DistributeInfo(String packageCenterName, String shortAddress, String consigneeBranchCode, String packageCenterCode) {
        this.packageCenterName = packageCenterName;
        this.shortAddress = shortAddress;
        this.consigneeBranchCode = consigneeBranchCode;
        this.packageCenterCode = packageCenterCode;
    }
    private String packageCenterName;
    private String shortAddress;
    private String consigneeBranchCode;
    private String packageCenterCode;




    public String getPackageCenterCode() {
        return packageCenterCode;
    }

    public void setPackageCenterCode(String packageCenterCode) {
        this.packageCenterCode = packageCenterCode;
    }


    public String getConsigneeBranchCode() {
        return consigneeBranchCode;
    }

    public void setConsigneeBranchCode(String consigneeBranchCode) {
        this.consigneeBranchCode = consigneeBranchCode;
    }


    public String getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(String shortAddress) {
        this.shortAddress = shortAddress;
    }

    public String getPackageCenterName() {
        return packageCenterName;
    }

    public void setPackageCenterName(String packageCenterName) {
        this.packageCenterName = packageCenterName;
    }
}
