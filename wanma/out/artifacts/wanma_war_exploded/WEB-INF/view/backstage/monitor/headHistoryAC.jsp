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
	<div class="mainContent" style="width: 102%; background: #333" layoutH="0";>
		<input id="headIdDiv" type="hidden" value="${params.headId}" /> <input
			id="dateStrDiv" type="hidden" value="" /> <input id="headState"
			type="hidden" value="${params.status}" /> <input type="hidden"
			id="eCode" value="${params.eCode}" /> <input type="hidden"
			id="eChargeType" value="${params.eType}" />
			
			<input id="pkHeadIdDiv" type="hidden" value="${params.pkHeadId}" />
			<input type="hidden" id="titleName" value="${params.titleName}"/>
			<div class="mainTop" Style="height:20px;">
			<div Style="text-align: center;margin-top:10px;">${params.titleName}</div>
			</div>
		<div class="huadong">
			<div class="huadong-NAV" onclick="jumpheadNowDataTab()">实时信息</div>
			<div class="huadong-NAV" onclick="jumpToChargeLineTab()">充电曲线</div>
			<div class="huadong-NAV" onclick="jumpToChargeStatisticsTab()">充电统计</div>
			<div class="huadong-NAV huadong-NAV-current">历史消息记录</div>
		</div>
		<div class="shishijiankong-main">
			<div style="width: 1000px; float: left; margin-top: 40px;">
				<div id="month-tabs" class="JK-liebiao-top">
					<span class="yue">5月</span> <span class="yue">4月</span> <span
						class="yue">3月</span> <span class="yue">2月</span> <span
						class="yue">1月</span> <span class="nian">2015年12月</span> <span
						class="yue">11月</span> <span class="yue">10月</span>
				</div>
				<div class="JK-liebiao-main">
					<ul id='infoUl'>
					</ul>
				</div>
				<!-- 分页 -->
				<div id="pageEle1" class="JK-fanye"
					style="bottom: 0px; width: 100%; height: 4%; background: #ffffff;">
				</div>
				<!-- 分页end -->
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/headMonitorHistory.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/statistics/headTabJumpAC.js"></script>
</body>
</html>
