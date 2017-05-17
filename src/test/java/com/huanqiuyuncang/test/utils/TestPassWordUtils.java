package com.huanqiuyuncang.test.utils;


import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by lzf on 2017/4/2.
 */
public class TestPassWordUtils {
     public static void main(String[] args) {
        String passwd = new SimpleHash("SHA-1", "admin", "123456").toString();	//密码加密
        System.out.println(passwd);
    }
}
