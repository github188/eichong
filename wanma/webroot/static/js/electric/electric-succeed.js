/**
 * Created by Kael on 2015/3/20.
 */
(function(){

	/**
	 * 相关充电点
	 */
	var data_lon = location.href.getQueryValue('l');//当前电桩经度
	var data_lat = location.href.getQueryValue('w');//当前电桩纬度
	Ajax.pageRequest({
		url:config.IGetRelated,
		data:{
			longitude : data_lon,
			latitude : data_lat
		},
        type: 'POST'
	});
	
	/**
	 * 我的预约
	 */
	$('body').on('click', '#order_view', function(){
		 window.location.href = config.PMyOrder;
	});

	/**
	 * 我的钱包
	 */
	$('body').on('click', '#purse_view', function(){
		 window.location.href = config.PMyPurse;
	});

	/**
	 * 充电点详情
	 */
	$('body').on('click', '#plant_detail', function(){
		var data_id = $(this).attr('data-id');
		window.location.href = config.PElectricPlant + '?e=' + data_id;
	});
	
})();