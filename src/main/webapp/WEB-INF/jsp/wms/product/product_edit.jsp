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
                                            <input type='file' size='27' id='imgsFile' name='imgsFile' class="file" onchange='submitUpload()' />
                                            <input type="hidden" id="image" name="image" value="${product.image}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">货号:</td>
                                        <td><input type="text" name="productnum" id="productnum" onblur="checkProductNum()" value="${product.productnum}" maxlength="30" placeholder="这里输入货号" title="货号" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">商品名称:</td>
                                        <td><input type="text" name="productname" id="productname" value="${product.productname}" maxlength="30" placeholder="这里输入商品名称" title="商品名称" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">外文名称:</td>
                                        <td><input  type="text" name="productename" id="productename" value="${product.productename}" maxlength="50" placeholder="这里输入外文名称" title="外文名称" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">品牌:</td>
                                        <td>
                                            <select class="chosen-select form-control" name="brandname" id="brandname" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty brandList}">
                                                        <c:forEach items="${brandList}" var="brand" varStatus="brandStatus">

                                                                    <option value="${brand.brandid}" id="${brand.brandid}"
                                                                            <c:if test="${brand.brandid == product.brandname}">
                                                                            selected="selected"
                                                                            </c:if>
                                                                    >${brand.brandname}</option>

                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">主条码:</td>
                                        <td><input type="text" name="barcodeMain" id="barcodeMain" onblur="checkBarcode(this)" value="${product.barcodeMain}" maxlength="255" placeholder="这里输入主条码" title="主条码" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码1:</td>
                                        <td><input type="text" name="barcodeAuxiliary1" id="barcodeAuxiliary1" onblur="checkBarcode(this)" value="${product.barcodeAuxiliary1}" maxlength="255" placeholder="这里输入辅助条码1" title="辅助条码1" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码2:</td>
                                        <td><input type="text" name="barcodeAuxiliary2" id="barcodeAuxiliary2" onblur="checkBarcode(this)" value="${product.barcodeAuxiliary2}" maxlength="255" placeholder="这里输入辅助条码2" title="辅助条码2" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码3:</td>
                                        <td><input type="text" name="barcodeAuxiliary3" id="barcodeAuxiliary3" onblur="checkBarcode(this)" value="${product.barcodeAuxiliary3}" maxlength="255" placeholder="这里输入辅助条码3" title="辅助条码3" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">辅助条码4:</td>
                                        <td><input type="text" name="barcodeAuxiliary4" id="barcodeAuxiliary4" onblur="checkBarcode(this)" value="${product.barcodeAuxiliary4}" maxlength="255" placeholder="这里输入辅助条码4" title="辅助条码4" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">单位:</td>
                                        <td><input type="text" name="unit" id="unit" value="${product.unit}" maxlength="255" placeholder="这里输入单位" title="单位" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">规格:</td>
                                        <td><input type="text" name="standard" id="standard" value="${product.standard}" maxlength="255" placeholder="这里输入规格" title="规格" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">产地:</td>
                                        <td>
                                            <select class="chosen-select form-control" name="producingArea" id="producingArea" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty producingAreaList}">
                                                        <c:forEach items="${producingAreaList}" var="producingArea" varStatus="producingAreaStatus">

                                                                    <option value="${producingArea.COUNTRY_ID}" id="${producingArea.COUNTRY_ID}"
                                                                            <c:if test="${producingArea.COUNTRY_ID == product.producingArea}">
                                                                            selected="selected"</c:if>>${producingArea.COUN_C_NAME}</option>

                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">保质期（天）:</td>
                                        <td><input type="text" name="expirationDate" id="expirationDate" value="${product.expirationDate}" maxlength="255" placeholder="这里输入保质期" title="保质期" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">毛重:</td>
                                        <td><input type="number" name="grossWeight" id="grossWeight" value="${product.grossWeight}" maxlength="255" placeholder="这里输入毛重" title="毛重" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">净重:</td>
                                        <td><input type="number" name="netWeight" id="netWeight" value="${product.netWeight}" maxlength="255" placeholder="这里输入净重" title="净重" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">长:</td>
                                        <td><input type="number" name="productLength" id="productLength"  onblur="volume()" value="${product.productLength}" maxlength="255" placeholder="这里输入长" title="长" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">宽:</td>
                                        <td><input type="number" name="productWidth" id="productWidth"  onblur="volume()" value="${product.productWidth}" maxlength="255" placeholder="这里输入宽" title="宽" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">高:</td>
                                        <td><input type="number" name="productHigh" id="productHigh" onblur="volume()" value="${product.productHigh}" maxlength="255" placeholder="这里输入高" title="高" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">体积:</td>
                                        <td colspan="3"><input type="number" name="productVolume" id="productVolume" value="${product.productVolume}" maxlength="255" placeholder="这里输入体积" title="体积" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td colspan="3">
                                            <textarea rows="5" cols="10" id="remark1" name="remark1" style="width:98%;"  title="备注">${product.remark1}</textarea>
                                        </td>
                                    </tr>
                                    </c:if>
                                    <c:if test="${QX.privateFrom == 1 }">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">跨境速递费:</td>
                                        <td colspan="3"><input type="number" name="crossborderCourierfee" id="crossborderCourierfee" value="${product.crossborderCourierfee}" maxlength="255" placeholder="这里输入跨境速递费" title="跨境速递费" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">零售价（元）:</td>
                                        <td><input type="number" name="retailPrice" id="retailPrice" value="${product.retailPrice}" maxlength="255" placeholder="这里输入零售价" title="零售价" style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">申报价（元）:</td>
                                        <td><input type="number" name="declarePrice" id="declarePrice" value="${product.declarePrice}" maxlength="255" placeholder="这里输入申报价" title="申报价" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">海关编码:</td>
                                        <td>
                                            <select class="chosen-select form-control" name="customscode" id="customscode" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty customsList}">
                                                        <c:forEach items="${customsList}" var="customs" varStatus="customsStatus">
                                                                    <option value="${customs.customsid}" id="${customs.customsid}"
                                                                            <c:if test="${customs.customsid== product.customscode}">
                                                                            selected="selected"  </c:if>>${customs.customsname}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">行邮分类:</td>
                                        <td>
                                            <select class="chosen-select form-control" name="luggagemail" id="luggagemail" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                <option value="">请选择</option>

                                                <c:choose>
                                                    <c:when test="${not empty luggageMailList}">
                                                        <c:forEach items="${luggageMailList}" var="luggageMail" varStatus="luggageMailStatus">
                                                                    <option value="${luggageMail.luggagemailid}" id="${luggageMail.luggagemailid}"
                                                                            <c:if test="${luggageMail.luggagemailid == product.luggagemail}">
                                                                            selected="selected" </c:if>>${luggageMail.luggagemailname}</option>
