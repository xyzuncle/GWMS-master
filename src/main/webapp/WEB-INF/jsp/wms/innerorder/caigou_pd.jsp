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

                        <form action="caigoudingdan/${msg }.do" name="Form" id="Form" method="post">
                            <input type="hidden" name="id" id="id" value="${caigoupd.id}" />
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">商品货号:</td>
                                        <td><input type="text" onblur="checkProductNum()" name="shangpinhuohao" id="shangpinhuohao" value="${caigoupd.shangpinhuohao}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:92px;text-align: right;padding-top: 13px;">数量:</td>
                                        <td><input  type="text" name="shuliang" id="shuliang" value="${caigoupd.shuliang}" maxlength="50" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:92px;text-align: right;padding-top: 13px;">采购价格:</td>
                                        <td><input type="text" name="caigoujiage" id="caigoujiage"value="${caigoupd.caigoujiage}" maxlength="255" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:92px;text-align: right;padding-top: 13px;">小计:</td>
                                        <td><input type="text" name="xiaoji" id="xiaoji"value="${caigoupd.xiaoji}" maxlength="255" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:92px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td><textarea rows="5" cols="10" id="beizhu" name="beizhu" style="width:98%;"  title="备注">${caigoupd.beizhu}</textarea></td>
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
<script type="text/javascript">
    $(top.hangge());
    //保存
    function save(){
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }

    //检查货号
    function checkProductNum(){
        var id = $("#id").val();
        if(id == ""){
            var productnum = $.trim($("#shangpinhuohao").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>product/findProductByProductNum.do',
                data: {productnum:productnum},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" == data.result){
                        $("#shangpinhuohao").tips({
                            side:3,
                            msg:'货号'+productnum+' 不存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#shangpinhuohao").val('');
                    }
                }
            });
        }
    }

</script>
</body>
</html>
