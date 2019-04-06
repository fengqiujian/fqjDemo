<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>患者统计表</title>
	<%--<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>--%>
	<%--<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>--%>
	<%--&lt;%&ndash;<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css"/>&ndash;%&gt;--%>
	<%--&lt;%&ndash;<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css"/>&ndash;%&gt;--%>
</head>
<body>
<input type="hidden" value="${uid}" id="uid"/>
<input type="hidden" value="${userType}" id="userType"/>

<div class="panel-body" style="padding-bottom:0px;">
	<div class="col-sm-12">
		<div class="panel panel-default" style="background-color: #f5f5f5;">
			<div class="panel-body">
				<div class="col-sm-12">
					<div class="row">
						<form id="formSearch" class="form-horizontal">
							<div class="col-sm-3">
								<div class="form-group">
									<label  class="col-sm-4 control-label">开始日期</label>
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
									<label  class="col-sm-4 control-label">截止日期</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="endDate" name="endDate">
					          <span class="date-icon" style="right: 16px;bottom: 7px;position: absolute;">
						      	<img src="${path}/img/date.jpg" alt="点击显示日期" class="img-responsive center-block" />
						      </span>
									</div>
								</div>
							</div>
							<c:if test="${userType==2}">
								<div class="col-sm-3">
									<div class="form-group">
										<label class="col-sm-4 control-label">医生姓名</label>
										<div class="col-sm-8" id ="search">
											<select class="form-control" id="dentistId" name="dentistId" required="required" data-id="${register.dentistId }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}">
											</select>
										</div>
									</div>
								</div>
							</c:if>

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
<%--&lt;%&ndash;<script src="${path}/laydate/laydate.js"></script>&ndash;%&gt;--%>
<%--<script src="${path}/js/selectUi.js"></script>--%>
<%--&lt;%&ndash;<script src="${path}/js/lq.datetimepick.js"></script>&ndash;%&gt;--%>
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


		//动态获取select的option
		$("select").each(function(i,n){
			var url = $('#'+n.id).attr('url');
			if(url!=undefined){
				var selectedId = $('#'+n.id).attr('data-id');
				$.ajax({
					url : url,
					type : 'post',
					async: false,
					dataType : 'json',
					success : function(data) {
						var tempAjax = "";
						tempAjax += "<option value=''>请选择</option>";
						$.each(data.returndata,function(i,n){
							if(n.id==selectedId)
								tempAjax += "<option value='"+n.id+"' selected='selected'>"+n.name+"</option>";
							else
								tempAjax += "<option value='"+n.id+"'>"+n.name+"</option>";
						});
						$("#"+n.id).empty();
						$("#"+n.id).append(tempAjax);
					}
				});
			}
		});

		//初始化表格
		initTable();


		$("#search-btn").click(function(){
			$('#report-table').bootstrapTable('refresh');
			setTimeout('addRow()',300);
		});
		$("#toExl").click(function(){
			toExl();
		});
		
		function toExl(){
	    	var beginDate=$("#beginDate").val();
	    	var endDate = $("#endDate").val();
	    	var docId = $("#search-text").attr("code");
	    	
	    	var userType = $("#userType").val();
			var doctorId;
			if(userType==1){
				doctorId = $("#uid").val();
			}else{
				doctorId = $("#dentistId option:selected").val();
			}
			
	    	window.location = "/enjoyhisfy/client/register/patient_report_dataToExcell.htm?beginDate="+beginDate+"&endDate="+endDate+"&doctorId="+doctorId
	    }

		$('#testid').click(function(){
			$('#report-table').bootstrapTable('insertRow', {
				index: 1,
				row: {
					registerDate: 1,
					patientNum: 2,
					registerNum: 1,
					newRegisterNum: 1,
					oldRegisterNum: 1,
					bookingNum: 1,
					nonArrival: 1,
				}
			});
		});

	});

	function addRow(){
		var length = $('#report-table').bootstrapTable('getOptions').data.length;
		var patientNumTotal = 0;
		var registerNumTotal = 0 ;
		var newRegisterNumTotal = 0;
		var	oldRegisterNumTotal = 0;
		var	bookingNumTotal= 0;
	    var	nonArrivalTotal= 0;
		$.each($('#report-table').bootstrapTable('getOptions').data,function(i,n){
			patientNumTotal = patientNumTotal+ n.patientNum;
			registerNumTotal = registerNumTotal+ n.registerNum;
			newRegisterNumTotal = newRegisterNumTotal+ n.newRegisterNum;
			oldRegisterNumTotal = oldRegisterNumTotal+ n.oldRegisterNum;
			bookingNumTotal = bookingNumTotal+ n.bookingNum;
			nonArrivalTotal = nonArrivalTotal+ n.nonArrival;
		});
		$('#report-table').bootstrapTable('insertRow', {
			index: length,
			row: {
				registerDate: "合计",
				patientNum: patientNumTotal,
				registerNum: registerNumTotal,
				newRegisterNum: newRegisterNumTotal,
				oldRegisterNum: oldRegisterNumTotal,
				bookingNum: bookingNumTotal,
				nonArrival: nonArrivalTotal,
			}
		});
	}

	function initTable(){
		$('#report-table').bootstrapTable({
			method: 'post',
			url: "/enjoyhisfy/client/register/patient_report_data.json",
			pagination: true,
			queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
			// 设置为 ''  在这种情况下传给服务器的值为：pageSize  pageNumber
			toolbar: '#toolbar',    //工具按钮用哪个容器
			striped: true,      //是否显示行间隔色
			cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true,     //是否显示分页（*）
			sortable: false,      //是否启用排序
			sortOrder: "asc",     //排序方式
			pageNumber:1,      //初始化加载第一页，默认第一页
			pageSize: 1000,      //每页的记录行数（*）
			pageList: [10,20,50],  //可供选择的每页的行数（*）
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
							field: 'registerDate',
							align: 'center',
							title: '就诊日期'
						},
						{
							field: 'patientNum',
							align: 'center',
							title: '患者总数'
						},
						{
							field: 'registerNum',
							align: 'center',
							title: '挂号总数'
						},{
						field: 'newRegisterNum',
						align: 'center',
						title: '初诊挂号人数'
					},{
						field: 'oldRegisterNum',
						align: 'center',
						title: '复诊挂号人数'
					},{
						field: 'bookingNum',
						align: 'center',
						title: '预约人数'
					},{
						field: 'nonArrival',
						align: 'center',
						title: '预约未到'
					}
					],
			pagination:true,
		});
		setTimeout('addRow()',300);
	}

	//table查询参数
	function queryParams(params) {
		var userType = $("#userType").val();
		var doctorId;
		if(userType==1)
			doctorId = $("#uid").val();
		else
			doctorId = $("#dentistId option:selected").val();
		return {
			beginDate:$("#beginDate").val(),
			endDate:$("#endDate").val(),
			doctorId:doctorId,
			doctorId:doctorId,
			pageSize: params.pageSize,
			pageNumber: params.pageNumber,
		};

	}
	function sendMsgOrCall(registerId,type){

		$.ajax({
			url: "/enjoyhisfy/client/register/update_msg_call.json",
			data:{"registerId":registerId,"type":type},
			type: 'post',
			dataType: 'json',
			success: function (data) {
				$('#report-table').bootstrapTable('refresh');
			}
		});
	}

	function sendMsg(mobile,registerId){
		$.ajax({
			url: "/enjoyhisfy/client/register/send_msg.json",
			data:{"mobile":mobile,"registerId":registerId},
			type: 'post',
			dataType: 'json',
			success: function (data) {
				$('#report-table').bootstrapTable('refresh');
			}
		});
	}


</script>
</body>
</html>