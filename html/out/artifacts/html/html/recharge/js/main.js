init();
function init() {
	var code=getSValue("code");

	if(!code){
		window.location.href = "error.html";
	}
	$.ajax({
		url : getRootPath() + "/itf/charge/valid",
		type : "get",
		data:{
			code:code
		},
		dataType : 'json',
		success : function(datas) {
			if (datas.status == 100) {
				$("#mainDiv").show();
			}else{
				window.location.href = "error.html";
			}
		}

	});
}

$("#submitButton").click(function(){
	
	
	debugger;
	var phone=$("#phone").val();
	var charge=$("#charge").val();
	var gift=$("#gift").val();
	var acountType=$("#acountType").val();
	var code=getSValue("code");
	var remark=$("#remark").val();
	remark=remark?remark:"...";
	
	
	
	if(gift==""){
		gift=0;
	}
	
	var type;
	if(acountType=="大账户"){
		type=1;
	}
	else if(acountType=="普通账户"){
		type=2;
	}
	//判断是否为空
	if( phone==""){
		parent.layer.alert("请输入充值账户！");
		return;
		
	}
	if( charge==""){
		parent.layer.alert("请输入充值金额！");
		return;
		
	}
	//校验大账户
	if(acountType=="大账户"){
		if(phone.length!=12){
			parent.layer.alert("该账户不是大账户！");
			return;
		}
		 if(parseFloat(charge)>1000000){
		parent.layer.alert("大账户充值金额不能高于100万！");
		return;
		}
	}
	//校验普通账户
	else  if(acountType=="普通账户"){
		
    	if(phone.length!=11){
			parent.layer.alert("该账户不是普通账户！");
			return;
		}
		 if(parseFloat(charge)>2000){

			parent.layer.alert("普通账户充值金额不能高于2000元！");
			return;
		}
    	
    	
	}
	//校验赠送金额
	if(parseFloat(gift)>100){
		parent.layer.alert("赠送金额过大");
		return;
	}
	$.ajax({
		url : getRootPath() + "/itf/charge/chargeGift",
		type : "get",
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:{
			phone:phone,
			charge:charge,
			gift:gift,
			remark:encodeURI(remark),
			code:code,
			type:type
			
		},
		dataType : 'json',
		success : function(datas) {
			if (datas.status == 100) {
				$("#phone").val("");
				$("#charge").val("");
				$("#gift").val("");
				$("#remark").val("");
				parent.layer.alert("操作成功！！！");
			}else{
				parent.layer.alert(datas.msg);
			}
		}
	});
});

function clearNoNum(obj){
   
   obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
   obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
   obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
   obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
   obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
   
   
   
}