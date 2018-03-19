<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
	$(".pages").html("");
}
});
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="order/elecUnitOrder.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>订单编号</span>
					<input name="chorCode" placeholder="请输入" value="${tblChargingOrder.chorCode }"/>
				</td>
				<td>
					<span>充电点名称</span>
					<input name="chargePointName" placeholder="请输入" value="${tblChargingOrder.chargePointName }"/>
				</td>
				<td><span>充电时间&nbsp;&nbsp;&nbsp;&nbsp;</span> <input id="startDate_unitCharge"
							name="beginChargetime" placeholder="请选择"
							value="${tblChargingOrder.beginChargetime}" class="date"
							onClick="WdatePicker({el:'startDate_unitCharge',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_unitCharge\')}'})">
						<span>至</span><input
							id="endDate_unitCharge" name="endChargetime"
							value="${tblChargingOrder.endChargetime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_unitCharge',minDate:'#F{$dp.$D(\'startDate_unitCharge\')}'})">
						</td>
				</tr>
		        <tr>
				<td>
					<span>订单状态</span>
					<select name="chorChargingstatus" style="width:155px">
						<option value=""}>全部</option>
						<option value="1" ${tblChargingOrder.chorChargingstatus == 1?"selected":""}>未支付</option>
						<option value="2" ${tblChargingOrder.chorChargingstatus == 2?"selected":""}>支付成功</option>
						<option value="3" ${tblChargingOrder.chorChargingstatus == 3?"selected":""}>完成未结算 </option>
						<%-- <option value="3" ${tblChargingOrder.chorChargingstatus == 3?"selected":""}>完成操作</option> --%>
					</select>
				</td>
				<td>
					<span>商家名称&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input name="usName" placeholder="请输入"  value="${tblChargingOrder.usName }"/>
				</td>
				<%-- <td>
			       <span>充电开始时间</span>
			        <input id="begin_charge_time" placeholder="请选择" name="beginChargetime" value="${tblChargingOrder.beginChargetime }" class="date" onClick="WdatePicker()" dateFmt="yyyy-MM-dd HH:mm:ss" >
			                  
		        </td> --%>
		        	
					<td>
						<span>充电点地址</span>
						<input name="chargePointAddress" placeholder="请输入" value="${tblChargingOrder.chargePointAddress }"/>
						<span>桩体编号</span>
						<input
							name="eleCode" placeholder="请输入"
							value="${tblChargingOrder.eleCode }" />
					</td>
			
<td></td><td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
						<td align="right">
   						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','orderExport/UnitCharge.do')"/>
			   </td>
			</tr>
		</tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="110">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkChargeUnits"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th>
		    <th><bmtag:message messageKey="商家名称"/></th>
		    <th><bmtag:message messageKey="桩体编号"/></th>
		    <th><bmtag:message messageKey="充电点名称"/></th>
		    <th><bmtag:message messageKey="充电点地址"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="收益(元)"/></th>
		    <th><bmtag:message messageKey="电量"/></th>
		    <th><bmtag:message messageKey="充电金额"/></th>
		    <th><bmtag:message messageKey="充电服务费"/></th>
		    <th><bmtag:message messageKey="充电时间段"/></th>
		    <th><bmtag:message messageKey="订单状态"/></th>
		    
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${ChargeUnitList}" var="ChargeUnit" varStatus="status">
			<tr target="pkChargingorder" rel="${ChargeUnit.pkChargingorder }" align="center">
				<td>
					<input name="pkChargeUnits"  value="${ChargeUnit.pkChargingorder }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${ChargeUnit.chorCode }</td>
				<td>${ChargeUnit.comName }</td>
				<td>${ChargeUnit.eleCode }</td>
				<td>${ChargeUnit.chargePointName }</td>
				<td>${ChargeUnit.chargePointAddress }</td>
				<td>${ChargeUnit.usName }</td>
				<td>${ChargeUnit.chorMoeny }</td>
				<td>${ChargeUnit.chorQuantityelectricity }</td>
				<td>${ChargeUnit.chorChargemoney }</td>
				<td>${ChargeUnit.chorServicemoney }</td>
				<td>${ChargeUnit.chorTimequantum }</td>
				<td><c:if test="${ChargeUnit.chorChargingstatus==1}" >待支付</c:if>
				<c:if test="${ChargeUnit.chorChargingstatus==2}" >支付成功</c:if>
				<c:if test="${ChargeUnit.chorChargingstatus==3}" >完成未结算 </c:if></td>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
