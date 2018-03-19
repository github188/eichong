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
	<input class="date textInput readonly valid" id="chartPileStartDate"
		type="text" value=""
		onClick="WdatePicker({el:'chartPileStartDate',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'chartPileEndDate\')}'})" />
	结束时间：
	<input class="date textInput readonly valid" id="chartPileEndDate" type="text"
		value="" onClick="WdatePicker({el:'chartPileEndDate',minDate:'#F{$dp.$D(\'chartPileStartDate\')}'})" />
	<input type="button" value="生成图形" onclick="drowecharts()" />
	<hr/>
	<div id="pileCountDiv" class="chartDiv"
		style="float:left;width:49%; border: 1px solid #ccc; padding: 5px;"></div>
	<div id="pileConsumeDiv" class="chartDiv"
		style="float:right;width:48%; border: 1px solid #ccc; padding: 5px;"></div>
	<div id="pileDistributeDiv" class="chartDiv"
		style="float:left;width:49%; border: 1px solid #ccc; padding: 5px;"></div>	
	<div id="pileFaultDiv" class="chartDiv"
		style="float:right;width:48%; border: 1px solid #ccc; padding: 5px;"></div>		
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
	var pileCountDiv;
	var pileConsumeDiv;
	var pileDistributeDiv;
	var pileFaultDiv;
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
			pileCountDiv = ec.init(document.getElementById('pileCountDiv'), theme);
			pileConsumeDiv = ec.init(document.getElementById('pileConsumeDiv'), theme);
			pileDistributeDiv= ec.init(document.getElementById('pileDistributeDiv'), theme);
			pileFaultDiv= ec.init(document.getElementById('pileFaultDiv'), theme);
			initChart();
		});
	}

	function initChart() {
		if (pileCountDiv) {
			pileCountDiv.clear();
			pileConsumeDiv.clear();
			pileDistributeDiv.clear();
			pileFaultDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileInfoData.do";
		var startDate = $("#chartPileStartDate").val();
		var endDate = $("#chartPileEndDate").val();
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
				//alert(JSON.stringify(json.data3));
				pileCountDiv.setOption(json.data1);
				pileConsumeDiv.setOption(json.data2);
				pileDistributeDiv.setOption(json.data3);
				pileFaultDiv.setOption(json.data4);
			}
		});
	}
</script>