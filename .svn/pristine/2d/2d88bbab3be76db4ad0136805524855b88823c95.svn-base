<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<h2 class="contentTitle"><bmtag:message messageKey="新增费率"/></h2>
<div class="pageContent">
<form id="form" method="post" action="rateinfo/addRateInfo.do" class="pageForm required-validate" 
	 onsubmit="return validateCallback(this, navTabAjaxDone)">
	<input id="raIn_QuantumDate" name="raIn_QuantumDate" type="hidden">
	<input id="raIn_CityFeeLimit"  value="" type="hidden">
	<input id="raInInfo_AreaId"  value="" type="hidden">
	<div class="pageFormContent nowrap" layoutH="97">	
		<tr>
				<td>
					<label>区域选择</label>
				</td>
				<td colspan="2">
							<select  id="RateAddselProvince" name="raIn_ProvinceId" class="required" style="float: none">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${provinceMap}">
									<option value="${item.key}"
										${item.key== raIn_ProvinceId ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
							</select>
							<select id="RateAddselCity" name="raIn_CityId" class="required" style="float: none;">
								 <option value="">--请选择城市--</option>
								 <c:forEach var="item" items="${cityList}">
									<option value="${item.CITY_ID}"
										${item.CITY_ID==raIn_CityId  ? 'selected="selected"' : ''} >
										${item.CITY_NAME}</option>
								</c:forEach>
							</select>
							<select id="RateAddselDistrict" name="raIn_AreaId" class="required" name="startm" style="float: none;">
								<option value="">--请选择区/县--</option>
								<c:forEach var="item" items="${areaList}">
									<option value="${item.area_id}"
										${item.area_id==raIn_AreaId ? 'selected="selected"' : ''} >
										${item.area_name}</option>
								</c:forEach>
							</select>	
				</td>
		</tr>
		 <!--  
		  <dl><dt><bmtag:message messageKey="rateinfo.label.effdate"/></dt>
			<dd>
	
			 <input id="raIn_EffectiveDates" value="2015-01-01" name="raIn_EffectiveDates"  class=" required"  style="width:130px" type="text"
			 readonly="true" 
						 onClick="WdatePicker({el:'raIn_EffectiveDates',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'raIn_ExpiryDates\')}'})" >
			             
			<span class="info"></span></dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="rateinfo.label.expdate"/></dt>
			<dd>
			<input id="raIn_ExpiryDates"  value="2015-01-02" name="raIn_ExpiryDate"  class=" required"  style="width:130px"
			type="text"  readonly="true" 
			             onClick="WdatePicker({el:'raIn_ExpiryDates',minDate:'#F{$dp.$D(\'raIn_EffectiveDates\')}'})"> 
			<span class="info"></span></dd>
		</dl>	
		-->	
		<dl>
		<dt><bmtag:message messageKey="费率位数"/></dt>
		<dd><label><input type="radio" id="radio1" name="raInType"  value=1>两位</label>
		    <label><input type="radio" name="raInType"  value=2 />四位</label></dd>
		</dl>
		<dl>
		
			<dt><bmtag:message messageKey="尖段电价 / 度"/></dt><dd>
			<input name="raIn_TipTimeTariff" id="raIn_Tip_Electricity"  class="textInput required" maxlength="6" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="峰段电价 / 度"/></dt><dd>
			<input name="raIn_PeakElectricityPrice" id="raIn_Peak_Electricity"   class="textInput required" maxlength="6" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="平段电价 / 度"/></dt><dd>
			<input name="raIn_UsualPrice" id="raIn_Flat_Electricity"   class="textInput required" maxlength="6" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="谷段电价 / 度"/></dt><dd>
			<input name="raIn_ValleyTimePrice" id="raIn_Valley_Electricity"   class="textInput required " maxlength="6" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl><dt><bmtag:message messageKey="rateinfo.label.reserate"/> / 分钟</dt><dd>
			<input name="raIn_ReservationRate" id="raIn_ReservationRate"  class="textInput required number" maxlength="5" style="width:130px;"/>
			<span class="info"></span></dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="rateinfo.label.servicecharge"/> / 度</dt><dd>
			<input name="raIn_ServiceCharge" id="raIn_ServiceCharge"   class="textInput required number"  maxlength="6" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey=""/>备注</dt><dd>
			<textarea name="raInRemarks" id="raInChange_raInRemarks"  maxlength="50" cols="25" rows="4"></textarea>
			<span class="info"></span>
			</dd>
		</dl>
		<div>&nbsp;</div>
		<div style="float: left;vertical-align: middle; padding-left: 5px;">
				时间段设置
		</div>
		<div id="jsondiv">
			<div id="datainput" name="datainput" style="margin-left: 130px;">
				<select name="mark" class="request" style="float: none">
					<option value="1">尖</option>
					<option value="2">峰</option>
					<option value="3">平</option>
					<option value="4">谷</option>
				</select>&nbsp;&nbsp;&nbsp;
				<select name="starth" style="float: none;">
					<c:forEach var="x" begin="0" end="24"><option value="${x}" >${x}</option></c:forEach>
				</select>&nbsp;时
				<select name="startm" style="float: none;">
					<c:forEach var="x" begin="0" end="59"><option value="${x}">${x}</option></c:forEach>
				</select>&nbsp;分
				&nbsp;&nbsp;--&nbsp;&nbsp;
				<select name="endh" style="float: none;">
					<c:forEach var="x" begin="0" end="24"><option value="${x}">${x}</option></c:forEach>
				</select>&nbsp;时
				<select name="endm" style="float: none;">
					<c:forEach var="x" begin="0" end="59"><option value="${x}">${x}</option></c:forEach>
				</select>&nbsp;分
				
			</div>
			
		</div>
		<div style="margin-left: 200px;margin-top: 15px;">
			<input id="continue" name="continue" type="button" value="继续设置" onclick="continueadd()">
		</div>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="button" onclick="formatJson()" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>

<script type="text/javascript">

$("#radio1").attr("checked","checked");

	var basepath="<%=basePath%>";		
	function checkTimePart(){
		var raInReservationRate = $("#raIn_ReservationRate").val();
		var	cityFeeLimit = $("#raIn_CityFeeLimit").val();		
		var serviceCharge = $("#raIn_ServiceCharge").val();	
		var AreaSelect = $("#raInInfo_AreaId").val();
		if(AreaSelect!=''){
			if(parseFloat(raInReservationRate)>parseFloat(10)){
				alert("您输入的预约单价高于上限: 10元，请重新输入");
				return;
			}
			if(parseFloat(serviceCharge)>parseFloat(cityFeeLimit)){
				alert("您输入的服务费高于该地区服务费上限:"+cityFeeLimit+"元，请重新输入");
				return;
			}
		}else{
			alert("请选择区域");
				return;
		}
		
	/* 	var raIn_EffectiveDates = $("#raIn_EffectiveDates").val();
		var raIn_ExpiryDates = $("#raIn_ExpiryDates").val();
		if(raIn_EffectiveDates >= raIn_ExpiryDates){
			alert("失效时间不能小于或等于生效时间，请重新设置");
			return false;
		} */
		
		var firstEndH = "";
		var firstEndM = "";
		var firstEndHT = "";
		var firstEndMT = "";
		var secondStartH = "";
		var secondStartM = "";
		var boo = 1;
		//遍历每行时间
		$("div [name='datainput']").each(function(index){
			//遍历每行的每个select
			$(this).children("select").each(function(cindex){
				var name = $(this).attr("name");
				//将每行的开始时间存放起来
				if(name == "starth"){
					secondStartH = $(this).val();
				}else if(name == "startm"){
					secondStartM = $(this).val();
				}else if(name == "endh"){
					firstEndH = $(this).val();
				}else if(name == "endm"){
					firstEndM = $(this).val();
				}
			});
			
			//第一行的开始时间必须从0点开始			
			if(index == 0){
				if(secondStartH != 0 || secondStartM != 0){
					alert("费率的起始时间必须从每天0点开始，请重新设置");
					boo = 0;
					return false;
				}
			}
			if((parseInt(secondStartH) * 60 + parseInt(secondStartM)) >= (parseInt(firstEndH) * 60 + parseInt(firstEndM))){
				//alert(((secondStartH * 60 + secondStartM) >= (firstEndH * 60 + firstEndM)) + "||" + (secondStartH * 60 + secondStartM) + "--" + (firstEndH * 60 + firstEndM));
				alert("时间段设置--同行的结束时间必须大于开始时间，请重新设置");
				boo = 0;
				return false;
			}
			//在有多行的时候再开始判断
			if(index > 0){
				//alert(secondStartH + ":" + secondStartM + "--" + firstEndHT + ":" + firstEndMT);
				//上一次的结束时间不等于这一次的开始时间的话给出提示
				if(secondStartH != firstEndHT || secondStartM != firstEndMT){
					alert("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置");
					boo = 0;
					return false;
				}
				firstEndHT = "";
				firstEndMT = "";
			}
			//将本行最后的时间保存下来，用来与下一行的开始时间比对
			firstEndHT = firstEndH;
			firstEndMT = firstEndM;
		});
		
		//费率的结束时间必须是每天24点
		if(firstEndH != "24"){
			alert("费率的最终结束时间必须为每天的24点，请重新设置");
			boo = 0;
			return false;
		}
		if(boo == 0){
			returnValue = false;
			return false;
		}
		else
			return true;
	}
	
	function formatJson(){
		var boo = checkTimePart(); 
		if(!boo){
			return false;
		}
		var json = "{\"data\":["; 
		//遍历每行
		$("div [name='datainput']").each(function(index){
			var st = 0;
			var et = 0;
			var mark = "";
			json += "{";
			$(this).children("select").each(function(cindex){
				var name = $(this).attr("name");
				//将每行的开始时间存放起来
				if(name == "starth"){
					st = $(this).val() * 60;
				}else if(name == "startm"){
					st = "\"" + (parseInt(st) + parseInt($(this).val())) +"\"";
				}else if(name == "endh"){
					et = $(this).val() * 60;
				}else if(name == "endm"){
					et = "\"" + (parseInt(et) + parseInt($(this).val())) + "\"";
				}else if(name = "mark"){
					mark = $(this).val();
				}
			});
			json += "\"st\":" + st + ",\"et\":" + et + ",\"mark\":" + "\"" + mark + "\"";
			json += "},";
		});
		json = json.substring(0,json.length - 1);
		json += "]}";      
		$("#raIn_QuantumDate").val(json);

		//return false;
		$("#form").submit();
	}
	
	function continueadd(){
		var html = "<div name='datainput' style='margin-left: 130px;padding-top: 10px;'>";
		html += $("#jsondiv div:first-child").html();
		html += "&nbsp;&nbsp;<input id='del' name='del' value='移除' onclick='removele(this)' type='button'></div>"
		$("#jsondiv").append(html);	
	}
	
	function removele(obj){
		$(obj).parent().remove();
		
	};
        
         $("#RateAddselProvince").change(function(){
    	  	 $("#RateAddselDistrict").html('<option value="">--请选择区/县--</option>');
    		var provinceId=$(this).val();   		
    		if(!provinceId){
    			$("#RateAddselCity").html('<option value="">--请选择城市--</option>');
    			return;
    		}
    		$.ajax({
    			type:'POST', 
    			url:basepath+"/admin/rateinfo/searchProvinceList.do", 
    			data: {provinceId:provinceId},
    			dataType:'json',
    			cache: false,
    			success: function(datas) {
    				if(datas.status==100){
    					var data=datas.data;
    					$("#raIn_Tip_Electricity").val(data[0].Tip_Electricity);
   						$("#raIn_Peak_Electricity").val(data[0].Peak_Electricity);
   						$("#raIn_Flat_Electricity").val(data[0].Flat_Electricity);
   						$("#raIn_Valley_Electricity").val(data[0].Valley_Electricity);
    				}else{
    					alertMsg.error(datas.msg);
    				}
    				
               }
    			
    		});
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
    						content+='<option  data-value="'+data[i].serviceLimit+ '" value="'+data[i].CITY_ID+'">'+data[i].CITY_NAME+'</option>';
    						
    					}
    					 $("#RateAddselCity").html(content);
    				}else{
    					alertMsg.error(datas.msg);
    				}
    				
               }
    		});
    	});
    	
    	$("#RateAddselCity").change(function(){
    		var feeLimit = $("#RateAddselCity  option:selected").attr("data-value"); 
        	$("#raIn_CityFeeLimit").val(feeLimit); 
    		var cityId=$(this).val();
    		if(!cityId){
    			$("#RateAddselDistrict").html('<option value="">--请选择区/县--</option>');
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
    					 $("#RateAddselDistrict").html(content);
    				}else{
    					alertMsg.error(datas.msg);
    				}
    				
               }
    		});
    	});
    	
    	  $("#RateAddselDistrict").change(function(){
         	 var RateEaId = $("#RateAddselDistrict").val();
              $("#raInInfo_AreaId").val(RateEaId);
         	 
          })
