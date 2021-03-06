package com.huanqiuyuncang.dao.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrderInfoDTO;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface InnerOrderDAO {
    int insert(InnerOrderEntity record);

    int insertSelective(InnerOrderEntity record);

    int updateByPrimaryKeySelective(InnerOrderEntity record);

    List<OrderInfoDTO> datalistPage(Page page);

    int deleteByPrimaryKey(String innerorderid);

    InnerOrderEntity selectByPrimaryKey(String innerorderid);

    List<PageData> selectDictionaries(String parent_id);

    List<PageData> selectProvince();

    List<PageData> selectCity(String code);

    List<PageData> selectArea(String code);

    String selectProvinceNameByCode(String code);

    String selectCityNameByCode(String code);

    String selectDictBianMaByName(String baoguanmoshi);

    String selectProvinceCodeByName(String code);

    String selectCityCodeByName(String code);

    String[] selectAreaCodeByName(String baoguanmoshi);

    List<InnerOrderEntity> selectByCustomerInfo(InnerOrderEntity innerOrder);

    String selectAreaNameByCode(String areaCode);


    InnerOrderEntity selectByOuterordernum(String outerordernum);

    String selectCustomernumByOrderNumId(String ordernumid);

    List<InnerOrderEntity> packagelistPage(Page page);

    List<InnerOrderEntity> distinctRecipientByOrdertime(PageData pd);



  /*  InnerOrderEntity selectByDingdanhao(String dingdanhao);

    List<InnerOrderEntity> selectByBaoguoDanhao(String baoguodanhao);*/
}