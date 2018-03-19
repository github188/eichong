<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<link href="<%=basePath%>/static/css/rateInfoDisplay.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/static/js/other/rateInfoDisplay.js"></script>


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
<div id="settle" style="width:300px;height:220px;left:350px;top:120px;z-index:9999;position:absolute;background:#e6edef;padding:40px;padding-right:60px;display: none">	
	<div class="one" >
	                                                                     预充金额：<span id="settleAmt">--</span><span>元</span><br><br>
						电量：<span id="settlePower">--</span><span>度</span><br><br>
						充电电费：<span id="settleChargeFee">--</span><span>元</span><br><br>
						充电服务费：<span id="settleFwfee">--</span><span>元</span><br><br>
						金额：<span id="settleMoney">--</span><span>元</span><br><br>
						结束时间：<span id="settleEnd">--</span><br><br>
				
	</div>
	<div  style="display:none;" id="orderFlag"></div>
	<div  style="display:none;" id="orderId"></div>
	<div  style="display:none;" id="chRePayMode"></div>
	<div class="one">
		<div class="myBtn" style="margin-left:50px" id="settleConfirm" >结算</div>
		<div class="myBtn" style="margin-left:50px" id="settleCancel">取消</div>
	</div>
</div>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="order/elecOrder.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		
	
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>订单编号</span> <input name="chorCode"
							placeholder="请输入" value="${tblChargingOrder.chorCode }" /></td>
						<td><span>手机号&nbsp;&nbsp;&nbsp;&nbsp;</span> <input
							name="usPhone" placeholder="请输入"
							value="${tblChargingOrder.usPhone }" /></td>
						<td><span>桩体编号</span> <input name="eleCode" placeholder="请输入"
							value="${tblChargingOrder.eleCode }" /></td>
						<td><span>开票状态</span> <select name="puhiInvoiceStatus"
							style="width: 155px">
								<option value="">全部</option>
								<option value="0"
									${tblChargingOrder.puhiInvoiceStatus == "0"?"selected":""}>未开票</option>
								<option value="1"
									${tblChargingOrder.puhiInvoiceStatus == "1"?"selected":""}>待开票</option>
								<option value="2"
									${tblChargingOrder.puhiInvoiceStatus == "2"?"selected":""}>已开票</option>
						</select></td>
						<td><span>区域选择</span> <select class="provinceCode required"
							id="selProvince" next="selCity" name="provinceCode"
							style="float: none; width: 130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== tblChargingOrder.provinceCode ? 'selected="selected"' : ''}>
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
						</select> <select class="cityCode required" id="selCity" next="selDistrict"
							data-val="${tblChargingOrder.cityCode}" name="cityCode"
							style="float: none; width: 130px;">
								<option value="">--请选择城市--</option>
						</select> <select id="selDistrict"
							data-val="${tblChargingOrder.countryCode}" name="countryCode"
							class="required" style="float: none; width: 130px;">
								<option value="">--请选择区/县--</option>
						</select></td>

					</tr>
					<tr>

						<td><span>订单状态</span> <select name="chorChargingstatus"
							style="width: 155px">
								<option value=""}>全部</option>
								<option value="1"
									${tblChargingOrder.chorChargingstatus == '1'?"selected":""}>未支付</option>
								<option value="2"
									${tblChargingOrder.chorChargingstatus == '2'?"selected":""}>支付成功</option>
								<option value="3"
									${tblChargingOrder.chorChargingstatus == '3'?"selected":""}>完成未结算</option>
								<option value="4"
									${tblChargingOrder.chorChargingstatus == '4'?"selected":""}>异常订单</option>
						</select></td>

						<td style="padding-right: 0 !important;"><span>充电时间</span> <input
							id="startDate_f5" name="beginChargetime" placeholder="请选择"
							value="${tblChargingOrder.beginChargetime}" class="date"
							onClick="WdatePicker({el:'startDate_f5',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_f5\')}'})">
						</td>
						<td><span>至</span> &nbsp;&nbsp;&nbsp;&nbsp; <input
							id="endDate_f5" name="endChargetime"
							value="${tblChargingOrder.endChargetime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_f5',minDate:'#F{$dp.$D(\'startDate_f5\')}'})">
						</td>
						<td><span>充电卡卡号</span> <input name="exterCardNumber"
							placeholder="请输入" value="${tblChargingOrder.exterCardNumber }" /></td>
						<td></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>

						<td align="right"><bmtag:button messageKey="excel导出"
								type="button"
								onclick="exportSubmit('pagerForm','orderExport/normalCharge.do')" />
						</td>



					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<!--浮动的尖峰平谷汇率-->
	<div id="JFPG_BOX_DIV_P"
		style="height: auto; position: absolute; right: 20px; display: none">
		<ul id="JFPG_BOX_DIV" class="JFPG_BOX">
		</ul>
		<span class="closeP" onclick="closeRateWindow();">X</span>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
	<%-- 	<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="order/personSettle.do"
				rel="ids"  messageKey="common.icon.settle" postType="string" altKey="确定对该订单进行人工结算吗？" dwzClass="edit" id="personSettle" />
			</li> --%>
			
				<li><bmtag:link isAuth="true"
					id="personSettlePage"  href="#"
					messageKey="人工结算" dwzClass="edit"
					onclick="checkSelect(this)" /></li>
		</ul>
		
		
	</div>
	<!--汇率结束-->
	<table class="table" width="100%" layoutH="150">
		<thead>
			<tr>
				<th width="10"><input type="checkbox" group="pkCharges"
					class="checkboxCtrl" /></th>
				<th><bmtag:message messageKey="序号" /></th>
				<th><bmtag:message messageKey="订单编号" /></th>
				<th><bmtag:message messageKey="桩体编号" /></th>
				<th><bmtag:message messageKey="编号" /></th>
				<th><bmtag:message messageKey="充电点名称" /></th>
				<th><bmtag:message messageKey="电桩所有权" /></th>
				<%-- <th><bmtag:message messageKey="用户姓名" /></th> --%>
				<th><bmtag:message messageKey="充电卡卡号" /></th>
				<th><bmtag:message messageKey="用户手机" /></th>
				<th><bmtag:message messageKey="金额(元)" /></th>
				<th><bmtag:message messageKey="电量" /></th>
				<th><bmtag:message messageKey="充电电费(元)" /></th>
				<th><bmtag:message messageKey="充电服务费(元)" /></th>
				<th><bmtag:message messageKey="实际优惠金额(元)" /></th>
				<th><bmtag:message messageKey="消费时间段" /></th>
				<th><bmtag:message messageKey="充电时长(分钟)" /></th>
				<th><bmtag:message messageKey="订单状态" /></th>
				<th><bmtag:message messageKey="开票状态" /></th>
				<th><bmtag:message messageKey="费率" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ChargeList}" var="Charge" varStatus="status">

				<c:if test="${Charge.settleFlag=='1'}">
					<tr target="pkChargingorder" rel="${Charge.pkChargingorder }"
						align="center" style='color: red'>
				</c:if>
				<c:if test="${Charge.settleFlag=='0'}">
					<tr target="pkChargingorder" rel="${Charge.pkChargingorder }"
						align="center">
				</c:if>

		<%-- 		<td><input name="pkCharges" value="${Charge.pkChargingorder }"  rel="${Charge.pkChargingorder }
					type="checkbox"  data-state="${concentrator.coctState}" /></td> --%>
				<td><input name="ids" value="${Charge.pkChargingorder}"
						type="checkbox" data-state="${Charge.settleFlag}"></td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${Charge.chorCode }</td>
				<td>${Charge.eleCode }</td>
				<td>${Charge.cpn }</td>
				<td>${Charge.chargePointName }</td>
				<td>${Charge.ownerShip }</td>
				<%-- <td>${Charge.usName}<c:if
							test="${Charge.usName==null || Charge.usName==''}">${Charge.userPhone }</c:if></td> --%>
				<td>${Charge.exterCardNumber }</td>
				<td>${Charge.userPhone }</td>
				<td>${Charge.chorMoeny }</td>
				<td>${Charge.chorQuantityelectricity }</td>
				<td>${Charge.chorChargemoney }</td>
				<td>${Charge.chorServicemoney }</td>
				<td>${Charge.couponMoney }</td>
				<td>${Charge.chorTimequantum }</td>
				<td>${Charge.chargeTimeMinute }</td>
				<td><c:choose>
						<c:when test="${Charge.chorChargingstatus == '1'}">
							待支付
						</c:when>
						<c:when test="${Charge.chorChargingstatus == '2'}">
							支付成功
						</c:when>
						<c:when test="${Charge.chorChargingstatus == '3'}">
							完成未结算
						</c:when>
						<c:when test="${Charge.chorChargingstatus == '4'}">
							异常订单
						</c:when>
					</c:choose></td>
				<td>
					<%-- <c:if test="${Charge.puhiInvoiceStatus==0}">未开票</c:if>
					    <c:if test="${Charge.puhiInvoiceStatus==1}">已提交</c:if>
					    <c:if test="${Charge.puhiInvoiceStatus==2}">已开票</c:if> --%> <c:choose>
						<c:when test="${Charge.puhiInvoiceStatus==0}">未开票</c:when>
						<c:when test="${Charge.puhiInvoiceStatus==1}">待开票</c:when>
						<c:when test="${Charge.puhiInvoiceStatus==2}">已开票</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</td>
				<td><div class="FengZhi" data-index="${status.index + 1}"
						data-rateTime='${Charge.chReQuantumDate}'
						data-rateJ='${Charge.chReJPrice}'
						data-rateF='${Charge.chReFPrice}'
						data-rateP='${Charge.chRePPrice}'
						data-rateG='${Charge.chReGPrice}'
						data-rateS='${Charge.chReServiceCharge}' data-flag='1'
						onclick="makeRateInfoWindow(this)">查看费率</div></td>
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
	loadCity($("#selProvince"));
	loadArea($("#selCity"));
});

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

