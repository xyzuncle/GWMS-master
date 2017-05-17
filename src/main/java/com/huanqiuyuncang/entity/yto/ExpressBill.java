package com.huanqiuyuncang.entity.yto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by xyz on 2017/5/16.
 * 面单返回的XML的转换对象
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
@XmlType(propOrder = {
        "logisticProviderID",
        "txLogisticID",
        "clientID",
        "mailNo",
        "distributeInfo",
        "code",
        "success"
})
public class ExpressBill {

    private String logisticProviderID;

    private String txLogisticID;

    private String mailNo;

    private String code;

    private String success;

    private List<DistributeInfo> distributeInfo;

    private String clientID;


    public ExpressBill() {
    }
    public ExpressBill(String success, String code, String mailNo, String txLogisticID, String logisticProviderID) {
        this.success = success;
        this.code = code;
        this.mailNo = mailNo;
        this.txLogisticID = txLogisticID;
        this.logisticProviderID = logisticProviderID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DistributeInfo> getDistributeInfo() {
        return distributeInfo;
    }

    public void setDistributeInfo(List<DistributeInfo> distributeInfo) {
        this.distributeInfo = distributeInfo;
    }
}
