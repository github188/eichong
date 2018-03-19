			$.ajax({
		         type: 'POST',
		         url: "header.html" ,
		         success: function(data){
		        	$(data).insertBefore("#afterHead");
		        	UserService.showUser();
		        	if(this_tag=="index"){
						$("#"+this_tag).addClass("select");
					}else{
						$("#"+this_tag).addClass("select2");
					}
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		         }
		    });
			
			$.ajax({
		         type: 'POST',
		         url: "footer.html" ,
		         async:false,
		         success: function(data){
		        	$(data).insertAfter("#footerBefore");
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		         }
		    });
			
