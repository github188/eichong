//事件监听浏览器兼容===========================================================================
function attEvent(obj,type,fn){
	if(obj.addEventListener){
		obj.addEventListener(type,fn,false)
		
		}
	else if(obj.attachEvent){
		obj.attachEvent('on'+type,fn)
		
		}
	
	}
function removeEvent(obj,type,fn){
	if(obj.removeEventListener){
		obj.removeEventListener(type,fn,false)
		}
	else if(obj.detachEvent){
		obj.detachEvent('on'+type,fn)
		}
	
	}	
function getTarget(ev){
	if(ev.target){
	return ev.target
		}
	else if(window.event.srcElement){
	return window.event.srcElement
		}
}

//判断=======================================================================
attEvent(window,'load',function(){
		attEvent(paymentPassword,'blur',paymentPasswordTest);//支付密码
		attEvent(rePaymentPassword,'blur',rePaymentPasswordTest);//确认支付密码
})
//设置支付密码=========
function paymentPasswordTest(){
	var paymentPassword=document.getElementById("paymentPassword");
	var paymentPasswordValue=paymentPassword.value;
	var reg=/^[0-9]{6}$/;//支付密码验证
	if(!paymentPasswordValue){
		paymentPassword.focus();
		mui.toast("请输入支付密码");
		return false;
	}
	if(!reg.test(paymentPasswordValue)){
			paymentPassword.focus();
			mui.toast("支付密码格式为6位数字,请重新填写");
			return false;
		}
	return true;	
}
//确认支付密码========
function rePaymentPasswordTest(){
	var rePaymentPassword=document.getElementById("rePaymentPassword");
	var rePaymentPasswordValue=rePaymentPassword.value;
	var reg2=/^[0-9]{6}$/;//支付密码验证
	if(!rePaymentPasswordValue){
		rePaymentPassword.focus();
		mui.toast("请确认支付密码");
		return false;
	}
	if(!reg2.test(rePaymentPasswordValue)){
			rePaymentPassword.focus();
			mui.toast("确认支付密码格式为6位数字,请重新填写");
			return false;
		}
	return true;	
}
//点击确定之后=================
function checkPaymentPassword(){
	if(paymentPasswordTest()&&rePaymentPasswordTest()){
		$("#payInfoModal").modal('show');
		var paymentPasswordValue=paymentPassword.value;
		var rePaymentPasswordValue=rePaymentPassword.value;
		if(paymentPasswordValue==rePaymentPasswordValue){
			var url="/itf/userinfo/setPayPwd";
			$.ajax({
				type:"get",
				url:getRootPath()+url,
				async:true,
				dataType:"json",
				data:{
					uid:userId,
					pwd:paymentPasswordValue 
				},
				success:function(req){
					//alert(JSON.stringify(req));
					if(req.status==100){
						var user=getUserInfo();
						user.isPpw=1;
						setSValue("userInfo",JSON.stringify(user));
						$(".pay-modal-title2").show();
						$(".pay-modal-title").hide();
					}
					
				}
			});
		}else{
			$(".pay-modal-title2").hide();
			$(".pay-modal-title").show();
		}
	}
}

function toPay(){
	var isPpw=getUserInfo().isPpw;
	//alert(isPpw)
	if(isPpw==1){
		$('#payModal').modal('show');
		$("#payPassValue").focus();
		var payBalance=parseFloat($("#orderFree").html());
		$("#payBalance").html(payBalance);
	}else{
		toSetPayPasswordPage();
	}
}

function toSetPayPasswordPage(){
	$("#paymentPasswordDiv").show();
	$("#mainDiv").hide();
}

function toMainPage(){
	var isPpw=getUserInfo().isPpw;
	$("#payInfoModal").modal("hide");
	if(isPpw==1){
		//alert(3)
		$("#paymentPasswordDiv").hide();
		$("#mainDiv").show();
	}
}


//支付密码的样式效果===============================================
$("#payPassValue").on("input propertychange",function(){
	var passValue=$("#payPassValue").val();
	if(passValue){
		$("#finishPayment").addClass("certain2").attr("disabled",false);
	}else{
		$("#finishPayment").removeClass("certain2").attr("disabled",true);
	}
})


