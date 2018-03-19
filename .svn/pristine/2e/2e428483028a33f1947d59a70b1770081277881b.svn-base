<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>/res/echarts/js/echarts.js"></script>
<body>

<form id="pagerForm" method="post" action=""> 
	开始时间：
	<input class="date textInput readonly valid" id="chartPileChargeStartDate"
		type="text" value=""
		onClick="WdatePicker({el:'chartPileChargeStartDate',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'chartPileChargeEndDate\')}'})" />
	结束时间：
	<input class="date textInput readonly valid" id="chartPileChargeEndDate" type="text"
		value="" onClick="WdatePicker({el:'chartPileChargeEndDate',minDate:'#F{$dp.$D(\'chartPileChargeStartDate\')}'})" />
	<input type="button" value="生成图形" onclick="drowecharts()" />
	<hr/>
	<div id="pileChargingDiv" class="chartDiv"
		style="float:left;height:240px;width:49%; border: 1px solid #ccc; padding: 5px;"></div>
	<div id="pileBesPokingDiv" class="chartDiv"
		style="float:right;height: 240px;width:48%; border: 1px solid #ccc; padding: 5px;"></div>
</form>
</body>
<script type="text/javascript">
var boxHeight = $("div.layoutBox:first").height();
$(".chartDiv").css("height",(boxHeight-50)/2);
var placeHoledStyle = {
	    normal:{
	        barBorderColor:'rgba(0,0,0,0)',
	        color:'rgba(0,0,0,0)'
	    },
	    emphasis:{
	        barBorderColor:'rgba(0,0,0,0)',
	        color:'rgba(0,0,0,0)'
	    }
	};
	var dataStyle = { 
	    normal: {
	        label : {
	            show: true,
	            position: 'insideLeft',
	            formatter: '{c}%'
	        }
	    }
	};
	var pileChargingDiv;
	var pileBesPokingDiv;
	require.config({
		paths : {
			echarts : '<%=basePath%>/res/echarts/js',
		}
	});

	drowecharts();

	function drowecharts() {
		require([ 
		        'echarts', 
		        'echarts/theme/macarons',
				'echarts/chart/bar',
		        'echarts/chart/line',
		        'echarts/chart/pie',
		
		], function(ec, theme) {
			pileChargingDiv = ec.init(document.getElementById('pileChargingDiv'), theme);
			pileBesPokingDiv = ec.init(document.getElementById('pileBesPokingDiv'), theme);
			initChart();
		});
	}

	function initChart() {
		if (pileChargingDiv) {
			pileChargingDiv.clear();
			pileBesPokingDiv.clear();
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
				pileChargingDiv.setOption(json.data1);
				pileBesPokingDiv.setOption(json.data2);
			}
		});
	}
</script>