$(function () {	
	$.ajax({
        type: 'get',
        url: "/web/advertise/homePageImages.do" ,
        dataType: "json",
        success: function(data){
        	var imgContent="";
        	var btnContent="";
        	var url="";
        	for(var i=0;i<data.length;i++){
        		url=data[i].homePageUrl?data[i].homePageUrl:"javascript:";
        		if(data[i].homePageImage){
        			if(i==0){
            			imgContent+='<a target="_blank" href="'+url+'"  style="z-index:3;opacity:4;"><img src="'+data[i].homePageImage+'" /></a>';
            			btnContent+='<a  class="ggOn"><em></em></a>';
            		}else{
            			imgContent+='<a target="_blank" href="'+url+'"><img src="'+data[i].homePageImage+'" /></a>';
            			btnContent+='<a ><em></em></a>';
            		}
        		}
        	}
        	$("#ggBox").html(imgContent);
        	$("#ggBtns").html(btnContent);
        }
   });
	loadDt("1");
	loadDt("2,3");
});

function loadDt(type){
	var params={
		pageSize:3,
		pageNum:1
	}
	$.ajax({
        type: 'POST',
        url: "/web/list/findByType.do?releType="+type,
        dataType: "json",
        data:params,
        success: function(datas){
	       	var data=datas.data; 
	       	var html="";
	       	var title="";
	       	for(var i=0;i<data.length;i++){
       			title=data[i].releTitle;
       			title=title.length<28?title:title.substring(0, 28)+"...";
       			if(i==0){
       				html+='<li class="EM" ><a href="'+basepath+'/newsDetail.html?type='+data[i].releType+'&pkId='+data[i].pkRelease+'"><img src="'+data[i].imageUrl+'" width="640" height="240" />'+title+'</a></li>';
       			}else{
       				html+='<li class="Row"><span>'+timestampformat(data[i].releCreatedate)+'</span><a href="'+basepath+'/newsDetail.html?type='+data[i].releType+'&pkId='+data[i].pkRelease+'">'+title+'</a></li>';
       			}
					                			
	       	} 
	       	$("#dt"+type.split(',')[0]).after(html);
        }
   });
}

function timestampformat(timestamp) {
   	 return new Date(parseInt(timestamp)).format('yyyy-MM-dd');
  }