
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

//var userId=getUrlParam("userId");
var serialno="";
var ep_code="";
var PK_ElectricPile="";
var ep_gun_no="";

//var pkElectricPile=getSValue("pkElectricPile");
////桩体编号
//var elpiElectricpilecode=getSValue("elpiElectricpilecode");
////枪头编号
//var ePHeElectricpileHeadId=getSValue("ePHeElectricpileHeadId");


//checkUserLogin();
var waitUI;
var user=getUserInfo();
var userId=user.pkUserinfo;

chargeDetail();
function chargeDetail(){
	var url="/itf/charge/getChangeInfoN";
	//alert(userId)
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			pkUserinfo:userId
		},
		success:function(req){
			//alert(JSON.stringify(req));
			if(req.status==100){
				var datas=req.data;
				//alert(JSON.stringify(datas));
				var chargedTime=datas[0].chargedTime;
				//alert(chargedTime)
				$("#chargedTime").html(chargedTime);
				var fronzeAmt=datas[0].fronzeAmt;
				$("#fronzeAmt").html(fronzeAmt);
				var chargePrice=datas[0].chargePrice;
				$("#chargePrice").html(chargePrice);
				var chargedMeterNum=datas[0].chargedMeterNum;
				$("#chargedMeterNum").html(chargedMeterNum);
				var elec_type=datas[0].elec_type;
	//			已充金额字段
				var chargedCost=datas[0].chargedCost;
				$("#chargedCost").html(chargedCost);
				//alert(chargedCost)
	//			拿到soc的值
	//			var soc=datas[0].soc;
	//			$("#soc").html(soc);
				//alert(elec_type);==1是直流
	//			实时充电流水号码
				serialno=datas[0].serialno;
				//alert(serialno)
				setSValue("serialno",serialno);
				//alert(serialno)
	//			电桩id
				PK_ElectricPile=datas[0].PK_ElectricPile;
				setSValue("PK_ElectricPile",PK_ElectricPile);
	//			桩体编号
				ep_code=datas[0].ep_code;
	//			枪口id
				ep_gun_no=datas[0].ep_gun_no;
	//			if(elec_type==1){
	//				$(".SOC").show();
	//			}else{
	//				$(".SOC").hide();
	//			}
				
				
				
			}
			
		}
	});
//	setInterval(chargeDetail,5000);
}
setInterval(chargeDetail,1000*60);


//去结束充电
function toChargeEnd(){
//	$("#waitLoad").show();
	loadStart();
	var url="/itf/charge/finishCharge";
	//alert(userId)
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			pkElectricPile:PK_ElectricPile,
			elpiElectricpilecode:ep_code,
			ePHeElectricpileHeadId:ep_gun_no,
			did:deviceId,
			uid:userId
		},
		success:function(req){
//			$("#waitLoad").hide();
			loadStop();
			//alert(JSON.stringify(req));
			if(req.status==100){
				toPage("chargingDetailFinally.html");
				mui.toast(req.msg);
			}
			
		}
	});
}
