package com.huanqiuyuncang.test.utils;

import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;

/**
 * Created by lzf on 2017/4/18.
 */
public class TestMoney {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("120.5");
        BigDecimal bigDecima2 = new BigDecimal("0.4");
        System.out.println(bigDecimal.multiply(bigDecima2).toString());

        JedisPool pool = new JedisPool(null,"qq",22,22,"111");
    }
}
