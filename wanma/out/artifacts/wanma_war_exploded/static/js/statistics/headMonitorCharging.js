/** ***********************************************数据加载start************************************************************** */
var chargingData = [ {} ];
var chargingChartData = [ {} ];
var chargingIntervalIdAC
/**
 * 加载充电数据
 */
function loadChargeData() {
	$.ajax({
		type : 'post',
		url : config.hbaseUrl + "/getChargingData?headId="
				+ $("#headIdDiv").val() + "&epCode=" + $("#eCode").val()
				+ "&epType=" + $("#eChargeType").val(),
		dataType : "json",
		// data:jumpUiParams,
		success : function(datas) {
			var dataTemp = datas.data;
			if (dataTemp != null && dataTemp.length > 0) {
				chargingData = dataTemp;
				if (datas.chargingStatus == 3) {
					chargingChartData = dataTemp;
				}
			}
			droweVoltageCharts();
			droweCurrentCharts();
			var headTemperatureE = document.getElementById('headTemperature');
			if (headTemperatureE) {
				droweTemperatureCharts();
			}
			setTableData();
			chargingData = [ {} ];
			chargingChartData = [ {} ];
		}
	});
}
function loadChargeDataF() {
	loadChargeData();
	clearInterval(chargingIntervalIdAC);
	chargingIntervalIdAC = setInterval("loadChargeData()", 180000);
}
loadChargeDataF();

/** ***********************************************数据加载end************************************************************** */

/** ********************************************************充电曲线start**************************************************************** */
var headOutputVoltage;
var headOutputCurrent;
var headTemperature;
require.config({
	paths : {
		echarts : '/wanma/res/echarts/js'
	}
});
var voltageOption = jQuery.extend(true, {}, lineOption);
var currentOption = jQuery.extend(true, {}, lineOption);
var temperatureOption = jQuery.extend(true, {}, lineOption);
/**
 * 实时电压
 */
function droweVoltageCharts() {
	if (headOutputVoltage) {
		headOutputVoltage.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				headOutputVoltage = ec.init(document
						.getElementById('headOutputVoltage'), theme);
				refreshVoltageData();
			});
}

/**
 * 定时刷新电压数据
 */
function refreshVoltageData() {
	var dataX = []
	var dataY = []
	dataX[0] = "0分";
	dataY[0] = dealNullToFloat(chargingChartData[0].voltageValue);
	var dataTimeStart = dealNullToInt(chargingChartData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < chargingChartData.length; i++) {
		var dataTemp = chargingChartData[i];
		var dataTime = dealNullToFloat(dataTemp.ts)
		var differenceTime = parseFloat((dataTime - dataTimeStart)
				/ (1000 * 60));
		if (differenceTime > index * lineSplitNum) {
			index++;
			dataX[index] = index * lineSplitNum + "分"
			dataY[index] = dealNullToFloat(dataTemp.voltageValue)
		}
	}
	voltageOption.series[0].data = dataY;
	voltageOption.xAxis[0].data = dataX;
	headOutputVoltage.setOption(voltageOption, true);
}

/**
 * 实时电流
 */
function droweCurrentCharts() {
	if (headOutputCurrent) {
		headOutputCurrent.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				headOutputCurrent = ec.init(document
						.getElementById('headOutputCurrent'), theme);
				currentOption.legend.data[0] = '实时电流'
				currentOption.series[0].name = '实时电流'
				currentOption.yAxis[0].axisLabel.formatter = '{value}A'
				refreshCurrentData();
			});
}

/**
 * 定时刷新电流数据
 */
function refreshCurrentData() {
	var dataX = []
	var dataY = []
	dataX[0] = "0分";
	dataY[0] = dealNullToFloat(chargingChartData[0].currentValue);
	var dataTimeStart = dealNullToInt(chargingChartData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < chargingChartData.length; i++) {
		var dataTemp = chargingChartData[i];
		var dataTime = dealNullToFloat(dataTemp.ts)
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime > index * lineSplitNum) {
			index++;
			dataX[index] = index * lineSplitNum + "分"
			dataY[index] = dealNullToFloat(dataTemp.currentValue)
		}
	}
	currentOption.series[0].data = dataY;
	currentOption.xAxis[0].data = dataX;
	headOutputCurrent.setOption(currentOption, true);
}

