//页面基本的正则验证部分
$(".validateBlock input").on("blur",function(){
	if($(this).val()==""||$(this).val().length==0){
		var thisContent=$(this).siblings().html();
		$(".stationEditTextTip").show().html(thisContent+"不能为空");
		$(this).focus();
		return false;
	}else{
		$(".stationEditTextTip").hide().html("");
		return true;
	}
})
loadProvince("stationEdit");
$("#stationEditSaveBtn").on("click",function(){
	if($(".stationEditTextTip").css("display")=="none"){
		if(isNumeric("#longitude",".stationEditTextTip")&&isNumeric("#latitude",".stationEditTextTip")){
			saveStationInfo();
		}
		
	}else{
		return false;
	}
})
//点击保存的操作
function saveStationInfo(){
	console.log("电站编辑");
}

//取消
$("body").off("click","#stationEditCancelBtn").on("click","#stationEditCancelBtn",function(){
	goBack();
})
$(function(){
	initSelects("0","postIsappoint");	
	$.ajax({
		type:"get",
		url:basePath + stationDetailUrl+"?format=true&pkPowerstation="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			var datas=obj.electricList;
			$("#pkPowerstation").val(obj.pkPowerstation);
			$("#postName").html(obj.postName);
			$("#postStatus").html(obj.extValue4);
			loadProvince("stationEdit",obj.postOwnProvinceCode);
			loadCity(obj.postOwnProvinceCode,obj.postOwnCityCode);
			loadAreaList(obj.postOwnCityCode,obj.postOwnCountyCode);
			$("#postAddress").val(obj.postAddress);
			$("#longitude").val(obj.postLongitude);
			$("#latitude").val(obj.postLatitude);
			$("#postPhone").val(obj.postPhone);
			$("#poStOnlineTime").val(obj.poStOnlineTime);
			$("#postIsappoint").val(obj.postIsappoint);
			electricListEditCallback(datas);
		}
	});
});
function electricListEditCallback(req){
	var listTr="";
	for(var i=0;i<req.length;i++){
		listTr+='<tr><input type="hidden" name="electricList['+i+'].pkElectricpile" value="'+req[i].pkElectricpile+'"/>'
				+'<input type="hidden" name="electricList['+i+'].elpiElectricpilecode" value="'+req[i].elpiElectricpilecode+'"/>'
				+'<input type="hidden" name="electricList['+i+'].extValue1" value="'+req[i].elPiRateInformationId+'"/>'
				+'<td>'+(i+1)+'</td><td>'
				+req[i].elpiElectricpilecode+'</td><td>'
				+req[i].elpiElectricpilename+'</td><td>'
				+req[i].extValue1+'</td><td>'
				+req[i].extValue2+'</td><td>'
				+req[i].extValue3+'</td><td><input type="text" name="electricList['+i+'].elPiRateInformationId"'
				+'id="" value="'+req[i].elPiRateInformationId+'" /></td></tr>';
	}
	$("#stationEditTbody").html(listTr);
}