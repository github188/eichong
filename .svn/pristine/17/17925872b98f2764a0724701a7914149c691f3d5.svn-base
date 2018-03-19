<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
		$(".pages").html("");
	}
});
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
function checkSelect(obj){
		
		var $this = $(obj);
		var ids = getIds();
		if (ids.length==0) {
			
			alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
			return false;
		}/* else if(ids.length >1){
			
			alertMsg.error("只能选择一条信息！");
		} */else{
			
			navTab.openTab("", basepath+"/admin/powerstation/onlineReasonUi.do", { title:"驳回", fresh:false, data:{id:ids.join()}});
		}

	}
	function getIds(){
		//data-value格式:编码：上线：连接
		var ids = [];
		var $box =navTab.getCurrentPanel();
		$('input[name="ids"]:checked').each(function(){
			ids.push($(this).val());
		});
		if(ids == null || ids.length == 0){
			var trSelected = $box.find("tr.selected");
			var trSelectEdId = trSelected.attr('rel');
			if(trSelectEdId){
				ids.push(trSelectEdId);
			}
		} 
		return ids;
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="powerstation/getOnLinePowersList.do"
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
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
	<c:if test="${loginUserLevel == '1' || loginUserLevel == '2'}">
		<ul class="toolBar">
			 <li><a class="delete" target="selectedTodo" rel="ids" postType="string" title="确定通过吗？"
			      href="<c:url value='powerstation/changePowersState.do?changeType=onLinePage'/>" title="通过"><span>通过</span>
		           </a>
		       </li>
		        <li>
			 	<bmtag:link 
				 	rel="ids" dwzClass="edit" messageKey="驳回"  onclick="checkSelect(this)"/>
			</li>
			<%-- <li><bmtag:link href="powerstation/changePowersState.do?id={id}&changeType=onLinePage" target="ajaxTodo"
					altKey="common.msg.examine.confirm" messageKey="electric.icon.onLine.ok" dwzClass="add"
					/></li> --%>
		</ul>
	</c:if>
	</div>
	<table class="table"  width="100%" layoutH="114">
		<thead>
			<tr align="center">
			   <th width="10"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="powerstation.label.name" /></th>
				<th><bmtag:message messageKey="powerstation.label.address" /></th>
				<th><bmtag:message messageKey="powerstation.label.state" /></th>
				<th><bmtag:message messageKey="powerstation.label.electric" /></th>
				<th><bmtag:message messageKey="powerstation.label.tel" /></th>
				<%-- <th><bmtag:message messageKey="powerstation.label.location" /></th> --%>
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
					    <c:if test="${powerstation.postStatus ==12}" >分享申请中</c:if>
					    <c:if test="${powerstation.postStatus ==3}" >已驳回 </c:if>
					    <c:if test="${powerstation.postStatus ==10}" >专属 </c:if>
					    <c:if test="${powerstation.postStatus ==15}" >分享</c:if></td>
					<td>${powerstation.electricPileCount}</td>
					<td>${powerstation.postPhone}</td>
					<%-- <td><c:if test="${powerstation.postLongitude!=null}" >经度：${powerstation.postLongitude}</c:if><c:if test="${powerstation.postLatitude!=null}" >&nbsp;&nbsp;纬度：${powerstation.postLatitude}</c:if></td> --%>
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
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
