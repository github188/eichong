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

<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/roleSelectList.do?backRel=appApiRoleList&processType=appApiRole" target="dialog"
					rel="selDeptRole" messageKey="common.icon.new"
					dwzClass="add" id="addAppRoleLink"/></li>
			<li><bmtag:link
					href="appApi/removeAppApiRole.do?roleId={roleId}"
					target="ajax" rel="appApiRoleList" id="delAppRoleLink" 
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="appApiRoleList">
	<table class="table" width="100%" layoutH="310" >
		<thead>
			<tr>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="role.label.role_id" /></th>
				<th><bmtag:message messageKey="role.label.role_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${appApiModel.roleList}" var="role" varStatus="status">
				<tr target="roleId" rel="${role.roleId }" align="center">
					<td>${ status.index + 1}</td>
					<td>${role.roleId }</td>
					<td>${role.roleName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>
