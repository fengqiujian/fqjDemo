//出生日期选择
;!function() {
	laydate.skin('molv');
	laydate({
		elem : '#birthday'
	})
}();

$(function() {

	if(isNew!=1){
		// 如果是复诊患者，则患者信息不允许修改
		$('#patName').attr('readonly','readonly');
		$('#persid').attr('readonly','readonly');
		$('#birthday').attr('readonly','readonly');
		$('#mobile').attr('readonly','readonly');
		$('#tel').attr('readonly','readonly');
		$('#email').attr('readonly','readonly');
		$('#address').attr('readonly','readonly');
		
	}

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
	selectPatient();

	var value = strNewlyAsk;
	value = strNewlyAsk.replace("肝炎,", "");
	value = value.replace("结核,", "");
	value = value.replace("糖尿病,", "");
	value = value.replace("高血压,", "");
	value = value.replace("冠心病,", "");
	value = value.replace("风湿病,", "");
	value = value.replace("癫痫,", "");
	$('#other-id').val(value);

	// 选择患者
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
var cb = function(){
	B2C(1);
	window.location = "/enjoyhisfy/client/register/register_index.htm";
}
	$("#add-btn").click(function() {
		var mobile = $("#mobile").val();
		if (mobile.length < 11) {
			alert("请输入11位手机号！");
			return;
		}
		$("#register-form").ajaxSubmit(function(result) {
			if (result.returndata.flag) {
				if(result.returndata.patNo != undefined && result.returndata.patNo != null){
					alert("<span style='font-size:20px;'>操作成功!此患者病历编号为：" + result.returndata.patNo +"</span>",cb);
				}else{
					alert("操作成功！",cb);
				}
			} else {
				alert("操作失败，请填写必填项( * 为必填项)！");
			}
		});
		return false;
	});

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
					// 调叫号系统
					$.ajax({
						url : "/enjoyhisfy/client/registerCall/call.json",
						data : {
							"calltype" : 1,
							"type" : 5
						},
						type : 'post',
						async : false,
						dataType : 'json',
						success : function(data) {
							if (data.ret == 0) {
							}
						}
					});
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
		// search:true,
		columns : [ {
			field : 'id',
			title : '',
			align : 'center',
			formatter : function(value, row, index) {
				return '<label class="pos-rel"><input id=' + value + ' type="checkbox" class="ace"/><span class="lbl"></span></label>';
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

// B调C:
function B2C(status) {
	Cef.webCallQT(status)
}
