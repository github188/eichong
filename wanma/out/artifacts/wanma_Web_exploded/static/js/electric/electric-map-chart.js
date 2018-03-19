require.config({
	paths : {
		echarts : basepath + '/res/echarts/js'
	}
});
/**
 * 电压电流表
 * @param data
 */
function drowVoltageChart(data) {
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/bar',
			'echarts/chart/line', 'echarts/chart/pie', 'echarts/chart/gauge',
			'echarts/chart/tree', 'echarts/chart/map'

	], function(ec, theme) {
		var voltageDiv = ec.init(document.getElementById('voltageDiv'), theme);
		var currentDiv = ec.init(document.getElementById('currentDiv'), theme);
		voltageDiv.clear();
		currentDiv.clear();
		var head = data.headList;
		var outputVoltage = data.monitorData.output_voltage;
		if (outputVoltage != null) {
			var newVoltageData = jQuery.extend(true, {}, option);
			var serie = newVoltageData.series[0]
			var serieData = serie.data[0]
			serieData.value = parseFloat(outputVoltage)
			serie.max = 850
			serie.name="电压"
			newVoltageData.title.text = "实时电压（V）"
			voltageDiv.setOption(newVoltageData);
		} else {
			var newVoltageData = jQuery.extend(true, {}, option);
			var serie = newVoltageData.series[0]
			var serieData = serie.data[0]
			serieData.value = 0
			serie.max = 850
			serie.name="电压"
			newVoltageData.title.text = "实时电压（V）"
			voltageDiv.setOption(newVoltageData);
		}

//		var outputCurrent = head.outputCurrent;
		var outputCurrent = data.monitorData.output_current;
		if (outputCurrent != null) {
			var newCurrentData = jQuery.extend(true, {}, option);
			var serie = newCurrentData.series[0]
			var serieData = serie.data[0]
			serieData.value = parseFloat(outputCurrent)
			serie.max = 250
			serie.name="电流"
			newCurrentData.title.text = "实时电流（A）"
			currentDiv.setOption(newCurrentData);
		} else {
			var newCurrentData = jQuery.extend(true, {}, option);
			var serie = newCurrentData.series[0]
			var serieData = serie.data[0]
			serieData.value = 0
			serie.max = 250
			serie.name="电流"
			newCurrentData.title.text = "实时电流（A）"
			currentDiv.setOption(newCurrentData);
		}
	});
}

/**
 * 电池容量图
 * @param data
 */
function drowDcDiv(data) {
	var soc = dealNullToInt(data.monitorData.soc);
//	var soc = 90;
	//进度条控制
	if(soc && soc > 0){
		$(".dcProcess").hide();
		var indexMonitor = 0;
		var indexLimit = soc/100*6;
		if(indexLimit > 5 && indexLimit < 6){
			indexLimit = 5;
		}else{
			indexLimit = Math.ceil(indexLimit);
		}
		var dcPictureProcess = window.setInterval(function() {
			indexMonitor += 1;
			$("#dianchiProcessDiv"+indexMonitor).show();
			if(indexMonitor == indexLimit) {
				clearInterval(dcPictureProcess);
			}
		}, 200);
		document.getElementById('percent').innerHTML = "电池容量（" + soc + "%）";
	}else{
		$(".dcProcess").hide();
		document.getElementById('percent').innerHTML = "电池容量（" + 0 + "%）";
	}
}

/**
 * 隐藏实时监控区域
 */
function hideChartDiv() {
	$("#pileCountDiv").css({
		'bottom' : '0'
	});
	$("#chartDiv").hide();
}

/**
 * 展示实时监控区域
 */
function showChartDiv() {
	$("#pileCountDiv").css({
		'bottom' : '225px'
	});
	$("#chartDiv").show();
	$("#chartDivIn").show();
	$("#slideDivA").attr("onclick", "chartDivSlideDown()");
}

/**
 * 展开实时监控区域
 */
function chartDivSlideUp() {
	$('#pileCountDiv').animate({
		bottom : '225px'
	}, 1000)
	$("#chartDivIn").animate({
		height : 'toggle'
	}, 1000);
	$("#slideDivA").attr("onclick", "chartDivSlideDown()");
	$("#slideImg").attr("src",
			basepath + "/static/images/img/electric/slideDown.png");
}

/**
 * 收缩实时监控区域
 */
function chartDivSlideDown() {
	$('#pileCountDiv').animate({
		bottom : '0'
	}, 1000)
	$("#chartDivIn").animate({
		height : 'toggle'
	}, 1000);
	$("#slideDivA").attr("onclick", "chartDivSlideUp()");
	$("#slideImg").attr("src",
			basepath + "/static/images/img/electric/slideUp.png");
}


/**
 * 图表数据格式
 */
var option = {
	title : {
		"text" : "",
	  textStyle: 
	  	{
		    fontSize: 15,
		    color:"#ff8000"
		}
	},
	toolbox : {
		show : false,
		feature : {
			mark : {
				show : true
			},
			restore : {
				show : true
			},
			saveAsImage : {
				show : true
			}
		}
	},
	series : [ {
		name : '',
		type : 'gauge',
		z : 3,
		min : 0,
		max : 220,
        startAngle:230,
        endAngle:-50,
        center:['50%', '60%'],
		splitNumber : 5,
		axisLine : { // 坐标轴线
			lineStyle : { // 属性lineStyle控制线条样式
				width : 5
			}
		},
		axisTick : { // 坐标轴小标记
			length : 7, // 属性length控制线长
			lineStyle : { // 属性lineStyle控制线条样式
				color : 'auto'
			}
		},
		splitLine : { // 分隔线
			length : 13, // 属性length控制线长
			lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
				color : 'auto'
			}
		},
		title : {
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 20,
				fontStyle : 'italic'
			}
		},
		detail : {
			offsetCenter : [0, 10],
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 20
			}
		},
		data : [ {
			value : 50,
			name : ''
		} ]
	} ]
};

function dealNullToInt(val){
	var valTemp = val;
	return valTemp==null || valTemp ==""? "0":parseInt(valTemp)
}

function dealNullToFloat(val){
	var valTemp = val;
	return valTemp==null || valTemp ==""? "0.00":parseFloat(valTemp).toFixed(2);
}