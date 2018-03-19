/**
 * Created by Kael on 2015/3/20.
 */
var mapObject;
$(function() {

	/**
	 * 电桩筛选条件
	 */
	config.data = function(pageNum, pageSize) {
		var electricSelProvince = $("#electricSelProvince").val()
		var electricSelCity = $("#electricSelCity").val()
		var electricSelDistrict = $("#electricSelDistrict").val()
		var pileName = $("#serach").val();
		return {
			electricSelProvince : electricSelProvince,
			electricSelCity : electricSelCity,
			electricSelDistrict : electricSelDistrict,
			pileName : pileName,
			pageNum : pageNum,
			pageSize : pageSize
		};
	}

	/**
	 * 电桩查找 地图模式
	 */
	var map;
	var mapDatas;
	config.mapRequest = function(resetMapCenter) {
		if (!map) {
			map = new AMap.Map('allMap', {
				view : new AMap.View2D({
					zoom : 13,
				}),
				scrollWheel : true
			});

			// 不设置城市，默认加载到当前城市
			// 在地图中添加ToolBar插件
			map.plugin([ "AMap.ToolBar" ], function() {
				var toolBar = new AMap.ToolBar();
				map.addControl(toolBar);
			});
			map.on('click', function(e) {
			});
			map.on('moveend', function(e) {
				drawMarkers(mapDatas);
			});
			map.on('zoomend', function(e) {
				drawMarkers(mapDatas);
			});
			mapObject = map;
		} else {
			map.clearMap();
		}

		var param = config.data(null, null);
		$
				.ajax({
					type : 'post',
					url : basepath
							+ "/admin/electricPileMonitor/getElectricPileForMap.do",
					dataType : "json",
					data : param,
					success : function(datas) {
						var msgInfo = JSON3
								.stringify(datas.electricPileMonitorMapList);
						if (msgInfo.indexOf("needSelectValue") >= 0) {
							$("input[name=suitable]").attr("checked", false);
							return;
						}
						mapDatas = datas.electricPileMonitorMapList;
						if (mapDatas.length == 0) {
							alertMsg.error("没有电桩");
							return;
						}
						if (resetMapCenter) {
							map.setCenter([ mapDatas[0].electricLongitude,
									mapDatas[0].electricLatitude ]);
						}
						drawMarkers(mapDatas);

						// ie8以下浏览器，地图label样式无法修改，此处弥补
						var browser = getBrowserInfo();
						if (browser.browser = "ie" && browser.version <= 9) {
							setTimeout(setLabelStyle, 1500);
						}

					}
				});

		function drawMarkers(datas) {
			var data;
			var bounds = map.getBounds();
			var point;
			var containsFlag = false;
			for (var i = 0; i < datas.length; i++) {
				data = datas[i];
				if (data.electricLongitude && data.electricLatitude) {
					var point = new AMap.LngLat(data.electricLongitude,
							data.electricLatitude);
					containsFlag = bounds.contains(point);
					if (containsFlag) {
						addMarker(data);
						// 删除全局数组中的该点
						mapDatas.splice(i, 1);
					}
				}
			}
		}

		function setLabelStyle() {
			$(".amap-marker-label").addClass("marker-label").removeClass(
					"amap-marker-label");
		}
		function addMarker(data) {
			var offset = new AMap.Pixel(15, 10);
			var imgIcon = "pdiv-1.png"
			if (data.isError == 1) {
				imgIcon = "pdiv-1.png"
			}
			var marker = new AMap.Marker({
				icon : basepath + "/static/images/map/" + imgIcon,
				position : new AMap.LngLat(data.electricLongitude,
						data.electricLatitude),
				map : map
			});
			marker.setLabel({
				style : "border:0px",
				offset : offset,
			// content : data.headNum
			});
			var infoWindow;
			// 鼠标移近标点时触发事件
			AMap.event
					.addListener(
							marker,
							'mouseover',
							function() {
								var electricName = data.electricName.length > 12 ? data.electricName
										.substring(0, 12)
										+ "..."
										: data.electricName;
								var electricAddress = data.electricAddress.length > 40 ? data.electricAddress
										.substring(0, 40)
										+ "..."
										: data.electricAddress;
								// 拼接弹窗内容
								var content = ' <div id="infoWinow" class="floatL list2"><dl style="margin-top:0px;" >'
										+ '<dt><img src="'
										+ basepath
										+ '/static/images/img/ic-1.png" width="43" height="43" alt="  " /></a></dt>'
										+ '<br><dd class="P-Name"><h3 class="zhuang_title" title="'
										+ data.electricName
										+ '">'
										+ electricName
										+ '</h3></dd>'
										+ ' <br><dd class="add"><span class="zhuang_roud" title="'
										+ data.electricAddress
										+ '">地址：'
										+ electricAddress
										+ '</span></dd></dl><div>';
								var obj = {
									lng : data.electricLongitude,
									lat : data.electricLatitude,
									type : "",
									name : data.electricName,
									addr : data.electricAddress,
									service : 1.0
								}
								// 构建信息窗体中显示的内容
								infoWindow = new AMap.InfoWindow({
									content : content,
									offset : new AMap.Pixel(8, -25), // 相对于基点的位置
									size : new AMap.Size(480, 140)
								});
								infoWindow.open(map, marker.getPosition());
							});

			// 鼠标点击marker弹出自定义的信息窗体
			AMap.event.addListener(marker, 'mouseout', function() {
				infoWindow.close(map, marker.getPosition());
			});

			// 鼠标点击marker弹出自定义的信息窗体
			AMap.event.addListener(marker, 'click', function() {
				var pileId = data.electricId;
				if (data.electricType == 1) {
					if(data.pileStatus == 0){
						alertMsg.error("该电桩已断开，暂无实时详情");
						return;
					}
					selectHeadList(pileId);
				} else if (data.electricType == 2) {
					queryPileListByStationId(pileId);
				}
			});
		}
	}

	// 内容加载
	loadContent();
	function loadContent() {
		$("#allMap").show();
		queryAllCount();
		searchAll(1, 10);
	}
});

