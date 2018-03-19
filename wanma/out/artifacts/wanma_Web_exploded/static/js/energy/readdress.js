/**
 * 收货地址
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
	 * 获取用户所有地址
	 */
	Ajax.pageRequest({
        url: config.IAddresses + '?pradUserid=' + data_user
    },function(res){
    	 $(".other_addr ul li:last-child").css("border-bottom","0");
    	    $(".addr_info .check_box .def_check").click(function(){
    	    	if($(this).hasClass("checked")){
    	    		 $(this).toggleClass("checked");
    	    	}else{
    	    		 $(this).toggleClass("checked");
    	    		 $(".other_addr ul li .addrDet .check_box .def_check").removeClass("checked");
    	    	}
    	      
    	    });
    	    $(".other_addr ul li .addrDet .check_box .def_check").click(function(){
    	        if($(this).hasClass("checked")){
    	            $(this).removeClass("checked");
    	        }else{
    	        	$(".def_check").removeClass("checked");
    	            $(this).addClass("checked");
    	        }

    	    });
    });
	
	/**
	 * 删除收货地址
	 */
	$("#data-list").on('click','.del',function(){
		var pkUseraddress = $(this).parent().prev().val();
		Ajax.custom({
		url:config.IDeleteAddress,
	 	data:{
	 		"pkUseraddress":pkUseraddress
	 	}
		},function(res){
			if(res.code=="OK"){
				location.href = config.PAddress+"?a="+id;
			}else{
				alert("删除失败");
			}
		});
	});
	
	/**
	 * 编辑收货地址
	 */
	$("#data-list").on('click','.edit',function(){
		var pkUseraddress = $(this).parent().prev().val();
		location.href = config.IFindAddress+"?d="+pkUseraddress+"&a="+id;
	});
		
	/**
	 * 新增收货地址
	 */
	$(".cancal").click(function(){
		location.href = config.PAddAddress+"?a="+id;
	});
	
	/**
	 * 设为默认收货地址
	 */
	$(".sure").click(function(){
		var pkUseraddress = $(".checked").parent().nextAll(".pkUseraddress").val();
		if(!pkUseraddress){
			alert("请选择地址");
			return;
		}
		Ajax.custom({
			url:config.IUpdateIsDefault,
			data:{
				"pkUseraddress":pkUseraddress,
				"pradUserid":data_user
			}
		},function(res){
			 var orderType = location.href.getQueryValue('ty');
			if(res.code=="OK"){
				if(orderType=="confirmOrder"){//确认订单
					location.href = config.PPromptlyBuy+"?a="+id;
				}else{//跳转订单
					location.href = config.POrderInstallDetail+"?a="+id;
				}
			}else{
				alert("设置失败");
			}
		});
		
	});
})();