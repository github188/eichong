/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
	/**
	 * 电桩查找 列表模式
	 */
	var data_type = 0;
	var data_name = location.href.getQueryValue('n');//搜索电桩名称
	config.pageSize = 6;
	config.pageRequest = function(){
    	Ajax.pageRequest({
    		url:config.IFindElectricResult,
    		data:{
    			pageNumber : config.begin,
    			pageNum : config.pageSize,
    			searchName : data_name,
    			searchType : data_type
    		},
            type: 'POST'
    	});
	}
	config.pageRequest();
    
	/**
     * 电桩查找
     */
    $('body').on('click', '.list-tit a', function(){
        $(this).addClass("current").siblings("a").removeClass("current");
          
		config.begin = 1;
		data_type = $(this).index();
    	config.pageRequest();
    });

	/**
	 * 电桩/充电点详情
	 */
	$('body').on('click', '#pile_detail', function(){
		var data_type = $(this).attr('data-type');
		var url = (data_type == 2 ? config.PElectricPlant : config.PElectricPile);
		window.location.href = url + '?e=' + $(this).attr('data-id');
	});
	
})();