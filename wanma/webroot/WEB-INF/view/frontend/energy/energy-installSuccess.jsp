<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>预约成功</title>
    <%--common css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/register.css">
    <style>
    .content {
        width: 1162px;
        height: 555px;
        border-radius: 15px;
        margin: 0px auto;
        color: #4a4a4a;
    }
       .pic{
        margin-top: 40px;
        height: 320px;
        background:  url(<%=request.getContextPath()%>/static/images/icon/anzhuang.png) center center no-repeat;
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
            <a>预约成功</a>
        </div>
        <div class="pic"></div>
        <p class="tm mt10">您的安装预约已提交成功！</p>
        <p class="tm mt10">近期会有安装人员电联您，</p>
        <p class="tm mt10">请注意接听~</p>
    </div>

<jsp:include page="../common/footer.jsp" />

</body>
</html>