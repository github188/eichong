/** 添加新行 */
function addNew() {
	// 设置list参数下标
	var listIndex = $(".bomList").length;
	var row_obj = '<tr class="bomList">'
			+ '<td><input class="textInput required rmIndex" name=".blHardwareNumber" type="text"></td>'
			+ '<td><input class="textInput required rmIndex" name=".blHardwareVersion" type="text"></td>'
			+ '<td><input class="textInput required rmIndex" name=".blFirmwareNumber" type="text"></td>'
			+ '<td><input class="textInput required rmIndex" name=".blFirmwareVersion" type="text" ></td>'
			+ '<td><input class="textInput required rmIndex" name=".blFileMd5" type="text" minlength="32" maxlength="32" style="width:230px;"></td>'
			+ '<td><div style="width: 80px;margin-left: 10px;">'
			+ '<input class="required radio rmIndex" name="'
			+ listIndex
			+ '.blForceUpdate" value="1" type="radio">是&nbsp;'
			+ '<input class="required radio rmIndex" name="'
			+ listIndex
			+ '.blForceUpdate" value="0" type="radio" checked>否</div></td>'
			+ '<td><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="removeTr(this)">删除</button></div></div></td></tr>';
	var rowBomDiv = navTab.getCurrentPanel().find("#row_bom");
	rowBomDiv.prepend(row_obj); // 插入行
}

/** *删除当前行** */
function removeTr(this_e) {
	var oldNum = $(".bomList").length;
	var rowBomDiv = navTab.getCurrentPanel().find("#row_bom");
	if (oldNum == 1) {
		alertMsg.warn("最后一行不可删除！");
		return;
	}
	var $thTr = $(this_e).parent().parent().parent().parent();
	$thTr.remove();
	//radio下标特殊处理
	var rowBomDiv = $("#row_bom");
	rowBomDiv.find(".radio").each(function(j) {
		var $th = $(this);
		var paramName = $th.attr("name");
		var replaceEnd = paramName.indexOf('.');
		var headIndex = paramName.substring(0, replaceEnd);
		$th.attr("name", paramName.replace(headIndex,headIndex+'99'));
	});
}

/**
 * 表单提交
 */
function doSubmit(){
	makeListIndex();// 重新设置行下标
}

/**
 * 重置list下标
 */
function makeListIndex(){
	var rowBomDiv = $("#row_bom");
	rowBomDiv.find("tr").each(function(i) {
		var $thisTr = $(this);
		// 重新设置list参数下标
		$thisTr.find(".rmIndex").each(function(j) {
			var $th = $(this);
			var paramName = $th.attr("name");
			var replaceEnd = paramName.indexOf('.');
			paramName = 'bomList['+i+']'+paramName.replace(paramName.substring(0, replaceEnd),'');
			$th.attr("name", paramName);
		});
	});
	$("#formDiv").submit();//提交表单
}