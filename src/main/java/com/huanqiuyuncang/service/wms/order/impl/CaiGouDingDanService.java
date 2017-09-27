package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.customer.GongYingShangDAO;
import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.kuwei.ShangPinKuWeiDAO;
import com.huanqiuyuncang.dao.order.CaiGouDingDanDAO;
import com.huanqiuyuncang.dao.order.CaiGouShangPinDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.saomiao.ShangPinSaomiaoDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.saomiao.ShangPinSaomiaoEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.order.CaiGouDingDanInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.OrderUtil;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lzf on 2017/5/30.
 */
@Service
public class CaiGouDingDanService implements CaiGouDingDanInterface {
    @Autowired
    private CaiGouDingDanDAO caiGouDingDanDAO;
    @Autowired
    private ProductWarehouseDAO productWarehouseDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private GongYingShangDAO gongYingShangDAO;

    @Autowired
    private CaiGouShangPinDAO caiGouShangPinDAO;

    @Autowired
    private CangKuDAO cangKuDAO;

    @Autowired
    private ShangPinKuWeiDAO shangPinKuWeiDAO;

    @Autowired
    private ShangPinSaomiaoDAO shangPinSaomiaoDAO;

    @Override
    public int deleteByPrimaryKey(String caigoudingdanid) {
        List<CaiGouShangPinEntity> list = caiGouShangPinDAO.selectByCaiGouDingDanId(caigoudingdanid);
        for (CaiGouShangPinEntity caigoushangpin :list) {
            caiGouShangPinDAO.deleteByPrimaryKey(caigoushangpin.getId());
        }
        return caiGouDingDanDAO.deleteByPrimaryKey(caigoudingdanid);
    }

    @Override
    public int insert(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.insert(record);
    }

    @Override
    public int insertSelective(CaiGouDingDanEntity record, String token) {
        String gongyingshangbianhao = record.getGongyingshangbianhao();
        String caigoudingdanhao = OrderUtil.getCaiGouNum(gongyingshangbianhao);
        record.setCaigoudingdanhao(caigoudingdanhao);
        List<CaiGouShangPinEntity> list = caiGouShangPinDAO.selectByCaiGouDingDanId(token);
        if(list != null && list.size()>0){
            list.forEach(caigoushangpin ->{
                caigoushangpin.setCaigoudingdanid(record.getCaigoudingdanid());
                caiGouShangPinDAO.updateByPrimaryKeySelective(caigoushangpin);
            });
        }
        return caiGouDingDanDAO.insertSelective(record);
    }

    @Override
    public CaiGouDingDanEntity selectByPrimaryKey(String caigoudingdanid) {
        return caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
    }

    @Override
    public int updateByPrimaryKeySelective(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CaiGouDingDanEntity record) {
        return caiGouDingDanDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CaiGouDingDanEntity> datalistPage(Page page) {
        return caiGouDingDanDAO.datalistPage(page);
    }

    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            List<CaiGouShangPinEntity> list = caiGouShangPinDAO.selectByCaiGouDingDanId(id);
            for (CaiGouShangPinEntity caigoushangpin :list) {
                caiGouShangPinDAO.deleteByPrimaryKey(caigoushangpin.getId());
            }
             caiGouDingDanDAO.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void shenheAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yiqueren");
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }

