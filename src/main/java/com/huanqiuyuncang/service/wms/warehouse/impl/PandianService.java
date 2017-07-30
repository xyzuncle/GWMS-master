package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.order.CaiGouDingDanDAO;
import com.huanqiuyuncang.dao.order.CaiGouShangPinDAO;
import com.huanqiuyuncang.dao.warehouse.ChuKuShangPinDAO;
import com.huanqiuyuncang.dao.warehouse.PandianDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.ChuKuShangPinEntity;
import com.huanqiuyuncang.entity.warehouse.PandianEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.entity.warehouse.RuKuBaoGuoEntity;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.warehouse.PandianInterface;
import com.huanqiuyuncang.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lzf on 2017/7/30.
 */
@Service
public class PandianService implements PandianInterface {

    @Autowired
    private PandianDAO pandianDAO;

    @Autowired
    private ProductWarehouseDAO productWarehouseDAO;

    @Autowired
    private CangKuDAO cangKuDAO;

    @Autowired
    private CaiGouShangPinDAO caiGouShangPinDAO;

    @Autowired
    private CaiGouDingDanDAO caiGouDingDanDAO;

    @Autowired
    private ChuKuShangPinDAO chuKuShangPinDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return pandianDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PandianEntity record) {
        return pandianDAO.insert(record);
    }

    @Override
    public int insertSelective(PandianEntity record) {
        return pandianDAO.insertSelective(record);
    }

    @Override
    public PandianEntity selectByPrimaryKey(String id) {
        return pandianDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PandianEntity record) {
        return pandianDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PandianEntity record) {
        return pandianDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<PandianEntity> datalistPage(Page page) {
        return pandianDAO.datalistPage(page);
    }

    @Override
    public void updatePandian(String kuwei, Map<String, Integer> map) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        PageData pd = new PageData();
        pd.put("kuwei",kuwei);
        String yyyyMMdd = DateUtil.format(date, "yyyyMMdd");
        for(String tiaoma : map.keySet()){
            pd.put("status","pandian_daiqueren");
            pd.put("tiaoma",tiaoma);
            PandianEntity pandian = pandianDAO.selectByPageData(pd);
            if(pandian != null){
                ProductWarehouseEntity pw = productWarehouseDAO.selectByPrimaryKey(pandian.getProductwarehouseid());
                Integer kucunshuliang = Integer.parseInt(pw.getShuliang());
                Integer saomashuliang = map.get(tiaoma);
                if(kucunshuliang == saomashuliang){
                    pandian.setBeizhu(yyyyMMdd+"正常");
                }else if(kucunshuliang < saomashuliang){
                    pandian.setBeizhu(yyyyMMdd+"盘盈");
                    CaiGouDingDanEntity caiGouDingDan = new CaiGouDingDanEntity();
                    String caigoudingdanid = UuidUtil.get32UUID();
                    caiGouDingDan.setCaigoudingdanid(caigoudingdanid);
                    caiGouDingDan.setCaigoudingdanstatus("caigouStatus_yiruku");
                    CangKuEntity cangKuEntity = cangKuDAO.selectByCangKu(pandian.getCangku());
                    caiGouDingDan.setCangku(cangKuEntity !=null?cangKuEntity.getId():"");
                    caiGouDingDan.setCangwei(kuwei);
                    caiGouDingDan.setBeizhu("盘点时，库存出现差异，自动补齐，生成出库单。");
                    String caigoudingdanhao = OrderUtil.getCaiGouNum("A00000");
                    caiGouDingDan.setCreateuser(username);
                    caiGouDingDan.setCreatetime(date);
                    caiGouDingDan.setUpdatetime(date);
                    caiGouDingDan.setUpdateuser(username);
                    caiGouDingDan.setCaigoudingdanhao(caigoudingdanhao);
                    CaiGouShangPinEntity caiGouShangPinEntity = new CaiGouShangPinEntity();
                    caiGouShangPinEntity.setId(UuidUtil.get32UUID());
                    caiGouShangPinEntity.setCaigoudingdanid(caigoudingdanid);
                    caiGouShangPinEntity.setShuliang((saomashuliang-kucunshuliang)+"");
                    caiGouShangPinEntity.setSaomastatus("1");
                    caiGouShangPinEntity.setCaigoujiage("0");
                    caiGouDingDanDAO.insertSelective(caiGouDingDan);
                    caiGouShangPinDAO.insertSelective(caiGouShangPinEntity);
                    pw.setShuliang(saomashuliang+"");
                }else {
                    pandian.setBeizhu(yyyyMMdd+"盘亏");
                    ChuKuShangPinEntity chuKuShangPinEntity = new ChuKuShangPinEntity();
                    chuKuShangPinEntity.setChukuzhuangtai("yichuku");
                    chuKuShangPinEntity.setCangku(pandian.getCangku());
                    chuKuShangPinEntity.setCangwei(kuwei);
                    chuKuShangPinEntity.setChukushangpinid(UuidUtil.get32UUID());
                    chuKuShangPinEntity.setNeibuhuohao(pandian.getShangpinbianhao());
                    chuKuShangPinEntity.setShangpintiaoma(tiaoma);
                    chuKuShangPinEntity.setShuliang((kucunshuliang-saomashuliang)+"");
                    chuKuShangPinEntity.setCreateuser(username);
                    chuKuShangPinEntity.setCreatetime(date);
                    chuKuShangPinEntity.setUpdatetime(date);
                    chuKuShangPinEntity.setUpdateuser(username);
                    chuKuShangPinDAO.insertSelective(chuKuShangPinEntity);
                    pw.setShuliang(saomashuliang+"");
                }
                pw.setSuokustatus("0");
                productWarehouseDAO.updateByPrimaryKeySelective(pw);
                pandian.setStatus("pandian_yiqueren");
                pandian.setUpdateuser(username);
                pandian.setUpdatetime(new Date());
                pandianDAO.updateByPrimaryKeySelective(pandian);
            }
        }
    }
}
