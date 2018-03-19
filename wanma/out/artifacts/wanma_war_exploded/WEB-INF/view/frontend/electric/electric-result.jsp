<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>搜索电桩</title>
    <%--custom css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/xiamain_s.css">
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--//section-->
<div class="content">
	<div class="content-head">
           <a>首页 > </a> 
           <a>充电桩 > </a>
           <a>检索结果</a>
       </div>

	<!-- 智能排序 -->
	<dl class="list-tit">
		<dt class="fl">
			<img src="<%=basePath%>/static/images/icon/right_icon.png">&nbsp;智能排序：</dt>
		<dd>
			<a class="current">不限</a>
			<a>距离</a>
			<a>价格</a>
			<a>好评</a>
		</dd>
	</dl>

	<!-----第二部分内容------>
	<div class="electric-num">

		<ul class="electric-con" id="data-list">
		</ul>
	</div>
	<jsp:include page="../common/page.jsp"/>
</div>
<div class="clear"></div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-result.js"></script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
			<li>
				<a href="javascript:void(0);" id="pile_detail" data-id="<!--[=list[i].electricId]-->" data-type="<!--[=list[i].electricType]-->">
					<img src="<!--[=$absImg(list[i].electricImage)]-->"/>
				</a>
				<h4>
					<!--[=list[i].electricName]-->
				</h4>

				<!--[if(list[i].electricType == 1){]-->
				<p class="mt5">[<!--[=$mateArray(list[i].electricUse, 11)]-->]</p>
				<p class="mt5 tips-wrap">
					<span class="tips"><!--[=$mateArray(list[i].electriChargingMode, 10)]--></span>
					<span class="tips"><!--[=$mateArray(list[i].electricPowerInterface, 7)]--></span>
					<span class="tips">额定功率<!--[=list[i].electricPowerSize]-->kw</span>
					<span class="tips">最大电流<!--[=list[i].electricMaxElectricity]-->A</span>
				</p>
				<!--[}else{]-->
				<p>
					[共 <span class="fc-orange f16"><!--[=list[i].electricPileSum]--></span> 个桩体]
				</p>
				<!--[}]-->

				<p class="mt5">
					地址：<!--[=$ellipsis(list[i].electricAddress, 20)]-->
				</p>
			</li>	
	<!--[}]-->
</script>
</body>
</html>