var basepath = "";
var this_tag = "";
var urlParams = location.search.substring(1);
if(urlParams.indexOf("type") >=0 && urlParams.indexOf("pkId") >=0){
	var params = urlParams.split("&");
	var typeParams =  params[0].split("=");
	var pkParams =  params[1].split("=");
	var typeParamName = typeParams[0];
	var pkParamName = pkParams[0];
	var typeParamStr = typeParams[1];
	if(typeParamStr == "1"){
		this_tag = "huodongMenu";
	}else{
		this_tag = "hangyeMenu";
	}
	var pkParamStr = pkParams[1];
	infoDetails(typeParamStr,pkParamStr);
}else{
	alert('请求参数不合法！')
}
//从左边列表进入企业详情
function infoDetails(type,pkRelease) {
	  $.ajax({
	         type: 'POST',
	         url: basepath+'/web/list/findByPk.do?pkRelease='+pkRelease,
	         dataType: "json",
	         contentType:"application/json; charset=UTF-8",
	         success: function(data){      	
	        	 var obj=data;
	        	 var titleHtml = "";
		         if(type=="1"){
		        	 $("#title").html('<a onclick ="returnBack('+type+')">活动中心</a>- 详情');
		         }else if(type=="2"){
	        		 $("#title").html('<a onclick ="returnBack('+type+')">爱充动态 </a>- 详情');
	        	 }else{
	        		 $("#title").html('<a onclick ="returnBack('+type+')">行业资讯 </a>- 详情');
	        	 }   
	        	 var html=	
	  		        '<li align="center" class="articleTitle">'+obj.releTitle+'</li>'+
	  		        '<li class="articleSubtitle"><span>发布时间：'+timestampformat(parseInt(obj.releCreatedate.time))+' 发布人：爱充网</li>'+
	  		         obj.releContent+ '<p class="content_main"><div class="back" align="center"><a style="color:#FF7F00" onclick ="returnBack('+type+')"><img src="'+basepath+'/static/images/img/back.png" alt="返回列表" /></a></div></p>';        		    
	 		        $("#content").html('<div class="ChannelContent pad_top50 pad_bot50"><ul class="">'+html+'</ul></div>');
	         }	         
	    });
	  }

//时间戳转化成指定格式
function timestampformat(timestamp) {       	
	 return new Date(timestamp).format('yyyy-MM-dd');
}

//返回
function returnBack(type){
	if(type=="1"){
		window.location.href = "/activity.html?type="+type;
	}else{
		window.location.href = "/company.html?type="+type;
	}
}