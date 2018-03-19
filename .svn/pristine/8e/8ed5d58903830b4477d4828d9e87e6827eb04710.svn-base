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
<title>实时信息</title>
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
	<div class="mainContent" style="width: 100%; background: #333"
		layoutH="0";>

		<input id="headIdDiv" type="hidden" value="${params.headId}" /><input
			id="headState" type="hidden" value="${params.status}" /> <input
			type="hidden" id="eCode" value="${params.eCode}" /> <input
			type="hidden" id="eChargeType" value="${params.eType}" /> <input
			id="pkHeadIdDiv" type="hidden" value="${params.pkHeadId}" /> <input
			type="hidden" id="titleName" value="${params.titleName}" />
			<input type="hidden" id="intervalIds" value="intervalIdLoadeShishi" />
			<input type="hidden" id="reloadF" value="loadShishiData" />

		<div class="mainTop" Style="height: 20px;">
			<div Style="text-align: center; margin-top: 10px;">${params.titleName}</div>
		</div>
		<div class="huadong">
			<div class="huadong-NAV huadong-NAV-current">实时信息</div>
			<div class="huadong-NAV" onclick="jumpToChargeLineTab()">充电曲线</div>
			<div class="huadong-NAV" onclick="jumpToChargeStatisticsTab()">充电统计</div>
			<div class="huadong-NAV" onclick="jumpHeadHistoryTab()">历史消息记录</div>
		</div>
		<div class="shishijiankong-main">

			<div
				style="width: 220px; float: left; margin-right: 20px; margin-top: 40px;">
				<div class="jiankong-box boxW220">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>运行数据</h3>
						<li><span id="ratedPower">--</span>电桩额定功率</li>
						<li><span id="chargeStatus">--</span>充电状态</li>
						<li><span id="chargeTime">--</span>本次已充电时间</li>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			<!-- 	<div class="jiankong-box boxW220" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				<ul class="boxMain-jiankong">
						<h3>数据统计</h3>
						<li><span class="JK-c-white" id="all_charge_count2">----</span>充电总次数</li>
						<li><span class="JK-c-white" id="all_charge_c2">----</span>总充电度数</li>
						<li><span class="JK-c-white" id="all_charge_time2">----</span>总充电时长</li>
					</ul> 
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div> -->
				<div class="jiankong-box boxW220" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>故障状态</h3>
						<li class="guzhuangZT ZT9">急停</li>
						<li class="guzhuangZT ZT5">防雷器故障</li>
						<li class="guzhuangZT ZT2">输入欠压</li>
						<li class="guzhuangZT ZT3">输入过压</li>
						<li class="guzhuangZT ZT10">读卡器故障</li>
						<li class="guzhuangZT ZT8">绝缘检查故障</li>
						<li class="guzhuangZT ZT4">过流</li>
						<li class="guzhuangZT ZT6">电度表故障</li>
						<li class="guzhuangZT ZT11">过温</li>
						<li class="guzhuangZT ZT7">接触器故障</li>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>

			<div
				style="width: 420px; float: left; margin-right: 20px; margin-top: 40px;">
				<div class="jiankong-box boxW420">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<!--<h3>运行数据</h3>-->
						<div style="height: 300px;" id="voltageDiv"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				<div class="jiankong-box boxW420" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<!--<h3>运行数据</h3>-->
						<div style="height: 300px;" id="currentDiv">222</div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>

			<div style="width: 420px; float: left; margin-top: 40px;">
				<div class="jiankong-box boxW420">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<!--<h3>运行数据</h3>-->
						<div style="height: 300px;" id="allChargeDiv">333</div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				<div class="jiankong-box boxW420" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<!--<h3>运行数据</h3>-->
						<div style="height: 300px;" id="powerRateDiv">444</div>
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
		src="<%=request.getContextPath()%>/static/js/statistics/headTabJumpAC.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/dataModel/echartsModel.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/statistics/shishixinxi.js"
		type="text/javascript"></script>
</body>
</html>
