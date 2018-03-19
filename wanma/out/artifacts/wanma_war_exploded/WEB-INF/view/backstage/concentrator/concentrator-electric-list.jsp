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
<style type="text/css">
 .binddiv{
 margin-left: -120px;/*Chrome、Safari、Firefox、Opera */ 
 margin-left: -70px;/* Firefox */ 
 }
 .bindfont{
 font-size: 5px;/*Chrome、Safari、Firefox、Opera */ 
  font-size: 13px; /* Firefox */ 
 }
</style>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="concentrator/getConcentratorElectricPileList.do"
		onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.code" /> </label></td>
						<td><input name="elpiElectricpilecode"
							value="${tblElectricpile.elpiElectricpilecode}" /></td>
						<td><label><bmtag:message
									messageKey="electric.label.way" /> </label></td>
						<td><select name="elpiChargingmode" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${chargeList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>
								</c:forEach>
						</select></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
								<td><div class="button" > <div class="buttonContent"><button type="button" multLookup="orgId" warn="请选电桩"><font class="bindfont" >绑定电桩</font></button></div></div></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
		<%-- 		<tr>
				<td><label><bmtag:message
									messageKey="electric.label.state" /> </label></td>
						<td><select name="elpiState" class="select_Style">
								<option value="">全部</option>
								<option value="0" ${tblElectricpile.elpiState==0
									? 'selected="selected"' : ''}>草稿</option>
								<option value="5" ${tblElectricpile.elpiState==5
									? 'selected="selected"' : ''}>提交审核</option>
								<option value="10" ${tblElectricpile.elpiState==10
									? 'selected="selected"' : ''}>离线</option>
								<option value="15" ${tblElectricpile.elpiState==15
									? 'selected="selected"' : ''}>上线</option>
						</select></td>
						<td></td> 
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
								<td><div class="button binddiv" > <div class="buttonContent"><button type="button" multLookup="orgId" warn="请选电桩"><font class="bindfont" >绑定电桩</font></button></div></div></td>
					    <td align="right"></td>
				</tr> --%>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table"  width="100%" layoutH="109">
		<thead>
			<tr>
			   <th><input type="checkbox" group="orgId"  class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="electric.label.code" /></th>
				<th><bmtag:message messageKey="electric.label.name" /></th>
				<th><bmtag:message messageKey="electric.label.state" /></th>
				<th><bmtag:message messageKey="electric.label.way" /></th>
				<th><bmtag:message messageKey="electric.label.connector" /></th>
				<th><bmtag:message messageKey="electric.label.type" /></th>
				<th><bmtag:message messageKey="electric.label.power" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pk_ElectricPile}" align="center">
				    <td><input name="orgId"  value="${electric.electricpileValue}" type="checkbox"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${electric.elPi_ElectricPileCode}</td>
					<td>${electric.elPi_ElectricPileName}</td>
					<td><c:if test="${electric.elPi_State ==0}" >草稿</c:if>
					    <c:if test="${electric.elPi_State ==5}" >提交审核 </c:if>
					    <c:if test="${electric.elPi_State ==10}" >专属 </c:if>
					    <c:if test="${electric.elPi_State ==12}" >分享申请中 </c:if>
					    <c:if test="${electric.elPi_State ==15}" >分享</c:if>
					</td>
					<td>${electric.chargingModeName}</td>
					<td>${electric.powerName}</td>
					<td>${electric.typeName}</td>
					<td>${electric.powerSizeName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span><!--  <select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})"
				>
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="dialog"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
