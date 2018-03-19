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
<form id="pagerForm" method="post" action="business/productList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>商品名称</span>
					<input name="prodProductname" placeholder="请输入" value="${tblProduct.prodProductname }"/>
				</td>
				<td>
					<span>商品编号</span>
					<input name="prodProductcode" placeholder="请输入" value="${tblProduct.prodProductcode }"/>
				</td>
				<td>
					<span>上架状态</span>
					<select name="prodProductlsadd" style="width:162px">
						<option value="">全部</option>
						<option value="1" ${tblProduct.prodProductlsadd == 1?"selected":""}>待上架</option>
						<option value="2" ${tblProduct.prodProductlsadd == 2?"selected":""}>已上架</option>
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
			<li><bmtag:link href="business/newProduct.do" target="navTab"
					rel="ProductAddPage" messageKey="common.icon.new" dwzClass="add"
					id="addLink" /></li>

			<li><bmtag:link
					href="business/editProduct.do?pageNum=${pager.pageNum }&pkProduct={pkProduct}"
					target="navTab" rel="ProductEditPage"
					messageKey="common.icon.edit" dwzClass="edit" /></li>

			<li><a class="edit" target="selectedTodo" rel="pkProducts"
				postType="string" title="确定要批量上架吗？"
				href="<c:url value='/admin/business/updateStatusAll.do'/>"
				title="批量上架"><span>批量上架</span> </a>
			<li><a class="delete" target="selectedTodo" rel="pkProducts"
				postType="string" title="确定要批量删除吗？"
				href="<c:url value='/admin/business/removeProductAll.do'/>"
				title="批量删除"><span>批量删除</span> </a>
			<li><bmtag:link href="business/viewProduct.do?pageNum=${pager.pageNum }&pkProduct={pkProduct}"
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
		    <th><bmtag:message messageKey="商品编号"/></th>
		    <th><bmtag:message messageKey="所属类目"/></th>
		    <th><bmtag:message messageKey="价格（元）"/></th>
		    <th><bmtag:message messageKey="上架状态"/></th>
		    <th><bmtag:message messageKey="下架时间"/></th>
		    <th><bmtag:message messageKey="库存数量（个）"/></th>
		    <th><bmtag:message messageKey="操作"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${ProductList}" var="Product" varStatus="status">
			<tr target="pkProduct" rel="${Product.pkProduct }" align="center">
				<td>
					<input name="pkProducts"  value="${Product.pkProduct}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${Product.prodProductname}</td>
				<td>${Product.prodProductcode}</td>
				<td>${Product.caName }</td>
				<td>${Product.prodMarketprice }</td>
				<td>
					<c:choose>
						<c:when test="${Product.prodProductlsadd == '1'}">
							待上架
						</c:when>
						<c:when test="${Product.prodProductlsadd == '2'}">
							已上架
						</c:when>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${Product.prodUpdatedate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${Product.prodStockquantity }</td>
				<td>
					<c:choose>
						<c:when test="${Product.prodProductlsadd == '1'}">
							<a target="ajaxTodo" href="<c:url value='/admin/business/updateStatusList.do?pkProduct=${Product.pkProduct}&prodProductlsadd=2'/>" title="确定要处理吗？" style='color:#0099FF'>手动上架</a>
						</c:when>
						<c:when test="${Product.prodProductlsadd == '2'}">
							已上架
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
