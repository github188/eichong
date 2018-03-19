<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>支付成功</title>
	<jsp:include page="../common/header-css.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/AppointmentInstall_s.css">
	<style>
		.address-wrap{
			background-color: #eee;
			padding: 40px;
			position: relative;
		}
		.address-wrap .tx{
			font-size: 20px;
			color: #808080;	
		}
		.address-wrap .arrow{
			width: 16px;
			height: 37px;
			background:  url(<%=request.getContextPath()%>/static/images/icon/arrow09.png) 0 0 no-repeat;
			position: absolute;
			right: 20px;
			top: 50%;
			margin-top: -18px;
		}

	</style>
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<div class="content">
		<div class="content-head"  style="margin-bottom:0;">
            <a>首页 > </a> 
            <a>新能源商城 > </a>
            <a>新品区列表 > </a>
            <a>商品详情 > </a>
            <a>支付成功</a>
        </div>
		<div class="tm success">
			<p class="pic"></p>
			<p class="tx">恭喜您，交易成功</p>
			<button class="btn" id="subscribeInstall">去预约安装</button>
		</div>
		<!-- 模块 -->
		<h3 class="mt40 mb30 title">订单详情</h3>
		<!-- 商品列表模块 -->
		<div id="data-list">
    	</div>
		<!-- 模块end -->
		
		<!-- order-num -->
		<div class="order-num">
			<p class="mt5">万马订单编号：  <span id="ordeCode" class="ml10">8186999250</span></p>
			<p class="mt5">支付订单号：   <span class="ml10">20140022100100123</span></p>
			<p class="mt5">成交时间：    <span id="ordeUpdatedate" class="ml10">2014</span></p>
		</div>
		<!-- 流程模块 -->
		<div class="process-moudel" id="track-data">
			<div class="process clearfix tm">
				<div id="1" class="ball-empty middle">
					<div class="static top">
						<p>购买</p>
						<p class="date"></p>
						<p class="time"></p>
					</div>
				</div>
				<span class="middle arrow"> >>>>>>>></span>
				<div id="2" class="ball-empty middle">
					<div class="static bottom">
						<p class="time"></p>
						<p class="date"></p>
						<p>付款</p>
					</div>
				</div>
				<span class="middle arrow"> >>>>>>>></span>
				<div id="3" class="ball-empty middle">
					<div class="static top">
						<p>预约安装</p>
						<p class="date"></p>
						<p class="time"></p>
					</div>
				</div>
				<span class="middle arrow"> >>>>>>>></span>
				<div id="4" class="ball-empty middle">
					<div class="static bottom">
						<p class="time"></p>
						<p class="date"></p>
						<p>安装中</p>
					</div>
				</div>
				<span class="middle arrow"> >>>>>>>></span>
				<div id="5" class="ball-empty middle">
					<div class="static top left">
						<p>收货</p>
						<p class="date"></p>
						<p class="time"></p>
					</div>
				</div>
				<span class="middle arrow"> >>>>>>>></span>
				<div id="6" class="ball-empty middle">
					<div class="static bottom left">
						<p class="time"></p>
						<p class="date"></p>
						<p>完成</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 合计 -->
		<!-- <div class="all mt10">
			<div class="fr all-r">			
				<button class="all-btn">删除订单</button>
				<button class="all-btn ml30">追加评论</button>
			</div>
		</div> -->
		<div class="clear"></div>
		<jsp:include page="../common/footer.jsp" />
	</div>
	
	<!-- 商品列表 -->
<script type="text/html" id="data-list-tmpl">
	<!--[for(i=0;i<list.length;i++){]-->
	<div class="god-moudel">
			<div class="tit"><!--[=list[i].prodBrandName]--></div>
			<!--[for(j=0;j<list[i].productList.length;j++){]-->
			<div class="con clearfix">
				<img class="pic fl" src="<!--[=$absImg(list[i].productList[j].prodProductimage)]-->" alt="">
				<div class="fl">
					<div class="mt10">
						<h3>【万马】<!--[=list[i].productList[j].ordeProductname]--></h3>

						<p class="tx sort mt20">
							<span>属性分类：</span>
							<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>
							<!--[=list[i].productList[j].ordeQuantity]-->个
						</p>

						<p class="same car mt10">
							<span class="ml10 fc-orange f18">
								&yen;
								<span class="pay ml5"><!--[=list[i].productList[j].ordePrice]--></span>
								元
							</span>
						</p>
					</div>
				</div>
			</div>
			<!--[}]-->
		</div>
		<div class="god-moudel-foot tr f18">
			（共计
			<span class="fc-orange"><!--[=list[i].ordeQuantity]--></span>
			件商品）合计：
			<span class="fc-orange">
				&yen;
				<span class="pay ml5 mr5"><!--[=list[i].ordeTotalamount]--></span>
				元
			</span>
		</div>
		<!--[}]-->
</script>

<script src="<%=request.getContextPath()%>/static/js/energy/bookFix.js" type="text/javascript"></script>
</body>
</html>