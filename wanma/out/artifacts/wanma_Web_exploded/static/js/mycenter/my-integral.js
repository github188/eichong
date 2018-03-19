/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
    /**
     * 积分商城
     */
	Ajax.queryDetail({
		url:config.IGetMyCenter,
		data:{userId : UserService.getUserId()},
        type: 'POST'
	});

})();