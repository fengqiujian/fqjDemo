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
					<li class="active">岗位维护 </li>
				</ul><!-- .breadcrumb -->
			</div>
				<div class="col-xs-10">
					<div class="row">
						<div class="col-sm-5">
							<div class="form-group">
						      <label for="firstname" class="col-sm-4 control-label">岗位名称:</label>
						      <div class="col-sm-7">
						         <input type="text" class="form-control" id="gwName"  name="gwName1" value="" placeholder="岗位名称">
						      </div>
						    </div>
						</div>		
					</div>								
				</div>
				<div class="col-xs-1">
					<div class="form-group">
					  <button id="search-btn" type="button" name="search" class="btn btn-info mybtn" style="height:34px ;line-height:14px;">查询</button>
				    </div>
				</div>	
				<!-- <div class="col-xs-1">
					<div class="form-group">
					  <button type="button" id="add-modal" class="btn btn-info" style="height:34px ;line-height:14px;" data-toggle = "modal" data-target =".bs-example-modal-lg">增加</button>
				    </div>
				</div>	 -->
				
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
			   						<h3>添加岗位信息</h3>
			   					</div>
			   					
							 <form class="form-horizontal myforms" role="form" action="addOrEdit_station.json" name="activitys" id="activitys">
							    <br>
							    <div class="form-group">      
							    	<label class="col-sm-2" for="id">ID:</label>     
							    <div class="controls">
							        <input onblur="checkUser(this)" type="text"  class="input-xlarge" id="id" name="id" placeholder="ID">   
							    </div>   
							    <div id="check"  style="position:absolute;left:140px;"></div>
							    </div>
							    
							    <div class="form-group">      
							    	<label class="col-sm-2" for="gwName">岗位名称:</label>     
							    <div class="controls">
							        <input type="text"  class="input-xlarge" id="gwName" name="gwName" placeholder="岗位姓名">   
							    </div>   
							    </div>
							    <div class="form-group">      
							    	<label class="col-sm-2" for="classes">级别:</label>     
							    <div class="controls">
							        <input type="text"  class="input-xlarge" id="classes" name="classes" placeholder="级别">   
							    </div>   
							    </div>
							    
								<div class="form-group">
									&nbsp;&nbsp;&nbsp;&nbsp;<label class="checkbox-inlin"> 状态:</label>
									<label class="checkbox-inlin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								   	<label class="checkbox-inlin">
								      	<input type="radio" name="status" id="status1"  value="1" checked>有效
								   	</label>&nbsp;&nbsp;&nbsp;&nbsp;
								   	<label class="checkbox-inlin">
								      	<input type="radio" name="status" id="status0"  value="0"> 无效 
								    </label>
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
			  
				/* //添加信息btnAdd提交 请求数据
				$(".btnAdd").click(function() {
					$.ajax({
						cache : true,
						type : "POST",
						url : "/enjoyhisfy/client/stationpage/addOrEdit_station.json",
						data : $('#activitys').serialize(),
						dataType : "json",
						async : false,
						error : function(request) {
							alert("添加失败");
						},
						success : function(data) {
							if (data == 1) {
								$("#modal").fadeOut();
								$("#meng").fadeOut();
								alert("添加成功！");
								$(".myforms input").val("");//清空input的数据
								window.location.reload();
							} else {
								alert("数据处理错误");
							}
						}
					});

				}); */
				
			/* 	//关闭
				$(".close").click(function() {
					$("#meng").fadeOut(500);
					$("#modal").fadeOut(500);
					$(".modaltwo").fadeOut(500);
					$(".myforms input").val("");//清空input的数据
					window.location.reload();
				}); */
				
				//查询按钮
				$(".mybtn").click(function() {
					//刷新表格
					$('#patient-table').bootstrapTable('refresh');
					//清空input的数据
					$(".sbcondition input").val("");
				});
				
				
            });
			
			/**验证ID在数据库中是否存在*/
			/* function checkUser(object){
				var id = object.value;
				var xmlHttpRequest = $.post("/enjoyhisfy/client/stationpage/check_id.htm",{"id":id},function(data,textstatus){
					if(data==1){
						document.getElementById("check").innerHTML = "<font color='red'>当前ID已存在</font>";
						object.focus();
					}
					else{
						document.getElementById("check").innerHTML = "<font color='green'>当前ID可用</font>";
					}
				});
			} */
			
			//初始化表格
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/client/stationpage/find_List.json",
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
		    	    		[ {
				    	        field: 'id',
				    	        title: 'ID',
				    	        align: 'center'
				    	    }
				    	    ,
					    	    {
					    	        field: 'gwName',
					    	        title: '岗位名称',
					    	        align: 'center'
					    	    }/* ,
					    	    {
					    	        field: 'pym',
					    	        title: '拼音码',
					    	        align: 'center'
					    	    }
					    	    ,
					    	    {
					    	        field: 'classes',
					    	        title: '级别',
					    	        align: 'center'
					    	    } *//* ,
					   	 	    {
					    	        field: 'id',
					    	        title: '操作',
					    	        align: 'center',
				    	        	formatter:function(value,row,index){  
				    	                   var e = '<a href="#" id="edid" mce_href="#" onclick="edit(\''+ row.id + '\')" data-toggle = "modal" data-target =".bs-example-modal-lg">编辑</a> ';  
				    	                   var d = '<a href="#" mce_href="#" onclick="del(\''+ row.id +'\' ,\''+ row.status +'\',\''+ row.gwName +'\',\''+ row.classes +'\',\''+ row.pym +'\')">删除</a> '; 
				    	                        return e+d;  
				    	                    } 
					    	    } */],
		    	    pagination:true,
			    		onClickRow:function(row,tr){
			    		console.info(row.fmtBirthday);
			    	}
		    	});
			}
			
			/* //编辑
			function edit(id){
				 //修改数据
                $(".mengone").fadeIn(500);
				$(".modaltwo").fadeIn(500);
				$(".tooltips").fadeOut();
				$(".myforms input").val("");//清空input的数据
				$.ajax({
					type : "POST",
					url : "/enjoyhisfy/client/stationpage/modify_station.json",
					dataType : "json",
					data:{"id":id},  //页面
					success : function(data) {
						//console.info(data)
 						$(".myforms :input").eq(0).val(data.returndata.id);
 						$(".myforms :input").eq(1).val(data.returndata.gwName);
 						$(".myforms :input").eq(2).val(data.returndata.classes);
 						$(".myforms :input").eq(3).val(data.returndata.status);
 					
					}
				});
			}
			
			//删除
			function del(id,status,gwName,classes,pym){
				
            	 $.ajax({
                     type: "POST",
                     dataType: "json",
                     url: "/enjoyhisfy/client/stationpage/delete_station.htm",
                     data:{"id":id,
                    	 "status":status,
                    	 "gwName":gwName,
                    	 "classes":classes,
                    	 "pym":pym},
                     error : function(request) {
							alert("删除失败");
						},
						success : function(data) {
							if (data == 1) {
								$("#modal").fadeOut();
								$("#meng").fadeOut();
								alert("删除成功！");
								window.location.reload();
							} else {
								alert("数据处理错误");
							}
						}
                 });
            	
			} */
			//chaxun tiao jian 
			function queryParams(params) {
				return {
					pwName:$('#gwName').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
						
		</script>
	</body>
</html>