/**
 * 安装预约
 */
(function () {

	 
    var data_user = UserService.getUserId();
    var orderInstallData;//安装预约数据
	if(!data_user){
        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
        return;
    }
//	订单id
	 var id = location.href.getQueryValue('a');
	    if (!id) return;
	
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
	//商品信息
	Ajax.pageRequest({
		url:config.selectProductsByOrderId,
		data:{
			"orderId":id
		}
	},function(res){
		orderInstallData=res.data;
		$(".mr5").html(res.msg);
	});
	
	/**
	 * 提交
	 */
    $("#orderSubmit").click(function(){ 
    	//联系人
    	var receivePeople=$("#receive_people").text();
    	//联系电话
    	var receivePhone=$("#receive_phone").text();
    	//联系地址
    	var receiveAddress=$("#receive_address").text();
    	Ajax.custom({
    		url:config.IOrderInstall,
    		data:{
    			"orderInstallData":JSON.stringify(orderInstallData),
    			"userId":data_user,
    			"receivePeople":receivePeople,
    			"receivePhone":receivePhone,
    			"receiveAddress":receiveAddress
    			
    		}
    	},function(){
    		location.href =config.PInstallSuccess;
    	});
    });
    
	/**
	 * 结算
	 */
/*    $(".all-btn").click(function(){ 
    	Ajax.custom({
    		url:config.IUpdateOrder,
    		data:{
    			"pkOrder":id,
    			"userId":data_user
    		}
    	},function(){
    		location.href =config.PPaySucces+"?a="+id;
    	});
    	
    	
    });*/
})();