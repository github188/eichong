<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的收藏</title>
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
        <div class="m_right personalconsume fl">
            <div class="collect_box">
                <ul id="data-list">
                </ul>
            </div>
        
			<!--//page-->
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
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-collect.js"></script>
<script type="text/javascript">
    $(".nav ul li:last-child").css("border-right","1px solid #d4d4d4");
</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
                    <li>
                        <div class="c_tit">
                            <span class="fl"><!--[=list[i].favoriteName]--></span>
                            <span class="fr"><a href="javascript:void(0);" id="collect_remove" data-id="<!--[=list[i].favoriteId]-->"><img src="<%=basePath%>/static/images/img/member/cancel_icon.png" width="25"/></a></span>
                        </div>
                        <div class="bill_detail">
                            <div class="fl bill_img"><a><img style="width:130px;heigh:131px" src="<!--[=$absImg(list[i].favoriteImage)]-->"></a></div>
                            <div class="bill_tap fl">
                                <p class="bill_name"><a><!--[=list[i].favoriteName]--></a></p>
                                <p class="cate">属性分类：<!--[=$mateArray(list[i].favoriteProductTag, 7)]--></p>
                                <p class="cate"><span class="now_price">¥ <!--[=list[i].favoriteMarketPrice]--></span> </p>
                                <p class="del"><span class="del_price"><del>¥ (无)</del></span></p>
                            </div>
                        </div>
                    </li>
	<!--[}]-->
</script>

</body>
</html>