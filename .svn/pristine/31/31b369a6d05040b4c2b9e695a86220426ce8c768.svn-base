electricSearch();
function electricSearch(){
	initTable("electricForm","electricPage",electricListUrl,electricCallback);
}

function electricCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkElectricpile+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].elpiElectricpilecode+'</td><td>'
				+data[i].elpiElectricpilename+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].extValue2+'</td><td>'
				+data[i].extValue3+'</td><td>'
				+data[i].elPiUserName+'</td>'
				+'<td><span class="electricDetail" relId="'+data[i].pkElectricpile+'">详细</span>'
				+'&nbsp;&nbsp;<span class="electricEdit" relId="'+data[i].pkElectricpile+'">编辑</span>'
				+'</td></tr>';
	}
	$("#electricTbody").html(listTr);
}
initSelects("4,12","elpiPowersize,elpiState");
//电桩导入
$("#fileInput").change(function(){
	$("#fileText").html($("#fileInput").val());
})
$("#insertBtn").on("click",function(){
	layer.closeAll();
  	layer.open({
	  type: 1,
	  offset: '100px',
	  title: ["电桩导入","font-size:12px"],
	  shadeClose:false,
	  closeBtn:2,
	  area: ['310px', '260px'],//宽高 
	  content:$("#fileInputContainer"),
	  btn:["确定"],
	  yes: function(index,layero){
		$("#electricImportForm").iframeSubmitForm();
	  	layer.closeAll();
	  },
	  cancel: function(index,layero){
	  	layer.closeAll();
	  }
	});
})
//点击进入电桩详细
$("body").off("click",".electricDetail").on("click",".electricDetail",function(){
	relId=$(this).attr("relId");
	loadpage(electricDetailPage);
});
//点击进入电桩编辑
$("body").off("click",".electricEdit").on("click",".electricEdit",function(){
	relId=$(this).attr("relId");
	loadpage(electricEditPage);
})

$("body").off("click","#downloadXlsx").on("click","#downloadXlsx",function(){
	window.location.href=$(this).attr("href");
});