$(function() {
	var $search = $('#search');
	var $searchInput = $search.find('#search-text');
	$searchInput.attr('autocomplete', 'off');
	var $autocomplete = $('<div class="autocomplete"></div>').hide()
			.insertAfter('#search-text');
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
		$autocomplete.find('li').removeClass('highlight').eq(selectedItem)
				.addClass('highlight');
	};
	var ajax_request = function(){
$.ajax({
			'url' : '/enjoyhisfy/report/source/getDocList.json',
			'data' : {
				'pym' : $searchInput.val()
			},
			'dataType' : 'json',
			'type' : 'POST',
			'success' : function(data) {
				var datas = data.rows;
				if (datas.length) {
					$.each(datas, function(index, term) {
						$('<li code="' + term.code + '"></li>').text(
								term.name).appendTo($autocomplete)
								.addClass('clickable').hover(
										function() {
											$(this).siblings().removeClass(
													'highlight');
											$(this).addClass('highlight');
											selectedItem = index;
										}, function() {
											$(this).removeClass('highlight');
											selectedItem = -1;
										}).click(function() {
									$searchInput.val(term.name).attr("code",term.code);
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
			alert(1);
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
			var code = $autocomplete.find('li').eq(selectedItem).attr("code");
			$searchInput.val($autocomplete.find('li').eq(selectedItem).text).attr("code",code);
			$autocomplete.empty().hide();
			event.preventDefault();

		}
	}).keydown(function(event) {
		if (event.keyCode == 27) {
			$autocomplete.empty().hide();
			event.preventDefault();
		}
	});
//	$(window).resize(function() {
//		var ypos = $searchInput.position().top;
//		var xpos = $searchInput.position().left;
//		$autocomplete.css('width', $searchInput.css('width'));
//		$autocomplete.css({
//			'position' : 'relative',
//			'left' : xpos + "px",
//			'top' : ypos + "px"
//		});
//	});
});