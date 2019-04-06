<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head> 
		<meta charset="utf-8" />
		<title>控制台</title>
		<meta name="keywords" content="北京汇河科技后台管理系统" />
		<meta name="description" content="汇河科技是一家专业的网络服务承接公司" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="../../assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="../../assets/css/font-awesome.css" />
		<link rel="stylesheet" href="../../assets/css/ace.min.css" />
		<script src="${path}/js/jquery/jquery-1.8.3.min.js"></script>
		<script src="${path}/js/jquery/jquery.cookie.js"></script>
		<script src="${path}/js/jquery/jquery.form.js"></script>
		
		<style>
		/*loading-3*/
		@-webkit-keyframes loading-3{
			0%{
				left: 50px;
				top: 0;
			}
			80%{
				left: 0px;
				top: 0;
			}
			85%{
				left: 0;
				top: -10px;
				width: 10px;
				height: 10px;
			}
			90%{
				width: 10px;
				height: 10px;
			}
			95%{
				left: 50px;
				top: -10px;
				width: 10px;
				height: 10px;
			}
			100%{
				left: 50px;
				top:0;
			}
		}
		@-moz-keyframes loading-3{
			0%{
				left: 50px;
				top: 0;
			}
			80%{
				left: 0px;
				top: 0;
			}
			85%{
				left: 0;
				top: -10px;
				width: 10px;
				height: 10px;
			}
			90%{
				width: 10px;
				height: 10px;
			}
			95%{
				left: 50px;
				top: -10px;
				width: 10px;
				height: 10px;
			}
			100%{
				left: 50px;
				top:0;
			}
		}
		.loading-3{
		position: relative;
		width: 80px;
		height: 10px;
		}
		.loading-3 i{
			display: block;
			width: 10px;
			height: 10px;
			border-radius: 50%;
			background-color: #008000;
			margin-right: 10px;
			position: absolute;
			margin-top: 10px;
		}
		.loading-3 i:nth-child(1){
			-webkit-animation: loading-3 2s linear 0s infinite;
			-moz-animation: loading-3 2s linear 0s infinite;
		}
		.loading-3 i:nth-child(2){
			-webkit-animation: loading-3 2s linear -0.4s infinite;
			-moz-animation: loading-3 2s linear -0.4s infinite;
		}
		.loading-3 i:nth-child(3){
			-webkit-animation: loading-3 2s linear -0.8s infinite;
			-moz-animation: loading-3 2s linear -0.8s infinite;
		}
		.loading-3 i:nth-child(4){
			-webkit-animation: loading-3 2s linear -1.2s infinite;
			-moz-animation: loading-3 2s linear -1.2s infinite;
		}
		.loading-3 i:nth-child(5){
			-webkit-animation: loading-3 2s linear -1.6s infinite;
			-moz-animation: loading-3 2s linear -1.6s infinite;
		}
		</style>
	</head>

	<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		</script>

		<ul class="breadcrumb">
			<li>
				<i class="icon-home home-icon"></i>
				<a href="#">首页</a>
			</li>
			<li class="active">后台系统管理</li>
			<li class="active">诊室配置</li>
		</ul><!-- .breadcrumb -->
	</div>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<div class="col-sm-12" id="my">		
						<form class="form-horizontal" method="post" action="/enjoyhisfy/admin/hisdocroom/home.htm" role="form" id="searchs">
							<div class="col-xs-10">
								<div class="row">
								
									<div class="col-sm-3">
										<div class="form-group">
									      <label  class="col-sm-5 control-label">诊室名称:</label>
									      <div class="col-sm-7">
									         <input type="text" class="form-control" id="roomName"  name="roomName" value=""
									            placeholder="诊室名称">
									      </div>
									    </div>
									</div>
	
									<div class="col-sm-3">
										<div class="form-group">
									      <label  class="col-sm-5 control-label">诊室IP:</label>
									      <div class="col-sm-7">
									         <input type="text" class="form-control" id="roomIp" name="roomIp"  value=""
									            placeholder="请输入诊室IP">
									      </div>
									    </div>
									</div>			
									
								</div>								
							</div>
							<div class="col-xs-1">
								<div class="form-group">
								  <button type="button" name="search" class="btn btn-info mybtn" style="height:34px ;line-height:14px;">查询</button>
							    </div>
							</div>	
					    </form>
					    	<div class=" pull-right">
								<div class="row">
									<a id="add-modal" class="btn btn-info btn-lg smaller-90 bg-primary white pull-right" data-toggle="modal" data-target="" style="height:34px;">
										<span class="glyphicon glyphicon-plus"></span> 添加
									</a>
								</div>
							</div>			   
					</div><!-- /.page-header -->
						<div class="row">  
							<form method="get" id="" >
								<div class="col-md-12" style="margin: 0 auto;">								
									<table class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>诊室IP</th>
												<th>诊室名称</th>
												<th>诊室创建时间</th>
												<th>诊室修改时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="data">
											<c:forEach items="${requestScope.roomList}" var="item" varStatus="vs">
												<tr>
													<td>${item.id}</td>
													<td>${item.roomIp}</td>
													<td>${item.roomName}</td>
													<td><fmt:formatDate value="${item.createTime}" type="both"  pattern="yyyy/MM/dd"/> </td>
													<td><fmt:formatDate value="${item.modifyTime}" type="both"  pattern="yyyy/MM/dd"/> </td>
													<td>
														<a href="#" openurl="modify_room?modifyid=${item.id}" class="find-modal btn btn-xs btn-info" style="text-decoration:none;color:#00f;" >
														<i class="ace-icon fa fa-pencil bigger-120"></i></a>&nbsp;
														<a class="btn btn-xs btn-danger" clickurl="delete_room?appid=${item.id}" href="javascript:void(0)" style="text-decoration:none;color:#00f;">
														<i class="ace-icon fa fa-trash-o bigger-120"></i></a>										
													</td>
												</tr>
						 					</c:forEach>
							
										</tbody>
									</table>
								</div>
								</form>
								<!--col-md-11-->
								<div class="row">
								<div class="col-xs-12">
									<div id="dynamic-table_paginate" class="dataTables_paginate paging_simple_numbers" style="text-align: center;">
										<ul class="pagination">
											<li id="dynamic-table_previous" tabindex="0" aria-controls="dynamic-table" class="paginate_button previous disabled"><a href="#">上一页</a></li>
											<li tabindex="0" aria-controls="dynamic-table" class="paginate_button active"><a href="#">1</a></li>
											<li tabindex="0" aria-controls="dynamic-table" class="paginate_button "><a href="#">2</a></li>
											<li tabindex="0" aria-controls="dynamic-table" class="paginate_button "><a href="#">3</a></li>
											<li tabindex="0" aria-controls="dynamic-table" class="paginate_button "><a href="#">4</a></li>
											<li tabindex="0" aria-controls="dynamic-table" class="paginate_button "><a href="#">5</a></li>
											<li tabindex="0" aria-controls="dynamic-table" class="paginate_button "><a href="#">6</a></li>
											<li id="dynamic-table_next" tabindex="0" aria-controls="dynamic-table" class="paginate_button next"><a href="#">下一页</a></li>
										</ul>
									</div>
								</div>
				            </div>
						</div>
					</div>
				</div>
				<div class="space"></div>
				<div class="row">																									
				<hr />								     						     						     		
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div>
	<!--新增数据-->
	</div><!-- /.page-content -->
