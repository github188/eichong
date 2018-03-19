<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="order/subsUnitOrder.do" onsubmit="return navTabSearch(this);"> 
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
					<span>充电点名称</span>
					<input name="chargePointName" placeholder="请输入" value="${tblBespoke.chargePointName }"/>
				</td>
				
				 <td>
			      <span>预约时间&nbsp;&nbsp;&nbsp;&nbsp;</span>				             	 
			      <input id="startDats_unit" name="bespBeginTimes"  placeholder="请选择" value="${tblBespoke.bespBeginTimes}" class="date" 
						 onClick="WdatePicker({el:'startDats_unit',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDats_unit\')}'})">
			   
			    <span>至 </span>
			    <input id="endDats_unit" name="bespEndTimes"  placeholder="请选择" value="${tblBespoke.bespEndTimes}" class="date"  
			           onClick="WdatePicker({el:'endDats_unit',minDate:'#F{$dp.$D(\'startDats_unit\')}'})">                       	            
		        </td>
				
				</tr>

			<tr>
				<td>
					<span>订单状态</span>
					<select name="bespOrderType" style="width:155px">
						<option value="">全部</option>
						<option value="0" ${tblBespoke.bespOrderType == "0"?"selected":""}>未支付</option>
						<option value="1" ${tblBespoke.bespOrderType == "1"?"selected":""}>订单结束</option>
						<option value="2" ${tblBespoke.bespOrderType == "2"?"selected":""}>完成未结算</option>
					</select>
				</td>
				<%-- <td>
			       <span>预约开始时间</span>
			        <input id="besp_BeginTime" placeholder="请选择" name="bespBeginTimes" value="${tblBespoke.bespBeginTimes }" class="date" onClick="WdatePicker()" dateFmt="yyyy-MM-dd HH:mm:ss" >
			                  
		        </td> --%>
		        <td>
					<span>充电点地址</span>
					<input name="chargePointAddress" placeholder="请输入" value="${tblBespoke.chargePointAddress }"/>
				</td>
		        <td>
					<span>桩体编号</span>
					<input name="eleCode" placeholder="请输入" value="${tblBespoke.eleCode }"/>
				</td>
		        <td></td>
			<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
				<td align="right">
   						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','orderExport/unitBespoke.do')"/>
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
			<th width="10"><input type="checkbox" group="pkBespokes"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th>
		    <th><bmtag:message messageKey="商家名称"/></th>
		    <th><bmtag:message messageKey="桩体编号"/></th>
		    <th><bmtag:message messageKey="充电点名称"/></th>
		    <th><bmtag:message messageKey="充电点地址"/></th>
		    <%-- <th><bmtag:message messageKey="预约流水号"/></th> --%>
		    <%-- <th><bmtag:message messageKey="用户姓名"/></th> --%>
		  <%--   <th><bmtag:message messageKey="手机账号"/></th> --%>
		    <th><bmtag:message messageKey="收益(元)"/></th>
		    <th><bmtag:message messageKey="预约开始时间"/></th>
		    <%-- <th><bmtag:message messageKey="预约结束时间"/></th> --%>
		    <th><bmtag:message messageKey="实际预约结束时间"/></th>
		    <th><bmtag:message messageKey="订单状态"/></th>
		    
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${unitBespokeList}" var="Bespoke" varStatus="status">
			<tr target="pkBespoke" rel="${Bespoke.pkBespoke }" align="center">
				<td>
					<input name="pkBespokes"  value="${Bespoke.pkBespoke }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${Bespoke.bespResepaymentcode }</td>
				<td>${Bespoke.comName}</td>
				<td>${Bespoke.eleCode }</td>
				<td>${Bespoke.chargePointName }</td>
				<td>${Bespoke.chargePointAddress }</td>
				<!-- <td>-</td> -->
				<%-- <td>${Bespoke.username }</td> --%>
				<%-- <td>${Bespoke.usPhone }</td> --%>
				<td>${Bespoke.bespBespokeprice }</td>
				<td>
					<fmt:formatDate value="${Bespoke.bespBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td><fmt:formatDate value="${Bespoke.bespEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
				<td><fmt:formatDate value="${Bespoke.bespRealityTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:choose>
						<c:when test="${Bespoke.bespOrderType == '0'}">
							未支付
						</c:when>
						<c:when test="${Bespoke.bespOrderType == '1'&& tblBespoke.bespBespokestatus!='7'}">
							订单结束
						</c:when>
						<c:when test="${tblBespoke.bespBespokestatus =='7'}">
							完成未结算
						</c:when>
					</c:choose>
				</td>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
