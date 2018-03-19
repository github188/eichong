<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<style>
</style>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="couponVariety/getCouponVarietyList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td ><label style="width:50px;padding:0 0 0 5px;"><bmtag:message
									messageKey="电桩限制" /> </label>
						</td>
						<td><select name="covaLimitation" class="select_Style" id="covaLimitation">
								<option value="" ${tblCouponVariety.covaLimitation==""
									? 'selected="selected"' : ''}>全部</option>
								<option value="1" ${tblCouponVariety.covaLimitation==1
									? 'selected="selected"' : ''}>仅限交流充电桩</option>
								<option value="2" ${tblCouponVariety.covaLimitation==2
									? 'selected="selected"' : ''}>仅限直流充电桩</option>
								<option value="3" ${tblCouponVariety.covaLimitation==3
									? 'selected="selected"' : ''}>不限充电桩</option>
						</select>
						</td>
						<td ><label style="width:40px;padding:0 0 0 5px;"><bmtag:message
									messageKey="状态" /> </label>
						</td>
						<td><select name="covaStutas" class="select_Style" id="covaStutas">
								<option value="" ${tblCouponVariety.covaStutas==""
									? 'selected="selected"' : ''}>全部</option>
								<option value="0" ${tblCouponVariety.covaStutas==0
									? 'selected="selected"' : ''}>已上架</option>
								<option value="1" ${tblCouponVariety.covaStutas==1
									? 'selected="selected"' : ''}>已下架</option>
						</select>
						</td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" />
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
			<li>
				<bmtag:link isAuth="true" target="navTab" href="couponVariety/addCouponVarietyUi.do"
					rel="electricOperate" messageKey="common.icon.new" dwzClass="add" id="couponVarietyAddPage" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="couponVariety/changeCouponVarietyUi.do?id={id}"
					 rel="electricOperate" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<!--  <li>
				<bmtag:link isAuth="true" target="selectedTodo" href="coupon/removeCouponVariety.do"
					rel="ids" postType="string" altKey="确定删除吗？" dwzClass="delete" messageKey="批量删除" />
			</li>-->
			<!--  <li>
				<bmtag:link isAuth="true"  target="selectedTodo" href="coupon/onlineCouponVariety.do?"
			 		rel="ids" postType="string" altKey="确定上架/下架吗？" dwzClass="edit" messageKey="上架/下架" />
			</li>-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="名称" /></th>
				<th><bmtag:message messageKey="电桩限制" /></th>
				<th><bmtag:message messageKey="面值(元)" /></th>
				<th><bmtag:message messageKey="使用条件" /></th>
				<th><bmtag:message messageKey="备注" /></th>
				<th><bmtag:message messageKey="标签" /></th>
				<th><bmtag:message messageKey="状态" /></th>
				<th><bmtag:message messageKey="创建时间" /></th>
				<th><bmtag:message messageKey="操作人" /></th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${couponVarietyList}" var="couponVariety" varStatus="status">
				<tr target="id" rel="${couponVariety.pkCouponVariety}" data-state="${ couponVariety.modifyFlag}" align="center">
					<td><input name="ids" value="${couponVariety.pkCouponVariety}" type="checkbox" data-state="${ couponVariety.modifyFlag}"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${couponVariety.covaActivityName} </td>
					<td><c:if test="${couponVariety.covaLimitation == '1'}">仅限交流电桩</c:if>
						<c:if test="${couponVariety.covaLimitation == '2'}">仅限直流电桩</c:if>
						<c:if test="${couponVariety.covaLimitation == '3'}">不限充电桩</c:if>
					</td>
					<td>${couponVariety.covaCouponValue}</td>
					<td>
						<c:if test="${couponVariety.covaCouponCondition == '0'}">无门槛</c:if>
						<c:if test="${couponVariety.covaCouponCondition != '0' }">满${couponVariety.covaCouponCondition}元可用</c:if>
					</td>
					<td  title="${couponVariety.covaRemark}" width="255">${couponVariety.covaRemark}</td>
					<td  title="${couponVariety.covaLabel}" width="100">${couponVariety.covaLabel}</td>
					<td><c:if test="${couponVariety.covaStutas == '0'}">已上架</c:if>
						<c:if test="${couponVariety.covaStutas == '1'}">已下架</c:if>
					</td>
					<td>${couponVariety.covaCreatedate}</td>
					<td>${couponVariety.userAccount}</td>
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
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	
if(userIdForShow==8231){
	$(".pages").html("");
}
});
</script>