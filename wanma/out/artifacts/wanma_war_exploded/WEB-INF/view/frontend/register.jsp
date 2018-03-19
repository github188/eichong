<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>注册页面</title>
	<%--common css--%>
	<jsp:include page="common/header-css.jsp"/>
    <%--custom css--%>
    <link rel="stylesheet" href="<%=basePath%>/static/css/register.css">
</head>
<body>

<!--//header -->
<jsp:include page="common/header.jsp"/>

<!--//section-->
<div class="content">
    <div class="content-head">
        <a>首页 > </a>
        <a>注册</a>
    </div>
    <div class="cm-con-bm clearfix">
        <div class="pic-left">
            <img src="<%=basePath%>/static/images/img/login-left.png"/>
        </div>
        <div class="content-right">
	        <form id="regist-form">
	            <div class="login-detail">
	                <h3>用户注册</h3>
	
	                <p class="wrap">
	                    <img src="<%=basePath%>/static/images/img/login-user.png"/>
	                    <input type="text" placeholder="输入您的手机号码" data-rule="手机号码:required;mobile;remote[<%=basePath%>/web/user/validPhone.do, usinPhone]" name="usinPhone"/>
	                    <span class="error f12 fc-orange msg-box" for="usinPhone"></span>
	                </p>
	
	                <p class="wrap">
	                    <img src="<%=basePath%>/static/images/img/login-password.png"/>
	                    <input type="password" placeholder="请输入密码" data-rule="密码:required;password" name="verifyPwd"/>
	                    <span class="error f12 fc-orange msg-box" for="verifyPwd"></span>
	                </p>
	
	                <p class="wrap">
	                    <img src="<%=basePath%>/static/images/img/login-password.png"/>
	                    <input type="password" placeholder="请再次输入密码" data-rule="确认密码:required;match(verifyPwd);" name="usinPassword"/>
	                    <span class="error f12 fc-orange msg-box" for="usinPassword"></span>
	                </p>
	
	                <p class="checkid wrap">
	                    <img src="<%=basePath%>/static/images/img/login-dial.png"/>
	                    <input type="text" data-rule="验证码:required" name="code"/>
						<img id="check-img" src="<%=basePath%>/web/user/getValidCode.do"/>
						<span class="error f12 fc-orange msg-box" for="code">看不清，重换一张</span>
	                </p>
	
	                <p class="checkid wrap">
	                    <img src="<%=basePath%>/static/images/img/iPhone.png"/>
	                    <input type="text" data-rule="验证码:required" name="msgCode"/>
	                    <a id="btn-message">发送验证码</a>
	                    <span class="error f12 fc-orange msg-box" for="msgCode"></span>
	                </p>
	
	                <p class="btn-register wrap">
	                    <input type="submit" class="zc f18" value="注册账户"/>
	                </p>
	            </div>
	         </form>
        </div>
    </div>
</div>

<!--//footer-->
<jsp:include page="common/footer.jsp"/>

<%--validate plugin--%>
<jsp:include page="common/validator.jsp"/>

<%--custom js--%>
<script src="<%=basePath%>/static/js/index/regist.js" type="text/javascript"></script>

</body>
</html>