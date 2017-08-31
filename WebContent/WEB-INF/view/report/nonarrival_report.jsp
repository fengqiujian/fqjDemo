<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>预约未到统计表</title>
	<%--<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>--%>
	<%--<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>--%>
	<%--<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css"/>--%>
	<%--<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css"/>--%>
</head>
<body>
<div class="panel-body" style="padding-bottom:0px;">
	<div class="col-sm-12">
		<div class="panel panel-default" style="background-color: none;">
			<div class="panel-body">
				<div class="col-sm-12">
					<div class="row">
						<form id="formSearch" class="form-horizontal">
							<div class="col-sm-3">
								<div class="form-group">
									<label  class="col-sm-4 control-label">日期</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="beginDate" name="beginDate">
					          <span class="date-icon" style="right: 16px;bottom: 7px;position: absolute;">
						      	<img src="${path}/img/date.jpg" alt="点击显示日期" class="img-responsive center-block" />
						      </span>
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label  class="col-sm-4 control-label">日期</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="endDate" name="endDate">
					          <span class="date-icon" style="right: 16px;bottom: 7px;position: absolute;">
						      	<img src="${path}/img/date.jpg" alt="点击显示日期" class="img-responsive center-block" />
						      </span>
									</div>
								</div>
							</div>
							<div class="col-sm-1">
								<button type="button" id="search-btn" class="btn btn-block btn-success">查询</button>
							</div>
							<div class="col-sm-1">
									    	<button type="button" class="btn btn-block btn-success" id="toExl">导出EXL</button>
									    </div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
		<table id="report-table"></table>
	</div>
</div>
<!--@*1、Jquery组件引用*@-->
<%--<script src="${path}/js/jquery-2.1.4.min.js"></script>--%>
<%--<script src="${path}/js/bootstrap.min.js"></script>--%>
<%--<script src="${path}/js/bootstrap-table.min.js"></script>--%>
<%--<script src="${path}/js/bootstrap-table-zh-CN.min.js"></script>--%>
<%--<script src="${path}/laydate/laydate.js"></script>--%>
<%--<script src="${path}/js/selectUi.js"></script>--%>
<%--<script src="${path}/js/lq.datetimepick.js"></script>--%>
<script>
	;!function(){
		laydate.skin('molv');
		var now =laydate.now();
		$('#beginDate').val(now)
		laydate({
			elem: '#beginDate',
			event: 'click', //触发事件
			format: 'YYYY-MM-DD', //日期格式
			istime: false, //是否开启时间选择
			isclear: true, //是否显示清空
			istoday: true, //是否显示今天
			issure: true, //是否显示确认
			festival: true, //是否显示节日
			min: '2000-01-01', //最小日期
			max: '2099-12-31', //最大日期
			start: now,    //开始日期
			fixed: false, //是否固定在可视区域
			zIndex: 9999999,
			choose: function(dates){ //选择好日期的回调
//				$('#report-table').bootstrapTable('refresh');
			}
		})

	}();

	;!function(){
		laydate.skin('molv');
		var now =laydate.now();
		$('#endDate').val(now)
		laydate({
			elem: '#endDate',
			event: 'click', //触发事件
			format: 'YYYY-MM-DD', //日期格式
			istime: false, //是否开启时间选择
			isclear: true, //是否显示清空
			istoday: true, //是否显示今天
			issure: true, //是否显示确认
			festival: true, //是否显示节日
			min: '2000-01-01', //最小日期
			max: '2099-12-31', //最大日期
			start: now,    //开始日期
			fixed: false, //是否固定在可视区域
			zIndex: 9999999,
			choose: function(dates){ //选择好日期的回调
//				$('#report-table').bootstrapTable('refresh');
			}
		})

	}();


	$(function () {
		//初始化表格
		initTable();

		$("#search-btn").click(function(){
			$('#report-table').bootstrapTable('refresh');
		});
		$("#toExl").click(function(){
			toExl();
		});
	});
	
	function toExl(){
	    	var beginDate=$("#beginDate").val();
	    	var endDate = $("#endDate").val();

	    	window.location = "/enjoyhisfy/client/register/nonarrival_report_dataToExcell.htm?beginDate="+beginDate+"&endDate="+endDate
	    }

	function initTable(){
		$('#report-table').bootstrapTable({
			method: 'post',
			url: "/enjoyhisfy/client/register/nonarrival_report_data.json",
			pagination: true,
			queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
			// 设置为 ''  在这种情况下传给服务器的值为：pageSize  pageNumber
			toolbar: '#toolbar',    //工具按钮用哪个容器
			striped: true,      //是否显示行间隔色
			cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: false,     //是否显示分页（*）
			sortable: false,      //是否启用排序
			sortOrder: "asc",     //排序方式
			pageNumber:1,      //初始化加载第一页，默认第一页
			pageSize: 1000,      //每页的记录行数（*）
//			pageList: [10,20,50],  //可供选择的每页的行数（*）
			queryParams: queryParams,//传递参数（*）
			sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
			minimumCountColumns: 2,    //最少允许的列数
			clickToSelect: true,    //是否启用点击选中行
			contentType: "application/x-www-form-urlencoded",
			searchOnEnterKey: true,
// 		search:true,
			columns:
					[
						{
							field: 'patId',
							align: 'center',
							title: '患者ID'
						},
						{
							field: 'patNo',
							align: 'center',
							title: '病历编号'
						},
						{
							field: 'patName',
							align: 'center',
							title: '患者姓名'
						},{
						field: 'mobile',
						align: 'center',
						title: '手机号'
					},{
						field: 'beginDate',
						align: 'center',
						title: '预约日期'
					},{
						field: 'beginTime',
						align: 'center',
						title: '时间',
						formatter: function(value,row,index){
							return row.beginTime.substring(11,16);
						}
					},{
						field: 'appoLen',
						align: 'center',
						title: '需时'
					},{
						field: 'serviceItems',
						align: 'center',
						title: '预约事项'
					},{
						field: 'remark',
						align: 'center',
						title: '预约备注'
					},{
						field: 'doctorName',
						align: 'center',
						title: '医师'
					},{
						field: 'lasthospDate',
						align: 'center',
						title: '最后就诊日'
					},{
						field: 'operator',
						align: 'center',
						title: '操作人'
					}
					],
			pagination:true,
		});
	}

	//table查询参数
	function queryParams(params) {
		return {
			beginDate:$("#beginDate").val(),
			endDate:$("#endDate").val(),
//			pageSize: params.pageSize,
//			pageNumber: params.pageNumber,
		};

	}



</script>
</body>
</html>