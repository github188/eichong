//main部分的tab切换
$("body").off("click", ".provinceStyle");
$(function() {
	$(".mainTab span").on("click", function() {
		$(this).addClass('active').siblings().removeClass('active');
		var index = $(this).index();
		$(".mainTabBlock").eq(index).show().siblings().hide();
	})
})
//四个基础数据=========================================
assetData();
function assetData() {
	$.ajax({
		type: "get",
		url: basePath + assertDataCountUrl,
		dataType: 'json',
		success: function(datas) {
			var req = datas.data;
			$("#v1").html(req.v1);
			$("#v2").html(req.v2);
			$("#v3").html(req.v3);
			$("#v4").html(req.v4);
		}
	});
}
//饼状图=============================================
var pieChart;
var pieChartOption;
initPieChart();
ajaxPieChart();

function initPieChart() {
	pieChart = echarts.init(document.getElementById('pieChart'));
	pieChartOption = {
		title: {},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			left: 'center',
			data: []
		},
		series: []
	};
	return pieChartOption;
}

function ajaxPieChart() {
	$.ajax({
		type: "get",
		url: basePath + assertDataPieUrl,
		dataType: 'json',
		success: function(datas) {
			var req = datas.data;
			pieChartOption.legend.data = req.legend;
			var obj = {
				name: "故障占比",
				type: "pie",
				data: [],
				radius: '55%',
				center: ['50%', '60%'],
				itemStyle: {
					normal: {
						barBorderRadius: 5
					},
					emphasis: {
						barBorderRadius: 5
					}
				}
			};
			for(var j = 0; j < req.legend.length; j++) {
				for(var key in req) {
					if(key == req.legend[j]) {
						obj.data[j] = {
							value: req[key],
							name: key
						};
					}
				}
			}
			pieChartOption.series[0] = obj;
			pieChart.setOption(pieChartOption);
		}
	});
}
//列表==================================
ajaxDetailList();
function ajaxDetailList() {
	$.ajax({
		type: "get",
		url: basePath + assertDataDetailUrl,
		dataType: 'json',
		success: function(datas) {
			var req = datas.data;
//			alert(JSON.stringify(req));
			var listTr = "";
			for(var i = 0; i < req.length; i++) {
				if(req[i].cityCode == undefined) {
					listTr += '<tr class="proTr ' +
						req[i].provinceCode + '"><td class="provinceStyle">' +
						req[i].provinceName + '</td><td>' +
						req[i].chargeNum + '</td><td>' +
						req[i].pileNum + '</td><td>' +
						req[i].acPileNum + '</td><td>' +
						req[i].dcPileNum + '</td></tr>';
				} else {
					listTr += '<tr class="' + 'cityTr' + req[i].provinceCode + '" style="display:none;"><td class="cityActive" style="text-align: center;">' +
						req[i].cityName + '</td><td>' +
						req[i].chargeNum + '</td><td>' +
						req[i].pileNum + '</td><td>' +
						req[i].acPileNum + '</td><td>' +
						req[i].dcPileNum + '</td></tr>';
				}
			}
			$("#assetDataTbody").html(listTr);
		}
	});
}
//
$("body").on("click", ".provinceStyle", function() {
	var arr = $(this).parent().attr("class");
	var provinceCode = arr.substring(6, arr.length);
	var cityTrClass = ".cityTr" + provinceCode;
	$(cityTrClass).toggle();
	var flag = $(cityTrClass).is(":hidden");
	if(flag) {
		$(this).addClass("provinceStyleActive").removeClass("provinceStyleActive");
	} else {
		$(this).removeClass("provinceStyleActive").addClass("provinceStyleActive");
	}
})

//地图初始化====================================
var mapOption;
var mapChart;
initMap();
function initMap() {
	mapChart = echarts.init(document.getElementById('mapChart'));
	mapOption = {
		title: {},
		tooltip: {
			trigger: 'item',
			backgroundColor: 'rgba(50,50,50,0.5)'
		},
		color: ['#ff8900', '#ff5d00', '#b7a3df'],
		legend: {
			orient: 'horizontal',
			left: 'center',
			data: []
		},
		visualMap: [{
			type:'piecewise',
			itemWidth:30,
			orient:'horizontal',
			itemGap:40,
			left:240,
			target: {
				outOfRange: {
	                color: ['#121122', 'rgba(3,4,5,0.4)', 'red'],
	                symbolSize: [60, 200]
	            }
			},
	        inRange: {
	            color: ['#299246', 'rgba(41,146,70,0.4)', 'green'],
	            symbolSize: [60, 200]
	        },
	        controller: {
	            inRange: {
	                symbolSize: [30, 100]
	            },
	            outOfRange: {
	                symbolSize: [30, 100]
	            }
	        }
		}],
		toolbox: {
			show: true,
			orient: 'vertical',
			left: 'left',
			top: 'center'
		},
		series: []
	};
	
}

//加载地图=================================================
ajaxMapChart();
function ajaxMapChart() {
	$.ajax({
		type: "get",
		url: basePath + assertDataMapUrl,
		dataType: 'json',
		success: function(datas) {
			var req = datas.data;
			var mapData = [];
			mapOption.legend.data = req.legend;
			var mapObj = {
				name: req.legend,
				type: 'map',
				mapType: 'china',
				roam: false,
				itemStyle: {
					normal: {
						areaStyle: {
							color: '#f00'
						},
						label: {
							textStyle: {
								color: '#d87a80'
							}
						}
					},
					emphasis: {
						areaStyle: {
							color: '#fe994e'
						}

					}
				},
				data: mapData
			};

			for(var i = 0; i < req.columns.length; i++) {
				var dName = formatAreaName(req.columns[i].name);
				var dValue = req.columns[i].value;
				var oneData = {};
				oneData = {
					name: dName,
					value: dValue
				};
				mapData.push(oneData);
			}

			mapObj.data = mapData;
			mapOption.series[0] = mapObj;
			mapChart.setOption(mapOption);
//			console.log(mapOption)
		}
	});
}


function formatAreaName(name){
	return name.replace("省","").replace("市","").replace("自治区","");
}
