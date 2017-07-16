package com.huanqiuyuncang.test.utils;

import com.huanqiuyuncang.entity.warehouse.YiKuSaomiaoDTO;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by lzf on 2017/4/18.
 */
public class TestMoney {

    public static void main(String[] args) {
      /*  BigDecimal bigDecimal = new BigDecimal("120.5");
        BigDecimal bigDecima2 = new BigDecimal("0.4");
        System.out.println(bigDecimal.multiply(bigDecima2).toString());

        JedisPool pool = new JedisPool(null,"qq",22,22,"111");*/
        /*List<YiKuSaomiaoDTO> list = new ArrayList<>();
        list.add(new YiKuSaomiaoDTO("aaa","bbb","333"));
        list.add(new YiKuSaomiaoDTO("aaa","bbb","444"));
        list.add(new YiKuSaomiaoDTO("aaa","bbb","555"));
        Stream<Map<String, List<YiKuSaomiaoDTO>>> mapStream = list.stream().map(yiKuSaomiaoDTO -> {
            Map<String, List<YiKuSaomiaoDTO>> map = new HashMap<String, List<YiKuSaomiaoDTO>>();
            String kuwei = yiKuSaomiaoDTO.getKuwei();
            if(map.keySet().contains(kuwei)){
                List<YiKuSaomiaoDTO> list1 = map.get(kuwei);
                list1.add(yiKuSaomiaoDTO);
            }else{
               List<YiKuSaomiaoDTO> list1 =  new ArrayList<>();
                list1.add(yiKuSaomiaoDTO);
                map.put(kuwei,list1);
            }
            return map;
        });
        */

    }
}
