mui.init();
var mapDatas;
var pointType=0;
var pointId=0;
var map= new AMap.Map('container',{
            resizeEnable: true,
            zoom: 12
    	});
    	
//AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){
//      var toolBar = new AMap.ToolBar();
//      var scale = new AMap.Scale();
////      map.addControl(toolBar);
////      map.addControl(scale);
//});
//地图放大缩小图标控制============
$("#zoomin").click(function(){
	map.zoomIn();
})
$("#zoomout").click(function(){
	map.zoomOut();
})
map.on('moveend', function(e) {
	drawMarkers(mapDatas);
});
map.on('zoomend', function(e) {
	drawMarkers(mapDatas);
});
var district = new AMap.DistrictSearch({//高德行政区划查询插件实例
        subdistrict: 1   //返回下一级行政区
    });
var cityCode="330100";
//使用定位居中标志，当使用地址时，如果地址的首选项居中快于定位居中，默认取消定位居中
var useSelfLocation=true;
function getCity() {
    map.getCity(function(data) {
        if (data['province'] && typeof data['province'] === 'string') {
        	district.search(data["province"], function(status, result) {
    			var districtData = result.districtList[0];
            	if (districtData.districtList) {
                   var list=districtData.districtList;
                   for(var i=0;i<list.length;i++){
                	   if(list[i].citycode==data["citycode"]){
                		   cityCode=list[i].adcode;
                	  }
                   }
                } 
            });
        }
    });
}
getCity();

var geolocation;
var centerLon=120.12823;
var centerLat=30.271116;
map.plugin('AMap.Geolocation', function() {
   geolocation = new AMap.Geolocation({
        enableHighAccuracy: true,//是否使用高精度定位，默认:true
        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
        buttonOffset: new AMap.Pixel(20, 60),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
        zoomToAccuracy: false,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        showMarker:true,
        panToLocation:false,
        buttonPosition:'LT'
//      buttonDom
        
//      locationMarker:geolocation
    });
    map.addControl(geolocation);
//  geolocation.getCurrentPosition();
	getCurrentPosition();
    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
});
 $(".amap-controls").hide();
// $(".amap-geo").hide();
//解析定位结果
function onComplete(data) {
    centerLon=data.position.getLng();
    centerLat=data.position.getLat();
    if(useSelfLocation){
    	map.setCenter([centerLon,centerLat]);
    }
}
// alert(centerLat);
//解析定位错误信息
function onError(data) {
    mui.toast('定位失败');
}
$("#geolocationNew").click(function(){
//	alert(1)
	useSelfLocation=true;
	getCurrentPosition();
})
function getCurrentPosition(){
//	alert(2)
	geolocation.getCurrentPosition();
	map.setCenter([centerLon,centerLat]);
}

//$(".amap-geo").click(function(){
//	useSelfLocation=true;
//	geolocation.getCurrentPosition();
//});
//点击筛选按钮，底部框弹出，背景换颜色，底部隐藏；
//再次点击，底部框隐藏，背景切换回来，底部显示；
$(".filter").click(function(){
	if(isFilterHidden()){
		showFilterWindow();
	}else{
		showBottomWindow();
	}
	
})

//快慢充问题==================================================capacityTip===========
var chargingMode=null;
$(".chargingDot").click(function() {
	var flag=$(this).hasClass("active");
	chargingDotClear();
	if(flag){
        chargingMode=null;
	}else{
		$(this).addClass("active");
		$(this).find("img").css("display","block");
		chargingMode=$(this).attr("data-val");
	}
});	
function chargingDotClear() {
        $(".chargingDot").removeClass("active");
        $(".chargingDot").find("img").css("display","none");
}
//国标选择=================================================================
var powerInterface=null;
$(".chargingDot1").click(function() {
	var flags=$(this).hasClass("active1");
	powerInterfaceClear();
	if(flags){
        powerInterface=null;
	}else{
		$(this).addClass("active1");
		$(this).find("img").css("display","block");
		powerInterface=$(this).attr("data-val");
	}
});

function powerInterfaceClear() {
   $(".chargingDot1").removeClass("active1");
   $(".chargingDot1").find("img").css("display","none");
}
//空闲充点电=====================================================================
var freeStatus=1;
$("#freeChangingDot").click(function(){
	$(this).find("img").toggle();
	$(this).css({"border":"0.1rem solid #FF7D00","color":"#FF7D00"});
	var $img=$("#freeChangingDot").find("img");
	if($img.is(':hidden')){
		freeStatus=1;
		$("#freeChangingDot").css({"border":"0.1rem solid #ccc","color":"#000"});
	}else{
		freeStatus=1;
	}
})

//地图部分========================================================================

var url="/itf/electric/getElectricPileMapList";

var searchAddressUrl="/itf/electric/searchByAddress";
var data={};
data.freeStatus=data.freeStatus?data.freeStatus:1;
//data.freeStatus=true;
var address=getUrlParam("address");
address=address?unescape(address):"";
var hideOfflinePoint=false;//隐藏离线的点

