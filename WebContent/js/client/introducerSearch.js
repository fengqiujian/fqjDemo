$(function() {
	var $search = $('#search');
	var $searchInput = $search.find('#introducer');
	$searchInput.attr('autocomplete', 'off');
	var $autocomplete = $('<div class="autocomplete" style="background:white;z-index:9999;"></div>').hide().insertAfter('#introducer');
	var clear = function() {
		$autocomplete.empty().hide();
	};
	$searchInput.blur(function() {
		setTimeout(clear, 500);
	});
	var selectedItem = null;
	var timeoutid = null;
	var setSelectedItem = function(item) {
		selectedItem = item;
		if (selectedItem < 0) {
			selectedItem = $autocomplete.find('li').length - 1;
		} else if (selectedItem > $autocomplete.find('li').length - 1) {
			selectedItem = 0;
		}
		$autocomplete.find('li').removeClass('highlight').eq(selectedItem).addClass('highlight');
	};
	var ajax_request = function() {
		$.ajax({
			'url' : '/enjoyhisfy/client/employeeinfo/getEmployeeList.json',
			'data' : {
				'pym' : $searchInput.val()
			},
			'dataType' : 'json',
			'type' : 'POST',
			'success' : function(data) {
				var datas = data.rows;
				if (datas.length) {
					$.each(datas, function(index, term) {
						$('<li style="cursor:pointer;z-index:9999;" code="' + term.code + '"></li>').text(term.name).appendTo($autocomplete).addClass('clickable').hover(function() {
							$(this).siblings().removeClass('highlight');
							$(this).addClass('highlight');
							selectedItem = index;
						}, function() {
							$(this).removeClass('highlight');
							selectedItem = -1;
						}).click(function() {
							$searchInput.val(term.name).attr("code", term.code);
							$autocomplete.empty().hide();
						});
					});
					var ypos = $searchInput.position().top;
					var xpos = $searchInput.position().left;
					$autocomplete.css('width', $searchInput.css('width'));
					$autocomplete.css({
						'position' : 'absolute',
						'left' : xpos + "px",
						'top' : 30 + "px"
					});
					setSelectedItem(0);
					$autocomplete.show();
				}
			}
		});
	};
	$searchInput.keyup(function(event) {
		if (event.keyCode > 40 || event.keyCode == 8 || event.keyCode == 32) {
			$autocomplete.empty().hide();
			clearTimeout(timeoutid);
			timeoutid = setTimeout(ajax_request, 100);
		} else if (event.keyCode == 38) {
			if (selectedItem == -1) {
				setSelectedItem($autocomplete.find('li').length - 1);
			} else {
				setSelectedItem(selectedItem - 1);
			}
			event.preventDefault();
		} else if (event.keyCode == 40) {
			if (selectedItem == -1) {
				setSelectedItem(0);
			} else {
				setSelectedItem(selectedItem + 1);
			}
			event.preventDefault();
		}
	}).keypress(function(event) {
		if (event.keyCode == 13) {
			if ($autocomplete.find('li').length == 0 || selectedItem == -1) {
				return;
			}
			var doc = $autocomplete.find('li').eq(selectedItem);
			var code = $(doc).attr("code");
			var name = $(doc).text();
			$searchInput.val(name).attr("code", code);
			$autocomplete.empty().hide();
			event.preventDefault();

		}
	}).keydown(function(event) {
		if (event.keyCode == 27) {
			$autocomplete.empty().hide();
			event.preventDefault();
		}
	});
});
