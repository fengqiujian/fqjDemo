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
				<div class="col-sm-12" style="margin-top: 15px;">
					<div class="container-fluid ">
			   				   				
			   		</div>
			   		<hr />			   		
			   			<!--底部表格 START-->
			   			<div class="col-md-12 table-responsive">
			   				<div class="row">
			   					<form class="form-horizontal" role="form">				   					 
			   					  	<div class="col-md-12">
					   					<div class="row">
					   					<div class="col-sm-10">
					   					   <div class="col-sm-4">
										   	    <div class="form-group">
											      <label for="firstname" class="col-sm-4 control-label">开始日期</label>
											      <div class="col-sm-8">
											          <input type="text" class="form-control" id="strDate">
											      </div>
											    </div>
										    </div>	
										    <div class="col-sm-4">
										   	    <div class="form-group">
											      <label for="firstname" class="col-sm-4 control-label">截止日期</label>
											      <div class="col-sm-8">
											          <input type="text" class="form-control" id="endDate">
											      </div>
											    </div>
										    </div>
										    <c:if test="${idDoc==false}">
										    	<div class="col-sm-4">
										   		    <div class="form-group">
												      <label for="firstname" class="col-sm-4 control-label">医生姓名</label>
												      <div class="col-sm-8" id ="search">
												          <input class="form-control" type="text" id="search-text" code="0" name="search-text" />
												      </div>
												    </div>
										    	</div>
										    </c:if>
										   
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
										<th>患者ID</th>
										<th>患者姓名</th>
										<th>医生姓名</th>
										<th>处置单号</th>
										<th>处置单金额</th>
										<th>收费单号</th>
										<th>查看</th>
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
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		   aria-labelledby="myModalLabel" aria-hidden="true" style="width: 100%">
		   <div class="modal-dialog">
		      <div class="modal-content" style="width: 100%">
		         <div class="modal-header" style="height: 30px;">
		            <h5 class="modal-title text-center" style="line-height: 30px;font-size:15px;margin-top:-15px">
		               	查看详情
		            </h5>
		         </div>
		         <div class="modal-body" style="height: 450px">
					<p id="PPP"></p>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default"
		               data-dismiss="modal">关闭
		            </button>
		         </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
		
		
		<%--<script src="../../js/jquery-2.1.4.min.js"></script>--%>
		<%--<script src="../../js/bootstrap.min.js"></script>--%>
		<%--<script src="../../laydate/laydate.js"></script>--%>
		<%--<script src="../../js/selectUi.js"></script>--%>
		<%--<script src="../../js/bootstrap-table.min.js"></script>--%>
		<%--<script src="../../js/bootstrap-table-zh-CN.min.js"></script>--%>
		<%--<script src="../../js/lq.datetimepick.js"></script>--%>
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
	    	if($("#search-text").val()==""||$("#search-text").val()==null){
				$("#search-text").attr("code",0);
			}
	    	var strDate = $("#strDate").val();
	    	var endDate = $("#endDate").val();
	    	var docId = $("#search-text").attr("code");
	    	window.location = "/enjoyhisfy/report/ChargeDetail/ChargeDetailReportToExcell.htm?strDate="+strDate+"&endDate="+endDate+"&docId="+docId
	    }
	
		function getList(){
			if($("#search-text").val()==""||$("#search-text").val()==null){
				$("#search-text").attr("code",0);
			}
			$.ajax({
			   url: '/enjoyhisfy/report/ChargeDetail/ChargeDetailReport.json?',
				type:'POST',
				async:false,
				dataType:'json',
				data:{
					strDate : $("#strDate").val(),
					endDate : $("#endDate").val(),
					docId : $("#search-text").attr("code"),
				},
				success:function(data,textStatus,XMLHttpRequest){
					var datas = data.rows;		
					var htmlText = "";
					$(datas).each(function(){
						if(this.oldCode!=undefined && this.oldCode!=null && this.oldCode!=''){
							htmlText += '<tr>'+
							'<td>'+this.zjri+'</td><td>'+this.patId+'</td>'+
							'<td>'+this.hzName+'</td><td>'+this.docName+'</td>'+
							'<td>'+this.statementItemid+'</td><td>'+this.totalAmount.toFixed(2)+'</td>'+
							'<td>'+this.oldCode+'</td>'+
							'<td><a href="#" onclick="show('+this.id+')">查看</a></td></tr>';
						}else {
							htmlText += '<tr>'+
							'<td>'+this.zjri+'</td><td>'+this.patId+'</td>'+
							'<td>'+this.hzName+'</td><td>'+this.docName+'</td>'+
							'<td>'+this.statementItemid+'</td><td>'+this.totalAmount.toFixed(2)+'</td>'+
							'<td>'+this.id+'</td>'+
							'<td><a href="#" onclick="show('+this.id+')">查看</a></td></tr>';
						}
					});
					$('#tableHtml').html(htmlText);
				}
			});
		}
		</script>
		<script>
		var showId;
		function show(id){
			showId = id;
			$('#myModal').modal({
				backdrop: 'static',
				keyboard: true
			})
			
		}
		
		$(function () { $('#myModal').on('show.bs.modal', function () {
			$.ajax({
				   url: '/enjoyhisfy/report/ChargeDetail/Detail.json?',
					type:'POST',
					async:false,
					dataType:'json',
					data:{
						sid : showId
					},
					success:function(data,textStatus,XMLHttpRequest){
						var htmlText = "";
						var datas = data.rows;	
						var obj = data.obj;
						var key;
						$(datas).each(function(){
							key = this.paymentType;
							if(key=="POS"||key=="TG"){
								key = this.paymentSubtype;
								htmlText += obj[key];
								htmlText += ":"+this.realAmount;
							}else{
								htmlText += obj[key];
								htmlText += ":"+this.realAmount;
							}
							htmlText += "</br>";
						});
					  	$("#PPP").html(htmlText);
					}
				});
		  })
	   }); 
		
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


