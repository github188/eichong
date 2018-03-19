//checkUserLogin();
var user=getUserInfo();
var userId=user?user.pkUserinfo:"";
var userAccount=user?user.usinPhone:"";
var userAccountbalance =user?user.usinAccountbalance:"";
$("#balance").html(userAccountbalance);
//一进页面今开始执行，所有的记录======================================
//var userId=54;
//var userId=getUrlParam("userId");

var dataObject={
	userId:userId
}
//$("#waitLoad").show();
loadStart();
function consumeRecord(){
	var appDate = $("#appDate").val();
	var appDate2=$("#appDate2").val();
	if(!compareDate(parseDate(appDate),parseDate(appDate2))){
		$("#myModal").modal('show');
		$("#modalTip").html("开始日期不能大于结束日期");
		return;
	}
	dataObject.starttime=appDate+" 00:00:00";
	dataObject.endtime=appDate2+" 23:59:59";
	dataObject.type=$("#typeChoice").val();
	var url="/itf/userinfo/getMyWallet";
	loadStart();
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:dataObject,
		success:function(req){
//			$("#waitLoad").hide();
			loadStop();
//			alert(JSON.stringify(req));
			if(req.status==100){
				var data=req.data;
//				var balance=data.balance;
				//alert(balance)
//				$("#balance").html(balance);
				//alert(JSON.stringify(data));
				var consumeRecord=data.consumeRecord;
	//			alert(consumeRecord.length);
//				if(consumeRecord.length==0){
//					console.log("没有消费记录");
//				}
				var html="";
				for(var i=0;i<consumeRecord.length;i++){
					var recordTitle=consumeRecord[i].recordTitle;
					var recordContent=consumeRecord[i].recordContent;
					var recordTime=consumeRecord[i].recordTime;
					var recordMoney=consumeRecord[i].recordMoney;
	//				alert(recordTitle)
					var str="";
					var sign="";
					if(recordTitle==1){
						str="充电消费";
						sign="-";
					}else if(recordTitle==2){
						str="预约消费";
						sign="-";
					}else if(recordTitle==3){
						str="购物消费";
						sign="-";
					}else if(recordTitle==4){
						str="充值";
						sign="+";
					}
					html+='<div class="col-xs-4 average" class="kinds">'+str
					+'</div><div class="col-xs-6 average" class="datas">'+recordTime
					+'</div><div class="col-xs-2 average" class="money">'+sign+recordMoney
					+'</div>';
	//				alert(str)
	//				alert(recordTitle);
				}
				$("#filterList").html(html);
			}
			
		}
	});
}

//根据select筛选===========================================================================================================
$(function(){
	var d2=new Date().format("yyyy-MM-dd");
	var d=new Date(new Date().getTime()-30*24*3600*1000).format("yyyy-MM-dd");
	document.getElementById("appDate").value=d;
	document.getElementById("appDate2").value=d2;
    var currYear = (new Date()).getFullYear();
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.setting = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		onSelect:consumeRecord,
        startYear: currYear, //开始年份
        endYear: currYear + 10 //结束年份
	};
  	$("#appDate").mobiscroll($.extend(opt['date'], opt['setting']));
    $("#appDate2").mobiscroll($.extend(opt['date'], opt['setting']));
    consumeRecord();
	$("body").on("change","#typeChoice",function(){
		consumeRecord();
	});
})

function toMyPage(){
	toPage("myPage.html");
}


function compareDate(d1,d2){
	return d2.getTime()-d1.getTime()>=0;
}
//var checkPage=getUrlParam("checkPage");
var checkPage=getSValue("checkPage");
function getBack(){
	if(checkPage=="map"){
		toPage("map_index.html");
	}else if(checkPage=="orderList"){
		toPage("order_appointment.html");
	}
	
}
//去充值页面=========================================================================================
function toWalletCharge(){
	toPage("myWalletCharge.html");
}