<!--盲板弹出框-->
<div class="meng mengone" id="meng" style="position: absolute; left: 0;top: 0;width: 100%;height: 100%;background-color: rgba(0,0,0,0.5);z-index: 1040;display: none;">	
<!--添加信息-->
	<div class="col-sm-7 " id="modal" style="float: none;margin:100px auto;z-index: 1001;background-color: #fff; padding: 20px; display: none;">
		<div class="container-fluid">
			<div class="modal-header" style="margin-bottom: 10px;">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  ×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               添加诊室
	            </h4>
	        </div>
			<form class="form-horizontal myform" role="form" action="add.htm" name="addRoom" id="addRoom">
			   <div class="form-group">
			      <label  class="col-sm-3 control-label">诊室名称:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="roomName" name="roomName"
			            placeholder="诊室名称">
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="lastname" class="col-sm-3 control-label">诊室IP:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="roomIp" name="roomIp"
			            placeholder="诊室IP">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-3 control-label">创建诊室时间:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="createTime" name="createTime"
			            placeholder="创建诊室时间">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-3 control-label">修改诊室时间:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="modifyTime" name="modifyTime" 
			            placeholder="修改诊室时间">
			      </div>
			   </div>
				<div class="form-group" style="display:none;">
			      <label  class="col-sm-3 control-label">隐藏id:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="id" name="id"
			            placeholder="请输入">
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-10">
			         <button type="button" class="btn btn-default btnAdd">提交</button>
			      </div>
			   </div>
			</form>
		</div>
	</div><!--添加信息-->
