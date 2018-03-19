//checkUserLogin();
var user=getUserInfo();
var userId=user?user.pkUserinfo:0;
var userAccount=user?user.usinPhone:"";
var userAccountbalance =user?user.usinAccountbalance:100;
var userIdValue=$("#body").val(userId);

$("#userAccountbalance").html(userAccountbalance);
$("#purseValue").on("input propertychange",function(){
	var purseValue=$(this).val();
	if(purseValue==10){
		clearChangeStyle();
		$(".costList li[data-val=10]").addClass("changeStyle");
	}else if(purseValue==20){
		clearChangeStyle();
		$(".costList li[data-val=20]").addClass("changeStyle");
	}else if(purseValue==50){
		clearChangeStyle();
		$(".costList li[data-val=50]").addClass("changeStyle");
	}else if(purseValue==100){
		clearChangeStyle();
		$(".costList li[data-val=100]").addClass("changeStyle");
	}else if(purseValue==200){
		clearChangeStyle();
		$(".costList li[data-val=200]").addClass("changeStyle");
	}else if(purseValue==500){
		clearChangeStyle();
		$(".costList li[data-val=500]").addClass("changeStyle");
	}else{
		clearChangeStyle();
	}
	btnColor();
});
//	支付金额选择==========================================================
			$(".costList li").click(function(){
				var flag=$(this).hasClass("changeStyle");
				clearChangeStyle();
				if(flag){
					$("#purseValue").val("");
				}else{
					$(this).addClass("changeStyle");
					$("#purseValue").val($(this).attr("data-val"));
					//alert(n);
				}
				btnColor();
			})
			function clearChangeStyle(){
				$(".costList li").removeClass("changeStyle");
			}
			


//支付方式选择====================================================================
//			var n=null;
//			$(".costWay li").click(function(){
//				var flags=$(this).hasClass("styleWay");
//				clearStyleWay();
//				if(flags){
//					n=null;
//					
//				}else{
//					$(this).addClass("styleWay");
//					n=$(this).attr("data-val");
//					//alert(n);
//				}
//				btnColor();
//			})
//			function clearStyleWay(){
//				$(".costWay li").removeClass("styleWay");
//			}
//按钮颜色判断=====================================================================
function btnColor(){
	var purseValue=$("#purseValue").val();
//	if(n!=null&(purseValue!="")){
	if((purseValue!="")){
	//alert(payId)
		$("#instantCharge").addClass("instantChargeStyle");
	}else{
		$("#instantCharge").removeClass("instantChargeStyle");
	}

}
//回到我的钱包预充值页面===================================
function toMyWallet(){
	toPage("myWallet.html");
}

//表单提交======
$("#instantCharge").on("click",function(){
	$("#form1").submit();
})




