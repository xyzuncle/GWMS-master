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

    <!-- jsp文件头和头部 -->
    <%@ include file="../../system/index/top.jsp"%>
    <link rel="stylesheet" href="plugins/jqzoom/css/base.css" />
</head>
<body>
        <div class="row">
            <div class="col-xs-12">
                <div id="preview" class="spec-preview"> <span class="jqzoom"><img jqimg="${yuantu}" src="${src}" /></span> </div>
                <!--缩图开始-->
                <div class="spec-scroll"> <a class="prev">&lt;</a> <a class="next">&gt;</a>
                    <div class="items">
                        <ul>
                            <li><img  bimg="${yuantu}" src="${src}" onmousemove="preview(this);"></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /.col -->
        </div>
                <!-- /.row -->
<!-- 页面底部js¨ -->
<%@ include file="../../system/index/foot.jsp"%>

<script src="plugins/jqzoom/js/base.js"></script>
<script src="plugins/jqzoom/js/jquery.jqzoom.js"></script>
<script type="text/javascript">
    $(top.hangge());
</script>
</body>
</html>