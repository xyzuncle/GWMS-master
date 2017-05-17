package com.huanqiuyuncang.service.yto;

import com.huanqiuyuncang.entity.yto.TransferCenter;
import com.huanqiuyuncang.util.DateUtil;
import org.codehaus.jackson.map.ObjectMapper;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xyz on 2017/4/13.
 * 走件流程查询接口和目的中心信息接口
 * 走件流程接口描述:
 * 根据圆通物流运单号查询已有的快件物流信息，
 * 在物流信息里面会包含物流状态，
 * 如 【客户 **** 已签收】，物流信息保持与官网一致。
 *
 * 目的中心接口：
 * 描述：
 * 根据省、市、区，查询目的中心条码，目的条码可打印在面单上面。
 */
public class LogisticsInterface {
    private static String Secret_Key = "P9PmfC"; //秘钥
    private static String app_key = "Ei2v8k";//分配给用户的app_key
    private static String user_id = "LTHKHK";//客户标识
    private static String format = "JSON";// 返回信息是JSON返回
    private static String method = "yto.Marketing.WaybillTrace";//走单请求方法名称
    private static String timestamp = DateUtil.getTime();//时间戳
    private static String v = "1.01";// API协议的版本号
    private static String url = "http://MarketingInterface.yto.net.cn";// 走单请求的url

    //目的中心信息参数
    private static String centerMethod = "yto.BaseData.TransferInfo";//目的中心方法
    private static String centerUrl = "http://BaseDataManagent.yto.net.cn";//目的中心url


    /**
     * 走件接口的查询和回调函数和返回结果
     */
    public static void logisticsQuery(){
        String unSingStr = LogisticsSign.UnSignString(app_key,format,method,timestamp,user_id,v,Secret_Key);
        String singStr=LogisticsSign.Sign(unSingStr);
        String queryString="sign="+singStr
                            +"&app_key="+app_key
                            +"&format="+format
                            +"&method="+method
                            +"&timestamp="+timestamp
                            +"&user_id="+user_id
                            +"&v="+v
                            +"&param=[{\"Number\":\"1111111111\"}]";
        LogisticsSign.sendContent(url,queryString);
    }

    /**
     *  目的地接口,用户输入参数获取地址对应的编码
     *  @param province 省份
     *  @param city 城市
     *  @param districts  区县
     *  @return  把JSON转换成目的地对象
     */
    public static TransferCenter destination(String province, String city, String districts){
        String timestamp1 = DateUtil.getTime();
        String unSingStr = LogisticsSign.UnSignString(app_key,format,centerMethod,timestamp1,user_id,v,Secret_Key);
        String singStr=LogisticsSign.Sign(unSingStr);
        String queryString="sign="+singStr
                +"&app_key="+app_key
                +"&format="+format
                +"&method="+centerMethod  //注意这个方法不一样
                +"&timestamp="+timestamp1
                +"&user_id="+user_id
                +"&v="+v
                +"&param=[{\"Province\":\""+province+"\",\"City\":\""+city+"\",\"District\":\""+districts+"\"}]";
        String resultStr = LogisticsSign.sendContent(centerUrl,queryString);

        ObjectMapper mapper = new ObjectMapper();
        TransferCenter center = null;
        try {
             center =  mapper.readValue(resultStr,TransferCenter.class);
             System.out.println("center===="+center.TransferCenterCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return center;
    }

    public static void main(String[] args) {
        LogisticsInterface.destination("湖北","襄阳市","樊城区");
    }



}
