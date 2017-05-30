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
                            <input type="hidden" name="caigoudingdanid" id="caigoudingdanid" value="${caigoudingdan.caigoudingdanid}"/>
                            <div id="zhongxin" style="padding-top: 13px;">
                                <table id="table_report" class="table table-striped table-bordered table-hover">
                                    <tr>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">采购订单号:</td>
                                        <td><input type="text" name="caigoudingdanhao" id="caigoudingdanhao" value="${caigoudingdan.caigoudingdanhao}" maxlength="30" style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">供应商编号:</td>
                                        <td><input type="text" name="gongyingshangbianhao" id="gongyingshangbianhao" value="${caigoudingdan.gongyingshangbianhao}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">商品货号:</td>
                                        <td><input type="text"  name="shangpinhuohao" id="shangpinhuohao"   value="${caigoudingdan.shangpinhuohao}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">客户编号:</td>
                                        <td><input type="text" name="kehubianhao" id="kehubianhao" value="${caigoudingdan.kehubianhao}" maxlength="30"style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">数量:</td>
                                        <td><input type="text" name="shuliang" id="shuliang"  value="${caigoudingdan.shuliang}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">采购价格:</td>
                                        <td><input type="text" name="caigoujiage" id="caigoujiage" value="${caigoudingdan.caigoujiage}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:82px;text-align: right;padding-top: 13px;">小计:</td>
                                        <td><input type="text" name="xiaoji" id="xiaoji"  value="${caigoudingdan.xiaoji}" maxlength="30"  style="width:98%;"/></td>
                                        <td style="width:90px;text-align: right;padding-top: 13px;">备注:</td>
                                        <td><input type="text" name="beizhu" id="beizhu" value="${caigoudingdan.beizhu}" maxlength="30" style="width:98%;"/></td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">创建者:</td>
                                        <td >
                                            <input type="text" disabled name="createuser" id="createuser" value="${caigoudingdan.createuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">创建时间:</td>
                                        <td >
                                            <input type="text" disabled name="createtime" id="createtime" value="${caigoudingdan.formatCreateTime}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">修改者:</td>
                                        <td >
                                            <input type="text" disabled name="updateuser" id="updateuser" value="${caigoudingdan.updateuser}" maxlength="255" title="申报价" style="width:98%;"/>
                                        </td>
                                        <td style="width:78px;text-align: right;padding-top: 13px;">修改时间:</td>
                                        <td >
                                            <input type="text" disabled name="updatetime" id="updatetime" value="${caigoudingdan.formateUpdateTime}" maxlength="255" title="申报价" style="width:98%;"/>
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
    $(function(){

        //下拉框
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

    });
    //保存
    function save(){

        $("#Form").submit();
        $("#zhongxin").hide();
        $("#zhongxin2").show();
    }



</script>
</body>
</html>