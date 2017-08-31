<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>患者来源</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	</head>
	<body>
		<div class="container-fluid">
	        <div class="modal-content">
	            <!--患者table -->				
	            <div class="modal-body">
	            <div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript"> try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){} </script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a class="text-dection" href="#">首页</a>
					</li>
					<li class="active"><a href="#">后台系统管理</a></li>
					<li class="active">患者来源 </li>
				</ul>
			<!-- .breadcrumb -->
		        </div>
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
		 var $table = $('#patient-table'), 
	         $button = $('#delete')
			$search=$("#search");
			$(function(){
				//初始化表格
			    initPatientTable();
            });
				
			function initPatientTable(){
				$('#patient-table').bootstrapTable({
		    		method: 'post',
		    		url: "/enjoyhisfy/client/patientSource/findAllRow.json",
		    		pagination: true,
		    		queryParamsType:'',	//默认值为 'limit' ,在默认情况下 传给服务端的值为：offset  limit sort
		    							// 设置为 ''  在这种情况下传给服务器的值为：pageSize  pageNumber
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
					    	        field: 'employeeName',
					    	        title: '日期',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'departId',
					    	        title: '来源',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'docroomId',
					    	        title: '患者',
					    	        align: 'center'
					    	    }
					    	    ,
					    	    {
					    	        field: 'userType',
					    	        title: '医生',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'islogin',
					    	        title: '处置单金额',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'mobile',
					    	        title: '优惠金额',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'password',
					    	        title: '实收金额',
					    	        align: 'center'
					    	    },
					    	    {
					    	        field: 'roleId',
					    	        title: '欠费金额',
					    	        align: 'center'
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
					mobile:$('#mobile').val(),
					employeeName:$('#employeeName').val(),
					pageSize: params.pageSize,
					pageNumber: params.pageNumber,
				};
			}
						
		</script>
	</body>
</html>
