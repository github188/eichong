<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>/res/echarts/js/echarts.js"></script>
<style>

.chart_button {
	width: 150px;
	height: 24px;
	border: 1px #999 solid;
	color: #999;
	cursor: pointer;
}

.chart_button1{
	width: 150px;
	height: 24px;
	border: 1px #ff8800 solid;
	color: #FFF;
	cursor: pointer;
	background: #ff8800;
}

.chart_button:hover {
	border: 1px #ff8800 solid;
	color: #FFF;
	background: #ff8800;
}
</style>
<body style="overflow-y: scroll;">

	<form id="pagerForm" method="post" action="">
		<div>
			<strong><input name="type_checkbox" type="checkbox"
				onclick="initMapChart()" value="0">其他--</strong><strong><input
				name="type_checkbox" type="checkbox" onclick="initMapChart()"
				value="1">爱充网--</strong><input name="type_checkbox" type="checkbox"
				onclick="initMapChart()" value="2"><strong>国网--</strong><input
				name="type_checkbox" type="checkbox" onclick="initMapChart()"
				value="3"><strong>特斯拉</strong>
		</div>
		<div id="pileMapChartDiv" class="chartDiv1"
			style="float: left; width: 100%; border: 1px solid #ccc;"></div>
		<hr>
		<div>
			开始时间： <input class="date textInput readonly valid" id="begin_date"
				type="text" value=""
				onClick="WdatePicker({el:'begin_date',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'begin_date\')}'})" />
			结束时间： <input class="date textInput readonly valid" id="end_date"
				type="text" value=""
				onClick="WdatePicker({el:'end_date',minDate:'#F{$dp.$D(\'end_date\')}'})" /><input id='peopleButton'
				class="chart_button1" type="button" value="用户"
				onclick="drowecharts()" /> <input id='pileButton' class="chart_button"
				type="button" value="电桩" onclick="drowePileCharts()" />
		</div>
		<hr>
		<div id="peopleMonthZcDiv" class="chartDiv"
			style="float: left; width: 49%; border: 1px solid #ccc; padding: 5px;"></div>
		<div id="peopleZcFromDiv" class="chartDiv"
			style="float: right; width: 48%; border: 1px solid #ccc; padding: 5px;"></div>
		<div id="peopleChargeDiv" class="chartDiv"
			style="float: left; width: 49%; border: 1px solid #ccc; padding: 5px;"></div>
		<div id="peopleConsumptionDiv" class="chartDiv"
			style="float: right; width: 48%; border: 1px solid #ccc; padding: 5px;"></div>

		<div id="myChartPileBespokeDiv" class="chartDiv_other"
			style="float: left; width: 49%; border: 1px solid #ccc; padding: 5px;"></div>
		<div id="pileChargeDiv" class="chartDiv_other"
			style="float: right; width: 48%; border: 1px solid #ccc; padding: 5px;"></div>
		<div id="pileBesPokeDiv" class="chartDiv_other"
			style="float: left; width: 49%; border: 1px solid #ccc; padding: 5px;"></div>
		<div id="myChartPileChargeDiv" class="chartDiv_other"
			style="float: right; width: 48%; border: 1px solid #ccc; padding: 5px;"></div>
	</form>
</body>

