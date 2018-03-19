$(function(){
	rateinfoListSearch();
	loadProvince("rateInfoEdit");
	loadProvince("rateInfoAdd");
});
function rateinfoListSearch() {
	initTable("rateinfoListForm", "rateinfoListPage", rateinfoListUrl, rateinfoListCallback);
}

function rateinfoListCallback(req) {
	var data = req.data;
	var listTr = "";
	for(var i = 0; i < data.length; i++) {
		listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkRateinformation+'"/></td><td>' 
			+data[i].pkRateinformation + '</td><td>' 
			+data[i].cityName + '</td><td>' 
			+data[i].rainReservationrate + '</td><td>' 
			+data[i].rainServicecharge + '</td><td>' 
			+data[i].rainTiptimetariff + '</td><td>' 
			+data[i].rainPeakelectricityprice + '</td><td>' 
			+data[i].rainUsualprice + '</td><td>' 
			+data[i].rainValleytimeprice + '</td><td>' 
			+data[i].rainRemarks + '</td><td><span class="rateInfoEdit" relId="'+data[i].pkRateinformation+'">编辑</span>' 
			+'</td></tr>';
	}
	$("#rateinfoListTbody").html(listTr);
}
//费率新增
$("body").on("click", "#rateInfoAdd", function() {
	cleanRow("rateinfoTbody");	
	timeListAddRow("rateinfoTbody");
		layer.closeAll();
		layer.open({
			type: 1,
			resize: false,
			move: false,
			offset: '100px',
			title: ["费率新增", "font-size:12px"],
			shadeClose: false,
			closeBtn: 2,
			area: ['600px', 'auto'], //宽高 
			content: $("#rateInfoAddContent"),
			btn: ["确定"],
			btn1: function(index, layero) {
				rateInfoAddCallBack();
			},
			cancel: function(index, layero) {
				layer.closeAll();
			}
		});
	})
	//费率新增加载省市区

function rateInfoAddCallBack() {
	if($("#rateInfoAddProvince option:selected").attr("value") == undefined) {
		$("#rateInfoAddProvince").focus();
		$(".rateInfoAddTipText").html("省不能为空").show();
		return false;
	}
	if($("#rateInfoAddCity option:selected").attr("value") == undefined) {
		$("#rateInfoAddCity").focus();
		$(".rateInfoAddTipText").html("市不能为空").show();
		return false;
	}
	if($("#rateInfoAddArea option:selected").attr("value") == undefined) {
		$("#rateInfoAddArea").focus();
		$(".rateInfoAddTipText").html("区域不能为空").show();
		return false;
	} else if(
		testArray(".checkInput", ".rateInfoAddTipText") && isNumeric("#jdCharge", ".rateInfoAddTipText") &&
		isNumeric("#fdCharge", ".rateInfoAddTipText") && isNumeric("#pdCharge", ".rateInfoAddTipText") &&
		isNumeric("#gdCharge", ".rateInfoAddTipText") && isNumeric("#orderCharge", ".rateInfoAddTipText") &&
		isNumeric("#serviceCharge", ".rateInfoAddTipText") 
		&&testTimeAdd()
	) {
//		alert("费率新增")
		console.log("费率新增");
		var json=formatTimeListToJSON("rateinfoTbody");
		$("#rainQuantumdateAdd").val(json);
		$("#rateinfoAddForm").ajaxSubmitForm();
		layer.closeAll();
		return true;
	}

	return true;
}
$("#timeListAddBtn").off("click");
$("#timeListAddBtn").on("click", function() {
	$(".rateInfoAddTipText").html("").hide();
	timeListAddRow("rateinfoTbody");
})

