<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	$(function(){
		$.get(webroot+"/codegroup/codeList.do?codeGroupId=CD_G_001",{},function(data){
			$("<option value=''></option>").appendTo($("#actionLevelList_actionList"));	
			var json = eval("("+data+")");
			$.each(json, function(i){
				$("<option value='"+json[i].codeId+"'>"+json[i].codeName+"</option>").appendTo($("#actionLevelList_actionList"));	
			});
			$("#formSubmitter").removeAttr("disabled");
		});
	});
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader" style="width: 350px;"> 
<form id="pagerForm" method="post" action="action/actionList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="action.label.action_id"/></label>
				</td>
				<td>
					<input name="actionId"  value="${actionModel.actionId }"/>
				</td>
				<td>
					<label><bmtag:message messageKey="action.label.action_contents"/></label>
				</td>
				<td>
					<input name="actionContents" value="${actionModel.actionContents }"/>
				</td>
				<td>
					<input name="pageId"  value="${actionModel.pageId }"/>
				</td>
				<td>
					<label><bmtag:message messageKey="action.label.page_contents"/></label>
				</td>
				<td>
					<input name="pageContents" value="${actionModel.pageContents }"/>
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
			<li><bmtag:link href="action/newAction.do"  
				target="navTab" rel="actionAddPage" messageKey="common.icon.new" dwzClass="add"  id="addLink"/></li>
			<li><bmtag:link href="action/removeAction.do?pageNum=${pager.pageNum }&actionId={actionId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/></li>
			<li><bmtag:link href="action/editAction.do?pageNum=${pager.pageNum }&actionId={actionId}"
				target="navTab" rel="actionEditPage" messageKey="common.icon.edit" dwzClass="edit" /></li>
			<li><bmtag:link href="action/viewAction.do?pageNum=${pager.pageNum }&actionId={actionId}"
				target="navTab" rel="actionViewPage" messageKey="common.icon.view" dwzClass="icon"/></li>
		</ul>
	</div>
	<table class="table" width="99%" layoutH="250">
		<thead>
		<tr>
		       <th><bmtag:message messageKey="action.label.action_id"/></th>
		       <th><bmtag:message messageKey="action.label.action_contents"/></th>
		       <th><bmtag:message messageKey="action.label.page_contents"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${actionList}" var="action" varStatus="status">
			<tr target="actionId" rel="${action.actionId }" align="center">
				<td>${action.actionId }</td>
				<td>${action.actionContents }</td>
				<td>${action.pageContents }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
	</div>
</div>