function mapRequest(data){
	$("#address").val("");
	//不选当前城市了
	//data.cityCode=cityCode;
	//data.reqTime="2010-11-11";//new Date().format("yyyy-MM-dd");
	loadMap(url,data,false);
}
function mapSearchAddress(data){
	loadMap(searchAddressUrl,data,true);
}
function loadMap(url,data,showFirst){
	if(data.freeStatus){
		hideOfflinePoint=true;
	}else{
		hideOfflinePoint=false;
	}
	map.clearMap();
	//加载中效果==============================================
//	$("#waitLoad").show();
	loadStart();
	$.ajax({
	type:"get",	
	url: getRootPath()+url,
	data: data,
	dataType: "json",
	success: function(datas){
//		$("#waitLoad").hide();
		loadStop();
		//alert(JSON.stringify(datas));
		  	if(datas.status==100){
		  		var data=datas.data;
		  		if(!data){
		  			mui.toast("加载出错");
		  			return;
		  		}
		  		mapDatas=data;
		  		if(data.length==0){
		  			mui.toast("抱歉，无查询结果");
		  		}
		  		if(showFirst&&data.length>0){
		  			map.setCenter([parseFloat(data[0].longitude),parseFloat(data[0].latitude)]);
		  		}
		  		geolocation.getCurrentPosition();
		  		drawMarkers(mapDatas);
		  		useSelfLocation=false;
		  	}else{
		  		mui.toast(datas.msg);
		  	}
		  	
		}
	});
}
if(address){
	$("#address").val(address);
	data.elpiElectricpileaddress=address;
	setTimeout("mapSearchAddress(data)",500);
}else{
	setTimeout("mapRequest(data);",500); 
}

function drawMarkers(data){
	if(data){
		var bounds=map.getBounds();
		var containsFlag=false;
		for(var i=data.length-1;i>=0;i--){
			if(!(isNaN(data[i].longitude)&&isNaN(data[i].latitude))){
				containsFlag=bounds.contains([parseFloat(data[i].longitude),parseFloat(data[i].latitude)]);
				if(containsFlag){
					drawMarker(data[i]);
					//删除全局数组中的该点
					mapDatas.splice(i,1);
				}
	    	}
	    }
	}
}
    
function drawMarker(data){
		if(hideOfflinePoint&&data.electricState==10){
			return;//不显示未联网的充电点
		}
    	 lon=data.longitude;
    	 lat=data.latitude;
    	 
    	var url="";
    	if(data.isAppoint==1){
    		url="img/btn_map_charging_appointment.png";
    	}else{
    		url="img/btn_map_charging.png";
    	}
    	var marker = new AMap.Marker({
    		icon : url,
            position: [parseFloat(lon), parseFloat(lat)],
            map:map
    	});
    AMap.event.addListener(marker,'click',function() {
		//设置充电点类型为站或者桩
		pointType=data.electricType;
		pointId=data.electricId;
		//		弹出窗口
    	showInfoWindow();
    	
    	
    	if(data.isAppoint==1){
    		//显示空闲数  智能桩
    		$(".freeCount").show();
    		$(".makeOrder").find("a").css("color","#FF7D00");
    		$(".yellowClock").show();
    		$(".grayClock").hide();
//  		$("body").on("click","#makeOrder",function(){
//				toBespoke();
////  			event.stopPropagation();
////				event.preventDefault();
////				return false;
//  		})
			$("#makeOrder").click(function(){
				toBespoke();
			})
			
    	}else{
    		//隐藏空闲数  普通桩
    		$(".freeCount").hide();
    		$(".yellowClock").hide();
    		$(".grayClock").show();
    		$(".grayClock").css("display","block");
			$(".makeOrder").find("a").css("color","#CCCCCC");
			$("#makeOrder").unbind();
    	}
	    //获取电桩的名称和地址
		$("#generalAddr1").html(data.electricName);
		$("#detailAddr1").html(data.electricAddress);
		//点击简版详情地址，进入详情页
		$("#detailAddr1").on("click",function(){
			toBespoke();
		})
		var detailUrl="/itf/electric/getAnchorSummary";
		$.ajax({
			type:"get",	
			url: getRootPath()+detailUrl,
			data: {
				eid:data.electricId,
				type:data.electricType,
				lng:centerLon,
				lat:centerLat
			},
			dataType: "json",
			success: function(datas){
				  	if(datas.status==100){
				  		var data=datas.data;
				  		var kilometre=parseInt(data.distance)/1000;
				  		$("#distance1").html(kilometre.toFixed(2)+"公里");
				  		setSValue("distance",kilometre.toFixed(2));
				  		if(kilometre>15){
				  			$("#distance1").html("未知");	
				  		}
				  		$("#zlHeadNum").html(data.zlHeadNum);
				  		$("#zlFreeHeadNum").html(data.zlFreeHeadNum);
				  		$("#jlHeadNum").html(data.jlHeadNum);
				  		$("#jlFreeHeadNum").html(data.jlFreeHeadNum);
				  	}else{
				  		mui.toast(datas.msg);
				  	}
				  	
				}
		});
		
    })
}

