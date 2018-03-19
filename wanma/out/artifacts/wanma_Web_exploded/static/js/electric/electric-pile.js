/**
 * Created by Kael on 2015/3/20.
 */
(function(){

	var data_user = UserService.getUserId();
	/**
	 * 获取用户当前地区
	 */
	config.point = function(){
		var data_pot;
		Ajax.queryDetail({
			url:config.IGetPoint,
		}, function (res) {
			data_pot = res.data.point;
		});
	    return data_pot || Cookie.get(Cookie.CCITY);
	}

	/**
	 * 电桩详细
	 */
	var data_id = location.href.getQueryValue('e');//电桩Id
	config.queryDetail = function(){
		Ajax.queryDetail({
			url:config.IGetElectricPile,
			data:{
				electricPileId : data_id,
				pkUserinfo : data_user
			},
            type: 'POST'
		}, function (res) { 
			// 创建点坐标  
			var longitude = res.data[0].elpiLongitude;
			var latitude = res.data[0].elpiLatitude;
			var point;
			if(longitude && latitude){
		        point = new BMap.Point(longitude, latitude);  // 创建点坐标 
		    }else{
		    	point = config.point();
		    }
			var map = new BMap.Map('pileMap');          // 创建地图实例     
		    map.centerAndZoom(point, 18);
		    var marker = new BMap.Marker(point);  // 创建标注
		    map.addOverlay(marker);           // 将标注添加到地图中
        	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		});
	}
	config.queryDetail();

	/**
	 * 新增收藏
	 */
	$('body').on('click', '#collect_submit', function(){
		if(!data_user){
	        location.href = config.PLogin + '?from=' + encodeURIComponent(location.href);
	        return;
	    }
	    Ajax.custom({
	        url:config.ICollect,
	        data:{
	        	favoProductid : data_id,
	        	favoUserid : data_user
	        },
            type: 'POST'
	    },function(res){
	    	alert(res.msg);
	    });
	});
	
	/**
	 * 评分选择
	 */
	var data_grade;
	$('body').on('click', '.img_grade', function(){
		data_grade = $(this).index() + 1;
		$('.img_grade').each(function(){
			if($(this).index() < data_grade){
				$(this).attr('src', '../../static/images/img/star.png');
			}else{
				$(this).attr('src', '../../static/images/icon/star_gray.png');
			}
		});
	});

	/**
	 * 新增评价
	 */
	$('body').on('click', '#evaluate_submit', function(){
		var data_content = $('#prcoContent').val();
		if(!data_content) {
			alert('您还是随便说点什么吧！');
			return;
		}
		if(!data_grade) {
			alert('您还没有评分哦！');
			return;
		}
		if(!data_user){
	        location.href = config.PLogin + '?from=' + encodeURIComponent(location.href);
	        return;
	    }
		var data_name = $(this).attr('data-name');
	    Ajax.custom({
	        url:config.IInsertElectricEvaluate,
	        data:{
	        	prcoUserid : data_user,
	        	prcoCommentType : CommentType.Electric,
	        	prcoCommentstart : data_grade,
	        	prcoProductid : data_id,
	        	prcoUsername : data_name,
	        	prcoContent : data_content
	        },
            type: 'POST'
	    },function(res){
	    	if(res.code != 'OK'){
	    		alert(res.msg);
	    		return;
	    	}
	    	config.queryDetail();
	    });
	});

	/**
	 * 评价列表
	 */
	$('body').on('click', '#evaluate_detail', function(){
		var param = '?e=' + data_id + '&t=' + CommentType.Electric;
		window.location.href = config.PElectricEvaluate + param;
	});

	/**
	 * 预约详细
	 */
	$('body').on('click', '#order_detail', function(){
		var data_check = $('input[name=api]:checked').val();
		if(!data_check){
			alert('此电桩的枪口没有空闲，请选择其他电桩!');
			return;
		}
		window.location.href = config.PElectricOrder + '?e=' + data_id + '&p=' + data_check;
	});
	
	/**
	 * 分享
	 */
	var data_window;
	$('body').on('click', '.share-icon span', function(){
		var data_code = $(this).attr('data-code');
    	var data_title = $('#data_title').text();
    	var data_summary = $('#data_address').text() + ' - ' + $('#data_tel').text();
    	var data_pic = $('#data_pic').attr('src');
    	var data_pram = '?url=' + location.href + '&title=' + data_title + '&summary=' + data_summary + '&pic=' + data_pic;
    	if(data_window) data_window.close();
    	data_window = window.open ('http://api.bshare.cn/share/' + data_code + data_pram, 'sharewindow', 
    		'height=599, width=799, toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=100,left=300');
	});
	
})();