package com.huanqiuyuncang.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * Created by lzf on 2017/4/2.
 */
public class BeanMapUtil {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanUtils.populate(obj, map);

        return obj;
    }

}
