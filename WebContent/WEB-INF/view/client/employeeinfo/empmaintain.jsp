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
					<li class="active">人员维护</li>
				</ul>
			<!-- .breadcrumb -->
		</div>
       <form class="form-horizontal sbcondition" method="post" action="/enjoyhisfy/client/employeeinfo/empmaintain_home.htm" role="form" id="searchs">
			<div class="col-md-12">
				<div class="row">			
					<div class="col-sm-6">
						<div class="form-group">
					      <label for="employeeName" class="col-sm-4">雇员名称:</label>
					      <div class="col-sm-8">
					         <input type="text" class="form-control" id="employeeName"  name="employeeName" value="" placeholder="请输入名称">
					      </div>
					    </div>
					</div>			
					<div class="col-sm-6">
						<div class="form-group">
					      <label for="mobile" class="col-sm-4 control-label">手机号:</label>
					      <div class="col-sm-8">
					         <input type="text" class="form-control" id="mobile" name="mobile"  value="" placeholder="请输入手机号">
					      </div>
					    </div>
					</div>	
<!-- 					<div class="col-sm-6">
						<div class="form-group">
					      <label for="mobile" class="col-sm-4 control-label">手机号:</label>
					      <div class="col-sm-8">
					         <input type="text" class="form-control" id="mobile" name="mobile"  value="" placeholder="请输入手机号">
					      </div>
					    </div>
					</div>	 -->
					<br class="clear" />		
					<div class="col-sm-6">
						<div class="form-group">
					      <label for="unitCode" class="col-sm-4">院区:</label>
					      <div class="col-sm-8">
					         <select  class="form-control" id="unitCode" name="unitCode" required="required" data-id=""  url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_organiz&&column1=id&&column2=unit_name">
							</select>
					      </div>
					    </div>
					</div>
					 <div class="col-md-4" style="margin-left:95px;">
				         <div class="row">
					     <div class="form-group">
					       <div class=" col-sm-5">
						      <div class="checkbox">
						        <label>
						         <input type="checkbox"  name="serchisshow" id="isshowID" value="0" onclick="showIsNo()"/>是否属于分院
						        </label>
						      </div>
						  </div>
						  <div class=" col-sm-4" style="position:absolute;right:-60px;">
						      <button type="button" name="search" class="btn btn-block btn-info mybtn">查询</button>
						  </div>
					    </div>
					 </div>
			   </div>	
				</div>								
			</div>
		</form>
       <table id="patient-table" class="col-xs-12" ></table>
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
		/* var showID = null; */
		function showIsNo(){

			if(document.getElementById("isshowID").checked){
				document.getElementById("isshowID").value = "1";
				$('#patient-table').bootstrapTable('refresh');
            }else{
                document.getElementById("isshowID").value = "0";
                $('#patient-table').bootstrapTable('refresh');
			}
		}
		
		 var $table = $('#patient-table'), 
	         $button = $('#delete')
		
			$(function(){
				//初始化表格
			    initPatientTable();
				//关闭
				$(".close").click(function() {
					$("#meng").fadeOut(500);
					$("#modal").fadeOut(500);
					$(".modaltwo").fadeOut(500);
					$(".sbcondition input").val("");//清空input的数据
					window.location.reload();
				});
				
				 //查询按钮
				$(".mybtn").click(function() {
					//刷新表格
					$('#patient-table').bootstrapTable('refresh');
				});
            }); 
		 
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/client/employeeinfo/findEmployee.json",
		    		pagination: true,
		    		queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
		   		   	striped: true,      //是否显示行间隔色
		   		   	cache: true,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		   		   	pagination: true,     //是否显示分页（*）
		   		   	sortable: false,      //是否启用排序
		   		   	sortOrder: "asc",     //排序方式
		   		   	pageNumber:1,      //初始化加载第一页，默认第一页
		   		   	pageSize: 5,      //每页的记录行数（*）
		   		   	pageList: [3,5,10],  //可供选择的每页的行数（*）
		   		 	queryParams: queryParams,//传递参数（*）
		   		   	sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
		   		  	minimumCountColumns: 2,    //最少允许的列数
		   		   	clickToSelect: true,    //是否启用点击选中行
		   		 	contentType: "application/x-www-form-urlencoded",
		   		 	searchOnEnterKey: true,
		   		    allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
		    	    columns: 
		    	    		[
					    	    {
					    	        field: 'id',
					    	        title: 'ID',
					    	        align: 'center'
					    	    },{
					    	        field: 'employeeName',
					    	        title: '雇员名称',
					    	        align: 'center'
					    	    },{
					    	        field: 'title',
					    	        title: '人员职称',
					    	        align: 'center'
					    	    },{
					    	        field: 'depName',
					    	        title: '部门',
					    	        align: 'center',
					    	        formatter: function(value,row,index){
					    	        	if (value==null) {
											return "-";
										}
					    	        	return value;
					    	        }
					    	    },{
					    	        field: 'unitName',
					    	        title: '院区',
					    	        align: 'center',
					    	        formatter: function(value,row,index){
					    	        	if (value==null) {
											return "-";
										}
					    	        	return value;
					    	        }
					    	    },{
					    	        field: 'userTypeName',
					    	        title: '岗位',
					    	        align: 'center',
					    	        formatter: function(value,row,index){
					    	        	if (value==null) {
											return "-";
										}
					    	        	return value;
					    	        }
					    	    },{
					    	        field: 'mobile',
					    	        title: '手机',
					    	        align: 'center'
					    	    },{
					    	        field: 'roleName',
					    	        title: '系统角色',
					    	        align: 'center',
					    	        formatter:function(value,row,index){  
					    	        	if (value==null) {
											return "-";
										}
						    	        return value;
						    	    } 
					    	    },{
					    	    	field: 'isShow',
					    	        title: '是否属于分院',
					    	        align: 'center',
					    	        formatter:function(value,row,index){ 
					    	        	if (value==null) {
											return;
										}
					    	        	var ch="";
					    	        	if (value==1) {
					    	        		ch = '<input type="checkbox" checked ="checked" name="checkName" id='+value+' onclick="js_zsq(\'' + 0 + '\',\''+ row.id +'\')" />'
										} else if(value==0){
											ch = '<input type="checkbox"  name="checkName" id='+value+' onclick="js_zsq(\'' + 1 + '\',\''+ row.id +'\')" />';
										}
					    	        return ch;
		    	                    } 
					    	    }],
		    	    pagination:true
		    	});
			}
			
			//chaxun tiao jian 
			function queryParams(params) {
				return {
					serchshow: $('#isshowID').val(),
					unitCode:$('#unitCode').val(),
					mob:$('#mobile').val(),
					empName:$('#employeeName').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
			
			function js_zsq(s, id){
				$.ajax({
					cache : true,
					type : "POST",
					url : "/enjoyhisfy/client/employeeinfo/modify_show_status.json",
					/* data : $('#activitys').serialize(), */
					dataType : "json",
					async : false,
					data:{
						"id":id,
						"isShow":s
					},
					error : function(request) {
						alert("修改失败");
						return false;
					},
					success : function(data) {
						if (data == 1) {
							//$("#modal").fadeOut();
							//$("#meng").fadeOut();
							//$(".sbcondition input").val("");//清空input的数据
							//window.location.reload();
							$('#patient-table').bootstrapTable('refresh');
						} else if(data == 0) {
							alert("数据处理错误");
							return false;
						}
					}
				});
			}
			
			
			$(function(){
				//动态获取select的option
				 $("#userType").each(function(i,n){
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
				 $("#roleId").each(function(i,n){
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
				 $("#docroomId").each(function(i,n){
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
				 $("#departId").each(function(i,n){
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
				 $("#unitCode").each(function(i,n){
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
