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
	<input class="date textInput readonly valid" id="beginDate"
		type="text" value=""
		onClick="WdatePicker({el:'beginDate',minDate:'1970-01-01'})" />
	结束时间：
	<input class="date textInput readonly valid" id="endDate" type="text"
		value="" onClick="WdatePicker({el:'endDate',minDate:'1970-01-01'})" />
	<input type="button" value="生成图形" onclick="drowecharts()" />
	<div id="myChartPileChargeCountDiv"
		style="height:500px; border: 1px solid #ccc; padding: 10px;"></div>

</form>
</body>
<script type="text/javascript">
	var myChartPileChargeCountDiv;
	var begin_date_val;
	var end_date_val;
	require.config({
		paths : {
			echarts : 'http://echarts.baidu.com/build/dist',
		}
	});

	drowecharts();

	function drowecharts() {
		begin_date_val = $("#beginDate").val();
		end_date_val = $("#endDate").val();		
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
			myChartPileChargeCountDiv = ec.init(document
					.getElementById('myChartPileChargeCountDiv'), theme);
			initChart()
		});
	}

	function initChart() {
		if (myChartPileChargeCountDiv) {
			myChartPileChargeCountDiv.clear();
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
				myChartPileChargeCountDiv.setOption(json);
				$.ajax({
					type : 'POST',
					url : ajaxUrl,
					dataType : "json",
					cache : false,
					success : function(json) {						
						myChartPileChargeCountDiv.setOption(json);
					
					}
				});
			}
		});
	}
</script>