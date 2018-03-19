/**
 * 新增收货地址
 */
(function () {
	var data_user = UserService.getUserId();
	if(!data_user){
        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
        return;
    }
	
//	订单id
	 var id = location.href.getQueryValue('a');
	    if (!id) return;
	/**
	 * 新增收货地址
	 */
	$("#addAddress-form").on('valid.form', function (e, f) {
		
		 var data = formJson(f);
		 Ajax.submitForm({
	            url: config.IAddAddress,
	            data: data
	        }, function (res) {
	            if (res.code != 'OK') {
	                alert(res.msg);
	                return;
	            }else{
	            	location.href =config.PAddress+"?a="+id;
	            }
	        });
    });
	
})();