//checkUserLogin();
var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;

var chOrTransactionNumber=getSValue("serialno");
var PK_ElectricPile=getSValue("PK_ElectricPile");
var pkChargingorder=getSValue("pkChargingorder");
var url="/itf/electric/chargeOrderList";
//alert(pkChargingorder)
var obj={
	userId:userId,
	coId:pkChargingorder
};
orderHistory();
function orderHistory(){
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:obj,
		success:function(req){
			//alert(JSON.stringify(req));
			if(req.status==100){
				var datas=req.data;
//				alert(JSON.stringify(datas));
	//			订单编号
				var chorCode=datas[0].chOr_Code;
				$("#chorCode").html(chorCode);
//				订单状态
				var chOr_ChargingStatus=datas[0].chOr_ChargingStatus;
				if(chOr_ChargingStatus==1){
					$("#chOr_ChargingStatus").html("待支付");
				}else if(chOr_ChargingStatus==2){
					$("#chOr_ChargingStatus").html("支付成功");
				}else if(chOr_ChargingStatus==3){
					$("#chOr_ChargingStatus").html("充电完成，未支付");
				}
	//			充电电量
				var chorQuantityelectricity=datas[0].chOr_QuantityElectricity;
				$("#chorQuantityelectricity").html(chorQuantityelectricity);
	//			充电电费
				var chorChargemoney=datas[0].chOr_ChargeMoney;
				$("#chorChargemoney").html(chorChargemoney);
//				优惠券减免金额
				var chOr_CouponMoney=datas[0].chOr_CouponMoney;
				$("#chOr_CouponMoney").html(chOr_CouponMoney);
	//			充电服务费
				var chorServicemoney=datas[0].chOr_ServiceMoney;
				$("#chorServicemoney").html(chorServicemoney);
	//			实付金额
				var costMoney=parseFloat(chorChargemoney) + parseFloat(chorServicemoney);
				var costMoneyValue=parseFloat(costMoney).toFixed(2);
				$("#costMoney").html(costMoneyValue);
	//			充电开始时间
				var start=datas[0].begin_charge_time;
				stringToDate(start);
//				var startDate=datas[0].begin_charge_time.replace(/\-/g,'.');
//				var startDateValue=startDate.substring(0,startDate.length-3);
				$("#startDate").html(stringToDate(start));
	//			充电结束时间
				var end=datas[0].end_charge_time;
				var endDate=stringToDate(end);
//				var endDate=datas[0].end_charge_time.replace(/\-/g,'.');
				var endDateValue=endDate.substring(10,endDate.length);
				$("#endDate").html(endDateValue);	
	//			充电点
				var elPi_ElectricPileName=datas[0].elPi_ElectricPileName;
				$("#elPi_ElectricPileName").html(elPi_ElectricPileName);
				$("#chargePointDetail").html(elPi_ElectricPileName);
	//			充电地址
				var elPi_ElectricPileAddress=datas[0].elPi_ElectricPileAddress;
				$("#elPi_ElectricPileAddress").html(elPi_ElectricPileAddress);
				$("#chargePointAddress").html(elPi_ElectricPileAddress);
	//			充电时长
				var time=new Date(end.replace(/-/g, "/"))-new Date(start.replace(/-/g, "/"));
//				alert(time);
				var hour=Math.floor(time/1000/60/60);
				$("#hour").html(hour);
				var Minutes=Math.floor((time-hour*3600000)/60000);
				$("#min").html(Minutes);
				
				var sec=Math.floor((time-hour*60*1000-Minutes*60*1000)/1000);
				$("#sec").html(sec);
			}
			
		}
	});
}
function toOrderDetail(){
	checkUserLogin();
	toPage("order_appointment.html?type=charge");
}
//时间日期格式的处理
function stringToDate(str){
    var tempStrs = str.split(" ");
    var dateStrs = tempStrs[0].split("-");
    var year = parseInt(dateStrs[0], 10);
    var month = parseInt(dateStrs[1], 10);
    var day = parseInt(dateStrs[2], 10);
    var timeStrs = tempStrs[1].split(":");
    var hour = parseInt(timeStrs [0], 10);
    var minute = parseInt(timeStrs[1], 10);
    var second = parseInt(timeStrs[2], 10);
    var result=year+"年"+month+"月"+day+"日 "+hour+"时"+minute+"分";
    return result;
}