!function() {
	laydate.skin('molv');
	var now = laydate.now();
	$('#dateTime').val(now)
	$('.mydate').text(now);
	laydate({
		elem : '#dateTime',
		event : 'click', // 触发事件
		format : 'YYYY-MM-DD', // 日期格式
		istime : false, // 是否开启时间选择
		istoday : true, // 是否显示今天
		issure : true, // 是否显示确认
		festival : true, // 是否显示节日
		min : '2000-01-01', // 最小日期
		max : '2099-12-31', // 最大日期
		start : now, // 开始日期
		fixed : false, // 是否固定在可视区域
		zIndex : 9999999,
		choose : function(dates) { // 选择好日期的回调
			$('.mydate').text(dates);
			intialTd();// 初始化表头医生的信息和画出每个td
			devideList();// 把对应每个医生的所有患者进行分组
		}
	})
}();
!function() {
	laydate.skin('molv');
	laydate({
		elem : '#birthday',
		event : 'click', // 触发事件
		format : 'YYYY-MM-DD', // 日期格式
		// istime: true, //是否开启时间选择
		// isclear: true, //是否显示清空
		istoday : true, // 是否显示今天
		issure : true, // 是否显示确认
		festival : true, // 是否显示节日
		min : '1900-01-01', // 最小日期
		max : '2099-12-31', // 最大日期
		start : '1970-6-15', // 开始日期
		fixed : false, // 是否固定在可视区域
		zIndex : 9999999,
		choose : function(dates) { // 选择好日期的回调
			var age = $("#age");// 年龄
			var mydate = new Date();// 获得当前年份 2016
			age.val(mydate.getFullYear() - dates.substr(0, 4));
		}
	})
}();

/** 圆圆写的start */
var tdcolor = 'color5';
intialTd();// 初始化表头医生的信息和画出每个td
devideList();// 把对应每个医生的所有患者进行分组
function intialTd() {
	var date = null;
	var isBookingDocs = $("#doctor-booding-btn").is(":checked");
	if (isBookingDocs) {
		date = $('#dateTime').val();
	}
	// 获取医生列表
	var doctorArray = [];
	$('[name=items]:checkbox:checked').each(function(index) {
		doctorArray[index] = $(this).val();
	});
	if (doctorArray.length == 0) {
		alert("请至少选择一个医生！");
		return;
	}
	$.ajax({
		url : "/enjoyhisfy/client/employeeinfo/getDoctorList.json",
		data : {
			bookingDate : date
		},
		type : 'post',
		async : false,
		dataType : 'json',
		success : function(doctorData) {
			$(".headTitle th").remove();
			$("td").filter(".removeTd").remove();
			$('.headTitle').append('<th id="tableTime" style="background:#e4e4e4; width:80px;">时间</th>');
			for (var k = 0; k < doctorArray.length; k++) {
				for (var i = 0; i < doctorData.length; i++) {
					var data = doctorData[i];
					if (doctorArray[k] == doctorData[i].id) {
						$('.headTitle').append('<th class="docList" style="background-color: #e4e4e4;">' + data.employeeName + '</th>');
						if (doctorArray.length <= 8) {
							$('.docList').css('width', 'auto');
							$('.index-table').css('width', '100%')
						} else {
							$('.index-table').css('overflow-x', 'visible')
							$('.docList').css('width', '150px')
						}
						for (var j = 0; j < 33; j++) {
							var beginTime = $('.tr' + j + ' td').first().text();
							$('.tr' + j).append('<td style="height:28px;" class="removeTd" docId=' + data.id + ' beginTime=' + beginTime + ' onclick=clickTd(this) attr0=1 class="removeTd" style="border:1px solid #e4e4e4" data-target="#bookingInfoModal"></td>');
						}
					}
				}
			}
		}
	});
}
function setTdcolor(data) {
	switch (data.serviceItems) {
	case '正畸':
		tdcolor = 'color1';
		break;
	case '复诊加力':
		tdcolor = 'color1';
		break;
	case '初诊咨询':
		tdcolor = 'color1';
		break;
	case '讲解计划':
		tdcolor = 'color1';
		break;
	case '粘接托槽':
		tdcolor = 'color1';
		break;
	case '取模':
		tdcolor = 'color1';
		break;
	case '去除托槽':
		tdcolor = 'color1';
		break;
	case '戴保持器':
		tdcolor = 'color1';
		break;
	case '隐形矫治':
		tdcolor = 'color1';
		break;
	case '正畸拔牙':
		tdcolor = 'color1';
		break;
	case '种植':
		tdcolor = 'color2';
		break;
	case '种植手术':
		tdcolor = 'color2';
		break;
	case '种植二期':
		tdcolor = 'color2';
		break;
	case '种植取模':
		tdcolor = 'color2';
		break;
	case '种植戴牙':
		tdcolor = 'color2';
		break;
	case '拆线':
		tdcolor = 'color2';
		break;
	case '窦内提':
		tdcolor = 'color2';
		break;
	case '窦外提':
		tdcolor = 'color2';
		break;
	case 'GBR术':
		tdcolor = 'color2';
		break;
	case '自骨移植':
		tdcolor = 'color2';
		break;
	case '定期复查':
		tdcolor = 'color2';
		break;
	case '美容':
		tdcolor = 'color3';
		break;
	case '儿牙':
		tdcolor = 'color4';
		break;
	case '乳牙充填':
		tdcolor = 'color4';
		break;
	case '乳牙根管':
		tdcolor = 'color4';
		break;
	case '间隙保持':
		tdcolor = 'color4';
		break;
	case '甲冠戴牙':
		tdcolor = 'color4';
		break;
	case '乳牙拔除':
		tdcolor = 'color4';
		break;
	case '乳牙修复':
		tdcolor = 'color4';
		break;
	default:
		tdcolor = 'color5';
		break;
	}
}

