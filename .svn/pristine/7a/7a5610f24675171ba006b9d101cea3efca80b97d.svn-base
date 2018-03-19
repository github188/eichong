<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var postId = "${postModel.postId}";
	var createUser = "${postModel.createUser}";
	$(function() {
		if (postId == "") {
			disableItems("init");
		} else if (postId != "" && createUser == "") {
			disableItems("new");
		} else {
			disableItems("edit");
		}
	});
	function ajaxDoneCallback(json) {
	}

	function disableItems(type) {
		if ("init" == type) {
			$("input").attr("readonly", true);
			$("textarea").attr("readonly", true);
			$("#addPost").attr("disabled", true);
            disabledButton($("#addPost_B"));
			$("#deletePost").attr("disabled", true);
            disabledButton($("#deletePost_B"));
            disabledButtonLink($("#postCopyLink"));
            disabledLink($("#addPostUserLink"));
            disabledLink($("#delPostUserLink"));
		} else if ("new" == type) {
            disabledButtonLink($("#postCopyLink"));
			$("#deletePost").attr("disabled", true);
            disabledButton($("#deletePost_B"));
            disabledButtonLink($("#postCopyLink"));
		} else if ("edit" == type) {
			$("#postId").attr("readonly", true);
			$("#postId").removeAttr("remote");
		}
	}

	function onAddClick() {
		var isCheckOK = false;
		document.postForm.action = "post/savePost.do";
		isCheckOK = document.postForm.onsubmit();

		if (undefined == isCheckOK) {
			document.postForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.postForm.action = "post/modifyPost.do";
		isCheckOK = document.postForm.onsubmit();
		if (undefined == isCheckOK) {
			document.postForm.submit();
		}
	}

	function onDeleteClick() {
		var isCheckOK = false;
		document.postForm.action = "post/removePost.do";
		isCheckOK = document.postForm.onsubmit();

		if (undefined == isCheckOK) {
			document.postForm.submit();
		}
	}
</script>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.centerTools {
	float: left;
	display: block;
}

ul.centerTools li {
	float: left;
	display: block;
	margin-left: 220px;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<div class="panel" defH="25">
		<h1>
			<jstltag:if
				test="${postModel.createUser==null ||postModel.createUser==''}">
				<bmtag:message messageKey="post.title.new_post" />
			</jstltag:if>
			<jstltag:if
				test="${postModel.createUser!=null && postModel.createUser != ''}">
				<bmtag:message messageKey="post.title.edit_post" />${postModel.postName}
					</jstltag:if>
		</h1>
		<div>
			<ul class="rightTools">
				<li><bmtag:button messageKey="common.button.delete"
						type="button" id="deletePost" onclick="onDeleteClick();" /></li>
			</ul>
		</div>
	</div>
	<div class="divider"></div>

	<div class="pageFormContent nowrap" layoutH="97">
		<form method="post" action="post/savePost.do" id="postForm"
			name="postForm" class="pageForm required-validate"
			enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.base" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="post.tab.title.post_users" /></span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div layoutH="234">
						<dl>
							<dt>
								<bmtag:message messageKey="post.label.post_id" />
							</dt>
							<dd>
								<input name="postId" class="textInput halfsymbol required"
									id="postId" value="${postModel.postId}"
									remote="post/checkPostUnique.do?companyId=${postModel.companyId}"
									maxlength="20" style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="post.label.post_name" />
							</dt>
							<dd>
								<input name="postName" class="textInput required"
									value="${postModel.postName}" maxlength="20"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="post.label.company_id" />
							</dt>
							<dd>
								<input name="companyId" class="textInput required" id="companyId"
									value="${postModel.companyId}" maxlength="20"
									style="width: 130px;" readonly /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="post.label.sort_key" />
							</dt>
							<dd>
								<input name="sortKey" class="textInput digits required"
									id="sortKey" value="${postModel.sortKey}" maxlength="4"
									style="width: 80px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="post.label.notes" />
							</dt>
							<dd>
								<textarea name="notes" style="width: 230px;">${postModel.notes}</textarea>
								<span class="info"></span>
							</dd>
						</dl>
					</div>
					<div id="postUser" class="unitBox">
						<%@include file="postUser.jsp"%>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			<br/>
			<ul class="centerTools">
				<li><jstltag:if
						test="${postModel.createUser==null ||postModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="addPost" onclick="onAddClick();" />
					</jstltag:if> <jstltag:if
						test="${postModel.createUser!=null && postModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="modifyPost" onclick="onModifyClick();" />
					</jstltag:if></li>
			</ul>
		</form>
	</div>
</div>