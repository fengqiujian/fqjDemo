<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>修改预约</title>
	<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	<link rel="stylesheet" href="${path}/layer/skin/layer.css" />

</head>
<body>
<input type="hidden" value="${userType}" id="userType"/>
<input type="hidden" value="${uid}" id="uid"/>

<div class="container-fluid pad-top">
	<div class="row">
		<div class="col-sm-12">
			<div class="container-fluid ">
				<div class="col-sm-2 ">
					<div class="row">
						<ol class="breadcrumb">
							<li><a href="/enjoyhisfy/client/register/register_index.htm">返回</a></li>
							<li><a href="#">预约</a></li>
						</ol>
					</div>
				</div>
				<div class="col-sm-10 ">
					<div class="row">
						<div class="col-sm-2" style="margin-left:-20px">
							<button id="reset-btn" type="button" class="btn btn-block btn-success">初诊预约</button>
						</div>
						<div class="col-sm-2">
							<button id="open-patient-dialog" type="button" class="btn btn-block btn-success">复诊预约</button>
						</div>
						<div class="col-sm-2">
							<button id="booking-plan-btn" type="button" class="btn btn-block btn-success">全部预约计划</button>
						</div>
					</div>
				</div>
			</div>
			<hr />
			<form id="booking-form" class="form-horizontal" role="form" action="/enjoyhisfy/client/register/save_booking" method="post">
				<input id="id" name="id"  type="hidden"  value="${register.id}" >
				<input id="patId" name="patId"  type="hidden"  value="${register.patient.id }" >
				<input id="isAppoint" name="isAppoint"  type="hidden"  value="1" >
				<input id="status" name="status"  type="hidden"  value="1" >
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">患者姓名<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="patName" name="patName" value="${register.patient.patName }" placeholder="请输入名字" required="required" readonly="readonly" maxlength=50>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">病历编号<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="patNo" name="patNo" value="${register.patient.patNo }" readonly="readonly"  placeholder="自动生成" readonly="readonly">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">性别<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<%--<select id="userSex" name="userSex" class="form-control" value="${register.patient.userSex }" required="required">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>--%>
								<input type="text" class="form-control" id="userSex" name="userSex" value="${register.patient.userSex }" readonly="readonly"  placeholder="" readonly="readonly">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">手机号码<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="mobile" name="mobile" value="${register.patient.mobile }" required="required"  placeholder="请输入手机号码" readonly="readonly" maxlength=15>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">年龄</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="age" name="age"
								   placeholder="请输入年龄" value="${register.patient.age }" onkeyup="this.value=this.value.replace(/\D/g,'')"   readonly="readonly" maxlength=11>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="control-label col-sm-2" for="date">出生年月</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="birthday" name="strBirthday" value="${register.patient.fmtBirthday }" required="required"  placeholder="请选择" readonly="readonly">
						</div>
					      <span class="date-icon" style="right: 127px;bottom: 22px;position: absolute;">
					      	<img  class="img-responsive center-block" />
					      	<%-- src="${path}/img/date.jpg" --%>
					      </span>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">预约医生<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<c:choose>
								<c:when test="${userType==1}">
									<select class="form-control" id="dentistId" name="dentistId" required="required" data-id="${uid}" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'id=${uid}'}">
									</select>
								</c:when>
								<c:otherwise>
									<select class="form-control" id="dentistId" name="dentistId" required="required" data-id="${register.dentistId }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}">
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">预约日期<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="bookingDate" name="bookingDate" value="${register.bookingDate}">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">预约时间<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="begin" name="begin" value="${register.begin }">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">预约时长<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="hidden" id="appoLenId" value="${register.appoLen}"/>
							<select class="form-control" id="appoLen">
								<option value="">请选择</option>
								<option value="15">15分钟</option>
								<option value="30">30分钟</option>
								<option value="45">45分钟</option>
								<option value="60">1小时</option>
								<option value="120">2小时</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label  class="col-sm-2 control-label">预约目的<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<select class="form-control" id="serviceItems" name="serviceItems" required="required" data-id="${register.serviceItems }"
									url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_dict&&column1=dict_name&&column2=dict_name&&sqlstr={'where':'dict_type=\'JZMD\''}">>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-6" style="display: none;">
					<div class="form-group">
						<label  class="col-sm-2 control-label">结束时间<sup style="color: red">*</sup></label>
						<div class="col-sm-8">
							<input type="hidden" class="form-control" id="end" name="end">
						</div>
					</div>
				</div>
				<br style="clear:both" />
				<div class="col-sm-12 ">
					<div class="form-group">
						<label  class="col-sm-1 control-label">预约备注</label>
						<div class="col-sm-11">
							<textarea class="form-control" name="remark" id="remark" rows="6" cols="" maxlength=255></textarea>
						</div>
					</div>
				</div>
				<br style="clear:both" />
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1">
						<button id="add-booking-btn" type="button" class="btn btn-block btn-success" style="margin-left:15px;">完成预约</button>
					</div>
					<div class="col-sm-2 col-sm-offset-1">
						<button id="exit_booking" type="button" value="${register.id }" class="btn btn-block btn-success">取消预约</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!--  modal-dialog -->
