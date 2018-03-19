/** ***********************************************数据加载start************************************************************** */
var powerChargingIntervalId
var powerChargingData = [{}]
/**
 * 加载充电数据
 */
function loadPowerChargeData() {
	$.ajax({
		type : 'post',
		url : config.hbaseUrl + "/getBatteryModuleData?headId="
				+ $("#headIdDiv").val() + "&epCode=" + $("#eCode").val()
				+ "&epType=" + $("#eChargeType").val() + "&index="
				+ $("#index").val(),
		dataType : "json",
		success : function(datas) {
			var jdata = datas.data;
			if (jdata && jdata.length > 0) {
				powerChargingData = jdata;
			}
			drowePowerVoltageCharts();
			drowePowerCurrentCharts();
			drowePowerTemperatureCharts();
		}
	});
}
/** ***********************************************数据加载end************************************************************** */

/** ***********************************************充电曲线************************************************************** */
var powerOutputVoltage;
var powerOutputCurrent;
var powerTemperature;
var powerVoltageOption = jQuery.extend(true, {}, powerLineOption);
var powerCurrentOption = jQuery.extend(true, {}, powerLineOption);
var powerTemperatureOption = jQuery.extend(true, {}, lineOption);
require.config({
	paths : {
		echarts : '/wanma/res/echarts/js'
	}
});
var intervalIdVoltage;
var intervalIdCurrent;
var intervalIdTemperature;
/**
 * 实时电压
 */
function drowePowerVoltageCharts() {
	if (powerOutputVoltage) {
		powerOutputVoltage.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				powerOutputVoltage = ec.init(document
						.getElementById('powerOutputVoltage'), theme);
				var legendData = powerVoltageOption.legend.data;
				var seriesData = powerVoltageOption.series;
				refreshPowerVoltageData();
			});
}

/**
 * 定时刷新电压数据
 */
function refreshPowerVoltageData() {
	var dataX = []
	var dataTY = [];
	var dataAY = [];
	var dataBY = [];
	var dataCY = [];
	dataX[0] = "0分";
	dataTY[0] = dealNullToFloat(powerChargingData[0].tv);
	dataAY[0] = dealNullToFloat(powerChargingData[0].av);
	dataBY[0] = dealNullToFloat(powerChargingData[0].bv);
	dataCY[0] = dealNullToFloat(powerChargingData[0].cv);
	var dataTimeStart = dealNullToFloat(powerChargingData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < powerChargingData.length; i++) {
		var dataTemp = powerChargingData[i];
		var dataTime = dealNullToFloat(dataTemp.ts)
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime > index * lineSplitNum) {
			index++;
			dataX[index] =  index * lineSplitNum + "分"
			dataTY[index] = dataTemp.tv;
			dataAY[index] = dataTemp.av;
			dataBY[index] = dataTemp.bv;
			dataCY[index] = dataTemp.cv;
		}
	}
	var seriesData = powerVoltageOption.series;
	seriesData[0].data = dataTY;
	seriesData[1].data = dataAY;
	seriesData[2].data = dataBY;
	seriesData[3].data = dataCY;
	powerVoltageOption.xAxis[0].data = dataX;
	powerOutputVoltage.setOption(powerVoltageOption);
}
/**
 * 实时电流
 */
function drowePowerCurrentCharts() {
	if (powerOutputCurrent) {
		powerOutputCurrent.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				powerOutputCurrent = ec.init(document
						.getElementById('powerOutputCurrent'), theme);
				var legendData = powerCurrentOption.legend.data;
				var seriesData = powerCurrentOption.series;
				legendData[0] = '输出电流'
				legendData[1] = 'A相电流'
				legendData[2] = 'B相电流'
				legendData[3] = 'C相电流'
				seriesData[0].name = '输出电流'
				seriesData[1].name = 'A相电流'
				seriesData[2].name = 'B相电流'
				seriesData[3].name = 'C相电流'
				powerCurrentOption.yAxis[0].axisLabel.formatter = '{value}A'
				refreshPowerCurrentData();
			});
}

/**
 * 定时刷新电流数据
 */
function refreshPowerCurrentData() {
	var dataX = []
	var dataTY = [];
	var dataAY = [];
	var dataBY = [];
	var dataCY = [];
	dataX[0] = "0分";
	dataTY[0] = dealNullToFloat(powerChargingData[0].tc);
//	dataAY[0] = dealNullToFloat(powerChargingData[0].ac);
//	dataBY[0] = dealNullToFloat(powerChargingData[0].bc);
//	dataCY[0] = dealNullToFloat(powerChargingData[0].cc);
	var dataTimeStart = dealNullToFloat(powerChargingData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < powerChargingData.length; i++) {
		var dataTemp = powerChargingData[i];
		var dataTime = dealNullToFloat(dataTemp.ts)
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime > index * lineSplitNum) {
			index++;
			dataX[index] = index * lineSplitNum + "分"
			dataTY[index] = dataTemp.tc;
//			dataAY[index] = dataTemp.ac;
//			dataBY[index] = dataTemp.bc;
//			dataCY[index] = dataTemp.cc;
		}
	}
	var seriesData = powerCurrentOption.series;
	seriesData[0].data = dataTY;
//	seriesData[1].data = dataAY;
//	seriesData[2].data = dataBY;
//	seriesData[3].data = dataCY;
	powerCurrentOption.xAxis[0].data = dataX;
	powerOutputCurrent.setOption(powerCurrentOption);
}

/**
 * 实时温度
 */
function drowePowerTemperatureCharts() {
	if (powerTemperature) {
		powerTemperature.clear();
	}
	require(
			[ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				powerTemperature = ec.init(document
						.getElementById('powerTemperature'), theme);
				powerTemperatureOption.legend.data[0] = '实时温度'
				powerTemperatureOption.series[0].name = '实时温度'
				powerTemperatureOption.yAxis[0].axisLabel.formatter = '{value}°C'
				refreshPowerTemperatureData();
			});
}

/**
 * 定时刷新电流数据
 */
function refreshPowerTemperatureData() {
	var dataX = []
	var dataY = []
	dataX[0] = "0分";
	dataY[0] = powerChargingData[0].tt;
	var dataTimeStart = dealNullToFloat(powerChargingData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < powerChargingData.length; i++) {
		var dataTemp = powerChargingData[i];
		var dataTime = dealNullToFloat(dataTemp.ts)
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime >  index * lineSplitNum) {
			index++;
			dataX[index] = index * lineSplitNum + "分"
			dataY[index] = dataTemp.tt
		}
	}
	powerTemperatureOption.series[0].data = dataY;
	powerTemperatureOption.xAxis[0].data = dataX;
	powerTemperature.setOption(powerTemperatureOption);
}
loadPowerChargeDataF();
function loadPowerChargeDataF(){
	loadPowerChargeData();
	clearInterval(powerChargingIntervalId);
	powerChargingIntervalId = setInterval("loadPowerChargeData()", 180000);
}
