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
<title>电源模块</title>
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
	<!--电池模块=======-->
	<input id="headIdDiv" type="hidden" value="${params.headId}" />
	<input id="headState" type="hidden" value="${params.status}" />
	<input type="hidden" id="eCode" value="${params.eCode}" />
	<input type="hidden" id="eChargeType" value="${params.eType}" />
	<input type="hidden" id="chargeStatusDiv" value="${params.chargeStatus}" />
	<div class="MyBox" id="MyBox"
		style="width: 100%; height: 100%; position: absolute; top: -50px">
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/util.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/static/js/statistics/powerModuleIndex.js"></script>
	<script>
		var powerChargingLineParams = {
			eCode : $("#eCode").val(),
			headId : $("#headIdDiv").val(),
			eType : $("#eChargeType").val()
		}
		function showPowerChargingLineChart(index) {
			var indexVal = index;
			navTab
					.openTab(
							"powerChargingLine",
							basepath
									+ "/admin/electricPileMonitorV2/powerChargingLineUi.do?headId="
									+ $("#headIdDiv").val() + "&eCode="
									+ $("#eCode").val() + "&eType="
									+ $("#eChargeType").val() + "&status="
									+ $('#headState').val() + "&index="
									+ indexVal, {
								title : "源端实时充电曲线",
								fresh : false,
								data : powerChargingLineParams
							});
		}
	</script>
</body>
</html>
