		var userId=getUrlParam("userId");
		var typeInfo=getUrlParam("typeInfo");
		var people=unescape(getUrlParam("people"));
		
		//$("#aaa").html(people);
		$("#submitForm").click(function(){
			$.ajax({
			  type:"post",	
			  //http://10.9.3.116/ec_html/itf/applyep/new
			  url: url,
			  data: {
			  	userId:userId,
			  	typeInfo:typeInfo
			  },
			  dataType: json,
			  success: function(data){
			  	
			  }
			});
		})