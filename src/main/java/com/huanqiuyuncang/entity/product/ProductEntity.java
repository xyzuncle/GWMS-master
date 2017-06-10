package com.huanqiuyuncang.entity.product;

import java.util.Date;

public class ProductEntity {
    private String productId;

    private String image;

    private String productnum;

    private String productname;

    private String productename;

    private String barcodeMain;

    private String barcodeAuxiliary1;

    private String barcodeAuxiliary2;

    private String barcodeAuxiliary3;

    private String barcodeAuxiliary4;

    private String brandname;

    private String unit;

    private String standard;

    private String producingArea;

    private String expirationDate;

    private Double declarePrice;

    private Double retailPrice;

    private Double grossWeight;

    private Double netWeight;

    private Double productLength;

    private Double productWidth;

    private Double productHigh;

    private Double productVolume;

    private Double crossborderCourierfee;

    private String customscode;

    private String luggagemail;

    private String cartontypea;

    private Integer cartontypeanum;

    private String cartontypeb;

    private Integer cartontypebnum;

    private String defaultpackage;

    private String remark1;

    private String remark2;

    private String remark3;

    private String auditStatus;

    private String qinngguanchengben1;

    private String qinngguanchengben2;

    private String qinngguanchengben3;

    private String qinngguanchengben4;

    private String kuajingjiage1;

    private String kuajingjiage2;

    private String kuajingjiage3;

    private String kuajingjiage4;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    public ProductEntity(String productId, String image, String productnum, String productname, String productename, String barcodeMain, String barcodeAuxiliary1, String barcodeAuxiliary2, String barcodeAuxiliary3, String barcodeAuxiliary4, String brandname, String unit, String standard, String producingArea, String expirationDate, Double declarePrice, Double retailPrice, Double grossWeight, Double netWeight, Double productLength, Double productWidth, Double productHigh, Double productVolume, Double crossborderCourierfee, String customscode, String luggagemail, String cartontypea, Integer cartontypeanum, String cartontypeb, Integer cartontypebnum, String defaultpackage, String remark1, String remark2, String remark3, String auditStatus, String qinngguanchengben1, String qinngguanchengben2, String qinngguanchengben3, String qinngguanchengben4, String kuajingjiage1, String kuajingjiage2, String kuajingjiage3, String kuajingjiage4, String createuser, Date createtime, String updateuser, Date updatetime) {
        this.productId = productId;
        this.image = image;
        this.productnum = productnum;
        this.productname = productname;
        this.productename = productename;
        this.barcodeMain = barcodeMain;
        this.barcodeAuxiliary1 = barcodeAuxiliary1;
        this.barcodeAuxiliary2 = barcodeAuxiliary2;
        this.barcodeAuxiliary3 = barcodeAuxiliary3;
        this.barcodeAuxiliary4 = barcodeAuxiliary4;
        this.brandname = brandname;
        this.unit = unit;
        this.standard = standard;
        this.producingArea = producingArea;
        this.expirationDate = expirationDate;
        this.declarePrice = declarePrice;
        this.retailPrice = retailPrice;
        this.grossWeight = grossWeight;
        this.netWeight = netWeight;
        this.productLength = productLength;
        this.productWidth = productWidth;
        this.productHigh = productHigh;
        this.productVolume = productVolume;
        this.crossborderCourierfee = crossborderCourierfee;
        this.customscode = customscode;
        this.luggagemail = luggagemail;
        this.cartontypea = cartontypea;
        this.cartontypeanum = cartontypeanum;
        this.cartontypeb = cartontypeb;
        this.cartontypebnum = cartontypebnum;
        this.defaultpackage = defaultpackage;
        this.remark1 = remark1;
        this.remark2 = remark2;
        this.remark3 = remark3;
        this.auditStatus = auditStatus;
        this.qinngguanchengben1 = qinngguanchengben1;
        this.qinngguanchengben2 = qinngguanchengben2;
        this.qinngguanchengben3 = qinngguanchengben3;
        this.qinngguanchengben4 = qinngguanchengben4;
        this.kuajingjiage1 = kuajingjiage1;
        this.kuajingjiage2 = kuajingjiage2;
        this.kuajingjiage3 = kuajingjiage3;
        this.kuajingjiage4 = kuajingjiage4;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
    }

