var headState = $('#headState').val();
var vapData = {
	voltageValue : 0,
	currentValue : 0,
	presentChargeValue : 0,
	chargeStatus : 0,
	chargedTime : 0,
	errorList : [ 0 ]
};
var newVoltageData = jQuery.extend(true, {}, ybpOption);
var newCurrentData = jQuery.extend(true, {}, ybpOption);
var newAllChargeData = jQuery.extend(true, {}, ybpOption);
var newPowerRateData = jQuery.extend(true, {}, ybpOption);

var voltageDiv;
var currentDiv;
var allChargeDiv;
var powerRateDiv;
// 输出电压===============================
function drowVAPChart() {
	// 使用
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/gauge' ],
			function(ec, theme) {
				voltageDiv = ec.init(document.getElementById('voltageDiv'),
						theme);
				currentDiv = ec.init(document.getElementById('currentDiv'),
						theme);
				allChargeDiv = ec.init(document.getElementById('allChargeDiv'),
						theme);
				powerRateDiv = ec.init(document.getElementById('powerRateDiv'),
						theme);
				outputPowerRate();// 功率图表初始化
				outputVoltage();// 电压图表初始化
				outputCurrent();// 电流图表初始化
				outputAllElectricity();// 电量图表初始化
			});
}

/**
 * 电压表初始化
 */
function outputVoltage() {
	if (voltageDiv) {
		voltageDiv.clear();
	}
	// 电压===============
	var serie = newVoltageData.series[0];
	var serieData = serie.data[0];
	serieData.value = 0;
	serie.max = 850;
	serie.name = "电压";
	newVoltageData.title.text = "输出电压（V）";
	setVoltageValue();
}

/**
 * 电流表初始化
 */
function outputCurrent() {
	// 电流======
	if (currentDiv) {
		currentDiv.clear();
	}
	var serie = newCurrentData.series[0];
	var serieData = serie.data[0];
	serieData.value = 0;
	serie.max = 125;
	serie.name = "电流";
	newCurrentData.title.text = "输出电流（A）";
	setCurrentValue();
}

/**
 * 功率表初始化
 */
function outputPowerRate() {
	// 功率==================================
	if (powerRateDiv) {
		powerRateDiv.clear();
	}
	var serie = newPowerRateData.series[0];
	var serieData = serie.data[0];
	serieData.value = 0;
	serie.max = 130;
	serie.name = "功率";
	newPowerRateData.title.text = "输出功率（kW）";
	powerRateDiv.setOption(newPowerRateData);
}

/**
 * 电量表初始化
 */
function outputAllElectricity() {
	// 总电量==========
	if (allChargeDiv) {
		allChargeDiv.clear();
	}
	var serie = newAllChargeData.series[0];
	var serieData = serie.data[0];
	serieData.value = 0;
	serie.max = 300;
	serie.name = "总电量";
	newAllChargeData.title.text = "输出总电量（kWh）";
	setAllChargeValue();
}

/**
 * 实时图表数据加载
 */
function loadVAPData() {
	$.ajax({
		type : 'post',
		url : config.hbaseUrl + "/getRealtimeData?headId="
				+ $("#headIdDiv").val() + "&epCode=" + $("#eCode").val()
				+ "&epType=" + $("#eChargeType").val(),
		dataType : "json",
		success : function(datas) {
			if (datas) {
				vapData = datas;
			} else {
				vapData = {
					voltageValue : 0,
					currentValue : 0,
					presentChargeValue : 0,
					chargedTime : 0,
					errorList : [ 0 ]
				};
			}
			var cgStsFilter = ['0','2','8','9','11','17','12','30'];
			if(isIn(cgStsFilter,vapData.chargeStatus)){
				vapData.voltageValue = 0;
				vapData.currentValue = 0;
				vapData.presentChargeValue = 0;
			}
			drowVAPChart();
			showErrorZt();
			showRunTimeData();
			$("#chargeStatusDiv").val(vapData.chargeStatus);
		}
	});
}

