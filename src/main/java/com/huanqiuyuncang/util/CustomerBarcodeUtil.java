package com.huanqiuyuncang.util;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.*;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.xml.sax.SAXException;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by xyz on 2017/5/10.
 */
public class CustomerBarcodeUtil {
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }

    /**
     * 生成到流
     *  @param msg
     * @param ous
     */
    public static OutputStream generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return ous;
        }

      /*  Code128Bean bean = new Code128Bean();
       // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(2.0f / dpi);

        // 配置对象
        bean.setModuleWidth(0.21);
        bean.setHeight(15);
        bean.doQuietZone(true);
        bean.setQuietZone(2);//两边空白区
        //human-readable
        bean.setFontName("Helvetica");
        bean.setFontSize(3);
        bean.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
*/
        String format = "image/png";
        //通过获取配置文件的形式来得到条形码
        try {
            BarcodeUtil util = BarcodeUtil.getInstance();
            BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("code128"));

            int resolution = 200;
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    ous, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            gen.generateBarcode(canvas, msg);
            canvas.finish();
            return ous;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            e.printStackTrace();
        }
        return ous;
    }


    private static Configuration buildCfg(String type) {
        DefaultConfiguration cfg = new DefaultConfiguration("barcode");

        //Bar code type
        DefaultConfiguration child = new DefaultConfiguration(type);
        cfg.addChild(child);

        //增加宽度和高度属性
        DefaultConfiguration height = new DefaultConfiguration("height");
        height.setValue("15mm");
        child.addChild(height);

        //增加宽度和高度属性
        DefaultConfiguration width = new DefaultConfiguration("module-width");
        width.setValue("0.21mm");
        child.addChild(width);

        //新增code128的类型
        DefaultConfiguration codesets = new DefaultConfiguration("codesets");
        codesets.setValue("C");
        child.addChild(codesets);



        //Human readable text position
        DefaultConfiguration attr = new DefaultConfiguration("human-readable");
        DefaultConfiguration subAttr = new DefaultConfiguration("placement");
        subAttr.setValue("bottom");
        attr.addChild(subAttr);

        child.addChild(attr);
        return cfg;
    }


    private static Configuration buildCfg1(String type) {
        DefaultConfigurationBuilder  builder = new DefaultConfigurationBuilder();
        Configuration cfg = null;
        try {
            cfg = builder.buildFromFile("/barcode.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }


        return cfg;
    }

    public static void main(String[] args) {
        String msg = "8806390506007";
        String path = "barcode.png";
        generate(msg);
    }
}
