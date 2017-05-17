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
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" border="0" class="table">
                                    <tr>
                                        <td style="width:82px;text-align: center;padding-top: 13px;" colspan="6">
                                            <h2>销售订单</h2>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >客户:</td>
                                        <td><input type="text" class="txt" readonly value="${pingZheng.customerName}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >日期:</td>
                                        <td><input type="text" readonly value="${pingZheng.orderTime}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >单据号:</td>
                                        <td><input type="text" readonly value="${pingZheng.danjuhao}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >仓库:</td>
                                        <td><input type="text" readonly value="${pingZheng.cangku}" maxlength="30"  style="width:98%;"/> </td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >业务员:</td>
                                        <td><input type="text" readonly value="${pingZheng.yewuyuan}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >折扣:</td>
                                        <td><input type="text" readonly value="${pingZheng.zhekou}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >订单金额:</td>
                                        <td><input type="text" readonly value="${pingZheng.dingdanjine}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >有效期:</td>
                                        <td><input type="text" readonly value="${pingZheng.youxiaoqi}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >付款方式:</td>
                                        <td><input type="text" readonly value="${pingZheng.fukuanfangshi}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >订单备注:</td>
                                        <td colspan="3"><input type="text" readonly value="${pingZheng.dingdanbeizhu}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:82px;text-align: right;padding-top: 13px;" >币种:</td>
                                        <td><input type="text" readonly value="${pingZheng.bizhong}" maxlength="30"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                            <thead>
                                            <tr>
                                                <th class="center" style="width:50px;">行号</th>
                                                <th class="center">货号</th>
                                                <th class="center">商品名称</th>
                                                <th class="center">单位</th>
                                                <th class="center">数量</th>
                                                <th class="center">单价</th>
                                                <th class="center">总价</th>
                                                <th class="center">跨境速递费</th>
                                                <th class="center">备注</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <!-- 开始循环 -->
                                            <c:choose>
                                                <c:when test="${not empty pingZheng.pdlist}">
                                                        <c:forEach items="${pingZheng.pdlist}" var="var" varStatus="vs">
                                                            <tr>
                                                                <td class='center' style="width: 30px;">${vs.index+1}</td>
                                                                <td class='center'>${var.productnum}</td>
                                                                <td class='center'>${var.productname}</td>
                                                                <td class='center'>${var.unit}</td>
                                                                <td class='center'>${var.count}</td>
                                                                <td class='center'>${var.price}</td>
                                                                <td class='center'>${var.totalprice}</td>
                                                                <td class='center'>${var.crossborderCourierfee}</td>
                                                                <td class='center'>${var.remark}</td>
                                                            </tr>
                                                        </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr class="main_info">
                                                        <td colspan="100" class="center" >没有相关数据</td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                            </tbody>
                                        </table>
                                    </tr>
                                </table>
                            </div>
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
    });




</script>
</body>
</html>