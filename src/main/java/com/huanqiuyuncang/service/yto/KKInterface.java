package com.huanqiuyuncang.service.yto;

import com.huanqiuyuncang.util.DateUtil;


import java.net.URLEncoder;

/**
 * Created by xyz on 2017/4/30.
 * 国外走件信息推送到kk
 * 接口描述：
 * 客户端发起请求后，KK系统接收走件信息并推送给圆通核心系统，之后客户可以在圆通官网（避免通过其他渠道查看）查看到推送的走件信息。
 */
public class KKInterface {
    private static String clientId = "STD";
    private static String partnered = "STD";
    private static String url = "http://180.153.190.90/globalunion/outcall/trackinginfo"; //KK测试地址
    private static String desKey = "F9390A4F-89BD-49CD-9983-D7741C6468E8"; //这个秘钥是最新的，跟官网的不一致
    private static String timestamp = DateUtil.getTime();//时间戳


    /**
     * 该方法用于请求海外订单的的具体数据请求
     */
    public static void kkQuery(){
        String xmlStr = "";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        sb.append("<Message>");//报文根节点
        sb.append("<Header>");//报文Header节点
        sb.append("<SeqNo>1111111111</SeqNo>");//客户端的消息流水号
        sb.append("<SendTimeStamp>Tue May 02 2017 16:14:45 GMT 0800 (中国标准时间)");//客户端消息发送时间戳
        //sb.append(timestamp);
        sb.append("</SendTimeStamp>");
        sb.append("</Header>");

        //订单开始
        sb.append("<RequestTrackingInfo>");
        sb.append("<RecordCount>1</RecordCount>"); //信息记录数
        sb.append("<SetInfo>");
        sb.append("<ClientID>STD</ClientID>");//上游公司ID
        sb.append("<LogisticProviderID>YTO</LogisticProviderID>");//下游公司ID
        sb.append("<TrackingInfoProvider>STD</TrackingInfoProvider>");//走件信息提供方的ID
        sb.append("<BillID>3284649231</BillID>");//上游公司运单号 ？
        sb.append("<OrderID>20126754501</OrderID>");//下游公司运单号 ？
        sb.append("<DepName>安徽省滁州市</DepName>");//事件发生地
        sb.append("<CreateDateTime>2013-10-20 18:47:36</CreateDateTime>");//事件发生事件 ？
        /**
         * P767 海外派件中P769 海外签收P760 海外收入P762 海外发运P764
         * 清关中P765 海外清关完成P771 航班已起飞P773 航班抵达保税区P774 进境清关
         */
        sb.append("<StatusCode>P767</StatusCode>");
        sb.append("<StatusDesc>海外派件中</StatusDesc>");
        sb.append("<FacilityType>1</FacilityType>");// 	站点类型：1.网点；2.转运中心
        sb.append("<FacilityName>广东</FacilityName>");//站点名字
        sb.append("<Contacter></Contacter>"); //联系人
        sb.append("<ContactInfo></ContactInfo>");//联系方式；固话或者手机号码
        sb.append("<Remark></Remark>"); //备注
        sb.append("</SetInfo>");
        sb.append("</RequestTrackingInfo>");
        sb.append("</Message>");
        xmlStr = sb.toString();

        System.out.println("加密前报文：\t" + xmlStr);

        String logistics_interface = KKSign.encryptSendData(desKey, xmlStr);
        System.out.println("des：\t\t" + logistics_interface);
        String data_digest = KKSign.signuatureSendData(partnered, logistics_interface);
        System.out.println("data_digest：\t" + data_digest);


        String queryString = "";
        try {
            System.out.println("data_digest_ecode：\t" + URLEncoder.encode(logistics_interface, "UTF-8"));
            queryString = "logistics_interface=" + URLEncoder.encode(logistics_interface, "UTF-8")
                    + "&data_digest=" +  URLEncoder.encode(data_digest, "UTF-8")
                    + "&clientID=" + URLEncoder.encode(clientId, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发送报文：\t" + queryString);

        SendContent.sendAndGetStr(url, queryString);
    }
}
