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
<form id="pagerForm" method="post" action="order/subsOrder.do" onsubmit="return navTabSearch(this);"> 
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
					<input name="bespResepaymentcode" placeholder="请输入" value="${tblBespoke.bespResepaymentcode }"/>
				</td>
				<td>
					<span>手机号&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<input name="usPhone" placeholder="请输入" value="${tblBespoke.usPhone }"/>
				</td>
				
				
				  <td>
			      <span>预约时间</span>				             	 
			      <input id="startDats_f5" name="bespBeginTimes"  placeholder="请选择" value="${tblBespoke.bespBeginTimes}" class="date" 
						 onClick="WdatePicker({el:'startDats_f5',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDats_f5\')}'})">
			    <span>至 </span>
			    <input id="endDats_f5" name="bespEndTimes"  placeholder="请选择" value="${tblBespoke.bespEndTimes}" class="date"  
			           onClick="WdatePicker({el:'endDats_f5',minDate:'#F{$dp.$D(\'startDats_f5\')}'})">                       	            
		        </td>
				
				</tr><tr>
				<td>
					<span>用户姓名</span>
					<input name="username" placeholder="请输入"  value="${tblBespoke.username }"/>
				</td>
				<%-- <td>
			       <span>预约开始时间</span>
			        <input id="besp_BeginTime" placeholder="请选择" name="bespBeginTimes" value="${tblBespoke.bespBeginTimes }" class="date" onClick="WdatePicker()" dateFmt="yyyy-MM-dd HH:mm:ss" >
			                  
		        </td>
		        <td></td> --%>
		      
		        <td>
					<span>订单状态</span>
					<select name="bespOrderType" style="width:155px">
						<option value="">全部</option>
						<option value="0" ${tblBespoke.bespOrderType == "0"?"selected":""}>未支付</option>
						<option value="1" ${tblBespoke.bespOrderType == "1"?"selected":""}>订单结束</option>
						<option value="2" ${tblBespoke.bespOrderType == "2"?"selected":""}>完成未结算</option>
					</select>
				</td>
				<td>
					<span>桩体编号</span>
					<input name="eleCode" placeholder="请输入" value="${tblBespoke.eleCode }"/>
				</td>
				<td>
					<span>开票状态</span>
					<select name="puhiInvoiceStatus" style="width:155px">
						<option value="">全部</option>
						<option value="0" ${tblBespoke.puhiInvoiceStatus == "0"?"selected":""}>未开票</option>
						<option value="1" ${tblBespoke.puhiInvoiceStatus == "1"?"selected":""}>待开票</option>
						<option value="2" ${tblBespoke.puhiInvoiceStatus == "2"?"selected":""}>已开票</option>
					</select></td>
		       	<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td> 	 
			 <td align="right">
   						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','orderExport/normalBespoke.do')"/>
			   </td>
			</tr>
		</tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="122">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkBespokes"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th>
		    <th><bmtag:message messageKey="桩体编号"/></th>
		    <th><bmtag:message messageKey="电桩地址"/></th>
		    <th><bmtag:message messageKey="电桩所有权"/></th>
		     <th><bmtag:message messageKey="用户姓名"/></th>
		      <th>用户手机</th>
		    <th><bmtag:message messageKey="预约单价(元)"/></th>
		    <th><bmtag:message messageKey="金额(元)"/></th>
		    <th><bmtag:message messageKey="预约开始时间"/></th>
		    <th><bmtag:message messageKey="预约结束时间"/></th>
		    <th><bmtag:message messageKey="实际预约结束时间"/></th>
		     <th><bmtag:message messageKey="订单状态"/></th>
		     <th><bmtag:message messageKey="开票状态"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${BespokeList}" var="Bespoke" varStatus="status">
			<tr target="pkBespoke" rel="${Bespoke.pkBespoke }" align="center">
				<td>
					<input name="pkBespokes"  value="${Bespoke.pkBespoke }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${Bespoke.bespResepaymentcode }</td>
				<td>${Bespoke.eleCode }</td>
				<td>${Bespoke.chargePointAddress }</td>
				<td>${Bespoke.ownerShip }</td>
				<td>${Bespoke.username }<c:if test="${Bespoke.username==''}">${Bespoke.userPhone }</c:if></td>
				<td>${Bespoke.userPhone }</td>
				<td>${Bespoke.bespBespokeprice }</td>
				<td>${Bespoke.bespFrozenamt }</td>
				<td>
					<fmt:formatDate value="${Bespoke.bespBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${Bespoke.bespEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${Bespoke.bespRealityTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><c:if test="${Bespoke.bespOrderType==0}" >未支付</c:if>
				<c:if test="${Bespoke.bespOrderType==1  && tblBespoke.bespBespokestatus!='7'}" >订单结束</c:if>
				<c:if test="${tblBespoke.bespBespokestatus=='7'}" >完成未结算</c:if></td>
					<td>
				     <c:choose>  
					      <c:when test="${Bespoke.puhiInvoiceStatus==0}">未开票</c:when>  
					      <c:when test="${Bespoke.puhiInvoiceStatus==1}">待开票</c:when>  
					      <c:when test="${Bespoke.puhiInvoiceStatus==2}">已开票</c:when>  
					      <c:otherwise>-</c:otherwise> 
					  </c:choose>
				    </td>
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
