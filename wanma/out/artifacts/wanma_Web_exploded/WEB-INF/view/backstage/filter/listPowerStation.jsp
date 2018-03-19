<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="filter/lookPowerStation.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			<input type="hidden" name="companyId" value="${TblPowerstation.companyId}" />
	 	<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td>
					<span>充电点名称</span>
					<input name="postName"  value="${TblPowerstation.postName}"/>
				
				</td>
				<td><span>区域选择</span>
							<select class="provinceCode required"  id="filter_selProvince"  next="filter_selCity"  name="postOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== TblPowerstation.postOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="filter_selCity"   next="filter_selDistrict" data-val="${TblPowerstation.postOwnCityCode}" name="postOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="filter_selDistrict" data-val="${TblPowerstation.postOwnCountyCode}" name="postOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
						</td>

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
			<li>
				<a target="navTab" id="addPowerStation" title="添加充电点" href="filter/addPowerStation.do?comPanyId=${TblPowerstation.companyId}" class="add"><span>添加充电点</span></a>

			</li> --%>
			<li><bmtag:link isAuth="false" target="navTab"
					href="filter/addPowerStation.do?AddCompanyId=${TblPowerstation.companyId}" rel="addPowerStation"
					messageKey="添加充电点" dwzClass="add" id="addPowerStation" /></li>
			<li class="">
				<a target="selectedTodo" rel="ids" href="filter/removePowerStation.do?RemoveCompanyId=${TblPowerstation.companyId}" class="delete" title="删除充电点，会删除充电点下所有的桩！"><span>批量删除</span></a>
			</li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
			<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
                <th><bmtag:message messageKey="充电点名称" /></th>
				<th><bmtag:message messageKey="充电点ID" /></th>
				<th><bmtag:message messageKey="城市" /></th>
			    <th><bmtag:message messageKey="详细地址" /></th>
			    <th><bmtag:message messageKey="充电站所属公司" /></th>
			     <th><bmtag:message messageKey="充电桩数量" /></th>
				<th  align="center" ><bmtag:message messageKey="查看" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${powerList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkPowerstation}" align="center">
					 <td><input name="ids" value="${item.pkPowerstation}"
						type="checkbox"></td> 
					 <td  title="${item.postName}">${item.postName}</td> 
					<td>${item.pkPowerstation}</td>
					<td>${item.cityName}</td>
					<td>${item.postAddress}</td>
					<td>${item.poStUserName}</td>
					<td>${item.powerStationSum}</td>
					<td>
					<a title="${item.postName}" rel="ok" target="navTab" href="filter/lookPile.do?LookPileCompanyId=${TblPowerstation.companyId}&LookPilePowerId=${item.pkPowerstation}" class="btnView">${item.postName}</a>
                    </td>
                   
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			共${pager.total }条, 共${pager.pageTotal}页
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	loadCity($("#filter_selProvince"));
	loadArea($("#filter_selCity"));
});
	
</script>


