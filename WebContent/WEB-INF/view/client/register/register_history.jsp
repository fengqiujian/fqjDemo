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
<title>就诊记录</title>
<link href="${path}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${path}/css/bootstrap-table.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<input type="hidden" id="userType" value="${userType }" />
		<div class="col-sm-12">
			<div class="row">
				<div class="col-sm-12">
					<div style="margin-bottom: 0; margin-top: 15px;">
						<div class="panel-body" style="padding: 0px;">
							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-2">
										<div class="row">
											<ol class="breadcrumb">
												<li><a href="/enjoyhisfy/client/register/register_index.htm">返回</a></li>
												<li><a href="#">查看历史就诊记录</a></li>
											</ol>
										</div>
									</div>
									<form id="formSearch" class="form-horizontal">
										<div class="col-sm-10">
											<div class="row">
												<input type="hidden" id="dentistId" value="${dentistId }" />
												<div class="col-sm-3">
													<div class="form-group">
														<label class="col-sm-4 control-label">日期</label>
														<div class="col-sm-8">
															<input type="text" class="form-control" id="dateTime" name="dateTime"> <span class="date-icon" style="right: 16px; bottom: 7px; position: absolute;"> <img class="img-responsive center-block" />
															</span>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label col-sm-4" for="txt_search_departmentname">患者姓名</label>
														<div class="col-sm-8">
															<input id="name" name="name" type="text" class="form-control selectRegister" id="txt_search_departmentname" maxlength=50>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label col-sm-4" for="txt_search_statu">手机号</label>
														<div class="col-sm-8">
															<input id="mobile" name="mobile" type="text" class="form-control selectRegister" id="txt_search_statu" maxlength=15>
														</div>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label col-sm-4" for="txt_search_statu">状态</label>
														<div class="col-sm-8">
															<select size="1" rows="10" class="form-control" id="type" name="type">
																<option value="0">全部</option>
																<option value="1">预约未到</option>
																<option value="2">已挂号</option>
																<option value="3">待收费</option>
																<option value="4">已完成</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<table id="register-table-history"></table>
				</div>
			</div>
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="margin-top: 10%;">
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
		</div>
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
	<script src="${path}/js/client/register_history.js"></script>
	<script>
		var callNoIp = '${callNoIp}';
		var dentistId = '${dentistId}';
	</script>
</body>
</html>