var tempHour=0;
var tempMinute=0;
function cleanRow(tableId){
	tempHour=0;
	tempMinute=0;
	$("#"+tableId).html("");
}
function timeListAddRow(tableId,data) {
	//第一行复选框禁用
	var tempType="";
	var selected="";
	var indexList=$("#"+tableId+" tr").length;
	var disabled=indexList==0?'disabled="disabled"':"";
	var typeOptions="";
	var shOptions="",smOptions="",ehOptions="",emOptions="";
	var type="",sh=0,sm=0,eh=0,em=0;
	
	if(data){
		type=data.mark;
		sh=data.sh;
		sm=data.sm;
		eh=data.eh;
		em=data.em;
	}
	var typeArray=new Array("尖","峰","平","谷");
	for(var i=1;i<=4;i++){
		if(type==i){
			selected="selected";
		}else{
			selected="";
		}
		typeOptions+='<option value="'+i+'" '+selected+'>'+typeArray[i-1]+'</option>';
	}
	for(var i=0;i<=24;i++){
		if((tempHour!=0&&tempHour==i)||sh==i){
			selected="selected";
		}else{
			selected="";
		}
		shOptions+='<option value="'+i+'" '+selected+'>'+i+'</option>';
	}
	for(var i=0;i<=24;i++){
		if(eh==i){
			selected="selected";
		}else{
			selected="";
		}
		ehOptions+='<option value="'+i+'" '+selected+'>'+i+'</option>';
	}
	for(var i=0;i<60;i++){
		if((tempMinute!=0&&tempMinute==i)||sm==i){
			selected="selected";
		}else{
			selected="";
		}
		smOptions+='<option value="'+i+'" '+selected+'>'+i+'</option>';
	}
	for(var i=0;i<60;i++){
		if(em==i){
			selected="selected";
		}else{
			selected="";
		}
		emOptions+='<option value="'+i+'" '+selected+'>'+i+'</option>';
	}
	var html = '<tr><td class="smallWidth"><input '+disabled+' type="checkbox" name="test" class="selectPer" value=""/></td>' 
			+'<td><select name="mark">'+typeOptions+'</select></td>'
			+'<td><select name="starth">'+shOptions+'</select><span>时</span>' 
			+'<select name="startm">'+smOptions+'</select><span>分</span></td>' 
			+'<td><select name="endh" onchange="setHour(this)">'+ehOptions+'</select><span>时</span>'
			+'<select name="endm" onchange="setMinute(this)">'+emOptions+'</select><span>分</span></td></tr>';
	$("#"+tableId).append(html);
}

function setHour(obj){
	tempHour=obj.value;
}
function setMinute(obj){
	tempMinute=obj.value;
}

function formatTimeListToJSON(tableId){
	var json = "{\"data\":["; 
	$("#"+tableId+" tr").each(function(i){
		var mark=$(this).find("select[name='mark']").val();
		var starth=$(this).find("select[name='starth']").val();
		var startm=$(this).find("select[name='startm']").val();
		var endh=$(this).find("select[name='endh']").val();
		var endm=$(this).find("select[name='endm']").val();
		var st=parseInt(starth)*60+parseInt(startm);
		var et=parseInt(endh)*60+parseInt(endm);
		console.log(mark+"___"+starth+"___"+startm+"___"+endh+"___"+endm);
		json += "{\"st\":" + st + ",\"et\":" + et + ",\"mark\":" + "\"" + mark + "\"},";
	})
	json = json.substring(0,json.length - 1);
	json += "]}";
	return json;
}

$("#timeListDeleteBtn").off("click");
$("#timeListDeleteBtn").on("click", function() {
	timeListDelete();
})

function timeListDelete() {
	if($("#rateinfoTbody tr").length == 1) {
		$(".rateInfoAddTipText").html("第一行不能删除").show();
		return;
	}
	if($("#rateinfoTbody tr").length > 1) {
		if($("#rateinfoTbody tr").length == $("input[name='test']:checked").length) {
			$("#rateinfoTbody tr:not(:first)").remove();
			$(".rateInfoAddTipText").html("第一行不能删除").show();
		} else {
			$("input[name='test']:checked").each(function() { // 遍历选中的checkbox
				n = $(this).parents("tr").index() + 1; // 获取checkbox所在行的顺序，多加1行是因为标题
				$("table#rateInfoAddTable").find("tr:eq(" + n + ")").remove();
				$(".rateInfoAddTipText").html("").hide();
			});
		}

	}
	return true;
}


//遍历每行时间

function testTimeAdd() {
	var firstEndH = "";
	var firstEndM = "";
	var firstEndHT = "";
	var firstEndMT = "";
	var secondStartH = "";
	var secondStartM = "";
	var boo = 1;
	$("#rateinfoTbody").find("tr").each(function(index) {
		//遍历每行的每个select
		//	alert($("#rateinfoTbody tr").length)
		$("select", this).each(function(cindex) {
			var name = $(this).attr("name");
			//		alert(cindex);
			//将每行的开始时间存放起来
			if(name == "starth") {
				secondStartH = $(this).val();
			} else if(name == "startm") {
				secondStartM = $(this).val();
			} else if(name == "endh") {
				firstEndH = $(this).val();
			} else if(name == "endm") {
				firstEndM = $(this).val();
			}
		});

		//第一行的开始时间必须从0点开始			
		if(index == 0) {
			if(secondStartH != 0 || secondStartM != 0) {
				//			alert("费率的起始时间必须从每天0点开始，请重新设置");
				$(".rateInfoAddTipText").html("费率的起始时间必须从每天0点开始，请重新设置").show();
				boo = 0;
				return false;
			}
		}
		if((parseInt(secondStartH) * 60 + parseInt(secondStartM)) >= (parseInt(firstEndH) * 60 + parseInt(firstEndM))) {
			//alert(((secondStartH * 60 + secondStartM) >= (firstEndH * 60 + firstEndM)) + "||" + (secondStartH * 60 + secondStartM) + "--" + (firstEndH * 60 + firstEndM));
			//		alert("时间段设置--同行的结束时间必须大于开始时间，请重新设置");
			$(".rateInfoAddTipText").html("时间段设置--同行的结束时间必须大于开始时间，请重新设置").show();
			boo = 0;
			return false;
		}
		//在有多行的时候再开始判断
		if(index > 0) {
			//alert(secondStartH + ":" + secondStartM + "--" + firstEndHT + ":" + firstEndMT);
			//上一次的结束时间不等于这一次的开始时间的话给出提示
			if(secondStartH != firstEndHT || secondStartM != firstEndMT) {
				//			alert("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置");
				$(".rateInfoAddTipText").html("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置").show();
				boo = 0;
				return false;
			}
			firstEndHT = "";
			firstEndMT = "";
		}
		//将本行最后的时间保存下来，用来与下一行的开始时间比对
		firstEndHT = firstEndH;
		firstEndMT = firstEndM;
		//费率的结束时间必须是每天24点
	});
	if(firstEndH != "24") {
		//	alert("费率的最终结束时间必须为每天的24点，请重新设置");
		$(".rateInfoAddTipText").html("费率的最终结束时间必须为每天的24点，请重新设置").show();
		boo = 0;
		return false;
	}
	if(boo == 0) {
		return false;
	} else{
		return true;
	}
		
}

