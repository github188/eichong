//基础接口
//var basePath="http://10.9.3.122:8020/cms2";
var basePath="http://10.8.3.252:8020/cms2";
//var basePath="http://192.168.1.103:8020/cms2";
//var basePath="http://10.9.3.116/cms";
//var menuChildrenUrl="/static/json/getChildrenMenu.json";
var menuChildrenUrl="/static/json/chargeInfo.json";
var ajaxTodoUrl="/static/json/selectedTodo.json";
var toLoginUrl="/login.html";

//电桩充电点集中器
var electricListUrl="/static/json/electric/electricList.json";
var electricDetailUrl="/static/json/electric/electricDetail.json";
var electricEditUrl="/static/json/electric/electricEdit.json";
var stationDetailUrl="/static/json/station/stationDetail.json";
var concentratorEditUrl="/static/json/concentrator/concentratorEdit.json";
var concentratorListUrl="/static/json/concentrator/concentratorList.json";
var concentratorEditElectricPileListUrl="/static/json/concentrator/getElectricPileList.json";
var powerStationListUrl="/static/json/station/getStationList.json";
var electricDetailPage="/static/html/electric/electricDetail.html";
var electricEditPage="/static/html/electric/electricEdit.html";
var stationEditPage="/static/html/station/stationEdit.html";
var stationDetailPage="/static/html/station/stationDetail.html";
var concentratorAddPage="/static/html/concentrator/concentratorAdd.html";
var concentratorEditPage="/static/html/concentrator/concentratorEdit.html";


//订单管理
var listBespokeUrl="/static/json/order/listBespoke.json";
var listBusBespokeUrl="/static/json/order/listBusBespoke.json";
var listBusChargeUrl="/static/json/order/listBusCharge.json";
var listChargeUrl="/static/json/order/listCharge.json";
var listChargeDetailPage="/static/html/order/listChargeDetail.html";
var listPayOrderUrl="/static/json/order/listPayOrder.json";
var listChargeDetailUrl="/static/json/order/listChargeDetail.json";
//配置接口URL
var provinceListUrl="/static/json/cityJson/provinceList.json";
var cityListUrl="/static/json/cityJson/cityList.json";
var areaListUrl="/static/json/cityJson/areaList.json";

var elpiPowersizeUrl="/static/json/selected/elpiPowersize.json";//4功率
var elpiStateUrl="/static/json/selected/elpiState.json";//12状态
var puhiChargeTypeUrl="/static/json/selected/puhiChargeType.json";//18充值渠道
var orderStateUrl="/static/json/selected/orderState.json";//19:订单状态
var bespokeStateUrl="/static/json/selected/bespokeState.json";//20:预约订单状态
var serviceProviderUrl="/static/json/selected/serviceProvider.json";//8:SIM卡运营商
var concentratorStateUrl="/static/json/selected/concentratorState.json";//9:集中器状态
var normRegisterTypeUrl="/static/json/selected/puhiRegisterType.json";//10:普通用户注册来源
var elpiPowerinterfaceUrl="/static/json/selected/elpiPowerinterface.json";//5:充电方式
var pileMakerDictUrl="/static/json/selected/pileMakerDict.json";//11:制造厂商
var elPiOwnerCompanyUrl="/static/json/selected/elPiOwnerCompany.json";//22:运营平台
var elpiChargingmodeUrl="/static/json/selected/elpiChargingmode.json";//type=3充电方式
var elpiTypeUrl="/static/json/selected/elpiType.json";//1类型
var yesOrNoUrl="/static/json/selected/yesOrNo.json";//0是否

//注册用户
var listUserInfoUrl="/static/json/userNormal/listUserInfo.json";


