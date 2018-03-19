	var parseDatas = function() {
		var cur_li = [];
		var lb=$("#lb span.shaix_cose").attr("data-type");
		//alert(lb);
		$(".shaix_cose").each(function(i, o) {
			var cur_li_val = $(this).attr('data-type');
			cur_li[i] = (lb == 4 ? "": cur_li_val);
		});
		
		return {
			screenType :'',
			chargingMode : cur_li[0],
			screenState : 15,
			powerInterface : "",
			address:$("#address").val()
		};
	}
	map=null;
	var mapRequest = function() {
		if(!map){
			map = new AMap.Map('gdMap', {
				view: new AMap.View2D({
					//center: new AMap.LngLat(120.197428, 30.28923),
					zoom: 12,
				}),
				scrollWheel:false
			});
			//不设置城市，默认加载到当前城市
			//map.setCity(config.point());
			//在地图中添加ToolBar插件
			map.plugin(["AMap.ToolBar"],function(){		
				var toolBar = new AMap.ToolBar();
				map.addControl(toolBar);		
			});
		}else{
			map.clearMap( );
			//var mapCenter = map.getCenter();
			//alert('当前地图中心点坐标：' + mapCenter.getLng() + ',' + mapCenter.getLat());		
		}
		$.ajax({
			type : 'post',
			url : basepath+"/admin/electricPileMap/getElectricPileMapList.do",
			dataType : "json",
			data:parseDatas(),
			success : function(datas) {
				var data;
		        for (var i = 0; i<datas.length; i++) {
		        	data=datas[i];
			    	if(data.longitude && data.latitude){
			    		addMarker(data);
			    	}
			    }
		      
			}
		});
	}
	  mapRequest();
	  $("#search").click(function(){
		  mapRequest();
	    })
	//实例化点标记
	function addMarker(data){
		 
		var marker = new AMap.Marker({				  
			//icon:"http://webapi.amap.com/images/0.png",
			icon:basepath+"/static/images/map/map-"+data.electricType+".png",
			position:new AMap.LngLat(data.longitude,data.latitude),
			map: map
		});
		//鼠标点击marker弹出自定义的信息窗体
	   AMap.event.addListener(marker,'click',function(){
		   //alert(JSON.stringify(data));
		   //站弹出新页面
		   if(data.electricType==2){
			   navTab.openTab("stationDetail", 
					   basepath+"/admin/powerstation/getStationDetail.do?pkPowerstation="+data.electricId,
					   {title:"充电点详情"});
		   }else{//桩是气泡显示
			 //判断访问地址
	            var url = "#";
	            var imgSrc=basepath+"/static/images/icon/img-"+data.electricType+".png";
	            var info="";
	            //差异化信息
	            if(data.electricType==1){//桩
	            	if(data.electricState==10){
	            		info="离线"
	            	}else if(data.electricState==15){
	            		info='<a onclick="offline('+data.electricId+')">离线</a>';
	            	}else{
	            		info="【未知】";
	            	}
	            }else if(data.electricType==2){//站
	            	info='本站有<u>'+data.electricPileSum+'</u>个充电桩';
	            }else{//自行车
	            	info="";
	            }
	            var electricName=data.electricName.length>12?data.electricName.substring(0,12)+"...":data.electricName;
	            var electricAddress=data.electricAddress.length>40?data.electricAddress.substring(0,40)+"...":data.electricAddress;
	            //拼接弹窗内容
	            var content=' <dl class="zhuangLeft" style="margin-top:10px;height:120px;overflow:auto;" >'
			        +'<dd><h3 class="zhuang_title" title="'+data.electricName+'">'+electricName+'</h3></dd>'
			        +'<dd style="margin-top:5px;"><span class="zhuang_roud" title="'+data.electricAddress+'">地址：'+ electricAddress
			        +'</span></dd>'
			        +'<dd style="margin-top:5px;">'+info+'</dd>'
			        +'</dl>'
	            //构建信息窗体中显示的内容
				var infoWindow = new AMap.InfoWindow({  
					content:content,
					offset:new AMap.Pixel(8,-25), //相对于基点的位置  
					size:new AMap.Size(380, 0)
				}); 
				 infoWindow.open(map,marker.getPosition());	
		   }
	   });	
	}
	$(".shaix_cose_bai").click(function(){
		if($(this).hasClass("shaix_cose_hui")){
			return;
		}
		$(this).siblings().removeClass("shaix_cose");
		$(this).addClass("shaix_cose");
		if($(this).attr("data-type")){
			if($(this).attr("data-type")=="4"){
				$(".props span").addClass("shaix_cose_hui");
			}else{
				$(".props span").removeClass("shaix_cose_hui");
			}
		}
		mapRequest();
	});
	
	function offline(id){
		$.ajax({
			type : 'post',
			url : basepath+"/admin/electric/changeElectricPileState.do",
			dataType : "json",
			data:{changeType:"offLinePage",ids:id},
			success : function(data) {
				if(data.statusCode==200){
					alertMsg.correct(data.message);
					navTab.reloadFlag("getElectricPileMap");
				}else{
					alertMsg.error(data.message);
				}
			}
		});
	}