/**
 * 电压表赋值
 */
function setVoltageValue() {
	var newValue = dealNullToFloat(vapData.voltageValue);
	// var newValue = parseInt(Math.random()*850+1);
	newVoltageData.series[0].data[0].value = newValue;
	voltageDiv.setOption(newVoltageData);
	setPowerRateValue();
}

/**
 * 电流表赋值
 */
function setCurrentValue() {
	var newValue = dealNullToFloat(vapData.currentValue);
	// var newValue = parseInt(Math.random()*125+1);
	newCurrentData.series[0].data[0].value = newValue;
	currentDiv.setOption(newCurrentData);
	setPowerRateValue();
}

/**
 * 功率表赋值
 */
function setPowerRateValue() {
	var newVlaue = (newVoltageData.series[0].data[0].value * newCurrentData.series[0].data[0].value) / 1000;
	newPowerRateData.series[0].data[0].value = parseFloat(newVlaue).toFixed(2);
	powerRateDiv.setOption(newPowerRateData);
}

/**
 * 电量表赋值
 */
function setAllChargeValue() {
	var newValue = dealNullToFloat(vapData.presentChargeValue);
	newAllChargeData.series[0].data[0].value = newValue;
	allChargeDiv.setOption(newAllChargeData);
}

/**
 * 运行数据加载
 */
function showRunTimeData() {
	var status;

	if (!vapData.chargeStatus) {
		if ($('#headState').val() == 3) {
			status = "预约";
		} else if ($('#headState').val() == 17) {
			status = "等待充电";
		}  else if ($('#headState').val() == 6) {
			status = "充电";
		} else if ($('#headState').val() == 0) {
			status = "空闲";
		} else if ($('#headState').val() == 9) {
			status = "故障";
		}
	} else {
		if (vapData.chargeStatus == 3) {
			status = "充电";
		} else if (vapData.chargeStatus == 10 || vapData.chargeStatus == 17){
			status = "等待充电";
		}else if (vapData.chargeStatus == 8) {
			status = "预约";
		} else if (vapData.chargeStatus == 2 || vapData.chargeStatus == 11
				|| vapData.chargeStatus == 12 || vapData.chargeStatus == 30) {
			status = "空闲";
		} else if (vapData.chargeStatus == 0 || vapData.chargeStatus == 9) {
			status = "断开";
		} else if(vapData.chargeStatus > 30) {
			status = "故障";
		}else{
			status = "未知";
		}
	}
	$("#chargeStatus").html(status);
	$("#chargeTime").html(
			(dealNullToFloat(vapData.chargedTime) / 60).toFixed(2) + "小时");
	if ($("#eChargeType").val() == '5') {
		var chargeType = vapData.chargeType;
		var powerHighestTemperature = dealNullToFloat(vapData.powerHighestTemperature);
		var chargeRemainTime = dealNullToInt(vapData.chargeRemainTime);
		if (chargeType == "0") {
			chargeType = "不可信"
		}
		if (chargeType == "1") {
			chargeType = "恒压"
		}
		if (chargeType == "2") {
			chargeType = "恒流"
		} else if (!chargeType) {
			chargeType = "未知"
		}
		$("#chargeModel").html(chargeType);
		$("#oneT").html(powerHighestTemperature + "°C");
		$("#lastTime").html(chargeRemainTime + "分钟");

		var batteryType = vapData.batteryType;
		if (!batteryType) {
			batteryType = "未知"
		} else if (batteryType == "1") {
			batteryType = "铅酸电池"
		} else if (batteryType == "2") {
			batteryType = "皋氢电池"
		} else if (batteryType == "3") {
			batteryType = "磷酸铁锂电池"
		} else if (batteryType == "4") {
			batteryType = "锰酸锂电池"
		} else if (batteryType == "6") {
			batteryType = "三元材料电池"
		} else if (batteryType == "7") {
			batteryType = "聚合物锂离子电池"
		} else if (batteryType == "8") {
			batteryType = "钛酸锂电池"
		}
		$("#batteryType").html(batteryType);
		$("#carIdentification").html(vapData.carIdentification);
		if(vapData.carIdentification!=null){
			getCarLicenseValue(vapData.carIdentification);
		}
		
		$("#carTotalVoltage").html(
				dealNullToFloat(vapData.carTotalVoltage) + "V");
		$("#bpHighestVoltage").html(vapData.bpHighestVoltage);
		$("#carSoc").html(dealNullToFloat(vapData.soc)+"%");
		
		$("#bpLowestTemperature").html(
				dealNullToFloat(vapData.bpLowestTemperature) + "°C");
		$("#bpHighestTemperature").html(
				dealNullToFloat(vapData.bpHighestTemperature) + "°C");
		drowDcDiv(vapData);
	}
}

