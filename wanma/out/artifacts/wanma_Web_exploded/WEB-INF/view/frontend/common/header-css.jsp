<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link href="<%=basePath%>/static/css/base.css" rel="stylesheet"/>
<link href="<%=basePath%>/static/css/common.css" rel="stylesheet"/>
