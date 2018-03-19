<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function checkDetails(){
		
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="moneyRecord/getPartnerMoneyRecordList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
					<td style=" padding-left: 25px;">
						<span>公司名称&nbsp;</span>
						<input name="companyName" value="${params.companyName }" placeholder="搜索公司名称" />
					</td>				
					<td >
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
				</tr>
			 </tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="85">
			
		<thead>
			<tr align="center">					
				<th width="30" ><divmtag:message messageKey="序号" /></th>
				<th><bmtag:message messageKey="公司名称" /></th>
				<th><bmtag:message messageKey="公司标示" /></th>
				<th><bmtag:message messageKey="总消费(元)" /></th>
	        </tr>
		</thead>		
		<tbody>
		 
		<c:forEach items="${partnerMoneyRecordList}" var="partnerMoneyRecord" varStatus="status">
			<tr target="puHi_UserId" rel="${partnerMoneyRecord.puHi_UserId }" align="center">			
				<td value="${partnerMoneyRecord.pk_PurchaseHistory}"  name="pk_PurchaseHistory" >${ status.index + 1 }</td>
				<td><a target="navTab"  href="<c:url value='/admin/moneyRecord/getPartnerMoneyRecordDetials.do?chOrOrgNo=${partnerMoneyRecord.companyNameNumber }&all_account=${partnerMoneyRecord.busi_account }'/>" style='color:#0099FF'>${partnerMoneyRecord.companyName}</a></td>
				<td>${partnerMoneyRecord.companyNameNumber}</td>
				<td><fmt:formatNumber value="${partnerMoneyRecord.totalChargeMoney*5 }" pattern="0.00"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
</script>