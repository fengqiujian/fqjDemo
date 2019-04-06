// 城市联动院区
function changeCate() {
	var cityIds = $("#city").find("option:selected").val();
	var txtcity = $("#city").find("option:selected").text();
	$("#unitCode").empty().append("<option value=''>请选择</option>");
	$("#groupCode").empty().append("<option value=''>请选择</option>");
	$("#dentistId").empty().append("<option value=''>请选择</option>");
	if (cityIds == '') {
		return false;
	}

	$.ajax({
		url : "/enjoyhisfy/client/hisorganiz/menu_select.json",
		type : "post",
		dataType : "json",
		async : false,
		data : {
			"city" : txtcity
		},
		error : function(request) {
			alert("失败");
		},
		success : function(data) {
			$("#unitCode").empty().append("<option value=''>请选择</option>");
			for (var i = 0; i < data.returndata.length; i++) {
				//获取院区id，通过事件传递，查询组
				var option = '<option value="' + data.returndata[i].id + '">' + data.returndata[i].unitName + '</option>';
				$("#unitCode").append(option);
			}
		}
	});
}

// 院区联动组
function groupInfo(obj) {
	console.log($(obj).val());
	$("#groupCode").empty().append("<option value=''>请选择</option>");
	$("#dentistId").empty().append("<option value=''>请选择</option>");
	if ($(obj).val() == '') {
	}
	$.ajax({
		url : "/enjoyhisfy/client/groupmanager/findEntityInfos.json?unitId=" + $(obj).val(),
		type : "post",
		dataType : "json",
		async : false,
		error : function(request) {
			alert("失败");
		},
		success : function(data) {
			$("#groupCode").empty().append("<option value=''>请选择</option>");
			for (var i = 0, len = data.returndata.length; i < len; i++) {
				$("#groupCode").append($("<option value='"+data.returndata[i].id +  "'>" + data.returndata[i].groupName + " </option>"));
			}
		}
	});
}

// 组联动人员
function employeeInfo(obj) {
	var id = $(obj).val();
	$("#dentistId").empty().append("<option value=''>请选择</option>");
	$.ajax({
		url : "/enjoyhisfy/client/employeegroup/findEntityInfos.json",
		type : "post",
		dataType : "json",
		data : {
			"groupId" : id
		},
		async : false,
		error : function(request) {
			alert("失败");
		},
		success : function(data) {
			//人员id
			$("#dentistId").empty().append("<option value=''>请选择</option>");
			var employeeIds = "";
			for (var i = 0; i < data.returndata.length; i++) {
				employeeIds += data.returndata[i].employeeId+","
			}
			employeeIds += "0"
			$.ajax({
//					url : "/enjoyhisfy/client/employeeinfo/findEntityInfos.json?id=" + data.returndata[i].employeeId,
				url : "/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name&&sqlstr={'where':'user_type=1 and status=1 and id in ("+employeeIds+")'}",
				async : false,
				success : function(data) {
					if (data != null) {
						for (var i = 0; i < data.returndata.length; i++) {
							console.log(data.returndata[i]);
							$("#dentistId").append($("<option value='"+data.returndata[i].id + "'>" + data.returndata[i].name + "</option>"));
						}
					}
				}
			});
		}
	});
}

$(function() {
	//动态获取select的option
	$("#city").each(function(i, n) {
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
					$.each(
						data.returndata,
						function(i, n) {
							if (n.id == selectedId)
								tempAjax += "<option value='" +n.id+ "' selected='selected'>"+ n.name+ "</option>";
							else
								tempAjax += "<option value='"+n.id+"'>" + n.name + "</option>";
						});
					$("#" + n.id).empty();
					$("#" + n.id).append(tempAjax);
				}
			});
		}
	});
})
