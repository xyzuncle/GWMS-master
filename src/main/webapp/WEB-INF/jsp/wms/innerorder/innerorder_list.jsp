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

                        <!-- 检索  -->
                        <form action="innerorder/list.do" method="post" name="Form" id="Form">
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
                                            订单号：
                                        </span>
                                        <span class="input-icon">
                                            <input type="text"  class="nav-search-input" id="nav-search-outerordernum" autocomplete="off" name="outerordernum" style="width:90px;"  value="${pd.outerordernum}"/>
                                        </span>
                                        <span class="input-icon">
                                            下单时间：
                                        </span>
                                        <span class="input-icon">
                                            <input class="date-picker" name="starttime" id="starttime"  value="${pd.starttime}" type="text" data-date-format="yyyy-mm-dd " readonly="readonly" style="width:110px;" placeholder="开始日期" />
                                            <input class="date-picker" name="endtime" id="endtime"  value="${pd.endtime}" type="text" data-date-format="yyyy-mm-dd " readonly="readonly" style="width:110px;" placeholder="结束日期"/>
                                        </span>
                                        <%-- <span class="input-icon">
                                            寄件人国家：
                                        </span>
                                        <span class="input-icon">
                                            <input type="text" class="nav-search-input" id="nav-search-sendercountry" autocomplete="off" name="sendercountry" style="width:90px;" value="${pd.sendercountry }" />
                                        </span>
                                        <span class="input-icon">
                                            寄件人姓名/电话：
                                        </span>--%>
                                        <span class="input-icon">
                                            <input type="text" class="nav-search-input" id="nav-search-sender" autocomplete="off" name="sender" value="${pd.sender }" />
                                        </span>
                                        <span class="input-icon">
                                            收件人姓名/电话/身份证号码：
                                        </span>
                                        <span class="input-icon">
                                            <input type="text"class="nav-search-input" id="nav-search-recipient" autocomplete="off" name="recipient" value="${pd.recipient }" />
                                        </span>
                                        <c:if test="${QX.adminOrder == 1 }">
                                        <span class="input-icon">
                                           创建者：
                                        </span>
                                        <span class="input-icon">
                                            <input type="text"  class="nav-search-input" id="nav-search-createuser" autocomplete="off" name="createuser" value="${pd.createuser }" />
                                        </span>
                                        </c:if>
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
                                        <h4 class="widget-title lighter">订单管理</h4>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab2">

                                                <li status="daiqueren" name="orderStatus">
                                                    <a data-toggle="tab" href="#base" onclick="changeTable('orderStatus_daiqueren')">未审核</a>
                                                </li>
                                                <li  status="yiqueren" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yiqueren')">已审核</a>
                                                </li>
                                                <li  status="daidabao" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_daidabao')">待打包</a>
                                                </li>
                                                <li  status="yidabao" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yidabao')">已打包</a>
                                                </li>
                                                <li  status="yilanshou" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yilanshou')">已揽收</a>
                                                </li>
                                                <li  status="yiqingguan" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yiqingguan')">已请关</a>
                                                </li>
                                                <li  status="yiqianshou" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yiqianshou')">已签收</a>
                                                </li>
                                                <li  status="yichangjian" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_yichangjian')">异常件</a>
                                                </li>
                                                <li  status="zuofei" name="orderStatus">
                                                    <a data-toggle="tab" href="#defined" onclick="changeTable('orderStatus_zuofei')">作废</a>
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
                                    <th class="center">订单号</th>
                                    <th class="center">寄件人姓名</th>
                                    <th class="center">收件人姓名</th>
                                    <th class="center">收件人省</th>
                                    <th class="center">收件人城市</th>
                                    <th class="center">商品数量</th>
                                    <th class="center">订单金额</th>
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
                                                        <label class="pos-rel"><input type='checkbox' name='ids' value="${var.id}" class="ace" /><span class="lbl"></span></label>
                                                    </td>
                                                    <td class='center' style="width: 30px;">${page.currentResult+vs.index+1}</td>
                                                    <td class='center'>
                                                        <a class="btn btn-xs btn-success" title="编辑" onclick="showChild('${var.id}');">
                                                        ${var.ordernum} </a><br> ${var.outerordernum} </td>
                                                    <td class='center'>${var.sender}</td>
                                                    <td class='center'>${var.recipient}</td>
                                                    <td class='center'>${var.recipientprovince}</td>
                                                    <td class='center'>${var.recipientcity}</td>
                                                    <td class='center'>${var.orderproductcount}</td>
                                                    <td class='center'>${var.ordervalue}</td>
                                                    <td class='center'>${var.remark}</td>
                                                    <td class="center">
                                                        <c:if test="${QX.edit != 1 && QX.del != 1 }">
                                                            <span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
                                                        </c:if>
                                                        <div class="hidden-sm hidden-xs btn-group">
                                                            <a class="btn btn-xs btn-primary" onclick="viewinnerorder('${var.id}');">
                                                                <i class="ace-icon fa   fa-eye bigger-120" title="详情"></i>

                                                            <c:if test="${QX.edit == 1 }">
                                                                <a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.id}');">
                                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
                                                                </a>
                                                            </c:if>
                                                               <c:if test="${pd.orderstatus == 'orderStatus_yiqueren' }">
                                                                   <a class="btn btn-xs btn-success" title="凭证" onclick="pingzheng('${var.id}');">
                                                                       <i class="ace-icon fa fa-eye bigger-120" title="凭证"></i>
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
                                                                                <a style="cursor:pointer;" onclick="edit('${var.id}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
                                                                            </a>
                                                                        </li>
                                                                    </c:if>
                                                                    <c:if test="${QX.del == 1 }">
                                                                        <li>
                                                                            <a style="cursor:pointer;" onclick="del('${var.id}');" class="tooltip-error" data-rel="tooltip" title="删除">
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
                                <tfoot>
                                <tr>
                                    <td class="center" colspan="2"></td>
                                    <td class="center" colspan="2">订单总金额</td>
                                    <td class="center" colspan="2">${allprice}</td>
                                    <td class="center" colspan="2">商品总数量</td>
                                    <td class="center" colspan="2">${allcount}</td>
                                    <td class="center"></td>
                                </tr>
                                </tfoot>
                            </table>

                            <!-- 检索  -->
                            <div class="page-header position-relative">
                                <table style="width:100%;">
                                    <tr>
                                        <td style="vertical-align:top;">
                                            <c:if test="${QX.add == 1 }">
                                                <a class="btn btn-sm btn-success" onclick="add();">新增</a>
                                            </c:if>
                                            <c:if test="${QX.FromExcel == 1 }">
                                                <a class="btn btn-sm btn-success" onclick="fromExcel();" title="从EXCEL导入"><i class='ace-icon fa fa-cloud-upload bigger-120'></i></a>
                                            </c:if>
                                            <c:if test="${QX.del == 1 && pd.orderstatus == 'orderStatus_daiqueren' }">
                                                <a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'orderStatus_daiqueren' && QX.shenhe == 1 }">
                                                <a class="btn btn-sm btn-primary" onclick="makeAllShenHe('确定要审核选中的数据吗?');" title="批量审核" ><i class='ace-icon fa fa-eye-slash bigger-120'></i></a>
                                            </c:if>
                                            <c:if test="${QX.tuisongcangku == 1 && pd.orderstatus == 'orderStatus_yiqueren' }">
                                                <a class="btn btn-xs btn-success" title="生成出库单" onclick="makeChuku();">
                                                    <i class='ace-icon fa fa-bookmark bigger-120'></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'orderStatus_yiqingguan' || pd.orderstatus == 'orderStatus_yiqianshou' }">
                                                <a class="btn btn-xs btn-success" title="异常" onclick="yichang('确定要将选中的订单生成异常件?');">
                                                    <i class='ace-icon  fa fa-gavel bigger-120'></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'orderStatus_daiqueren' || pd.orderstatus == 'orderStatus_yiqueren' }">
                                                <a class="btn btn-xs btn-success" title="作废" onclick="makeAllZuofei('确定要将选中的数据作废?');">
                                                    <i class='ace-icon  fa fa-gavel bigger-120'></i>
                                                </a>
                                            </c:if>

                                            <a class="btn btn-xs btn-success" title="添加商品" onclick="addproduct('确定要将选中订单添加商品吗?');">
                                                <i class='ace-icon  fa fa-exclamation-circle bigger-120'></i>
                                            </a>

                                            <c:if test="${pd.orderstatus == 'orderStatus_yiqueren'  }">
                                                <a class="btn btn-xs btn-success" title="合单" onclick="hedan();">
                                                    合单
                                                </a>
                                            </c:if>
                                            <c:if test="${pd.orderstatus == 'orderStatus_yiqueren' }">
                                                <a class="btn btn-xs btn-success" title="拆单" onclick="chaidan('确定要将选中的订单生成异常件?');">
                                                    拆单
                                                </a>
                                            </c:if>
                                            <a class="btn btn-xs btn-success" title="导出订单号" onclick="toDingdanhaoExcel();">
                                                导出订单号
                                            </a>
                                            <a class="btn btn-xs btn-success" title="导出订单号" onclick="toDingdanExcelForYT();">
                                                导出圆通模板
                                            </a>
                                            <a class="btn btn-xs btn-success" title="导出分拣单" onclick="tofenjiandan();">
                                                导出圆通模板
                                            </a>

                                        </td>
                                        <td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
                                    </tr>
                                </table>
                            </div>
                        </form>

                    </div>
                    <!-- /.col -->
                    <div id="modal-table" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body no-padding">
                                    <table id="orderChildren" data-height="200"  class="table table-bordered"></table>
                                </div>
                                <div class="modal-footer no-margin-top">
                                    <button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
                                        <i class="ace-icon fa fa-times"></i>
                                        Close
                                    </button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div>
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


