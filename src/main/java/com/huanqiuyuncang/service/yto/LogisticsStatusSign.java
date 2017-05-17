package com.huanqiuyuncang.service.yto;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * Created by xyz on 2017/4/29
 * 走件签名类：
 * 作用：根据圆通走件签规则进行签名加密
 */
public class LogisticsStatusSign {

    /**
     *
     * @param partnerId  秘钥
     * @param encryptData  XML内容
     * @return
     */
    public static String sign(String partnerId, String encryptData){
        if (partnerId == null || encryptData == null){
            return encryptData;
        }
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update((encryptData+partnerId).getBytes("UTF-8"));
            byte[] digestData = messagedigest.digest();
            String encodeData = new String(Base64.encodeBase64(digestData));
            System.out.println("encodeData===="+encodeData);
            return encodeData;
        } catch (Exception e) {}
        return encryptData;
    }

    //发送走件接口报文，得到圆通返回结果
    public static void sendContent(String apiUrl, String data){
        OutputStreamWriter outputStreamWriter=null;
        InputStream inputStream = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded charset=UTF-8");
            connection.connect();

            outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            long a = System.currentTimeMillis();

            outputStreamWriter.write(data);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            String responseString = "";
            String strLine = "";
            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((strLine = reader.readLine()) != null ){
                responseString += strLine + "\n";
            }

            inputStream.close();

            long b = System.currentTimeMillis();

            long c = b - a;
            System.err.println("时间：\t\t" + c + "ms");

            System.err.println("返回信息：" + "\n" + responseString);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStreamWriter!=null){
                    outputStreamWriter.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String str = "<order></order>";
        try {
            //String estr = URLEncoder.encode(str,"utf-8");
            //System.out.println("estr \t: "+estr);
            LogisticsStatusSign.sign("123456",str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
