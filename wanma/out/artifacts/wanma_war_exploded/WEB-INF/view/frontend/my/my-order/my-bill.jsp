<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>我的订单</title>
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
            <div class="nav">
                <ul>
                    <li data-type="1" class="current">全部</li>
                    <li data-type="2">待支付</li>
                    <li data-type="3">待安装</li>
                    <li data-type="4">已完成</li>
                </ul>
            </div>
            <div class="bill_box">
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
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-bill.js"></script>
<script type="text/javascript">
    $(".nav ul li:last-child").css("border-right","1px solid #d4d4d4");
    $(".bill_box ul li .bill_info .bill_check").click(function(){
        $(this).toggleClass("checked");
    })
</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
					<li>
                        <div class="bill_info">
                            <span class="fl bill_num">订单编号：<!--[=list[i].orderId]--> </span>
                            <span class="fl date"><!--[=list[i].commodityTime]--></span>
                            <span class="fr bill_state no_payment">
							<!--[if(list[i].orderType == '1'){]-->
								<!--[=$mateArray(list[i].orderState, 3)]-->
							<!--[}else{]-->
								<!--[=$mateArray(list[i].orderState, 4)]-->
							<!--[}]-->
							</span>
                        </div>
					<!--[for(j = 0;j < list[i].orders.length;j++){]-->
                        <div class="bill_detail">
                            <div class="fl bill_img"><a><img style="  width: 130px;height: 131px;" src="<!--[=$absImg(list[i].orders[j].commodityImage)]-->"/></a></div>
                            <div class="bill_tap fl">
                                <p class="bill_name"><a><!--[=list[i].orders[j].commodityName]--></a></p>
                                <p class="cate">属性分类：<!--[=$mateArray(list[i].orders[j].commodityProperty, 13)]--></p>
                                <p class="cate">x <!--[=list[i].orders[j].commodityCount]--></p>
                                <p class="cate"><span class="now_price">¥ <!--[=list[i].orders[j].commodityProductPrice]--></span> <span class="del_price"><del>¥ (无)</del></span></p>
                            </div>
                        </div>
					<!--[}]-->
					<!--[if(list[i].orderState == '1'){]-->
                        <a class="pay_btn billBtn"><!--[=$mateArray(list[i].orderType, 8)]--></a>
					<!--[}]-->
                    </li>
	<!--[}]-->
</script>

</body>
</html>