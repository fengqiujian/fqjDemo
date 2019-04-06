// 预约目的联动填充
$('#bookingItem1 td').click(function() {
	$('#bookingItem1 td').css('background', '');
	$('#bookingItem2 td').css('background', '');
	$(this).css('background', '#cfcfcf');
	$('#serviceItems').val($(this).text());

	// 如果选中了“正畸”或者“种植”，则直接给checkbox选中
	if($(this).text().indexOf('正畸')>-1){
		if($('#zhengji')!=undefined){
			$('#zhengji').attr('checked','checked');
		}
	}
	if($(this).text().indexOf('种植')>-1){
		if($('#zhongzhi')!=undefined){
			$('#zhongzhi').attr('checked','checked');
		}
	}
	// 如果选了常用
	if($(this).text().indexOf('常用')>-1){
		$('#serviceItems').val('');
	}
})

$('#bookingItem2 td').click(function() {
	$('#bookingItem2 td').css('background', '');
	$(this).css('background', '#afafaf');
	$('#serviceItems').val($(this).text());
})

$('#item1').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr>';
	if(oftenItems.length > 5){
		for(var i = 0; i < 5; i++){
			tableValue = tableValue + '<td>' + oftenItems[i].itemName + '</td>';
		}
		tableValue += '</tr>';
		tableValue += '<tr>';
		for(var i = 5; i < oftenItems.length; i++){
			tableValue = tableValue + '<td>' + oftenItems[i].itemName + '</td>';
		}
		for(var i = oftenItems.length; i < 10; i++){
			tableValue = tableValue + '<td></td>';
		}
		tableValue += '</tr>';
	}else{
		for(var i = 0; i < oftenItems.length; i++){
			tableValue = tableValue + '<td>' + oftenItems[i].itemName + '</td>';
		}
		for(var i = oftenItems.length; i < 5; i++){
			tableValue = tableValue + '<td></td>';
		}
		tableValue += '</tr>';
	}
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item2').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>备牙</td><td>取模</td><td>试戴牙</td><td>取颌关系</td><td>备桩</td></tr><tr><td>粘桩</td><td>永久粘固</td><td>贴面戴牙</td><td></td><td></td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item3').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>补牙</td><td>根管预备</td><td>根管充填</td><td>封药</td><td>备牙</td></tr><tr><td>备桩</td><td>粘桩</td><td>戴牙</td><td>根尖手术</td><td>根管再治</td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item4').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>种植手术</td><td>种植二期</td><td>种植取模</td><td>种植戴牙</td><td>拆线</td></tr><tr><td>窦内提</td><td>窦外提</td><td>GBR术</td><td>自骨移植</td><td>定期复查</td></tr><tr><td>种植咨询</td><td></td><td></td><td></td><td></td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item5').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>龈上洁治</td><td>龈下洁治</td><td>翻瓣刮治</td><td>牙周手术</td><td>调颌</td></tr><tr><td>松牙固定</td><td>GBR术</td><td>自骨移植</td><td>定期复查</td><td>口腔检查</td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item6').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>复诊加力</td><td>初诊咨询</td><td>讲解计划</td><td>粘接托槽</td><td>取模</td></tr><tr><td>去除托槽</td><td>戴保持器</td><td>隐形矫治</td><td>正畸拔牙</td><td></td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item7').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>拔牙</td><td>拆线</td><td>牙槽修整</td><td>门诊手术</td><td>冠周冲洗</td></tr><tr><td></td><td></td><td></td><td></td><td></td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})

$('#item8').click(function() {
	$('#bookingItem2').html('');
	var tableValue = '<tr><td>乳牙充填</td><td>乳牙根管</td><td>间隙保持</td><td>甲冠戴牙</td><td>乳牙拔除</td></tr><tr><td>乳牙修复</td><td></td><td></td><td></td><td></td></tr>';
	$('#bookingItem2').append(tableValue);
	$('#bookingItem2 td').click(function() {
		$('#bookingItem2 td').css('background', '');
		$(this).css('background', '#afafaf');
		$('#serviceItems').val($(this).text());
	})
})