<div id="select-patient-content" class="modal fade" tabindex="-1">
	<div class="modal-dialog" style="width: 820px;">
		<div class="modal-content">
			<span class="counter pull-right"></span>
			<div class="modal-header">
				<h3 class="smaller lighter blue no-margin"><span id="">选择患者</span></h3>
			</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" style="margin-top:15px">
						<div class="col-sm-4">
							<div class="row">
								<label class="control-label col-sm-4" for="txt_search_departmentname">患者姓名</label>
								<div class="col-sm-8">
									<input id="select_patName" name="patName" type="text" class="form-control selectPatient" id="txt_search_departmentname" placeholder="首字母、拼音" maxlength=50/>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="row">
								<label class="control-label col-sm-4" for="txt_search_statu">手机号</label>
								<div class="col-sm-8">
									<input id="select_mobile" name="mobile" type="text"  class="form-control selectPatient" id="txt_search_statu" placeholder="手机号码" maxlength=15/>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="row">
								<label class="control-label col-sm-4" for="txt_search_statu">初诊医生</label>
								<div class="col-sm-8">
									<select class="form-control" id="select_maindocId" name="maindocId" required="required" data-id="${register.dentistId }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}">
									</select>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!--患者table -->
			<div class="modal-body">
				<table id="patient-table" class="col-xs-12" ></table>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>

<script src="${path}/js/jquery-2.1.4.min.js"></script>
<script src="${path}/js/bootstrap.min.js"></script>
<script src="${path}/laydate/laydate.js"></script>
<script src="${path}/js/selectUi.js"></script>
<script src="${path}/js/lq.datetimepick.js"></script>
<script src="${path}/assets/js/bootstrap-table.min.js"></script>
<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
<script src="${path}/js/jquery/jquery.form.js"></script>
<script src="${path}/layer/layer.js"></script>

