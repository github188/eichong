<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<style type="text/css">
 .binddiv{
 margin-left: -120px;/*Chrome、Safari、Firefox、Opera */ 
 margin-left: -70px;/* Firefox */ 
 }
 .bindfont{
 font-size: 5px;/*Chrome、Safari、Firefox、Opera */ 
  font-size: 13px; /* Firefox */ 
 }
</style>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="electric/getCompanyNumberList.do"
		onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="公司名称" /> </label></td>
						<td><input name="cpyCompanyname"
							value="${tblCompany.cpyCompanyname}" /></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
								<td><div class="button" > <div class="buttonContent"><button type="button" multLookup="orgId" warn="请选择公司标识"><font class="bindfont" >指定公司标识</font></button></div></div></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table"  width="100%" layoutH="109">
		<thead>
			<tr>
			   <th><input type="checkbox" group="orgId"  class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="公司名称" /></th>
				<th><bmtag:message messageKey="公司标识" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="company" varStatus="status">
				<tr target="id" rel="${company.cpyCompanyNumber}" align="center">
				    <td><input name="orgId"  value="{companyNumber:${company.cpyCompanyNumber},companyName:'${company.cpyCompanyname}'}" type="checkbox"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${company.cpyCompanyname}</td>
					<td>${company.cpyCompanyNumber}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="dialog"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
	var listSize = ${listSize};
	if(listSize == 0){
		alert("未搜索到公司标识，请联系管理员添加！")
	}
</script>
