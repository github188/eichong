<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>支付</title>
    <%--common css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link href="<%=request.getContextPath()%>/static/css/order.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<!--评价详情内容-->
<div class="content">
    <!-------导航-------->
    <div class="content-head">
        <a>首页 > </a> <a>新能源商城</a>
    </div>

    <!--中间内容-->
    <div class="cont_box">
        <div class="payCont">
            <div class="pay_div">
                <span class="pay_left">充值费用</span>
                <span class="pay_right price yellow">￥800元 </span>
            </div>
            <div class="pay_div">
                <span class="pay_left">支付方式</span>
                <div class="pay_right">
                    <div class="pay_type fl">
                        <span class="radio_icon checked"></span>
                        <span class="type_img"><img src="<%=request.getContextPath()%>/static/images/img/pay.jpg" style="padding-top: 5px;" /></span>
                    </div>
                    <div class="pay_type fl ml">
                        <span class="radio_icon"></span>
                        <span class="type_img"><img src="<%=request.getContextPath()%>/static/images/img/bank.png"></span>
                    </div>
                </div>
            </div>
            <div class="pay_div">
                <span class="pay_left">支付密码</span>
                <span class="pay_right price yellow"><input type="text" class="words" /> </span>
            </div>
            <div class="type_a">
                <a class="type_btn sure">确认</a>
                <a class="type_btn cancal">取消</a>
            </div>
        </div>
    </div>

</div>
<jsp:include page="../common/footer.jsp" />
<script type="text/javascript">
    $(".eva_detail ul li:last-child").css("border-bottom","0");
</script>
</body>
</html>