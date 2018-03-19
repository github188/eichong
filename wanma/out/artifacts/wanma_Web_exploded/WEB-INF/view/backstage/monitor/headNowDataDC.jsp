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
<style type="">
.dcProcess {
	float: left;
	border-radius: 5px 5px 5px 5px;
	height: 90%;
	width: 13.5%;
	margin-top: 3px;
	margin-left: 2%;
	background: #ff8000;
	display: none
}

.dianchi {
	width: 139px;
	height: 67px;
	margin-left: 20%;
	margin-top: 100px;
	background: url(/wanma/static/images/img/electric/dianciBG.png);
	background-repeat: no-repeat;
}
</style>
</head>
<body>
	<div class="mainContent" style="width: 102%; background: #333" layoutH="0";>
	<input id="titleName" type="hidden" value="${params.titleName}" /> 
		<input id="headIdDiv" type="hidden" value="${params.headId}" /> <input
			id="headState" type="hidden" value="${params.status}" /> <input
			type="hidden" id="eCode" value="${params.eCode}" /> <input
			type="hidden" id="eChargeType" value="${params.eType}" />
		<input id="pkHeadIdDiv" type="hidden" value="${params.pkHeadId}" />
		<input type="hidden" id="chargeStatusDiv" value="" />
		<input type="hidden" id="intervalIds" value="intervalIdLoadeShishi" />
			<input type="hidden" id="reloadF" value="loadShishiData" />
      <div class="mainTop" Style="height:20px;">
			<div Style="text-align: center;margin-top:10px;">${params.titleName}</div>
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
						<li><span id="ratedPower"></span>电桩额定功率</li>
						<li><span id="chargeStatus">--</span>充电状态</li>
						<li><span id="chargeModel">--</span>充电模式</li>
						<li><span id="oneT">--</span>电源单体最高温度</li>
						<li><span id="chargeTime">--</span>本次已充电时间</li>
						<li><span id="lastTime">--</span>估计剩余充电时间</li>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				<div class="jiankong-box boxW220" style="margin-top: 20px;">
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
				</div>
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
						<li class="guzhuangZT ZT12">输出过流</li>
						<li class="guzhuangZT ZT13">输出过压</li>
						<li class="guzhuangZT ZT15">BMS欠压</li>
						<li class="guzhuangZT ZT14">BMS过压</li>
						<li class="guzhuangZT ZT16">BMS通信异常</li>
						<li class="guzhuangZT ZT17">蓄电池过温告警</li>
						<li class="guzhuangZT ZT18">蓄电池过流告警</li>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>

			<div
				style="width: 280px; float: left; margin-right: 20px; margin-top: 40px;">
				<div class="jiankong-box boxW280">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<div style="height: 250px;" id="voltageDiv"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				<div class="jiankong-box boxW280" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<div style="height: 250px;" id="currentDiv"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>

			<div
				style="width: 280px; float: left; margin-right: 20px; margin-top: 40px;">
				<div class="jiankong-box boxW280">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<div style="height: 250px;" id="allChargeDiv"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
				<div class="jiankong-box boxW280" style="margin-top: 20px;">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<div style="height: 250px;" id="powerRateDiv"></div>
					</ul>
					<ul class="jiugongge_t">
						<li class="jiugongge_b_r"></li>
						<li class="jiugongge_b_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
				</div>
			</div>

			<div style="width: 250px; float: left; margin-top: 40px;">
				<div class="jiankong-box boxW250">
					<ul class="jiugongge_t">
						<li class="jiugongge_t_r"></li>
						<li class="jiugongge_t_l"></li>
						<li class="jiugongge_t_c"></li>
					</ul>
					<ul class="boxMain-jiankong">
						<h3>车端数据</h3>
						<li class="xiahuaxian"><span id="batteryType"></span>电池类型</li>
						<li class="xiahuaxian"><span id="carIdentification"></span>车辆识别码</li>
						<li class="xiahuaxian"><span id="carTotalVoltage"></span>车端总电压</li>
						<li class="xiahuaxian"><span id="bpHighestVoltage"></span>单体最高电压和组号</li>
						<li class="xiahuaxian"><span id="bpHighestTemperature"></span>单体电池最高温度</li>
						<li class="xiahuaxian"><span id="bpLowestTemperature"></span>单体电池最低温度</li>
						<li><span></span>SOC</li>
					</ul>
					<ul class="boxMain-jiankong">
						<div style="height: 330px;" class="xiahuaxian">

							<!-- 实时电池容量 -->
							<div
								style="width: 100%; height: 87%;; opacity: 0.8; background: #000; float: left; border: 1px solid #ccc; margin-right: 1%; display: inline-block;">
								<div
									style="width: 70%; height: 13%; float: left; opacity: 0.8; background: #000; margin-top: 5%; margin-left: 5px; font-size: 14px; color: #ff8000; font-family: 黑体;">实时电池容量（100%）</div>
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
									style="width: 52%; height: 12%; float: left; opacity: 0.8; background: #000; margin-top: 15%; margin-left: 25%; font-size: 14px; color: #ff8000; font-family: 黑体;">电池容量（0%）</div>
							</div>
						</div>
					
						<div class="JK-shujv-svn">
							<a href="#" onclick="openPowerModelTab()">车端电源模块数据</a>
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
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/headTabJumpDC.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/dataModel/echartsModel.js"></script>
	<script
		src="<%=request.getContextPath()%>/static/js/statistics/shishixinxi.js"
		type="text/javascript"></script>
</body>
</html>
