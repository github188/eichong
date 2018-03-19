<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<style>
.pCenterRed {
	color: red;
	margin-top: 5px;
}

.pCenterRedGreen {
	color: green;
	margin-top: 5px;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/electric/electric-list.js" />
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="electric/getElectricPileList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.code" /> </label></td>
						<td><input name="elpiElectricpilecode"
							value="${tblElectricpile.elpiElectricpilecode}" /></td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.label.name" /> </label></td>
						<td><input name="elpiElectricpilename"
							value="${tblElectricpile.elpiElectricpilename}" /></td>

						<td><label><bmtag:message
									messageKey="electric.label.way" /> </label></td>
						<td><select name="elpiChargingmode" class="select_Style">
								<option value="">全部</option>
								<%-- <c:forEach var="item" items="${chargeList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>
								</c:forEach> --%>
								<c:forEach var="item" items="${param3}">
									<option value="${item.key}"
										${item.key == tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''}>
										${item.value.coCo_Content}</option>
								</c:forEach>
						</select></td>
						<td><label><bmtag:message messageKey="连接状态" /> </label></td>
						<td><select name="comm_status" class="select_Style">
								<option value="">全部</option>
								<option value="1"
									${tblElectricpile.comm_status=="1"
									? 'selected="selected"' : ''}>已连接</option>
								<option value="0"
									${tblElectricpile.comm_status=="0"
									? 'selected="selected"' : ''}>未连接</option>

						</select></td>
					</tr>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.state" /> </label></td>
						<td><select name="elpiState" class="select_Style">
								<option value="">全部</option>
								<option value="0"
									${tblElectricpile.elpiState==0
									? 'selected="selected"' : ''}>草稿</option>
								<option value="5"
									${tblElectricpile.elpiState==5
									? 'selected="selected"' : ''}>提交审核</option>
								<option value="12"
									${tblElectricpile.elpiState==12
									? 'selected="selected"' : ''}>分享申请中</option>
								<%--<option value="3" ${tblElectricpile.elpiState==3
									? 'selected="selected"' : ''}>已驳回</option> --%>
								<option value="10"
									${tblElectricpile.elpiState==10
									? 'selected="selected"' : ''}>专属</option>
								<option value="15"
									${tblElectricpile.elpiState==15
									? 'selected="selected"' : ''}>分享</option>
						</select></td>
						<td><label><bmtag:message
									messageKey="electric.label.power" /></label></td>
						<td><select name="elpiPowersize" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${param4}">
									<option value="${item.key}"
										${item.key == tblElectricpile.elpiPowersize ? 'selected="selected"' : ''}>
										${item.value.coCo_Content}</option>
								</c:forEach>
								<%-- <c:forEach var="item" items="${powerList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiPowersize ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach> --%>
						</select></td>
						<td><label><bmtag:message
									messageKey="electric.label.type" /> </label></td>
						<td><select name="elpiType" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${param1}">
									<option value="${item.key}"
										${item.key == tblElectricpile.elpiType ? 'selected="selected"' : ''}>
										${item.value.coCo_Content}</option>
								</c:forEach>
						</select></td>

					</tr>
					<tr>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="制造厂商" /> </label></td>
						<td><select name=elpiMaker class="select_Style">
					            <option value="">全部</option>
								<c:forEach var="item" items="${getElpiMaker }">
									<option value="${item.elpiMaker}"
										${item.elpiMaker == tblElectricpile.elpiMaker ? 'selected="selected"' : ''}>
										${item.makerRemark}</option>
								</c:forEach>
								
						</select></td>

						<%-- <td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.icon.muzzleSum" /> </label>
						</td>
						<td><select name="elpiPowernumber" class="select_Style">
								<option value="0">全部</option>
								<option value="1" ${tblElectricpile.elpiPowernumber==1
									? 'selected="selected"' : ''}>单枪头</option>
								<option value="2" ${tblElectricpile.elpiPowernumber==2
									? 'selected="selected"' : ''}>多枪头</option>
						</select>
						</td> --%>

						<c:choose>
							<c:when
								test="${loginUser.userLevel==1 || loginUser.userLevel ==2}">
								<td><label><bmtag:message
											messageKey="electric.label.user" /> </label></td>
								<td><input name="elPiUserName"
									value="${tblElectricpile.elPiUserName}" /></td>
							</c:when>

						</c:choose>

						<td><label><bmtag:message messageKey="所有权" /> </label></td>
						<td><input name="ownerShip"
							value="${tblElectricpile.ownerShip}" /></td>
					</tr>

					<tr>
						<td><label>区域选择</label></td>
						<td colspan="2"><select class="provinceCode required"
							id="electricSelProvince" next="electricSelCity"
							name="elPiOwnProvinceCode" style="float: none; width: 130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== tblElectricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''}>
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
						</select> <select class="cityCode required" id="electricSelCity"
							next="electricSelDistrict"
							data-val="${tblElectricpile.elPiOwnCityCode}"
							name="elPiOwnCityCode" style="float: none; width: 130px;">
								<option value="">--请选择城市--</option>
						</select> <select id="electricSelDistrict"
							data-val="${tblElectricpile.elPiOwnCountyCode}"
							name="elPiOwnCountyCode" class="required"
							style="float: none; width: 130px;">
								<option value="">--请选择区/县--</option>
						</select></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
						<td align="right"><bmtag:button messageKey="excel导出"
								type="button"
								onclick="exportSubmit('pagerForm','electric/electricExport.do')" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link isAuth="true" target="navTab"
					href="electric/addElectricPileUi.do" rel="electricOperate"
					messageKey="common.icon.new" dwzClass="add" id="electricAddPage" />
			</li>
			<li><bmtag:link isAuth="true" target="navTab"
					href="electric/changeElectricPileUi.do?id={id}"
					rel="electricOperate" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li><bmtag:link isAuth="true" target="selectedTodo"
					href="electric/removeElectricPile.do" rel="ids" postType="string"
					altKey="确定删除吗？" dwzClass="delete" messageKey="批量删除" /></li>
			<li><bmtag:link isAuth="true" target="navTab"
					href="electric/showElectricPile.do?id={id}" rel="electricShowPage"
					messageKey="common.icon.view" dwzClass="icon" id="electricShowPage" />
			</li>
			<li><bmtag:link isAuth="true" target="selectedTodo"
					href="electric/changeElectricPileState.do?changeType=examinePage"
					rel="ids" postType="string" altKey="确定提审吗？" dwzClass="edit"
					messageKey="提审" /></li>
			<li><bmtag:link isAuth="true" target="selectedTodo"
					href="electric/changeElectricPileState.do?&changeType=offLinePage"
					rel="ids" postType="string" altKey="确定要申请专属吗？" dwzClass="edit"
					messageKey="专属申请" /></li>
			<!--  <li>
				<bmtag:link isAuth="true"  target="selectedTodo" href="electric/changeElectricPileState_02.do?&changeType=toInit"
					 rel="ids" postType="string" altKey="确定要改为草稿状态吗？" dwzClass="edit" messageKey="状态重置" />
			</li>-->
			<li><bmtag:link isAuth="true" target="selectedTodo"
					href="electric/shareApplication.do?&changeType=toApplication"
					rel="ids" postType="string" altKey="确定要分享申请吗？" dwzClass="edit"
					messageKey="分享申请" /></li>
			<li><bmtag:link isAuth="true" target="navTab"
					href="electric/toImport.do" rel="electricOperate" messageKey="导入"
					dwzClass="edit" /></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="190">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<%-- <th><bmtag:message messageKey="common.label.index" />
				</th> --%>
				<th><bmtag:message messageKey="electric.label.code" /></th>
				<th><bmtag:message messageKey="electric.label.name" /></th>
				<th><bmtag:message messageKey="electric.label.user" /></th>
				<th><bmtag:message messageKey="electric.label.state" /></th>
				<th><bmtag:message messageKey="electric.label.way" /></th>
				<th style="display: none;"><bmtag:message messageKey="electric.label.connector" /></th>
				<th><bmtag:message messageKey="electric.label.type" /></th>
				<th><bmtag:message messageKey="electric.label.power" /></th>
				<th><bmtag:message messageKey="electric.label.location" /></th>
				<th><bmtag:message messageKey="electric.label.madein" /></th>
				<th style="display: none;"><bmtag:message messageKey="运营平台" /></th>
				<%-- <th><bmtag:message messageKey="electric.label.muzzleState" />
				</th>
				<th><bmtag:message messageKey="electric.label.carState" />
				</th> --%>
				<th style="display: none;"><bmtag:message messageKey="electric.label.rejectReason" />
				</th>
				<th><bmtag:message messageKey="连接状态" /></th>
				<th><bmtag:message messageKey="是否绑定充电点" /></th>
				<th style="display: none;"><bmtag:message messageKey="是否支持预约" /></th>
				<th><bmtag:message messageKey="所属区域" /> <!--bmtag:message messageKey="所属区/县" /--></th>
				<%-- <th><bmtag:message messageKey="城市代码" />
				</th> --%>
				<th><bmtag:message messageKey="费率id" /></th>
				<th><bmtag:message messageKey="电表读数" /></th>
				<th><bmtag:message messageKey="备注信息" /></th>
				<th><bmtag:message messageKey="创建时间" /></th>
				<th style="display: none;"><bmtag:message messageKey="gate_id" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pk_ElectricPile}" align="center">
					<td><input name="ids" value="${electric.pk_ElectricPile}"
						data-value="${electric.elPi_ElectricPileCode}:${electric.elPi_RateInformationId}:${electric.elPi_State}:${electric.comm_status}"
						type="checkbox"></td>
					<%-- <td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td> --%>
					<td>${electric.elPi_ElectricPileCode}</td>
					<td>${electric.elPi_ElectricPileName}</td>
					<td>${electric.elPi_UserName}</td>
					<td><c:if test="${electric.elPi_State ==0}">草稿</c:if> <c:if
							test="${electric.elPi_State ==5}">提交审核 </c:if> <c:if
							test="${electric.elPi_State ==12}">分享申请中 </c:if> <c:if
							test="${electric.elPi_State ==3}">已驳回 </c:if> <c:if
							test="${electric.elPi_State ==10}">专属 </c:if> <c:if
							test="${electric.elPi_State ==15}">分享</c:if></td>
					<td>${electric.chargingModeName}</td>
					<td style="display: none;">${electric.powerName}</td>
					<td>${electric.typeName}</td>
					<td>${electric.powerSizeName}</td>
					<td>${electric.elPi_ElectricPileAddress}<%-- <c:if test="${electric.elPi_Longitude!=null}">经度：${electric.elPi_Longitude}</c:if>
						<c:if test="${electric.elPi_Latitude!=null}">&nbsp;&nbsp;纬度：${electric.elPi_Latitude}</c:if> --%>
					</td>
					<td>${electric.elPi_Maker_Name}</td>
					<td style="display: none;"><c:if test="${electric.elPi_OwnerCompany ==0}">其他</c:if> <c:if
							test="${electric.elPi_OwnerCompany ==1}">爱充</c:if> <c:if
							test="${electric.elPi_OwnerCompany ==2}">国网 </c:if> <c:if
							test="${electric.elPi_OwnerCompany ==3}">特斯拉 </c:if></td>
					<%-- <td>${electric.headSateDetail}</td>
					<td></td> --%>
					<td style="display: none;"  title="${electric.elPi_RejectionReason}"><c:if
							test="${electric.elPi_RejectionReason.length()>10}">
							${fn:substring(electric.elPi_RejectionReason,0,10)}...
						</c:if> <c:if test="${electric.elPi_RejectionReason.length()<=10}">
							${electric.elPi_RejectionReason}
						</c:if>
					<td><c:if test="${electric.comm_status == '0'}">
							<p class="pCenterRed">未连接</p>
						</c:if> <c:if test="${electric.comm_status == '1'}">
							<p class="pCenterRedGreen">已连接
						</c:if></td>
					<td><c:if test="${electric.elPi_Binding == '0'}">
							<p class="pCenterRed">否</p>
						</c:if> <c:if test="${electric.elPi_Binding == '1'}">
							<p class="pCenterRedGreen">是</p>
						</c:if></td>

					<td style="display: none;"><c:if test="${electric.elPi_IsAppoint == '0'}">
							<p class="pCenterRed">否</p>
						</c:if> <c:if test="${electric.elPi_IsAppoint == '1'}">
							<p class="pCenterRedGreen">是</p>
						</c:if></td>
					<td>${electric.elPi_elPiOwnCity}${electric.elPi_elPiOwnCounty}</td>
					<%-- <td>${electric.elPi_OwnCountyCode}</td> --%>
					<td>${electric.elPi_RateInformationId}</td>
					<td>
						<c:if test="${electric.totalMeter > 0 }">${electric.totalMeter}</c:if>
					</td>
					<td>${electric.elPi_Remark}</td>
					<td>${electric.elPi_Createdate}</td>
					<td style="display: none;">${electric.elPi_GateId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<!-- <span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value});pageChange()">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select>  -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	
	if(userIdForShow==8231){
		$(".pages").html("");
	}
	loadCity($("#electricSelProvince"));
	loadArea($("#electricSelCity"));
});
</script>