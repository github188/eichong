<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>故障统计</title>
		<link rel="stylesheet" type="text/css" href="${ctx }/static/css/electricStatistics/electricStatistics.css"/>
	</head>
	<body>
		<div class="container-statistics">
			<section class="header">
				<div class="title">
					故障统计
				</div>
				<div class="selectInput">
					<form id="statisticFaultDataForm">
						<select id="faultDataProvince" name="provinceCode" onchange="ProvinceChange(this)">
							<option disabled selected>省</option>
						</select>
						<select id="faultDataCity" name="cityCode" style="margin-left: 20px;">
							<option disabled selected>请选择市</option>
						</select>
						<span class="check" onclick="faultDataSearch()">确定</span>
					</form>
				</div>
			</section>
			<section class="unitData">
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						故障电桩数
					</div>
					<div class="unitDataValue" id="v1">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						离线电桩数
					</div>
					<div class="unitDataValue" id="v2">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						违规拔枪
					</div>
					<div class="unitDataValue" id="v3">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						BMS通信异常
					</div>
					<div class="unitDataValue" id="v4">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						电表异常
					</div>
					<div class="unitDataValue" id="v5">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						急停
					</div>
					<div class="unitDataValue" id="v6">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						接触器故障
					</div>
					<div class="unitDataValue" id="v7">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						过流停止
					</div>
					<div class="unitDataValue" id="v8">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						过压停止
					</div>
					<div class="unitDataValue" id="v9">
					</div>
				</div>
				<div class="faultDataBlock">
					<div class="unitDataTitle">
						防雷器故障
					</div>
					<div class="unitDataValue" id="v10">
					</div>
				</div>
			
			</section>
			<section class="main">
				<div class="mainTab">
					<span class="active">故障占比</span>
				</div>
				<div class="mainTabContent">
					<div class="mainTabBlock" id="pieChart" style="width:1000px;height: 350px;margin: 0 auto;">
						
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
									<th style="padding-left: 12px;">故障名称</th>
									<th>桩体编号</th>
									<th>电桩地址</th>
								</tr>
							</thead>
							<tbody id="faultDataTbody">
								
							</tbody>
						</table>
						<div id="faultDataPage" class="pagination col-md-10 col-sm-10">
							
						</div>
					</div>
				</div>
				
			</section>
		</div>
	</body>
	<script src="${ctx }/static/lib/Echarts/echarts.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctx }/static/js/electricStatistics/faultData.js" type="text/javascript" charset="utf-8"></script>
</html>

