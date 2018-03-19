<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function checkDetails() {
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="moneyRecord/getPartnerMoneyRecordDetials.do?chOrOrgNo=${params.chOrOrgNo}"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /><input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>消费时间&nbsp;</span> <input
							id="moneyDetails_createDate" name="startTime"
							value="${params.startTime }" class="date" style="width: 155px"
							onClick="WdatePicker({el:'moneyDetails_createDate',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'moneyDetails_lastUpdateDate\')}'})">
							至 <input id="moneyDetails_lastUpdateDate" name="endTime"
							value="${params.endTime }" class="date" style="width: 155px"
							onClick="WdatePicker({el:'moneyDetails_lastUpdateDate',minDate:'#F{$dp.$D(\'moneyDetails_createDate\')}'})">
						</td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					<tr height="10px;"></tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent" >
			<table class="table" width="100%" layoutH="95">
				<thead>
					<tr align="center">
						<th width="30"><bmtag:message messageKey="序号" /></th>
						<th><bmtag:message messageKey="消费时间" /></th>
						<th><bmtag:message messageKey="消费金额(元)" /></th>
						<th><bmtag:message messageKey="消费类型" /></th>
						<th><bmtag:message messageKey="消费方式" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${moneyRecordListByUserId}"
						var="moneyRecordByUserId" varStatus="status">
						<tr target="" rel="${moneyRecordByUserId.pkChargingOrder }" align="center">
							<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
							<td><fmt:formatDate
									value="${moneyRecordByUserId.chOrTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${moneyRecordByUserId.chOrMoney}</td>
							<td><c:if
									test="${moneyRecordByUserId.puHi_Type == '1'||moneyRecordByUserId.puHi_Type == '2'||moneyRecordByUserId.puHi_Type == '3'}">
									<p style="color: red;">消费</p>
								</c:if> <c:if test="${moneyRecordByUserId.puHi_Type == '4'}">
									<p style="color: green;">充值</p>
								</c:if></td>
							<td><c:if
									test="${moneyRecordByUserId.puHi_ChargeType == '1'}">
										支付宝充值
								</c:if> <c:if test="${moneyRecordByUserId.puHi_ChargeType == '2'}">
										微信充值
								</c:if> <c:if test="${moneyRecordByUserId.puHi_ChargeType == '4'}">
										平台人工充值
								</c:if> <c:if test="${moneyRecordByUserId.puHi_ChargeType == '5'}">
										换卡转账
								</c:if> <c:if test="${moneyRecordByUserId.puHi_ChargeType == '6'}">
										7月活动送
								</c:if> <c:if test="${moneyRecordByUserId.puHi_Type == '1'}">
										充电消费
								</c:if> <c:if test="${moneyRecordByUserId.puHi_Type == '2'}">
										预约消费
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	<div class="panelBar">
		<div class="pages">
			<span> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

