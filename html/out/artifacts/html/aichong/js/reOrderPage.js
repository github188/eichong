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