<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width" />
<title>就诊管理</title>
<link href="${path}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${path}/css/bootstrap-table.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
</head>
<body>
	<input type="hidden" name="pid" id="pid" value="${patient.id }" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="container-fluid ">
					<div class="row">
						<div class="col-sm-2 " style="line-height: 30px; margin-top: 15px;">
							<a class="text-dection" href="/enjoyhisfy/client/patient/patient_manager.htm">返回 /</a> <a class="text-dection" href="#">就诊管理</a>
						</div>
					</div>
				</div>
				<hr />
				<div class="col-md-12">
					<div class="col-sm-3">
						<div class="form-group">
							<span>患者姓名：</span><span>${patient.patName }</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<span>性别：</span><span>${patient.userSex }</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<span>年龄：</span><span>${patient.age }</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<span>手机号码：</span><span>${patient.mobile }</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<span>病历编号：</span><span>${patient.patNo }</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<span>初诊医生：</span><span>${patient.docName }</span>
						</div>
					</div>
					<br class="clear" />
					<!--底部表单 START-->
					<div class="col-sm-9 pull-right">
						<div class="col-sm-4"></div>
					</div>
					<!--底部表单 END-->
				</div>
				<table id="patient-table"></table>
			</div>
		</div>
	</div>
	<!--@*1、Jquery组件引用*@-->
	<script src="${path}/js/jquery-2.1.4.min.js"></script>
	<script src="${path}/js/bootstrap.min.js"></script>
	<script src="${path}/js/bootstrap-table.min.js"></script>
	<script src="${path}/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
	<script src="${path}/laydate/laydate.js"></script>
	<script src="${path}/js/selectUi.js"></script>
	<script src="${path}/js/lq.datetimepick.js"></script>
	<script src="${path}/js/client/patient_register.js"></script>
</body>
</html>