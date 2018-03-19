<%@page import="com.wanma.model.TblUser"%>
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
<div id="pure_settle" style="width:300px;height:220px;left:350px;top:120px;z-index:9999;position:absolute;background:#e6edef;padding:40px;padding-right:60px;display: none">	
	<div class="one" >
	                                                                     预充金额：<span id="pure_settleAmt">--</span><span>元</span><br><br>
						电量：<span id="pure_settlePower">--</span><span>度</span><br><br>
						充电电费：<span id="pure_settleChargeFee">--</span><span>元</span><br><br>
						充电服务费：<span id="pure_settleFwfee">--</span><span>元</span><br><br>
						金额：<span id="pure_settleMoney">--</span><span>元</span><br><br>
						结束时间：<span id="pure_settleEnd">--</span><br><br>
				
	</div>
	<div  style="display:none;" id="pure_orderFlag"></div>
	<div  style="display:none;" id="pure_orderId"></div>
	<div  style="display:none;" id="pure_chRePayMode"></div>
	<div class="one">
		<div class="myBtn" style="margin-left:50px" id="pure_settleConfirm" >结算</div>
		<div class="myBtn" style="margin-left:50px" id="pure_settleCancel">取消</div>
	</div>
</div>
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
								<option value="">全部</option>
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
	<div class="panelBar">
		<ul class="toolBar">

			
				<li><bmtag:link isAuth="true"
					id="pureSettlePage"  href="#"
					messageKey="人工结算" dwzClass="edit"
					onclick="checkSelect_pure(this)" /></li>
		</ul>
		</div>

	<table class="table" layoutH="153">
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" group="pkCharges"
					class="checkboxCtrl" /></th>
				<th width="3%"><bmtag:message messageKey="序号" /></th>
				<th width="6%"><bmtag:message messageKey="订单编号" /></th>
				<th width="6%"><bmtag:message messageKey="桩体编号" /></th>
				<th width="2%"><bmtag:message messageKey="编号" /></th>
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
				<c:if test="${Firm.settleFlag=='1'}">
					<tr target="pkChargingorder" rel="${Firm.pkChargingorder }"
						align="center" style='color: red'>
				</c:if>
				<c:if test="${Firm.settleFlag=='0'}">
					<tr target="pkChargingorder" rel="${Firm.pkChargingorder }"
						align="center">
				</c:if>
			
			
			<%-- 	<tr target="pkChargingorder" rel="${Firm.pkChargingorder }"
					align="center"> --%>
				<%-- 	<td><input name="ids" value="${Firm.pkChargingorder }"
						type="checkbox" /></td> --%>
					<td><input name="ids" value="${Firm.pkChargingorder}"
						type="checkbox" data-state="${Firm.settleFlag}"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${Firm.chorCode }</td>
					<td>${Firm.eleCode }</td>
					<td>${Firm.cpn }</td>
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
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
		$(".pages").html("");
	}
	loadCity($("#selpurProvince"));
	loadArea($("#selpurCity"));
});


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
					  
					  $("#pure_settleAmt").html((datas.chReFrozenAmt) );
					  $("#pure_settlePower").html((datas.power) );
					  $("#pure_settleChargeFee").html((datas.money));
					  var fwFee=datas.power*0.6;
					  $("#pure_settleFwfee").html(fwFee);
					  $("#pure_settleMoney").html((Number(datas.money)+Number(fwFee)).toFixed(4));
					  $("#pure_settleEnd").html((datas.charingTime));
					  $("#pure_orderFlag").html((datas.orderFlag));
					  $("#pure_orderId").html((datas.orderId));
					  $("#pure_chRePayMode").html((datas.chRePayMode));
					  $("#pure_settle").show();
					  
				  }
					
				
				}
			});
		}
	}

}
$("#pure_settleCancel").click(function(){
	$("#pure_settle").hide();
	
})


$("#pure_settleConfirm").click(function(){
	$("#pure_settle").hide();
	var ordersFlag=  $("#pure_orderFlag").text();
	
		
		$.ajax({
			type : 'post',
			url : "/wanma/admin/order/personSettle.do",
			dataType : "json",
			data :{
				settleAmt:$("#pure_settleAmt").text(),
				settlePower:$("#pure_settlePower").text(),
				settleChargeFee:$("#pure_settleChargeFee").text(),
				settleMoney:$("#pure_settleMoney").text(),
				settleFwfee:$("#pure_settleFwfee").text(),
				settleEnd:$("#pure_settleEnd").text(),
				orderId:$("#pure_orderId").text(),
				chRePayMode:$("#pure_chRePayMode").text(),
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