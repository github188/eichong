function makeRateInfoWindow(this_e) {
	var $this = $(this_e);
	var rateTimeArray = JSON.parse($this.attr('data-rateTime')).data;
	var chReJPrice = $this.attr('data-rateJ');
	var chReFPrice = $this.attr('data-rateF');
	var chRePPrice = $this.attr('data-rateP');
	var chReGPrice = $this.attr('data-rateG');
	var chorServicemoney = $this.attr('data-rateS');
	var flag = $this.attr('data-flag');
	var fengzhiHtml = '';
	if (rateTimeArray != null && rateTimeArray.length > 0) {
		for (var i = 0; i < rateTimeArray.length; i++) {
			var timeData = rateTimeArray[i]
			var st = parseInt(timeData.st);
			var et = parseInt(timeData.et);
			var timeSt = parseInt(st / 60);
			var stMin = st - timeSt * 60;
			var timeEt = parseInt(et / 60)
			var etMin = et - timeEt * 60;
			if ('1' == timeData.mark) {
				fengzhiHtml += "<li class='Jian'><span class='JFPG_BOX_l'>"
						+ timeSt + ":" + (stMin < 10 ? "0" + stMin : stMin)
						+ "--" + timeEt + ":"
						+ (etMin < 10 ? "0" + etMin : etMin) + "</span>"
						+ "<span class='JFPG_BOX_c'>尖</span>"
						+ "<span class='JFPG_BOX_r'>" + chReJPrice
						+ "元/度</span></li>";

			}
			if ('2' == timeData.mark) {
				fengzhiHtml += "<li class='Feng'><span class='JFPG_BOX_l'>"
						+ timeSt + ":" + (stMin < 10 ? "0" + stMin : stMin)
						+ "--" + timeEt + ":"
						+ (etMin < 10 ? "0" + etMin : etMin) + "</span>"
						+ "<span class='JFPG_BOX_c'>峰</span>"
						+ "<span class='JFPG_BOX_r'>" + chReFPrice
						+ "元/度</span></li>";
			}
			if ('3' == timeData.mark) {
				fengzhiHtml += "<li class='Ping'><span class='JFPG_BOX_l'>"
						+ timeSt + ":" + (stMin < 10 ? "0" + stMin : stMin)
						+ "--" + timeEt + ":"
						+ (etMin < 10 ? "0" + etMin : etMin) + "</span>"
						+ "<span class='JFPG_BOX_c'>平</span>"
						+ "<span class='JFPG_BOX_r'>" + chRePPrice
						+ "元/度</span></li>";
			}
			if ('4' == timeData.mark) {
				fengzhiHtml += "<li class='Gu'><span class='JFPG_BOX_l'>"
						+ timeSt + ":" + (stMin < 10 ? "0" + stMin : stMin)
						+ "--" + timeEt + ":"
						+ (etMin < 10 ? "0" + etMin : etMin) + "</span>"
						+ "<span class='JFPG_BOX_c'>谷</span>"
						+ "<span class='JFPG_BOX_r'>" + chReGPrice
						+ "元/度</span></li>";
			}
		}
		//尖峰平谷下面增加服务费
		fengzhiHtml +="<li class='Service'>"
			+"<span id='ServiceTitle' >服务费</span>"
			+"<span id='ServiceMoney'>"+chorServicemoney
			+"元/度</span></li>";
		//flag判断不同页面打开费率
		if(flag==3){
			$("#JFPG_BOX_DIV_Business").html(fengzhiHtml);
			$("#JFPG_BOX_DIV_BusinessP").css({
				"top" : parseInt($this.attr('data-index')) * 20 + "px",
				"z-index" : 5000
			}).show();
		}else if(flag==1){
			$("#JFPG_BOX_DIV").html(fengzhiHtml);
			$("#JFPG_BOX_DIV_P").css({
				"top" : parseInt($this.attr('data-index')) * 20 + "px",
				"z-index" : 5000
			}).show();
		}else if(flag==2){
			$("#JFPG_BOX_DIV_Person").html(fengzhiHtml);
			$("#JFPG_BOX_DIV_PersonP").css({
				"top" : parseInt($this.attr('data-index')) * 20 + "px",
				"z-index" : 5000
			}).show();
		}
		


	}
}
function closeRateWindow() {
	$("#JFPG_BOX_DIV_P").hide();
}
function closePersonChargeWindow() {
	$("#JFPG_BOX_DIV_PersonP").hide();
}
function closeBusinessChargeWindow() {
	$("#JFPG_BOX_DIV_BusinessP").hide();
}
