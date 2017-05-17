package com.huanqiuyuncang.service.yto;

import org.apache.commons.codec.binary.Base64;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * Created by xyz on 2017/4/30.
 * KK签名类，用于KK接口加密
 */
public class KKSign {
    //logistics_interface报文DES+Base64
    public static String encryptSendData(String key, String data){
        if(key == null || data == null){
            return data;
        }

        String encryptData = getEncString(key, data);
        System.out.println("DES加密算法==="+encryptData);
        encryptData = new String(Base64.encodeBase64(encryptData.getBytes()));

        return encryptData;
    }

    public static String getEncString(String publicKey, String data){
        return new Des(publicKey).getEncString(data);
    }

    //data_digest报文MD5
    public static String signuatureSendData(String companyCode, String encryptData){
        if (companyCode == null || encryptData == null){
            return encryptData;
        }
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update((companyCode + encryptData + companyCode).getBytes("UTF-8"));
            byte[] digestData = messagedigest.digest();
            String encodeData = new String(Base64.encodeBase64(digestData));
            return encodeData;
        } catch (Exception e) {}
        return encryptData;
    }

    //发送报文，打印返回信息
    public static void sendAndGetStr(String apiUrl, String data){
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            long a = System.currentTimeMillis();

            out.write(data);
            out.flush();
            out.close();

            String responseString = "";
            String strLine = "";
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((strLine = reader.readLine()) != null ){
                responseString += strLine + "\n";
            }

            in.close();

            long b = System.currentTimeMillis();

            long c = b - a;
            System.err.println("时间：\t\t" + c + "ms");

            System.err.println("返回信息：" + "\n" + responseString);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void testDe(){
        String key="INTERFACESTANDARDENCRYPTKEY2014";
        String data="123456";
        KKSign.encryptSendData(key,data);
    }
}
