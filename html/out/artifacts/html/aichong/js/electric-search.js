/**
 * Created by Kael on 2015/3/20.
 */
$(function() {
	$("#search").click(function() {
		if ($("#address").val() != "") {
			loadContent();
		}
	})

	$(".searchCondition").click(function() {
		loadContent();
	});

	$(':checkbox[name=chargingMode]').each(function() {
		$(this).click(function() {
			if ($(this).is(':checked')) {
				$(this).siblings().removeAttr('checked');
			}
			loadContent();
		});
	});

	$('#suitable').click(function() {
		var userid = UserService.getUserId();		
		if (!userid) {
			$("#suitable").attr("checked",false);
			$("#login").click();
			return;
		}
		// loadContent();
	});
	/**
	 * 电桩筛选条件
	 */
	config.data = function() {
		var cur_li = [];
		// var lb=$("#lb span.shaix_cose").attr("data-type");
		var lb = 3;
		/*
		 * $(".shaix_cose").each(function(i, o) { var cur_li_val =
		 * $(this).attr('data-type'); cur_li[i] = (lb == 4 ? "": cur_li_val);
		 * });
		 */
		var chargingMode = "";
		$(':checkbox[name=chargingMode]').each(function() {
			if ($(this).is(':checked')) {
				chargingMode = $(this).val();
			}
		});
		var powerInterface = "";
		$(':checkbox[name=powerInterface]').each(function() {
			if ($(this).is(':checked')) {
				powerInterface = $(this).val();
			}
		});
		return {
			screenType : lb,
			chargingMode : chargingMode,
			powerInterface : powerInterface,
			headState : $("#headState").is(':checked') ? $("#headState").val()
					: null,
			commStatus : $("#commStatus").is(':checked') ? $("#commStatus")
					.val() : null,
			suitable : $("#suitable").is(':checked') ? $("#suitable").val()
					: null,
			address : $("#address").val()
		};
	}

	/**
	 * 获取用户当前地区
	 */
	config.point = function() {
		var data_pot;
		$.ajax({
			type : 'get',
			url : config.IGetPoint,
			dataType : "json",
			async : false,
			success : function(data) {
				data_pot = data.data.point;
			}
		});
		return data_pot;
	}

	/**
	 * 获取图片地址
	 */
	config.absImg = function(imgUrl) {
		if (!imgUrl) {
			return config.defaultImg;
		}
		if (imgUrl && imgUrl.indexOf('http://') == 0) {
			return imgUrl;
		}
		return config.imageServer + imgUrl;
	};

	/**
	 * 电桩查找 地图模式
	 */
	var map;
	config.mapRequest = function() {
		if (!map) {
			map = new AMap.Map('allMap', {
				view : new AMap.View2D({
					// center: new AMap.LngLat(120.197428, 30.28923),
					zoom : 13,
				}),
				scrollWheel : true
			});
			// 不设置城市，默认加载到当前城市
			// map.setCity(config.point());
			// 在地图中添加ToolBar插件
			map.plugin([ "AMap.ToolBar" ], function() {
				var toolBar = new AMap.ToolBar();
				map.addControl(toolBar);
			});
		} else {
			map.clearMap();
			// var mapCenter = map.getCenter();
			// alert('当前地图中心点坐标：' + mapCenter.getLng() + ',' +
			// mapCenter.getLat());
		}
		$.ajax({
			type : 'post',
			url : config.IFindElectricMapSearch,
			dataType : "json",
			data : config.data(),
			success : function(datas) {
			var msgInfo = JSON3.stringify(datas);
				if (msgInfo.indexOf("needSelectValue") >= 0) {
					 $("input[name=suitable]").attr("checked",false);						 
					alert(msgInfo.replace("--needSelectValue", ""));
				}
				var data;
				for (var i = 0; i < datas.length; i++) {
					data = datas[i];
					if (data.electricLongitude && data.electricLatitude) {
						addMarker(data);
					}
				}
				// ie8以下浏览器，地图label样式无法修改，此处弥补
				var browser = getBrowserInfo();
				if (browser.browser = "ie" && browser.version <= 9) {
					setTimeout(setLabelStyle, 1500);
				}

			}
		});

		function setLabelStyle() {
			$(".amap-marker-label").addClass("marker-label").removeClass(
					"amap-marker-label");
		}
		function addMarker(data) {
			var imageSize = "a";
			var offset = new AMap.Pixel(15, 10);
			if (data.headNum >= 10) {
				imageSize = "b";
				offset = new AMap.Pixel(15, 15);
			} else if (data.headNum >= 100) {
				imageSize = "c";
				offset = new AMap.Pixel(20, 22);
			}
			var useHeadNum = parseInt(data.headNum - data.freeHeadNum);
			var imageType = Math.floor((useHeadNum / data.headNum) * 4) + 1;
			var marker = new AMap.Marker({
				// icon:"http://webapi.amap.com/images/0.png",
				icon : basepath + "/static/images/map/map_" + imageSize + "_"
						+ imageType + ".png",
				position : new AMap.LngLat(data.electricLongitude,
						data.electricLatitude),
				map : map
			});
			marker.setLabel({
				style : "border:0px",
				offset : offset,
				content : data.headNum
			});
			// 鼠标点击marker弹出自定义的信息窗体
			AMap.event
					.addListener(
							marker,
							'click',
							function() {

								// 判断访问地址
								var url = (data.electricType == 2 ? config.IGetElectricPlant
										: config.IGetElectricPile)
										+ "?eid=" + data.electricId;
								var imgSrc = basepath
										+ "/static/images/icon/img-"
										+ data.electricType + ".png";
								// 微信分享的桩站类型与本处相反，桩2站1
								var weixinDataType = data.electricType == 1 ? 2
										: 1;
								var weixinImg = '<img class="weixin_share" src="'
										+ basepath
										+ '/static/images/prettyPhoto/weixin.png" style="cursor:pointer;" '
										+ ' lng="'
										+ data.electricLongitude
										+ '" lat="'
										+ data.electricLatitude
										+ '" type="'
										+ weixinDataType
										+ '" '
										+ ' name="'
										+ data.electricName
										+ '" addr="'
										+ data.electricAddress
										+ '"  service="1.0" />';
								var info = "";
								var znImage = "ic-2.png";
								// 差异化信息
								if (data.electricType == 1) {// 桩
									if (data.electricState == 10) {
										info = "【普通】";
									} else if (data.electricState == 15) {
										info = "【智能】";
										znImage = "ic-1.png";
									} else {
										info = "【未知】";
									}
								} else if (data.electricType == 2) {// 站
									// info='本站有<u>'+data.electricPileSum+'</u>个充电桩';
									info = '共有充电桩 <span>'
											+ data.electricPileSum
											+ '</span> 个';
									if (data.electricState == 15) {
										znImage = "ic-1.png";
									}
								} else {// 自行车
									info = "";
								}
								// 当公司类型为2国网，3特斯拉时，显示各自公司的LOGO
								if (data.companyType == 2) {
									znImage = "ic-4.png";
								}
								if (data.companyType == 3) {
									znImage = "ic-3.png";
								}
								var electricName = data.electricName.length > 12 ? data.electricName
										.substring(0, 12)
										+ "..."
										: data.electricName;
								var electricAddress = data.electricAddress.length > 40 ? data.electricAddress
										.substring(0, 40)
										+ "..."
										: data.electricAddress;
								// 拼接弹窗内容
								var content = ' <div class="floatL list2"><dl style="margin-top:0px;" >'
										+ '<div class="bshare-custom"><a title="分享到QQ空间"  class="bshare-qzone" ></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到微信" class="bshare-weixin"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a></div>'
										+ '<dt><a href="'
										+ url
										+ '"><img src="'
										+ basepath
										+ '/static/images/img/'
										+ znImage
										+ '" width="43" height="43" alt="  " /></a></dt>'
										+ ' <dd class="zhuangtai">'
										+ info
										+ '</dd>'
										+ '<dd class="P-Name"><a href="'
										+ url
										+ '" target="_blank"><h3 class="zhuang_title" title="'
										+ data.electricName
										+ '">'
										+ electricName
										+ '</h3></a></dd>'
										+ ' <dd class="add"><span class="zhuang_roud" title="'
										+ data.electricAddress
										+ '">地址：'
										+ electricAddress
										+ '</span></dd></dl><div>';
								var obj = {
									lng : data.electricLongitude,
									lat : data.electricLatitude,
									type : weixinDataType,
									name : data.electricName,
									addr : data.electricAddress,
									service : 1.0
								}
								initTwoDicodeImg(obj);
								// 构建信息窗体中显示的内容
								var infoWindow = new AMap.InfoWindow({
									content : content,
									offset : new AMap.Pixel(8, -25), // 相对于基点的位置
									size : new AMap.Size(480, 140)
								});
								infoWindow.open(map, marker.getPosition());
								// $("#zhuangInfo").html(
								// $("#jiathis_style").html())
								// $("#jiathis_style").insertAfter("#zhuangInfo");
							});
		}
	}

	// 内容加载
	var dataType = "map";
	loadContent();
	function loadContent() {
		// alert(brow.msie);
		if (dataType == "map") {
			$("#allMap").show();
			$("#table").hide();
			config.mapRequest();
		} else {
			$("#allMap").hide();
			$("#table").show();
			config.begin = 1;
			listRequest(1, 10);
		}
		// alert($(".amap-marker-label").css([ "borderTopWidth",
		// "borderRightWidth", "borderBottomWidth", "borderLeftWidth" ]));
	}

	/**
	 * 内容切换
	 */
	$('.ChannelNav').click(function() {
		$(this).siblings().children().removeClass('show').addClass("unshow");
		$(this).children().addClass('show').removeClass("unshow");
		dataType = $(this).attr('data-type');
		loadContent();
	});

	$(".shaix_cose_bai").click(function() {
		if ($(this).hasClass("shaix_cose_hui")) {
			return;
		}
		$(this).siblings().removeClass("shaix_cose");
		$(this).addClass("shaix_cose");
		if ($(this).attr("data-type")) {
			if ($(this).attr("data-type") == "4") {
				$(".props span").addClass("shaix_cose_hui");
			} else {
				$(".props span").removeClass("shaix_cose_hui");
			}
		}
		loadContent();
	});

	function initTwoDicodeImg(obj) {
		var params = obj.lng + "|" + obj.lat + "|" + "|" + obj.addr + "|"
				+ obj.type + "|" + obj.service;
		share_config.share_url = basepath + "/reurl_act_appShare_ip_"
				+ appShareUrl + "_web_" + encodeURIComponent(params) + ".shtml";
	}

	// /**
	// * 电桩查找
	// */
	// $('body').on(
	// 'click',
	// '.search_li li',
	// function() {
	// if (!$(this).hasClass('ok') && !$(this).hasClass('no')) {
	// $(this).addClass('current cur_li').siblings('li')
	// .removeClass('current cur_li');
	//
	// cur_li_fir = $('.cur_li:first').attr('data-type');
	// if (cur_li_fir == 4) {
	// $('.search_li:not(:first) li').addClass('no');
	// $('.search_li:not(:first)').fadeTo("fast", 0.5);
	// } else {
	// $('.search_li:not(:first) li').removeClass('no');
	// $('.search_li:not(:first)').fadeTo("fast", 1);
	// }
	//
	// if (data_title) {
	// config.mapRequest();
	// } else {
	// config.begin = 1;
	// config.pageRequest();
	// }
	// }
	// });

	/**
	 * 电桩/电站详情
	 */
	$('body').on(
			'click',
			'#pile_detail',
			function() {
				var data_type = $(this).attr('data-type');
				var url = (data_type == 2 ? config.PElectricPlant
						: config.PElectricPile);
				window.location.href = url + '?e=' + $(this).attr('data-id');
			});
});

