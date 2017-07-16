package com.huanqiuyuncang.service.wms.warehouse;

import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.warehouse.YiKuEntity;
import com.huanqiuyuncang.entity.warehouse.YiKuSaomiaoDTO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface YiKuInterface {
    int deleteByPrimaryKey(String id);

    int insert(YiKuEntity record);

    int insertSelective(YiKuEntity record);

    YiKuEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YiKuEntity record);

    int updateByPrimaryKey(YiKuEntity record);

    void insert(List<YiKuEntity> list);

    List<YiKuEntity> datalistPage(Page page);

    List<YiKuEntity> selectByPdNumAndTargetCangkuInfo(String productnum, String cangkubianhao, String kuwei);

    void updateyiku(List<YiKuSaomiaoDTO> saomiaoList, ModelAndView mv);
}