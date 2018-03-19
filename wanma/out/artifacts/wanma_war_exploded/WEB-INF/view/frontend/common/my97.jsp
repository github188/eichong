<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script language="javascript" type="text/javascript" src="<%=basePath%>/static/lib/My97DatePicer/WdatePicker.js"></script>
