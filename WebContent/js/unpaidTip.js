(function() {
	checkIsExist();// 先执行一遍函数
	// 获取后台人数
	function checkIsExist() {
		$.ajax({
			url : "/enjoyhisfy/client/register/find_register.json?type=3",
			data : null,
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.total != 0) {
					//alert("dentistId=="+$(dentistId).val());
					if (($('#dentistId').val()) > 1) {
						$('#unpaid-tip').stop().hide();
						window.clearInterval(c);
					} else {
						var str = '';
						str += '<a href="#">当前未缴费人数:<i id="noPaid">' + data.total + '</i>人</a>';
						$('#unpaid-tip').html(str);
						$('#unpaid-tip').stop().show(1000);

					}
				} else {
					$('#unpaid-tip').stop().hide();
					window.clearInterval(c);

				}
			},
			error : function(err) {
				$("#alert").stop().hide();
				window.clearInterval(c);
			}
		});
	}

	window.clearInterval(c);
	var c = setInterval(checkIsExist, 10000);// 每10秒执行一次checkIsExist方法
})();
