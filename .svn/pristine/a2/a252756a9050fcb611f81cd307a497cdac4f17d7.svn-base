<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>新品区</title>
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
            <a>新品区列表</a>
        </div>
		<!--查找电桩内容-->
		<div class="search-electric newGoods">
			<!-----第二部分内容------>
			<div class="electric-num toggle none">
				<ul class="electric-con">
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
			<!-- -地图模式内容- -->
			<div class="map-con toggle none" >
				<img src="images/img/searchmap_icon.png">
			</div>
			<div class="page">
				<span class="sp next">下一页</span>
				<ul>
					<li class="current">1</li>
					<li>2</li>
					<li>3</li>
					<li>3</li>
					<li>...</li>
					<li>20</li>
				</ul>
				<span class="sp pre">上一页</span>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../common/footer.jsp" />

	<script type="text/javascript">
		$(function () {
			$(".search-con .list-con li").click(function () {
				if(!$(this).hasClass('ok')){
					if($(this).hasClass('lot')){
						$(this).addClass("current");
						$('.delete').removeClass("current");
					} else{
						$(this).addClass("current").siblings("li").removeClass("current");
					}
				}
			})
			//分页
			$(".page ul li").click(function(){
				$(this).addClass("current").siblings("li").removeClass("current")
			})
			//内容切换
			$(".search-title li").click(function(){
//                alert("1");
				$(this).addClass("current").siblings("li").removeClass("current");
				var liindex = $(this).index();
//                alert(liindex);
				$(".toggle").eq(liindex).show().siblings(".none").hide();
			})
		})
	</script>
</body>
</html>