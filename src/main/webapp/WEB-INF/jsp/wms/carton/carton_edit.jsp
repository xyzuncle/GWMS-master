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

                        <form action="carton/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="cartonid" id="cartonid" value="${carton.cartonid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">纸箱型号:</td>
                                        <td><input type="text" name="cartontype" id="cartontype" onblur="checkCartonType()" value="${carton.cartontype}" maxlength="30" placeholder="这里输入纸箱型号" title="纸箱型号" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">纸箱名称:</td>
                                        <td><input  type="text" name="cartonname" id="cartonname" value="${carton.cartonname}" maxlength="50" placeholder="这里输入纸箱名称" title="纸箱名称" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">长（cm）:</td>
                                        <td><input type="text" name="length" id="length" value="${carton.length}" maxlength="255" placeholder="这里输入长" title="长" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">宽（cm）:</td>
                                        <td><input type="text" name="width" id="width" value="${carton.width}" maxlength="255" placeholder="这里输入宽" title="宽" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">高（cm）:</td>
                                        <td><input type="text" name="high" id="high" value="${carton.high}" maxlength="255" placeholder="这里输入高" title="高" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">体积(cm³）:</td>
                                        <td><input type="text" name="volume" id="volume" value="${carton.volume}" maxlength="255" placeholder="这里输入体积" title="体积" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">价格:</td>
                                        <td><input type="text" name="price" id="price" value="${carton.price}" maxlength="255" placeholder="这里输入价格" title="价格" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">重量（g）:</td>
                                        <td><input type="text" name="weight" id="weight" value="${carton.weight}" maxlength="255" placeholder="这里输入重量" title="重量" style="width:98%;"/></td>
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
        if($("#cartontype").val()==""){

            $("#cartontype").tips({
                side:3,
                msg:'输入纸箱型号',
                bg:'#AE81FF',
                time:3
            });
            $("#cartontype").focus();
            return false;
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    function checkCartonType(){
        var cartonid = $("#cartonid").val()
        if(cartonid == ""){
            var cartontype = $.trim($("#cartontype").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>carton/findCartonByCartonCode.do',
                data: {cartontype:cartontype},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" != data.result){
                        $("#cartontype").tips({
                            side:3,
                            msg:'纸箱型号'+cartontype+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#cartontype").val('');
                    }
                }
            });
        }
    }

</script>
</body>
</html>
