var loginPage="login.html";
var expireTime=1000*10;//3600*24*365;
var indexPage="map_index.html";
var deviceId="e10adc3949ba59abbe56e057f20f883e";
var jpushRegistrationid="MTIzNDU2";
checkDeviceType();

function checkUserLogin(url){
	if(url) setSValue("redirect",url);	
	if(sessionStorage.userAccount||sessionStorage.userAccount!=undefined){
		window.location.href=url;
		return;
	}	
	if(localStorage.userAccount&&localStorage.userExpire){
		var expire=parseInt(localStorage.userExpire);
		if(new Date().getTime()>expire){
			openLoginWindow();
		}
	}else{
		openLoginWindow();
	}
	
}


function openLoginWindow(){
//	if(redirect){
//		loginPage+="?redirect="+redirect;
//	}
//	window.location.href=loginPage;
		$("#parentPage").remove();
		var mark='<div class="parentPage" id="parentPage"><div class="loginPage" id="loginPage">'
			+'<link rel="stylesheet" type="text/css" href="css/login.css"/><div class="headerHide">'
			+'<div class="col-xs-4 headLeft"></div><div class="col-xs-4 headRight">登录'
			+'</div><div class="col-xs-4 headLeft" id="closeWindow"><img src="img/close.png"/>'
			+'</div></div><div class="passBlock">'
			+'<input type="text" name="userAccount" id="userAccount" value="" class="userInfo" placeholder="手机号"/>'
			+'</div><div class="passBlock repassBlock">'
			+'<input type="password" name="userPassword" id="userPassword" value="" class="userInfo" placeholder="密码"/>'
			+'</div><a href="#" id="login" onclick="login()" class="loginBtn">登录</a><div class="occupied" style="width: 100%;height: 5rem;"></div></div></div>';
		$("body").append(mark);
		return;
}


initLogin();
function initLogin(){
	if(localStorage.userAccount){
		$("#userAccount").val(localStorage.userAccount);
	}
}


function login(){
	var userAccount=$("#userAccount").val();
	var userPassword=$("#userPassword").val();
	var url="/itf/userinfo/login";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			usinPhone:userAccount,
			usInPassword:userPassword,
			jpushRegistrationid:jpushRegistrationid,
			jpushDevicetype:localStorage.deviceType,
			did:deviceId
		},
		success:function(req){
//			alert(JSON.stringify(req));
			if(req.status==100){
//				var redirect=unescape(getUrlParam("redirect"));
//				if(!redirect){
//					redirect=indexPage;
//				}
				sessionStorage.userAccount=userAccount;
				sessionStorage.userInfo=JSON.stringify(req.data);
				localStorage.userAccount=userAccount;
				localStorage.userExpire=new Date().getTime()+expireTime;
				window.location.href=getSValue("redirect");
			}else{
				mui.toast(req.msg);
			}
		
		}
	});
}


function logout(){
	if(!sessionStorage.userInfo){
		alert("用户尚未登录");
		return;
	}	
	var user=getUserInfo();
	var url="/itf/userinfo/logout";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			userId:user.pkUserinfo
		},
		success:function(req){
			if(req.status==100){
				sessionStorage.userAccount="";
				sessionStorage.userInfo="";
				localStorage.userAccount="";
				localStorage.userExpire=0;
			}else{
				mui.toast(req.msg);
			}
			
		}
	});
}

function refreshUserAB(){
	var user=getUserInfo();
	var url="/itf/userinfo/getUserAB";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:false,
		dataType:"json",
		data:{
			uid:user.pkUserinfo
		},
		success:function(req){
			if(req.status==100){
				user.usinAccountbalance=req.data.userAB;
				setSValue("userInfo",JSON.stringify(user));
			}else{
				mui.toast(req.msg);
			}
			
		}
	});
	
}

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

function checkDeviceType(){
	 if(localStorage.deviceType){	 
		return;
	 } 
	 var browser={
		versions:function(){ 
			   var u = navigator.userAgent, app = navigator.appVersion; 
			   return {
					trident: u.indexOf('Trident') > -1, 
					presto: u.indexOf('Presto') > -1, 
					webKit: u.indexOf('AppleWebKit') > -1, 
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, 
					mobile: !!u.match(/AppleWebKit.*Mobile.*/), 
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), 
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, 
					iPhone: u.indexOf('iPhone') > -1 , 
					iPad: u.indexOf('iPad') > -1, 
					webApp: u.indexOf('Safari') == -1 
				};
			 }(),
			 language:(navigator.browserLanguage || navigator.language).toLowerCase()
	} 
	if(!(browser.versions.mobile)){
		alert("not mobile");
	}else{
		if(browser.versions.ios){
			localStorage.deviceType=2;
		}else if(browser.versions.android){
			localStorage.deviceType=1;
		}
	}
}

function setLValue(key,value){
	localStorage.setItem(key,value);
}

function getLValue(key){
	return localStorage.getItem(key);
}

function setSValue(key,value){
	sessionStorage.setItem(key,value);
}

function getSValue(key){
	return sessionStorage.getItem(key);
}

function getUserInfo(){
	return JSON.parse(getSValue("userInfo"));
}

//点击关闭，关闭这个登录层======
$("body").on("click","#closeWindow",function(){
	$("#parentPage").remove();
})
