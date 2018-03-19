$("body").on("click","#selectPileBtn",function(){
	electricSearch();
	layer.closeAll();
	 layer.open({
	  type: 1,
	  title: '选择电桩',
	  btn:["确定"],
	  shadeClose: true,
	  closeBtn:2,//点击遮罩关闭层
	  area : ['800px' , '400px'],
	  content: $("#bindPile"),
	  yes: function(){
	  	$(".selAll").attr("checked",false);
	  	selectElectricCallback();
	  	layer.closeAll();
	  }
	 });
})
//页面正则验证
//sim卡运营商
initSelects("8","serviceProvider");
$("body").on("blur",".validateBlock input",function(){
	if($(this).val()==""||$(this).val().length==0){
		var thisContent=$(this).siblings().html();
		$(".concentratorEditTextTip").show().html(thisContent+"不能为空");
		$(this).focus();
		return false;
	}else{
		$(".concentratorEditTextTip").hide().html("");
		return true;
	}
})
$("#concentratorEditSaveBtn").on("click",function(){
	if($(".concentratorEditTextTip").css("display")=="none"){
		if(testArray(".validateBlock",".concentratorEditTextTip")&&isInteger(".cardNumber",".concentratorEditTextTip")&&testSelect(".validateBlock",".concentratorEditTextTip")){
			concentratorEditInfo();
		}
	}else{
		return false;
	}
})
function concentratorEditInfo(){
	console.log("集中器编辑");
}
//取消
$("body").off("click","#concentratorEditCancelBtn").on("click","#concentratorEditCancelBtn",function(){
	goBack();
})
$(function(){
	$.ajax({
		type:"get",
		url:basePath + concentratorEditUrl+"?pkConcentratorID="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			var datas=obj.pileList;
			$("#pkConcentratorID").val(obj.pkConcentratorID);
			$("#coctConcentratorName").val(obj.coctConcentratorName);
			$("#coctSIMMAC").val(obj.coctSIMMAC);
			$("#coctSIMCODE").val(obj.coctSIMCODE);
			$("#serviceProvider").val(obj.coctSIMTYPE);
			concentratorEditCallback(datas);
		}
	});
});
function concentratorEditCallback(req){
	var listTr="";
	for(var i=0;i<req.length;i++){
		listTr+='<tr><td>'
				+'<input type="hidden" name="selectedIds"  value="'+req[i].pkElectricpile+'" />'
				+(i+1)+'</td><td>'
				+req[i].elpiElectricpilecode+'</td><td>'
				+req[i].elpiElectricpilename+'</td><td>'
				+req[i].extValue1+'</td><td>'
				+req[i].extValue2+'</td><td>'
				+req[i].extValue3+'</td></tr>';
	}
	$("#concentratorEditTbody").html(listTr);
}
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
	var i = parseInt($("#concentratorEditTbody tr").length);
	var listTr="";
	$('input[name="ids"]:checked').each(function(){
		listTr+='<tr><td>'
			+'<input type="hidden" name="selectedIds"  value="'+$(this).val()+'" />'
			+(++i)+'</td><td>'
			+$(this).attr("elpiElectricpilecode")+'</td><td>'
			+$(this).attr("elpiElectricpilename")+'</td><td>'
			+$(this).attr("extValue3")+'</td><td>'
			+$(this).attr("extValue1")+'</td><td>'
			+$(this).attr("extValue2")+'</td></tr>';
	});
	$("#concentratorEditTbody").append(listTr);
}