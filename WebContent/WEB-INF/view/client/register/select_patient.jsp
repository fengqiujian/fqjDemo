<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>挂号</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	</head>
<body>
		<div class="container-fluid">
			<div class="row">
			   	<div class="col-sm-10"></div>
			</div>
		</div>	
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="smaller lighter blue no-margin"><span id="">选择患者</span></h3>
                </div>
               	<!--患者table -->				
                <div class="modal-body">
                    <table id="patient-table" class="col-xs-12" ></table>
                </div>
            </div><!-- /.modal-content -->
				
		<script src="${path}/js/jquery-2.1.4.min.js"></script>
		<script src="${path}/js/bootstrap.min.js"></script>		
		<script src="${path}/assets/js/bootstrap-table.min.js"></script>
		<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
		<script>
			$(function(){
				//初始化表格
			    initPatientTable();
            });
				
			function initPatientTable(){
// 				$('#patient-table').bootstrapTable("destroy");
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/client/register/find_patient.json",
		    		pagination: true,
		    		queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
		    							// 设置为 ''  在这种情况下传给服务器的值为：pageSize  pageNumber
//		    		   	toolbar: '#toolbar',    //工具按钮用哪个容器
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
//								{
//					    	        field: 'id',
//					    	        title: '',
//					    	        align: 'center',
//					    	        formatter: function(value,row,index){
//					    	        	return '<label class="pos-rel"><input id='+value+' type="checkbox" class="ace"/><span class="lbl"></span></label>';
//					   	        	}
//					   			},
					   	 	    {
					    	        field: 'patName',
					    	        title: '患者姓名',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'mobile',
					    	        title: '患者电话',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'patNo',
					    	        title: '病历编号',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'userSex',
					    	        title: '性别',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'age',
					    	        title: '年龄',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'docName',
					    	        title: '初诊医生',
					    	        align: 'center'
					    	       
					    	    }
					    	    
				   			],
		    	    pagination:true,
			    	onClickRow:function(row,tr){
			    		console.info(row.fmtBirthday);
			    		
			    	}
		    	});
			}
			function queryParams(params) {
				return {
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};

			}
			
			
		</script>
	</body>
</html>
