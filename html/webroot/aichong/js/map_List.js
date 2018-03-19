checkUserLogin();
$("#classifyList >li").click(function(){
	var index=$(this).index();
	$(this).addClass("active").siblings().removeClass("active");
	var $div=$("#mainContent >div");
	$div.eq(index).show().siblings().hide();

})
var centerLon=getUrlParam("centerLon");
var centerLat=getUrlParam("centerLat");
var electricPrices=0;
var electricEvaluate=0;
var screenRadius=1;

loadPoint();

function loadPoint(){
	var url="/itf/electric/getElectricPileListN";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			searchKey:$("#searchKey").val(),
			Longitude:centerLon,
			Latitude:centerLat,
			electricPrices:electricPrices,
			electricEvaluate:electricEvaluate,
			screenRadius:screenRadius
		},
		success:function(req){
			//alert(JSON.stringify(req));
			if(req.status==100){
				var data=req.data;
	//			$("#distanceBlock").html("");
				for(i=0;i<data.length;i++){
					
					var str1="";
					var appointClass="";
//					alert(1)
					appointBlock(data[i]);
//					if(screenRadius==1){
//						appointBlock(data[i]);
//					}else if(electricPrices==1){
//						appointBlockPrices(data[i]);
//					}else if(electricEvaluate==1){
//						appointBlockEvaluate(data[i]);
//					}
					
				}
			}
				
		}
	});
}
//alert(centerLon)

function appointBlock(pile){
	var appointValue=pile.isAppoint;
	var electricId=pile.electricId;
//	 alert(electricId)
	var electricType=pile.electricType;
	if(appointValue==1){
		str1="<a href='##' class='Ybtn' data-id='"+electricId+"' data-electricType='"+electricType+"'>预约</a>";
		appointClass="appointmentBtnActive";
	}else{
		str1="预约";
		appointClass="appointmentBtn";
	}
	creatSection(appointClass,str1,pile);
}
function creatSection(appointClass,str1,pile){
	var electricDistance=(pile.electricDistance/1000).toFixed(2);
	var html =$("#distanceBlock").html();
	 html+='<section><div class="detailTop"><div class="powerStationAddress left">'+pile.electricName
			+'</div><div class="distance right">'+electricDistance+'km'
			+'</div></div><div class="detailCenter" data-id="'+pile.electricId+'" data-electricType="'+pile.electricType+'"><ul class="showBtn"><li><span>快充</span><span class="fastRound">'+pile.zlHeadNum
			+'</span><span>空闲</span><span class="freeRound">'+pile.zlFreeHeadNum
			+'</span></li><li><span>慢充</span><span class="slowlyRound">'+pile.jlHeadNum
		    +'</span><span>空闲</span><span class="freeRound">'+pile.jlFreeHeadNum
			+'</span></li></ul><div class="powerStationAddress">'+pile.electricAddress
			+'</div></div><div class="detailBottom"><div class="col-xs-3 baseStyle rate" style="border-left: 0;"><span>￥'+pile.serviceCharge
			+'</span>元/度</div><div class="col-xs-3 baseStyle star"><span>'+pile.commentStart
			+'</span>分</div><div class="col-xs-3 baseStyle navImgActive" data-id="'+pile.electricId+'">导航'
			+'</div><div class="col-xs-3 baseStyle '+appointClass+'">'+str1
			+'</div></div></section>';
					
			$("#distanceBlock").html(html);
}


function loadBySearchKey(){
	var electricPrices=0;
	var electricEvaluate=0;
	var screenRadius=0;
	loadPoint();
//	alert($("#searchKey").val())
}


function loadByElectricPrices(){
	var electricPrices=1;
	var electricEvaluate=0;
	var screenRadius=0;
	loadPoint();
}

function loadByElectricEvaluate(){
	var electricPrices=0;
	var electricEvaluate=1;
	var screenRadius=0;
	loadPoint();
}

function loadByScreenRadius(){
	var electricPrices=0;
	var electricEvaluate=0;
	var screenRadius=1;
	loadPoint();
}


//点击预约-=======
$("body").on("click",".Ybtn",function(){
		var electricId=$(this).attr("data-id");
		var electricType=$(this).attr("data-electricType");
		//alert(electricId);
		if(electricType==2){
			setSValue("powerStationId",electricId);
			toPage("smartDetail.html");
		}else{
			setSValue("electricPileId",electricId);
			toPage("pileDetail.html");
		}
//		toPage("orderPage.html");
//		toPage("orderPage.html?electricId="+electricId);
		
	});
//点击导航部分======
$("body").on("click",".navImgActive",function(){
		var electricId=$(this).attr("data-id");
		toPage("bespoke.html?electricId="+electricId);
	});
//动态创建的section ，添加点击事件，跳到详情页===============
$("body").on("click",".detailCenter",function(){
		var electricId=$(this).attr("data-id");
		var electricType=$(this).attr("data-electricType");
		
//		alert(electricType);
		if(electricType==2){
			setSValue("powerStationId",electricId);
			toPage("smartDetail.html");
		}else{
			setSValue("electricPileId",electricId);
			toPage("pileDetail.html");
		}
		//toPage("bespoke.html?electricId="+electricId);
	});
	



