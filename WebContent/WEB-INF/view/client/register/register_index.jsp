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
<title>今日就诊</title>
<link href="${path}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${path}/css/bootstrap-table.min.css" rel="stylesheet" />
<link href="${path}/css/his.css" rel="stylesheet" />
</head>
<body>
	<div class="panel-body no-padding" style="padding-bottom: 0px;">
		<input type="hidden" id="userType" value="${userType }" />
		<div class="col-sm-12">
			<div>
				<div class="panel-body no-padding">
					<form id="formSearch" class="form-horizontal">
						<div class="col-sm-10">
							<div class="row">
								<input type="hidden" id="dentistId" value="${dentistId }" />
								<div class="form-group" style="margin-top: 15px">
									<div class="col-sm-3">
										<label class="control-label col-sm-4" for="txt_search_departmentname">患者姓名</label>
										<div class="col-sm-8">
											<input id="name" name="name" type="text" class="form-control selectRegister" id="txt_search_departmentname" maxlength=50>
										</div>
									</div>
									<div class="col-sm-3">
										<label class="control-label col-sm-4" for="txt_search_statu">手机号</label>
										<div class="col-sm-8">
											<input id="mobile" name="mobile" type="text" class="form-control selectRegister" id="txt_search_statu" maxlength=15>
										</div>
									</div>
									<c:choose>
										<c:when test="${userType==2}">
											<%--护士端--%>
											<div class="col-sm-3">
												<div class="col-sm-6 ">
													<button id="clear-btn" type="button" class="btn btn-block btn-success">清空筛选</button>
												</div>
												<div class="col-sm-6 ">
													<button id="refresh-btn" type="button" class="btn btn-block btn-success">刷新</button>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<%--医生端--%>
											<div class="col-sm-6">
												<div class="row">
													<button id="clear-btn" type="button" class="btn  btn-success pull-left" style="margin-right: 3px;">清空筛选</button>
													<button id="refresh-btn" type="button" class="btn  btn-success pull-left" style="margin-right: 3px;">刷新</button>
													<c:if test="${userType==1 }">
														<button id="next-btn" type="button" class="btn  btn-success pull-left" style="margin-right: 3px;">下一位</button>
														<button id="recall-btn" type="button" class="btn  btn-success pull-left" style="margin-right: 3px;">重呼</button>
														<button id="again-btn" type="button" class="btn  btn-success pull-left" style="margin-right: 3px;">重排</button>
														<button id="setting-X-btn" type="button" class="btn  btn-success pull-left">配置X光</button>
													</c:if>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</form>
					<div class="col-sm-2 text-left" style="margin-top: 20px;">
						<a class="text-dection" href="/enjoyhisfy/client/register/register_history.htm">查看历史就诊记录</a>
					</div>
				</div>
			</div>
		</div>
		<div id="toolbar" class="btn-group btngroup">
			<button id="btn_add" type="button" class="btn btn-success btn-color" value="0">
				<span class="" aria-hidden="true">全部 (<i id="count1"></i>)
				</span>
			</button>
			<button id="btn_edit" type="button" class="btn btn-success" value="2">
				<span class="" aria-hidden="true">今日挂号(<i id="count2"></i>)
				</span>
			</button>
			<button id="btn_delete" type="button" class="btn btn-success" value="1">
				<span class="" aria-hidden="true">今日预约未到(<i id="count3"></i>)
				</span>
			</button>
		</div>
		<div class="col-sm-12">
			<table id="register-table"></table>
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
	<!-- 模态框（Modal） -->
	<!--弹出提示-->
	<div class="alert" id="unpaid-tip">
	</div>
	<!--@*1、Jquery组件引用*@-->
	<script src="${path}/js/jquery-2.1.4.min.js"></script>
	<script src="${path}/js/bootstrap.min.js"></script>
	<script src="${path}/js/bootstrap-table.min.js"></script>
	<script src="${path}/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
	<script src="${path}/js/unpaidTip.js"></script>
	<script src="${path}/js/client/register_index.js"></script>
	<script>
		var callNoIp = '${callNoIp}';
		var dentistId = '${dentistId}';
	</script>
</body>
</html>