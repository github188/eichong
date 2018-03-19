listBusChargeSearch();
function listBusChargeSearch(){
	initTable("listBusChargeForm","listBusChargePage",listBusChargeUrl,listBusChargeCallback);
}
function listBusChargeCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="" class="selectedBox" value=""/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].chorCode+'</td><td>'
				+data[i].eleCode+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].comName+'</td><td>'
				+data[i].chorChargemoney+'</td><td>'
				+data[i].chorQuantityelectricity+'</td><td>'
				+data[i].chorTimequantum+'</td><td>'
				+data[i].chargingstatusName+'</td>'
				+'</tr>';
	}
	$("#listBusChargeTbody").html(listTr);
}
//电桩状态
initSelects("19","elpiState");