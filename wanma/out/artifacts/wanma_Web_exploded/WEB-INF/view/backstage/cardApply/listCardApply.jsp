<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 


<form id="pagerForm" method="post" action="cardApply/findCardApplyList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>申请人</span>
					<input name="cafRealName" placeholder="请输入" value="${card.cafRealName}"/>
				</td>
				<td>
					<span>充电卡号</span>
					<input name="cafUsercard" placeholder="请输入" value="${card.cafUsercard}"/>
				</td>									
				<td>
			        <span>申请时间&nbsp;</span>
	            
		           	<input name="startTime" value="${card.startTime }" class="date" style="width:155px"
						 onClick="WdatePicker()" >
			                                    至 <input name="endTime" value="${card.endTime }" class="date"  style="width:155px"
			             onClick="WdatePicker()">   
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
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
		<tr align="center" >
			<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
			<th><bmtag:message messageKey="用户"/></th>
		    <th><bmtag:message messageKey="联系人"/></th>
		    <th><bmtag:message messageKey="联系电话"/></th>
		    <th><bmtag:message messageKey="邮寄地址"/></th>
		    <th><bmtag:message messageKey="卡号"/></th>
		    <th><bmtag:message messageKey="申请时间"/></th>
		    <th><bmtag:message messageKey="挂失时间"/></th>
		    <th><bmtag:message messageKey="状态"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="card" varStatus="status">
			<tr target="id" rel="${card.pkCardapplicationform}" align="center">
				<td>
					<input name="ids"  value="${card.pkCardapplicationform}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${card.userAccount}</td>
				<td>${card.cafRealName}</td>
				<td>${card.cafPhone}</td>
				<td>${card.cafAddress}</td>
				<td>${card.cafUsercard}</td>
				<td>
					<fmt:formatDate value="${card.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<fmt:formatDate value="${card.reportLossDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:choose>
						<c:when test="${card.reportLossStatus == 1}">挂失</c:when>
						<c:otherwise>
							<c:if test="${card.cafStatus == 0}">申请中</c:if>
							<c:if test="${card.cafStatus == 1}">申请成功</c:if>
							<c:if test="${card.cafStatus == 2}">申请失败</c:if>
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
