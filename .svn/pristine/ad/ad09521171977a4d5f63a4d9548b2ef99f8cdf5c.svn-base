var getHeadInfoCountIntervalId0;
var getHeadInfoCountIntervalId1;
/**
 * 加载统计数据
 */
function loadAllStatisticsData() {
	/**
	 * 资产统计
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getAssetStatisticsCount.do",
		dataType : "json",
		cache : false,
		success : function(json) {
			var countAc = json.countAc;
			var countDc = json.countDc;
			if (json.userId == 8231) {
				countAc = countAc * 5;
				countDc = countDc * 5;
				json.count_point = json.count_point * 5;
				json.all_head_count = json.all_head_count * 5;
			}
			navTab.getCurrentPanel().find("#cdd_txt").html(json.count_point);
			navTab.getCurrentPanel().find("#cdd_dzcount_txt").html(
					countAc + countDc);
			navTab.getCurrentPanel().find("#ac_txt").html(countAc);
			navTab.getCurrentPanel().find("#dc_txt").html(countDc);
			navTab.getCurrentPanel().find("#pile_head_txt").html(
					json.all_head_count);
		}
	});

	/**
	 * 充电情况数量
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getChargeInfoCount.do",
		dataType : "json",
		cache : false,
		success : function(obj) {
			json = obj.data1;
			var userId = obj.userId;
			json = JSON.parse(json)
			if (userId == 8231) {
				var str = json.allCharge_t.split(",");
				var newStr = str.join("");
				json.all_charge_count = json.all_charge_count * 5;
				json.allCharge_c = json.allCharge_c * 5;
				json.allCharge_t = parseInt(newStr) * 5;
			}
			var all_charge_count = json.all_charge_count;
			var allCharge_c = json.allCharge_c;
			var allCharge_t = json.allCharge_t;
			$("#all_charge_count_txt").html(
					all_charge_count == null ? 0 : all_charge_count);
			$("#all_charge_c_txt").html(
					allCharge_c == null ? 0 : parseFloat(allCharge_c)
							.toFixed(2));
			$("#all_charge_time_txt").html(
					allCharge_t == null ? 0 : allCharge_t)
		}
	});
	getHeadInfoCountF();
}

function getHeadInfoCountF() {
	getHeadInfoCount();
	clearInterval(getHeadInfoCountIntervalId0);
	getHeadInfoCountIntervalId0 = setInterval("getHeadInfoCount()", 600000)
}

function getHeadInfoCount() {
	/**
	 * 枪头数量
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getHeadInfoCount.do",
		dataType : "json",
		cache : false,
		success : function(obj) {
			json = obj.data1;
			var userId = obj.userId;
			json = JSON.parse(json)
			if (userId == 8231) {
				json.bespoke_count = json.bespoke_count * 5;
				json.charging_count = json.charging_count * 5;
				json.free_count = json.free_count * 5;
				json.error_count = json.error_count * 5;
				json.countOff = json.countOff * 5;
			}
			$("#bespoke_head_txt").html(json.bespoke_count);
			$("#charging_head_txt").html(json.charging_count);
			$("#free_head_txt").html(json.free_count);
			$("#error_head_txt").html(json.error_count);
			$("#off_pile_txt").html(json.countOff);
		}
	});
}

var provinceCountData = {};
function loadProvinceStatisticsData(province) {
	/**
	 * 资产统计
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getAssetStatisticsCount.do",
		dataType : "json",
		data : {
			province : province
		},
		cache : false,
		success : function(json) {

			var countAc = json.countAc;
			var countDc = json.countDc;
			$("#cdd_txt1").html(json.count_point);
			$("#cdd_dzcount_txt1").html(countAc + countDc);
			$("#ac_txt1").html(countAc);
			$("#dc_txt1").html(countDc);
			$("#pile_head_txt1").html(json.all_head_count);
		}
	});

	/**
	 * 充电情况数量
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getChargeInfoCount.do",
		dataType : "json",
		data : {
			province : province
		},
		cache : false,
		success : function(json) {
			//debugger;
			var obj = json.data1;
			if (typeof obj == 'string') {
				obj = JSON.parse(obj);

			}

			var all_charge_count = obj.all_charge_count;
			var allCharge_c = obj.allCharge_c;
			var allCharge_t = obj.allCharge_t;
			$("#all_charge_count_txt1").html(
					all_charge_count == null ? 0 : all_charge_count);
			$("#all_charge_c_txt1").html(
					allCharge_c == null ? 0 : allCharge_c);
			$("#all_charge_time_txt1").html(
					allCharge_t == null ? 0 : allCharge_t);
		}
	});
	getHeadInfoCountInnerF(province);
}

function getHeadInfoCountInnerF(province) {
	getHeadInfoCountInner(province);
	clearInterval(getHeadInfoCountIntervalId1);
	getHeadInfoCountIntervalId1 = setInterval("getHeadInfoCountInner('"
			+ province + "')", 600000)
}
function getHeadInfoCountInner(province) {
	/**
	 * 枪头数量
	 */
	$.ajax({
		type : 'POST',
		url : "/wanma/admin/chartStatistics/getHeadInfoCount.do",
		dataType : "json",
		data : {
			province : province
		},
		cache : false,
		success : function(json) {
			var obj = json.data1;
			if (typeof obj == 'string') {
				obj = JSON.parse(obj);

			}
			$("#bespoke_head_txt1").html(obj.bespoke_count);
			$("#charging_head_txt1").html(obj.charging_count);
			$("#free_head_txt1").html(obj.free_count);
			$("#error_head_txt1").html(obj.error_count);
			$("#off_pile_txt1").html(obj.countOff);
		}
	});
}
$(document).ready(function() {
	$('body').on('click', '#back', function() {
		$(".cityArea").hide();
		$(".allArea").show();
	})
})