<!--修改信息-->
	<div class="col-sm-7 modaltwo" id="" style="float: none;margin:100px auto;z-index: 1001;background-color: #fff; padding: 20px; display: none;">
		<div class="container-fluid">
			<div class="modal-header" style="margin-bottom: 10px;">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                  ×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	               修改诊室
	            </h4>
	        </div>
			<form class="form-horizontal myforms" role="form" id="uproom"  action="update_room"  enctype="multipart/form-data"  method="post">
			  <div class="form-group">
			      <label  class="col-sm-3 control-label">诊室名称:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="roomName" name="roomName"
			            placeholder="诊室名称" >
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="lastname" class="col-sm-3 control-label">诊室IP:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="roomIp" name="roomIp" 
			            placeholder="诊室IP">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-3 control-label">创建诊室时间:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="createTime" name="createTime" 
			            placeholder="创建诊室时间">
			      </div>
			   </div>
			   <div class="form-group">
			      <label  class="col-sm-3 control-label">修改诊室时间:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="modifyTime" name="modifyTime" 
			            placeholder="修改诊室时间">
			      </div>
			   </div>
			   <div class="form-group" style="display:none;">
			      <label  class="col-sm-3 control-label">隐藏id:</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="id" name="id"
			            placeholder="请输入">
			      </div>
			   </div>
			   <div class="form-group">
			      <div class="col-sm-offset-3 col-sm-10">
			         <button type="submit" class="btn btn-default btntwo">提交</button>
			      </div>
			   </div>
			</form>
		</div>
	</div><!--信息修改-->
	<div class="col-sm-3 tooltips col-md-offset-4 text-center" style="z-index:1011;background:#FFFFFF;padding: 50px;float:none;margin:200px auto;">
		<div class="container-fluid">
			<h3 class="itsuccess">提交中
			    <div class="loading-3 pull-right">
					<i></i>
					<i></i>	
					<i></i>
					<i></i>
					<i></i>
				</div>
			</h3>
			<!--<div class="checksuccess" style="display: none;">提交成功！</div>-->
		</div>
	</div>
</div><!--蒙版-->
<!--提交后的提示信息-->
<!--<div class="mengtwo" style="position: absolute;left: 0;top: 0;width: 100%;height: 100%;z-index: 1010;background: #222222;opacity: 0.5;display: none;"></div>-->
	<script type="text/javascript">
		
		//tooltip
		$(function() {
		
			//添加
			$("#add-modal").on("click", function() {
				$("#meng").fadeIn(500);
				$("#modal").fadeIn(500);
				$(".tooltips").fadeOut();
			});
			
			
			//关闭
			$(".close").click(function() {
				$("#meng").fadeOut(500);
				$("#modal").fadeOut(500);
				$(".modaltwo").fadeOut(500);
				$(".myform input").val("");//清空input的数据
			});
			
			//修改数据
			$(".find-modal").on("click", function() {
				$(".mengone").fadeIn(500);
				$(".modaltwo").fadeIn(500);
				$(".tooltips").fadeOut();
				$(".myforms input").val("");//清空input的数据
				$.ajax({
					type : "POST",
					url : $(this).attr("openurl"),
					dataType : "json",
					success : function(data) {
						//console.info(data)
						$(".myforms :input").eq(0).val(data.roomName);
						$(".myforms :input").eq(1).val(data.roomIp);
						$(".myforms :input").eq(2).val(data.createTime);
						$(".myforms :input").eq(3).val(data.modifyTime);
						$(".myforms :input").eq(4).val(data.id);
					}
				});

			});
			var cb = function(){
						window.location.reload();
			}
			//修改信息提交
			$(".btntwo").click(function() {
				$("#uproom").ajaxSubmit(function(result) {
					if (result == 1) {
						$(".meng").fadeOut(500);
						$(".modaltwo").fadeOut(500);
						alert("操作成功！",cb);
					} else {
						alert("操作失败！");
					}

				});
				return false;
			});

			//删除数据
			$(".btn-danger").on("click", function() {
				if(confirm("确定要删除吗？")) {
					$.ajax({
						type : "GET",
						url : $(this).attr("clickurl"),
						dataType : "json",
						success : function(data) {
							window.location.reload();
						}
					});
				}
			});
			
			
			//查询按钮
			$(".mybtn").click(function() {
				$("#searchs").submit();
			});
			
			
			//添加信息btnAdd提交 请求数据
			$(".btnAdd").click(function() {
		
				$.ajax({
					cache : true,
					type : "POST",
					url : "add.htm",
					data : $('#addRoom').serialize(),
					dataType : "json",
					//data : $('#addRoom').serialize(),// 你的formid
					async : false,
					error : function(request) {
						alert("保存失败");
					},
					success : function(data) {
//						alert("testdate"+data.recordsTotal)
						if (data.recordsTotal == 1) {
							$("#modal").fadeOut();
							$("#meng").fadeOut();
							alert("操作成功！",cb);
//							window.location.reload();
						} else {
							alert("数据处理错误");
						}
					}
				});

			});
		});
		
		
	</script>
</body>
</html>
