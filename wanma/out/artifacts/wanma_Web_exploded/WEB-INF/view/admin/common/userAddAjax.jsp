<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

	<table class="table" width="100%" layoutH="310">
		<thead>
			<tr>
				<%-- <th align="center"><bmtag:message messageKey="common.label.index" /></th> --%>
				<th align="center"><bmtag:message messageKey="账户id" /></th>
				<th align="center"><bmtag:message messageKey="账户归属" /></th>
				<th align="center"><bmtag:message messageKey="账户级别" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userSelectList}" var="user" varStatus="status">
				<tr target="userId" rel="${user.userId }" align="center">
					<%-- <td align="center">${ status.index + 1}</td> --%>
					<td align="center">${user.userId }</td>
					<td align="center">${user.userAccount }</td>
					<td align="center">
						<c:choose>
							<c:when test="${user.userLevel == 1}">超级管理员</c:when>
							<c:when test="${user.userLevel == 2}">普通管理员</c:when>
							<c:when test="${user.userLevel == 3}">纯商家用户</c:when>
							<c:when test="${user.userLevel == 5}">个体商家用户</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>