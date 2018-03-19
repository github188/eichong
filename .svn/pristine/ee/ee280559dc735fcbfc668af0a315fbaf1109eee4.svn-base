$(function(){
	$.ajax({
		type:"get",
		url:basePath + listChargeDetailUrl+"?format=true&pkChargingorder="+relId,
		dataType:"json",
		success:function(req){
			var obj=req.data;
			$("#chorCode").html(obj.chorCode);
			$("#chorPilenumber").html(obj.chorPilenumber);
			$("#electricName").html(obj.extValue1);
			$("#userPhone").html(obj.userPhone);
			$("#chargeType").html(obj.extValue2);
			$("#ownerShip").html(obj.ownerShip);
			$("#chorMoeny").html(obj.chorMoeny);
			$("#chorQuantityelectricity").html(obj.chorQuantityelectricity);
			$("#chorChargemoney").html(obj.chorChargemoney);
			$("#chorServicemoney").html(obj.chorServicemoney);
			$("#couponMoney").html(obj.couponMoney);
			$("#chorTimequantum").html(obj.chorTimequantum);
			$("#chargingstatusName").html(obj.chargingstatusName);
			$("#puhiInvoiceStatus").html(obj.puhiInvoiceStatus);
			$("#rateValue").html(obj.elPiRateInformationId);
			var rainQuantumdate=JSON.parse(obj.chReQuantumDate);
			var typeArray=new Array("","尖","峰","平","谷");
			var feeArray=new Array("",obj.chReJPrice,obj.chReFPrice,obj.chRePPrice,obj.chReGPrice);
			var html="";
			$.each(rainQuantumdate.data,function(i,item){
				var data={
						sh:parseInt(item.st/60),
						sm:item.st%60<10?"0"+(item.st%60):item.st%60,
						eh:parseInt(item.et/60),
						em:item.et%60<10?"0"+(item.et%60):item.et%60,
						mark:item.mark
				};
				html+='<div class="rateMainBlock">'
					+'<div class="width30">'+data.sh+':'+data.sm+'~'+data.eh+':'+data.em+'</div>'
					+'<div class="width30">'+typeArray[data.mark]+'</div>'
					+'<div class="width30"><span>'+feeArray[data.mark]+'</span>元/度</div>'
					+'</div>'
			})
			$("#rainMain").html(html);
			$("#chReServiceCharge").html(obj.chReServiceCharge);
		}
	});
});

//返回
$("body").off("click","#goBack").on("click","#goBack",function(){
	goBack();
})
$("body").on("click","#rateValue",function(){
	layer.closeAll();
	layer.open({
		  type: 1,
		  resize:false,
		  move:false, 
		  offset: '100px',
		  title: false,
		  shadeClose:true,
		  closeBtn:0,
		  area: ['310px', '186px'],//宽高 
		  content:$("#rateTable")
		});
})