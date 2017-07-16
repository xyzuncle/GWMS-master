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
                        <form action="cangku/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="id" id="id" value="${cangku.id}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">仓库编号:</td>
                                        <td>
                                            <input type="text" disabled  name="cangkubianhao" id="cangkubianhao" value="${cangku.cangkubianhao}" maxlength="30"style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">仓库名称:</td>
                                        <td>
                                            <input  type="text" disabled name="cangkuname" id="cangkuname" value="${cangku.cangkuname}"
                                                    maxlength="50" style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">仓库管理员:</td>
                                        <td>
                                            <input  type="text"  name="cangkuuser" id="cangkuuser" value="${cangku.cangkuuser}"
                                                    placeholder="用户之间用英文逗号隔开" title="仓库管理员"   maxlength="50" style="width:98%;"/>
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
            url: '${pageContext.request.contextPath}/shangpinkuwei/list.do', //请求地址
            columns: [
                {
                    field : 'productnum',
                    align : "center",
                    title : '商品货号'
                },
                {
                    field : 'kuwei',
                    align : "center",
                    title : '库位'
                },
                {
                    field : 'beizhu',
                    align : "center",
                    title : '备注'
                },
                {
                    align : "center",
                    title : '操作',
                    formatter : function operateFormatter(value, row,index) {
                        //'+row["orderproducrtid"]+'
                        var json = [
                            '<a class="btn btn-xs btn-primary" onclick="editkuwei(\''+row["id"]+'\');">',
                            '<i class="ace-icon fa   fa-eye bigger-120" title="详情"></i>',
                            '</a>',
                            '<a class="btn btn-xs btn-danger" onclick="delkuwei(\''+row["id"]+'\');">',
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
                var cangkuid = $("#token").val();
                obj["cangkuid"] = cangkuid;
                return obj;
            }, //查询条件
            sidePagination: "server", //服务端请求
            singleSelect:true,//设置表格单选
            cache:false,//是否对表格数据进行缓存，默认false
            contentType:"application/x-www-form-urlencoded",//spring只有这个格式在POST请求下，才能实现
            dataType:"json"//这格式传输内容的格式
        };
        $("#kuweitable").bootstrapTable(option);
    });

    function save(){
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    function addKuWei(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="新增商品";
        diag.URL = '<%=basePath%>shangpinkuwei/goAdd.do';
        diag.Width = 500;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            $("#kuweitable").bootstrapTable("refresh");
            diag.close();
        };
        diag.show();
    }

    function editkuwei(id){

        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑商品";
        diag.URL = '<%=basePath%>shangpinkuwei/goEdit.do?id='+id;
        diag.Width = 500;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            $("#kuweitable").bootstrapTable("refresh");
            diag.close();
        };
        diag.show();
    }

    function delkuwei(id){
        $.ajax({
            type: "POST",
            url: '<%=basePath%>shangpinkuwei/delete.do',
            data: {id:id},
            dataType:'json',
            cache: false,
            success: function(data){
                $("#kuweitable").bootstrapTable("refresh");
            }
        });
    }

</script>
</body>
</html>
