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
                        <form action="gongyingshang/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="gongyingshangid" id="gongyingshangid" value="${gongyingshang.gongyingshangid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">供应商编号:</td>
                                        <td>
                                            <input type="text"
                                                    <c:if test="${msg == 'edit' }">
                                                        disabled
                                                    </c:if>
                                                   name="gongyingshangcode" id="gongyingshangcode" onblur="checkCode()" value="${gongyingshang.gongyingshangcode}" maxlength="30" placeholder="这里输入供应商编号" title="供应商编号" style="width:98%;"/>
                                            <input type="hidden" id="yuanshicode" value="${gongyingshang.gongyingshangcode}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">供应商名称:</td>
                                        <td>
                                            <input  type="text" name="gongyingshangname" id="gongyingshangname" onblur="checkName()" value="${gongyingshang.gongyingshangname}"
                                                    maxlength="50" placeholder="这里输入供应商名称" title="供应商名称" style="width:98%;"/>
                                            <input type="hidden" id="yuanshiname" value="${gongyingshang.gongyingshangname}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">默认发货仓库:</td>
                                        <td><input type="text" name="defaultwarehouse" id="defaultwarehouse" value="${gongyingshang.defaultwarehouse}" maxlength="255" placeholder="默认发货仓库" title="默认发货仓库" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">登录用户:</td>
                                        <td><input type="text" name="loginname" id="loginname" value="${gongyingshang.loginname}" maxlength="255" placeholder="默认发货仓库" title="默认发货仓库" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td><textarea rows="5" cols="10" id="remark" name="remark" style="width:98%;"  title="备注">${gongyingshang.remark}</textarea></td>
                                    </tr>
                                    <c:if test="${QX.adminOrder == 1 }">
                                        <tr>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">创建者:</td>
                                            <td >
                                                <input type="text" disabled name="createuser" id="createuser" value="${gongyingshang.createuser}" maxlength="255" style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">创建时间:</td>
                                            <td >
                                                <input type="text" disabled name="createtime" id="createtime" value="${gongyingshang.formatCreateTime}" maxlength="255"  style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">修改者:</td>
                                            <td >
                                                <input type="text" disabled name="updateuser" id="updateuser" value="${gongyingshang.updateuser}" maxlength="255"  style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width:78px;text-align: right;padding-top: 13px;">修改时间:</td>
                                            <td >
                                                <input type="text" disabled name="updatetime" id="updatetime" value="${gongyingshang.formateUpdateTime}" maxlength="255"  style="width:98%;"/>
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
        if($("#gongyingshangcode").val()==""){
            $("#gongyingshangcode").tips({
                side:3,
                msg:'输入编号',
                bg:'#AE81FF',
                time:3
            });
            $("#gongyingshangcode").focus();
            return false;
        }
        if($("#gongyingshangname").val()==""){
            $("#gongyingshangname").tips({
                side:3,
                msg:'输入名称',
                bg:'#AE81FF',
                time:3
            });
            $("#gongyingshangname").focus();
            return false;
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    function checkCode(){
        var gongyingshangid = $("#gongyingshangid").val();
        var yuanshicode = $("#yuanshicode").val();
        var gongyingshangcode = $("#gongyingshangcode").val();
        if(gongyingshangid == "" || yuanshicode != gongyingshangcode ){
            var gongyingshangcode = $.trim($("#gongyingshangcode").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>gongyingshang/findgongyingshangByCode.do',
                data: {gongyingshangcode:gongyingshangcode},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" != data.result){
                        $("#gongyingshangcode").tips({
                            side:3,
                            msg:'编号'+gongyingshangcode+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#gongyingshangcode").val('');
                    }
                }
            });
        }
    }
    function checkName(){
        var gongyingshangid = $("#gongyingshangid").val();
        var yuanshiname = $("#yuanshiname").val();
        var gongyingshangname = $("#gongyingshangname").val();
        if(gongyingshangid == "" || yuanshiname != gongyingshangname){
            var gongyingshangname = $.trim($("#gongyingshangname").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>gongyingshang/findgongyingshangByName.do',
                data: {gongyingshangname:gongyingshangname},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" != data.result){
                        $("#gongyingshangname").tips({
                            side:3,
                            msg:'名称'+gongyingshangname+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#gongyingshangname").val('');
                    }
                }
            });
        }
    }


</script>
</body>
</html>
