<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<%--<div class="pageHeader"> 
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
								messageKey="common.button.search" type="button"
								onclick="onSearchClick();"
								id="deptUserSearchBtn" />
								<bmtag:link
					href="department/searchDeptUserList.do?pageNum=${pager.pageNum }&userId={userId}&userName={userName}"
					target="ajax" rel="departmentUserList"
					altKey="common.msg.delete.confirm"
					messageKey="common.button.search" dwzClass="button" /></td>
					</tr>
				</tbody>
			</table>
		</div>
</div>--%>
<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/userSelectList.do?backRel=departmentUserList&processType=department" target="dialog"
					rel="selDeptUser" messageKey="common.icon.new"
					dwzClass="add" id="addDeptUserLink"/></li>
			<li><bmtag:link
					href="department/removeDeptUser.do?userId={userId}"
					target="ajax" rel="departmentUserList"  id="delDeptUserLink"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="departmentUserList">
	<table class="table" width="100%" layoutH="310" >
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
