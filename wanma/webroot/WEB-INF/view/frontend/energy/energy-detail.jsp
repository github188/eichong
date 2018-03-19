<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>商品详情</title>
	<%--common css--%>
	<jsp:include page="../common/header-css.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/xiamain_s.css">
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

	<div class="content">
		<div class="content-head">
            <a>首页 > </a> 
            <a>新能源商城 > </a>
            <a>商品详情</a>
        </div>
		<!--查找电桩内容-->
		<div class="search-electric selectNum">
			<div class="electri-inf" >
				<div class="left-con">
					<img src="<%=basePath%>/static/images/img/carbg_icon.png" id='data_pic'></div>
				<div class="left-con mt30 ml30">
					<div id="detail-data"><h3>【万马】上城区充充电点</h3>

					<p class="same sort mt20">
						<span>属性分类：</span>
						<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>
						个
					</p>

					<p class="same car ">
						<span>价格：</span>
						<span class="ml10 fc-orange">
							&yen;
							<span class="ml5 mr5 f18">800</span>元
						</span>
					</p></div>

					<div class="same list clearfix">
						<span class="fl f16">数量：</span>
						<div class="fl count-wrap ml20">
						<input type="text"   id="energy-spinner"/>
						</div>
						<span class="fl" style="text-indent:0;" id="stock-amount">(库存2556件)</span>
					</div>
					<div class="btn-wrap mt20 clearfix">
						<button id="shopCar">加入购物车</button>
						<button class="ml20 current" id="promptlyBuy">立即购买</button>
					</div>
					<div class="clearfix mt40 cs-wrap">
						<!-- <div class="assess fl mr10">评价</div> -->
						<div class="collect fl" id="collect_submit">收藏</div>
						<div class="share fl">
							<span class="share-txt fl">分享到</span>
							<span class="share-icon fl">
							 <span class="icon weibo" title="分享到新浪微博" data-code="sinaminiblog"></span>
							<span class="icon qsoze" title="分享到QQ空间" data-code="qzone"></span>
							<span class="icon friends" title="分享到朋友网" data-code="qqxiaoyou"></span>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="order-details clearfix">
				<div class="odl-l fl" >
					<h4 class="tit">商品信息</h4>
					<div class="parameters electric-num" id="html-text">
						<!-- <li>
							规格尺寸:
							<span class="ml10">高1390mm 厚215mm</span>
						</li>
						<li>
							额定交流工作电压:
							<span class="ml10">220v</span>
						</li>
						<li>
							额定交流工作电流:
							<span class="ml10">32A</span>
						</li>
						<li>
							充电模式:
							<span class="ml10">自动、定时、定费、定量、预约</span>
						</li>
						<li>
							通信方式:
							<span class="ml10">以太网、CAN、RS485</span>
						</li>
						<li>
							用户验证方式:
							<span class="ml10">无卡、IC卡、二维码扫描</span>
						</li> -->
					</div>
					<h4 class="mt40 mb30 tit">人工服务</h4>
					<div class="services">客服电话请拨打：400-800-994</div>
					<h4 class="mt40 mb30 tit">安装预约须知</h4>
					<div class="clearfix">
						<span class="fc-gray mr10 fl">预约须知</span>
						<div class="fr text-wrap fc-gray">
							<p>
								预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知
							</p>
							<!-- <p class="mt10">
								<input class="ck" type="checkbox">我同意《预约须知》</p> -->
						</div>
					</div>
				</div>

				<div class="odl-r fr mt10">
					<h4 class="mb30 tit">
						用户评价<span id="comment-count">（0）</span>
					</h4>
					<ul class="assess-wrap" id="comment-list">
						<li>
							<div class="awr-t">
								<div class="clearfix">
									<img class="fl" src="images/img/person_icon.png">
									<div class="fl user-info f18 ml10">
										<p>小豆芽</p>
										<p>2014-12-08</p>
										<div class="star-wrap clearfix mt5">
											<p class="fl">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt=""></p>
											<span class="fl f18 ml10">5.0</span>
										</div>
									</div>
								</div>
							</div>
							<div class="awr-b mt10">
								<p>充电桩非常好用，使用方便简单 划算可以推荐身边的朋友，离家 又不远，价廉物美值得再去...</p>
							</div>
						</li>
						<li>
							<div class="awr-t">
								<div class="clearfix">
									<img class="fl" src="images/img/person_icon.png">
									<div class="fl user-info f18 ml10">
										<p>小豆芽</p>
										<p>2014-12-08</p>
										<div class="star-wrap clearfix mt5">
											<p class="fl">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt=""></p>
											<span class="fl f18 ml10">5.0</span>
										</div>
									</div>
								</div>
							</div>
							<div class="awr-b mt10">
								<p>充电桩非常好用，使用方便简单 划算可以推荐身边的朋友，离家 又不远，价廉物美值得再去...</p>
							</div>
						</li>
						<li>
							<div class="awr-t">
								<div class="clearfix">
									<img class="fl" src="images/img/person_icon.png">
									<div class="fl user-info f18 ml10">
										<p>小豆芽</p>
										<p>2014-12-08</p>
										<div class="star-wrap clearfix mt5">
											<p class="fl">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt=""></p>
											<span class="fl f18 ml10">5.0</span>
										</div>
									</div>
								</div>
							</div>
							<div class="awr-b mt10">
								<p>充电桩非常好用，使用方便简单 划算可以推荐身边的朋友，离家 又不远，价廉物美值得再去...</p>
							</div>
						</li>
						<li>
							<div class="awr-t">
								<div class="clearfix">
									<img class="fl" src="images/img/person_icon.png">
									<div class="fl user-info f18 ml10">
										<p>小豆芽</p>
										<p>2014-12-08</p>
										<div class="star-wrap clearfix mt5">
											<p class="fl">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt=""></p>
											<span class="fl f18 ml10">5.0</span>
										</div>
									</div>
								</div>
							</div>
							<div class="awr-b mt10">
								<p>充电桩非常好用，使用方便简单 划算可以推荐身边的朋友，离家 又不远，价廉物美值得再去...</p>
							</div>
						</li>
						<li>
							<div class="awr-t">
								<div class="clearfix">
									<img class="fl" src="images/img/person_icon.png">
									<div class="fl user-info f18 ml10">
										<p>小豆芽</p>
										<p>2014-12-08</p>
										<div class="star-wrap clearfix mt5">
											<p class="fl">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt="">
												<img class="img-star" src="images/icon/star_gray.png" alt=""></p>
											<span class="fl f18 ml10">5.0</span>
										</div>
									</div>
								</div>
							</div>
							<div class="awr-b mt10">
								<p>充电桩非常好用，使用方便简单 划算可以推荐身边的朋友，离家 又不远，价廉物美值得再去...</p>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../common/footer.jsp" />
