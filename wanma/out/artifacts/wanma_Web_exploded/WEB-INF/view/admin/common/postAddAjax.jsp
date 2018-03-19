<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

	<table class="table" width="100%" layoutH="310">
		<thead>
			<tr>
				<th align="center"><bmtag:message messageKey="common.label.index" /></th>
				<th align="center"><bmtag:message messageKey="company.label.company_name" /></th>
				<th align="center"><bmtag:message messageKey="post.label.post_id" /></th>
				<th align="center"><bmtag:message messageKey="post.label.post_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${postSelectList}" var="post" varStatus="status">
				<tr target="postId" rel="${post.postId }" align="center">
					<td align="center">${ status.index + 1}</td>
					<td align="center">${post.companyName }</td>
					<td align="center">${post.postId }</td>
					<td align="center">${post.postName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>