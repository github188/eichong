var share_config={
		share_url:location.href,
		share_title:encodeURIComponent(document.title),
		share_img:"http://www.eichong.com/static/images/bg-logo.jpg",
		share_from:encodeURIComponent("爱充网"),
		sina_appkey:"",
		weixin_twodicode_img:"http://www.eichong.com/static/images/weixin.jpg"
		
} 
// qq空间
$('body').on('click', '.bshare-qzone', function () {
	//$("#index").html("url="+share_config.share_url+"&title="+share_config.share_title+"&pics="+share_config.share_img+"&site="+share_config.share_from+"")
	window.open("http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+share_config.share_url+"&title="+share_config.share_title+"&pics="+share_config.share_img+"&site="+share_config.share_from+"","newwindow");
});
// 新浪微博
$('body').on('click', '.bshare-sinaminiblog', function () {
	window.open("http://v.t.sina.com.cn/share/share.php?appkey="+share_config.sina_appkey+"&title="+share_config.share_title+"&url="+share_config.share_url+"&source=eichong&retcode=0&ralateUid=111","newwindow");
});
// 人人
$('body').on('click', '.bshare-renren', function () {
	window.open('http://widget.renren.com/dialog/share?resourceUrl='+share_config.share_url+'&title='+share_config.share_title+'&images='+share_config.share_img+'','newwindow');
});
// 微信
$('body').on('click', '.bshare-weixin', function () {
	var html='<div id="bsWXBox" class="bsBox" style="display: block; left: 50%; top: 50%; margin-left: -110px; margin-top: -122.5px; position: fixed; height: 245px; width: 220px;"><div class="bsClose" style="top: 8px; right: 20px;">X</div><div class="bsTop" style="width: 220px;"><span style="margin-left:15px;">分享到</span><span class="bsPlatName">&nbsp;-&nbsp;微信</span></div><div class="bsFrameDiv"><div style="height: 200px; width: 200px; display: block;padding:20px 0 0 20px;" id="twoDiCodeDiv"></div></div></div>';
	$("body").append(html);
	//alert(share_config.share_url);
	$('#twoDiCodeDiv').html("").qrcode({
  	 	 width: 180,
	     height: 180,
	     text: share_config.share_url
  });
});
$("body").on('click', '.bsClose',function(){
	$("#bsWXBox").remove();
})

function utf16to8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
    	c = str.charCodeAt(i);
    	if ((c >= 0x0001) && (c <= 0x007F)) {
    	    out += str.charAt(i);
    	} else if (c > 0x07FF) {
    	    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
    	    out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
    	    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
    	} else {
    	    out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
    	    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
    	}
        }
        return out;
    }

