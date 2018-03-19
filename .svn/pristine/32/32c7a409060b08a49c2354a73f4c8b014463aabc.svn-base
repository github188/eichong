powerStationListSearch();
function powerStationListSearch(){
	initTable("powerStationListForm","powerStationListPage",powerStationListUrl,powerStationListCallback);
}

function powerStationListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkPowerstation+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].postName+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].electricPileCount+'</td><td>'
				+data[i].postAddress+'</td><td>'
				+data[i].stateName+'</td><td>'
				+data[i].elPi_UserName+'</td>'
				+'<td><span class="stationDetail" relId="'+data[i].pkPowerstation+'">详细</span>'
				+'&nbsp;&nbsp;<span class="stationEdit" relId="'+data[i].pkPowerstation+'">编辑</span>'
				+'</td></tr>';
	}
	$("#powerStationListTbody").html(listTr);
}
loadProvince("powerStationList");

initSelects("12","stationState");
//点击进入充电点编辑
$("body").off("click",".stationEdit").on("click",".stationEdit",function(){
	relId=$(this).attr("relId");
	loadpage(stationEditPage);
})

//点击进入充电点详细
$("body").off("click",".stationDetail").on("click",".stationDetail",function(){
	relId=$(this).attr("relId");
	loadpage(stationDetailPage);
})