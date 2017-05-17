package com.huanqiuyuncang.controller.wms.barcode;


import com.huanqiuyuncang.entity.yto.ExpressBill;
import com.huanqiuyuncang.entity.yto.TransferCenter;
import com.huanqiuyuncang.service.yto.ExpressBillService;
import com.huanqiuyuncang.service.yto.LogisticsInterface;
import com.huanqiuyuncang.util.CustomerBarcodeUtil;
import com.huanqiuyuncang.util.XMLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyz on 2017/5/10.
 */
@Controller
@RequestMapping("/barcode")
public class BarCodeController {

    @RequestMapping("/destination")
    @ResponseBody
    public void getDestinationCodeImg(HttpServletResponse responses,String provide,String city,String area){

        try {
            //根据接口参数获取具体的目的地地址
            TransferCenter center = LogisticsInterface.destination(provide,city,area);
            String msg1 = center.TransferCenterCode;
            OutputStream out = responses.getOutputStream();
            CustomerBarcodeUtil.generate(msg1,out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping("/bar")
    @ResponseBody
    public void getBarCodeImg(String msg,HttpServletResponse outputStream){
        try {
            OutputStream out = outputStream.getOutputStream();
            CustomerBarcodeUtil.generate(msg,out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/getBillInfo")
    public ModelAndView getBarCodeImgInfo(String msg){

        Map<String,Object> billMap = new HashMap<String,Object>();
        ModelAndView view = new ModelAndView();
        try {
            //需要通过拉取面单的接口来获取面单号
            ExpressBill bill = ExpressBillService.mites();
            String msg1= bill.getMailNo();
            view.setViewName("/barcode");
            view.addObject("mailNo",msg1);
            view.addObject("MarkDestination",bill.getDistributeInfo().get(0).getShortAddress());
            view.addObject("recipient","");
            view.addObject("recipientprovince","");
            view.addObject("recipientcity","");
            view.addObject("recipientarea","");
            view.addObject("recipientaddress","");
            view.addObject("recipientpostcode","");
            view.addObject("recipientphone","");
            view.addObject("senderphone","");
            view.addObject("sendercountry","");
            view.addObject("senderprovince","");
            view.addObject("sendercity","");
            view.addObject("sendercountry","");
            view.addObject("senderarea","");
            view.addObject("senderaddress","");
            view.addObject("senderpostcode","");
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
