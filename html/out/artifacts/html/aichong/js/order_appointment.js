var user=getUserInfo();
var userId=user?user.pkUserinfo:"";
myBespokeList();
function myBespokeList(){
	var url="/itf/electric/myBespoke";
	var html="";
	var content="";
		$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			bespUserInfo:userId
		},
		success:function(req){
			var data=req.data;
			//alert(JSON.stringify(data));
			if(req.status==100){
				//alert(JSON.stringify(data));
				//alert(data.length);
				if(data.length==0){
					$(".bespCreateMessage").show();
	//				$(".orderBlock").hide();
					$(".bespCreateMessage").html("暂无预约记录");
					$(".bespCreate").hide();
					$(".finished").hide();
				}else{
					for(var i=0;i<data.length;i++){
						//alert(data[i].bespBespokestatus);
						var bespBespokestatus=data[i].bespBespokestatus;
						bespBespoketime =data[i].bespBespoketime;
						if(bespBespokestatus==4){
							$(".bespCreate").show();
//							页面时间显示处理
							var orderBeginTime=data[i].bespBeginTime.replace(/\//g,'.');
							var orderBeginTimeValue=orderBeginTime.substring(0,orderBeginTime.length-3);
							var orderEndTime=data[i].bespEndTime.replace(/\//g,'.');
							var orderEndTimeValue=orderEndTime.substring(0,orderEndTime.length-3);
							
							content='<div class="bespCreateDetail"><div class="epName" data-id="'+data[i].bespResepaymentcode
							+'" data-pkBespoke="'+data[i].pkBespoke
							+'">'+data[i].epName
							+'</div><div class="bespCreateDate" data-id="'+data[i].bespResepaymentcode
							+'" data-pkBespoke="'+data[i].pkBespoke
							+'"><span>'+orderBeginTimeValue
							+'</span><span> - </span><span>'+orderEndTimeValue
							+'</span></div><div class="epAddress" data-id="'+data[i].bespResepaymentcode
							+'" data-pkBespoke="'+data[i].pkBespoke
							+'">'+data[i].epAddress
							+'</div><div class="toReOrder" id="toReOrder" data-bespBespoketime="'+data[i].bespBespoketime
							+'">续约</div></div><div class="navMap" id="navMap">导航</div>';
							$(".bespCreate").append(content);	
						}else if(bespBespokestatus==2){
							$(".finished").show();
//							预约结束的代码段========
//							页面时间显示处理
							var bespBespokeBeginTime=data[i].bespBeginTime.replace(/\//g,'.');
							var bespBespokeBeginTimeValue=bespBespokeBeginTime.substring(0,bespBespokeBeginTime.length-3);
							var bespBespokeEndTime=data[i].bespEndTime.slice(11,16);
							html='<div class="bespCreateFinishedDetail" data-id="'+data[i].bespResepaymentcode
							+'" data-val="'+data[i].pkBespoke
							+'"><div class="BelectricName">'+data[i].epName
							+'</div><div class="BtotalTime"><span>预约时间：</span><span>'+bespBespokeBeginTimeValue
							+'</span><span> - </span><span>'+bespBespokeEndTime
							+'</span></div><div class="BelectricAddress">'+data[i].epAddress
							+'</div></div>';
							$(".finished").append(html);
						}else if(bespBespokestatus!=4&&bespBespokestatus!=2){
							$(".bespCreateMessage").show();
			//				$(".orderBlock").hide();
							$(".bespCreateMessage").html("暂无预约记录");
							$(".bespCreate").hide();
							$(".finished").hide();
						}
						
					}
				}
				
			}
			
		}
	});	
}

