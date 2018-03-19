function getToken(){
	var token="";
	$.ajax({
		type:'POST', 
		url:basepath+"/common/getToken.do", 
		dataType:'json',
		async:false,
		cache: false,
		success: function(data) {
			token=data;
        }
	});
	return token;
}

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

$(function(){
	$("body").on("change",".provinceCode",function(){
		if($(this).attr("next")){
			loadCity($(this));
		}
	});
	$("body").on("change",".cityCode",function(){
		if($(this).attr("next")){
			loadArea($(this));
		}
	});
});

function loadCity(province){
	var provinceId=province.val();
	if(!provinceId){
		return;
	}
	var cityId=province.attr("next");
	var city=$("#"+cityId);
	var cityValue=city.attr("data-val");
	if($(this).attr("next")){
		loadArea(city);
	}
	$.ajaxSettings.global=false;
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
				city.html(content);
				if(cityValue){
					$("#"+cityId+" option[value='"+cityValue+"']").attr("selected", true);
				}
				city.attr("data-val","");//清除表单返回值，让区域选择依照城市值切换
			}else{
				alertMsg.error(datas.msg);
			}
			
        }
	});
	$.ajaxSettings.global=true;
}

function loadArea(city){
	var initCityId=city.attr("data-val");
	var cityId="";
	if(initCityId){
		cityId=initCityId;
	}else{
		cityId=city.val();
	}
	if(!cityId){
		return;
	}
	var areaId=city.attr("next");
	var area=$("#"+areaId);
	var areaValue=area.attr("data-val");
	area.attr("data-val","");
	$.ajaxSettings.global=false;
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
				area.html(content);
				if(areaValue){
					$("#"+areaId+" option[value='"+areaValue+"']").attr("selected", true);
				}
			}else{
				alertMsg.error(datas.msg);
			}
			
        }
	});
	$.ajaxSettings.global=true;
}

function exportSubmit(formId,url){
	var form = navTab.getCurrentPanel().find("#"+formId);
	var oldUrl = form.attr("action");
	var oldSubmitMethod = form.attr("onsubmit");
	form.attr("onsubmit",null)
	form.attr("action",url);
	form.submit();
	form.attr("action",oldUrl);
	form.attr("onsubmit",oldSubmitMethod);
}