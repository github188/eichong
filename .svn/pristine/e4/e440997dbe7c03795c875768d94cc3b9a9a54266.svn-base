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
	<form id="pagerForm" method="post" action="order/elecPartnerOrder.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>订单编号&nbsp;</span> <input name="chorCode"
							width="30px" placeholder="请输入"
							value="${tblChargingOrder.chorCode }" /></td>
						<td><span>充电时间</span> <input id="startDate_busCharge"
							name="beginChargetime" placeholder="请选择"
							value="${tblChargingOrder.beginChargetime}" class="date"
							onClick="WdatePicker({el:'startDate_busCharge',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_busCharge\')}'})"><span>至</span></td>
						<td><input
							id="endDate_busCharge" name="endChargetime"
							value="${tblChargingOrder.endChargetime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_busCharge',minDate:'#F{$dp.$D(\'startDate_busCharge\')}'})">
						</td>
						<td><span>桩体编号</span> <input
							name="eleCode" placeholder="请输入"
							value="${tblChargingOrder.eleCode }" /></td>
					</tr>
					<tr>
						<td><span>订单状态&nbsp;</span> <select name="chorChargingstatus"
							style="width: 155px">
								<option value=""}>全部</option>
								<option value="1"
									${tblChargingOrder.chorChargingstatus == 1?"selected":""}>未支付</option>
								<option value="2"
									${tblChargingOrder.chorChargingstatus == 2?"selected":""}>支付成功</option>
								<option value="3"
									${tblChargingOrder.chorChargingstatus == 3?"selected":""}>完成未结算</option>
						</select></td>
						<td><span>合作商名称&nbsp;&nbsp;&nbsp;&nbsp;</span> <input
							name="partnerName" placeholder="请输入"
							value="${tblChargingOrder.partnerName }" /></td>
						
						<td><span>用户标示</span> <input name="chorParterUserLogo"
							placeholder="请输入" value="${tblChargingOrder.chorParterUserLogo }" />
						</td>
						<td align="right"><bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="110">
		<thead>
			<tr align="center">
				<th width="2%" ><input type="checkbox" group="pkCharges"
					class="checkboxCtrl"  /></th>
				<th width="3%"><bmtag:message messageKey="序号" /></th>
				<th width="12%"><bmtag:message messageKey="订单编号" /></th>
				<th width="8%"><bmtag:message messageKey="桩体编号" /></th>
				<th width="8%"><bmtag:message messageKey="电桩名称" /></th>
				<th width="8%"><bmtag:message messageKey="用户标示" /></th>
				<th width="5%"><bmtag:message messageKey="合作商" /></th>
				<th width="4%"><bmtag:message messageKey="金额(元)" /></th>
				<th width="3%"><bmtag:message messageKey="电量" /></th>
				<th width="4%"><bmtag:message messageKey="充电电费(元)" /></th>
				<th width="5%"><bmtag:message messageKey="充电服务费(元)" /></th>
				<th width="11%"><bmtag:message messageKey="充电时间段" /></th>
				<th width="5%"><bmtag:message messageKey="订单状态" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ChargePartnerList}" var="Partner" varStatus="status">
				<tr target="pkChargingorder" rel="${Partner.pkChargingorder }"
					align="center">
					<td><input name="pkCharges" value="${Partner.pkChargingorder }"
						type="checkbox" /></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${Partner.chorCode }</td>
					<td>${Partner.eleCode }</td>
					<td>${Partner.chargePointName }</td>
					<td>${Partner.chorParterUserLogo }</td>
					<td>${Partner.partnerName }</td>
					<td>${Partner.chorMoeny }</td>
					<td>${Partner.chorQuantityelectricity }</td>
					<td>${Partner.chorChargemoney }</td>
					<td>${Partner.chorServicemoney }</td>
					<td>${Partner.chorTimequantum }</td>
					<td><c:if test="${Partner.chorChargingstatus==1}">未支付</c:if> <c:if
							test="${Partner.chorChargingstatus==2}">支付成功</c:if> <c:if
							test="${Partner.chorChargingstatus==3}">完成未结算</c:if></td>
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
