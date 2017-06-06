package com.huanqiuyuncang.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by lzf on 2017/4/2.
 */
public class BeanMapUtil {
    /**
     * @desc 将map集合转换成Entity实体对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanUtils.populate(obj, map);

        return obj;
    }

    /**
     * @desc 添加实体的创建人信息
     * @param pd
     * @return
     */
    public static void setCreateUserInfo(PageData pd) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        pd.put("createuser", username);
        pd.put("createtime", date);
    }
    /**
     * @desc 添加实体的修改人信息
     * @param pd
     * @return
     */
    public static void setUpdateUserInfo(PageData pd) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        pd.put("updateuser", username);
        pd.put("updatetime", date);
    }
}
