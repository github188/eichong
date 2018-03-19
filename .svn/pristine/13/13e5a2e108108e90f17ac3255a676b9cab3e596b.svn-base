/**
 * Created by Kael on 2015/3/20.
 */
(function(){

	var data_user = UserService.getUserId();
	if(!data_user){
        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
        return;
    }
	
	/**
	 * 支付详情
	 */
	var data;
	var data_idE = location.href.getQueryValue('e');//电桩Id
	var data_idP = location.href.getQueryValue('p');//枪头Id
	var data_idB = location.href.getQueryValue('b');//预约Id
	var data_time = location.href.getQueryValue('t');//预约时间
	Ajax.queryDetail({
		url:config.IGetElectricPay,
		data:{
			pkUserinfo : data_user,
			pkElectricPile : data_idE,
			pkElectricpileHead : data_idP,
			pkBespoke : data_idB,
			bespBespoketime : data_time
		},
        type: 'POST'
	}, function(res){
		data = res.data[0];
	});

	/**
	 * 电站详情
	 */
	$('body').on('click', '#plant_detail', function(){
		var data_id = $(this).attr('data-id');
		window.location.href = config.PElectricPlant + '?e=' + data_id;
	});

	/**
	 * 预约支付
	 */
	$('body').on('click', '#succeed_detail', function(){
		if(data.bespFrozenamt > data.usInAccountBalance){
			if(confirm('您的账户余额不足，是否前去充值！')){
				window.location.href = config.PMyPurse;
			}
			return; 
		}
		if('ok' != $('input[name=check]:checked').val()){
			alert('请您先阅读预约须知，再进行支付！');
			return; 
		}
		Ajax.custom({
	        url:config.IInsertElectricPay,
	        data:{
	        	pkBespoke : data.pkBespoke,
	        	bespElectricpileid : data.pkElectricPile,
	        	bespBespoketime : data.bespBespoketime,
	        	bespBespokeremark : data.elPiElectricPileName + data.ePHeElectricpileHeadName,
	        	bespBespoketimes : data.bespBespoketimes,
	        	bespElectricpilehead : data.pkElectricpileHead,
	        	bespUserinfo : data_user,
	        	bespResepaymentcode : data.bespResePaymentCode,
	        	bespFrozenamt : data.bespFrozenamt,
	        	//bespBespokeprice : data.unitPrice,
	        	bespBeginTime : data.bespBeginTime,
	        	bespEndTime : data.bespEndTime
	        },
            type: 'POST',
            timeout: 20000
	    },function(res){
	    	if(res.code != 'OK'){
	    		alert(res.msg);
	    		return;
	    	}
    		var param = '?l=' + data.elPiLongitude + '&w=' + data.elPiLatitude;
	    	window.location.href = config.PElectricSucceed + param;
	    });
	});
	
	/**
	 * 取消
	 */
	$('body').on('click', '#order_detail', function(){
		var param = '?e=' + data_idE + '&p=' + data_idP + '&b=' + data_idB;
		window.location.href = config.PElectricOrder + param;
	});
	
})();