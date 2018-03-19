initTable("","pileMakerListPage",pileMakerListUrl,pileMakerListCallback);
function pileMakerListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkCarmaker+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].makerName+'</td><td>'
				+data[i].makerRemark+'</td><td><span class="pileMakerEdit" relId="'+data[i].pkCarmaker+'">编辑</span>'
				+'</td></tr>';
	}
	$("#pileMakerListTbody").html(listTr);
}
//新增
$("body").on("click","#pileMakerAdd",function(){
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false,
		  offset: '100px',
		  title: ["电桩制造商新增","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['310px', '268px'],//宽高 
		  content:$("#pileMakerAddContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
		  	pileMakerAddCallBack();
		  },
		  cancal: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
//新增回调
function pileMakerAddCallBack(){
	if(
		testNull("#carMarkerNameAdd",".pileMakerAddTextTip")&&isModelName("#carMarkerNameAdd",".pileMakerAddTextTip")
		&&testNull("#carMarkerTagAdd",".pileMakerAddTextTip")&&isInteger("#carMarkerTagAdd",".pileMakerAddTextTip")
	){
//		关闭按钮回调
		$("#pilemakerAddForm").ajaxSubmitForm();
		layer.closeAll();
		return true;
	}else{
		return false;
	}
}
//编辑
$("body").on("click",".pileMakerEdit",function(){
	var relId=$(this).attr("relId");
	pileMakerDetail(relId);
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false,
		  offset: '100px',
		  title: ["电桩制造商编辑","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['310px', '250px'],//宽高 
		  content:$("#pileMakerEditContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
		  	pileMakerEditCallBack();
		  },
		  cancal: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
function pileMakerEditCallBack(){
	if(
			testNull("#makerName",".pileMakerEditTextTip")&&isModelName("#makerName",".pileMakerEditTextTip")
			&&testNull("#makerRemark",".pileMakerEditTextTip")&&isInteger("#makerRemark",".pileMakerEditTextTip")
	){
//		关闭按钮回调
		$("#pilemakerEditForm").ajaxSubmitForm();
		layer.closeAll();
		return true;
	}else{
		return false;
	}
}
function pileMakerDetail(relId){
	$.ajax({
		type:"get",
		url:basePath + pilemakerDetailUrl+"?pkCarmaker="+relId,
		dataType:"json",
		success:function(req){
			console.log(JSON.stringify(req));
			if (req.status == 100) {
				var obj=req.data;
				$("#pkCarmaker").val(obj.pkCarmaker);
				$("#makerName").val(obj.makerName);
				$("#makerRemark").val(obj.makerRemark);
			}else if (req.status == 0){
				layer.closeAll();
				layer.alert(req.msg,{offset: '100px'});
			}
		}
	});
}