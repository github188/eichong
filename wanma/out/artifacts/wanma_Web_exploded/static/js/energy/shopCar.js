/**
 * 购物车
 */
(function () {
	
	var data_user = UserService.getUserId();
	if(!data_user){
        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
        return;
    }
    /**
     * 获取我的购物车
     */
    config.myCartRequest=function(){
    	Ajax.pageRequest({
    		url: config.IFindMyCart,
    		data:null,
    		type: 'POST'
    	});
    },
    config.myCartRequest();

    /**
     * 计算某品牌下商品价格和数量
     */
    $('#data-list').on('click','#unitCheckbox',function(){
    	if($(".check").length==$(".check:checked").length){
    		document.getElementById("all").checked=true;
    	}
    	
    	// 选中商品数量
    	var count = parseInt($(this).nextAll('div').find('.shcaCount').text());
    	
    	var money = parseFloat($(this).nextAll('div').find('.pay').text());
    	money = money*count;
    	// 某品牌下商品数量
    	var totalBrandCount = parseInt($(this).parents('.god').next().children('.fc-totalCount').text());
    	// 某品牌下商品价格
    	var totalBrandMoney = parseFloat($(this).parents('.god').next().children().children('.fc-totalMoney').text());
    	
    	if($(this).is(":checked")){
    		totalBrandCount+=count;
    		$(this).parents('.god').next().children('.fc-totalCount').html(totalBrandCount);
    		totalBrandMoney += money;
    		$(this).parents('.god').next().children().children('.fc-totalMoney').html(totalBrandMoney);
    	}else{
    		totalBrandCount = totalBrandCount-parseInt(count);
    		$(this).parents('.god').next().children('.fc-totalCount').html(totalBrandCount);
    		totalBrandMoney = totalBrandMoney-parseFloat(money);
    		$(this).parents('.god').next().children().children('.fc-totalMoney').html(totalBrandMoney);
    		$("#all").attr("checked",false);
    	}
    	//计算总价格
    	var totalCount = 0;
    	var totalMoney = 0;
    	$('.god-moudel-foot').each(function(){
    		totalCount+=parseInt($(this).find('.fc-totalCount').text());
    		totalMoney+=parseFloat($(this).find('.fc-totalMoney').text());   		
    	});
    	
    	$('.all-r').find('.pay.ml5').html(totalMoney);
    });
    
 // 全选按钮
	$("#all").on('click', function() {
		$(".check").each(function() {
			if($("#all").is(":checked")){
				this.checked = true;
			}else{
				this.checked = false;
			}
		});
//		全选 计算总价格
		if($(this).is(":checked")){
			//总价格
			var totalMoney = 0;
			$(".god").each(function(){
				// 品牌下总价格
				var totalBrandMoney=0;
				//品牌下总数量
				var totalBrandCount=0;
				$(this).find('.check').each(function(){
					var unitCount = parseInt($(this).nextAll('div').find('.shcaCount').text());
					var unitMoney = parseFloat($(this).nextAll('div').find('.pay').text());
					unitMoney = unitMoney*unitCount;
					
					totalBrandCount +=unitCount;
					totalBrandMoney+=unitMoney;
				});
				$(this).next().find('.fc-totalCount').html(totalBrandCount);
				$(this).next().find('.fc-totalMoney').html(totalBrandMoney);
				
				totalMoney+=parseFloat(totalBrandMoney);
			});
			$('.all-r').find('.pay.ml5').html(totalMoney);
		}else{
			$(".god-moudel-foot").each(function(){
				$(this).find('.fc-totalCount').html(0);
				$(this).find('.fc-totalMoney').html(0);
				$('.all-r').find('.pay.ml5').html(0);
			});
		}
		
		
	});
    
	/**
	 * 结算
	 */
    $("#all-btn").click(function(){ 
    	
    	if(!data_user){
            location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
            return;
        }
    	
    	$(this).html("结算中");
    	$(this).attr("disabled","disabled");
    	var array = new Array();
    	
    	$(".check").each(function() {
    		if($(this).is(":checked")){
    			array.push($(this).val());
    		}
    	});
    	if(array.length==0){
    		$(this).removeAttr("disabled");
    		$(this).html("结算");
    		alert("请选择商品");
    		return;
    	}
    	
    	var totalMoney = $('.all-r').find('.pay.ml5').text();
    	Ajax.submitForm({
    		url:config.IOrder,
    		data:{
    			"ids":array.toString(),
    			"totalMoney":totalMoney,
    			"userId":data_user
    		},
    		 type: 'POST'
    	},function(res){
    		if(res.code=="FAILED"){
    			alert(res.msg);
    		}else{
    			location.href =config.PPromptlyBuy+"?a="+res.msg;
    		}
    	});
    });
})();

