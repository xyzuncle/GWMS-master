package com.huanqiuyuncang.service.wms.order;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.order.InnerOrderEntity;
import com.huanqiuyuncang.entity.order.OrderInfoDTO;
import com.huanqiuyuncang.util.PageData;

import java.util.List;

public interface InnerOrderInterface {
    int insert(InnerOrderEntity record);

    int insertSelective(InnerOrderEntity record);

    int updateByPrimaryKeySelective(InnerOrderEntity record);

    List<OrderInfoDTO> datalistPage(Page page);

    int deleteByPrimaryKey(String innerorderid);

    InnerOrderEntity selectByPrimaryKey(String innerorderid);

    void deleteAll(String[] arrayDATA_ids);

    List<PageData> selectDictionaries(String PARENT_ID);


    void insertOrderInfo(InnerOrderEntity innerOrder, String token);


    List<PageData> selectProvince();

    List<PageData> selectCity(String code);

    List<PageData> selectArea(String code);

    String selectProvinceNameByCode(String code);

    String selectCityNameByCode(String code);

    String selectAreaNameByCode(String code);

    void shenheAll(String[] arrayDATA_ids);

    String saveOrderFromExcel(List<PageData> orderListPd, List<PageData> listPd);

    void createpackage(String[] id);

    String savePackageFromExcel(List<PageData> orderList, List<PageData> orderPdList);

    void zuofeiAll(String[] arrayDATA_ids);

    void yichang(String[] arrayDATA_ids);

    void makequeren(String[] arrayDATA_ids);

    void makeshenhe(String[] arrayDATA_ids);


    List<CangKuEntity> getCangku(String code);

    void saveShangpinChuku(String[] ids, String cangkushuxing, String cangku, String kuwei);

    void saveBaoguoRuku(String[] ids, String baoguokuwei);

    String selectCustomernumByOrderNumId(String ordernumid);

    List<InnerOrderEntity> packagelistPage(Page page);

    String savehedan(String DATA_IDS);

    String savechaidan(String DATA_IDS);

    List<OrderInfoDTO> selectByhedan(PageData pd);

    List<OrderInfoDTO> selectBychaidan(PageData pd);

/*    InnerOrderEntity selectByDingdanhao(String dingdanhao);

    List<InnerOrderEntity> selectByBaoguoDanhao(String baoguodanhao);*/
}