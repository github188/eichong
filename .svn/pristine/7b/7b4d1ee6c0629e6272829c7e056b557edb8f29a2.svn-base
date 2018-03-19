<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>充值消费订单</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/order/listPayOrder.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<div class="formList">
					<form id="listPayOrderForm">
						<input name="userPhone"  type="text" class="160width" placeholder="手机号/用户姓名">
							<div class="dataBlock">
								<input type="" name="startDate" id="startDatePayOrder" value="" placeholder="起始时间"
								onClick="WdatePicker({el:'startDatePayOrder',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDatePayOrder\')}'})"/>
								<span>至</span>
								<input type="" name="endDate" id="endDatePayOrder" placeholder="截止时间" 
								onClick="WdatePicker({el:'endDatePayOrder',minDate:'#F{$dp.$D(\'startDatePayOrder\')}'})" />
							</div>
						<select class="marginLeft10" name="puhiChargeType" id="puhiChargeType">
							<option disabled selected>充值渠道</option>
						</select>
						
						<span class="check marginLeft10" onclick="listPayOrderSearch()">查询</span>
						<span class="exportTable marginLeft10 fileExport" rel="listPayOrderForm" href="/admin/order/payOrderExport.do">导出EXCEL</span>
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
								<th>手机号</th>
								<th>用户姓名</th>
								<th>充值金额</th>
								<th>充值时间</th>
								<th>充值渠道</th>
								<th>消费备注</th>
							</tr>
						</thead>
						<tbody id="listPayOrderTbody">
							
						</tbody>
					</table>
					<div class="" style="height: 40px; width: auto;">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</div>
			</div>
			<div id="listPayOrderPage" class="pagination col-md-10 col-sm-10">
				
			</div>
		</div>
	</body>
<script src="${ctx}/static/js/order/listPayOrder.js" type="text/javascript" charset="utf-8"></script>
</html>