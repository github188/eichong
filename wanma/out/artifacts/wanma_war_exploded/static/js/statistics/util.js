var chargingStatusData = {
	'0' : '离线',
	'1' : '',
	'2' : '待机',
	'3' : '充电',
	'8' : '预约',
	'9' : '在线升级',
	'10' : '操作中',
	'11' : '设置状态',
	'12' : '充电模式选择',
	'17' : '充电等待',
	'30' : '电桩初始化',
	'31' : '欠压',
	'32' : '过压',
	'33' : '过电流',
	'34' : '防雷器',
	'35' : '电表',
	'36' : '接触器',
	'37' : '绝缘检查',
	'38' : '急停'
}

function dealNullToInt(val) {
	var valTemp = val;
	if (valTemp != null && valTemp != '') {
		return parseInt(valTemp);
	}
	return 0;
}

function dealNullToFloat(val) {
	var valTemp = val;
	if (valTemp != null && valTemp != '') {
		return parseFloat(valTemp);
	}
	return 0.0;
}

function dealNullToNullStr(val) {
	var valTemp = val;
	if (!valTemp) {
		return '--';
	}
	return valTemp;
}


// var allIntervalIds =
// ['getHeadInfoCountIntervalId0','getHeadInfoCountIntervalId1','getErrorDataIntervalId','getHeadChargingInfoIntervalId','powerChargingIntervalId']
//
// function clearOtherIntervalIds(){
// for(var i=0;i<allIntervalIds.length;i++){
// var idTemp = eval(allIntervalIds[i]);
// if(idTemp){
// clearInterval(idTemp);
// }
//		
// }
// }
function clearOtherIntervalIds() {
	if (getHeadChargingInfoIntervalId) {
		clearInterval(getHeadChargingInfoIntervalId);
	}
}

function isIn(strArray,str){
	for(var i=0;i<strArray.length;i++){
		if(strArray[i] == str){
			return true;
		}
	}
	return false;
}