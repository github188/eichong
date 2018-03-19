listBusBespokeSearch();
function listBusBespokeSearch(){
	initTable("listBusBespokeForm","listBusBespokePage",listBusBespokeUrl,listBusBespokeCallback);
}
function listBusBespokeCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="" class="selectedBox" value=""/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].bespResepaymentcode+'</td><td>'
				+data[i].eleCode+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].comName+'</td><td>'
				+data[i].bespBespokeprice+'</td><td>'
				+new Date(data[i].bespBeginTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+new Date(data[i].bespEndTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+new Date(data[i].bespRealityTime).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+data[i].bespOrderStateName+'</td>'
				+'</tr>';
	}
	$("#listBusBespokeTbody").html(listTr);
}
//电桩状态
initSelects("20","elpiState");
