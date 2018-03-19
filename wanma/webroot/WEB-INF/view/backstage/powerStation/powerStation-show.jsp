<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	
	
</script>

<h2 class="contentTitle"><bmtag:message messageKey="查看充电点"/></h2>
<div class="pageContent">
<form method="post" action="powerstation/addPowers.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">

	<div class="pageFormContent nowrap" layoutH="97">
	
		<dl><dt><bmtag:message messageKey="powerstation.label.name"/></dt><dd>
			<input name="postName"  disabled="disabled" class="textInput alphanumeric required"  value="${tblPowerstation.postName}"remote="electric/checkElectricUnique.do" maxlength="10" style="width:165px;"/>
			<span class="info"></span></dd></dl>
	  <dl><dt><bmtag:message messageKey="powerstation.label.address"/></dt><dd>
			<input name="postAddress" disabled="disabled" class="textInput required" value="${tblPowerstation.postAddress}" maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>	
		 <dl><dt><bmtag:message messageKey="powerstation.label.tel"/></dt><dd>
			<input name="postPhone" disabled="disabled" class="textInput required" value="${tblPowerstation.postPhone}" maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>		
		 <dl><dt><bmtag:message messageKey="electric.label.longitude"/></dt><dd>
			<input name="postLongitude" disabled="disabled" class="textInput required" value="${tblPowerstation.postLongitude}" maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>
	 <dl><dt><bmtag:message messageKey="electric.label.latitude"/></dt><dd>
			<input name="postLatitude" disabled="disabled" class="textInput required" value="${tblPowerstation.postLatitude}" maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>
		<dl>
			<dt>
				<bmtag:message messageKey="省份" />
			</dt>
			<dd>
				<select name="postOwnProvinceCode" id="postOwnProvinceCode" style="width: 172px !important" class="select_Style" onchange="getCity(this)" disabled="disabled">
					 <c:forEach var="item" items="${provinceCodeList}">
						<option value="${item.provinceId}" ${item.provinceId==
							tblPowerstation.postOwnProvinceCode ? 'selected="selected"' : ''} >${item.provinceName}</option>
					</c:forEach>
				</select> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="城市" />
			</dt>
			<dd>
				<select id="editCityId" name="postOwnCityCode" class="select_Style editCityId" onchange="getCounty(this)" disabled="disabled">
					<c:forEach var="item" items="${cityList}">
						<option value="${item.cityId}" ${item.cityId==
							tblPowerstation.postOwnCityCode ? 'selected="selected"' : ''} >${item.cityName}</option>
					</c:forEach>
				</select> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="区/县" />
			</dt>
			<dd>
				<select id="editCountyId" name="postOwnCountyCode" class="select_Style editCountyId" disabled="disabled">
				<c:forEach var="item" items="${countyList}">
						<option value="${item.areaId}" ${item.areaId==
							tblPowerstation.postOwnCountyCode ? 'selected="selected"' : ''} >${item.areaName}</option>
					</c:forEach>
				</select> <span class="info"></span>
			</dd>
		</dl>
	 <dl><dt><bmtag:message messageKey="充电点状态" /></dt><dd>
                       <c:if test="${tblPowerstation.postStatus ==0}" >草稿</c:if>
					    <c:if test="${tblPowerstation.postStatus ==5}" >提交审核 </c:if>
					     <c:if test="${tblPowerstation.postStatus ==12}" >分享申请中 </c:if>
					    <c:if test="${tblPowerstation.postStatus ==3}" >已驳回 </c:if>
					    <c:if test="${tblPowerstation.postStatus ==10}" >专属 </c:if>
					    <c:if test="${tblPowerstation.postStatus ==15}" >分享</c:if></dd></dl>
	
			<dl>
				<dt>
					<bmtag:message messageKey="是否支持预约" />
				</dt>
				<dd>
					<c:if test="${tblPowerstation.postIsappoint == 1}">是</c:if>
					<c:if test="${tblPowerstation.postIsappoint == 0}">否</c:if>
				</dd>
			</dl>				    
	 <dl><dt><bmtag:message messageKey="electric.label.listImage"/></dt>
	       <dd >
				<c:forEach var="item" items="${tblPowerstation.postPicUrl}" varStatus="status" >
							<t>
							<img  src="${item}" style="width: 150px; height: 150px;" />
							</t>
					</c:forEach>
		   </dd>
	       <dd>
			<span class="info"></span></dd></dl>
	 <%-- <dl><dt><bmtag:message messageKey="electric.label.detailImage"/></dt>
	       <dd style="width: 200px;"><img src="${tblPowerstation.postDetailpic}" style="width: 150px; height: 150px;" onerror="this.src='<%=request.getContextPath()%>/res/bluemobi/img/default.png'"></dd>
	      <dd>
			<span class="info"></span></dd></dl> --%>
	 <dl></dl>
	 			<dl>
				<dt>
					<bmtag:message messageKey="开放时间" />
				</dt>
				<dd><bmtag:message messageKey="${tblPowerstation.poStOnlineTime}" />
				</dd>
			</dl>
	 <dl>所属电桩列表</dl>
	 <dl></dl>
	 <dl>
	<table class="table"  width="100%" layoutH="409">
		<thead>
			<tr>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="electric.label.code" /></th>
				<th><bmtag:message messageKey="编号" /></th>
				<th><bmtag:message messageKey="electric.label.name" /></th>
				<th><bmtag:message messageKey="electric.label.state" /></th>
				<th><bmtag:message messageKey="electric.label.way" /></th>
				<th><bmtag:message messageKey="electric.label.connector" /></th>
				<th><bmtag:message messageKey="electric.label.type" /></th>
				<th><bmtag:message messageKey="electric.label.power" /></th>
				<th><bmtag:message messageKey="electric.label.location" /></th>
				<th><bmtag:message messageKey="费率id" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pk_ElectricPile}" align="center">
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${electric.elPi_ElectricPileCode}</td>
					<td>${electric.ep_num}号桩</td>
					<td>${electric.elPi_ElectricPileName}</td>
					<td><c:if test="${electric.elPi_State ==0}" >草稿</c:if>
					    <c:if test="${electric.elPi_State ==5}" >提交审核 </c:if>
					    <c:if test="${electric.elPi_State ==12}" >分享申请中 </c:if>
					    <c:if test="${electric.elPi_State ==10}" >专属 </c:if>
					    <c:if test="${electric.elPi_State ==15}" >分享</c:if>
					</td>
					<td>${electric.chargingModeName}</td>
					<td>${electric.powerName}</td>
					<td>${electric.typeName}</td>
					<td>${electric.powerSizeName}</td>
					<td><c:if test="${electric.elPi_Longitude!=null}" >经度：${electric.elPi_Longitude}</c:if><c:if test="${electric.elPi_Latitude!=null}" >&nbsp;&nbsp;纬度：${electric.elPi_Latitude}</c:if></td>
					<td>${electric.elPi_RateInformationId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	 </dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>