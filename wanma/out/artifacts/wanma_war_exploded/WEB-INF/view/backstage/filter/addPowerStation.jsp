<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="filter/addPowerStation.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" /> <input
			type="hidden" name="companyId" value="${TblPowerstation.companyId}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>充电点名称</span> <input name="postName"
							value="${TblPowerstation.postName}" /></td>
						<td><span>区域选择</span> <select class="provinceCode required"
							id="filter_addProvince" next="filter_addCity"
							name="postOwnProvinceCode" style="float: none; width: 130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== TblPowerstation.postOwnProvinceCode ? 'selected="selected"' : ''}>
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
						</select> <select class="cityCode required" id="filter_addCity"
							next="filter_addDistrict"
							data-val="${TblPowerstation.postOwnCityCode}"
							name="postOwnCityCode" style="float: none; width: 130px;">
								<option value="">--请选择城市--</option>
						</select> <select id="filter_addDistrict"
							data-val="${TblPowerstation.postOwnCountyCode}"
							name="postOwnCountyCode" class="required"
							style="float: none; width: 130px;">
								<option value="">--请选择区/县--</option>
						</select></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_power" /></td>

					</tr>
				</tbody>
			</table>
		</div>

	</form>
</div>
<div class="pageContent">
	<div class="panelBar">


		<ul class="toolBar">

			<%-- 
			<li class="">
				<a target="selectedTodo" rel="ids" href="filter/savePowerStation.do?companyId=${TblPowerstation.companyId}" class="add" title="确定为该合作商添加此充电点下的所有桩吗？"><span>确认添加</span></a>

			</li> --%>
			<li><bmtag:link isAuth="false" target="selectedTodo"
					altKey="确定为该合作商添加此充电点下的所有桩吗？"
					href="filter/savePowerStation.do?companyId=${TblPowerstation.companyId}"
					rel="ids" messageKey="确认添加" dwzClass="add" /></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<%-- <th><bmtag:message messageKey="编号" /></th> --%>
				<th><bmtag:message messageKey="充电点名称" /></th>
				<th><bmtag:message messageKey="充电点ID" /></th>
				<th><bmtag:message messageKey="城市" /></th>
				<th><bmtag:message messageKey="详细地址" /></th>
				<th><bmtag:message messageKey="充电站所属公司" /></th>
				<th><bmtag:message messageKey="充电桩数量" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allPwList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkPowerstation}" align="center">
					<td><input name="ids" value="${item.pkPowerstation}"
						type="checkbox"></td>
					<td title="${item.postName}">${item.postName}</td>
					<td>${item.pkPowerstation}</td>
					<td>${item.cityName}</td>
					<td>${item.postAddress}</td>
					<td>${item.poStUserName}</td>
					<td>${item.powerStationSum}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	loadCity($("#filter_addProvince"));
	loadArea($("#filter_addCity"));
});
	
</script>

