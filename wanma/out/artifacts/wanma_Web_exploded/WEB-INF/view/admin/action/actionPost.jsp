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
			<li><bmtag:link href="common/postSelectList.do?backRel=actionPostList&processType=actionPost" target="dialog"
					rel="selActionPost" messageKey="common.icon.new"
					dwzClass="add" id="addAcPostLink"/></li>
			<li><bmtag:link
					href="action/removeActionPost.do?postIds={postId}"
					actionId="ACT-001-002"  target="ajax" rel="actionPostList" id="delAcPostLink" 
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="actionPostList">
	<table class="table" width="100%" layoutH="310" >
		<thead>
			<tr>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="company.label.company_name" /></th>
				<th><bmtag:message messageKey="post.label.post_id" /></th>
				<th><bmtag:message messageKey="post.label.post_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${actionModel.postList}" var="post" varStatus="status">
				<tr target="postId" rel="${post.companyId },${post.postId }" align="center">
					<td>${ status.index + 1}</td>
					<td>${post.companyName }</td>
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
