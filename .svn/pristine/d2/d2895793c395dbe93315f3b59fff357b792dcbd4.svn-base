powerRateListSearch();
function powerRateListSearch(){
	initTable("powerRateListForm","powerRateListPage",powerRateListUrl,powerRateListCallback);
}

function powerRateListCallback(req){
	var data=req.data;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="provinceId" class="selectedBox" value="'+data[i].PROVINCE_ID+'"/></td><td>'
				+(i+1)+'</td><td>'
				+data[i].PROVINCE_NAME+'</td><td>'
				+data[i].Tip_Electricity+'</td><td>'
				+data[i].Peak_Electricity+'</td><td>'
				+data[i].Flat_Electricity+'</td><td>'
				+data[i].Valley_Electricity+'</td><td>'
				+'<span class="powerRateEdit" relId="'+data[i].PROVINCE_ID+'">编辑</span></td></tr>';
	}
	$("#powerRateListTbody").html(listTr);
}
loadProvince("powerRateList");
//编辑弹框
$("body").on("click",".powerRateEdit",function(){
	var relId=$(this).attr("relId");
	powerRateDetail(relId);
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["省份电费编辑","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['610px', '290px'],//宽高 
		  content:$("#powerRateEdit"),
		  btn:["确定"],
		  yes: function(index,layero){
		  	powerRateEditCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})

function powerRateDetail(relId){
	$.ajax({
		type:"get",
		url:basePath + powerRateDetailUrl+"?provinceId="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			$("#provinceId").val(obj.PROVINCE_ID);
			$("#PROVINCE_NAME").html(obj.PROVINCE_NAME);
			$("#Tip_Electricity").val(obj.Tip_Electricity);
			$("#Peak_Electricity").val(obj.Peak_Electricity);
			$("#Flat_Electricity").val(obj.Flat_Electricity);
			$("#Valley_Electricity").val(obj.Valley_Electricity);
		}
	});
}

function powerRateEditCallBack(){
	if(testNull("#Tip_Electricity",".powerRateEditTipText")&&isNumeric("#Tip_Electricity",".powerRateEditTipText")&&maxValue("#Tip_Electricity",".powerRateEditTipText")
		&&testNull("#Peak_Electricity",".powerRateEditTipText")&&isNumeric("#Peak_Electricity",".powerRateEditTipText")&&maxValue("#Peak_Electricity",".powerRateEditTipText")
		&&testNull("#Flat_Electricity",".powerRateEditTipText")&&isNumeric("#Flat_Electricity",".powerRateEditTipText")&&maxValue("#Flat_Electricity",".powerRateEditTipText")
		&&testNull("#Valley_Electricity",".powerRateEditTipText")&&isNumeric("#Valley_Electricity",".powerRateEditTipText")&&maxValue("#Valley_Electricity",".powerRateEditTipText")
	){
		//保存逻辑操作
		$("#powerRateEditForm").ajaxSubmitForm("powerRateEditForm");
		layer.closeAll();
		return true;
	}else{
		return false;
	}
}
