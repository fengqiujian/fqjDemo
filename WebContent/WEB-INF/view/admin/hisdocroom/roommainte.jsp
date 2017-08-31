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
         <div class="modal-body">
             <div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript"> try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){} </script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="#">首页</a>
					</li>
					<li class="active"><a href="#">后台系统管理</a></li>
					<li class="active">诊室配置</li>
				</ul><!-- .breadcrumb -->
		 	  </div>
              <form class="form-horizontal sbcondition" method="post" role="form" id="searchs">
				<div class="col-xs-10">
					<div class="row">
						<div class="col-sm-5">
							<div class="form-group">
						      <label  class="col-sm-4 control-label">诊室名称:</label>
						      <div class="col-sm-7">
						         <input type="text" class="form-control" id="roomName"  name="roomName" value=""
						            placeholder="诊室名称">
						      </div>
						    </div>
						</div>		
						<div class="col-sm-5">
							<div class="form-group">
						      <label  class="col-sm-4 control-label">诊室IP:</label>
						      <div class="col-sm-7">
						         <input type="text" class="form-control" id="roomIp" name="roomIp"
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
				<div class="col-xs-1">
					<div class="form-group">
					  <button type="button" id="add-modal" class="btn btn-info mybtn" style="height:34px ;line-height:14px;" data-toggle = "modal" data-target =".bs-example-modal-lg">增加</button>
				    </div>
				</div>	
			 </form>
			<div class="modal bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mylargeModalLabel" aria-hidden="true" data-backdrop="static">
		      	<div class="modal-dialog modal-lg">
		      		<div class="modal-content">
		      			<div class="col-sm-12" style="z-index: 100;margin-bottom: 10px;">				 								   			
			   				<div class="col-sm-12" style="background-color: #fff;">		   				
		   						<div class="container-fluid text-center">
		   						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				        	          ×
				        	    </button>
		   							<h3>添加诊室</h3>
		   						</div>
							 <form class="form-horizontal myforms" role="form" action="addOrEdit_station.json" name="activitys" id="activitys">
							    <br>
				     			<div class="form-group">
							    	<label class="col-sm-2 text-right" for="roomName">诊室名称:</label>     
							    <div class="col-sm-4">
							        <input type="text" onblur="checkRoom(this)"   class="form-control" id="roomName" name="roomName" value="" placeholder="诊室名称">   
							    </div>   
							    <div id="checkroom" ></div>
							    </div>
							    
							    <div class="form-group">      
							    	<label class="col-sm-2 text-right" for="roomIp">诊室IP:</label>     
							    <div class="col-sm-4">
							        <input type="text" onblur="checkIP(this)"  class="form-control" id="roomIp" name="roomIp" placeholder="诊室IP">   
							    </div>   
							    <div id="check" ></div>
							    </div>
								
								<div class="form-group" style="display:none;">
								    <label for="id" class="col-sm-3 control-label">隐藏mid:</label>
								<div class="col-sm-5">
								    <input type="text" class="form-control" id="id" name="id"
								            placeholder="请输入">
								</div>
								</div>
								<br class="clear" />
								<div class="modal-footer">
					    	      	<button type="button" id="btn_submit" class="btn btn-primary btnAdd" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
							    </div>
							</form>
			   				</div>		   			
			   	    	</div>
		      		</div>
		      	</div>
			</div> 
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
	
	function checkRoom(object){
		var rn = object.value; 
		if (rn==null) {
			alert("该诊室不能为空！");
		}else {
			$.ajax({
	             type: "POST",
	             dataType: "json",
	             url: "/enjoyhisfy/admin/hisdocroom/check_room_ip.json",
	             data:{"roomName":rn},
	             error : function(request) {
						alert("校验失败");
					},
					success : function(data) {
						if (data == 1) {
							$("#modal").fadeOut();
							$("#meng").fadeOut();
							alert("该诊室已存在！");
							 $(".myforms input").val(""); 
						}else if (data==2) {
							alert("该诊室不能为空！");
						}else if (data==3) {
							document.getElementById("checkroom").innerHTML = "<font color='green'>该诊室可用！</font>";
						}
					}
	         });
		}
	}
	
	//IP校验
	//判断ip地址的合法性
	function checkIP(object){
		var ip = object.value;
	    var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	    var reg = ip.match(exp);
	   if(reg==null){
	    	/* document.getElementById("check").innerHTML = "<font color='red'>该IP不合法！</font>"; */
	    	alert("该IP不合法！");
	    	$(".myforms input").val("");
	    } else {
	    	//合法就发请求校验，如果存在返回0，不在返回1
	    	$.ajax({
	             type: "POST",
	             dataType: "json",
	             url: "/enjoyhisfy/admin/hisdocroom/check_room_ip.json",
	             data:{"roomIp":ip},
	             error : function(request) {
						alert("校验失败");
					},
					success : function(data) {
						if (data == 1) {
							$("#modal").fadeOut();
							$("#meng").fadeOut();
							/* document.getElementById("check").innerHTML = "<font color='red'>该IP已存在！</font>"; */
							alert("该IP已存在！");
							 $(".myforms input").val(""); 
						}else if (data==2) {
							alert("该IP不能为空！");
						}else if (data==3) {
							document.getElementById("check").innerHTML = "<font color='green'>该IP可用！</font>";
						}
					}
	         });
		}
	}
	
	//删除
	function del(id,roomName,roomIp){
    	 $.ajax({
             type: "POST",
             dataType: "json",
             url: "/enjoyhisfy/admin/hisdocroom/delete_room.json",
             data:{"id":id
            	 ,"roomName":roomName
            	 ,"roomIp":roomIp
            	},
             error : function(request) {
					alert("删除失败");
				},
				success : function(data) {
					if (data == 1) {
						$("#modal").fadeOut();
						$("#meng").fadeOut();
						alert("删除成功！",cb);
						
					} else {
						alert("数据处理错误");
					}
				}
         });
    	
	}
	var cb = function(){
		window.location.reload();
	}
	//编辑
	function edit(id){
		 //修改数据
        $(".mengone").fadeIn(500);
		$(".modaltwo").fadeIn(500);
		$(".tooltips").fadeOut();
		$(".myforms input").val("");//清空input的数据
		$.ajax({
			type : "POST",
			url : "/enjoyhisfy/admin/hisdocroom/modify_room.json",
			dataType : "json",
			data:{"id":id},  //页面
			success : function(data) {
				//console.info(data)
				$(".myforms :input").eq(0).val(data.returndata.roomName);
				$(".myforms :input").eq(1).val(data.returndata.roomIp);
				$(".myforms :input").eq(2).val(data.returndata.id);
			}
		});
	}
	
	//添加信息btnAdd提交 请求数据
	$(".btnAdd").click(function() {
		var roomN = $('#activitys #roomName').val();
		var roomP = $('#activitys #roomIp').val();
		
		if (roomN==''||roomN==null) {
			alert("诊室不能为空！")
			return;
		}if (roomP==''||roomP==null) {
			alert("IP不能为空");
			return;
		}
		
		$.ajax({
			cache : true,
			type : "POST",
			url : "add.htm",
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
					$(".myforms input").val("");//清空input的数据
					alert("添加成功！",cb);
//					window.location.reload();
				} else {
					alert("数据处理错误");
				}
			}
		});

	});
	
	
	
		 var $table = $('#patient-table'), 
	         $button = $('#delete'),
			$search=$("#search");
			$edit=$("#edit");
		 
			$(function(){
				//初始化表格
			    initPatientTable();
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
					$(".myforms input").val("");//清空input的数据
					window.location.reload();
				});
				
				//查询按钮
				$(".mybtn").click(function() {
					//刷新表格
					$('#patient-table').bootstrapTable('refresh');
					$(".sbcondition input").val("");//清空input的数据
				});
            });
				
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/admin/hisdocroom/find_docroom.json",
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
		   		   	sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
		   		  	minimumCountColumns: 2,    //最少允许的列数
		   		   	clickToSelect: true,    //是否启用点击选中行
		   		 	contentType: "application/x-www-form-urlencoded",
		   		 	searchOnEnterKey: true,
		    	    columns: 
		    	    		[ 
					   	 	    
					    	    {
					    	        field: 'roomIp',
					    	        title: '诊室IP',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'roomName',
					    	        title: '诊室名称',
					    	        align: 'center'
					    	    },{
					    	        field: 'id',
					    	        title: '操作',
					    	        align: 'center',
					    	        formatter:function(value,row,index){  
				    	                   var e = '<a href="#" id="edid" mce_href="#" onclick="edit(\''+ row.id + '\')" data-toggle = "modal" data-target =".bs-example-modal-lg">编辑</a> ';  
				    	                   var d = '<a href="#" mce_href="#" onclick="del(\''+ row.id +'\',\''+ row.roomName +'\',\''+ row.roomIp +'\')">删除</a> '; 
				    	                        return e+d;  
				    	                    }
					    	    }
					    	    
				   			],
		    	    pagination:true,
			    		onClickRow:function(row,tr){
			    		console.info(row.fmtBirthday);
			    	}
		    	});
			}
			
			//Query conditions
			function queryParams(params) {
				return {
 					rName:$('#roomName').val(),
					rIp:$('#roomIp').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
						
		</script>
	</body>
</html>