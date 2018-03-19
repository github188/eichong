<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="order/elecFirmOrder.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>订单编号&nbsp;</span> <input name="chorCode"
							width="30px" placeholder="请输入"
							value="${tblChargingOrder.chorCode }" /></td>
						<td><span>充电点名称</span> <input name="chargePointName"
							placeholder="请输入" value="${tblChargingOrder.chargePointName }" />
						</td>
						<td><span>充电时间</span> <input id="startDate_busCharge"
							name="beginChargetime" placeholder="请选择"
							value="${tblChargingOrder.beginChargetime}" class="date"
							onClick="WdatePicker({el:'startDate_busCharge',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_busCharge\')}'})"><span>至</span></td>
						<td><input
							id="endDate_busCharge" name="endChargetime"
							value="${tblChargingOrder.endChargetime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_busCharge',minDate:'#F{$dp.$D(\'startDate_busCharge\')}'})">
						</td>
						<td><span>桩体编号</span> <input
							name="eleCode" placeholder="请输入"
							value="${tblChargingOrder.eleCode }" /></td>
							<td><span>充电卡卡号</span> <input name="exterCardNumber" placeholder="请输入"
							value="${tblChargingOrder.exterCardNumber }" /></td>
						
						<td><span>区域选择</span>
							<select class="provinceCode required"  id="selpurProvince"  next="selpurCity"  name="provinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== tblChargingOrder.provinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="selpurCity"   next="selpurDistrict" data-val="${tblChargingOrder.cityCode}" name="cityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="selpurDistrict" data-val="${tblChargingOrder.countryCode}" name="countryCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
						</td>	
							
							
				
					</tr>
					<tr>
						<td><span>订单状态&nbsp;</span> <select name="chorChargingstatus"
							style="width: 155px">
								<option value=""}>全部</option>
								<option value="1"
									${tblChargingOrder.chorChargingstatus == 1?"selected":""}>未支付</option>
								<option value="2"
									${tblChargingOrder.chorChargingstatus == 2?"selected":""}>支付成功</option>
								<option value="3"
									${tblChargingOrder.chorChargingstatus == 3?"selected":""}>完成未结算</option>
						</select></td>
						<td><span>企业名称&nbsp;&nbsp;&nbsp;&nbsp;</span> <input
							name="comName" placeholder="请输入"
							value="${tblChargingOrder.comName }" /></td>
						
						<!--  <td><span>充电点地址</span> <input name="chargePointAddress"
							placeholder="请输入" value="${tblChargingOrder.chargePointAddress }" />
						</td>-->
						<td><span>合作商名称</span> <input name="partnerName"
							placeholder="请输入" value="${tblChargingOrder.partnerName }" />
						</td>
						<td><span>大账户号</span> <input name="userAccount"
							placeholder="请输入" value="${tblChargingOrder.userAccount }" />
						</td>
						<td><span>vin码</span> <input name="vinCode" placeholder="请输入"
							value="${tblChargingOrder.vinCode }" /></td>
							<td><span>车牌号</span> <input name="cvLicenseNumber" placeholder="请输入"
							value="${tblChargingOrder.cvLicenseNumber }" /></td>
						<td align="right"><bmtag:button messageKey="excel导出" type="button"
								onclick="exportSubmit('pagerForm','orderExport/pureCharge.do')" />
							<bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" />
						</td>
						
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="123">
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="pkCharges"
					class="checkboxCtrl" /></th>
				<th width="3%"><bmtag:message messageKey="序号" /></th>
				<th width="6%"><bmtag:message messageKey="订单编号" /></th>
				<th width="6%"><bmtag:message messageKey="桩体编号" /></th>
				<th width="4%"><bmtag:message messageKey="卡号" /></th>
				<th width="4%"><bmtag:message messageKey="vin码" /></th>
				<th width="4%"><bmtag:message messageKey="车牌号" /></th>
				<th width="8%"><bmtag:message messageKey="充电点名称" /></th>
				<!--  <th width="8%"><bmtag:message messageKey="充电点地址" /></th>-->
				<th width="4%"><bmtag:message messageKey="收益(元)" /></th>
				<th width="3%"><bmtag:message messageKey="电量" /></th>
				<th width="4%"><bmtag:message messageKey="充电电费(元)" /></th>
				<th width="5%"><bmtag:message messageKey="充电服务费(元)" /></th>
				<th width="4%"><bmtag:message messageKey="实际优惠金额(元)" /></th>
				<th width="7%"><bmtag:message messageKey="充电时间段" /></th>
				<th width="4%"><bmtag:message messageKey="充电时长(分钟)" /></th>
				<th width="4%"><bmtag:message messageKey="枪口" /></th>
				<th width="5%"><bmtag:message messageKey="订单状态" /></th>
				<!--  <th width="4%"><bmtag:message messageKey="开始soc(%)" /></th>
				<th width="4%"><bmtag:message messageKey="结束soc(%)" /></th>-->
				<th width="4%"><bmtag:message messageKey="企业名称" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ChargeFirmList}" var="Firm" varStatus="status">
				<tr target="pkChargingorder" rel="${Firm.pkChargingorder }"
					align="center">
					<td><input name="pkCharges" value="${Firm.pkChargingorder }"
						type="checkbox" /></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${Firm.chorCode }</td>
					<td>${Firm.eleCode }</td>
					<td>${Firm.exterCardNumber }</td>
					<td>${Firm.vinCode }</td>
					<td>${Firm.cvLicenseNumber }</td>
					<td>${Firm.chargePointName }</td>
					<!--<td>${Firm.chargePointAddress }</td> -->
					<td>${Firm.chorMoeny }</td>
					<td>${Firm.chorQuantityelectricity }</td>
					<td>${Firm.chorChargemoney }</td>
					<td>${Firm.chorServicemoney }</td>
					<td>${Firm.couponMoney }</td>
					<td>${Firm.chorTimequantum }</td>
					<td>${Firm.chargeTimeMinute }</td>
					<td>${Firm.eleheadName }</td>
					<td><c:if test="${Firm.chorChargingstatus==1}">未支付</c:if> <c:if
							test="${Firm.chorChargingstatus==2}">支付成功</c:if> <c:if
							test="${Firm.chorChargingstatus==3}">完成未结算</c:if></td>
				
				  <!--   <td>${Firm.startSoc }</td>
				   <td>${Firm.endSoc }</td>-->
				   <td>${Firm.comName }</td>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	loadCity($("#selpurProvince"));
	loadArea($("#selpurCity"));
});
</script>