/**
 * 充电情况数据加载
 */
/*function getChargeInfoCount() {
	var headId = $("#headIdDiv").val();
	$.ajax({
		type : 'get',
		url : basepath + "/admin/chartStatistics/getChargeInfoCountInner.do",
		dataType : "json",
		data : {
			headId : $("#pkHeadIdDiv").val()
		},
		success : function(json) {
			var all_charge_count = json.all_charge_count;
			var allCharge_c = json.allCharge_c;
			var allCharge_t = json.allCharge_t;
			var elPi_PowerSize = json.elPi_PowerSize;
			$("#all_charge_count2")
					.html(
							all_charge_count == null ? 0 + '次'
									: all_charge_count + '次');
			$("#all_charge_c2").html(
					allCharge_c == null ? 0 + '度' : parseFloat(allCharge_c)
							.toFixed(2)
							+ '度');
			$("#all_charge_time2").html(
					allCharge_t == null ? 0 + '小时' : (parseFloat(allCharge_t))
							.toFixed(2)
							+ '小时');
			$("#ratedPower").html(elPi_PowerSize);
		}
	});
}*/


function getCarLicenseValue(value) {
$.ajax({
	type : 'get',
	url : basepath + "/admin/chartStatistics/getCarLicenseValue.do",
	dataType : "json",
	data : {
		carVin : value
	},
	success : function(json) {
		
		if(json!=null){
		var cv_license_number = json.cv_license_number;
		$("#carLicense").html(cv_license_number);
		}
	}
});
}
/**
 * 实时故障加载
 */
function showErrorZt() {
	// $(".guzhuangZT-red").addClass('guzhuangZT');
	$(".guzhuangZT-red").removeClass('guzhuangZT-red');
	var errorStateS = vapData.errorList
	if (errorStateS != null && errorStateS.length > 0) {
		for (var i = 0; i < errorStateS.length; i++) {
			$(".ZT" + errorStateS[i]).addClass('guzhuangZT-red');
		}
	}
}

/**
 * 电池容量图
 * 
 * @param data
 */
function drowDcDiv(data) {
	var soc = dealNullToInt(data.soc);
	// var soc = 90;
	// 进度条控制
	if (soc && soc > 0) {
		$(".dcProcess").hide();
		var indexMonitor = 0;
		var indexLimit = soc / 100 * 6;
		if (indexLimit > 5 && indexLimit < 6) {
			indexLimit = 5;
		} else {
			indexLimit = Math.ceil(indexLimit);
		}
		var dcPictureProcess = window.setInterval(function() {
			indexMonitor += 1;
			$("#dianchiProcessDiv" + indexMonitor).show();
			if (indexMonitor == indexLimit) {
				clearInterval(dcPictureProcess);
			}
		}, 200);
		document.getElementById('percent').innerHTML = "电池容量（" + soc + "%）";
	} else {
		$(".dcProcess").hide();
		document.getElementById('percent').innerHTML = "电池容量（" + 0 + "%）";
	}
}

var intervalIdLoadeShishi
function loadShishiData() {
	loadVAPData();
	clearInterval(intervalIdLoadeShishi);
	intervalIdLoadeShishi = setInterval("loadVAPData()", 300000);
}
loadShishiData();
//getChargeInfoCount();
