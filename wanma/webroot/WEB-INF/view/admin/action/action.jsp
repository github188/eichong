<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var actionId = "${actionModel.actionId}";
	var createUser = "${actionModel.createUser}";
	$(function() {

		if (actionId == "") {
			disableItems("init");
		} else if (actionId != "" && createUser == "") {
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
			$("#addAction").attr("disabled", true);
            disabledButton($("#addAction_B"));
			$("#deleteAction").attr("disabled", true);
            disabledButton($("#deleteAction_B"));
			$("#actionEdit").attr("disabled", true);
            disabledButton($("#actionEdit_B"));
            disabledButtonLink($("#actionCopyLink"));
            disabledLink($("#addAcRoleLink"));
            disabledLink($("#delAcRoleLink"));
            disabledLink($("#addAcPostLink"));
            disabledLink($("#delAcPostLink"));
            disabledLink($("#addAcDeptLink"));
            disabledLink($("#delAcDeptLink"));
		} else if ("new" == type) {		
            disabledButtonLink($("#actionCopyLink"));
			$("#deleteAction").attr("disabled", true);
            disabledButton($("#deleteAction_B"));
            disabledButtonLink($("#actionAddLink"));
		} else if ("edit" == type) {
			$("#actionId").attr("readonly", true);
			$("#actionId").removeAttr("remote");
		}

		//$("input").attr("readonly", true);
	}

	function onAddClick() {
		var isCheckOK = false;
		document.actionForm.action = "action/saveAction.do";
		isCheckOK = document.actionForm.onsubmit();

		if (undefined == isCheckOK) {
			document.actionForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.actionForm.action = "action/modifyAction.do";
		isCheckOK = document.actionForm.onsubmit();
		if (undefined == isCheckOK) {
			document.actionForm.submit();
		}
	}

	function onDeleteClick() {
		var isCheckOK = false;
		document.actionForm.action = "action/removeAction.do";
		isCheckOK = document.actionForm.onsubmit();

		if (undefined == isCheckOK) {
			document.actionForm.submit();
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
	margin-left: 220px;
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
			<bmtag:message messageKey="action.title.action.new" />
		</h1>
		<div>

			<ul class="rightTools">
				<li><bmtag:link messageKey="common.button.copy"
						href="action/copyAction.do?actionId=${actionModel.actionId}" target="ajax"
						rel="actionBox" dwzClass="button" id="actionCopyLink" /></li>
				<li><bmtag:link messageKey="common.button.new"
						href="action/newAction.do?actionId=${actionModel.actionId}" target="ajax"
						rel="actionBox" dwzClass="button" id="actionAddLink" /></li>
				<li><bmtag:button messageKey="common.button.delete"
						type="button" id="deleteAction" onclick="onDeleteClick();" /></li>
			</ul>

		</div>
	</div>

	<div class="pageFormContent nowrap" layoutH="97">
		<form method="post" action="action/saveAction.do" id="actionForm"
			name="actionForm" class="pageForm required-validate"
			enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">

			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.base" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="action.tab.role_users" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="action.tab.department_users" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="action.tab.post_users" /></span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div id="actionBase" class="unitBox" layoutH="234"
						style="overflow: auto;">
						<%@include file="actionBaseInfo.jsp"%>
					</div>
					<div id="actionRole" class="unitBox">
						<%@include file="actionRole.jsp"%>
					</div>
					<div id="actionDept" class="unitBox">
						<%@include file="actionDepartment.jsp"%>
					</div>
					<div id="actionPost" class="unitBox">
						<%@include file="actionPost.jsp"%>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>

			<br />
			<ul class="centerTools">
				<li><jstltag:if
						test="${actionModel.createUser==null ||actionModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="addAction" onclick="onAddClick();" />
					</jstltag:if> <jstltag:if
						test="${actionModel.createUser!=null && actionModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="actionEdit" onclick="onModifyClick();" />
					</jstltag:if></li>
			</ul>
		</form>
	</div>
</div>