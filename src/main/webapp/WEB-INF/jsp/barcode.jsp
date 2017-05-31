<%--
  Created by IntelliJ IDEA.
  User: xyz
  Date: 2017/5/10
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<head>

    <script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/print/jquery.jqprint-0.3.js"></script>
</head>
<body>
<div id="printContent">
    <%--必须这么写，否则打印预览无样式--%>
    <style >
        * { margin: 0; padding: 0; font-family: "simsun"}
        .print_paper { font-size:14px; border:none; border-collapse: collapse; width:375px; margin-top:-1px; table-layout:fixed; }
        .print_paper td{ border: solid #000 1px; padding:0 5px; overflow: hidden; }
        .table_first { margin-top: 0; }
        .print_paper .x1 { font-size: 32px; font-weight: bold; text-align: center;letter-spacing:5px; line-height:0.95;font-family: "Microsoft YaHei";}
        .print_paper .x4 { font-size: 20px; font-weight:bold;font-family: "Microsoft YaHei"; }
        .print_paper .xx10 { font-size:10px; }
        .print_paper .xx12 { font-size:12px; font-weight:bold; }
        .print_paper .xx14 { font-size:14px; font-weight:bold; font-family: "SimHei";}
        .print_paper .xx48 { font-size:40px; font-weight:bold; text-align: center;font-family: "Microsoft YaHei";}
        .no_border{ width:100%; height:100%; font-size:14px;}
        .no_border td{ border:none; vertical-align: top;}
        .fwb{ font-weight:bold;}
        .f36{ font-size:36pt; }
        .f14{ font-size: 14pt;}
        .b{ font-weight: bold;}
        .f9{ font-size: 9pt;}
        .fss{ font-family: 'SimSun';}
        .pt5{ padding-top: 5px!important;}
        .tc{ text-align: center; }
    </style>
<table class="print_paper table_first" height="14">
    <tr>
        <td>
            <table class="no_border">
                <tr>
                    <td  width="197" class="f14 fss pt5" >
                       <img src="" style="vertical-align:middle;float: right" id="bar2">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<table class="print_paper table_first" height="25">
    <tr height="15">
        <td class="f36 fss b" colspan="2" style="text-align:center; vertical-align:middle;">
            ${MarkDestination}
        </td>
    </tr>
    <tr height="10">
        <td colspan="2" class="xx14" style="text-align:center;">
           <img width="280" height="60" src="" id="bar1"/>

        </td>
    </tr>
</table>
<table class="print_paper" height="185">
    <tr height="64">
        <td colspan="2">
            <table class="no_border fwb">
                <tr>
                    <td width="60">收件人：</td>
                    <td>周梦怡 湖北 襄阳市 樊城区 城区人民路20号
                        ${FromName} ${FromProvinceName} ${FromCityName} ${FromExpAreaName} ${FromAddress}
                        <br/>
                        ${FromMobile}
                        15072201022
                    </td>
                </tr>
            </table>
        </td>
        <td rowspan="3" width="20" style="vertical-align:middle; text-align:center;">签<br/>收<br/>联</td>
    </tr>
    <tr height="64">
        <td colspan="2">
            <table class="no_border ">
                <tr>
                    <td width="60">寄件人：</td>
                    <td>杨芳 江西九江市永修县 艾城镇枫林涉外本小区
                        ${ToName} ${ToProvinceName} ${ToCityName} ${ToExpAreaName} ${ToAddress}
                        <br/>
                        ${ToMobile}
                        13479831432
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="160" style="vertical-align:top">收件人/代收人：</td>
        <td style="vertical-align:top">
            签收时间：<br/><br/>
            &nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
        </td>
    </tr>
</table>



<table class="print_paper" height="56" style="border-top:5px solid #000;">
    <tr>
        <td>
            <table class="no_border">
                <tr>
                    <td style="vertical-align:middle;"><img height="35" class="logo" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIoAAAAoCAMAAAAWjZKzAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAABhQTFRF4dTfYjFxsZm56nkuiGKT86VnSQVb////WZAgDwAABCdJREFUeNrEWIty5CAMs7wu/P8f1y+IIWzn2ttumdk2L0DIsnBC/SeNqL++fXtMYoE34dmX6Q+gMFppwn5RJA/eCGUFYg3kPPErePnOENIOjTspkveyQmjHJp1F6J1QniExLDOhqLbItHJ25R9tCGgeLE/TofMNiVTZyCWmLXQQjWomnGvNB0WLscnIZLD/AorMvmU0PJaGTSCd1tMBBaM1nU/HstRvdqYotAuzgtITTTqbVwnVP7rMAYXL6ubqZUEiW0hWKI2msNN1dHj0mMzxNYk1Yy4JPq+Ta0A5A1RmkDMp7QaF9wtJvXGgP7K5LASqaQ+orxusia+AOHIOYOcPwXOMMIDOtT6+IMVX5H2UZ1RagIQiZPCIrgFRDQHOmclSMTXrRAlFJs/n8Dz27EmeTYUotCgVbDLQ1bKu0+LBrnB2WcBYaOLagTKmJs3VKqtU+YlmHwuKJd5DW5GOTXJV+t+gKLSQAsx+bAblTWyeONXHxOIlPDN6cDEmeXx8fAwYeniRAnanx8wcXrPPIOhGaTRQnhsFYjli3bhkOtuz/oS4R1IvYqnJSQNJvQ2qLoMSW8kE0lXbohkTis8V6ao3WZzYTKjLnSYU2VXZ+2CFSoLH5jeSzs+WPjp2C0rYdcnbXPpUXuDMN/IgcqTb3SUj7gNKtRAOVKFWX9jKFwyCrVooKQttSM7lcjeSgkVVsXhXDqfqtwhFNpT4TDEPfswpsbLpFIWTsonDc9m0IQ4wkQliXgm/Nhk5j9Nt1wiFAdABypQ1EopshUuMzTazDs6pczHrTUK5JwwbofllpTcqL7rtY/wcClqFshRSoNtmaLtAZj+cDQ+Muo6Yo5APp96rCcdSAkS7hxfVbkqKZ8zAgQ2KeZY3nU83Qy51n1U1lUZ1Uy6nBUrHlq5FtevOl8TBH+Rbmh9eDpYKxHF6LUxRs0ejAkW2/b7E51Y1ucnyyiZeV8XJOmKVyskBNcLDpmpcLTixWjICfG/O1wEjgCczcWlcKcZ/zSZHKNJKoeEJgLZ5HE/6OHaAjjyaly5nuyoeyQtzO7xiMOJTVeudYGvBsOwLOKFIxRIzDhDO4qnLWd/kgd4L6egtiqwA0T2DdigXZ3K9oC4bQS0SLF958Ch5QG4kkdocnivJjmRdgJOv0EG1eXu+YBQ/maXRtZUNUG1stBibQt7jIUlOMgkntz2rNgeyt2R7Yz4U/jLryRnRpNG6jfkw6t/xFNrgUP4dCrevGuZOlglkm0+uXIYCJOJj82aWRSWhaSeH7VCOqn36krq9fNAIlU2KoZCxliwFOkpKSak3plai0dEiT95yqvenYdhfmgaT92InqN6T9zg/j7zkRfXdn3pIfhfJf3/UAPe/gHIIkrzwo9x3P4BVW4G89OvgTz4L+hu6CL/4M+XPhvuVj6WfAgwAx/B1isMqYE8AAAAASUVORK5CYII=" alt=""/></td>
                    <td class="xx12" style="vertical-align:middle; text-align:center;">
                        <img width="160" height="48" src="" id="bar3"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<table class="print_paper" height="105">
    <tr height="52">
        <td>
            <table class="no_border">
                <tr>
                    <td width="60">收件人：</td>
                    <td>
                        周梦怡 湖北 襄阳市 樊城区 城区人民路20号彩虹城
                        ${FromName} ${FromProvinceName} ${FromCityName} ${FromExpAreaName} ${FromAddress}
                        <br/>
                        ${FromMobile}
                        15072201022
                    </td>
                </tr>
            </table>
        </td>
        <td rowspan="2" width="20" style="vertical-align:middle; text-align:center;">收<br/>件<br/>联</td>
    </tr>
    <tr height="52">
        <td>
            <table class="no_border">
                <tr>
                    <td width="60">寄件人：</td>
                    <td>
                        ${ToName} ${ToProvinceName} ${ToCityName} ${ToExpAreaName} ${ToAddress}
                        <br/>
                        ${ToMobile}
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<table class="print_paper" height="120">
    <tr height="98">
        <td colspan="2">
            <table class="no_border" style="margin-top:8px;">
                <tr>
                    <td width="70">订单详情：</td>
                    <td>
                        ${Goods}
                    </td>
                </tr>
            </table>
        </td>
       <%-- <td width="128" style="text-align:center;">[二维码]</td>--%>
    </tr>
    <tr>
        <td class="xx10 fwb">
            &nbsp;<!--代收货款&nbsp;&nbsp;金额(小写)：￥ - 元-->
            订单号：KD17280001 ${orderNum}
        </td>
        <td>
            数量：1 ${orderCountsss}
        </td>
    </tr>

</table>
</div>

<input type="button" id="btnPrint" value="打印"/>

</body>

<script type="text/javascript">
    function myprint(){
        window.print();
    }
    $(function(){
       var mailNo  = "${mailNo}";
       var provide = "湖北";
       var city = "襄阳市";
       var area = "樊城区";
       $("#bar1").attr("src","<%=basePath%>barcode/bar.do?msg="+mailNo);
       $("#bar3").attr("src","<%=basePath%>barcode/bar.do?msg="+mailNo);
       $("#bar2").attr("src","<%=basePath%>barcode/destination.do?provide="+provide+"&city="+city+"&area="+area);

        $("#btnPrint").click(function(){
            $("#printContent").jqprint({
                debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
                importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
                printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
                operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
            });
        }
        );
    })

</script>
</html>
