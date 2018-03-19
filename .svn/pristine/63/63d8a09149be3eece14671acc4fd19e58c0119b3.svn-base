/* center modal */
var waitUI;
var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;
var bespElectricpileid=getSValue("elictricPileId");
var bespElectricpilehead="";
var bespBespokeprice=getSValue("raIn_ReservationRate");//预约单价
var pkBespoke=getSValue("pkBespoke");
var bespBespoketime=getSValue("bespBespoketime");
var lng=120.12823;
var lat=30.271116;
var park_lock;
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

function centerModals(){
    $('.modal').each(function(i){
        var $clone = $(this).clone().css('display', 'block').appendTo('body');
        var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 3);
        top = top > 0 ? top : 0;
        $clone.remove();
        $(this).find('.modal-content').css("margin-top", top);
    });
}
$('.modal').on('show.bs.modal', centerModals);
$(window).on('resize', centerModals);
//一进页面获取详情值
pageDetail();
function pageDetail(){
		loadStart();
		//页面获取预约详情的值
		var url="/itf/electric/detailBespoke";
		$.ajax({
			type:"get",
			url:getRootPath()+url,
			async:true,
			dataType:"json",
			data:{
				pkBespoke:pkBespoke
			},
			success:function(req){
		//		$("#waitLoad").hide();
				loadStop();
				//alert(JSON.stringify(req));
				if(req.status==100){
					var datas=req.data;
					bespElectricpilehead=datas.bespElectricpilehead;
//					电站id
					elPi_RelevancePowerStation=datas.elPi_RelevancePowerStation;
//					电桩id
					pk_ElectricPile=datas.pk_ElectricPile;
//					alert(elPi_RelevancePowerStation+":::"+pk_ElectricPile);
		//			alert(JSON.stringify(datas));
					bespBespoketime=datas.bespBespoketime/60;
					unitPrice=datas.unitPrice*60;
					//alert(unitPrice.toFixed(2))
		//			订单编号
					var bespResePaymentCode=datas.bespResepaymentcode;
					$("#bespResePaymentCode").html(bespResePaymentCode);
		//			冻结金额
					var bespFrozenamt=datas.bespFrozenamt;
					$("#bespFrozenamt").html(bespFrozenamt);
		//			下单时间
					var bespBeginTime=datas.bespBeginTime;
					var orderTime=new Date(parseInt(bespBeginTime)).format("hh:mm:ss");
		//			var orderTime=new Date(parseInt(bespBeginTime)).format("yyyy-MM-dd hh:mm:ss");
					$("#bespBespoketime").html(orderTime);
		//		   下单日期
					var chargeDate=new Date(parseInt(bespBeginTime)).format("yyyy-MM-dd");
					var chargeDateValue=chargeDate.replace(/\-/g,'.');
					$("#chargeDate").html(chargeDateValue);
					var bespEndTime=datas.bespEndTime;
					//alert(new Date(bespEndTime).format("yyyy-MM-dd hh:mm:ss"));
					
		//			预约地址
					var epAddress=datas.epAddress;
					$("#epAddress").html(epAddress);
					
		//			电站名称
					var epName=datas.epName;
					$("#epName").html(epName);	
					$("#chargePoint").html(epName);
		//			桩号码			
					var ep_num=datas.ep_num;
					$("#ep_num").html(ep_num);
		//			快慢充
					var chargingMode=datas.chargingMode;
					if(chargingMode==5){
						$("#chargingMode").html("快充");
					}else if(chargingMode==14){
						$("#chargingMode").html("慢充");
					}
		//			powerInterface
					var powerInterface=datas.powerInterface;
					if(powerInterface==7){
						$("#powerInterface").html("国标");
					}else if(powerInterface==19){
						$("#powerInterface").html("美标");
					}else if(powerInterface==20){
						$("#powerInterface").html("欧标");
					}
		//			枪口名称
					var ePHeElectricpileHeadName=datas.ePHeElectricpileHeadName;
					$("#ePHeElectricpileHeadName").html(ePHeElectricpileHeadName);
		//			车位号			
					park_num=datas.park_num;
					$("#park_num").html(park_num);
					setSValue("park_num",park_num);
		//			电桩编号
				    elPi_ElectricPileCode=datas.elPi_ElectricPileCode;
					setSValue("elPi_ElectricPileCode",elPi_ElectricPileCode);
					
		//			枪口号
					eleHeadNum=datas.eleHeadNum;
					setSValue("eleHeadNum",eleHeadNum);
		//			桩的经纬度
					elPi_Longitude=datas.elPi_Longitude;
					setSValue("elPi_Longitude",elPi_Longitude);
					
					
					elPi_Latitude=datas.elPi_Latitude;
					setSValue("elPi_Latitude",elPi_Latitude);
					
		//			是否有led灯 1有
					led_flash=datas.led_flash;
					setSValue("led_flash",led_flash);
		//			是否有地锁
					park_lock=datas.park_lock;
					setSValue("park_lock",park_lock);
					displayFallLock();
					getTime(bespEndTime);
		//			setInterval(getTime,1000);
				}
			}
		});
}
function getTime(bespEndTime){
//		倒计时
	var time=new Date(bespEndTime).getTime()-new Date().getTime();
	var hour=Math.floor(time/1000/60/60);
	$("#hour").html(hour);
	var Minutes=Math.floor((time-hour*3600000)/60000);
	$("#min").html(Minutes);
}

