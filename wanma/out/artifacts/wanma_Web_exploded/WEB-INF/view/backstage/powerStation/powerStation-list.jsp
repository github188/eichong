<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<style>
  .pCenterRed {
  	color:red;
  	margin-top:5px;
  }
  
  .pCenterRedGreen {
  	color:green;
  	margin-top:5px;
  }
</style>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="powerstation/getPowersList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="powerstation.label.name" /> </label></td>
						<td><input name="postName"
							value="${powerstation.postName}" /></td>
                       <td><label><bmtag:message
									messageKey="powerstation.label.state" /> </label></td>
						<td><select name="postStatus" class="select_Style">
								<option value="">全部</option>
								<option value="0" ${powerstation.postStatus==0
									? 'selected="selected"' : ''}>草稿</option>
								<option value="5" ${powerstation.postStatus==5
									? 'selected="selected"' : ''}>提交审核</option>
								<option value="12" ${powerstation.postStatus==12
									? 'selected="selected"' : ''}>分享申请中</option>
<%-- 								<option value="3" ${powerstation.postStatus==3
									? 'selected="selected"' : ''}>已驳回</option> --%>
								<option value="10" ${powerstation.postStatus==10
									? 'selected="selected"' : ''}>专属</option>
								<option value="15" ${powerstation.postStatus==15
									? 'selected="selected"' : ''}>分享</option>
						</select></td>
							<tr>
						<td>
							<label>区域选择</label>
						</td>
						<td colspan="4">
							<select class="provinceCode required"  id="powerStationSelProvince" next="powerStationSelCity" name="postOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== powerstation.postOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="powerStationSelCity" next="powerStationSelDistrict" data-val="${powerstation.postOwnCityCode}" name="postOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="powerStationSelDistrict" data-val="${powerstation.postOwnCountyCode}" name="postOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
						</td>
							<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" />
						</td>
					</tr>
						
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
				<bmtag:link isAuth="true" target="navTab" href="powerstation/addPowersUi.do"
					 rel="powerOperate" messageKey="common.icon.new" dwzClass="add" id="electricAddPage" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab"  href="powerstation/changePowersUi.do?id={id}"
					rel="powerOperate" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="powerstation/removePower.do" 
					dwzClass="delete"  rel="ids" postType="string" altKey="确定删除吗？" messageKey="批量删除"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="powerstation/showPowers.do?id={id}" 
					rel="electricShowPage" messageKey="common.icon.view" dwzClass="icon"  />
			</li>
		    <li>
		    	<bmtag:link isAuth="true" target="selectedTodo" href="powerstation/changePowersState.do?changeType=examinePage"
		    	 	dwzClass="edit"  rel="ids" postType="string" altKey="确定提审吗？"  messageKey="提审" />
	       </li>
		   <li>
		   		<bmtag:link isAuth="true" target="selectedTodo" href="powerstation/changePowersState.do?changeType=offLinePage"
		   			dwzClass="edit"  rel="ids" postType="string" altKey="确定要专属吗？" messageKey="专属申请" />
	       </li>
	        <li>
		   		<bmtag:link isAuth="true" target="selectedTodo" href="powerstation/changePowersState_02.do?changeType=toInit"
		   			dwzClass="edit"  rel="ids" postType="string" altKey="确定要改为草稿状态吗？" messageKey="状态重置" />
	       </li>
	       <li>
				<bmtag:link isAuth="true"  target="selectedTodo" href="powerstation/shareApplication.do?&changeType=toApplication"
				 rel="ids" postType="string" altKey="确定要分享申请吗？" dwzClass="edit" messageKey="分享申请" />
			</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="140">
		<thead>
			<tr align="center">
			   <th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="powerstation.label.name" /></th>
				<th><bmtag:message messageKey="powerstation.label.address" /></th>
				<th><bmtag:message messageKey="powerstation.label.state" /></th>
				<th><bmtag:message messageKey="是否支持预约" /></th>
				<th><bmtag:message messageKey="powerstation.label.electric" /></th>
				<th><bmtag:message messageKey="powerstation.label.tel" /></th>
				<%-- <th><bmtag:message messageKey="powerstation.label.location" /></th> --%>
				<th><bmtag:message messageKey="驳回原因" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${powerstationList}" var="powerstation" varStatus="status">
				<tr target="id" rel="${powerstation.pkPowerstation}" align="center">
				    <td><input name="ids" value="${powerstation.pkPowerstation}" type="checkbox"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${powerstation.postName}</td>
					<td>${powerstation.postAddress}</td>
					<td><c:if test="${powerstation.postStatus ==0}" >草稿</c:if>
					    <c:if test="${powerstation.postStatus ==5}" >提交审核 </c:if>
                        <c:if test="${powerstation.postStatus ==12}" >分享申请中 </c:if>
					    <c:if test="${powerstation.postStatus ==3}" >已驳回 </c:if>
					    <c:if test="${powerstation.postStatus ==10}" >专属 </c:if>
					    <c:if test="${powerstation.postStatus ==15}" >分享</c:if></td>
					<td>
						<c:if test="${powerstation.postIsappoint == 1}"><p class="pCenterRed">是</p></c:if>
						<c:if test="${powerstation.postIsappoint == 0}"><p class="pCenterRedGreen">否</p></c:if>
					</td>
					<td>${powerstation.electricPileCount}</td>
					<td>${powerstation.postPhone}</td>
					<%-- <td><c:if test="${powerstation.postLongitude!=null}" >经度：${powerstation.postLongitude}</c:if><c:if test="${powerstation.postLatitude!=null}" >&nbsp;&nbsp;纬度：${powerstation.postLatitude}</c:if></td>--%>
					<td>${powerstation.rejectionReason}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select>  -->共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	loadCity($("#powerStationSelProvince"));
	loadArea($("#powerStationSelCity"));
});
</script>