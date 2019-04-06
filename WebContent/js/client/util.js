//比较两条患者信息是不是有重合的时间
function compareTime(preObj, nexObj) {// patientsData的每一项跟其他项进行比较
	var preBeginArray = preObj.beginTime.split(':');
	var preBeginMinute = parseInt(preBeginArray[0]) * 60 + parseInt(preBeginArray[1]);
	var nexBeginArray = nexObj.beginTime.split(':');
	var nexBeginMinute = parseInt(nexBeginArray[0]) * 60 + parseInt(nexBeginArray[1]);
	// 上一条患者的预约时长>下一条患者的预约时长
	if (preBeginMinute > nexBeginMinute) {
		var distance = getBetween(nexObj.beginTime, preObj.endTime).length;
	} else {
		var distance = getBetween(preObj.beginTime, nexObj.endTime).length;
	}
	var preDistance = getBetween(preObj.beginTime, preObj.endTime).length;
	var nexDistance = getBetween(nexObj.beginTime, nexObj.endTime).length;
	if (distance > (preDistance + nexDistance - 1)) {// 没有重合的地方
		return false;
	} else {
		return true;
	}
}

//比较两条患者信息预约开始时间是否相同
function compareBeginTime(preObj, nexObj) {// patientsData的每一项跟其他项进行比较
	var preBeginArray = preObj.beginTime.split(':');
	var preBeginMinute = parseInt(preBeginArray[0]) * 60 + parseInt(preBeginArray[1]);
	var nexBeginArray = nexObj.beginTime.split(':');
	var nexBeginMinute = parseInt(nexBeginArray[0]) * 60 + parseInt(nexBeginArray[1]);
	// 上一条患者的预约时长>下一条患者的预约时长
	if (preBeginMinute == nexBeginMinute) {
		return true;
	} else {
		return false;
	}
}

function getDuration(beginTime, endTime) {
	var timeArray = new Array(); // 返回值
	var beginArray = beginTime.split(':');
	var endArray = endTime.split(':');
	var beginMinute = parseInt(beginArray[0]) * 60 + parseInt(beginArray[1]); // 开始时间转换为分钟
	var endMinute = parseInt(endArray[0]) * 60 + parseInt(endArray[1]); // 结束时间转换为分钟
	var duration = endMinute - beginMinute;
	return duration;
}

// 得到开始时间和结束时间之前的时间段
function getBetween(beginTime, endTime) {
	var timeArray = new Array(); // 返回值
	var beginArray = beginTime.split(':');
	var endArray = endTime.split(':');
	var beginMinute = parseInt(beginArray[0]) * 60 + parseInt(beginArray[1]); // 开始时间转换为分钟
	var endMinute = parseInt(endArray[0]) * 60 + parseInt(endArray[1]); // 结束时间转换为分钟
	if (endMinute < beginMinute) {
		var tmpMinute = endMinute;
		endMinute = beginMinute;
		beginMinute = tmpMinute;
	}
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
	timeArray.length--;
	return timeArray;
}

// 添加时间的标识
function addTimeId(patientsData) {
	for (var i = 0; i < patientsData.length; i++) {
		var patient = patientsData[i];
		patient.beginTimeId = parseFloat(patientsData[i].beginTime.replace(':', ''));
		patient.endTimeId = parseFloat(patientsData[i].endTime.replace(':', ''));
	}
}

function getWidth(ary1, ary2, ary3) {
	// 计算list2宽度，如果第二行有和第三行重复的，则宽度为1/3，否则为1/2
	if (ary2.length) {
		for (var i = 0; i < ary2.length; i++) {
			var n = 0;
			for (var j = 0; j < ary3.length; j++) {
				if (ary3[j].beginTimeId > ary2[i].endTimeId || ary3[j].endTimeId < ary2[i].beginTimeId) {
					continue;
				} else {
					n++;
				}
			}
			if (n == 0) {
				ary2[i].widthFlag = 0.495;
			} else {
				ary2[i].widthFlag = 0.33;
			}
		}
	}

	// 计算list1宽度
	for (var i = 0; i < ary1.length; i++) {
		var m = 0, x = 0;
		for (var j = 0; j < ary3.length; j++) {
			var isFuse = compareTime(ary3[j], ary1[i]);
			if (!isFuse) {
				continue;
			} else {
				m++;
			}
		}
		if (m == 0) { // 如果list1与list3无重合时间
			// 判断list1和list2有没有重合时间
			for (var k = 0; k < ary2.length; k++) {
				var isFuse = compareTime(ary2[k], ary1[i]);
				if (!isFuse) {
					continue;
				} else {
					if (ary2[k].widthFlag == 0.33) {
						ary1[i].widthFlag = 0.33;
					} else if (ary1[i].widthFlag != 0.33 && ary2[k].widthFlag == 0.495) {
						ary1[i].widthFlag = 0.495;
					}
					x++;
				}
			}
			if (x == 0) { // 若list1和list2无重合时间，则list1宽度为整个宽度。
				ary1[i].widthFlag = 1;
			}
		} else {
			ary1[i].widthFlag = 0.33;
		}
	}

	// 计算第三行的宽度
	for (var i = 0; i < ary3.length; i++) {
		console.log('ary3.length=' + ary3.length);
		console.log('i=' + i);
		console.log(ary3[i]);
		ary3[i].widthFlag = 0.33;
		ary3[i].patientName = '...';
		for (var j = i + 1; j < ary3.length; j++) {
			console.log(ary3[j]);
			var isFuse = compareBeginTime(ary3[i], ary3[j]);
			if (isFuse) {
				ary3.splice(j, 1);
				j--;
			}
		}
	}
	var ary = ary1.concat(ary2, ary3);
	return ary;
}