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
                        <form action="yiku/list.do" method="post" name="Form" id="Form">
                            <table style="margin-top:5px;">
                                <tr>
                                    <td>
                                        <div class="nav-search">
                                            <input type="hidden" id="nav-search-yikustatus" name="yikustatus" value="${pd.yikustatus }" >
                                            <span class="input-icon">
											    内部货号：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-productnum" autocomplete="off" name="productnum" value="${pd.productnum }" placeholder="这里输入关键词"/>
										    </span>
                                           <%--  <span class="input-icon">
											    源仓库：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-srccangku" autocomplete="off" name="srccangku" value="${pd.srccangku }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    源仓位：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-srccangwei" autocomplete="off" name="srccangwei" value="${pd.srccangwei }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    目的仓库：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-targetcangku" autocomplete="off" name="targetcangku" value="${pd.targetcangku }" placeholder="这里输入关键词"/>
										    </span>
                                            <span class="input-icon">
											    目的仓位：
										    </span>
										    <span class="input-icon">
											    <input type="text" placeholder="这里输入关键词" class="nav-search-input"
                                                       id="nav-search-targetcangwei" autocomplete="off" name="targetcangwei" value="${pd.targetcangwei }" placeholder="这里输入关键词"/>
										    </span>--%>
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
                                        <h4 class="widget-title lighter">移库管理</h4>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab2">

                                                <li status="daiyiku" name="yikuStatus">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('yiku_daiyiku')">待移库</a>
                                                </li>
                                                <li  status="yiyiku" name="yikuStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('yiku_yiyiku')">已移库</a>
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
                                    <th class="center">货号</th>
                                    <th class="center">源仓库</th>
                                    <th class="center">源仓位</th>
                                    <th class="center">目的仓库</th>
                                    <th class="center">目的仓位</th>
                                    <th class="center">移库数量</th>
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
                                                    <td class='center'>${var.productnum}</td>
                                                    <td class='center'>${var.srccangku}</td>
                                                    <td class='center'>${var.srccangwei}</td>
                                                    <td class='center'>${var.targetcangku}</td>
                                                    <td class='center'>${var.targetcangwei}</td>
                                                    <td class='center'>${var.yikushuangliang}</td>
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
                                            <c:if test="${ pd.yikustatus == 'yiku_daiyiku' }">
                                                <a class="btn btn-sm btn-primary" onclick="saomiao();" title="移库扫描" ><i class='ace-icon fa fa-bookmark bigger-120'></i></a>
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

        var yikuStatus = "${pd.yikustatus}";

        var arr = yikuStatus.split("_");
        $("li[name='yikuStatus']").each(function(){
            var status = $(this).attr("status");
            if(status == arr[1] ){
                $(this).addClass("active");
            }else{
                $(this).removeClass("active");
            }
        });

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


    function saomiao(){

        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="移库扫描";
        diag.URL = '<%=basePath%>yiku/yikusaomiao.do';
        diag.Width = 800;
        diag.Height = 300;
        diag.CancelEvent = function(){ //关闭事件
            if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();


    }

    function changeTable(auditStatus){
        $("#nav-search-yikustatus").val(auditStatus);
        top.jzts();
        $("#Form").submit();
    }


</script>

</body>
</html>
