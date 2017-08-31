<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>患者资料</title>
<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/his.css" />
<link rel="stylesheet" type="text/css" href="${path}/laydate/need/laydate.css" />
</head>
<body>
	<input type="hidden" value="${userType}" id="userType" />
	<input type="hidden" value="${uid}" id="uid" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<div class="container-fluid " style="margin-top: 15px;">
					<div class="row">
						<div class="col-sm-2" style="line-height: 30px;">
							<a class="text-dection" href="/enjoyhisfy/client/patient/patient_manager.htm">返回 /</a> <a class="text-dection" href="#">患者资料</a>
						</div>
						<div class="col-sm-2">
							<a href="/enjoyhisfy/client/account/gotoPage.htm?id=${patient.id }" class="btn btn-block btn-success">账户管理</a>
						</div>
						<div class="col-sm-2">
							<a href="/enjoyhisfy/client/patient/patient_register_view.htm?pid=${patient.id }" class="btn btn-block btn-success">就诊管理</a>
						</div>
						<div class="col-sm-2">
							<a href="/enjoyhisfy/client/case/case_record_view.htm?pid=${patient.id }" class="btn btn-block btn-success">病历记录</a>
						</div>
					</div>
				</div>
				<hr />
				<form id="patient-form" class="form-horizontal" role="form" action="/enjoyhisfy/client/patient/save" method="post">
					<input type="hidden" name="id" value="${patient.id }" />
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">患者姓名<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input type="text" class="form-control readonly-text" id="patName" name="patName" value="${patient.patName }" placeholder="请输入名字" readonly="readonly" maxlength=50>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">病历编号</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="patNo" name="patNo" value="${patient.patNo }" placeholder="自动生成" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">患者性别<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<select id="userSex" name="userSex" class="form-control readonly-text" readonly="readonly">
									<c:choose>
										<c:when test="${patient.userSex=='男'}">
											<option value="男" selected>男</option>
											<option value="女">女</option>
										</c:when>
										<c:otherwise>
											<option value="男">男</option>
											<option value="女" selected>女</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">身份证号</label>
							<div class="col-sm-8">
								<input id="persid" name="persid" value="${patient.persid }" type="text" class="form-control readonly-text" placeholder="请输入身份证号" readonly="readonly" maxlength=45>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">年龄</label>
							<div class="col-sm-8">
								<input id="age" name="age" value="${patient.age }" type="text" class="form-control readonly-text" placeholder="请输入年龄" readonly="readonly" maxlength=11>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">出生年月</label>
							<div class="col-sm-8">
								<input type="text" class="form-control readonly-text" id="birthday" name="birthday" value="${patient.fmtBirthday }" placeholder="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">手机号码<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input type="text" class="form-control readonly-text" id="mobile" name="mobile" value="${patient.mobile }" placeholder="请输入手机号码" readonly="readonly" maxlength=15>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">联系电话</label>
							<div class="col-sm-8">
								<input id="tel" name="tel" value="${patient.tel }" type="text" class="form-control readonly-text" placeholder="请输入联系电话 " readonly="readonly" maxlength=45>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">电子邮箱</label>
							<div class="col-sm-8">
								<input id="email" name="email" value="${patient.email }" type="text" class="form-control readonly-text" placeholder="请输入电子邮箱" readonly="readonly" maxlength=45>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label class="col-sm-2 control-label">地址</label>
							<div class="col-sm-10">
								<input type="text" class="form-control readonly-text" id="address" name="address" value="${patient.address }" placeholder="" readonly="readonly" maxlength=255>
							</div>
						</div>
					</div>
					<br class="clear" />
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">患者来源<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<%-- <select class="form-control readonly-text" id="source" name="source" required="required" data-id="${patient.source }" readonly="readonly"
										  url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_dict&&column1=dict_name&&column2=dict_name&&sqlstr={'where':'dict_type=\'HZLY\''}">
								  </select>--%>
								<input type="text" class="form-control" id="source" name="source" value="${patient.source }" placeholder="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">介绍人</label>
							<div class="col-sm-8">
								<input type="text" class="form-control readonly-text" id="introducer" name="introducer" value="${patient.introducer }" placeholder="" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">初诊医师<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input class="form-control" type="text" required="required" readonly="readonly" value="${patient.maindocName}">
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label class="col-sm-2 control-label">初诊询问</label>
							<div class="col-sm-10 ">
								<label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="肝炎" data-value="${patient.newlyAsk }"> 肝炎
								</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="结核" data-value="${patient.newlyAsk }"> 结核
								</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="糖尿病" data-value="${patient.newlyAsk }"> 糖尿病
								</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="高血压" data-value="${patient.newlyAsk }"> 高血压
								</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="冠心病" data-value="${patient.newlyAsk }"> 冠心病
								</label> <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox3" name="newlyAsk" value="风湿病" data-value="${patient.newlyAsk }"> 风湿病
								</label> <br class="clear" /> <label class="checkbox-inline pull-left"> <input type="checkbox" id="inlineCheckbox2" name="newlyAsk" value="癫痫" data-value="${patient.newlyAsk }"> 癫痫
								</label>
								<div class="col-sm-10 ">
									<label class="col-sm-3 control-label">其他</label>
									<div class="col-sm-9">
										<input type="text" class="form-control readonly-text" id="other-id" name="newlyAsk" placeholder="" data-value="${patient.newlyAsk }" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label class="col-sm-2 control-label">过敏史</label>
							<div class="col-sm-10">
								<input type="text" class="form-control readonly-text" id="allergicHis" name="allergicHis" value="${patient.allergicHis }" placeholder="" readonly="readonly" maxlength=255>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-10">
								<textarea id="remark" name="remark" value="${patient.remark }" class="form-control readonly-text" rows="3" readonly="readonly">${patient.remark }</textarea>
							</div>
						</div>
					</div>
					<br class="clear" />

					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">初诊日期</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="newlyDate" value="${patient.fmtNewlyDate }" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">就诊次数</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="firstname" value="${patient.visitTimes }" placeholder="0" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">上次就诊</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="firstname" value="${patient.fmtlasthospDate }" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">下次预约</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="firstname" value="${patient.fmtlastappointDate }" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">累计消费</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="firstname" value="￥${patient.totalSpand }" placeholder="￥0.00" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="col-sm-4 control-label">当前欠费</label>
							<div class="col-sm-8">
								<c:if test="${patient.accountAmount < 0}">
									<input type="text" class="form-control" id="firstname" value="￥${-patient.accountAmount }" placeholder="￥0.00" readonly="readonly">
								</c:if>
								<c:if test="${patient.accountAmount >= 0}">
									<input type="text" class="form-control" id="firstname" value="￥0.00" placeholder="￥0.00" readonly="readonly">
								</c:if>
							</div>
						</div>
					</div>
					<div class="col-sm-2" id="add-btn-div">
						<button id="add-btn" type="button" class="btn btn-block btn-success">修改资料</button>
						<button id="save-btn" type="button" class="btn btn-block btn-success" style="display: none">保存资料</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="${path}/js/jquery-2.1.4.min.js"></script>
	<script src="${path}/js/bootstrap.min.js"></script>
	<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
	<script src="${path}/js/jquery/jquery.form.js"></script>
	<script src="${path}/laydate/laydate.js"></script>
	<script>
		$(function() {

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

			//checkbox默认选中
			$("input[type=checkbox]").each(function() { //由于复选框一般选中的是多个,所以可以循环输出
				var data = $(this).attr('data-value');
				var checkboxValue = $(this).val();
				var doc = $(this);
				if (data != undefined) {
					$.each(data.split(','), function(n, value) {
						if (value == checkboxValue)
							$(doc).attr('checked', 'checked');
					});
				}
			});

			//其他
			appendOther();

			//修改操作
			$("#add-btn").click(function() {
				$(" .readonly-text").removeAttr("readonly");
				$(this).hide();
				$("#save-btn").css("display", 'block');
			});
var cb = function(){
						window.location = "/enjoyhisfy/client/patient/patient_manager.htm";
}
			//保存操作
			$("#save-btn").click(function() {
				$("#patient-form").ajaxSubmit(function(result) {
					if (result.returndata) {
						alert("操作成功！",cb);
						//window.location = "/enjoyhisfy/client/patient/patient_manager.htm";
					} else {
						alert("操作失败！");
					}
				});
				return false;
			});

			//年龄与日期联动
			$('#age').keyup(function() {
				show();
			});
		});

		//出生年月选择	
		;!function() {
			laydate.skin('molv');
			laydate({
				elem : '#birthday',
				event : 'click', //触发事件
				format : 'YYYY-MM-DD', //日期格式
				//istime: true, //是否开启时间选择
				isclear : true, //是否显示清空
				istoday : true, //是否显示今天
				issure : true, //是否显示确认
				festival : true, //是否显示节日
				min : '1900-01-01 00:00:00', //最小日期
				max : '2099-12-31 23:59:59', //最大日期
				start : '1970-6-15 23:00:00', //开始日期
				fixed : false, //是否固定在可视区域
				zIndex : 99999999, //css z-index 
				choose : function(dates) { //选择好日期的回调
					var age = $("#age");//年龄		        
					var mydate = new Date();//获得当前年份 2016
					age.val(mydate.getFullYear() - dates.substr(0, 4));//2016-出生年份
				}
			})

		}();

		//获取当前日期
		function show() {
			var age = $("#age");//年龄
			var str = "";
			if (age.val() != '') {
				var mydate = new Date();//
				var str = "" + mydate.getFullYear() - age.val() + "-";
				str += "01" + "-";
				str += "01" + "";
			}
			$("#birthday").val(str);
		}

		function appendOther() {
			var str = $('#other-id').attr('data-value');
			var value = "";
			value = str.replace("肝炎,", "");
			value = value.replace("结核,", "");
			value = value.replace("糖尿病,", "");
			value = value.replace("高血压,", "");
			value = value.replace("冠心病,", "");
			value = value.replace("风湿病,", "");
			value = value.replace("癫痫,", "");
			$('#other-id').val(value);
		}
	</script>
</body>
</html>