/**
 * 浏览器兼容判断
 * 
 * @returns
 */
function getBrowserInfo() {
	var userAgent = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
	var browser;
	var version;
	var ua = userAgent.toLowerCase();
	var match = rMsie.exec(ua);
	if (match != null) {
		return {
			browser : "ie",
			version : match[2] || "0"
		};
	}
	var match = rFirefox.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rOpera.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rChrome.exec(ua);
	if (match != null) {
		return {
			browser : match[1] || "",
			version : match[2] || "0"
		};
	}
	var match = rSafari.exec(ua);
	if (match != null) {
		return {
			browser : match[2] || "",
			version : match[1] || "0"
		};
	}
	if (match != null) {
		return {
			browser : "",
			version : "0"
		};
	}
	return null;
}

/**
 * 加载所有数据
 * 
 * @param pageNum
 * @param pageSize
 */
function searchAll(pageNum, pageSize, resetMapCenter) {
	config.mapRequest(resetMapCenter);
	serachList(pageNum, pageSize);
	hideChartDiv();
}

/**
 * 加载列表数据
 * 
 * @param pageNum
 * @param pageSize
 */
function serachList(pageNum, pageSize) {
	$
			.ajax({
				type : 'post',
				url : basepath
						+ "/admin/electricPileMonitor/getElectricPileMapList.do",
				dataType : "json",
				data : config.data(pageNum, pageSize),
				success : function(datas) {
					var msgInfo = datas.data;
					var content = "";
					for (var i = 0; i < msgInfo.length; i++) {
						var pileData = msgInfo[i];
						var headState = "";
						var headColor = "";
						var electricName = pileData.electricName;
						var electricAddress = pileData.electricAddress;
						var listNameClass = "listNameLink";
						if (pileData.pileStatus == 0) {
							headState += '<span class="zt_color_c">断开</span>、';
							listNameClass = "";
						} else {
							if (pileData.isFree == 1) {
								headState += '<span class="zt_color_a">空闲</span>、'
							}
							if (pileData.isBespeak == 1) {
								headState += '<span class="zt_color_a">预约</span>、'
							}
							if (pileData.isCharge == 1) {
								headState += '<span class="zt_color_b">充电</span>、'
							}
							if (pileData.isError == 1) {
								headState += '<span class="zt_color_c">故障</span>、'
							}
						}
						headState = headState.substr(0, headState.length - 1);

						content += '<dl class="lie_box '+listNameClass+'" data-id='
								+ pileData.electricId
								+ ' data-type='
								+ pileData.electricType
								+ ' data-name='
								+ electricName
								+ ' data-address='
								+ electricAddress
								+ ' data-longitude='
								+ pileData.electricLongitude
								+ ' data-latitude='
								+ pileData.electricLatitude
								+ '>'
								+ '<dt>'
								+ electricName
								+ '</dt>'
								+ '<dd>地址：'
								+ electricAddress
								+ '</dd>'
								+ '<dd>状态：'
								+ headState
								+ '</dd>'
								+ '<dd style=" border-bottom:1px #CCCCCC solid; height:20px;"></dd></dl>'

					}
					$("#listDetail").show();
					$("#listDetail").html(content);
					initPage(datas, "serachList");
					$("#pageEle").show();

					/**
					 * 列表点击充电点名称锚点跟踪
					 */
					$(".listNameLink").click(
							function() {
								listNameLink($(this));
								moveToMarkerPosition($(this));
							})
				}

			});

}

