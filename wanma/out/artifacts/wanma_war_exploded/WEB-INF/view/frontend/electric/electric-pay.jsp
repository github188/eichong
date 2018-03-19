<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预约支付</title>
    <%--custom css--%>
    <link rel="stylesheet" href="<%=basePath%>/static/css/bespeak.css">
    <jsp:include page="../common/header-css.jsp"/>
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--//section-->
<div class="content">
	<div class="content-head">
           <a>首页 > </a> 
           <a>电桩查找 > </a>
           <a>电桩详情 > </a>
      	   <a>电桩预约 > </a>
           <a>预约支付</a>
       </div>
	<div class="content-body" id="detail-data">
	</div>

</div>
<div class="clear"></div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-pay.js"></script>
<script type="text/html" id="detail-data-tmpl">
		<div class="cont-left">
			<ul class="list">
				<li class="clearfix">
					<p class="ol-l">订单编号:</p>
					<p class="ol-r"><!--[=bespResePaymentCode]--></p>
				</li>
				<li class="clearfix">
					<p class="ol-l">预约内容:</p>
					<p class="ol-r">
						<!--[=elPiElectricPileName]--><!--[=ePHeElectricpileHeadName]-->
					</p>
				</li>
				<li class="clearfix">
					<p class="ol-l">电桩地址:</p>
					<p class="ol-r"><!--[=elPiElectricPileAddress]--></p>
				</li>
				<li class="clearfix">
					<p class="ol-l">预约买断时间:</p>
					<p class="ol-r" id="period"><!--[=bespBespoketimes]--></p>
				</li>
				<li class="clearfix">
					<p class="ol-l">预约冻结金额:</p>
					<p class="ol-r fc-gray"><!--[=bespFrozenamt]-->元</p>
				</li>
				<li class="clearfix">
					<p class="ol-l">账户余额:</p>
					<p class="ol-r fc-orange"><!--[=usInAccountBalance]-->元</p>
				</li>

			</ul>
			<div class="clearfix">
				<span class="fc-gray mr10 fl">预约须知</span>
				<div class="fr text-wrap fc-gray">
					<p>
					预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知预约须知
					</p>
					<p class="mt10">
					<label><input type="checkbox" class="ck" name="check" value="ok"/>我同意《预约须知》</label>
					</p>
				</div>
			</div>

			<div class="div-style mt40">
				<a class="bt yellow-bt" href="javascript:void(0);" id="succeed_detail">预约支付</a>
				<a class="bt grey-bt" href="javascript:void(0);" id="order_detail">取消</a>
			</div>

		</div>
		<div class="cont-right">
			<p class="title-top">相关充充电点</p>
			<ul>
			<!--[for(i=0;i<relatedList.length;i++){]-->
				<li>
					<a href="javascript:void(0);" id="plant_detail" data-id="<!--[=relatedList[i].relatedId]-->">
						<img src="<!--[=$absImg(relatedList[i].relatedImage)]-->" />
					</a>
					<label class="one"><!--[=relatedList[i].relatedName]--></label>
					<label class="two">距离<!--[=relatedList[i].distance]-->M</label>
					<span >
					<!--[for(j = 0;j < 5;j++){]-->
						<!--[if(j < relatedList[i].relatedStart){]-->
							<img class="img-star" src="<%=basePath%>/static/images/img/star.png">
						<!--[}else{]-->
							<img class="img-star" src="<%=basePath%>/static/images/icon/star_gray.png">
						<!--[}]-->
					<!--[}]-->
					</span >
				</li>
     		<!--[}]-->
			</ul>
		</div>
</script>

</body>
</html>