//普通描点点击，弹出对应的框==========================================================================================

//点击确定筛选按钮，执行一些操作===========================================================================================
$("#map_search").click(function(){
	data.powerInterface=powerInterface;
	data.chargingMode=chargingMode;
	data.freeStatus=freeStatus;
	showBottomWindow();
	mapRequest(data);
})
//点击地图时对一些样式操作==============================================================================================
 map.on('click', function(e) {
      showBottomWindow();
 });   

function showBottomWindow(){
	hideFilter();
	hideInfo();
	showBottom();
}

function showFilterWindow(){
	showFilter();
	hideInfo();
	hideBottom();
}

function showInfoWindow(){
	hideFilter();
	showInfo();
	hideBottom();
}

function showFilter(){
	$(".filter_container").show();
	$(".filter").css({"opacity":"1","background":"#FF7D00"});
}

function hideFilter(){
	$(".filter_container").hide();
	$(".filter").css({"opacity":"0.4","background":"#000"});
}

function isFilterHidden(){
	return $('.filter_container').is(':hidden');
}

function showInfo(){
	$("#capacityTip").show();
}

function hideInfo(){
	$("#capacityTip").hide();
}
function isInfoHidden(){
	return $("#capacityTip").is(":hidden");	
}
function showBottom(){
	$(".bottom_fix").show();
}
function hideBottom(){
	$(".bottom_fix").hide();
}
//点击搜索框跳到界面===================================================================
$("#search_icon").click(function(){
	var address=$("#address").val();
	if(address){
		showBottomWindow();
		data.elpiElectricpileaddress=address;
		mapSearchAddress(data);
	}else{
		mui.toast("充电点地址不能为空");
	}
	
})
////扫一扫处理=======================================================================
//$(".scan").on("click",function(){
//	toPage("barcode_scan.html");
//})
$(".toList").on("click",function(){
	toPage("map_List.html?centerLon="+centerLon+"&centerLat="+centerLat);
})
//预约======充电快速入口=================================================================

var myBespokeUrl="/itf/electric/myBespoke";
var pkBespoke="";
var user=getUserInfo();
var userId=user?user.pkUserinfo:0;
$.ajax({
	type:"get",
	url:getRootPath()+myBespokeUrl,
	async:true,
	dataType:"json",
	data:{
		bespUserInfo:userId
	},
	success:function(req){
		//alert(JSON.stringify(req));
		if(req.status==100){
			var datas=req.data;
			
			//alert(JSON.stringify(datas));
			for(var i=0;i<datas.length;i++){
				//alert(data[i].bespBespokestatus);
				var bespBespokestatus=datas[i].bespBespokestatus;
				if(bespBespokestatus==4){
					pkBespoke=datas[i].pkBespoke;
					setSValue("pkBespoke",pkBespoke);
//					alert(pkBespoke);
					$("#statusBlock").show();
//					alert(bespBespokestatus)
					$("#statusText").html("预约中");
				}
					
			}
			
		}
		
	}
});
var chargeOrderListUrl="/itf/electric/chargeOrderList";
$.ajax({
	type:"get",
	url:getRootPath()+chargeOrderListUrl,
	async:true,
	dataType:"json",
	data:{
		userId:userId
	},
	success:function(req){
		//alert(JSON.stringify(req));
		if(req.status==100){
			var datas=req.data;
			//alert(JSON.stringify(datas));
			for(var i=0;i<datas.length;i++){
				var chOr_ChargingStatus=datas[i].chOr_ChargingStatus;
				if(chOr_ChargingStatus==1){
					$("#statusBlock").show();
					//alert(chOr_ChargingStatus)
					$("#statusText").html("充电中");
				}
					
			}
			
		}
		
	}
});
//跳转到预约和充电详情页面=======暂时搁置======================
$("#statusBlock").on("click",function(){
	var flag=$("#statusText").html();
	if(flag=="预约中"){
		toPage("order_detail.html");
	}else if(flag=="充电中"){
		toPage("charging_detail.html");
	}
})

//去预约
function toBespoke(){
	
//	alert(lon+":::"+lat);
	setSValue("lon",lon);
	setSValue("lat",lat);
	if(pointType==1){
		setSValue("electricPileId",pointId);
//		toPage("pileDetail.html");
		$("#capacityTip").hide();
		
		checkUserLogin("pileDetail.html");
	}else if(pointType==2){
		setSValue("powerStationId",pointId);
//		toPage("smartDetail.html");
		$("#capacityTip").hide();
		checkUserLogin("smartDetail.html");
	}
}
//$("#myPage").on("tap click",function(){
//	toMyPage()
//})
////去我的首页
//function toMyPage(){
//	checkUserLogin("myPage.html");
//}

//去导航=====
function toNavigate(){
//	alert(lon+":::"+lat);
	setSValue("lon",lon);
	setSValue("lat",lat);
	toPage("navigation.html");
}
//去我的钱包页面=======================

$("#myWallet").on("tap click",function(){
	checkUserLogin("myWallet.html");
	setSValue("checkPage","map");
})