    @Override
    public void zuofeiAll(String[] arrayDATA_ids) {
        for (String id:arrayDATA_ids) {
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yizuofei");
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }

    @Override
    public PageData saveruku(String caigoudingdanid,  Map<String,String> tiaomaMap) {
        PageData pd = new PageData();
        Boolean falg = true; // 记录订单中商品的盘点状态
        Boolean falg2 = true; // 记录扫描数量与商品采购数量是否相等
        //1.获取扫描的采购订单信息
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
        //2.获取采购订单的仓库信息
        String cangkuId = caiGouDingDan.getCangku();
        CangKuEntity cangKuEntity = cangKuDAO.selectByPrimaryKey(cangkuId);
        if(tiaomaMap != null){
            //3.获取扫描的条码集合
            List<String> tiaomaList  = new ArrayList<>(tiaomaMap.keySet());
            //4.获取采购订单的商品集合
            List<CaiGouShangPinEntity> shangpinList = caiGouShangPinDAO.selectByCaiGouDingDanId(caigoudingdanid);
            //5.保存和修改商品的扫描信息
            for (CaiGouShangPinEntity shangpin: shangpinList) {
                //5.1 获取商品库位信息 采购订单有仓位（该仓位是推送入库填写的）就延用，无就查询默认仓库。
                String cangwei = "";
                if(StringUtils.isBlank(caiGouDingDan.getCangwei())){
                    List<ShangPinKuWeiEntity> shangPinKuWeiEntities = shangPinKuWeiDAO.selectByCangkuAndProductnum(cangkuId, shangpin.getShangpinhuohao());
                    if(shangPinKuWeiEntities != null && shangPinKuWeiEntities.size()>0){
                        cangwei = shangPinKuWeiEntities.get(0).getKuwei();
                    }
                }else {
                    cangwei = caiGouDingDan.getCangwei();
                }

                //5.2 获取采购商品信息
                String huohao = shangpin.getShangpinhuohao();
                ProductEntity product = productDAO.findProductByProductNum(huohao);
                String barcodeMain = product.getBarcodeMain();
                String saomiaostatus = shangpin.getSaomastatus();
                //5.3 判断商品的扫描状态
                if(tiaomaList.contains(barcodeMain) &&"0".equals(saomiaostatus)){
                    //5.4 创建商品的扫描信息
                    String saomiaoshuliang = tiaomaMap.get(barcodeMain);
                    createShangPinSaomiao(saomiaoshuliang, shangpin);
                    //5.5 获取商品库存信息，无：创建；有：修改商品库存信息（判断盘点状态）
                    PageData args = new PageData();
                    args.put("neibuhuohao",shangpin.getShangpinhuohao());
                    args.put("kehubianhao",caiGouDingDan.getKehubianhao());
                    args.put("cangku",cangKuEntity.getCangkubianhao());
                    args.put("cangwei",cangwei);
                    ProductWarehouseEntity productWarehouse = productWarehouseDAO.selectByPd(args);
                    if(productWarehouse == null){
                        createProductWarehouse(shangpin,caiGouDingDan.getKehubianhao(),saomiaoshuliang,cangKuEntity.getCangkubianhao(),cangwei);
                    }else{
                        if("1".equals(productWarehouse.getSuokustatus())){
                            falg = false;

                        }else{
                            updateProductWarehouse(shangpin, productWarehouse,saomiaoshuliang);
                        }
                    }
                }
                //5.6 获取该商品的扫描总数量（扫描属性相等修改扫描状态；否修改falg2标记）
                Integer sum = shangPinSaomiaoDAO.selectSaomiaoSumByShangpin(shangpin.getId());
                sum = sum == null?0:sum;
                if(sum == Integer.parseInt(shangpin.getShuliang())){
                    saomiaostatus = "1";
                }else {
                    falg2 = false;
                    saomiaostatus = "0";
                }
                shangpin.setSaomastatus(saomiaostatus);
                caiGouShangPinDAO.updateByPrimaryKeySelective(shangpin);
            }
            //6.判断是否存在有盘点商品和未扫描完商品（true：修改采购订单状态；false：返回提示信息）
            if(falg && falg2){
                updateCaiGouDingDanStatus(caiGouDingDan);
                pd.put("msg","success");
                pd.put("resturt","ok");
            }else{
                pd.put("msg","error");
                pd.put("resturt","有未扫描的商品或该商品库存处于盘点状态");
            }
        }
        return pd;
    }

    private void createShangPinSaomiao(String saomiaoshuliang , CaiGouShangPinEntity shangpin) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        Integer shuliang = Integer.parseInt(saomiaoshuliang);
        ShangPinSaomiaoEntity shangPinSaomiaoEntity = new ShangPinSaomiaoEntity();
        shangPinSaomiaoEntity.setId(UuidUtil.get32UUID());
        shangPinSaomiaoEntity.setSaomiaoshuliang(shuliang);
        shangPinSaomiaoEntity.setShangpinid(shangpin.getId());
        shangPinSaomiaoEntity.setCreatetime(date);
        shangPinSaomiaoEntity.setCreateuser(username);
        shangPinSaomiaoEntity.setUpdatetime(date);
        shangPinSaomiaoEntity.setUpdateuser(username);
        shangPinSaomiaoDAO.insertSelective(shangPinSaomiaoEntity);
    }

