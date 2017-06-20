package com.huanqiuyuncang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzf on 2017/5/8.
 * 生成订单号和包裹单号
 */
public class OrderUtil {

    public static  String getOrderNum(String customernum) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYMMdd");
        String formatDate = format.format(date);
        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("orderserialnumber"));
        PropUtil.writeProperties("orderserialnumber",serialnumber+1+"");
        String serialnumberStr = String.format("%0" + 5 + "d", serialnumber);
        return "O"+ customernum +formatDate+serialnumberStr;
    }

    public static  String getPackageNum(String customernum) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYMMdd");
        String formatDate = format.format(date);
        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("packageserialnumber"));
        PropUtil.writeProperties("packageserialnumber",serialnumber+1+"");
        String serialnumberStr = String.format("%0" + 5 + "d", serialnumber);
        return "P"+ customernum +formatDate+serialnumberStr;
    }

    public static  String getCaiGouNum(String gongyingshangnum) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYMMdd");
        String formatDate = format.format(date);
        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("caigouserialnumber"));
        PropUtil.writeProperties("caigouserialnumber",serialnumber+1+"");
        String serialnumberStr = String.format("%0" + 5 + "d", serialnumber);
        return "B"+ gongyingshangnum +formatDate+serialnumberStr;
    }
}
