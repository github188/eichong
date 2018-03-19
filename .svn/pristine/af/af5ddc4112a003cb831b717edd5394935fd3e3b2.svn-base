var ws=null,wo=null;
var scan=null,domready=false;
// H5 plus事件处理
function plusReady(){
	if(ws||!window.plus||!domready){
		return;
	}
	// 获取窗口对象
	ws=plus.webview.currentWebview();
//	wo=ws.opener();
	embed=plus.webview.create("barcode.html","",{top:"0",bottom:"0"});
	embed.show();
	// 开始扫描
	ws.addEventListener('show',function(){
		scan=new plus.barcode.Barcode('bcid');
	    scan.onmarked=onmarked;
	    scan.start({conserve:true,filename:"_doc/barcode/"});
	});
	// 显示页面并关闭等待框
    ws.show("pop-in");
//  wo.evalJS("closeWaiting()");
}
if(window.plus){
	plusReady();
}else{
	document.addEventListener("plusready",plusReady,false);
}
// 监听DOMContentLoaded事件
document.addEventListener("DOMContentLoaded",function(){
	domready=true;
	plusReady();
},false);
// 二维码扫描成功
function onmarked(type,result,file){
    switch(type){
    	case plus.barcode.QR:
    	type = "QR";
    	break;
    	case plus.barcode.EAN13:
    	type = "EAN13";
    	break;
    	case plus.barcode.EAN8:
    	type = "EAN8";
    	break;
    	default:
    	type = "其它";
    	break;
    }
    result = result.replace(/\n/g, '');
    embed.evalJS("scaned('"+ type +"','"+ result +"','"+ file +"');");
    back();
}
var flash=false;
function setFlash(){
	flash=!flash;
	scan.setFlash(flash);
	if(flash){
	var f=document.getElementById('flash');
	f.textContent = '关闭手电筒';
	f.getAttribute("class");
	//alert(f.getAttribute("class"));
	f.setAttribute("class","");
	f.setAttribute("class","fbtActive");
	}else{
	var f=document.getElementById('flash');
	f.textContent = '打开手电筒';
	f.setAttribute("class","fbt");
	}
}

function returnPre(){
	ws.hide();
	embed=plus.webview.create("map_index.html","",{top:"0",bottom:"0"});
	embed.show();
}