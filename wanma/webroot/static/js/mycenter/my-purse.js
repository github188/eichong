/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
    /**
     * 我的钱包
     */
	Ajax.queryDetail({
		url:config.IGetMyPurse,
		data:{userId : UserService.getUserId()},
        type: 'POST'
	});

	/**
	 * 消费明细
	 */
	$('body').on('click', '#pay_detail', function(){
		window.location.href = config.PMyPayDetail;
	});

	/**
	 * 充值
	 */
	$('#recharge').click(function(e){
		e.preventDefault();
		$('#rechargeForm').trigger('submit');
	});

	$('#rechargeForm').on('valid.form',function(e,f){
		var data = formJson('#rechargeForm');
		data.pkUserinfo = UserService.getUserId();
		Ajax.submitForm({
			url:config.IAddMoney,
			data:data,
			backUrl:'self'
		});
	});

})();