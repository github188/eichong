listChargeSearch();
function listChargeSearch(){
	initTable("listChargeForm","listChargePage",listChargeUrl,listChargeCallback);
}
function listChargeCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkChargingorder+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].chorCode+'</td><td>'
				+data[i].eleCode+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].ownerShip+'</td><td>'
				+data[i].userPhone+'</td><td>'
				+data[i].chorMoeny+'</td><td>'
				+data[i].chorQuantityelectricity+'</td><td>'
				+data[i].couponMoney+'</td><td>'
				+data[i].chorTimequantum+'</td><td>'
				+data[i].chargingstatusName+'</td><td><span class="chargeDetail" relId="'+data[i].pkChargingorder+'">详细</span>'
//							
				+'</td></tr>';
	}
	$("#listChargeTbody").html(listTr);
}
loadProvince("listCharge");
//电桩状态
initSelects("19","elpiState");
//点击详细
$("body").off("click",".chargeDetail").on("click",".chargeDetail",function(){
	relId=$(this).attr("relId");
	loadpage(listChargeDetailPage);
})



