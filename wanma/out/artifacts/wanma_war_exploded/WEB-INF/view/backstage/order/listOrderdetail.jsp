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
<form id="pagerForm" method="post" action="order/subsInstall.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<%-- <ul class="toolBar">
			<li><bmtag:link href="order/findInstalldetail.do?pageNum=${pager.pageNum }&pkInstalldetail={pkInstalldetail}"
					target="navTab" rel="InstalldetailViewPage" messageKey="common.icon.view"
					dwzClass="icon"/>
			</li>
		</ul> --%>
	</div>
	<table class="table" width="100%" layoutH="85">
		<thead>
		<tr>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="商品名称"/></th>
		    <th><bmtag:message messageKey="商品编号"/></th>
		    <th><bmtag:message messageKey="单价"/></th>
		    <th><bmtag:message messageKey="数量"/></th>
		    <th><bmtag:message messageKey="总价"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${orderdetailList}" var="detail" varStatus="status">
			<tr target="pkOrderdetail" rel="${detail.pkOrderdetail }" align="center">
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${detail.ordeProductname }</td>
				<td>${detail.prodProductCode }</td>
				<td>${detail.ordePrice }</td>
				<td>${detail.ordeQuantity }</td>
				<td>${detail.ordeTotalamount }</td>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<%-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> --%>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
