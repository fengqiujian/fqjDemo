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
			<div class="">
				<div class="panel-body">
					<form id="formSearch" class="form-horizontal">
						<div class="col-sm-10">
							<div class="row">
								<input type="hidden" id="dentistId" value="${dentistId }" />
								<div class="col-sm-3">
									<div class="form-group">
										<label for="firstname" class="col-sm-4 control-label">日期</label>
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
												<option value="4">已诊结</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<div class="col-sm-4">
									<a style="cursor: pointer;padding-top: 5px;display:block" onclick="javascript:window.location.reload()" />刷新</a>
								</div>
								<div class="col-sm-8">
									<input type="button" class="btn btn-block btn-success" id="setting-btn" value="配置软件" />
								</div>
							</div>
						</div>
					</form>
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
						<br class="clear" />
						<div class="kong"></div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-block btn-default">患者资料</button>
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-block btn-default">账户管理</button>
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-block btn-default">就诊管理</button>
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-block btn-default">病历记录</button>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<!--  <button type="button" class="btn btn-primary">
	               提交更改
	            </button>-->
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

	<script>
		;
		!function() {
			laydate.skin('molv');
			var now = laydate.now();
			$('#dateTime').val(now)
			laydate({
				elem : '#dateTime',
				event : 'click', //触发事件
				format : 'YYYY-MM-DD', //日期格式
				istime : false, //是否开启时间选择
				isclear : true, //是否显示清空
				istoday : true, //是否显示今天
				issure : true, //是否显示确认
				festival : true, //是否显示节日
				min : '2000-01-01', //最小日期
				max : '2099-12-31', //最大日期
				start : now, //开始日期
				fixed : false, //是否固定在可视区域
				zIndex : 9999999,
				choose : function(dates) { //选择好日期的回调
					$('#register-table-history').bootstrapTable('refresh');
				}
			})

		}();
		$(function() {
			initTable();

			$('.selectRegister').keyup(function() {
				$('#register-table-history').bootstrapTable('refresh');
			});

			$('#type').change(function() {
				$('#register-table-history').bootstrapTable('refresh');
			});

			$("#setting-btn").click(function() {
				//		 1.配置X光
				Cef.webCallXraySoft()
			});

		});

		function initTable() {
			$('#register-table-history').bootstrapTable({
				method : 'post',
				url : "/enjoyhisfy/client/register/find_register.json",
				toolbar : '#toolbar', //工具按钮用哪个容器
				striped : true, //是否显示行间隔色
				cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				pagination : false, //是否显示分页（*）
				queryParams : queryParams,//传递参数（*）
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
				clickToSelect : true, //是否启用点击选中行
				contentType : "application/x-www-form-urlencoded",
				searchOnEnterKey : true,
				columns : [ {
					field : 'isnew',
					sortable : true,
					align : 'center',
					title : '初/复诊',
					formatter : function(value, row, index) {
						if (value == 0)
							return "复诊";
						else
							return "初诊";
					}
				}, {
					field : 'status',
					align : 'center',
					title : '就诊状态',
					formatter : function(value, row, index) {
						//状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
						if (value == 1)
							return '预约';
						else if (value == 2)
							return '已挂号';
						else if (value == 3)
							return '待收费';
						else if (value == 4)
							return '已完成';
						else if (value == 5)
							return "预约未到";
						else if (value == 6)
							return "取消预约";
						else
							return "退号";
					}
				}, {
					field : 'patNo',
					align : 'center',
					title : '病历编号'
				}, {
					field : 'docName',
					sortable : true,
					align : 'center',
					title : '医生'
				}, {
					field : 'patName',
					sortable : true,
					align : 'center',
					title : '患者姓名'
				}, {
					field : 'mobile',
					align : 'center',
					title : '手机号码'
				}, {
					field : 'sex',
					align : 'center',
					title : '性别'
				}, {
					field : 'age',
					align : 'center',
					title : '年龄'
				}, {
					field : 'beginTime',
					align : 'center',
					title : '预约时间',
					sortable : true,
					formatter : function(value, row, index) {
						if (row.beginTime != undefined && row.beginTime != null) {
							return row.beginTime.substring(11, 16);
						} else {
							return null;
						}
					}
				}, {
					field : 'ghTime',
					align : 'center',
					title : '挂号时间',
					sortable : true,
					formatter : function(value, row, index) {
						if (row.ghTime != undefined && row.ghTime != null) {
							return row.ghTime.substring(11, 16);
						} else {
							return null;
						}
					}
				}, {
					field : 'serviceItems',
					align : 'center',
					sortable : true,
					title : '事项'
				}, {
					field : 'source',
					align : 'center',
					title : '来源'
				}, {
					field : 'id',
					align : 'center',
					title : '更多操作',
					formatter : function(value, row, index) {
						var str = '<a href="#" onclick="B2C(' + row.patId + ');">';
						str = str + '<span >打开软件</span>';
						str = str + '</a>';
						return str;
					}
				} ],
			});
		}

		//列表切换
		$(function() {
			$(".btngroup button").on("click", function() {
				$(this).addClass("btn-color").siblings().removeClass("btn-color");
				$('#register-table-history').bootstrapTable('refresh');
			});
		});

		//table查询参数
		function queryParams(params) {
			return {
				userType : $('#userType').val(),
				dentistId : $('#dentistId').val(),
				dateTime : $('#dateTime').val(),
				name : $('#name').val(),
				mobile : $('#mobile').val(),
				type : $('#type').val(),
				pageSize : params.pageSize,
				pageNumber : params.pageNumber,
				sortName : params.sort,
				sortOrder : params.order
			};

		}

		//B调C:
		function B2C(patId) {
			//		   alert("patId="+patId);
			//打开X光
			patId = patId.toString();
			Cef.webCallXray(patId);
		}
	</script>
</body>
</html>