function checkSelect(obj) {

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
					  
					  $("#settleAmt").html((datas.chReFrozenAmt) );
					  $("#settlePower").html((datas.power) );
					  $("#settleChargeFee").html((datas.money));
					  var fwFee=datas.power*0.6;
					  $("#settleFwfee").html(fwFee);
					  $("#settleMoney").html((Number(datas.money)+Number(fwFee)).toFixed(4));
					  $("#settleEnd").html((datas.charingTime));
					  $("#orderFlag").html((datas.orderFlag));
					  $("#orderId").html((datas.orderId));
					  $("#chRePayMode").html((datas.chRePayMode));
					  $("#settle").show();
					  
				  }
					
				
				}
			});
		}
	}

}
$("#settleCancel").click(function(){
	$("#settle").hide();
	
})


$("#settleConfirm").click(function(){
	$("#settle").hide();
	var ordersFlag=  $("#orderFlag").text();
	
		$.ajax({
			type : 'post',
			url : "/wanma/admin/order/personSettle.do",
			dataType : "json",
			data :{
				settleAmt:$("#settleAmt").text(),
				settlePower:$("#settlePower").text(),
				settleChargeFee:$("#settleChargeFee").text(),
				settleMoney:$("#settleMoney").text(),
				settleFwfee:$("#settleFwfee").text(),
				settleEnd:$("#settleEnd").text(),
				orderId:$("#orderId").text(),
				chRePayMode:$("#chRePayMode").text(),
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