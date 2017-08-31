<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<style>
td, th {
	text-align: center;
	border: 1px solid #eee;
	height: 10%;
	background: #fff;
}

td {
	cursor: pointer;
}

#bookingItem1, #bookingItem2 {
	border: 2px solid #eee;
	width: 300px;
	border-collapse: separate;
	margin-left: 100px;
	float: left;
}

#bookingItem1 tr, #bookingItem2 tr {
	height: 30px;
}

#bookingItem1 td, #bookingItem2 td {
	height: 30px;
	text-align: center;
	cursor: pointer;
}

#bookingItem2 td {
	font-size: 12px;
}
</style>
<meta charset="UTF-8">
<title>修改挂号</title>
<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
<link rel="stylesheet" href="${path}/layer/skin/layer.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="row">
					<div class="container-fluid ">
						<div class="row">
							<ol class="breadcrumb">
								<li><a href="/enjoyhisfy/client/register/register_index.htm">返回</a></li>
								<li><a href="#">修改挂号&分诊</a></li>
							</ol>
						</div>
					</div>
					<hr />
					<form id="register-form" class="form-horizontal" role="form" action="/enjoyhisfy/client/register/save" method="post">
						<input id="id" name="id" type="hidden" value="${register.id }"> <input id="patId" name="patId" type="hidden" value="${register.patId }"> <input id="isAppoint" name="isAppoint" type="hidden" value="${register.isAppoint }"> <input id="status" name="status" type="hidden" value="${register.status }">
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">患者姓名<sup style="color: red;">*</sup></label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="patName" name="patName" value="${register.patient.patName }" placeholder="请输入名字" required="required" readonly="readonly" maxlength=50>
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">性别<sup style="color: red;">*</sup></label>
									<div class="col-sm-8">
										<select id="userSex" name="userSex" class="form-control" value="${register.patient.userSex }" required="required">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="date">出生年月</label>
									<div class="col-sm-8">
										<input type="hidden" class="form-control" id="age" name="age" value="${register.patient.age }">
										<input type="text" class="form-control" id="birthday" name="strBirthday" value="${register.patient.fmtBirthday }" required="required"  readonly="readonly">
									</div>
									<span class="date-icon" style="right: 128px; bottom: 22px; position: absolute;"> <img class="img-responsive center-block" />
									</span>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">手机号码<sup style="color: red;">*</sup></label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="mobile" name="mobile" value="${register.patient.mobile }" placeholder="请输入手机号码" maxlength=11 readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">患者来源<sup style="color: red;">*</sup></label>
									<div class="col-sm-8">
										<div id="source-div1" style="display: block">
											<input type="text" class="form-control" id="source" name="source" value="${register.patient.source }" required="required" readonly />
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">就诊医生<sup style="color: red;">*</sup></label>
									<div class="col-sm-8">
										<select class="form-control" id="dentistId" name="dentistId" required="required" data-id="${register.dentistId }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and is_show=1'}">
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">联系电话</label>
									<div class="col-sm-8">
										<input id="tel" name="tel" type="text" class="form-control" value="${register.patient.tel }" placeholder="请输入联系电话 " maxlength=45 readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">地址</label>
									<div class="col-sm-8">
										<input id="address" name="address" type="text" class="form-control" value="${register.patient.address }" placeholder="地址" maxlength=255 readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">就诊科室<sup style="color: red;"></sup></label>
									<div class="col-sm-8">
										<select class="form-control" id="deptCode" name="deptCode" required="required" data-id="${register.deptCode }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_docroom&&column1=id&&column2=room_name&&sqlstr=">
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">身份证号</label>
									<div class="col-sm-8">
										<input id="persid" name="persid" type="text" class="form-control" value="${register.patient.persid }" placeholder="请输入身份证号" maxlength=45 readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">电子邮箱</label>
									<div class="col-sm-8">
										<input id="email" name="email" type="text" class="form-control" value="${register.patient.email }" placeholder="请输入电子邮箱" maxlength=45 readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">预约人</label>
									<div class="col-sm-8" id="search">
										<input type="text" class="form-control" id="introducer" name="introducer" value="${register.introducer}" readonly="readonly" maxlenght=10>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">病历编号</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="patNo" name="patNo" value="${register.patient.patNo }" readonly="readonly" placeholder="自动生成">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">初诊医生</label>
									<div class="col-sm-8">
										<input type="hidden" class="form-control" readonly="readonly" id="maindocId" name="maindocId" value="${register.patient.maindocId}"> <input type="text" class="form-control" readonly="readonly" id="maindocName" name="maindocName" value="${register.patient.maindocName}">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">备注</label>
									<div class="col-sm-8">
										<textarea id="remark" name="remark" class="form-control" rows="3" maxlength=255>${register.remark }</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">就诊目的<sup style="color: red;">*</sup></label>
									<div class="col-sm-8">
										<input type="text" id="serviceItems" readonly="readonly" style="text-align: center; width: 70px;" name="serviceItems" value="${register.serviceItems}">
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
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
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
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
								</div>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">初诊询问</label>
									<div class="col-sm-8">
										<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="肝炎" data-value="${register.patient.newlyAsk }"> 肝炎
										</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="结核" data-value="${register.patient.newlyAsk }"> 结核
										</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="糖尿病" data-value="${register.patient.newlyAsk }"> 糖尿病
										</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="高血压" data-value="${register.patient.newlyAsk }"> 高血压
										</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="冠心病" data-value="${register.patient.newlyAsk }"> 冠心病
										</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="风湿病" data-value="${register.patient.newlyAsk }"> 风湿病
										</label> <label class="checkbox-inline pull-left"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="癫痫" data-value="${register.patient.newlyAsk }"> 癫痫
										</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="other-id" name="newlyAsk" data-value="${register.patient.newlyAsk }" placeholder="其他" style="width: 98%">
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="col-sm-4 control-label">过敏史&nbsp;&nbsp;</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="allergicHis" name="allergicHis" value="${register.patient.allergicHis }" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
						<br style="clear: both" />
						<div class="form-group">
							<div class="col-sm-2 ">
								<button id="add-btn" type="button" class="btn btn-block btn-success" style="margin-left: 15px;">完成挂号</button>
							</div>
							<div class="col-sm-2 col-sm-offset-1">
								<button id="exit_register" type="button" value="${register.id }" class="btn btn-block btn-success">退号</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="${path}/js/jquery-2.1.4.min.js"></script>
	<script src="${path}/js/bootstrap.min.js"></script>
	<script src="${path}/laydate/laydate.js"></script>
	<script src="${path}/assets/js/bootstrap-table.min.js"></script>
	<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
	<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
	<script src="${path}/js/jquery/jquery.form.js"></script>
	<script src="${path}/layer/layer.js"></script>
	<script src="${path}/js/client/update_register.js"></script>
	<script src="${path}/js/client/serviceItems.js"></script>
	<script>
		//处理性别
		var sex = '${register.patient.userSex }';
		if(sex!=undefined && sex!=null){
			$("#userSex").val(sex);
			$("#userSex").attr("readonly", "readonly");
		}
		var callNoIp = '${callNoIp}';
		var oftenItems = JSON.parse('${oftenItems}');
		var strNewlyAsk = '${register.patient.newlyAsk }';
		var isNew = '${register.isnew}';
	</script>
</body>
</html>
