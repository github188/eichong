<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>购物车</title>

    <%--common css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/goodsCar.css">
</head>
<body>
<!--//header -->
<jsp:include page="../common/header.jsp"/>
<div class="content">
    <div class="content-head" style="margin-bottom:0;">
        <a>首页 ></a>
        <a>新能源商城 ></a>
        <a>新品区列表 ></a>
        <a>商品详情 ></a>
        <a>积分兑换 ></a>
        <a>购物车</a>
    </div>
    <form action="energy/insertBespoke.do">
    	   
    </form>
    <!-- 模块 -->
    	<div id="data-list">
    	</div>
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
        <div class="fl">
            <input class="ml30" type="checkbox" id="all">
            <label for="all" class="fs ml10">全选</label>
        </div>
        <div class="fr all-r">
            合计：
				<span class="fc-orange">
					&yen;
                    <span class="pay ml5 mr5">0</span>
					元
				</span>
            （不含运费）
            <button class="all-btn" id='all-btn'>结算</button>

        </div>
    </div>
    <div class="clear"></div>
    <jsp:include page="../common/footer.jsp"/>
</div>

<script type="text/html" id="data-list-tmpl">
	<!--[for(i=0;i<list.length;i++){]-->
		<div class="god-moudel god">
        	<div class="tit"><!--[=list[i].prodBrandName]--></div>
				<!--[for(j=0;j<list[i].productList.length;j++){]-->
					 <div class="god-moudel">
        				<div class="con clearfix">
							
            				<input name="unitCheckbox" id="unitCheckbox"  class="check fl" value="<!--[=list[i].productList[j].shcaProductid]-->" type="checkbox"/>
            					<img class="pic fl" src="<!--[=$absImg(list[i].productList[j].prodProductimage)]-->" alt="">

            				<div class="fl">
                				<div class="mt10">
                    			<h3>【万马】<!--[=list[i].productList[j].prodProductName]--></h3>

                    			<p class="tx sort mt20">
                       			<span>属性分类：</span>
                        		<span class="ml10 cm_txt_one_cut">国际/额定功率30-45KW/一体式</span>
                        		<span class="shcaCount"><!--[=list[i].productList[j].shcaCount]-->个</span>
                    			</p>

                    			<p class="same car mt10">
									<span class="ml10 fc-orange f18">
									&yen;
                                	<span class="pay ml5"><!--[=list[i].productList[j].unitPrice]--></span>
									元
								</span>
                    			</p>
                		</div>
            		</div>
        	</div>
			</div>
		<!--[}]-->
    </div>
	 <div class="god-moudel-foot tr f18">
        （共计
        <span class="fc-orange fc-totalCount">0</span>
        件商品）合计：
			<span class="fc-orange">
				&yen;
                <span class="pay ml5 mr5 fc-totalMoney">0</span>
				元
			</span>
    </div>
	<!--[}]-->
   
</script>

<script src="<%=request.getContextPath()%>/static/js/energy/shopCar.js" type="text/javascript"></script>
</body>
</html>