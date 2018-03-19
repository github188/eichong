<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<h2 class="contentTitle"><bmtag:message messageKey="编辑费率"/></h2>
<div class="pageContent">
	<form method="post" action="rateinfo/updateRateInfo.do" id="meditForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" id="RateChange_AreaId"  value="${rateMap.raIn_area_id}"/>
		<input type="hidden" id="RateChange_CityId"  value="${rateMap.raIn_city_id}" />
		<input type="hidden" id="RateChange _PriovinceId"  value="${rateMap.raIn_province_id}" />
		<input type="hidden" name="pk_RateInformation" value="${rateMap.pk_RateInformation}" />
		<input type="hidden" id="raIn_QuantumDateEdit" name="raIn_QuantumDate" value='${rateMap.raIn_QuantumDate}'/>
		<input type="hidden" id="userId" name="userId" value="${rateMap.userId}" />
		<input type="hidden" name="raIn_MinFreezingMoney" value='${rateMap.raIn_MinFreezingMoney}'/>
		<input type="hidden" name="raIn_WarnMoney" value='${rateMap.raIn_WarnMoney}'/>
		<input type="hidden" id="relevanceNumber" value='${rateMap.relevanceNumber}'/>
		<div class="pageFormContent nowrap" layoutH="100">
		<tr>
				<td>
					<label>区域选择</label>
				</td>
				<td colspan="2">
							<select  id="RateChangeselProvince" name="raIn_ProvinceId" class="required" style="float: none">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${provinceMap}">
									<option value="${item.key}"
										${item.key== rateMap.raIn_province_id ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
							</select>
							<select id="RateChangeselCity" name="raIn_CityId" class="required" style="float: none;">
								 <option value="">--请选择城市--</option>
								 <c:forEach var="item" items="${cityList}">
									<option value="${item.CITY_ID}"   data-value = "${rateMap.serviceLimit}"
										${item.CITY_ID==rateMap.raIn_city_id  ? 'selected="selected"' : ''} >
										${item.CITY_NAME}</option>
								</c:forEach>
							</select>
							<select id="RateChangeselDistrict" name="raIn_AreaId" class="required" name="" style="float: none;">
								<option value="">--请选择区/县--</option>
								<c:forEach var="item" items="${areaList}">
									<option value="${item.area_id}"
										${item.area_id==rateMap.raIn_area_id ? 'selected="selected"' : ''} >
										${item.area_name}</option>
								</c:forEach>
							</select>	
				</td>
		</tr>
		<!--  
		<dl><dt><bmtag:message messageKey="rateinfo.label.effdate"/></dt>
			<dd>			
					 <input id="raIn_EffectiveDate" name="raIn_EffectiveDates"  class="required"  style="width:130px" type="text"
			 readonly="true" value="${rateMap.raIn_EffectiveDates}"
						 onClick="WdatePicker({el:'raIn_EffectiveDate',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'raIn_ExpiryDate\')}'})" >
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="rateinfo.label.expdate"/></dt><dd>			
						<input id="raIn_ExpiryDate" name="raIn_ExpiryDate"  class="required"  style="width:130px"
			type="text"  readonly="true"  value="${rateMap.raIn_ExpiryDate}"
			             onClick="WdatePicker({el:'raIn_ExpiryDate',minDate:'#F{$dp.$D(\'raIn_EffectiveDate\')}'})"> 
			<span class="info"></span></dd>
		</dl>
		-->
		<dl>
		
			<dt><bmtag:message messageKey="尖段电价 / 度"/></dt><dd>
			<input name="raIn_TipTimeTariff" id="raInChange_Tip_Electricity" value="${rateMap.raIn_TipTimeTariff}"   class="textInput required"  maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="峰段电价 / 度"/></dt><dd>
			<input name="raIn_PeakElectricityPrice" id="raInChange_Peak_Electricity" value="${rateMap.raIn_PeakElectricityPrice}"  class="textInput required" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="平段电价 / 度"/></dt><dd>
			<input name="raIn_UsualPrice" id="raInChange_Flat_Electricity" value="${rateMap.raIn_UsualPrice}"  class="textInput required" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="谷段电价 / 度"/></dt><dd>
			<input name="raIn_ValleyTimePrice" id="raInChange_Valley_Electricity" value="${rateMap.raIn_ValleyTimePrice}"   class="textInput required" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl><dt><bmtag:message messageKey="rateinfo.label.reserate"/> / 分钟</dt><dd>
			<input name="raIn_ReservationRate" id="raIn_ReservationRate"  class="textInput required number" 
			value="${rateMap.raIn_ReservationRate}" maxlength="5" style="width:130px;"/>
			<span class="info"></span></dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="rateinfo.label.servicecharge"/> / 度</dt><dd>
			<input name="raIn_ServiceCharge" id="raIn_ServiceCharge"  class="textInput required number" 
			value="${rateMap.raIn_ServiceCharge}" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="备注"/></dt><dd>
			<textarea name="raInRemarks" id="raInChange_raInRemarks" value=""  maxlength="50" cols="25" rows="4">${rateMap.raInRemarks}</textarea>
			<span class="info"></span>
			</dd>
		</dl>
		<div style="float: left;vertical-align: middle; padding-left: 5px; margin-top: 10px;">
				时间段设置
		</div>
		<div id="mjsondiv" style="padding-top: 10px;">
			
			
		</div>
		<div style="margin-left: 200px;margin-top: 15px;">
			<input id="continue" name="continue" type="button" value="继续设置" onclick="mcontinueadd()">
		</div>
	</div>					
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="button" onclick="modFormatJson()">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消	</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>	
</div>

<div id="mdatainputtemp" name="mdatainputtemp" style="margin-left: 130px;display: none;">
	<select name="mmark" class="request" style="float: none">
		<option value="1">尖</option>
		<option value="2">峰</option>
		<option value="3">平</option>
		<option value="4">谷</option>
	</select>&nbsp;&nbsp;&nbsp;
	<select name="mstarth" style="float: none;">
		<c:forEach var="x" begin="0" end="24"><option value="${x}" >${x}</option></c:forEach>
	</select>&nbsp;时
	<select name="mstartm" style="float: none;">
		<c:forEach var="x" begin="0" end="59"><option value="${x}">${x}</option></c:forEach>
	</select>&nbsp;分
	&nbsp;&nbsp;--&nbsp;&nbsp;
	<select name="mendh" style="float: none;">
		<c:forEach var="x" begin="0" end="24"><option value="${x}">${x}</option></c:forEach>
	</select>&nbsp;时
	<select name="mendm" style="float: none;">
		<c:forEach var="x" begin="0" end="59"><option value="${x}">${x}</option></c:forEach>
	</select>&nbsp;分
	
</div>
	
<script>		
	//页面加载完后解析费率，并展示出来
	var feeLimit = null;
	$(document).ready(function(){ 

		var rateJson = ${rateMap.raIn_QuantumDate};

		$.each(rateJson.data,function(i,item){
			//添加行
			mcontinueadd(i);
			//使用子元素方式获取当前行对象
			var line = $("#mjsondiv").children().eq(i);
			//设置尖峰平谷
			
			$(line).children().eq(0).val(item.mark);
			//设置开始时间
			var rslt = item.st / 60;
			var st = Math.floor(rslt);
			$(line).children().eq(1).val(st);
			$(line).children().eq(2).val(item.st - st*60);
			//设置结束时间
			var rsltend = item.et / 60; 
			var et = Math.floor(rsltend); 
			$(line).children().eq(3).val(et);
			$(line).children().eq(4).val(item.et - et*60);
			
			var relevanceNumber = $("#relevanceNumber").val();
			 if(relevanceNumber>0){
				$("#RateChangeselProvince").attr("disabled","disabled");
				$("#RateChangeselCity").attr("disabled","disabled");
				$("#RateChangeselDistrict").attr("disabled","disabled");
			} 
		});
		
	});
	
	function mcontinueadd(i){
		var html = "<div name='mdatainput' style='margin-left: 130px;padding-top: 10px;'>";
		html += $("#mdatainputtemp").html();
		html += "&nbsp;&nbsp;<input id='mdel' name='mdel' value='移除' onclick='removele(this," + i +")' ";
		html += "type='button'>";
		html += "</div>";
		$("#mjsondiv").append(html);
	}
	
	function removele(obj,i){
		//alert($(obj).parent().parent().html());
		var childNum = $(obj).parent().parent().children().length;
		if(childNum == 1){
			alert("请至少保留一条时间设置。");
			returnValue = false;
			return false;
		}
		$(obj).parent().remove();
	}
	
	function modCheckTimePart(){
		var raInReservationRate = $("#raIn_ReservationRate").val();	
		var cityFeeLimit= $("#RateChangeselCity  option:selected").attr("data-value"); 
		var serviceCharge = $("#raIn_ServiceCharge").val();	
		var AreaSelect = $("#RateChangeselDistrict").val();
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
		
/* 	var raIn_EffectiveDate = $("#raIn_EffectiveDate").val();
		var raIn_ExpiryDate = $("#raIn_ExpiryDate").val();
		if(raIn_EffectiveDate >= raIn_ExpiryDate){
			alert("失效时间不能小于或等于生效时间，请重新设置");
			return false;
		}
		 */
		
		var firstEndH = "";
		var firstEndM = "";
		var firstEndHT = "";
		var firstEndMT = "";
		var secondStartH = "";
		var secondStartM = "";
		var boo = 1;
		//遍历每行时间
		$("div [name='mdatainput']").each(function(index){
			//遍历每行的每个select
			$(this).children("select").each(function(cindex){
				var name = $(this).attr("name");
				//将每行的开始时间存放起来
				if(name == "mstarth"){
					secondStartH = $(this).val();
				}else if(name == "mstartm"){
					secondStartM = $(this).val();
				}else if(name == "mendh"){
					firstEndH = $(this).val();
				}else if(name == "mendm"){
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
			
			//alert(8") > eval_r("12"));
			if((parseInt(secondStartH) * 60 + parseInt(secondStartM)) >= (parseInt(firstEndH) * 60 + parseInt(firstEndM))){
				//alert(secondStartH + ":" + secondStartM >= firstEndH + ":" + firstEndM);
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
		
		var endHObjs = document.getElementsByName("mendh");
		//费率的结束时间必须是每天24点
		if(endHObjs[endHObjs.length-2].value != "24"){
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
	
	function modFormatJson(){
		
		
		$("#RateChangeselProvince").attr("disabled",false);
		$("#RateChangeselCity").attr("disabled",false);
		$("#RateChangeselDistrict").attr("disabled",false);
		var boo = modCheckTimePart();
		if(!boo){
			return false;
		}
		var json = "{\"data\":["; 
		//遍历每行
		$("div [name='mdatainput']").each(function(index){
			var st = 0;
			var et = 0;
			var mark = "";
			json += "{";
			$(this).children("select").each(function(cindex){
				var name = $(this).attr("name");
				//将每行的开始时间存放起来
				if(name == "mstarth"){
					st = $(this).val() * 60;
				}else if(name == "mstartm"){
					st = "\"" + (parseInt(st) + parseInt($(this).val())) +"\"";
				}else if(name == "mendh"){
					et = $(this).val() * 60;
				}else if(name == "mendm"){
					et = "\"" + (parseInt(et) + parseInt($(this).val())) + "\"";
				}else if(name = "mmark"){
					mark = $(this).val();
				}
			});
			json += "\"st\":" + st + ",\"et\":" + et + ",\"mark\":" + "\"" + mark + "\"";
			json += "},";
		});
		json = json.substring(0,json.length - 1);
		json += "]}";
		$("#raIn_QuantumDateEdit").val(json);
		//return false;
		$("#meditForm").submit();	
	}
	
		  $("#RateChangeselProvince").change(function(){
		 	  	 $("#RateChangeselDistrict").html('<option value="">--请选择区/县--</option>');
		 		var provinceId=$(this).val();
		 		if(!provinceId){
		 			$("#RateChangeselCity").html('<option value="">--请选择城市--</option>');
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
	    					$("#raInChange_Tip_Electricity").val(data[0].Tip_Electricity);
	   						$("#raInChange_Peak_Electricity").val(data[0].Peak_Electricity);
	   						$("#raInChange_Flat_Electricity").val(data[0].Flat_Electricity);
	   						$("#raInChange_Valley_Electricity").val(data[0].Valley_Electricity);
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
		 					 $("#RateChangeselCity").html(content);
		 				}else{
		 					alertMsg.error(datas.msg);
		 				}
		 				
		            }
		 		});
		 	});
		 	
		 	$("#RateChangeselCity").change(function(){
		 		
		 		var cityId=$(this).val();
		 		if(!cityId){
		 			$("#RateChangeselDistrict").html('<option value="">--请选择区/县--</option>');
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
		 					 $("#RateChangeselDistrict").html(content);
		 				}else{
		 					alertMsg.error(datas.msg);
		 				}
		 				
		            }
		 		});
		 	});
		 	
		 	  $("#RateChangeselDistrict").change(function(){
		      	 var RateEaId = $("#RateChangeselDistrict").val();
		           $("#RateChange_AreaId").val(RateEaId);
		      	 
		       })	
	 
$("#raInChange_Tip_Electricity").blur(function(){
	var value = $("#raInChange_Tip_Electricity").val();	
	var reg = /^[0-3]+(.[0-9]{2})$/;
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raIn_Tip_Electricity").val("");
		alert("费率不能大于3.00且必须保留2位小数点")
	}
		 	  })
$("#raInChange_Peak_Electricity").blur(function(){
	var value = $("#raInChange_Peak_Electricity").val();	
	var reg = /^[0-3]+(.[0-9]{2})$/;
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raInChange_Peak_Electricity").val("");
		alert("费率不能大于3.00且必须保留2位小数点")
	}
		 	  })
$("#raInChange_Flat_Electricity").blur(function(){
	var value = $("#raInChange_Flat_Electricity").val();	
	var reg = /^[0-3]+(.[0-9]{2})$/;
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raInChange_Flat_Electricity").val("");
		alert("费率不能大于3.00且必须保留2位小数点")
	}
		 	  })
$("#raInChange_Valley_Electricity").blur(function(){
	var value = $("#raInChange_Valley_Electricity").val();	
	var reg = /^[0-3]+(.[0-9]{2})$/;
	if(reg.test(value)&&value<=3){
		return;
	}else{
		$("#raInChange_Valley_Electricity").val("");
		alert("费率不能大于3.00且必须保留2位小数点")
	}
		 	  })


</script>