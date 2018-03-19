initTable("","typeSpanListPage",typeSpanListUrl,typeSpanListCallback);
function typeSpanListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td><input type="checkbox" name="ids" class="selectedBox" value="'+data[i].pk_TypeSpanId+'"/></td><td>'
				+(i+1+(pageNum-1)*20)+'</td><td>'
				+data[i].ts_TypeSpan+'</td><td>'
				+data[i].ts_ModelName+'</td><td>'
				+data[i].ts_FactTag+'</td><td>'
				+data[i].ts_FileName+'</td><td>'
				+data[i].ts_ProductNumber+'</td><td>'
				+data[i].ts_Remarks+'</td><td><span class="typeSpanDetail" relId="'+data[i].pk_TypeSpanId+'">详细</span>'
				+'&nbsp;&nbsp;<span class="typeSpanEdit" relId="'+data[i].pk_TypeSpanId+'">编辑</span>'
				+'</td></tr>';
	}
	$("#typeSpanListTbody").html(listTr);
}
//产品型号新增
$("body").on("click","#typeSpanAdd",function(){
	addNewRow("typeSpanAddTbody");
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["产品型号新增","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['980px', '440px'],//宽高 
		  content:$("#typeSpanAddContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
			typeSpanAddCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})
function typeSpanAddCallBack(){
	if(testArray(".validateBlock_typeSpanAdd",".typeSpanAddTipText")&&testBom()){
		console.log("产品型号新增");
		if(makeBomList("typeSpanAddTbody")){
			$("#typespanAddForm").ajaxSubmitForm();
		}
		layer.closeAll();
	}
}
//判断bom清单里面的内容是否为空
function testBom(){
	var list=$("#typeSpanAddTbody input[type='text']");
	var success = false;
	list.each(function(index){
		if(list[index].value==""){
			$(".typeSpanAddTipText").show().html("请填写bom清单");
			list[index].focus();
			success = false;
			return false;
		}
		else{
			$(".typeSpanAddTipText").hide().html("");
			success = true;
		}
	})
	return success;
}
//新增处理
$("#bomListAddBtn").off("click");
$("#bomListAddBtn").on("click",function(){
	addNewRow("typeSpanAddTbody");
})

function cleanRow(tableId){
	$("#"+tableId).html("");
}

function addNewRow(tableId,data){
	var indexList=$("#"+tableId+" tr").length;
	var disabled=indexList==0?'disabled="disabled"':"";
	var pkBomListId="";
	var blHardwareNumber="";
	var blHardwareVersion="";
	var blFirmwareNumber="";
	var blFirmwareVersion="";
	var blFileMd5="";
	var blForceUpdate="";
	//原有的行不能删除
	if(data){
		pkBomListId='<input class="bomClass" type="hidden" name="pkBomListId" value="'+data.pkBomListId+'" />';
		blHardwareNumber=data.blHardwareNumber;
		blHardwareVersion=data.blHardwareVersion;
		blFirmwareNumber=data.blFirmwareNumber;
		blFirmwareVersion=data.blFirmwareVersion;
		blFileMd5=data.blFileMd5;
		blForceUpdate=data.blForceUpdate;
		disabled='disabled="disabled"';
	}
	var html='<tr><td class="smallWidth">'+pkBomListId+'<input '+disabled+' type="checkbox" name="test" class="selAll1" value=""/></td>'
			+'<td><input class="bomClass" type="text" name="blHardwareNumber"  value="'+blHardwareNumber+'"/></td>'
			+'<td><input class="bomClass" type="text" name="blHardwareVersion"  value="'+blHardwareVersion+'" /></td>'
			+'<td><input class="bomClass" type="text" name="blFirmwareNumber"  value="'+blFirmwareNumber+'" /></td>'
			+'<td><input class="bomClass" type="text" name="blFirmwareVersion"  value="'+blFirmwareVersion+'" /></td>'
			+'<td><input class="bomClass" type="text" name="blFileMd5"  value="'+blFileMd5+'" /></td>'
			+'<td><input class="bomClass" type="radio" name="blForceUpdate_'+indexList+'"  value="1" '+(blForceUpdate==1?'checked="checked"':'')+' />是'
			+'<input  class="bomClass" type="radio" name="blForceUpdate_'+indexList+'"  value="0" '+(blForceUpdate==0?'checked="checked"':'')+'  style="margin-left: 6px;"/>否'
			+'</td></tr>';
	$("#"+tableId).append(html);
}

function makeBomList(tableId){
	if($("#"+tableId+" tr").length>0){
		$("#"+tableId+" tr").each(function(i) {
			var $thisTr = $(this);
			// 重新设置list参数下标
			$thisTr.find(".bomClass").each(function(j) {
				var $th = $(this);
				var paramName = $th.attr("name");
				paramName = paramName.indexOf('_')>0?paramName.substring(0,paramName.indexOf('_')):paramName;
				paramName = 'bomList['+i+'].'+paramName;
				$th.attr("name", paramName);
			});
		});
		return true;
	}else{
		layer.alert("BOM清单不能为空",{offset: '100px'});
		return false;
	}

}

$("#bomListDeleteBtn").off("click");
$("#bomListDeleteBtn").on("click",function(){
	bomListDeleteCallBack();
})
function bomListDeleteCallBack(){
	if($("#typeSpanAddTbody tr").length == 1) {
		$(".typeSpanAddTipText").html("第一行不能删除").show();
		return;
	}
	if($("#typeSpanAddTbody tr").length > 1) {
		if($("#typeSpanAddTbody tr").length == $("input[name='test']:checked").length) {
			$("#typeSpanAddTbody tr:not(:first)").remove();
			$(".typeSpanAddTipText").html("第一行不能删除").show();
		} else {
			$("input[name='test']:checked").each(function() { // 遍历选中的checkbox
				n = $(this).parents("tr").index() + 1; // 获取checkbox所在行的顺序，多加1行是因为标题
				$("table#typeSpanTable").find("tr:eq(" + n + ")").remove();
				$(".typeSpanAddTipText").html("").hide();
			});
		}

	}
	return true;
}
//产品型号编辑
$("body").on("click",".typeSpanEdit",function(){
	var relId=$(this).attr("relId");
	typespanDetail(relId,"_edit","typeSpanEditTbody");
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["产品型号编辑","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['980px', '440px'],//宽高 
		  content:$("#typeSpanEditContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
			typeSpanEditCallBack();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})

function typespanDetail(relId,suffix,tableId){
	$.ajax({
		type:"get",
		url:basePath + typespanDetailUrl+"?pkTypeSpanId="+relId,
		dataType:"json",
		success:function(req){
			if (req.status == 100) {
				var obj=req.data;
				$("#pkTypeSpanId"+suffix).val(obj.pkTypeSpanId);
				$("#tsTypeSpan"+suffix).val(obj.tsTypeSpan);
				$("#tsModelName"+suffix).val(obj.tsModelName);
				$("#tsFactTag"+suffix).val(obj.tsFactTag);
				$("#tsFileName"+suffix).val(obj.tsFileName);
				$("#tsProductNumber"+suffix).val(obj.tsProductNumber);
				$("#tsRemarks"+suffix).val(obj.tsRemarks);
				if(obj.bomList){
					cleanRow(tableId);
					var bomList=obj.bomList;
					for(var i=0;i<bomList.length;i++){
						addNewRow(tableId,bomList[i]);
					}
				}
				$("#typeSpanDetailTbody input").prop("readonly","readonly");
			}else if (req.status == 0){
				layer.closeAll();
				layer.alert(req.msg,{offset: '100px'});
			}
		}
	});
}

function typeSpanEditCallBack(){
	if(testArray(".validateBlock_typeSpanEdit",".typeSpanEditTipText")&&testBomEdit()){
		console.log("产品型号编辑");
		if(makeBomList("typeSpanEditTbody")){
			$("#typespanEditForm").ajaxSubmitForm();
		}
		layer.closeAll();
	}
}
//判断bom清单里面的内容是否为空
function testBomEdit(){
	var list=$("#typeSpanEditTbody input[type='text']");
	var success = false;
	list.each(function(index){
		if(list[index].value==""){
			$(".typeSpanEditTipText").show().html("请填写bom清单");
			list[index].focus();
			success = false;
			return false;
		}
		else{
			$(".typeSpanEditTipText").hide().html("");
			success = true;
		}
	})
	return success;
}
//新增处理
$("#bomListEditBtn").off("click");
$("#bomListEditBtn").on("click",function(){
	addNewRow("typeSpanEditTbody");
})

$("#bomListEditDeleteBtn").off("click");
$("#bomListEditDeleteBtn").on("click",function(){
	bomListEditDeleteCallBack();
})
function bomListEditDeleteCallBack(){
	if($("#typeSpanEditTbody tr").length == 1) {
		$(".typeSpanEditTipText").html("第一行不能删除").show();
		return;
	}
	if($("#typeSpanEditTbody tr").length > 1) {
		if($("#typeSpanEditTbody tr").length == $("input[name='test']:checked").length) {
			$("#typeSpanEditTbody tr:not(:first)").remove();
			$(".typeSpanEditTipText").html("第一行不能删除").show();
		} else {
			$("input[name='test']:checked").each(function() { // 遍历选中的checkbox
				n = $(this).parents("tr").index() + 1; // 获取checkbox所在行的顺序，多加1行是因为标题
				$("table#typeSpanEditTable").find("tr:eq(" + n + ")").remove();
				$(".typeSpanEditTipText").html("").hide();
			});
		}

	}
	return true;
}
//产品型号详细
$("body").on("click",".typeSpanDetail",function(){
	var relId=$(this).attr("relId");
	typespanDetail(relId,"_detail","typeSpanDetailTbody");
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: ["产品型号详细","font-size:12px"],
		  shadeClose:false,
		  closeBtn:2,
		  area: ['980px', '440px'],//宽高 
		  content:$("#typeSpanDetailContent"),
		  btn:["确定"],
		  btn1: function(index,layero){
		  	console.log("产品型号详细")
		  	layer.closeAll();
		  },
		  cancel: function(index,layero){
		  	layer.closeAll();
		  }
		});
})