<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Album Test</title>
</head>
<body>
获取token
<br>
<form action="<%=basePath%>/tk/getToken.do" method="get">

<table>
<tr>
<td>OperatorID<font color="red">*</font></td>
<td><input id="OperatorID" name="OperatorID" type="text" value="9010" /></td>
</tr>

<tr>
<td>OperatorSecret<font color="red">*</font></td>
<td><input id="OperatorSecret" name="OperatorSecret" type="text" value="BB92408F92B1B291BB22CF35D97FC0D8" /></td>
</tr>




</table>

<input type="submit" value="submit" />
<input type="reset" value="reset" />

</form>

</body>
</html>