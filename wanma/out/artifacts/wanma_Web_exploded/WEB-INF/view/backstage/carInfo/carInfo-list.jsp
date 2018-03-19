<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
td.rightTools {
	float: right;
}
</style>

<div class="pageHeader">


	<form id="pagerForm" method="post" action="carinfo/findCarList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>	
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="车品牌"/></label>
				</td>
				<td>
					<select  name="carinfoCompanyId" class="select_Style required">
					<option value="">--请选择--</option>
						<c:forEach var="carCompany" items="${carCompanyList}">
							<option value="${carCompany.pk_carCompany}"  ${carCompany.pk_carCompany==carinfo.carinfoCompanyId?'selected="selected"' : ''}>${carCompany.carCompany_Name}</option>
						</c:forEach>
					</select>
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
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
					<bmtag:link isAuth="true" target="navTab"  href="carinfo/newCarinfo.do" 
						 rel="newEpsc" messageKey="添加" dwzClass="add" />
				</li>
				<li>
					<bmtag:link isAuth="true" target="navTab" href="carinfo/editCarinfo.do?pkCarinfo={id}"
						rel="editEpsc" messageKey="编辑" dwzClass="edit" />
				</li>
				<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="carinfo/deleteCarinfos.do"
					 rel="ids" postType="string" altKey="确定要删除吗？" dwzClass="delete" messageKey="批量删除" />
				</li> 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="排序号" /></th>
				<th><bmtag:message messageKey="车型名称" /></th>
				<th><bmtag:message messageKey="车品牌" /></th>
				<th><bmtag:message messageKey="电池类型" /></th>
				<th><bmtag:message messageKey="电池容量" /></th>
				<th><bmtag:message messageKey="充电类型" /></th>
				<th><bmtag:message messageKey="接口标准" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${carList}" var="car" varStatus="status">
				<tr target="id" rel="${car.pkCarinfo}" align="center">
					<td><input name="ids"  value="${car.pkCarinfo}"
						type="checkbox" />
					</td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${car.carinfoStylename}</td>
					<td>${car.carCompany_Name}</td>
					<td>
					<c:if test="${car.carinfoBatteryType == 0}">其他</c:if>
					<c:if test="${car.carinfoBatteryType == 1}">铅酸电池</c:if>
					<c:if test="${car.carinfoBatteryType == 2}">胶体电池</c:if>
					<c:if test="${car.carinfoBatteryType == 3}">锂离子电池</c:if>
					<c:if test="${car.carinfoBatteryType == 4}">镍氢电池</c:if>
					<c:if test="${car.carinfoBatteryType == 5}">锌空电池</c:if>
					<c:if test="${car.carinfoBatteryType == 6}">铅晶蓄电池</c:if>
					</td>
					<td>${car.carinfoBatterycapacity}</td>
					<td>
					<c:if test="${car.carinfoChargingMode == 14}">交流</c:if>
					<c:if test="${car.carinfoChargingMode == 5}">直流</c:if>
					<c:if test="${car.carinfoChargingMode == 1}">交流和直流</c:if>				
					</td>
					<td>
					<c:if test="${car.carinfoPowerInterface == 7}">国标</c:if>
					<c:if test="${car.carinfoPowerInterface == 20}">欧标</c:if>
					<c:if test="${car.carinfoPowerInterface == 19}">美标</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
