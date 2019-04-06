<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>预约计划</title>
<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/lq.datetimepick.css" />
<style type="text/css">
 html,body{
	position:relative;
	} 
	
*{
	margin:0;
	padding:0;
	list-style:none;
}
.clearFix{
	clear:both;
	height:0;
	line_height:0;
	font-siez:0;
	margin:0;
	padding:0;
}
.color1{
	background:#ffbdc5;/* 正畸 */
}
.color2{
	background:#63ffce;/* 种植 */
}
.color3{
	background:#ceceff;/* 美容*/
}
.color4{
	background:#10c5ff;/* 儿牙 */
}
.color5{
	background:#efbd6b;/* 普通*/
}

.index-table table{
	table-layout:fixed ; 
} 
.index-table tbody tr td{
	padding:0;
	font-size:12px;
	height:3px;
	/* background:red; */
	vertical-align: middle;
}
td,th{
	text-align:center;
	border:1px solid #eee;
	height:10% ;
	background :#fff;
}
td{
	cursor:pointer;
}
.item_list{
	position:absolute;
	top:3px;
	right:0px;
	float:left;
	overflow:hidden;
}	
.item_list i,.item_list span{
	float:left;
	margin-left:10px;
	margin-bottom:10px;
}

.item1 i{
	background:#ffbdc5;
	width:20px;
	height:20px;
}
.item2 i{
	background:#63ffce;
	width:20px;
	height:20px;
}
.item3 i{
	background:#ceceff;
	width:20px;
	height:20px;
}
.item4 i{
	background:#10c5ff;
	width:20px;
	height:20px;
}
.item5 i{
	background:#efbd6b;
	width:20px;
	height:20px;
}
.item6 i{
	background:#949391;
	width:20px;
	height:20px;
}
 #detail_table{
	position:absolute;
	/* margin:auto; */
	z-index:99999;
	width:500px;
} 

#appointmentTime {
	width: 350px;
}

#appointmentTime td{
	height:30px;
	text-align: center;
	cursor:pointer;
}

.tdColor {
	background-color: #949391; ;
}

#bookingItem1, #bookingItem2{
	border:2px solid #eee;
	width: 300px;
	border-collapse:separate;
}

#bookingItem1 tr, #bookingItem2 tr{
	height:30px;
}

#bookingItem1 td, #bookingItem2 td{
	height:30px;
	text-align:center;
	cursor:pointer;
}

#bookingItem2 td{
	font-size: 12px;
}

#mainTable tr{
	height: 15px;
}
.listchange:hover{
	background:#eee;
}

