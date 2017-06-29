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

                        <form action="caigoudingdan/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="caigoudingdanid" id="caigoudingdanid" value="${caigoudingdan.caigoudingdanid}"/>
                            <input type="hidden" name="token" id="token" value="${token}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">采购订单号:</td>
                                        <td><input disabled type="text" disabled name="caigoudingdanhao" id="caigoudingdanhao" value="${caigoudingdan.caigoudingdanhao}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">供应商编号:</td>
                                        <td><input  disabled type="text" name="gongyingshangbianhao" id="gongyingshangbianhao" value="${caigoudingdan.gongyingshangbianhao}" maxlength="50" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">客户编号:</td>
                                        <td><input disabled type="text" name="kehubianhao" id="kehubianhao" value="${caigoudingdan.kehubianhao}" maxlength="255" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;height:130px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td >
                                            <textarea disabled rows="5" cols="10" id="beizhu" name="beizhu" style="width:98%;"  title="备注">${caigoudingdan.beizhu}</textarea>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="4">
                                            <table id="pdTable" data-height="200"  class="table table-bordered"></table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="text-align: center;" colspan="10">
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
<script type="text/javascript" src="static/ace/js/bootstrap-table.js"></script>
<script type="text/javascript" src="static/ace/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
    $(top.hangge());
    $(function(){
        var option = {
            url: '${pageContext.request.contextPath}/caigoudingdan/pdlist.do', //请求地址
            columns: [
                {
                    field : 'shangpinhuohao',
                    align : "center",
                    title : '商品货号'
                },
                {
                    field : 'shuliang',
                    align : "center",
                    title : '数量'
                },
                {
                    field : 'caigoujiage',
                    align : "center",
                    title : '采购价格'
                },
                {
                    field : 'xiaoji',
                    align : "center",
                    title : '小计'
                },
                {
                    field : 'beizhu',
                    align : "center",
                    title : '备注'
                }
            ],//表格字段
            method:"post",
            search:false,
            queryParamsType : "undefined",
            queryParams:function(params) {
                var obj = {};
                var caigoudingdanid = $("#caigoudingdanid").val();
                obj["caigoudingdanid"] = caigoudingdanid;
                return obj;
            }, //查询条件
            sidePagination: "server", //服务端请求
            singleSelect:true,//设置表格单选
            cache:false,//是否对表格数据进行缓存，默认false
            contentType:"application/x-www-form-urlencoded",//spring只有这个格式在POST请求下，才能实现
            dataType:"json"//这格式传输内容的格式
        };
        $("#pdTable").bootstrapTable(option);
    });



</script>
</body>
</html>
