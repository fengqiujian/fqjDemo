<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title> 管理系统</title>
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="../main/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="../main/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="../main/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="../main/assets/css/ace.min.css" id="main-ace-style" />
		<link rel="stylesheet" href="../main/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="../main/assets/css/ace-rtl.min.css" />
		<script src="../main/assets/js/ace-extra.min.js"></script>
		<script src="../main/mainPlug1.js"></script>
		<script src="../main/mainPlug2.js"></script>
		<script src="../main/assets/js/bootstrap.min.js"></script>
		<script src="../main/assets/js/ace-elements.min.js"></script>
		<script src="../main/assets/js/ace.min.js"></script>
		<script src="../assets/js/bootbox.js"></script>
	<script src="../assets/js/alert.js"></script>
		<script src="../main/sidebar-menu.js"></script>
		<script src="../main/main.js"></script>
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container" style="padding-right: 0px;">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							后台管理系统
						</small>
					</a>
				</div>
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<!--<img class="nav-user-photo" src="${path}/assets/avatars/user.jpg" alt="Jason's Photo" />-->
								<span class="user-info">
									<small>欢迎您,</small>
									 ${sessionScope.login_name.employeeName }
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>
							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="ace-icon fa fa-user"></i>
										回首页
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="/enjoyhisfy/login/log_out.htm">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main-container" id="main-container">
			

			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive">
				<ul class="nav nav-list" id="menuDemo">
				</ul>
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
			<div class="main-content tabbox">
				<iframe src="/enjoyhisfy/client/homepange/homes.htm" name="cframe" id="cframe" width="100%" 
				 marginheight="0" marginwidth="0" frameborder="0" height="507px">				
				</iframe>
			</div>
		</div>
	</body>
</html>