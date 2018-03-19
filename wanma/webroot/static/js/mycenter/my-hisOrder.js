/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
    /**
     * 历史预约
     */
	config.pageSize = 9;
	config.pageRequest = function(){
		Ajax.pageRequest({
			url:config.IFindMyHisOrder,
			data:{
				bespUserInfo : UserService.getUserId(),
				pageNumber : config.begin,
				pageNum : config.pageSize
			},
            type: 'POST'
		});
	}
	config.pageRequest();

})();