s                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">默认纸箱型号A:</td>
                                         <td>
                                             <select class="chosen-select form-control" name="cartontypea" id="cartontypea" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                 <option value="">请选择</option>
                                                 <c:choose>
                                                     <c:when test="${not empty cartonList}">
                                                         <c:forEach items="${cartonList}" var="carton" varStatus="cartonStatus">
                                                                     <option value="${carton.cartonid}" id="${carton.cartonid}"
                                                                             <c:if test="${carton.cartonid == product.cartontypea }">
                                                                             selected="selected"</c:if>>${carton.cartonname}</option>

                                                         </c:forEach>
                                                     </c:when>
                                                 </c:choose>
                                             </select>
                                         </td>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">可打包数量A:</td>
                                         <td><input type="number" name="cartontypeanum" id="cartontypeanum" value="${product.cartontypeanum}" maxlength="255" placeholder="这里输入可打包数量A" title="可打包数量A" style="width:98%;"/></td>
                                     </tr>
                                     <tr>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">默认纸箱型号B:</td>
                                         <td>
                                             <select class="chosen-select form-control" name="cartontypeb" id="cartontypeb" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                 <option value="">请选择</option>
                                                 <c:choose>
                                                     <c:when test="${not empty cartonList}">
                                                         <c:forEach items="${cartonList}" var="carton" varStatus="cartonStatus">
                                                                     <option value="${carton.cartonid}" id="${carton.cartonid}"
                                                                             <c:if test="${ carton.cartonid == product.cartontypeb }">
                                                                             selected="selected" </c:if>>${carton.cartonname}</option>

                                                         </c:forEach>
                                                     </c:when>
                                                 </c:choose>
                                             </select>
                                         </td>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">可打包数量B:</td>
                                         <td><input type="number" name="cartontypebnum" id="cartontypebnum" value="${product.cartontypebnum}" maxlength="255" placeholder="这里输入可打包数量B" title="可打包数量B" style="width:98%;"/></td>
                                     </tr>
                                    <tr>
                                         <td style="width:82px;text-align: right;padding-top: 13px;">默认包装类型:</td>
                                         <td colspan="3">
                                             <select class="chosen-select form-control" name="defaultpackage" id="defaultpackage" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                 <option value="">请选择</option>
                                                 <c:choose>
                                                     <c:when test="${not empty packageList}">
                                                         <c:forEach items="${packageList}" var="packagetype" varStatus="packageStatus">
                                                     <option value="${packagetype.packageid}" id="${packagetype.packageid}"
                                                             <c:if test="${packagetype.packageid == product.defaultpackage }">
                                                             selected="selected"
                                                             </c:if>>${packagetype.packagename}</option>

                                                         </c:forEach>
                                                     </c:when>
                                                 </c:choose>
                                             </select>
                                         </td>
                                     </tr>

                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注1:</td>
                                        <td colspan="3" >
                                            <textarea rows="5" cols="10" id="remark2" name="remark2" style="width:98%;"  title="备注1">${product.remark2}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注2:</td>
                                        <td colspan="3">
                                            <textarea rows="5" cols="10" id="remark3"  name="remark3" style="width:98%;"  title="备注2">${product.remark3}</textarea>
                                        </td>
                                    </tr>
                                        <tr>
                                            <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">创建者:</td>
                                            <td >
                                                <input type="text" disabled name="createuser" id="createuser" value="${product.createuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                            <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">创建时间:</td>
                                            <td >
                                                <input type="text" disabled name="createtime" id="createtime" value="${product.createtime}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">修改者:</td>
                                            <td >
                                                <input type="text" disabled name="updateuser" id="updateuser" value="${product.updateuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                            <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">修改时间:</td>
                                            <td >
                                                <input type="text" disabled name="updatetime" id="updatetime" value="${product.updatetime}" maxlength="255" title="申报价" style="width:98%;"/>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <tr>
                                        <td style="text-align: center;" colspan="10">
                                            <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
                                            <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
                        </form>

                        <div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
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
    $(function(){
        //下拉框
        if(!ace.vars['touch']) {
            $('.chosen-select').chosen({allow_single_deselect:true});
            $(window)
                    .off('resize.chosen')
                    .on('resize.chosen', function() {
                        $('.chosen-select').each(function() {
                            var $this = $(this);
                            $this.next().css({'width': $this.parent().width()});
                        });
                    }).trigger('resize.chosen');
            $(document).on('settings.ace.chosen', function(e, event_name, event_val) {
                if(event_name != 'sidebar_collapsed') return;
                $('.chosen-select').each(function() {
                    var $this = $(this);
                    $this.next().css({'width': $this.parent().width()});
                });
            });
            $('#chosen-multiple-style .btn').on('click', function(e){
                var target = $(this).find('input[type=radio]');
                var which = parseInt(target.val());
                if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
                else $('#form-field-select-4').removeClass('tag-input-style');
            });
        }
    });
    //保存
    function save(){
        if($("#productnum").val()==''){
            $("#productnum").tips({
                side:3,
                msg:'请输入货号',
                bg:'#AE81FF',
                time:2
            });
            $("#productnum").focus();
            return false;
        }
        if($("#productname").val()==''){
            $("#productname").tips({
                side:3,
                msg:'请输入商品名称',
                bg:'#AE81FF',
                time:2
            });
            $("#productname").focus();
            return false;
        }
        if($("#barcodeMain").val()==''){
            $("#barcodeMain").tips({
                side:3,
                msg:'请输入主条码',
                bg:'#AE81FF',
                time:2
            });
            $("#barcodeMain").focus();
            return false;
        }
        if($("#producingArea").val()==''){
            $("#producingArea").tips({
                side:3,
                msg:'请输入产地',
                bg:'#AE81FF',
                time:2
            });
            $("#producingArea").focus();
            return false;
        }
        if($("#declarePrice").val() != '' && isNaN($("#declarePrice").val()) ){
            $("#declarePrice").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#declarePrice").focus();
            return false;
        }
        if($("#retailPrice").val() != '' && isNaN($("#retailPrice").val()) ){
            $("#retailPrice").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#retailPrice").focus();
            return false;
        }
        if($("#grossWeight").val() != '' && isNaN($("#grossWeight").val()) ){
            $("#grossWeight").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#grossWeight").focus();
            return false;
        }
        if($("#netWeight").val() != '' && isNaN($("#netWeight").val()) ){
            $("#netWeight").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#netWeight").focus();
            return false;
        }
        if($("#productLength").val() != '' && isNaN($("#productLength").val()) ){
            $("#productLength").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#productLength").focus();
            return false;
        }
        if($("#productWidth").val() != '' && isNaN($("#productWidth").val()) ){
            $("#productWidth").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#productWidth").focus();
            return false;
        }
        if($("#productHigh").val() != '' && isNaN($("#productHigh").val()) ){
            $("#productHigh").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#productHigh").focus();
            return false;
        }
        if($("#productVolume").val() != '' && isNaN($("#productVolume").val()) ){
            $("#productVolume").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#productVolume").focus();
            return false;
        }
        if($("#crossborderCourierfee").val() != '' && isNaN($("#crossborderCourierfee").val()) ){
            $("#crossborderCourierfee").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#crossborderCourierfee").focus();
            return false;
        }
        if($("#cartontypeanum").val() != '' && isNaN($("#cartontypeanum").val()) ){
            $("#cartontypeanum").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#cartontypeanum").focus();
            return false;
        }
        if($("#cartontypebnum").val() != '' && isNaN($("#cartontypebnum").val()) ){
            $("#cartontypebnum").tips({
                side:3,
                msg:'请输入数字',
                bg:'#AE81FF',
                time:2
            });
            $("#cartontypebnum").focus();
            return false;
        }
        if($("#barcodeAuxiliary1").val()!=''){
           var barcodeMain =  $("#barcodeMain").val();
           var barcodeAuxiliary1 =  $("#barcodeAuxiliary1").val();
           var barcodeAuxiliary2 =  $("#barcodeAuxiliary2").val();
           var barcodeAuxiliary3 =  $("#barcodeAuxiliary3").val();
           var barcodeAuxiliary4 =  $("#barcodeAuxiliary4").val();
            if(barcodeAuxiliary1==barcodeAuxiliary2 || barcodeAuxiliary1 == barcodeMain
                    || barcodeAuxiliary1==barcodeAuxiliary3 || barcodeAuxiliary1 ==barcodeAuxiliary4){
                $("#barcodeAuxiliary1").tips({
                    side:3,
                    msg:'条码不能重复',
                    bg:'#AE81FF',
                    time:2
                });
                $("#barcodeAuxiliary1").focus();
                return false;
            }
        }
        if($("#barcodeAuxiliary2").val()!=''){
            var barcodeMain =  $("#barcodeMain").val();
            var barcodeAuxiliary1 =  $("#barcodeAuxiliary1").val();
            var barcodeAuxiliary2 =  $("#barcodeAuxiliary2").val();
            var barcodeAuxiliary3 =  $("#barcodeAuxiliary3").val();
            var barcodeAuxiliary4 =  $("#barcodeAuxiliary4").val();
            if(barcodeAuxiliary2==barcodeAuxiliary1 || barcodeAuxiliary2 == barcodeMain
                    || barcodeAuxiliary2==barcodeAuxiliary3 || barcodeAuxiliary2 ==barcodeAuxiliary4){
                $("#barcodeAuxiliary2").tips({
                    side:3,
                    msg:'条码不能重复',
                    bg:'#AE81FF',
                    time:2
                });
                $("#barcodeAuxiliary2").focus();
                return false;
            }
        }
        if($("#barcodeAuxiliary3").val()!=''){
            var barcodeMain =  $("#barcodeMain").val();
            var barcodeAuxiliary1 =  $("#barcodeAuxiliary1").val();
            var barcodeAuxiliary2 =  $("#barcodeAuxiliary2").val();
            var barcodeAuxiliary3 =  $("#barcodeAuxiliary3").val();
            var barcodeAuxiliary4 =  $("#barcodeAuxiliary4").val();
            if(barcodeAuxiliary3==barcodeAuxiliary1 || barcodeAuxiliary3 == barcodeMain
                    || barcodeAuxiliary3==barcodeAuxiliary2 || barcodeAuxiliary3 ==barcodeAuxiliary4){
                $("#barcodeAuxiliary3").tips({
                    side:3,
                    msg:'条码不能重复',
                    bg:'#AE81FF',
                    time:2
                });
                $("#barcodeAuxiliary3").focus();
                return false;
            }
        }
        if($("#barcodeAuxiliary4").val()!=''){
            var barcodeMain =  $("#barcodeMain").val();
            var barcodeAuxiliary1 =  $("#barcodeAuxiliary1").val();
            var barcodeAuxiliary2 =  $("#barcodeAuxiliary2").val();
            var barcodeAuxiliary3 =  $("#barcodeAuxiliary3").val();
            var barcodeAuxiliary4 =  $("#barcodeAuxiliary4").val();
            if(barcodeAuxiliary4==barcodeAuxiliary1 || barcodeAuxiliary4 == barcodeMain
                    || barcodeAuxiliary4==barcodeAuxiliary2 || barcodeAuxiliary4 ==barcodeAuxiliary3){
                $("#barcodeAuxiliary4").tips({
                    side:3,
                    msg:'条码不能重复',
                    bg:'#AE81FF',
                    time:2
                });
                $("#barcodeAuxiliary4").focus();
                return false;
            }
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }
    //图品上传
    function submitUpload(){
        var option = {
            url:"${pageContext.request.contextPath}/uploadPic.do",
            type:"post",
            dataType:"text",
            data:{
                fileName:"imgsFile"
            },
            success:function(responseText){


               var obj = $.parseJSON(responseText);
                $("#imgsImgSrc").attr("src", "${pageContext.request.contextPath}/upload/"+obj.realPath);
                $("#image").val("${pageContext.request.contextPath}/upload/"+obj.realPath);

            },
            error:function(){
                alert("系统错误");
            }
        }
        //以ajax的方式提交表单，如果option中指定url那么表单就不再提交到表单中action，而是提交option中的url上
        $("#Form").ajaxSubmit(option);
    }
    //检查条码
    function checkBarcode(obj){
        var productId = $("#productId").val();
        if(productId == ""){
            var barcode = $.trim($(obj).val());
            if(barcode != ""){
                $.ajax({
                    type: "POST",
                    url: '<%=basePath%>product/findProductByProductNum.do',
                    data: {barcode:barcode},
                    dataType:'json',
                    cache: false,
                    success: function(data){
                        if("success" != data.result){
                            $(obj).tips({
                                side:3,
                                msg:'条码'+barcode+' 已存在',
                                bg:'#AE81FF',
                                time:3
                            });
                            $(obj).val('');
                        }
                    }
                });
            }

        }
    }
    //检查货号
    function checkProductNum(){
        var productId = $("#productId").val();
        if(productId == ""){
            var productnum = $.trim($("#productnum").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>product/findProductByProductNum.do',
                data: {productnum:productnum},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" != data.result){
                        $("#productnum").tips({
                            side:3,
                            msg:'货号'+productnum+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#productnum").val('');
                    }
                }
            });
        }
    }

    //自动计算体积
    function volume(){
        var length = $("#productLength").val();
        var width = $("#productWidth").val();
        var high = $("#productHigh").val();
        if( length != ''&& width != ''&& high != ''){
            var volume = length*width*high;
            $("#productVolume").val(volume);
        }
    }
</script>
</body>
</html>