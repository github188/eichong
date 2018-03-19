<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Album Test</title>
</head>
<body>
开始充电指令下发
<br>


<form action="<%=basePath%>/eichong/chargeStart.do" method="post">

<table>
<tr>
<td>org<font color="red">*</font></td>
<td><input id="org" name="org" type="text" value="9010" /></td>
</tr>

<tr>
<td>outOrderId<font color="red">*</font></td>
<td><input id="outOrderId" name=outOrderId type="text" value="201707290091" /></td>
</tr>
<tr>
<td>outUserId<font color="red">*</font></td>
<td><input id="outUserId" name="outUserId" type="text" value="tjd0101" /></td>
</tr>
<tr>
<td>pointId<font color="red">*</font></td>
<td><input id="pointId" name="pointId" type="text" value=3301061987457973 /></td>
</tr>
<tr>
<td>gunId<font color="red">*</font></td>
<td><input id="gunId" name="gunId" type="text" value="1" /></td>
</tr>
<tr>
<td>userAmount<font color="red">*</font></td>
<td><input id="userAmount" name="userAmount" type="text" value="10" /></td>
</tr>
<tr>
<td>token<font color="red">*</font></td>
<td><input id="token" name="token" type="text" value="43f573817f2c4c0f098366ea84b2cbe6" /></td>
</tr>


</table>

<input type="submit" value="submit" />
<input type="reset" value="reset" />

</form>

</body>
</html>