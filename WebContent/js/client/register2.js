//出生年月选择	
;
!function() {
	laydate.skin('molv');
	laydate({
		elem : '#birthday',
		event : 'click', // 触发事件
		format : 'YYYY-MM-DD', // 日期格式
		// istime: true, //是否开启时间选择
		isclear : true, // 是否显示清空
		istoday : true, // 是否显示今天
		issure : true, // 是否显示确认
		festival : true, // 是否显示节日
		min : '1900-01-01 00:00:00', // 最小日期
		max : '2099-12-31 23:59:59', // 最大日期
		start : '1970-6-15 23:00:00', // 开始日期
		fixed : false, // 是否固定在可视区域
		zIndex : 99999999, // css z-index
		choose : function(dates) { // 选择好日期的回调
			var age = $("#age");// 年龄
			var mydate = new Date();// 获得当前年份 2016
			age.val(mydate.getFullYear() - dates.substr(0, 4));// 2016-出生年份
		}
	})

}();

$(function() {
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
						if (n.id == selectedId)
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

	selectPatient();

	// 选择患者
	$('#open-patient-dialog').click(function() {
		// $('#formSearch')[0].reset();
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
	var cb = function() {
		$.ajax({
			url : "/enjoyhisfy/client/registerCall/callNo.json",
			data : {
				"calltype" : 1,
				"type" : 0
			},
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data.ret == 0) {
				}
			}
		});
		B2C(1);
		window.location = "/enjoyhisfy/client/register/register_index.htm";
	}
	$("#add-btn").click(function() {
		$(this).attr("disabled", true);
		$("#register-form").ajaxSubmit(function(result) {
			if (result.returndata.flag) {
				alert("<span style='font-size:20px;'>操作成功!此患者病历编号为：" + result.returndata.patNo +"</span>", cb);
			} else {
				$("#add-btn").attr("disabled", false);
				alert("操作失败，请填写必填项( * 为必填项)！");
			}
		});
		return false;
	});

	$('#reset-btn').click(function() {
		$("#register-form")[0].reset();
		$("#patId").val("");
		$("#patName").removeAttr("readonly");
		$("#userSex").removeAttr("readonly", "readonly");
		$("#mobile").removeAttr("readonly", "readonly");
		$("#age").removeAttr("readonly", "readonly");
		$("#birthday").removeAttr("readonly", "readonly");
		$("#allergicHis").removeAttr("readonly", "readonly");

		$("#source-div2").fadeOut();
		$("#source-div1").fadeIn();
		$("#source2").val("");
	});

	// checkbox默认选中
	$("input[type=checkbox]").each(function() { // 由于复选框一般选中的是多个,所以可以循环输出
		var data = $(this).attr('data-value');
		var checkboxValue = $(this).val();
		var doc = $(this);
		if (strNewlyAsk != undefined) {
			$.each(strNewlyAsk.split(','), function(n, value) {
				if (value == checkboxValue) {
					$(doc).attr('checked', 'checked');
				}
			});
		}
	});
	var value = strNewlyAsk;
	value = strNewlyAsk.replace("肝炎,", "");
	value = value.replace("结核,", "");
	value = value.replace("糖尿病,", "");
	value = value.replace("高血压,", "");
	value = value.replace("冠心病,", "");
	value = value.replace("风湿病,", "");
	value = value.replace("癫痫,", "");
	$('#other-id').val(value);

});

function selectPatient() {
	$('#patient-table').bootstrapTable("destroy");
	$('#patient-table').bootstrapTable({
		method : 'post',
		url : "/enjoyhisfy/client/register/find_patient.json",
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
		}

		],
		pagination : true,
		onClickRow : function(row, tr) {
			// console.info(row);
			$("#patId").val(row.id);
			$("#patName").val(row.patName);
			$("#patName").attr("readonly", "readonly");

			$("#patNo").val(row.patNo);
			$("#userSex").val(row.userSex);
			$("#userSex").attr("readonly", "readonly");
			$("#mobile").val(row.mobile);
			$("#mobile").attr("readonly", "readonly");
			$("#age").val(row.age);
			$("#age").attr("readonly", "readonly");
			$("#birthday").val(row.birthday);
			$("#birthday").attr("readonly", "readonly");
			$("#allergicHis").val(row.allergicHis);
			$("#allergicHis").attr("readonly", "readonly");

			$("#source-div1").fadeOut();
			$("#source-div2").fadeIn();
			$("#source2").val(row.source);

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

// 年龄与日期联动
$(function() {
	$('#age').keyup(function() {
		show();
	});

});
// 获取当前日期
function show() {
	var age = $("#age");// 年龄
	var mydate = new Date();//
	var str = "" + mydate.getFullYear() - age.val() + "-";
	str += "01" + "-";
	str += "01" + "";
	// return str;
	$("#birthday").val(str);
}

// B调C:
function B2C(status) {
	Cef.webCallQT(status)
}