    private void updateCaiGouDingDanStatus(CaiGouDingDanEntity caiGouDingDan) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        caiGouDingDan.setUpdatetime(date);
        caiGouDingDan.setUpdateuser(username);
        caiGouDingDan.setCaigoudingdanstatus("caigouStatus_yiruku");
        caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDan);
    }

    private void updateProductWarehouse(CaiGouShangPinEntity shangpin, ProductWarehouseEntity productWarehouse, String shuliang) {
        Date date = new Date();
        String cangkuuser = Jurisdiction.getUsername();
        productWarehouse.setUpdatetime(date);
        productWarehouse.setUpdateuser(cangkuuser);
        if(StringUtils.isBlank(shuliang)){
            shuliang = shangpin.getShuliang();
        }
        Integer sum =Integer.parseInt(productWarehouse.getShuliang());
        sum = sum + Integer.parseInt(shuliang);
        productWarehouse.setShuliang(Integer.toString(sum));
        productWarehouseDAO.updateByPrimaryKeySelective(productWarehouse);
    }

    private void createProductWarehouse(CaiGouShangPinEntity shangpin, String kehubianhao, String shuliang,String cangku, String cangwei) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        ProductWarehouseEntity productWarehouse;
        productWarehouse = new ProductWarehouseEntity();
        productWarehouse.setProductwarehouseid(UuidUtil.get32UUID());
        if(StringUtils.isBlank(shuliang)){
            productWarehouse.setShuliang(shangpin.getShuliang());
        }else{
            productWarehouse.setShuliang(shuliang);
        }
        ProductEntity productByProductNum = productDAO.findProductByProductNum(shangpin.getShangpinhuohao());
        productWarehouse.setCangku(cangku);
        productWarehouse.setCangwei(cangwei);
        productWarehouse.setShangpintiaoma(productByProductNum.getBarcodeMain());
        productWarehouse.setCreatetime(date);
        productWarehouse.setCreateuser(username);
        productWarehouse.setUpdatetime(date);
        productWarehouse.setUpdateuser(username);
        productWarehouse.setKehubianhao(kehubianhao);
        productWarehouse.setNeibuhuohao(shangpin.getShangpinhuohao());
        productWarehouseDAO.insertSelective(productWarehouse);
    }

    @Override
    public String saveDingDanFromExcel(List<PageData> caigouList) {
        final StringBuffer resturt = new StringBuffer("");
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        String caigoudingdanID = UuidUtil.get32UUID();
        String gongyingshangbianhao = "";
        String kehubianhao = "";
        String caigoudingdanhao = "";
        List<CaiGouShangPinEntity> caigoushangpinList = new ArrayList<>();

        Boolean falg = true;
        for (int i = 0 ; i<caigouList.size(); i++) {
            PageData pd = caigouList.get(i);
            if(falg){
                falg = false;
                gongyingshangbianhao = pd.getString("var0");
                GongYingShangEntity gongYingShangEntity = gongYingShangDAO.selectByCodeAndLoginName(gongyingshangbianhao,username);
                if(gongYingShangEntity == null){
                    resturt.append("第"+(i+1)+"行供应商编号未找到，");
                }else{
                    caigoudingdanhao = OrderUtil.getCaiGouNum(gongyingshangbianhao);
                }
                List<CustomerEntity> list = customerDAO.selectByLoginName(username);
                if(list == null || list.size() == 0 ){
                    resturt.append("客户编号未找到，");
                }else{
                    kehubianhao =  list.get(0).getCustomercode();
                }
            }else{
                if(!gongyingshangbianhao.equals(pd.getString("var0"))){
                    resturt.append("第"+(i+1)+"行供应商编号与第一行不同");
                }
            }
            String shangpinhuohao = pd.getString("var1");
            ProductEntity productEntity = productDAO.findProductByBarCodeOrNum(shangpinhuohao);
            if(productEntity == null){
                resturt.append("第"+(i+1)+"商品货号未找到");
            }
            String shuliang = pd.getString("var2");
            String caigoujiage= pd.getString("var3");
            String xiaoji= pd.getString("var4");
            String beizhu = pd.getString("var5");
            CaiGouShangPinEntity  shangpin = new CaiGouShangPinEntity();
            shangpin.setId(UuidUtil.get32UUID());
            shangpin.setCaigoudingdanid(caigoudingdanID);
            shangpin.setBeizhu(beizhu);
            shangpin.setShangpinhuohao(shangpinhuohao);
            shangpin.setShuliang(shuliang);
            shangpin.setCaigoujiage(caigoujiage);
            shangpin.setXiaoji(xiaoji);
            caigoushangpinList.add(shangpin);
        }
        if(StringUtils.isBlank(resturt.toString())){
            CaiGouDingDanEntity dingdan = new CaiGouDingDanEntity();
            dingdan.setCaigoudingdanhao(caigoudingdanhao);
            dingdan.setCaigoudingdanstatus("caigouStatus_daiqueren");
            dingdan.setGongyingshangbianhao(gongyingshangbianhao);
            dingdan.setKehubianhao(kehubianhao);
            dingdan.setCaigoudingdanid(caigoudingdanID);
            dingdan.setCreateuser(username);
            dingdan.setCreatetime(date);
            dingdan.setUpdateuser(username);
            dingdan.setUpdatetime(date);
            caiGouDingDanDAO.insertSelective(dingdan);
            for (CaiGouShangPinEntity c:caigoushangpinList) {
                c.setSaomastatus("0");
                caiGouShangPinDAO.insertSelective(c);
            }
            return "";
        }else{
            return resturt.toString();
        }

    }

    @Override
    public void saveShangpinRuku(String[] ids, String cangkushuxing, String cangku, String kuwei) {
        String username = Jurisdiction.getUsername();
        CangKuEntity cangKuEntity = cangKuDAO.selectByPrimaryKey(cangku);
        if(StringUtils.isNotBlank(kuwei) && !"默认库位".equals(kuwei)&& !"自定义库位".equals(kuwei)){
            ShangPinKuWeiEntity shangPinKuWeiEntity = shangPinKuWeiDAO.selectByKuWei(kuwei);
            if(shangPinKuWeiEntity == null){
                ShangPinKuWeiEntity newKuWei = new ShangPinKuWeiEntity();
                newKuWei.setCangku(cangKuEntity.getId());
                newKuWei.setId(UuidUtil.get32UUID());
                newKuWei.setKuwei(kuwei);
                newKuWei.setCreateuser(username);
                newKuWei.setCreatetime(new Date());
                newKuWei.setUpdateuser(username);
                newKuWei.setUpdatetime(new Date());
                shangPinKuWeiDAO.insertSelective(newKuWei);
            }
        }else{
            kuwei = "";
        }
        for(String id : ids){
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
           // caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_dairuku");
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_yiruku");
            String createuser = cangKuEntity.getCreateuser();
            caiGouDingDanEntity.setCangkuuser(createuser);
            caiGouDingDanEntity.setCangku(cangku);
            caiGouDingDanEntity.setCangwei(kuwei);
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }
}
