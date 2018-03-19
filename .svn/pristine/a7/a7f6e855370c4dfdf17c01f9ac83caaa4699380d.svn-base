<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>历史数据</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/consumeStatistics/consumeStatistics.css"/>
	</head>
	<body>
		<div class="container-statistics">
			<section class="header">
				<div class="title">
					历史数据
				</div>
				<div class="selectInput">
					<form id="statisticHistoryForm">
					<input type="" name="startDate" id="startDateStatisticHistory" value="" placeholder="起始时间"
					onClick="WdatePicker({el:'startDateStatisticHistory',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDateStatisticHistory\')}'})"/>
					<span>至</span>
					<input type="" name="endDate" id="endDateStatisticHistory" value="" placeholder="截止时间" 
						onClick="WdatePicker({el:'endDateStatisticHistory',minDate:'#F{$dp.$D(\'startDateStatisticHistory\')}'})"/>
					<span class="check" onclick="loadHistory()">确定</span>
					</form>
				</div>
			</section>
			<section class="unitData">
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充值金额（元）
					</div>
					<div class="unitDataValue" id="payValue">
					</div>
				</div>
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充值笔数
					</div>
					<div class="unitDataValue" id="payCount">
					</div>
				</div>
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充电金额（元）
					</div>
					<div class="unitDataValue" id="chargeValue">
					</div>
				</div>
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充电次数
					</div>
					<div class="unitDataValue" id="chargeCount">
					</div>
				</div>
			</section>
			<section class="main">
				<div class="mainTab">
					<span class="active">充值</span>
					<span>充电</span>
					
				</div>
				<div class="mainTabContent">
					<!--充值金额&次数-->
					<div class="mainTabBlock" style="width:1000px;height: 350px;margin: 0 auto;" id="payTimes">
						
					</div>
					<!--充电金额&次数-->
					<div class="mainTabBlock" style="display: none;width:1000px;height: 350px;margin: 0 auto;" id="chargeTimes">
						
					</div>
				</div>
				<div class="mainTabContent">
					<div class="detaiData">
						<div class="detailDateTitle">
							详细数据
						</div>
						<table class="myTable" style="margin-top:0 ;">
							<thead>
								<tr class="active">
									<th>时间</th>
									<th style="padding-left: 12px;">微信充值金额</th>
									<th>微信充值次数</th>
									<th>支付宝充值金额</th>
									<th>支付宝充值次数</th>
									<th>充电金额</th>
									<th>充电次数</th>
								</tr>
							</thead>
							<tbody id="historyDataTbody">
								
							</tbody>
						</table>
						<div id="historyDataPage" class="pagination col-md-10 col-sm-10">
							
						</div>
					</div>
				</div>
				
			</section>
		</div>
	</body>
	<script src="${ctx}/static/js/consumeStatistics/historyData.js" type="text/javascript" charset="utf-8"></script>
</html>

