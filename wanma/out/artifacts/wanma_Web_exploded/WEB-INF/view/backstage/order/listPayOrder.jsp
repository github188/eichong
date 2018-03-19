<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="order/rechOrder.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<%-- <td>
					<span>订单编号</span>
					<input name="puhiConsumerremark" placeholder="请输入" value="${tblPurchasehistory.puhiConsumerremark }"/>
				</td> --%>
						<%-- <td>
					<span>订单状态</span>
					<select name="payoStatus" style="width:155px">
						<option value=""}>全部</option>
						<option value="1" ${tblPayOrder.payoStatus == 1?"selected":""}>未支付</option>
						<option value="2" ${tblPayOrder.payoStatus == 2?"selected":""}>支付成功</option>
						<option value="3" ${tblPayOrder.payoStatus == 3?"selected":""}>完成操作</option>
					</select>
				</td> --%>
						<td><span>用户姓名</span> <input name="userName"
							placeholder="请输入" value="${tblPurchasehistory.userName }" /></td>

						<td><span>充值时间</span> <input id="startDate_payOrder"
							name="beginTime" placeholder="请选择"
							value="${tblPurchasehistory.beginTime}" class="date"
							onClick="WdatePicker({el:'startDate_payOrder',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_payOrder\')}'})">
						<span>至</span><input
							id="endDate_payOrder" name="endTime"
							value="${tblPurchasehistory.endTime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_payOrder',minDate:'#F{$dp.$D(\'startDate_payOrder\')}'})">
						</td>
						<td align="right"><bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
						<td align="right"><bmtag:button messageKey="excel导出" type="button"
								onclick="exportSubmit('pagerForm','orderExport/payOrder.do')" />
						</td>
						
					</tr>
					<tr>


					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="86">
		<thead>
			<tr>
				<th style="width:3%"><input type="checkbox" group="pkPayOrder"
					class="checkboxCtrl" /></th>
				<th style="width:7%"><bmtag:message messageKey="序号" /></th>
				<th style="width:15%"><bmtag:message messageKey="用户姓名" /></th>
				<th style="width:15%">用户手机</th>
				<%-- <th><bmtag:message messageKey="用户类型"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th> --%>
				<th style="width:15%"><bmtag:message messageKey="金额" /></th>
				<th style="width:15%"><bmtag:message messageKey="充值时间" /></th>
				<th style="width:15%">充值来源</th>
				<th style="width:15%">消费备注</th>
				<%--  <th><bmtag:message messageKey="订单状态"/></th>
		    <th><bmtag:message messageKey="订单支付类型"/></th> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${payList}" var="pay" varStatus="status">
				<tr target="pkPayOrder" rel="${pay.pkPurchasehistory }"
					align="center">
					<td style="width:3%"><input name="pkPayOrders"
						value="${pay.pkPurchasehistory }" type="checkbox" /></td>
					<td style="width:7%">${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td style="width:15%">${pay.userName}<c:if test="${pay.userName==''}">${pay.userPhone }</c:if></td>
					<td style="width:15%">${pay.userPhone }</td>
					<%-- <td>${pay.groupName }</td>
				<td>${pay.puhiConsumerremark }</td> --%>
					<td style="width:15%">${pay.puhiMonetary }</td>
					<td style="width:15%"><fmt:formatDate value="${pay.puhiPurchasehistorytime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="width:15%"><c:choose>
							<c:when test="${pay.puhiChargeType == 1}">
							支付宝
						</c:when>
							<c:when test="${pay.puhiChargeType == 2}">
							微信
						</c:when>
							<c:when test="${pay.puhiChargeType == 3}">
							银联
						</c:when>
							<c:when test="${pay.puhiChargeType == 4}">
							充电卡现金
						</c:when>
							<c:when test="${pay.puhiChargeType == 5}">
							换卡转账 
						</c:when>
							<c:when test="${pay.puhiChargeType == 6}">
							7月活动送
						</c:when>
							<c:otherwise>
							平台人工充值
						</c:otherwise>
						</c:choose></td>
					<%-- <td>
					<c:choose>
						<c:when test="${pay.payoStatus == '1'}">
							待支付
						</c:when>
						<c:when test="${pay.payoStatus == '2'}">
							支付成功
						</c:when>
						<c:when test="${pay.payoStatus == '3'}">
							完成操作
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${pay.payoOrderType == '1'}">
							支付宝
						</c:when>
						<c:when test="${pay.payoOrderType == '2'}">
							银联支付
						</c:when>
					</c:choose>
				</td> --%>
				<td title="${pay.puhiConsumerremark}" style="width:15%">${pay.puhiConsumerremark}</td>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
