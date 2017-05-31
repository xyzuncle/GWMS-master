package com.huanqiuyuncang.service.yto;


import com.huanqiuyuncang.entity.yto.ExpressBill;
import com.huanqiuyuncang.util.XMLUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by xyz on 2017/4/13.
 * 圆通拉取面单的服务接口
 */


public class ExpressBillService {
    private static String clientId = "K11107565";
    private static String partnered = "X17kf3ct";
    private static String url = "http://service.yto56.net.cn/CommonOrderModeBPlusServlet.action";
   // private static String desKey = "INTERFACESTANDARDENCRYPTKEY2014";


    public static ExpressBill mites(String innerpackagenum){
        String xmlStr = "";
        //下单示例报文
        StringBuilder sb = new StringBuilder();
        //这是请求内容的主体
        sb.append("<RequestOrder>");//报文RequestOrder节点
        sb.append("<clientID>K11107565</clientID>");//商家代码（必须与customerId一致）
        sb.append("<logisticProviderID>YTO</logisticProviderID>");//物流公司ID
        sb.append("<customerId >K11107565</customerId>");// 商家代码 (由商家设置， 必须与clientID一致)
        sb.append("<txLogisticID>"+innerpackagenum+"</txLogisticID>");//物流订单号
        sb.append("<tradeNo>1</tradeNo>");//业务交易号 可选
        sb.append("<totalServiceFee>0.0</totalServiceFee>");//保值金额=insuranceValue*货品数量(默认为0.0）
        sb.append("<codSplitFee>0.0</codSplitFee>");//物流公司分润，没有使用;
        sb.append("<orderType>1</orderType>");//订单类型(0-COD,1-普通订单,2-便携式订单3-退货单)
        sb.append("<serviceType>1</serviceType>");//服务类型(1-上门揽收, 2-次日达 4-次晨达 8-当日达,0-自己联系)。默认为0
        sb.append("<flag>1</flag>");
        //发件人
        sb.append("<sender>");
        sb.append("<name>寄件人姓名</name>");
        sb.append("<postCode>526238</postCode>");
        sb.append("<phone>021-12345678</phone>");
        sb.append("<mobile>18112345678</mobile>");
        sb.append("<prov>上海</prov>");
        sb.append("<city>上海,青浦区</city>");
        sb.append("<address>华徐公路3029弄28号</address>");
        sb.append("</sender>");
        //收件人
        sb.append("<receiver>");
        sb.append("<name>收件人姓名</name>");
        sb.append("<postCode>0</postCode>");
        sb.append("<phone>0</phone>");
        sb.append("<mobile>1808966676</mobile>");
        sb.append("<prov>上海</prov>");
        sb.append("<city>上海市,青浦区</city>");
        sb.append("<address>华徐公路3029弄28号</address>");
        sb.append("</receiver>");
        sb.append("<sendStartTime>2015-12-12 12:12:12</sendStartTime>");//物流公司上门取货时间段，
                                                                        // 通过”yyyy-MM-dd HH:mm:ss”格式化，本文中所有时间格式相同。
        sb.append("<sendEndTime>2015-12-12 12:12:12</sendEndTime>"); //取货结束时间;
        sb.append("<goodsValue>1</goodsValue>");//商品金额，包括优惠和运费，但无服务费 可选
        //商品
        sb.append("<items>");
        sb.append("<item>");
        sb.append("<itemName>商品</itemName>"); //商品名称
        sb.append("<number>2</number>"); //商品数量
        sb.append("<itemValue>0</itemValue>"); //商品单价
        sb.append("</item>");
        sb.append("</items>");

        sb.append("<insuranceValue>1</insuranceValue>");//保值金额 （保价金额为货品价值（大于等于100少于3w），默认为0.0）可选
        sb.append("<special>1</special>");//special 	商品类型（保留字段，暂时不用
        sb.append("<remark>1</remark>");//remark 备注
        sb.append("</RequestOrder>");

       /* sb.append("<logisticsCode></logisticsCode>");//海关备案物流企业代码
        sb.append("<logisticsName></logisticsName>");//海关备案物流企业名称
        sb.append("<logisticsCodeCiq></logisticsCodeCiq>");//国检备案物流企业代码
        sb.append("<logisticsNameCiq></logisticsNameCiq>");//国检备案物流企业名称
        sb.append("<totalLogisticsNo></totalLogisticsNo>");//总运单号
        sb.append("<subLogisticsNo></subLogisticsNo>");//分运单号
        sb.append("<logisticsNo></logisticsNo>");//物流企业运单号
        sb.append("<orderNo></orderNo>");//订单编号
        sb.append("<platformCode></platformCode>");//海关备案电商平台代码
        sb.append("<platformName></platformName>");//海关备案电商平台名称
        sb.append("<platformCodeCiq></platformCodeCiq>");//国检备案电商平台代码
        sb.append("<platformNameCiq></platformNameCiq>");//国检备案电商平台名称
        sb.append("<trackingNo></trackingNo>");//航班号
        sb.append("<trackingStatus></trackingStatus>");//航班状态
        sb.append("<shipping></shipping>");//发货人名称
        sb.append("<shippingAddress></shippingAddress>");//发货人地址
        sb.append("<shippingTelephone></shippingTelephone>");//发货人电话
        sb.append("<ShippongZipCode></ShippongZipCode>");//发货人邮编
        sb.append("<shippingCountryCiq></shippingCountryCiq>");//发货人所在国（检）
        sb.append("<shippingCountryCus></shippingCountryCus>");//发货人所在国（关）
        sb.append("<consignee></consignee>");//收货人名称
        sb.append("<consigneeAddress></consigneeAddress>");//收货人地址
        sb.append("<consigneeTelephone></consigneeTelephone>");//收货人电话
        sb.append("<consigneeZipCode></consigneeZipCode>");//收货人邮编
        sb.append("<consigneeCountryCiq></consigneeCountryCiq>");//收货认所在国（检）
        sb.append("<consigneeCountryCus></consigneeCountryCus>");//收货人所在国（关）
        sb.append("<idType></idType>");//证件类型
        sb.append("<idNumber></idNumber>");//证件号码
        sb.append("<declarationDate></declarationDate>");//申报日期/进口日期
        sb.append("<internationalFreight></internationalFreight>");//跨境运费
        sb.append("<domesticFreight></domesticFreight>");//境内运费
        sb.append("<supportValue></supportValue>");//保价金额
        sb.append("<worth></worth>");//货品价值
        sb.append("<currCode></currCode>");//币制
        sb.append("<grossWeight></grossWeight>");//毛重
        sb.append("<quantity></quantity>");//数量
        sb.append("<packageTypeCiq></packageTypeCiq>");//包装种类（检）
        sb.append("<packageTypeCus></packageTypeCus>");//包装种类（关）
        sb.append("<packNum></packNum>");//件数
        sb.append("<netWeight></netWeight>");//净重
        sb.append("<goodsName></goodsName>");//商品名称
        sb.append("<deliveryMethod></deliveryMethod>");//交货方式
        sb.append("<transportationMethod></transportationMethod>");//运输方式（检）
        sb.append("<shipCode></shipCode>");//运输工具（检）
        sb.append("<shipName></shipName>");//运输工具名称
        sb.append("<destinationPort></destinationPort>");//装运港/指运港
        sb.append("<ieType></ieType>");//进出口标识
        sb.append("<stockFlag></stockFlag>");//集货/备货
        sb.append("<batchNumbers></batchNumbers>");//批次号
        sb.append("<tradeCountryCiq></tradeCountryCiq>");//贸易国别（检）
        sb.append("<tradeCountryCus></tradeCountryCus>");//贸易国别（关）
        sb.append("<agentCode></agentCode>");//海关备案代理企业代码
        sb.append("<agentName></agentName>");//海关备案代理企业名称
        sb.append("<agentCodeCiq></agentCodeCiq>");//国检备案代理企业代码
        sb.append("<agentNameCiq></agentNameCiq>");//国检备案代理企业名称
        sb.append("<billType></billType>");//总运单分运单标志
        sb.append("<modifyMark></modifyMark>");//操作类型
        sb.append("<customsField></customsField>");//场站代码
        sb.append("<note></note>");//备注
        sb.append("<reserve1></reserve1>");//备用字段1
        sb.append("<reserve2></reserve2>");//备用字段2
        sb.append("<reserve3></reserve3>");//备用字段3
        sb.append("<reserve4></reserve4>");//备用字段4
        sb.append("<reserve5></reserve5>");//备用字段5
        sb.append("</RequestOrder>");
        sb.append("</Message>");*/
        xmlStr = sb.toString();

        System.out.println("加密前报文：\t" + xmlStr);

        //String logistics_interface = SendContent.encryptSendData(desKey, xmlStr);
       // System.out.println("des：\t\t" + logistics_interface);
        String data_digest = SendContent.signuatureSendData(partnered,xmlStr);
        System.out.println("data_digest：\t" + data_digest);

        String queryString = "";
        try {
            System.out.println("data_digest_encode：\t" + URLEncoder.encode(data_digest, "UTF-8"));
            queryString = "logistics_interface=" + URLEncoder.encode(xmlStr, "UTF-8")
                    + "&data_digest=" + URLEncoder.encode(data_digest, "UTF-8")
                    + "&clientId=" + URLEncoder.encode(clientId, "UTF-8")
                    + "&type="+ URLEncoder.encode("offline", "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发送报文：\t" + queryString);

        String result = SendContent.sendAndGetStr(url, queryString);

        ExpressBill bill = (ExpressBill)XMLUtils.convertXmlStrToObject(ExpressBill.class,result);
        return bill;
    }


    public static void main(String[] args) throws IOException {
        String param = "<Response><logisticProviderID>YTO</logisticProviderID><txLogisticID>809949845992</txLogisticID><clientID>K11107565</clientID><mailNo>809949847433</mailNo><distributeInfo><shortAddress>300-006-251</shortAddress><consigneeBranchCode>210185</consigneeBranchCode><packageCenterCode>210901</packageCenterCode><packageCenterName>上海转运中心</packageCenterName></distributeInfo><code>200</code><success>true</success></Response>";

        ExpressBill bill = (ExpressBill)XMLUtils.convertXmlStrToObject(ExpressBill.class,param);
        System.out.println(bill.getLogisticProviderID());
        System.out.println(bill.getDistributeInfo().get(0).getShortAddress());

    }
}
