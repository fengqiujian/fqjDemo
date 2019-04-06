<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>账户管理</title>
		<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>
		<link href="../../css/bootstrap-table.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="../../css/his.css"/>
		<script src="../../js/jquery-2.1.4.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>
		<script src="../../js/bootstrap-table.min.js"></script>
 		<script src="../../js/bootstrap-table-zh-CN.min.js"></script>
 		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
		<script src="../../pagejs/client/patient/accountManagement.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid " style="margin-top: 15px;">
			   			<div class="row">
			   				<div class="col-sm-2" style="line-height: 30px;">
			   					<a class="text-dection" href="/enjoyhisfy/client/patient/patient_manager.htm">返回 /账户管理</a>		
			   				</div>	
			   				
			   			 	<c:if test="${isDoc}">
				   				<div class="col-sm-2" style="line-height: 30px;">
				   					<div class="btn btn-block btn-success" onclick="webCallAdvance(${patient.id})">预充值管理</div>	
				   				</div>	
				   				<!--<div class="col-sm-2" style="line-height: 30px;">
				   					<div class="btn btn-block btn-success">IC卡管理</div>	
				   				</div>
				   				<div class="col-sm-2" style="line-height: 30px;">
				   					<div class="btn btn-block btn-success">折扣卡管理</div>	
				   				</div>-->
				   				<div class="col-sm-2" style="line-height: 30px;">
				   					<div class="btn btn-block btn-success" onclick="webCallArrears(${patient.id})">收欠款</div>	
				   				</div>
			   				</c:if>
			   			</div>
			   		</div>
			   		<hr />
			   		<form class="form-horizontal" role="form">
			   			<input type="hidden" id="regId" value="${patient.id}"/>
						<div class="col-sm-2">
							<div class="form-group">
								<span>患者姓名：</span><span>${patient.patName}</span>
							</div>		   				
			   			</div>
			   			<div class="col-sm-2">
			   				<div class="form-group">
			   					<span>手机号码：</span><span>${patient.mobile}</span>
			   				</div>			   				
			   			</div>
			   			<div class="col-sm-2">
			   				<div class="form-group">
			   					<span>病历编号：</span><span>${patient.patNo}</span>
			   				</div>			   				
			   			</div>
			   			<div class="col-sm-2">
			   				<div class="form-group">
			   					<span>初诊医师：</span><span>${patient.maindocName}</span>
			   				</div>			   				
			   			</div>
			   			<div class="col-sm-2">
			   				<div class="form-group">
			   					<span>账户余额：</span><span>￥<i>${patient.accountAmount}</i><span>${msg}</span></span>
			   				</div>			   				
			   			</div>
			   			<%-- <div class="col-sm-2">
			   				<div class="form-group">
			   					<span>账户赠金：</span><span>￥<i>${patient.givenAmount}</i></span>
			   				</div>			   				
			   			</div> --%>
			   			<!--底部表格 START-->
			   			   <table class="table table-bordered" id="tb_departments" style="word-break:break-all; word-wrap:break-all;"></table>
			   			<!--底部表格 END-->
					</form>
				</div>				
			</div>
		</div>	
	</body>
</html>