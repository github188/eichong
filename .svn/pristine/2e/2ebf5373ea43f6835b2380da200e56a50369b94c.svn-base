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
<title></title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<link href="/wanma/static/css/pileDetail.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<!--第一大块蛇形线部分========================================-->
	<div class="mainContent" layoutH="0" style="width: 102%;">
		<input type="hidden" id="eId" value="${params.eId}" /> 
		<input type="hidden" id="eType" value="${params.eType}" />
		<input type="hidden" id="enames" value="${params.eName}" />
		<input type="hidden" id="intervalIds" value="getHeadChargingInfoIntervalId" />
			<input type="hidden" id="reloadF" value="getHeadChargingInfoF"/>
		<div class="mainTop">
			<div class="mainTip">数据更新时间：</div>
			<div id="systemTime" class="mainTip time">12:35</div>
			<div id="refreshHeadDetail" class="mainTip refresh">刷新</div>
			<div class="mainTip electricStation">${params.eName}</div>
		</div>
		<div id="headDetailFatherDiv" class="mainCenter">
			<!--一个pileList里面有两个ul，一个ul里面有四个li-->
		</div>
	</div>
	<!--右侧提示箭头===========================================-->
	<div class="row" onclick="showMsg()">站点详情</div>
	<!--右边栏提示信息部分========================================-->
	<div class="rightTip">
		<!--总数汇总======================================-->
		<div class="allCount">
			<div class="allChargeTimes">
				<div class="lable">总充电次数</div>
				<div class="lableValue">
					<span id="all_charge_count_txt2" class="lableValue"></span>次
				</div>

			</div>
			<div class="allChargeTimes">
				<div class="lable">总充电度数</div>
				<div class="lableValue">
					<span id="all_charge_c_txt2" class="lableValue"></span>度
				</div>
			</div>
			<div class="allChargeTimes">
				<div class="lable">总充电时长</div>
				<div class="lableValue">
					<span id="all_charge_time_txt2" class="lableValue"></span>小时
				</div>
			</div>
		</div>
		<!--电桩总数============================================-->
		<div class="pileTotal">
			<div class="propertyList">
				<div class="propertyTag">电桩总数</div>
				<div id="cdd_dzcount_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">枪口数</div>
				<div id="pile_head_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">交流桩</div>
				<div id="ac_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">直流桩</div>
				<div id="dc_txt2" class="propertyTagValue"></div>
			</div>
		</div>
		<!--状态枪口总数========================================-->
		<div class="pileStatusTotal">
			<div class="propertyList">
				<div class="propertyTag">预约中枪口数</div>
				<div id="bespoke_head_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">充电中枪口数</div>
				<div id="charging_head_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">空闲枪口数</div>
				<div id="free_head_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">故障枪口数</div>
				<div id="error_head_txt2" class="propertyTagValue"></div>
			</div>
			<div class="propertyList">
				<div class="propertyTag">断开枪口数</div>
				<div id="off_pile_txt2" class="propertyTagValue"></div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/statistics/pileDetail.js"
	type="text/javascript"></script>