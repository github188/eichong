<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>费率设置</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/config/rateinfoList.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<div class="formList">
					<form id="rateinfoListForm">
						<input type="text" name="cityName" class="160width" placeholder="城市">
						<span class="check" onclick="rateinfoListSearch()">查询</span>
						<span class="check" id="rateInfoAdd">新增</span>
						<a target="selectedTodo" rel="ids" href="/admin/rateinfo/rateinfoRemove.do"  posttype="string" title="确定删除吗？"><span class="check">删除</span></a>
					</form>
				</div>
			</div>
			<div class="box2 col-md-10 col-sm-8">
				<div class="padding30">
						<table class="myTable">
							<thead>
								<tr class="active">
									<th class="smallWidth"><input type="checkbox" name="" class="selAll" value=""/></th>
									<th class="smallWidth">费率ID</th>
									<th>城市</th>
									<th>预约单价</th>
									<th>服务费</th>
									<th>尖段电价</th>
									<th>峰段电价</th>
									<th>平段电价</th>
									<th>谷段电价</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="rateinfoListTbody">
							</tbody>
						</table>
						<div id="" class="" style="height: 40px; width: auto;">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
				</div>
			</div>
			<div id="rateinfoListPage" class="pagination col-md-10 col-sm-10">
			</div>
		</div>
		<!--费率新增-->
		<div class="rateinfoStyle" id="rateInfoAddContent" style="display: none;">
			<form id="rateinfoAddForm" method="post"
				action="/admin/rateinfo/rateinfoSave.do" callback="refreshCurrent()">
				<input type="hidden" name="rainQuantumdate" id="rainQuantumdateAdd"  />
			<div class="checkSelect">
				<div class="lineBlock">
					<div class="line">
						<span>区域选择</span>
						<select name="raInProvinceId" id="rateInfoAddProvince" onchange="ProvinceChange(this)">
						</select>
						<select name="raInCityId" id="rateInfoAddCity" onchange="cityChange(this)">
							<option disabled selected>请选择市</option>
						</select>
						<select name="raInAreaId" id="rateInfoAddArea">
							<option disabled selected>请选择区</option>
						</select>
					</div>
				</div>
			</div>
			<div class="checkInput">
				<div class="lineBlock">
					<div class="line">
						<span>尖段电价</span>
						<input type="text" name="rainTiptimetariff"  value="" class="marginLeft16" id="jdCharge"/>
					</div>
					<div class="line">
						<span>峰段电价</span>
						<input type="text" name="rainPeakelectricityprice"  value="" class="marginLeft16" id="fdCharge"/>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span>平段电价</span>
						<input type="text" name="rainUsualprice"  value="" class="marginLeft16" id="pdCharge"/>
					</div>
					<div class="line">
						<span>谷段电价</span>
						<input type="text" name="rainValleytimeprice"  value="" class="marginLeft16" id="gdCharge"/>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span>预约单价</span>
						<input type="text" name="rainReservationrate"  value="" class="marginLeft16" id="orderCharge"/>
					</div>
					<div class="line">
						<span>服务费</span>
						<input type="text" name="rainServicecharge"  value="" class="marginLeft28" id="serviceCharge"/>
					</div>
				</div>
				<div class="lineBlock">
					<div class="line">
						<span>备注</span>
						<textarea name="rainRemarks" id="" cols="30" rows="2" class="marginLeft40"></textarea>
					</div>
				</div>
			</div>
			
			
			<div class="timeList">
				<div class="timeListTitle">
					<span id="" class="boom">
						时间段设置
					</span>
					<span id="timeListAddBtn">
						新增
					</span>
					<span id="timeListDeleteBtn">
						删除
					</span>
				</div>
				<div class="">
					<table class="table table-bordered rateinfoTable" id="rateInfoAddTable">
						<thead>
							<tr class="active">
								<th class="smallWidth"><input type="checkbox" name="" class="selAll1" value="" disabled="disabled"/></th>
								<th>时间段</th>
								<th>开始时间</th>
								<th>结束时间</th>
							</tr>
						</thead>
						<tbody id="rateinfoTbody">
						</tbody>
					</table>
				</div>
				<div class="rateInfoAddTipText" style="display: none; margin-top: 6px; color: #FF0000;">
					
				</div>
			</div>
			</form>
		</div>
		<!--费率编辑-->
		<div class="rateinfoStyle" id="rateInfoEditContent" style="display: none;">
			<form id="rateinfoEditForm" method="post"
				action="/admin/rateinfo/rateinfoModify.do" callback="refreshCurrent()">
				<input type="hidden" name="pkRateinformation" id="pkRateinformation"  />
				<input type="hidden" name="rainWarnmoney" id="rainWarnmoney"  />
				<input type="hidden" name="rainMinfreezingmoney" id="rainMinfreezingmoney"  />
				<input type="hidden" name="rainQuantumdate" id="rainQuantumdateEdit"  />
				
				<div class="lineBlock">
					<div class="line">
						<span>区域选择</span>
						<select name="raInProvinceId" id="rateInfoEditProvince" onchange="ProvinceChange(this)">
						</select>
						<select name="raInCityId" id="rateInfoEditCity" onchange="cityChange(this)">
						</select>
						<select name="raInAreaId" id="rateInfoEditArea">
						</select>
					</div>
				</div>
				<div class="checkInputEdit">
					<div class="lineBlock">
						<div class="line">
							<span>尖段电价</span>
							<input type="text" name="rainTiptimetariff"  value="" class="marginLeft16" id="rainTiptimetariff"/>
						</div>
						<div class="line">
							<span>峰段电价</span>
							<input type="text" name="rainPeakelectricityprice"  value="" class="marginLeft16" id="rainPeakelectricityprice"/>
						</div>
					</div>
					<div class="lineBlock">
						<div class="line">
							<span>平段电价</span>
							<input type="text" name="rainUsualprice"  value="" class="marginLeft16" id="rainUsualprice"/>
						</div>
						<div class="line">
							<span>谷段电价</span>
							<input type="text" name="rainValleytimeprice"  value="" class="marginLeft16" id="rainValleytimeprice"/>
						</div>
					</div>
					<div class="lineBlock">
						<div class="line">
							<span>预约单价</span>
							<input type="text" name="rainReservationrate"  value="" class="marginLeft16" id="rainReservationrate"/>
						</div>
						<div class="line">
							<span>服务费</span>
							<input type="text" name="rainServicecharge"  value="" class="marginLeft28" id="rainServicecharge"/>
						</div>
					</div>
					<div class="lineBlock">
						<div class="line">
							<span>备注</span>
							<textarea name="rainRemarks" id="rainRemarks" cols="30" rows="2" class="marginLeft40"></textarea>
						</div>
					</div>
				</div>
			
				<div class="timeList">
					<div class="timeListTitle">
						<span id="" class="boom">
							时间段设置
						</span>
						<span id="timeListEditBtn">
							新增
						</span>
						<span id="timeListEditDeleteBtn">
							删除
						</span>
					</div>
					<div class="">
						<table class="table table-bordered rateinfoTable" id="rateInfoEditTable">
							<thead>
								<tr class="active">
									<th class="smallWidth"><input type="checkbox" name="" class="selAll2" value="" disabled="disabled"/></th>
									<th>时间段</th>
									<th>开始时间</th>
									<th>结束时间</th>
								</tr>
							</thead>
							<tbody id="rateinfoEditTbody">
							</tbody>
						</table>
					</div>
					<div class="rateInfoEditTipText" style="display: none; margin-top: 6px; color: #FF0000;">
						
					</div>
				</div>
			</form>	
		</div>
	</body>	
<script src="${ctx}/static/js/config/rateinfoList.js" type="text/javascript" charset="utf-8"></script>
</html>