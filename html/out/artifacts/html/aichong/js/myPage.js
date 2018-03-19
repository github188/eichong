
var user=getUserInfo();
var userAccountbalance =user?user.usinAccountbalance:0;
var userId=user?user.pkUserinfo:0;
var userAccount=user?user.usinPhone:"";
//用户名称
usinUsername=user?user.usinUsername:"爱充用户";
//用户头像
var usinHeadimage=getSValue("usinHeadimage"); 
var userAccount=getSValue("userAccount");
var usinAccountbalance=getSValue("usinAccountbalance");

function checkLoginInfo(){
	if(!user){
		$("#operationBtn").show();
		$("#userName").show();
		$("#showInfo").hide();
	}else{
		$("#operationBtn").hide();
		$("#userName").show();
		$("#showInfo").show();
//		用户余额
		$("#userAccountbalance").html(userAccountbalance);
//		用户名称
		$("#usinUsername").html(usinUsername);
//      用户头像
		if(usinHeadimage){
			$("#usinHeadimage").attr("src",usinHeadimage);
		}
		
	}
}
checkLoginInfo();
function toMyWallet(){
	checkUserLogin();
	toPage("myWallet.html");
}
function toMyFavorite(){
	checkUserLogin();
	toPage("myFavorite.html");
	
}
function toApplyPile(){
	checkUserLogin();
	toPage("applyBuilder.html?aepOrigin=99&useHead=1");
	
}
//去登陆
function toLogin(){
	checkUserLogin();
}
//去首页
function toMapIndex(){
	toPage("map_index.html");
}
//去订单
function toOrderDetail(){
	toPage("order_appointment.html");
}
