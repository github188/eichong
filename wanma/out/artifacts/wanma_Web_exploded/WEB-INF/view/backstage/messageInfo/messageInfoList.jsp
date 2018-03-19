<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div class="pageHeader">
<form id="pagerForm" method="post" action="messageInfo/messageInfoList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>标题</span> <input name="messageInfoName"
							placeholder="请输入" value="${messageInfo.messageInfoName }" /></td>
						<td><span>类型</span> 
						<select name="messageInfoType">
							<option value="">请选择</option>
							<option value="1" ${messageInfo.messageInfoType== 1 ? 'selected="selected"' : ''}>故障</option>
							<option value="2" ${messageInfo.messageInfoType== 2 ? 'selected="selected"' : ''}>新建</option>
						</select>
						</td>
						<td>
							<span>区域选择</span>
						</td>
						<td  >
							<select class="provinceCode required"  id="messageInfoSelProvince" data-val="${messageInfo.messageInfoProvinceCode}" next="messageInfoSelCity" name="messageInfoProvinceCode"  style="float: left;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== messageInfo.messageInfoProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="required" id="messageInfoSelCity" data-val="${messageInfo.messageInfoCityCode}"   name="messageInfoCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
					</tr>
					<tr>
						<td><span>开始时间</span> <input id="startDate_messageInfoBegintime"
							name="messageInfoBegintimeStartTime" placeholder="请选择"
							value="${messageInfo.messageInfoBegintimeStartTime}" class="date"
							onClick="WdatePicker({el:'startDate_messageInfoBegintime',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_messageInfoBegintime\')}'})"></td><td>
						<span>至  </span><input
							id="endDate_messageInfoBegintime" name="messageInfoBegintimeEndTime"
							value="${messageInfo.messageInfoBegintimeEndTime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_messageInfoBegintime',minDate:'#F{$dp.$D(\'startDate_messageInfoBegintime\')}'})">
						</td>
						<td><span>关闭时间</span></td><td> <input id="startDate_messageInfoEndtime"
							name="messageInfoEndtimeStartTime" placeholder="请选择"
							value="${messageInfo.messageInfoEndtimeStartTime}" class="date"
							onClick="WdatePicker({el:'startDate_messageInfoEndtime',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_messageInfoEndtime\')}'})">
						<span>至</span><input
							id="endDate_messageInfoEndtime" name="messageInfoEndtimeEndTime"
							value="${messageInfo.messageInfoEndtimeEndTime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_messageInfoEndtime',minDate:'#F{$dp.$D(\'startDate_messageInfoEndtime\')}'})">
						</td>
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
	<div class="panelBar">
		<ul class="toolBar">
			<li>
					<bmtag:link isAuth="true" target="navTab" href="messageInfo/addMessageInfoUi.do"
						rel="messageInfoAddPage" messageKey="common.icon.new" dwzClass="add" id="messageInfoAddPage" />
			</li>
		 	<li>
					<bmtag:link isAuth="true" target="navTab" href="messageInfo/editMessageInfoUi.do?id={id}"
						 rel="messageInfoEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="messageInfo/closeMessageInfo.do"
						rel="id"  altKey="确定关闭该条消息？" dwzClass="delete" messageKey="关闭" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="messageInfo/deleteMessageInfo.do"
						rel="id"  altKey="确定删除该条消息？" dwzClass="delete" messageKey="删除" />
			</li>
			
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="135">
		<thead>
			<tr align="center">
				<th style="width:5%"><bmtag:message messageKey="common.label.index" /></th>
				<th style="width: 5%"><bmtag:message messageKey="类型" /></th>
				<th style="width: 10%"><bmtag:message messageKey="标题" /></th>
				<th style="width: 10%"><bmtag:message messageKey="地区" /></th>
				<th style="width: 20%"><bmtag:message messageKey="开始时间" /></th>
				<th style="width: 20%"><bmtag:message messageKey="发布/编辑时间" /></th>
				<th style="width: 20%"><bmtag:message messageKey="关闭时间" /></th>
				<th style="width: 10%"><bmtag:message messageKey="状态" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${messageInfoList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkMessageInfoId}:${item.messageInfoPageStatus}" align="center">
					<td style="width:7%">${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>
						<c:if test="${item.messageInfoType == 1 }">故障</c:if>
						<c:if test="${item.messageInfoType == 2 }">新建</c:if>
					</td>
					<td>${item.messageInfoName}</td>
					<td>${item.messageInfoRegion}</td>
					<td><fmt:formatDate value="${item.messageInfoBegintime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${item.messageInfoUpdatedate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:set var="messageInfoEndtime"> <fmt:formatDate value="${item.messageInfoEndtime}" pattern="yyyy-MM-dd HH:mm:ss" /></c:set>  
							<c:choose>
								<c:when test="${messageInfoEndtime == '2030-01-01 00:00:00' }"></c:when>
								<c:otherwise>${messageInfoEndtime}</c:otherwise>
							</c:choose>
					<td>
						${item.messageInfoPageStatus} 
					</td>
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
	loadCity($("#messageInfoSelProvince"));
	loadArea($("#messageInfoSelCity"));
});
</script>
