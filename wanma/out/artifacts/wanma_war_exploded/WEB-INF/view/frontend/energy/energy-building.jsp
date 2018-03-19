<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>网站正在建设中</title>
    <jsp:include page="../common/header-css.jsp"/>
    <link href="<%=basePath%>/static/css/xiamain.css" rel="stylesheet"/>
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--评价详情内容-->
<div class="content">
    <!-------导航-------->
    <div class="content-head">
        <a>首页 > </a> <a>新能源商城</a>
    </div>

    <!--中间内容-->
    <div class="cont_box">
        <div class="web_cont">
            <p class="tc"><img src="<%=basePath%>/static/images/img/notic_icon.png"/></p>
            <div class="web_p">
                <p>我们正在紧张的规划二期，产品暂时未上架</p>
                <p>请您再等一等！</p>
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