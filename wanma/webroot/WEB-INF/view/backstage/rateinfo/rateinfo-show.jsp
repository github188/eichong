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
		<div class="pageFormContent nowrap" layoutH="100">
		<tr>
				<td>
					<label>区域选择</label>
				</td>
				<td colspan="2">
							<select disabled="disabled" id="RateChangeselProvince" name="raIn_ProvinceId" style="float: none">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${provinceMap}">
									<option value="${item.key}"
										${item.key== rateMap.raIn_province_id ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
							</select>
							<select disabled="disabled" id="RateChangeselCity" name="raIn_CityId" style="float: none;">
								 <option value="">--请选择城市--</option>
								 <c:forEach var="item" items="${cityList}">
									<option value="${item.CITY_ID}"   data-value = "${rateMap.serviceLimit}"
										${item.CITY_ID==rateMap.raIn_city_id  ? 'selected="selected"' : ''} >
										${item.CITY_NAME}</option>
								</c:forEach>
							</select>
							<select disabled="disabled" id="RateChangeselDistrict" name="raIn_AreaId" style="float: none;">
								<option value="">--请选择区/县--</option>
								<c:forEach var="item" items="${areaList}">
									<option value="${item.area_id}"
										${item.area_id==rateMap.raIn_area_id ? 'selected="selected"' : ''} >
										${item.area_name}</option>
								</c:forEach>
							</select>	
				</td>
		</tr>
		<dl>
		
			<dt><bmtag:message messageKey="尖段电价 / 度"/></dt><dd>
			<input name="raIn_TipTimeTariff" id="raInChange_Tip_Electricity" value="${rateMap.raIn_TipTimeTariff}" readonly="true" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="峰段电价 / 度"/></dt><dd>
			<input name="raIn_PeakElectricityPrice" id="raInChange_Peak_Electricity" value="${rateMap.raIn_PeakElectricityPrice}" readonly="true" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="平段电价 / 度"/></dt><dd>
			<input name="raIn_UsualPrice" id="raInChange_Flat_Electricity" value="${rateMap.raIn_UsualPrice}" readonly="true" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="谷段电价 / 度"/></dt><dd>
			<input name="raIn_ValleyTimePrice" id="raInChange_Valley_Electricity" value="${rateMap.raIn_ValleyTimePrice}"  readonly="true" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<dl><dt><bmtag:message messageKey="rateinfo.label.reserate"/> / 分钟</dt><dd>
			<input readonly="true" name="raIn_ReservationRate" id="raIn_ReservationRate"
			value="${rateMap.raIn_ReservationRate}" maxlength="5" style="width:130px;"/>
			<span class="info"></span></dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="rateinfo.label.servicecharge"/> / 度</dt><dd>
			<input readonly="true" name="raIn_ServiceCharge" id="raIn_ServiceCharge"
			value="${rateMap.raIn_ServiceCharge}" maxlength="5" style="width:130px;"/>
			<span class="info"></span>
			</dd>
		</dl>
		<div style="float: left;vertical-align: middle; padding-left: 5px; margin-top: 10px;">
				时间段设置
		</div>
		<div id="mjsondiv" style="padding-top: 10px;">
			
			
		</div>
	</div>					
		<div class="formBar">
			<ul>
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
	<select disabled="disabled" name="mmark" class="request" style="float: none">
		<option value="1">尖</option>
		<option value="2">峰</option>
		<option value="3">平</option>
		<option value="4">谷</option>
	</select>&nbsp;&nbsp;&nbsp;
	<select disabled="disabled" name="mstarth" style="float: none;">
		<c:forEach var="x" begin="0" end="24"><option value="${x}" >${x}</option></c:forEach>
	</select>&nbsp;时
	<select disabled="disabled" name="mstartm" style="float: none;">
		<c:forEach var="x" begin="0" end="59"><option value="${x}">${x}</option></c:forEach>
	</select>&nbsp;分
	&nbsp;&nbsp;--&nbsp;&nbsp;
	<select disabled="disabled" name="mendh" style="float: none;">
		<c:forEach var="x" begin="0" end="24"><option value="${x}">${x}</option></c:forEach>
	</select>&nbsp;时
	<select disabled="disabled" name="mendm" style="float: none;">
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
			$(line).children().eq(2).val(item.st - st);
			//设置结束时间
			var rsltend = item.et / 60; 
			var et = Math.floor(rsltend); 
			$(line).children().eq(3).val(et);
			$(line).children().eq(4).val(item.st - et);
		});
		
	});
	
	function mcontinueadd(i){
		var html = "<div name='mdatainput' style='margin-left: 130px;padding-top: 10px;'>";
		html += $("#mdatainputtemp").html();
		html += "</div>";
		$("#mjsondiv").append(html);
	}
</script>