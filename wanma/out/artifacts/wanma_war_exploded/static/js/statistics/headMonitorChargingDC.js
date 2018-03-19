/** ***********************************************数据加载start************************************************************** */
var chargingData = [ {} ];
var chargingChartData = [ {} ];
var chargingIntervalIdDC
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
		success : function(datas) {
			var dataTemp = datas.data;
			if (dataTemp != null && dataTemp.length > 0) {
				chargingData = dataTemp;
				if (datas.chargingStatus == 3) {
					chargingChartData = dataTemp;
				}
			}
			droweVCMoreCharts();
			droweVCOneCharts();
			var headTemperatureE = document.getElementById('headTemperature');
			if (headTemperatureE) {
				droweTemperatureCharts();
			}
			setTableData();
			chargingData = [{}];
			chargingChartData = [{}];
		}
	});
}
function loadChargeDataF() {
	loadChargeData();
	clearInterval(chargingIntervalIdDC);
	chargingIntervalIdDC = setInterval("loadChargeData()", 180000);
}
loadChargeDataF();
/** ***********************************************数据加载end************************************************************** */

/** ********************************************************充电曲线start**************************************************************** */
var headOutputVoltage;
var headOutputVCOne;
var headTemperature;
require.config({
	paths : {
		echarts : '/wanma/res/echarts/js'
	}
});
var voltageOption = jQuery.extend(true, {}, lineOptionDcVCS);
var vCOneOption = jQuery.extend(true, {}, lineOptionDcVCOne);
var temperatureOption = jQuery.extend(true, {}, lineOption);
/**
 * 实时三相电压电流
 */
function droweVCMoreCharts() {
	if (headOutputVoltage) {
		headOutputVoltage.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				headOutputVoltage = ec.init(document
						.getElementById('headOutputVoltage'), theme);
				refreshVCMoreData();
			});
}

/**
 * 定时刷新三相电压电流数据
 */
function refreshVCMoreData() {
	var dataX = [];
	var dataAVY = [];
	var dataBVY = [];
	var dataCVY = [];
	var dataACY = [];
	var dataBCY = [];
	var dataCCY = [];
	dataX[0] = "0分";
	dataAVY[0] = dealNullToFloat(chargingChartData[0].av);
	dataBVY[0] = dealNullToFloat(chargingChartData[0].bv);
	dataCVY[0] = dealNullToFloat(chargingChartData[0].cv);
	dataACY[0] = dealNullToFloat(chargingChartData[0].ac);
	dataBCY[0] = dealNullToFloat(chargingChartData[0].bc);
	dataCCY[0] = dealNullToFloat(chargingChartData[0].cc);
	var dataTimeStart = dealNullToInt(chargingChartData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < chargingChartData.length; i++) {
		var dataTemp = chargingChartData[i];
		var dataTime = dealNullToFloat(dataTemp.ts);
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime > index * lineSplitNum) {
			index++;
			dataX[index] = index * lineSplitNum + "分";
			dataAVY[index] = dealNullToFloat(dataTemp.av);
			dataBVY[index] = dealNullToFloat(dataTemp.bv);
			dataCVY[index] = dealNullToFloat(dataTemp.cv);
			dataACY[index] = dealNullToFloat(dataTemp.ac);
			dataBCY[index] = dealNullToFloat(dataTemp.bc);
			dataCCY[index] = dealNullToFloat(dataTemp.cc);
		}
	}
	var seriesData = voltageOption.series;
	seriesData[0].data = dataAVY;
	seriesData[1].data = dataBVY;
	seriesData[2].data = dataCVY;
	seriesData[3].data = dataACY;
	seriesData[4].data = dataBCY;
	seriesData[5].data = dataCCY;
	voltageOption.xAxis[0].data = dataX;
	headOutputVoltage.setOption(voltageOption, true);
}

/**
 * 实时输出电压电流
 */
function droweVCOneCharts() {
	if (headOutputVCOne) {
		headOutputVCOne.clear();
	}
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
			function(ec, theme) {
				headOutputVCOne = ec.init(document
						.getElementById('headOutputVCOne'), theme);
				refreshVCOneData();
			});
}

/**
 * 定时刷新输出电压电流数据
 */