$("#raIn_Tip_Electricity").blur(function(){
	var value = $("#raIn_Tip_Electricity").val();	
	
	var msg="";
	
	var raInType=$('input:radio[name="raInType"]:checked').val();
	if(raInType==1){
		var reg = /^[0-3]+(.[0-9]{2})$/;
		msg="费率不能大于3.00且必须保留2位小数点";
	}
	else{
		var reg = /^[0-3]+(.[0-9]{4})$/;
		msg="费率不能大于3.00且必须保留4位小数点";
	}
	
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raIn_Tip_Electricity").val("");
		alert(msg)
	}
		 	  })
$("#raIn_Peak_Electricity").blur(function(){
	var value = $("#raIn_Peak_Electricity").val();	
     var msg="";
	
	var raInType=$('input:radio[name="raInType"]:checked').val();
	if(raInType==1){
		var reg = /^[0-3]+(.[0-9]{2})$/;
		msg="费率不能大于3.00且必须保留2位小数点";
	}
	else{
		var reg = /^[0-3]+(.[0-9]{4})$/;
		msg="费率不能大于3.00且必须保留4位小数点";
	}
	

	
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raIn_Peak_Electricity").val("");
		alert(msg)
	}
		 	  })
$("#raIn_Flat_Electricity").blur(function(){
	var value = $("#raIn_Flat_Electricity").val();	
    var msg="";
	var raInType=$('input:radio[name="raInType"]:checked').val();
	if(raInType==1){
		var reg = /^[0-3]+(.[0-9]{2})$/;
		msg="费率不能大于3.00且必须保留2位小数点";
	}
	else{
		var reg = /^[0-3]+(.[0-9]{4})$/;
		msg="费率不能大于3.00且必须保留4位小数点";
	}
	
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raIn_Flat_Electricity").val("");
		alert(msg)
	}
		 	  })
$("#raIn_Valley_Electricity").blur(function(){
	var value = $("#raIn_Valley_Electricity").val();	
    var msg="";
	
	var raInType=$('input:radio[name="raInType"]:checked').val();
	if(raInType==1){
		var reg = /^[0-3]+(.[0-9]{2})$/;
		msg="费率不能大于3.00且必须保留2位小数点";
	}
	else{
		var reg = /^[0-3]+(.[0-9]{4})$/;
		msg="费率不能大于3.00且必须保留4位小数点";
	}
	
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raIn_Valley_Electricity").val("");
		alert(msg)
	}
		 	  })
	

 
</script>