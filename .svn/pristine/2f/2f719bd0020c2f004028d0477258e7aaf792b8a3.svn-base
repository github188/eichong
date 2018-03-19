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
<form id="pagerForm" method="post" action="feedback/feedbackList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="user/newUser.do"  
				target="navTab" rel="userAddPage" messageKey="common.icon.new" dwzClass="add"  id="addLink"/></li>
			<li><bmtag:link href="user/removeUser.do?pageNum=${pager.pageNum }&userId={userId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/></li>
			<li><bmtag:link href="user/editUser.do?pageNum=${pager.pageNum }&userId={userId}"
				target="navTab" rel="userEditPage" messageKey="common.icon.edit" dwzClass="edit" /></li>
			<li><bmtag:link href="user/viewUser.do?pageNum=${pager.pageNum }&userId={userId}"
				target="navTab" rel="userViewPage" messageKey="common.icon.view" dwzClass="icon"/></li>
			<li><bmtag:link href="user/resetPassword.do?userId={userId}"
				target="ajaxTodo" messageKey="common.icon.reset.password" dwzClass="edit" /></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr>
				<th>图片名称</th>
				<th>轮播次序</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${homepageList}" var="homepage" varStatus="status">
			<tr target="feedbackId" rel="${homepage.feedbackId }" align="center">
				<td>${homepage.phone }</td>
				<td>${homepage.userIname }</td>
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
