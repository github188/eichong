<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
	function ajaxDoneCallback(json){
	}
</script>
<h2 class="contentTitle"><bmtag:message messageKey="user.title.user.change_password"/></h2>
<div class="pageContent">
<form method="post" action="user/modifyPassword.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	    <input type="hidden" name="userId" value="${tblUser.userId}"/>
		<dl><dt><bmtag:message messageKey="user.label.user_name"/></dt><dd>
		    ${tblUser.userAccount}
			<span class="info"></span></dd></dl>
		<c:choose>
			<c:when test="${user.userLevel == 5}"><dl><dt><bmtag:message messageKey="user.label.now_password"/></dt><dd>
			<input name="nowPassword" type="password" class="required" minlength="6" maxlength="8" style="width:130px;" remote="user/checkNowPassword.do?userId=${tblUser.userId}"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="user.label.insert_password"/></dt><dd>
			<input name="userPassword" type="password" id="userPassword" class="required digits" minlength="6" maxlength="8" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="user.label.re_password"/></dt><dd>
			<input name="rePassword" type="password" equalto="#userPassword" class="required digits" minlength="6" maxlength="8" style="width:130px;"/>
			<span class="info"></span></dd></dl></c:when> 
		<c:otherwise>
			<dl><dt><bmtag:message messageKey="user.label.now_password"/></dt><dd>
				<input name="nowPassword" type="password" class="required alphanumeric" minlength="6" maxlength="20" style="width:130px;" remote="user/checkNowPassword.do?userId=${tblUser.userId}"/>
				<span class="info"></span></dd></dl>
			<dl><dt><bmtag:message messageKey="user.label.insert_password"/></dt><dd>
				<input name="userPassword" type="password" id="userPassword" class="required alphanumeric" minlength="6" maxlength="20" style="width:130px;"/>
				<span class="info"></span></dd></dl>
			<dl><dt><bmtag:message messageKey="user.label.re_password"/></dt><dd>
				<input name="rePassword" type="password" equalto="#userPassword" class="required alphanumeric" minlength="6" maxlength="20" style="width:130px;"/>
				<span class="info"></span></dd></dl>
		</c:otherwise>
			
		</c:choose>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.close"  type="submit" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>