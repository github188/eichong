<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="order/proOrderList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>订单编号</span>
					<input name="ordeCode" placeholder="请输入" value="${tblOrder.ordeCode }"/>
				</td>
				<td>
					<span>成交时间</span>
					<input id="proOrder_start_create_date" name="start_create_date" value="${tblOrder.start_create_date }" class="date"  style="width:155px"
					onClick="WdatePicker({el:'proOrder_start_create_date',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'proOrder_end_create_date\')}'})" >
			              至 <input id="proOrder_end_create_date" name="end_create_date" value="${tblOrder.end_create_date }" class="date"  style="width:155px"
			         onClick="WdatePicker({el:'proOrder_end_create_date',minDate:'#F{$dp.$D(\'proOrder_start_create_date\')}'})"  >
				</td>
			</tr>
			<tr>
				<td>
					<span>订单状态</span>
					<select name="ordeStatus" style="width:155px">
						<option value="">请选择</option>
						<option value="1" ${tblOrder.ordeStatus == 1?"selected":""}>待支付</option>
						<option value="2" ${tblOrder.ordeStatus == 2?"selected":""}>已支付</option>
					</select>
				</td>
				<td>
					<span>订单类型</span>
					<select name="ordeTypeOrder" style="width:162px">
						<option value="">请选择</option>
						<option value="1" ${tblOrder.ordeTypeOrder == 1?"selected":""}>支付宝</option>
						<option value="2" ${tblOrder.ordeTypeOrder == 2?"selected":""}>银联支付</option>
					</select>
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
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
			<li><bmtag:link href="order/viewProOrder.do?pageNum=${pager.pageNum }&pkOrder={pkOrder}"
				target="navTab" messageKey="common.icon.view" dwzClass="icon"/></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkProducts"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="商品名称"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th>
		    <th><bmtag:message messageKey="金额（元）"/></th>
		    <th><bmtag:message messageKey="单价（元）"/></th>
		    <th><bmtag:message messageKey="数量"/></th>
		    <th><bmtag:message messageKey="订单状态"/></th>
		    <th><bmtag:message messageKey="成交时间"/></th>
		    <th><bmtag:message messageKey="收货人"/></th>
		    <th><bmtag:message messageKey="收货地址"/></th>
		    <th><bmtag:message messageKey="安装地址"/></th>
		    <th><bmtag:message messageKey="订单类型"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${proOrderList}" var="proOrder" varStatus="status">
			<tr target="pkOrder" rel="${proOrder.pkOrder }" align="center">
				<td>
					<input name="pkOrders"  value="${proOrder.pkOrder}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${proOrder.prName}</td>
				<td>${proOrder.ordeCode}</td>
				<td>${proOrder.olTotal }</td>
				<td>${proOrder.olPrice }</td>
				<td>${proOrder.olQuan }</td>
				<td>
					<c:choose>
						<c:when test="${proOrder.ordeStatus == '1'}">
							待支付
						</c:when>
						<c:when test="${proOrder.ordeStatus == '2'}">
							支付成功
						</c:when>
					</c:choose>
				</td>	
				<td>
					<fmt:formatDate value="${proOrder.ordeCreatedate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>${proOrder.ordeReceiveingname }</td>
				<td>${proOrder.ordeReceiveingaddress }</td>
				<td>${proOrder.installAdress }</td>
				<td>
					<c:choose>
						<c:when test="${proOrder.ordeTypeOrder == '1'}">
							支付宝
						</c:when>
						<c:when test="${proOrder.ordeTypeOrder == '2'}">
							银联支付
						</c:when>
					</c:choose>
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
