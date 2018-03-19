lineOption = {
	title : {
		text : '',
	},
	tooltip : {
		trigger : 'axis'
	},
	legend : {
		data : [ '实时电压', ]
	},
	grid : {
		borderColor : '#666'
	},
	calculable : true,
	xAxis : [ {
		splitLine : {
			show : false
		},
		type : 'category',
		boundaryGap : false,
		data : []
	} ],
	yAxis : [ {
		splitLine : {
			show : false
		},
		type : 'value',
		axisLabel : {
			formatter : '{value}V'
		}
	} ],
	series : [ {
		name : '实时电压',
		type : 'line',
		data : []
	} ]
};

lineOptionDcVCS =  {
		title : {
			text : '',
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ 'A相电压','B相电压','C相电压','A相电流','B相电流','C相电流' ]
		},
		grid : {
			borderColor : '#666'
		},
		calculable : true,
		xAxis : [ {
			splitLine : {
				show : false
			},
			type : 'category',
			boundaryGap : false,
			data : []
		} ],
		yAxis : [ {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				formatter : '{value}V'
			}
		}, {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				formatter : '{value}A'
			}
		} ],
		series : [ {
			name : 'A相电压',
			type : 'line',
			data : []
		}, {
			name : 'B相电压',
			type : 'line',
			data : []
		}, {
			name : 'C相电压',
			type : 'line',
			data : []
		}, {
			name : 'A相电流',
			type : 'line',
            yAxisIndex: 1,
			data : []
		}, {
			name : 'B相电流',
			type : 'line',
            yAxisIndex: 1,
			data : []
		}, {
			name : 'C相电流',
			type : 'line',
            yAxisIndex: 1,
			data : []
		}]
	};

lineOptionDcVCOne =  {
		title : {
			text : '',
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '输出电压','输出电流' ]
		},
		grid : {
			borderColor : '#666'
		},
		calculable : true,
		xAxis : [ {
			splitLine : {
				show : false
			},
			type : 'category',
			boundaryGap : false,
			data : []
		} ],
		yAxis : [ {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				formatter : '{value}V'
			}
		}, {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				formatter : '{value}A'
			}
		} ],
		series : [ {
			name : '输出电压',
			type : 'line',
			data : []
		},{
			name : '输出电流',
			type : 'line',
            yAxisIndex: 1,
			data : []
		}]
	};

/**********************************源端曲线*****************************************************/
powerLineOption = {
		title : {
			text : '',
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '输出电压','A相电压','B相电压','C相电压' ]
		},
		grid : {
			borderColor : '#666'
		},
		calculable : true,
		xAxis : [ {
			splitLine : {
				show : false
			},
			type : 'category',
			boundaryGap : false,
			data : []
		} ],
		yAxis : [ {
			splitLine : {
				show : false
			},
			type : 'value',
			axisLabel : {
				formatter : '{value}V'
			}
		} ],
		series : [ {
			name : '输出电压',
			type : 'line',
			data : []
		},  {
			name : 'A相电压',
			type : 'line',
			data : []
		}, {
			name : 'B相电压',
			type : 'line',
			data : []
		}, {
			name : 'C相电压',
			type : 'line',
			data : []
		}]
	};

// 数据图表======================================
var ybpOption = {
	title : {
		"text" : "",
		textStyle : {
			fontSize : 15,
			color : "#fff"
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
		startAngle : 230,
		endAngle : -50,
		center : [ '50%', '60%' ],
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
			offsetCenter : [ 0, 50 ],
			textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
				fontWeight : 'bolder',
				fontSize : 20
			},
			formatter : '{value}'
		},
		data : [ {
			value : 50,
			name : ''
		} ]
	} ]
};