!function() {
	laydate.skin('molv');
	var now = laydate.now();
	$('#dateTime').val(now)
	laydate({
		elem : '#dateTime',
		event : 'click', // 触发事件
		format : 'YYYY-MM-DD', // 日期格式
		istime : false, // 是否开启时间选择
		isclear : true, // 是否显示清空
		istoday : true, // 是否显示今天
		issure : true, // 是否显示确认
		festival : true, // 是否显示节日
		min : '2000-01-01', // 最小日期
		max : '2099-12-31', // 最大日期
		start : now, // 开始日期
		fixed : false, // 是否固定在可视区域
		zIndex : 9999999,
		choose : function(dates) { // 选择好日期的回调
			$('#register-table-history').bootstrapTable('refresh');
		}
	})
}();

$(function() {
	initTable();
	$('.selectRegister').keyup(function() {
		$('#register-table-history').bootstrapTable('refresh');
	});
	$('#type').change(function() {
		$('#register-table-history').bootstrapTable('refresh');
	});
});

function initTable() {
	$('#register-table-history').bootstrapTable({
		method : 'post',
		url : "/enjoyhisfy/client/register/find_register.json",
		toolbar : '#toolbar', // 工具按钮用哪个容器
		pagination : false,
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		sortable : true, // 是否启用排序
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		queryParams : queryParams,// 传递参数（*）
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		contentType : "application/x-www-form-urlencoded",
		searchOnEnterKey : true,
		columns : [ {
			field : 'isnew',
			align : 'center',
			sortable : true,
			title : '初/复诊',
			formatter : function(value, row, index) {
				var val = '';
				if (row.affirm == 1) {
					val += '<span class = "glyphicon glyphicon-ok"><span> ';
				}
				if (row.patType != undefined && row.patType != null && row.patType != '') {
					var typeArray = row.patType.split(',');
					for (var i = 0; i < typeArray.length; i++) {
						if (typeArray[i] == 1) {
							val += '<span style = "width:10px;height:10px;background:pink;">正</span>&nbsp;';
						}
						if (typeArray[i] == 2) {
							val += '<span style = "width:10px;height:10px;background:aqua;">种</span>&nbsp;';
						}
						if (typeArray[i] == 3) {
							val += '<span style = "width:10px;height:10px;background:yellow;">美</span>&nbsp;';
						}
					}
				}
				if (value == 0) {
					val += "复诊";
				} else {
					val += "初诊";
				}
				return val;
			}
		}, {
			field : 'status',
			align : 'center',
			sortable : true,
			title : '就诊状态',
			formatter : function(value, row, index) {
				var userType = $('#userType').val();
				// 状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
				if (value == 1)
					if (userType == 1) {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="/enjoyhisfy/client/register/doctor_plan_view.htm?doctorId=' + dentistId + '">预约</a>';
						} else {
							return '<a href="/enjoyhisfy/client/register/doctor_plan_view.htm?doctorId=' + dentistId + '">预约</a>';
						}

					} else {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="javascript:void(0);" onclick="booking(' + row.registerId + ');">预约</a>';
						} else {
							return '<a href="javascript:void(0);" onclick="booking(' + row.registerId + ');">预约</a>';
						}

					}

				else if (value == 2)
					if (userType == 1) {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="/enjoyhisfy/client/DisposalItem/gotoPage.htm?id=' + row.registerId + '&patientId=' + row.patId + '">已挂号</a>';
						} else {
							return '<a href="/enjoyhisfy/client/DisposalItem/gotoPage.htm?id=' + row.registerId + '&patientId=' + row.patId + '">已挂号</a>';
						}
					} else {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="/enjoyhisfy/client/register/register_view.htm?id=' + row.registerId + '">已挂号</a>';
						} else {
							return '<a href="/enjoyhisfy/client/register/register_view.htm?id=' + row.registerId + '">已挂号</a>';
						}
					}

				else if (value == 3)
					if (userType == 1) {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="/enjoyhisfy/client/DisposalItem/gotoPage2.htm?id=' + row.registerId + '&patientId=' + row.patId + '">待收费</a>';
						} else {
							return '<a href="/enjoyhisfy/client/DisposalItem/gotoPage2.htm?id=' + row.registerId + '&patientId=' + row.patId + '">待收费</a>';
						}
					} else {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="javascript:void(0);" onclick="B2C(' + row.patId + ',\'' + row.statementItemid + '\',' + row.registerId + ',1,'
									+ row.statementCode + ');">待收费</a>';
						} else {
							return '<a href="javascript:void(0);" onclick="B2C(' + row.patId + ',\'' + row.statementItemid + '\',' + row.registerId + ',1,' + row.statementCode
									+ ');">待收费</a>';
						}
					}
				else if (value == 4)
					if (userType == 1) {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="javascript:void(0);">已完成</a>';
						} else {
							return '<a href="javascript:void(0);">已完成</a>';
						}
					} else {
						if (row.accountAmount < 0) {
							return '<a style="color:red;" href="javascript:void(0);" onclick="B2C(' + row.patId + ',' + row.statementItemid + ',' + row.registerId + ',2, '
									+ row.statementCode + ');">已完成</a>';
						} else {
							return '<a href="javascript:void(0);" onclick="B2C(' + row.patId + ',' + row.statementItemid + ',' + row.registerId + ',2, ' + row.statementCode
									+ ');">已完成</a>';
						}
					}
				else if (value == 5) {
					if (row.accountAmount < 0) {
						return '<span style="color:red;">预约未到</span>'
					} else {
						return '<span>预约未到</span>'
					}
				}
				// return "预约未到";
				else if (value == 6) {
					if (row.accountAmount < 0) {
						return '<span style="color:red;">取消预约</span>'
					} else {
						return '<span>取消预约</span>'
					}
				}
				// return "取消预约";
				else {
					if (row.accountAmount < 0) {
						return '<span style="color:red;">退号</span>'
					} else {
						return '<span>退号</span>'
					}
				}
				// return "退号";
			}
		}, {
			field : 'patNo',
			align : 'center',
			title : '病历编号'
		}, {
			field : 'docName',
			align : 'center',
			sortable : true,
			title : '医生'
		}, {
			field : 'patName',
			align : 'center',
			sortable : true,
			title : '患者姓名'
		}, {
			field : 'mobile',
			align : 'center',
			title : '手机号码'
		}, {
			field : 'beginTime',
			align : 'center',
			title : '预约时间',
			sortable : true,
			formatter : function(value, row, index) {
				if (row.beginTime != undefined && row.beginTime != null) {
					return row.beginTime.substring(11, 16);
				} else {
					return null;
				}
			}
		}, {
			field : 'ghTime',
			align : 'center',
			title : '挂号时间',
			sortable : true,
			formatter : function(value, row, index) {
				if (row.ghTime != undefined && row.ghTime != null) {
					return row.ghTime.substring(11, 16);
				} else {
					return null;
				}
			}
		}, {
			field : 'serviceItems',
			align : 'center',
			title : '目的',
			sortable : true,
		}, {
			field : 'source',
			align : 'center',
			title : '来源',
			sortable : true,
		}, {
			field : 'id',
			align : 'center',
			title : '常用操作',
			formatter : function(value, row, index) {
				var registerId = row.registerId;
				var patId = row.patId;
				var statementItemid = row.statementItemid;
				var status = row.status;
				var str = "";// 节点

				if ($("#userType").val() == 1) {
					str += '<a onclick="moreActions(' + registerId + ',2);" style="cursor:pointer;">新建预约</a> &nbsp;&nbsp;';
					str += '<a onclick="openX(' + patId + ');" style="cursor:pointer;">打开X光</a>';
				} else {
					if (status == 1 || status == 2 || status == 4) {
						str += '<a onclick="moreActions(' + registerId + ',2);" style="cursor:pointer;">新建预约</a>';
					} else if (status == 3) {
						str += '<a onclick="moreActions(' + registerId + ',2);" style="cursor:pointer;">新建预约</a>&nbsp;&nbsp;';
					}
				}
				return str;
			}
		}, {
			field : 'id',
			align : 'center',
			title : '更多操作',
			formatter : function(value, row, index) {
				var str = '<a href="#" onclick="mytest(' + row.registerId + ',' + row.patId + ',' + row.statementItemid + ',' + row.status + ');">';
				str = str + '<span class="glyphicon glyphicon-th" data-toggle="modal" data-target="#myModal"></span>';
				str = str + '</a>';
				return str;
			}
		}, ],
		pagination : true,

	});

}

