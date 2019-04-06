<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>报表查询</title>
		<%--<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css"/>--%>
		<%--<link rel="stylesheet" type="text/css" href="../../css/his.css"/>--%>
		<style type="text/css"> 
#search{ 
text-align: center;
position:relative;
}
.autocomplete{
border: 1px solid #9ACCFB;
background-color: white;
text-align: left;
z-index: 9999;
}
.autocomplete li{
list-style-type: none;

}
.clickable {
cursor: default;
}
.highlight {
background-color: #9ACCFB;
}
</style>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
			   		<hr />			   		
			   			<!--底部表格 START-->
			   			<div class="col-md-12 table-responsive">
			   				<div class="row">
			   					<form class="form-horizontal" role="form">				   					 
									<input type="hidden" value="${uid}" id="uid"/>
									<input type="hidden" value="${userType}" id="userType"/>
			   					  	<div class="col-md-12">
					   					<div class="row">
					   					<div class="col-sm-10">
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
										    <c:if test="${idDoc==false}">
										    	<div class="col-sm-3">
										   		    <div class="form-group">
												      <label for="firstname" class="col-sm-4 control-label">医生姓名</label>
												      <div class="col-sm-8" id ="search">
												          <input class="form-control" type="text" id="search-text" code="0" name="search-text" />
												      </div>
												    </div>
										    	</div>
										    </c:if>
										    <div class="col-sm-3">
										   	    <div class="form-group">
											      <label for="firstname" class="col-sm-4 control-label">初/复诊</label>
											      <div class="col-sm-8">
											          <select class="form-control" id="isnew">
											             <option value="2">全部</option>
											             <option value="1">初诊</option>
											             <option value="0">复诊</option>
											          </select>
											      </div>
											    </div>
										    </div>
					   					</div>			   											   						
									    <div class="col-sm-1">
									    	<button type="button" class="btn btn-block btn-success" id="search-btn">查询</button>
									    </div>
										<div class="col-sm-1">
									    	<button type="button" class="btn btn-block btn-success" id="toExl">导出EXL</button>
									    </div>
										</div>
					   				</div>			   					
				   				
			   					<table class="table table-bordered" style="word-break:break-all; word-wrap:break-all;">
									<thead>
									<tr>
										<th>就诊日期</th>
										<th>来源</th>
										<th>患者</th>
										<th>初/复诊</th>
										<th>手机号</th>
										<th>治疗项目</th>
										<th>医生</th>
										<th>处置单金额</th>
										<th>优惠金额</th>
										<th>应收金额</th>
										<th>欠费金额</th>
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
			$("#search-btn").click(function(){
				getList();
			});
			$("#toExl").click(function(){
				toExl();
			});
		});	
		function toExl(){
	    	var strDate = $("#strDate").val();
	    	var endDate = $("#endDate").val();
	    	var docId = $("#search-text").attr("code");
	    	if(docId == undefined){
	    		docId = $('#uid').val();
	    	}
	    	var isnew = $("#isnew").val();
	    	window.location = "/enjoyhisfy/report/source/sourceReportToExcell.htm?strDate="+strDate+"&endDate="+endDate+"&docId="+docId+"&isnew="+isnew
	    }
		function getList(){
			if($("#search-text").val()==""||$("#search-text").val()==null){
				$("#search-text").attr("code",0);
			}
			$.ajax({
			   url: '/enjoyhisfy/report/source/sourceReport.json?',
				type:'POST',
				async:false,
				dataType:'json',
				data:{
					strDate : $("#strDate").val(),
					endDate : $("#endDate").val(),
					docId : $("#search-text").attr("code"),
					isnew : $("#isnew").val(),
				},
				success:function(data,textStatus,XMLHttpRequest){
					var datas = data.rows;		
					var htmlText = "";
					$(datas).each(function(){
						htmlText += '<tr>'+
						'<td>'+this.gh_time+'</td><td>'+this.source+'</td>'+
						'<td>'+this.pat_name+'</td><td>'+this.isnew+'</td>'+
						'<td>'+this.mobile+'</td><td>'+this.service_items+'</td>'+
						'<td>'+this.doc_name+'</td><td>'+this.total_amount.toFixed(2)+'</td>'+
						'<td>'+this.qk_amount.toFixed(2)+'</td><td>'+this.pay_amount.toFixed(2)+'</td>'+
						'<td>'+this.debt_amount.toFixed(2)+'</td></tr>';
					});
					$('#tableHtml').html(htmlText);
				}
			});
		}
		</script>
		<script>
		$(function() {
			var $search = $('#search');
			var $searchInput = $search.find('#search-text');
			$searchInput.attr('autocomplete', 'off');
			var $autocomplete = $('<div class="autocomplete"></div>').hide()
					.insertAfter('#search-text');
			var clear = function() {
				$autocomplete.empty().hide();
			};
			$searchInput.blur(function() {
				setTimeout(clear, 500);
			});
			var selectedItem = null;
			var timeoutid = null;
			var setSelectedItem = function(item) {
				selectedItem = item;
				if (selectedItem < 0) {
					selectedItem = $autocomplete.find('li').length - 1;
				} else if (selectedItem > $autocomplete.find('li').length - 1) {
					selectedItem = 0;
				}
				$autocomplete.find('li').removeClass('highlight').eq(selectedItem)
						.addClass('highlight');
			};
			var ajax_request = function(){
		$.ajax({
					'url' : '/enjoyhisfy/report/source/getDocList.json',
					'data' : {
						'pym' : $searchInput.val()
					},
					'dataType' : 'json',
					'type' : 'POST',
					'success' : function(data) {
						var datas = data.rows;
						if (datas.length) {
							$.each(datas, function(index, term) {
								$('<li code="' + term.code + '"></li>').text(
										term.name).appendTo($autocomplete)
										.addClass('clickable').hover(
												function() {
													$(this).siblings().removeClass(
															'highlight');
													$(this).addClass('highlight');
													selectedItem = index;
												}, function() {
													$(this).removeClass('highlight');
													selectedItem = -1;
												}).click(function() {
											$searchInput.val(term.name).attr("code",term.code);
											$autocomplete.empty().hide();
										});
							});
							var ypos = $searchInput.position().top;
							var xpos = $searchInput.position().left;
							$autocomplete.css('width', $searchInput.css('width'));
							$autocomplete.css({
								'position' : 'absolute',
								'left' : xpos + "px",
								'top' : 30 + "px"
							});
							setSelectedItem(0);
							$autocomplete.show();
						}
					}
				});
			};
			$searchInput.keyup(function(event) {
				if (event.keyCode > 40 || event.keyCode == 8 || event.keyCode == 32) {
					$autocomplete.empty().hide();
					clearTimeout(timeoutid);
					timeoutid = setTimeout(ajax_request, 100);
				} else if (event.keyCode == 38) {
					if (selectedItem == -1) {
						setSelectedItem($autocomplete.find('li').length - 1);
					} else {
						setSelectedItem(selectedItem - 1);
					}
					event.preventDefault();
				} else if (event.keyCode == 40) {
					if (selectedItem == -1) {
						setSelectedItem(0);
					} else {
						setSelectedItem(selectedItem + 1);
					}
					event.preventDefault();
				}
			}).keypress(function(event) {
				if (event.keyCode == 13) {
					if ($autocomplete.find('li').length == 0 || selectedItem == -1) {
						return;
					}
					var doc = $autocomplete.find('li').eq(selectedItem);
					var code = $(doc).attr("code");
					var name = $(doc).text();
					$searchInput.val(name).attr("code",code);
					$autocomplete.empty().hide();
					event.preventDefault();

				}
			}).keydown(function(event) {
				if (event.keyCode == 27) {
					$autocomplete.empty().hide();
					event.preventDefault();
				}
			});
//			$(window).resize(function() {
//				var ypos = $searchInput.position().top;
//				var xpos = $searchInput.position().left;
//				$autocomplete.css('width', $searchInput.css('width'));
//				$autocomplete.css({
//					'position' : 'relative',
//					'left' : xpos + "px",
//					'top' : ypos + "px"
//				});
//			});
		});
		</script>
	</body>
</html>


