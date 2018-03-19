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
<form id="pagerForm" method="post" action="order/ShopOrder.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>商品编号</span>
					<input name="prodProductCode" placeholder="请输入"  value="${orderdetail.prodProductCode }"/>
				</td>
				<td>
					<span>商品名称</span>
					<input name="ordeProductname" placeholder="请输入"  value="${orderdetail.ordeProductname }"/>
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
	<table class="table" width="100%" layoutH="86">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkorders"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
			<th><bmtag:message messageKey="商家名称"/></th>
		    <th><bmtag:message messageKey="商品名称"/></th>
		    <th><bmtag:message messageKey="商品编号"/></th>
		    <th><bmtag:message messageKey="收益(元)"/></th>
		    <th><bmtag:message messageKey="单价"/></th>
		    <th><bmtag:message messageKey="数量(个)"/></th>
		    <th><bmtag:message messageKey="购买时间"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${shopList}" var="shop" varStatus="status">
			<tr target="pkOrder" rel="${shop.pkOrderdetail }" align="center">
				<td>
					<input name="pkorders"  value="${shop.pkOrderdetail }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${shop.prodUser }</td>
				<td>${shop.ordeProductname }</td>
				<td>${shop.prodProductCode }</td>
				<td>${shop.ordeTotalamount }</td>
				<td>${shop.ordePrice }</td>
				<td>${shop.ordeQuantity }</td>
				<td>
					<fmt:formatDate value="${shop.ordeCreatedate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
