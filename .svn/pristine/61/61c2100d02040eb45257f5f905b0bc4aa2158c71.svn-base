<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";
</script>

<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/BillOrder.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/> 
	<input type="hidden" name="pkCompanyId" value="${pkCompanyId}"/> 
	<input type="hidden" name="userId" value="${userId}"/> 
		<input  id="userids" type="hidden"  value="${userId}"/> 
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->	
					<td style="align:left">
			            <span>充电时间&nbsp;</span>				             	 
			             <input id="createDates_f2" name="createDates" value="${params.createDates}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'createDates_f2',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'updateDates_f2\')}'})">
			                                    至 <input id="updateDates_f2" name="updateDates" value="${params.updateDates}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'updateDates_f2',minDate:'#F{$dp.$D(\'createDates_f2\')}'})">                       	            
		            </td>
					<td><span>区域选择</span>
							<select class="provinceCode required"  id="bill_selProvince"  next="bill_selCity"  name="postOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== params.postOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="bill_selCity"   next="bill_selDistrict" data-val="${params.postOwnCityCode}" name="postOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="bill_selDistrict" data-val="${params.postOwnCountyCode}" name="postOwnCountyCode" class="required"  style="float: none;width:130px;">
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
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter_bill"/>
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
			 <li>
			 <bmtag:link isAuth="false" id="addOrder"
					           dwzClass="add" messageKey="添加" />
			</li> 
		</ul>
</div>
    <table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
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
			<c:forEach items="${orderList}" var="order" varStatus="status">
				<tr target="id" rel="${order.PurchaseHistory}" align="center">
					<td><input name="ids" value="${order.PurchaseHistory}" type="checkbox"   class="OrderCheckboxs"  id="${order.PurchaseHistory}"  data-value="${order.PurchaseHistory }"  data-value2="${order.ChargeMoney } "  data-value3="${order.ServiceMoney } "  data-value4="${order.discountChangMoney } "  data-value5="${order.discountServiceMoney }"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
                    <td>${order.Code }</td>
					<td>${order.TimeQuantum}</td>
					<td>${order.ChargeMoney }</td>
					<td>${order.ServiceMoney }</td>
					<td>${order.discountChangMoney }</td>
					<td>${order.discountServiceMoney }</td>
					<td>${order.PileNumber}</td>
					<td>${order.postName }</td>
					<td>${order.ElectricPileAddress}</td>
					 <td>
					 <c:if test="${order.ownerCompany ==0 }">其他</c:if>
					 <c:if test="${order.ownerCompany ==1 }">爱充网</c:if>
					 <c:if test="${order.ownerCompany ==2 }">国网</c:if>
					 <c:if test="${order.ownerCompany ==3 }">特斯拉</c:if>
					 </td>
					<td>
					<c:if test="${order.ChargingStatus ==1 }">待支付</c:if>
					<c:if test="${order.ChargingStatus ==2 }">支付成功</c:if>
					<c:if test="${order.ChargingStatus ==3 }">完成操作</c:if>
					<c:if test="${order.ChargingStatus ==4 }">异常订单</c:if>
					<c:if test="${order.ChargingStatus ==5 }">临时结算</c:if>
					</td>
					<td>
					<c:if test="${order.InvoiceStatus ==0}">未开票</c:if>
					<c:if test="${order.InvoiceStatus ==1 }">已提交</c:if>
					<c:if test="${order.InvoiceStatus ==2 }">已开票</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>

<script type="text/javascript">
$(function(){
	loadCity($("#bill_selProvince"));
	loadArea($("#bill_selCity"));
});
<%--添加--%>
$("#addOrder").click(function(){
	var PurchaseHistorys="";
	var sum1=0;
	var sum2=0;
	var sum3=0;
	var sum4=0;
 	$(".OrderCheckboxs").each(function(){
		var PurchaseHistory=$(this).attr('data-value');
		var ChargeMoney=$(this).attr('data-value2');
		var ServiceMoney=$(this).attr('data-value3');
		var discountChargeMoney=$(this).attr('data-value4');
		var discountServiceMoney=$(this).attr('data-value5');
		if ($("#"+PurchaseHistory).attr('checked')) {
		     sum1=parseFloat(sum1)+parseFloat(ChargeMoney);
		     sum2=parseFloat(sum2)+parseFloat(ServiceMoney);
		     sum3=parseFloat(sum3)+parseFloat(discountChargeMoney);
		     sum4=parseFloat(sum4)+parseFloat(discountServiceMoney);
		     PurchaseHistorys=PurchaseHistorys+PurchaseHistory+",";
		}
	}); 
 	if(PurchaseHistorys.length>0){
		 alertMsg.confirm("总电费为:"+sum1.toFixed(4)+"总服务费为:"+sum2.toFixed(4) +"总优惠后电费为:"+sum3.toFixed(4) +" 总优惠后服务费为:"+ sum4.toFixed(4) +"" , {
	    		okCall: function(){
	    			var ids=PurchaseHistorys;
	    			var userId=$("#userids").val();
	    			navTab.openTab("", basepath+"/admin/finance/newInvoiceRecord.do", { title:"添加开票记录", fresh:false, data:{userId:userId,PurchaseHistorys:ids}});
	    		}
	    		});
	}else{
		return;
	}  
});
	
</script>




