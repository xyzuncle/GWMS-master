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
    <!-- 日期框 -->
    <link rel="stylesheet" href="static/ace/css/datepicker.css" />
    <link href="static/ace/css/bootstrap-table.min.css" rel="stylesheet"/>

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

                        <form action="innerorder/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="innerorderid" id="innerorderid" value="${innerorder.innerorderid}"/>
                            <input type="hidden" name="token" id="token" value="${token}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">客户编号:</td>
                                        <td>
                                            <select class="chosen-select form-control"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                    name="customernum" id="customernum" data-placeholder="请选择" style="vertical-align:top;width:98%;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty customerList}">
                                                        <c:forEach items="${customerList}" var="customer" varStatus="customerStatus">
                                                            <option value="${customer.customercode}" id="${customer.customercode}"
                                                                    <c:if test="${customer.customercode == innerorder.customernum}">
                                                                        selected="selected"
                                                                    </c:if>
                                                            >${customer.customername}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">外部订单号:</td>
                                        <td><input type="text" name="outerordernum" id="outerordernum" value="${innerorder.outerordernum}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">下单时间:</td>
                                        <td><input type="text" class="date-picker" name="ordertime" id="ordertime" data-date-format="yyyy-mm-dd" readonly="readonly" value="${innerorder.formateOrderTime}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">寄件人姓名:</td>
                                        <td><input type="text" name="sender" id="sender" value="${innerorder.sender}" maxlength="30"style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">寄件人电话:</td>
                                        <td><input type="text" name="senderphone" id="senderphone"  value="${innerorder.senderphone}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">寄件人国别:</td>
                                        <td><input type="text" name="sendercountry" id="sendercountry" value="${innerorder.sendercountry}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">寄件人省|州:</td>
                                        <td><input type="text" name="senderprovince" id="senderprovince"  value="${innerorder.senderprovince}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">寄件人城市:</td>
                                        <td><input type="text" name="sendercity" id="sendercity" value="${innerorder.sendercity}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">寄件人区域:</td>
                                        <td><input type="text" name="senderarea" id="senderarea"  value="${innerorder.senderarea}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">寄件人邮编:</td>
                                        <td><input type="text" name="senderpostcode" id="senderpostcode"  value="${innerorder.senderpostcode}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">寄件人地址:</td>
                                        <td colspan="3"><input type="text" name="senderaddress" id="senderaddress" value="${innerorder.senderaddress}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人:</td>
                                        <td><input type="text" name="recipient" id="recipient"  value="${innerorder.recipient}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人身份证:</td>
                                        <td><input type="text" name="recipientidcard" id="recipientidcard"  value="${innerorder.recipientidcard}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人电话:</td>
                                        <td><input type="text" name="recipientphone" id="recipientphone"  value="${innerorder.recipientphone}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人国家:</td>
                                        <td>
                                            <select class="chosen-select form-control" style="vertical-align:top;width:98%;"  id="recipientcountry" name="recipientcountry" >
                                                <option value="china">中国</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人省:</td>
                                        <td>
                                            <select id="recipientprovince"  style="vertical-align:top;width:98%;" name="recipientprovince" onchange="changeprovince(this.value)">
                                                <option>请选择</option>
                                            </select>
                                            <input type="hidden" id="provinceHidden"  value="${innerorder.recipientprovince}" maxlength="30"  style="width:98%;"/>
                                        </td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人城市:</td>
                                        <td>
                                            <select id="recipientcity" style="vertical-align:top;width:98%;" name="recipientcity" onchange="changeCity(this.value)">
                                                <option>请选择</option>
                                            </select>
                                            <input type="hidden" id="cityHidden"  value="${innerorder.recipientcity}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人区:</td>
                                        <td>
                                            <select id="recipientarea" style="vertical-align:top;width:98%;" name="recipientarea" >
                                                <option>请选择</option>
                                            </select>
                                            <input type="hidden" id="areaHidden"  value="${innerorder.recipientarea}" maxlength="30"  style="width:98%;"/>
                                        </td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人邮编:</td>
                                        <td><input type="text" name="recipientpostcode" id="recipientpostcode"  value="${innerorder.recipientpostcode}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">收件人地址:</td>
                                        <td colspan="3"><input type="text" name="recipientaddress" id="recipientaddress"  value="${innerorder.recipientaddress}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">支付方式:</td>
                                        <td><input type="text" name="paymentmethod" id="paymentmethod"  value="${innerorder.paymentmethod}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">支付交易号:</td>
                                        <td><input type="text" name="paymentnum" id="paymentnum"  value="${innerorder.paymentnum}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">支付时间:</td>
                                        <td><input type="text" class="date-picker" name="paymenttime" id="paymenttime" data-date-format="yyyy-mm-dd" readonly="readonly" value="${innerorder.paymenttime}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">订单商品总件数:</td>
                                        <td><input type="text"
                                            <c:if test="${QX.adminOrder == 1 }">
                                                disabled
                                            </c:if> name="orderproductcount" id="orderproductcount"  value="${innerorder.orderproductcount}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">顾客备注:</td>
                                        <td><input type="text" name="customerremarks" id="customerremarks"  value="${innerorder.customerremarks}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">报关模式:</td>
                                        <td>
                                            <select class="chosen-select form-control" name="customsmodel" id="customsmodel" data-placeholder="请选择" style="vertical-align:top;width:98%;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty baoguanList}">
                                                        <c:forEach items="${baoguanList}" var="baoguan" varStatus="baoguanStatus">
                                                            <option value="${baoguan.BIANMA}" id="${baoguan.BIANMA}"
                                                                    <c:if test="${baoguan.BIANMA == innerorder.customsmodel}">
                                                                        selected="selected"
                                                                    </c:if>
                                                            >${baoguan.NAME}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <c:if test="${QX.adminOrder == 1 }">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">快递企业名称:</td>
                                        <td><input type="text" name="couriername" id="couriername" disabled value="${innerorder.couriername}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">快递单号:</td>
                                        <td><input type="text" name="couriernum" id="couriernum" disabled value="${innerorder.couriernum}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    </c:if>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">订单货值:</td>
                                        <td><input type="text" name="ordervalue" id="ordervalue"  value="${innerorder.ordervalue}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">申报货值:</td>
                                        <td><input type="text" name="declarevalue" id="declarevalue"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   value="${innerorder.declarevalue}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">税费:</td>
                                        <td><input type="text" name="taxesfees" id="taxesfees"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   value="${innerorder.taxesfees}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">运费:</td>
                                        <td><input type="text" name="freight" id="freight"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   value="${innerorder.freight}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">包裹费用:</td>
                                        <td><input type="text" name="packingcost" id="packingcost"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   value="${innerorder.packingcost}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">其他费用:</td>
                                        <td><input type="text" name="orthercost" id="orthercost"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   value="${innerorder.orthercost}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">发货仓库:</td>
                                        <td><input type="text" name="warehousedelivery" id="warehousedelivery"
                                                <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   value="${innerorder.warehousedelivery}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">订单状态:</td>
                                        <td>
                                            <select class="chosen-select form-control"
                                                    <c:if test="${QX.adminOrder == 1 && msg == 'edit' }">
                                                        disabled
                                                    </c:if>
                                                    name="orderstatus" id="orderstatus" data-placeholder="请选择" style="vertical-align:top;width:98%;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty orderStatusList}">
                                                        <c:forEach items="${orderStatusList}" var="orderstatus" varStatus="orderstatusInex">
                                                            <c:if test="${orderstatusInex.index < 3 }">
                                                            <option value="${orderstatus.BIANMA}" id="${orderstatus.BIANMA}"
                                                                    <c:if test="${orderstatus.BIANMA == innerorder.orderstatus}">
                                                                        selected="selected"
                                                                    </c:if>
                                                            >${orderstatus.NAME}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td colspan="3" >
                                            <textarea rows="5" cols="10" id="remark" name="remark" style="width:98%;"  title="备注1">${innerorder.remark}</textarea>
                                        </td>
                                    </tr>
                                    <c:if test="${QX.adminOrder == 1 }">
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注1:</td>
                                        <td colspan="3" >
                                            <textarea rows="5" cols="10" id="remark1" name="remark1" style="width:98%;"  title="备注1">${innerorder.remark1}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注2:</td>
                                        <td colspan="3">
                                            <textarea rows="5" cols="10" id="remark2"  name="remark2" style="width:98%;"  title="备注2">${innerorder.remark2}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">创建者:</td>
                                        <td >
                                            <input type="text" disabled name="createuser" id="createuser" value="${innerorder.createuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">创建时间:</td>
                                        <td >
                                            <input type="text" disabled name="createtime" id="createtime" value="${innerorder.formatCreateTime}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">修改者:</td>
                                        <td >
                                            <input type="text" disabled name="updateuser" id="updateuser" value="${innerorder.updateuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">修改时间:</td>
                                        <td >
                                            <input type="text" disabled name="updatetime" id="updatetime" value="${innerorder.formateUpdateTime}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                    </tr>
                                    </c:if>
                                    <tr>
                                        <td colspan="4">
                                            <a class="btn btn-sm btn-success" onclick="addProduct();">添加商品</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table id="orderpdTable" data-height="200"  class="table table-bordered"></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: center;" colspan="10">
                                            <a class="btn btn-mini btn-primary" onclick="save();">保存</a>
                                            <a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form>
                            <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>


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