// 列表切换
$(function() {
	$(".btngroup button").on("click", function() {
		$(this).addClass("btn-color").siblings().removeClass("btn-color");
		$('#register-table-history').bootstrapTable('refresh');
	});
});

function booking(id) {
	B2CMenu(2);
	window.location = '/enjoyhisfy/client/register/booking2register.htm?id=' + id;
}
// B调C:
function B2CMenu(status) {
	var userType = $('#userType').val();
	if (userType == 1)// 医生
		Cef.webCallYS(status);
	else
		Cef.webCallQT(status);
}

// table查询参数
function queryParams(params) {
	return {
		userType : $('#userType').val(),
		dentistId : $('#dentistId').val(),
		dateTime : $('#dateTime').val(),
		name : $('#name').val(),
		mobile : $('#mobile').val(),
		type : $('#type').val(),
		sortName : params.sort,
		sortOrder : params.order,
		pageSize : params.pageSize,
		pageNumber : params.pageNumber,
	};

}

// B调C:
function B2C(patId, statementItemid, regId, type, oldcode) {
	patId = patId.toString();
	statementItemid = statementItemid.toString();
	regId = regId.toString();
	Cef.webCallCharge(patId, statementItemid, regId, type, $('#dateTime').val(), oldcode);
}

function moreActions(registerId, type) {
	if (type == 1)// 新建挂号
		B2CMenu(2);
	if (type == 2)// 新建预约
		B2CMenu(3);
	window.location = "/enjoyhisfy/client/register/more_actions.htm?registerId=" + registerId + "&&type=" + type;
}

