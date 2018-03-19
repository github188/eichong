<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Album Test</title>
</head>
<body>
根据城市编号查询充电点信息
<br>

<!-- 
<form action="http://192.168.0.249:8001/interaction-service-2.0.0/playTimes" method="post">
<form action="http://localhost:8080/playTimes" method="post">
<form action="http://192.168.1.249:8001/interaction-service-2.0.0/playTimes" method="get">
 <form action="<c:url value='/user/addAblum.do'/>" method="post"> 
 -->
<form action="<%=basePath%>/eichong/syncStubGroupInfo.do" method="post">

<table>
<tr>
<td>org<font color="red">*</font></td>
<td><input id="org" name="org" type="text" value="9010" /></td>
</tr>
<tr>
<td>cityCode<font color="red">*</font></td>
<td><input id="cityCode" name="cityCode" type="text" value="330100" /></td>
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
<script type="text/javascript">


function init(){
	var  obj=document.getElementsByName("pre");
	var test=obj.innerHTML;
	var str = JSON.stringify(test, undefined, 4);

	output(syntaxHighlight(str));
	
}
function output(inp) {
	document.body.appendChild(document.createElement('pre')).innerHTML = inp;
}

function syntaxHighlight(json) {
	json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
	return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
		var cls = 'number';
		if(/^"/.test(match)) {
			if(/:$/.test(match)) {
				cls = 'key';
			} else {
				cls = 'string';
			}
		} else if(/true|false/.test(match)) {
			cls = 'boolean';
		} else if(/null/.test(match)) {
			cls = 'null';
		}
		return '<span class="' + cls + '">' + match + '</span>';
	});
}






</script>
</html>