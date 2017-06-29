package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.customer.GongYingShangDAO;
import com.huanqiuyuncang.dao.kuwei.CangKuDAO;
import com.huanqiuyuncang.dao.kuwei.ShangPinKuWeiDAO;
import com.huanqiuyuncang.dao.order.CaiGouDingDanDAO;
import com.huanqiuyuncang.dao.order.CaiGouShangPinDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
import com.huanqiuyuncang.entity.kuwei.CangKuEntity;
import com.huanqiuyuncang.entity.kuwei.ShangPinKuWeiEntity;
import com.huanqiuyuncang.entity.order.CaiGouDingDanEntity;
import com.huanqiuyuncang.entity.order.CaiGouShangPinEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.entity.warehouse.ProductWarehouseEntity;
import com.huanqiuyuncang.service.wms.order.CaiGouDingDanInterface;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.OrderUtil;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
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
    public PageData saveruku(String caigoudingdanid,  Map<String,String> huohaoArr) {
        PageData pd = new PageData();
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
        CangKuEntity cangKuEntity = cangKuDAO.selectByPrimaryKey(caiGouDingDan.getCangku());
        String cangkuuser = caiGouDingDan.getCangkuuser();
        Boolean falg = true;
        PageData args = new PageData();
        if(huohaoArr != null){
            List<String> huohaoList  = new ArrayList<>(huohaoArr.keySet());
            List<CaiGouShangPinEntity> shangpinList = caiGouShangPinDAO.selectByCaiGouDingDanId(caigoudingdanid);
            for (CaiGouShangPinEntity shangpin: shangpinList) {
                List<ShangPinKuWeiEntity> shangPinKuWeiEntities = shangPinKuWeiDAO.selectByKuweiAndProductnum(cangKuEntity.getId(), shangpin.getShangpinhuohao());
                String cangwei = "";
                if(shangPinKuWeiEntities != null && shangPinKuWeiEntities.size()>0){
                    cangwei = shangPinKuWeiEntities.get(0).getKuwei();
                }
                String saomiaostatus = shangpin.getShangpinhuohao();
                if(huohaoList.contains(saomiaostatus) &&"0".equals(saomiaostatus)){
                    args.put("neibuhuohao",shangpin.getShangpinhuohao());
                    args.put("kehubianhao",caiGouDingDan.getKehubianhao());
                    args.put("cangwei",cangwei);
                    ProductWarehouseEntity productWarehouse = productWarehouseDAO.selectByPd(args);
                    if(productWarehouse == null){
                        createProductWarehouse(shangpin,caiGouDingDan.getKehubianhao(),huohaoArr.get(saomiaostatus),cangwei);
                        saomiaostatus = "1";
                    }else{
                        if("1".equals(productWarehouse.getSuokustatus())){
                            falg = false;
                            saomiaostatus = "0";
                        }else{
                            updateProductWarehouse(shangpin, productWarehouse,huohaoArr.get(saomiaostatus));
                            saomiaostatus = "1";
                        }
                    }
                }
                shangpin.setSaomastatus(saomiaostatus);
                caiGouShangPinDAO.updateByPrimaryKeySelective(shangpin);
            }
            if(falg && shangpinList.size() == huohaoList.size()){
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

    private void updateCaiGouDingDanStatus(CaiGouDingDanEntity caiGouDingDan) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        caiGouDingDan.setUpdatetime(date);
        caiGouDingDan.setUpdateuser(username);
        caiGouDingDan.setCaigoudingdanstatus("caigouStatus_yiruku");
        caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDan);
    }

    private void updateProductWarehouse(CaiGouShangPinEntity shangpin, ProductWarehouseEntity productWarehouse, String shuliang) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        productWarehouse.setUpdatetime(date);
        productWarehouse.setUpdateuser(username);
        if(StringUtils.isBlank(shuliang) || "0".equals(shuliang)){
            shuliang = shangpin.getShuliang();
        }
        Integer sum =Integer.parseInt(productWarehouse.getShuliang());
        sum = sum + Integer.parseInt(shuliang);
        productWarehouse.setShuliang(Integer.toString(sum));
        productWarehouseDAO.updateByPrimaryKeySelective(productWarehouse);
    }

    private void createProductWarehouse(CaiGouShangPinEntity shangpin,String kehubianhao ,String shuliang,String cangwei) {
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        ProductWarehouseEntity productWarehouse;
        productWarehouse = new ProductWarehouseEntity();
        productWarehouse.setProductwarehouseid(UuidUtil.get32UUID());
        if(StringUtils.isBlank(shuliang) || "0".equals(shuliang)){
            productWarehouse.setShuliang(shangpin.getShuliang());
        }else{
            productWarehouse.setShuliang(shuliang);
        }
        ProductEntity productByProductNum = productDAO.findProductByProductNum(shangpin.getShangpinhuohao());
        CustomerEntity customerEntity = customerDAO.selectCustomerByCode(kehubianhao);
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
            PageData pd = caigouList.get(0);
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
                    resturt.append("第"+(i+1)+"行客户编号未找到，");
                }
            }else{
                if(!gongyingshangbianhao.equals(pd.getString("var0"))){
                    resturt.append("第"+(i+1)+"行供应商编号与第一行不同");
                }
                if(!kehubianhao.equals(pd.getString("var2"))){
                    resturt.append("第"+(i+1)+"行客户编号与第一行不同");
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
    public void saveShangpinRuku(String[] ids, String cangkushuxing, String cangku) {
        for(String id : ids){
            CaiGouDingDanEntity caiGouDingDanEntity = caiGouDingDanDAO.selectByPrimaryKey(id);
            caiGouDingDanEntity.setCaigoudingdanstatus("caigouStatus_dairuku");
            CangKuEntity cangKuEntity = cangKuDAO.selectByPrimaryKey(cangku);
            String createuser = cangKuEntity.getCreateuser();
            caiGouDingDanEntity.setCangkuuser(createuser);
            caiGouDingDanEntity.setCangku(cangku);
            caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDanEntity);
        }
    }
}
