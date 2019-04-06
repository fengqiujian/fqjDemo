<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>非当日扎帐解锁</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	</head>
	<body>
		
       <div class="modal-body">
         <div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript"> try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){} </script>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home home-icon"></i>
						<a href="#">首页</a>
					</li>
					<li class="active"><a href="#">后台系统管理</a></li>
					<li class="active">非当日扎帐解锁 </li>
				</ul>
			</div>
		   		<form class="form-horizontal sbcondition" method="post" role="form" > 
				  <div class="btn-group-vertical">
				  <div class="col-md-12">
				  <div class="row">	
					  <div class="col-sm-4">	
					  	<button onclick="sychData('99')" class="btn btn-primary" type="button" style="height:54px ;width: 280px;line-height:14px;" data-toggle = "modal" data-target =".bs-example-modal-lg">非当日扎帐解锁</button><br/>
					  </div>
				  </div>
				  </div>
					</div>
		 	   </form>
           <table id="patient-table" class="col-xs-12" > </table>
         </div>
		<script src="${path}/js/jquery-2.1.4.min.js"></script>
		<script src="${path}/js/bootstrap.min.js"></script>		
		<script src="${path}/assets/js/bootstrap-table.min.js"></script>
		<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
		<script>
		 function sychData(type){
			 $.ajax({
                 type: "POST",
                 dataType: "json",
                 url: "/enjoyhisfy/client/datasynchronization/synch.htm?type="+type,
                 
                  error : function(request) {
						alert("同步失败");
						return false;
					}, 
					success : function(data) {
						if (data == 1) {
							$("#modal").fadeOut();
							$("#meng").fadeOut();
							alert("解锁成功！",cb);
//							window.location.reload();
						} else {
							alert("解锁失败");
							return false;
						}
					}
             });
		 }
		 var cb = function(){
			 window.location.reload();
		 }
		</script>
	</body>
</html>