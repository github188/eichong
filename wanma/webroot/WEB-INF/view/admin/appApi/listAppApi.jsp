<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
	function setHrefData() {
		var inputData = "";
		var searchHref = "";
		var searchHrefPre = "";

		inputData = $("#searchAppApiId").attr("value");
		searchHref = $("#btnAppApiSearch").attr("href");
		searchHrefPre = searchHref.substring(0, searchHref.lastIndexOf("?"));

		if (searchHrefPre == "") {
			searchHrefPre = searchHref;
		}
		searchHref = searchHrefPre + "?searchAppApiId=" + inputData;
		$("#btnAppApiSearch").attr("href", searchHref);
	}
</script>
<div class="pageHeader" style="width: 330px;">
	<form id="searchAppApiForm" method="post"
		action="appApi/searchAppApiList.do" name="searchAppApiForm">
		<div class="searchBar">
			<table>
				<tbody>
					<tr align="center">
						<td><label><bmtag:message
									messageKey="appApi.label.appApi_id" /></label></td>
						<td><input name="searchAppApiId" id="searchAppApiId"
							value="${searchAppApiModel.appApiId}" onchange="setHrefData();" /></td>
						<td>&nbsp;&nbsp;</td>
						<td style="float: right;"><bmtag:link
								messageKey="common.button.search"
								href="appApi/searchAppApiList.do?searchAppApiId=${searchAppApiModel.appApiId}"
								target="ajax" rel="appApiTopBox" dwzClass="button"
								id="btnAppApiSearch" />
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="appApi/newAppApi.do" target="ajax"
					rel="appApiBox" messageKey="common.icon.new" dwzClass="add" /></li>
			<li><bmtag:link
					href="appApi/editAppApi.do?appApiId={editAppApiId}" target="ajax"
					rel="appApiBox" messageKey="common.icon.edit" dwzClass="edit" /></li>
			<li><bmtag:link
					href="appApi/removeAppApi.do?appApiId={editAppApiId}"
					altKey="common.msg.delete.confirm" target="ajaxTodo" rel="appApiBox"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="250">
		<thead>
			<tr>
				<th><bmtag:message messageKey="appApi.label.appApi_id" /></th>
				<th><bmtag:message messageKey="appApi.label.appApi_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${appApiList}" var="appApi" varStatus="status">
				<tr target="editAppApiId" rel="${appApi.appApiId }" align="center">
					<td>${appApi.appApiId }</td>
					<td>${appApi.appApiName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
