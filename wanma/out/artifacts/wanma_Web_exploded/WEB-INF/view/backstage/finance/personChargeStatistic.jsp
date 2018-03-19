<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/personChargeStatistic.do" onsubmit="return navTabSearch(this);"> 
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
						<span>手机号&nbsp;</span>
						<input name="userAccount" value="${params.userAccount}" placeholder="手机号" />
					</td>
					<td>
			            <span>充电时间&nbsp;</span>				             	 
			             <input id="startDate_f8" name="startDate" value="${params.startDate}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'startDate_f8',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_f8\')}'})">
			                                    至 <input id="endDate_f8" name="endDate" value="${params.endDate}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'endDate_f8',minDate:'#F{$dp.$D(\'startDate_f8\')}'})">                       	            
		            </td>
		          </tr>
		          <tr>
		          	<td style="align:left">
						<span>姓&nbsp;&nbsp;&nbsp;名</span>
						<input name="normRealName" value="${params.normRealName}" placeholder="姓名" />
					</td>
		            <td style="align:left">
						<span>城市&nbsp;</span>
						<select class="provinceCode required"  id="provinceCodef8" name="provinceCode" next="cityCodef8"  style="float: none;width:130px;">
							<option value="">--请选择省份--</option>
							<c:forEach var="item" items="${proviceMap}">
								<option value="${item.key}"
									${item.key== params.provinceCode ? 'selected="selected"' : ''} >
									${item.value.PROVINCE_NAME}
								</option>
							</c:forEach>
						</select>
						<select id="cityCodef8" data-val="${params.cityCode}" name="cityCode" class="required" style="float: none;width:130px;">
							 <option value="">--请选择城市--</option>
						</select>
					</td>
			
					<td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
			
					<td align="right">
						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','financeExcelReport/getPersonChargeStatisticData.do')"/>
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
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr align="center">
				<th><bmtag:message messageKey="城市" /></th>
				<th><bmtag:message messageKey="手机号" /></th>
				<th><bmtag:message messageKey="姓名" /></th>
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
				<td>${obj['手机号']}</td>
				<td>${obj['姓名']}</td>
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
	loadCity($("#provinceCodef8"));
});
</script>
