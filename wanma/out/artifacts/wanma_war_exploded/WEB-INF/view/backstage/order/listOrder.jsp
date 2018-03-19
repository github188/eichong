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
<form id="pagerForm" method="post" action="order/subsShopOrder.do" onsubmit="return navTabSearch(this);"> 
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
					<span>订单状态</span>
					<select name="ordeStatus" style="width:155px">
						<option value=""}>全部</option>
						<option value="1" ${tblOrder.ordeStatus == 1?"selected":""}>未支付</option>
						<option value="2" ${tblOrder.ordeStatus == 2?"selected":""}>支付成功</option>
						<option value="4" ${tblOrder.ordeStatus == 4?"selected":""}>待发货</option>
						<option value="5" ${tblOrder.ordeStatus == 5?"selected":""}>已发货</option>
						<option value="3" ${tblOrder.ordeStatus == 3?"selected":""}>完成操作</option>
					</select>
				</td>
				<td>
					<span>用户姓名</span>
					<input name="uiName" placeholder="请输入"  value="${tblOrder.uiName }"/>
				</td>
				<td>
					<span>手机账号</span>
					<input name="uiPhone" placeholder="请输入"  value="${tblOrder.uiPhone }"/>
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
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
			<th width="10"><input type="checkbox" group="pkorders"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="手机账号"/></th>
		    <th><bmtag:message messageKey="金额(元)"/></th>
		    <th><bmtag:message messageKey="数量(个)"/></th>
		    <th><bmtag:message messageKey="订单状态"/></th>
		    <th><bmtag:message messageKey="购买时间"/></th>
		    <th><bmtag:message messageKey="收货地址"/></th>
		    <th><bmtag:message messageKey="联系人"/></th>
		    <th><bmtag:message messageKey="联系电话"/></th>
		    <th><bmtag:message messageKey="订单支付类型"/></th>
		    <th><bmtag:message messageKey="操作"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${orderList}" var="order" varStatus="status">
			<tr target="pkOrder" rel="${order.pkOrder }" align="center">
				<td>
					<input name="pkorders"  value="${order.pkOrder }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${order.ordeCode }</td>
				<td>${order.uiName }<c:if test="${order.uiName==''}">${order.uiPhone }</c:if></td>
				<td>${order.uiPhone }</td>
				<td>${order.ordeTotalamount }</td>
				<td>${order.ordeCommodityTotal }</td>
				<td>
					<c:choose>
						<c:when test="${order.ordeStatus == '1'}">
							未支付
						</c:when>
						<c:when test="${order.ordeStatus == '2'}">
							支付成功
						</c:when>
						<c:when test="${order.ordeStatus == '4'}">
							待发货
						</c:when>
						<c:when test="${order.ordeStatus == '5'}">
							已发货
						</c:when>
						<c:when test="${order.ordeStatus == '3'}">
							完成操作
						</c:when>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${order.ordeCreatedate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${order.ordeReceiveingaddress }</td>
				<td>${order.ordeReceiveingname }</td>
				<td>${order.ordeReceiveingcontact }</td>
				<td>
					<c:choose>
						<c:when test="${order.ordeTypeOrder == '1'}">
							支付宝
						</c:when>
						<c:when test="${order.ordeTypeOrder == '2'}">
							银联支付
						</c:when>
					</c:choose>
				</td>
				<td>
					<a target="navTab" rel="findOrderdetail" href="<c:url value='/admin/order/findOrderdetail.do?ordeParentid=${order.pkOrder}'/>" title="订单详情" style='color:#0099FF'>订单详情</a>
					<c:choose>
						<c:when test="${order.ordeStatus == '4'}">
							<a target="ajaxTodo" href="<c:url value='/admin/order/updateFirmShopOrderStatus.do?pkOrder=${order.pkOrder}&ordeStatus=5'/>" title="确定要处理吗？" style='color:#0099FF'>已发货</a>
						</c:when>
					</c:choose>
				</td>
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
