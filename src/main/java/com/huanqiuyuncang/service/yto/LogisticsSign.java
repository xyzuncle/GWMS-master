package com.huanqiuyuncang.service.yto;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * Created by xyz on 2017/4/29
 * 走件签名类：
 * 作用：根据圆通走件签规则进行签名加密
 */
public class LogisticsSign {

    /**
     * 得到未加密的字符串
     * @param app_key
     * @param format
     * @param method
     * @param timestamp
     * @param user_id
     * @param version
     * @return
     */
    public static String UnSignString(String app_key,String format,String method,
                                      String timestamp,String user_id,String version,String password){
        System.out.println("未签名的串==="+password+"app_key"+app_key+"format"+format+"method"+method+"timestamp"+timestamp+"user_id"+user_id+"v"+version);
        return password+"app_key"+app_key+"format"+format+"method"+method+"timestamp"+timestamp+"user_id"+user_id+"v"+version;
    }

    /**
     * 对需要被加密的串，进行32位加密
     * @param encryptData 被加密内容的串
     * @return
     */
    public static String Sign(String encryptData){
        String result = "";
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(encryptData.getBytes("UTF-8"));
            byte[] digestData = messagedigest.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < digestData.length; offset++) {
                i = digestData[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            //最后转成大写
            if(StringUtils.isNotBlank(result)){
                result=result.toUpperCase();
            }

        } catch (Exception e) {}
        System.out.println("得到的结果 \t\t :"+result);
        return result;
    }

    //发送走件接口报文，得到圆通返回结果
    public static String sendContent(String apiUrl, String data){
        OutputStreamWriter outputStreamWriter=null;
        InputStream inputStream = null;
        String responseString = "";
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
        return responseString;
    }

    public static void main(String[] args) {
        String str = "123456app_keyABCDEFformatXMLmethodyto.Marketing.WaybillTracetimestamp2016-6-1 13:14:35user_idyto_userv1.01";
        LogisticsSign.Sign(str);
    }
}
