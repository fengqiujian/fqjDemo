<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>首页</title>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${path}/css/his.css"/>
	</head>
 <body style="width: 98%;">
	
	
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<div class="col-sm-12" id="my">		
						<form class="form-horizontal" method="get" role="form" id="searchs">
							<div class="col-xs-10">
								<div class="row">
									
									<div class="col-sm-12">
										<div class="form-group">
									      <label for="firstname" class="col-sm-12 control-label"  style="font-size:35px;">ENJOYHIS - ${sessionScope.title_name.unitName} - 分院后台管理系统</label>
									    </div>
									</div>			
								</div>								
							</div>
					    </form>
					</div><!-- /.page-header -->
					</div>
				</div>
				<hr />								     						     						     		
				<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div>
	<!--新增数据-->
	</div><!-- /.page-content -->

		
	

		<script src="${path}/assets/js/jquery.js"></script>
		<script src="${path}/assets/js/bootstrap.js"></script>
		<script src="${path}/assets/js/jquery-ui.custom.js"></script>
		<script src="${path}/assets/js/jquery.hotkeys.js"></script>
		<script src="${path}/assets/js/bootstrap-wysiwyg.js"></script>
		<script src="${path}/assets/js/bootbox.js"></script>
		<script src="${path}/assets/js/bootstrap-table.min.js"></script>
		<script src="${path}/assets/js/bootstrap-table-zh-CN.min.js"></script>
		<script src="../../assets/js/bootbox.js"></script>
	<script src="../../assets/js/alert.js"></script>
	<script>
	
	</script>
  </body>
</html>
