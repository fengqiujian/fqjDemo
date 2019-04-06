$(document).ready(function() {
						   
	var hash = window.location.hash;
	var href = $('#nav li a').each(function(){
		var href = $(this).attr('href');
		//alert("hash="+hash+"---href="+href)
		if(hash==href){
			$('#content').load(href)
		}											
	});

	$('#nav li a').click(function(){			  
		var toLoad = $(this).attr('href');
		//alert("toLoad:"+toLoad);
		$('#content').hide('fast',loadContent);
		$('#load').remove();
		//$('#wrapper').append('<span id="load">LOADING...</span>');
		$('#load').fadeIn('normal');
		//window.location.hash = $(this).attr('href').substr(0,$(this).attr('href').length-5);
		//window.location.hash = $(this).attr('href');
		window.location.hash = toLoad;
		function loadContent() {
			$('#content').load(toLoad,'',showNewContent())
		}
		function showNewContent() {
			$('#content').show('normal',hideLoader());
		}
		function hideLoader() {
			$('#load').fadeOut('normal');
		}
		
		return false;	
	});


				
});