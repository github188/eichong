<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	function ajaxDoneCallback(json) {
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="company/list.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="company.label.company_id" /></label></td>
						<td><input name="companyId" alt="" value="" /></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="company/newCompany.do" target="navTab"
					rel="companyAddPage"messageKey="common.icon.new"
					dwzClass="add"/></li>
			<li><bmtag:link
					href="company/removeCompany.do?pageNum=${pager.pageNum }&companyId={companyId}"
					target="ajaxTodo"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
			<li><bmtag:link
					href="company/editCompany.do?pageNum=${pager.pageNum }&companyId={companyId}"
					target="navTab" rel="companyEditPage"
					messageKey="common.icon.edit" dwzClass="edit" /></li>
			<li><bmtag:link
					href="company/viewCompany.do?pageNum=${pager.pageNum }&companyId={companyId}"
					target="navTab" rel="companyViewPage"
				      messageKey="common.icon.view" dwzClass="icon" /></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="company.label.company_id" /></th>
				<th><bmtag:message messageKey="company.label.company_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="company" varStatus="status">
				<tr target="companyId" rel="${company.companyId }" align="center">
					<td>${ status.index + 1}</td>
					<td>${company.companyId }</td>
					<td>${company.notes }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
