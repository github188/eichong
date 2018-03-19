<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="role/findRoleList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<bmtag:link isAuth="true" target="navTab" href="role/newRole.do" 
					rel="roleOperate" messageKey="common.icon.new" dwzClass="add"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab"  href="role/editRole.do?roleId={roleId}"
					 rel="roleOperate" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="ajaxTodo" href="role/removeRole.do?roleId={roleId}"
					altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="90">
		<thead>
		<tr>
			<th><bmtag:message messageKey="序号"/></th>
			<th><bmtag:message messageKey="角色名称"/></th>
			<th><bmtag:message messageKey="角色类别"/></th>
			<th><bmtag:message messageKey="创建人"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${roleList}" var="role" varStatus="status">
			<tr target="roleId" rel="${role.roleId }" align="center">
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${role.roleName }</td>
				<td>
					<c:choose>
						<c:when test="${role.category=='1'}">超级管理员</c:when>
						<c:when test="${role.category=='2'}">普通管理员</c:when>
						<c:when test="${role.category=='3'}">纯商家</c:when>
						<c:when test="${role.category=='4'}">商家子角色</c:when>
						<c:when test="${role.category=='5'}">个体商家</c:when>
					</c:choose>
				</td>
				<td>${role.createUserAccount }</td>
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
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
