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

<div class="pageContent">
	<form id="pagerForm" method="post" action="offlineCharge/listOfflineCharge.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<bmtag:link isAuth="true" href="offlineCharge/OfflineChargeAddUi.do"  
					target="navTab" rel="newOfflineCharge" messageKey="新增" dwzClass="add" id="addLink"/>
			</li>
			<li>
				<bmtag:link isAuth="true" href="offlineCharge/OfflineChargeEditUi.do?pkCompanyid={pkCompanyid}"
					target="navTab" rel="editOfflineCharge" messageKey="common.icon.edit" dwzClass="edit" />
			</li>	
			<li>
					  <bmtag:link isAuth="true" target="selectedTodo" href="offlineCharge/delelteOfflineCharge.do"
				rel="pkCompanyids"	  altKey="确定删除吗？" dwzClass="delete" messageKey="批量删除" />
			</li>	
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr align="center" >
					<th width="10"><input type="checkbox" group="pkCompanyids" class="checkboxCtrl" /></th>
					<th><bmtag:message messageKey="序号"/></th>
				    <th><bmtag:message messageKey="企业名称"/></th>
				    <th><bmtag:message messageKey="公司标识"/></th>
				    <th><bmtag:message messageKey="最大离线充电次数"/></th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${companyList}" var="item" varStatus="status">
			<tr target="pkCompanyid" rel="${item.pkCompanyid }" align="center">
				<td>
					<input name="pkCompanyids"  value="${item.pkCompanyid}" type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${item.cpyCompanyname}</td>
				<td>${item.cpyCompanyNumber }</td>
				<td>${item.cpyNum }</td>
			</tr>
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