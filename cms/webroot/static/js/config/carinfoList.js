$(function(){
	initSelects("16,16,17,17,5,5","addCarinfoBatteryType,editCarinfoBatteryType,addCarinfoChargingMode,editCarinfoChargingMode,"
			+"addCarinfoPowerInterface,editCarinfoPowerInterface");
	initSelect(carCompanyDictList,"addCarinfoCompanyId,editCarinfoCompanyId");
});	
//tab样式
$("body").on("click",".tabCarInfo>li",function(){
	var index=$(this).index();
	$(this).addClass("tabStyle").siblings().removeClass("tabStyle");
	$(".tabCarType").eq(index).show().siblings().hide();
})
//车型和品牌加载  需要判断此时是按照哪个类型 （传不同的url和callBack）
carinfoListSearch();
function carinfoListSearch(){
	initTable("carinfoListForm","carCompanyListPage",carinfoListUrl,carinfoListCallback);
}
//车型加载
function carinfoListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pkCarinfo+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].carinfoStylename+'</td><td>'
				+data[i].extValue1+'</td><td>'
				+data[i].extValue2+'</td><td>'
				+data[i].carinfoBatterycapacity+'</td><td>'
				+data[i].extValue3+'</td><td>'
				+data[i].extValue4+'</td><td>'
				+new Date(data[i].carinfoCreatedate).format("yyyy-MM-dd hh:mm:ss")+'</td>'
				+'<td><span class="carInfoEdit" relId="'+data[i].pkCarinfo+'">编辑</span>'
				+'</td></tr>';
	}
	$("#carinfoListTbody").html(listTr);
}
//品牌加载
$("body").on("click",".carCompany",function(){
	initTable("carCompanyListForm","carCompanyListPage",carCompanyListUrl,carCompanyListCallback);
})
carCompanyListSearch();
function carCompanyListSearch(){
	initTable("carCompanyListForm","carCompanyListPage",carCompanyListUrl,carCompanyListCallback);
}
function carCompanyListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var typeListTr="";
	for(var i=0;i<data.length;i++){
		typeListTr+='<tr><td><input type="checkbox" name="pkCarcompany" class="selectedBox" value="'+data[i].pkCarcompany+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].carcompanyName+'</td><td>'
				+new Date(data[i].carcompanyCreateDate).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+data[i].carcompanyRemark+'</td><td><span class="carCompanyEdit" relId="'+data[i].pkCarcompany+'">编辑</span>'
				+'</td></tr>';
	}
	$("#carCompanyListTbody").html(typeListTr);
}

//车型新增
$("body").on("click","#carinfoAdd",function(){
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["电动车车型新增","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['540px', '340px'],//宽高 
		  content:$("#carInfoAddContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
		  	saveCarInfoAddCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
function saveCarInfoAddCallBack(){
	if(testArray(".validateBlockAdd",".addCarInfoTipText")&&isModelName("#modelNameAdd",".addCarInfoTipText")
		&&isNumeric("#batteryCapacity",".addCarInfoTipText")&&isNumeric("#maxJourney",".addCarInfoTipText")
		&&isNumeric("#chargeTime",".addCarInfoTipText")
	){
//		保存逻辑操作
		$("#carinfoAddForm").ajaxSubmitForm();
		layer.closeAll();
		return true;
	}else{
		return false;
	}
}
//车型编辑
$("body").on("click",".carInfoEdit",function(){
	var relId=$(this).attr("relId");
	carInfoDetail(relId);
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["电动车车型编辑","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['540px', '340px'],//宽高 
		  content:$("#carInfoEditContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
		  	saveCarInfoEditCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
function saveCarInfoEditCallBack(){
	if(testArray(".validateBlock",".editCarInfoTipText")&&isModelName("#carinfoStylename",".editCarInfoTipText")
		&&isNumeric("#carinfoBatterycapacity",".editCarInfoTipText")&&isNumeric("#carinfoMaxodometer",".editCarInfoTipText")
		&&isModelName("#carinfoChargingTime",".editCarInfoTipText")
	){
		//保存逻辑操作
		$("#carinfoEditForm").ajaxSubmitForm();
		layer.closeAll();
		return true;
	}else{
		return false;
	}
}

function carInfoDetail(relId){
	$.ajax({
		type:"get",
		url:basePath + carinfoDetailUrl+"?pkCarinfo="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			$("#pkCarinfo").val(obj.pkCarinfo);
			$("#carinfoStylename").val(obj.carinfoStylename);
			$("#carinfoBatterycapacity").val(obj.carinfoBatterycapacity);
			$("#carinfoMaxodometer").val(obj.carinfoMaxodometer);
			$("#carinfoChargingTime").val(obj.carinfoChargingTime);
			$("#editCarinfoBatteryType").val(obj.carinfoBatteryType);
			$("#editCarinfoChargingMode").val(obj.carinfoChargingMode);
			$("#editCarinfoPowerInterface").val(obj.carinfoPowerInterface);
			$("#editCarinfoCompanyId").val(obj.carinfoCompanyId);
		}
	});
}
//品牌新增
$("body").on("click","#carCompanyAdd",function(){
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["电动车品牌新增","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['310px', '250px'],//宽高 
		  content:$("#carCompanyAddContent"),
		  btn:["确定"],
		  yes: function(index,layero){
		  	   saveBrandAddCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
//品牌新增的确定保存回调
function saveBrandAddCallBack(){
	if(testNull("#addCarCompany",".addCarCompanyTipText")){
  		if(isBrandName("#addCarCompany",".addCarCompanyTipText")){
  			//正则通过之后的确定方法开始
  			$("#carcompanyAddForm").ajaxSubmitForm();
  			layer.closeAll();
  			return true;
  		}
	}else{
		return false;
	}
}
//品牌编辑
$("body").on("click",".carCompanyEdit",function(){
	var relId=$(this).attr("relId");
	carcompanyDetail(relId);
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
//		  id:1,
		  title: ["电动车品牌编辑","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['310px', '250px'],//宽高 
		  content:$("#carCompanyEditContent"),
		  btn:["确定"],
		  yes: function(index,layero){
		  	saveBrandEditCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
//品牌编辑确定回调
function saveBrandEditCallBack(){
	if(testNull("#carcompanyName",".editCarCompanyTipText")){
  		if(isBrandName("#carcompanyName",".editCarCompanyTipText")){
  			//正则通过之后的确定方法开始
  			$("#carcompanyEditForm").ajaxSubmitForm();
  			layer.closeAll();
  			return true;
  		}
	}else{
		return false;
	}
}

function carcompanyDetail(relId){
	$.ajax({
		type:"get",
		url:basePath + carcompanyDetailUrl+"?pkCarcompany="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			$("#pkCarcompany").val(obj.pkCarcompany);
			$("#carcompanyName").val(obj.carcompanyName);
			$("#carcompanyRemark").val(obj.carcompanyRemark);
		}
	});
}