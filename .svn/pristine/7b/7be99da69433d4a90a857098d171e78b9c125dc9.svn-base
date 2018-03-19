serviceLimitListSearch();
function serviceLimitListSearch(){
	initTable("serviceLimitListForm","serviceLimitListPage",serviceLimitListUrl,serviceLimitListCallback);
}

function serviceLimitListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].cityId+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].cityName+'</td><td>'
				+data[i].provinceId+'</td><td>'
				+data[i].serviceLimit+'</td><td>'
				+'<span class="serviceLimitEdit" relId="'+data[i].cityId+'">编辑</span></td></tr>';
	}
	$("#serviceLimitListTbody").html(listTr);
}

loadProvince("serviceLimitList");

//编辑

$("body").on("click",".serviceLimitEdit",function(){
	var relId=$(this).attr("relId");
	serviceLimitDetail(relId);
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
//		  id:1,
		  title: ["服务费上限编辑","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['310px', '260px'],//宽高 
		  content:$("#serviceLimitEdit"),
		  btn:["确定"],
		  yes: function(index,layero){
		  	serviceLimitEditCallBack();
//		  	console.log("服务费上限编辑")
//		  	layer.closeAll();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
function serviceLimitEditCallBack(){
	var serviceLimitValue=$("#serviceLimit").val();
	if(testNull("#serviceLimit",".serviceLimitTextTip")&&isNumeric("#serviceLimit",".serviceLimitTextTip")){
		$("#serviceLimitEditForm").ajaxSubmitForm();
		console.log("服务费编辑成功")
		layer.closeAll();
		return true;
	}
}

function serviceLimitDetail(relId){
	$.ajax({
		type:"get",
		url:basePath + serviceLimitDetailUrl+"?cityId="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data[0];
			$("#cityId").val(obj.cityId);
			$("#cityName").html(obj.cityName);
			$("#serviceLimit").val(obj.serviceLimit);
		}
	});
}
