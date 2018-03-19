/**
 * 确认订单
 */
(function () {
	
	var data_user = UserService.getUserId();
	if(!data_user){
        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
        return;
    }
//	订单id
	 var id = location.href.getQueryValue('a');
	    if (!id) return;
	    /**
		 * 默认收货地址
		 */
	Ajax.queryDetail({
        url: config.IAddress,
        data:{
        	"pkUserInfo":data_user,
        	"orderId":id
        }
    },function(res){
    	if(!res.data){
    		$("#detail-data").html("请添加默认收货地址<a class='arrow' href="+config.PAddress+"?a="+id+"></a>");
    	}
    });
	 /**
	 * 订单中商品信息
	 */
	Ajax.pageRequest({
		url:config.selectProductsByOrderId,
		data:{
			"orderId":id
		}
	},function(res){
		$(".totalMoney").html(res.msg);
	});
	
	/**
	 * 结算
	 */
    $(".all-btn").click(function(){ 
    	if(!data_user){
            location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
            return;
        }
    	Ajax.custom({
    		url:config.IUpdateOrder,
    		data:{
    			"pkOrder":id,
    			"userId":data_user
    		}
    	},function(){
    		location.href =config.PPaySucces+"?a="+id;
    	});
    	
    	
    });
})();