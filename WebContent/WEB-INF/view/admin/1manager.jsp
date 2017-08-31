<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>控制台</title>
		<meta name="keywords" content="北京汇河科技后台管理系统" />
		<meta name="description" content="汇河科技是一家专业的网络服务承接公司" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${path}/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${path}/assets/css/font-awesome.css" />
		<!-- page specific plugin styles -->
		<!-- text fonts -->
		<link rel="stylesheet" href="${path}/assets/css/ace-fonts.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="${path}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${path}/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${path}/assets/css/ace-ie.css" />
		<![endif]-->
		<!-- inline styles related to this page -->
		<!-- ace settings handler -->
		<script src="${path}/assets/js/ace-extra.js"></script>
		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
		<!--[if lte IE 8]>
		<script src="${path}/assets/js/html5shiv.js"></script>
		<script src="${path}/assets/js/respond.js"></script>
		<![endif]-->
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
							ENJOYHIS - ${sessionScope.title_name.unitName} - 分院后台管理系统
						</small>
					</a>
				</div>
				<!-- #section:basics/navbar.dropdown -->
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
						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>
				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>
		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				<ul class="nav nav-list">
					<li class="active">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text">
								后台系统管理
							</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>
						<b class="arrow"></b>
						<ul class="submenu">
							
							<li class="">
								<a href="/enjoyhisfy/client/serviceitemflxm/serviceitem_xm.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									处置项维护
								</a>

								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/admin/hisdocroom/home.htm"  target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									诊室配置
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/client/employeeinfo/empmaintain_home.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									人员维护
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/client/pages/depart_fy_main.htm"  target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									科室维护
								</a>
								<b class="arrow"></b>
							</li>
							
							<li class="">
								<a href="/enjoyhisfy/client/dictionary/findAll.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									数据字典管理
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/client/prepayact/findactivityall.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									充值活动管理
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/client/stationpage/station_main.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									岗位维护
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/client/groupmanager/groupview_main.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									组管理
								</a>
								<b class="arrow"></b>
							</li>
							
							<li class="">
								<a href="/enjoyhisfy/client/employeegroup/empgrou_main.htm" target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									属组管理
								</a>
								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="/enjoyhisfy/client/datasynchronization/synchmenulist.htm"  target="cframe">
									<i class="menu-icon fa fa-caret-right"></i>
									数据同步项
								</a>
								<b class="arrow"></b>
							</li>
						</ul>
					</li>			
				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>
<!------------------------左侧菜单结束--------------------------------->
			<div class="main-content tabbox">
				<iframe src="/enjoyhisfy/client/homepange/homes.htm" name="cframe" id="cframe" height="600px" width="100%" 
				 marginheight="0" marginwidth="0" frameborder="0" scrolling="no" allowtransparency="yes" >				
				</iframe><!--ajax 加载位置-->
			</div>
<!---------------------------footer -------------------------------------------------->
			<!-- <div class="footer">
				<div class="footer-inner">
					#section:basics/footer
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">汇河</span>
							 &copy; 2015-2016
						</span>
						&nbsp; &nbsp;						
					</div>
					/section:basics/footer
				</div>
			</div> -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${path}/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

	
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${path}/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="${path}/assets/js/bootstrap.js"></script>
		<script src="${path}/assets/js/bootbox.js"></script>
	<script src="${path}/assets/js/alert.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${path}/assets/js/excanvas.js"></script>
		<![endif]-->
		<script src="${path}/assets/js/jquery-ui.custom.js"></script>
		<script src="${path}/assets/js/jquery.ui.touch-punch.js"></script>

		<!-- ace scripts -->
		<script src="${path}/assets/js/ace/elements.scroller.js"></script>
		<script src="${path}/assets/js/ace/elements.colorpicker.js"></script>
		<script src="${path}/assets/js/ace/elements.fileinput.js"></script>
		<script src="${path}/assets/js/ace/elements.typeahead.js"></script>
		<script src="${path}/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="${path}/assets/js/ace/elements.spinner.js"></script>
		<script src="${path}/assets/js/ace/elements.treeview.js"></script>
		<script src="${path}/assets/js/ace/elements.wizard.js"></script>
		<script src="${path}/assets/js/ace/elements.aside.js"></script>
		<script src="${path}/assets/js/ace/ace.js"></script>
		<script src="${path}/assets/js/ace/ace.ajax-content.js"></script>
		<script src="${path}/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${path}/assets/js/ace/ace.sidebar.js"></script>
		<script src="${path}/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${path}/assets/js/ace/ace.submenu-hover.js"></script>
		<script src="${path}/assets/js/ace/ace.widget-box.js"></script>
		<script src="${path}/assets/js/ace/ace.settings.js"></script>
		<script src="${path}/assets/js/ace/ace.settings-rtl.js"></script>
		<script src="${path}/assets/js/ace/ace.settings-skin.js"></script>
		<script src="${path}/assets/js/ace/ace.widget-on-reload.js"></script>
		<script src="${path}/assets/js/ace/ace.searchbox-autocomplete.js"></script>


		<script type="text/javascript"> ace.vars['base'] = '${path}'; </script>
		<script src="${path}/assets/js/ace/elements.onpage-help.js"></script>
		<script src="${path}/assets/js/ace/ace.onpage-help.js"></script>
		<script src="${path}/assets/docs/assets/js/rainbow.js"></script>
		<script src="${path}/assets/docs/assets/js/language/generic.js"></script>
		<script src="${path}/assets/docs/assets/js/language/html.js"></script>
		<script src="${path}/assets/docs/assets/js/language/css.js"></script>
		<script src="${path}/assets/docs/assets/js/language/javascript.js"></script>
		
		<script>
			//ajax 选项卡
		/*$('.submenu li a').click(function(){
			var thiscity = $(this).attr("href");//attr,设置被选元素的属性和值。
			$('.tabbox').load(thiscity);
			return false;
		});
		$('.submenu li a').eq(0).trigger("click");//第一个默认为点击状态，让内容显示出来
		//trigger()规定被选元素的触发方式，*/
		
		 //控制iframe高度
  	 function reinitIframe(){  
		var iframe = document.getElementById("cframe");  
		try{  
		    var bHeight = iframe.contentWindow.document.body.scrollHeight; // 浏览器所有内容高度 
		    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight; //  浏览器所有内容高度
		    var height = Math.max(bHeight, dHeight);  
		    iframe.height = height;  
		}catch (ex){}  
		}  		  
		var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
		  
		function reinitIframeEND(){  
		var iframe = document.getElementById("cframe");  
		try{  
		    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
		    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
		    var height = Math.max(bHeight, dHeight);  
		    iframe.height = height;  
		}catch (ex){}  
		// 停止定时  
		window.clearInterval(timer1);  		  
		}  
		</script>
	</body>
</html>
