$(document).ready(function() {
//	clearInterval(intervalId);
//	intervalId=setInterval("loadHeadDetailInfo()",10000);
});
loadHeadDetailInfo();
function loadHeadDetailInfo(){
	var selectHeadId=$(".qiangtouSelect").attr("data-id");
//	$.ajaxSettings.global=false;
	$.ajax({
		type : 'post',
		url : basepath+"/admin/electricCurrent/getHeadDetail.do",
		dataType : "json",
		data:{headId:selectHeadId,
			  epCode:$("#epCode").attr("data-code")
		},
		success : function(data) {
		   var headstate=data.epheElectricpileheadstate;
		   switch(headstate)
		   {
		   //0空闲中，3预约中，6充电中，9停用中
		   case '0':
			   headstate="空闲中";
		     break;
		   case '3':
			   headstate="预约中";
		     break;
		   case '6':
			   headstate="充电中";
			     break;
		   case '9':
			   headstate="停用中";
			     break;     
		   default:
			   headstate="空闲中";
		   }
		   var sum = (parseFloat(data.lowTemperature)+parseFloat(data.highTemperature))/2;
	       $("#epheElectricpileheadstateDiv").html(headstate);
	       $("#totalChargeDlDiv").html(dealNullToFloat(data.totalChargeDl));
	       $("#totalChargeTimeDiv").html(dealNullToInt(data.totalChargeTime));
	       $("#totalChargeAmtDiv").html(dealNullToFloat(data.totalChargeAmt));
	       $("#lowTemperatureDiv").html(dealNullToInt(data.lowTemperature));
	       $("#highTemperatureDiv").html(dealNullToInt(data.highTemperature));	       
		   $("input[name=detailTemperature]").val(sum); 		       	      
	       $("#Risk").click();
	       drowVoltageChart(data);
	       drowDcDiv(data);
	       
		}
	});
//	$.ajaxSettings.global=true;
}

function changeSelectClass(_this){
	$(".qiangtouSelect").addClass("qiangtouUnselect");
	$(".qiangtouSelect").removeClass("qiangtouSelect");
	$(_this).parent().removeClass("qiangtouUnselect");
	$(_this).parent().addClass("qiangtouSelect");
}

function dealNullToInt(val){
	var valTemp = val;
	return valTemp==null || valTemp.trim() ==""?0:valTemp
}

function dealNullToFloat(val){
	var valTemp = val;
	return valTemp==null || valTemp.trim() ==""?0.00:valTemp
}