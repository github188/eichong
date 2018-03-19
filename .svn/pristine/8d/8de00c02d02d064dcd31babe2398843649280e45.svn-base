function jumpheadNowDataTab() {
	var headId =  navTab.getCurrentPanel().find("#headIdDiv").val();
	var eCode = navTab.getCurrentPanel().find("#eCode").val();
	var eType = navTab.getCurrentPanel().find("#eChargeType").val();
	var status = navTab.getCurrentPanel().find("#headState").val();
	var titleName = navTab.getCurrentPanel().find("#titleName").val();
	var pkHeadId = navTab.getCurrentPanel().find("#pkHeadIdDiv").val();
	navTab.closeTab("headMonitorChargingTab")
	navTab.closeTab("headMonitorHistoryTab")
	navTab.closeTab("headChargeStatisticsTab")
	navTab.openTab("headMonitorNowTab", basepath
			+ "/admin/electricPileMonitorV2/headNowDataUiDC.do?headId="
			+ headId + "&eCode=" + eCode + "&eType=" + eType + "&status="
			+ status + "&titleName=" + titleName + "&pkHeadId=" + pkHeadId,
			{
				title : "实时信息",
				fresh : true
			});
	clearOtherIntervalIds();
}

function jumpToChargeLineTab() {
	var headId = navTab.getCurrentPanel().find("#headIdDiv").val();
	var eCode = navTab.getCurrentPanel().find("#eCode").val();
	var eType = navTab.getCurrentPanel().find("#eChargeType").val();
	var status = navTab.getCurrentPanel().find("#headState").val();
	var titleName = navTab.getCurrentPanel().find("#titleName").val();
	var pkHeadId = navTab.getCurrentPanel().find("#pkHeadIdDiv").val();
	navTab.closeTab("headMonitorHistoryTab")
	navTab.closeTab("headMonitorNowTab")
	navTab.closeTab("headChargeStatisticsTab")
	navTab.openTab("headMonitorChargingTab", basepath
			+ "/admin/electricPileMonitorV2/headChargeLineUiDC.do?headId="
			+ headId + "&eCode=" + eCode + "&eType=" + eType + "&status="
			+ status + "&titleName=" + titleName + "&pkHeadId=" + pkHeadId, {
		title : "充电曲线",
		fresh : true
	});
	clearOtherIntervalIds();
}

function jumpHeadHistoryTab() {
	var headId = navTab.getCurrentPanel().find("#headIdDiv").val();
	var eCode = navTab.getCurrentPanel().find("#eCode").val();
	var eType = navTab.getCurrentPanel().find("#eChargeType").val();
	var status = navTab.getCurrentPanel().find("#headState").val();
	var titleName = navTab.getCurrentPanel().find("#titleName").val();
	var pkHeadId = navTab.getCurrentPanel().find("#pkHeadIdDiv").val();
	navTab.closeTab("headMonitorChargingTab")
	navTab.closeTab("headMonitorNowTab")
	navTab.closeTab("headChargeStatisticsTab")
	navTab.openTab("headMonitorHistoryTab", basepath
			+ "/admin/electricPileMonitorV2/headHistoryUiDC.do?headId="
			+ headId + "&eCode=" + eCode + "&eType=" + eType + "&status="
			+ status + "&titleName=" + titleName + "&pkHeadId=" + pkHeadId, {
		title : "历史消息记录",
		fresh : true
	});
	clearOtherIntervalIds();
}

function jumpToChargeStatisticsTab() {
	var headId = navTab.getCurrentPanel().find("#headIdDiv").val();
	var eCode = navTab.getCurrentPanel().find("#eCode").val();
	var eType = navTab.getCurrentPanel().find("#eChargeType").val();
	var status = navTab.getCurrentPanel().find("#headState").val();
	var titleName = navTab.getCurrentPanel().find("#titleName").val();
	var pkHeadId = navTab.getCurrentPanel().find("#pkHeadIdDiv").val();
	navTab.closeTab("headMonitorNowTab")
	navTab.closeTab("headMonitorChargingTab")
	navTab.closeTab("headMonitorHistoryTab")
	navTab.openTab("headChargeStatisticsTab", basepath
			+ "/admin/electricPileMonitorV2/headChargeStatisticsUiDC.do?headId="
			+ headId + "&eCode=" + eCode + "&eType=" + eType + "&status="
			+ status + "&titleName=" + titleName + "&pkHeadId=" + pkHeadId, {
		title : "充电统计",
		fresh : true
	});
	clearOtherIntervalIds();
}

function openPowerModelTab() {
	var headId = navTab.getCurrentPanel().find("#headIdDiv").val();
	var eCode = navTab.getCurrentPanel().find("#eCode").val();
	var eType = navTab.getCurrentPanel().find("#eChargeType").val();
	var status = navTab.getCurrentPanel().find("#headState").val();
	var titleName = navTab.getCurrentPanel().find("#titleName").val();
	var pkHeadId = navTab.getCurrentPanel().find("#pkHeadIdDiv").val();
	var chargeStatus = navTab.getCurrentPanel().find("#chargeStatusDiv").val();
	clearInterval(intervalIdLoadeShishi);
	navTab.openTab("powerModuleTab", basepath
			+ "/admin/electricPileMonitorV2/powerModule.do?headId="
			+ headId + "&eCode=" +eCode + "&eType="
			+ eType + "&status=" + status+"&chargeStatus="+chargeStatus, {
		title : "电源模块",
		fresh : true
	});
}