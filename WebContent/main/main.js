$(function() {
	$.ajax({
		url : "../login/getMenu.json",
		type : 'POST',
		async : false,
		dataType : 'json',
		success : function(data, textStatus, XMLHttpRequest) {
			$('#menuDemo').sidebarMenu({
				data : data.rows
			}, "cframe");
		}
	});
	getHeight();
});
function demo(txt) {
	alert(txt);
}
function getHeight() {
	var height = parseInt($(window).height());
	$('#cframe').attr("height", height - 125);
}