// 根据状态显示提示框内容
function addbtn(registerId, patId, statementItemid, sta1, sta2, sta3, sta4, sta5, sta6) {
	$(".create").empty();
	var str = "";// 节点
	if (sta1 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" onclick="moreActions(' + registerId + ',3);">新建挂号</a></div>';
	}
	if (sta4 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" onclick="moreActions(' + registerId + ',4);">分诊</a></div>';
	}
	if (sta5 == 1) {
		str += '<div class="col-sm-3"><a class="btn btn-block btn-default" onclick="moreActions(' + registerId + ',5);">退号</a></div>';
	}
	str += '<br class="clear"  />';
	str += '<div class="kong"></div>';

	str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/patient/patient_update.htm?id=' + patId + '">患者资料</a></div>';
	str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/account/gotoPage.htm?id=' + patId + '">账户管理</a></div>';
	str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/patient/patient_register_view.htm?pid=' + patId + '">就诊管理</a></div>';
	str += '<div class="col-sm-3"><a class="btn btn-block btn-default" href="/enjoyhisfy/client/case/case_record_view.htm?pid=' + patId + '">病历记录</a></div>';

	$(".create").append(str);
};

//医生今日就诊打开X光
function openX(patId) {
	patId = patId.toString();
	Cef.webCalldoctorXray(patId);
}

function mytest(registerId, patId, statementItemid, status) {
	// 状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号
	if ($("#userType").val() == 1) {
		addbtn(registerId, patId, statementItemid, 0, 0, 0);
	} else {
		if (status == 1) {
			addbtn(registerId, patId, statementItemid, 0, 0, 0);
		}
		if (status == 2) {
			addbtn(registerId, patId, statementItemid, 0, 1, 1);
		}
		if (status == 3) {
			addbtn(registerId, patId, statementItemid, 0, 0, 0);
		}
		if (status == 4) {
			addbtn(registerId, patId, statementItemid, 1, 0, 0);
		}
		if (status == 7 || status == 6) {
			addbtn(registerId, patId, statementItemid, 0, 0, 0);
		}
	}
}
