<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8"/>
    <%--common css--%>
    <jsp:include page="common/header-css.jsp"/>
    <link href="<%=request.getContextPath()%>/static/css/about.css" rel="stylesheet"/>
</head>
<body>
<!--//header -->
<jsp:include page="common/header.jsp"/>
<div class="content">
    <div class="nav">
        <ul>
            <li class="current"><a>业务纵览</a></li>
            <li><a>成功案例</a></li>
            <li><a>新闻中心</a></li>
            <li><a>行业动态</a></li>
            <li><a>招贤纳士</a></li>
            <li><a>联系我们</a></li>
            <li><a>关于我们</a></li>
        </ul>
    </div>
    <div class="content-content">
        <img src="<%=request.getContextPath()%>/static/images/icon/about_banner.png"/>

        <div class="article"></div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>