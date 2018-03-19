<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/businessChargeStatistic.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->
					<td style="align:left">
						<span>纯商家名称&nbsp;</span>
						<input name="companyName" value="${params.companyName}" placeholder="纯商家名称" />
					</td>
					<td>
			            <span>充电时间&nbsp;</span>				             	 
			             <input id="startDate_f4" name="startDate" value="${params.startDate}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'startDate_f4',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_f4\')}'})">
			                                    至 <input id="endDate_f4" name="endDate" value="${params.endDate}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'endDate_f4',minDate:'#F{$dp.$D(\'startDate_f4\')}'})">                       	            
		            </td>
		          </tr>
		          <tr>
		            <td style="align:left">
						<span>城市&nbsp;</span>
						<select class="provinceCode required"  id="provinceCodef4" name="provinceCode" next="cityCodef4"  style="float: none;width:130px;">
							<option value="">--请选择省份--</option>
							<c:forEach var="item" items="${proviceMap}">
								<option value="${item.key}"
									${item.key== params.provinceCode ? 'selected="selected"' : ''} >
									${item.value.PROVINCE_NAME}
								</option>
							</c:forEach>
						</select>
						<select id="cityCodef4" data-val="${params.cityCode}" name="cityCode" class="required" style="float: none;width:130px;">
							 <option value="">--请选择城市--</option>
						</select>
					</td>
			
		            <td style="align:left">
					</td>
			
					<td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
			
					<td align="right">
						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','financeExcelReport/getBusinessChargeStatisticData.do')"/>
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
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr align="center">
				<th><bmtag:message messageKey="城市" /></th>
				<th><bmtag:message messageKey="纯商家名称" /></th>
				<th><bmtag:message messageKey="月份" /></th>
				<th><bmtag:message messageKey="总充电次数" /></th>
				<th><bmtag:message messageKey="总电量(度数)" /></th>
				<th><bmtag:message messageKey="收益金额(元)" /></th>
				<th><bmtag:message messageKey="充电电费(元)" /></th>
				<th><bmtag:message messageKey="充电服务费(元)" /></th>
	        </tr>
		</thead>		
		<tbody>
		<c:forEach items="${list}" var="obj" varStatus="status">
			<tr target="objId" align="center">
				<td>${obj['城市']}</td>
				<td>${obj['纯商家名称']}</td>
				<td>${obj['月份']}</td>
				<td>${obj['总充电次数']}</td>
				<td>${obj['总电量(度数)']}</td>
				<td>${obj['收益金额(元)']}</td>
				<td>${obj['充电电费(元)']}</td>
				<td>${obj['充电服务费(元)']}</td>
			</tr>
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
<script type="text/javascript">
$(function(){
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
		$(".pages").html("");
	}
	loadCity($("#provinceCodef4"));
});
</script>
