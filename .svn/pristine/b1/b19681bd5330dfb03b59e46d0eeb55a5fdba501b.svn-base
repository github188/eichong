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
		var outputVoltage = data.outputVoltage;
		if (outputVoltage != null && outputVoltage.trim() != "") {
			var newVoltageData = jQuery.extend(true, {}, option);
			var serie = newVoltageData.series[0]
			var serieData = serie.data[0]
			serieData.value = outputVoltage
			serie.max = 750
			serie.name="电压"
			newVoltageData.title.text = "实时电压（V）"
			voltageDiv.setOption(newVoltageData);
		} else {
			voltageDiv.showLoading({
				text : '电压详情(暂无数据)',
				effect : 'nullData',
				textStyle : {
					fontSize : 30
				}
			});
		}

		var outputCurrent = data.outputCurrent;
		if (outputCurrent != null && outputCurrent.trim() != "") {
			var newCurrentData = jQuery.extend(true, {}, option);
			var serie = newCurrentData.series[0]
			var serieData = serie.data[0]
			serieData.value = outputCurrent
			serie.max = 125
			serie.name="电流"
			newCurrentData.title.text = "实时电流（A）"
			currentDiv.setOption(newCurrentData);
		} else {
			currentDiv.showLoading({
				text : '电流详情(暂无数据)',
				effect : 'nullData',
				textStyle : {
					fontSize : 30
				}
			});
		}
	});
}

/**
 * 电池容量图
 * @param data
 */
function drowDcDiv(data) {
	var proportion = 2.3;//此比例为进度条总长度/100的值
	var soc = dealNullToInt(data.soc) * proportion;
	var soc_clor = "#EE0000"
	if (parseInt(soc) >= 20*proportion) {
		soc_clor = "#66CD00"
	}
	$("#allLine").html(
			'<div id="line" style="width:0px;height:100px;background:'
					+ soc_clor + ';"></div>');
	//进度条控制
	$("#line").each(function(i, item) {
		$(item).animate({
			width : soc + ""
		}, 1000);
	});
	var process = window.setInterval(function() {
		var a = $("#line").width();
		b = (a /(100*proportion) * 100).toFixed(0);
		document.getElementById('percent').innerHTML = "电池容量（" + b + "%）";
		if(b == soc/proportion) {
			clearInterval(process);
		}
	}, 70);
}

/**
 * 图表数据格式
 */
var option = {
	title : {
		"text" : ""
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
        startAngle:183,
        endAngle:-3,
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
