$(function() {
	initTable();
	// 动态获取select的option
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
						if (n.id == selectedId)// 假如当前的option的id等于传递过来的id，就给他添加selected属性
							tempAjax += "<option value='" + n.id + "' selected='selected'>" + n.name + "</option>";
						else
							tempAjax += "<option value='" + n.id + "'>" + n.name + "</option>";
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

	// 选择患者
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

	// 列表切换
	$(".btngroup button").on("click", function() {
		$(this).addClass("btn-color").siblings().removeClass("btn-color");
		$('#patient-table').bootstrapTable('refresh');
	});
});

function initTable() {
	$('#patient-table').bootstrapTable({
		method : 'post',
		url : "/enjoyhisfy/client/patient/patient_list.json",
		pagination : true,
		queryParamsType : '', // 默认值为 'limit' ,在默认情况下 传给服务端的值为：offset limit
		// sort
		// 设置为 '' 在这种情况下传给服务器的值为：pageSize pageNumber
		toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortOrder : "asc", // 排序方式
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 20, 50 ], // 可供选择的每页的行数（*）
		queryParams : queryParams,// 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		contentType : "application/x-www-form-urlencoded",
		searchOnEnterKey : true,
		columns : [ {
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
			field : 'maindocName',
			title : '初诊医生',
			align : 'center'
		}, {
			field : 'accountAmount',
			title : '账户状态',
			align : 'center',
			formatter : function(value, row, index) {
				if (value < 0)
					return "欠费";
				else
					return "正常";
			}
		}, {
			field : 'id',
			align : 'center',
			title : '更多操作',
			formatter : function(value, row, index) {
				var str = '<a href="#" onclick="mytest(' + value + ');">';
				str = str + '<span class="glyphicon glyphicon-th" data-toggle="modal" data-target="#myModal2"></span>';
				str = str + '</a>';
				return str;
			}
		}

		],
		pagination : true,
		onClickCell : function(field, value, row, cell) {
			if (field != 'id') {
				window.location = "/enjoyhisfy/client/patient/patient_update.htm?id=" + row.id;
			}
		}
	});
}

// table查询参数
function queryParams(params) {
	return {
		name : $('#name').val(),
		mobile : $('#mobile').val(),
		docId : $('#docId').val(),
		pageSize : params.pageSize,
		pageNumber : params.pageNumber,
	};
}

// B调C:
function B2C(patId, statementId, type) {
//	alert(patId + "--" + statementId + "---" + type);
	patId = patId.toString();
	statementId = statementId.toString();
	Cef.webCallCharge(patId, statementId, type);
}

// 根据状态显示提示框内容
function addbtn(pid, sta1, sta2, sta3, sta4, sta5, sta6, sta7) {
	$(".create").empty();
	var str = "";// 节点
	if (sta1 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default"  onclick="moreActions(' + pid + ',3);">新建挂号</a></div>';
	}
	if (sta2 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" onclick="moreActions(' + pid + ',2);">新建预约</a></div>';
	}

	str += '<br class="clear"  />';
	str += '<div class="kong"></div>';

	if (sta4 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/patient/patient_update.htm?id=' + pid + '">患者资料</a></div>';
	}
	if (sta5 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/account/gotoPage.htm?id=' + pid + '">账户管理</a></div>';
	}
	if (sta6 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/patient/patient_register_view.htm?pid=' + pid + '">就诊管理</a></div>';
	}
	if (sta7 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/case/case_record_view.htm?pid=' + pid + '">病历记录</a></div>';
	}

	$(".create").append(str);
};

function mytest(pid) {
	// 状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
	if ($("#userType").val() == 1) {
		addbtn(pid, 0, 1, 1, 1, 1, 1, 1);
	} else {
		addbtn(pid, 1, 1, 1, 1, 1, 1, 1);
	}

}

function openDiolag2View() {
	// 打开模态框
	$('.tabbtn a').click(function() {
		$("#modal").modal({
			backdrop : "static",// 阻止点击蒙版 模态框被关闭
			keyboard : true
		})
	});
}

function moreActions(pid, type) {
	if (type == 3)// 新建挂号
		B2CMenu(2);
	if (type == 2)// 新建预约
		B2CMenu(3);
	window.location = "/enjoyhisfy/client/register/more_actions.htm?pid=" + pid + "&&type=" + type;
}

// B调C:
function B2CMenu(status) {
	var userType = $('#userType').val();
	if (userType == 1)// 医生
		Cef.webCallYS(status);
	else
		Cef.webCallQT(status);
}
