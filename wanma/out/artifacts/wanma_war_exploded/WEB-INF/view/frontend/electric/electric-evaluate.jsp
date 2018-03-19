<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>评价详情</title>
    <%--custom css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/evaluate_detail.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/xiamain.css">
    <link rel="stylesheet" href="<%=basePath%>/static/lib/sliderbox/css/jquery.slideBox.css">
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--//section-->
<div class="content">
    <!-------导航-------->
    <div class="content-head">
        <a>首页 > </a> <a>新能源商城</a>
    </div>

    <!--中间内容-->
    <div class="cont_box">
        <div class="eva_detail">
            <div class="eva_all"></div>
            <ul class="eva_ul" id="data-list">
            </ul>

        </div>
        
		<!--//page-->
        <jsp:include page="../common/page.jsp"/>
        
    </div>


</div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/lib/sliderbox/js/jquery.slideBox.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-evaluate.js"></script>
<script type="text/javascript">
    $(".eva_detail ul li:last-child").css("border-bottom","0");
</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
                <li>
                    <div class="user_pic"><img src="<!--[=$absImg(userImage)]-->"/></div>
                    <div class="user_info">
                        <div class="user_det">
                            <div class="left_info fl">
                                <!--[=list[i].prcoUsername]--><span><!--[=$formatDate(list[i].prcoCreatetime, 6)]--></span>
                            </div>
                            <div class="right_info fr">
                                <span>
								<!--[for(j = 0;j < 5;j++){]-->
									<!--[if(j < list[i].prcoCommentstart){]-->
										<img class="img-star" src="<%=basePath%>/static/images/img/star.png">
									<!--[}else{]-->
										<img class="img-star" src="<%=basePath%>/static/images/icon/star_gray.png">
									<!--[}]-->
								<!--[}]-->
								</span>
								<span class="acount"><!--[=list[i].prcoCommentstart]--></span>
                            </div>
                        </div>
                        <p><!--[=list[i].prcoContent]--></p>
                    </div>
                </li>
	<!--[}]-->
</script>
</body>
</html>