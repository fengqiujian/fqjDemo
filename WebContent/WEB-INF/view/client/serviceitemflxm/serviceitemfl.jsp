<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>诊室配置</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	</head>
	<body>
		<div class="container">
           <div class="modal-content">
              	<!--患者table -->				
               <div class="modal-body">
               <div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript"> try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){} </script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="#">首页</a>
					</li>
					<li class="active"><a href="#">后台系统管理</a></li>
					<li class="active">二级处置项 </li>
				</ul><!-- .breadcrumb -->
			</div>
			<form class="form-horizontal sbcondition" method="post" role="form" > 
				<div class="col-xs-10">
					<div class="row">
						
						<div class="col-sm-6">
							<div class="form-group">
						      	<label for="itemName" class="col-sm-5 control-label">二级处置项名称:</label>
						      <div class="col-sm-7">
						         <input type="text" class="form-control" id="itemName"  name="itemName" value="" placeholder="科目名称">
						      </div>
						    </div>
						</div>
						
						<div class="form-group">      
						    	<label class="col-sm-2 control-label" for="parentId">一级处置项:</label>     
						    <div class="controls">
						        <select style="height:30px" class="select-xlarge" id="condition" name="parentId" required="required" data-id=""  url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_service_item&&column1=item_code&&column2=item_name">
								</select>   
						    </div>   
					    </div>	
					    		
					</div>								
				</div>
				<div class="col-xs-1">
					<div class="form-group">
					  <button id="search-btn" type="button" name="search" class="btn btn-info mybtn" style="height:34px ;line-height:14px;">查询</button>
				    </div>
				</div>	
				<div class="col-xs-1">
					<div class="form-group">
					  <button type="button" id="add-modal" class="btn btn-info" style="height:34px ;line-height:14px;" data-toggle = "modal" data-target =".bs-example-modal-lg">增加</button>
				    </div>
				</div>	
 			</form>
 			
               <form class="form-horizontal sbcondition" method="post" role="form" > 
				<div class="col-xs-10">
					<div class="row">
						
						<div class="col-sm-5">
							<div class="form-group">
						      	<label for="itemName" class="col-sm-4 control-label">科目名称:</label>
						      <div class="col-sm-7">
						         <input type="text" class="form-control" id="itemName"  name="itemName" value="" placeholder="岗位名称">
						      </div>
						    </div>
						</div>
						
						<div class="form-group">      
						    	<label class="col-sm-2 control-label" for="parentId">收费科目:</label>     
						    <div class="controls">
						        <select style="height:30px" class="select-xlarge" id="condition" name="parentId" required="required" data-id=""  url="/enjoyhisbj/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_service_item&&column1=item_code&&column2=item_name">
								</select>   
						    </div>   
					    </div>	
					    		
					</div>								
				</div>
				<div class="col-xs-1">
					<div class="form-group">
					  <button id="search-btn" type="button" name="search" class="btn btn-info mybtn" style="height:34px ;line-height:14px;">查询</button>
				    </div>
				</div>	
				<div class="col-xs-1">
					<div class="form-group">
					  <button type="button" id="add-modal" class="btn btn-info" style="height:34px ;line-height:14px;" data-toggle = "modal" data-target =".bs-example-modal-lg">增加</button>
				    </div>
				</div>	
 			</form>
				
				<!--添加信息-->
				<!--选择处置项 模态框（Modal） -->
				<div class="modal bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true" data-backdrop="static">
			      	<div class="modal-dialog modal-lg">
			      		<div class="modal-content">
			      			<div class="col-sm-12" style="z-index: 100;margin-bottom: 10px;">				 								   			
			   			<!--table START-->
				   			<div class="col-sm-12" style="background-color: #fff;">		   				
			   					<div class="container-fluid text-center">
			   					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					                  ×
					            </button>
			   						<h3>添加处置项信息</h3>
			   					</div>
			   					
							 <form class="form-horizontal myforms" role="form" name="activitys" id="activitys">
							    <br>
							    <div class="form-group">      
							    	<label class="col-sm-2 text-right" for="itemCode">ITEMCODE:</label>     
							    <div class="col-sm-4">
							        <input type="text"  onblur="checkUser(this)"  class="form-control" id="itemCode" name="itemCode" placeholder="itemCode">   
							    </div>   
							    <div id="check"  class="form-control" style="height:30px;border:0px"></div>
							    </div>
							    <div class="form-group">      
							    	<label class="col-sm-2 text-right" for="itemName">处置名称:</label>     
							    <div class="col-sm-4">
							        <input type="text"  class="form-control" id="itemName" name="itemName" placeholder="处置名称">   
							    </div>   
							    </div>
							    
							   
							   <div class="form-group">      
								    	<label class="col-sm-2 text-right" for="parentId">收费科目:</label>     
								    <div class="col-sm-4">
								        <select  class="form-control" id="parentId" name="parentId" required="required" data-id=""  url="/enjoyhisbj/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_service_item&&column1=item_code&&column2=item_name">
										</select>   
								    </div>   
							    </div>							    
							    <div class="form-group">   
							    	<label class="col-sm-2 text-right" for="hiscode">HIS码:</label>     
							    <div class="col-sm-4">   
							       <input type="text" class="form-control" id="hiscode" name="hiscode" placeholder="HIS码">      
							    </div>   
							    </div>
							   
								<div class="form-group">
									<label class="col-sm-2 text-right"> 状态:</label>
									<label class="checkbox-inlin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								   	<label class="checkbox-inlin">
								      	<input type="radio" name="status" id="status1"  value="1" checked>有效
								   	</label>&nbsp;&nbsp;&nbsp;&nbsp;
								   	<label class="checkbox-inlin">
								      	<input type="radio" name="status" id="status0"  value="0"> 无效 
								    </label>
								</div>
								
								<div class="form-group" style="display:none;">
							      <label for="createTime" class="col-sm-3 control-label">隐藏createTime:</label>
							      <div class="col-sm-5">
							         <input type="text" class="form-control" id="createTime" name="createTime"
							            placeholder="请输入">
							      </div>
							   </div>
							   
							   <div class="form-group" style="display:none;">
							      <label for="modifyTime" class="col-sm-3 control-label">隐藏modifyTime:</label>
							      <div class="col-sm-5">
							         <input type="text" class="form-control" id="modifyTime" name="modifyTime"
							            placeholder="请输入">
							      </div>
							   </div>
							   	<br class="clear" />
							   	<div class="modal-footer">
					          		<button type="button" id="btn_submit" class="btn btn-primary btnAdd" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
						        </div>
						        <!-- 
						        <br class="clear" />
						        <div class="modal-footer">
						            <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
						        </div>
						         -->
							</form>
							
				   			</div>		   			
				        	</div>
			      		</div>
			      	</div>
				</div> 
				
				<!--信息修改-->
               <table id="patient-table" class="col-xs-12" > </table>
             </div>
         </div><!-- /.modal-content -->
		</div>	
		<script src="${path}/js/jquery-2.1.4.min.js"></script>
		<script src="${path}/js/bootstrap.min.js"></script>		
		<script src="${path}/assets/js/bootstrap-table.min.js"></script>
		<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
		<script>
		 var $table = $('#patient-table'), 
			$search=$("#search");
			$(function(){
				//初始化表格
			    initPatientTable();
			  //添加
				$("#add-modal").on("click", function() {
					$("#meng").fadeIn(500);
					$("#modal").fadeIn(500);
					$(".tooltips").fadeOut();
				});
			  
				//添加信息btnAdd提交 请求数据
				$(".btnAdd").click(function() {
					$.ajax({
						cache : true,
						type : "POST",
						url : "/enjoyhisbj/client/serviceitemflxm/add_ItemFl.json",
						data : $('#activitys').serialize(),
						dataType : "json",
						async : false,
						error : function(request) {
							alert("添加失败");
							return false;
						},
						success : function(data) {
							if (data == 1) {
								$("#modal").fadeOut();
								$("#meng").fadeOut();
								alert("添加成功！",cb);
								//$(".myforms input").val("");//清空input的数据
								//window.location.reload();
							} else {
								alert("数据处理错误");
								return false;
							}
						}
					});

				});
				var cb = function(){
					$(".myforms input").val("");//清空input的数据
					window.location.reload();
				}
				//关闭
				$(".close").click(function() {
					$("#meng").fadeOut(500);
					$("#modal").fadeOut(500);
					$(".modaltwo").fadeOut(500);
					$(".myforms input").val("");//清空input的数据
					window.location.reload();
				});
				
				//查询按钮
				$(".mybtn").click(function() {
					//刷新表格
					$('#patient-table').bootstrapTable('refresh');
					//清空input的数据
					$(".sbcondition input").val("");
				});
				
				
            });
			
			/**验证ID在数据库中是否存在*/
			function checkUser(object){
				var id = object.value;
				var xmlHttpRequest = $.post("/enjoyhisbj/client/serviceitemflxm/check_item_id.htm",{"id":id},
					function(data,textstatus){
						if(data==1){
							document.getElementById("check").innerHTML = "<font color='red'>当前ID已存在</font>";
							object.focus();
						}
					else{
						document.getElementById("check").innerHTML = "<font color='green'>当前ID可用</font>";
					}
				});
				
				
			}
			
			//初始化表格
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisbj/client/serviceitemflxm/find_fl_List.json",
		    		pagination: true,
		    		queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
		   		   	striped: true,      //是否显示行间隔色
		   		   	cache: true,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		   		   	pagination: true,     //是否显示分页（*）
		   		   	sortable: false,      //是否启用排序
		   		   	sortOrder: "asc",     //排序方式
		   		   	pageNumber:1,      //初始化加载第一页，默认第一页
		   		   	pageSize: 5,      //每页的记录行数（*）
		   		   	pageList: [5,10],  //可供选择的每页的行数（*）
		   		 	queryParams: queryParams,//传递参数（*）
		   		   	sidePagination:  "server",   //分页方式：client客户端分页，server服务端分页（*）
		   		  	minimumCountColumns: 2,    //最少允许的列数
		   		   	clickToSelect: true,    //是否启用点击选中行
		   		 	contentType: "application/x-www-form-urlencoded",
		   		 	searchOnEnterKey: true,
		    	    columns: 
		    	    		[ 
					    	     {
					    	        field: 'itemCode',
					    	        title: '收费科目编码',
					    	        align: 'center'
					    	    },{
					    	        field: 'itemName',
					    	        title: '收费科目名称',
					    	        align: 'center'
					    	    },{
					    	        field: 'pym',
					    	        title: '拼音码',
					    	        align: 'center'
					    	    },{
					    	        field: 'itemParentName',
					    	        title: '收费科目',
					    	        align: 'center'
					    	    },{
					    	        field: 'hiscode',
					    	        title: 'HIS码',
					    	        align: 'center'
					    	    },{
					    	        field: 'itemCode',
					    	        title: '操作',
					    	        align: 'center',
				    	        	formatter:function(value,row,index){  
				    	                   var e = '<a href="#" id="edid" mce_href="#" onclick="edit(\''+ row.itemCode + '\')" data-toggle = "modal" data-target =".bs-example-modal-lg">编辑</a> ';  
				    	                   var d = '<a href="#" mce_href="#" onclick="del(\''+ row.itemCode +'\' ,\''+ row.status +'\',\''+ row.itemName +'\',\''+ row.hiscode +'\',\''+ row.parentId +'\' ,\''+ row.createTime +'\' ,\''+ row.modifyTime +'\')">删除</a> '; 
				    	                        return e+d;  
				    	                    } 
					    	    }],
		    	    pagination:true,
			    		onClickRow:function(row,tr){
			    		console.info(row.fmtBirthday);
			    	}
		    	});
			}
			
			//编辑
			function edit(itemCode){
                $(".mengone").fadeIn(500);
				$(".modaltwo").fadeIn(500);
				$(".tooltips").fadeOut();
				$(".myforms input").val("");//清空input的数据
				$.ajax({
					type : "POST",
					url : "/enjoyhisbj/client/serviceitemflxm/echos_ItemFl.json",
					dataType : "json",
					data:{"itemCode":itemCode},  //页面
					success : function(data) {
						//console.info(data)
 						$(".myforms :input").eq(0).val(data.returndata.itemCode);
 						$(".myforms :input").eq(1).val(data.returndata.itemName);
 						$(".myforms :input").eq(2).val(data.returndata.parentId);
 						$(".myforms :input").eq(3).val(data.returndata.hiscode);
 						$(".myforms :input").eq(4).val(data.returndata.status);
					}
				});
			}
			
			//删除
			function del(itemCode,status,itemName,hiscode,parentId,createTime,modifyTime){
            	 $.ajax({
                     type: "POST",
                     dataType: "json",
                     url:  "/enjoyhisbj/client/serviceitemflxm/delete_item_fl.htm",
                     data:{"itemCode":itemCode,
                    	 "status":status,
                    	 "itemName":itemName,
                    	 "hiscode":hiscode,
                    	 "parentId":parentId,
                    	 "createTime":createTime,
                    	 "modifyTime":modifyTime},
                    	 
                     error : function(request) {
							alert("删除失败");
							return false;
						},
						success : function(data) {
							if (data == 1) {
								$("#modal").fadeOut();
								$("#meng").fadeOut();
								alert("删除成功！",cb);
								//window.location.reload();
							} else {
								alert("数据处理错误");
								return false;
							}
						}
                 });
            	
			}
			//chaxun tiao jian 
			function queryParams(params) {
				return {
					iNames:$('#itemName').val(),
					charges:$('#condition').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
			
			$(function(){
				//动态获取select的option
				 $("#parentId").each(function(i,n){
	                var url = $('#'+n.id).attr('url');
	                if(url!=undefined){
		                var selectedId = $('#'+n.id).attr('data-id');
		                $.ajax({
				            url : url,
				            type : 'post',
				            async: false,
				            dataType : 'json',
				            success : function(data) {
				            	var tempAjax = "";
				            	tempAjax += "<option value=''>--------请选择--------</option>";
				                $.each(data.returndata,function(i,n){
				                    if(n.id==selectedId)
					                    tempAjax += "<option value='"+n.id+"' selected='selected'>"+n.name+"</option>";
				                    else
					                    tempAjax += "<option value='"+n.id+"'>"+n.name+"</option>";
				                });
				                $("#"+n.id).empty();
				                $("#"+n.id).append(tempAjax);
				            }
				        });
	                }
	            });
			})	
			
			$(function(){
				//动态获取select的option
				 $("#condition").each(function(i,n){
	                var url = $('#'+n.id).attr('url');
	                if(url!=undefined){
		                var selectedId = $('#'+n.id).attr('data-id');
		                $.ajax({
				            url : url,
				            type : 'post',
				            async: false,
				            dataType : 'json',
				            success : function(data) {
				            	var tempAjax = "";
				            	tempAjax += "<option value=''>--------请选择--------</option>";
				                $.each(data.returndata,function(i,n){
				                    if(n.id==selectedId)
					                    tempAjax += "<option value='"+n.id+"' selected='selected'>"+n.name+"</option>";
				                    else
					                    tempAjax += "<option value='"+n.id+"'>"+n.name+"</option>";
				                });
				                $("#"+n.id).empty();
				                $("#"+n.id).append(tempAjax);
				            }
				        });
	                }
	            });
			})
			
			
		</script>
	</body>
</html>