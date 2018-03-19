<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的足迹</title>
    <%--custom css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/personalcenter.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/member.css">
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--//section-->
<div class="content">
    <div class="content-head"><a>首页 > </a> <a>个人中心主页</a></div>
    <div class="cont_box">

		<!--//menu -->
		<jsp:include page="my-menu.jsp"/>
		
        <!--右侧-->
        <div class="m_right fl">
            <div class="foot_box">
                <ul id="data-list">
                </ul>
            </div>
        </div>

    </div>

</div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/personalcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-footprint.js"></script>
<script type="text/javascript">
    $(".nav ul li:last-child").css("border-right","1px solid #d4d4d4");
</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
			<!--[if(i % 2 == 0){]-->
                    <li class="right_foot">
                        <div class="step_info left">
                            <p class="f_tit tr"><!--[=list[i].foprContent]--></p>
                            <p class="f_time"><!--[=$formatDate(list[i].foprCreatedate, 'yyyy/MM/dd hh:mm')]--></p>
                        </div>
                    </li>
			<!--[}else{]-->
                    <li class="left_foot">
                        <div class="step_info right">
                            <p class="f_tit tl"><!--[=list[i].foprContent]--></p>
                            <p class="f_time"><!--[=$formatDate(list[i].foprCreatedate, 'yyyy/MM/dd hh:mm')]--></p>
                        </div>
                    </li>
			<!--[}]-->
	<!--[}]-->
</script>

</body>
</html>