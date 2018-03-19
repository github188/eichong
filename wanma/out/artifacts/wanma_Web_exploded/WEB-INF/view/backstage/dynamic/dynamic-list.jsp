<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="dynamic/list.do"
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
					<label style="align:left"><bmtag:message messageKey="新闻类型"/></label>
				</td>
				<td>
					<select  name="releType" class="select_Style required">
					<option value="">--请选择--</option>
					<option value="1" <c:if test="${dynamic.releType==1}">selected</c:if> >活动中心</option>
					<option value="2" <c:if test="${dynamic.releType==2}">selected</c:if> >爱充动态</option>
					<option value="3" <c:if test="${dynamic.releType==3}">selected</c:if> >行业资讯</option>
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
			<li><bmtag:link href="dynamic/new.do" 
					 target="navTab" rel="newEpsc"
					messageKey="添加" dwzClass="add" /></li>
			<li><bmtag:link href="dynamic/edit.do?pkRelease={id}"
					 target="navTab"
					rel="editEpsc" messageKey="编辑" dwzClass="edit" /></li>
			<li><a class="delete" target="selectedTodo" rel="ids"
				postType="string" title="确定要删除吗？"
				href="dynamic/delete.do"
				title="删除"><span>删除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="排序号" /></th>
				<th><bmtag:message messageKey="标题" /></th>
				<th><bmtag:message messageKey="类型" /></th>
				<th><bmtag:message messageKey="首图" /></th>
				<th><bmtag:message messageKey="有效" /></th>
				<th><bmtag:message messageKey="排序号" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dynamicList}" var="dynamic" varStatus="status">
				<tr target="id" rel="${dynamic.pkRelease}" align="center">
					<td><input name="ids"  value="${dynamic.pkRelease}"
						type="checkbox" />
					</td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${dynamic.releTitle}</td>
					<td>
						<c:if test="${dynamic.releType==1}">活动中心</c:if>
						<c:if test="${dynamic.releType==2}">爱充动态</c:if>
						<c:if test="${dynamic.releType==3}">行业资讯</c:if>
					</td>
					<td>
						<c:if test="${dynamic.releImg==0}">否</c:if>
						<c:if test="${dynamic.releImg==1}">是</c:if>
					</td>
					<td>
						<c:if test="${dynamic.validFlag==0}">否</c:if>
						<c:if test="${dynamic.validFlag==1}">是</c:if>
					</td>
					<td>
						${dynamic.releOrder}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
