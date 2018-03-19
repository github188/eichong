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
			<li><bmtag:link href="common/roleSelectList.do?backRel=menuRoleList&processType=menuRole&menuId=${menuModel.menuId}" target="dialog"
					rel="selMenuRole" messageKey="common.icon.new"
					dwzClass="add" id="addMenuRoleLink" /></li>
			<li><bmtag:link
					href="menu/removeMenuRole.do?roleId={roleId}"
					target="ajax" rel="menuRoleList" id="delMenuRoleLink"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="menuRoleList">
	<table class="table" width="100%" layoutH="310" >
		<thead>
			<tr>
				<th align="center"><bmtag:message messageKey="common.label.index" /></th>
				<th align="center"><bmtag:message messageKey="role.label.role_id" /></th>
				<th align="center"><bmtag:message messageKey="role.label.role_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${menuModel.roleList}" var="role" varStatus="status">
				<tr target="roleId" rel="${role.roleId }" align="center">
					<td align="center">${ status.index + 1}</td>
					<td align="center">${role.roleId }</td>
					<td align="center">${role.roleName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>
