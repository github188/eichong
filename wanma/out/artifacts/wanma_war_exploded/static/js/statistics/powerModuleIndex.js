/** ***********************************************数据加载start************************************************************** */
var powerInfoParams = {
		epCode : $("#eCode").val(),
		headId : $("#headId").val(),
		epType : $("#eType").val(),
		status : $('#headState').val()
}
var powerIndexData = []
if(navTab.getCurrentPanel().find("#chargeStatusDiv").val() == '3'){
	loadPowerInfoData();
}
/**
 * 加载源端数据
 */
function loadPowerInfoData() {
	$
			.ajax({
				type : 'post',
				url : config.hbaseUrl+"/getBatteryModulesData?headId="
				+ $("#headIdDiv").val() + "&epCode=" + $("#eCode").val()
				+ "&epType=" + $("#eChargeType").val(),
				dataType : "json",
				success : function(datas) {
					var dataInfo = datas.data;
					if (dataInfo != null && dataInfo.length > 0) {
						powerIndexData = dataInfo;
					}
					setPowerInfo();
				}
			});
}

function setPowerInfo() {
	var htmlStr = '';
	for (var i = 0; i < powerIndexData.length; i++) {
		var dataInfo = powerIndexData[i];
		htmlStr += '<div class="BoxLeft"><div class="powerModuleBlock">'
				+ '<span id="">'+(parseInt(dataInfo.index)+1)+'</span>#电源模块 <span class="line"onclick="showPowerChargingLineChart('+dataInfo.index+');"></span>'
				+ '</div><div class="powerModuleStatus">状态:&nbsp;<span class="powerModuleStatus-use">在用</span>'
				+ '</div><ul class="powerModuleDataList">'
				+ '<li>输入A相电压:<span>'+dealNullToInt(dataInfo.av)+'V</span></li>'
				+ '<li>输入B相电压:<span>'+dealNullToInt(dataInfo.bv)+'V</span></li>'
				+ '<li>输入C相电压:<span>'+dealNullToInt(dataInfo.cv)+'V</span></li>'
				+ '<li>输入A相电流:<span>'+dealNullToInt(dataInfo.ac)+'A</span></li>'
				+ '<li>输入B相电流:<span>'+dealNullToInt(dataInfo.bc)+'A</span></li>'
				+ '<li>输入C相电流:<span>'+dealNullToInt(dataInfo.cc)+'A</span></li></ul>'
				+ '<ul class="powerModuleDataList" style="border: 0;">'
				+ '<li>输出电压:<span>'+dealNullToInt(dataInfo.tv)+'V</span></li>'
				+ '<li>输出电流:<span>'+dealNullToInt(dataInfo.tc)+'A</span></li>'
				+ '<li>温度:<span>'+dealNullToInt(dataInfo.tt)+'℃</span></li></ul></div>';
	}
	$("#MyBox").html(htmlStr);
}

function dealNullToInt(val) {
	var valTemp = val;
	if (valTemp) {
		return parseInt(valTemp);
	}
	return 0;
}
