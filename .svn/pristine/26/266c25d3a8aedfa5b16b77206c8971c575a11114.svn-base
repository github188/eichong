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
</script>
<h2 class="contentTitle"><bmtag:message messageKey="electriclabel.showUser"/></h2>
<div class="pageContent">
<form method="post" action="electric/changeElectricPile.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
 <input type="hidden" name="pkElectricpile" value="${tblElectricpile.pkElectricpile}" />
	<div class="pageFormContent nowrap" layoutH="97">
	
		<dl><dt><bmtag:message messageKey="electric.label.code"/></dt><dd>
			<bmtag:message messageKey="${tblElectricpile.elpiElectricpilecode}"/></dd></dl>
		<dl><dt><bmtag:message messageKey="electric.label.name"/></dt><dd>
			 <bmtag:message messageKey="${tblElectricpile.elpiElectricpilename}"/></dd></dl>
		<dl><dt><bmtag:message messageKey="electric.label.way" /></dt><dd>
			<select name="elpiChargingmode" class="select_Style" disabled="disabled">
						  <c:forEach var="item" items="${chargeList}">
						     	<option value="${item.pkConfigcontent}" ${item.pkConfigcontent == tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''} >
								  ${item.cocoContent}
							   </option>
					 	   </c:forEach>
						</select>
			<span class="info"></span></dd></dl>
	 <dl><dt><bmtag:message messageKey="electric.label.state" /></dt><dd>
					  <select name="elpiState" class="select_Style" disabled="disabled">
							<option value="0" ${tblElectricpile.elpiState==0 ? 'selected="selected"' : ''}>草稿</option>
							<option value="5" ${tblElectricpile.elpiState==5 ? 'selected="selected"' : ''}>提交审核</option>
							<option value="12" ${tblElectricpile.elpiState==12 ? 'selected="selected"' : ''}>分享申请中</option>
							<option value="3" ${tblElectricpile.elpiState==3 ? 'selected="selected"' : ''}>已驳回</option>
							<option value="10" ${tblElectricpile.elpiState==10 ? 'selected="selected"' : ''}>专属</option>
							<option value="15" ${tblElectricpile.elpiState==15 ? 'selected="selected"' : ''}>分享</option>
						 </select>
			<span class="info"></span></dd></dl>
	 <dl><dt><bmtag:message messageKey="electric.label.power" /></dt><dd>
			 <select name="elpiPowersize" class="select_Style" disabled="disabled">
						 <c:forEach var="item" items="${powerList}">
						     	<option value="${item.pkConfigcontent}" ${item.pkConfigcontent == tblElectricpile.elpiPowersize ? 'selected="selected"' : ''} >
								  ${item.cocoContent}
							   </option>
						   
					 	   </c:forEach>
						 </select>
			<span class="info"></span></dd></dl>
	 <dl><dt><bmtag:message messageKey="electric.label.longitude"/></dt><dd>
	         <bmtag:message messageKey="${tblElectricpile.elpiLongitude}"/>
			 </dd></dl>
	 <dl><dt><bmtag:message messageKey="electric.label.latitude"/></dt><dd>
	    <bmtag:message messageKey="${tblElectricpile.elpiLatitude}"/>
			 </dd></dl>
	 <dl><dt><bmtag:message messageKey="electric.label.address"/></dt><dd>
	 <bmtag:message messageKey="${tblElectricpile.elpiElectricpileaddress}"/>
			 </dd></dl>
   <!--  	 <dl><dt><bmtag:message messageKey="electric.label.eleUser" /></dt><dd>
			 <select name="elpiPoweruser" class="select_Style" disabled="disabled">
					 <c:forEach var="item" items="${elctrcUseList}">
						     	<option value="${item.pkConfigcontent}" ${item.pkConfigcontent == tblElectricpile.elpiPoweruser ? 'selected="selected"' : ''} >
								  ${item.cocoContent}
							   </option>
						   
					 	   </c:forEach>
						 </select>
			<span class="info"></span></dd></dl>	-->
	 <dl><dt><bmtag:message messageKey="electric.label.type" /></dt><dd>
						<select name="elpiType" class="select_Style" disabled="disabled">
					 <c:forEach var="item" items="${typeList}">
						     	<option value="${item.pkConfigcontent}" ${item.pkConfigcontent == tblElectricpile.elpiType ? 'selected="selected"' : ''} >
								  ${item.cocoContent}
							   </option>
						   
					 	   </c:forEach>
						 </select>
			<span class="info"></span></dd></dl>
			<dl>
				<dt>
					<bmtag:message messageKey="接口标准" />
				</dt>
				<dd>
					<select name="elpiPowerinterface" class="select_Style" disabled="disabled">
						<c:forEach var="item" items="${connectorList}">
							<option value="${item.pkConfigcontent}" ${item.pkConfigcontent==
								tblElectricpile.elpiPowerinterface ? 'selected="selected"' : ''} >
								${item.cocoContent}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="制造厂商" />
				</dt>
				<dd>
					<select name="elpiMaker" class="select_Style" disabled="disabled">
						<c:forEach var="item" items="${markList}">
							<option value="${item.pkCarmaker}" ${item.pkCarmaker==
								tblElectricpile.elpiMaker ? 'selected="selected"' : ''} >
								${item.makerName}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="产品型号" />
				</dt>
				<dd>
					<select name="elpiTypeSpanId" id="elpiTypeSpanId" class="select_Style required" disabled="disabled">
						<c:forEach var="item" items="${typespanList}">
							<option value="${item.pkTypeSpanId}" ${item.pkTypeSpanId==
								tblElectricpile.elpiTypeSpanId ? 'selected="selected"' : ''} >
								${item.tsTypeSpan}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<c:if test="${loginUserLevel != 5}">
				<dl>
					<dt>
						<bmtag:message messageKey="公司标识" />
					</dt>
					<dd>
						${tblElectricpile.companyName}
					</dd>
				</dl>
			</c:if>
			
			
			
			 <dl><dt><bmtag:message messageKey="运营平台" /></dt><dd>
					  <select name="elPiOwnerCompany" class="select_Style" disabled="disabled">
							<%-- <option value="0" ${tblElectricpile.elPiOwnerCompany==0 ? 'selected="selected"' : ''}>草稿</option> --%>
							<option value="1" ${tblElectricpile.elPiOwnerCompany == 1? 'selected="selected"' : ''}>爱充</option>
							<option value="2" ${tblElectricpile.elPiOwnerCompany == 2? 'selected="selected"' : ''}>国网</option>
							<option value="3" ${tblElectricpile.elPiOwnerCompany == 3? 'selected="selected"' : ''}>特斯拉</option>
							<option value="0" ${tblElectricpile.elPiOwnerCompany == 0? 'selected="selected"' : ''}>其他</option>
						 </select>
			<span class="info"></span></dd></dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="所有权" />
				</dt>
				<dd>
					<bmtag:message messageKey="${tblElectricpile.ownerShip}" />
				 	<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="省份" />
				</dt>
				<dd>
					<select name="elPiOwnProvince" disabled="disabled" style="width: 172px !important" class="select_Style"  >
						<option value="">-请选择-</option>
						 <c:forEach var="item" items="${provinceCodeList}">
							<option value="${item.provinceId}" ${item.provinceId==
								tblElectricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''} >${item.provinceName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="城市" />
				</dt>
				<dd>
					<select id="cityId" disabled="disabled" name="elPiOwnCity" class="select_Style"  >
						<option value="">-请选择-</option>
						<c:forEach var="item" items="${cityList}">
							<option value="${item.cityId}" ${item.cityId==
								tblElectricpile.elPiOwnCityCode ? 'selected="selected"' : ''} >${item.cityName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="区/县" />
				</dt>
				<dd>
					<select id="countyId" disabled="disabled" name="elPiOwnCounty" class="select_Style">
					<option value="">-请选择-</option>
					<c:forEach var="item" items="${countyList}">
							<option value="${item.areaId}" ${item.areaId==
								tblElectricpile.elPiOwnCountyCode ? 'selected="selected"' : ''} >${item.areaName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>	
	 <dl><dt><bmtag:message messageKey="electric.label.listImage"/></dt>
	       <dd >
	       		<c:forEach var="item" items="${tblElectricpile.elpiImageUrl}" varStatus="status" >
						<t>
						<img  src="${item}" style="width: 150px; height: 150px;" />
						</t>
				</c:forEach>
	       </dd>
      </dl>
		<dl>
			<dt>
				<bmtag:message messageKey="联系电话" />
			</dt>
			<dd><bmtag:message messageKey="${tblElectricpile.elPi_Tell}" />
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="开放时间" />
			</dt>
			<dd><bmtag:message messageKey="${tblElectricpile.elPiOnlineTime}" />
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="费率" />
			</dt>
			<dd><bmtag:message messageKey="${tblElectricpile.raIn_ReservationRate}" />
			</dd>
		</dl>
		<dl>
				<dt>
					<bmtag:message messageKey="sim卡卡号" />
				</dt>
				<dd>
					<bmtag:message messageKey="${tblElectricpile.elPiSimPhoneNum}" />
				 	<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="sim卡编码" />
				</dt>
				<dd>
					<bmtag:message messageKey="${tblElectricpile.elPiSimMac}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="下线时间" />
				</dt>
				<dd>
					<bmtag:message messageKey="${tblElectricpile.offlineTime}" />
					<span class="info"></span>
				</dd>
			</dl> 
			<dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否支持预约" />
				</dt>
				<dd> 
					<c:if test="${tblElectricpile.elpiIsappoint == 1}">是</c:if>		 
					<c:if test="${tblElectricpile.elpiIsappoint == 0}">否</c:if>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否有支持通讯" />
				</dt>
				<dd>		 
							<c:if test="${tblElectricpile.elPiHaveGps == 1}">是</c:if>
							<c:if test="${tblElectricpile.elPiHaveGps == 0}">否</c:if>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否有LED灯" />
				</dt>
				<dd>		 
							<c:if test="${tblElectricpile.elPiHaveLedFlash == 1}">是</c:if>
							<c:if test="${tblElectricpile.elPiHaveLedFlash == 0}">否</c:if>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否有枪" />
				</dt>
				<dd>	
						<c:if test="${tblElectricpile.elPiHaveConnectLine == 1}">是</c:if>
						<c:if test="${tblElectricpile.elPiHaveConnectLine == 0}">否</c:if>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="最大输出电压" />
				</dt>
				<dd>
					<dd><bmtag:message messageKey="${tblElectricpile.elpiOutputvoltage}" />
						<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="最大输出电流" />
				</dt>
				<dd>
					<dd><bmtag:message messageKey="${tblElectricpile.elpiOutputcurrent}" />
						<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="所属Gate服务器" />
				</dt>
				<dd>
					<select class="required"  id="gateId" disabled="disabled" name="gateId" class="select_Style">
						<option value="" >无</option>
						 <c:forEach var="item" items="${gateList}">
							<option value="${item.pkGateid}" ${item.pkGateid==
								tblElectricpile.gateId ? 'selected="selected"' : ''} >${item.gtseGatename}</option>
						</c:forEach>
					</select> 
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.icon.muzzleSum" />
				</dt>
				<dd>
					<%--  <input type="hidden" name="elpiPowernumber" value="${tblElectricpile.elpiPowernumber}" /> --%>
					<input maxlength="20" class="textInput" readonly style="width: 165px;" value="${tblElectricpile.elpiPowernumber}"/>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<strong>枪头信息：</strong>
				</dt>
			</dl>
			
			<dl>
				<dt>
					<table width="30%">
						<colgroup width="40%">
						<colgroup width="30%">
						<colgroup width="30%">
						<thead>
							<tr height="20">
								<th align="left">车位号</th>
								<th align="left">地锁</th>
								<th align="left">雷达</th>
							</tr>
						</thead>
						<tbody id="row_head">
							<c:forEach var="headInfo" items="${tblElectricpile.headList}" varStatus="status">
								<tr>
									<td>
										<input type="text" readonly value="${headInfo.ephNum}"></td>
									<td><select style="width: 80px" disabled="disabled">
										<option value="0"<c:if test="${headInfo.havaCarPlaceLock == '0'}">selected</c:if>>无</option>
										<option value="1" <c:if test="${headInfo.havaCarPlaceLock == '1'}">selected</c:if>>有</option>
									</select></td>
									<td><select style="width: 80px" disabled="disabled">
										<option value="0" <c:if test="${headInfo.haveRadar == '0'}">selected</c:if>>无</option>
										<option value="1" <c:if test="${headInfo.haveRadar == '1'}">selected</c:if>>有</option>
									</select></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</dt>
			</dl>	
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>