<%--
  Created by IntelliJ IDEA.
  User: lzf
  Date: 2017/4/3
  Time: 8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <!-- 下拉框 -->
    <link rel="stylesheet" href="static/ace/css/chosen.css" />
    <!-- jsp文件头和头部 -->
    <%@ include file="../../system/index/top.jsp"%>

</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">

                        <form action="product/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="productId" id="productId" value="${product.productId}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <c:if test="${QX.commonForm == 1 }">
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">图片:</td>
                                        <td colspan="3">
                                            <img id='imgsImgSrc' src="${product.image}" height="100" width="100" />
                                            <input type="hidden" id="image" name="image" value="${product.image}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">货号:</td>
                                        <td><input type="text" name="productnum" readonly id="productnum" onblur="checkProductNum()" value="${product.productnum}" maxlength="30" placeholder="这里输入货号" title="货号" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">商品名称:</td>
                                        <td><input type="text" name="productname" readonly id="productname" value="${product.productname}" maxlength="30" placeholder="这里输入商品名称" title="商品名称" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">外文名称:</td>
                                        <td><input  type="text" name="productename" readonly id="productename" value="${product.productename}" maxlength="50" placeholder="这里输入外文名称" title="外文名称" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">品牌:</td>
                                        <td>
                                            <input  type="text" name="brandname" readonly id="brandname" value="${product.brandname}"  style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">主条码:</td>
                                        <td><input type="text" name="barcodeMain"readonly id="barcodeMain" value="${product.barcodeMain}" maxlength="255" placeholder="这里输入主条码" title="主条码" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码1:</td>
                                        <td><input type="text" name="barcodeAuxiliary1" readonly id="barcodeAuxiliary1"  value="${product.barcodeAuxiliary1}" maxlength="255" placeholder="这里输入辅助条码1" title="辅助条码1" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码2:</td>
                                        <td><input type="text" name="barcodeAuxiliary2" readonly id="barcodeAuxiliary2" value="${product.barcodeAuxiliary2}" maxlength="255" placeholder="这里输入辅助条码2" title="辅助条码2" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码3:</td>
                                        <td><input type="text" name="barcodeAuxiliary3" readonly id="barcodeAuxiliary3"  value="${product.barcodeAuxiliary3}" maxlength="255" placeholder="这里输入辅助条码3" title="辅助条码3" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码4:</td>
                                        <td><input type="text" name="barcodeAuxiliary4" readonly id="barcodeAuxiliary4" value="${product.barcodeAuxiliary4}" maxlength="255" placeholder="这里输入辅助条码4" title="辅助条码4" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">单位:</td>
                                        <td><input type="text" name="unit" id="unit" readonly value="${product.unit}" maxlength="255" placeholder="这里输入单位" title="单位" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">规格:</td>
                                        <td><input type="text" name="standard" id="standard" readonly value="${product.standard}" maxlength="255" placeholder="这里输入规格" title="规格" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">产地:</td>
                                        <td>
                                            <input  type="text" name="producingArea" readonly id="producingArea" value="${product.producingArea}"  style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">保质期（天）:</td>
                                        <td><input type="text" name="expirationDate" readonly id="expirationDate" value="${product.expirationDate}" maxlength="255" placeholder="这里输入保质期" title="保质期" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">毛重:</td>
                                        <td><input type="number" name="grossWeight" readonly id="grossWeight" value="${product.grossWeight}" maxlength="255" placeholder="这里输入毛重" title="毛重" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">净重:</td>
                                        <td><input type="number" name="netWeight" readonly id="netWeight" value="${product.netWeight}" maxlength="255" placeholder="这里输入净重" title="净重" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">长:</td>
                                        <td><input type="number" name="productLength" readonly id="productLength"  value="${product.productLength}" maxlength="255" placeholder="这里输入长" title="长" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">宽:</td>
                                        <td><input type="number" name="productWidth" id="productWidth" readonly value="${product.productWidth}" maxlength="255" placeholder="这里输入宽" title="宽" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">高:</td>
                                        <td><input type="number" name="productHigh" id="productHigh" readonly value="${product.productHigh}" maxlength="255" placeholder="这里输入高" title="高" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">体积:</td>
                                        <td colspan="3"><input type="number" name="productVolume" readonly id="productVolume" value="${product.productVolume}" maxlength="255" placeholder="这里输入体积" title="体积" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td colspan="3">
                                            <textarea rows="5" cols="10" id="remark1" name="remark1" readonly style="width:98%;"  title="备注">${product.remark1}</textarea>
                                        </td>
                                    </tr>
                                    </c:if>
                                    <c:if test="${QX.privateFrom == 1 }">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">跨境速递费:</td>
                                        <td colspan="3"><input type="number" readonly name="crossborderCourierfee" id="crossborderCourierfee" value="${product.crossborderCourierfee}" maxlength="255" placeholder="这里输入跨境速递费" title="跨境速递费" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">零售价（元）:</td>
                                        <td><input type="number" name="retailPrice" readonly id="retailPrice" value="${product.retailPrice}" maxlength="255" placeholder="这里输入零售价" title="零售价" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">申报价（元）:</td>
                                        <td><input type="number" name="declarePrice" id="declarePrice" value="${product.declarePrice}" maxlength="255" placeholder="这里输入申报价" title="申报价" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">海关编码:</td>
                                        <td>
                                            <input  type="text" name="customscode" readonly id="customscode" value="${product.customscode}"  style="width:98%;"/>
                                        </td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">商品分类:</td>
                                        <td>
                                            <input  type="text" name="luggagemail" readonly id="luggagemail" value="${product.luggagemail}"  style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">默认纸箱型号A:</td>
                                         <td>
                                             <input  type="text" name="cartontypea" readonly id="cartontypea" value="${product.cartontypea}"  style="width:98%;"/>
                                         </td>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">可打包数量A:</td>
                                         <td><input type="number" name="cartontypeanum" readonly id="cartontypeanum" value="${product.cartontypeanum}" maxlength="255" placeholder="这里输入可打包数量A" title="可打包数量A" style="width:98%;"/></td>
                                     </tr>
                                     <tr>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">默认纸箱型号B:</td>
                                         <td>
                                             <input  type="text" name="cartontypeb" readonly id="cartontypeb" value="${product.cartontypeb}"  style="width:98%;"/>
                                         </td>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">可打包数量B:</td>
                                         <td><input type="number" name="cartontypebnum" readonly id="cartontypebnum" value="${product.cartontypebnum}" maxlength="255" placeholder="这里输入可打包数量B" title="可打包数量B" style="width:98%;"/></td>
                                     </tr>
                                    <tr>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">默认包装类型:</td>
                                         <td colspan="3">
                                             <input  type="text" name="defaultpackage" readonly id="defaultpackage" value="${product.defaultpackage}"  style="width:98%;"/>
                                         </td>
                                     </tr>

                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注1:</td>
                                        <td colspan="3" >
                                            <textarea rows="5" cols="10" id="remark2" readonly name="remark2" style="width:98%;"  title="备注1">${product.remark2}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注2:</td>
                                        <td colspan="3">
                                            <textarea rows="5" cols="10" id="remark3"  readonly name="remark3" style="width:98%;"  title="备注2">${product.remark3}</textarea>
                                        </td>
                                    </tr>
                                        <tr>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">创建者:</td>
                                            <td >
                                                <input type="text" readonly name="createuser" id="createuser" value="${product.createuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">创建时间:</td>
                                            <td >
                                                <input type="text" readonly name="createtime" id="createtime" value="${product.formatCreateTime}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">修改者:</td>
                                            <td >
                                                <input type="text" readonly name="updateuser" id="updateuser" value="${product.updateuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">修改时间:</td>
                                            <td >
                                                <input type="text" readonly name="updatetime" id="updatetime" value="${product.formateUpdateTime}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                        </tr>
                                    </c:if>

                                </table>
                            </div>
                        </form>

                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->


<!-- 页面底部js¨ -->
<%@ include file="../../system/index/foot.jsp"%>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<script type="text/javascript" src="static/ace/js/jquery.form.js"></script>
<!-- 下拉框 -->
<script src="static/ace/js/chosen.jquery.js"></script>
<script type="text/javascript">
    $(top.hangge());
</script>
</body>
</html>