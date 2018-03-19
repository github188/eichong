/**
 * 交易成功
 */
(function () {
	
//	订单id
	 var id = location.href.getQueryValue('a');
	
	 /**
	 * 订单中商品信息
	 */
	Ajax.pageRequest({
		url:config.selectProductsByOrderId,
		data:{
			"orderId":id
		}
	});
	
	 /**
	 * 订单跟踪信息
	 */
	Ajax.custom({
		url:config.IFindOrderTracks,
		data:{
			"orderId":id
		}
	},function(res){
		for(var i = 0; i < res.data.length; i++){
			var a = res.data[i].ortrStatus;
				$("#"+a).removeClass("ball-empty");
				$("#"+a).addClass("ball");
				$("#"+a).find(".date").html(Tools.formatDate(res.data[i].ortrCreatedate,6));
				$("#"+a).find(".time").html(Tools.formatDate(res.data[i].ortrCreatedate,2));
		}
	});
	
	 /**
	 * 订单信息
	 */
	Ajax.custom({
		url:config.IGetOrder,
		data:{
			"orderId":id
		}
	},function(res){
		$("#ordeCode").html(res.data.ordeCode);
		$("#ordeUpdatedate").html(Tools.formatDate(res.data.ordeUpdatedate,6));
	});
  
	  var data_user = UserService.getUserId();
		if(!data_user){
	        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
	        return;
	    }
      // 订单id
	 var id = location.href.getQueryValue('a');
	    if (!id) return;
		    
	/**
	 * 去预约安装
	 */
    $("#subscribeInstall").click(function(){ 
    	location.href =config.POrderInstallDetail+"?a="+id;
    });
})();