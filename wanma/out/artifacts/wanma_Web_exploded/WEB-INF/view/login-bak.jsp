<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱充网后台管理系统</title>
<link href="${webroot }/res/login.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>/static/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/static/lib/validator/jquery.validator.js"></script>
<script src="<%=basePath%>/static/lib/jquery.MD5/jquery.md5.js"></script>

<script type="text/javascript">

  $(function(){
	   
	   $("#loginUser").click(function(){//保存用户
		   var form = document.forms[0];
			form.passwd.value =$.md5($("#passwd").val());
			form.action = "${pageContext.request.contextPath}/tologin.do";
			form.submit();
		   });
	});
</script>
</head>

<body>

	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://www.leju001.com"> <img
					src="${webroot }/res/dwz/themes/default/images/login_logo.gif" />
				</a>
			</h1>
			<div class="login_headerContent">
				<h2 class="login_title">
					<img
						src="${webroot }/res/dwz/themes/default/images/login_title.jpg" />
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="tologin.do" method="post" method="post"
					style="margin-top: 58px;">
					<p><b><font color="red">${erMessage}&nbsp;</font></b></p>
					<p>
						<label
							style="display: inline-block; width; 80 px; text-align: right;">用户名：</label>
						<input type="text" name="username" class="login_input" value="${username}"
							style="width: 130px;"  />
					</p>
					<p>
						<label
							style="display: inline-block; width; 80 px; text-align: right;">密码：</label>
						<input type="text" id="passwd" name="passwd" value=""    onfocus="this.type='password'"
							style="width: 130px;" />
					</p>

					<div class="login_bar"
						style="padding-left: 82px; padding-top: 15px;">
						<a id="loginUser"> <img
							src="${webroot }/res/dwz/themes/default/images/login_sub.png" />
						</a>
					</div>
				</form>
			</div>
			<div class="login_banner">
				<img
					src="${webroot }/res/dwz/themes/default/images/login_banner.jpg" />
			</div>
		</div>

	</div>
	<div id="login_footer">Copyright © 2014 爱充网 版权所有 浙ICP备000000000号</div>
	</div>
</body>
</html>