package com.huanqiuyuncang.service.wms.product.impl;

import com.huanqiuyuncang.dao.DaoSupport;
import com.huanqiuyuncang.dao.brand.BrandDAO;
import com.huanqiuyuncang.dao.luggagemail.LuggageMailDAO;
import com.huanqiuyuncang.dao.product.ProductDAO;
import com.huanqiuyuncang.entity.Page;
import com.huanqiuyuncang.entity.brand.BrandEntity;
import com.huanqiuyuncang.entity.luggagemail.LuggageMailEntity;
import com.huanqiuyuncang.entity.product.ProductEntity;
import com.huanqiuyuncang.service.wms.product.ProductInterface;
import com.huanqiuyuncang.util.BeanMapUtil;
import com.huanqiuyuncang.util.Jurisdiction;
import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lzf on 2017/4/2.
 */
@Service("productService")
public class ProductService implements ProductInterface {

    @Resource(name = "daoSupport")
    private DaoSupport dao;
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private LuggageMailDAO luggageMailDAO;

    @Override
    public int deleteByPrimaryKey(PageData pageData) throws Exception{
        String id = (String) pageData.get("productId");
        return productDAO.deleteByPrimaryKey(String.valueOf(id));
    }

    @Override
    public int insert(PageData pageData) throws Exception {
        ProductEntity product = (ProductEntity) BeanMapUtil.mapToObject(pageData, ProductEntity.class);
        return productDAO.insert(product);
    }

    @Override
    public int insertSelective(ProductEntity productEntity) throws Exception{
       // ProductEntity product = (ProductEntity) BeanMapUtil.mapToObject(pageData, ProductEntity.class);
        return productDAO.insertSelective(productEntity);
    }

    @Override
    public ProductEntity selectByPrimaryKey(String id)throws Exception {
        return productDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductEntity productEntity)throws Exception {
        return productDAO.updateByPrimaryKeySelective(productEntity);
    }

    @Override
    public int updateByPrimaryKey(ProductEntity record) throws Exception{
        return productDAO.updateByPrimaryKey(record);
    }


    @Override
    public void deleteAll(String[] arrayDATA_ids) {
        if(arrayDATA_ids != null && arrayDATA_ids.length>0){
            for (String id :arrayDATA_ids) {
                productDAO.deleteByPrimaryKey(id);
            }
        }
    }

    public List<PageData> list(Page page)throws Exception{
        return (List<PageData>)dao.findForList("com.huanqiuyuncang.dao.product.ProductDAO.datalistPage", page);
    }

    public List<ProductEntity> datalistPage(Page page)throws Exception{
        List<ProductEntity> productList = productDAO.datalistPage(page);
        if(productList != null &&productList.size()>0 ){
            productList.forEach(product -> {
                String producingArea = product.getProducingArea();
                producingArea = productDAO.selectCountryNameByID(producingArea);
                product.setProducingArea(producingArea);
                String brandname = product.getBrandname();
                BrandEntity brandEntity = brandDAO.selectByPrimaryKey(brandname);
                if(brandEntity != null){
                    product.setBrandname(brandEntity.getBrandname());
                }
                LuggageMailEntity luggageMailEntity = luggageMailDAO.selectByPrimaryKey(product.getLuggagemail());
                if(luggageMailEntity != null){
                    product.setLuggagemail(luggageMailEntity.getLuggagemailname());
                }

            });
        }
        return productList;
    }

    @Override
    public ProductEntity findProductByProductNum(String productnum) throws Exception {
        return productDAO.findProductByProductNum(productnum);
    }

    @Override
    public ProductEntity findProductByBarCode(String barCode) throws Exception {
        return productDAO.findProductByBarCode(barCode);
    }

    @Override
    public List<ProductEntity> selectForExcel(PageData pageData) throws Exception {
        List<ProductEntity> productList =  productDAO.selectForExcel(pageData);
        if(productList != null &&productList.size()>0 ){
            productList.forEach(product -> {
                String producingArea = product.getProducingArea();
                producingArea = productDAO.selectCountryNameByID(producingArea);
                product.setProducingArea(producingArea);
                String brandname = product.getBrandname();
                BrandEntity brandEntity = brandDAO.selectByPrimaryKey(brandname);
                product.setBrandname(brandEntity.getBrandname());

            });
        }
        return productList;
    }

