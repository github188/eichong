<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="no">
<meta name="apple-mobile-web-app-status-bar-style" content="no">
<meta name="format-detection" content="telephone=no">

<title>Insert title here</title>
</head>
<% %>
<script language="javascript">
 var download = "app.html";
if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {
 	window.location='eichong://?pt=2&d=<%=request.getParameter("d")%>&c=<%=request.getParameter("c")%>&et=<%=request.getParameter("et")%>';
 	setTimeout(function(){
		window.location=download;//如果超时就跳转到app下载页
	},1000);
}else if (navigator.userAgent.match(/(Android|android)/i)) { 
	//var state = null;
	//跳转类型，id，编码，桩站类型
	window.location = 'eichong://?pt=2&d=<%=request.getParameter("d")%>&c=<%=request.getParameter("c")%>&et=<%=request.getParameter("et")%>';
	window.setTimeout(function () {
		 window.location = download
		 },1000);
}else{
	window.location=download;
} 

</script>
<body>

</body>
</html>