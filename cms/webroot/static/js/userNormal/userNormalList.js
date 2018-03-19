listUserInfoSearch();
function listUserInfoSearch(){
	initTable("listUserInfoForm","listUserInfoPage",listUserInfoUrl,listUserInfoCallback);
}
function listUserInfoCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].userId+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+new Date(data[i].createDate).format("yyyy-MM-dd")+'</td><td>'
				+data[i].userAccount+'</td><td>'
				+data[i].normName+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].normEmail+'</td><td>'
				+data[i].normAddress+'</td><td>'
				+data[i].extValue2+'</td><td>'
				+data[i].extValue3+'</td><td>'
				+data[i].extValue4+'</td></tr>';
	}
	$("#listUserInfoTbody").html(listTr);
}
//注册渠道
initSelects("10","normRegisteType");