/**
 * 充电点名称点击展示详情信息
 * 
 * @param this_e
 */
function listNameLink(this_e) {
	var $this = this_e;
	var type = $this.attr("data-type");
	var pileId = $this.attr("data-id");
	if (type == 1) {
		selectHeadList(pileId);
	} else {
		queryPileListByStationId(pileId);
	}
}

/**
 * 地图锚点定位，并弹出窗体信息
 */
function moveToMarkerPosition(this_e) {
	var map = mapObject;
	var $this = this_e;
	var centerPosition = [ $this.attr("data-longitude"),
			$this.attr("data-latitude") ]
	map.panTo(centerPosition);
	var electricName = $this.attr("data-name");
	var electricAddress = $this.attr("data-address");
	// 拼接弹窗内容
	var content = ' <div id="infoWinow" class="floatL list2"><dl style="margin-top:0px;" >'
			+ '<dt><img src="'
			+ basepath
			+ '/static/images/img/ic-1.png" width="43" height="43" alt="  " /></a></dt>'
			+ '<br><dd class="P-Name"><h3 class="zhuang_title" title="'
			+ electricName
			+ '">'
			+ electricName
			+ '</h3></dd>'
			+ ' <br><dd class="add"><span class="zhuang_roud" title="'
			+ electricAddress
			+ '">地址：'
			+ electricAddress
			+ '</span></dd></dl><div>';
	// 构建信息窗体中显示的内容
	var infoWindow = new AMap.InfoWindow({
		content : content,
		offset : new AMap.Pixel(8, -25), // 相对于基点的位置
		size : new AMap.Size(10, 90)
	});
	infoWindow.open(map, centerPosition);

}

/**
 * 获取充电点下的电桩列表
 * 
 * @param pileId
 */
function queryPileListByStationId(pileId) {
	hideChartDiv();
	$(".branchListStyle").hide();
	$
			.ajax({
				type : 'post',
				url : basepath
						+ "/admin/electricPileMonitor/getElectricPileListByStationId.do",
				dataType : "json",
				data : {
					electricId : pileId
				},
				success : function(datas) {
					showElectricPileListInfo(datas);

				}
			});
}

/**
 * 展示充电点下的所有电桩列表
 */
function showElectricPileListInfo(datas) {
	var content = "";
	for (var i = 0; i < datas.length; i++) {
		var electricName = datas[i].electricName;
		var electricAddress = datas[i].electricAddress;
		var headState = "";
		var listNameClass = "listNameLink stationPileDetail";
		if (datas[i].pileStatus == 0) {
			headState += '<span class="zt_color_c">断开</span>、'
			listNameClass = "";
		} else {
			if (datas[i].isFree == 1) {
				headState += '<span class="zt_color_a">空闲</span>、'
			}
			if (datas[i].isBespeak == 1) {
				headState += '<span class="zt_color_a">预约</span>、'
			}
			if (datas[i].isCharge == 1) {
				headState += '<span class="zt_color_b">充电</span>、'
			}
			if (datas[i].isError == 1) {
				headState += '<span class="zt_color_c">故障</span>、'
			}
		}
		headState = headState.substr(0, headState.length - 1);
		content += '<dl class="lie_box '
				+ listNameClass
				+ '" data-id='
				+ datas[i].electricId
				+ '>'
				+ '<dt>'
				+ electricName
				+ '</dt>'
				+ '<dd>地址：'
				+ electricAddress
				+ '</dd>'
				+ '<dd>状态：'
				+ headState
				+ '</dd>'
				+ '<dd style=" border-bottom:1px #CCCCCC solid; height:20px;"></dd></dl>'
				+ '</div>';

	}
	$("#mainList").hide();
	$("#pileBranchListDiv").show();
	$("#pileBranchListDiv")
			.html(
					'<div"><br><span style="font-size: 12px;float:right;margin-right:10px;font-weight: normal;color: #333;text-decoration: none;cursor:pointer;"onclick=returnList("pileBranchListDiv")>返回</span>'
							+ content);

	/**
	 * 电桩名称点击展示详情信息
	 * 
	 * @param this_e
	 */
	$(".stationPileDetail").click(function() {
		var $this = $(this);
		selectHeadList($this.attr("data-id"));
	})
}

