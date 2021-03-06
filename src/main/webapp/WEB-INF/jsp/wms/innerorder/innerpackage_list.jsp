<%--
  Created by IntelliJ IDEA.
  User: lzf
  Date: 2017/4/2
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
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

                        <!-- 检索  -->
                        <form action="innerpackage/list.do" method="post" name="Form" id="Form">
                            <table style="margin-top:5px;">
                                <tr>
                                    <td>
                                        <div class="nav-search">
                                            <input type="hidden" id="nav-search-orderstatus" name="orderstatus" value="${pd.orderstatus }" >
                                        <span class="input-icon">
                                            客户：
                                        </span>
                                        <span class="input-icon">
                                             <select class="chosen-select form-control" name="customernum" id="customernum" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
                                                 <option value="">请选择</option>
                                                 <c:choose>
                                                     <c:when test="${not empty customerList}">
                                                         <c:forEach items="${customerList}" var="customer" varStatus="customerStatus">
                                                             <option value="${customer.customercode}" id="${customer.customercode}"
                                                                     <c:if test="${customer.customercode == pd.customernum}">
                                                                         selected="selected"
                                                                     </c:if>
                                                             >${customer.customername}</option>
                                                         </c:forEach>
                                                     </c:when>
                                                 </c:choose>
                                             </select>
                                        </span>
                                        <span class="input-icon">
                                            外部订单号：
                                        </span>
                                        <span class="input-icon">
                                            <input type="text"  class="nav-search-input" id="nav-search-outerordernum" autocomplete="off" name="outerordernum" style="width:90px;"  value="${pd.outerordernum}"/>
                                        </span>
                                        <span class="input-icon">
                                            下单时间：
                                        </span>
                                        <span class="input-icon">
                                            <input class="date-picker" name="starttime" id="starttime"  value="${pd.starttime}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:110px;" placeholder="开始日期" />
                                            <input class="date-picker" name="endtime" name="endtime"  value="${pd.endtime}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:110px;" placeholder="结束日期"/>
                                        </span>
                                        </div>
                                    </td>
                                    <c:if test="${QX.cha == 1 }">
                                        <td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
                                    </c:if>
                                </tr>
                            </table>

                            <div class="col-sm-12 widget-container-col">
                                <div class="widget-box transparent">
                                    <div class="widget-header">
                                        <h4 class="widget-title lighter">包裹管理</h4>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab2">

                                                <li id="daishenhe">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('packageStatus_daishenhe')">待审核</a>
                                                </li>
                                                <li id="yishenhe">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('packageStatus_yishenhe')">已审核</a>
                                                </li>
                                                <li id="baseTab">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('orderStatus_daidabao')">待打包/入库</a>
                                                </li>
                                                <li  id="definedTab">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yidabao')">已打包/存库</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main padding-12 no-padding-left no-padding-right">
                                            <div class="tab-content padding-4">
                                                <div id="base" class="tab-pane in active">
                                                </div>
                                                <div id="defined" class="tab-pane">
                                                </div>
                                                <div id="disable" class="tab-pane">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                <thead>
                                <tr>
                                    <th class="center" style="width:35px;">
                                        <label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
                                    </th>
                                    <th class="center" style="width:50px;">序号</th>
                                    <th class="center">包裹单号</th>
                                    <th class="center">商品数量</th>
                                    <th class="center">包裹金额</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <!-- 开始循环 -->
                                <c:choose>
                                    <c:when test="${not empty varList}">
                                        <c:if test="${QX.cha == 1 }">
                                            <c:forEach items="${varList}" var="var" varStatus="vs">
                                                <tr>
                                                    <td class='center'>
                                                        <label class="pos-rel"><input type='checkbox' name='ids' value="${var.id}" class="ace" /><span class="lbl"></span></label>
                                                    </td>
                                                    <td class='center' style="width: 30px;">${page.currentResult+vs.index+1}</td>
                                                    <td class='center'>${var.packagenum}</td>
                                                    <td class='center'>${var.productcount}</td>
                                                    <td class='center'>${var.packagevalue}</td>
                                                    <td class="center">
                                                        <a class="btn btn-xs btn-success" title="详情" onclick="view('${var.id}');">
                                                            <i class="ace-icon fa fa-pencil-square-o bigger-120" title="详情"></i>
                                                        </a>
                                                        <c:if test="${pd.orderstatus == 'orderStatus_yidabao' }">
                                                            <a class="btn btn-sm btn-primary" onclick="miandan('${var.id}');" title="面单" ><i class='ace-icon fa fa-barcode bigger-120'></i></a>
                                                        </c:if>
                                                    </td>
                                                </tr>

                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${QX.cha == 0 }">
                                            <tr>
                                                <td colspan="100" class="center">您无权查看</td>
                                            </tr>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="main_info">
                                            <td colspan="100" class="center" >没有相关数据</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>

                            <!-- 检索  -->
                            <div class="page-header position-relative">
                                <table style="width:100%;">
                                    <tr>
                                        <td style="vertical-align:top;">
                                            <c:if test="${QX.FromExcel == 1 }">
                                                <a class="btn btn-sm btn-success" onclick="fromExcel();" title="从EXCEL导入"><i class='ace-icon fa fa-cloud-upload bigger-120'></i></a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'orderStatus_daidabao' }">
                                                <a class="btn btn-sm btn-primary" onclick="makeAllShenHe('确定要审核选中的数据吗?');" title="批量审核" ><i class='ace-icon fa fa-eye-slash bigger-120'></i></a>
                                            </c:if>
                                            <c:if test="${QX.tuisongcangku == 1 && pd.orderstatus == 'orderStatus_daidabao' }">
                                                <a class="btn btn-xs btn-success" title="生成出库单" onclick="makebaoguoruku();">
                                                    <i class='ace-icon fa fa-bookmark bigger-120'></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'packageStatus_daishenhe' && QX.shenhe == 1  }">
                                                <a class="btn btn-sm btn-primary" onclick="makequeren('确定要审核选中的数据吗?');" title="批量审核" ><i class='ace-icon fa fa-eye-slash bigger-120'></i></a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'packageStatus_yishenhe' }">
                                                <a class="btn btn-sm btn-primary" onclick="makeshenhe('确定要审核选中的数据吗?');" title="批量审核" ><i class='ace-icon fa fa-eye-slash bigger-120'></i></a>
                                            </c:if>
                                        </td>
                                        <td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
                                    </tr>
                                </table>
                            </div>
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

    <!-- 返回顶部 -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>