$("#certain2").click(function(){
	$("#myModal2").modal("hide");
	toPage("orderDetailFinally.html?pkBespoke="+pkBespoke);
})

//去取消预约==================================================================
function toCancelOrder(){
	loadStart();
	var url="/itf/electric/cancel";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			pkBespoke:pkBespoke,
			bespElectricpilehead:bespElectricpilehead,
			uId:userId,
			did:deviceId
		},
		success:function(req){
			loadStop();
			if(req.status==100){
				$("#myModal2").modal('show');
				refreshUserAB();
				var datas=req.data;
//				alert(JSON.stringify(datas));
				var currentConsume=datas.consumamt;
				$("#currentConsume").html(currentConsume);
				var userBalance=datas.balance;
				$("#userBalance").html(userBalance);
	
			}else{
				mui.toast(req.msg);
//				alert(req.status);
			}
		}
	});
}
//去续约=======================================================
function toRePkBespoke(){
	if(bespBespoketime==6){
		mui.toast("预约时间不能超过6小时");
	}else{
		toPage("orderPage.html");
	}
}
//返回
function toHistoryPage(){
	if(elPi_RelevancePowerStation&&elPi_RelevancePowerStation!=0){
			setSValue("powerStationId",elPi_RelevancePowerStation);
	//		alert(elPi_RelevancePowerStation);
			toPage("smartDetail.html");
	//		toPage("smartDetail.html?userId="+userId+"&elPi_RelevancePowerStation="+elPi_RelevancePowerStation);
		}
		else if(elPi_RelevancePowerStation==""||elPi_RelevancePowerStation==0){
	//		alert(11)
			if(pk_ElectricPile){
				setSValue("electricPileId",pk_ElectricPile);
		//		setSValue("electricPileId",pk_ElectricPile);
				toPage("pileDetail.html");
		//		toPage("pileDetail.html?userId="+userId+"&pk_ElectricPile="+pk_ElectricPile);
			}
		}
}
//去开led灯   ================================
function toLightOn(){
	if(led_flash==1){
		// $("#lightOn").addClass("lightOn");
		// $("#lightOn").removeClass("lightOff");
		var url="/itf/electric/ledControl";
		$.ajax({
			type:"get",
			url:getRootPath()+url,
			async:true,
			dataType:"json",
			data:{
				epCode:elPi_ElectricPileCode,
				type:1,
				uid:userId,
				lat:lat,
				lng:lng,
				eplat:elPi_Latitude,
				eplng:elPi_Longitude
			},
			success:function(req){
				//alert(JSON.stringify(req));
				if(req.status==100){
					mui.toast(req.msg);
				}
			}
		});
	}else{
		mui.toast("此桩体无LED灯功能");
	}
}

//$("#lightOn").on("touchstart",function(){
//		 $("#lightOn").addClass("lightOff");
//		 $("#lightOn").removeClass("lightOn");
//})
//$("#lightOn").on("touchend",function(){
//		 $("#lightOn").removeClass("lightOff");
//		 $("#lightOn").addClass("lightOn");
//})
//去降地锁 ================================
function toFallLock(){
		toFallLockStyle();
		var url="/itf/electric/downParkLock";
		$.ajax({
			type:"get",
			url:getRootPath()+url,
			async:true,
			dataType:"json",
			data:{
				epCode:elPi_ElectricPileCode,
				headNum:eleHeadNum,
				parkNum:park_num,
				uid:userId,
				lat:lat,
				lng:lng,
				eplat:elPi_Latitude,
				eplng:elPi_Longitude
			},
			success:function(req){
//				alert(JSON.stringify(req));
				if(req.status==100){
					mui.toast(req.msg);
				}
			}
		});
}
//若桩体无地锁时,地锁按钮隐藏
function displayFallLock(){
	var park_lock=getSValue("park_lock");
//	alert(park_lock);
	if(park_lock!=1){
		$("#fallLock").hide();
		$("#lightOn").css("margin-left","25%");
	}
}


//去降地锁样式=======
function toFallLockStyle(){
		$("#fallLock").addClass("fallLock");
		$("#fallLock").removeClass("fallLock_on");
		$("#fallLock").html("正在降锁");
		$("#fallLock").removeAttr("onclick");
		$(".timeDown").show();
	timer=setInterval("run();", 1000);
}
 function run(){
	var s = document.getElementById("seconds");
	if(s.innerHTML == 0){
			$("#fallLock").addClass("fallLock_on");
	    $("#fallLock").removeClass("fallLock");
	    $("#fallLock").html("降地锁");
	    $("#fallLock").attr("onclick","toFallLock()");
	    $(".timeDown").hide();
	    $("#seconds").html(10);
	    window.clearInterval(timer);
			return false;
	}
	s.innerHTML = s.innerHTML * 1 - 1;
}
	
