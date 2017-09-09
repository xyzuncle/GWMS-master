<%--
  Created by IntelliJ IDEA.
  User: lzf
  Date: 2017/4/4
  Time: 9:05
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

                        <form action="sender/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="id" id="id" value="${sender.id}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">客户编号:</td>
                                        <td>
                                            <select class="chosen-select form-control"
                                                    name="customerid" id="customerid" data-placeholder="请选择" style="vertical-align:top;width:98%;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty customerList}">
                                                        <c:forEach items="${customerList}" var="customer" varStatus="customerStatus">
                                                            <option value="${customer.customercode}" id="${customer.customercode}"
                                                                    <c:if test="${customer.customercode == sender.customercode}">
                                                                        selected="selected"
                                                                    </c:if>
                                                            >${customer.customername}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">发件人:</td>
                                        <td><input type="text"
                                                   name="sender" id="sender" value="${sender.sender}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">电话:</td>
                                        <td><input  type="text" name="senderphone" id="senderphone" value="${sender.senderphone}" maxlength="50" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">国家:</td>
                                        <td><input  type="text" name="sendercountry" id="sendercountry" value="${sender.sendercountry}" maxlength="50"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">地区/省:</td>
                                        <td><input  type="text" name="senderprovince" id="senderprovince" value="${sender.senderprovince}" maxlength="50" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">城市:</td>
                                        <td><input  type="number" name="sendercity" id="sendercity" value="${sender.sendercity}" maxlength="50"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">地址:</td>
                                        <td><input type="text" name="senderarea" id="senderarea" value="${sender.senderarea}" maxlength="255"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">邮编:</td>
                                        <td><input type="text" name="senderpostcode" id="senderpostcode" value="${sender.senderpostcode}" maxlength="255"  style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td><textarea rows="5" cols="10" id="remark" name="remark" style="width:98%;"  title="备注">${sender.remark}</textarea></td>
                                    </tr>
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
<script type="text/javascript">
    $(top.hangge());
    //保存
    function save(){

        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

</script>
</body>
</html>
