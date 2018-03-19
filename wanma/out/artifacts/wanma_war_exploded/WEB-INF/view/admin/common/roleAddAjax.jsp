<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

	<table class="table" width="100%" layoutH="310">
		<thead>
			<tr>
				<th align="center"><bmtag:message messageKey="common.label.index" /></th>
				<th align="center"><bmtag:message messageKey="role.label.role_id" /></th>
				<th align="center"><bmtag:message messageKey="role.label.role_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roleSelectList}" var="role" varStatus="status">
				<tr target="roleId" rel="${role.roleId }" align="center">
					<td align="center">${ status.index + 1}</td>
					<td align="center">${role.roleId }</td>
					<td align="center">${role.roleName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>