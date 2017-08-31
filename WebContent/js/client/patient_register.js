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
						if (n.id == selectedId) {
							tempAjax += "<option value='" + n.id + "' selected='selected'>" + n.name + "</option>";
						} else {
							tempAjax += "<option value='" + n.id + "'>" + n.name + "</option>";
						}
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
});

function initTable() {
	$('#patient-table').bootstrapTable({
		method : 'post',
		url : "/enjoyhisfy/client/patient/patient_register.json",
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
		pageSize : 1000, // 每页的记录行数（*）
		pageList : [ 10, 20, 50 ], // 可供选择的每页的行数（*）
		queryParams : queryParams,// 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		contentType : "application/x-www-form-urlencoded",
		searchOnEnterKey : true,
		columns : [ {
			field : 'modifyTime',
			title : '日期',
			align : 'center'
		}, {
			field : 'isnew',
			align : 'center',
			title : '初/复诊',
			formatter : function(value, row, index) {
				if (value == 0) {
					return "复诊";
				} else {
					return "初诊";
				}
			}
		}, {
			field : 'isAppoint',
			align : 'center',
			title : '是否预约',
			formatter : function(value, row, index) {
				if (value == 0) {
					return "否";
				} else {
					return "是";
				}
			}
		}, {
			field : 'beginTime',
			align : 'center',
			title : '预约时间',
			formatter : function(value, row, index) {
				if (null == value) {
					return "";
				} else {
					return row.beginTime;
				}
			}
		}, {
			field : 'status', // 状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
			align : 'center',
			title : '是否挂号',
			formatter : function(value, row, index) {
				if (value == 1) {
					return "";
				} else if (value == 6) {
					return "取消预约";
				} else if (value == 2 || value == 3 || value == 4) {
					return "是";
				} else {
					return "已退号";
				}
			}
		}, {
			field : 'ghTime',
			align : 'center',
			title : '挂号时间',
			formatter : function(value, row, index) {
				if (null == value) {
					return "";
				} else {
					return row.ghTime;
				}
			}
		}, {
			field : 'maindocName',
			title : '医生',
			align : 'center'
		}, {
			field : 'source',
			title : '来源',
			align : 'center'
		}, {
			field : 'serviceItems',
			title : '就诊目的',
			align : 'center'
		}, {
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				// 状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
				var str = "";
				if (row.status > 1 && row.isAppoint == 1 && row.ghTime == null) {
					str = str + '<a href="/enjoyhisfy/client/register/more_actions.htm?registerId=' + row.id + '&&type=9">查看预约</a>&nbsp;&nbsp;&nbsp;&nbsp;';
				} else if (row.status > 1 && row.isAppoint == 1) {
					str = str + '<a href="/enjoyhisfy/client/register/more_actions.htm?registerId=' + row.id + '&&type=9">查看预约</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					str = str + '<a href="/enjoyhisfy/client/register/more_actions.htm?registerId=' + row.id + '&&type=8">查看挂号</a>';
				}
				if (row.status > 1 && row.isAppoint == 0) {
					str = str + '<a href="/enjoyhisfy/client/register/more_actions.htm?registerId=' + row.id + '&&type=8">查看挂号</a>';
				}
				return str;
			}
		} ],
		pagination : true,
	});
}

// 列表切换
$(function() {
	$(".btngroup button").on("click", function() {
		$(this).addClass("btn-color").siblings().removeClass("btn-color");
		$('#patient-table').bootstrapTable('refresh');
	});
});

// table查询参数
function queryParams(params) {
	return {
		pid : $('#pid').val(),
		pageSize : params.pageSize,
		pageNumber : params.pageNumber,
	};
}

// B调C:
function B2C(patId, statementId, type) {
	// alert(patId+"--"+statementId+"---"+type);
	patId = patId.toString();
	statementId = statementId.toString();
	Cef.webCallCharge(patId, statementId, type);
}

// 根据状态显示提示框内容
function addbtn(sta1, sta2, sta3, sta4, sta5, sta6, sta7) {
	$(".create").empty();
	var str = "";// 节点
	if (sta1 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">新建挂号</a></div>';
	if (sta2 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">新建预约</a></div>';
	if (sta3 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">缴费</a></div>';

	str += '<br class="clear"  />';
	str += '<div class="kong"></div>';

	if (sta4 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">患者资料</a></div>';
	if (sta5 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">账户管理</a></div>';
	if (sta6 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/patient/patient_register.json?pid=' + 1 + '">就诊管理</a></div>';
	if (sta7 == 1)
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="">病历记录</a></div>';

	$(".create").append(str);
};

function mytest(registerId, pid, status) {
	// 状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
	addbtn(1, 1, 1, 1, 1, 1, 1);
}

function openDiolag2View() {
	$('.tabbtn a').click(function() {// 打开模态框
		$("#modal").modal({
			backdrop : "static",// 阻止点击蒙版 模态框被关闭
			keyboard : true
		})
	});
}
