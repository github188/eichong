<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
		$(".pages").html("");
	}
});
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<style>
  .pCenterRed {
  	color:red;
  	margin-top:5px;
  }
  
  .pCenterRedGreen {
  	color:green;
  	margin-top:5px;
  }
  .title{
  	text-align:center;
  	font-size:16px;
  	
  }
  .one{
  	width:100%;
  	margin-top:10px;
  	text-align:center;
  	font-size:14px;
  }
  .myBtn{
  	display:inline-block;
  	width:80px;
  	height:30px;
  	border:1px solid #ccc;
  	text-align:center;
  	line-height:30px;
  	border-radius:8px;
  	cursor:pointer; 
  	color:#000;
  	background-color:#ebebeb;
  }
</style>
<div id="partner_settle" style="width:300px;height:220px;left:350px;top:120px;z-index:9999;position:absolute;background:#e6edef;padding:40px;padding-right:60px;display: none">	
	<div class="one" >
	                                                                     预充金额：<span id="partner_settleAmt">--</span><span>元</span><br><br>
						电量：<span id="partner_settlePower">--</span><span>度</span><br><br>
						充电电费：<span id="partner_settleChargeFee">--</span><span>元</span><br><br>
						充电服务费：<span id="partner_settleFwfee">--</span><span>元</span><br><br>
						金额：<span id="partner_settleMoney">--</span><span>元</span><br><br>
						结束时间：<span id="partner_settleEnd">--</span><br><br>
				
	</div>
	<div  style="display:none;" id="partner_orderFlag"></div>
	<div  style="display:none;" id="partner_orderId"></div>
	<div  style="display:none;" id="partner_chRePayMode"></div>
	<div class="one">
		<div class="myBtn" style="margin-left:50px" id="partner_settleConfirm" >结算</div>
		<div class="myBtn" style="margin-left:50px" id="partner_settleCancel">取消</div>
	</div>
</div>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="order/elecPartnerOrder.do"
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
						<td><span>充电时间</span> <input id="startDate_f7"
							name="beginChargetime" placeholder="请选择"
							value="${tblChargingOrder.beginChargetime}" class="date"
							onClick="WdatePicker({el:'startDate_f7',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_f7\')}'})"><span>至</span></td>
						<td><input
							id="endDate_f7" name="endChargetime"
							value="${tblChargingOrder.endChargetime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_f7',minDate:'#F{$dp.$D(\'startDate_f7\')}'})">
						</td>
						<td><span>桩体编码</span> <input
							name="eleCode" placeholder="请输入"
							value="${tblChargingOrder.eleCode }" /></td>
							
					   <td><span>充电点名称&nbsp;&nbsp;&nbsp;&nbsp;</span> <input
							name="chargePointName" placeholder="请输入"
							value="${tblChargingOrder.chargePointName }" /></td>
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
						<td><span>合作商名称&nbsp;&nbsp;&nbsp;&nbsp;</span> <input
							name="partnerName" placeholder="请输入"
							value="${tblChargingOrder.partnerName }" /></td>
					    <td><span>用户标示</span> <input name="chorParterUserLogo"
							placeholder="请输入" value="${tblChargingOrder.chorParterUserLogo }" />
						</td>
						<td align="right"><bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" />
						</td>
						<td align="right"><bmtag:button messageKey="excel导出"
								type="button"
								onclick="exportSubmit('pagerForm','order/PartnerBusChargeExport.do')" />
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

			
				<li><bmtag:link isAuth="true"
					id="partnerSettlePage"  href="#"
					messageKey="人工结算" dwzClass="edit"
					onclick="checkSelect_pure(this)" /></li>
		</ul>
		</div>

	<table class="table" layoutH="150">
		<thead>
			<tr align="center">
				<th width="2%" ><input type="checkbox" group="ids"
					class="checkboxCtrl"  /></th>
				<th width="3%"><bmtag:message messageKey="序号" /></th>
				<th width="12%"><bmtag:message messageKey="订单编号" /></th>
				<th width="8%"><bmtag:message messageKey="桩体编码" /></th>
				<th width="8%" style="display: none"><bmtag:message messageKey="电桩名称" /></th>
				<th width="8%"><bmtag:message messageKey="充电点名称" /></th>
				<th width="8%"><bmtag:message messageKey="用户标示" /></th>
				<th width="5%"><bmtag:message messageKey="合作商" /></th>
				<th width="4%"><bmtag:message messageKey="金额(元)" /></th>
				<th width="3%"><bmtag:message messageKey="电量" /></th>
				<th width="4%"><bmtag:message messageKey="充电电费(元)" /></th>
				<th width="5%"><bmtag:message messageKey="充电服务费(元)" /></th>
				<th width="11%"><bmtag:message messageKey="充电时间段" /></th>
				<th width="5%"><bmtag:message messageKey="订单状态" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ChargePartnerList}" var="Partner" varStatus="status">
			<%-- 	<tr target="pkChargingorder" rel="${Partner.pkChargingorder }"
					align="center"> --%>
					
					<c:if test="${Partner.settleFlag=='1'}">
					<tr target="pkChargingorder" rel="${Partner.pkChargingorder }"
						align="center" style='color: red'>
				</c:if>
				<c:if test="${Partner.settleFlag=='0'}">
					<tr target="pkChargingorder" rel="${Partner.pkChargingorder }"
						align="center">
				</c:if> 
					<td><input name="ids" value="${Partner.pkChargingorder}"
						type="checkbox" data-state="${Partner.settleFlag}"></td>
					<%-- <td><input name="pkCharges" value="${Partner.pkChargingorder }"
						type="checkbox" /></td> --%>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${Partner.chorCode }</td>
					<td>${Partner.eleCode }</td>
					<td style="display: none">${Partner.elpiElectricpileName }</td>
					<td>${Partner.chargePointName }</td>
					<td>${Partner.chorParterUserLogo }</td>
					<td>${Partner.partnerName }</td>
					<td>${Partner.chorMoeny }</td>
					<td>${Partner.chorQuantityelectricity }</td>
					<td>${Partner.chorChargemoney }</td>
					<td>${Partner.chorServicemoney }</td>
					<td>${Partner.chorTimequantum }</td>
					<td><c:if test="${Partner.chorChargingstatus==1}">未支付</c:if> <c:if
							test="${Partner.chorChargingstatus==2}">支付成功</c:if> <c:if
							test="${Partner.chorChargingstatus==3}">完成未结算</c:if></td>
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

