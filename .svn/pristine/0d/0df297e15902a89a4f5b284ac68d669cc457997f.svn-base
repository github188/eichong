<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Album Test</title>
</head>
<body>
结束充电指令下发
<br>

<!-- 
<form action="http://192.168.0.249:8001/interaction-service-2.0.0/playTimes" method="post">
<form action="http://localhost:8080/playTimes" method="post">
<form action="http://192.168.1.249:8001/interaction-service-2.0.0/playTimes" method="get">
 <form action="<c:url value='/user/addAblum.do'/>" method="post"> 
 -->
<form action="<%=basePath%>/cczc/chargeStop.do" method="post">

<table>
<tr>
<td>org<font color="red">*</font></td>
<td><input id="org" name="org" type="text" value="9002" /></td>
</tr>
<tr>
<td>orderId<font color="red">*</font></td>
<td><input id="orderId" name="orderId" type="text" value="" /></td>
</tr>

<tr>
<td>token<font color="red">*</font></td>
<td><input id="token" name="token" type="text" value="" /></td>
</tr>

</table>

<input type="submit" value="submit" />
<input type="reset" value="reset" />

</form>

</body>
</html>