//var html="";
//var content="";
//预约订单===============================================
$(".orderBtn").on("click",function(){
	$(this).addClass("activeLeft");
	$(".chargeBtn").removeClass("activeRight");
	$(".chargeBlock").hide();

	$(".orderBlock").show();
	
})
var flag=1;
//充电订单==============================================================================
function chargeList(){
	$(".chargeBtn").addClass("activeRight");
	$(".orderBtn").removeClass("activeLeft");
	$(".orderBlock").hide();
	$(".chargeBlock").show();
	//充电订单加载=========================	
	var url="/itf/electric/chargeOrderList";
	var charge="";
	var info="";
	if(flag==1){
		$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			userId:userId
		},
		success:function(req){
			var data=req.data;
			//alert(JSON.stringify(data));
			if(req.status==100){
				//alert(JSON.stringify(data));
				if(data.length==0){
					$(".charging").hide();
					$("#CHfinished").hide();
					$(".chargeMessage").show();
	//				$(".orderBlock").hide();
					$(".chargeMessage").html("暂无充电记录");
				}else{
					for(var i=0;i<data.length;i++){
						//alert(data[i].bespBespokestatus);
						var chOr_ChargingStatus=data[i].chOr_ChargingStatus;
	//					var CHdetailTime=data[i].begin_charge_time;
	//					var CHdetailTimeValue=new Date(parseInt(CHdetailTime)).format("yyyy-MM-dd hh:mm:ss");
						if(chOr_ChargingStatus==1){
							$(".charging").show();
							$(".charging p").show();
//							充电中的代码段======================
//							页面时间显示处理
							var chargingBeginTime=data[i].begin_charge_time;
							var chargingBeginTimeValue=chargingBeginTime.slice(11,16);
							charge='<div class="chargingDetail" data-id="'+data[i].pk_ChargingOrder
							+'" data-userId="'+userId
							+'"><div class="elPi_ElectricPileName">'+data[i].elPi_ElectricPileName
							+'</div><div class="chargingTime"><span>正在充电，开始时间：</span><span>'+chargingBeginTimeValue
							+'</span></div><div class="elPi_ElectricPileAddress">'+data[i].elPi_ElectricPileAddress
							+'</div></div>';
							$(".charging").append(charge);	
						}else if(chOr_ChargingStatus==2){
							$("#CHfinished").show();
//							充电结束的代码段====================
//							页面时间显示处理
							var beginTime=data[i].begin_charge_time.replace(/\-/g,'.');
							var beginTimeValue=beginTime.substring(0,beginTime.length-3);
							var endTime=data[i].end_charge_time.slice(11,16);
							info='<div class="finishedDetail" data-id="'+data[i].pk_ChargingOrder
							+'"><div class="CelectricName">'+data[i].elPi_ElectricPileName
							+'</div><div class="CtotalTime"><span>'+beginTimeValue
							+'</span><span> - </span><span>'+endTime
							+'</span></div><div class="CelectricAddress">'+data[i].elPi_ElectricPileAddress
							+'</div></div>';
							$("#CHfinished").append(info);
						}else if(chOr_ChargingStatus!=1&&chOr_ChargingStatus!=2){
							$(".chargeMessage").show();
							$(".chargeMessage").html("暂无充电记录");
							$(".charging").hide();
							$("#CHfinished").hide();
						}
					}
				}
				
			}
			
		}
	});
	flag=0;
	}else{
		return;
	}

}


var type=getUrlParam("type");
if(type=="charge"){
//	$(".chargeBtn").click();
	chargeList();
}else if(type=="order"){
	$(".orderBtn").click();
}

//跳转预约中明细===============================================================
$("body").on("click",".epName,.bespCreateDate,.epAddress",function(){
	var bespResepaymentcode=$(this).attr("data-id");
	var pkBespoke=$(this).attr("data-pkBespoke");
	setSValue("pkBespoke",pkBespoke);
	toPage("order_detail.html");
});
//去续约按钮==============================================================
$("body").on("click","#toReOrder",function(){
	var bespBespoketimeValue=$(this).attr("data-bespBespoketime");
	if(bespBespoketimeValue<360){
		toPage("orderPage.html");
	}else{
		mui.toast("预约时间不能超过6小时");
	}
	
})

//跳转到充电中===============================================================
$("body").on("click",".chargingDetail",function(){
	var userId=$(this).attr("data-userId");
//	alert(pk_ChargingOrder)
	toPage("charging_detail.html?userId="+userId);
});
//预约已完成点击==============================================================

$("body").on("click",".bespCreateFinishedDetail",function(){
	var bespResepaymentcode=$(this).attr("data-id");
	var pkBespoke=$(this).attr("data-val");
	setSValue("pkBespoke",pkBespoke);
	//alert(pkBespoke);
	toPage("orderDetailFinally.html");
});
//充电已完成点击=============================================================

$("body").on("click",".finishedDetail",function(){
	var pk_ChargingOrder=$(this).attr("data-id");
//	alert(pk_ChargingOrder)
	setSValue("pkChargingorder",pk_ChargingOrder)
	toPage("chargingDetailLast.html");
});

//去我的首页
//function toMyPage(){
//	toPage("myPage.html");
//}
//去首页
function toMapIndex(){
	toPage("map_index.html");
}
//去钱包页面==========================================
$("#myWallet").on("tap click",function(){
	checkUserLogin("myWallet.html");
	setSValue("checkPage","orderList");
})