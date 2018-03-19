<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var roleId = "${roleModel.roleId}";
	var createUser = "${roleModel.createUser}";
	$(function() {
		if (roleId == "") {
			disableItems("init");
		} else if (roleId != "" && createUser == "") {
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
			$("#addRole").attr("disabled", true);
            disabledButton($("#addRole_B"));
			$("#deleteRole").attr("disabled", true);
            disabledButton($("#deleteRole_B"));
            disabledButtonLink($("#roleCopyLink"));
            disabledLink($("#addRoleUserLink"));
            disabledLink($("#delRoleUserLink"));
		} else if ("new" == type) {
			 
            disabledButtonLink($("#roleCopyLink"));
			$("#deleteRole").attr("disabled", true);
            disabledButton($("#deleteRole_B"));
            disabledButtonLink($("#roleAddLink"));
		} else if ("edit" == type) {
			 
			$("#roleId").attr("readonly", true);
			$("#roleId").removeAttr("remote");
		}
	}

	function onAddClick() {
		var isCheckOK = false;
		document.roleForm.action = "role/saveRole.do";
		isCheckOK = document.roleForm.onsubmit();

		if (undefined == isCheckOK) {
			document.roleForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.roleForm.action = "role/modifyRole.do";
		isCheckOK = document.roleForm.onsubmit();
		if (undefined == isCheckOK) {
			document.roleForm.submit();
		}
	}

	function onDeleteClick() {
		var isCheckOK = false;
		document.roleForm.action = "role/removeRole.do";
		isCheckOK = document.roleForm.onsubmit();

		if (undefined == isCheckOK) {
			document.roleForm.submit();
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
			<bmtag:message messageKey="role.title.role.new" />
		</h1>
		<div>
			<ul class="rightTools">
				
				<c:choose>
				  <c:when test="${isSuperUser!=true}">
				    <li><bmtag:link messageKey="common.button.copy"  actionId="ACT-101-002"
						href="role/copyRole.do?roleId=${roleModel.roleId}"
						target="ajax" rel="roleBox" dwzClass="button" id="roleCopyLink" /></li>
				     <li><bmtag:link messageKey="common.button.new"  actionId="ACT-101-001"
						href="role/newRole.do?roleId=${roleModel.roleId}"
						target="ajax" rel="roleBox" dwzClass="button" id="roleAddLink" /></li>
						<li><bmtag:button messageKey="common.button.delete" type="button" actionId="ACT-101-003"
							id="deleteRole" onclick="onDeleteClick();" /></li>
				  </c:when>
				  <c:otherwise>
				    <li><bmtag:link messageKey="common.button.copy"  actionId="ACT-101-002"
						href="role/copyRole.do?roleId=${roleModel.roleId}"
						target="ajax" rel="roleBox" dwzClass="button"   id="roleCopyLink" /></li>
				     <li><bmtag:link messageKey="common.button.new"  actionId="ACT-101-001"
						href="role/newRole.do?roleId=${roleModel.roleId}"
						target="ajax" rel="roleBox" dwzClass="button"   id="roleAddLink" /></li>
						<li><bmtag:button messageKey="common.button.delete" type="button"  actionId="ACT-101-003"
							id="deleteRole" onclick="onDeleteClick();" /></li>
				  </c:otherwise>
				</c:choose>
				
			</ul>
		</div>
	</div>
	<div class="divider"></div>

	<div class="pageFormContent nowrap" layoutH="97">
		<form method="post" action="role/saveRole.do"
			id="roleForm" name="roleForm"
			class="pageForm required-validate" enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.base" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.role_users" /></span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div  layoutH="234">
						<dl>
							<dt>
								<bmtag:message messageKey="role.label.role_id" />
							</dt>
							<dd>
								<input name="roleId"
									class="textInput halfsymbol required" id="roleId"
									value="${roleModel.roleId}"
									remote="role/checkRoleUnique.do" maxlength="20"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="role.label.role_name" />
							</dt>
							<dd>
								<input name="roleName" class="textInput required"
									value="${roleModel.roleName}" maxlength="20"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message
									messageKey="role.label.parent_role_id" />
							</dt>
							<dd>
								<input name="parentRoleId" class="textInput"
									id="parentRoleId"
									value="${roleModel.parentRoleId}" maxlength="20"
									style="width: 130px;" readonly /> <span class="info"></span>
							</dd>
						</dl>
					</div>
					<div id="roleUser"  >
						<%@include file="roleUser.jsp"%>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			<br/>
			<ul class="centerTools">
				<li><jstltag:if
						test="${roleModel.createUser==null ||roleModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="addRole" onclick="onAddClick();" actionId="ACT-101-005"/>
					</jstltag:if> <jstltag:if
						test="${roleModel.createUser!=null && roleModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="modifyRole" onclick="onModifyClick();" actionId="ACT-101-004"/>
					</jstltag:if></li>
			</ul>
		</form>
	</div>
</div>