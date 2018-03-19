<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>app注册</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/consumeStatistics/consumeStatistics.css"/>
	</head>
	<body>
		<div class="container-statistics">
			<section class="header">
				<div class="title">
					App注册
				</div>
				
			</section>
			<section class="unitData">
				<div class="unitBlock">
					<div class="unitDataTitle">
						今日注册
					</div>
					<div class="unitDataValue" id="todayRegisterCount">
					</div>
				</div>
				<div class="unitBlock">
					<div class="unitDataTitle">
						昨日注册
					</div>
					<div class="unitDataValue" id="yesterdayRegisterCount">
					</div>
				</div>
				<div class="unitBlock">
					<div class="unitDataTitle">
						累计注册
					</div>
					<div class="unitDataValue" id="totalRegisterCount">
					</div>
				</div>
			</section>
			<section class="main">
				<div class="mainTab">
					<span class="active">最近7天</span>
					<span >每月注册</span>
				</div>
				<div class="mainTabContent">
					<div class="mainTabBlock" id="latestData" style="width:1000px;height: 350px;margin: 0 auto;">
						
					</div>
					<div class="mainTabBlock" id="mounthData" style="display: none;width:1000px;height: 350px;margin: 0 auto;">
						
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
									<th>注册时间</th>
									<th style="padding-left: 12px;">手机号</th>
									<th>注册渠道</th>
								</tr>
							</thead>
							<tbody id="appRegisterTbody">
							</tbody>
						</table>
						<div id="appRegisterListPage" class="pagination col-md-10 col-sm-10">
						</div>
					</div>
				</div>
				
			</section>
		</div>
	</body>
	<script src="${ctx}/static/lib/Echars/echarts.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctx}/static/js/consumeStatistics/appRegister.js" type="text/javascript" charset="utf-8"></script>
	
</html>

