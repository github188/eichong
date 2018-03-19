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
	<form id="pagerForm" method="post" action="gate/getGateList.do"
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
				<bmtag:link isAuth="true" target="navTab" href="gate/addGateUi.do" 
					 rel="addGateUi" messageKey="添加" dwzClass="add" />
			</li>
			<%-- <li><bmtag:link href="gate/chageGateUi.do?gateId={id}"
					 target="navTab" rel="chageGateUi" messageKey="编辑"
					dwzClass="edit" /></li> --%>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="gate/removeGate.do"
					rel="ids"  postType="string" altKey="确定要删除吗？"  messageKey="批量删除" dwzClass="delete" />
			</li>
			<%-- <li><bmtag:link href="gate/showGateUi.do?gateId={id}" 
				target="navTab" rel="showGateUi" messageKey="查看" dwzClass="edit"/></li>	
			</a>
			</li> --%>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="87">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				<th><bmtag:message messageKey="名称" /></th>
				<th><bmtag:message messageKey="IP" /></th>
				<th><bmtag:message messageKey="端口" /></th>
				<th><bmtag:message messageKey="操作人" /></th>
				<th><bmtag:message messageKey="创建时间" /></th>
				<th><bmtag:message messageKey="修改时间" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${gateList}" var="gate" varStatus="status">
				<tr target="id" rel="${gate.pkGateid}" align="center">
					<td><input name="ids" value="${gate.pkGateid}"
						type="checkbox">
					</td>
					<td>${gate.gtseGatename}</td>
					<td>${gate.gtseGateip}</td>
					<td>${gate.gtseGateport}</td>
					<td>${gate.gtSeCreateUserName}</td>
					<td><fmt:formatDate value="${gate.gtseCreatetime}" type="both"/></td>
					<td><fmt:formatDate value="${gate.gtseUpdatetime}" type="both"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<%-- 	<div class="panelBar">
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
			pageNumShown="10" currentPage="${pager.pageNum}"></div> --%>
	</div>
</div>
