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
	<div class="mainContent" style="width: 102%; background: #333"
		layoutH="0";>
		<input id="headIdDiv" type="hidden" value="${params.headId}" /> <input
			id="headState" type="hidden" value="${params.status}" /> <input
			type="hidden" id="eCode" value="${params.eCode}" /> <input
			type="hidden" id="eChargeType" value="${params.eType}" /> <input
			id="pkHeadIdDiv" type="hidden" value="${params.pkHeadId}" /> <input
			type="hidden" id="titleName" value="${params.titleName}" />
		<div class="mainTop" Style="height: 20px;">
			<div Style="text-align: center; margin-top: 10px;">${params.titleName}</div>
		</div>
		<div class="huadong">
			<div class="huadong-NAV" onclick="jumpheadNowDataTab()">实时信息</div>
			<div class="huadong-NAV" onclick="jumpToChargeLineTab()">充电曲线</div>
			<div class="huadong-NAV huadong-NAV-current">充电统计</div>
			<div class="huadong-NAV" onclick="jumpHeadHistoryTab()">历史消息记录
			</div>
		</div>
		<div class="shishijiankong-main">
			<div style="width: 1000px; float: left; margin-top: 40px;">
				<div class="chongdiantongji">
					<span>电桩ID：<strong>${map_demo.pkId}</strong></span> <span>电桩编码：<strong>${params.eCode}</strong></span>
					<span>枪头编号：<strong>${headId}</strong></span>
				</div>
				<div class="tongji_biaoge">
					<ul>
					<li class="tongji_biaoge_T"><span class="w140"
							style="font-size: 22px; font-weight: bold;">统计</span><span
							class="w160">今日<u>（${nowDay}）</u></span><span class="w140">本月<u>（${nowMonth}）</u></span><span
							class="w140">今年<u>（${nowYear}）</u></span><span class="w220">历史<u>（${between}）</u></span></li>
						<li class="tongji_biaoge_M">
						<span class="w140">充电次数<u>（次）</u></span>
						<span class="w160">${map_04.chCount01}</span>
						<span class="w140">${map_03.chCount01}</span>
						<span class="w140">${map_02.chCount01}</span>
						<span class="w220">${map_01.chCount01}</span></li>
						<li class="tongji_biaoge_M">
						<span class="w140">充电金额<u>（元）</u></span>
						<span class="w160">${map_04.chMoney01}</span>
						<span class="w140">${map_03.chMoney01}</span>
						<span class="w140">${map_02.chMoney01}</span>
						<span class="w220">${map_01.chMoney01}</span></li>
						<!-- 
						<li class="tongji_biaoge_M">
						<span class="w140">支付<u>（次）</u></span>
						<span class="w160">${map_04.chCount01}</span>
						<span class="w140">${map_03.chCount01}</span>
						<span class="w140">${map_02.chCount01}</span>
						<span class="w220">${map_01.chCount01}</span></li>
						-->
						<li class="tongji_biaoge_M">
						<span class="w140">充电度数<u>（度）</u></span>
						<span class="w160">${map_04.chQuantity01}</span>
						<span class="w140">${map_03.chQuantity01}</span>
						<span class="w140">${map_02.chQuantity01}</span>
						<span class="w220">${map_01.chQuantity01}</span></li>


					</ul>
				</div>


			</div>
		</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/static/js/statistics/headTabJumpAC.js"></script>
</body>
</html>
