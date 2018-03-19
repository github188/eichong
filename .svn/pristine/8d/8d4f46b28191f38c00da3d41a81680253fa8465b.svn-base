<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的预约</title>
    <%--custom css--%>
    <jsp:include page="../../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/personalcenter.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/member.css">
    <style type="text/css">
		.clock {
		    position: relative;
		    width: 300px;
		    height: 260px;
		    margin: 20px auto;
		}
		.cursor{
		    position: absolute;
		    width: 188px;
		    height: 188px;
		    top: 36px;
		    left: 53px;
		}
    </style>
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
        <div class="m_right fl" id="detail-data"></div>

    </div>

</div>

<!--//footer-->
<jsp:include page="../../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/personalcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-order.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery.rotate.js"></script>

<script type="text/html" id="detail-data-tmpl">
    <!--[if(pkBespoke){]-->
			<div class="notic_order">
                <div class="h_tit">预约倒计时提醒</div>
                <p class="tc fz">你的预约当前有效</p>
                <p class="tc">
        			<div class="clock">
						<img src="<%=basePath%>/static/images/img/order/clock_1.png"/>
						<div class="cursor">
							<img id="rotate-image" src="<%=basePath%>/static/images/img/order/cursor.png"/>
            			</div>
       				</div>
				</p>
                <p class="tc">倒计时：</p>
                <p class="tc times" id="count_down"></p>
            </div>
   			<div class="new_order" >
        		<div class="h_tit">最新预约 <a class="yellow more_link" href="javascript:void(0);" id="order_list">查看更多</a></div>
        		<div class="new_det">
            		<p>您已成功预约买断了<!--[=$formatDate(bespBeginTime, 1)]-->&nbsp;<!--[=bespBespoketimes]-->的时间段，
						预约桩体编号为：<span class="num"><!--[=elPiElectricPileCode]--></span>，
						当前已冻结金额为 <span class="new_price yellow"><!--[=bespFrozenamt]--></span> 元。
            		</p>
           			 <p class="new_time"><!--[=$formatDate(bespCreatedate)]--></p>
        		</div>
        		<div class="order_btn">
            		<a class="continue" href="javascript:void(0);" id="order_continue">继续预约</a>
            		<a class="calcel" href="javascript:void(0);" id="order_remove">取消预约</a>
       			</div>
			</div>
    <!--[}else{]-->
			<p>您当前没有有效的预约纪录！</p>
    <!--[}]-->

</script>

</body>
</html>