<script>
/*	//出生日期选择
	;!function(){
		laydate.skin('molv');
		laydate({
			elem: '#birthday',
			event: 'click', //触发事件
			format: 'YYYY-MM-DD', //日期格式
			//istime: true, //是否开启时间选择
			isclear: true, //是否显示清空
			istoday: true, //是否显示今天
			issure: true, //是否显示确认
			festival: true, //是否显示节日
			min: '1900-01-01', //最小日期
			max: '2099-12-31', //最大日期
			start: '1970-6-15',  //开始日期
			fixed: false, //是否固定在可视区域
			zIndex: 9999999,
			choose: function(dates){ //选择好日期的回调
				//alert(dates);
				var age=$("#age");//年龄
				var mydate = new Date();//获得当前年份 2016
				age.val(mydate.getFullYear()-dates.substr(0,4));
			}
		})
	}();*/
	;!function(){
		laydate.skin('molv');
		var now =laydate.now();
		laydate({
			elem: '#bookingDate',
			event: 'click', //触发事件
			format: 'YYYY-MM-DD', //日期格式
			//istime: true, //是否开启时间选择
			isclear: true, //是否显示清空
			istoday: true, //是否显示今天
			issure: true, //是否显示确认
			festival: true, //是否显示节日
			min: now, //最小日期
			max: '2099-12-31', //最大日期
			fixed: false, //是否固定在可视区域
			zIndex: 9999999
		})

	}();

	$(function(){
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

		//回显预约时长
		$("#appoLen option").each(function(i,n){
			var temp = $("#appoLenId").val();
			if(n.value==temp){
				$(n).attr("selected",true);
				getendtime();
			}
		});


		selectPatient();


		//选择患者
		$('#open-patient-dialog').click(function(){
			$('#formSearch')[0].reset();
// 			$('#patient-table').bootstrapTable('refresh');
			selectPatient();
			$('#select-patient-content').modal({  backdrop:true,    keyboard:true,    show:true});
		});

		$('.selectPatient').keyup(function () {
			$('#patient-table').bootstrapTable('refresh');
		});

		$('#select_maindocId').change(function(){
			$('#patient-table').bootstrapTable('refresh');
		});


		$("#add-booking-btn").click(function () {
			$("#booking-form").ajaxSubmit(function (result) {
				if(result.returndata){
					alert("操作成功！",cb);
					//B2C(1);
					//window.location="/enjoyhisfy/client/register/register_index.htm";
				}else{
					alert("操作失败！");
				}
			});
			return false;
		});
var cb = function(){
	B2C(1);
	window.location="/enjoyhisfy/client/register/register_index.htm";
}
		$('#reset-btn').click(function(){
			$("#booking-form")[0].reset();
		});


		//跳转全部预约计划页面
		$("#booking-plan-btn").click(function(){
			var patId = $("#patId").val();
			var registerId = $("#id").val();
			if($("#userType").val()==1){
				var doctorId = $("#uid").val();
				window.location="/enjoyhisfy/client/register/doctor_plan_view.htm?doctorId="+doctorId;
			}else {
				window.location="/enjoyhisfy/client/register/booking_plan_view.htm?registerId="+registerId+"&&patId="+patId+"&&type=2";
			}

		});

		//取消预约操作
		$("#exit_booking").click(function(){
			var id = $(this).val();
			layer.prompt({title: '请填写取消原因，并确认', formType: 2}, function(text){
				var remark = text;
				$.ajax({
					url : '/enjoyhisfy/client/register/exit_booking',
					data: {"id":id,"remark":"取消预约备注:"+remark},
					type : 'post',
					dataType : 'json',
					success : function(data) {
						layer.msg('您已成功取消预约！');
						B2C(1);
						window.location="/enjoyhisfy/client/register/register_index.htm";
					}
				});
			});

		});



	});


	function selectPatient(){
		$('#patient-table').bootstrapTable("destroy");
		$('#patient-table').bootstrapTable({
			method: 'post',
			url: "/enjoyhisfy/client/register/find_patient.json",
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
			pageSize: 10,      //每页的记录行数（*）
			pageList: [10,20,50],  //可供选择的每页的行数（*）
			queryParams: queryParams,//传递参数（*）
			sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
			minimumCountColumns: 2,    //最少允许的列数
			clickToSelect: true,    //是否启用点击选中行
			contentType: "application/x-www-form-urlencoded",
			searchOnEnterKey: true,
//	   		    search:true,
			columns:
					[
//						{
//							field: 'id',
//							title: '',
//							align: 'center',
//							formatter: function(value,row,index){
//								return '<label class="pos-rel"><input id='+value+' type="checkbox" class="ace"/><span class="lbl"></span></label>';
//							}
//						},
						{
							field: 'patName',
							title: '患者姓名',
							align: 'center'
						},
						{
							field: 'mobile',
							title: '患者电话',
							align: 'center'
						},
						{
							field: 'patNo',
							title: '病历编号',
							align: 'center'
						},
						{
							field: 'userSex',
							title: '性别',
							align: 'center'
						},
						{
							field: 'age',
							title: '年龄',
							align: 'center'
						},
						{
							field: 'docName',
							title: '初诊医生',
							align: 'center'

						},
						{
							field: 'pym',
							title: 'pym',
							align: 'center'
						}

					],
			pagination:true,
			onClickRow:function(row,tr){
				$("#patId").val(row.id);
				$("#patName").val(row.patName);
				$("#patNo").val(row.patNo);
				$("#userSex").val(row.userSex);
				$("#mobile").val(row.mobile);
				$("#age").val(row.age);
				$("#birthday").val(row.birthday);
				$("#allergicHis").val(row.allergicHis);
				$('#select-patient-content').modal("hide");
			}
		});
	}

	function queryParams(params) {
		return {
			patName: $('#select_patName').val(),
			mobile: $('#select_mobile').val(),
			maindocId: $('#select_maindocId').val(),
			pageSize: params.pageSize,
			pageNumber: params.pageNumber,
		};
	}

	//时间范围选择
	$("#begin").on("click",function(e){
		e.stopPropagation();
		$(this).lqdatetimepicker({
			css : 'datetime-hour'
		})

	});

	//计算结束时间
	function getendtime(){
		var timeend=$("#end");//
		var strtime=$("#begin").val();//开始值
		var seltime=$("#appoLen").val();//select的值
		var spltime=strtime.split(":");//分割开始值 如：6：00  分割为 6 : 00

		var temp_hours = spltime[0];//第一部分  6
		var temp_min = spltime[1];//第二部分  00

		var hours = temp_hours*1 + Math.floor((temp_min*1+seltime*1)/60);//计算小时  并且取整数
		var min = (temp_min*1+seltime*1)%60;//计算分钟
		if( hours>21 || (hours==21 && min>0) ){
			alert("预约结束时间最晚是21:00,请重新选择！");
			$("#appoLen option:first").prop("selected", 'selected');
		}
		if (min==0) {
			min='0'+min;
		}
//		alert(hours+"--"+min);
		timeend.val(hours+":"+min);
	};

	//年龄与日期联动
	$(function(){
		$('#age').keyup(function(){
			show();
		});

		$("#appoLen").change(function(){
			getendtime();
		});
	});
	//获取当前日期
	function show(){
		var age=$("#age");//年龄
		var mydate = new Date();//
		var str = "" + mydate.getFullYear()-age.val() + "-";
		str += "01" + "-";
		str += "01" + "";
// 		   return str;
		$("#birthday").val(str);
	}

	//B调C:
	function B2C(status){
		var userType = $("#userType").val();
//		alert("userType="+userType)
		if(userType==2) {//护士
			Cef.webCallQT(status)
		}else{
			Cef.webCallYS(status)
		}
	}
</script>
</body>
</html>
