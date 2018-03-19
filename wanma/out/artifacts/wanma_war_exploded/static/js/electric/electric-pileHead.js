/**添加新行*/  
function addNew(){
	var elpiPowernumberDiv = navTab.getCurrentPanel().find("#elpiPowernumber");
    var oldNum = parseInt(elpiPowernumberDiv.val());
    elpiPowernumberDiv.val(oldNum+1);
	//设置list参数下标
    var listIndex = oldNum
    var row_obj = '<tr><td><input class="textInput required" name="headList['+listIndex+'].ephNum" type="text"></td>'
			     +'<td><select class="required" style="width: 80px" name="headList['+listIndex+'].havaCarPlaceLock"><option value="0">无</option><option value="1">有</option></select></td>'
				 +'<td><select class="required" style="width: 80px" name="headList['+listIndex+'].haveRadar"><option value="0">无</option><option value="1">有</option></select></td>'
				 +'<td><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="removeTr(this)">删除</button></div></div></td></tr>';
    var rowHeadDiv = navTab.getCurrentPanel().find("#row_head");
    rowHeadDiv.prepend(row_obj); // 插入行 
}

/***删除当前行***/
function removeTr(this_e){
	var elpiPowernumberDiv = navTab.getCurrentPanel().find("#elpiPowernumber");
	var rowHeadDiv = navTab.getCurrentPanel().find("#row_head");
    var oldNum = parseInt(elpiPowernumberDiv.val());
    if(oldNum == 1){
    	alertMsg.warn("最后一行不可删除！");
    	return;
    }
    var $thTr = $(this_e).parent().parent().parent().parent();
	$thTr.remove();
	var newNum = oldNum-1;
    elpiPowernumberDiv.val(newNum);
    //重新设置行下标
	rowHeadDiv.find("tr").each(function(i){
		var $th = $(this);
		$th.attr("index-data",newNum-1-i);
	});
    //重新设置list参数下标
    rowHeadDiv.find(".required").each(function(i){
		var $th = $(this);
		var listIndex = $th.parent().parent().attr("index-data");
		var paramName = $th.attr("name");
		var needReplaceStr = paramName.substring(9,10);
		$th.attr("name",paramName.replace(needReplaceStr,listIndex));
	});
}