function devideList(onlyBooking) {
	var date = null;
	if (onlyBooking) {
		date = $('#dateTime').val();
	}
	// 获取医生列表
	var doctorArray = [];
	$('[name=items]:checkbox:checked').each(function(index) {
		doctorArray[index] = $(this).val();
	});
	if (doctorArray.length == 0) {
		alert("请至少选择一个医生！");
		return;
	}
	// 把每个医生id传入后台，拿到每个医生对应的所有患者信息，并且分列
	for (var j = 0; j < doctorArray.length; j++) {
		var ary1 = [], ary2 = [], ary3 = [];
		var list1 = 0, list2 = 0, list3 = 0;
		$.ajax({
			url : "/enjoyhisfy/client/register/doctorPlan.json",
			data : {
				"beginDate" : $('#dateTime').val() + ' 00:00:00',
				"endDate" : $('#dateTime').val() + ' 23:59:59',
				"doctorId" : doctorArray[j],
			},
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(patientsData) {
				addTimeId(patientsData)
				// 将每一个医生所对应的患者信息进行分列和分组
				for (var i = 0; i < patientsData.length; i++) {
					patient = patientsData[i];
					if (patient.beginTimeId >= list1) {
						list1 = patient.endTimeId;
						patient.list = 'list1';
						ary1.push(patient);
					} else if (patient.beginTimeId >= list2) {
						list2 = patient.endTimeId;
						patient.list = 'list2';
						ary2.push(patient);
					} else {
						list3 = 1;
						patient.list = 'list3';
						ary3.push(patient);
					}
				}
				
				var array = getWidth(ary1, ary2, ary3);

				renderTable(array);
				clickFor();
			}
		});
	}
}

function renderTable(ary) {
	var patientsData = ary;
	for (var i = 0; i < patientsData.length; i++) {
		var data = patientsData[i];
		if (data.patientName == undefined) {
			data.patientName = "新患者"
		}
		var serviceObj = {};
		serviceObj.serviceTime = getBetween(data.beginTime, data.endTime);
		serviceObj.docId = data.docId;
		var _Height = $('td[begintime=9\\:00][docId=' + patientsData[0].docId + ']').css('height');
		setTdcolor(data);
		for (var j = 0; j < serviceObj.serviceTime.length; j++) {// 循环当前这一项预约项目的预约时长
			var time = serviceObj.serviceTime[0];// 开始的预约时间
			var timeArray = time.split(':');
			time = timeArray[0] + '\\:' + timeArray[1], firstBegin = $('td[begintime=' + time + '][docId=' + serviceObj.docId + ']');// //获取到第一个渲染的表格的坐标
			firstBegin.addClass('first');// 给每一个预约项的第一个要渲染的单元格添加一个属性first
			var tdWidth = parseFloat(firstBegin.css('width')) * data.widthFlag + 'px';
			var tdHeight = parseFloat(_Height) + 'px';
			// 分别得到要插入的span
			var str = '<span';
			// 计算得到当前span要插入的td
			var newtime = serviceObj.serviceTime[j];// 开始的预约时间
			var _timeArray = newtime.split(':');
			newtime = _timeArray[0] + '\\:' + _timeArray[1], appendTd = $('td[begintime=' + newtime + '][docId=' + serviceObj.docId + ']');// //获取到要渲染的表格的坐标
			appendTd.css('padding', 0);
			var spanLength = appendTd.children('span').length;
			var spanList = $(appendTd.children('span').get(0)).attr('list');
			var tdMargin;
			if (data.list == 'list2' && spanLength == 0) {
				tdMargin = tdWidth;
			} else if (data.list == 'list2' && spanLength == 1) {
				tdMargin = 0;
			} else if (data.list == 'list3' && spanLength == 0) {
				tdMargin = parseFloat(tdWidth) * 2;
			} else if (data.list == 'list3' && spanList == 'list2' && spanLength == 1) {
				tdMargin = 0;

			} else if (data.list == 'list3' && spanList == 'list1' && spanLength == 1) {
				tdMargin = parseFloat(tdWidth) + 'px';
			} else if (data.list == 'list3' && spanLength == 2) {
				tdMargin = 0;
			}
			if (j == 0) {
				if (data.list == 'list3') {
						str = '<span';
						str = str + ' class="removeTd clickFor ' + tdcolor + '" ';
						str = str + 'style="font-size:20px; width:' + tdWidth + '; border:2px solid ' + tdcolor + '; height:' + tdHeight + '; margin-left:' + tdMargin
						+ '; float:left; overflow:hidden;  border-left:1px solid white; position:relative; line-height:' + tdHeight + ';'
						str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId + '" data-toggle="modal" data-target="#myModal" >'
						+ data.patientName.substr(0, 3) + '</span>';
						appendTd.append(str);
						appendTd.addClass('clearFix');
						//alert('i='+i)
						break;
						//break outfor;
				} else {
					//预约已确认
					if (data.affirm) {
						if(data.status != 1){ // 已挂号
							str = str + ' class="removeTd tdColor" ';
							str = str + 'style="background:#949391; width:' + tdWidth + '; border:2px solid color6; height:' + tdHeight + '; margin-left:' + tdMargin
							+ '; float:left; overflow:hidden; border-left:1px solid white; line-height:' + tdHeight + ';'
							str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId
							+ '" onmouseenter="showDetail(this, event);" style="" data-toggle="modal" data-target="#myModal" >'
							+ '<i class="glyphicon glyphicon-ok"></i>' + data.patientName.substr(0, 3) + '</span>';
						} else{
							str = str + ' class="removeTd ' + tdcolor + '" ';
							str = str + 'style="width:' + tdWidth + '; border:2px solid ' + tdcolor + '; height:' + tdHeight + '; margin-left:' + tdMargin
									+ '; float:left; overflow:hidden; border-left:1px solid white; line-height:' + tdHeight + ';'
							str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId
									+ '" onclick="clickSpan(this, event);" onmouseenter="showDetail(this, event);" style="" data-toggle="modal" data-target="#myModal" >'
									+ '<i class="glyphicon glyphicon-ok"></i>' + data.patientName.substr(0, 3) + '</span>';
						}
					} else {
						if(data.status != 1){ // 已挂号
							str = str + ' class="removeTd color6" ';
							str = str + 'style="background:#949391; width:' + tdWidth + '; border:2px solid color6; height:' + tdHeight + '; margin-left:' + tdMargin
							+ '; float:left; overflow:hidden; border-left:1px solid white; line-height:' + tdHeight + '";'
							str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId
							+ '" onmouseenter="showDetail(this, event);" style="" data-toggle="modal" data-target="#myModal" >' + data.patientName.substr(0, 3)
							+ '</span>';
						}else{
							str = str + ' class="removeTd ' + tdcolor + '" ';
							str = str + 'style="width:' + tdWidth + '; border:2px solid ' + tdcolor + '; height:' + tdHeight + '; margin-left:' + tdMargin
							+ '; float:left; overflow:hidden; border-left:1px solid white; line-height:' + tdHeight + '";'
							str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId
							+ '" onclick="clickSpan(this, event);" onmouseenter="showDetail(this, event);" style="" data-toggle="modal" data-target="#myModal" >' + data.patientName.substr(0, 3)
							+ '</span>';
						}
					}
				}
			} else {
				if(data.status != 1){
					data.patientName = '';
					str = str + ' class="removeTd color6" ';
					str = str + 'style="background:#949391; width:' + tdWidth + '; border:2px solid color6; height:' + tdHeight + '; margin-left:' + tdMargin
					+ '; float:left; overflow:hidden; border-left:1px solid white;  line-height:' + tdHeight + ';'
					str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId
					+ '" onmouseenter="showDetail(this, event);" style="" data-toggle="modal" data-target="#myModal" ></span>';
				}else{
					str = str + ' class="removeTd ' + tdcolor + '" ';
					data.patientName = '';
					str = str + 'style="width:' + tdWidth + '; border:2px solid ' + tdcolor + '; height:' + tdHeight + '; margin-left:' + tdMargin
					+ '; float:left; overflow:hidden; border-left:1px solid white;  line-height:' + tdHeight + ';'
					str = str + '" registId="' + data.id + '" name="' + data.patientName + '" list="' + data.list + '" docId="' + data.docId
					+ '" onclick="clickSpan(this, event);" onmouseenter="showDetail(this, event);" style="" data-toggle="modal" data-target="#myModal" ></span>';
				}
			}
			appendTd.append(str);
			appendTd.addClass('clearFix');
			str = '';
		}
	}
}

