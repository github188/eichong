concentratorListSearch();
function concentratorListSearch(){
	initTable("concentratorListForm","concentratorListPage",concentratorListUrl,concentratorListCallback);
}
function concentratorListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkConcentratorID+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].pkConcentratorID+'</td><td>'
				+data[i].coctConcentratorName+'</td><td>'
				+data[i].coctSIMMAC+'</td><td>'
				+data[i].coctSIMCODE+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].coctUserName+'</td><td>'
				+data[i].extValue2+'</td><td><span class="concentratorEdit" relId="'+data[i].pkConcentratorID+'">编辑</span>'
				+'</td></tr>';
	}
	$("#concentratorListTbody").html(listTr);
}
initSelects("8,9","serviceProvider,concentratorState");

$("body").off("click","#concentratorAdd").on("click","#concentratorAdd",function(){
	loadpage(concentratorAddPage);
})
$("body").off("click",".concentratorEdit").on("click",".concentratorEdit",function(){
	relId=$(this).attr("relId");
	loadpage(concentratorEditPage);
})