$(document).ready(function () {
	$("#buyPileRemind").hide();
    //车品牌类型加载
    $.ajax({
	    type: 'POST',
	    url:basepath+ "/web/paraconfig/findCarCompanyList.do",
	    dataType: "json",
	    success: function(data){	
	    			var arr1 = data.data;	
	    			var html="<option value='0'>请选择</option>";
			   		 for(var i =0;i<arr1.length;i++){	 
			   			 html+='<option value="'+arr1[i].pk_carCompany+'">'+arr1[i].carCompany_Name+'</option>';			   		
			   		 };			   		 
			   		 $("#applyQcpp").html(html);
			   		 var applyQcpp=$("#applyQcpp").attr("data-value");			   		
			   		 if(applyQcpp){
			   			$("#applyQcpp").val(applyQcpp);
			   			getModels(applyQcpp,true);
			   		 }else{
			   			 $("#applyPpcx").html("<option value='0'>请选择</option>");
			   			 return;
			   		 }			   		
			   		//alert();
  	    		}		 
	});
	//选项切换
    $('body').on('change', '#applyQcpp', function (e) {  
        e.preventDefault();
        getModels($(this).val());
    });
     
	function getModels(carCompanyId,select){
		$.ajax({
		    type: 'POST',
		    url:basepath+ "/web/paraconfig/findCarinfoList.do",
		    dataType: "json",
		    data:{carinfoCompanyId:carCompanyId},	 
		    success: function(data){
		    	if(carCompanyId!=0){
		    		var arr2 = data.data;	
			    	var html = "";
				   		 for(var i =0;i<arr2.length;i++){
				   			 html+='<option value="'+arr2[i].pkCarinfo+'">'+arr2[i].carinfoStylename+'</option>';
				   		 };  
				   		 $("#applyPpcx").html(html);
				   		 var applyPpcx=$("#applyPpcx").attr("data-value")
				   		 if(applyPpcx&&select){
				   			$("#applyPpcx").val(applyPpcx);
				   		 }		    		
		    	}else{
		    		var html = "<option value='0'>请选择</option>";			   		 
			   		$("#applyPpcx").html(html);		    		
		    		return;		    		
		    	}		    	
	  	    }		 
		});
	}
	
	$("#buyPileApply").click(function(){				
		if($("input[name='apEpAgreement']:checked").val()!=1){
			showInfo("请同意合作协议","error");			
			return;
		}
		
		if($.trim($("input[name=apEpServiceQQ]").val())){
			if($.trim($("input[name=apEpServiceQQ]").val()).length>=13){
				showInfo("客服QQ必须小于13个字","error");
				return;
			} 
			if(isNaN(parseInt($("input[name=apEpServiceQQ]").val()))){
				showInfo("客服QQ只能是数字","error");
				return;
			};
		}
		if(!$.trim($("input[name=apEpUserName]").val())){
			showInfo("请填写联系人姓名","error");
			return;
		}
		if($.trim($("input[name=apEpUserName]").val()).length>=11){
			showInfo("联系人姓名必须小于11个字","error");
			return;
		} 		
		if(!$.trim($("input[name=apEpUserPhone]").val())){
			showInfo("请填写联系电话","error");
			return;
		}
		var applyPileMobile = $.trim($("input[name=apEpUserPhone]").val());
		 if(!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(applyPileMobile) || /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|17[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/.test(applyPileMobile))){
			 showInfo("请正确填写电话格式","error")
			 $("input[name=apEpUserPhone]").focus();
			 return;
		 }
		if(isNaN(parseInt($("input[name=apEpUserPhone]").val()))){
			showInfo("请正确填写电话格式","error");
			return;
		};
		if(!(($.trim($("input[name=apEpProvinceAddress]").val()))&&($.trim($("input[name=apEpCityAddress]").val()))&&($.trim($("input[name=apEpAreaAddress]").val()))&&($.trim($("input[name=apEpStreetAddress]").val()))&&($.trim($("input[name=apEpRoomNumber]").val())))){
			showInfo("请填写完整地址","error");
			return;
		}
		if($.trim($("input[name=apEpProvinceAddress]").val()).length>5){
			showInfo("省份名字数必须小于6","error");
			return;
		}
		if($.trim($("input[name=apEpCityAddress]").val()).length>7){
			showInfo("城市名字数必须小于8","error");
			return;
		}
		if($.trim($("input[name=apEpAreaAddress]").val()).length>7){
			showInfo("地区名字数必须小于8","error");
			return;
		}
		if($.trim($("input[name=apEpStreetAddress]").val()).length>9){
			showInfo("街道名字数必须小于10","error");
			return;
		}
		if($.trim($("input[name=apEpRoomNumber]").val()).length>19){
			showInfo("车位具体位置名字数必须小于20","error");
			return;
		}
		
		$("#apEpPointAddress").val($("#apEpProvinceAddress").val()+"省"+$("#apEpCityAddress").val()+"市"+$("#apEpAreaAddress").val()+"区"+$("#apEpStreetAddress").val()+"街道"+$("#apEpRoomNumber").val());

		if($("#applyQcpp option:selected").val()==0){
			showInfo("请选择汽车品牌","error");
			return;
		}
		if($("#applyPpcx option:selected").val()==0){
			showInfo("请选择车辆型号","error");
			return;
		}
		if(!($("input[name=apEpPropertyNature]:checked").val()>0)){
			showInfo("请选择物业性质","error");
			return;
		}
		if(!($("input[name=apEpParkingPlace]:checked").val()>=0)){
			showInfo("请选择车位位置","error");
			return;
		}
		if(!($("input[name=apEpOpenShare]:checked").val()>=1)){
			showInfo("请选择是否对外开放","error");
			return;
		}
		if($.trim($("input[name=apEpCapacitance]").val()).length>=8){
			showInfo("可配电容量字数必须小于8","error");
			return;
		}
		
		if($("input[name=apEpParkingOwner]:checked").val()==2){
			if($.trim($("input[name=apEpParkingOwnerYear]").val()).length>=6){
				showInfo("车位所属权字数必须小于6","error");
				return;
			};
			$("input[name=apEpParkingOwnerText]").val($("#apEpParkingOwnerYear").val());
		}else if($("input[name=apEpParkingOwner]:checked").val()==3){
			if($.trim($("input[name=apEpParkingOwnerOther]").val()).length>=6){
				showInfo("车位所属权字数必须小于6","error");
				return;
			};
			$("input[name=apEpParkingOwnerText]").val($("#apEpParkingOwnerOther").val());
		}
		if($("input[name=apEpPowerPosition]:checked").val()==3){
			if($.trim($("input[name=apEpPowerPositionOther]").val()).length>=12){
				showInfo("电源位置字数必须小于12","error");
				return;
			};
			$("input[name=apEpPowerPositionText]").val($("#apEpPowerPositionOther").val())
		}
		if($("input[name=apEpDistance]:checked").val()==3){
			if($.trim($("input[name=apEpDistanceOther]").val()).length>=12){
				showInfo("电源到车位距离字数必须小于12","error");
				return;
			};
			$("input[name=apEpDistanceText]").val($("#apEpDistanceOther").val())
		}
		$.ajax({
	         type: 'POST',
	         url: basepath+"/admin/applyElecPile/insert.do" ,
	         dataType: "json",
	         data:$("#applyElecPileForm").serialize(),
	         success: function(data){
	        		$("#buyPileRemind").show();
	        		$("#buyPileForm").hide();
	         }
	    });	

	});
});