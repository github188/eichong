function initSelects(ids, selectIds) {
	$.ajax({
		type : "get",
		url : basePath +"/admin/config/dictList.do?type="+ids,
		dataType : "json",
		success : function(req) {
			var obj = req.data;
			var idArray=ids.split(",");
			var selectArray=selectIds.split(",");
			$.each(obj, function(num) {
				for(var i=0;i<idArray.length;i++){
					if(idArray[i]==num){
						var html = "";
						var options=obj[num];
						for (var j = 0; j < options.length; j++) {
							html += '<option  value="' + options[j].k
									+ '">' + options[j].v + '</option>';
						}
						$("#" + selectArray[i]).append(html);
					}
				}
			});
		}
	});
}

//基础接口
var menuChildrenUrl="/admin/menu/getChildrenMenu.do";
var ajaxTodoUrl="";
var toLoginUrl="/tologin.do";
var logoutUrl="/logout.do";

//电桩充电点集中器
var electricListUrl="/admin/electric/getElectricPileList.do";
var electricDetailPage="/admin/electric/electricDetailPage.do";
var electricEditPage="/admin/electric/electricEditPage.do";
var electricDetailUrl="/admin/electric/electricDetail.do";
var powerStationListUrl="/admin/station/getStationList.do";
var stationEditPage="/admin/station/stationEditPage.do";
var stationDetailPage="/admin/station/stationDetailPage.do";
var stationDetailUrl="/admin/station/stationDetail.do";
var concentratorListUrl="/admin/concentrator/getConcentratorList.do";
var concentratorEditPage="/admin/concentrator/concentratorEditPage.do";
var concentratorAddPage="/admin/concentrator/concentratorAddPage.do";
var concentratorEditUrl="/admin/concentrator/concentratorDetail.do";


//订单管理
var listBespokeUrl="/admin/order/bespokeOrder.do";
var listBusBespokeUrl="/admin/order/bespokeEarnOrder.do";
var listBusChargeUrl="/admin/order/chargeEarnOrder.do";
var listChargeUrl="/admin/order/chargeOrder.do";
var listChargeDetailPage="/admin/order/chargeOrderDetailPage.do";
var listChargeDetailUrl="/admin/order/chargeOrderDetail.do";
var listPayOrderUrl="/admin/order/payOrder.do";

//配置接口URL
var provinceListUrl="/admin/config/provinceList.do";
var cityListUrl="/admin/config/cityList.do";
var areaListUrl="/admin/config/areaList.do";

var elpiPowersizeUrl="/admin/config/dictList.do?type=4";
var serviceProviderUrl="/admin/config/dictList.do?type=8";//8:SIM卡运营商
var concentratorStateUrl="/admin/config/dictList.do?type=9";//9:集中器状态
var normRegisterTypeUrl="/admin/config/dictList.do?type=10";//10:普通用户注册来源
var elpiStateUrl="/admin/config/dictList.do?type=12";
var puhiChargeTypeUrl="/admin/config/dictList.do?type=18";//18:充值渠道
var orderStateUrl="/admin/config/dictList.do?type=19";
var bespokeStateUrl="/admin/config/dictList.do?type=20";//20:预约订单状态
var typeSpanDictUrl="/admin/config/typeSpanList.do";//产品型号
var rateInfoDictUrl="/admin/config/rateInfoList.do";//费率
var pileMakerDictUrl="/admin/config/pileMakerList.do";//电桩制造商
var carCompanyDictList="/admin/config/carCompanyList.do";//汽车品牌


//用户管理
var listUserInfoUrl="/admin/userNormal/getUserNormalList.do";


//配置管理部分
var rateinfoListUrl="/admin/rateinfo/getRateInfoList.do";
var rateinfoDetailUrl="/admin/rateinfo/rateinfoDetail.do";
var serviceLimitListUrl="/admin/feelimit/getFeelimitList.do";
var serviceLimitDetailUrl="/admin/feelimit/feelimitDetail.do";
var pileMakerListUrl="/admin/pilemaker/getPileMakerList.do";
var pilemakerDetailUrl="/admin/pilemaker/pilemakerDetail.do";
var typeSpanListUrl="/admin/typespan/getTypeSpanList.do";
var typespanDetailUrl="/admin/typespan/typespanDetail.do";
var powerRateListUrl="/admin/powerrate/getPowerRateList.do";
var powerRateDetailUrl="/admin/powerrate/powerRateDetail.do";
var carinfoListUrl="/admin/carinfo/getCarinfoList.do";
var carinfoDetailUrl="/admin/carinfo/carinfoDetail.do";
var carCompanyListUrl="/admin/carinfo/getCarcompanyList.do";
var carcompanyDetailUrl="/admin/carinfo/carcompanyDetail.do";

//二期===============================================
//用户注册
var userRegisteCountUrl="/admin/statistic/userRegisteCount.do";
var userRegisteForMonthUrl="/admin/statistic/userRegisteForMonth.do";
var userRegisteLatestUrl="/admin/statistic/userRegisteLatest.do";
var userRegisteListUrl="/admin/statistic/userRegisteList.do";
//实时数据=============================================
var realtimeDataUrl="/admin/statistic/realtimeData.do";
var realtimeDataForHourUrl="/admin/statistic/realtimeDataForHour.do";
var realtimeDataListUrl="/admin/statistic/realtimeDataList.do";
//历史数据============================================
var historyDataUrl="/admin/statistic/historyData.do";
var historyChargeDataForDayUrl="/admin/statistic/historyChargeDataForDay.do";
var historyDataForDayUrl="/admin/statistic/historyDataForDay.do";
var historyDataListUrl="/admin/statistic/historyDataList.do";

//资产统计================================================
var assertDataCountUrl="/admin/statistic/assertDataCount.do";
var assertDataPieUrl="/admin/statistic/assertDataPie.do";
var assertDataMapUrl="/admin/statistic/assertDataMap.do";
var assertDataDetailUrl="/admin/statistic/assertDataDetail.do";
//充电统计=================================================
var chargeDataUrl="/admin/statistic/chargeDataCount.do";
var chargeDataLineUrl="/admin/statistic/chargeDataLine.do";
var chargeDataPieUrl="/admin/statistic/chargeDataPie.do";
var chargeDataDetailUrl="/admin/statistic/chargeDataDetail.do";
//故障统计=================================================
var faultDataCountUrl="/admin/statistic/faultDataCount.do";
var faultDataPieUrl="/admin/statistic/faultDataPie.do";
var faultDataDetailUrl="/admin/statistic/faultDataDetail.do";