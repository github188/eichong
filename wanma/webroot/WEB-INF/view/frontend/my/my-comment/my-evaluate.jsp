<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的评价</title>
    <%--custom css--%>
    <jsp:include page="../../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/personalcenter.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/member.css">
</head>
<body>

<!--//header -->
<jsp:include page="../../common/header.jsp"/>

<!--//section-->
<div class="content">
    <div class="content-head"><a>首页 > </a> <a>个人中心主页</a></div>
    <div class="cont_box">

		<!--//menu -->
		<jsp:include page="../my-menu.jsp"/>
		
        <!--右侧-->
        <div class="m_right fl">
            <div class="evaluate_box">
                <ul id="data-list">
                </ul>
            </div>
            <jsp:include page="../../common/page.jsp"/>
        </div>

    </div>

</div>

<!--//footer-->
<jsp:include page="../../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/personalcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-evaluate.js"></script>
<script type="text/javascript">
    $(".evaluate_box ul li .c_tit .bill_check").click(function(){
       $(this).toggleClass("checked");
    });

</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
 					<li>
                        <div class="c_tit">
                            <span class="fr"><a href="javascript:void(0);" id="evaluate_remove" data-id="<!--[=list[i].productCommentId]-->">
								<img src="<%=basePath%>/static/images/img/member/cancel_icon.png" width="25"></a></span>
                        </div>
                        <div class="eva_box">
                            <div class="user_pic fl"><img src="<!--[=$absImg(list[i].prCoUserImage)]-->"></div>
                            <div class="user_info fl">
                                <p class="user_name"><!--[=list[i].prCoUserName]--><span class="u_time"><!--[=list[i].prCoCreatedate]--></span></p>
                                <div class="eva_cont">
                                    <p><!--[=list[i].prCoContent]--></p>
                                </div>
                            </div>
                        </div>
                    </li>
	<!--[}]-->
</script>

</body>
</html>