<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>新能源商城</title>

    <%--common css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <%--custom css--%>
    <link href="<%=request.getContextPath()%>/static/css/electric_info.css" rel="stylesheet"/>
     <link href="<%=request.getContextPath()%>/static/css/common.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/static/lib/sliderbox/css/jquery.slideBox.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!-------导航-------->
<div class="content-head">
    <a>首页 > </a> <a>新能源商城</a>
</div>
<div id="banner" class="slideBox">
    <ul class="items">
        <li><img src="<%=request.getContextPath()%>/static/images/img/toppic.jpg"></li>
        <li><img src="<%=request.getContextPath()%>/static/images/img/toppic2.jpg"></li>
        <li><img src="<%=request.getContextPath()%>/static/images/img/toppic3.jpg"></li>
    </ul>
</div>

<!--新能源详情内容-->
<div class="content">
    <div class="liList">
        <ul>
            <li>
                <a id="new-product">
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/new_goods.png"/></p>
                    <p class="ele_tit">新品区</p>
                </a>
            </li>
            <li>
                <a style="cursor: auto;">
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/recharge.png"/></p>
                    <p class="ele_tit">充值</p>
                </a>
            </li>
            <li>
                <a style="cursor: auto;">
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/buying.png"/></p>
                    <p class="ele_tit">团购</p>
                </a>
            </li>
            <li>
                <a style="cursor: auto;">
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/voucher.png" style="margin-top: 12px;" /></p>
                    <p class="ele_tit">代金券</p>
                </a>
            </li>
            <li>
                <a id="discount-product" >
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/sale.png"/></p>
                    <p class="ele_tit">打折区</p>
                </a>
            </li>
            <li>
                <a style="cursor: auto;">
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/fast_buy.png"/></p>
                    <p class="ele_tit">限时抢购</p>
                </a>
            </li>
            <li>
                <a style="cursor: auto;">
                    <p class="ele_pic"><img src="<%=request.getContextPath()%>/static/images/img/electric/account.png"/></p>
                    <p class="ele_tit">积分兑换</p>
                </a>
            </li>
        </ul>
    </div>
    <div class="all_cont">
        <div class="top_list">
            <ul class="menu_list">
                <li class="on hot"><a>热门推荐</a></li>
                <li class="car"><a>电动汽车</a></li>
                <li class="bike"><a>电动自行车</a></li>
                <li class="equipmentPart"><a>充电设备&配件</a></li>
                <li class="carPart"><a>充电汽车&配件</a></li>
            </ul>
            <div class="condition">
                <a><img src="<%=request.getContextPath()%>/static/images/img/electric/triangle.png"/></span> 智能排序：</a>
                <a class="cur conditionDetail noLimit">不限</a>
                <a class="conditionDetail priceSort">价格</a>
                <a class="rel conditionDetail"><span>所有 </span>
                   <span><img src="<%=request.getContextPath()%>/static/images/img/electric/down.png"/></span>
                       <ul class="jiaoliu abs carTypeHide" id="jiaoliu">
                       <li class="conditionDetailList" value="1">所有</li>
                       <li class="conditionDetailList" value="1">交流</li>
                        <li class="br conditionDetailList" value="1">直流</li>
                    </ul>
                </a>
                <a class="conditionDetail soldQuantity">销售量优先</a>
            </div>
        </div>
        <ul class="ul_cont" id="data-list">
            <%-- <li>
                <a>
                    <p class="el_img"><img src="<%=request.getContextPath()%>/static/images/img/car_icon.png"></p>
                    <div class="el_info">
                        <p class="elTit">【万马】上城区充充电点</p>
                        <p class="elPrice">
                            <span class="fl e_price">￥800元</span>
                            <span class="fr e_tore">库存：999</span>
                        </p>
                    </div>
                </a>
            </li>
            <li>
                <a>
                    <p class="el_img"><img src="<%=request.getContextPath()%>/static/images/img/car_icon.png"></p>
                    <div class="el_info">
                        <p class="elTit">【万马】上城区充充电点</p>
                        <p class="elPrice">
                            <span class="fl e_price">￥800元</span>
                            <span class="fr e_tore">库存：999</span>
                        </p>
                    </div>
                </a>
            </li>
            <li>
                <a>
                    <p class="el_img"><img src="<%=request.getContextPath()%>/static/images/img/car_icon.png"></p>
                    <div class="el_info">
                        <p class="elTit">【万马】上城区充充电点</p>
                        <p class="elPrice">
                            <span class="fl e_price">￥800元</span>
                            <span class="fr e_tore">库存：999</span>
                        </p>
                    </div>
                </a>
            </li> --%>
        </ul>
    </div>
    <jsp:include page="../common/page.jsp" />
</div>

<jsp:include page="../common/footer.jsp" />

<script src="<%=request.getContextPath()%>/static/lib/sliderbox/js/jquery.slideBox.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        var width = parseInt($(window).width());
        var height = width / 3.2;
        $('#banner').slideBox({'width': width, 'height': height,'hideClickBar':false,'clickBarRadius':10});
    });

</script>
<script src="<%=request.getContextPath()%>/static/js/energy/index.js" type="text/javascript"></script>
<script type="text/html" id="data-list-tmpl">
	<!--[for(i=0;i<list.length;i++){]-->
	<li data-id="<!--[=list[i].pkProduct]-->"  stock-amount="<!--[=list[i].prodStockquantity]-->">
		<a href="javascript:void (0);">
			<img src="<!--[=$absImg(list[i].prodProductimage)]-->" class="produect_imge"/>
		</a>
		<h4 class="cm_txt_one_cut">
			 <!--[=list[i].prodProductname]-->
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
			<span class="fr mr20">库存：<!--[=list[i].prodStockquantity]--> 件</span>
		</p>
	</li>
	<!--[}]-->
</script>
</body>
</html>