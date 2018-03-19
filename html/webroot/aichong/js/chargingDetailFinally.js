var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;

var chOrTransactionNumber=getSValue("serialno");
var PK_ElectricPile=getSValue("PK_ElectricPile");
//$("#waitLoad").show();

//chargeFinally();
function chargeFinally(){
	loadStart();
	var url="/itf/charge/selectChargeData";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			chOrTransactionNumber:chOrTransactionNumber,
			pkUserinfo:userId
		},
		success:function(req){
//			$("#waitLoad").hide();
			loadStop();
			//alert(JSON.stringify(req));
			//alert(req.status);
			if(req.status==100){
				
				var datas=req.data;
	//			订单编号
				var chorCode=datas.chorCode;
				$("#chorCode").html(chorCode);
//				优惠券金额
				var couMoney=datas.couMoney;
				$("#couMoney").html(couMoney);
	//			充电电量
				var chorQuantityelectricity=datas.chorQuantityelectricity;
				$("#chorQuantityelectricity").html(chorQuantityelectricity);
	//			充电服务费
				var chorServicemoney=datas.chorServicemoney;
				$("#chorServicemoney").html(chorServicemoney);
	//			实付金额
				var costPrice=datas.chorMoeny;
				$("#costPrice").html(costPrice);
	//			充电电费
				var chorChargemoney=datas.chorChargemoney;
				$("#chorChargemoney").html(chorChargemoney);
//				alert(chorChargemoney);
	//			充电开始时间
				var startDate=datas.startDate;
				stringToDate(startDate);
//				var startDateValue=new Date(parseInt(startDate)).format("yyyy-MM-dd hh:mm:ss");
				$("#startDate").html(stringToDate(startDate));
	//			充电结束时间
				var endDate=datas.endDate;
				stringToDate(endDate);
//				var endDateValue=new Date(parseInt(endDate)).format("yyyy-MM-dd hh:mm:ss");
				$("#endDate").html(stringToDate(endDate));
//				充电时长
				var time=new Date(endDate.replace(/-/g, "/"))-new Date(startDate.replace(/-/g, "/"));
//				alert(time);
				var hour=Math.floor(time/1000/60/60);
				$("#hour").html(hour);
				var Minutes=Math.floor((time-hour*3600000)/60000);
				$("#min").html(Minutes);
				
				var sec=Math.floor((time-hour*60*1000-Minutes*60*1000)/1000);
				$("#sec").html(sec);
				$("#chargeTime").html(hour+"小时"+Minutes+"分"+sec+"秒");
				
	//			充电订单主键
				pkChargingorder=datas.pkChargingorder;
				setSValue("pkChargingorder",pkChargingorder);
			}
			
		}
	});
}
chargeFinally();
//setTimeout(chargeFinally,3000);
$("#finishedBtn").click(function(){
	toPage("chargingDetailLast.html");
})
$("#evaluateBtn").click(function(){
	toPage("evaluate.html");
})
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