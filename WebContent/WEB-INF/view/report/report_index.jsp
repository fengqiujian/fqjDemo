<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css"/>
</head>
<body>
<!--table START-->
<div class="container-fluid" style="background-color: #F5F5F5;">
	<div id="col-sm-12">
		<ul class="nav nav-pills" id="nav">
			<li ><a href="/enjoyhisfy/report/finance/gotoFinanceReport.htm">收款汇总统计表</a></li>
			<li><a href="/enjoyhisfy/client/register/patient_report_view.htm">患者统计表</a></li>
			<c:if test="${userType==2}">
				<li><a href="/enjoyhisfy/client/register/nonarrival_report_view.htm">预约未到统计表</a></li>
			</c:if>
			<li><a href="/enjoyhisfy/report/source/gotoSourceReport.htm">来源统计表</a></li>
			<li><a href="/enjoyhisfy/report/income/gotoIncomeReport.htm">医生收入表</a></li>
			<c:if test="${userType==2}">
				<li><a href="/enjoyhisfy/report/ChargeDetail/gotoChargeDetailReport.htm">收费明细表</a></li>
				<li><a href="/enjoyhisfy/report/WriteOff/gotoWriteOffReport.htm">冲销调整明细表</a></li>
			</c:if>
		</ul>
	</div>

	<div class="col-sm-12" id="content">

	</div>

</div>


<!-- 模态框（Modal） -->
<script src="${path}/js/jquery-2.1.4.min.js"></script>
<script src="${path}/js/bootstrap.min.js"></script>
<script src="${path}/js/bootstrap-table.min.js"></script>
<script src="${path}/js/bootstrap-table-zh-CN.min.js"></script>
<script src="${path}/assets/js/bootbox.js"></script>
	<script src="${path}/assets/js/alert.js"></script>
<script src="${path}/laydate/laydate.js"></script>
<script src="${path}/js/selectUi.js"></script>
<script src="${path}/js/lq.datetimepick.js"></script>
<script src="${path}/js/ajax.js"></script>
<script>
//	$(function(){
//		//触发元素，默认显示第一页
//       $("#nav li a").eq(0).trigger("click");
//		//nav变色
//		$(".nav-pills li").click(function(){
//			$(this).addClass("active").siblings().removeClass("active");
//		});
//	});
	
	$(function(){
		//触发元素，默认显示第一页
       $("#nav li a").eq(0).trigger("click");
		//nav变色
		$("#nav li a").click(function(){
     
			$(this).parent().addClass("active").siblings().removeClass("active");
		});
	});

</script>
</body>
</html>
