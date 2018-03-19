<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
	<meta charset="utf-8">
	<title>购物车</title>
	<link href="<%=request.getContextPath()%>/static/css/base.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/static/css/common.css" rel="stylesheet"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/goodsCar.css">
	<script src="<%=request.getContextPath()%>/static/js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/static/js/common.js" type="text/javascript"></script>
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
	<jsp:include page="../common/header.jsp"/>

	<div class="content">
		<div class="content-head" style="margin-bottom:0;">
            <a>首页 > </a> 
            <a>新能源商城 > </a>
            <a>新品区列表 > </a>
            <a>商品详情 > </a>
            <a>积分兑换 > </a>
            <a>购物车</a>
        </div>
		<div class="address-wrap mt20" id="detail-data">
			<!-- <p class="tx">
				收货人：
				<span>李宏伟</span>
				<span>13599762382</span>
			</p>
			<p class="tx mt5">
				收货地址：
				<span>上海市虹口区广纪路766号</span>
			</p>
			<p class="fc-orange mt5">（结算后，请注意填写安装地址）</p>
			<a class="arrow" href=""></a> -->
		</div>
		
		<!-- 商品列表模块 -->
		<div id="data-list">
    	</div>
		
		<!-- 模块 -->
		<%-- <div class="god-moudel">
			<div class="tit">万马品牌</div>
			<div class="con clearfix">
				<input class="check fl" type="checkbox"/>
				<img class="pic fl" src="<%=request.getContextPath()%>/static/images/img/car.png" alt="">
				<div class="fl">
					<div class="mt10">
						<h3>【万马】上城区充充电点</h3>

						<p class="tx sort mt20">
							<span>属性分类：</span>
							<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>
							个
						</p>

						<p class="same car mt10">
							<span class="ml10 fc-orange f18">
								&yen;
								<span class="pay ml5">800</span>
								元
							</span>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="god-moudel-foot tr f18">
			（共计
			<span class="fc-orange">1</span>
			件商品）合计：
			<span class="fc-orange">
				&yen;
				<span class="pay ml5 mr5">800</span>
				元
			</span>
		</div>
		<!-- 模块end -->
		<!-- 模块 -->
		<div class="god-moudel">
			<div class="tit">万马品牌</div>
			<div class="con clearfix">
				<input class="check fl" type="checkbox"/>
				<img class="pic fl" src="<%=request.getContextPath()%>/static/images/img/car.png" alt="">
				<div class="fl">
					<div class="mt10">
						<h3>【万马】上城区充充电点</h3>

						<p class="tx sort mt20">
							<span>属性分类：</span>
							<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>
							个
						</p>

						<p class="same car mt10">
							<span class="ml10 fc-orange f18">
								&yen;
								<span class="pay ml5">800</span>
								元
							</span>
						</p>
					</div>
				</div>
			</div>
		</div> --%>
		<!-- <div class="god-moudel-foot tr f18">
			（共计
			<span class="fc-orange">1</span>
			件商品）合计：
			<span class="fc-orange">
				&yen;
				<span class="pay ml5 mr5">800</span>
				元
			</span>
		</div> -->
		<!-- 模块end -->
		<!-- 合计 -->
		<div class="all mt10">
			<!-- <div class="fl">
				<input class="ml30" type="checkbox" id="all">
				<label for="all" class="fs ml10">全选</label>
			</div> -->
			<div class="fr all-r">
				合计：
				<span class="fc-orange">
					&yen;
					<span class="pay ml5 mr5 totalMoney"></span>
					元
				</span>
				（不含运费）
				<button class="all-btn">结算</button>

			</div>
		</div>
		<div class="clear"></div>
		
		<jsp:include page="../common/footer.jsp" />

<!-- 默认收货地址 -->
<script type="text/html" id="detail-data-tmpl">
	<p class="tx">
				收货人：
				<span><!--[=pradUsername]--></span>
				<span><!--[=pradPhonenumber]--></span>
			</p>
			<p class="tx mt5">
				收货地址：
				<span><!--[=pradAddress]--></span>
			</p>
			<p class="fc-orange mt5">（结算后，请注意填写安装地址）</p>
			<a class="arrow" href="address.do?a=<!--[=orderId]-->&ty=confirmOrder"></a>
</script>


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


<script src="<%=request.getContextPath()%>/static/js/energy/confirmOrder.js" type="text/javascript"></script>
</body>
</html>