//配置管理部分
var rateinfoListUrl="/static/json/config/rateinfoList.json";
var rateInfoDictUrl="/static/json/config/rateInfoDict.json";//费率
var rateinfoDetailUrl="/static/json/config/rateinfoDetail.json";
var serviceLimitListUrl="/static/json/config/serviceLimitList.json";
var serviceLimitDetailUrl="/static/json/config/serviceLimitDetail.json";
var pileMakerListUrl="/static/json/config/pileMakerList.json";
var pilemakerDetailUrl="/static/json/config/pilemakerDetail.json";
var typeSpanListUrl="/static/json/config/typeSpanList.json";
var typeSpanDictUrl="/static/json/config/typeSpanDict.json";//产品型号
var typespanDetailUrl="/static/json/config/typespanDetail.json";
var powerRateDetailUrl="/static/json/config/powerRateDetail.json";
var powerRateListUrl="/static/json/config/powerRateList.json";
//品牌
var carCompanyListUrl="/static/json/config/carCompanyList.json";
var carcompanyDetailUrl="/static/json/config/carcompanyDetail.json";
//车型
var carinfoListUrl="/static/json/config/carinfoList.json";
var carinfoDetailUrl="/static/json/config/carinfoDetail.json";
var carCompanyDictList="/static/json/config/carCompanyDictList.json";


function initSelects(ids, selectIds) {
	var urlArray=[yesOrNoUrl,elpiTypeUrl,"",elpiChargingmodeUrl,elpiPowersizeUrl,elpiPowerinterfaceUrl,"","",serviceProviderUrl,concentratorStateUrl,normRegisterTypeUrl,
	           "",elpiStateUrl,"","","","","",puhiChargeTypeUrl,orderStateUrl,bespokeStateUrl,
	           "",elPiOwnerCompanyUrl];
	var idArray=ids.split(",");
	var selectArray=selectIds.split(",");
	for(var i=0;i<idArray.length;i++){
		$.ajax({
			type : "get",
			url : basePath + urlArray[parseInt(idArray[i])],
			dataType : "json",
			async:false,
			success : function(req) {
				var obj = req.data;
					$.each(obj, function(num) {
						if(idArray[i]==num){
							var html = "";
							var options=obj[num];
							for (var j = 0; j < options.length; j++) {
								html += '<option  value="' + options[j].k
										+ '">' + options[j].v + '</option>';
							}
							$("#" + selectArray[i]).append(html);
						}
					});
				
				
			}
		});
	}
	
}
//二期===============================================
//用户注册
var userRegisteCountUrl="/static/json/userRegiste/userRegisteCount.json";
var userRegisteForMonthUrl="/static/json/userRegiste/userRegisteForMonth.json";
var userRegisteLatestUrl="/static/json/userRegiste/userRegisteLatest.json";
var userRegisteListUrl="/static/json/userRegiste/userRegisteList.json";
//实时数据=============================================
var realtimeDataUrl="/static/json/realtime/realtimeData.json";
var realtimeDataForHourUrl="/static/json/realtime/realtimeDataForHour.json";
var realtimeDataListUrl="/static/json/realtime/realtimeDataList.json";
//历史数据============================================
var historyDataUrl="/static/json/historyData/historyData.json";
var historyChargeDataForDayUrl="/static/json/historyData/historyChargeDataForDay.json";
var historyDataForDayUrl="/static/json/historyData/historyDataForDay.json";
var historyDataListUrl="/static/json/historyData/historyDataList.json";

//资产统计================================================
var assertDataCountUrl="/static/json/assertData/assertData.json";
var assertDataPieUrl="/static/json/assertData/assertDataPie.json";
var assertDataMapUrl="/static/json/assertData/assertDataMap.json";
var assertDataDetailUrl="/static/json/assertData/assertDataDetail.json";
//充电统计=================================================
var chargeDataUrl="/static/json/chargeData/chargeData.json";
var chargeDataLineUrl="/static/json/chargeData/chargeDataLine.json";
var chargeDataPieUrl="/static/json/chargeData/chargeDataPie.json";
var chargeDataDetailUrl="/static/json/chargeData/chargeDataDetail.json";
//故障统计=================================================
var faultDataCountUrl="/static/json/faultData/faultData.json";
var faultDataPieUrl="/static/json/faultData/faultDataPie.json";
var faultDataDetailUrl="/static/json/faultData/faultDataDetail.json";