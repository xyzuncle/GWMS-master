package com.huanqiuyuncang.util;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class PropUtil {
	private static Properties props;
	private static String defaultPropertiesFileName = "MyConfig.properties";
	private static final Logger logger = Logger.getLogger(PropUtil.class);
	private static long lastTime=new Date().getTime();
	public static String webPath=null;
	
	static {
		try {
			init(defaultPropertiesFileName);
		} catch (IOException e) {
			logger.warn("默认属性文件读取失败!");
		}
	}

	public static void init(String resourceName) throws IOException {
		props = PropertiesLoaderUtils.loadAllProperties(resourceName);
	}

	/**
	 * 默认返回String类型的属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		long nowTime=new Date().getTime();
		if(nowTime-lastTime>10000){
			try {
				lastTime=nowTime;
				init(defaultPropertiesFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props.getProperty(key);
	}

    public static void setProperty(String key,String value) {
        long nowTime=new Date().getTime();
        if(nowTime-lastTime>10000){
            try {
                lastTime=nowTime;
                init(defaultPropertiesFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         props.setProperty(key,value);
    }

	/**
	 * 指定获取Integer类型的属性值
	 * 
	 * @param key
	 * @return
	 */
	public static Integer getIntegerProperty(String key) {
		long nowTime=new Date().getTime();
		if(nowTime-lastTime>10000){
			try {
				lastTime=nowTime;
				init(defaultPropertiesFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Integer i;
		try{
			i= Integer.parseInt(getProperty(key));
		}catch(NumberFormatException e){
			logger.warn("默认属性文件中 存在的属性值不能解析为整数，将会返回null！");
			return null;
		}
		return i;
	}
	/**
	 * 从指定的属性文件中读取属性值
	 * @param fileName 指定属性文件名
	 * @param key
	 * @return
	 */
	public static String getProperty(String fileName, String key) {
		Properties p;
		try {
			p = PropertiesLoaderUtils.loadAllProperties(fileName);
			String value = p.getProperty(key);
			if (StringUtils.hasText(value)) {
				return value;
			} else {
				logger.warn("指定属性文件中不存在的属性！");
				return null;
			}
		} catch (IOException e) {
			logger.warn("指定属性文件读取失败！");
			return null;
		}
	}
	/**
	 * 从指定的属性文件中读取Integer类型属性值
	 * @param fileName 指定属性文件名
	 * @param key
	 * @return
	 */
	public static Integer getIntegerProperty(String fileName, String key) {
		String value = getProperty(fileName, key);
		return Integer.parseInt(value);
	}
}

