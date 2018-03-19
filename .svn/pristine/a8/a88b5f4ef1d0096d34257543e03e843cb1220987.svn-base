<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="evaluate/saveFilterWord.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
					<li><bmtag:link isAuth="true" href="evaluate/newFilterWord.do"
							target="navTab" rel="filterAddPage" messageKey="common.icon.new"
							dwzClass="add" id="addLink" /></li>
					<li><bmtag:link isAuth="true"
							href="evaluate/deleteFilterWord.do?filterWord={filterWord}"
							target="ajaxTodo" altKey="common.msg.delete.confirm"
							messageKey="common.icon.delete" dwzClass="delete" /></li>
			</ul>
	</div>
	<table class="table" width="100%" layoutH="60">
		<thead>
		<tr>
			<th>序号</th>
			<th>过滤字</th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${filterWordsList}" var="filterWord" varStatus="status">
			<tr target="filterWord" rel="${filterWord }" align="center">
				<td>${ status.index + 1 }</td>
				<td>${filterWord }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="panelBar">
		<div class="pages">
			<span>
			<!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div> --%>
</div>