<script type="text/javascript" src="static/ace/js/bootstrap-table.js"></script>
<script type="text/javascript" src="static/ace/js/bootstrap-table-zh-CN.js"></script>

<script type="text/javascript">
    $(top.hangge());//关闭加载状态

    function toDingdanhaoExcel(){
        window.location.href='<%=basePath%>innerorder/toDingdanhaoExcel.do';
    }

    function toDingdanExcelForYT(){
        window.location.href='<%=basePath%>innerorder/toDingdanExcelForYT.do';
    }

    function tofenjiandan(){
        bootbox.confirm("是否导出选中订单的分拣单？", function(result) {
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
                    window.location.href='<%=basePath%>innerorder/tofenjiandan.do?DATA_IDS='+str;
                }
            }
        });
    }

    //检索
    function tosearch(){
        top.jzts();
        $("#Form").submit();
    }
    function showChild(id){
        $("#orderChildren").bootstrapTable({
            queryParams: function (params) {
                obj["id"] = id;
                return obj;
            }
        });
        $('#orderChildren').bootstrapTable('refresh');
        $("#modal-table").modal("show");
    }
    $(function() {
        var orderstatus = "${pd.orderstatus}";
        var arr = orderstatus.split("_");
        $("li[name='orderStatus']").each(function(){
            var status = $(this).attr("status");
            if(status == arr[1] ){
                $(this).addClass("active");
            }else{
                $(this).removeClass("active");
            }
        });
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

        var option = {
            url: '${pageContext.request.contextPath}/innerorder/orderChildren.do', //请求地址
            columns: [
                {
                    field : 'ordernum',
                    align : "center",
                    title : '订单号'
                },
                {
                    field : 'orderproductcount',
                    align : "center",
                    title : '商品数量'
                },
                {
                    field : 'ordervalue',
                    align : "center",
                    title : '订单货值'
                },
                {
                    field : 'yujingstatus',
                    align : "center",
                    title : '预警状态'
                }
            ],//表格字段
            method:"post",
            search:false,
            queryParamsType : "undefined",

            sidePagination: "server", //服务端请求
            singleSelect:true,//设置表格单选
            cache:false,//是否对表格数据进行缓存，默认false
            contentType:"application/x-www-form-urlencoded",//spring只有这个格式在POST请求下，才能实现
            dataType:"json"//这格式传输内容的格式
        };
        $("#orderChildren").bootstrapTable(option);

    });
    //新增
    function add(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="新增";
        diag.URL = '<%=basePath%>innerorder/goAdd.do';
        diag.Width = 700;
        diag.Height = 800;
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
                var url = "<%=basePath%>innerorder/delete.do?id="+Id+"&tm="+new Date().getTime();
                $.get(url,function(data){
                    if("success"  == data){
                        nextPage(${page.currentPage});
                    }else{
                        alert("该数据已被使用，无法删除！");
                        nextPage(${page.currentPage});
                    }
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
        diag.URL = '<%=basePath%>innerorder/goEdit.do?id='+Id;
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
    function makeChuku(){
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
            diag.Title ="订单商品出库";
            diag.URL = '<%=basePath%>innerorder/goChuku.do?id='+str;
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
    function viewinnerorder(Id){

        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>innerorder/goview.do?id='+Id;
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
                            url: '<%=basePath%>innerorder/deleteAll.do?tm='+new Date().getTime(),
                            data: {DATA_IDS:str},
                            dataType:'json',
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
                            url: '<%=basePath%>innerorder/shenheAll.do?tm='+new Date().getTime(),
                            data: {DATA_IDS:str},
                            dataType:'json',
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
    function pingzheng(id){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="编辑";
        diag.URL = '<%=basePath%>innerorder/pingzheng.do?id='+id;
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
    //打开上传excel页面
    function fromExcel(){
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag=true;
        diag.Title ="EXCEL 导入到数据库";
        diag.URL = '<%=basePath%>innerorder/goUploadExcel.do';
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
    function makeAllZuofei(msg){
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
                            url: '<%=basePath%>innerorder/zuofeiAll.do?tm='+new Date().getTime(),
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
    };
    function yichang(msg){
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
                        url: '<%=basePath%>innerorder/yichang.do?tm='+new Date().getTime(),
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
    };
    function addproduct(msg){
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
                    var diag = new top.Dialog();
                    diag.Drag=true;
                    diag.Title ="添加商品";
                    diag.URL = '<%=basePath%>innerorder/goOrderProductPage.do?id='+str;
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
        });
    };

    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
    function hedan(){
        var starttime = $("#starttime").val();
        var endtime = $("#endtime").val();

        if(starttime === "" || starttime == undefined || starttime == null){
            starttime = getNowFormatDate();
        }
        if(endtime === "" || endtime == undefined || endtime == null){
            endtime = getNowFormatDate();
        }

        var msg = "是否将"+starttime+"至"+endtime+"的订单进行合单处理！";
        bootbox.confirm(msg, function(result) {
            if(result) {
                top.jzts();
                var diag = new top.Dialog();
                diag.Drag=true;
                diag.Title ="合单";
                diag.URL = '<%=basePath%>innerorder/gohedan.do?starttime='+starttime+"&endtime"+endtime;
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
        });

    }
    function chaidan(){
        var starttime = $("#starttime").val();
        var endtime = $("#endtime").val();

        if(starttime === "" || starttime == undefined || starttime == null){
            starttime = getNowFormatDate();
        }
        if(endtime === "" || endtime == undefined || endtime == null){
            endtime = getNowFormatDate();
        }

        var msg = "是否将"+starttime+"至"+endtime+"的订单进行拆单处理";
        bootbox.confirm(msg, function(result) {
            if(result) {
                top.jzts();
                var diag = new top.Dialog();
                diag.Drag=true;
                diag.Title ="拆单";
                diag.URL = '<%=basePath%>innerorder/gochaidan.do?starttime='+starttime+"&endtime"+endtime;
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
        });

    }





</script>


</body>
</html>
