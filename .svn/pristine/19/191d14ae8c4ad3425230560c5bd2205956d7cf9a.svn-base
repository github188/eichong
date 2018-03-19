<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>充电订单详细</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/order/listChargeDetail.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="nav">
				<span class="icon" id="goBack"></span><span>充电订单详情</span>
			</div>
			<div class="orderDetail">
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">订单编号</span>
						<span class="lineRight" id="chorCode"></span>
					</div>
					<div class="line">
						<span class="lineLeft">桩体编码 </span>
						<span class="lineRight" id="chorPilenumber"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">电桩名称</span>
						<span class="lineRight" id="electricName"></span>
					</div>
					<div class="line">
						<span class="lineLeft">手机号</span>
						<span class="lineRight" id="userPhone"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">充电方式</span>
						<span class="lineRight" id="chargeType"></span>
					</div>
					<div class="line">
						<span class="lineLeft">商家名称</span>
						<span class="lineRight" id="ownerShip"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">金额（元）</span>
						<span class="lineRight" id="chorMoeny"></span>
					</div>
					<div class="line">
						<span class="lineLeft">电量（度）</span>
						<span class="lineRight" id="chorQuantityelectricity"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">充电电费（元）</span>
						<span class="lineRight" id="chorChargemoney"></span>
					</div>
					<div class="line">
						<span class="lineLeft">充电服务费（元）</span>
						<span class="lineRight" id="chorServicemoney"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">实际优惠（元）</span>
						<span class="lineRight" id="couponMoney"></span>
					</div>
					<div class="line">
						<span class="lineLeft">充电时间段</span>
						<span class="lineRight" id="chorTimequantum"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">订单状态</span>
						<span class="lineRight" id="chargingstatusName" ></span>
					</div>
					<div class="line">
						<span class="lineLeft">开票状态</span>
						<span class="lineRight" id="puhiInvoiceStatus"></span>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span class="lineLeft">费率id</span>
						<span class="lineRight rateValue" id="rateValue"></span>
					</div>
				</div>
			</div>
		</div>
		<div class="rateTable" id="rateTable" style="display: none;">
			<div class="rateBlock">
				<div class="rateTitle">
					费率
				</div>
				<div class="rateMain" id="rainMain">
				</div>
				<div class="rateBottom">
					<div class="width60">
						服务费
					</div>
					<div class="width30">
						<span id="chReServiceCharge"></span>元/度
					</div>
				</div>
			</div>
		</div>
	</body>
<script src="${ctx}/static/js/order/listChargeDetail.js" type="text/javascript" charset="utf-8"></script>
</html>