.currentWeek2, .lastWeek2, .nextWeek2, .afterFourWeek2{
	cursor: pointer;
	background-color: #eee;
}
</style>
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<input id="id" name="id" type="hidden" /> <!-- 预约主键ID -->
		<input id="patId" name="patId" type="hidden" value="${register.patient.id }" > <!-- 患者ID -->
		<input type="hidden" value="2" id="userType"/> <!-- 登录用户类型 -->
		<input type="hidden" value="${uid}" id="uid"/> <!-- 登录用户ID -->
		<input id="status" name="status"  type="hidden"  value="1" >
		<div class="col-sm-12">
			<div>
				<div class="panel-body">
					<div class="col-sm-12">
						<div class="form-group">
							<div class="col-sm-1 text-center">
								<div class="row">
									<ol class="breadcrumb">
										<li><a href="/enjoyhisfy/client/register/register_index.htm">返回</a></li>
									</ol>
								</div>
							</div>
							<div class="col-sm-3">
								<label class="col-sm-4 control-label" style="line-height: 33px;">日期</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="dateTime" name="dateTime">
									<span class="date-icon" style="right: 16px; bottom: 7px; position: absolute;">
										<img class="img-responsive center-block" />
									</span>
								</div>
							</div>
						</div>
						<div class="form-group" style="position:absolute;top:0px;width:68%;left:38%;">
							<div class="col-sm-2" >
								<input type="button" class="btn btn-block btn-success" id="sort-doctor-dialog" value="筛选排序医生" />
							</div>
							<div class="col-sm-2 checkbox" style="position:absolute;top:-8px;width:62%;left:20%;">
								<label>
									<input type="checkbox" checked="checked" id="doctor-booding-btn" value="">只查看有预约的医生
								</label>
							</div>
						</div>
						<div class="form-group">
							<ul class="item_list col-sm-4">
								<li class="item1"><i></i><span>正畸</span></li>
								<li class="item2"><i></i><span>种植</span></li>
								<li class="item3"><i></i><span>美容</span></li>
								<li class="item4"><i></i><span>儿牙</span></li>
								<li class="item5"><i></i><span>普通</span></li>
								<li class="item6"><i></i><span>已挂号</span></li>
							</ul> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--表格 start-->
	<div class="table-responsive index-table">
		<table class="table" id="mainTable">
			<thead>
				<tr class="headTitle"></tr>
			</thead>
			<tbody>
				<c:forEach begin="0" end="30" var="value" step="2">
					<tr class="tr${value}">
						<td><fmt:formatNumber type="number" value="${value/2+8}" maxFractionDigits="0"/>:00</td>
					</tr>
					<tr class="tr${value+1}">
						<td><fmt:formatNumber type="number" value="${value/2+8}" maxFractionDigits="0"/>:30</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!--表格 end-->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="bookingInfoModal" role="dialog">
		<div class="modal-dialog" style="width:1000px; margin-top: 1px;">
			<div class="modal-content">
			<!-- 
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">预约信息</h4>
				</div>
			 -->
				<!-- 这里添加医生表单 -->
				<div class="modal-body">
					<div class="container-fluid">
						<div class="col-md-12">
							<form id="doctorInfo" class="form-horizontal">
								<div class="form-group">
									<label for="city" class="col-sm-1 control-label">城市：</label>
									<div class="col-sm-2">
										<select class="form-control" name="city" id="city" onchange="changeCate();"
											url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4Info.json?table=his_organiz&&column1=id&&column2=city&&sqlstr={'where':'city IS NOT NULL GROUP BY city'}">
										</select>
									</div>
									<label for="unit" class="col-sm-1 control-label">院区：</label>
									<div class="col-sm-2">
										<select onchange="groupInfo(this)" class="form-control" id="unitCode" name="unitCode">
											<option value="">请选择</option>
										</select>
									</div>
									<label for="groupCode" class="col-sm-1 control-label">组别：</label>
									<div class="col-sm-2">
										<select onchange="employeeInfo(this)" class="form-control" id="groupCode" name="groupCode">
											<option value="">请选择</option>
										</select>
									</div>
									<label for="dentistId" class="col-sm-1 control-label">医生：</label>
									<div class="col-sm-2">
										<select class="form-control" id="dentistId" data-id="${register.dentistId}"
											url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}"></select>
									</div>
								</div>
							</form>
							<hr />
							<form id="bookingForm" class="form-horizontal">
								<div class="col-sm-12">
									<div class="col-sm-2" style="margin-left: -20px">
										<button id="reset-btn" type="button" class="btn btn-block btn-success">初诊预约</button>
									</div>
									<div class="col-sm-2">
										<button id="open-patient-dialog" type="button" class="btn btn-block btn-success">复诊预约</button>
									</div>
								</div>
								<br> <br> <input type="hidden" class="form-control" id="patNo" name="patNo" value="${register.patient.patNo }">
								<!-- 病历编号隐藏 -->
								<div class="col-sm-12">
									<div class="form-group">
										<label class="col-sm-1 control-label">姓名<sup style="color: red">*</sup></label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="patName" name="patName" value="${register.patient.patName }" placeholder="请输入名字" required="required" maxlenght=50>
										</div>
										<label class="col-sm-1 control-label">手机<sup style="color: red">*</sup></label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="mobile" name="mobile" value="${register.patient.mobile }" required="required" placeholder="请输入手机号码" maxlength=11>
										</div>
										<label class="col-sm-1 control-label">性别<sup style="color: red">*</sup></label>
										<div class="col-sm-2">
											<select id="userSex" name="userSex" class="form-control" value="${register.patient.userSex }" required="required">
												<option value="男">男</option>
												<option value="女">女</option>
											</select>
										</div>
										<label class="col-sm-1 control-label">预约人</label>
										<div class="col-sm-2" id="search">
											<input type="text" class="form-control" id="introducer" name="introducer" placeholder="请输入预约人" value="${employee.employeeName}" maxlenght=10>
										</div>
									</div>
									<div class="form-group">
										<input type="hidden" class="form-control" id="age" name="age" placeholder="请输入年龄" value="${register.patient.age }" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength=3> <label
											class="control-label col-sm-1">生日</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="birthday" name="birthday" value="${register.patient.fmtBirthday }" required="required" placeholder="请选择">
										</div>
										<span class="date-icon" style="right: 127px; bottom: 22px; position: absolute;"> </span> <label class="col-sm-1">患者来源<sup style="color: red;">*</sup></label>
										<div class="col-sm-2">
											<div id="source-div1" style="display: block">
												<input type="hidden" id="oldSource" value="${register.patient.source}"> <select class="form-control" id="source" name="source" required="required"
													data-id="${register.patient.source }"
													url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_dict&&column1=dict_name&&column2=dict_name&&sqlstr={'where':'dict_type=\'HZLY\''}">
												</select>
											</div>
											<div id="source-div2" class="col-sm-12" style="padding: 0; display: none;">
												<input type="text" class="form-control" id="source2" name="source" value="" required="required" readonly />
											</div>
										</div>
										<div class="checkbox col-sm-2">
											<label> <input id="zhengji" type="checkbox" name="type" value="1"> 是否正畸患者
											</label>
										</div>
										<div class="checkbox col-sm-2">
											<label> <input id="zhongzhi" type="checkbox" name="type" value="2"> 是否种植患者
											</label>
										</div>
										<div class="checkbox col-sm-2">
											<label> <input id="meirong" type="checkbox" name="type" value="3"> 是否美容患者
											</label>
										</div>
									</div>
								</div>
							</form>
						<div class="form-group">
								<div class="col-sm-3">
									<label class="control-label">预约日期&nbsp;<input readonly="readonly" style="border:none;width:80px;color:red;" id="appointmentDate" name="appointmentDate" required="required"></label>
									<br>
									<span class="lastWeek2"> < 上周 </span>&nbsp;<span class="currentWeek2">今天</span>&nbsp;
									<span class="nextWeek2"> 下周 > </span>&nbsp;<span class="afterFourWeek2"> 四周后 </span>
									<div id="appointmentDateDiv"></div>
								</div>
								<div class="col-sm-5">
									<label class="control-label">预约时间
										<span style="color: red;" id="bookingTime"></span>&nbsp;&nbsp;&nbsp;&nbsp; 时长(<span id="bookingLength" style="color: red;"></span>分钟)
									</label>
									<table border="normal" id="appointmentTime">
										<tr>
											<td rowspan="2" class="resetColorTd">上午</td>
											<td>8:00</td>
											<td>8:30</td>
											<td>9:00</td>
											<td>9:30</td>
										</tr>
										<tr>
											<td>10:00</td>
											<td>10:30</td>
											<td>11:00</td>
											<td>11:30</td>
										</tr>
										<tr>
										</tr>
										<tr>
											<td rowspan="3" class="resetColorTd">下午</td>
											<td>12:00</td>
											<td>12:30</td>
											<td>13:00</td>
											<td>13:30</td>
										</tr>
										<tr>
											<td>14:00</td>
											<td>14:30</td>
											<td>15:00</td>
											<td>15:30</td>
										</tr>
										<tr>
											<td>16:00</td>
											<td>16:30</td>
											<td>17:00</td>
											<td>17:30</td>
										</tr>
										<tr>
											<td rowspan="3" class="resetColorTd">晚上</td>
											<td>18:00</td>
											<td>18:30</td>
											<td>19:00</td>
											<td>19:30</td>
										</tr>
										<tr>
											<td>20:00</td>
											<td>20:30</td>
											<td>21:00</td>
											<td>21:30</td>
										</tr>
										<tr>
											<td>22:00</td>
											<td>22:30</td>
											<td>23:00</td>
											<td class="resetColorTd"></td>
										</tr>
									</table>
								</div>
								<div class="col-sm-4">
									<label class="control-label">预约目的：</label>
									<input type="text" id="serviceItems" readonly="readonly" style="text-align: center; width: 70px;" name="serviceItems" value="${register.serviceItems}"> <br> <br>
									<table id="bookingItem1">
										<tr>
											<td id="item1">常用</td>
											<td id="item2">修复</td>
											<td id="item3">牙体</td>
											<td id="item4">种植</td>
										</tr>
										<tr>
											<td id="item5">牙周</td>
											<td id="item6">正畸</td>
											<td id="item7">外科</td>
											<td id="item8">儿牙</td>
										</tr>
									</table>
									<br>
									<table id="bookingItem2">
										<tr>
											<td>备牙</td>
											<td>取模</td>
											<td>试戴牙</td>
											<td>取颌关系</td>
											<td>备桩</td>
										</tr>
										<tr>
											<td>粘桩</td>
											<td>永久粘固</td>
											<td>贴面戴牙</td>
											<td></td>
											<td></td>
										</tr>
									</table>
									<div class="form-group">
										<label class="control-label">预约备注</label>
										<div>
											<textarea class="form-control" name="remark" id="remark" rows="" cols="" maxlength="200"></textarea>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox" name="affirm" id="affirm" value="1"> 预约是否确认
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="container-fluid">
						<div class="col-sm-12 text-right btngroup checkbox">
							<button type="button" class="btn btn-primary btnsure">保存</button>
							<input type="button" class="btn btn-primary newBtnsure" value="另存为新预约" />
							<input type="button" class="btn btn-default cancelBooking" data-dismiss="modal" style="background: #ccc; border-color: #ccc;" value="取消预约" />
							<button type="button" class="btn btn-default btncel" data-dismiss="modal" style="background: #ccc; border-color: #ccc;">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
	<!--  modal-dialog -->
	<div id="select-patient-content" class="modal fade" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog" style="width: 820px;">
			<div class="modal-content">
				<span class="counter pull-right"></span>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="smaller lighter blue no-margin">
						<span id="">选择患者</span>
					</h3>
				</div>
				<div class="panel-body">
					<form id="patientFormSearch" class="form-horizontal">
						<div class="form-group" style="margin-top: 15px">
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
										<input id="select_mobile" name="mobile" type="text" class="form-control selectPatient" id="txt_search_statu" placeholder="手机号码" maxlength=15/>
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
				<!-- 患者table -->
				<div class="modal-body">
					<table id="patient-table" class="col-xs-12"></table>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!--  modal-dialog  筛选医生-->
	<div id="select-doctor-content" class="modal fade" tabindex="-1">
		<div class="modal-dialog" style="width: 820px;">
			<div class="modal-content">
				<div class="modal-body">
					<table class="table1 table table-bordered text-center">
						<thead>
							<tr>
								<th>选择</th>
								<th>医生</th>
								<th>排序</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${doctors}" varStatus="status">
								<tr>
									<td>
										<input type="checkbox" name="items" value="${item.id}" checked>
									</td>
									<td>${item.name}</td>
									<td>
										<a href="#" class="up">上移</a> 
										<a href="#" class="down">下移</a> 
										<a href="#" class="top">置顶</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<div class="col-sm-12">
						<div class="col-sm-3">
							<input type="button" id="checkedall" class="btn btn-block btn-success" value="全选" />
						</div>
						<div class="col-sm-3">
							<input type="button" id="checkedno" class="btn btn-block btn-success" value="全不选" />
						</div>
						<div class="col-sm-3">
							<input type="button" id="select-doctor-btn" class="btn btn-block btn-success" value="提交" />
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 省略号的弹出框 -->
	<div class="table-responsive" style="display:none" id="detail_table">
	</div>
