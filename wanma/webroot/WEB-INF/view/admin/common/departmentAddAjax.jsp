<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

	<table class="table" width="100%" layoutH="310">
		<thead>
			<tr>
				<th align="center"><bmtag:message messageKey="common.label.index" /></th>
				<th align="center"><bmtag:message messageKey="company.label.company_name" /></th>
				<th align="center"><bmtag:message messageKey="department.label.department_id" /></th>
				<th align="center"><bmtag:message messageKey="department.label.department_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${departmentSelectList}" var="department" varStatus="status">
				<tr target="departmentIds" rel="${department.companyId},${department.departmentId}" align="center">
					<td align="center">${ status.index + 1}</td>
					<td align="center">${department.companyName }</td>
					<td align="center">${department.departmentId }</td>
					<td align="center">${department.departmentName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>