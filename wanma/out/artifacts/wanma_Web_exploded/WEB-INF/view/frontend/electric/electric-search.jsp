<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
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
           <a>电桩查找 > </a>
           <a id="title">列表模式</a>
       </div>
	<!--查找电桩内容-->
	<div class="search-electric">
		<ul class="search-title">
			<li class="current" style="margin-right: 10px;">
				<img src="<%=basePath%>/static/images/icon/list_icon.png"/>
				列表模式
			</li>
			<li data-title="map">
				<img src="<%=basePath%>/static/images/icon/map_icon.png"/>
				地图模式
			</li>
		</ul>
		<div class="search-con">
			<!--列表模式内容 -->
			<div class="list-con">
				<div class="list-tit">
					<img src="<%=basePath%>/static/images/icon/right_icon.png">筛选：</div>
				<ul class="list-con-first list-con" id="screen-list">
				</ul>
			</div>
		</div>
		<!-----第二部分内容------>
		<div class="electric-num toggle none">
			<ul class="electric-con" id="data-list">
			</ul>
		</div>
		<!-- -地图模式内容- -->
		<div class="map-con toggle none" id="allMap">
		</div>
		<jsp:include page="../common/page.jsp"/>
	</div>
</div>
<div class="clear"></div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=pBYPU9rfQCTflKcU2jSF9iuy"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MapWrapper/1.2/src/MapWrapper.min.js"></script>
<script type="text/html" id="screen-list-tmpl">
	<!--[for(i = 0;i < list.length;i++){]-->
					<li class="search_li" style="disabled:true;">
						<span><!--[=list[i].screenType]-->：</span>
						<ul>
							<li data-type="0" class="current cur_li">不限</li>
							<!--[for(j = 0;j < list[i].screen.length;j++){]-->
								<li data-type="<!--[=list[i].screen[j].pkConfigcontent]-->"><!--[=list[i].screen[j].cocoContent]--></li>
							<!--[}]-->
							<li class="ok">（单选）</li>
						</ul>
					</li>
	<!--[}]-->
</script>
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
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-search.js"></script>
</body>
</html>