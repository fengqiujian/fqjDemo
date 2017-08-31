<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>组维护</title>
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
					<li class="active">组添加医生 </li>
				</ul><!-- .breadcrumb -->
			</div>
              <form class="form-horizontal sbcondition" method="post" role="form" >
				<div class="col-xs-10">
					<div class="row">
						<div class="col-sm-5">
							<div class="form-group">
						      <label for="depName" class="col-sm-4 control-label">组名称:</label>
						      <div class="col-sm-7">
						      	<select  class="form-control" id="groupName2" name="groupName2" required="required" data-id=""  
					         		url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4Info.json?table=his_group&&column1=id&&column2=group_name&&sqlstr={'where':'status=1 AND  group_name IS NOT NULL GROUP BY group_name'}">
								</select>
						      </div>
						    </div>
						</div>
						
						<div class="col-sm-5">
						<div class="form-group">
					      <label for="employeeName" class="col-sm-6 control-label">人员名称:</label>
					      <div class="col-sm-6">
					      	<select  class="form-control" id="employee_id1" name="employee_id1" required="required" data-id=""  
							    url="/enjoyhisfy/client/framework/FrameworkCommonAction/query4DropDown.json?table=his_employee&&column1=id&&column2=employee_name">
							</select>
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
			
			
				//查询按钮
				$(".mybtn").click(function() {
					
					//刷新表格
					$('#patient-table').bootstrapTable('refresh');
					//清空input的数据
					$(".sbcondition input").val("");
				});
            });
			
			
			
			//初始化表格
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/client/employeegroup/find_List.json",
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
		    	    		[ {
				    	        field: 'id',
				    	        title: 'ID',
				    	        align: 'center'
				    	    },{
		    	        field: 'groupId',
		    	        title: '组名',
		    	        align: 'center',
		    	        formatter: function(value,row,index){
		    	        	var groupName = ""
	    	        	     $.ajax({ url: "/enjoyhisfy/client/groupmanager/findEntityInfo.json?id="+value, 
    	        	    		 async :false, 
    	        	    		 success: function(data){
		    	        			if(data!=null){
		    	        				groupName = data.returndata.groupName;
			    	        		}
	    	        	      }});
		    	        	return groupName;
		    	        }
		    	    },{
		    	        field: 'employeeId',
		    	        title: '人员名称',
		    	        align: 'center',
	    	        	formatter: function(value,row,index){
		    	        	var eName = ""
	    	        	     $.ajax({ url: "/enjoyhisfy/client/employeeinfo/modify_info.json?id="+value, 
    	        	    		 async :false, 
    	        	    		 success: function(data){
		    	        			if(data!=null){
		    	        				eName = data.returndata.employeeName;
			    	        		}
	    	        	      }});
		    	        	return eName;
		    	        }
		    	    }],
   	    pagination:true,
  		onClickRow:function(row,tr){
   		console.info(row.fmtBirthday);
			    	}
		    	});
			}
			
			
			
			//chaxun tiao jian 
			function queryParams(params) {
				return {
					gName:$('#groupName2').val(),
					eId:$('#employee_id1').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
			
			
			$(function(){
				//动态获取select的option docroomId
				 $("#groupName2").each(function(i,n){
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
			});
			$(function(){
				//动态获取select的option docroomId
				 $("#groupName1").each(function(i,n){
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
			});
			$(function(){
				//动态获取select的option docroomId
				 $("#unitId").each(function(i,n){
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
			});	
			
			
			$(function(){
				//动态获取select的option docroomId
				 $("#employee_id1").each(function(i,n){
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
			});
		</script>
	</body>
</html>