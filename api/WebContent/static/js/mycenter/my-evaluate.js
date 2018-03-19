/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
	 /**
	 * 我的评价
	 */
	config.pageSize = 6;
	config.pageRequest = function(){
		Ajax.pageRequest({
			url:config.IFindMyEvaluate,
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
	 * 删除评价
	 */
	$('body').on('click', '#evaluate_remove', function(){
		var data_id = $(this).attr('data-id');
		Ajax.custom({
			url:config.IRemoveMyEvaluate,
			data:{productCommentId : data_id},
	        type: 'POST'
		},function(){
			config.pageRequest();
		});
	});
	
})();