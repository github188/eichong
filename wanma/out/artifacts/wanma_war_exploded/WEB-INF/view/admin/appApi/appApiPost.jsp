<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>

<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/postSelectList.do?backRel=appApiPostList&processType=appApiPost" target="dialog"
					rel="addAppPostLink" messageKey="common.icon.new"
					dwzClass="add" id="addAppPostLink"/></li>
			<li><bmtag:link
					href="appApi/removeAppApiPost.do?postIds={postId}"
					target="ajax" rel="appApiPostList" id="delAppPostLink"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="appApiPostList">
	<table class="table" width="100%" layoutH="310" >
		<thead>
			<tr>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="post.label.post_id" /></th>
				<th><bmtag:message messageKey="post.label.post_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${appApiModel.postList}" var="post" varStatus="status">
				<tr target="postId" rel="${post.companyId },${post.postId }" align="center">
					<td>${ status.index + 1}</td>
					<td>${post.postId }</td>
					<td>${post.postName }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>
