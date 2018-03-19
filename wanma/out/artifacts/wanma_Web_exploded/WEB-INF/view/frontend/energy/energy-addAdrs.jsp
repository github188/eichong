<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增收货地址</title>
    <%--common css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/register.css">
    <style>
    .pic-left, .content-right{
        width: 45%;
    }
    .content-right{
        width: 55%;
    }
    .login-detail{
        width: auto;
    }
    .login-detail .title{
        width: 85px;
        line-height: 48px;
        text-align: right;
        font-size: 16px;
        color: #999; 
    }
    .login-detail .wrap{
        margin-top: 0;
        margin-left: 10px;
        width: 300px;
    }
    .login-detail input{
        float: none;
    }
    .error{
        left: 312px;
    }
</style>
</head>
<body>
<!--//header -->
<jsp:include page="../common/header.jsp"/>


<div class="content">
        <div class="content-head">
            <a>首页 > </a> 
            <a>新能源商城 > </a>
            <a>新品区列表 > </a>
            <a>商品详情 > </a>
            <a>购物车 > </a>
            <a>确定订单 > </a>
            <a>新增收货地址 </a>
        </div>
        <div class="cm-con-bm clearfix">
            <div class="pic-left">
                <img src="<%=request.getContextPath()%>/static/images/img/login-left.png"/>
            </div>
            <form id="addAddress-form">
            <div class="content-right">
                <div class="login-detail">
                    <div class="mt10 clearfix">
                        <p class="fl tm title">联系人姓名</p>
                        <p class="wrap fl">
                            <input name="pradUsername" data-rule="联系人姓名:required" placeholder="输入您的真实姓名">
                            <span class="error f12 fc-orange msg-box" for="pradUsername"></span>
                        </p>
                    </div>

                    
                    <div class="mt10 clearfix">
                        <p class="fl tm title">手机号码</p>
                        <p class="wrap fl">
                            <input name="pradPhonenumber" data-rule="手机号码或者用户名:required;mobile" placeholder="11位手机号码">
                            <span class="error f12 fc-orange msg-box" for="pradPhonenumber"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">邮政编码</p>
                        <p class="wrap fl">
                            <input name="pradPostalCode" data-rule="邮政编码:required;postcode" placeholder="6位邮编">
                            <span class="error f12 fc-orange msg-box" for="pradPostalCode"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">省、市、区</p>
                        <p class="wrap fl">
                            <input name="pradAddress" data-rule="省、市、区:required" placeholder="请输入">
                            <span class="error f12 fc-orange msg-box" for="pradAddress"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">街道</p>
                        <p class="wrap fl">
                            <input name="pradStreet" data-rule="街道:required" placeholder="请输入">
                            <span class="error f12 fc-orange msg-box" for="pradStreet"></span>
                        </p>
                    </div>
                    <p class="btn-register wrap" style="margin-left:80px;">
                        <!-- <a class="zc f18 ">保存</a> -->
                        <input class="zc f18" type="submit" value="保存" />
                    </p>

                </div>
                
               </form> 
                
            </div>
        </div>
    </div>
<jsp:include page="../common/footer.jsp" />
<%--validate plugin--%>
<jsp:include page="../common/validator.jsp"/>

<script src="<%=request.getContextPath()%>/static/js/energy/addAdrs.js" type="text/javascript"></script>
</body>
</html>