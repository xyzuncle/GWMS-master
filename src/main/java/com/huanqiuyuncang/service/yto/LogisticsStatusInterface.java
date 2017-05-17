package com.huanqiuyuncang.service.yto;

import com.huanqiuyuncang.util.DateUtil;


import java.net.URLEncoder;

/**
 * Created by xyz on 2017/4/13.
 * 物流状态订单接口
 *  接口描述:
 *  本接口是客户开发，用来接收圆通推送的订单状态。
 */
public class LogisticsStatusInterface {
    private static String clientId = "K11107565"; //
    private static String partnerId = "X17kf3ct";//
    private static String url = "http://ytohk.com/api/l/yuantong/";// 走单请求的url
    private static String timestamp = DateUtil.getTime();//时间戳
    public  void testSign(){

        String xmlStr = "";
        StringBuilder sb = new StringBuilder();
        sb.append("<UpdateInfo>");
        sb.append("<logisticProviderID>YTO</logisticProviderID>"); //物流公司ID（YTO）
        sb.append("<clientID>K11107565</clientID>");//	vip客户标识(客户编号)
        sb.append("<txLogisticID>LP05082300225709000</txLogisticID>"); //	物流号
        sb.append("<infoType>STATUS</infoType>");//通知类型STATUS：物流状态
        /**
         * ACCEPT 接单 UNACCEPT 不接单  GOT 已收件 NOT_SEND 揽收失败
         ARRIVAL 已收入 DEPARTURE 已发出 PACKAGE 已打包 UNPACK 已拆包
         SENT_SCAN 派件 SIGNED 签收成功 FAILED 签收失败
         */
        sb.append("<infoContent>ACCEPT</infoContent>");
        sb.append("<weight>0.0</weight>"); //揽收重量
        sb.append("<acceptTime>2017-05-1 14:28:31.0 CST");
        //sb.append(timestamp);
        sb.append("</acceptTime>"); //事件发生时间
        sb.append("<remark>接单成功</remark>"); //备注或失败原因（值为中文原因或备注）
        sb.append("</UpdateInfo>");
        xmlStr= sb.toString();
        String data_digest=LogisticsStatusSign.sign(partnerId,xmlStr);
        String queryString="";
        try {
            queryString = "logistics_interface=" + URLEncoder.encode(xmlStr, "UTF-8")
                    + "&data_digest=" + URLEncoder.encode(data_digest, "UTF-8")
                    +"&type=online"
                    +"&clientId=" + URLEncoder.encode(clientId, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogisticsStatusSign.sendContent(url,queryString);



    }
}
