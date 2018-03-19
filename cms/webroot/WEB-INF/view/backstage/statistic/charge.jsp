<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>充电统计</title>
		<link rel="stylesheet" type="text/css" href="${ctx }/static/css/electricStatistics/electricStatistics.css"/>
	</head>
	<body>
		<div class="container-statistics">
			<section class="header">
				<div class="title">
					充电统计
				</div>
				<div class="selectInput">
					<form id="statisticChargeDataForm">
						<select id="chargeDataProvince" name="provinceCode" onchange="ProvinceChange(this)">
							<option disabled selected>省</option>
						</select>
						<select id="chargeDataCity" name="cityCode" style="margin-left: 20px;">
							<option disabled selected>请选择市</option>
						</select>
						<span class="check" onclick="chargeDataSearch()">确定</span>
					</form>
				</div>
			</section>
			<section class="unitData">
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充电度数
					</div>
					<div class="unitDataValue" id="v1">
					</div>
				</div>
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充电时长（小时）
					</div>
					<div class="unitDataValue" id="v2">
					</div>
				</div>
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充电次数
					</div>
					<div class="unitDataValue" id="v3">
					</div>
				</div>
				<div class="realtimeBlock">
					<div class="unitDataTitle">
						充电金额（元）
					</div>
					<div class="unitDataValue" id="v4">
					</div>
				</div>
			</section>
			<section class="main">
				<div class="mainTab">
					<span class="active">充电度数</span>
					<span>交直流充电占比</span>
					
				</div>
				<div class="mainTabContent">
					<div class="mainTabBlock" id="chargeCount" style="width:1000px;height: 350px;margin: 0 auto;">
						
					</div>
					<div class="mainTabBlock" id="pieChart" style="display: none;width:1000px;height: 350px;margin: 0 auto;">
						
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
									<th style="padding-left: 12px;">充电度数</th>
									<th>充电时长</th>
									<th>充电次数</th>
									<th>充电金额（元）</th>
								</tr>
							</thead>
							<tbody id="chargeDataTbody">
								
							</tbody>
						</table>
						<div id="chargeDataPage" class="pagination col-md-10 col-sm-10">
							
						</div>
					</div>
				</div>
				
			</section>
		</div>
	</body>
	<script src="${ctx }/static/js/electricStatistics/chargeData.js" type="text/javascript" charset="utf-8"></script>
</html>

