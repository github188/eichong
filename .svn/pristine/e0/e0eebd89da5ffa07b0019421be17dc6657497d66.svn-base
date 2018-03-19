/**
 * Created by Kael on 2015/3/20.
 */
(function(){
	
	/**
	 * 电桩筛选条件
	 */
	var cur_li_fir;
	config.data = function(){
		var cur_li = [];
    	$(".cur_li").each(function (i, o) {
    		var cur_li_val = $(this).attr('data-type');
    		cur_li[i] = (cur_li_fir == 4 ? 0 : cur_li_val);
    	});
		return {
			pageNumber : config.begin,
			pageNum : config.pageSize,
			searchPoweruser : cur_li_fir,
			searchCharging : cur_li[1],
			searchStatus : cur_li[2],
			searchInterface : cur_li[3]
		};
	}

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
	 * 获取图片地址
	 */
	config.absImg = function (imgUrl) {
        if (!imgUrl){
        	return config.defaultImg;
        }
        if (imgUrl && imgUrl.indexOf('http://') == 0){
        	return imgUrl;
        }
        return config.imageServer + imgUrl;
    };
	
	/**
	 * 电桩查找 列表模式
	 */
	Ajax.pageRequest({
		url:config.IFindScreen,
        renderFor: 'screen-list-tmpl',
        renderEle: '#screen-list'
	});
	
	/**
	 * 电桩查找 列表模式
	 */
	config.pageSize = 6;
	config.pageRequest = function(){
    	Ajax.pageRequest({
    		url:config.IFindElectricSearch,
    		data:config.data(),
            type: 'POST'
    	});
	}
	config.pageRequest();
	
	/**
	 * 电桩查找 地图模式
	 */
	config.mapRequest = function(){
		Ajax.pageRequest({
			url:config.IFindElectricMapSearch,
			data:config.data(),
            type: 'POST'
		}, function (res) {
			var map = new BMap.Map('allMap');          // 创建地图实例     
	        map.centerAndZoom('上海市虹口区', 15);
	       //map.centerAndZoom(config.point(), 15);
	        map.enableScrollWheelZoom();
	        
	        var datas = res.data;
	        for (var i = 0, data; data = datas[i]; i++) {
		    	if(data.longitude && data.latitude){
		            //判断访问地址
		            var url = (data.electricType == 1 ? config.PElectricPile : config.PElectricPlant);
		            //拼接弹窗内容
		            var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
						'<img src="' + config.absImg(data.electricImage) + '" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
	            		'地址：' + data.electricAddress + '<br/><br/><a style="font-size: 13px;color: rgb(61, 109, 204);margin-left: 5px;" href="' + url + '?e=' + data.electricId + '">查看详情>></a></div>';

		    		var point = new BMap.Point(data.longitude, data.latitude);  // 创建点坐标 
		            //判断标注偏移
		    		var px = (data.electricType == 1 ? -275 : -250);
		    		var icon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {
	                        offset: new BMap.Size(10, 25), // 指定定位位置  
	                        imageOffset: new BMap.Size(0, px) // 设置图片偏移  
	                    });  
		            var marker = new BMap.Marker(point, {icon:icon});  // 创建标注
		            map.addOverlay(marker);               // 将标注添加到地图中
		            
		            addClickHandler(data.electricName, content ,marker);
		    	}
		    }
		    function addClickHandler(title, content, marker){
				marker.addEventListener("click",function(e){openInfo(title, content, e)});
		    }
			function openInfo(title, content, e){
		    	var opts = {
						width : 250,     // 信息窗口宽度
						height: 120,     // 信息窗口高度
						title : title, // 信息窗口标题
						enableMessage:true//设置允许信息窗发送短息
				};
				var p = e.target;
				var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
				var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象 
				map.openInfoWindow(infoWindow, point); //开启信息窗口
			}
		});
	}
	
	/**
     * 内容切换
     */
	var data_title;
    $('.search-title li').click(function(){
        $(this).addClass('current').siblings('li').removeClass('current');
        var liindex = $(this).index();
        $('.toggle').eq(liindex).show().siblings('.none').hide();
        
        data_title = $(this).attr('data-title');
    	$('#title').html($(this).text());//根据切换调整标题
    	//判断切换的是地图模式
        if(data_title){
        	config.mapRequest();
        	$('#page-dom').hide();
        }else{
        	config.pageRequest();
	    	$('#page-dom').show();
        }
    });
    
	/**
     * 电桩查找
     */
    $('body').on('click', '.search_li li', function(){
    	if(!$(this).hasClass('ok') && !$(this).hasClass('no')){
			$(this).addClass('current cur_li').siblings('li').removeClass('current cur_li');	

			//判断选取的第一个是否电动自行车
			cur_li_fir = $('.cur_li:first').attr('data-type');
			if(cur_li_fir == 4){
				$('.search_li:not(:first) li').addClass('no');
				$('.search_li:not(:first)').fadeTo("fast",0.5);
			}else{
				$('.search_li:not(:first) li').removeClass('no');
				$('.search_li:not(:first)').fadeTo("fast",1);
			}
			//判断切换的是地图模式
			if(data_title){
		    	config.mapRequest();
	        }else{
	    		config.begin = 1;
	        	config.pageRequest();
	        }
    	}
    });

	/**
	 * 电桩/充电点详情
	 */
	$('body').on('click', '#pile_detail', function(){
		var data_type = $(this).attr('data-type');
		var url = (data_type == 2 ? config.PElectricPlant : config.PElectricPile);
		window.location.href = url + '?e=' + $(this).attr('data-id');
	});
	
})();