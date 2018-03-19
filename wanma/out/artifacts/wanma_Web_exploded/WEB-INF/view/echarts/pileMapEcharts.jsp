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
	<div id="pileMapDiv" class="chartDiv"
		style="float:left;width:100%; border: 1px solid #ccc; "></div>
</form>
</body>
<script type="text/javascript">
var boxHeight = $("div.layoutBox:first").height();
$(".chartDiv").css("height",boxHeight);
	var pileMapDiv;
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
		        'echarts/chart/map'
		
		], function(ec, theme) {
			pileMapDiv = ec.init(document.getElementById('pileMapDiv'), theme);
			initChart();
		});
	}

	function initChart() {
		if (pileMapDiv) {
			pileMapDiv.clear();
		}
		var ajaxUrl = "<%=basePath%>/admin/chart/getPileMapData.do";
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
				pileMapDiv.setOption(json.data1);
			}
		});
	}
</script>