<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>充电收益订单</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/order/listBusCharge.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<div class="formList">
					<form id="listBusChargeForm">
						<input name="chorCode" type="text" style="width: 200px;" class="160width" placeholder="订单编号/电桩编号/电桩名称">
						<input name="comName" type="text" style="width: 160px;" class="160width" placeholder="商家">
							<div class="dataBlock">
								<input type="" name="startDate" id="startDateBusCharge" value="" placeholder="起始时间"
									onClick="WdatePicker({el:'startDateBusCharge',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDateBusCharge\')}'})"/>
								<span>至</span>
								<input type="" name="endDate" id="endDateBusCharge" value="" placeholder="截止时间"
									onClick="WdatePicker({el:'endDateBusCharge',minDate:'#F{$dp.$D(\'startDateBusCharge\')}'})" />
							</div>
						<select class="marginLeft10" name="chorChargingstatus" id="elpiState">
							<option disabled selected>订单状态</option>
						</select>
						
						<span class="check marginLeft10" onclick="listBusChargeSearch()">查询</span>
						<!-- <span class="exportTable marginLeft10 fileExport" rel="listBusChargeForm" href="/admin/order/chargeEarnOrderExport.do" >导出EXCEL</span> -->
					</form>
				</div>
			</div>
			<div class="box2 col-md-10 col-sm-8">
				<div class="padding30">
					<table class="myTable listPayOrderTable">
						<thead>
							<tr class="active">
								<th class="smallWidth"><input type="checkbox" name="" class="selAll" value=""/></th>
								<th class="smallWidth">序号</th>
								<th>订单编号</th>
								<th>桩体编号</th>
								<th>电桩名称</th>
								<th>商家名称</th>
								<th>收益</th>
								<th>电量</th>
								<th>充电时间段</th>
								<th>订单状态</th>
							</tr>
						</thead>
						<tbody id="listBusChargeTbody">
						</tbody>
					</table>
					<div class="" style="height: 40px; width: auto;">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</div>
			</div>
			<div id="listBusChargePage" class="pagination col-md-10 col-sm-10">
			</div>
		</div>
	</body>
<script src="${ctx}/static/js/order/listBusCharge.js" type="text/javascript" charset="utf-8"></script>
</html>