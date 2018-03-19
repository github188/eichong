<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageContent">
<form id="pagerForm" method="post" action="comquestion/problemHelp.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
</form>
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="comquestion/newQuestion.do"
					target="navTab" rel="questionAddPage" messageKey="新增常见问题"
					dwzClass="add" id="addLink" /></li>
			<li><a class="delete" target="selectedTodo" rel="questionIds"
				postType="string" title="确定删除吗？"
				href="<c:url value='comquestion/deleteQueAll.do'/>" title="批量删除"><span>批量删除</span>
			</a></li>
			<%-- 		<li><bmtag:link href="comquestion/deleteQueAll.do?pageNum=${pager.pageNum }&questionId={questionId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
			</li> --%>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="76">
		<thead>
			<tr>
				<th width="10"><input type="checkbox" group="questionIds"
					class="checkboxCtrl" />
				</th>
				<th>序号</th>
				<th>常见问题</th>
				<th>回复内容</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${questionsList}" var="questions" varStatus="status">
			<tr target="questionId" rel="${questions.questionId }" align="center">
				<td><input name="questionIds"  value="${questions.questionId}"
						type="checkbox" />
					</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${questions.questionName }</td>
				<td>${questions.replyContent }</td>
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
