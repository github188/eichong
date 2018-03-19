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
	<form id="pagerForm" method="post" action="order/subsFirmOrder.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>订单编号</span> <input name="bespResepaymentcode"
							placeholder="请输入" value="${tblBespoke.bespResepaymentcode }" /></td>
						<td><span>充电点名称</span> <input name="chargePointName"
							placeholder="请输入" value="${tblBespoke.chargePointName }" /></td>
						<td><span>充电点地址</span> <input
							name="chargePointAddress" placeholder="请输入"
							value="${tblBespoke.chargePointAddress }" /></td>
							<td><span>预约时间&nbsp;&nbsp;&nbsp;&nbsp;</span> <input id="startDats_besp"
						name="bespBeginTimes" placeholder="请选择"
						value="${tblBespoke.bespBeginTimes}" class="date"
						onClick="WdatePicker({el:'startDats_besp',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDats_besp\')}'})">
					<span>至</span> <input
						id="endDats_besp" name="bespEndTimes" placeholder="请选择"
						value="${tblBespoke.bespEndTimes}" class="date"
						onClick="WdatePicker({el:'endDats_besp',minDate:'#F{$dp.$D(\'startDats_besp\')}'})">
					</td>
						
					<tr>
					</tr>
					<td><span>企业名称</span> <input name="comName" placeholder="请输入"
						value="${tblBespoke.comName }" /></td>
					<td><span>桩体编号&nbsp;&nbsp;&nbsp;&nbsp;</span> <input
						name="eleCode" placeholder="请输入" value="${tblBespoke.eleCode }" />
					</td>
					<td><span>订单状态</span> <select name="bespOrderType"
							style="width: 155px">
								<option value="">全部</option>
								<option value="0"
									${tblBespoke.bespOrderType == "0"?"selected":""}>未支付</option>
								<option value="1"
									${tblBespoke.bespOrderType == "1"?"selected":""}>订单结束</option>
								<option value="2"
									${tblBespoke.bespOrderType == "2"?"selected":""}>完成未结算</option>
						</select></td>
						<td align="right"><bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" /><span></span><bmtag:button messageKey="excel导出" type="button"
							onclick="exportSubmit('pagerForm','orderExport/pureBespoke.do')" />
					</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="10"><input type="checkbox" group="pkBespokes"
					class="checkboxCtrl" /></th>
				<th><bmtag:message messageKey="序号" /></th>
				<th><bmtag:message messageKey="订单编号" /></th>
				<th><bmtag:message messageKey="桩体编号" /></th>
				<th><bmtag:message messageKey="充电点名称" /></th>
				<th><bmtag:message messageKey="充电点地址" /></th>
				<th><bmtag:message messageKey="企业名称" /></th>
				<th><bmtag:message messageKey="收益(元)" /></th>
				<th><bmtag:message messageKey="预约开始时间" /></th>
				<th><bmtag:message messageKey="预约结束时间" /></th>
				<th><bmtag:message messageKey="实际预约结束时间" /></th>
				<th><bmtag:message messageKey="订单状态" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${firmBespokeList}" var="bus" varStatus="status">
				<tr target="pkBespoke" rel="${bus.pkBespoke }" align="center">
					<td><input name="pkBespokes" value="${bus.pkBespoke }"
						type="checkbox" /></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${bus.bespResepaymentcode }</td>
					<td>${bus.eleCode }</td>
					<td>${bus.chargePointName }</td>
					<td>${bus.chargePointAddress }</td>
					<td>${bus.comName }</td>
					<td>${bus.bespBespokeprice }</td>
					<td><fmt:formatDate value="${bus.bespBeginTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${bus.bespEndTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${bus.bespRealityTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if test="${bus.bespOrderType==0}">未支付</c:if> <c:if
							test="${bus.bespOrderType==1 && tblBespoke.bespBespokestatus!='7'}">订单结束</c:if>
							<c:if test="${tblBespoke.bespBespokestatus=='7'}">完成未结算</c:if>
							</td>
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