function clickList(obj) {
	$('#detail_table').css('display', 'none');
	// 修改预约不允许修改患者信息
	$("#patName").attr("readonly", "readonly");
	$("#userSex").attr("readonly", "readonly");
	$("#mobile").attr("readonly", "readonly");
	$("#age").attr("readonly", "readonly");
	$("#birthday").attr("readonly", "readonly");
	$("#allergicHis").attr("readonly", "readonly");
	$("#source-div1").fadeOut();
	$("#source-div2").fadeIn();

	// 显示另存为与取消预约按钮
	$('.newBtnsure').attr('type', 'button');
	$('.cancelBooking').attr('type', 'button');
	// 表单元素reset
	$('#doctorInfo')[0].reset();
	$('#bookingForm')[0].reset();
	$('#appointmentTime td').css('background', '');
	// 回显医生
	$("#dentistId").attr("data-id", $(obj).attr("docid"));
	var docDate = $('#dateTime').val();
	addTdColor(docDate + ' 00:00:01', docDate + ' 23:59:59', $(obj).attr("docid")); // 调用染色方法
	initSelect();
	$.ajax({
		url : '/enjoyhisfy/client/register/getRegisterInfoById.json',
		data : {
			'id' : $(obj).attr('registId')
		},
		type : 'get',
		success : function(result) {
			var register = JSON.parse(result);
			$('#id').val(register.id); // 标识为修改预约
			// 患者信息
			$('#patName').val(register.patient.patName);
			$('#patId').val(register.patient.id);
			$('#userSex').val(register.patient.userSex);
			$('#mobile').val(register.patient.mobile);
			$('#age').val(register.patient.age);
			var birthday = register.patient.birthday;
			if (birthday != undefined && birthday != null) {
				$('#birthday').val(birthday.split(' ')[0]);
			}
			$('#introducer').val(register.patient.introducer);// 预约人
			$("#source2").val(register.patient.source);
			doc = register.dentistId;
			var patType = register.patType; // 患者vip类型
			if (patType != undefined && patType != null && patType != '') {
				var patTypeArray = patType.split(',');
				if (patTypeArray.length > 0) {
					for (var i = 0; i < patTypeArray.length; i++) {
						$('[name=type]:checkbox').each(function() {
							if ($(this).val() == patTypeArray[i]) {
								$(this).attr('checked', 'checked');
							}
						})
					}
				}
			}
			/* 预约信息 */
			$('#appointmentDate').val(register.bookingDate);// 预约日期
			// 预约时间，单元格染色
			beginTime = register.begin;
			endTime = register.end;

			var timeArray = getBetweenTime(beginTime, endTime);
			$('#bookingTime').text(beginTime);
			$('#bookingLength').text((timeArray.length - 1) * 30);

			for (var j = 0; j < timeArray.length; j++) {
				$("#appointmentTime td").each(function() {
					if (timeArray[j] == $(this).text()) {
						$(this).css('background', '#58bccc');// 添加颜色属性
					}
				});
			}
			// 预约目的
			$('#serviceItems').val(register.serviceItems);

			$('#remark').val(register.remark);
			if (register.affirm == 1) {
				$('#affirm').attr('checked', 'checked');
			}
			$('#bookingInfoModal').modal('show');
		}
	})

}

