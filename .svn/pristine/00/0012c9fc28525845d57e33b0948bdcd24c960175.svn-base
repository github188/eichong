mui.init();
var cityPicker = new mui.PopPicker({
	layer : 3
});
cityPicker.setData(cityData3);
//cityPicker.hide();
$(".mui-poppicker").hide();
var showCityPickerButton = document.getElementById('showCityPicker3');
var cityResult3 = document.getElementById('cityResult3');
var provinceId = document.getElementById('provinceId');
var cityId = document.getElementById('cityId');
var areaId = document.getElementById('areaId');
function showCityPicker() {
	$(".mui-poppicker").show();
	cityPicker.show(function(items) {
		cityResult3.innerText = (items[0] || {}).text + " "
				+ (items[1] || {}).text + " " + (items[2] || {}).text;
		provinceId.value = (items[0] || {}).value;
		cityId.value = (items[1] || {}).value;
		areaId.value = (items[2] || {}).value;
		// 返回 false 可以阻止选择框的关闭
//		 return false;
	});
}

//mui.toast(1);
var useHead = getUrlParam("useHead");
useHead = isNaN(useHead) || (!useHead) ? -1 : useHead;
if (useHead != -1) {
	$(".container").show();
}

//mui.toast(2);
var userId = getUrlParam("userId");
////checkUserLogin();
//var user=getUserInfo();
//var userId=user.pkUserinfo;
userId = isNaN(userId) || (!userId) ? -1 : userId;
if (!userId) {
	userId = -1;
	
} else {
	loadMyApply(userId);
}

//mui.toast(3);
var aepOrigin = getUrlParam("aepOrigin");
var token = getUrlParam("token");
aepOrigin = isNaN(aepOrigin) || (!aepOrigin) ? -1 : aepOrigin;
token = !token ? -1 : token;
var pageFlag = true;
checkValid(token, aepOrigin);
//mui.toast(4);
// loadMyApply(userId);
/*
 * $("#rightApply1").on("click tap",function(){ toPage("main2"); });
 */

function toPage1() {
	$(".noApply").hide();
	toPage("main2");
}

function toPage2() {
	if (peopleTest() && phoneTest() && areaTest() && addrTest()) {
		toPage("main3");
		if ($("#classInfo").val() == "1") {
			$(".class1").show();
			$(".class2").hide();
		} else {
			$(".class1").hide();
			$(".class2").show();
		}
	}
}
$("body").on("click","#rightApply3",function(){
	toPage3();
})
function toPage3() {
	// return;
	$.ajax({
		url : getRootPath() + "/itf/applyep/new",
		type : "post",
		dataType : 'json',
		timeout : 3000,
		data : {
			aepAType : $("#typeInfo").val(),
			aepCType : $("#classInfo").val(),
			aepName :$("#people").val(),
			aepPhone : $("#telNumber").val(),
			aepPCode : $("#provinceId").val(),
			aepCCode : $("#cityId").val(),
			aepACode : $("#areaId").val(),
			aepAddr : $("#detailInfo").val(),
			aepHPark : $("#aepHPark").val(),
			aepParkNum : $("#aepParkNum").val(),
			aepEpType : $("#aepEpType").val(),
			aepIAgree : $("#aepIAgree").val(),
			aepIReport : $("#aepIReport").val(),
			aepIGround : $("#aepIGround").val(),
			aepUserId : userId,
			aepOrigin : aepOrigin,
			token : token
		},
		success : function(datas) {
			if (datas.status = 100) {
				//mui.toast("申请成功！");
				toPage("main1");
				loadMyApply(userId);
				$("#rightApply1").hide();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			/*alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);*/
			//mui.toast("网络异常，请稍后再试！");
		}
	});
}

function toPage(id) {
//	mui.toast("跳转页面1：" + id);
	$(".tabContent").hide();
	$("#" + id).show();
//	mui.toast("跳转页面2：" + id);
}

$("#back2").click(function() {
	toPage("main2");
});
$("#back1").click(function() {
	toPage("main1");
});

function checkValid(token, aepOrigin) {
	$.ajax({
		url : getRootPath() + "/itf/applyep/valid",
		type : "get",
		dataType : 'json',
		data : {
			token : token,
			aepOrigin : aepOrigin
		},
		success : function(datas) {
			if (datas.status != 100) {
				pageFlag = false;
				// //mui.toast("页面参数有误！");
				window.location.href = "error.html";
			}
		}

	});
}

function loadMyApply(userId) {
	// //mui.toast("页面参数有误！");
	if (userId == -1)
		return;
	$.ajax({
		url : getRootPath() + "/itf/applyep",
		type : "get",
		dataType : 'json',
		data : {
			aepUserId : userId
		},
		success : function(datas) {
			//alert("加载请求返回:" + JSON.stringify(datas));
			var data = datas.data;
			if (datas.status == 100) {
				if (data.length > 0) {
					$(".applyD").show();
//					$(".noApply").hide();
				}else{
					var tip='<div class="noApply" style="width: 100%;height: 4rem;line-height: 4rem; padding-left: 10%;font-size: 1.6rem;position: absolute;bottom: 20%;left: 0;">'
						+'暂无申请记录'
						+'</div>';
						$("body").append(tip);
						return;
				}
				var content = "";
				var aepAType = "", aepCType = "", createDate = "", aepStatus = "";
				for (var i = 0; i < data.length; i++) {
					aepAType = data[i].aepAType == "1" ? "免费建桩"
							: "自费建桩";
					aepCType = data[i].aepCType == 1 ? "个人" : "企业";
					createDate = new Date(data[i].createDate)
							.format("yyyy年MM月dd日")
					if (data[i].aepStatus == 1) {
						aepStatus = "提交申请";
					} else if (data[i].aepStatus == 2) {
						aepStatus = "勘探现场";
					} else if (data[i].aepStatus == 3) {
						aepStatus = "现场施工";
					} else if (data[i].aepStatus == 4) {
						aepStatus = "建桩成功";
					}
					/*
					 * content+='<tr><td scope="row">'+aepAType+'</td><td>'+aepCType+'</td>' +'<td>'+createDate+'</td><td class="red" id="SubmitStatus">'+aepStatus+'</td></tr>';
					 */
					content += '<div class="applyStates"><div class="col-xs-3 headValue headValueCenter">'
							+ aepAType
							+ '</div><div class="col-xs-3 headValue headValueCenter">'
							+ aepCType
							+ '</div><div class="col-xs-3 headValue">'
							+ createDate
							+ '</div><div class="col-xs-3 headValue red headValueCenter">'
							+ aepStatus + '</div></div>';
				}
				// alert(content);
				$("#applyMain").html(content);
			} else {
				var tip='<div class="noApply" style="width: 100%;height: 4rem; line-height: 4rem; padding-left: 10%;font-size: 1.6rem;position: absolute;bottom: 20%;left: 0;display: none;">'
						+'暂无申请记录'
						+'</div>';
				$("body").append(tip);
				//mui.toast(datas.msg);
			}
		}
	});
}
function toMyPage(){
	window.location.href="myPage.html";
}
