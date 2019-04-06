<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>收款汇总</title>
	<%--<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>--%>
	<%--<link rel="stylesheet" type="text/css" href="../../css/his.css"/>--%>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12" >
			<div class="container-fluid ">
				<div class="row">
				</div>
			</div>
			<hr />
			<!--底部表格 START-->
			<div class="col-md-12 table-responsive">
				<div class="row">
					<form class="form-horizontal" role="form">
						<div class="col-md-12">
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label for="firstname" class="col-sm-4 control-label">开始日期</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="strDate">
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label for="firstname" class="col-sm-4 control-label">截止日期</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="endDate">
										</div>
									</div>
								</div>
								<div class="col-sm-1">
									<button type="button" class="btn btn-block btn-success" onclick="getList()">查询</button>
								</div>
								<div class="col-sm-1">
									    	<button type="button" class="btn btn-block btn-success" id="toExl">导出EXL</button>
									    </div>
							</div>
						</div>

						<table class="table table-bordered" style="word-break:break-all; word-wrap:break-all;">
							<thead>
							<tr>
								<th rowspan="2">收款时间</th>
								<th colspan="11">POS机</th>
								<th rowspan="2">微信</th>
								<th rowspan="2">支付宝</th>
								<th rowspan="2">约克平台</th>
								<th rowspan="2">约克牙医微信</th>
								<th rowspan="2">支票</th>
								<th rowspan="2">现金</th>
								<th rowspan="2">合计</th>
							</tr>
							<tr>
								<th>中国银行</th>
								<th>建设银行</th>
								<th>招商银行</th>
								<th>工商银行</th>
								<th>拉卡拉</th>
								<th>民生银行</th>
								<th>浦发银行</th>
								<th>通联</th>
								<th>北京银行</th>
								<th>银行借记卡</th>
								<th>银联商务</th>
							</tr>
							</thead>
							<tbody id="tableHtml">
							</tbody>
						</table>
					</form>
					<!--底部表格 END-->
				</div>
			</div>
		</div>
	</div>
</div>
<%--<script src="../../js/jquery-2.1.4.min.js"></script>--%>
<%--<script src="../../js/bootstrap.min.js"></script>--%>
<%--<script src="../../laydate/laydate.js"></script>--%>
<%--<script src="../../js/selectUi.js"></script>--%>
<script>
	$(function(){
		;!function(){
			laydate.skin('molv');
			var now =laydate.now();
			$('#strDate').val(now)
			laydate({
				elem: '#strDate',
				event: 'click', //触发事件
				format: 'YYYY-MM-DD', //日期格式
				istime: false, //是否开启时间选择
				isclear: true, //是否显示清空
				istoday: true, //是否显示今天
				issure: true, //是否显示确认
				festival: true, //是否显示节日
				min: '2000-01-01', //最小日期
				max: '2099-12-31', //最大日期
				start: now,    //开始日期
				fixed: false, //是否固定在可视区域
				zIndex: 9999999
			})

		}();
		;!function(){
			laydate.skin('molv');
			var now =laydate.now();
			$('#endDate').val(now)
			laydate({
				elem: '#endDate',
				event: 'click', //触发事件
				format: 'YYYY-MM-DD', //日期格式
				istime: false, //是否开启时间选择
				isclear: true, //是否显示清空
				istoday: true, //是否显示今天
				issure: true, //是否显示确认
				festival: true, //是否显示节日
				min: '2000-01-01', //最小日期
				max: '2099-12-31', //最大日期
				start: now,    //开始日期
				fixed: false, //是否固定在可视区域
				zIndex: 9999999
			})

		}();
		
		getList();
		$("#toExl").click(function(){
			toExl();
		});
	});	
    function toExl(){
    	var strDate = $("#strDate").val();
    	var endDate = $("#endDate").val();
    	window.location = "/enjoyhisfy/report/finance/reportFirstToExcell.htm?strDate="+strDate+"&endDate="+endDate
    }
	
	function getList(){
		$.ajax({
			url:"/enjoyhisfy/report/finance/reportFirst.json",
			type:'POST',
			async:false,
			dataType:'json',
			data:{
				strDate:$("#strDate").val(),
				endDate:$("#endDate").val()
			},
			success:function(data,textStatus,XMLHttpRequest){
				var datas = data.rows;		
				var htmlText = "";
				$(datas).each(function(){
					htmlText += '<tr>'+
					'<td>'+this.time+'</td><td>'+this.zgyh.toFixed(2)+'</td>'+
					'<td>'+this.jsyh.toFixed(2)+'</td><td>'+this.zsyh.toFixed(2)+'</td>'+
					'<td>'+this.gsyh.toFixed(2)+'</td><td>'+this.lkl.toFixed(2)+'</td>'+
					'<td>'+this.msyh.toFixed(2)+'</td><td>'+this.pfyh.toFixed(2)+'</td>'+
					'<td>'+this.tl.toFixed(2)+'</td><td>'+this.bjyh.toFixed(2)+'</td>'+
					'<td>'+this.yhjjk.toFixed(2)+'</td><td>'+this.ylsw.toFixed(2)+'</td>'+
					'<td>'+this.wx.toFixed(2)+'</td><td>'+this.zfb.toFixed(2)+'</td>'+
					'<td>'+this.ykpt.toFixed(2)+'</td><td>'+this.ykyywx.toFixed(2)+'</td>'+
					'<td>'+this.zp.toFixed(2)+'</td><td>'+this.xj.toFixed(2)+'</td>'+
					'<td>'+this.hj.toFixed(2)+'</td></tr>';
				});
				$('#tableHtml').html(htmlText);
			}
		});
	}
</script>
</body>
</html>