<%--评论模板--%>
<script type="text/html" id="comment-list-tmpl">
<!--[for(i=0;i<list.length;i++){]-->
<li>
	<div class="awr-t">
		<div class="clearfix">
			<img class="fl" src="images/img/person_icon.png">
			<div class="fl user-info f18 ml10">
				<p><!--[=list[i].prcoUsername]--></p>
				<p><!--[=list[i].prcoUsername]--></p>
				<div class="star-wrap clearfix mt5">
					<p class="fl">
						<img class="img-star" src="<%=request.getContextPath()%>/static/images/icon/star_gray.png" alt="">
						<img class="img-star" src="<%=request.getContextPath()%>/static/images/icon/star_gray.png" alt="">
						<img class="img-star" src="<%=request.getContextPath()%>/static/images/icon/star_gray.png" alt="">
						<img class="img-star" src="<%=request.getContextPath()%>/static/images/icon/star_gray.png" alt="">
						<img class="img-star" src="<%=request.getContextPath()%>/static/images/icon/star_gray.png" alt=""></p>
					<span class="fl f18 ml10"><!--[=list[i].prcoCommentstart]--></span>
				</div>
			</div>
		</div>
	</div>
	<div class="awr-b mt10">
		<p><!--[=list[i].prcoContent]--></p>
	</div>
</li>
<!--[}]-->
</script>
 <script type="text/html" id="detail-data-tmpl">
		<h3 id="data_title"><!--[=prodProductname || '【万马】']--></h3>
		<p class="same sort mt20">
			<span>属性分类：</span>
			<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>个
		</p>
		<p class="same car ">
			<span>价格：</span>
			<span class="ml10 fc-orange">&yen;<span class="ml5 mr5 f18">
            <!--[if(prodDiscountprice!=null){]-->
            <!--[=prodDiscountprice]-->
            <!--[}else{]-->
            <!--[=prodMarketprice]-->
           <!--[}]-->
        
        </span>元</span>
		</p>
</script> 

<!-- <div class="left-con mt30 ml30">
		<h3 id="data_title">【万马】[=prodProductname || '']</h3>
		<p class="same sort mt20">
			<span>属性分类：</span>
			<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>个
		</p>
		<p class="same car ">
			<span>价格：</span>
			<span class="ml10 fc-orange">&yen;<span class="ml5 mr5 f18">800</span>元</span>
		</p>
		<div class="same list clearfix">
			<span class="fl f16">数量：</span>
			<input type="text" class="spinnerExample"/>
			<span class="fl" style="text-indent:0;">(库存2556件)</span>
		</div>
		<div class="btn-wrap mt20 clearfix buyType">
			<button id="shopCar">加入购物车</button>
			<button class="ml20 current" id="promptlyBuy">立即购买</button>
		</div>
		<div class="clearfix mt40 cs-wrap">
			<div class="assess fl mr10">评价</div>
			[if(isCollect=='0'){]
			<div class="collect fl">收藏</div>
			[}else{]
			<div class="collect fl active">收藏</div>
			[}]
			<div class="share fl">
				<span class="share-txt fl">分享到</span>
				<span class="share-icon fl">
					<span class="icon weibo"></span>
					<span class="icon qsoze"></span>
					<span class="icon friends"></span>
				</span>
			</div>+
		</div>
	</div> -->
 <script src="<%=request.getContextPath()%>/static/js/energy/detail.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/static/lib/spinner/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/lib/spinner/js/jquery.spinner.js"></script>
<link rel="stylesheet" href="<%=basePath%>/static/lib/spinner/css/jquery.spinner.css" />
<script type="text/javascript">
//01查询库存 id="stock-amount">(库存2556件)
 var bar=location.href.getQueryValue('sm');
 $("#stock-amount").html("(库存"+bar+"件)");
//02初始化spinner
$("#energy-spinner").spinner({
    max:bar,
    min:1
});
</script>
</body>
</html>