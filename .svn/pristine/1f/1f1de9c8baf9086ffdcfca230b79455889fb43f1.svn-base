<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electric-list.js" />
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="electric/getElectricPileHeadList.do"
		onsubmit="return divSearch(this,'relBox');">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="elPiOwnCityCode" value="${tblElectricpile.elPiOwnCityCode}" />
		<input type="hidden" name="elPiOwnProvinceCode" value="${tblElectricpile.elPiOwnProvinceCode}" />
		<input type="hidden" name="elPiOwnCountyCode" value="${tblElectricpile.elPiOwnCountyCode}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="充点电名称" /> </label>
						</td>
						<td><input name="elpiElectricpilename"
							value="${tblElectricpile.elpiElectricpilename}" />
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="充点电地址" /> </label>
						</td>
						<td><input name="elpiElectricpileaddress"
							value="${tblElectricpile.elpiElectricpileaddress}" />
						</td>
						<td align="right"><bmtag:button
							messageKey="common.button.search" type="submit"
							id="formSubmitter" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="95">
		<thead>
			<tr>
				<th>充点电名称</th>
				<th>充点电地址</th>
				<th>电桩总数</th>
				<th>联网总数</th>
				<th>空闲总数</th>
				<th>异常总数</th>
				<th>车位空闲数量</th>
				<th>已预约总量</th>
				<th>正在充电点数</th>
				<th>公共/专属</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="electricitem" items="${electricList}">
			<tr>
				<td>
					<a href="javascript://" onclick="openDetailTab(this)" ref="<c:if test="${electricitem.type == 1}">/admin/electricpile/detailMap.do?eid=${electricitem.pkId}</c:if><c:if test="${electricitem.type == 2}">/admin/powerstation/detailMap.do?eid=${electricitem.pkId}</c:if>">${electricitem.name}</a>
				</td>
				<td>
					${electricitem.address}
				</td>
				<td>
					${electricitem.pileCount}
					
				</td>
				<td>
					${electricitem.linkCount}
					
				</td>
				<td>
					${electricitem.freeHeadCount}
					
				</td>
				<td>
					${electricitem.faultCount}
					
				</td>
				<td>
					${electricitem.carSpacesCount}
					
				</td>
				<td>
					${electricitem.bespokeCount}
					
				</td>
				<td>
					${electricitem.chargingCount}
				</td>
				<td>
					${electricitem.shareCount} / ${electricitem.ownerCount}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
			<input type="hidden" name="status" value="${pager.status}" /> 
			<input type="hidden" name="keywords" value="${pager.keywords}" /> 
			<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
			<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	</div>
	<div class="panelBar">
		<div class="pages">
			<!-- <span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value});pageChange()">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select>  -->共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" rel="relBox"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