/**
 * 获取电桩枪头详情信息
 * 
 * @param pileId
 */
function selectHeadList(pileId) {
	var chargingMode = "";
	$.ajax({
		type : 'post',
		url : basepath + "/admin/electricPileMonitor/getElectricPileDetail.do",
		dataType : "json",
		data : {
			electricId : pileId
		},
		success : function(datas) {
			$("#mainList").hide();
			$("#headBranchListDiv").show();
			var detailContent = makePileDetailInfo(datas);
			$("#headBranchListDiv").html(detailContent);
			showChartDiv();
			drowVoltageChart(datas);
			drowDcDiv(datas);
			temperature(datas);
			queryFeiLv(datas);
		}
	});
}

/**
 * 电桩详情数据组装
 */
function makePileDetailInfo(data) {
	var datas = data;
	var pileDetail = datas.pileDetail;
	var electricPileName = pileDetail.electricPileName;
	var electricPileCode = pileDetail.electricPileNo;
	var electricPileAdress = pileDetail.electricPileAdress;
	var electricPowerSize = dealNullToFloat(pileDetail.electricPowerSize);
	var raInServiceCharge = dealNullToFloat(pileDetail.raInServiceCharge);
	var headList = datas.headList;
	var totalChargeDl = headList[0] == null ? 0.00 : headList[0].totalChargeDl;
	var totalChargeTime = headList[0] == null ? 0.00
			: headList[0].totalChargeTime;
	var totalChargeAmt = headList[0] == null ? 0.00
			: headList[0].totalChargeAmt;
	var state = headList[0] == null ? "0"
			: headList[0].epheElectricpileheadstate;
	if (state == '0') {
		state = "空闲";
	}
	if (state == '3') {
		state = "预约";
	}
	if (state == '6') {
		state = "充电";
	}
	if (state == '9') {
		state = "故障";
	}
	if (pileDetail.electricPileChargingMode == 5) {
		chargingMode = "快充";
	} else if (pileDetail.electricPileChargingMode == 14) {
		chargingMode = "慢充";
	}
	return '<table width="100%" border="0">'
			+ '<tr><td width="10">&nbsp;</td><td width="100%"><table width="100%" border="0">'
			+ '<tr><td class="xiangqing_top">'
			+ electricPileName
			+ '</td><td class="xiangqing_top"><a style="display:block;margin-right:10px;width:100%;font-size: 12px;text-align: right;" href="#" onclick=returnList("headBranchListDiv")>返回</a></td>'
			+ '</table></td><td width="10">&nbsp;</td></tr>'
			+ '</table><br><div style="clear: both;"></div>'
			+ '<table width="100%" border="0">'
			+ '<tr><td width="10">&nbsp;</td><td width="100%"><table width="100%" border="0"cellpadding="0" cellspacing="0" class="lie_box_xq_biaoge">'
			+ '<tr><td>编号</td><td><strong>'
			+ electricPileCode
			+ '</strong></td></tr>'
			+ '<tr><td>服务费</td><td><strong><span class="zt_color_c">'
			+ raInServiceCharge
			+ '</span>元/度</strong></td></tr>'
			+ '<tr><td>电费</td><td onmouseenter="showFeilvDiv()" onmouseleave="hideFeilvDiv()"><strong>查看费率</strong></td></tr>'
			+ '<tr><td>充电方式</td><td><strong>'
			+ chargingMode
			+ '</strong></td></tr>'
			+ '<tr><td>额定功率</td><td><strong>'
			+ electricPowerSize
			+ '</strong></td></tr>'
			// +'<tr><td>操作</td><td
			// class="/static/images/img/biaoge_right_open"><a
			// href="#"><strong>开启</strong></a></td></tr>'
			+ '</table></td><td width="10">&nbsp;</td></tr>'
			+ '</table>'
			+ '<div style="clear: both;"></div>'
			+ '<table width="100%" border="0" style="margin-top: 10px;">'
			+ makeHeadDiv(headList)
			+ '</table>'
			+ '<div style="clear: both;"></div>'
			+ '<table width="100%" border="0">'
			+ '<tr><td width="10">&nbsp;</td><td width="100%"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="lie_box_xq_biaoge">'
			+ '<tr><td>状态</td><td><strong id="headStateSpan">'
			+ state
			+ '</strong></td></tr>'
			+ '<tr><td>总充电电量</td><td><strong><span class="zt_color_c" id="totalChargeDlSpan">'
			+ totalChargeDl
			+ '</span>度</strong></td></tr>'
			+ '<tr><td>总充电小时</td><td><strong id="totalChargeTimeSpan">'
			+ (parseFloat(totalChargeTime) / 60).toFixed(2)
			+ '小时</strong></td></tr>'
			+ '<tr><td>总充电费用</td><td><strong id="totalChargeAmtSpan">'
			+ totalChargeAmt
			+ '元</strong></td></tr></table></td>'
			+ '<td width="10">&nbsp;</td></tr></table>'
			+ '<div style="clear: both; height: 20px;"></div>'
}