<script type="text/javascript" src="static/ace/js/bootstrap-table.js"></script>
<script type="text/javascript" src="static/ace/js/bootstrap-table-zh-CN.js"></script>
<!-- 下拉框 -->
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    $(top.hangge());
    $(function(){


        $.ajax({
            type: "POST",
            url: '<%=basePath%>innerorder/getprovince.do?tm='+new Date().getTime(),
            data: {},
            dataType:'json',
            cache: false,
            success: function(data){
                $("#recipientprovince").html('<option>请选择</option>');
                $.each(data.list, function(i, dvar){
                    $("#recipientprovince").append("<option value="+dvar.provinceid+" id="+dvar.provinceid+">"+dvar.province+"</option>");
                });
                var provinceHidden = $("#provinceHidden").val();
                if(provinceHidden != ''){
                    $("#"+provinceHidden).attr("selected","selected")
                    changeprovince(provinceHidden);
                }
            }
        });



        //日期框
        $('.date-picker').datepicker({autoclose: true,todayHighlight: true});
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
        var option = {
            url: '${pageContext.request.contextPath}/innerorder/orderpdlist.do', //请求地址
            columns: [
                {
                    field : 'outerordernum',
                    align : "center",
                    title : '外部订单号'
                },
                {
                    field : 'outerproductnum',
                    align : "center",
                    title : '外部货号'
                },
                {
                    field : 'barcode',
                    align : "center",
                    title : '商品条码'
                },
                {
                    field : 'count',
                    align : "center",
                    title : '数量'
                },
                {
                    field : 'declareprice',
                    align : "center",
                    title : '实际零售价'
                },
                {
                    field : 'retailprice',
                    align : "center",
                    title : '结算价格'
                },
                {
                    field : 'remark',
                    align : "center",
                    title : '备注'
                },
                {
                    align : "center",
                    title : '操作',
                    formatter : function operateFormatter(value, row,index) {
                    //'+row["orderproducrtid"]+'
                        var json = [
                            '<a class="btn btn-xs btn-primary" onclick="editOrderPd(\''+row["orderproducrtid"]+'\');">',
                            '<i class="ace-icon fa   fa-eye bigger-120" title="详情"></i>',
                            '</a>',
                            '<a class="btn btn-xs btn-danger" onclick="delOrderPd(\''+row["orderproducrtid"]+'\');">',
                            '  <i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>',
                            '</a>'
                        ].join('');
                        return json;
                    }
                }
            ],//表格字段
            method:"post",
            search:false,
            queryParamsType : "undefined",
            queryParams:function(params) {
                var obj = {};
                var customerordernum = $("#token").val();
                obj["customerordernum"] = customerordernum;
                return obj;
            }, //查询条件
            sidePagination: "server", //服务端请求
            singleSelect:true,//设置表格单选
            cache:false,//是否对表格数据进行缓存，默认false
            contentType:"application/x-www-form-urlencoded",//spring只有这个格式在POST请求下，才能实现
            dataType:"json"//这格式传输内容的格式
        };
        $("#orderpdTable").bootstrapTable(option);
    });
    //保存
    function save(){

        if($("#outerordernum").val()==""){
            $("#outerordernum").tips({
                side:3,
                msg:'输入外部订单号',
                bg:'#AE81FF',
                time:3
            });
            $("#outerordernum").focus();
            return false;
        }
        if($("#ordertime").val()==""){
            $("#ordertime").tips({
                side:3,
                msg:'输入下单时间',
                bg:'#AE81FF',
                time:3
            });
            $("#ordertime").focus();
            return false;
        }
        if($("#sender").val()==""){
            $("#sender").tips({
                side:3,
                msg:'输入寄件人姓名',
                bg:'#AE81FF',
                time:3
            });
            $("#sender").focus();
            return false;
        }
        if($("#senderphone").val()==""){
            $("#senderphone").tips({
                side:3,
                msg:'输入寄件人电话',
                bg:'#AE81FF',
                time:3
            });
            $("#senderphone").focus();
            return false;
        }
        if(!(/^1(3|4|5|7|8)\d{9}$/.test($("#senderphone").val()))){
            $("#senderphone").tips({
                side:3,
                msg:'电话格式不正确',
                bg:'#AE81FF',
                time:3
            });
            $("#senderphone").focus();
            return false;
        }
        if($("#sendercountry").val()==""){
            $("#sendercountry").tips({
                side:3,
                msg:'输入寄件人国别',
                bg:'#AE81FF',
                time:3
            });
            $("#sendercountry").focus();
            return false;
        }
        if($("#senderprovince").val()==""){
            $("#senderprovince").tips({
                side:3,
                msg:'输入寄件人省',
                bg:'#AE81FF',
                time:3
            });
            $("#senderprovince").focus();
            return false;
        }
        if($("#sendercity").val()==""){
            $("#sendercity").tips({
                side:3,
                msg:'输入寄件人城市',
                bg:'#AE81FF',
                time:3
            });
            $("#sendercity").focus();
            return false;
        }
        if($("#senderarea").val()==""){
            $("#senderarea").tips({
                side:3,
                msg:'输入寄件人区域',
                bg:'#AE81FF',
                time:3
            });
            $("#senderarea").focus();
            return false;
        }
        if($("#senderaddress").val()==""){
            $("#senderaddress").tips({
                side:3,
                msg:'输入寄件人地址',
                bg:'#AE81FF',
                time:3
            });
            $("#senderaddress").focus();
            return false;
        }
        if($("#recipient").val()==""){
            $("#recipient").tips({
                side:3,
                msg:'输入收件人',
                bg:'#AE81FF',
                time:3
            });
            $("#recipient").focus();
            return false;
        }
        if($("#recipientidcard").val()==""){
            $("#recipientidcard").tips({
                side:3,
                msg:'输入收件人身份证',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientidcard").focus();
            return false;
        }
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if(reg.test($("#recipientidcard").val()) === false){
            $("#recipientidcard").tips({
                side:3,
                msg:'身份证格式不正确',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientidcard").focus();
            return false;
        }
        if($("#recipientphone").val()==""){
            $("#recipientphone").tips({
                side:3,
                msg:'输入收件人电话',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientphone").focus();
            return false;
        }
        if(!(/^1(3|4|5|7|8)\d{9}$/.test($("#recipientphone").val()))){
            $("#recipientphone").tips({
                side:3,
                msg:'电话格式不正确',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientphone").focus();
            return false;
        }
            if($("#recipientcountry").val()==""){
            $("#recipientcountry").tips({
                side:3,
                msg:'输入收件人国别',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientcountry").focus();
            return false;
        }
        if($("#recipientprovince").val()==""){
            $("#recipientprovince").tips({
                side:3,
                msg:'输入收件人省',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientprovince").focus();
            return false;
        }
        if($("#recipientcity").val()==""){
            $("#recipientcity").tips({
                side:3,
                msg:'输入收件人城市',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientcity").focus();
            return false;
        }
        if($("#recipientarea").val()==""){
            $("#recipientarea").tips({
                side:3,
                msg:'输入收件人区',
                bg:'#AE81FF',
                time:3
            });
            $("#recipientarea").focus();
            return false;
        }
        if($("#recipientaddress").val()==""){
            $("#recipient").tips({
                side:3,
                msg:'输入收件人地址',
                bg:'#AE81FF',
                time:3
            });
            $("#recipient").focus();
            return false;
        }
        if($("#customsmodel").val()==""){
            $("#customsmodel").tips({
                side:3,
                msg:'输入报关模式',
                bg:'#AE81FF',
                time:3
            });
            $("#customsmodel").focus();
            return false;
        }
        if($("#ordervalue").val()==""){
            $("#ordervalue").tips({
                side:3,
                msg:'输入订单货值',
                bg:'#AE81FF',
                time:3
            });
            $("#ordervalue").focus();
            return false;
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    function addProduct(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="新增商品";
        diag.URL = '<%=basePath%>innerorder/goAddProduct.do';
        diag.Width = 500;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            $("#orderpdTable").bootstrapTable("refresh");
            diag.close();
        };
        diag.show();
    }

    function editOrderPd(id){

        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑商品";
        diag.URL = '<%=basePath%>innerorder/goEditProduct.do?orderproducrtid='+id;
        diag.Width = 500;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            $("#orderpdTable").bootstrapTable("refresh");
            diag.close();
        };
        diag.show();
    }

    function delOrderPd(id){
        $.ajax({
            type: "POST",
            url: '<%=basePath%>innerorder/delOrderPd.do',
            data: {orderproducrtid:id},
            dataType:'json',
            cache: false,
            success: function(data){
                $("#orderpdTable").bootstrapTable("refresh");
            }
        });
    }

    function changeprovince(code){
        $.ajax({
            type: "POST",
            url: '<%=basePath%>innerorder/getCity.do?tm='+new Date().getTime(),
            data: {code:code},
            dataType:'json',
            cache: false,
            success: function(data){
                $("#recipientcity").html('<option>请选择</option>');
                $.each(data.list, function(i, dvar){
                    $("#recipientcity").append("<option value="+dvar.cityid+" id="+dvar.cityid+">"+dvar.city+"</option>");
                });
                var cityHidden = $("#cityHidden").val();
                if(cityHidden != ''){
                    $("#"+cityHidden).attr("selected","selected");
                    changeCity(cityHidden);
                }
            }
        });
    }

    function changeCity(code){
        $.ajax({
            type: "POST",
            url: '<%=basePath%>innerorder/getArea.do?tm='+new Date().getTime(),
            data: {code:code},
            dataType:'json',
            cache: false,
            success: function(data){
                $("#recipientarea").html('<option>请选择</option>');
                $.each(data.list, function(i, dvar){
                    $("#recipientarea").append("<option value="+dvar.areaid+" id="+dvar.areaid+">"+dvar.AREA+"</option>");
                });
                var areaHidden = $("#areaHidden").val();
                if(areaHidden != ''){
                    $("#"+areaHidden).attr("selected","selected")
                }
            }
        });
    }
</script>
</body>
</html>