listPayOrderSearch();
function listPayOrderSearch(){
	initTable("listPayOrderForm","listPayOrderPage",listPayOrderUrl,listPayOrderCallback);
}
function listPayOrderCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="" class="selectedBox" value=""/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].userPhone+'</td><td>'
				+data[i].userName+'</td><td>'
				+data[i].puhiMonetary+'</td><td>'
				+new Date(data[i].puhiCreatedate).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].puhiPurchasecontent+'</td></tr>';
	}
	$("#listPayOrderTbody").html(listTr);
}
//充值渠道
initSelects("18","puhiChargeType");