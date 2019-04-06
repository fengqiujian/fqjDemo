<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>冲销调整明细表</title>
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
.table-responsive {
	/*min-height: .01%;*/
	overflow: auto;
	max-width: 100%;
	height: 500px;
}
</style>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="container-fluid ">
			   				   				
			   		</div>
			   		<hr />			   		
			   			<!--底部表格 START-->
			   			<div class="table-responsive">
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
				   				
			   					<table class="table table-bordered" >
									<thead>
									<tr>
										<th rowspan="2">日期</th>
										<th rowspan="2">病历编号</th>
										<th rowspan="2">患者</th>
										<th rowspan="2">就诊医生</th>
										<th rowspan="2">账单类型</th>
										<th rowspan="2">处置单编号</th>
										<th rowspan="2">处置单总额</th>
										<th rowspan="2">收费单编号</th>
										<th rowspan="2">优惠</th>
										<th class="text-center" colspan="11">POS机</th>
										<th rowspan="2">微信</th>
										<th rowspan="2">现金</th>
										<th rowspan="2">支票</th>
										<th rowspan="2">保险</th>
										<th rowspan="2">预收款转入</th>
										<th rowspan="2">团购</th>
										<th rowspan="2">欠费</th>
										<th rowspan="2">抹零</th>
										<th rowspan="2">约克平台</th>
										<th rowspan="2">约克牙医微信</th>
										<th rowspan="2">现金折扣</th>
										<th rowspan="2">收费单总额</th>
										<th rowspan="2">原处置单号</th>
										<th rowspan="2">备注</th>
									</tr>
									<tr>
										<th>中国银行</th>
										<th>建设银行</th>
										<th>招商银行</th>
										<th>通联</th>
										<th>拉卡拉</th>
										<th>工商银行</th>
										<th>北京银行</th>
										<th>银联商务</th>
										<th>民生银行</th>
										<th>浦发银行</th>
										<th>银行借记卡</th>
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
	    	var strDate = $("#strDate").val();
	    	var endDate = $("#endDate").val();
	    	var docId = $("#search-text").attr("code");
	    	window.location = "/enjoyhisfy/report/WriteOff/getWriteOfflistToExcell.htm?strDate="+strDate+"&endDate="+endDate+"&docId="+docId
	    }
		function getList(){
			if($("#search-text").val()==""||$("#search-text").val()==null){
				$("#search-text").attr("code",0);
			}
			$.ajax({
			   url: '/enjoyhisfy/report/WriteOff/getWriteOfflist.json?',
				type:'POST',
				async:false,
				dataType:'json',
				data:{
					strDate : $("#strDate").val(),
					endDate : $("#endDate").val(),
					docId : $("#search-text").attr("code")
				},
				success:function(data,textStatus,XMLHttpRequest){
					var datas = data.rows;		
					var htmlText = "";
					$(datas).each(function(){
						var sj=this.sj==undefined?"":this.sj
						var blbh=this.blbh==undefined?"":this.blbh
						var hzxm=this.hzxm==undefined?"":this.hzxm
						var ys=this.ys==undefined?"":this.ys
						var lx=this.lx==undefined?"":this.lx
						var czdid=this.czdid==undefined?"":this.czdid
						var czdze=this.czdze==undefined?"0.00":this.czdze.toFixed(2);
						var sfdbh = '';
						if(this.sfdbh2!=undefined && this.sfdbh2!=null && this.sfdbh2!=''){
							sfdbh=this.sfdbh2
						}else{
							sfdbh=this.sfdbh==undefined?"":this.sfdbh
						}
						var yh=this.yh==undefined?"0.00":this.yh.toFixed(2)
						var zgyh=this.zgyh==undefined?"0.00":this.zgyh.toFixed(2)
						var jsyh=this.jsyh==undefined?"0.00":this.jsyh.toFixed(2)
						var zsyh=this.zsyh==undefined?"0.00":this.zsyh.toFixed(2)
						var tl=this.tl==undefined?"0.00":this.tl.toFixed(2)
						var lkl=this.lkl==undefined?"0.00":this.lkl.toFixed(2)
						var gsyh=this.gsyh==undefined?"0.00":this.gsyh.toFixed(2)
						var bjyh=this.bjyh==undefined?"0.00":this.bjyh.toFixed(2)
						var ylsw=this.ylsw==undefined?"0.00":this.ylsw.toFixed(2)
						var msyh=this.msyh==undefined?"0.00":this.msyh.toFixed(2)
						var pfyh=this.pfyh==undefined?"0.00":this.pfyh.toFixed(2)
						var yhjjk=this.yhjjk==undefined?"0.00":this.yhjjk.toFixed(2)
						var wx=this.wx==undefined?"0.00":this.wx.toFixed(2)
						var xj=this.xj==undefined?"0.00":this.xj.toFixed(2)
						var zp=this.zp==undefined?"0.00":this.zp.toFixed(2)
						var bx=this.bx==undefined?"0.00":this.bx.toFixed(2)
						var yskzr=this.yskzr==undefined?"0.00":this.yskzr.toFixed(2)
						var tg=this.tg==undefined?"0.00":this.tg.toFixed(2)
						var qf=this.qf==undefined?"0.00":this.qf.toFixed(2)
						var ml=this.ml==undefined?"0.00":this.ml.toFixed(2)
						var ykpt=this.ykpt==undefined?"0.00":this.ykpt.toFixed(2)
						var ykyywx=this.ykyywx==undefined?"0.00":this.ykyywx.toFixed(2)
						var hz=this.hz==undefined?"0.00":this.hz.toFixed(2)
						var sfdze=this.sfdze==undefined?"0.00":this.sfdze.toFixed(2)
						var bz=this.bz==undefined?"":this.bz
						var yczdh=this.yczdh==undefined?"":this.yczdh
						htmlText += '<tr>'+
						'<td>'+sj+'</td>'+
						'<td>'+blbh+'</td>'+
						'<td>'+hzxm+'</td>'+
						'<td>'+ys+'</td>'+
						'<td>'+lx+'</td>'+
						'<td>'+czdid+'</td>'+
						'<td>'+czdze+'</td>'+
						'<td>'+sfdbh+'</td>'+
						'<td>'+yh+'</td>'+
						'<td>'+zgyh+'</td>'+
						'<td>'+jsyh+'</td>'+
						'<td>'+zsyh+'</td>'+
						'<td>'+tl+'</td>'+
						'<td>'+lkl+'</td>'+
						'<td>'+gsyh+'</td>'+
						'<td>'+bjyh+'</td>'+
						'<td>'+ylsw+'</td>'+
						'<td>'+msyh+'</td>'+
						'<td>'+pfyh+'</td>'+
						'<td>'+yhjjk+'</td>'+
						'<td>'+wx+'</td>'+
						'<td>'+xj+'</td>'+
						'<td>'+zp+'</td>'+
						'<td>'+bx+'</td>'+
						'<td>'+yskzr+'</td>'+
						'<td>'+tg+'</td>'+
						'<td>'+qf+'</td>'+
						'<td>'+ml+'</td>'+
						'<td>'+ykpt+'</td>'+
						'<td>'+ykyywx+'</td>'+
						'<td>'+hz+'</td>'+
						'<td>'+sfdze+'</td>'+
						'<td>'+yczdh+'</td>'+
						'<td>'+bz+'</td>'+
						'</tr>';
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


