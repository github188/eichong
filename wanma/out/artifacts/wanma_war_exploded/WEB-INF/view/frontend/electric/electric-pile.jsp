<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>电桩详情</title>
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
           <a>充电点详情</a>
       </div>
	<!--查找电桩内容-->
	<div class="search-electric" id="detail-data">
	</div>
</div>
<div class="clear"></div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=pBYPU9rfQCTflKcU2jSF9iuy"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-pile.js"></script>
<script type="text/html" id="detail-data-tmpl">
		<div class="electri-inf">
			<div class="left-con">
				<img src="<!--[=$absImg(electricPileImage)]-->" id="data_pic"></div>
			<div class="left-con mt30 ml30">
				<h3 id="data_title"><!--[=electricPileName]--></h3>

				<p class="same num">
					<span>车位状态：</span>
					<span class="ml10">(无)</span>
					
				</p>

				<p class="same static">
					<span>电桩状态：</span>
					<span class="ml10"><!--[=$mateArray(electricPileState, 9)]--></span>
				</p>

				<p class="same address" id="data_address">
					<span><!--[=electricPileAdress]--></span>
				</p>

				<p class="same tel" id="data_tel">
					<span>人工服务：</span>
					<span class="ml10"><!--[=electricPileTell]--></span>
				</p>
				<div class="clearfix select-wrap">
					<div class=" select-l fl ml10">
					<!--[for(i = 0;i < pileHeadList.length;i++){]-->
						<p class="rest mt10">
							<input type="radio" name="api" id="radio_<!--[=i]-->" value="<!--[=pileHeadList[i].pileHeadId]-->"
								 <!--[=$attrDecide(pileHeadList[i].pileHeadState, '0', 'checked', 'disabled="disabled"')]-->>
							<label for="radio_<!--[=i]-->"><span class="ml10"><!--[=pileHeadList[i].pileHeadName]--></span>
							<span class="<!--[=$attrDecide(pileHeadList[i].pileHeadState, '0', 'fc-orange')]--> ml20">
								<!--[=$mateArray(pileHeadList[i].pileHeadState, 1)]--></span></label>
						</p>
					<!--[}]-->
					</div>
					<div class=" select-r fr">
						<a class=" tm" href="javascript:void(0);" id="order_detail">立即预约</a>
					</div>
				</div>
				<div class="clearfix mt40 cs-wrap">
					<div class="collect fl" id="collect_submit" data-id="<!--[=powerStationId]-->">收藏</div>
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
		<div class="electric-main">
			<div class="left-main">
				<h4 class="tit">充电点位置</h4>
				<div class="map-wrap" id="pileMap">
				</div>

			</div>
		</div>

		<!-----第二部分内容------>
		<div class="electric-num">
			<h4 class="mt40 mb30 tit">参数属性</h4>
			<ul class="parameters">
				<li>桩体编号: <span class="ml10"><!--[=electricPileNo]--></span></li>
				<li>充电方式: <span class="ml10"><!--[=$mateArray(electricPileChargingMode, 10)]--></span></li>
				<li>充电接口: <span class="ml10"><!--[=$mateArray(electricPowerInterface, 7)]--></span></li>
				<li>额定功率: <span class="ml10"><!--[=$mateArray(electricPowerSize, 12)]--></span></li>
				<li>最大电流: <span class="ml10"><!--[=electricMaxElectricity]-->A</span></li>
			</ul>
		</div>
		<div class="clearfix">
		</div>
			<h4 class="mb30 tit">我要点评
				<div class="star-wrap">
					<span class="fl f18">请评分：</span>
					<p class="fl">
						<img class="img-star img_grade" src="<%=basePath%>/static/images/icon/star_gray.png">
						<img class="img-star img_grade" src="<%=basePath%>/static/images/icon/star_gray.png">
						<img class="img-star img_grade" src="<%=basePath%>/static/images/icon/star_gray.png">
						<img class="img-star img_grade" src="<%=basePath%>/static/images/icon/star_gray.png">
						<img class="img-star img_grade" src="<%=basePath%>/static/images/icon/star_gray.png">
					</p>
				</div>
			</h4>
			<textarea class="reviews" id="prcoContent" cols="30" rows="10" placeholder="说点什么吧~~"></textarea>
			<div class="clearfix">
				<ul class="fl fileImg-wrap mt10">
					<li>
						<input class="file-img size" type="file">
					</li>
					<li>
						<input class="file-img size" type="file">
					</li>
					<li>
						<input class="file-img size" type="file">
					</li>
					<li>
						<input class="file-img size" type="file">
					</li>
					<li>
						<input class="file-img size" type="file">
					</li>
				</ul>
				<a class="submit-btn tm fr mt10" href="javascript:void(0);" id="evaluate_submit" data-name="${webUser.userRealName}">提交</a>
			</div>
		<h4 class="mb30 tit">用户评价<span>（<!--[=electricPileCommentSum]-->）</span>
			<div class="star-wrap">
				<a class="fl f18 more" id="evaluate_detail">点击更多 >></a>
			</div>
		</h4>
		<ul class="ping-con">
			<!--[for(i = 0;i < commentList.length;i++){]-->
               <li>
                   <img src="<!--[=$absImg(userImage)]-->">

                   <div class="ping-main">
                       <p><!--[=commentList[i].prcoUsername]--><span><!--[=$formatDate(commentList[i].prcoCreatetime, 6)]--></span>
                       	<div class="star-wrap">
							<p class="fl">
							<!--[for(j = 0;j < 5;j++){]-->
								<!--[if(j < commentList[i].prcoCommentstart){]-->
									<img class="img-star" src="<%=basePath%>/static/images/img/star.png">
								<!--[}else{]-->
									<img class="img-star" src="<%=basePath%>/static/images/icon/star_gray.png">
								<!--[}]-->
							<!--[}]-->
							</p>
							<span class="fl f18 ml10"><!--[=commentList[i].prcoCommentstart]--></span>
						</div>
                       </p>

                       <p><!--[=commentList[i].prcoContent]--></p>
                   </div>
               </li>
			<!--[}]-->
        </ul>
</script>
</body>
</html>