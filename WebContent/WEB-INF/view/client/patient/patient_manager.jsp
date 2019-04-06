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
<title>患者管理</title>
<link href="${path}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${path}/css/bootstrap-table.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
</head>
<body>
	<input type="hidden" id="userType" value="${userType }" />

	<div class="panel-body" style="padding-bottom: 0px;">
		<div class="col-sm-12">
			<div>
				<div class="">
					<div class="col-sm-12">
						<div class="row">
							<form id="formSearch" class="form-horizontal">
								<div class="form-group" style="">
									<div class="col-sm-3">
										<label class="control-label col-sm-5" for="txt_search_departmentname">患者姓名</label>
										<div class="col-sm-7">
											<input id="name" name="name" type="text" class="form-control selectPatient" id="txt_search_departmentname" maxlength=50>
										</div>
									</div>
									<div class="col-sm-3">
										<label class="control-label col-sm-4" for="txt_search_statu">手机号</label>
										<div class="col-sm-7">
											<input id="mobile" name="mobile" type="text" class="form-control selectPatient" id="txt_search_statu" maxlength=15>
										</div>
									</div>

									<c:if test="${userType==1 }">
										<input type="hidden" value="${dentistId }" id="docId" />
									</c:if>
									<c:if test="${userType==2 }">
										<div class="col-sm-3">
											<label class="control-label col-sm-5" for="txt_search_statu">初诊医生</label>
											<div class="col-sm-7">
												<select size="1" rows="10" class="form-control" id="docId" name="docId" data-id="${dentistId }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}">
												</select>
											</div>
										</div>
									</c:if>

									<div class="col-sm-3">
										<div class="col-sm-6">
											<button id="clean-select-btn" type="button" class="btn btn-block btn-success">清空筛选</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<table id="patient-table"></table>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<!--加载新页面模态框（Modal） -->
	<div class="modal bs-example-modal-lg" tabindex="-1" id="modal" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-lg" style="width: 1150px;">
			<div class="modal-content">
				<div class="col-sm-12" style="z-index: 100; margin-bottom: 10px;">
					<div class="row">
						<!--table START-->
						<div class="col-sm-12" style="background-color: #F5F5F5;">
							<div class="row">
								<div class="col-sm-12 tabbox">
									<div id="myTabContent" class="tab-content tabcon">
										<iframe src="/enjoyhisfy/client/patient/patient_add.htm" name="cframe" id="cframe" height="600px" width="100%" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" allowtransparency="yes"> </iframe>
										<!--ajax 加载位置-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br class="clear" />
				<div class="modal-content">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--选择处置项 模态框（Modal） -->

	<!-- 更多操作模态框（Modal） -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="margin-top: 10%;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title text-center" id="myModalLabel ">更多操作</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="create"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 模态框（Modal） -->
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
	<script src="${path}/js/client/patient_manager.js"></script>
</body>
</html>