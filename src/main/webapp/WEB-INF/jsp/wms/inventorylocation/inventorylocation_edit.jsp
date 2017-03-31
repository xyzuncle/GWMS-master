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
					
					<form action="inventorylocation/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="INVENTORYLOCATION_ID" id="INVENTORYLOCATION_ID" value="${pd.INVENTORYLOCATION_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品名称:</td>
								<td><input type="text" name="GOODS_NAME" id="GOODS_NAME" value="${pd.GOODS_NAME}" maxlength="60" placeholder="这里输入商品名称" title="商品名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">条码:</td>
								<td><input type="text" name="GOODS_UPC" id="GOODS_UPC" value="${pd.GOODS_UPC}" maxlength="20" placeholder="这里输入条码" onblur="hasUPC('${pd.GOODS_NAME }')" title="条码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">库位:</td>
								<td><input type="text" name="LOCATION" id="LOCATION" value="${pd.LOCATION}" maxlength="20" placeholder="这里输入库位" title="库位" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARKS_1" id="REMARKS_1" value="${pd.REMARKS_1}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
                            <%--
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注2:</td>
								<td><input type="text" name="REMARKS_2" id="REMARKS_2" value="${pd.REMARKS_2}" maxlength="255" placeholder="这里输入备注2" title="备注2" style="width:98%;"/></td>
							</tr>
                            --%>
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
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#GOODS_NAME").val()==""){
				$("#GOODS_NAME").tips({
					side:3,
		            msg:'请输入商品名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GOODS_NAME").focus();
			return false;
			}
			if($("#GOODS_UPC").val()==""){
				$("#GOODS_UPC").tips({
					side:3,
		            msg:'请输入条码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GOODS_UPC").focus();
			return false;
			}else{
                $("#GOODS_UPC").val($.trim($("#GOODS_UPC").val()));
            }
			if($("#LOCATION").val()==""){
				$("#LOCATION").tips({
					side:3,
		            msg:'请输入库位',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LOCATION").focus();
			return false;
			}
            <%--
			if($("#REMARKS_1").val()==""){
                $("#REMARKS_1").tips({
                    side:3,
                    msg:'请输入备注1',
                    bg:'#AE81FF',
                    time:2
                });
                $("#REMARKS_1").focus();
                return false;
            }
            if($("#REMARKS_2").val()==""){
                $("#REMARKS_2").tips({
                    side:3,
                    msg:'请输入备注2',
                    bg:'#AE81FF',
                    time:2
                });
                $("#REMARKS_2").focus();
                return false;
            --%>
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

        //判断UPC是否存在
        function hasUPC(GOODS_NAME){
            var GOODS_UPC = $.trim($("#GOODS_UPC").val());
            $.ajax({
                type: "POST",
                url: '<%=basePath%>inventorylocation/hasUPC.do',
                data: {GOODS_UPC:GOODS_UPC,GOODS_NAME:GOODS_NAME,tm:new Date().getTime()},
                dataType:'json',
                cache: false,
                success: function(data){
                    if("success" != data.result){
                        $("#GOODS_UPC").tips({
                            side:3,
                            msg:'条码 '+GOODS_UPC+' 已存在',
                            bg:'#AE81FF',
                            time:3
                        });
                        $("#GOODS_UPC").val('');
                    }
                }
            });
        }

		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>