<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史消息记录</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<link href="<%=basePath%>/static/css/headMonitor/headMonitorTab3.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width: 100%; background: #333" layoutH="0";>
		<input id="headIdDiv" type="hidden" value="${params.headId}" /> <input id="headState"
			type="hidden" value="${params.status}" /> <input
				type="hidden" id="eCode" value="${params.eCode}" /> <input
				type="hidden" id="eChargeType" value="${params.eType}" /> <input
				type="hidden" id="index" value="${params.index}" /><input
			id="lineSplitNum" type="hidden" value="3" /> 
			<input type="hidden" id="intervalIds" value="powerChargingIntervalId" />
			<input type="hidden" id="reloadF" value="loadPowerChargeDataF" />
		<div class="shishijiankong-main">
			<div
				style="width: 680px; float: left; margin-right: 20px; margin-top: 40px;">
				<div class="jiankong-box boxW680">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>输出电压曲线</h3>
						<div id="powerOutputVoltage" style="height: 220px;"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				<!--输出温度曲线-->
				<div class="jiankong-box boxW680"
					style="margin-top: 20px; margin-bottom: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>温度曲线</h3>
						<div id="powerTemperature" style="height: 220px;"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>
			<div style="width: 400px; float: left; margin-top: 20px;">
				<div class="jiankong-box boxW680" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>输出电流曲线</h3>
						<div id="powerOutputCurrent" style="height: 220px;"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>

			</div>

		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/dataModel/echartsModel.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/powerModule.js"></script>
</body>
</html>
