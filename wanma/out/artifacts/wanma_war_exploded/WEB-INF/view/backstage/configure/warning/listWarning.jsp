<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
	<form id="warningForm" method="post" action="warning/listWarning.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	 	<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
					    <td><span>公司名称:</span> <input name="thwBusiName"
							value="${tblWarning.thwBusiName}"  /></td>
	                    <td><span>大账户:</span> <input name="userAccount"
							value="${tblWarning.userAccount}" /></td>
					    <td><span>手机号:</span> <input name="thwCellphone"
							value="${tblWarning.thwCellphone}" /></td>
							
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_warning" /></td>

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
				<bmtag:link isAuth="true" target="navTab" href="warning/addWarning.do"
				rel="ids"  messageKey="common.icon.new" dwzClass="add" id="addWarning" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="warning/removeWarning.do"
					rel="ids" postType="string" altKey="确认要删除该账户的预警？" dwzClass="delete" messageKey="批量删除" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th><bmtag:message messageKey="编号" /></th>
                <th><bmtag:message messageKey="公司名称" /></th>
				<th><bmtag:message messageKey="大账户账号" /></th>
				<th><bmtag:message messageKey="手机号" /></th>
				<th><bmtag:message messageKey="预警金额（元）" /></th>
				<th align="center" ><bmtag:message messageKey="操作" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${warningList}" var="item" varStatus="status">
				<tr target="id" rel="${item.thwPkId}" align="center">
					<td><input name="ids" value="${item.thwPkId}"
						type="checkbox"></td>
					<td>${item.thwPkId}</td>
					<td>${item.thwBusiName}</td>
					<td>${item.userAccount}</td>
					<td>${item.thwCellphone}</td>
					<td>${item.thwThreshold}</td>
					<td><a title="编辑" target="navTab" href="warning/editWarning.do?thwPkId=${item.thwPkId}&thwCellphone=${item.thwCellphone}&thwThreshold=${item.thwThreshold}&thwCustomerPhone=${item.thwCustomerPhone}" class="btnEdit"></a></td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>