/**
 * 枪头div组装
 * 
 * @param data
 * @returns {String}
 */
function makeHeadDiv(data) {
	var headList = data;
	var headDetail = ""
	var trLimit = Math.ceil(headList.length / 2)
	var selectClass = "";
	for (var i = 0; i < trLimit; i++) {
		if (i == 0) {
			selectClass = "qiangtou_NAV_left_select";
		} else {
			selectClass = "";
		}
		if (2 * i == headList.length - 1) {
			headDetail += '<tr><td width="20"></td><td width="45%" class="qiangtou_NAV_left '
					+ selectClass
					+ '" data-totalChargeDl='
					+ headList[2 * i].totalChargeDl
					+ ' data-totalChargeTime='
					+ headList[2 * i].totalChargeTime
					+ ' data-totalChargeAmt='
					+ headList[2 * i].totalChargeAmt
					+ ' data-epheElectricpileheadstate='
					+ headList[2 * i].epheElectricpileheadstate
					+ ' onclick="selectHead(this)"><a>'
					+ (2 * i + 1)
					+ '号枪头'
					+ '</a></td><td width="10"></td><td width="20"></td><td width="45%"</td><td width="10"></td></tr>';
		} else {
			headDetail += '<tr><td width="20"></td><td width="45%" class="qiangtou_NAV_left '
					+ selectClass
					+ '" data-totalChargeDl='
					+ headList[2 * i].totalChargeDl
					+ ' data-totalChargeTime='
					+ headList[2 * i].totalChargeTime
					+ ' data-totalChargeAmt='
					+ headList[2 * i].totalChargeAmt
					+ ' data-epheElectricpileheadstate='
					+ headList[2 * i].epheElectricpileheadstate
					+ ' onclick="selectHead(this)"><a>'
					+ (2 * i + 1)
					+ '号枪头'
					+ '</a></td><td width="10"></td><td width="20"></td><td width="45%" class="qiangtou_NAV_left" data-totalChargeDl='
					+ headList[2 * i + 1].totalChargeDl
					+ ' data-totalChargeTime='
					+ headList[2 * i + 1].totalChargeTime
					+ ' data-totalChargeAmt='
					+ headList[2 * i + 1].totalChargeAmt
					+ ' data-epheElectricpileheadstate='
					+ headList[2 * i + 1].epheElectricpileheadstate
					+ ' onclick="selectHead(this)"><a>'
					+ (2 * i + 2)
					+ '号枪头'
					+ '</a></td><td width="10"></td></tr>';
		}
	}
	return headDetail;
}

/**
 * 枪头切换控制
 * 
 * @param this_e
 */
function selectHead(this_e) {
	changeHeadClass(this_e);
	$("#totalChargeDlSpan").html($(this_e).attr("data-totalChargeDl"));
	$("#totalChargeTimeSpan").html(
			(parseFloat($(this_e).attr("data-totalChargeTime")) / 60)
					.toFixed(2)
					+ "小时");
	$("#totalChargeAmtSpan").html($(this_e).attr("data-totalChargeAmt") + "元");
	var state = $(this_e).attr("data-epheElectricpileheadstate")
	if (state == '0') {
		$("#headStateSpan").html("空闲");
	}
	if (state == '3') {
		$("#headStateSpan").html("预约");
	}
	if (state == '6') {
		$("#headStateSpan").html("充电");
	}
	if (state == '9') {
		$("#headStateSpan").html("故障");
	}
}

function changeHeadClass(this_e) {
	$(".qiangtou_NAV_left").removeClass("qiangtou_NAV_left_select");
	$(this_e).addClass("qiangtou_NAV_left_select");
}

function queryFeiLv(datas) {
	$("#fengziU").html(datas.fengzhiHtml);
}

function showFeilvDiv() {
	$("#fengziDiv").show();
}

function hideFeilvDiv() {
	$("#fengziDiv").hide();
}

/**
 * 返回列表页
 * 
 * @param hideId
 */
function returnList(hideId) {
	$("#mainList").show();
	$("#" + hideId).hide();
}