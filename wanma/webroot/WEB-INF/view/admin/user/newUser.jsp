<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2 class="contentTitle"><bmtag:message messageKey="user.title.user.new"/></h2>
<div class="pageContent">
<form method="post" action="user/saveUser.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	
		<dl><dt><bmtag:message messageKey="用户名"/></dt><dd>
		<input id="adminUserAdd" name="userAccount" value=" " class="textInput userAccount required" remote="user/checkUserUnique.do" maxlength="32" style="width:130px;"/>
		<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="user.label.user_password"/></dt><dd>
			<input id="userPassword" name="userPassword" value="${abc}" type="password"    class="required alphanumeric textInput"  minlength="6" maxlength="20" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="user.label.re_password"/></dt><dd>
			<input name="rePassword" type="password"  value="${userModel.rePassword}" equalto="#userPassword" class="required alphanumeric" minlength="6" maxlength="20" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl>
			<dt>
				<bmtag:message messageKey="姓名" />
			</dt>
			<dd>
				<input name="adminName" maxlength="20" class="textInput required" style="width: 130px;" /> <span
					class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="手机号" />
			</dt>
			<dd>
				<input name="adminPhone" maxlength="11"  class="textInput phone required" remote="user/checkAminUserPhone.do" style="width: 130px;" /> <span
					class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="选择角色" />
			</dt>
			<dd>
			  	<c:forEach var="item" items="${roleList}">
			  		<c:if test="${item.category == '2'}">
						<input class="required" type="checkbox" name="roleId" value="${item.roleId}" />${item.roleName}
					</c:if>
			  	</c:forEach>  
			</dd>
		</dl>	
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
<script type="text/javascript">
$(function() {
	setTimeout("clearUserName()",500)
});	
function clearUserName(){
	$("#adminUserAdd").val("");
}
</script>