//返回
$("body").off("click","#goBack").on("click","#goBack",function(){
	goBack();
})
$(function(){
//	initSelects("0","postIsappoint");	
	$.ajax({
		type:"get",
		url:basePath + stationDetailUrl+"?format=true&pkPowerstation="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			var datas=obj.electricList;
//			alert(JSON.stringify(datas));
			$("#postName").html(obj.postName);
			$("#postStatus").html(obj.extValue4);
			$("#postOwnProvinceCode").html(obj.extValue1);
			$("#postOwnCityCode").html(obj.extValue2);
			$("#postOwnCountyCode").html(obj.extValue3);
			$("#postAddress").html(obj.postAddress);
			$("#postLongitude").html(obj.postLongitude);
			$("#postLatitude").html(obj.postLatitude);
			$("#postPhone").html(obj.postPhone);
			$("#poStOnlineTime").html(obj.poStOnlineTime);
			$("#postIsappoint").html(obj.extValue5);
			electricListCallback(datas);
		}
	});
});

function electricListCallback(req){
	var listTr="";
	for(var i=0;i<req.length;i++){
		listTr+='<tr><td>'
				+(i+1)+'</td><td>'
				+req[i].elpiElectricpilecode+'</td><td>'
				+req[i].elpiElectricpilename+'</td><td>'
				+req[i].extValue1+'</td><td>'
				+req[i].extValue2+'</td><td>'
				+req[i].extValue3+'</td><td>'
				+req[i].elPiRateInformationId+'</td></tr>';
	}
	$("#stationDetailTbody").html(listTr);
}