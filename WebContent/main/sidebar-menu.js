(function ($) {
    $.fn.sidebarMenu = function (options,labelId) {
		
        options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
        var target = $(this);
        if (options.data) {
            init(target, options.data);
        }
        else {
            if (!options.url) return;
            $.getJSON(options.url, options.param, function (data) {
                init(target, data);
            });
        }
        var url = window.location.pathname;
        function init(target, data) {
            $.each(data, function (i, item) {
                var li = $('<li></li>');
                var a = $('<a></a>');
                var icon = $('<i></i>');
                icon.addClass(item.icon);
                var text = $('<span></span>');
                text.addClass('menu-text').text(item.text);
                a.append(icon);
                a.append(text);
				a.append(b);
				var b=$('<b class="arrow"></b>');
                if (item.menus&&item.menus.length>0) {
					if(item.isOpen){
						li.addClass('active open');
					}
                    a.attr('href', '#');
//                    a.attr('onclick', 'als(this)');
                    a.addClass('dropdown-toggle');
                    var arrow = $('<b class="arrow fa fa-angle-down"></b>');
                    a.append(arrow);
                    li.append(a);
					li.append(b);
                    var menus = $('<ul></ul>');
                    menus.addClass('submenu');
                    if(item.isOpen){
                    	 menus.addClass('nav-show');
					}
                    init(menus, item.menus);
                    li.append(menus);
                }
                else {
					if(item.isOpen){
						li.addClass('active');
					}
					a.attr('href', item.url);
					a.attr('target', labelId);
					a.attr('onclick', 'al(this)');
                    li.append(a);
					li.append(b);
                }
                target.append(li);
            });
        }
    }

    $.fn.sidebarMenu.defaults = {
        url: null,
        param: null,
        data: null
    };
})(jQuery);

//function als(doc){
//	alert(doc)
//	var a = $(doc).next().next();
//	var as = $(a).attr("display");
//	alert(as);
//	if(as=="block"){
//		$(a).removeClass("nav-show").addClass("nav-hide").css("display","none");
//	}else{
//		$(".nav-show").removeClass("nav-show").addClass("nav-hide").css("display","none");
//		$(a).removeClass("nav-hide").addClass("nav-show").css("display","block");
//	}
//}
function al(doc){
	var docs = $(".active");
	$(docs).each(function(){
		$(this).removeClass("active");
	});
	//submenu
	$(doc).parent().addClass("active");
	$(doc).parent().parent().parent().addClass("active");
}