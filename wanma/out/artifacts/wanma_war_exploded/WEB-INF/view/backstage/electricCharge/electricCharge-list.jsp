<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function search(){
		$("#FeelimitformSubmitter").click();
	}	
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="electricCharge/getElectricChargeList.do" onsubmit="return navTabSearch(this);"> 
	<div class="searchBar">
		<table class="searchContent" >			
		</table>
	</div>
</form>
</div>
<div class="pageContent">
<div class="panelBar">
		<ul class="toolBar">
			<li>
				<bmtag:link isAuth="true" target="navTab" href="electricCharge/editElectricCharge.do?provinceId={PROVINCE_ID}"  
				 rel="editEpsc" messageKey="电费编辑" dwzClass="edit"/>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="124">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="PROVINCE_IDS"
					class="checkboxCtrl" />				
				</th>		
				<th><bmtag:message messageKey="序号" /></th>				
				<th><bmtag:message messageKey="省份名称" /></th>
				<th><bmtag:message messageKey="尖段电费" /></th>
				<th><bmtag:message messageKey="峰段电费" /></th>
				<th><bmtag:message messageKey="平段电费" /></th>
				<th><bmtag:message messageKey="谷段电费" /></th>
			
	        </tr>
		</thead>
			<tbody>
		<c:forEach items="${electricChargeList}" var="electricCharge" varStatus="status">
			<tr target="PROVINCE_ID" rel="${electricCharge.PROVINCE_ID}" align="center">
				<td>
					<input name="PROVINCE_IDS"  value="${electricCharge.PROVINCE_ID}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${electricCharge.PROVINCE_NAME }</td>
				<td>${electricCharge.Tip_Electricity }</td>
				<td>${electricCharge.Peak_Electricity }</td>
				<td>${electricCharge.Flat_Electricity }</td>
				<td>${electricCharge.Valley_Electricity }</td>			
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
