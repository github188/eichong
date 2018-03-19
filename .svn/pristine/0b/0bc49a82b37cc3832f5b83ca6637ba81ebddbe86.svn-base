<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>爱充网-个人中心</title>
    <%--custom css--%>
    <jsp:include page="../../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/order.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/personalcenter.css">
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
	  	<div class="m_right">
	   		<div class="info-show">
	      		<div class="info-details">
	        		<div class="info-title"><span class="rect"></span>
	          			<h3>您账户中当前余额</h3>
	          			<div class="right"><span class="lb" id="detail-data"></span><a class="bt detail" href="javascript:void(0);" id="pay_detail">详细</a></div>
	        		</div>
	        		<div class="info-title"><span class="rect"></span>
	          			<h3>充值</h3>
	        		</div>
					<form id="rechargeForm">
	        		<div class="div-style"><span>充值费用</span>
	          			<input type="text" id="money" name="usinAccountbalance" data-rule="required;number;" class="text"/>
	        		</div>
	        		<div class="div-style div-pay-way"><span class="sp">支付方式</span>
	          			<ul>
	            			<li>
	                			<span class="radio_icon checked"></span>
	                			<span class="type_img"><img src="<%=basePath%>/static/images/icon/zhifubao.jpg" /></span>
	            			</li>
		            		<li>
		                		<span class="radio_icon"></span>
		                		<span class="type_img"><img src="<%=basePath%>/static/images/icon/yinlian.jpg" /></span>
		            		</li>
	          			</ul>
	        		</div>
			        <div class="div-btn">
			            <a href="#" id="recharge" class="bt yellow-bt">充值</a>
			        </div>
					</form>
	        		<div class="clear"></div>
	      		</div>
	    	</div>
	  </div>
	  <div class="clear"></div>
</div>

<!--//footer-->
<jsp:include page="../../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/personalcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-purse.js"></script>
<script type="text/html" id="detail-data-tmpl">
	<!--[=balance]-->元
</script>

</body>
</html>