package com.huanqiuyuncang.service.wms.warehouse.impl;

import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.kuwei.ShangPinKuWeiDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.saomiao.ShangPinSaomiaoDAO;
import com.huanqiuyuncang.dao.warehouse.YiKuDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.saomiao.ShangPinSaomiaoEntity;
import com.huanqiuyuncang.entity.warehouse.YiKuEntity;
import com.huanqiuyuncang.entity.warehouse.YiKuSaomiaoDTO;
import com.huanqiuyuncang.service.wms.kuwei.CangKuInterface;
import com.huanqiuyuncang.service.wms.kuwei.ShangPinKuWeiInterface;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.service.wms.warehouse.YiKuInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/7/11.
 */
@Service
public class YiKuService implements YiKuInterface {
    @Autowired
    private YiKuDAO yiKuDAO;


    @Autowired
    private CangKuDAO cangKuDAO;

    @Autowired
    private ShangPinKuWeiDAO shangPinKuWeiDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ShangPinSaomiaoDAO shangPinSaomiaoDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return yiKuDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YiKuEntity record) {
        return yiKuDAO.insert(record);
    }

    @Override
    public int insertSelective(YiKuEntity record) {
        return yiKuDAO.insertSelective(record);
    }

    @Override
    public YiKuEntity selectByPrimaryKey(String id) {
        return yiKuDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YiKuEntity record) {
        return yiKuDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YiKuEntity record) {
        return yiKuDAO.updateByPrimaryKey(record);
    }

    @Override
    public void insert(List<YiKuEntity> list) {
        for (YiKuEntity yiku : list) {
            yiKuDAO.insertSelective(yiku);
        }
    }

    @Override
    public List<YiKuEntity> datalistPage(Page page) {
        return yiKuDAO.datalistPage(page);
    }

    @Override
    public  List<YiKuEntity> selectByPdNumAndTargetCangkuInfo(String productnum, String cangkubianhao, String kuwei) {
        return yiKuDAO.selectByPdNumAndTargetCangkuInfo(productnum,cangkubianhao,kuwei);
    }

    @Override
    public void updateyiku(List<YiKuSaomiaoDTO> saomiaoList, ModelAndView mv) {
        String createuser = Jurisdiction.getUsername();
        Date date = new Date();
        for(YiKuSaomiaoDTO saomiao : saomiaoList){
            //1.根据登陆用户的扫描的库位查询库位信息。
            ShangPinKuWeiEntity kuWei = shangPinKuWeiDAO.selectByKuWeiAndCreateUser(saomiao.getKuwei(),createuser);
            String cangkuid = kuWei.getCangku();
            //2.根据库位信息查询所属仓库
            CangKuEntity cangKuEntity = cangKuDAO.selectByPrimaryKey(cangkuid);
            //3.根据商品编号和仓库信息查询移库信息
            String shangpintiaoma = saomiao.getShangpintiaoma();
            ProductEntity product = productDAO.findProductByBarCode(shangpintiaoma);
            String productnum = product.getProductnum();
            PageData pd  = new PageData();
            pd.put("targetcangku",cangKuEntity.getCangkubianhao());
            pd.put("targetcangwei",saomiao.getKuwei());
            pd.put("productNum",productnum);
            pd.put("yikustatus","yiku_daiyiku");
            List<YiKuEntity> list = yiKuDAO.selectByArgs(pd);
            YiKuEntity yiKuEntity = list.get(0);
            //4.保存商品扫描信息
            ShangPinSaomiaoEntity shangPinSaomiaoEntity = new ShangPinSaomiaoEntity();
            shangPinSaomiaoEntity.setSaomiaoshuliang(Integer.parseInt(saomiao.getShuliang()));
            shangPinSaomiaoEntity.setShangpinid(yiKuEntity.getId());
            shangPinSaomiaoEntity.setId(UuidUtil.get32UUID());
            shangPinSaomiaoDAO.insertSelective(shangPinSaomiaoEntity);
            //5.判断扫描数量和移库数量是否相等
            Integer sum = shangPinSaomiaoDAO.selectSaomiaoSumByShangpin(yiKuEntity.getId());
            //5.1相等 修改移库信息状态
            if(Integer.parseInt(yiKuEntity.getYikushuangliang()) == sum){
                yiKuEntity.setYikustatus("yiku_yiyiku");
                yiKuDAO.updateByPrimaryKeySelective(yiKuEntity);
                mv.addObject("msg","success");
            }else if(Integer.parseInt(yiKuEntity.getYikushuangliang()) < sum){
                mv.addObject("msg","error");
                mv.addObject("resturt",saomiao.getShangpintiaoma()+" 扫描总数量过多，请联系管理员！");
            }
            //5.2不相等 不修移库改信息
        }
    }
}