function clickFor() {
	$('.clickFor').on('click', function(e) {
		e.stopPropagation();
		e.preventDefault();
		$('#detail_table').css('display', 'block');
		$('#detail_table').empty();
		var str = '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" onclick="closeDetailTable()" aria-hidden="true">&times;</button><h4 class="modal-title">当前时间的预约信息</h4></div>';
		str += '<table class="table table-bordered detailTable"><thead><tr><th style="background:#eee">日期</th><th style="background:#eee">姓名</th><th style="background:#eee">开始时间</th><th style="background:#eee">结束时间</th><th style="background:#eee">预约目的</th><th style="background:#eee">是否确认</th></tr></thead><tbody>';
		$.ajax({
			url : "/enjoyhisfy/client/register/doctorPlanByTime.json",
			data : {
				"currentDate" : $('#dateTime').val() + ' ' + $(this).parent().attr('beginTime') + ':00',
				"doctorId" : $(this).attr('docId')
			},
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					if(data[i].status != 1){
						str += ' <tr class="listchange" style="cursor:pointer;" docId=' + data[i].dentistId + ' name=' + data[i].patName + ' beginTime='
						+ data[i].beginTime.slice(10) + ' endTime=' + data[i].endTime.slice(10) + ' registId=' + data[i].id + '><td>' + data[i].beginTime.slice(0, 10) + '</td>';
						str += ' <td>' + data[i].patName + '(已挂号)</td>';
						str += ' <td>' + data[i].beginTime.slice(10) + '</td>';
						str += ' <td>' + data[i].endTime.slice(10) + '</td>';
						str += ' <td>' + data[i].serviceItems + '</td>';
						str += ' <td>' + (data[i].affirm == 0 ? '否' : '是') + '</td></tr>';
					}else {
						str += ' <tr class="listchange" style="cursor:pointer;" onclick="clickList(this);" docId=' + data[i].dentistId + ' name=' + data[i].patName + ' beginTime='
						+ data[i].beginTime.slice(10) + ' endTime=' + data[i].endTime.slice(10) + ' registId=' + data[i].id + '><td>' + data[i].beginTime.slice(0, 10) + '</td>';
						str += ' <td>' + data[i].patName + '</td>';
						str += ' <td>' + data[i].beginTime.slice(10) + '</td>';
						str += ' <td>' + data[i].endTime.slice(10) + '</td>';
						str += ' <td>' + data[i].serviceItems + '</td>';
						str += ' <td>' + (data[i].affirm == 0 ? '否' : '是') + '</td></tr>';
					}
				}
				str += ' </tbody> </table>'
			}
		});
		$('#detail_table').append(str);
		$('#detail_table').css('background', '#fff');
		$('#detail_table').css("left", event.pageX);
		$('#detail_table').css("top", event.pageY);
		$('#detail_table').css("border", '5px solid #eee');
		var left = $('#detail_table').css("left");
		if (left - 500 <= 0) {
			$('#detail_table').css("left", 500);
		} else if (left + 500 > document.documentElement.clientWidth) {
			$('#detail_table').css("left", document.documentElement.clientWidth - 500);
		}
	})
}

function closeDetailTable() {
	$('#detail_table').css('display', 'none');
}

function showDetail(obj, event) {
	var str = '';
	$.ajax({
		url : "/enjoyhisfy/client/register/getRegisterInfoById.json",
		data : {
			"id" : $(obj).attr("registId")
		},
		type : 'post',
		async : false,
		dataType : 'json',
		success : function(data) {
			str += '<ul id="show" style=" position:absolute;z-index:999999; width: 260px; height: 320px;background: #eee; "> ';
			var duration = getDuration(data.begin, data.end);
			if (!data.remark) {
				data.remark = ' ';
			}
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;" ><span style="float:left;text-aligin:left;width:50%;height:100%;"> 预约患者：</span><span style="float:right;width:50%;text-align:center;">'
					+ data.patName + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;" ><span style="float:left;text-aligin:left;width:50%;height:100%;"> 手机号：</span><span style="float:right;width:50%;text-align:center;">'
				+ data.patient.mobile + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;" ><span style="float:left;text-aligin:left;width:50%;height:100%;"> 病历编号：</span><span style="float:right;width:50%;text-align:center;">'
				+ data.patient.patNo + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;" ><span style="float:left;text-aligin:left;width:50%;height:100%;"> 患者来源：</span><span style="float:right;width:50%;text-align:center;">'
				+ data.patient.source + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;"><span style="float:left;text-aligin:left;width:50%;height:100%;"> 预约日期：</span><span style="float:right;width:50%;text-align:center;">'
					+ data.bookingDate + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;"><span style="float:left;text-aligin:left;width:50%;height:100%;"> 预约时间：</span><span style="float:right;width:50%;text-align:center;">'
					+ data.begin + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;"><span style="float:left;text-aligin:left;width:50%;height:100%;"> 预约时长：</span><span style="float:right;width:50%;text-align:center;">'
					+ duration + '分钟</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;"><span style="float:left;text-aligin:left;width:50%;height:100%;">预约目的：</span><span style="float:right;width:50%;text-align:center;">'
					+ data.serviceItems + '</span></li>';
			str += ' <li style="height:35px; line-height:35px; border-bootom:2px solid white;"><span style="float:left;text-aligin:left;width:50%;height:100%;">预约备注：</span><span style="float:right;width:50%;text-align:center;">'
					+ data.remark + '</span></li></ul>';
			$(obj).append(str);
			$('#show').css("left", event.pageX);
			$('#show').css("top", event.pageY);
			$(obj).on('mouseleave', function() {
				str = '';
				$('#show').remove();
			})
		}
	});
}
/** 圆圆写的end */

var beginTime = null; // 全局变量预约开始时间
var endTime = null; // 全局变量预约结束时间
var tempgroup = 1; // td组（old用到的）
var doc;// 定义一个全局变量，用于接收 select的值
var firstClickTd = true;

