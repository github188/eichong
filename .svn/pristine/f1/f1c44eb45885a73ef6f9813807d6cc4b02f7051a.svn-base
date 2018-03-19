<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电桩查找</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<style type="text/css">
.amap-marker-label {
	border: 0px solid #000;
	font-weight: bold;
	font-size: 18px;
	font-family: "Arial";
	padding: 0px;
}

.marker-label {
	border: 0px solid #fff;
	position: absolute;
	z-index: 2;
	background-color: #fff;
	white-space: nowrap;
	cursor: default;
	border: 0px solid #000;
	font-weight: bold;
	font-size: 18px;
	font-family: "Arial";
	padding: 0px;
	line-height: 14px;
}
</style>
</head>
<body style="background-color: #9C9;">
	<div id="wrapper">

		<!--------筛选结束--------->

		<div class="map" id="allMap" layoutH="5" style="position: reletive;">
			<!-- 实时图表信息 -->
			<div id="chartDiv"
				style="width: 82.2%; background: #000; position: absolute; bottom: 0px; left: 0; z-index: 100; opacity: 0.8; padding-left: 0px; overflow: hidden;">
				<div id="slideDiv"
					style="width: 100%; height: 25px; top: 1%; text-align: center;">
					<a id="slideDivA" href="#" onclick="chartDivSlideDown()"><img
						id="slideImg" style="margin-top: 0.3%; border: 0;"
						src="<%=basePath%>/static/images/img/electric/slideDown.png"
						width="26" height="15"> </a>
				</div>
				<div id="chartDivIn"
					style="width: 100%; height: 200px; margin-bottom: 1%;">
					<!-- 实时电压 -->
					<div id="voltageDiv"
						style="width: 23.5%; height: 100%; opacity: 0.8; background: #000; float: left; border: 1px solid #ccc; margin-left: 1%; margin-right: 1%; display: inline-block;"></div>

					<!-- 实时电流 -->
					<div id="currentDiv"
						style="width: 23.5%; height: 100%; opacity: 0.8; background: #000; float: left; border: 1px solid #ccc; margin-right: 1%; display: inline-block;"></div>

					<!-- 实时电池容量 -->
					<div
						style="width: 23.5%; height: 100%;; opacity: 0.8; background: #000; float: left; border: 1px solid #ccc; margin-right: 1%; display: inline-block;">
						<div
							style="width: 60%; height: 13%; float: left; opacity: 0.8; background: #000; margin-top: 1.5%; margin-left: 5px; font-size: 14px; color: #ff8000; font-family: 黑体;">实时电池容量（100%）</div>
						<div id='allLine' class="dianchi">
							<div id='dianchiProcessDiv1' class="dcProcess"></div>
							<div id='dianchiProcessDiv2' class="dcProcess"></div>
							<div id='dianchiProcessDiv3' class="dcProcess"></div>
							<div id='dianchiProcessDiv4' class="dcProcess"></div>
							<div id='dianchiProcessDiv5' class="dcProcess"></div>
							<div id='dianchiProcessDiv6' class="dcProcess"></div>
							<div id="dianchiLine" style="width: 0px; display: none"></div>
						</div>
						<div id="percent"
							style="width: 52%; height: 12%; float: left; opacity: 0.8; background: #000; margin-top: 1%; margin-left: 25%; font-size: 14px; color: #ff8000; font-family: 黑体;">电池容量（0%）</div>
					</div>

					<!-- 实时温度 -->
					<div id="centered"
						style="width: 23.5%; height: 100%; float: left; background: #000; margin-right: 0px; opacity: 0.8; border: 1px solid #ccc;">
						<div
							style="width: 300px; height: 10px; opacity: 0.8; background: #000; float: left; margin-top: 10px; margin-left: 5px; font-size: 14px; color: #ff8000; font-family: 黑体;">电池组平均温度（℃）</div>
						<div id="mometer" style="left: 30%">
							<div id="hot"></div>
							<div>
								<img style="margin-top: 100px; margin-left: -15px;"
									src="<%=basePath%>/static/images/wenduji/glassBottom.png">
							</div>
							<span id="detailElectric">
								<div id="Hgheader">0℃</div>
								<div id="Hg"></div>
							</span>
						</div>
						<input type="hidden" name="detailTemperature" type="text" id="num" />
						<input type="hidden" name="" type="button" id="Risk" value="查看度数" />
					</div>
				</div>
			</div>
		</div>
		<!-- 数量统计 -->
		<div id="pileCountDiv"
			style="width: 130px; height: 150px; left: 0; bottom: 0; position: absolute; z-index: 1000; opacity: 0.8; color: #fff;">

			<!-- 故障桩体 -->
			<div id="pileErrorDiv"
				style="width: 130px; height: 30px; background: #ea0000; margin-botton: 5px; border: 1px solid #ccc; overflow: hidden; cursor: pointer;">
				<div
					style="text-align: right; line-height: 30px; width: 75px; height: 30px; float: left;">故障数：</div>
				<div id="pileErrorCount"
					style="text-align: left; line-height: 30px; width: 55px; height: 30px; float: right;"></div>
			</div>

			<!-- 充电点 -->
			<div
				style="width: 130px; height: 30px; background: #ff7d00; margin-botton: 5px; border: 1px solid #ccc;">
				<div
					style="text-align: right; line-height: 30px; width: 75px; height: 30px; float: left;">充电点：</div>
				<div id="psCount"
					style="text-align: left; line-height: 30px; width: 55px; height: 30px; float: right;"></div>
			</div>

			<!-- 充电中 -->
			<div
				style="width: 130px; height: 30px; background: #ff7d00; margin-botton: 5px; border: 1px solid #ccc; overflow: hidden">
				<div
					style="text-align: right; line-height: 30px; width: 75px; height: 30px; float: left;">充电中：</div>
				<div id="chargingCount"
					style="text-align: left; line-height: 30px; width: 55px; height: 30px; float: right;"></div>
			</div>

			<!-- 预约中 -->
			<div
				style="width: 130px; height: 30px; background: #ff7d00; margin-botton: 5px; border: 1px solid #ccc; overflow: hidden">
				<div
					style="text-align: right; line-height: 30px; width: 75px; height: 30px; float: left;">预约中：</div>
				<div id="bespokeCount"
					style="text-align: left; line-height: 30px; width: 55px; height: 30px; float: right;"></div>
			</div>
		</div>

		<!--列表内容-->
		<div id="tableList" class="tableListStyle"></div>
		<!--浮动的尖峰平谷汇率-->
		<div id="fengziDiv" style="z-index: 7000;position: absolute;right: 5%;top: 150px;height: 180px;width: 10%;margin: 10px;display:none">
			<div class="JFPG">
				<ul id="fengziU" class="JFPG_BOX">
				</ul>
			</div>
		</div>
		<!--汇率结束-->
		<div id="mainList" class="mainListStyle">

			<!-- 搜索区域 -->
			<div style="width: 100%; height: 4%; margin-top: 1%;">
				<span style="display: block; float: left; width: 85%;"> <input
					name="pileName" type="text" id="serach" class="textInput" style="width:100%;height:24px;"
					placeholder="直接搜索充电点"
					style="display: block; width: 100%; height: 30px;"></span><a
					href="#" onclick="searchAll(1,10,1)"><span
					style="display: block; float: left; width: 15%; height: 30px; background: #ff8800;">
						<img style="margin: 15% 0px 0px 24%;"
						src="<%=basePath%>/static/images/img/electric/selector.png"
						width="50%" height="55%">
				</span></a>
			</div>
			<div style="width: 100%; height: 4%; margin-top: 2%;">
				<select class="provinceCode required" id="electricSelProvince"
					next="electricSelCity" name="elPiOwnProvinceCode"
					style="float: none; width: 32%; height: 90%;">
					<option value="">全国</option>
					<c:forEach var="item" items="${proviceMap}">
						<option value="${item.key}"
							${item.key== tblElectricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''}>
							${item.value.PROVINCE_NAME}</option>
					</c:forEach>
				</select> <select class="cityCode required" id="electricSelCity"
					next="electricSelDistrict"
					data-val="${tblElectricpile.elPiOwnCityCode}"
					name="elPiOwnCityCode"
					style="float: none; height: 90%; width: 32%;">
					<option value="">选择市</option>
				</select> <select id="electricSelDistrict"
					data-val="${tblElectricpile.elPiOwnCountyCode}"
					name="elPiOwnCountyCode" class="required"
					style="float: none; width: 32%; height: 90%;">
					<option value="">选择区</option>
				</select>
			</div>
			<!-- 搜索区域end-->


			<!-- 列表区域 -->
			<div id="list" class="listInfo" style="margin-top: 2%">
				<div id="listDetail"
					style="height: 99%; overflow-y: auto; overflow-x: hidden;"></div>
				<div id="pileDetailList" class="pileDetailListStyle"
					style="height: 99%; width: 99%; overflow-y: auto; overflow-x: hidden; display: none">

				</div>
				<div style="clear: both;"></div>

				<!-- 分页 -->
				<div id="pageEle"
					style="bottom: 0px; width: 100%; height: 4%; background: #ffffff;">
				</div>
				<!-- 分页end -->

			</div>
			<!-- 列表区域end-->


		</div>

		<!-- 详情列表区域 -->
		<div id="headBranchListDiv" class="branchListStyle"
			style="background: #fff;z-index: 6001; display: none; overflow-y: scroll;"></div>
		<!-- 详情列表区域end -->
		<div id="pileBranchListDiv" class="branchListStyle"
			style="z-index: 6000;background: #fff; display: none; overflow-y: scroll;"></div>

		<!-- 视频监控区域 -->
		<div
			style="z-index: 5000; position: absolute; right: 0.5%; top: 71%; width: 15.5%; margin: 10px; height: 25%; display: none;">
			<img src="<%=basePath%>/static/images/img/shipin.png" width="100%"
				height="100%">
		</div>
		<!-- 视频监控区域end -->

		<div style="clear: both;"></div>
	</div>
	<!-- end content -->
	<!--//footer-->
	
	<link href="<%=basePath%>/static/css/monitor.css" rel="stylesheet"
		type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/config.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/lib/json3/json3.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/electric/electric-count-query.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/electric/electric-map-chart.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/electric/electric-map-temperature.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/electric/electric-map.js"></script>

	<script type="text/javascript">
		$(function() {
			loadCity($("#electricSelProvince"));
			loadArea($("#electricSelCity"));
		});
	</script>
</body>
</html>
