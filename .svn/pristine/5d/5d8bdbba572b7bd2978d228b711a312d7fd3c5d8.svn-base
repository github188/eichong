/**
 * Created by Kael on 2015/3/20.
 */
(function(){

	/**
	 * 当前时间
	 */
	config.nowTime = function(){
		var _date = new Date();
		$('#now_time').text(ct(_date.getHours()) + ':' + ct(_date.getMinutes()) + ':' + ct(_date.getSeconds()));
		function ct(t) {
			return t >= 10 ? t : '0' + t;
		}
	}

	/**
	 * 累计预约时间
	 */
	config.rotate = function(angle){
		if(!angle) angle = 0;
		//旋转图片
		$('#rotate-image').rotate(angle, true);
		//得到旋转之后的图片宽度
		var length = $('#rotate-image').attr('width') || 133;
		//计算边距像素
		var pixel = Math.round((188 - length) / 2) + 'px';
		//给旋转之后的图片添加边距
		$('#rotate-image').css({'margin-left':pixel,'margin-top':pixel});
	}
	
	/**
	 * 预约详细
	 */
	var data_idE = location.href.getQueryValue('e');//电桩Id
	var data_idP = location.href.getQueryValue('p');//枪口Id
	var data_idB = location.href.getQueryValue('b');//预约Id
	var order_time, order_price;
	Ajax.queryDetail({
		url:config.IGetElectricOrder,
		data:{
			pkElectricPile : data_idE,
			pkElectricpilehead : data_idP,
			pkBespoke : data_idB
		},
        type: 'POST'
	}, function (res) {
		//得到当前预约时间
		order_time = res.data[0].bespBespokeTime * 1;
		//得到当前预约单价
		order_price = res.data[0].unitPrice * 1;
		//计算当前预约费用
		var total_price = (order_price * order_time).toFixed(2);
		$('#order_price').html(total_price + '元');
		//预约最大6*60分钟，即360度，1分钟就跳转1度
		config.rotate(order_time);
		config.nowTime();//初始加载当前时间
	});
	
	/**
     * 预约时间设置
     */
	var data_time;
	$('body').on('click', '.datelist_box img', function(){
		data_time = $(this).attr('data-time') * 1;
		//累计时间=已经预约时间+当前所选预约时间
		var data_angle = order_time + data_time;
		if(data_angle > 360){
			alert('预约充电最多只能预约6个小时！');
			return;
		}
		$('.datelist_box img').each(function () {
			$(this).attr('src', '../../static/images/img/order/step_1.png');
	    });
		$(this).attr('src', '../../static/images/img/order/step_2.png');
		//计算当前预约费用
		var total_price = (order_price * data_angle).toFixed(2);
		$('#order_price').html(total_price + '元');
		//预约最大6*60分钟，即360度，1分钟就跳转1度
		config.rotate(data_angle);
    });

	/**
	 * 支付详情
	 */
	$('body').on('click', '#pay_detail', function(){
		if(!data_time){
			alert('请设置预约时间!');
			return;
		}
		var param = '?e=' + data_idE + '&p=' + data_idP + '&b=' + data_idB + '&t=' + data_time;
		window.location.href = config.PElectricPay + param;
	});

	//每隔一秒刷新一次当前时间
	setInterval('config.nowTime()',1000);
})();