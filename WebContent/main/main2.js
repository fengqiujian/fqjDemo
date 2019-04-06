$(function(){
				var htmlText = '<li class="active open">'+
							   '	<a href="#" class="dropdown-toggle">'+
							   '		<i class="menu-icon fa fa-desktop"></i>'+
							   '		<span class="menu-text"> 一级菜单 </span>'+
							   '		<b class="arrow fa fa-angle-down"></b>'+
							   '	</a>'+
							   '	<b class="arrow"></b>'+
							   '	<ul class="submenu">'+
							   '		<li class="active open">'+
							   '			<a href="#" class="dropdown-toggle" onclick="abc(this)">'+
							   '				<i class="menu-icon fa fa-caret-right"></i>'+
							   '					二级菜单1'+
							   '				<b class="arrow fa fa-angle-down"></b>'+
							   '			</a>'+
							   '			<b class="arrow"></b>'+
							   '			<ul class="submenu">'+
							   '				<li class="">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单11'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '				<li class="active">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单21'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '				<li class="">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单31'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '				<li class="">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单41'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '			</ul>'+
							   '		</li>'+
							   '		<li class="">'+
							   '			<a href="#" class="dropdown-toggle" onclick="abc(this)">'+
							   '				<i class="menu-icon fa fa-caret-right"></i>'+
							   '				二级菜单2'+
							   '				<b class="arrow fa fa-angle-down"></b>'+
							   '			</a>'+
							   '			<b class="arrow"></b>'+
							   '			<ul class="submenu">'+
							   '				<li class="">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单12'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '				<li class="">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单22'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '				<li class="">'+
							   '					<a href="#">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单32'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '				<li class="">'+
							   '					<a href="#" onclick="choos(this)">'+
							   '						<i class="menu-icon fa fa-caret-right"></i>'+
							   '						子菜单42'+
							   '					</a>'+
							   '					<b class="arrow"></b>'+
							   '				</li>'+
							   '			</ul>'+
							   '		</li>'+
							   '	</ul>'+
							   '</li>';
				$("#menuDemo").html(htmlText);
			})
			function abc(doc){
				var parDoc = $(doc).parent().parent();
				var parDocC = $(parDoc).find('.open');
				var parDocU = $(parDoc).find('ul');
				$(parDocC).removeClass("open");
				$(parDocU).removeClass("nav-show").addClass("nav-hide").css("display","none");
			}
			function choos(doc){
				var docs = $(".active");
				$(docs).each(function(){
					$(this).removeClass("active");
				});
				$(doc).parent().addClass("active");
				$(doc).parent().parent().parent().addClass("active");
				$(doc).parent().parent().parent().parent().parent().addClass("active");
			}