    public ProductEntity() {
        super();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum == null ? null : productnum.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductename() {
        return productename;
    }

    public void setProductename(String productename) {
        this.productename = productename == null ? null : productename.trim();
    }

    public String getBarcodeMain() {
        return barcodeMain;
    }

    public void setBarcodeMain(String barcodeMain) {
        this.barcodeMain = barcodeMain == null ? null : barcodeMain.trim();
    }

    public String getBarcodeAuxiliary1() {
        return barcodeAuxiliary1;
    }

    public void setBarcodeAuxiliary1(String barcodeAuxiliary1) {
        this.barcodeAuxiliary1 = barcodeAuxiliary1 == null ? null : barcodeAuxiliary1.trim();
    }

    public String getBarcodeAuxiliary2() {
        return barcodeAuxiliary2;
    }

    public void setBarcodeAuxiliary2(String barcodeAuxiliary2) {
        this.barcodeAuxiliary2 = barcodeAuxiliary2 == null ? null : barcodeAuxiliary2.trim();
    }

    public String getBarcodeAuxiliary3() {
        return barcodeAuxiliary3;
    }

    public void setBarcodeAuxiliary3(String barcodeAuxiliary3) {
        this.barcodeAuxiliary3 = barcodeAuxiliary3 == null ? null : barcodeAuxiliary3.trim();
    }

    public String getBarcodeAuxiliary4() {
        return barcodeAuxiliary4;
    }

    public void setBarcodeAuxiliary4(String barcodeAuxiliary4) {
        this.barcodeAuxiliary4 = barcodeAuxiliary4 == null ? null : barcodeAuxiliary4.trim();
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname == null ? null : brandname.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea == null ? null : producingArea.trim();
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate == null ? null : expirationDate.trim();
    }

    public Double getDeclarePrice() {
        return declarePrice;
    }

    public void setDeclarePrice(Double declarePrice) {
        this.declarePrice = declarePrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getProductLength() {
        return productLength;
    }

    public void setProductLength(Double productLength) {
        this.productLength = productLength;
    }

    public Double getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Double productWidth) {
        this.productWidth = productWidth;
    }

    public Double getProductHigh() {
        return productHigh;
    }

    public void setProductHigh(Double productHigh) {
        this.productHigh = productHigh;
    }

    public Double getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(Double productVolume) {
        this.productVolume = productVolume;
    }

    public Double getCrossborderCourierfee() {
        return crossborderCourierfee;
    }

    public void setCrossborderCourierfee(Double crossborderCourierfee) {
        this.crossborderCourierfee = crossborderCourierfee;
    }

    public String getCustomscode() {
        return customscode;
    }

    public void setCustomscode(String customscode) {
        this.customscode = customscode == null ? null : customscode.trim();
    }

    public String getLuggagemail() {
        return luggagemail;
    }

    public void setLuggagemail(String luggagemail) {
        this.luggagemail = luggagemail == null ? null : luggagemail.trim();
    }

    public String getCartontypea() {
        return cartontypea;
    }

    public void setCartontypea(String cartontypea) {
        this.cartontypea = cartontypea == null ? null : cartontypea.trim();
    }

    public Integer getCartontypeanum() {
        return cartontypeanum;
    }

    public void setCartontypeanum(Integer cartontypeanum) {
        this.cartontypeanum = cartontypeanum;
    }

    public String getCartontypeb() {
        return cartontypeb;
    }

    public void setCartontypeb(String cartontypeb) {
        this.cartontypeb = cartontypeb == null ? null : cartontypeb.trim();
    }

    public Integer getCartontypebnum() {
        return cartontypebnum;
    }

    public void setCartontypebnum(Integer cartontypebnum) {
        this.cartontypebnum = cartontypebnum;
    }

    public String getDefaultpackage() {
        return defaultpackage;
    }

    public void setDefaultpackage(String defaultpackage) {
        this.defaultpackage = defaultpackage == null ? null : defaultpackage.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getQinngguanchengben1() {
        return qinngguanchengben1;
    }

    public void setQinngguanchengben1(String qinngguanchengben1) {
        this.qinngguanchengben1 = qinngguanchengben1 == null ? null : qinngguanchengben1.trim();
    }

    public String getQinngguanchengben2() {
        return qinngguanchengben2;
    }

    public void setQinngguanchengben2(String qinngguanchengben2) {
        this.qinngguanchengben2 = qinngguanchengben2 == null ? null : qinngguanchengben2.trim();
    }

    public String getQinngguanchengben3() {
        return qinngguanchengben3;
    }

    public void setQinngguanchengben3(String qinngguanchengben3) {
        this.qinngguanchengben3 = qinngguanchengben3 == null ? null : qinngguanchengben3.trim();
    }

    public String getQinngguanchengben4() {
        return qinngguanchengben4;
    }

    public void setQinngguanchengben4(String qinngguanchengben4) {
        this.qinngguanchengben4 = qinngguanchengben4 == null ? null : qinngguanchengben4.trim();
    }

    public String getKuajingjiage1() {
        return kuajingjiage1;
    }

    public void setKuajingjiage1(String kuajingjiage1) {
        this.kuajingjiage1 = kuajingjiage1 == null ? null : kuajingjiage1.trim();
    }

    public String getKuajingjiage2() {
        return kuajingjiage2;
    }

    public void setKuajingjiage2(String kuajingjiage2) {
        this.kuajingjiage2 = kuajingjiage2 == null ? null : kuajingjiage2.trim();
    }

    public String getKuajingjiage3() {
        return kuajingjiage3;
    }

    public void setKuajingjiage3(String kuajingjiage3) {
        this.kuajingjiage3 = kuajingjiage3 == null ? null : kuajingjiage3.trim();
    }

    public String getKuajingjiage4() {
        return kuajingjiage4;
    }

    public void setKuajingjiage4(String kuajingjiage4) {
        this.kuajingjiage4 = kuajingjiage4 == null ? null : kuajingjiage4.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    private String formatCreateTime;

    private String formateUpdateTime;

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getFormateUpdateTime() {
        return formateUpdateTime;
    }

    public void setFormateUpdateTime(String formateUpdateTime) {
        this.formateUpdateTime = formateUpdateTime;
    }
}