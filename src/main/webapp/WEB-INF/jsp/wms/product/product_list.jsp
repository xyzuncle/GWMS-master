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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/chosen.css" />
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

                        <!-- 检索  -->
                        <form action="product/list.do" method="post" name="Form" id="Form">
                            <table style="margin-top:5px;">
                                <tr>
                                    <td>
                                        <div class="nav-search">
                                            <input type="hidden" id="nav-search-auditStatus" name="auditStatus" value="${pd.auditStatus }"  >
                                            <span class="input-icon">
											    商品名称：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-productname" autocomplete="off" name="productname" value="${pd.productname }" placeholder="这里输入关键词"/>
										    </span>
                                             <span class="input-icon">
											    商品UPC：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-barcodeMain" autocomplete="off" name="barcodeMain" value="${pd.barcodeMain }" placeholder="这里输入关键词"/>
										    </span>
                                             <span class="input-icon">
											    商品品牌：
										    </span>
                                            <span class="input-icon">
											    	<select class="chosen-select form-control" name="brandname" id="brandname" data-placeholder="请选择" style="vertical-align:top;width: 100px;">
                                                        <option value=""></option>
                                                        <c:choose>
                                                        <c:when test="${not empty brandList}">
                                                            <c:forEach items="${brandList}" var="brand" varStatus="brandStatus">
                                                                <c:choose>
                                                                <c:when test="${not empty pd && brand.brandid == pd.brandname}">
                                                                <option value="${brand.brandid}" id="${brand.brandid}" selected="selected">${brand.brandname}</option>
                                                                </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${brand.brandid}" id="${brand.brandid}" >${brand.brandname}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </c:when>
                                                        </c:choose>
                                                    </select>
										    </span>

                                            <span class="input-icon">
											    类别：
										    </span>
										   <span class="input-icon">
											    	<select class="chosen-select form-control" name="luggagemail" id="luggagemail" data-placeholder="请选择" style="vertical-align:top;width: 100px;">
                                                        <option value=""></option>
                                                        <c:choose>
                                                        <c:when test="${not empty luggageMailList}">
                                                            <c:forEach items="${luggageMailList}" var="luggageMail" varStatus="luggageMailStatus">
                                                                <c:choose>
                                                                <c:when test="${not empty pd && luggageMail.luggagemailid == pd.luggagemail}">
                                                                    <option value="${luggageMail.luggagemailid}" id="${luggageMail.luggagemailid}" selected="selected">${luggageMail.luggagemailname}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <option value="${luggageMail.luggagemailid}" id="${luggageMail.luggagemailid}">${luggageMail.luggagemailname}</option>
                                                                </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </c:when>
                                                        </c:choose>
                                                    </select>
										    </span>
                                            <span class="input-icon">
											    产地：
										    </span>
										   <span class="input-icon">
											    	<select class="chosen-select form-control" name="producingArea" id="producingArea" data-placeholder="请选择" style="vertical-align:top;width: 100px;">
                                                        <option value=""></option>
                                                        <c:choose>
                                                        <c:when test="${not empty producingAreaList}">
                                                            <c:forEach items="${producingAreaList}" var="producingArea" varStatus="producingAreaStatus">
                                                                <c:choose>
                                                                <c:when test="${not empty pd &&  producingArea.COUNTRY_ID == pd.producingArea}">
                                                                    <option value="${producingArea.COUNTRY_ID}" id="${producingArea.COUNTRY_ID}" selected="selected">${producingArea.COUN_C_NAME}</option>
                                                                </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${producingArea.COUNTRY_ID}" id="${producingArea.COUNTRY_ID}" >${producingArea.COUN_C_NAME}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </c:when>
                                                        </c:choose>
                                                    </select>
										    </span>
                                            <span class="input-icon">
											    备注：
										    </span>
										      <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-remark1" autocomplete="off" name="remark1" value="${pd.remark1 }" placeholder="这里输入关键词"/>
										    </span>
                                        </div>
                                    </td>
                                    <c:if test="${QX.cha == 1 }">
                                        <td style="vertical-align:top;padding-left:2px">
                                            <a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i  class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a>
                                        <c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i  class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
                                        <c:if test="${QX.FromExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="fromExcel();" title="从EXCEL导入"><i  class="ace-icon fa fa-cloud-upload bigger-110 nav-search-icon blue"></i></a></td></c:if>
                                        </td>
                                    </c:if>
                                </tr>
                            </table>
                            <div class="col-sm-12 widget-container-col">
                                <div class="widget-box transparent">
                                    <div class="widget-header">
                                        <h4 class="widget-title lighter">商品管理</h4>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab2">

                                                <li id="baseTab">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('product_yishenhe')">公共商品库</a>
                                                </li>
                                                <li  id="definedTab">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('product_daishenhe')">自定义商品库</a>
                                                </li>
                                                <c:if test="${QX.productDisable == 1 }">
                                                <li id="disableTab">
                                                    <a data-toggle="tab" href="#disable" onclick="changeTable('product_yitingyong')">停用商品库</a>
                                                </li>
                                                </c:if>
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
                                    <th class="center">图片</th>
                                    <th class="center">货号</th>
                                    <th class="center">商品名称</th>
                                    <th class="center">商品主条码</th>
                                    <th class="center">品牌</th>
                                    <th class="center">单位</th>
                                    <th class="center">规格</th>
                                    <th class="center">产地</th>
                                    <th class="center">跨境速递费</th>
                                    <th class="center">备注</th>
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
                                                        <label class="pos-rel"><input type='checkbox' name='ids' value="${var.productId}" class="ace" /><span class="lbl"></span></label>
                                                    </td>
                                                    <td class='center' style="width: 30px;">${vs.index+1}</td>
                                                    <td class='center'> <img id='imgsImgSrc' src="${var.image}" onclick="imageView('${var.image}')" height="100" width="100" /></td>
                                                    <td class='center'>${var.productnum}</td>
                                                    <td class='center'>${var.productname}</td>
                                                    <td class='center'>${var.barcodeMain}</td>
                                                    <td class='center'>${var.brandname}</td>
                                                    <td class='center'>${var.unit}</td>
                                                    <td class='center'>${var.standard}</td>
                                                    <td class='center'>${var.producingArea}</td>
                                                    <td class='center'>${var.crossborderCourierfee}</td>
                                                    <td class='center'>${var.remark1}</td>
                                                    <td class="center">
                                                        <c:if test="${QX.edit != 1 && QX.del != 1 }">
                                                            <span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
                                                        </c:if>
                                                        <div class="hidden-sm hidden-xs btn-group">
                                                            <a class="btn btn-xs btn-primary" onclick="viewProduct('${var.productId}');">
                                                                <i class="ace-icon fa   fa-eye bigger-120" title="详情"></i>
                                                            </a>
                                                            <c:choose>
                                                                <c:when test="${var.auditStatus == 'product_yishenhe'}">
                                                                    <c:if test="${QX.edit == 1 && QX.productComEdit == 1 }">
                                                                        <a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.productId}');">
                                                                            <i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
                                                                        </a>
                                                                    </c:if>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:if test="${QX.edit == 1 }">
                                                                        <a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.productId}');">
                                                                            <i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
                                                                        </a>
                                                                    </c:if>
                                                                </c:otherwise>
                                                            </c:choose>


                                                            <c:if test="${var.auditStatus == 'product_daishenhe' && QX.productAuditor == 1 }">

                                                                <a class="btn btn-xs btn-primary" title="审核" onclick="auditor('${var.productId}');">
                                                                    <i class="ace-icon fa fa-check bigger-120" title="审核"></i>
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${var.auditStatus != 'product_yitingyong' && QX.productBlock == 1 }">

                                                                <a class="btn btn-xs btn-warning" title="停用" onclick="blockStatus('${var.productId}');">
                                                                    <i class="ace-icon fa  fa-key bigger-120" title="停用"></i>
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${var.auditStatus == 'product_yitingyong' && QX.productBlock == 1 }">

                                                                <a class="btn btn-xs btn-warning" title="启用" onclick="blockStatus('${var.productId}');">
                                                                    <i class="ace-icon fa  fa-key bigger-120" title="启用"></i>
                                                                </a>
                                                            </c:if>
                                                        </div>
                                                        <div class="hidden-md hidden-lg">
                                                            <div class="inline pos-rel">
                                                                <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                                </button>
                                                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                                    <c:if test="${QX.edit == 1 }">
                                                                        <li>
                                                                            <a style="cursor:pointer;" onclick="edit('${var.productId}');" class="tooltip-success" data-rel="tooltip" title="修改">
                                                                                <span class="green">
                                                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                                </span>
                                                                            </a>
                                                                        </li>
                                                                    </c:if>
                                                                    <c:if test="${QX.del == 1 }">
                                                                        <li>
                                                                            <a style="cursor:pointer;" onclick="del('${var.productId}');" class="tooltip-error" data-rel="tooltip" title="删除">
                                                                                <span class="red">
                                                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                                </span>
                                                                            </a>
                                                                        </li>
                                                                    </c:if>
                                                                </ul>
                                                            </div>
                                                        </div>
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
                                            <c:if test="${QX.add == 1 }">
                                                <a class="btn btn-sm btn-success" onclick="add();">新增</a>
                                            </c:if>
                                            <c:if test="${QX.del == 1 }">
                                                <a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
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
<script src="${pageContext.request.contextPath}/static/ace/js/bootbox.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/static/ace/js/ace/ace.js"></script>
<!-- 下拉框 -->
<script src="${pageContext.request.contextPath}/static/ace/js/chosen.jquery.js"></script>

