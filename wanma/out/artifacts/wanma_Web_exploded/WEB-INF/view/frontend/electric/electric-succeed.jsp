<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预约成功</title>
    <%--custom css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/order.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/bespeak.css">
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--//section-->
<div class="content">
    <div class="content-head">
        <a>首页 > </a> 
        <a>电桩查找 > </a>
        <a>电桩详情 > </a>
   	    <a>电桩预约 > </a>
        <a>预约成功</a>
    </div>
    <div class="cont_box">
        <div class="cont-left">
            <div class="succeed_cont">
                <div class="tc"><img src="<%=basePath%>/static/images/img/order/succeed_img.png" /></div>
                <p class="suc_tit tc" style="color: #f08307;font-size: 30px;">预约成功！</p>
                <div class="notice">
                    <p>预约生效，您的充电名额已预留，</p>
                    <p>请准时前往··· </p>
                </div>
                <div class="btn_cont">
                    <div class="pay_btn"><a href="javascript:void(0);" id="purse_view">我要去充值</a></div>
                    <div class="a_btn">
                        <a href="javascript:void(0);" id="order_view">查看我的预约</a>
                        <%-- <a class="daohang rel"><span class="abs nav_icon"><img src="<%=basePath%>/static/images/img/order/nav_icon.png" style="width: 15px;" /></span>导航到目的地</a> --%>
                    </div>
                </div>
            </div>
        </div>

        <div class="cont-right">
            <p class="title-top">相关充充电点 <a class="more_link">更多</a></p>
            <ul id="data-list">
            </ul>
        </div>
    </div>

</div>
<div class="clear"></div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-succeed.js"></script>
<script type="text/html" id="data-list-tmpl">
		<!--[for(i=0;i<list.length;i++){]-->
				<li>
					<a href="javascript:void(0);" id="plant_detail" data-id="<!--[=list[i].relatedId]-->">
						<img src="<!--[=$absImg(list[i].relatedImage)]-->" />
					</a>
					<label class="one"><!--[=list[i].relatedName]--></label>
					<label class="two">距离<!--[=list[i].distance]-->M</label>
					<span >
					<!--[for(j = 0;j < 5;j++){]-->
						<!--[if(j < list[i].relatedStart){]-->
							<img class="img-star" src="<%=basePath%>/static/images/img/star.png">
						<!--[}else{]-->
							<img class="img-star" src="<%=basePath%>/static/images/icon/star_gray.png">
						<!--[}]-->
					<!--[}]-->
					</span >
				</li>
     	<!--[}]-->
</script>

</body>
</html>