/**
 * 电桩查找 列表模式
 */
function listRequest(pageNum, pageSize) {
	var pageData = config.data();
	pageData.pageNum = pageNum;
	pageData.pageSize = pageSize;
	$.ajax({
				type : 'post',
				url : config.IFindElectricSearch,
				dataType : "json",
				data : pageData,
				success : function(datas) {
					var msgInfo = JSON3.stringify(datas);
					if (msgInfo.indexOf("needSelectValue") >= 0) {
						 $("input[name=suitable]").attr("checked",false);						 
						alert(msgInfo.replace("--needSelectValue", ""));
					}										
					var obj = datas.data;
					var html = "";
					for (var i = 0; i < obj.length; i++) {
						data = obj[i];
						// 判断访问地址
						var divClass = i % 2 == 0 ? "floatL" : "floatR";
						var url = (data.electricType == 2 ? config.IGetElectricPlant
								: config.IGetElectricPile)
								+ "?eid=" + data.electricId;
						var imgSrc = basepath + "/static/images/icon/img-"
								+ data.electricType + ".png";
						// 微信分享的桩站类型与本处相反，桩2站1
						var weixinDataType = data.electricType == 1 ? 2 : 1;
						var weixinImg = '<img class="weixin_share" src="'
								+ basepath
								+ '/static/images/prettyPhoto/weixin.png" style="cursor:pointer;" '
								+ ' lng="' + data.electricLongitude + '" lat="'
								+ data.electricLatitude + '" type="'
								+ weixinDataType + '" ' + ' name="'
								+ data.electricName + '" addr="'
								+ data.electricAddress + '"  service="1.0" />';
						var info = "";
						var znImage = "ic-2.png";
						// 差异化信息
						if (data.electricType == 1) {// 桩
							if (data.electricState == 10) {
								info = "【普通】";
							} else if (data.electricState == 15) {
								info = "【智能】";
								znImage = "ic-1.png";
							} else {
								info = "【未知】";
							}
						} else if (data.electricType == 2) {// 站
							// info='本站有<u>'+data.electricPileSum+'</u>个充电桩';
							info = '共有充电桩 <span>' + data.electricPileSum
									+ '</span> 个';
							if (data.electricState == 15) {
								znImage = "ic-1.png";
							}
						} else {// 自行车
							info = "";
						}
						// 当公司类型为2国网，3特斯拉时，显示各自公司的LOGO
						if (data.companyType == 2) {
							znImage = "ic-4.png";
						}
						if (data.companyType == 3) {
							znImage = "ic-3.png";
						}
						var electricName = data.electricName.length > 12 ? data.electricName
								.substring(0, 12)
								+ "..."
								: data.electricName;
						var electricAddress = data.electricAddress.length > 40 ? data.electricAddress
								.substring(0, 40)
								+ "..."
								: data.electricAddress;
						// 拼接弹窗内容
						var content = ' <div class="'
								+ divClass
								+ ' list"><dl style="margin-top:0px;" >'
								// +'<dt><a href="'+url+'" target="_blank"><img
								// src="' + config.absImg(data.electricImage) +
								// '" width="200" height="100" /></a></dt>'
								+ '<dt><a href="'
								+ url
								+ '"><img src="'
								+ basepath
								+ '/static/images/img/'
								+ znImage
								+ '" width="43" height="43" alt="  " /></a></dt>'
								+ ' <dd class="zhuangtai">'
								+ info
								+ '</dd>'
								+ '<dd class="P-Name"><a href="'
								+ url
								+ '" target="_blank"><h3 class="zhuang_title" title="'
								+ data.electricName
								+ '">'
								+ electricName
								+ '</h3></a></dd>'
								// +'<dd><div class="bshare-custom"><a
								// title="分享到QQ空间" class="bshare-qzone" ></a><a
								// title="分享到新浪微博"
								// class="bshare-sinaminiblog"></a><a
								// title="分享到微信" class="bshare-weixin"></a><a
								// title="更多平台" class="bshare-more
								// bshare-more-icon
								// more-style-addthis"></a></div></dd>'
								+ ' <dd class="add"><span class="zhuang_roud" title="'
								+ data.electricAddress + '">地址：'
								+ electricAddress + '</span></dd></dl></div>';
						html += content;
					}
					$("#data-list").html(html);
					initPage(datas, "listRequest");
				}
			});

}

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