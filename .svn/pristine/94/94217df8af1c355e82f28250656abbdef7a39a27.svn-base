<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
	<element title="123">
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="product/typeSpanList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label>产品型号 </label>
						</td>
						<td><input name="tsTypeSpan"
							value="${tblTypespan.tsTypeSpan}" />
						</td>
						<td style="align: left"><label style="align: left">产品名称 </label>
						</td>
						<td><input name="tsModelName"
							value="${tblTypespan.tsModelName}" />
						</td>
							<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" />
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
				<bmtag:link isAuth="true" target="navTab" href="product/addTypeSpanUi.do"
					rel="typeSpanOperate" messageKey="common.icon.new" dwzClass="add" id="typeSpanAddPage" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="product/changeTypeSpanUi.do?pkTypeSpanId={id}"
					 rel="typeSpanOperate" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="product/viewTypeSpanUi.do?pkTypeSpanId={id}"
					 rel="typeSpanViewPage" messageKey="common.icon.view" dwzClass="icon" id="typeSpanShowPage" />
			</li>
			 <li>
				<bmtag:link isAuth="true" target="navTab" href="product/updateEpVisionUi.do?pkTypeSpanId={id}"
					 rel="updateEpVisionUi"  messageKey="选择电桩"  dwzClass="edit" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th>产品型号</th>
				<th>名称</th>
				<th>说明</th>
				<th>文件ID和名称</th>
				<th>外部产品型号</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tblTypespanList}" var="typeSpan" varStatus="status">
				<tr target="id" rel="${typeSpan.pk_TypeSpanId}" align="center">
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${typeSpan.ts_TypeSpan}</td>
					<td>${typeSpan.ts_ModelName}</td>
					<td>${typeSpan.ts_FactTag}</td>
					<td>${typeSpan.ts_FileName}</td>
					<td>${typeSpan.ts_ProductNumber}</td>
					<td>${typeSpan.ts_Remarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">共${pager.total }条, 共${pager.pageTotal}页</span>
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