var cb = function(){
	window.location = "/enjoyhisfy/client/register/booking_plan_view.htm?bookingDate=" + $("#appointmentDate").val();
}
$(function() {
	if(bookingDate != undefined && bookingDate != null && bookingDate != ''){
		$('#dateTime').val(bookingDate);
		$('.mydate').text(bookingDate);
		intialTd();// 初始化表头医生的信息和画出每个td
		devideList();// 把对应每个医生的所有患者进行分组
	}

	$('.currentWeek2').on('click', function() {
		var ndate = new Date();
		ndate = ndate.format("yyyy-mm-dd")
		$('#appointmentDate').val(ndate);
		console.log('ndate=' + ndate);
		WdatePicker({
			eCont : 'appointmentDateDiv',
			lang : 'zh-cn',
			isShowClear : true,
			firstDayOfWeek : 1,
			isShowToday : true,
			startDate : ndate,
			alwaysUseStartDate:true,
			onpicked : function(date) {
				$('#appointmentDate').val(date.cal.getDateStr());
				addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
			}
		})
	})

	$('.lastWeek2').on('click', function() {
		var ndate = $('#appointmentDate').val();
		var newDate = new Date(ndate);
		ndate = new Date(newDate.getTime() - 7 * 24 * 60 * 60 * 1000).format("yyyy-mm-dd");
		$('#appointmentDate').val(ndate);
		console.log('ndate=' + ndate);
		WdatePicker({
			eCont : 'appointmentDateDiv',
			lang : 'zh-cn',
			isShowClear : true,
			firstDayOfWeek : 1,
			isShowToday : false,
			startDate : ndate,
			alwaysUseStartDate:true,
			onpicked : function(date) {
				$('#appointmentDate').val(date.cal.getDateStr());
				addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
				$('#dateTime').val();
			}
		})
	})

	$('.nextWeek2').on('click', function() {
		var ndate = $('#appointmentDate').val();
		var newDate = new Date(ndate);
		ndate = new Date(newDate.getTime() + 7 * 24 * 60 * 60 * 1000).format("yyyy-mm-dd");
		$('#appointmentDate').val(ndate);
		console.log('ndate=' + ndate);
		WdatePicker({
			eCont : 'appointmentDateDiv',
			lang : 'zh-cn',
			isShowClear : true,
			firstDayOfWeek : 1,
			isShowToday : false,
			startDate : ndate,
			alwaysUseStartDate:true,
			onpicked : function(date) {
				$('#appointmentDate').val(date.cal.getDateStr());
				addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
				$('#dateTime').val();
			}
		})
	})

	$('.afterFourWeek2').on('click', function() {
		var ndate = $('#appointmentDate').val();
		var newDate = new Date(ndate);
		ndate = new Date(newDate.getTime() + 28 * 24 * 60 * 60 * 1000).format("yyyy-mm-dd");
		$('#appointmentDate').val(ndate);
		console.log('ndate=' + ndate);
		WdatePicker({
			eCont : 'appointmentDateDiv',
			lang : 'zh-cn',
			isShowClear : true,
			firstDayOfWeek : 1,
			isShowToday : false,
			startDate : ndate,
			alwaysUseStartDate:true,
			onpicked : function(date) {
				$('#appointmentDate').val(date.cal.getDateStr());
				addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
				$('#dateTime').val();
			}
		})
	})
	// 打开筛选医生模态框
	$('#sort-doctor-dialog').click(function() {
		$("#select-doctor-content").css("display", "block");
		$(".modal-backdrop").css("display", "block");
		$('#select-doctor-content').modal({
			backdrop : true,
			keyboard : true,
			show : true
		});
	});

	// 全选
	$("#checkedall").click(function() {
		$("[name=items]:checkbox").prop("checked", true);
	});
	$("#checkedno").click(function() {
		$("[name=items]:checkbox").prop("checked", false);
	});

	// 全不选
	$("[name=items]:checkbox").click(function() {
		var flag = true;// 定义一个变量，默认为true;
		$("[name=items]:checkbox").each(function() {
			if (!this.checked) {
				flag = false;
			}
		});
		$("#checkedall").attr("checked", flag);
	});

	// 上下移动、置顶 操作
	upOrDown();

	// 医生筛选提交按钮操作
	$("#select-doctor-btn").click(function() {
		intialTd();// 初始化表头医生的信息和画出每个td
		devideList();// 把对应每个医生的所有患者进行分组
		$("#select-doctor-content").css("display", "none");
		$(".modal-backdrop").css("display", "none");
	});

	// 查看预约的医生
	$("#doctor-booding-btn").click(function() {
		intialTd();// 初始化表头医生的信息和画出每个td
		devideList();// 把对应每个医生的所有患者进行分组
		$("#select-doctor-content").css("display", "none");
		$(".modal-backdrop").css("display", "none");
	});

	// 点击确定的事件
	$(".btnsure").on("click", function() {
		$(this).attr('disabled', true);
		if (endTime == null) {
			var tArray = beginTime.split(':');
			var mi = parseInt(tArray[1]) + 30;
			if (mi == 30) {
				endTime = tArray[0] + ':' + '30';
			} else {
				endTime = parseInt(tArray[0]) + 1 + ':' + '00';
			}
		} else {
			var tArray = endTime.split(':');
			var mi = parseInt(tArray[1]);
			if (mi == 30) {
				endTime = parseInt(tArray[0]) + 1 + ':' + '00';
			} else {
				endTime = tArray[0] + ':' + '30';
			}
		}
		var mobile = $("#mobile").val();
		if (mobile.length != 11) {
			alert("请输入11位手机号！");
			$(this).attr('disabled', false);
			return;
		}

		/* begin 患者类型选中的checkbox转成string，逗号分隔 */
		var type = '';
		var typeArray = [];
		$('input[name="type"]:checked').each(function() {
			typeArray.push($(this).val());
		});
		if (typeArray.length > 0) {
			for (var i = 0; i < typeArray.length; i++) {
				type += typeArray[i];
				type += ","
			}
			type = type.substring(0, type.length - 1);
		} else {
			type = typeArray[0];
		}
		/* end 患者类型选中的checkbox转成string，逗号分隔 */
		var source = $('#source').val();
		if (source == undefined || source == null || source == '') {
			source = $('#source2').val();
		}
		var affirm = 0;
		if ($('#affirm').is(':checked')) {
			affirm = 1;
		}
		// 提交信息
		$.ajax({
			url : "/enjoyhisfy/client/register/save_booking",
			data : {
				"id" : $('#id').val(), // 预约单号
				"patId" : $('#patId').val(), // 患者ID
				"patName" : $('#patName').val(), // 患者姓名
				"patNo" : $('#patNo').val(), // 病历编号
				"userSex" : $('#userSex').val(), // 性别
				"mobile" : mobile, // 手机号码
				"age" : $('#age').val(), // 年龄
				"birthday" : $('#birthday').val(), // 出生年月
				"dentistId" : doc, // 预约医生
				"bookingDate" : $("#appointmentDate").val(), // 预约日期（年-月-日）
				"begin" : beginTime, // 预约开始时间
				"end" : endTime, // 预约结束时间
				"serviceItems" : $("#serviceItems").val(), // 预约目的
				"remark" : $('#remark').val(), // 预约备注
				"isAppoint" : 1, // 是否预约，1为预约
				"status" : 1, // 预约状态，1为预约
				"source" : source, // 患者来源
				"type" : type, // 患者类型
				"affirm" : affirm, // 是否确认预约
				"introducer" : $('#introducer').val(), // 预约人
				"unitCode" : $('#unitCode').val() // 若是跨院预约，院区码
			},
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.returndata) {
					alert("操作成功！",cb);
				} else {
					alert("操作失败！");
					$(".btnsure").removeAttr('disabled');
				}
			}
		})
	});

	// 另存为新预约
	$(".newBtnsure").on("click", function() {
		if (endTime == null) {
			var tArray = beginTime.split(':');
			var mi = parseInt(tArray[1]) + 30;
			if (mi == 30) {
				endTime = tArray[0] + ':' + '30';
			} else {
				endTime = parseInt(tArray[0]) + 1 + ':' + '00';
			}
		}else {
			var tArray = endTime.split(':');
			var mi = parseInt(tArray[1]);
			if (mi == 30) {
				endTime = parseInt(tArray[0]) + 1 + ':' + '00';
			} else {
				endTime = tArray[0] + ':' + '30';
			}
		}
		var mobile = $("#mobile").val();
		if (mobile.length < 11) {
			alert("请输入11位手机号！");
			return;
		}
		/* begin 患者类型选中的checkbox转成string，逗号分隔 */
		var type = '';
		var typeArray = [];
		$('input[name="type"]:checked').each(function() {
			typeArray.push($(this).val());
		});
		if (typeArray.length > 1) {
			for (var i = 0; i < typeArray.length; i++) {
				type += typeArray[i];
				type += ","
			}
			type = type.substring(0, type.length - 1);
		} else {
			type = typeArray[1];
		}
		/* end 患者类型选中的checkbox转成string，逗号分隔 */
		var source = $('#source').val();
		if (source == undefined || source == null || source == '') {
			source = $('#source2').val();
		}
		var affirm = 0;
		if ($('#affirm').is(':checked')) {
			affirm = 1;
		}
		// 提交信息
		$.ajax({
			url : "/enjoyhisfy/client/register/save_booking",
			data : {
				"patId" : $('#patId').val(), // 患者ID
				"patName" : $('#patName').val(), // 患者姓名
				"patNo" : $('#patNo').val(), // 病历编号
				"userSex" : $('#userSex').val(), // 性别
				"mobile" : mobile, // 手机号码
				"age" : $('#age').val(), // 年龄
				"birthday" : $('#birthday').val(), // 出生年月
				"dentistId" : doc, // 预约医生
				"bookingDate" : $("#appointmentDate").val(), // 预约日期（年-月-日）
				"begin" : beginTime, // 预约开始时间
				"end" : endTime, // 预约结束时间
				"serviceItems" : $("#serviceItems").val(), // 预约目的
				"remark" : $('#remark').val(), // 预约备注
				"isAppoint" : 1, // 是否预约，1为预约
				"status" : 1, // 预约状态，1为预约
				"source" : source, // 患者来源
				"type" : type, // 患者类型
				"affirm" : affirm, // 是否确认预约
				"introducer" : $('#introducer').val(),
				"unitCode" : $('#unitCode').val()
			// 预约人ID
			},
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(result) {
				if (result.returndata) {
					alert("操作成功！",cb);
				} else {
					alert("操作失败！");
				}
			}
		})
	});
	
	/* 病人相关start */
	selectPatient();
	// 选择患者
	$('#open-patient-dialog').click(function() {
		$('#patientFormSearch')[0].reset();
		selectPatient();
		$('#select-patient-content').modal({
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
	// 初诊按钮
	$('#reset-btn').click(function() {
		// 隐藏另存为与取消预约按钮
		$('.newBtnsure').attr('type', 'hidden');
		$('.cancelBooking').attr('type', 'hidden');
		$("#bookingForm")[0].reset();
		$("#introducer").val(introducer);
		$("#patId").val("");
		$("#id").val(""); // 新建预约，清空修改预约单号
		$("#patName").removeAttr("readonly");
		$("#userSex").removeAttr("readonly", "readonly");
		$("#mobile").removeAttr("readonly", "readonly");
		$("#age").removeAttr("readonly", "readonly");
		$("#birthday").removeAttr("readonly", "readonly");
		$("#zhengji").removeAttr('checked');
		$("#zhongzhi").removeAttr('checked');
		$("#meirong").removeAttr('checked');
		$("#source-div2").fadeOut();
		$("#source-div1").fadeIn();
		$("#source2").val("");
	});
	/* 病人相关 end */

	$('#appointmentDate').bind('input propertychange', function() {
		addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
	});

	// 弹出层预约时间点击事件
	$('#appointmentTime td').not('.resetColorTd').click(function() {
		if (beginTime == null) {
			$(this).css('background', '#58bccc');
			beginTime = $(this).text();
			$('#bookingTime').text(beginTime);
			$('#bookingLength').text(30);
		} else {
			if (endTime == null) {
				var beginArray = beginTime.split(':');
				var endText = $(this).text();
				var endArray = endText.split(':');
				// 若第二次点击的时间小于第一次点击，则互换beginTime与endTime
				if (parseInt(beginArray[0]) > parseInt(endArray[0])) {
					endTime = beginTime;
					beginTime = endText;
					$(this).css('background', '#58bccc');
					var timeArray = getBetweenTime(beginTime, endTime);
					for (var j = 0; j < timeArray.length; j++) {
						$("#appointmentTime td").each(function() {
							if (timeArray[j] == $(this).text()) {
								$(this).css('background', '#58bccc');// 添加颜色属性
							}
						});
					}
					$('#bookingTime').text(beginTime);
					$('#bookingLength').text(timeArray.length * 30);
				} else {
					// 若第二次点击的时间小于第一次点击，则互换beginTime与endTime
					if (parseInt(beginArray[0]) == parseInt(endArray[0]) & parseInt(beginArray[1]) >= parseInt(endArray[1])) {
						endTime = beginTime;
						beginTime = endText;
						$(this).css('background', '#58bccc');
						var timeArray = getBetweenTime(beginTime, endTime);
						for (var j = 0; j < timeArray.length; j++) {
							$("#appointmentTime td").each(function() {
								if (timeArray[j] == $(this).text()) {
									$(this).css('background', '#58bccc');// 添加颜色属性
								}
							});
						}
						$('#bookingTime').text(beginTime);
						$('#bookingLength').text(timeArray.length * 30);
					} else {
						$(this).css('background', '#58bccc');
						endTime = endText;
						var timeArray = getBetweenTime(beginTime, endTime);
						for (var j = 0; j < timeArray.length; j++) {
							$("#appointmentTime td").each(function() {
								if (timeArray[j] == $(this).text()) {
									$(this).css('background', '#58bccc');// 添加颜色属性
								}
							});
						}
						$('#bookingLength').text(timeArray.length * 30);
					}
				}
			} else {
				// 先清除原来的染色
				beginTime = null;
				endTime = null;
				$('#appointmentTime td').css('background', '');
				// 将当前td设置为当前时间
				beginTime = $(this).text();
				$('#bookingTime').text(beginTime);
				$('#bookingLength').text('');
				$(this).css('background', '#58bccc');
				$('#bookingLength').text(30);
			}
		}
	})

	// 点击关闭按钮，清空表单
	$('.btncel').click(function() {
		// 表单元素reset
		$('#doctorInfo')[0].reset();
		$('#bookingForm')[0].reset();
		$("#introducer").val(introducer);
		$("#zhengji").removeAttr('checked');
		$("#zhongzhi").removeAttr('checked');
		$("#meirong").removeAttr('checked');
		$("#affirm").removeAttr('checked');
		beginTime = null;
		endTime = null;
	})

	// 取消预约操作
	$(".cancelBooking").click(function() {
		var id = $('#id').val();
		layer.prompt({
			title : '请填写取消原因，并确认',
			formType : 2
		}, function(text) {
			var remark = text;
			$.ajax({
				url : '/enjoyhisfy/client/register/exit_booking',
				data : {
					"id" : id,
					"remark" : "取消预约备注:" + remark
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					layer.msg('您已成功取消预约！');
					B2C(1);
					window.location = "/enjoyhisfy/client/register/register_index.htm";
				}
			});
		});
	});

	$('#dentistId').change(function() {
		doc = $(this).val();
	})
});

// 根据开始时间与结束时间，返回之间所有的时间段
function getBetweenTime(beginTime, endTime) {
	var timeArray = new Array(); // 返回值
	var beginArray = beginTime.split(':');
	var endArray = endTime.split(':');
	var beginMinute = parseInt(beginArray[0]) * 60 + parseInt(beginArray[1]); // 开始时间转换为分钟
	var endMinute = parseInt(endArray[0]) * 60 + parseInt(endArray[1]); // 结束时间转换为分钟
	var betweenNum = (endMinute - beginMinute) / 30; // 开始时间与结束时间之间的时间段
	timeArray[0] = Number(beginArray[0]) + ':' + beginArray[1];
	if (betweenNum > 1) {
		for (var i = 1; i < betweenNum; i++) {
			var mi = (beginMinute + 30 * i) % 60;
			if (mi == 0) {
				mi = '00';
			}
			var hh = parseInt((beginMinute + 30 * i) / 60) + '';
			timeArray[i] = hh + ':' + mi;
		}
	}
	timeArray[betweenNum] = Number(endArray[0]) + ':' + endArray[1];
	return timeArray;
}

// 给弹出层的时间表染色
function addTdColor(beginDate, endDate, doctorId) {
	$('#appointmentTime td').removeClass('tdColor');
	$.ajax({
		url : "/enjoyhisfy/client/register/doctorPlan.json",
		data : {
			"beginDate" : beginDate,
			"endDate" : endDate,
			"doctorId" : doctorId
		},
		type : 'post',
		dataType : 'json',
		success : function(data) {
			// 染色
			for (var i = 0; i < data.length; i++) {
				var timeArray = getBetweenTime(data[i].beginTime, data[i].endTime);
				for (var j = 0; j < timeArray.length - 1; j++) {
					$("#appointmentTime td").each(function() {
						if (timeArray[j] == $(this).text()) {
							$(this).addClass('tdColor');// 添加颜色属性
						}
					});
				}
			}
		}
	});
}

// 点击每一个格子触发事件
function clickTd(obj) {
	var currentDate = $('#dateTime').val();
	WdatePicker({
		eCont : 'appointmentDateDiv',
		lang : 'zh-cn',
		isShowClear : true,
		firstDayOfWeek : 1,
		isShowToday : false,
		startDate : currentDate,
		alwaysUseStartDate:true,
		onpicked : function(date) {
			$('#appointmentDate').val(date.cal.getDateStr());
			addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
		}
	})
	// 因为新建预约&修改预约都在此页面，此属性用来区分
	$('#id').val('');
	$("#patId").val(alreadyPatId);
	// 隐藏另存为与取消预约按钮
	$('.newBtnsure').attr('type', 'hidden');
	$('.cancelBooking').attr('type', 'hidden');
	// 表单元素reset
	$('#doctorInfo')[0].reset();
	$('#bookingForm')[0].reset();
	$('#appointmentTime td').css('background', '');
	$("#introducer").val(introducer);
	// 弹出层默认点击的时间
	$('#appointmentDate').val($('#dateTime').val());
	// 若上次选择了复诊，本次可填
	$("#patName").removeAttr("readonly");
	$("#userSex").removeAttr("readonly", "readonly");
	$("#mobile").removeAttr("readonly", "readonly");
	$("#age").removeAttr("readonly", "readonly");
	$("#birthday").removeAttr("readonly", "readonly");
	$("#zhengji").removeAttr('checked');
	$("#zhongzhi").removeAttr('checked');
	$("#meirong").removeAttr('checked');
	$("#source-div2").fadeOut();
	$("#source-div1").fadeIn();
	$("#source2").val("");
	// 全局变量重置
	beginTime = $(obj).attr('beginTime');
	endTime = null;
	$('#bookingTime').text(beginTime);
	$('#bookingLength').text(30);
	$("#appointmentTime td").each(function() {
		if ($(obj).attr('beginTime') == $(this).text()) {
			$(this).css('background', '#58bccc');// 添加颜色属性
		}
	});

	// 点击的表格元素(变量名不要改了)
	doc = $(obj).attr('docid');
	// 回显医生
	$("#dentistId").attr("data-id", doc);
	addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
	initSelect();
	$('#bookingInfoModal').modal('show');
}

// 动态获取select的option
function initSelect() {
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
}

// 上下移动、置顶操作
function upOrDown() {
	// 上移
	var $up = $(".up")
	$up.click(function() {
		var $tr = $(this).parents("tr");
		if ($tr.index() != 0) {
			$tr.fadeOut().fadeIn();
			$tr.prev().before($tr);
		}
	});
	// 下移
	var $down = $(".down");
	var len = $down.length;
	$down.click(function() {
		var $tr = $(this).parents("tr");
		if ($tr.index() != len - 1) {
			$tr.fadeOut().fadeIn();
			$tr.next().after($tr);
		}
	});
	// 置顶
	var $top = $(".top");
	$top.click(function() {
		var $tr = $(this).parents("tr");
		$tr.fadeOut().fadeIn();
		$(".table1").prepend($tr);
		$tr.css("color", "#f60");
	});
}

// 选择复诊患者
function selectPatient() {
	$('#patient-table').bootstrapTable("destroy");
	$('#patient-table').bootstrapTable({
		method : 'post',
		url : "/enjoyhisfy/client/register/find_patient.json",
		pagination : true,
		queryParamsType : '', // 默认值为 'limit' ,在默认情况下 传给服务端的值为：offset limit
								// sort，设置为 '' 在这种情况下传给服务器的值为：pageSize
								// pageNumber
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
		} ],
		pagination : true,
		onClickRow : function(row, tr) {
			// 隐藏另存为与取消预约按钮
			$('.newBtnsure').attr('type', 'hidden');
			$('.cancelBooking').attr('type', 'hidden');
			$("#id").val(""); // 新建预约，清空修改预约单号
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
			$("#introducer").val(row.introducer);
			$("#allergicHis").val(row.allergicHis);
			$("#allergicHis").attr("readonly", "readonly");
			$("#source-div1").fadeOut();
			$("#source-div2").fadeIn();
			$("#source2").val(row.source);
			$('#select-patient-content').modal("hide");

			var patType = row.type; // 患者vip类型
			if (patType != undefined && patType != null && patType != '') {
				var patTypeArray = patType.split(',');
				if (patTypeArray.length > 0) {
					for (var i = 0; i < patTypeArray.length; i++) {
						$('[name=type]:checkbox').each(function() {
							if ($(this).val() == patTypeArray[i]) {
								$(this).attr('checked', 'checked');
							}
						})
					}
				}
			}
		}
	});
}