</div>
<!-- /.main-container -->

<!-- basic scripts -->
<!-- 页面底部js¨ -->
<%@ include file="../../system/index/foot.jsp"%>

<!-- 删除时确认窗口 -->
<script src="static/ace/js/bootbox.js"></script>
<!-- ace scripts -->
<script src="static/ace/js/ace/ace.js"></script>
<!-- 下拉框 -->
<script src="static/ace/js/chosen.jquery.js"></script>
<!-- 日期框 -->
<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<script type="text/javascript">
    $(top.hangge());//关闭加载状态
    //检索
    function tosearch(){
        top.jzts();
        $("#Form").submit();
    }

    $(function() {

       var orderstatus = "${pd.orderstatus}";
        if(orderstatus == "orderStatus_daidabao" ){
            $("#daishenhe").removeClass("active");
            $("#yishenhe").removeClass("active");
            $("#definedTab").removeClass("active");
            $("#baseTab").addClass("active");
        }else if (orderstatus == "orderStatus_yidabao"){
            $("#daishenhe").removeClass("active");
            $("#yishenhe").removeClass("active");
            $("#baseTab").removeClass("active");
            $("#definedTab").addClass("active");
        }else if (orderstatus == "packageStatus_daishenhe"){
            $("#baseTab").removeClass("active");
            $("#daishenhe").addClass("active");
            $("#definedTab").removeClass("active");
            $("#yishenhe").removeClass("active");
        }else if (orderstatus == "packageStatus_yishenhe"){
            $("#baseTab").removeClass("active");
            $("#yishenhe").addClass("active");
            $("#definedTab").removeClass("active");
            $("#daishenhe").removeClass("active");
        }

        //日期框
        $('.date-picker').datepicker({autoclose: true,todayHighlight: true});
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
        //复选框全选控制
        var active_class = 'active';
        $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
            var th_checked = this.checked;//checkbox inside "TH" table header
            $(this).closest('table').find('tbody > tr').each(function(){
                var row = this;
                if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
            });
        });
    });


    //修改
    function view(Id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>innerpackage/goView.do?id='+Id;
        diag.Width = 700;
        diag.Height = 800;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }

    function miandan(Id){
        var url = '<%=basePath%>barcode/getBillInfo.do?id='+Id;
        window.open(url,'newwindow');
    }

    function changeTable(auditStatus){
        $("#nav-search-orderstatus").val(auditStatus);
        top.jzts();
        $("#Form").submit();
    }

    function makeAllShenHe(msg){
        bootbox.confirm(msg, function(result) {
            if(result) {
                var str = '';
                for(var i=0;i < document.getElementsByName('ids').length;i++){
                    if(document.getElementsByName('ids')[i].checked){
                        if(str=='') str += document.getElementsByName('ids')[i].value;
                        else str += ',' + document.getElementsByName('ids')[i].value;
                    }
                }
                if(str==''){
                    bootbox.dialog({
                        message: "<span class='bigger-110'>您没有选择任何内容!</span>",
                        buttons:
                        { "button":{ "label":"确定", "className":"btn-sm btn-success"}}
                    });
                    $("#zcheckbox").tips({
                        side:1,
                        msg:'点这里全选',
                        bg:'#AE81FF',
                        time:8
                    });
                    return;
                }else{
                    if(msg == '确定要审核选中的数据吗?'){
                        top.jzts();
                        $.ajax({
                            type: "POST",
                            url: '<%=basePath%>innerpackage/shenheAll.do?tm='+new Date().getTime(),
                            data: {DATA_IDS:str},
                            dataType:'json',
                            //beforeSend: validateData,
                            cache: false,
                            success: function(data){
                                $.each(data.list, function(i, list){
                                    nextPage(${page.currentPage});
                                });
                            }
                        });
                    }
                }
            }
        });
    }


    function makequeren(msg){
        bootbox.confirm(msg, function(result) {
            if(result) {
                var str = '';
                for(var i=0;i < document.getElementsByName('ids').length;i++){
                    if(document.getElementsByName('ids')[i].checked){
                        if(str=='') str += document.getElementsByName('ids')[i].value;
                        else str += ',' + document.getElementsByName('ids')[i].value;
                    }
                }
                if(str==''){
                    bootbox.dialog({
                        message: "<span class='bigger-110'>您没有选择任何内容!</span>",
                        buttons:
                        { "button":{ "label":"确定", "className":"btn-sm btn-success"}}
                    });
                    $("#zcheckbox").tips({
                        side:1,
                        msg:'点这里全选',
                        bg:'#AE81FF',
                        time:8
                    });
                    return;
                }else{
                        top.jzts();
                        $.ajax({
                            type: "POST",
                            url: '<%=basePath%>innerpackage/makequeren.do?tm='+new Date().getTime(),
                            data: {DATA_IDS:str},
                            dataType:'json',
                            //beforeSend: validateData,
                            cache: false,
                            success: function(data){
                                $.each(data.list, function(i, list){
                                    nextPage(${page.currentPage});
                                });
                            }
                        });
                }
            }
        });
    }

    function makeshenhe(msg){
        bootbox.confirm(msg, function(result) {
            if(result) {
                var str = '';
                for(var i=0;i < document.getElementsByName('ids').length;i++){
                    if(document.getElementsByName('ids')[i].checked){
                        if(str=='') str += document.getElementsByName('ids')[i].value;
                        else str += ',' + document.getElementsByName('ids')[i].value;
                    }
                }
                if(str==''){
                    bootbox.dialog({
                        message: "<span class='bigger-110'>您没有选择任何内容!</span>",
                        buttons:
                        { "button":{ "label":"确定", "className":"btn-sm btn-success"}}
                    });
                    $("#zcheckbox").tips({
                        side:1,
                        msg:'点这里全选',
                        bg:'#AE81FF',
                        time:8
                    });
                    return;
                }else{
                        top.jzts();
                        $.ajax({
                            type: "POST",
                            url: '<%=basePath%>innerpackage/makeshenhe.do?tm='+new Date().getTime(),
                            data: {DATA_IDS:str},
                            dataType:'json',
                            //beforeSend: validateData,
                            cache: false,
                            success: function(data){
                                $.each(data.list, function(i, list){
                                    nextPage(${page.currentPage});
                                });
                            }
                        });
                }
            }
        });
    }


    //打开上传excel页面
    function fromExcel(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="EXCEL 导入到数据库";
        diag.URL = '<%=basePath%>innerpackage/goUploadExcel.do';
        diag.Width = 400;
        diag.Height = 150;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                if('${page.currentPage}' == '0'){
                    top.jzts();
                    setTimeout("self.location.reload()",100);
                }else{
                    nextPage(${page.currentPage});
                }
            }
            diag.close();
        };
        diag.show();
    }

    function makebaoguoruku(){
        var str = '';
        for(var i=0;i < document.getElementsByName('ids').length;i++){
            if(document.getElementsByName('ids')[i].checked){
                if(str=='') str += document.getElementsByName('ids')[i].value;
                else str += ',' + document.getElementsByName('ids')[i].value;
            }
        }
        if(str==''){
            bootbox.dialog({
                message: "<span class='bigger-110'>您没有选择任何内容!</span>",
                buttons:
                { "button":{ "label":"确定", "className":"btn-sm btn-success"}}
            });
            $("#zcheckbox").tips({
                side:1,
                msg:'点这里全选',
                bg:'#AE81FF',
                time:8
            });
            return;
        }else{
            top.jzts();
            var diag = new top.Dialog();
            diag.Drag=true;
            diag.Title ="包裹入库";
            diag.URL = '<%=basePath%>innerorder/gobaoguokuwei.do?id='+str;
            diag.Width = 400;
            diag.Height = 200;
            diag.CancelEvent = function(){ //关闭事件
                if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                    nextPage(${page.currentPage});
                }
                diag.close();
            };
            diag.show();
        }

    }

</script>


</body>
</html>
