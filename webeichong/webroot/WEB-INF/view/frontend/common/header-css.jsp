<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link href="<%=path%>/favicon.ico" type="image/x-icon" rel="shortcut icon">
<link href="<%=path%>/static/css/page.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/static/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/static/css/archefoucs.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/static/css/infobox.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/static/css/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/static/css/loginBox.css" rel="stylesheet" type="text/css"/>
<!-- common js aere -->
<script src="<%=path%>/static/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script>
var basepath="<%=basePath%>";
</script>