//费率编辑======================================================================================
$("body").off("click",".rateInfoEdit").on("click",".rateInfoEdit", function() {
	cleanRow("rateinfoEditTbody");	
	var relId=$(this).attr("relId");
	rateinfoDetail(relId);
	layer.closeAll();
	layer.open({
		type: 1,
		resize: false,
		move: false,
		offset: '100px',
		title: ["费率编辑", "font-size:12px"],
		shadeClose: false,
		closeBtn: 2,
		area: ['600px', 'auto'], //宽高 
		content: $("#rateInfoEditContent"),
		btn: ["确定"],
		btn1: function(index, layero) {
			rateInfoEditCallBack();
		},
		cancel: function(index, layero) {
			layer.closeAll();
		}
	});
})

function rateinfoDetail(relId){
	$.ajax({
		type:"get",
		url:basePath + rateinfoDetailUrl+"?id="+relId,
		dataType:"json",
		success:function(req){
			console.log(JSON.stringify(req));
			if (req.status == 100) {
				var obj=req.data;
				$("#pkRateinformation").val(obj.pkRateinformation);
				loadProvince("rateInfoEdit",obj.raInProvinceId);
				loadCity(obj.raInProvinceId,obj.raInCityId);
				loadAreaList(obj.raInCityId,obj.raInAreaId);
				$("#rainTiptimetariff").val(obj.rainTiptimetariff);
				$("#rainWarnmoney").val(obj.rainWarnmoney);
				$("#rainMinfreezingmoney").val(obj.rainMinfreezingmoney);
				$("#rainPeakelectricityprice").val(obj.rainPeakelectricityprice);
				$("#rainUsualprice").val(obj.rainUsualprice);
				$("#rainValleytimeprice").val(obj.rainValleytimeprice);
				$("#rainReservationrate").val(obj.rainReservationrate);
				$("#rainServicecharge").val(obj.rainServicecharge);
				$("#rainRemarks").text(obj.rainRemarks);
				var rainQuantumdate=JSON.parse(obj.rainQuantumdate);
				$.each(rainQuantumdate.data,function(i,item){
					var data={
							sh:parseInt(item.st/60),
							sm:item.st%60,
							eh:parseInt(item.et/60),
							em:item.et%60,
							mark:item.mark
					};
					timeListAddRow("rateinfoEditTbody",data);
				})
				
			}else if (req.status == 0){
				layer.closeAll();
				layer.alert(req.msg,{offset: '100px'});
			}
		}
	});
}