<script type="text/javascript">
	var pileMapChartDiv;

	var myChartDatePeopleCountDiv;
	var myChartFromPeopleCountDiv;
	var myConsumptionChartDateDiv;
	var myChargeChartDateDiv;
	var begin_date_val;
	var end_date_val;
	
	var myChartPileBespokeDiv;
	var pileChargeDiv;
	var pileBesPokeDiv;
	var myChartPileChargeDiv;

	var boxHeight = $("div.layoutBox:first").height();
	$(".chartDiv").css("height",(boxHeight-50)/2);
	$(".chartDiv_other").hide();
	$(".chartDiv1").css("height",boxHeight-250);
	$("div.layoutBox:first").css("overflow-y","scroll");
	
	require.config({
		paths : {
			echarts : '<%=basePath%>/res/echarts/js',
		}
	});

	drowecharts();
	droweMapcharts();

	function droweMapcharts() {
		require([ 
		        'echarts', 
		        'echarts/theme/macarons',
				'echarts/chart/bar',
		        'echarts/chart/line',
		        'echarts/chart/pie',
		        'echarts/chart/map'
		
		], function(ec, theme) {
			pileMapChartDiv = ec.init(document.getElementById('pileMapChartDiv'), theme);
			initMapChart();
		});
	}

	function drowecharts() {
		$('#pileButton').removeClass('chart_button1');
		$('#pileButton').addClass('chart_button');
		$('#peopleButton').addClass('chart_button1');
		$(".chartDiv_other").hide();
		begin_date_val = $("#begin_date").val();
		end_date_val = $("#end_date").val();
		require([ 
		        'echarts', 
		        'echarts/theme/macarons',
				'echarts/chart/bar',
		        'echarts/chart/line',
		        'echarts/chart/pie',
		        'echarts/chart/gauge',
		        'echarts/chart/tree',
		        'echarts/chart/map' 
		
		], function(ec, theme) {
			myChartDatePeopleCountDiv = ec.init(document
					.getElementById('peopleMonthZcDiv'), theme);
			myChartFromPeopleCountDiv = ec.init(document
					.getElementById('peopleZcFromDiv'), theme);
			myConsumptionChartDateDiv = ec.init(document
					.getElementById('peopleChargeDiv'), theme);
			myChargeChartDateDiv = ec.init(document
					.getElementById('peopleConsumptionDiv'), theme);
			if (myChartPileBespokeDiv) {
				myChartPileBespokeDiv.clear();
				pileChargeDiv.clear();
				pileBesPokeDiv.clear();
				myChartPileChargeDiv.clear();
			}
			initPeopleCountChart()
			initChargeChart();
			initConsumptionChart();
		});
	}

	function initPeopleCountChart() {
		if (myChartDatePeopleCountDiv) {
			myChartDatePeopleCountDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPeopleCountDate.do?onlyData=1";
		if (begin_date_val != null && begin_date_val !="") {
			ajaxUrl += '&begin_date=' + begin_date_val
		} 
		if (end_date_val != null && end_date_val !="") {
			ajaxUrl += '&end_date=' + end_date_val
		}
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			dataType : "json",
			cache : false,
			success : function(json) {
				myChartDatePeopleCountDiv.setOption(json);
				$.ajax({
					type : 'POST',
					url : ajaxUrl,
					dataType : "json",
					cache : false,
					success : function(json) {
						if(json.data1 != null){
							myChartDatePeopleCountDiv.setOption(json.data1);
						}						
						if(json.data2 != null){
							myChartFromPeopleCountDiv.setOption(json.data2);
						}
					}
				});
			}
		});
	}	

	function initChargeChart() {
		if (myChargeChartDateDiv) {
			myChargeChartDateDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPeopleChargeDate.do?type=chargeBar";
		if (begin_date_val != null && begin_date_val !="") {
			ajaxUrl += '&begin_date=' + begin_date_val
		} 
		if (end_date_val != null && end_date_val !="") {
			ajaxUrl += '&end_date=' + end_date_val
		}
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			dataType : "json",
			cache : false,
			success : function(json) {
				myChargeChartDateDiv.setOption(json);
				$.ajax({
					type : 'POST',
					url : ajaxUrl,
					dataType : "json",
					cache : false,
					success : function(json) {
						myChargeChartDateDiv.setOption(json);
					}
				});
			}
		});
	}
	

	function initConsumptionChart() {
		if (myConsumptionChartDateDiv) {
			myConsumptionChartDateDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPeopleConsumptionDate.do?type=consumptionBar";
		if (begin_date_val != null && begin_date_val !="") {
			ajaxUrl += '&begin_date=' + begin_date_val
		} 
		if (end_date_val != null && end_date_val !="") {
			ajaxUrl += '&end_date=' + end_date_val
		}
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			dataType : "json",
			cache : false,
			success : function(json) {
				myConsumptionChartDateDiv.setOption(json);
				$.ajax({
					type : 'POST',
					url : ajaxUrl,
					dataType : "json",
					cache : false,
					success : function(json) {
						myConsumptionChartDateDiv.setOption(json);
					}
				});
			}
		});
	}
	
	
	
	function drowePileCharts() {
		$('#peopleButton').removeClass('chart_button1');
		$('#peopleButton').addClass('chart_button');
		$('#pileButton').addClass('chart_button1');
		$(".chartDiv_other").css("height",(boxHeight-50)/2);
		$(".chartDiv_other").show();
		require([ 
		        'echarts', 
		        'echarts/theme/macarons',
				'echarts/chart/bar',
		        'echarts/chart/line',
		        'echarts/chart/pie',
		
		], function(ec, theme) {
			myChartPileBespokeDiv = ec.init(document.getElementById('myChartPileBespokeDiv'), theme);
			pileChargeDiv = ec.init(document.getElementById('pileChargeDiv'), theme);
			pileBesPokeDiv= ec.init(document.getElementById('pileBesPokeDiv'), theme);
			myChartPileChargeDiv= ec.init(document.getElementById('myChartPileChargeDiv'), theme);
			
			myChartDatePeopleCountDiv.clear();
			myChartFromPeopleCountDiv.clear();
			myConsumptionChartDateDiv.clear();
			myChargeChartDateDiv.clear();
			
			initPileChart();
			initBespokeChart();
			initPileChargeChart();
			initMyChartPileChargeChart();
		});
	}
	
	function initPileChart() {
		if (myChartDatePeopleCountDiv) {
			myChartDatePeopleCountDiv.clear();
			myChartFromPeopleCountDiv.clear();
			myConsumptionChartDateDiv.clear();
			myChargeChartDateDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileInfoData.do";
		var startDate = $("#begin_date").val();
		var endDate = $("#end_date").val();
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			data : {
				onlyData : 1,
				startDate : startDate,
				endDate : endDate
			},
			dataType : "json",
			cache : false,
			success : function(json) {
				if(json.data1 != null){
					myChartDatePeopleCountDiv.setOption(json.data1);
				}
				if(json.data2 != null){
					myChartFromPeopleCountDiv.setOption(json.data2);
				}
				if(json.data3 != null){
					myConsumptionChartDateDiv.setOption(json.data3);
				}
				if(json.data4 != null){
					myChargeChartDateDiv.setOption(json.data4);
				}
			}
		});
	}

	function initBespokeChart() {
		if (myChartPileBespokeDiv) {
			myChartPileBespokeDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileBespokeDate.do?onlyData=1";
		if (begin_date_val != null && begin_date_val !="") {
			ajaxUrl += '&beginDate=' + begin_date_val
		} 
		if (end_date_val != null && end_date_val !="") {
			ajaxUrl += '&endDate=' + end_date_val
		}
		
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			dataType : "json",
			cache : false,
			success : function(json) {
				myChartPileBespokeDiv.setOption(json);
				$.ajax({
					type : 'POST',
					url : ajaxUrl,
					dataType : "json",
					cache : false,
					success : function(json) {
						if(json.data1 != null){
							if(json.data1 != null){
								myChartPileBespokeDiv.setOption(json.data1);
							}	
						}		
					}
				});
			}
		});
	}
	
	function initPileChargeChart() {
		if (pileChargeDiv) {
			pileChargeDiv.clear();
			pileBesPokeDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileChargeData.do";
		var startDate = $("#chartPileChargeStartDate").val();
		var endDate = $("#chartPileChargeEndDate").val();
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			data:{
				onlyData:1,
				startDate:startDate,
				endDate:endDate
			},
			dataType : "json",
			cache : false,
			success : function(json) {
				if(json.data1 != null){
					pileChargeDiv.setOption(json.data1);
				}
				if(json.data2 != null){
					pileBesPokeDiv.setOption(json.data2);
				}
			}
		});
	}
	
	function initMyChartPileChargeChart() {
		if (myChartPileChargeDiv) {
			myChartPileChargeDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileChargedData.do?type=pileChargeBar";
		if (begin_date_val != null && begin_date_val !="") {
			ajaxUrl += '&beginDate=' + begin_date_val
		} 
		if (end_date_val != null && end_date_val !="") {
			ajaxUrl += '&endDate=' + end_date_val
		}
		
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			dataType : "json",
			cache : false,
			success : function(json) {
				myChartPileChargeDiv.setOption(json);
				$.ajax({
					type : 'POST',
					url : ajaxUrl,
					dataType : "json",
					cache : false,
					success : function(json) {						
						myChartPileChargeDiv.setOption(json);
					
					}
				});
			}
		});
	}
	
	function initMapChart() {
		if (pileMapChartDiv) {
			pileMapChartDiv.clear();
		}
		
		var checkboxs = document.getElementsByName('type_checkbox');
		var checkTypeParam="";
		for(var i=0;i<checkboxs.length;i++){
			var checkbox_e = checkboxs[i];
			if(checkbox_e.checked){
				checkTypeParam += checkbox_e.value+",";
			}
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileMapData.do";
		if (checkTypeParam != "") {
			checkTypeParam = checkTypeParam
					.substr(0, checkTypeParam.length - 1);
		}
		var startDate = $("#begin_date").val();
		var endDate = $("#end_date").val();
		$.ajax({
			type : 'POST',
			url : ajaxUrl,
			data : {
				onlyData : 1,
				startDate : startDate,
				endDate : endDate,
				typeparams : checkTypeParam
			},
			dataType : "json",
			cache : false,
			success : function(json) {
				if(json.data1 != null){
					pileMapChartDiv.setOption(json.data1);
				}
			}
		});
	}

	function reMakeCount(this_e) {
		var dataTemp = mapJsonData;
		var dataLength = mapJsonData.length;
		for (var i = 0; i < dataLength; i++) {
			var JosnDataTemp = mapTypeData[dataTemp[i].name]
			//获取选中的种类
			var type = 0;
			//这里统计所选的种类数量
			var selectedTypeCount = JosnDataTemp[type];
			dataTemp[i].value = selectedTypeCount;
		}
		if (pileMapChartDiv) {
			pileMapChartDiv.clear();
			pileMapChartDiv.setOption(dataTemp);
		}
	}

	/**
	 * 复制对象
	 */
	function clone(myObj) {
		if (typeof (myObj) != 'object') {
			return myObj;
		}
		if (myObj == null) {
			return myObj;
		}
		var myNewObj = new Object();
		for ( var i in myObj) {
			myNewObj[i] = clone(myObj[i]);
		}
		return myNewObj;
	}
</script>