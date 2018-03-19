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
<style>
.boxMain-jiankong-all {
	position: absolute;
	top: 10%;
	left: 30%;
	right: 0;
	bottom: 0;
	width: 410px;
	opacity: 0.9;
	height: 600px;
	border-radius: 8px;
}

.closeP {
	display: block;
	height: 18px;
	width: 18px;
	float: right;
	margin-top: -10px;
	background: #f00;
	line-height: 18px;
	text-align: center;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="mainContent" style="width: 102%; background: #333" layoutH="0";>
		<input id="headIdDiv" type="hidden" value="${params.headId}" /><input
			id="lineSplitNum" type="hidden" value="3" /> <input id="headState"
			type="hidden" value="${params.status}" /> <input type="hidden"
			id="eCode" value="${params.eCode}" /> <input type="hidden"
			id="eChargeType" value="${params.eType}" />
		<input id="titleName" type="hidden" value="${params.titleName}" /> 
		<input id="pkHeadIdDiv" type="hidden" value="${params.pkHeadId}" />
		<input type="hidden" id="intervalIds" value="chargingIntervalIdDC" />
		<input type="hidden" id="reloadF" value="loadChargeDataF" />
      <div class="mainTop" Style="height:20px;">
			<div Style="text-align: center;margin-top:10px;">${params.titleName}</div>
	</div>
		<div id="huadongDiv" class="huadong">
			<div class="huadong-NAV" onclick="jumpheadNowDataTab()">实时信息</div>
			<div class="huadong-NAV huadong-NAV-current">充电曲线</div>
			<div class="huadong-NAV" onclick="jumpToChargeStatisticsTab()">充电统计</div>
			<div class="huadong-NAV" onclick="jumpHeadHistoryTab()">历史消息记录</div>
		</div>
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
						<h3>三相电压电流曲线</h3>
						<div id="headOutputVoltage" style="height: 220px;"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				
				<div class="jiankong-box boxW680" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>输出电压电流曲线</h3>
						<div id="headOutputVCOne" style="height: 220px;"></div>
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
						<h3>输出温度曲线</h3>
						<div id="headTemperature" style="height: 220px;"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>
			<div style="width: 400px; float: left; margin-top: 40px;">
				<div class="jiankong-box boxW400" style="width:500px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>
							<span><a href="#" onclick="showAll()">查看全部数据</a></span>充电数据采样表
						</h3>
					</ul>
					<ul class="boxMain-jiankong">
						<div class="caiyangbiao-01">
							<span class="caiyangbiaoW75">时间</span><span
								class="caiyangbiaoW75">电表读数</span><span class="caiyangbiaoW100">已充度数（度）</span><span
								class="caiyangbiaoW100">已充金额（元）</span><span
							class="caiyangbiaoW100">电池（%）</span>
						</div>
						<div id="chargeDataSom">
						</div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>

			</div>

		</div>
		<div id="chargeDataAllDiv"
			style="width: 100%; height: 100%; background: #333; opacity: 0.9; position: relative; display: none">
			<div class="jiankong-box boxW400 boxMain-jiankong-all" style="width:500px; z-index: 8000">
				<div class="boxMain-jiankong">
					<h3>
						<br> 充电数据采样表 
					</h3>
					<span class="closeP" onclick="closeAll();">X</span>
				</div>
				<ul class="boxMain-jiankong">
					<div class="caiyangbiao-01">
						<span class="caiyangbiaoW75">时间</span><span class="caiyangbiaoW75">电表读数</span><span
							class="caiyangbiaoW100">已充度数（度）</span><span
							class="caiyangbiaoW100">已充金额（元）</span><span
							class="caiyangbiaoW100">电池（%）</span>
					</div>
					<div id="chargeDataAll"
						style="position: absolute; overflow-y: scroll; height: 75%;">
					</div>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/dataModel/echartsModel.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/headMonitorChargingDC.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/headTabJumpDC.js"></script>
</body>
</html>
