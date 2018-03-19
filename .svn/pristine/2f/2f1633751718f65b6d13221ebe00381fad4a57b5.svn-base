var rateCityId="";
function xiafa(obj){
		var $this = $(obj);
		var targetType = $this.attr("targetType");
		var ids = getIds("ids", targetType);
		//alert(ids);
		if (!ids) {
			alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
			return false;
		}else if(ids=="-1"){
			alertMsg.error("有电桩离线或者未连接");
			return false;
		}else if(ids=="-2"){
			alertMsg.error("有电桩编号不存在");
			return false;
		}else if(ids=="-3"){
			alertMsg.error("被下发的桩不在一个城市，请重新选择");
			return false;
		}else if(ids=="-4"){
			alertMsg.error("GATE服务器ID未获取，请重新选择");
			return false;
		}
		navTab.openTab("electricChooseRate", basepath+"/admin/sendRate/chooseElectricRate.do", { title:"费率选择", fresh:false, data:{ids:ids,rateCityId:rateCityId}});

}
	
	function getIds(selectedIds, targetType){
		//data-value格式:编码：上线：连接
		var ids = "";
		var gateId="";
		var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
		$box.find("input:checked").filter("[name='"+selectedIds+"']").each(function(i){	
			rateCityId = rateCityId?rateCityId:$(this).attr("city-id");
			if($(this).attr("city-id")!=rateCityId){			
				ids="-3";
				return false;
			}			
			gateId = $(this).attr("gate-id");
			if(gateId<=0){
				ids="-4";
				return false;
			}
			var val = $(this).attr("data-value");
			//电桩在线且连接
			if(val.indexOf(":15:1")>=0){
				var arr=val.split(":");
				//电桩桩体编码存在
				if(arr[0]){
					var str=arr[0];
					ids += i==0 ? str : ","+str;
				}else{
					ids="-2";
					return false;
				}
			}else{
				ids="-1";
				return false;
			}
		});
		return ids;
	}
	/*$("#electricSelProvince").change(function(){
		//初始化区县下拉框
		$("#electricSelDistrict").html('<option value="">--请选择区/县--</option>');
		var provinceId=$(this).val();
		if(!provinceId){
			$("#electricSelCity").html('<option value="">--请选择城市--</option>');
			return;
		}
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/rateinfo/searchCityList.do", 
			data: {provinceId:provinceId},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">--请选择城市--</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].CITY_ID+'">'+data[i].CITY_NAME+'</option>';
					}
					 $("#electricSelCity").html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
            }
		});
	});
	
	$("#electricSelCity").change(function(){
		var cityId=$(this).val();
		if(!cityId){
			$("#electricSelDistrict").html('<option value="">--请选择区/县--</option>');
			return;
		}
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/rateinfo/searchAreaList.do", 
			data: {cityId:cityId},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">--请选择区/县--</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].area_id+'">'+data[i].area_name+'</option>';
					}
					 $("#electricSelDistrict").html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
            }
		});
	});*/


	 
	function openDetailTab(obj){
		var url=basepath+$(obj).attr("ref");
		navTab.openTab("electricDetail", url,{title:"详情"});
	}	