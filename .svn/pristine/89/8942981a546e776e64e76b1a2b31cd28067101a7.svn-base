checkUserLogin();
var user=getUserInfo();
var userId=user.pkUserinfo;

var lng=120.12823;
var lat=30.271116;
//var lng="";
//var lat="";
  if (navigator.geolocation) {  
    navigator.geolocation.getCurrentPosition(function(pos) {  
        // 成功回调函数，接受一个地理位置的对象作为参数。  
        // https://developer.mozilla.org/cn/docs/Web/API/Position 参数说明  
        
        lng=pos.coords.longitude;
        lat=pos.coords.latitude;
    }, function(err) {  
        // 错误的回调  
        // https://developer.mozilla.org/cn/docs/Web/API/PositionError 错误参数  
    }, {  
        enableHighAccuracy: true, // 是否获取高精度结果  
        timeout: 5000, //超时,毫秒  
        maximumAge: 0 //可以接受多少毫秒的缓存位置  
        // 详细说明 https://developer.mozilla.org/cn/docs/Web/API/PositionOptions  
    });  
  } 

//alert(lng+":::"+lat);

var url="/itf/collect/list";
$.ajax({
	type:"get",
	url:getRootPath()+url,
	async:true,
	dataType:"json",
	data:{
		userId:userId,
		lat:lat,
		lng:lng
	},
	success:function(req){
		//alert(JSON.stringify(req));
		if(req.status==100){
			var content="";
			var datas=req.data;
			for(i=datas.length-1;i>=0;i--){
				var str1="";
				var orderClass="";
				orderBlock(datas[i]);
			}
			
			
		}
		
	}
});
function orderBlock(index){
	var orderValue=index.poSt_IsAppoint;
	var usCo_Objectid=index.usCo_Objectid;
	var usCo_Type=index.usCo_Type;
	
//	alert(poSt_IsAppoint)
	//alert(electricId);
	if(orderValue==1){
		str1="<a href='##' class='Ybtn' data-id='"+usCo_Objectid+"' data-usCo_Type='"+usCo_Type+"'>预约</a>";
		orderClass="orderBtn";
		//alert(orderClass);
	}else{
		str1="预约";
		orderClass="noOrderBtn";
	}
//	creatSection(appointClass,str1,pile);
	creatBlock(orderClass,str1,index);
}
function creatBlock(orderClass,str1,index){
	var detailDistance=(index.distance/1000).toFixed(2);
	
	var content =$("#content").html();
	 content+='<section><div class="container"><div class="col-xs-9 stationName">'+index.NAME
				+'</div><div class="col-xs-3 distance"><span>'+detailDistance
				+'km</span></div></div><div class="container container2"><div class="col-xs-12 detailAddress" data-id="'+index.usCo_Objectid+'" data-usCo_Type="'+index.usCo_Type+'">'+index.addr
				+'</div></div><div class="container container3"><div class="col-xs-6 gunNum"><button type="button" class="mui-btn num-btn">快充 <span class="mui-badge">'+index.zlHeadNum
				+'</span></button><button type="button" class="mui-btn num-btn">慢充 <span class="mui-badge">'+index.jlHeadNum
				+'</span></button></div><div class="col-xs-3 navBtn" data-id="'+index.usCo_Objectid+'">导航</div><div class="col-xs-3 '+orderClass+'" data-id="'+index.usCo_Objectid+'">'+str1
				+'</div></div></section>';
	$("#content").html(content);
}
//点击预约-=======
$("body").on("click",".Ybtn",function(){
//		电站电桩id
		var usCo_Objectid=$(this).attr("data-id");
		var usCo_Type=$(this).attr("data-usCo_Type");
		//alert(usCo_Type);
		if(usCo_Type==2){
			setSValue("powerStationId",usCo_Objectid);
			toPage("smartDetail.html");
		}else{
			setSValue("electricPileId",usCo_Objectid);
			toPage("pileDetail.html");
		}
		
	});
//点击导航部分======
//$("body").on("click",".navBtn",function(){
//		var usCo_Objectid=$(this).attr("data-id");
//		toPage("bespoke.html?usCo_Objectid="+usCo_Objectid);
//	});
//动态创建的部分，点击进入详情页=
$("body").on("click",".detailAddress",function(){
		var usCo_Objectid=$(this).attr("data-id");
//		toPage("bespoke.html?usCo_Objectid="+usCo_Objectid);
		var usCo_Type=$(this).attr("data-usCo_Type");
		if(usCo_Type==2){
			setSValue("powerStationId",usCo_Objectid);
			toPage("smartDetail.html");
		}else{
			setSValue("electricPileId",usCo_Objectid);
			toPage("pileDetail.html");
		}
	});
	

//返回我的首页
function toMyPage(){
	toPage("myPage.html");
}
