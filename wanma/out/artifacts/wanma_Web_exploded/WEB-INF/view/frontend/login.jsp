<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录页面</title>
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
        <a>登录</a>
    </div>
    <div class="cm-con-bm clearfix">
        <div class="pic-left">
            <img src="<%=basePath%>/static/images/img/login-left.png"/>
        </div>
        <div class="content-right">
            <form id="login-form">
                <div class="login-detail">
                    <h3>登录</h3>

                    <p class="wrap">
                        <img src="<%=basePath%>/static/images/img/login-user.png"/>
                        <input placeholder="输入您的手机号码或者用户名" data-rule="手机号码或者用户名:required;mobile" name="usinPhone"/>
                        <span class="error f12 fc-orange msg-box" for="usinPhone"></span>
                    </p>

                    <p class="wrap">
                        <img src="<%=basePath%>/static/images/img/login-password.png"/>
                        <input placeholder="请输入密码" data-rule="密码:required;" name="usInPassword" type="password"/>
                        <span class="error f12 fc-orange msg-box" for="usInPassword"></span>
                    </p>

                    <div class="clearfix rem-wrap">
                        <span class="fl rem-pass">
                            <input type="checkbox" name="rememberMe" class="checkbox" checked='checked'>
                            <span class="tx f14 fc-gray">记住账号</span>
                        </span>
                        <a class="fr f14 for-pass fc-gray" id="reset-jump" href="javascript:void(0);">忘记密码?</a>
                    </div>
                    <p class="checkid wrap">
                        <img src="<%=basePath%>/static/images/img/login-dial.png"/>
                        <input type="text" data-rule="验证码:required;" name="code"/>
                        <img id="check-img" src="<%=basePath%>/web/user/getValidCode.do"/>
                        <span class="error f12 fc-orange msg-box" for="code">看不清，重换一张</span>
                    </p>

                    <p class="btn-register wrap">
                        <input class="zc f18" type="submit" value="登录"/>
                    </p>

                    <div>
                        <span class="f12">还没有账号？</span>
                        <a class="f12 fc-orange" href="javascript:void(0);" id="regist-jump">免费注册</a>
                    </div>

                    <div class="other-login">
                        <div class="line">
                            <p class="fl tm tx fc-gray">选择其他方式登录</p>
                        </div>
                        <div class="img-wrap tm">
                            <span class="qq"></span>
                            <span class="weixin"></span>
                            <span class="weibo"></span>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!--//footer-->
<jsp:include page="common/footer.jsp"/>
<%--validate plugin--%>
<jsp:include page="common/validator.jsp"/>
<script src="<%=basePath%>/static/js/index/login.js"></script>

</body>
</html>