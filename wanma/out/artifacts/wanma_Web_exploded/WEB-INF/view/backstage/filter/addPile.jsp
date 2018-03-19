<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="filter/addPile.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			<input type="hidden" name="comPanyId" value="${tblElectricpile.comPanyId}" />
			<input type="hidden" name="relevancePowerStation" value="${tblElectricpile.relevancePowerStation}" />
	 	<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td>
					<span>充电桩编号</span>
					<input name="elpiElectricpilecode"  value="${tblElectricpile.elpiElectricpilecode}"/>
				</td>

						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_power" /></td>

					</tr>
				</tbody>
			</table>
		</div>

	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		
		
		<ul class="toolBar">
			
			
			<li class="">
				<a target="selectedTodo" rel="ids" href="filter/savePile.do?SaveCompanyId=${tblElectricpile.comPanyId}&SavePowerId=${tblElectricpile.relevancePowerStation}" class="add" title="确定为该合作商添加此充电桩吗？"><span>确认添加</span></a>

			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
			<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<%-- <th><bmtag:message messageKey="编号" /></th> --%>
              <%--   <th><bmtag:message messageKey="充电点名称" /></th> --%>
				<th><bmtag:message messageKey="充电桩编号" /></th>
				<th><bmtag:message messageKey="充电方式" /></th>
			    <th><bmtag:message messageKey="功率" /></th>
			    <th><bmtag:message messageKey="枪头数" /></th>
			     <th><bmtag:message messageKey="制造厂商" /></th>
			     <th><bmtag:message messageKey="电桩类型" /></th>
			       <th><bmtag:message messageKey="连接状态" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${addPileList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkElectricpile}" align="center">
					 <td><input name="ids" value="${item.pkElectricpile}"
						type="checkbox"></td> 
					<%--  <td  title="${item.elPiRelevancePowerStation}">${item.elPiRelevancePowerStation}</td>  --%>
					<td>${item.elpiElectricpilecode}</td>
					<td><c:if test="${item.elpiChargingmode==5}">直流</c:if> <c:if
							test="${item.elpiChargingmode==14}">交流</c:if></td>
					<td>${item.elpiPowersize} kw</td>
				    <td>${item.pileHeadSum}</td>
					<td>${item.makerRemark}</td>
						<td><c:if test="${item.elpiState==10}">专属</c:if> <c:if
							test="${item.elpiState==15}">分享</c:if></td>
						<td><c:if test="${item.comm_status==0}"><span style="color:red">未连接</span></c:if> <c:if
							test="${item.comm_status==1}">已连接</c:if></td>
					
                   
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

<script type="text/javascript">

	
</script>


