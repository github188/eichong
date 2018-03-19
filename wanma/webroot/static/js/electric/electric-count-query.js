/**
 * 获取数量统计信息
 * 
 * @param hideId
 */
function queryAllCount() {
	pileErrorCount();// 获取故障电桩数量
	getPileCount();// 获取充电点电桩数量
	getChargingCount();// 获取充电中的电桩数量
	getBespokeCount();// 获取预约中的电桩数量
	getOnlineCount();// 获取分享点数量
	getOfflineCount();// 获取专属点数量
}

/**
 * 获取故障电桩数量
 */
function pileErrorCount() {
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/electricPileMonitor/pileErrorCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			$("#pileErrorCount").html(json.count);
			$("#pileErrorDiv").click(function() {
				queryErrorPile();
			});
		}
	});
}

/**
 * 获取故障电桩列表
 */
function queryErrorPile() {
	hideChartDiv();
	$
			.ajax({
				type : 'post',
				url : basepath + "/admin/electricPileMonitor/queryErrorPile.do",
				dataType : "json",
				cache : false,
				success : function(datas) {
					var content = "";
					for (var i = 0; i < datas.length; i++) {
						var electricName = datas[i].electricName;
						var electricAddress = datas[i].electricAddress;
						content += '<dl class="lie_box listNameLink" onclick="selectHeadList('
								+ datas[i].electricId
								+ ')">'
								+ '<dt>'
								+ electricName
								+ '</dt>'
								+ '<dd>地址：'
								+ electricAddress
								+ '</dd>'
								+ '<dd>状态：<span class="zt_color_c">故障</span></dd>'
								+ '<dd style=" border-bottom:1px #CCCCCC solid; height:20px;"></dd></dl>'
								+ '</div>';

					}
					$("mainList").hide();
					$(".branchListStyle").hide();
					$("#pileBranchListDiv").show();
					$("#pileBranchListDiv")
							.html(
									'<span style="display:block;float:right;margin-right:10px;margin-top:5px;font-size: 12px;font-weight: normal;color: #333;text-decoration: none;cursor:pointer;"onclick=returnList("pileBranchListDiv")>返回</span>'
											+ content);
				}
			});
}
/**
 * 获取充电点电桩数量
 */
function getPileCount() {
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/electricPileMonitor/getAllPileCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			$("#psCount").html(json.pile_count);
		}
	});
}
/**
 * 获取充电中的电桩数量
 */
function getChargingCount() {
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/electricPileMonitor/getChargingCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			$("#chargingCount").html(json.pile_count);
		}
	});
}
/**
 * 获取预约中电桩数量
 */
function getBespokeCount() {
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/electricPileMonitor/getBespokeCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			$("#bespokeCount").html(json.pile_count);
		}
	});
}

/**
 * 获取分享点数量
 */
function getOnlineCount() {
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/electricPileMonitor/getOnlineCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			$("#onlineCount").html(json.pile_count);
		}
	});
}

/**
 * 获取专属点数量
 */
function getOfflineCount() {
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/electricPileMonitor/getOfflineCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			$("#offlineCount").html(json.pile_count);
		}
	});
}
