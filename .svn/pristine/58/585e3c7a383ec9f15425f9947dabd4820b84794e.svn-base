$("body").on("click","#selectPile",function(){
	electricSearch();
	layer.closeAll();
	 layer.open({
	  type: 1,
	  title: '选择电桩',
	  btn:["确定"],
	  shadeClose: true, //点击遮罩关闭层
	  area : ['800px' , '400px'],
	  content: $("#bindPile"),
	  yes: function(){
	  	$(".selAll").attr("checked",false);
	  	selectElectricCallback();
	  	layer.closeAll();
	  }
	 });
})
//sim卡运营商
initSelects("8","serviceProvider");
//页面基本的验证
$("#concentratorAddSaveBtn").on("click",function(){
	if(testArray(".validateBlock",".concentratorAddTextTip")&&testSelect(".validateBlock",".concentratorAddTextTip")){
		return true;
	}else{
		return false;
	}
})
//取消
$("body").off("click","#concentratorAddCancelBtn").on("click","#concentratorAddCancelBtn",function(){
	goBack();
})

//绑定电桩列表

function electricSearch(){
	initTable("BindElectricForm","BindElectricPage",electricListUrl,getElectricPileList);
}
function getElectricPileList(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids"   value="'+data[i].pkElectricpile+'" class="selectedBox" '
				+'elpiElectricpilecode="'+data[i].elpiElectricpilecode+'" '
				+'elpiElectricpilename="'+data[i].elpiElectricpilename+'" '
				+'extValue1="'+data[i].extValue1+'" '
				+'extValue2="'+data[i].extValue2+'" '
				+'extValue3="'+data[i].extValue3+'" '
				+'/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].elpiElectricpilecode+'</td><td>'
				+data[i].elpiElectricpilename+'</td><td>'
				+data[i].extValue3+'</td><td>'
				+data[i].extValue1+'</td></tr>'
	}
	$("#bindPileTbody").html(listTr);
}

function selectElectricCallback(){
	var i = parseInt($("#concentratorAddTbody tr").length);
	var listTr="";
	$('input[name="ids"]:checked').each(function(){
//		console.log($(this).val());
		listTr+='<tr><td>'
			+'<input type="hidden" name="selectedIds"  value="'+$(this).val()+'" />'
			+(++i)+'</td><td>'
			+$(this).attr("elpiElectricpilecode")+'</td><td>'
			+$(this).attr("elpiElectricpilename")+'</td><td>'
			+$(this).attr("extValue3")+'</td><td>'
			+$(this).attr("extValue1")+'</td><td>'
			+$(this).attr("extValue2")+'</td></tr>';
	});
	$("#concentratorAddTbody").append(listTr);
}