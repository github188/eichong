/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
    /**
     * 我的收藏
     */
	config.pageSize = 6;
	config.pageRequest = function(){
		Ajax.pageRequest({
			url:config.IFindMyCollect,
			data:{
				userId : UserService.getUserId(),
				pageNumber : config.begin,
				pageNum : config.pageSize
			},
            type: 'POST'
		});
	}
	config.pageRequest();

	/**
	 * 删除收藏
	 */
	$('body').on('click', '#collect_remove', function(){
		var data_id = $(this).attr('data-id');
		Ajax.custom({
			url:config.IRemoveMyCollect,
			data:{userCollectId : data_id},
            type: 'POST'
		},function(){
			config.pageRequest();
		});
	});
	
})();