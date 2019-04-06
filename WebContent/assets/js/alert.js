window.alert = function(str,cb){
	bootbox.alert({  
                buttons: {  
                   ok: {  
                        label: '确定',  
                        className: 'btn-info'  						
                    }  
                },  
                message: str,  
                callback: function() {  
                	 if (typeof cb === "function"){
                         //alert(callback);
                		 cb(); 
                     }
                },  
                title: "提示信息",  
            });
}