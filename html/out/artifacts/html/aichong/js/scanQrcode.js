//初始化扫描二维码按钮，传入自定义的 node-type 属性 
$(function() {
	Qrcode.init($('[node-type=jsbridge]'));
});
(function($) {
	var Qrcode = function(tempBtn) {
		// 该对象只支持微博域下的解析，也就是说不是微博域下的页面只能用第二种方案解析二维码
		if (window.WeiboJSBridge) {
			$(tempBtn).on('click', this.weiBoBridge);
		} else {
			$(tempBtn).on('change', this.getImgFile);
		}
	};
	Qrcode.prototype = {
		weiBoBridge : function() {
			WeiboJSBridge.invoke('scanQRCode', null, function(params) {
				// 得到扫码的结果
				location.href = params.result;
			});
		},
		getImgFile : function() {
			var _this_ = this;
			var imgFile = $(this)[0].files;
			var oFile = imgFile[0];
			var oFReader = new FileReader();
			var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
			if (imgFile.length === 0) {
				return;
			}
			if (!rFilter.test(oFile.type)) {
				alert("选择正确的图片格式!");
				return;
			}
			// 读取图片成功后执行的代码
			oFReader.onload = function(oFREvent) {
				qrcode.decode(oFREvent.target.result);
				qrcode.callback = function(data) {
					//alert(JSON.stringify(data));
//alert(getParam("code",data));
//alert(getParam("q",data));
					setSValue("elpiElectricpilecode",getParam("code",data));
					setSValue("ePHeElectricpileHeadId",getParam("q",data));
					// 得到扫码的结果
					toPage("barcode.html");
				};
			};
			oFReader.readAsDataURL(oFile);
		},
		destory : function() {
			$(tempBtn).off('click');
		}
	};
	// 初始化
	Qrcode.init = function(tempBtn) {
		var _this_ = this;
		var inputDom;
		tempBtn.each(function() {
			new _this_($(this));
		});
		$('#scanQrcode').on('click', function() {
			$(this).find('[node-type=jsbridge]')[0].click();
		});
	};
	window.Qrcode = Qrcode;
})(jQuery);

function getParam(name,url) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var params=decodeURI(url);
	var r = params.substr(1).match(reg);
		if(r!=null)return  r[2]; return null;
}