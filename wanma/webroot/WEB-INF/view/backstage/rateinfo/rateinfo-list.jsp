<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	$(function(){
		loadCity($("#rateinfo_selProvince"));
		loadArea($("#rateinfo_selCity"));
	});
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="rateinfo/getRateInfoList.do"
		onsubmit="return navTabSearch(this)">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> 
			<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
					<div class="searchBar">
						 <table class="searchContent">
							<tbody>
								<tr>
									<td><span>费率id&nbsp;</span> <input name="rateInformation"
							                width="30px" placeholder="请输入"
							                 value="${tblRateInfo.rateInformation }" />
							      </td>
							    </tr>
								<tr>
								<td><span>区域选择</span>
							<select class="provinceCode required"  id="rateinfo_selProvince"  next="rateinfo_selCity"  name="raIn_ProvinceId"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key==tblRateInfo.raIn_ProvinceId ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="rateinfo_selCity"   next="rateinfo_selDistrict" data-val="${tblRateInfo.raIn_CityId}" name="raIn_CityId"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="rateinfo_selDistrict" data-val="${tblRateInfo.raIn_AreaId}" name="raIn_AreaId" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
				            </td> 
				           <td align="right"><bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" />
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
				<bmtag:link isAuth="true" target="navTab" href="rateinfo/addRateInfoUi.do"
					 rel="electricAddPage" messageKey="common.icon.new" dwzClass="add" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="rateinfo/changeRateInfoUi.do?id={id}"
				 rel="rateInfoEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<%-- <li><bmtag:link href="electric/showElectricPile.do?id={id}"
					target="navTab" rel="electricShowPage"
					messageKey="common.icon.view" dwzClass="icon" id="electricShowPage" />
			</li> --%>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="rateinfo/removeRateInfo.do"
			 		rel="ids" postType="string" altKey="确定删除吗？" messageKey="批量删除" dwzClass="delete" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>			
	<%-- 			<th><bmtag:message messageKey="rateinfo.label.effdate" />
				</th>
				<th><bmtag:message messageKey="rateinfo.label.expdate" /> 
				</th>--%>
				<th><bmtag:message messageKey="id" /></th>
				<th><bmtag:message messageKey="rateinfo.label.reserate" />
				</th>
				<th><bmtag:message messageKey="rateinfo.label.servicecharge" />
				</th>
				<th><bmtag:message messageKey="rateinfo.label.quendate" />
				</th>
				<th><bmtag:message messageKey="城市" />
				</th>
				<th><bmtag:message messageKey="尖段电价" />
				</th>
				<th><bmtag:message messageKey="峰段电价" />
				</th>
				<th><bmtag:message messageKey="平段电价" />
				</th>
				<th><bmtag:message messageKey="谷段电价" />
				</th>
				<th><bmtag:message messageKey="添加人" /></th>
				<th><bmtag:message messageKey="公司" /></th>
				<th><bmtag:message messageKey="备注" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rateInfoList}" var="rateinfo" varStatus="status">
				<tr target="id" rel="${rateinfo.pk_RateInformation}" align="center">
					<td><input name="ids" value="${rateinfo.pk_RateInformation}"
						type="checkbox">
					</td>
									
				<%-- 	<td>${rateinfo.raIn_EffectiveDates}</td>
					<td>${rateinfo.raIn_ExpiryDate}</td> --%>
					<td>${rateinfo.pk_RateInformation}</td>
					<td>${rateinfo.raIn_ReservationRate}</td>
					<td>${rateinfo.raIn_ServiceCharge}</td>
					<td width="500px;">${rateinfo.raIn_QuantumDate}</td>
					<td>${rateinfo.CITY_NAME}</td>
					<td>${rateinfo.raIn_TipTimeTariff}</td>
					<td>${rateinfo.raIn_PeakElectricityPrice}</td>
					<td>${rateinfo.raIn_UsualPrice}</td>
					<td>${rateinfo.raIn_ValleyTimePrice}</td>
					<td>${rateinfo.userId}</td>
					<td>${rateinfo.company_name}</td>
					<td>${rateinfo.raInRemarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span style="display: none;"> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})" >
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select></span><span> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