/**
 * 获取选中的ID
 */
 function getIds() {
		var $box = navTab.getCurrentPanel();
		var ids = [];
		$box.find("input:checked").filter("[name='ids']").each(function(i) {
			ids.push($(this).attr("value") + ',' + $(this).attr("data-state"));
		})
		return ids;
	}

	function checkSelect_pure(obj) {

		var $this = $(obj);
		var ids = getIds();
		if (ids.length == 0) {
			alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
			return false;
		} else if (ids.length > 1) {
			alertMsg.error("不支持多条订单同时人工结算！");
		} else {
			var idAndState = ids[0].split(',')
			if (idAndState[1] == 0) {
				alertMsg.error("该条订单不支持人工结算！");
			} else {
		
				$.ajax({
					type : 'post',
					url : "/wanma/admin/order/getSettleInfo.do",
					data : {
						id : idAndState[0]
					},
					cache : false,
					dataType : "json",
					success : function(datas) {
						
					  if (typeof datas == 'string') {
							datas = JSON.parse(datas);
					  }
					  if(datas.commonStatus=="1"){
						  
						 alertMsg.error("电桩在连接状态,不能进行人工结算！");
						 return;
					  }
					  
					  if(datas.orderFlag=="0"){
						  
						  alertMsg.error("未查到相关hbase数据，不能够进行人工结算！");
						  return;
					  }
					  
					  else if(datas.orderFlag=="1"){
						  
						  $("#partner_settleAmt").html((datas.chReFrozenAmt) );
						  $("#partner_settlePower").html((datas.power) );
						  $("#partner_settleChargeFee").html((datas.money));
						  var fwFee=datas.power*0.6;
						  $("#partner_settleFwfee").html(fwFee);
						  $("#partner_settleMoney").html((Number(datas.money)+Number(fwFee)).toFixed(4));
						  $("#partner_settleEnd").html((datas.charingTime));
						  $("#partner_orderFlag").html((datas.orderFlag));
						  $("#partner_orderId").html((datas.orderId));
						  $("#partner_chRePayMode").html((datas.chRePayMode));
						  $("#partner_settle").show();
						  
					  }
						
					
					}
				});
			}
		}

	}
	$("#partner_settleCancel").click(function(){
		$("#partner_settle").hide();
		
	})


	$("#partner_settleConfirm").click(function(){
		$("#partner_settle").hide();
		var ordersFlag=  $("#partner_orderFlag").text();
			$.ajax({
				type : 'post',
				url : "/wanma/admin/order/personSettle.do",
				dataType : "json",
				data :{
					settleAmt:$("#partner_settleAmt").text(),
					settlePower:$("#partner_settlePower").text(),
					settleChargeFee:$("#partner_settleChargeFee").text(),
					settleMoney:$("#partner_settleMoney").text(),
					settleFwfee:$("#partner_settleFwfee").text(),
					settleEnd:$("#partner_settleEnd").text(),
					orderId:$("#partner_orderId").text(),
					chRePayMode:$("#partner_chRePayMode").text(),
				},
				success : function(datas) {
				
					if(datas=="1"){
						alertMsg.correct("结算成功！");
					}
					else if(datas=="2"){
						alertMsg.error("预充金额小于消费金额，无法人工结算!");
					}
					else{
						
						alertMsg.error("结算失败！");
					}
					$("#formSubmitter").click();
					
				}
			});
		
		})
</script>