    @Override
    public String saveProductFromExcel(List<PageData> listPd) {
        //*存入数据库操作======================================
            /*
            titles.add("货号");	//1
            titles.add("商品名称");	//2
            titles.add("外文名称");	//3
            titles.add("主条码");	//4
            titles.add("辅助条码1");	//5
            titles.add("辅助条码2");	//6
            titles.add("辅助条码3");	//7
            titles.add("辅助条码4");	//8
            titles.add("品牌");	//9
            titles.add("单位");	//10
            titles.add("规格");	//11
            titles.add("产地");	//12
            titles.add("保质期（天）");	//13
            titles.add("毛重（g）");	//14
            titles.add("净重（g）");	//15
            titles.add("长（cm）");	//16
            titles.add("宽（cm）");	//17
            titles.add("高（cm）");	//18
            titles.add("体积（cm³）");	//19
            titles.add("备注");	//20
             */

        StringBuffer resturt = new StringBuffer("");
        if(listPd != null && listPd.size()>0){
            for(int i = 0 ;i<listPd.size();i++){
                int step = i+1;
                PageData pageData = listPd.get(i);
                String productnum = pageData.getString("var0");
                String productname = pageData.getString("var1");
                String productename = pageData.getString("var2");
                String barcodeMain = pageData.getString("var3");
                String barcodeAuxiliary1 = pageData.getString("var4");
                String barcodeAuxiliary2 = pageData.getString("var5");
                String barcodeAuxiliary3 = pageData.getString("var6");
                String barcodeAuxiliary4 = pageData.getString("var7");
                String brandname = pageData.getString("var8");
                String unit = pageData.getString("var9");
                String standard = pageData.getString("var10");
                String producingArea = pageData.getString("var11");
                String expirationDate = pageData.getString("var12");
                String grossWeight = pageData.getString("var13");
                String netWeight = pageData.getString("var14");
                String productLength = pageData.getString("var15");
                String productWidth = pageData.getString("var16");
                String productHigh = pageData.getString("var17");
                String productVolume = pageData.getString("var18");
                String luggagemail = pageData.getString("var19");
                String remark1 = pageData.getString("var20");
                if(StringUtils.isBlank(productnum)){
                    resturt.append("表格第"+step+"行货号不能为空。" );
                }else{
                    ProductEntity productByProductNum = productDAO.findProductByProductNum(productnum);
                    if(productByProductNum != null){
                        resturt.append("表格第"+step+"行货号已使用，请重新填写。" );
                    }
                }
                if(StringUtils.isBlank(productname)){
                    resturt.append("表格第"+step+"行商品名称不能为空。" );
                }
                if(StringUtils.isBlank(barcodeMain)){
                    resturt.append("表格第"+step+"行主条码不能为空。" );
                }else{
                    ProductEntity productByBarCode = productDAO.findProductByBarCode(barcodeMain);
                    if(productByBarCode != null){
                        resturt.append("表格第"+step+"行主条码已在（货号："+productByBarCode.getProductnum()+"中使用），请重新填写。" );
                    }
                }
                if(StringUtils.isNotBlank(barcodeAuxiliary1)){
                    ProductEntity productByBarCode1 = productDAO.findProductByBarCode(barcodeAuxiliary1);
                    if(productByBarCode1 != null){
                        resturt.append("表格第"+step+"行辅助条码1已在（货号："+productByBarCode1.getProductnum()+"中使用），请重新填写。" );
                    }
                }
               if(StringUtils.isNotBlank(barcodeAuxiliary2)) {
                   ProductEntity productByBarCode2 = productDAO.findProductByBarCode(barcodeAuxiliary2);
                   if (productByBarCode2 != null) {
                       resturt.append("表格第" + step + "行辅助条码2已在（货号：" + productByBarCode2.getProductnum() + "中使用），请重新填写。");
                   }
               }
                if(StringUtils.isNotBlank(barcodeAuxiliary3)) {
                    ProductEntity productByBarCode3 = productDAO.findProductByBarCode(barcodeAuxiliary3);
                    if(productByBarCode3 != null){
                        resturt.append("表格第"+step+"行辅助条码3已在（货号："+productByBarCode3.getProductnum()+"中使用），请重新填写。" );
                    }
                }
                if(StringUtils.isNotBlank(barcodeAuxiliary4)) {
                    ProductEntity productByBarCode4 = productDAO.findProductByBarCode(barcodeAuxiliary4);
                    if (productByBarCode4 != null) {
                        resturt.append("表格第" + step + "行辅助条码4已在（货号：" + productByBarCode4.getProductnum() + "中使用），请重新填写。");
                    }
                }
                if(StringUtils.isNotBlank(brandname)) {
                    BrandEntity brand = brandDAO.selectBrandByBrandName(brandname);
                    if(brand != null){
                        brandname = brand.getBrandid();
                    }else{
                        resturt.append("表格第"+step+"行品牌不存在。");
                    }
                }
                if(StringUtils.isNotBlank(producingArea)) {
                    String countryID = productDAO.selectCountryIDByName(producingArea);
                    if(StringUtils.isNotBlank(countryID)){
                        producingArea = countryID;
                    }else{
                        resturt.append("表格第"+step+"行产地不存在。");
                    }
                }else{
                    resturt.append("表格第"+step+"行产地不能为空。");
                }
                if(StringUtils.isNotBlank(grossWeight)){
                    try{
                        Double.parseDouble(grossWeight);
                    }catch (Exception e){
                        resturt.append("表格第"+step+"行毛重为非法数字。");
                    }
                }else{
                    grossWeight = "0";
                }
                if(StringUtils.isNotBlank(netWeight)){
                    try{
                        Double.parseDouble(netWeight);
                    }catch (Exception e){
                        resturt.append("表格第"+step+"行净重为非法数字。");
                    }
                }else{
                    netWeight = "0";
                }
                if(StringUtils.isNotBlank(productLength)){
                    try{
                        Double.parseDouble(productLength);
                    }catch (Exception e){
                        resturt.append("表格第"+step+"行长非法数字。");
                    }
                }else {
                    productLength = "0";
                }
                if(StringUtils.isNotBlank(productWidth)){
                    try{
                        Double.parseDouble(productWidth);
                    }catch (Exception e){
                        resturt.append("表格第"+step+"行宽非法数字。");
                    }
                }else {
                    productWidth = "0";
                }
                if(StringUtils.isNotBlank(productHigh)){
                    try{
                        Double.parseDouble(productHigh);
                    }catch (Exception e){
                        resturt.append("表格第"+step+"行高非法数字。");
                    }
                }else {
                    productHigh = "0";
                }
                if(StringUtils.isNotBlank(productVolume)){
                    try{
                        Double.parseDouble(productVolume);
                    }catch (Exception e){
                        resturt.append("表格第"+step+"行体积非法数字。");
                    }
                }else {
                    productVolume = "0";
                }
                if(StringUtils.isNotBlank(luggagemail)){
                    LuggageMailEntity luggageMailEntity = luggageMailDAO.selectLuggaageMailByName(luggagemail);
                    if(luggageMailEntity != null){
                        luggagemail = luggageMailEntity.getLuggagemailid();
                    }else{
                        resturt.append("表格第"+step+"行商品分类不存在。");
                    }
                }
                if(StringUtils.isBlank(resturt.toString())){
                    String userName = Jurisdiction.getUsername();
                    ProductEntity product = new ProductEntity();
                    product.setProductId(UuidUtil.get32UUID());
                    product.setProductnum(productnum);
                    product.setProductname(productname);
                    product.setProductename(productename);
                    product.setBarcodeMain(barcodeMain);
                    product.setBarcodeAuxiliary1(barcodeAuxiliary1);
                    product.setBarcodeAuxiliary2(barcodeAuxiliary2);
                    product.setBarcodeAuxiliary3(barcodeAuxiliary3);
                    product.setBarcodeAuxiliary4(barcodeAuxiliary4);
                    product.setBrandname(brandname);
                    product.setUnit(unit);
                    product.setStandard(standard);
                    product.setProducingArea(producingArea);
                    product.setExpirationDate(expirationDate);
                    product.setGrossWeight(Double.parseDouble(grossWeight));
                    product.setNetWeight(Double.parseDouble(netWeight));
                    product.setProductLength(Double.parseDouble(productLength));
                    product.setProductWidth(Double.parseDouble(productWidth));
                    product.setProductHigh(Double.parseDouble(productHigh));
                    product.setProductVolume(Double.parseDouble(productVolume));
                    product.setLuggagemail(luggagemail);
                    product.setRemark1(remark1);
                    product.setCreatetime(new Date());
                    product.setCreateuser(userName);
                    product.setUpdatetime(new Date());
                    product.setUpdateuser(userName);
                    product.setAuditStatus(0);
                    product.setBlockStatus(0);
                    productDAO.insertSelective(product);
                }else{
                    resturt.append("\n");

                }
            };
        }
        return resturt.toString();
    }

    @Override
    public String selectCountryNameByID(String countryId) {
        return productDAO.selectCountryNameByID(countryId);
    }


}
