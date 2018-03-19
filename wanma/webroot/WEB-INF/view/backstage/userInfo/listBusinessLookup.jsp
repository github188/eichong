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
<div class="pageHeader"> 


<form id="pagerForm" method="post" action="${webroot}/admin/userManager/businessListLookup.do" onsubmit="return dwzSearch(this, 'dialog');"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<input type="hidden" name="ucPayMode" value="${user.ucPayMode }"/>
	<input type="hidden" name="isQuicklyApply" value="${user.isQuicklyApply }"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>姓名</span>
					<input name="normRealName" placeholder="请输入" value="${user.normRealName }"/>
				</td>
				<td>
					<span>手机</span>
					<input name="userAccount" placeholder="请输入" value="${user.userAccount }"/>
				</td>
				<%-- <td>
					<span>邮箱</span>
					<input name="normEmail" placeholder="请输入"  value="${user.normEmail }"/>
				</td> --%>
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
	<table class="table" width="100%" layoutH="85" targetType="dialog">
		<thead>
		<tr>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="手机号"/></th>
		    <th><bmtag:message messageKey="邮箱"/></th>
		    <th><bmtag:message messageKey="查找带回"/></th>
		    
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${userList}" var="user" varStatus="status">
			<tr target="pkUserinfo" rel="${user.userId }" align="left">
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${user.normRealName}</td>
				<td>${user.userAccount }</td>
				<td>${user.normEmail }</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'${user.userId}', orgName:'${empty user.normRealName?user.userAccount:user.normRealName}'})" title="查找带回">选择</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
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
		<div class="pagination" targetType="dialog" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