//处理排序===点击距离按照距离排序=============================================================
$("#screenRadius").click(function(){
	var url="/itf/electric/list";
	var html=$("#distanceBlock").html();
	if(html)return;
	loadByScreenRadius();
})
//按照价格排序===============================================================================
var flag=1;
$("#electricPrices").click(function(){
	var url="/itf/electric/getElectricPileListN";
	if(flag==1){
		loadByElectricPrices();
		$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			Longitude:centerLon,
			Latitude:centerLat,
			electricPrices:1
		},
		success:function(req){
			//alert(JSON.stringify(req));
			//$(".priceBlock").html("");
			if(req.status==100){
				var data=req.data;
				for(i=0;i<data.length;i++){
					var str1="";
					var appointClass="";
					appointBlockPrices(data[i]);
					
				}
			}
				
		}
	});
	flag=0;
	}else{
		return;
	}
})
function appointBlockPrices(pile){
	var appointValue=pile.isAppoint;
	var electricId=pile.electricId;
	var electricType=pile.electricType;
	if(appointValue==1){
		str1="<a href='##' class='Ybtn' data-id='"+electricId+"' data-electricType='"+electricType+"'>预约</a>";
		appointClass="appointmentBtnActive";
	}else{
		str1="预约";
		appointClass="appointmentBtn";
	}
	creatSectionPrices(appointClass,str1,pile);
}
function creatSectionPrices(appointClass,str1,pile){
	var electricDistance=(pile.electricDistance/1000).toFixed(2);
	var html =$(".priceBlock").html();
	 html+='<section><div class="detailTop"><div class="powerStationAddress left">'+pile.electricName
			+'</div><div class="distance right">'+electricDistance+'km'
			+'</div></div><div class="detailCenter" data-id="'+pile.electricId+'" data-electricType="'+pile.electricType+'"><ul class="showBtn"><li><span>快充</span><span class="fastRound">'+pile.zlHeadNum
			+'</span><span>空闲</span><span class="freeRound">'+pile.zlFreeHeadNum
			+'</span></li><li><span>慢充</span><span class="slowlyRound">'+pile.jlHeadNum
		    +'</span><span>空闲</span><span class="freeRound">'+pile.jlFreeHeadNum
			+'</span></li></ul><div class="powerStationAddress">'+pile.electricAddress
			+'</div></div><div class="detailBottom"><div class="col-xs-3 baseStyle rate" style="border-left: 0;"><span>￥'+pile.serviceCharge
			+'</span>元/度</div><div class="col-xs-3 baseStyle star"><span>'+pile.commentStart
			+'</span>分</div><div class="col-xs-3 baseStyle navImgActive" data-id="'+pile.electricId+'">导航'
			+'</div><div class="col-xs-3 baseStyle '+appointClass+'">'+str1
			+'</div></div></section>';
					
			$(".priceBlock").html(html);
}

//按照评分排序========================================================================
var flag2=1;
$("#electricEvaluate").click(function(){
	var url="/itf/electric/getElectricPileListN";
	if(flag2==1){
		loadByElectricEvaluate();
		$.ajax({
			type:"get",
			url:getRootPath()+url,
			async:true,
			dataType:"json",
			data:{
				Longitude:centerLon,
				Latitude:centerLat,
				electricEvaluate:1
			},
			success:function(req){
				//alert(JSON.stringify(req));
				$(".starBlock").html("");
				if(req.status==100){
					var data=req.data;
					for(i=0;i<data.length;i++){
						var str1="";
						var appointClass="";
						appointBlockEvaluate(data[i]);
					}
				}
			}
		});
	flag2=0;
	}else{
		return;
	}
})
function appointBlockEvaluate(pile){
	var appointValue=pile.isAppoint;
	var electricId=pile.electricId;
	var electricType=pile.electricType;
	if(appointValue==1){
		str1="<a href='##' class='Ybtn' data-id='"+electricId+"' data-electricType='"+electricType+"'>预约</a>";
		appointClass="appointmentBtnActive";
	}else{
		str1="预约";
		appointClass="appointmentBtn";
	}
	creatSectionEvaluate(appointClass,str1,pile);
}
function creatSectionEvaluate(appointClass,str1,pile){
	var electricDistance=(pile.electricDistance/1000).toFixed(2);
	var html =$(".starBlock").html();
	 html+='<section><div class="detailTop"><div class="powerStationAddress left">'+pile.electricName
			+'</div><div class="distance right">'+electricDistance+'km'
			+'</div></div><div class="detailCenter" data-id="'+pile.electricId+'" data-electricType="'+pile.electricType+'"><ul class="showBtn"><li><span>快充</span><span class="fastRound">'+pile.zlHeadNum
			+'</span><span>空闲</span><span class="freeRound">'+pile.zlFreeHeadNum
			+'</span></li><li><span>慢充</span><span class="slowlyRound">'+pile.jlHeadNum
		    +'</span><span>空闲</span><span class="freeRound">'+pile.jlFreeHeadNum
			+'</span></li></ul><div class="powerStationAddress">'+pile.electricAddress
			+'</div></div><div class="detailBottom"><div class="col-xs-3 baseStyle rate" style="border-left: 0;"><span>￥'+pile.serviceCharge
			+'</span>元/度</div><div class="col-xs-3 baseStyle star"><span>'+pile.commentStart
			+'</span>分</div><div class="col-xs-3 baseStyle navImgActive" data-id="'+pile.electricId+'">导航'
			+'</div><div class="col-xs-3 baseStyle '+appointClass+'">'+str1
			+'</div></div></section>';
					
			$(".starBlock").html(html);
}

//扫一扫处理=======================================================================
$(".scan").on("click",function(){
	toPage("barcode_scan.html");
})
$(".toMap").on("click",function(){
	toPage("map_index.html");
})

//}
//去订单
$("#orderDetail").on("tap click",function(){
	toOrderDetail();
})
function toOrderDetail(){
	checkUserLogin();
	toPage("order_appointment.html");
}
$("#myPage").on("tap click",function(){
	toMyPage()
})
//去我的首页
function toMyPage(){
	toPage("myPage.html");
}