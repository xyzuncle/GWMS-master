package com.huanqiuyuncang.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;

/**
 * Created by xyz on 2016/3/14
 *
 * 次方法是用于设置把对象转换成JSON格式时，对日期的处理的配置
 */
public class DateJsonConfig {
    public static JsonConfig getJsonConfig(){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
            private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                return  value == null ?"" : sd.format(value);
            }
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {
                return null;
            }
        });
        return jsonConfig;
    }
}
