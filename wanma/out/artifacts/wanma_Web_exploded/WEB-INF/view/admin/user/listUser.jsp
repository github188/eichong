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
<form id="pagerForm" method="post" action="user/userList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td style="align:left">
					<span><bmtag:message messageKey="帐号"/></span>
					<input name="userAccount"  value="${tblUser.userAccount }"/>
				</td>
				<td style="align:left">
					<span><bmtag:message messageKey="姓名"/></span>
					<input name="adminName"  value="${tblUser.adminName }"/>
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
				<bmtag:link isAuth="true" target="navTab" href="user/newUser.do" 
					rel="userAddPage" messageKey="common.icon.new" dwzClass="add"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="user/editUser.do?userId={userId}"
					 rel="userEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="user/removeUser.do" rel="pkUserinfos"
					altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="ajaxTodo" href="user/resetPassword.do?userId={userId}"
					 messageKey="common.icon.reset.password" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="user/viewUser.do?userId={userId}&userLevel=1"
				 rel="userViewPage" messageKey="common.icon.view" dwzClass="icon"/>
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/frostAllList.do?rel=userList" 
					target="selectedTodo" altKey="确定要 冻结吗？" rel="pkUserinfos"
					 postType="string" messageKey="冻结/批量冻结" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/unFrost.do?rel=userList" 
					target="selectedTodo" altKey="确定要 解冻吗？" rel="pkUserinfos"
					 postType="string" messageKey="解冻/批量解冻" dwzClass="edit" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
		<tr align="center">
			<th width="10"><input type="checkbox" group="pkUserinfos"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
			<th><bmtag:message messageKey="账号"/></th>
			<th><bmtag:message messageKey="姓名"/></th>
			<th><bmtag:message messageKey="手机号"/></th>
			<th><bmtag:message messageKey="角色"/></th>
			<th><bmtag:message messageKey="状态"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${userList}" var="user" varStatus="status">
			<tr target="userId" rel="${user.userId }" align="center">
				<td>
					<input name="pkUserinfos"  value="${user.userId}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${user.userAccount }</td>
				<td>${user.adminName }</td>
				<td>${user.adminPhone }</td>
				<td>${user.roleNames }</td>
				<td>
					<c:choose>
						<c:when test="${user.userStatus == '1'}">
							正常
						</c:when>
						<c:when test="${user.userStatus == '2'}">
							冻结
						</c:when>
					</c:choose></td>
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
