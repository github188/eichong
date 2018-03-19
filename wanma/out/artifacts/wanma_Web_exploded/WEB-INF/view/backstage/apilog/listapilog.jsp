<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="pageHeader">
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="50" align="center"><bmtag:message messageKey="接口名称" /></th>
				<th width="20" align="center"><bmtag:message messageKey="接口" /></th>
				<th width="80" align="center"><bmtag:message messageKey="访问数" /></th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${apiLogList}" var="log"  >
			<tr>
				<td>${log.apiname}</td>
				<td>${log.url}</td>
				<td>${log.count}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>