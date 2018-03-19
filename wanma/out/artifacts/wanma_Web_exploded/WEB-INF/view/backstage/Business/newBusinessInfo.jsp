<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.new" />
</h2>
<div class="pageContent">
	<form method="post" action="userManager/saveBusinessList.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="爱充网账号" />
				</dt>
				<dd>
					<input name="userAccount" maxlength="32" remote="userManager/checkBusinessAcount.do" class="textInput userAccount required" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="姓名" />
				</dt>
				<dd>
					<input name="busiName" maxlength="20" class="textInput required" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="手机号" />
				</dt>
				<dd>
					<input name="busiPhone" maxlength="11"  class="textInput phone required" remote="userManager/checkBusinessUserPhone.do"  style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="登录密码" />
				</dt>
				<dd>
					<input type="password" name="userPassword" minlength="6" maxlength="20" class="textInput userAccount required"  style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>
			<c:if test="${loginUser.userLevel<=2}">
				<dl>
					<dt>
						<bmtag:message messageKey="所属企业" />
					</dt>
					<dd>
						<select name="busiCompanyId"  style="width: 135px !important" class="required select_Style" >
						 <option value="">-请选择-</option>
							  <c:forEach var="item" items="${companyList}">
								<option value="${item.pkCompanyid}">${item.cpyCompanyname}</option>
							</c:forEach>  
						</select> <span class="info"></span>
					</dd>
				</dl>
			</c:if>
			<dl>
				<dt>
					<bmtag:message messageKey="选择角色" />
				</dt>
				<dd>
				  	<c:forEach var="item" items="${roleList}">
						<input class="required" type="checkbox" name="roleId" value="${item.roleId}" />${item.roleName}
				  	</c:forEach>  
				</dd>
			</dl>
			<%-- <dl>
				<dt>
					<bmtag:message messageKey="状态" />
				</dt>
				<dd>
					<select name="userStatus" style="width: 136px;">
						<option value="1">正常</option>
						<option value="2">禁用</option>
					</select> <span class="info"></span>
				</dd>
			</dl> --%>

		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
