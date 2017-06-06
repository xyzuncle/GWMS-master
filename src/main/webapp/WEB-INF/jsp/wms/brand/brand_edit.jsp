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

                        <form action="brand/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="brandid" id="brandid" value="${brand.brandid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">编号:</td>
                                        <td><input type="text"
                                                <c:if test="${msg == 'edit' }">
                                                    disabled
                                                </c:if>
                                                   name="brandcode" onblur="checkBrandCode()" id="brandcode" value="${brand.brandcode}" maxlength="30" placeholder="这里输入编号" title="编号" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">品牌:</td>
                                        <td><input  type="text" name="brandname" id="brandname" value="${brand.brandname}" maxlength="50" placeholder="这里输入品牌" title="品牌" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">外文名称:</td>
                                        <td><input type="text" name="brandename" id="brandename" value="${brand.brandename}" maxlength="255" placeholder="这里输入外文名称" title="外文名称" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td >
                                            <textarea rows="5" cols="10" id="remark" name="remark" style="width:98%;"  title="备注">${brand.remark}</textarea>
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
        if($("#brandcode").val()==""){

            $("#brandcode").tips({
                side:3,
                msg:'输入编号',
                bg:'#AE81FF',
                time:3
            });
            $("#brandcode").focus();
            return false;
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    function checkBrandCode(){
        var brandid = $("#brandid").val();
        if(brandid == ""){
            var brandcode = $.trim($("#brandcode").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>brand/findBrandByBrandCode.do',
                data: {brandcode:brandcode},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" != data.result){
                        $("#brandcode").tips({
                            side:3,
                            msg:'品牌编号'+brandcode+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#brandcode").val('');
                    }
                }
            });
        }
    }

</script>
</body>
</html>
