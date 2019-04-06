<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page" />
<!DOCTYPE html>
<html lang="en">
<head>
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
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">患者姓名<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="patName" name="patName" value="${register.patient.patName }" placeholder="请输入名字" required="required" readonly="readonly" maxlength=50>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">病历编号<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="patNo" name="patNo" value="${register.patient.patNo }" readonly="readonly" placeholder="自动生成" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">性别<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="userSex" name="userSex" value="${register.patient.userSex }" readonly="readonly" />
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">手机号码<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="mobile" name="mobile" value="${register.patient.mobile }" required="required" placeholder="请输入手机号码" readonly="readonly" maxlength=15>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">年龄</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="age" name="age" value="${register.patient.age }" onkeyup="this.value=this.value.replace(/\D/g,'')" required="required" placeholder="请输入年龄" readonly="readonly" maxlength=11>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label col-sm-4">出生年月</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id=birthday name="strBirthday" value="${register.patient.fmtBirthday }" required="required" placeholder="请选择" readonly="readonly">
							</div>
							<span class="date-icon" style="right: 16px; bottom: 22px; position: absolute;"> <img class="img-responsive center-block" />
							</span>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">过敏史</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="allergicHis" name="allergicHis" value="${register.patient.allergicHis }" readonly="readonly" maxlength=255>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">患者来源<sup style="color: red;">*</sup></label>
							<div class="col-sm-8">
								<select class="form-control" id="source" name="source" required="required" data-id="${register.source }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_dict&&column1=dict_name&&column2=dict_name&&sqlstr={'where':'dict_type=\'HZLY\''}" readonly="readonly">
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">就诊科室</label>
							<div class="col-sm-8">
								<select class="form-control" id="deptCode" name="deptCode" required="required" data-id="${register.deptCode }" url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_docroom&&column1=id&&column2=room_name" readonly="readonly">
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">就诊医生</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="maindocName" name="maindocName" value="${register.maindocName }" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="col-sm-4 control-label">就诊目的</label>
							<div class="col-sm-8">
								<!-- 
					           <select class="form-control" id="serviceItems" name="serviceItems" required="required" data-id="${register.deptCode }"  
					          	url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_dict&&column1=dict_name&&column2=dict_name&&sqlstr={'where':'dict_type=\'JZMD\''}" readonly="readonly">
					      	 -->
								<input readonly="readonly" type="text" class="form-control" value="${register.serviceItems }" id="serviceItems" name="serviceItems" /> </select>
							</div>
						</div>
					</div>

				</form>
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
	<script>
		//出生日期选择
		;
		!function() {
			laydate.skin('molv');
			laydate({
				elem : '#birthday'
			})

		}();

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

			selectPatient();

			//选择患者
			$('#open-patient-dialog').click(function() {
				$('#formSearch')[0].reset();
				$('#patient-table').bootstrapTable('refresh');
				$('#select-patient-content').modal({
					backdrop : true,
					keyboard : true,
					show : true
				});
			});

			$('.selectPatient').keyup(function() {
				$('#patient-table').bootstrapTable('refresh');
			});

			$('#select_maindocId').change(function() {
				$('#patient-table').bootstrapTable('refresh');
			});

			$("#add-btn").click(function() {
				$("#register-form").ajaxSubmit(function(result) {
					if (result.returndata) {
						alert("操作成功！",cb);
//						B2C(1);
//						window.location = "/enjoyhisfy/client/register/register_index.htm";
					} else {
						alert("操作失败，请填写必填项( * 为必填项)！");
					}
				});
				return false;
			});
var cb = function(){
	B2C(1);
	window.location = "/enjoyhisfy/client/register/register_index.htm";
}
			$('#exit_register').click(function() {
				var id = $(this).val();
				layer.prompt({
					title : '请填写取消原因，并确认',
					formType : 2
				}, function(text) {
					var remark = text;
					$.ajax({
						url : "/enjoyhisfy/client/register/exit_register.json",
						data : {
							'id' : id,
							"remark" : "退号备注:" + remark
						},
						type : 'post',
						async : false,
						dataType : 'json',
						success : function(data) {
							layer.msg('您已成功退号！');
							B2C(1);
							window.location = "/enjoyhisfy/client/register/register_index.htm";
						}
					});
				});

			});
		});

		function selectPatient() {
			$('#patient-table').bootstrapTable("destroy");
			$('#patient-table').bootstrapTable({
				method : 'post',
				url : "/enjoyhisfy/client/register/find_patient.json",
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
				pageSize : 10, //每页的记录行数（*）
				pageList : [ 10, 20, 50 ], //可供选择的每页的行数（*）
				queryParams : queryParams,//传递参数（*）
				sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
				minimumCountColumns : 2, //最少允许的列数
				clickToSelect : true, //是否启用点击选中行
				contentType : "application/x-www-form-urlencoded",
				searchOnEnterKey : true,
				// 		   		    search:true,
				columns : [ {
					field : 'id',
					title : '',
					align : 'center',
					formatter : function(value, row, index) {
						return '<label class="pos-rel"><input id='+value+' type="checkbox" class="ace"/><span class="lbl"></span></label>';
					}
				}, {
					field : 'patName',
					title : '患者姓名',
					align : 'center'
				}, {
					field : 'mobile',
					title : '患者电话',
					align : 'center'
				}, {
					field : 'patNo',
					title : '病历编号',
					align : 'center'
				}, {
					field : 'userSex',
					title : '性别',
					align : 'center'
				}, {
					field : 'age',
					title : '年龄',
					align : 'center'
				}, {
					field : 'docName',
					title : '初诊医生',
					align : 'center'

				}, {
					field : 'pym',
					title : 'pym',
					align : 'center'

				}

				],
				pagination : true,
				onClickRow : function(row, tr) {
					// 			    		console.info(row);
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
				patName : $('#select_patName').val(),
				mobile : $('#select_mobile').val(),
				maindocId : $('#select_maindocId').val(),
				pageSize : params.pageSize,
				pageNumber : params.pageNumber,
			};

		}

		//B调C:
		function B2C(status) {
			Cef.webCallQT(status)
		}
	</script>
</body>
</html>