<!--提示框-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.tips.js"></script>

<script type="text/javascript">
    $(top.hangge());//关闭加载状态
    //检索
    function tosearch(){
        top.jzts();
        $("#Form").submit();
    }

    $(function() {
        var auditStatus = '${pd.auditStatus}';
        if(auditStatus == 'product_daishenhe' ){
            $("#baseTab").removeClass("active");
            $("#disableTab").removeClass("active");
            $("#definedTab").addClass("active");
        }else if (auditStatus == 'product_yishenhe'){
            $("#disableTab").removeClass("active");
            $("#definedTab").removeClass("active");
            $("#baseTab").addClass("active");
        }else if (auditStatus == 'product_yitingyong'){
            $("#baseTab").removeClass("active");
            $("#definedTab").removeClass("active");
            $("#disableTab").addClass("active");
        }
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

    //新增
    function add(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="新增";
        diag.URL = '<%=basePath%>product/goAdd.do';
        diag.Width = 600;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                if('${page.currentPage}' == '0'){
                    top.jzts();
                    setTimeout("self.location=self.location",100);
                }else{
                    nextPage(${page.currentPage});
                }
            }
            diag.close();
        };
        diag.show();
    }

    //删除
    function del(Id){
        bootbox.confirm("确定要删除吗?", function(result) {
            if(result) {
                top.jzts();
                var url = "<%=basePath%>product/delete.do?productId="+Id+"&tm="+new Date().getTime();
                $.get(url,function(data){
                    if("success"  == data.msg){
                        nextPage(${page.currentPage});
                    }else{
                        alert("该数据已被使用，无法删除！");
                        nextPage(${page.currentPage});
                    }
                });
            }
        });
    }

    function viewProduct(Id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>product/goView.do?productId='+Id;
        diag.Width = 600;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }
    //修改
    function edit(Id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>product/goEdit.do?productId='+Id;
        diag.Width = 600;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }
    //审核
    function auditor(Id){

        bootbox.confirm("是否审核该商品！", function(result) {
            if(result) {
                var url = "<%=basePath%>product/updateAuditor.do?productId="+Id+"&tm="+new Date().getTime();
                $.get(url,function(data){
                    nextPage(${page.currentPage});
                });
            }
        });
    }
    //停用
    function blockStatus(Id){
        top.jzts();
        var url = "<%=basePath%>product/updateBlock.do?productId="+Id+"&tm="+new Date().getTime();
        $.get(url,function(data){
            nextPage(${page.currentPage});
        });
    }
    //批量操作
    function makeAll(msg){
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
                    if(msg == '确定要删除选中的数据吗?'){
                        top.jzts();
                        $.ajax({
                            type: "POST",
                            url: '<%=basePath%>product/deleteAll.do?tm='+new Date().getTime(),
                            data: {DATA_IDS:str},
                            dataType:'json',
                            //beforeSend: validateData,
                            cache: false,
                            success: function(data){
                                if("success"  == data.msg){
                                    nextPage(${page.currentPage});
                                }else{
                                    alert("该数据已被使用，无法删除！");
                                    nextPage(${page.currentPage});
                                }
                            }
                        });
                    }
                }
            }
        });
    };

    //改变商品库
    function changeTable(auditStatus){
        $("#nav-search-auditStatus").val(auditStatus);
        top.jzts();
        $("#Form").submit();
    }

    //导出excel
    function toExcel(){
        var auditStatus = $("#nav-search-auditStatus").val();
        var productname = $("#nav-search-productname").val();
        var barcodeMain = $("#nav-search-barcodeMain").val();
        var brandname = $("#brandname").val();
        var luggagemail = $("#luggagemail").val();
        var producingArea = $("#producingArea").val();
        var remark1 = $("#nav-search-remark1").val();
        window.location.href='<%=basePath%>product/excel.do?auditStatus='+auditStatus+'&productname='+productname+'&barcodeMain='+barcodeMain+'&brandname='+brandname
                +'&luggagemail='+luggagemail+'&producingArea='+producingArea+'&remark1='+remark1;
    }

    //打开上传excel页面
    function fromExcel(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="EXCEL 导入到数据库";
        diag.URL = '<%=basePath%>product/goUploadExcel.do';
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

    function imageView(src){
        if(src == "" || src == undefined || src == null){
            bootbox.dialog({
                message: "<span class='bigger-110'>该商品没有图片！</span>",
                buttons:
                { "button":{ "label":"确定", "className":"btn-sm btn-success"}}
            });
            return;
        }

        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="原图";
        diag.URL = '<%=basePath%>product/goImage.do?src='+src;
        diag.Width = 800;
        diag.Height = 500;
        diag.CancelEvent = function(){ //关闭事件
            diag.close();
        };
        diag.show();
    }
</script>


</body>
</html>