// table查询参数
function queryParams(params) {
	return {
		patName : $('#select_patName').val(),
		mobile : $('#select_mobile').val(),
		maindocId : $('#select_maindocId').val(),
		pageSize : params.pageSize,
		pageNumber : params.pageNumber,
	};
}

function clickSpan(obj, event) {
	var currentDate = $('#dateTime').val();
	WdatePicker({
		eCont : 'appointmentDateDiv',
		lang : 'zh-cn',
		isShowClear : true,
		firstDayOfWeek : 1,
		isShowToday : false,
		startDate : currentDate,
		onpicked : function(date) {
			$('#appointmentDate').val(date.cal.getDateStr());
			addTdColor($("#appointmentDate").val() + ' 00:00:01', $("#appointmentDate").val() + ' 23:59:59', doc); // 调用染色方法
		}
	})
	event.stopPropagation();

	// 修改预约不允许修改患者信息
	$("#patName").attr("readonly", "readonly");
	$("#userSex").attr("readonly", "readonly");
	$("#mobile").attr("readonly", "readonly");
	$("#age").attr("readonly", "readonly");
	$("#birthday").attr("readonly", "readonly");
	$("#allergicHis").attr("readonly", "readonly");
	$("#source-div1").fadeOut();
	$("#source-div2").fadeIn();

	// 显示另存为与取消预约按钮
	$('.newBtnsure').attr('type', 'button');
	$('.cancelBooking').attr('type', 'button');
	// 表单元素reset
	$('#doctorInfo')[0].reset();
	$('#bookingForm')[0].reset();
	$('#appointmentTime td').css('background', '');
	// 回显医生
	$("#dentistId").attr("data-id", $(obj).attr("docid"));
	var docDate = $('#dateTime').val();
	addTdColor(docDate + ' 00:00:01', docDate + ' 23:59:59', $(obj).attr("docid")); // 调用染色方法
	initSelect();
	$.ajax({
		url : '/enjoyhisfy/client/register/getRegisterInfoById.json',
		data : {
			'id' : $(obj).attr('registId')
		},
		type : 'get',
		success : function(result) {
			var register = JSON.parse(result);
			$('#id').val(register.id); // 标识为修改预约
			// 患者信息
			$('#patName').val(register.patient.patName);
			$('#patId').val(register.patient.id);
			$('#userSex').val(register.patient.userSex);
			$('#mobile').val(register.patient.mobile);
			$('#age').val(register.patient.age);
			var birthday = register.patient.birthday;
			if (birthday != undefined && birthday != null) {
				$('#birthday').val(birthday.split(' ')[0]);
			}
			$('#introducer').val(register.patient.introducer);// 预约人
			$("#source2").val(register.patient.source);
			doc = register.dentistId;
			var patType = register.patType; // 患者vip类型
			if (patType != undefined && patType != null && patType != '') {
				var patTypeArray = patType.split(',');
				if (patTypeArray.length > 0) {
					for (var i = 0; i < patTypeArray.length; i++) {
						$('[name=type]:checkbox').each(function() {
							if ($(this).val() == patTypeArray[i]) {
								$(this).attr('checked', 'checked');
							}
						})
					}
				}
			}
			/* 预约信息 */
			$('#appointmentDate').val(register.bookingDate);// 预约日期
			// 预约时间，单元格染色
			beginTime = register.begin;
			endTime = register.end;

			var timeArray = getBetweenTime(beginTime, endTime);
			if (endTime != null) {
				var tArray = endTime.split(':');
				var mi = parseInt(tArray[1]);
				if (mi == 30) {
					endTime = parseInt(tArray[0]) + ':' + '00';
				} else {
					endTime = parseInt(tArray[0]) - 1 + ':' + '30';
				}
			}
			$('#bookingTime').text(beginTime);
			$('#bookingLength').text(timeArray.length * 30 - 30);

			for (var j = 0; j < timeArray.length - 1; j++) {
				$("#appointmentTime td").each(function() {
					if (timeArray[j] == $(this).text()) {
						$(this).css('background', '#58bccc');// 添加颜色属性
					}
				});
			}
			// 预约目的
			$('#serviceItems').val(register.serviceItems);

			$('#remark').val(register.remark);
			if (register.affirm == 1) {
				$('#affirm').attr('checked', 'checked');
			}
			$('#bookingInfoModal').modal('show');
		}
	})
}
function closeDetailTable() {
	$('#detail_table').css('display', 'none');
}
function B2C(status) {
	Cef.webCallQT(status)
}
