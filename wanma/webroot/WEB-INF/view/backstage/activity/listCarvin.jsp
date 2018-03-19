<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">

function exportSubmit(formId,url){
	var form = navTab.getCurrentPanel().find("#"+formId);
	var oldUrl = form.attr("action");
	var oldSubmitMethod = form.attr("onsubmit");
	form.attr("onsubmit",null)
	form.attr("action",url);
	form.submit();
	form.attr("action",oldUrl);
	form.attr("onsubmit",oldSubmitMethod);
}

</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="activity/listCarvin.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	 	<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>

						<td><span>车辆识别码:</span> <input name="cvVinCode"
							value="${tblCarVin.cvVinCode}"  /></td>
	                    <td><span>合作方:</span> <input name="cvName"
							value="${tblCarVin.cvName}" /></td>
					
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_chactivity" /></td>

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
				<bmtag:link isAuth="true" target="selectedTodo" href="activity/removeCarvin.do"
					rel="ids" postType="string" altKey="确定删除吗？" dwzClass="delete" messageKey="删除" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="activity/toImportCarvin.do"
					 messageKey="导入" dwzClass="edit"  />
			</li>
			<li><a class="icon" targettype="navTab"
				target="dwzExport" onclick="exportSubmit('pagerForm','orderExport/carvin.do')"> <span>导出EXCEL</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th><bmtag:message messageKey="车辆识别码" /></th>
                <th><bmtag:message messageKey="合作方" /></th>
				<th><bmtag:message messageKey="服务费" /></th>
				<th><bmtag:message messageKey="创建时间" /></th>
				<th><bmtag:message messageKey="车牌号" /></th>
			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${carvinList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkCarVin}" align="center">
					<td><input name="ids" value="${item.pkCarVin}"
						type="checkbox"></td>
					<td>${item.cvVinCode}</td>
					<td>${item.cvName}</td>
					<td>${item.cvServicemoney}</td>
					<td><fmt:formatDate value="${item.cvCreatedate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${item.cvLicenseNumber}</td>
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

<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
if(userIdForShow==8231){
	$(".pages").html("");
}
});
	
</script>


