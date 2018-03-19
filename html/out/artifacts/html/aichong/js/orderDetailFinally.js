//页面获取预约详情的值
var pkBespoke=getSValue("pkBespoke");
var ePHeElectricpileHeadState="";
var elPi_RelevancePowerStation="";
//电桩id
var pk_ElectricPile="";
//枪头名称
var ePHeElectricpileHeadName="";
var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;
//var bespElectricpileid=getSValue("elictricPileId");
var bespElectricpileid=getSValue("electricPileId");
var bespElectricpilehead=getSValue("pileHeadId");
var bespBespokeprice=getSValue("raIn_ReservationRate");//预约单价

var bespBespoketime=getSValue("bespBespoketime");
//设置pkBespoke1目的是使得再次预约返回时仍然有数据
var pkBespoke1=getSValue("pkBespoke1");
if(!pkBespoke){
	pkBespoke=pkBespoke1;
}


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
		//alert(JSON.stringify(req));
		if(req.status==100){
			var datas=req.data;
			//alert(JSON.stringify(datas));
//			枪头状态==========================
			ePHeElectricpileHeadState=datas.ePHeElectricpileHeadState;
			elPi_RelevancePowerStation=datas.elPi_RelevancePowerStation;
			pk_ElectricPile=datas.pk_ElectricPile;
//			枪头id
			bespElectricpilehead=datas.bespElectricpilehead;
//			枪头名称
			
//			订单编号
			var bespResePaymentCode=datas.bespResepaymentcode;
			//alert(bespResePaymentCode)
			$("#bespResePaymentCode").html(bespResePaymentCode);
//			冻结金额
			var bespFrozenamt=datas.bespFrozenamt;
			$("#bespFrozenamt").html(bespFrozenamt);
//			预约开始时间
			var bespBeginTime=datas.bespBeginTime;
			var orderTime=new Date(parseInt(bespBeginTime)).format("yyyy-MM-dd hh:mm:ss");
			$("#bespBespoketime").html(orderTime);
//			预约结束时间
			var bespEndTime=datas.bespEndTime;
			var endTime=new Date(bespEndTime).format("yyyy-MM-dd hh:mm:ss");
			$("#endTime").html(endTime);
//			本次消费
			var bespBespoketime=datas.bespBespoketime;
			var unitPrice=datas.unitPrice;
			//var expense=(bespBespoketime*unitPrice).toFixed(2);
			$("#expense").html(bespFrozenamt);
//			预约地址
			var epAddress=datas.epAddress;
			$("#epAddress").html(epAddress);
//			电站名称
			var epName=datas.epName;
			$("#epName").html(epName);	
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
		    ePHeElectricpileHeadName=datas.ePHeElectricpileHeadName;
			$("#ePHeElectricpileHeadName").html(ePHeElectricpileHeadName);
//			车位号			
			var park_num=datas.park_num;
			$("#park_num").html(park_num);
		}
	}
});

//再次预约====a)	系统判断：若可预约，则点击直接进入预约界面；
//			  b)若不可预约，则点击链接到充电点详情页。

//alert(ePHeElectricpileHeadName)
function reOrder(){
	
	setSValue("pkBespoke1",pkBespoke);
	setSValue("pkBespoke","");
//	if(ePHeElectricpileHeadState==0){
//		setSValue("bespBespoketime","");
//		toPage("orderPage.html");
//	}else 
//	alert(111)
	if(elPi_RelevancePowerStation&&elPi_RelevancePowerStation!=0){
		setSValue("powerStationId",elPi_RelevancePowerStation);
//		alert(elPi_RelevancePowerStation);
		toPage("smartDetail_reOrder.html");
//		toPage("smartDetail.html?userId="+userId+"&elPi_RelevancePowerStation="+elPi_RelevancePowerStation);
	}
	else if(elPi_RelevancePowerStation==""||elPi_RelevancePowerStation==0){
//		alert(11)
		if(pk_ElectricPile){
			setSValue("electricPileId",pk_ElectricPile);
	//		setSValue("electricPileId",pk_ElectricPile);
			toPage("pileDetail_reOrder.html");
	//		toPage("pileDetail.html?userId="+userId+"&pk_ElectricPile="+pk_ElectricPile);
		}
	}

}

function toHistoryPage(){
	toPage("map_index.html");
}
function toOrderDetail(){
	checkUserLogin();
	toPage("order_appointment.html?type=order");
}
