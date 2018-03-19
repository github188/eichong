$(document).ready(function () {
	queryEichongInfo(1,10); 
});

function queryEichongInfo(pageNum,pageSize){
		 $.ajax({
		     type: 'POST',
		     url:basepath+ "/web/list/findByType.do?releType=1",
		     dataType: "json",
		     data:initPageParams(pageNum,pageSize),
		     success: function(datas){
		       	 var obj=datas.data;
		       	 var html="";
		       	 for(var i=0;i<obj.length;i++){        		 
			       	var temp=obj[i];
			       	if(temp!=""){
			       		html += '<li class="Row"><span>'+timestampformat(temp.releCreatedate)+'</span><a href="/newsDetail.html?type=1&pkId='+temp.pkRelease+'">'+temp.releTitle+'</a></li>'
				       		
			       	}	       		
		       	 }
			     $("#huodong").html(html)
			     initPage(datas,"queryEichongInfo");
		     }
		 });
	}

//时间戳转化成指定格式
function timestampformat(timestamp) {       	
	 return new Date(timestamp).format('yyyy-MM-dd');
}