function rateInfoEditCallBack() {
	if($("#rateInfoEditProvince option:selected").attr("value") == undefined) {
		$("#rateInfoEditProvince").focus();
		$(".rateInfoEditTipText").html("省不能为空").show();
		return false;
	}
	if($("#rateInfoEditCity option:selected").attr("value") == undefined) {
		$("#rateInfoEditCity").focus();
		$(".rateInfoEditTipText").html("市不能为空").show();
		return false;
	}
	if($("#rateInfoEditArea option:selected").attr("value") == undefined) {
		$("#rateInfoEditArea").focus();
		$(".rateInfoEditTipText").html("区域不能为空").show();
		return false;
	} else if(
		testArray(".checkInputEdit", ".rateInfoEditTipText") && isNumeric("#rainTiptimetariff", ".rateInfoEditTipText") &&
		isNumeric("#rainPeakelectricityprice", ".rateInfoEditTipText") && isNumeric("#rainUsualprice", ".rateInfoEditTipText") &&
		isNumeric("#rainValleytimeprice", ".rateInfoEditTipText") && isNumeric("#rainReservationrate", ".rateInfoEditTipText") &&
		isNumeric("#rainServicecharge", ".rateInfoEditTipText")&&testTimeEdit()
	) {
//		alert("费率编辑");
		console.log("费率编辑");
		var json=formatTimeListToJSON("rateinfoEditTbody");
		$("#rainQuantumdateEdit").val(json);
		$("#rateinfoEditForm").ajaxSubmitForm();
		layer.closeAll();
		return true;
	}

	return true;
}
$("#timeListEditBtn").off("click");
$("#timeListEditBtn").on("click", function() {
	$(".rateInfoEditTipText").html("").hide();
	timeListAddRow("rateinfoEditTbody");
})

$("#timeListEditDeleteBtn").off("click");
$("#timeListEditDeleteBtn").on("click", function() {
	timeListEditDelete();
})

function timeListEditDelete() {
	if($("#rateinfoEditTbody tr").length == 1) {
		$(".rateInfoEditTipText").html("第一行不能删除").show();
		return;
	}
	if($("#rateinfoEditTbody tr").length > 1) {
		if($("#rateinfoEditTbody tr").length == $("input[name='rainSelect']:checked").length) {
			//			alert($("input[name='test']:checked").length);
			$("#rateinfoEditTbody tr:not(:first)").remove();
			$(".rateInfoEditTipText").html("第一行不能删除").show();
		} else {
			$("input[name='test']:checked","#rateinfoEditTbody").each(function() { // 遍历选中的checkbox
				n = $(this).parents("tr").index() + 1; // 获取checkbox所在行的顺序，多加1行是因为标题
				$("table#rateInfoEditTable").find("tr:eq(" + n + ")").remove();
			});
		}

	}
	return true;
}
function testTimeEdit() {
	var firstEndH = "";
	var firstEndM = "";
	var firstEndHT = "";
	var firstEndMT = "";
	var secondStartH = "";
	var secondStartM = "";
	var boo = 1;
	$("#rateinfoEditTbody").find("tr").each(function(index) {
		//遍历每行的每个select
		//	alert($("#rateinfoTbody tr").length)
		$("select", this).each(function(cindex) {
			var name = $(this).attr("name");
			//		alert(cindex);
			//将每行的开始时间存放起来
			if(name == "starth") {
				secondStartH = $(this).val();
			} else if(name == "startm") {
				secondStartM = $(this).val();
			} else if(name == "endh") {
				firstEndH = $(this).val();
			} else if(name == "endm") {
				firstEndM = $(this).val();
			}
		});

		//第一行的开始时间必须从0点开始			
		if(index == 0) {
			if(secondStartH != 0 || secondStartM != 0) {
				//			alert("费率的起始时间必须从每天0点开始，请重新设置");
				$(".rateInfoEditTipText").html("费率的起始时间必须从每天0点开始，请重新设置").show();
				boo = 0;
				return false;
			}
		}
		if((parseInt(secondStartH) * 60 + parseInt(secondStartM)) >= (parseInt(firstEndH) * 60 + parseInt(firstEndM))) {
			//alert(((secondStartH * 60 + secondStartM) >= (firstEndH * 60 + firstEndM)) + "||" + (secondStartH * 60 + secondStartM) + "--" + (firstEndH * 60 + firstEndM));
			//		alert("时间段设置--同行的结束时间必须大于开始时间，请重新设置");
			$(".rateInfoEditTipText").html("时间段设置--同行的结束时间必须大于开始时间，请重新设置").show();
			boo = 0;
			return false;
		}
		//在有多行的时候再开始判断
		if(index > 0) {
			//alert(secondStartH + ":" + secondStartM + "--" + firstEndHT + ":" + firstEndMT);
			//上一次的结束时间不等于这一次的开始时间的话给出提示
			if(secondStartH != firstEndHT || secondStartM != firstEndMT) {
				//			alert("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置");
				$(".rateInfoEditTipText").html("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置").show();
				boo = 0;
				return false;
			}
			firstEndHT = "";
			firstEndMT = "";
		}
		//将本行最后的时间保存下来，用来与下一行的开始时间比对
		firstEndHT = firstEndH;
		firstEndMT = firstEndM;
		//费率的结束时间必须是每天24点
	});
	if(firstEndH != "24") {
		//	alert("费率的最终结束时间必须为每天的24点，请重新设置");
		$(".rateInfoEditTipText").html("费率的最终结束时间必须为每天的24点，请重新设置").show();
		boo = 0;
		return false;
	}
	if(boo == 0) {
		returnValue = false;
		return false;
	} else
		return true;
}