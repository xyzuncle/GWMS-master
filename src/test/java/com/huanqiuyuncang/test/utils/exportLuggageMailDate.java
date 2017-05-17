package com.huanqiuyuncang.test.utils;

import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.service.wms.luggagemail.LuggageMailInterface;
import com.huanqiuyuncang.service.wms.luggagemail.impl.LuggageMailService;
import com.huanqiuyuncang.util.ObjectExcelRead;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.PropUtil;
import com.huanqiuyuncang.util.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/4/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "parent",
        locations = {"classpath:spring/ApplicationContext-main.xml",
                "classpath:spring/ApplicationContext-dataSource.xml",
                "classpath:spring/ApplicationContext-shiro.xml",
                "classpath:spring/ApplicationContext-redis.xml"})
public class exportLuggageMailDate {
    @Autowired
    private LuggageMailInterface luggageMailService;
    @Test
    public void exportDate() {
        String filePath = "F:\\工作\\4.汇通国际";
        String fileName = "行邮税号.xls";
        List<PageData> listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 1, 0, 0);
        String userName = "admin";
        if(listPd != null && listPd.size()>0){
            listPd.forEach(pageData -> {
                LuggageMailEntity luggageMailEntity = new LuggageMailEntity();
                String var0 = pageData.getString("var0");
                String var1 =pageData.getString("var1");
                String var2 =pageData.getString("var2");
                String var3 =pageData.getString("var3");
                String var4 =pageData.getString("var4");
                String var5 =pageData.getString("var5");
                luggageMailEntity.setLuggagemailcode(var0);
                luggageMailEntity.setLuggagemailname(var1);
                luggageMailEntity.setUnit(var2);
                luggageMailEntity.setUnitcode(var3);
                luggageMailEntity.setDutiable(var4);
                luggageMailEntity.setRates(var5);
                luggageMailEntity.setCreateuser(userName);
                luggageMailEntity.setUpdateuser(userName);
                luggageMailEntity.setCreatetime(new Date());
                luggageMailEntity.setUpdatetime(new Date());
                luggageMailEntity.setLuggagemailid(UuidUtil.get32UUID());
                try {
                    luggageMailService.insertSelective(luggageMailEntity);
                }catch (Exception e){
                    System.out.println("保存失败，出现异常");
                }


            });
        }
    }
    @Test
    public void aaa() {
        Integer serialnumber = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber);
        PropUtil.writeProperties("serialnumber",serialnumber+1+"");
        Integer serialnumber1 = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber1);
        PropUtil.writeProperties("serialnumber",serialnumber1+1+"");
        Integer serialnumber2 = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber2);
        PropUtil.writeProperties("serialnumber",serialnumber2+1+"");
        Integer serialnumber3 = Integer.parseInt(PropUtil.getKeyValue("serialnumber"));
        System.out.println(serialnumber3);
    }

}
