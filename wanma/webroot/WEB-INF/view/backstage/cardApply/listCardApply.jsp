<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />

<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
 <link href="<%=request.getContextPath()%>/static/css/bannerList.css" rel="stylesheet" /> 
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
						<span>申请状态</span>
						<select name="cafStatus"  class="select_Style">
							<option value="">选择申请状态</option>
							<option value="0" ${card.cafStatus == 0?"selected":""}>申请中</option>
							<option value="1" ${card.cafStatus == 1?"selected":""}>申请成功</option>
							<option value="2" ${card.cafStatus == 2?"selected":""}>申请不成功</option>
							<option value="3" >挂失</option>      
						</select>
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
				<div class="myEditForTypeSpan" id="cardReject">驳回</div> 					
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
					<input name="ids" class="cardApplyCheckbox" value="${card.pkCardapplicationform}" type="checkbox" 
						data-value="${card.pkCardapplicationform}" data-values="${card.cafStatus}" />
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
							<c:if test="${card.cafStatus == 2}">申请不成功</c:if>
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
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
if(userIdForShow==8231){
	$(".pages").html("");
}
});

var pkCardAppFromIds="";
var cds="";
$("#cardReject").click(function(){
	pkCardAppFromIds="";
	$(".cardApplyCheckbox").each(function(){
		var pkCardAppFromId = $(this).attr('data-value');
		var cdv=$(this).attr('data-values');
		if ($(this).attr('checked')) {
			pkCardAppFromIds=pkCardAppFromIds+pkCardAppFromId+":";
			cds+=cdv;
		}
	});
	if(pkCardAppFromIds.length>0){
		alertMsg.confirm("已选择"+cds.length+"条记录，确定取消申请吗？", {
			okCall: function(){
				if(parseInt(cds)>0){
						alertMsg.error('取消申请失败');
						navTab.reloadFlag("findCardApplyList");
						return;
				}else{
					$.ajax({
						type : 'post',
						url : basepath+ "/admin/cardApply/updateCardStatus.do",
						dataType : "json",
						data :{
							pkCardAppFromIds:pkCardAppFromIds
						},
						success : function(json) {
								navTab.reloadFlag("findCardApplyList");
								alertMsg.info('取消申请成功');
						}
					});
				}
			},
		 cancelCall : function() {
			 pkCardAppFromIds="";
				cds="";
		 }
		})
	}else{
		alertMsg.error("请选择至少一条记录！");
		return;
	}
})

</script>