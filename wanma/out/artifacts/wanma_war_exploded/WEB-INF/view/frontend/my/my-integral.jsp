<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的积分</title>
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
        <div class="m_right personalconsume fl">
            <div class="int_cont" id="detail-data">
            </div>
            <div class="int_box">
                <p class="box_tit">积分兑换规则</p>
                <div class="problem_box">
                    <p class="yellow">神马是积分？</p>
                    <p>答：用户（经过注册验证的用户）在使用本款APP充电消费后获得的一种奖励，从而实现爱充网对于客户的关怀，及争取对客户忠诚度的重视。</p>
                    <p class="yellow">如何获得积分？</p>
                    <p>答：注册验证的用户，只需使用本款APP充电消费，当用户充电消费一度电，就可以获得一个积分啦。</p>
                </div>
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
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-integral.js"></script>
<script type="text/html" id="detail-data-tmpl">
                <div class="mem_head int_head fl"><img src="<!--[=$absImg(userImage)]-->"></div>
                <div class="mem_info fl">
                    <p><span class="leftTit">用 户 名：</span> <span class="rightName"><!--[=userRealName]--></span></p>
                    <p><span class="leftTit">当前积分：</span> <span class="rightName"><!--[=userIntegral]--></span></p>
                </div>
</script>

</body>
</html>