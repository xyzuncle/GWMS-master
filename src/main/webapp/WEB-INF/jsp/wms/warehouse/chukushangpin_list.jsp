<%--
  Created by IntelliJ IDEA.
  User: lzf
  Date: 2017/5/14
  Time: 8:45
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
                        <form action="chukushangpin/list.do" method="post" name="Form" id="Form">
                            <input type="hidden" id="nav-search-chukuzhuangtai" name="chukuzhuangtai" value="${pd.chukuzhuangtai }" >
                            <table style="margin-top:5px;">
                                <tr>
                                    <td>
                                        <div class="nav-search">
                                             <span class="input-icon">
											    客户订单号：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-kehudingdanhao" autocomplete="off" name="kehudingdanhao" value="${pd.kehudingdanhao }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    外部订单号：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-waibudingdanhao" autocomplete="off" name="waibudingdanhao" value="${pd.waibudingdanhao }" placeholder="这里输入关键词"/>
										    </span>

                                            <span class="input-icon">
											    货号/条码：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-shangpintiaoma" autocomplete="off" name="shangpintiaoma" value="${pd.shangpintiaoma }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    仓位：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-cangwei" autocomplete="off" name="cangwei" value="${pd.cangwei }" placeholder="这里输入关键词"/>
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
                                        <h4 class="widget-title lighter">出库商品</h4>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab2">

                                                <li id="baseTab">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('daichuku')">待出库</a>
                                                </li>
                                                <li  id="definedTab">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('yichuku')">已出库</a>
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


                            <!-- 检索  -->
                            <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">
                                <thead>
                                <tr>
                                    <th class="center" style="width:35px;">
                                        <label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
                                    </th>
                                    <th class="center" style="width:50px;">序号</th>
                                    <th class="center">客户订单号</th>
                                    <th class="center">外部订单号</th>
                                    <th class="center">商品条码</th>
                                    <th class="center">数量</th>
                                    <th class="center">仓位</th>
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
                                                        <label class="pos-rel"><input type='checkbox' name='ids' value="${var.chukushangpinid}" class="ace" /><span class="lbl"></span></label>
                                                    </td>
                                                    <td class='center' style="width: 30px;">${vs.index+1}</td>
                                                    <td class='center'>${var.kehudingdanhao}</td>
                                                    <td class='center'>${var.waibudingdanhao}</td>
                                                    <td class='center'>${var.shangpintiaoma}</td>
                                                    <td class='center'>${var.shuliang}</td>
                                                    <td class='center'>${var.cangwei}</td>
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
                            <div class="page-header position-relative">
                                <table style="width:100%;">
                                    <c:if test="${pd.chukuzhuangtai == 'daichuku' }">
                                        <a class="btn btn-xs btn-primary" onclick="saoma();" title="扫描入库" >
                                            <i class='ace-icon fa fa-eye bigger-120'></i></a>
                                    </c:if>
                                    <tr>
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

        var chukuzhuangtai = "${pd.chukuzhuangtai}";
        if(chukuzhuangtai == "daichuku" ){
            $("#definedTab").removeClass("active");
            $("#baseTab").addClass("active");
        }else if (chukuzhuangtai == "yichuku"){
            $("#baseTab").removeClass("active");
            $("#definedTab").addClass("active");
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

    function changeTable(auditStatus){
        $("#nav-search-chukuzhuangtai").val(auditStatus);
        top.jzts();
        $("#Form").submit();
    }


    //新增
    function add(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="新增";
        diag.URL = '<%=basePath%>chukushangpin/goAdd.do';
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
                var url = "<%=basePath%>chukushangpin/delete.do?chukushangpinid="+Id+"&tm="+new Date().getTime();
                $.get(url,function(data){
                    nextPage(${page.currentPage});
                });
            }
        });
    }

    //修改
    function edit(Id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>chukushangpin/goEdit.do?chukushangpinid='+Id;
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
                            url: '<%=basePath%>chukushangpin/deleteAll.do?tm='+new Date().getTime(),
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
    };

    function saoma(id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>chukushangpin/goSaoma.do?chukushangpinid='+id;
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

    //导出excel
    function toExcel(){
        window.location.href='<%=basePath%>chukushangpin/excel.do';
    }
</script>

</body>
</html>

