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

                        <form action="customer/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="customerid" id="customerid" value="${customerid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">计算跨境速递费:</td>
                                        <td>
                                            <select style="width:80px;" style="width:80px;" name="0" id="0">
                                                <option value="1">是</option>
                                                <option value="0">否</option>
                                            </select>
                                        </td>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">是否外部商品转换:</td>
                                        <td>
                                            <select style="width:80px;" name="1" id="1">
                                                <option value="1">转换</option>
                                                <option value="0">不转换</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">发货仓库:</td>
                                        <td>
                                            <select style="width:80px;" name="2" id="2">
                                                <option value="1">默认仓</option>
                                                <option value="0">公共仓</option>
                                                <option value="2">自有商品</option>
                                            </select>
                                        </td>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">按商品内部货值计算申报货值:</td>
                                        <td>
                                            <select style="width:80px;" name="3" id="3">
                                                <option value="1">是</option>
                                                <option value="0">否</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">收款状态:</td>
                                        <td>
                                            <select style="width:80px;" name="4" id="4">
                                                <option value="1">需要收款</option>
                                                <option value="0">不需要收款</option>
                                            </select>
                                        </td>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">计算预计纸箱和包装及费用:</td>
                                        <td>
                                            <select style="width:80px;" name="5" id="5">
                                                <option value="1">是</option>
                                                <option value="0">否</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">计算运费:</td>
                                        <td colspan="3">
                                            <select style="width:80px;" name="6" id="6">
                                                <option value="1">是</option>
                                                <option value="0">否</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:130px;text-align: right;padding-top: 13px;">是否负仓出库:</td>
                                        <td colspan="3">
                                            <select style="width:80px;" name="7" id="7">
                                                <option value="0">否</option>
                                                <option value="1">是</option>
                                            </select>
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
                            <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
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
