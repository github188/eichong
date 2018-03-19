<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script src="<%=basePath%>/static/lib/validator/jquery.validator.js"></script>
<script src="<%=basePath%>/static/lib/validator/local/zh_CN.js"></script>
<script src="<%=basePath%>/static/lib/jquery.MD5/jquery.md5.js"></script>
