<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<%-- <div class="pageHeader">
	<form id="pagerForm" method="post" action="user/list.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><bmtag:message
									messageKey="user.label.user_id" /></td>
						<td><input name="userId" alt="" value="" /></td>
						<td><bmtag:message messageKey="user.label.user_name" /></td>
						<td><input name="userName" alt=""
							value="${userModel.userName }" /></td>
						<td><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>--%>
<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/userSelectList.do?backRel=postUserList&processType=post" target="dialog"
					rel="selDeptUser"messageKey="common.icon.new"
					dwzClass="add" id="addPostUserLink"/></li>
			<li><bmtag:link
					href="post/removePostUser.do?pageNum=${pager.pageNum }&userId={userId}"
					target="ajax" rel="postUserList" id="delPostUserLink"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="postUserList">
	<table class="table" width="100%" layoutH="310">
		<thead>
			<tr>
				<th align="center"><bmtag:message messageKey="common.label.index" /></th>
				<th align="center"><bmtag:message messageKey="user.label.user_id" /></th>
				<th align="center"><bmtag:message messageKey="user.label.user_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user" varStatus="status">
				<tr target="userId" rel="${user.userId }" align="center">
					<td align="center">${ status.index + 1}</td>
					<td align="center">${user.userId }</td>
					<td align="center">${user.userName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>
