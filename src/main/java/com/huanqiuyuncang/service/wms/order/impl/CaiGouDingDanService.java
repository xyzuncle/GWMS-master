package com.huanqiuyuncang.service.wms.order.impl;

import com.huanqiuyuncang.dao.customer.CustomerDAO;
import com.huanqiuyuncang.dao.customer.GongYingShangDAO;
import com.huanqiuyuncang.dao.order.CaiGouDingDanDAO;
import com.huanqiuyuncang.dao.order.CaiGouShangPinDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.dao.warehouse.ProductWarehouseDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.customer.CustomerEntity;
import com.huanqiuyuncang.entity.customer.GongYingShangEntity;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String caigoudingdanhao = OrderUtil.getOrderNum(gongyingshangbianhao);
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
    public PageData saveruku(String caigoudingdanid) {
        PageData pd = new PageData();
        CaiGouDingDanEntity caiGouDingDan = caiGouDingDanDAO.selectByPrimaryKey(caigoudingdanid);
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        caiGouDingDan.setUpdatetime(date);
        caiGouDingDan.setUpdateuser(username);
        caiGouDingDan.setCaigoudingdanstatus("caigouStatus_yiruku");
        caiGouDingDanDAO.updateByPrimaryKeySelective(caiGouDingDan);
        PageData args = new PageData();
        args.put("neibuhuohao",caiGouDingDan.getShangpinhuohao());
        args.put("kehubianhao",caiGouDingDan.getKehubianhao());
        ProductWarehouseEntity productWarehouse = productWarehouseDAO.selectByPd(args);
        if(productWarehouse == null){
            productWarehouse = new ProductWarehouseEntity();
            productWarehouse.setProductwarehouseid(UuidUtil.get32UUID());
            productWarehouse.setShuliang(caiGouDingDan.getShuliang());
            ProductEntity productByProductNum = productDAO.findProductByProductNum(caiGouDingDan.getShangpinhuohao());
            CustomerEntity customerEntity = customerDAO.selectCustomerByCode(caiGouDingDan.getKehubianhao());
            productWarehouse.setCangwei(customerEntity.getDefaultwarehouse());
            productWarehouse.setShangpintiaoma(productByProductNum.getBarcodeMain());
            productWarehouse.setCreatetime(date);
            productWarehouse.setCreateuser(username);
            productWarehouse.setUpdatetime(date);
            productWarehouse.setUpdateuser(username);
            productWarehouse.setKehubianhao(caiGouDingDan.getKehubianhao());
            productWarehouse.setNeibuhuohao(caiGouDingDan.getShangpinhuohao());
            productWarehouseDAO.insertSelective(productWarehouse);
            pd.put("msg","success");
            pd.put("resturt","OK");
        }else{
            if("1".equals(productWarehouse.getSuokustatus())){
                pd.put("msg","error");
                pd.put("resturt","仓库处于盘点状态，不能进行进出库操作。");
            }else{
                String shuliang = productWarehouse.getShuliang();
                Integer sum =Integer.parseInt(shuliang);
                sum = sum + Integer.parseInt(caiGouDingDan.getShuliang());
                productWarehouse.setShuliang(Integer.toString(sum));
                productWarehouseDAO.updateByPrimaryKeySelective(productWarehouse);
                pd.put("msg","success");
                pd.put("resturt","OK");
            }

        }


        return pd;
    }

    @Override
    public String saveDingDanFromExcel(List<PageData> caigouList) {
        final StringBuffer resturt = new StringBuffer("");
        String username = Jurisdiction.getUsername();
        Date date = new Date();
        String caigoudingdanID = UuidUtil.get32UUID();
        String gongyingshangbianhao = "";
        String kehubianhao = "";
        List<CaiGouShangPinEntity> caigoushangpinList = new ArrayList<>();
        CaiGouDingDanEntity dingdan = new CaiGouDingDanEntity();
        dingdan.setGongyingshangbianhao(gongyingshangbianhao);
        dingdan.setKehubianhao(kehubianhao);
        dingdan.setCaigoudingdanid(caigoudingdanID);
        dingdan.setCreateuser(username);
        dingdan.setCreatetime(date);
        dingdan.setUpdateuser(username);
        dingdan.setUpdatetime(date);
        Boolean falg = true;
        for (int i = 0 ; i<caigouList.size(); i++) {
            PageData pd = caigouList.get(0);
            if(falg){
                falg = false;
                gongyingshangbianhao = pd.getString("var0");
                GongYingShangEntity gongYingShangEntity = gongYingShangDAO.selectGongyingshangByCode(gongyingshangbianhao);
                if(gongYingShangEntity == null){
                    resturt.append("第"+(i+1)+"行供应商编号未找到，");
                }
                kehubianhao = caigouList.get(0).getString("var2");
                CustomerEntity customerEntity = customerDAO.selectCustomerByCode(kehubianhao);
                if(customerEntity == null){
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
            String shuliang = pd.getString("var3");
            String caigoujiage= pd.getString("var4");
            String xiaoji= pd.getString("var5");
            String beizhu = pd.getString("var6");
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
            caiGouDingDanDAO.insertSelective(dingdan);
            for (CaiGouShangPinEntity c:caigoushangpinList) {
                caiGouShangPinDAO.insertSelective(c);
            }
            return "";
        }else{
            return resturt.toString();
        }

    }
}
