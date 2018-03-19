$('.showBtn').on("click", function() {
	$('.checkList').toggle();
})
//根据check的属性控制table显示
function showTableInfo(info) {
	$('.checkList').find('input[data-flag=' + info + ']').attr('checked', true);
	$('.checkList').find('input[data-flag=' + info + ']').parent().addClass('checkboxActiveStyle').removeClass('checkboxStyle');
	$('.table').find('th[class=' + info + ']').show();
	$('.table').find('td[class=' + info + ']').show();
}
//根据check的属性控制table隐藏
function hideTableInfo(info) {
	$('.checkList').find('input[data-flag=' + info + ']').attr('checked', false);
	$('.checkList').find('input[data-flag=' + info + ']').parent().addClass('checkboxStyle').removeClass('checkboxActiveStyle');
	$('.table').find('th[class=' + info + ']').hide();
	$('.table').find('td[class=' + info + ']').hide();
}
//初始化获取html 页面check的属性值,当获取本地的localstorage为空时,判断一次
function getInputDataFlagsCheckedProp(data) {
	return checkFlag = $('.checkList').find('input[data-flag=' + data + ']').prop('checked');
}
function toGetValue(objStr,objValue){
	if(objValue == 1) {
		showTableInfo(objStr);
	} else if(objValue == null) {
		getInputDataFlagsCheckedProp(objStr);
		if(checkFlag == true) {
			showTableInfo(objStr);
		} else {
			hideTableInfo(objStr);
		}
	} else {
		hideTableInfo(objStr);
	}
}
//这里是页面首次加载，判断字段在不在，如果字段不在默认选中，如果有，按照字段展示
function toGetLocalStorageInfo(arr) {
	for(var i=0;i<arr.length;i++){
		toGetValue(arr[i],window.localStorage.getItem(arr[i]));
	}
}
//对于复选框,进行点击事件,改变状态值
$('.checkList').find('input').off("click").on("click", function() {
	var $this = $(this);
	var dataFlag = $this.attr('data-flag');
	var dataState = $this.attr('data-state');
	if($this.prop('checked') == true) {
		$this.parent().addClass('checkboxActiveStyle').removeClass('checkboxStyle');
		$this.attr('data-state', 1);
		dataState = $this.attr('data-state');
		window.localStorage.setItem(dataFlag, dataState);
		$('.table').find('.' + dataFlag).show();
	} else {
		$this.parent().addClass('checkboxStyle').removeClass('checkboxActiveStyle');
		$this.attr('data-state', 0);
		dataState = $this.attr('data-state');
		window.localStorage.setItem(dataFlag, dataState);
		$('.table').find('.' + dataFlag).hide();
	}
})