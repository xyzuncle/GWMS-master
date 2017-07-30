<%--
  Created by IntelliJ IDEA.
  User: lzf
  Date: 2017/4/3
  Time: 8:03
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
    <!-- 下拉框 -->
    <link rel="stylesheet" href="static/ace/css/chosen.css" />
    <!-- jsp文件头和头部 -->
    <%@ include file="../../system/index/top.jsp"%>
    <!-- 日期框 -->
    <link rel="stylesheet" href="static/ace/css/datepicker.css" />
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
                        <form action="caigoudingdan/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="caigoudingdanid" id="caigoudingdanid" value="${pd.caigoudingdanid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">仓库属性:</td>
                                        <td>
                                            <select class="chosen-select form-control" onchange="searchCangku()" name="cangkushuxing" id="cangkushuxing" data-placeholder="请选择" style="vertical-align:top;width:98%;">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${not empty dictionaries}">
                                                        <c:forEach items="${dictionaries}" var="dic" varStatus="baoguanStatus">
                                                            <option value="${dic.BIANMA}"
                                                                    <c:if test="${dic.BIANMA == cangkushuxing}"> selected</c:if>
                                                                    id="${dic.BIANMA}">${dic.NAME}</option>

                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">仓库:</td>
                                        <td>
                                            <select id="cangku" style="vertical-align:top;width:98%;" name="cangku" >
                                                <option>请选择</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">库位:</td>
                                        <td>
                                            <input type="text"  name="kuwei" id="kuwei" value="${kuwei}" maxlength="30" style="width:98%;"/>
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
                        <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
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
<!-- 下拉框 -->
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    $(top.hangge());




    $(function() {
        if(!ace.vars['touch']) {
            $('.chosen-select').chosen({allow_single_deselect:true});
            $(window)
                    .off('resize.chosen')
                    .on('resize.chosen', function() {
                        $('.chosen-select').each(function() {
                            var $this = $(this);
                            $this.next().css({'width': $this.parent().width()});
                        });
                    }).trigger('resize.chosen');
            $(document).on('settings.ace.chosen', function(e, event_name, event_val) {
                if(event_name != 'sidebar_collapsed') return;
                $('.chosen-select').each(function() {
                    var $this = $(this);
                    $this.next().css({'width': $this.parent().width()});
                });
            });
            $('#chosen-multiple-style .btn').on('click', function(e){
                var target = $(this).find('input[type=radio]');
                var which = parseInt(target.val());
                if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
                else $('#form-field-select-4').removeClass('tag-input-style');
            });
        }
        /*mv.addObject("cangkushuxing", cangKuEntity.getCangkushuxing());
         mv.addObject("cangkuid", cangKuEntity.getId());*/
        var cangkushuxing = $("#cangkushuxing").val();
        var cangkuid = "${cangkuid}";
        if(cangkushuxing){
            searchCangku();
            $("#"+cangkuid).attr("selected","selected");
        }
    });
    function save(){
        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }
    function searchCangku(){
        var code = $("#cangkushuxing").val();
        $.ajax({
            type: "POST",
            url: '<%=basePath%>innerorder/getCangku.do?tm='+new Date().getTime(),
            data: {code:code},
            dataType:'json',
            cache: false,
            async: false,
            success: function(data){
                $("#cangku").html('<option>请选择</option>');
                $.each(data.list, function(i, dvar){
                    $("#cangku").append("<option value="+dvar.id+" id="+dvar.id+">"+dvar.cangkuname+"</option>");
                });
            }
        });
    }



</script>
</body>
</html>