/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
	/**
	 * 倒计时
	 */
	var data_time, data_interval;
	config.countDown = function() {
		var _date = new Date();
        var s = (data_time - _date.getTime()) / 1000;//计算倒计时总共秒数
        //判断倒计时是否有效
        if(!s || s < 0){
        	s = 0;
        	clearInterval(data_interval);//倒计时结束或者无效则停止循环
        }
		//计算倒计时时间ct(s / 3600)小时ct(s / 60 % 60)分钟ct(s % 60)秒
    	$('#count_down').text(ct(s / 3600) + ':' + ct(s / 60 % 60) + ':' + ct(s % 60));
		//旋转图片(s / 60).toFixed(2)计算分钟
		$('#rotate-image').rotate((s / 60).toFixed(2), true);
		//得到旋转之后的图片宽度
		var length = $('#rotate-image').attr('width') || 133;
		//计算边距像素
		var pixel = Math.round((188 - length) / 2) + 'px';
		//给旋转之后的图片添加边距
		$('#rotate-image').css({'margin-left':pixel,'margin-top':pixel});
		function ct(t) {
			t = Math.floor(t);
			return t >= 10 ? t : '0' + t;
		}
	}
	
    /**
     * 我的预约
     */
	var data_idE, data_idP, data_idB;
	config.queryDetail = function(){
		Ajax.queryDetail({
			url:config.IGetMyOrder,
            data: {bespUserInfo: UserService.getUserId()},
            type: 'POST'
		}, function (res) {
			var data = res.data[0];
			data_idE = data.bespElectricpileid;
			data_idP = data.bespElectricpilehead;
			data_idB = data.pkBespoke;
			data_time = data.bespEndTime;
			config.countDown();//初始加载倒计时
		});
	}
	config.queryDetail();

	/**
	 * 历史预约
	 */
	$('body').on('click', '#order_list', function(){
		window.location.href = config.PMyHisOrder;
	});
	
	/**
	 * 继续预约
	 */
	$('body').on('click', '#order_continue', function(){
		if(data_idB){
			var param = '?e=' + data_idE + '&p=' + data_idP + '&b=' + data_idB;
			window.location.href = config.PElectricOrder + param;
		}else{
			window.location.href = config.PElectricSearch;
		}
	});

	/**
	 * 取消预约
	 */
	$('body').on('click', '#order_remove', function(){
		Ajax.custom({
			url:config.IUpdateMyHisOrder,
			data:{
				pkBespoke : data_idB,
				bespElectricpilehead : data_idP,
			},
            type: 'POST'
		},function(){
			config.queryDetail();
		});
	});

	//每隔一秒刷新一次倒计时
	data_interval = setInterval("config.countDown()",1000);
})();