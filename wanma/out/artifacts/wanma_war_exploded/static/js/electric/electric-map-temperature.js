function temperature(data) {
	$("#hot").fadeTo(0, 0);// 初始透明度为0；
	var inputvalue = null;
	var bp_lowest_temperature = 0;
	var bp_highest_temperature = 0;
	if(data.monitorData.bp_lowest_temperature){
		bp_lowest_temperature = data.monitorData.bp_lowest_temperature;
	}
	if(data.monitorData.bp_highest_temperature){
		bp_highest_temperature = data.monitorData.bp_highest_temperature;
	}
	inputvalue = (parseFloat(bp_lowest_temperature) + parseFloat(bp_highest_temperature)) / 2;
	var inputnum = parseInt(inputvalue);

	$("#Hgheader").html(inputvalue + "℃");
	if (inputnum >= 100) {
		inputnum = 100;
		$('#num').val(100)
		$("#Hgheader").html(100 + "℃");
	}
	var Columnhe = inputnum / 100;
	$("#Hg").animate({
		height : inputnum
	}, 'show');
	$("#hot").fadeTo('slow', Columnhe);

}