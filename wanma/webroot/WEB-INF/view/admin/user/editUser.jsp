<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	var webroot = "${webroot}";
	$("#w_validation_pwd_temp").hide();
	function ajaxDoneCallback(json) {
	}

</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.edit" />
</h2>
<div class="pageContent">
	<form method="post" action="user/modifyUser.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="用户名" />
				</dt>
				<dd>
					<input type="hidden" name="userId" value="${user.userId}" />
					<input type="hidden" name="userLevel" value="${user.userLevel}" />
					<input name="userAccount" class="textInput userAccount required"
						readonly value="${user.userAccount}" maxlength="32"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="姓名" />
				</dt>
				<dd>
					<input name="adminName" value="${user.adminName}" maxlength="20" class="textInput required" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="手机号" />
				</dt>
				<dd>
					<input name="adminPhone" value="${user.adminPhone}" idName="userId" data-id="${user.userId}" remote="user/checkAminUserPhone.do" maxlength="11"  class="textInput phone required" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<!-- <dl>
				<dt>密码：</dt>
				<dd><input id="w_validation_pwd_temp" type="password" style="display:none"/>
				<input id="w_validation_pwd" type="password" name="userPassword" value=""
						class="required alphanumeric" minlength="6" maxlength="20" />
				</dd>
			</dl> -->
			<dl>
				<dt>
					<bmtag:message messageKey="选择角色" />
				</dt>
				<dd>
				  	<c:forEach var="item" items="${roleList}">
				  		<c:if test="${item.category == '2'}">
					  		<input class="required" type="checkbox" name="roleId" value="${item.roleId}" 
								<c:forEach var="selectItem" items="${selectRoleList}">
									<c:if test="${item.roleId==selectItem.roleId}">checked</c:if>
								</c:forEach>
							/>${item.roleName}
						</c:if>
				  	</c:forEach>  
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
	</form>
</div>