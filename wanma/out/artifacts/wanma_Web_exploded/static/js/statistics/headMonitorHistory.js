/** ***********************************************历史信息记录************************************************************** */
var historyData = []
/**
 * 查询参数
 */
headHistoryParamsData = function() {
	var dateStr = $("#dateStrDiv").val();
	var headId = $("#headIdDiv").val();
	var epCode = $("#eCode").val();
	var epType = $("#eType").val();
	return {
		pageNum : 1,
		pageSize : 10,
		date : dateStr,
		headId : $("#headIdDiv").val(),
		epCode : $("#eCode").val(),
		epType : $("#eChargeType").val()
	};
}

/**
 * 加载历史统计数据
 */
function searchHistory(pageNum, pageSize) {
	var params = headHistoryParamsData();
	$.ajax({
		type : 'post',
		url : basepath + "/admin/electricPileMonitorV2/getHeadHistoryList.do",
		dataType : "json",
		data : params,
		success : function(datas) {
			if (datas.data) {
				historyData = datas.data;
			}
			setHistoryInfo(pageNum, pageSize);
		}

	});
}

function setHistoryInfo(pageNum, pageSize) {
	var infoHtml = '';
	var allCount = historyData.length;
	var lastIndex = allCount - pageNum * pageSize;
	var startIndex = lastIndex + pageSize;
	if (historyData != null && allCount > 0) {
		for (var i = startIndex-1; i >= 0; i--) {
			if (startIndex-i <= pageSize) {
				var history = historyData[i];
				var dataTime = new Date(dealNullToInt(history.ts))
				var y = dataTime.getFullYear();
				var M = dataTime.getMonth() + 1;
				var d = dataTime.getDate();
				var h = dataTime.getHours();
				var m = dataTime.getMinutes();
				if (M < 10) {
					M = "0" + M;
				}
				if (d < 10) {
					d = "0" + d;
				}
				if (h < 10) {
					h = "0" + h;
				}
				if (m < 10) {
					m = "0" + m;
				}
				var status = parseInt(history.status);
				if (status == 8) {
					infoHtml += '<li>' + y + "/" + M + '/' + d + ' ' + h + ':'
							+ m + '，用户预约' + $("#eCode").val()
							+ '电桩<span class="yuyue">【预约】</span></li>';
				} else if (status == 3) {
					infoHtml += '<li>'
							+ y
							+ "/"
							+ M
							+ '/'
							+ d
							+ ' '
							+ h
							+ ':'
							+ m
							+ '，用户在'
							+ $("#eCode").val()
							+ '电桩上开始充电。<span class="chongdian">【充电】</span></li>';
				} else if (status == 1 || status >= 30) {
					infoHtml += '<li>' + y + "/" + M + '/' + d + ' ' + h + ':'
							+ m + '，' + $("#eCode").val() + '电桩发生了'
							+ chargingStatusData[status]
							+ '故障<span class="guzhuang">【故障】</span></li>';
				} else {
					infoHtml += '<li>' + y + "/" + M + '/' + d + ' ' + h + ':'
							+ m + '，' + $("#eCode").val() + '电桩状态为'
							+ chargingStatusData[status]
							+ '<span class="putong">【其他】</span></li>';
				}
			}else{
				break;
			}
		}
	}
	$("#infoUl").html(infoHtml);
	historyData.totalPage = Math.ceil(allCount / pageSize)
	historyData.count = pageSize
	historyData.currentPage = pageNum
	initPage(historyData, "setHistoryInfo", 'pageEle1');
}

/** *初始化月份标签** */
function initMonthTab() {
	var myDate = new Date();
	var dangqian = myDate.getMonth() + 1;
	var monthTabHtml = "";
	for (var i = 5; i >= 0; i--) {
		var historyMonth = dangqian - i;
		var yearVal = myDate.getFullYear();
		if (i == 0) {
			monthTabHtml = '<span class="dangqian monthTab" data-value='
					+ historyMonth + ' data-year=' + yearVal + '>'
					+ historyMonth + '月</span>' + monthTabHtml;
			if (historyMonth < 10) {
				historyMonth = "0" + historyMonth
			}
			$("#dateStrDiv").val(yearVal + historyMonth);
		} else {
			if (historyMonth <= 0) {
				historyMonth = historyMonth + 12;
				yearVal = yearVal - 1;
				if (historyMonth == 12) {
					monthTabHtml = '<span class="nian monthTab" data-value='
							+ historyMonth + ' data-year=' + yearVal + '>'
							+ yearVal + '年12月</span>' + monthTabHtml;
				} else {
					monthTabHtml = '<span class="yue monthTab" data-value='
							+ historyMonth + ' data-year=' + yearVal + '>'
							+ historyMonth + '月</span>' + monthTabHtml;
				}
			} else {
				monthTabHtml = '<span class="yue monthTab" data-value='
						+ historyMonth + ' data-year=' + yearVal + '>'
						+ historyMonth + '月</span>' + monthTabHtml;
			}
		}
	}
	$("#month-tabs").html(monthTabHtml);

	/** *************tab切换事件************** */
	$(".monthTab").click(function() {
		$this = $(this);
		var monthVal = $this.attr('data-value');
		var yearVal = $this.attr('data-year');
		if (monthVal < 10) {
			monthVal = "0" + monthVal;
		}
		$(".dangqian").addClass('yue');
		$(".dangqian").removeClass('dangqian');
		$this.addClass('dangqian');
		$("#dateStrDiv").val(yearVal + monthVal);
		searchHistory(1, 10);
	})
}
initMonthTab();
searchHistory(1, 10);
