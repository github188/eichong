var winWidth = 0;
var winHeight = 0;
//var countOffs=0;
function findDimensions() // 函数：获取尺寸
{
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;

	// 通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	if (winWidth < 1469) {
		$(".rightTip").hide(500);
		$(".row").animate({
			right : '1%'
		}, 100);
	} else {
		$(".rightTip").show(500);
		$(".row").animate({
			right : '235px'
		}, 100);
	}
}
findDimensions(); // 调用函数，获取数值
window.onresize = findDimensions;

function showMsg() {
	if (!$(".rightTip").is(":hidden")) {
		$(".rightTip").hide(500);
		$(".row").animate({
			right : '1%'
		}, 100);
	} else {
		$(".rightTip").show(500);
		$(".row").animate({
			right : '235px'
		}, 100);
	}

}
var getHeadChargingInfoIntervalId;
/**
 * 获取统计信息
 * 
 * @param province
 */
function loadStatisticsDataByPointId(params) {
	/**
	 * 资产统计
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getAssetStatisticsCountInner.do",
		dataType : "json",
		data : params,
		cache : false,
		success : function(json) {
			var countAc = json.countAc;
			var countDc = json.countDc;
			$("#cdd_dzcount_txt2").html(countAc + countDc);
			$("#ac_txt2").html(countAc);
			$("#dc_txt2").html(countDc);
			$("#pile_head_txt2").html(json.all_head_count);
		}
	});

	/**
	 * 充电情况数量
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getChargeInfoCountInner.do",
		dataType : "json",
		data : params,
		cache : false,
		success : function(json) {
			var all_charge_count = json.all_charge_count;
			var allCharge_c = json.allCharge_c;
			var allCharge_t = json.allCharge_t;
			$("#all_charge_count_txt2").html(
					all_charge_count == null ? 0 : all_charge_count);
			$("#all_charge_c_txt2").html(
					allCharge_c == null ? 0 : parseFloat(allCharge_c)
							.toFixed(2));
			$("#all_charge_time_txt2").html(
					allCharge_t == null ? 0 : (parseFloat(allCharge_t))
							.toFixed(2));
		}
	});
	getHeadChargingInfoF();
}
function getHeadChargingInfoF(){
	if("1" == $("#eType").val()){
		getHeadChargingInfo(params);
	}else{
		getHeadChargingInfo(params1);
	}
	clearInterval(getHeadChargingInfoIntervalId);
	getHeadChargingInfoIntervalId = setInterval("getHeadChargingInfo()", 600000);
}

function getHeadChargingInfo(queryParams){
	/**
	 * 枪头数量
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getHeadInfoCountInner.do",
		dataType : "json",
		data : queryParams,
		cache : false,
		success : function(json) {
			$("#bespoke_head_txt2").html(json.bespoke_count);
			$("#charging_head_txt2").html(json.charging_count);
			$("#free_head_txt2").html(json.free_count);
			$("#error_head_txt2").html(json.error_count);
			$("#off_pile_txt2").html(json.countOff);
		}
	});
}

/**
 * 获取枪头详情
 * 
 * @param params
 */
function loadHeadDetail(params) {
	$.ajax({
		type : 'post',
		url : basepath + "/admin/electricPileMonitorV2/headDetail.do",
		dataType : "json",
		data : params,
		success : function(json) {
			
			makeHeadDetail(json);
		}

	});
}

/**
 * 组装枪头详情信息
 * 
 * @param headDetailHtml
 */
function makeHeadDetail(json) {
	$("#headDetailFatherDiv").html("");//先清空
	var headDetailHtml = '<div class="pileList"><ul>';
	$("#headDetailFatherDiv").append(headDetailHtml);
	var countLi = 0;
	var countUl = 0;
	var pileIndex = 0;
	var pileId = 0;
	var flagDiv=0;
	var flagUl=0;
//	debugger;
	var enames=$("#enames").val();
	//加个
	for (var i = 0; i < json.length; i++) {
		var jsonData = json[i];
		var color = ""
		var stateHtml = "";
		var chargeMode = "";
		if(jsonData.comm_status==0){
			color = "gray";
			stateHtml = "断开";
		}else {if (jsonData.ePHe_ElectricpileHeadState == '0') {
			color = "green";
			stateHtml = "空闲";
		} else if (jsonData.ePHe_ElectricpileHeadState == '3') {
			color = "blue";
			stateHtml = "预约";
		} else if (jsonData.ePHe_ElectricpileHeadState == '17') {
			color = "blue";
			stateHtml = "等待充电";
		}else if (jsonData.ePHe_ElectricpileHeadState == '6') {
			color = "yellow";
			stateHtml = "充电";
		} else if (jsonData.ePHe_ElectricpileHeadState == '9') {
			color = "red";
			stateHtml = "故障";
		}
		}
		var headId = jsonData.ePHe_ElectricpileHeadId;
		var epCode = jsonData.elPi_ElectricPileCode;
		var eChargeType = jsonData.elPi_ChargingMode;
		var headState = jsonData.ePHe_ElectricpileHeadState;
		var headName =jsonData.ePHe_ElectricpileHeadName;
		var titleName =jsonData.ePHe_ElectricpileHeadName+"-"+jsonData.ep_num+"号桩-"+chargeMode+"-"+enames;;
		//var soc =0;
		if(eChargeType==5&&headState==6&&jsonData.comm_status!=0){
			 $.ajax({
				 	type : 'post',
					url : config.hbaseUrl + "/getRealtimeData?headId="
							+ headId + "&epCode=" + epCode
							+ "&epType=" + eChargeType,
					dataType : "json",
					async: false,
					success : function(datas) {
						 var soc =0;
						 var stateHtml;
							var json = datas;
							if(json.soc){
								soc=json.soc;
								color = "yellow";
								stateHtml = '充电';
							}else{
								color = "green";
								stateHtml = '空闲';
							}
							chargeMode = "直流";
								headDetailHtml = '<li onclick=jumpToMonitorDetail('
									+ headId + ','+ eChargeType + ','+ headState + ',"'
									+ epCode + '",'+ jsonData.pk_ElectricpileHead +',"'			
									+ titleName +'") class="' + color + '">'
									+ '<p class="pileNum">' + '<span class="f18" id="">'
									+ jsonData.ep_num + '</span><span class="f18">#桩</span>'
									+ '<span id="">' + chargeMode + '</span> <span id="">'
									+ jsonData.ePHe_ElectricpileHeadName + '</span>' + '</p>'
									+ '<p class="power">'
									+ '<span id="" style="margin-left: -5%">额定功率:'
									+ jsonData.elPi_PowerSize + '</span>' + '</p>'
									+'<p class="power"><span>SOC:'+soc+'%</span></p>'
									+ '<p class="pileStatus f18-l1">' + stateHtml + '</p>'
									+ '</li>';
								if (pileId != jsonData.pk_ElectricPile) {
									pileId = jsonData.pk_ElectricPile;
									pileIndex++;
								}
								if(countLi<4){
									$("#headDetailFatherDiv").children().eq(flagDiv).children().eq(flagUl).append(headDetailHtml);
								}
								if(countLi==4){
									if(countUl==8){
										headDetailHtml = '<div class="pileList"><ul>'+headDetailHtml;
										$("#headDetailFatherDiv").append(headDetailHtml);
										countUl = 0;
										flagDiv++;
										countLi = 0;
										flagUl =0;
									}else{
										headDetailHtml='<ul>'+headDetailHtml;
										$("#headDetailFatherDiv").children().eq(flagDiv).append(headDetailHtml);
										countLi = 0;
										flagUl++;
									}
								}
								countLi++;
								countUl++;
					}
				});
			 }else if(eChargeType==14){
				 chargeMode = "交流";
				 headDetailHtml = '<li onclick=jumpToMonitorDetail('
						+ headId + ','+ eChargeType + ','+ headState + ',"'
						+ epCode + '",'+ jsonData.pk_ElectricpileHead +',"'			
						+ titleName +'") class="' + color + '">'
						+ '<p class="pileNum">' + '<span class="f18" id="">'
						+ jsonData.ep_num + '</span><span class="f18">#桩</span>'
						+ '<span id="">' + chargeMode + '</span> <span id="">'
						+ jsonData.ePHe_ElectricpileHeadName + '</span>' + '</p>'
						+ '<p class="power">'
						+ '<span id="" style="margin-left: -5%">额定功率:'
						+ jsonData.elPi_PowerSize + '</span>' + '</p>'
						+'<p class="power"><span></span></p><p class="pileStatus f18-l1">' 
						+ stateHtml + '</p></li>';
				 if (pileId != jsonData.pk_ElectricPile) {
						pileId = jsonData.pk_ElectricPile;
						pileIndex++;
					}
				 	if(countLi<4){
						$("#headDetailFatherDiv").children().eq(flagDiv).children().eq(flagUl).append(headDetailHtml);
					}
				 	if(countLi==4){
						if(countUl==8){
							headDetailHtml = '<div class="pileList"><ul>'+headDetailHtml;
							$("#headDetailFatherDiv").append(headDetailHtml);
							countUl = 0;
							flagDiv++;
							countLi = 0;
							flagUl =0;
						}else{
							headDetailHtml='<ul>'+headDetailHtml;
							$("#headDetailFatherDiv").children().eq(flagDiv).append(headDetailHtml);
							countLi = 0;
							flagUl++;
						}
					}
					countLi++;
					countUl++;
			 }else{
				 chargeMode = "直流";
					headDetailHtml = '<li onclick=jumpToMonitorDetail('
						+ headId + ','+ eChargeType + ','+ headState + ',"'
						+ epCode + '",'+ jsonData.pk_ElectricpileHead +',"'			
						+ titleName +'") class="' + color + '">'
						+ '<p class="pileNum">' + '<span class="f18" id="">'
						+ jsonData.ep_num + '</span><span class="f18">#桩</span>'
						+ '<span id="">' + chargeMode + '</span> <span id="">'
						+ jsonData.ePHe_ElectricpileHeadName + '</span>' + '</p>'
						+ '<p class="power">'
						+ '<span id="" style="margin-left: -5%">额定功率:'
						+ jsonData.elPi_PowerSize + '</span>' + '</p>'
						+'<p class="power"><span>SOC:'+0+'%</span></p>'
						+ '<p class="pileStatus f18-l1">' + stateHtml + '</p>'
						+ '</li>';
					if (pileId != jsonData.pk_ElectricPile) {
						pileId = jsonData.pk_ElectricPile;
						pileIndex++;
					}
					if(countLi<4){
						$("#headDetailFatherDiv").children().eq(flagDiv).children().eq(flagUl).append(headDetailHtml);
					}
					if(countLi==4){
						if(countUl==8){
							headDetailHtml = '<div class="pileList"><ul>'+headDetailHtml;
							$("#headDetailFatherDiv").append(headDetailHtml);
							countUl = 0;
							flagDiv++;
							countLi = 0;
							flagUl =0;
						}else{
							headDetailHtml='<ul>'+headDetailHtml;
							$("#headDetailFatherDiv").children().eq(flagDiv).append(headDetailHtml);
							countLi = 0;
							flagUl++;
						}
					}
					countLi++;
					countUl++;
			 }
	}
}

function jumpToMonitorDetail(headId, chargeModel, status, eCode, pkHeadId,titleName) {
	var url = '';
	if (chargeModel == '5') {
		url = basepath
		+ "/admin/electricPileMonitorV2/headNowDataUiDC.do?headId="
		+ headId + "&status=" + status + "&eCode=" + eCode + "&eType="
		+ chargeModel + "&pkHeadId=" + pkHeadId+ "&titleName=" + titleName;
	} else if (chargeModel == '14') {
		url = basepath
				+ "/admin/electricPileMonitorV2/headNowDataUiAC.do?headId="
				+ headId + "&status=" + status + "&eCode=" + eCode + "&eType="
				+ chargeModel + "&pkHeadId=" + pkHeadId+ "&titleName=" + titleName;
	} else {
		return;
	}
	navTab.closeTab("headMonitorChargingTab")
	navTab.closeTab("headMonitorHistoryTab")
	clearInterval(getHeadChargingInfoIntervalId);
	navTab.openTab("headMonitorNowTab", url, {
		title : "实时信息",
		fresh : true
	});
}

function setRefreshTime() {
	var mydate = new Date();
	var mytime = mydate.toLocaleTimeString();
	$("#systemTime").html(mytime);
}
var params = {};
var eId = $("#eId").val();
var eType = $("#eType").val();
params.eId = eId;
params.eType = eType;
var params1 = {};
if (eType == 1) {
	params1.eId = eId;
} else {
	params1.pId = eId;
}
loadHeadDetail(params);
loadStatisticsDataByPointId(params1);
setRefreshTime();
$("#refreshHeadDetail").click(function() {
	loadHeadDetail(params);
	loadStatisticsDataByPointId(params1);
	setRefreshTime();
	$this = $(this);
})