function refreshVCOneData() {
	var dataX = [];
	var dataVY = [];
	var dataCY = [];
	dataX[0] = "0分";
	dataVY[0] = dealNullToFloat(chargingChartData[0].tv);
	dataCY[0] = dealNullToFloat(chargingChartData[0].tc);
	var dataTimeStart = dealNullToInt(chargingChartData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < chargingChartData.length; i++) {
		var dataTemp = chargingChartData[i];
		var dataTime = dealNullToFloat(dataTemp.ts);
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime > index * lineSplitNum) {
			index++;
			dataX[index] = index * lineSplitNum + "分";
			dataVY[index] = dataTemp.voltageValue;
			dataCY[index] = dataTemp.currentValue;
		}
	}
	var seriesData = vCOneOption.series;
	seriesData[0].data = dataVY;
	seriesData[1].data = dataCY;
	vCOneOption.xAxis[0].data = dataX;
	headOutputVCOne.setOption(vCOneOption, true);
}
//
// /**
// * 实时电流
// */
// function droweCurrentCharts() {
// if (headOutputCurrent) {
// headOutputCurrent.clear();
// }
// require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line' ],
// function(ec, theme) {
// headOutputCurrent = ec.init(document
// .getElementById('headOutputCurrent'), theme);
// currentOption.legend.data[0] = '实时电流'
// currentOption.series[0].name = '实时电流'
// currentOption.yAxis[0].axisLabel.formatter = '{value}A'
// refreshCurrentData();
// clearInterval(intervalId1);
// intervalId1 = setInterval("refreshCurrentData()", 2000);
// });
// }
//
// /**
// * 定时刷新电流数据
// */
// function refreshCurrentData() {
// var dataX = []
// var dataY = []
// dataX[0] = "0分";
// dataY[0] = dealNullToFloat(chargingData[0].currentValue);
// var dataTimeStart = dealNullToInt(chargingData[0].ts);
// var index = 0;
// var lineSplitNum = parseInt($("#lineSplitNum").val());
// for (var i = 1; i < chargingData.length; i++) {
// var dataTemp = chargingData[i];
// var dataTime = dealNullToFloat(dataTemp.ts)
// var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
// if (differenceTime > index) {
// index = differenceTime;
// dataX[index] = differenceTime * lineSplitNum + "分"
// dataY[index] = dealNullToFloat(dataTemp.currentValue)
// }
// }
// currentOption.series[0].data = dataY;
// currentOption.xAxis[0].data = dataX;
// headOutputCurrent.setOption(currentOption, true);
// }

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
				temperatureOption.legend.data[0] = '实时温度';
				temperatureOption.series[0].name = '实时温度';
				temperatureOption.yAxis[0].axisLabel.formatter = '{value}C';
				refreshTemperatureData();
			});
}

/**
 * 定时刷新温度数据
 */
function refreshTemperatureData() {
	var dataX = [];
	var dataY = [];
	dataX[0] = "0分";
	dataY[0] = dealNullToFloat(0);
	var dataTimeStart = dealNullToInt(chargingChartData[0].ts);
	var index = 0;
	var lineSplitNum = parseInt($("#lineSplitNum").val());
	for (var i = 1; i < chargingChartData.length; i++) {
		var dataTemp = chargingChartData[i];
		var dataTime = dealNullToFloat(dataTemp.ts);
		var differenceTime = parseInt((dataTime - dataTimeStart) / (1000 * 60));
		if (differenceTime >  index * lineSplitNum) {
			index++;
			dataX[index] =  index * lineSplitNum + "分";
			dataY[index] = dealNullToFloat(dataTemp.temperature);
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
	var lastState = chargingData[chargingData.length-1].chargingState;
	var cgStsFilter = ['0','2','8','9','11','12','30'];
	if(lastState != null && !isIn(cgStsFilter,lastState)){
		for (var i = chargingData.length - 1; i >= 0; i--) {
			var dataTemp = chargingData[i];
			if (dealNullToFloat(dataTemp.ts) == "0.0") {
				return;
			}
			var dataTime = new Date(dealNullToFloat(dataTemp.ts));
			// var differenceTime = parseInt((dataTime-dataTimeStart)/(1000*60));
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
						+ '</span><span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.soc) + '</span>' + '</div>'
			} else {

				strTemp = '<div class="caiyangbiao-02">'
						+ '<span class="caiyangbiaoW75">' + h + ':' + m + ':' + s
						+ '</span>' + '<span class="caiyangbiaoW75">'
						+ dealNullToNullStr(dataTemp.allChargeValue)
						+ '</span><span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.presentChargeValue) + '</span>'
						+ '<span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.presentChargeFee)
						+ '</span><span class="caiyangbiaoW100">'
						+ dealNullToNullStr(dataTemp.soc) + '</span>' + '</div>'

			}
			if (i > chargingData.length - 10) {
				dataDivStrSom += strTemp;
			}
			dataDivStr += strTemp;
		}
	}
	$("#chargeDataSom").html(dataDivStrSom);
	$("#chargeDataAll").html(dataDivStr);
	if(dataDivStr == ""){
		$("#chargeDataAll").css("overflow-y","");
	}else{
		$("#chargeDataAll").css("overflow-y","scroll");
	}
}
/** *********************************************************充电数据采样end***************************************************************************** */
function showAll() {
	$("#huadongDiv").css("height","0px");
	$("#chargeDataAllDiv").show();
}

function closeAll() {
	$("#huadongDiv").css("height","44px");
	$("#chargeDataAllDiv").hide();
}