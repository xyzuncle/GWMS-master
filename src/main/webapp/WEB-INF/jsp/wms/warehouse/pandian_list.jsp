<%--
  Created by IntelliJ IDEA.
  User: lzf
  Date: 2017/5/14
  Time: 11:08
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
                        <form action="pandian/list.do" method="post" name="Form" id="Form">
                            <input type="hidden" id="nav-search-status" name="status" value="${pd.status }" >
                            <table style="margin-top:5px;">
                                <tr>
                                    <td>
                                        <div class="nav-search">
                                            <span class="input-icon">
											    货号：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-neibuhuohao" autocomplete="off" name="shangpinbianhao" value="${pd.shangpinbianhao }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    仓库：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-cangku" autocomplete="off" name="cangku" value="${pd.cangku }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    仓位：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-kuwei" autocomplete="off" name="kuwei" value="${pd.kuwei }" placeholder="这里输入关键词"/>
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
                                        <h4 class="widget-title lighter">盘点管理</h4>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab2">
                                                <li id="baseTab">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('pandian_daiqueren')">待确认</a>
                                                </li>
                                                <li  id="definedTab">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('pandian_yiqueren')">已确认</a>
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
                                    <th class="center">仓库</th>
                                    <th class="center">仓位</th>
                                    <th class="center">货号</th>
                                    <th class="center">备注</th>
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
                                                        <label class="pos-rel"><input type='checkbox' name='ids' value="${var.productwarehouseid}" class="ace" /><span class="lbl"></span></label>
                                                    </td>
                                                    <td class='center' style="width: 30px;">${page.currentResult+vs.index+1}</td>
                                                    <td class='center'>${var.cangku}</td>
                                                    <td class='center'>${var.kuwei}</td>
                                                    <td class='center'>${var.shangpinbianhao}</td>
                                                    <td class='center'>${var.beizhu}</td>
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
                                    <tr>
                                        <td style="vertical-align:top;">
                                            <a class="btn btn-xs btn-success" title="自动扫描" onclick="saomiao(0);">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120" title="自动扫描"></i>
                                            </a>
                                            <a class="btn btn-xs btn-success" title="手动扫描" onclick="saomiao(1);">
                                                <i class="ace-icon fa  fa-ban bigger-120" title="手动扫描"></i>
                                            </a>
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

        var status = "${pd.status}";
        if(status == "pandian_daiqueren" ){
            $("#definedTab").removeClass("active");
            $("#baseTab").addClass("active");
        }else if (caigoudingdanstatus == "pandian_yiqueren"){
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
        $("#nav-search-status").val(auditStatus);
        top.jzts();
        $("#Form").submit();
    }

    function saomiao(status){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="";
        diag.URL = '<%=basePath%>pandian/goSaoMiao.do?status='+status;
        diag.Width = 900;
        diag.Height = 900;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }

</script>

</body>
</html>