</body>
</html>
<script type="text/javascript" src="${path}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/laydate/laydate.js"></script>
<script type="text/javascript" src="${path}/js/selectUi.js"></script>
<script type="text/javascript" src="${path}/js/lq.datetimepick.js"></script>
<script type="text/javascript" src="${path}/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${path}/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="../../assets/js/bootbox.js"></script>
<script type="text/javascript" src="../../assets/js/alert.js"></script>
<script type="text/javascript" src="${path}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${path}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/layer/layer.js"></script>
<script type="text/javascript" src="${path}/js/client/date.format.js"></script>
<script type="text/javascript" src="${path}/js/unpaidTip.js"></script>
<script type="text/javascript" src="${path}/js/client/util.js"></script>
<script type="text/javascript" src="${path}/js/client/select.js"></script>
<script type="text/javascript" src="${path}/js/client/booking_plan.js"></script>
<script type="text/javascript" src="${path}/js/client/introducerSearch.js"></script>
<script type="text/javascript" src="${path}/js/client/serviceItems.js"></script>

<script type="text/javascript">
var introducer = '${employee.employeeName}';
// 患者的vip类型
var patType = '${register.patType}';
if(patType != undefined && patType != null && patType.length > 0) {
	var patTypeArray = patType.split(',');
	for(var i = 0; i < patTypeArray.length; i++) {
		$(':checkbox[name=type]').each(function() {
			if($(this).val() == patTypeArray[i]){
				$(this).attr('checked','checked');
			}
		})
	}
}
$("#source").val('${register.patient.source}');
var oftenItems = JSON.parse('${oftenItems}');
var alreadyPatId = '${register.patient.id }';
var bookingDate = '${bookingDate}';

//处理性别
var sex = '${register.patient.userSex }';
if(sex!=undefined && sex!=null){
	$("#userSex").val(sex);
	$("#userSex").attr("readonly", "readonly");
}
</script>