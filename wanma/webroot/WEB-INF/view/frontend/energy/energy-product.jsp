<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title class="title"></title>
	<%--common css--%>
	<jsp:include page="../common/header-css.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/xiamain_s.css">

</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>
	<div class="content">
		<div class="content-head" style="margin-bottom:0;">
            <a>首页 > </a>
            <a>新能源商城 > </a>
            <a class="title">列表</a>
        </div>
		<!--查找电桩内容-->
		<div class="search-electric newGoods">
			<!-----第二部分内容------>
			<div class="electric-num toggle none">
				<ul class="electric-con" id="data-list">
					<li>
						<a href="electric_info.html">
							<img src="images/img/car_icon.png"/>
						</a>
						<h4 class="cm_txt_one_cut">
							【万马】上城区充充电点
						</h4>
						<p class="mt5 tips-wrap">
							<span class="tips">一体式</span>
							<span class="tips">额定功率30-45kw</span>
						</p>
						<p class="mt5 fc-orange price">
							<span class="fl fc-orange f16">&yen;<span class="num ml10">800元</span></span>
							<span class="fr mr20">库存：999</span>
						</p>
					</li>
					<li>
						<a href="electric_info.html">
							<img src="images/img/car_icon.png"/>
						</a>
						<h4 class="cm_txt_one_cut">
							【万马】上城区充充电点
						</h4>
						<p class="mt5 tips-wrap">
							<span class="tips">一体式</span>
							<span class="tips">额定功率30-45kw</span>
						</p>
						<p class="mt5 fc-orange price">
							<span class="fl fc-orange f16">&yen;<span class="num ml10">800元</span></span>
							<span class="fr mr20">库存：999</span>
						</p>
					</li>
					<li>
						<a href="electric_info.html">
							<img src="images/img/car_icon.png"/>
						</a>
						<h4 class="cm_txt_one_cut">
							【万马】上城区充充电点
						</h4>
						<p class="mt5 tips-wrap">
							<span class="tips">一体式</span>
							<span class="tips">额定功率30-45kw</span>
						</p>
						<p class="mt5 fc-orange price">
							<span class="fl fc-orange f16">&yen;<span class="num ml10">800元</span></span>
							<span class="fr mr20">库存：999</span>
						</p>
					</li>
					<li>
						<a href="electric_info.html">
							<img src="images/img/car_icon.png"/>
						</a>
						<h4 class="cm_txt_one_cut">
							【万马】上城区充充电点
						</h4>
						<p class="mt5 tips-wrap">
							<span class="tips">一体式</span>
							<span class="tips">额定功率30-45kw</span>
						</p>
						<p class="mt5 fc-orange price">
							<span class="fl fc-orange f16">&yen;<span class="num ml10">800元</span></span>
							<span class="fr mr20">库存：999</span>
						</p>
					</li>
					<li>
						<a href="electric_info.html">
							<img src="images/img/car_icon.png"/>
						</a>
						<h4 class="cm_txt_one_cut">
							【万马】上城区充充电点
						</h4>
						<p class="mt5 tips-wrap">
							<span class="tips">一体式</span>
							<span class="tips">额定功率30-45kw</span>
						</p>
						<p class="mt5 fc-orange price">
							<span class="fl fc-orange f16">&yen;<span class="num ml10">800元</span></span>
							<span class="fr mr20">库存：999</span>
						</p>
					</li>
					<li>
						<a href="electric_info.html">
							<img src="images/img/car_icon.png"/>
						</a>
						<h4 class="cm_txt_one_cut">
							【万马】上城区充充电点
						</h4>
						<p class="mt5 tips-wrap">
							<span class="tips">一体式</span>
							<span class="tips">额定功率30-45kw</span>
						</p>
						<p class="mt5 fc-orange price">
							<span class="fl fc-orange f16">&yen;<span class="num ml10">800元</span></span>
							<span class="fr mr20">库存：999</span>
						</p>
					</li>
				</ul>
			</div>
			<jsp:include page="../common/page.jsp"/>
		</div>
	</div>
	<div class="clear"></div>
<jsp:include page="../common/footer.jsp" />

<script type="text/html" id="data-list-tmpl">
	<!--[for(i=0;i<list.length;i++){]-->
	<li data-id="<!--[=list[i].pkProduct]-->" stock-amount="<!--[=list[i].prodStockquantity]-->">
		<a href="javascript:void (0);">
			<img src="<!--[=$absImg(list[i].prodProductimage)]-->"/>
		</a>
		<h4 class="cm_txt_one_cut">
			【万马】<!--[=list[i].prodProductname]-->
		</h4>
		<p class="mt5 tips-wrap">
			<span class="tips">一体式</span>
			<span class="tips">额定功率30-45kw</span>
		</p>
		<p class="mt5 fc-orange price">
			        <!--[if(list[i].prodDiscountprice!=null){]-->
       <span class="fl fc-orange f16">&yen;<span class="num ml10"><!--[=list[i].prodDiscountprice]-->元</span></span>
        <!--[}else{]-->
       <span class="fl fc-orange f16">&yen;<span class="num ml10"><!--[=list[i].prodMarketprice]-->元</span></span>
        <!--[}]-->
			<span class="fr mr20">库存：<!--[=list[i].prodStockquantity]--></span>
		</p>
	</li>
	<!--[}]-->
</script>
<script src="<%=request.getContextPath()%>/static/js/energy/product.js" type="text/javascript"></script>
</body>
</html>