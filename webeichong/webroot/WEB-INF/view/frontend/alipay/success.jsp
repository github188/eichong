<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>充值信息</title>
<jsp:include page="../common/header-css.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

</head>
<body>
	<div id="wrapper">
		<!--//header -->
		<jsp:include page="../common/header2.jsp" />

		<div id="UserCenter"> 

<div class="DivTop" style="padding-top:100px;margin:100px 0; text-align:center;">充值成功<br />
<a style="font-size:16px; line-height:40px;" href="<%=basePath%>/mycenter.do">返回订单列表查看</a></div>


</div>
<div class="clear"> </div>
	</div>



		<!--//footer-->
		<jsp:include page="../common/footer2.jsp" />
</body>
</html>
