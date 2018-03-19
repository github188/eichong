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
		<input type="hidden" id="cityCode" value="${cityCode}" /> <input
			type="hidden" id="cityName" value="${cityName}" /> <input
			type="hidden" id="jwdStr" value="${jwdStr}" />

		<!--------筛选结束--------->

		<div class="map" id="allMap" layoutH="1" style="position: reletive;">
		</div>
		<!-- 数量统计 -->
		<div id="pileCountDiv"
			style="width: 430px; height: 150px; left: 0; bottom: 0; position: absolute; z-index: 1000; color: #1A1A1A; background: #EAEAEA; display: none">
		</div>

		<!--列表内容-->
		<div id="tableList" class="tableListStyle"></div>
		<div id="mainList" class="mainListStyle">

			<!-- 搜索区域 -->
			<div style="width: 100%; height: 4%; margin-top: 1%;">
				<span style="display: block; float: left; width: 85%;"> <input
					name="pileName" type="text" id="serach" class="textInput"
					style="width: 100%; height: 24px;" placeholder="充电点名称或地址"
					style="display: block; width: 100%; height: 30px;"></span><a
					href="#" onclick="searchAll(1,10,1)"><span
					style="display: block; float: left; width: 15%; height: 30px; background: #ff8800;">
						<img style="margin: 15% 0px 0px 24%;"
						src="<%=basePath%>/static/images/img/electric/selector.png"
						width="50%" height="55%">
				</span></a>
			</div>
			<div
				style="width: 100%; min-height: 5%; margin-top: 2%; background: #fff">
				<!-- <input id="" type="checkbox" name="headState" value="3"
					otherCheckBox="pileStatus" onclick="remakeCheckBox(this)" />预约  -->
				<input
					id="" type="checkbox" name="headState" value="6"
					otherCheckBox="pileStatus" onclick="remakeCheckBox(this)" />充电 
			   <input
					id="" type="checkbox" name="headState" value="0"
					otherCheckBox="pileStatus" onclick="remakeCheckBox(this)" />空闲
				<input
					id="" type="checkbox" name="headState" value="17"
					otherCheckBox="pileStatus" onclick="remakeCheckBox(this)" />等待
				<input id="" type="checkbox" name="headState" value="9"
					otherCheckBox="pileStatus" onclick="remakeCheckBox(this)" />故障 <br>
				<input
					id="pileStatus" type="checkbox" name="pileStatus" value="0"
					otherCheckBox="headState" onclick="remakeCheckBox(this)" />通讯中断
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

		<div style="clear: both;"></div>
	</div>
	<!-- end content -->
	<!--//footer-->

	<link href="<%=basePath%>/static/css/monitor.css" rel="stylesheet"
		type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/lib/json3/json3.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/dataModel/jwd.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/config.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/electric-map.js"></script>
</body>
</html>
