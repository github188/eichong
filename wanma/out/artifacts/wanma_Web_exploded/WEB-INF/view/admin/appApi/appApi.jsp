<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var appApiId = "${appApiModel.appApiId}";
	var createUser = "${appApiModel.createUser}";
	$(function() {

		if (appApiId == "") {
			disableItems("init");
		} else if (appApiId != "" && createUser == "") {
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
			$("#searchAppApiId").removeAttr("readonly");
			$("textarea").attr("readonly", true);
			$("#appApiAdd").attr("disabled", true);
			$("#deleteAppApi").attr("disabled", true);
			$("#appApiEdit").attr("disabled", true);
			$("#addAppRoleLink").attr("disabled", true);
			$("#delAppRoleLink").attr("disabled", true);
			$("#addAppPostLink").attr("disabled", true);
			$("#delAppPostLink").attr("disabled", true);
			$("#addAppDeptLink").attr("disabled", true);
			$("#delAppDeptLink").attr("disabled", true);
		} else if ("new" == type) {
			$("#appApiCopyLink").attr("disabled", true);
			$("#deleteAppApi").attr("disabled", true);
			$("#appApiAddLink").attr("disabled", true);
		} else if ("edit" == type) {
			//$("#appApiId").attr("readonly", true);
			$("#appApiId").removeAttr("remote");
		}

		//$("input").attr("readonly", true);
	}

	function onAddClick() {
		var isCheckOK = false;
		document.appApiForm.action = "appApi/saveAppApi.do";
		isCheckOK = document.appApiForm.onsubmit();

		if (undefined == isCheckOK) {
			document.appApiForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.appApiForm.action = "appApi/modifyAppApi.do";
		isCheckOK = document.appApiForm.onsubmit();
		if (undefined == isCheckOK) {
			document.appApiForm.submit();
		}
	}

	function onDeleteClick() {
		var isCheckOK = false;
		document.appApiForm.action = "appApi/removeAppApi.do";
		isCheckOK = document.appApiForm.onsubmit();

		if (undefined == isCheckOK) {
			document.appApiForm.submit();
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
	margin-left: 170px;
}

ul.centerTools li {
	float: left;
	display: block;
	margin-left: 170px;
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
			<bmtag:message messageKey="appApi.title.appApi.new" />
		</h1>
		<div>
			<%-- 
			<ul class="rightTools">
				<li><bmtag:link messageKey="common.button.copy"
						href="appApi/copyAppApi.do?appApiId=${appApiModel.appApiId}" target="ajax"
						rel="appApiBox" dwzClass="button" id="appApiCopyLink" /></li>
				<li><bmtag:link messageKey="common.button.new"
						href="appApi/newAppApi.do?appApiId=${appApiModel.appApiId}" target="ajax"
						rel="appApiBox" dwzClass="button" id="appApiAddLink" /></li>
				<li><bmtag:button messageKey="common.button.delete"
						type="button" id="deleteAppApi" onclick="onDeleteClick();" /></li>
			</ul>
		--%>
		</div>
	</div>
<div class="pageFormContent nowrap" layoutH="97">

			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.base" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="appApi.tab.role_users" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="appApi.tab.department_users" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="appApi.tab.post_users" /></span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div id="appApiBase" class="unitBox" layoutH="234"
						style="overflow: auto;">
						<%@include file="appApiBaseInfo.jsp"%>
					</div>
					<div id="appApiRole" class="unitBox">
						<%@include file="appApiRole.jsp"%>
					</div>
					<div id="appApiDept" class="unitBox">
						<%@include file="appApiDepartment.jsp"%>
					</div>
					<div id="appApiPost" class="unitBox">
						<%@include file="appApiPost.jsp"%>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>

			<br />
			<ul class="centerTools">
				<li><jstltag:if
						test="${appApiModel.createUser==null ||appApiModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="appApiAdd" onclick="onAddClick();" />
					</jstltag:if> <jstltag:if
						test="${appApiModel.createUser!=null && appApiModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="appApiEdit" onclick="onModifyClick();" />
					</jstltag:if></li>
			</ul>
	</div>
</div>