/**
 * 实时温度
 */
function droweTemperatureCharts() {
	if (headTemperature) {
		headTemperature.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				headTemperature = ec.init(document
						.getElementById('headTemperature'), theme);
				temperatureOption.legend.data[0] = '实时温度'
				temperatureOption.series[0].name = '实时温度'
				temperatureOption.yAxis[0].axisLabel.formatter = '{value}C'
				refreshTemperatureData();
			});
}

/**
 * 定时刷新温度数据
 */
function refreshTemperatureData() {
	var dataX = []
	var dataY = []
	dataX[0] = "0分";
	dataY[0] = dealNullToFloat(chargingChartData[0].temperature);
	var dataTimeStart = dealNullToInt(chargingChartData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < chargingChartData.length; i++) {
		var dataTemp = chargingChartData[i];
		var dataTime = dealNullToFloat(dataTemp.ts)
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime > index) {
			index = differenceTime;
			dataX[index] = differenceTime * lineSplitNum + "分"
			dataY[index] = dealNullToFloat(dataTemp.temperature)
		}
	}
	temperatureOption.series[0].data = dataY;
	temperatureOption.xAxis[0].data = dataX;
	headTemperature.setOption(temperatureOption, true);
}
/** ********************************************************充电曲线end**************************************************************** */

/** *********************************************************充电数据采样start***************************************************************************** */
function setTableData() {
	var dataDivStrSom = '';
	var dataDivStr = '';
	var lastState = chargingData[chargingData.length - 1].chargingState;
	var cgStsFilter = [ '0', '2', '8', '9', '11', '12', '30' ];
	if (lastState != null && !isIn(cgStsFilter, lastState)) {
		for (var i = chargingData.length - 1; i >= 0; i--) {
			var dataTemp = chargingData[i];
			if (dealNullToFloat(dataTemp.ts) == "0.0") {
				return;
			}
			var dataTime = new Date(dealNullToFloat(dataTemp.ts))
			// var differenceTime =
			// parseInt((dataTime-dataTimeStart)/(1000*60));
			var h = dataTime.getHours();
			var m = dataTime.getMinutes();
			var s = dataTime.getSeconds();
			if (h < 10) {
				h = "0" + h;
			}
			if (m < 10) {
				m = "0" + m;
			}
			if (s < 10) {
				s = "0" + s;
			}
			var strTemp;
			if (dataTemp.chargingState == 1 || dataTemp.chargingState > 30) {
				strTemp = '<div class="caiyangbiao-02" style="background-color:orange;">'
						+ '<span class="caiyangbiaoW75">'
						+ h
						+ ':'
						+ m
						+ ':'
						+ s
						+ '</span>'
						+ '<span class="caiyangbiaoW75">'
						+ dealNullToNullStr(dataTemp.allChargeValue)
						+ '</span><span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.presentChargeValue)
						+ '</span>'
						+ '<span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.presentChargeFee)
						+ '</span>' + '</div>'
			} else {
				strTemp = '<div class="caiyangbiao-02">'
						+ '<span class="caiyangbiaoW75">' + h + ':' + m + ':'
						+ s + '</span>' + '<span class="caiyangbiaoW75">'
						+ dealNullToNullStr(dataTemp.allChargeValue)
						+ '</span><span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.presentChargeValue)
						+ '</span>' + '<span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.presentChargeFee)
						+ '</span>' + '</div>'

			}
			if (i > chargingData.length - 10) {
				dataDivStrSom += strTemp;
			}
			dataDivStr += strTemp;
		}
	}
	$("#chargeDataSom").html(dataDivStrSom);
	$("#chargeDataAll").html(dataDivStr);
	if (dataDivStr == "") {
		$("#chargeDataAll").css("overflow-y", "");
	} else {
		$("#chargeDataAll").css("overflow-y", "scroll");
	}
}
/** *********************************************************充电数据采样end***************************************************************************** */
function showAll() {
	$("#huadongDiv").css("height", "0px");
	$("#chargeDataAllDiv").show();
}

function closeAll() {
	$("#huadongDiv").css("height", "44px");
	$("#chargeDataAllDiv").hide();
}
