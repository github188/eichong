<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	function showContent(this_e) {
		alertMsg.info($(this_e).html());
	}
</script>
<style type="text/css">
.dd_content {
	width: 180px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	course: hand;
}
</style>
<div class="pageHeader">
<form id="pagerForm" method="post" action="feedback/feedbackList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->
					<td style="align:left">
						<span>用户账号&nbsp;</span>
						<input name="phone" value="${feedback.phone }" placeholder="搜索关键字" />
					</td>
					
					<td>
			            <span>提交时间&nbsp;</span>				             	 
			             <input id="feedback_createDate" name="startDate" value="${feedback.startDate }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'feedback_createDate',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'feedback_lastUpdateDate\')}'})">
			                                    至 <input id="feedback_lastUpdateDate" name="endDate" value="${feedback.endDate}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'feedback_lastUpdateDate',minDate:'#F{$dp.$D(\'feedback_createDate\')}'})">                       	            
		            </td>
		            <td style="align:left">
						<span>处理状态&nbsp;</span>
						<select name="processStatus" class="select_Style">
							<option value="">请选择</option>			
							<option value="0">未处理</option>					
							<option value="1">接收正在处理</option>
							<option value="2">处理完成</option>							
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
			<li><bmtag:link authUrl="feedback/editFeedback.do?feedbackId={feedbackId}"
				 rel="editEpsc" messageKey="编辑处理原因" dwzClass="edit" onclick="checkSelect(this)"/></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="feedbackIds"
					class="checkboxCtrl" />
				
				</th>						
				<th><bmtag:message messageKey="序号" /></th>
				<th><bmtag:message messageKey="用户账号" /></th>
				<th><bmtag:message messageKey="联系人" /></th>
				<th><bmtag:message messageKey="提交时间" /></th>
				<th><bmtag:message messageKey="意见反馈(文本描述)" /></th>
				<th><bmtag:message messageKey="处理状态" /></th>
				<th width="500px;"><bmtag:message messageKey="处理原因" /></th>
				<th><bmtag:message messageKey="回复人账号" /></th>
	        </tr>
		</thead>		
		<tbody>
		<c:forEach items="${feedbackList}" var="feedback" varStatus="status">
			<tr target="feedbackId" rel="${feedback.feedbackId }" data-state="${feedback.processStatus}" align="center">
				<td>
					<input name="feedbackIds"  value="${feedback.feedbackId}"
						type="checkbox" data-state="${feedback.processStatus}" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${feedback.phone }</td>
				<td>${feedback.userIname }</td>
				<td>
					<fmt:formatDate value="${feedback.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><div class='dd_content' onclick='showContent(this)'>${feedback.content }</div></td>
				<td >
					<c:if test="${feedback.processStatus == 0}">未处理</c:if>
				  	<c:if test="${feedback.processStatus == 1}">处理中</c:if>
				  	<c:if test="${feedback.processStatus == 2}">已处理</c:if>
				</td>
				<td>${feedback.reason }</td>
				<td>${feedback.replyUserAccount }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">

/**
 * 校验是否可编辑
 */
function checkSelect(obj){
	var $this = $(obj);
	var ids = getIds();
	if (ids.length==0) {
		alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
		return false;
	}else if(ids.length > 1){
		alertMsg.error("只能选择一条信息！");
	}else{
		var idAndState = ids[0].split(',')
		if(idAndState[1] == 2){
			alertMsg.error("已处理完成，无需再处理！");
		}else{
			navTab.openTab("concentratorEditPage", basepath+"/admin/feedback/editFeedback.do?feedbackId="+idAndState[0], { title:"编辑", fresh:false});
		}
	}

}

/**
 * 获取选中的ID
 */
function getIds(){
	var $box =navTab.getCurrentPanel();
	var ids = [];
	$box.find("input:checked").filter("[name='ids']").each(function(i){
		ids.push($(this).attr("value")+','+$(this).attr("data-state"));
	})
	if(ids == null || ids.length == 0){
		var trSelected = $box.find("tr.selected");
		var trSelectEdId = trSelected.attr('rel');
		if(trSelectEdId){
			ids.push(trSelectEdId+','+ trSelected.attr("data-state"));
		}
	}
	return ids;
}
</script>
