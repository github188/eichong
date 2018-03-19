<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">

	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/invoicedBillOrder.do?pkInvoice=${pkInvoice}" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/> 
	<input type="hidden" name="pkInvoice" value="${pkInvoice}"/> 
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->	
					<td style="align:left">
			            <span>充电时间&nbsp;</span>				             	 
			             <input id="createDates_f3" name="createDates" value="${params.createDates}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'createDates_f3',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'updateDates_f3\')}'})">
			                                    至 <input id="updateDates_f3" name="updateDates" value="${params.updateDates}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'updateDates_f3',minDate:'#F{$dp.$D(\'createDates_f3\')}'})">                       	            
		            </td>
					<td><span>区域选择</span>
							<select class="provinceCode required"  id="filter_selProvince"  next="filter_selCity"  name="postOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== TblPowerstation.postOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="filter_selCity"   next="filter_selDistrict" data-val="${TblPowerstation.postOwnCityCode}" name="postOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="filter_selDistrict" data-val="${TblPowerstation.postOwnCountyCode}" name="postOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
				   </td>
				   </tr>
				   <tr>
				   <td style="align:left">
						<span>充点电</span>
						<input name="postName" value="${params.postName}" >
					</td>
					<td >
						<span>订单号</span>
						<input name="Code" value="${params.Code}"  />
					</td>
					<td >
						<span>电桩编码</span>
						<input name="PileNumber" value="${params.PileNumber}" />
					</td>
		            <td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter_invoice"/>
					</td>
				   </tr>
			 </tbody>
		</table>
	</div>

</form>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
		</ul>
</div>
    <table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th><bmtag:message messageKey="序号" /></th>
                <th><bmtag:message messageKey="订单号" /></th>								
				<th><bmtag:message messageKey="充电时间" /></th>
				<th><bmtag:message messageKey="电费"/></th>
				<th><bmtag:message messageKey="服务费"/></th>
				<th><bmtag:message messageKey="优惠后电费"/></th>
				<th><bmtag:message messageKey="优惠后服务费"/></th>
				<th><bmtag:message messageKey="充电桩编码"/></th>
				<th><bmtag:message messageKey="充点电"/></th>
				<th><bmtag:message messageKey="城市"/></th>
				<th><bmtag:message messageKey="归属公司"/></th>
				<th><bmtag:message messageKey="订单状态"/></th>
				<th><bmtag:message messageKey="开票状态"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${invoicedOrderList}" var="orders" varStatus="status">
				<tr target="id" rel="${orders.ChargingOrder}" align="center">
             		<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
                    <td>${orders.Code }</td>
					<td>${orders.TimeQuantum}</td>
					<td>${orders.ChargeMoney }</td>
					<td>${orders.ServiceMoney }</td>
					<td>${orders.discountChangMoney }</td>
					<td>${orders.discountServiceMoney }</td>
					<td>${orders.PileNumber}</td>
					<td>${orders.postName }</td>
					<td>${orders.ElectricPileAddress}</td>
				    <td>
				     <c:if test="${orders.ownerCompany ==0 }">其他</c:if>
					 <c:if test="${orders.ownerCompany ==1 }">爱充网</c:if>
					 <c:if test="${orders.ownerCompany ==2 }">国网</c:if>
					 <c:if test="${orders.ownerCompany ==3 }">特斯拉</c:if>
				   </td>
					<td>
					<c:if test="${orders.ChargingStatus ==1 }">待支付</c:if>
					<c:if test="${orders.ChargingStatus ==2 }">支付成功</c:if>
					<c:if test="${orders.ChargingStatus ==3 }">完成操作</c:if>
					<c:if test="${orders.ChargingStatus ==4 }">异常订单</c:if>
					<c:if test="${orders.ChargingStatus ==5 }">临时结算</c:if>
					</td>
					<td>
					<c:if test="${orders.InvoiceStatus ==0}">未开票</c:if>
					<c:if test="${orders.InvoiceStatus ==1 }">已提交</c:if>
					<c:if test="${orders.InvoiceStatus ==2 }">已开票</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
    




