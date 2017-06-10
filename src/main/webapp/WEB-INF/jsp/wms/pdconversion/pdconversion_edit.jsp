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

                        <form action="pdconversion/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="productconversionid" id="productconversionid" value="${pdconversion.productconversionid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">外部货号:</td>
                                        <td><input type="text" name="outerproductnum" onblur="checkNum()" id="outerproductnum" value="${pdconversion.outerproductnum}" maxlength="30" placeholder="这里输入外部货号" title="外部货号" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">客户编号:</td>
                                        <td><input  type="text" name="customercode" id="customercode" value="${pdconversion.customercode}" maxlength="50" placeholder="这里输入客户编号" title="客户编号" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">货号1:</td>
                                        <td><input  type="text" name="productnum1" id="productnum1" onblur="checkProductNum(this)" value="${pdconversion.productnum1}" maxlength="50" placeholder="这里输入货号1" title="货号1" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">数量1:</td>
                                        <td><input  type="text" name="productcount1" id="productcount1" value="${pdconversion.productcount1}" maxlength="50" placeholder="这里输入数量1" title="数量1" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">价格占比1:</td>
                                        <td><input  type="text" name="priceof1" id="priceof1" value="${pdconversion.priceof1}" maxlength="50" placeholder="这里输入价格占比1" title="价格占比1" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">货号2:</td>
                                        <td><input  type="text" name="productnum2" id="productnum2" onblur="checkProductNum(this)" value="${pdconversion.productnum2}" maxlength="50" placeholder="这里输入货号2" title="货号2" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">数量2:</td>
                                        <td><input  type="text" name="productcount2" id="productcount2"   value="${pdconversion.productcount2}" maxlength="50" placeholder="这里输入数量2" title="数量2" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">价格占比2:</td>
                                        <td><input  type="text" name="priceof2" id="priceof2" value="${pdconversion.priceof2}" maxlength="50" placeholder="这里输入价格占比2" title="价格占比2" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">货号3:</td>
                                        <td><input  type="text" name="productnum3" id="productnum3" onblur="checkProductNum(this)" value="${pdconversion.productnum3}" maxlength="50" placeholder="这里输入货号3" title="货号3" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">数量3:</td>
                                        <td><input  type="text" name="productcount3" id="productcount3" value="${pdconversion.productcount3}" maxlength="50" placeholder="这里输入数量3" title="数量3" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">价格占比3:</td>
                                        <td><input  type="text" name="priceof3" id="priceof3" value="${pdconversion.priceof3}" maxlength="50" placeholder="这里输入价格占比3" title="价格占比3" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">货号4:</td>
                                        <td><input  type="text" name="productnum4" id="productnum4" onblur="checkProductNum(this)" value="${pdconversion.productnum4}" maxlength="50" placeholder="这里输入货号4" title="货号4" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">数量4:</td>
                                        <td><input  type="text" name="productcount4" id="productcount4" value="${pdconversion.productcount4}" maxlength="50" placeholder="这里输入数量4" title="数量4" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">价格占比4:</td>
                                        <td><input  type="text" name="priceof4" id="priceof4" value="${pdconversion.priceof4}" maxlength="50" placeholder="这里输入价格占比4" title="价格占比4" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td><textarea rows="5" cols="10" id="remark" name="remark" style="width:98%;"  title="备注">${pdconversion.remark}</textarea></td>
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
        if($("#outerproductnum").val()==""){

            $("#outerproductnum").tips({
                side:3,
                msg:'输入外部货号',
                bg:'#AE81FF',
                time:3
            });
            $("#outerproductnum").focus();
            return false;
        }
        if($("#customercode").val()==""){

            $("#customercode").tips({
                side:3,
                msg:'输入客户编号',
                bg:'#AE81FF',
                time:3
            });
            $("#customercode").focus();
            return false;
        }
        if($("#productnum1").val()==""){

            $("#productnum1").tips({
                side:3,
                msg:'输入货号1',
                bg:'#AE81FF',
                time:3
            });
            $("#productnum1").focus();
            return false;
        }
        if($("#productcount1").val()==""){

            $("#productcount1").tips({
                side:3,
                msg:'输入数量1',
                bg:'#AE81FF',
                time:3
            });
            $("#productcount1").focus();
            return false;
        }
        if($("#priceof1").val()==""){

            $("#priceof1").tips({
                side:3,
                msg:'输入价格占比1',
                bg:'#AE81FF',
                time:3
            });
            $("#priceof1").focus();
            return false;
        }
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }
    function checkProductNum(obj){
        var pdconversionid = $("#productconversionid").val();
        if(pdconversionid == ""){
            var productnum = $.trim($(obj).val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>product/findProductByProductNum.do',
                data: {productnum:productnum},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" == data.result){
                        $(obj).tips({
                            side:3,
                            msg:'货号'+productnum+' 不存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $(obj).val('');
                    }
                }
            });
        }
    }

    function checkNum(){
        var pdconversionid = $("#productconversionid").val();
        if(pdconversionid == ""){
            var outerproductnum = $("#outerproductnum").val();
            $.ajax({
                type: "POST",
                url: '<%=basePath%>pdconversion/checkNum.do',
                data: {outerproductnum:outerproductnum},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" == data.result){
                        $(obj).tips({
                            side:3,
                            msg:'外部货号'+productnum+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $(obj).val('');
                    }
                }
            });
        }
    }
</script>
</body>
</html>
