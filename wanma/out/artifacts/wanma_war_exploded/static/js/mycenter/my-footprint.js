/**
 * Created by Kael on 2015/3/20.
 */
(function(){
    /**
     * 我的足迹
     */
	Ajax.pageRequest({
		url:config.IGetMyFootprint,
		data:{userId : UserService.getUserId()},
        type: 'POST'
	});

})();