<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
td.rightTools {
	float: right;
}
</style>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="companyManager/getCompanyList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			
		<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>	
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="公司名称"/></label>
				</td>
				<td>
					<input type="text" name="cpyCompanyname" value="${tblCompany.cpyCompanyname}">
				</td>
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="地址"/></label>
				</td>
				<td>
					<input type="text" name="cpyCompanyaddress" value="${tblCompany.cpyCompanyaddress}">
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
			<li>
				<bmtag:link isAuth="true" target="navTab" href="companyManager/addCompanyUi.do"  
				 	rel="addCompanyUi" messageKey="新增" dwzClass="add"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="companyManager/changeCompanyUI.do?companyId={id}" 
					rel="changeCompanyUI" messageKey="编辑" dwzClass="edit"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="companyManager/removeCompany.do"
					altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="companyManager/showCompanyUI.do?companyId={id}"  
				 rel="showCompanyUI" messageKey="查看" dwzClass="edit"/>
			</li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="109">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="企业名称" /></th>
				<th><bmtag:message messageKey="企业地址" /></th>
				<th><bmtag:message messageKey="授权人名称" /></th>
				<th><bmtag:message messageKey="授权人电话" /></th>
				<th><bmtag:message messageKey="创建时间" /></th>
				<th><bmtag:message messageKey="修改时间" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="company" varStatus="status">
				<tr target="id" rel="${company.pkCompanyid}" align="center">
					<td><input name="ids"  value="${company.pkCompanyid}"
						type="checkbox" />
					</td>
					<td>${company.cpyCompanyname}</td>
					<td>${company.cpyCompanyaddress}</td>
					<td>${company.cpyAuthorizedname}</td>
					<td>${company.cpyAuthorizedphone}</td>
					<td><fmt:formatDate value="${company.cpyCreatedate}" type="both"/></td>
					<td><fmt:formatDate value="${company.cpyUpdatedate}" type="both"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>  <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select>  共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div> 
	</div>
</div>
