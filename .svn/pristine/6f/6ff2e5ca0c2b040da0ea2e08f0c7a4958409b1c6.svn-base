<%@page import="com.sun.org.apache.bcel.internal.generic.SIPUSH"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作日志查看</title>
<style type="text/css">
table.gridtable {
	width:100%;
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>


<!-- Table goes in the document BODY -->
<table class="gridtable" >
<tr>
	<th>序号</th><th>时间</th><th>IP</th><th>访问URL</th>
</tr>
<c:forEach var="item" varStatus="status" items="${logList}"> 
<c:set var="str" value="${fn:split(item, '|')}" />
<fmt:parseDate value="${str[0]}" var="date" pattern="yyyyMMddHHmmss"/>
<tr>
	<td>${status.index+1}</td><td><fmt:formatDate value="${date}" type="both"/></td><td>${str[1]}</td><td>${str[2]}</td>
</tr>
</c:forEach>

</table>
<table>

</table>
</body>
</html>
