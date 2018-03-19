/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
    /**
     * 消费明细
     */
	var _date = new Date();
	//得到初始开始时间（当前月第一天）
	var begin_time = _date.getFullYear() + "-" + ct(_date.getMonth()+1) + "-01 00:00:00";
	//得到初始结束时间（当前月最后一天）
	var end_time = _date.getFullYear() + "-" + ct(_date.getMonth()+1) + "-30 23:59:59";
	config.pageSize = 9;
	config.pageRequest = function(){
		Ajax.pageRequest({
			url:config.IFindMyPayDetail,
			data:{
				userId : UserService.getUserId(),
				walletType : 'all',
				beginTime : begin_time,
				endTime : end_time,
				pageNumber : config.begin,
				pageNum : config.pageSize
			},
            type: 'POST'
		});
	};
	config.pageRequest();
	
	/**
     * 消费明细
     */
	$('.nav li').on('click',function () {
		_date = new Date();
		var data_type = $(this).attr('data-type');
		if(data_type){
			//当前月前2个月的月份
			_date.setMonth(_date.getMonth() - 2);
		}
		//得到初始开始时间（当前月前2个月的月份第一天）
		begin_time = _date.getFullYear() + "-" + ct(_date.getMonth()+1) + "-01 00:00:00";
		config.begin = 1;
		config.pageRequest();
    });
	
})();

function ct(t) {
	return t >= 10 ? t : '0' + t;
}