$(function(){
	$.ajax({
		type:"get",
		url:basePath + electricDetailUrl+"?format=true&pkElectricpile="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			$("#elpiElectricpilecode").html(obj.elpiElectricpilecode);
			$("#elpiElectricpilename").html(obj.elpiElectricpilename);
			$("#elpiChargingmode").html(obj.extValue1);
			$("#elpiState").html(obj.extValue2);
			$("#elpiPowersize").html(obj.extValue3);
			$("#elpiType").html(obj.extValue4);
			$("#elpiPowerinterface").html(obj.extValue5);
			$("#elpiMaker").html(obj.extValue15);
			$("#elpiTypeSpanId").html(obj.extValue16);
			$("#companyFlag").val(obj.companyNumber);
			$("#elPiOwnerCompany").html(obj.extValue7);
			$("#elPiRateInformationId").html(obj.extValue17);
			$("#electricDetailProvince").html(obj.extValue12);
			$("#electricDetailCity").html(obj.extValue13);
			$("#electricDetailArea").html(obj.extValue14);
			$("#elpiElectricpileaddress").val(obj.elpiElectricpileaddress);
			$("#longitude").val(obj.elpiLongitude);
			$("#latitude").val(obj.elpiLatitude);
			$("#maxVoltage").val(obj.elpiOutputvoltage);
			$("#maxCurrent").val(obj.elpiOutputcurrent);
			$("#elPiHaveConnectLine").html(obj.extValue8);
			$("#headNum").val(obj.elpiPowernumber);
			$("#elPi_Tell").val(obj.elPi_Tell);
			$("#elPiOnlineTime").val(obj.elPiOnlineTime);
			$("#elPiSimPhoneNum").val(obj.elPiSimPhoneNum);
			$("#elPiSimMac").val(obj.elPiSimMac);
			$("#elPiHaveGps").html(obj.extValue9);
			$("#elpiIsappoint").html(obj.extValue10);
			$("#elPiHaveLedFlash").html(obj.extValue11);
			
		}
	});
});

$("body").off("click","#goBack").on("click","#goBack",function(){
	goBack();
})