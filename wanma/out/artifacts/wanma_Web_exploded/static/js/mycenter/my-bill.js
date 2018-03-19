/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
    /**
     * 我的订单
     */
	var data_type = 1;
	config.pageSize = 4;
	config.pageRequest = function(){
		Ajax.pageRequest({
			url:config.IFindMyBill,
			data:{
				userId : UserService.getUserId(),
				orderType : data_type,
				pageNumber : config.begin,
				pageNum : config.pageSize
			},
            type: 'POST'
		});
	}
	config.pageRequest();
	
	/**
     * 我的订单
     */
	$('.nav li').on('click',function () {
		data_type = $(this).attr('data-type');
		config.begin = 1;
		config.pageRequest();
    });

	/**
	 * 订单支付
	 */
	$('body').on('click', '.billBtn', function(){
		alert('支付成功！');
	});

})();