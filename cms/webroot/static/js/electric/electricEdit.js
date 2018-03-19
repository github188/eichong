$(function(){
	initSelects("3,12,4,1,5,22,0,0,0,0","elpiChargingmode,elpiState,elpiPowersize,elpiType,"
			+"elpiPowerinterface,elPiOwnerCompany,elPiHaveConnectLine,elPiHaveGps,elpiIsappoint,elPiHaveLedFlash");	
	initSelect(pileMakerDictUrl,"elpiMaker");
	initSelect(typeSpanDictUrl,"elpiTypeSpanId");
	initSelect(rateInfoDictUrl,"elPiRateInformationId");
	$.ajax({
		type:"get",
		url:basePath + electricDetailUrl+"?pkElectricpile="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			$("#pkElectricpile").val(obj.pkElectricpile);
			$("#elpiMaker").val(obj.elpiMaker);
			$("#elpiTypeSpanId").val(obj.elpiTypeSpanId);
			$("#elPiRateInformationId").val(obj.elPiRateInformationId);
			$("#elpiElectricpilecode").html(obj.elpiElectricpilecode);
			$("#elpiElectricpilename").html(obj.elpiElectricpilename);
			$("#elpiChargingmode").val(obj.elpiChargingmode);
			$("#elpiState").val(obj.elpiState);
			$("#elpiPowersize").val(obj.elpiPowersize);
			$("#companyFlag").val(obj.companyNumber);
			$("#elPiOwnerCompany").val(obj.elPiOwnerCompany);
			loadProvince("electricEdit",obj.elPiOwnProvinceCode);
			loadCity(obj.elPiOwnProvinceCode,obj.elPiOwnCityCode);
			loadAreaList(obj.elPiOwnCityCode,obj.elPiOwnCountyCode);
			$("#elpiElectricpileaddress").val(obj.elpiElectricpileaddress);
			$("#longitude").val(obj.elpiLongitude);
			$("#latitude").val(obj.elpiLatitude);
			$("#maxVoltage").val(obj.elpiOutputvoltage);
			$("#maxCurrent").val(obj.elpiOutputcurrent);
			$("#elPiHaveConnectLine").val(obj.elPiHaveConnectLine);
			$("#headNum").val(obj.elpiPowernumber);
			$("#elPi_Tell").val(obj.elPi_Tell);
			$("#elPiOnlineTime").val(obj.elPiOnlineTime);
			$("#elPiSimPhoneNum").val(obj.elPiSimPhoneNum);
			$("#elPiSimMac").val(obj.elPiSimMac);
			$("#elPiHaveGps").val(obj.elPiHaveGps);
			$("#elpiIsappoint").val(obj.elpiIsappoint);
			$("#elPiHaveLedFlash").val(obj.elPiHaveLedFlash);
		}
	});
});
//取消
$("body").off("click","#electricEditCancelBtn").on("click","#electricEditCancelBtn",function(){
	goBack();
})


//页面基本的正则验证部分
$(".validateBlock input").on("blur",function(){
	if($(this).val()==""||$(this).val().length==0){
		var thisContent=$(this).siblings().html();
		$(".electricEditTextTip").show().html(thisContent+"不能为空");
		$(this).focus();
		return false;
	}else{
		$(".electricEditTextTip").hide().html("");
		return true;
	}
})
$("#electricEditSaveBtn").on("click",function(){
	if($(".electricEditTextTip").css("display")=="none"){
		if(testNumber(".numTest",".electricEditTextTip")&&isNumeric("#longitude",".electricEditTextTip")&&isNumeric("#latitude",".electricEditTextTip")
			&&isNumeric("#maxVoltage",".electricEditTextTip")&&isNumeric("#maxCurrent",".electricEditTextTip")
			&&isInteger("#headNum",".electricEditTextTip")
		){
			saveElectricInfo();
		}
	}else{
		return false;
	}
})
//点击保存执行的方法
function saveElectricInfo(){
	console.log("电桩编辑");
}

