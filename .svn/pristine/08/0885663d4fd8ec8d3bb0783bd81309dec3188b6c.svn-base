//var pkElectricPile=15219;
//var elpiElectricpilecode="3301021010000004";
//var ePHeElectricpileHeadId=1;
//checkUserLogin();
var user=getUserInfo();
//alert(JSON.stringify(user));
var userId=user?user.pkUserinfo:0;
var userAccount=user?user.usinPhone:"";
var pkElectricPile=getSValue("pkElectricPile");
//桩体编号
var elpiElectricpilecode=getSValue("elpiElectricpilecode");
//枪头编号
var ePHeElectricpileHeadId=getSValue("ePHeElectricpileHeadId");
var scantype=1;

var userAccountBalance=parseFloat(user.usinAccountbalance);
$("#balance").html(userAccountBalance);
$("#usinAccountbalance").html(userAccountBalance);


if(userAccountBalance>=1&&userAccountBalance<=200){
	$("#purseValue").attr("placeholder",userAccountBalance);
	$("#purseValue").val(userAccountBalance);
}else if(userAccountBalance>200){
	$("#purseValue").val(200);
}
else if(userAccountBalance<1){
	mui.toast("余额不足，请去充值。");
}

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
		attEvent(purseValue,'blur',passTest);//支付密码
})
//设置支付金额规则=================================================================
function passTest(){
	var setPass=document.getElementById("purseValue");
//	var balance=document.getElementById("balance");
//	var balanceValue=balance.innerHTML;
	var setPassValue=setPass.value;
	var reg=/^\d{1,3}$/;//支付金额验证
	if(setPassValue<1){
		setPass.focus();
		mui.toast("最小预充金额值1元,请重新填写");
		return false;
	}else if(setPassValue>200){
			setPass.focus();
			mui.toast("最大值预充金额为200元,请重新填写");
			return false;
	}else if(setPassValue>userAccountBalance){
		setPass.value=userAccountBalance;
		return true;
	}else if((!reg.test(setPassValue))&&userAccountBalance!=setPass.value){
		setPass.focus();
		mui.toast("预充金额只允许输入整数,请重新填写");
		return false;
	}
	
	return true;	
}
//点击出现模态框情况============================================
function toCharge(){
	var payValue=$("#purseValue").val();
	$("#payValue").html(payValue);
	if(passTest()){
		toPay();
	}
}
//支付密码的样式效果===============================================
$("#passValue").on("input propertychange",function(){
	var passValue=$("#passValue").val();
	if(passValue){
		$("#certain").addClass("certain2");
		$("#certain").removeAttr("disabled","");
	}else{
		$("#certain").removeClass("certain2");
		$("#certain").attr("disabled",true);
	}
})


//返回按钮=====================================
function toHistory(){
	window.history.back();
}
function finishPayment(){
//	$("#waitLoad").show();
	loadStart();
	var payPassword=$("#payPassValue").val();
	var url="/itf/userinfo/checkPayPwd";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			uid:userId,
			phone:userAccount,
			pwd:payPassword 
		},
		success:function(req){
			
//				alert(JSON.stringify(req));
			if(req.status==100){
				$("#payModal").modal('hide');
				$.ajax({
					type:"get",
					url:getRootPath()+"/itf/charge/beginCharge",
					async:true,
					dataType:"json",
					data:{
						pkElectricPile:pkElectricPile,
						elpiElectricpilecode:elpiElectricpilecode,
						ePHeElectricpileHeadId:ePHeElectricpileHeadId, 
						pkUserinfo:userId,
						scantype:scantype,
						did:deviceId,
						preMoney:$("#purseValue").val(),
						org:1000,
						payMod:1
					},
					success:function(req){
						loadStop();
//						$("#waitLoad").hide();
						refreshUserAB();
						if(req.status==100){
							loadStop();
							mui.toast(req.msg);
							toPage("charging_detail.html");
						}else if(req.status==6001){
							loadStop();
							mui.toast(req.msg);
							console.log(req.status);
							toPage("order_appointment.html?type=charge");
						}else{
							loadStop();
							mui.toast(req.msg);
//							alert(req.status);
						}
						
					}
				});
			}else{
//				$("#waitLoad").hide();
				loadStop();
				mui.toast(req.msg);
			}
			
		}
	});
}

function toPileInfo(){
	checkUserLogin("barcode.html");
	//window.location.href="barcode.html";
}
