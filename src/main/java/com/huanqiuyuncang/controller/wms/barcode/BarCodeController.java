package com.huanqiuyuncang.controller.wms.barcode;


import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrderProductEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.yto.ExpressBill;
import com.huanqiuyuncang.entity.yto.TransferCenter;
import com.huanqiuyuncang.service.wms.order.InnerOrderInterface;
import com.huanqiuyuncang.service.wms.order.OrderProductInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.service.yto.ExpressBillService;
import com.huanqiuyuncang.service.yto.LogisticsInterface;
import com.huanqiuyuncang.util.CustomerBarcodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xyz on 2017/5/10.
 */
@Controller
@RequestMapping("/barcode")
public class BarCodeController {

    @Autowired
    private InnerOrderInterface innerOrderService;

    @Autowired
    private OrderProductInterface orderProductService;

    @Autowired
    private ProductInterface productService;

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
    public ModelAndView getBarCodeImgInfo(String innerorderid){

        Map<String,Object> billMap = new HashMap<String,Object>();
        ModelAndView view = new ModelAndView();
        try {
            InnerOrderEntity innerOrderEntity = innerOrderService.selectByPrimaryKey(innerorderid);
            Integer sum = orderProductService.orderproductSum(innerOrderEntity.getInnerpackagenum());
            String recipientarea = innerOrderService.selectAreaNameByCode(innerOrderEntity.getRecipientarea());
            String recipientprovince = innerOrderService.selectProvinceNameByCode(innerOrderEntity.getRecipientprovince());
            String recipientcity = innerOrderService.selectCityNameByCode(innerOrderEntity.getRecipientcity());
            innerOrderEntity.setRecipientarea(recipientarea);
            innerOrderEntity.setRecipientprovince(recipientprovince);
            innerOrderEntity.setRecipientcity(recipientcity);
            List<OrderProductEntity> orderProductEntities = orderProductService.selectOrderProductBypackagenum(innerOrderEntity.getInnerpackagenum());
            StringBuffer goods = new StringBuffer("");
            if(orderProductEntities != null && orderProductEntities.size()>0){
                orderProductEntities.forEach(orderProduct -> {
                    try {
                        ProductEntity product = productService.findProductByBarCode(orderProduct.getOuterproductnum());
                        goods.append(product.getProductname());
                        goods.append(",");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }


            //包裹单号，用于生产面单号使用
            String innerpackagenum = innerOrderEntity.getInnerpackagenum();

            //需要通过拉取面单的接口来获取面单号
            ExpressBill bill = ExpressBillService.mites(innerpackagenum);
            String msg1= bill.getMailNo();
            view.setViewName("/barcode");
            view.addObject("mailNo",msg1);
            view.addObject("MarkDestination",bill.getDistributeInfo().get(0).getShortAddress());
            view.addObject("order",innerOrderEntity);
            view.addObject("sum",sum);
            view.addObject("goods",goods.substring(0,goods.length()-1));
            /*view.addObject("recipient","");
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
            view.addObject("senderpostcode","");*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
