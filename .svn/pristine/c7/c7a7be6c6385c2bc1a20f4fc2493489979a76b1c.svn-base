var user = getUserInfo();
var userId = user ? user.pkUserinfo : 0;
var userAccount = user ? user.usinPhone : "";
var pkElectricPile = getSValue("pkElectricPile");

// var pkUserinfo=8;
var scantype = 1;
$(".loginPage").hide();
// var img = null;
// var blist = [];
// //var m="http://eichong.com/qrcode?type=cdz&code=3301851010000007&q=1";
// var lastR="";
// var x;
// var obj=null;
// function scaned( t, r, f ) {
// var li=null,hl = document.getElementById("history");
// if ( blist.length > 0 ) {
// li = document.createElement("li");
// li.className = "ditem";
// hl.insertBefore( li, hl.childNodes[0] );
// } else {
// li = document.getElementById("nohistory");
// }
// // li.id = blist.length;
// var html = '<div class="hdata">';
// html += r;
// html += '</div>';
//
//obj = {
//	elpiElectricpilecode : getParam("code", r),
//	ePHeElectricpileHeadId : getParam("q", r)
//};
// loadInfo();
// blist[blist.length] = {type:t,result:r,file:f};
// return obj;
// };
//
// function getParam(name,url) {
// var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
// var params=decodeURI(url);
// var r = params.substr(1).match(reg);
// if(r!=null)return r[2]; return null;
// }

loadInfo();

function loadInfo() {
	var barcodeUrl = "/itf/electric/selectPileInfo";
	var obj = {
		elpiElectricpilecode : getSValue("elpiElectricpilecode"),
		ePHeElectricpileHeadId : getSValue("ePHeElectricpileHeadId")
	};
	//alert(JSON.stringify(obj));
	$.ajax({
		type : "get",
		url : getRootPath() + barcodeUrl,
		async : true,
		dataType : "json",
		data : obj,
		success : function(req) {
			// alert(JSON.stringify(req));
			if (req.status == 100) {
				// alert(req.data);
				var datas = req.data;
				// alert(JSON.stringify(datas));
				// 地址部分
				var elpiElectricpileaddress = datas.elpiElectricpileaddress;
				// alert(elpiElectricpileaddress)
				$("#elpiElectricpileaddress").html(elpiElectricpileaddress);
				// 充电方式
				var elPiChargingMode = datas.elPiChargingMode;
				$("#elPiChargingMode").html(elPiChargingMode);
				// 充电接口
				var elPiPowerInterface = datas.elPiPowerInterface;
				$("#elPiPowerInterface").html(elPiPowerInterface);
				// 枪口编号
				ePHeElectricpileHeadId = datas.ePHeElectricpileHeadId;
				$("#ePHeElectricpileHeadId").html(ePHeElectricpileHeadId);
				// 电桩额定功率
				var elPiPowerSize = datas.elPiPowerSize;
				$("#elPiPowerSize").html(elPiPowerSize);
				// 电桩id
				pkElectricPile = datas.pkElectricpile;
				// 桩体编号
				elpiElectricpilecode = datas.elpiElectricpilecode;
				setSValue("pkElectricPile", pkElectricPile);
				setSValue("ePHeElectricpileHeadId", ePHeElectricpileHeadId);
				setSValue("elpiElectricpilecode", elpiElectricpilecode);
			}

		}
	});
}

// alert(result);

function returnPre() {
	toPage("map_index.html");
}

function toCharge() {
	checkUserLogin("prepare_recharge.html");
}
