<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style type="text/css">
td.rightTools {
	float: right;
}

.dd_content {
	width: 180px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	course: hand;
}
</style>
<script type="text/javascript">
	function showContent(this_e) {
		alertMsg.info($(this_e).html());
	}
</script>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="message/findMessageList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
				<li><bmtag:link href="message/newMessage.do" 
						target="navTab" rel="newEpsc" messageKey="添加" dwzClass="add" /></li>
				<li><bmtag:link href="message/editMessage.do?id={id}"
						 target="navTab" rel="editEpsc" messageKey="编辑"
						dwzClass="edit" /></li>
				<li><bmtag:link href="message/push.do?id={id}"
						target="ajaxTodo" altKey="common.msg.push.confirm" messageKey="推送"
						dwzClass="edit" /></li>
				<li><bmtag:link href="message/deleteById.do?id={id}"
						target="ajaxTodo" altKey="common.msg.delete.confirm"
						messageKey="common.icon.delete" dwzClass="delete" /></li>
				<li><a class="delete" target="selectedTodo" rel="ids"
					postType="string" title="确定要批量删除吗？"
					href="<c:url value='/admin/message/deleteMessages.do'/>"
					title="批量删除"><span>批量删除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="87">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="排序号" /></th>
				<th><bmtag:message messageKey="收信人" /></th>
				<th><bmtag:message messageKey="发送人" /></th>
				<th><bmtag:message messageKey="标题" /></th>
				<th><bmtag:message messageKey="内容" /></th>
				<th><bmtag:message messageKey="发送时间" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${messageList}" var="message" varStatus="status">
				<tr target="id" rel="${message.id}" align="center">
					<td><input name="ids"  value="${message.id}"
						type="checkbox" />
					</td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>
						${empty message.userName?message.userPhone:message.userName}
						<c:if test="${message.userName==null }">爱充网</c:if>
					</td>
					<td>${message.fromUsername}</td>
					<td>${message.title}</td>
					<td title="${message.content}">
					<div class='dd_content' onclick='showContent(this)'>${message.content}</div>
					</td>
					<td><fmt:formatDate value="${message.editTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
