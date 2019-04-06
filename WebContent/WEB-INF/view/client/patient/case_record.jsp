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
<title>病历记录</title>
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
							<a class="text-dection" href="/enjoyhisfy/client/patient/patient_manager.htm">返回 /</a> <a class="text-dection" href="#">病历记录</a>
						</div>
						<c:if test="${userType==1}">
							<div class="col-sm-4" style="margin-top: 15px;">
								<div class="col-sm-6 ">
									<a class="btn btn-block btn-success" href="/enjoyhisfy/client/case/case_add_view.htm?pid=${patient.id}">新建病历记录</a>
								</div>
								<div class="col-sm-6 ">
									<button id="setting-X-btn" type="button" class="btn btn-block btn-success">打开X光软件|配置</button>
								</div>
							</div>
						</c:if>
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
	<script>
		$(function() {
			initTable();

			//配置X光
			$('#setting-X-btn').click(function() {
				Cef.webCallDoctorXraySoft();
			});

			//动态获取select的option
			$("select").each(function(i, n) {
				var url = $('#' + n.id).attr('url');
				if (url != undefined) {
					var selectedId = $('#' + n.id).attr('data-id');
					$.ajax({
						url : url,
						type : 'post',
						async : false,
						dataType : 'json',
						success : function(data) {
							var tempAjax = "";
							tempAjax += "<option value=''>请选择</option>";
							$.each(data.returndata, function(i, n) {
								if (n.id == selectedId)
									tempAjax += "<option value='"+n.id+"' selected='selected'>" + n.name + "</option>";
								else
									tempAjax += "<option value='"+n.id+"'>" + n.name + "</option>";
							});
							$("#" + n.id).empty();
							$("#" + n.id).append(tempAjax);
						}
					});
				}
			});

			$('.selectPatient').keyup(function() {
				$('#patient-table').bootstrapTable('refresh');
			});

			$('#docId').change(function() {
				$('#patient-table').bootstrapTable('refresh');
			});

			//选择患者
			$('#open-patient-dialog').click(function() {
				$('#select-patient-content').modal({
					backdrop : true,
					keyboard : true,
					show : true
				});
			});

			openDiolag2View();

			$('#clean-select-btn').click(function() {
				$('#formSearch')[0].reset();
				$('#patient-table').bootstrapTable('refresh');
			});
		});

		function initTable() {
			$('#patient-table').bootstrapTable({
				method : 'post',
				url : "/enjoyhisfy/client/case/patient_case_data.json",
				pagination : true,
				queryParamsType : '', //默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
				// 设置为 ''  在这种情况下传给服务器的值为：pageSize  pageNumber
				toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : true, //是否显示分页（*）
				sortable : false, //是否启用排序
				sortOrder : "asc", //排序方式
				pageNumber : 1, //初始化加载第一页，默认第一页
				pageSize : 1000, //每页的记录行数（*）
				pageList : [ 10, 20, 50 ], //可供选择的每页的行数（*）
				queryParams : queryParams,//传递参数（*）
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
				minimumCountColumns : 2, //最少允许的列数
				clickToSelect : true, //是否启用点击选中行
				contentType : "application/x-www-form-urlencoded",
				searchOnEnterKey : true,
				columns : [ {
					field : 'blDate',
					title : '记录日期',
					align : 'center',
					formatter : function(value, row, index) {
						return value.substring(0, 16);
					}

				}, {
					field : 'maindocName',
					title : '医生',
					align : 'center'
				}, {
					field : 'jzsx',
					title : '就诊事项',
					align : 'center',
					formatter : function(value, row, index) {
						return '<p style=" word-wrap:break-word; word-break:break-all;">' + value + '</p>'
					}
				}, {
					field : 'remark',
					title : '备注',
					align : 'center',
					formatter : function(value, row, index) {
						return '<p style=" word-wrap:break-word; word-break:break-all;">' + value + '</p>'
					}
				} ],
				pagination : true,
				onClickCell : function(field, value, row, cell) {
					window.location = "/enjoyhisfy/client/case/case_detail_view.htm?caseId=" + row.id;
				}
			});
		}

		//列表切换
		$(function() {
			$(".btngroup button").on("click", function() {
				$(this).addClass("btn-color").siblings().removeClass("btn-color");
				$('#patient-table').bootstrapTable('refresh');
			});
		});

		//table查询参数
		function queryParams(params) {
			return {
				pid : $('#pid').val(),
				pageSize : params.pageSize,
				pageNumber : params.pageNumber,
			};
		}

		//B调C:
		function B2C(patId, statementId, type) {
			patId = patId.toString();
			statementId = statementId.toString();
			Cef.webCallCharge(patId, statementId, type);
		}

		//根据状态显示提示框内容
		function addbtn(sta1, sta2, sta3, sta4, sta5, sta6, sta7) {
			$(".create").empty();
			var str = "";//节点
			if (sta1 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">新建挂号</a></div>';
			if (sta2 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">新建预约</a></div>';
			if (sta3 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">缴费</a></div>';

			str += '<br class="clear"  />';
			str += '<div class="kong"></div>';

			if (sta4 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">患者资料</a></div>';
			if (sta5 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">账户管理</a></div>';
			if (sta6 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/patient/patient_register.json?pid=' + 1 + '">就诊管理</a></div>';
			if (sta7 == 1)
				str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">病历记录</a></div>';

			$(".create").append(str);
		};

		function mytest(registerId, pid, status) {
			//状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
			addbtn(1, 1, 1, 1, 1, 1, 1);
		}

		function openDiolag2View() {
			$('.tabbtn a').click(function() {//打开模态框	
				$("#modal").modal({
					backdrop : "static",//阻止点击蒙版 模态框被关闭
					keyboard : true
				})
			});
		}
	</script>
</body>
</html>