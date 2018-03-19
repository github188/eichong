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
<form id="pagerForm" method="post" action="feelimit/searchServiceLimitList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent" >
			<tbody>				
				<tr>
					<td>
						<span>选择省份&nbsp;</span>
						<select name="provinceId" style="align:left;width:162px" onchange="search()">
							<option value="">所有城市费率 </option>
							<option value="110000" ${params.provinceId == 110000? 'selected="selected"' : ''}>北京市</option>
							<option value="120000" ${params.provinceId == 120000? 'selected="selected"' : ''}>天津市</option>
							<option value="130000" ${params.provinceId == 130000? 'selected="selected"' : ''}>河北省</option>
							<option value="140000" ${params.provinceId == 140000? 'selected="selected"' : ''}>山西省</option>
							<option value="150000" ${params.provinceId == 150000? 'selected="selected"' : ''}>内蒙古自治区</option>
							<option value="210000" ${params.provinceId == 210000? 'selected="selected"' : ''}>辽宁省</option>
							<option value="220000" ${params.provinceId == 220000? 'selected="selected"' : ''}>吉林省</option>
							<option value="230000" ${params.provinceId == 230000? 'selected="selected"' : ''}>黑龙江省</option>
							<option value="310000" ${params.provinceId == 310000? 'selected="selected"' : ''}>上海市</option>
							<option value="320000" ${params.provinceId == 320000? 'selected="selected"' : ''}>江苏省</option>
							<option value="330000" ${params.provinceId == 330000? 'selected="selected"' : ''}>浙江省</option>
							<option value="340000" ${params.provinceId == 340000? 'selected="selected"' : ''}>安徽省</option>
							<option value="350000" ${params.provinceId == 350000? 'selected="selected"' : ''}>福建省</option>
							<option value="360000" ${params.provinceId == 360000? 'selected="selected"' : ''}>江西省</option>
							<option value="370000" ${params.provinceId == 370000? 'selected="selected"' : ''}>山东省</option>
							<option value="410000" ${params.provinceId == 410000? 'selected="selected"' : ''}>河南省</option>
							<option value="420000" ${params.provinceId == 420000? 'selected="selected"' : ''}>湖北省</option>
							<option value="430000" ${params.provinceId == 430000? 'selected="selected"' : ''}>湖南省</option>
							<option value="440000" ${params.provinceId == 440000? 'selected="selected"' : ''}>广东省</option>
							<option value="450000" ${params.provinceId == 450000? 'selected="selected"' : ''}>广西壮族自治区</option>
							<option value="460000" ${params.provinceId == 460000? 'selected="selected"' : ''}>海南省</option>							
							<option value="500000" ${params.provinceId == 500000? 'selected="selected"' : ''}>重庆市</option>
							<option value="510000" ${params.provinceId == 510000? 'selected="selected"' : ''}>四川省</option>
							<option value="520000" ${params.provinceId == 520000? 'selected="selected"' : ''}>贵州省</option>
							<option value="530000" ${params.provinceId == 530000? 'selected="selected"' : ''}>云南省</option>
							<option value="540000" ${params.provinceId == 540000? 'selected="selected"' : ''}>西藏自治区</option>	
							<option value="610000" ${params.provinceId == 610000? 'selected="selected"' : ''}>陕西省</option>	
							<option value="620000" ${params.provinceId == 620000? 'selected="selected"' : ''}>甘肃省</option>	
							<option value="630000" ${params.provinceId == 630000? 'selected="selected"' : ''}>青海省</option>	
							<option value="640000" ${params.provinceId == 640000? 'selected="selected"' : ''}>宁夏回族自治区</option>	
							<option value="650000" ${params.provinceId == 650000? 'selected="selected"' : ''}>新疆维吾尔自治区</option>	
							<option value="710000" ${params.provinceId == 710000? 'selected="selected"' : ''}>台湾省</option>	
							<option value="810000" ${params.provinceId == 810000? 'selected="selected"' : ''}>香港特别行政区</option>										
							<option value="820000" ${params.provinceId == 820000? 'selected="selected"' : ''}>澳门特别行政区</option>					
						</select>						
					</td>
					<td align="right" style="display:none;" >
						<bmtag:button messageKey="common.button.search" type="submit" id="FeelimitformSubmitter"/>
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
				<bmtag:link isAuth="true" target="navTab" href="feelimit/editServiceLimit.do?cityId={cityId}"  
				 rel="editEpsc" messageKey="费率编辑" dwzClass="edit"/>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="114">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="cityIds"
					class="checkboxCtrl" />				
				</th>		
				<th><bmtag:message messageKey="序号" /></th>				
				<th><bmtag:message messageKey="城市名称" /></th>
				<th><bmtag:message messageKey="城市费率" /></th>
			
	        </tr>
		</thead>
			<tbody>
		<c:forEach items="${serviceLimitList}" var="serviceLmt" varStatus="status">
			<tr target="cityId" rel="${serviceLmt.cityId }" align="center">
				<td>
					<input name="cityIds"  value="${serviceLmt.cityId}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${serviceLmt.cityName }</td>
				<td>${serviceLmt.serviceLimit }</td>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
