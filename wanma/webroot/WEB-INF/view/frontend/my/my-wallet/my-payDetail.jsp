<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>爱充网-个人中心-我的钱包-消费详情</title>
    <%--custom css--%>
    <jsp:include page="../../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/personalcenter.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/member.css">
</head>
<body>

<!--//header -->
<jsp:include page="../../common/header.jsp"/>

<!--//section-->
<div class="personalcenter personalconsume">
    <div class="content-head"><a>首页 > </a> <a>个人中心主页</a></div>
    <div class="cont_box">

		<!--//menu -->
		<jsp:include page="../my-menu.jsp"/>
		
        <!--右侧-->
  		<div class="content">
    		<div class="nav">
      			<ul>
        			<li class="current">本月消费记录</li>
        			<li data-type="trimester">三个月内消费记录</li>
      			</ul>
    		</div>
    		<div class="info-show">
      			<div class="info-details" style="display:block;">
        			<table class="info-table" border="0" id="data-list">
        			</table>
      			</div>
    		</div>
    		<div class="clear"></div>
        
			<!--//page-->
	        <jsp:include page="../../common/page.jsp"/>
        
  		</div>
  		<div class="clear"></div>
  	</div>
</div>

<!--//footer-->
<jsp:include page="../../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/personalcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-payDetail.js"></script>
<script type="text/javascript">
    $(".nav ul li:last-child").css("border-right","1px solid #d4d4d4");
</script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
			<tr>
				<td><span class="arrow"></span><span><!--[=$formatDate(list[i].recordTime, 4)]--></span></td>
				<td><!--[=$mateArray(list[i].recordTitle, 6)]--></td>
				<td><!--[=list[i].recordContent]--></td>
				<td><!--[=list[i].recordMoney]-->元 </td>
			</tr>
	<!--[}]-->
</script>

</body>
</html>