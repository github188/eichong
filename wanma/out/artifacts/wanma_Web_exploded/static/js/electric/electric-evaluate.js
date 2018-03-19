/**
 * Created by Kael on 2015/3/20.
 */
(function(){

	/**
	 * 评价列表
	 */
	var data_id = location.href.getQueryValue('e');//电桩Id
	var data_type = location.href.getQueryValue('t');//评价类型
	config.pageSize = 6;
	config.pageRequest = function(){
		Ajax.pageRequest({
			url:config.IFindElectricEvaluate,
			data:{
				pageNumber : config.begin,
				pageNum : config.pageSize,
				prCoProductId : data_id,
				prcoCommentType : data_type
			},
            type: 'POST'
		},function(res){
			$('.eva_all').text('用户评价（' + res.countData + '）');
		});
	}
	config.pageRequest();
	
})();
