listBespokeSearch();
function listBespokeSearch(){
	initTable("listBespokeForm","listBespokePage",listBespokeUrl,listBespokeCallback);
}
function listBespokeCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="" class="selectedBox" value=""/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].bespResepaymentcode+'</td><td>'
				+data[i].eleCode+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].ownerShip+'</td><td>'
				+data[i].userPhone+'</td><td>'
				+data[i].bespBespokeprice+'</td><td>'
				+data[i].bespFrozenamt+'</td><td>'
				+new Date(data[i].bespBeginTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+new Date(data[i].bespEndTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+new Date(data[i].bespRealityTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+data[i].bespOrderStateName+'</td>'
				+'</tr>';
	}
	$("#listBespokeTbody").html(listTr);
}
loadProvince("listBespoke");
//电桩状态
initSelects("20","elpiState");
