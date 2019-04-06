<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>汇河消息中间件 管理系统</title>
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
		<script src="../assets/js/bootbox.js"></script>
	<script src="../assets/js/alert.js"></script>
		<script src="../main/assets/js/ace-elements.min.js"></script>
		<script src="../main/assets/js/ace.min.js"></script>
		<script src="../main/sidebar-menu.js"></script>
		<script src="../main/main.js"></script>
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">
			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							消息中间件 管理系统
						</small>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info">
									<small>Welcome,</small>
									Jason
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										Settings
									</a>
								</li>
								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										Profile
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="#">
										<i class="ace-icon fa fa-power-off"></i>
										Logout
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
				<iframe src="../admin/login.htm" name="cframe" id="cframe" width="100%" 
				 marginheight="0" marginwidth="0" frameborder="0" height="507px">				
				</iframe>
			</div>
			
			<div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">汇河</span>
							 &copy; 2015-2016
						</span>
						&nbsp; &nbsp;						
					</div>
					<!-- /section:basics/footer -->
				</div>
			</div>

		</div>
	</body>
</html>