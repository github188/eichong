var basePath = "http://localhost:8088";
var getChgStatus = "/WeChatCharge/getChgStatus.do?ext=t";
var getElectricPile = "/WeChatCharge/getElectricPile.do?ext=t";
var getWxAccount = "/WeChatCharge/getWxAccount.do?ext=t";
var getWxAccountDetail = "/WeChatCharge/getWxAccountDetail.do?ext=t";
var config="/WechatJs/config.do?ext=t";
var wxTempOrder="/WechatJs/wxTempOrder.do?ext=t";
var orderInfo="/WeChatCharge/getOrderInfo.do?ext=t";
var chargeStop="/WeChatCharge/wxChargeStop.do?ext=t";
//采用正则表达式获取地址栏参数
function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var params=decodeURI(window.location.search);
    var r = params.substr(1).match(reg);
    if(r!=null)return  r[2]; return null;
}
//一些公共方法=====================
function toPage(url) {
    return window.location.href = url;
}
//
function  wxloadStart() {
    var loadHtml='<div class="mainll"><div class="spinner"><div class="bounce1"></div>'
                +'<div class="bounce2"></div><div class="bounce3"></div></div></div>'
    $('body').prepend(loadHtml);
}
function  wxloadStop() {
    $('.mainll').remove();
}