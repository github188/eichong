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
	<input class="date textInput readonly valid" id="consumption_begin_date"
		type="text" value=""
		onClick="WdatePicker({el:'consumption_begin_date',minDate:'1970-01-01'})" />
	结束时间：
	<input class="date textInput readonly valid" id="consumption_end_date" type="text"
		value="" onClick="WdatePicker({el:'consumption_end_date',minDate:'1970-01-01'})" />
	<input type="button" value="生成图形" onclick="drowecharts()" />
	<div id="myConsumptionChartDateDiv"
		style="height:500px; border: 1px solid #ccc; padding: 10px;"></div>
</form>
</body>
<script type="text/javascript">
	var myConsumptionChartDateDiv;
	var begin_date_val;
	var end_date_val;
	require.config({
		paths : {
			echarts : '<%=basePath%>/res/echarts/js',
		}
	});

	drowecharts();

	function drowecharts() {
		begin_date_val = $("#consumption_begin_date").val();
		end_date_val = $("#consumption_end_date").val();
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
			if(begin_date_val>end_date_val && end_date_val != ""){
				alert("开始时间不可大于结束时间！")
				return;
			}
			myConsumptionChartDateDiv = ec.init(document
					.getElementById('myConsumptionChartDateDiv'), theme);
			initChart()
		});
	}

	function initChart() {
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
</script>