$(document).ready(function () {	
	$("#hangyeL").click(function(){
		var queryFlag = showTab(this);
		if(queryFlag){
			queryHangyeInfo(1,10);
		}
	})
	
	$("#eichongL").click(function(){
		var queryFlag = showTab(this);
		if(queryFlag){
			queryEichongInfo(1,10);
		}
	})
   
	var typeParams = location.search.substring(1).split("=");
	var paramName = typeParams[0];
	var typeParamStr = typeParams[1];
	if(typeParams == ""){
		queryEichongInfo(1,10);
	}else{
		if(paramName && paramName == 'type'){
			if(typeParamStr=="2"){
				queryEichongInfo(1,10)
			}else{
				var queryFlag = showTab($("#hangyeL"));
				if(queryFlag){
					queryHangyeInfo(1,10)
				}
			}
		}else{
			alert('请求参数不合法！')
		}
	}
     
});


function queryEichongInfo(pageNum,pageSize){
		    $.ajax({
		        type: 'POST',
		        url:basepath+ "/web/list/findByType.do?releType=2",
		        dataType: "json",
		        data:initPageParams(pageNum,pageSize),
		        success: function(datas){
		       	 	var obj=datas.data;
		       		var html="";
		       	 	for(var i=0;i<obj.length;i++){        		 
			       		var temp=obj[i];
			       		if(temp!=""){
			       			html += '<li class="Row"><span>'+timestampformat(temp.releCreatedate)+'</span><a href="/newsDetail.html?type=2&pkId='+temp.pkRelease+'">'+temp.releTitle+'</a></li>'
				       		
			       	}	       		
		       	 }
			     $("#eichongLContent").html(html);
			     initPage(datas,"queryEichongInfo","page_eichongL");
		       }
		   });
	}

function queryHangyeInfo(pageNum,pageSize){
		//加载右侧UL
	    $.ajax({
	        type: 'POST',
	        url:basepath+ "/web/list/findByType.do?releType=3",
	        dataType: "json",
	        data:initPageParams(pageNum,pageSize),
	        success: function(datas){
	       	 	var obj=datas.data;
	       		var html="";
	       	 	for(var i=0;i<obj.length;i++){        		 
		       		var temp=obj[i];
		       		if(temp!=""){
		       			html += '<li class="Row"><span>'+timestampformat(temp.releCreatedate)+'</span><a href="/newsDetail.html?type=3&pkId='+temp.pkRelease+'">'+temp.releTitle+'</a></li>'
			       		
		       		}	       		
	       	 	}
		       	$("#hangyeLContent").html(html);
			    initPage(datas,"queryHangyeInfo","page_hangyeL");
	       }
	   });
}

//时间戳转化成指定格式
function timestampformat(timestamp) {       	
	 return new Date(timestamp).format('yyyy-MM-dd');
}

function showTab(obj){
	var lb=$(obj).attr("id");
	var flag = $(obj).children().hasClass('unshow');
	if(flag){
		$(obj).siblings().children().addClass('unshow');
		$(obj).children().removeClass('unshow');
		$(".content").hide();
		$(".fanye").hide();
		$("#"+lb+"Content").show();
		$("#page_"+lb).show();
		if($("#"+lb+"Content").html().trim() != ""){
			flag=false;
		}
	}
	return flag;
}