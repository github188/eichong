<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Album Test</title>
</head>
<body>
订单汇总
<br>

<form action="<%=basePath%>/cczc/checkChargeOrders.do" method="get">

<table>
<tr>
<td>org<font color="red">*</font></td>
<td><input id="org" name="org" type="text" value="9002" /></td>
</tr>

<tr>
<td>startTime<font color="red">*</font></td>
<td><input id="startTime" name="startTime" type="text" value="" /></td>
</tr>
<tr>
<td>endTime<font color="red">*</font></td>
<td><input id="endTime" name="endTime" type="text" value="" /></td>
</tr>

<tr>
<td>token<font color="red">*</font></td>
<td><input id="token" name="token" type="token" value="t" /></td>
</tr>


</table>

<input type="submit" value="submit" />
<input type="reset" value="reset" />

</form>

</body>
</html>