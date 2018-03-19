/**
 * Created by Aaron on 2015/3/24.
 */
(function () {
	
    var id = location.href.getQueryValue('id');
    var data_user = UserService.getUserId();
    if (!id) return;
    
    /**
     * 获取产品详情
     */
    Ajax.queryDetail({
        url: config.IEnergyProductDetail + '?pkProduct=' + id
    }, function (res) {
        $('#html-text').html(res.data[0].prodParameters);
        //评论总数
        $('#comment-count').html('(' + (res.data[0].commCount || 0) + ')');
        $('#data_pic').attr("src",res.data[0].prodDetailimage);
    });


    /**
     * 获取评论列表
     */
    Ajax.pageRequest({
        url: config.ICommentList,
        data: {
            pageNumber: config.begin,
            pageNum: config.pageSize,
            prcoCommentType: CommentType.Mall,
            prCoProductId: id
        },
        renderFor: 'comment-list-tmpl',
        renderEle: '#comment-list'
    });

    /**
     * 收藏成功之后回调操作
     */
    UserService.callback = function () {
        console.log('coolect success');
    };
   
	/**
	 * 加入购物车
	 */
	$('body').on('click', '#shopCar', function(){
		//获取商品个数
		var merchandiseCount=$("#energy-spinner").val();
			if(!data_user){
		        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
		        return;
		    }
			
		 Ajax.custom({
		        url: config.IInsertIntoCart,
		        data:{
		        	'shcaProductid':id,
		        	'shcaCount':merchandiseCount
		        },
		        type: 'POST',
	            timeout: 20000
		    }, function (res) {

		    	if(res.code=="OK"){
		        	location.href = config.PShopCar;
		        }
		    });
		
	});
	/**
	 * 立即购买
	 */
	$('body').on('click', '#promptlyBuy', function(){
		if(!data_user){
	        location.href = config.PLogin + "?from=" + encodeURIComponent(location.href);
	        return;
	    }
		//获取商品个数
		var merchandiseCount=$("#energy-spinner").val();
		
		 Ajax.custom({
		        url: config.IProductOrder,
		        data:{
		        	'id':id,
		        	'shcaCount':merchandiseCount,
		        	'userId':data_user
		        },
		        type: 'POST'
		    }, function (res) {
		    	if(res.code=="OK"){
		    		location.href =config.PPromptlyBuy+"?a="+res.msg;
		        }
		    });
	});
   

    /**
     * 评价
     */
    //-----
	
    /**
     * 收藏
     */
	$('body').on('click', '#collect_submit', function(){
		if(!data_user){
	        location.href = config.PLogin + '?from=' + encodeURIComponent(location.href);
	        return;
	    }
	    Ajax.custom({
	        url:config.ICollect,
	        data:{
	        	favoProductid : id,
	        	favoUserid : data_user
	        },
            type: 'POST'
	    },function(res){
	    	alert(res.msg);
	    });
	});
	/**
	 * 分享
	 */
	var data_window;
	$('body').on('click', '.share-icon span', function(){
		var data_code = $(this).attr('data-code');
    	var data_title = $('#data_title').text();
    	//var data_summary = $('#data_address').text() + ' - ' + $('#data_tel').text();
    	var data_pic = $('#data_pic').attr('src');
    	//var data_pram = '?url=' + location.href + '&title=' + data_title + '&pic=' + data_pic;
    	var data_pram = '?url=' + location.href
    	if(data_window) data_window.close();
    	data_window = window.open ('http://api.bshare.cn/share/' + data_code + data_pram, 'sharewindow', 
    		'height=599, width=799, toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=100,left=300');
	});

})();