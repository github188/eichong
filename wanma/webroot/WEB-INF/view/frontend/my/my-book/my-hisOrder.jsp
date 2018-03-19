<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>查看更多详情</title>
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
            <div class="h_tit">历史预约记录</div>
            <ul class="more_list" id="data-list">
            </ul>
        
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
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-hisOrder.js"></script>
<script type="text/javascript">
    $(".more_list li:last-child").css("border-bottom",'0');
</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
 				<li>
                    <div class="new_det">
                        <p>您已成功预约买断了<!--[=$formatDate(list[i].bespBeginTime, 1)]-->  <!--[=list[i].bespBespoketimes]--> 的时间段，预约桩体编号为:
                            <!--[=list[i].elPiElectricPileCode]-->，当前已冻结金额为 <!--[=list[i].bespFrozenamt]--> 元。
                        </p>
                        <p class="new_time"><!--[=$formatDate(list[i].bespBeginTime)]--></p>
                    </div>
                </li>
	<!--[}]-->
</script>

</body>
</html>